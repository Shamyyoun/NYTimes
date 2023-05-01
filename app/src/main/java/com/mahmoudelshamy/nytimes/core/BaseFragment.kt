package com.mahmoudelshamy.nytimes.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import com.mahmoudelshamy.nytimes.R
import com.mahmoudelshamy.nytimes.common.NavigationCommand

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    protected abstract val viewModel: VM

    protected lateinit var binding: VB

    protected abstract fun onReady(savedInstanceState: Bundle?)

    protected abstract fun createViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNavigation()
        onReady(savedInstanceState)
    }

    private val navOptions = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner) {
            handleNavigationCommand(it)
        }
    }

    private fun handleNavigationCommand(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirection -> findNavController().navigate(
                command.direction,
                navOptions
            )
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    protected fun showView(view: View, vararg viewsToBeHidden: View) {
        view.isVisible = true
        viewsToBeHidden.forEach {
            it.isVisible = false
        }
    }
}
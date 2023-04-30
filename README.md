
# NY Times App

A sample Android project to apply latest technologies and architectures in Android.





## Tech Stack
* Clean architecture (Oninon architecture).
* MVI for presentation.
* Dagger Hilt for DI.
* Kotlin coroutines.
* Navigation component.
* Retrofit with OkHTTP for networking.
* Coil for image loading.

## Testing Stack
* JUnit 4.
* PowerMockito.
* Turbine for flow testing.
* Espresso for UI testing.
* JaCoCo for code coverage.


## Getting Started
First connect your device or run the emulator.
* To install the app to your device enter this command `./gradlew :app:installDebug`, then launch the app in your device.
* To run the unit tests enter this command `./gradlew :app:testDebugUnitTest`.
* To run the android instrumentation tests enter this command `./gradlew :app:connectedDebugAndroidTest`.
* To generate code coverage report enter this command `./gradlew testDebugUnitTestCoverage`, then you can find the report in `/app/build/reports/jacoco/testDebugUnitTestCoverage/html/index.html`.

package com.mahmoudelshamy.nytimes.features.articles.data

object FakeArticlesData {
    const val articlesJson = "{\n" +
            "  \"status\": \"OK\",\n" +
            "  \"copyright\": \"Copyright (c) 2023 The New York Times Company.  All Rights Reserved.\",\n" +
            "  \"num_results\": 4,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"id\": 100000008874473,\n" +
            "      \"asset_id\": 100000008874473,\n" +
            "      \"source\": \"New York Times\",\n" +
            "      \"published_date\": \"2023-04-26\",\n" +
            "      \"updated\": \"2023-04-28 15:27:38\",\n" +
            "      \"section\": \"Business\",\n" +
            "      \"subsection\": \"Media\",\n" +
            "      \"nytdsection\": \"business\",\n" +
            "      \"column\": null,\n" +
            "      \"type\": \"Article\",\n" +
            "      \"title\": \"Article 1\",\n" +
            "      \"abstract\": \"Content for article 1\",\n" +
            "      \"per_facet\": [\n" +
            "        \"Carlson, Tucker\",\n" +
            "        \"Murdoch, Lachlan\",\n" +
            "        \"Murdoch, Rupert\",\n" +
            "        \"Grossberg, Abby\"\n" +
            "      ],\n" +
            "      \"eta_id\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 100000008874473,\n" +
            "      \"asset_id\": 100000008874473,\n" +
            "      \"source\": \"New York Times\",\n" +
            "      \"published_date\": \"2023-04-26\",\n" +
            "      \"updated\": \"2023-04-28 15:27:38\",\n" +
            "      \"section\": \"Business\",\n" +
            "      \"subsection\": \"Media\",\n" +
            "      \"nytdsection\": \"business\",\n" +
            "      \"column\": null,\n" +
            "      \"type\": \"Article\",\n" +
            "      \"title\": \"Article 2\",\n" +
            "      \"abstract\": \"Content for article 2\",\n" +
            "      \"per_facet\": [\n" +
            "        \"Carlson, Tucker\",\n" +
            "        \"Murdoch, Lachlan\",\n" +
            "        \"Murdoch, Rupert\",\n" +
            "        \"Grossberg, Abby\"\n" +
            "      ],\n" +
            "      \"eta_id\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 100000008874473,\n" +
            "      \"asset_id\": 100000008874473,\n" +
            "      \"source\": \"New York Times\",\n" +
            "      \"published_date\": \"2023-04-26\",\n" +
            "      \"updated\": \"2023-04-28 15:27:38\",\n" +
            "      \"section\": \"Business\",\n" +
            "      \"subsection\": \"Media\",\n" +
            "      \"nytdsection\": \"business\",\n" +
            "      \"column\": null,\n" +
            "      \"type\": \"Article\",\n" +
            "      \"title\": \"Article 3\",\n" +
            "      \"abstract\": \"Content for article 3\",\n" +
            "      \"per_facet\": [\n" +
            "        \"Carlson, Tucker\",\n" +
            "        \"Murdoch, Lachlan\",\n" +
            "        \"Murdoch, Rupert\",\n" +
            "        \"Grossberg, Abby\"\n" +
            "      ],\n" +
            "      \"eta_id\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 100000008874473,\n" +
            "      \"asset_id\": 100000008874473,\n" +
            "      \"source\": \"New York Times\",\n" +
            "      \"published_date\": \"2023-04-26\",\n" +
            "      \"updated\": \"2023-04-28 15:27:38\",\n" +
            "      \"section\": \"Business\",\n" +
            "      \"subsection\": \"Media\",\n" +
            "      \"nytdsection\": \"business\",\n" +
            "      \"column\": null,\n" +
            "      \"type\": \"Article\",\n" +
            "      \"title\": \"Article 4\",\n" +
            "      \"abstract\": \"Content for article 4\",\n" +
            "      \"per_facet\": [\n" +
            "      ],\n" +
            "      \"eta_id\": 0\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    const val emptyArticlesJson = "{\n" +
            "  \"status\": \"OK\",\n" +
            "  \"copyright\": \"Copyright (c) 2023 The New York Times Company.  All Rights Reserved.\",\n" +
            "  \"num_results\": 20,\n" +
            "  \"results\": []\n" +
            "}"

    var articlesResponse = Pair(200, articlesJson)
}
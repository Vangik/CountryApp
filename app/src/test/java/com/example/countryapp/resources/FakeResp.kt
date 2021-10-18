package com.example.countryapp.resources

object FakeResp {
    const val COUNTRY_LIST = "{\n" +
            "  \"data\": {\n" +
            "    \"countries\": [\n" +
            "      {\n" +
            "      \"__typename\": \"Country\",\n" +
            "      \"code\": \"AD\",\n" +
            "      \"emoji\": \"\uD83C\uDDE6\uD83C\uDDE9\",\n" +
            "      \"name\": \"Andorra\",\n" +
            "      \"capital\": \"Andorra la Vella\",\n" +
            "      \"native\": \"Andorra\",\n" +
            "      \"continent\": {\n" +
            "        \"__typename\": \"Continent\",\n" +
            "        \"code\": \"EU\",\n" +
            "        \"name\": \"Europe\"\n" +
            "      },\n" +
            "      \"currency\": \"EUR\",\n" +
            "      \"phone\": \"376\",\n" +
            "      \"languages\": [\n" +
            "        {\n" +
            "          \"__typename\": \"Language\",\n" +
            "          \"name\": \"Catalan\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }]" +
            "  }" +
            "}"
    const val COUNTRY_BY_ID ="{\n" +
            "  \"data\": {\n" +
            "    \"country\": \n" +
            "      {\n" +
            "      \"__typename\": \"Country\",\n" +
            "      \"code\": \"AD\",\n" +
            "      \"emoji\": \"\uD83C\uDDE6\uD83C\uDDE9\",\n" +
            "      \"name\": \"Andorra\",\n" +
            "      \"capital\": \"Andorra la Vella\",\n" +
            "      \"native\": \"Andorra\",\n" +
            "      \"continent\": {\n" +
            "        \"__typename\": \"Continent\",\n" +
            "        \"code\": \"EU\",\n" +
            "        \"name\": \"Europe\"\n" +
            "      },\n" +
            "      \"currency\": \"EUR\",\n" +
            "      \"phone\": \"376\",\n" +
            "      \"languages\": [\n" +
            "        {\n" +
            "          \"__typename\": \"Language\",\n" +
            "          \"name\": \"Catalan\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }" +
            "  }" +
            "}"

    const val COUNTRY_NAME = "Andorra"
    const val COUNTRY_CAPITAL = "Andorra la Vella"
    const val COUNTRY_REGION = "Europe"
    const val COUNTRY_ID = "AD"

//    const val ERROR = "{\n" +
//        " \"errors\": [\n" +
//        " { \n" +
//            "\"message\"": "\"Cannot query field \"specie\" on type \"Country\". Did you mean \"species\"?"\",
//            "locations": [
//            {
//                "line": 6,
//                "column": 13
//            }
//            ],
//            "extensions": {
//            "code": "GRAPHQL_VALIDATION_FAILED"
//        }
//        }
//        ]
//    }"
}
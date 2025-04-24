package tests

import utils.TestProperties
import utils.JsonProcessor

open class Tests {

    internal fun runTest(
        type: String,
        caseID: String,
        action: String,
        jsonParameters: Map<String, String>
    ) {
        val configFolder = "data/test_cases/$type/$caseID/$action/"
        TestProperties.setProperty("type", type)
        TestProperties.setProperty("caseId", caseID)
        TestProperties.setProperty("action", action)

        val processedJson = JsonProcessor.processedJson(configFolder + "TestDescription.json", jsonParameters)
        //executeTest(processedJson)
    }
}
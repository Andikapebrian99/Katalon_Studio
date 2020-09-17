import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

response_yuk = WS.sendRequest(findTestObject('MS Auth/Post_Token_Success', [('url') : GlobalVariable.url]))

def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(response_yuk.getResponseBodyContent())

def value = result.message.token

println('Token is generated correctly: ' + value)

GlobalVariable.auth = value

WS.sendRequestAndVerify(findTestObject('MS Agent Performance Tracking/Get_Agent_Performance-Positive'))

WS.sendRequestAndVerify(findTestObject('MS Agent Performance Tracking/Get_Agent_Performance-Negative-No_auth'))

WS.sendRequestAndVerify(findTestObject('MS Agent Performance Tracking/Get_Agent_PErformance-Negative-Invalid_Token'))

WS.sendRequestAndVerify(findTestObject('MS Agent Performance Tracking/Get_Agent_Performance-Negative-Empty_Auth'))


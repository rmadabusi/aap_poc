'''
@author: Treselle
'''
import urllib2
import json
from api_gateway.test_cases.config.integrationtest_config import urlheader_backend, urlheader_gateway, backend_service, gateway_service

class comparision(object):
    
    def __init__(self):
        super(comparision, self).__init__()
        print "Process started"
        self.backendService()
        self.gatewayservice()
    
    '''Taking Backend service response'''
    def backendService(self):
        self.Step1 = "Step 1: Processing Microservice response"
        microservice_response = urllib2.urlopen(urlheader_backend+backend_service)       
        self.backendService_data = json.load(microservice_response)       
    
    '''Taking Python service response'''
    def gatewayservice(self):
        self.Step2 = "Step 2: Processing Gateway Service service"
        microservice_response = urllib2.urlopen(urlheader_gateway+gateway_service)
        self.gatewayservice_data = json.load(microservice_response)            
    
    '''Test scenarios'''     
    def testScenario(self):         
        self.testScenario1 =  "1. To check the response data availability"
        self.testScenario2 =  "2. Comparing Gateway_Api and Microservice response results"          
        print "Test scenarios\n"+self.testScenario1+"\n"+self.testScenario2
        print "\nTest Result"
        self.testResult()   
             
    '''Test cases'''    
    def testResult(self):
        self.responseEmpty()
        self.responseComparision()
        self.htmlreport()
    
    '''To check the response data availability for gatewayService and backendService'''       
    def responseEmpty(self):       
        if len(self.gatewayservice_data['response']) == 0 and len(self.backendService_data['response']) == 0:
            self.responseEmpty_comments = ("Gateway_Api and Microservice responses are empty")
            self.responseEmpty_status = "False"
            
        elif len(self.gatewayservice_data['response']) == 0 or len(self.backendService_data['response']) == 0:
            if len(self.gatewayservice_data['response']) == 0:                
                self.responseEmpty_comments = ("Gateway_Api response is empty")
                self.responseEmpty_status = "False"
            
            elif len(self.backendService_data['response']) == 0:                
                self.responseEmpty_comments = ("Microservice response is empty")
                self.responseEmpty_status = "False"            
        else:
            self.responseEmpty_comments = ("Gateway_Api and Microservice response are not empty")
            self.responseEmpty_status = "True"    
        print "\n"+self.testScenario1+"\nComments :"+self.responseEmpty_comments+"\nStatus   :"+self.responseEmpty_status
            
    '''Comparing the response data of gatewayservice and backendService'''
    def responseComparision(self):        
        if sorted(self.gatewayservice_data.items()) == sorted(self.backendService_data.items()):
            self.responseComparision_comments =  "Gateway_Api and Microservice responses are same"
            self.responseComparision_status = "True"
        else:
            self.responseComparision_comments =  "Gateway_Api and Microservice responses are not same"
            self.responseComparision_status = "False"
        print "\n"+self.testScenario2+"\nComments :"+self.responseComparision_comments +"\nStatus   :"+self.responseComparision_status
                  
    '''Test case result Design'''       
    def htmlreport(self):        
        f = open('../integration_test/integrationTestcaseReport.html','w')        
        message = """<html>
        <head></head>
        <body>
        <table style="width:50%" border= 1px>
        <tr>
            <th align="center" colspan="3">Integration Testing Result</th>
        </tr>
        <tr>
            <td><b>Test Scenario</td>
            <td><b>Test Result Status</td>
            <td><b>Comments</td>
        </tr>
        <tr>
            <td>"""+self.testScenario1+"""</td>
            <td align="center">"""+self.responseEmpty_status+"""</td>
            <td>"""+self.responseEmpty_comments+"""</td>            
        </tr>
        <tr>
            <td>"""+self.testScenario2+"""</td>  
            <td align="center">"""+self.responseComparision_status+"""</td>
            <td>"""+self.responseComparision_comments+"""</td>          
        </tr>      
        </table>    
        <p>File name : utilities/test_cases/integration_test/integration.py           
        </html>"""
        
        f.write(message)
        f.close()
        print "\nTestcase report generated location: utilities/test_cases/integration_test/integrationTestcaseReport.html\nProcess completed."     
        
runner = comparision()
runner.testScenario()

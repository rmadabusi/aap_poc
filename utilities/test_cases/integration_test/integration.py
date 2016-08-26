'''
Created on Aug 25, 2016

@author: Treselle
'''
import urllib2
import json

urlheader_backend = "http://54.164.45.232:2001/"
#urlheader_gateway = "http://54.164.45.232:3000/"
urlheader_gateway = "http://127.0.0.1:3000/"

backend_service = "aap/pets/families/1"
gateway_service = "/pets/families/1"

class comparision(object):
    
    def __init__(self):
        super(comparision, self).__init__()
        print "Process started"
        self.backendService()
        self.gatewayservice()
    
    '''Taking Backend service response'''
    def backendService(self):
        self.Step1 = "Step 1: Backend service processing"
        microservice_response = urllib2.urlopen(urlheader_backend+backend_service)
        self.backendService_data = json.load(microservice_response)       
    
    '''Taking Python service response'''
    def gatewayservice(self):
        self.Step2 = "Step 2: Gateway Service service processing"
        microservice_response = urllib2.urlopen(urlheader_gateway+gateway_service)
        self.gatewayservice_data = json.load(microservice_response)            
    
    '''Test scenarios'''     
    def testScenario(self):         
        self.testScenario1 =  "Response data availability"
        self.testScenario2 =  "Comparing the service results"  
        self.testResult()   
             
    '''Test cases'''    
    def testResult(self):
        self.responseEmpty()
        self.responseComparision()
        self.htmlreport()
    
    '''To check the response data availability for gatewayService and backendService'''       
    def responseEmpty(self):       
        if len(self.gatewayservice_data['response']) == 0 and len(self.backendService_data['response']) == 0:
            self.responseEmpty_comments = ("Gateway and Back-end services are empty")
            self.responseEmpty_status = "False"
            
        elif len(self.gatewayservice_data['response']) == 0 or len(self.backendService_data['response']) == 0:
            if len(self.gatewayservice_data['response']) == 0:                
                self.responseEmpty_comments = ("Gateway service is empty")
                self.responseEmpty_status = "False"
            
            elif len(self.backendService_data['response']) == 0:                
                self.responseEmpty_comments = ("Back-end service is empty")
                self.responseEmpty_status = "False"            
        else:
            self.responseEmpty_comments = ("Gateway and Back-end services are not empty")
            self.responseEmpty_status = "True"
            
    '''Comparing the response data of gatewayservice and backendService'''
    def responseComparision(self):        
        if sorted(self.gatewayservice_data.items()) == sorted(self.backendService_data.items()):
            self.responseComparision_comments =  "Both the response are same"
            self.responseComparision_status = "True"
        else:
            self.responseComparision_comments =  "Both the response are not same"
            self.responseComparision_status = "False"
    
    '''Test case result Design'''       
    def htmlreport(self):        
        f = open('../integration_test_report.html','w')
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
        </html>"""
        
        f.write(message)
        f.close()
        print "Process completed"     
        
runner = comparision()
runner.testScenario()

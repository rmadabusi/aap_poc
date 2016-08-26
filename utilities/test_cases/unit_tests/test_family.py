import ast
import unittest
import urllib2
from utilities.test_cases.config.families_config import families_details
from api_gateway.aap.config.config import URL_HEADER_FAMILY, GET_FAMILY_DETAILS

__author__ = "Treselle"

class InequalityTest(unittest.TestCase):

    #Validating the family details data with Backend service response

    def testEqual(self):
        id = "1"
        families_list = []
        assertion_result = {}
        for id in list(id):
            family_dict = {}
            family_dict["name"] = families_details[int(id)]
            family_dict["family"] = int(id)
            families_list.append(family_dict)
        assertion_result["response"] = families_list
        service = URL_HEADER_FAMILY+GET_FAMILY_DETAILS+id
        result = urllib2.urlopen(service).read()
        self.assertEqual(assertion_result, ast.literal_eval(result))
        id = "2"
        service = URL_HEADER_FAMILY+GET_FAMILY_DETAILS+id
        result = urllib2.urlopen(service).read()
        self.assertNotEqual(assertion_result, ast.literal_eval(result))

if __name__ == '__main__':
    unittest.main()

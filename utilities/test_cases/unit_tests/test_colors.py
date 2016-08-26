import ast
import unittest
import urllib2
from utilities.test_cases.config.colors_config import colors_details
from api_gateway.aap.config.config import URL_HEADER_COLORS, GET_COLOR_DETAILS

__author__ = "Treselle"

class InequalityTest(unittest.TestCase):

    #Validating the pet colors data with Backend service response

    def testEqual(self):
        id = "1"
        colors_list = []
        assertion_result = {}
        for id in list(id):
            color_dict = {}
            color_dict["name"] = colors_details[int(id)]
            color_dict["color"] = int(id)
            colors_list.append(color_dict)
        assertion_result["response"] = colors_list
        service = URL_HEADER_COLORS+GET_COLOR_DETAILS+id
        result = urllib2.urlopen(service).read()
        self.assertEqual(assertion_result, ast.literal_eval(result))
        id = "2"
        service = URL_HEADER_COLORS+GET_COLOR_DETAILS+id
        result = urllib2.urlopen(service).read()
        self.assertNotEqual(assertion_result, ast.literal_eval(result))

if __name__ == '__main__':
    unittest.main()

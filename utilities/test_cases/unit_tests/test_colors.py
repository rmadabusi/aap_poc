import ast
import unittest
import urllib2
from utilities.test_cases.config.colors_config import colors_details
from api_gateway.aap.config.config import URL_HEADER_COLORS, GET_COLOR_DETAILS

__author__ = "Treselle"

class InequalityTest(unittest.TestCase):

    #Validating the pet colors data with Backend service response

    def testEqual(self):
        print "\nStarted"
        print "This test is used for colors service output response validation\n"

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
        print "The input for both expected function and actual function are same for colors service"
        self.assertEqual(assertion_result, ast.literal_eval(result))
        print "The expected output and actual output are Same for colors service"

        id = "2"
        service = URL_HEADER_COLORS+GET_COLOR_DETAILS+id
        result = urllib2.urlopen(service).read()
        self.assertNotEqual(assertion_result, ast.literal_eval(result))

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(InequalityTest)
    unittest.TextTestRunner(verbosity=2).run(suite)

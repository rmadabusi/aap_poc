import ast
import unittest
import urllib2
from utilities.test_cases.config.families_config import families_details
from api_gateway.aap.config.config import URL_HEADER_FAMILY, GET_FAMILY_DETAILS

__author__ = "Treselle"

class InequalityTest(unittest.TestCase):

    #Validating the family details data with Backend service response

    def testEqual(self):
        print "\nStarted"
        print "This test is used for family service output response validation\n"
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
        print "The input for both excpected function and actual function are same for family service"
        self.assertEqual(assertion_result, ast.literal_eval(result), "The expected output and actual output are not Same  for family service")
        print "The expected output and actual output are Same for family service"

        id = "2"
        service = URL_HEADER_FAMILY+GET_FAMILY_DETAILS+id
        result = urllib2.urlopen(service).read()
        self.assertNotEqual(assertion_result, ast.literal_eval(result), "Assertion error")
        print "END"



if __name__ == '__main__':
    #unittest.main()
    suite = unittest.TestLoader().loadTestsFromTestCase(InequalityTest)
    unittest.TextTestRunner(verbosity=2).run(suite)
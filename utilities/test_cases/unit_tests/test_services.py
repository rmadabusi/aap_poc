import unittest
import urllib2
from api_gateway.aap.config.config import URL_HEADER_FAMILY,URL_HEADER_COLORS, STATUS

class ServiceStatusTest(unittest.TestCase):

    #Validating the server_is_up_and_running status

    def test_server_is_up_and_running(self):
        service_family = URL_HEADER_FAMILY+STATUS
        service_colors = URL_HEADER_COLORS+STATUS
        family_response = urllib2.urlopen(service_family)
        colors_response = urllib2.urlopen(service_colors)
        self.assertEqual(family_response.code, 200)
        self.assertEqual(colors_response.code, 200)

if __name__ == '__main__':
    unittest.main()

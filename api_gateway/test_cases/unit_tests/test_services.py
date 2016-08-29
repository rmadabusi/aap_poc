import unittest
import urllib2
from api_gateway.aap.config.config import URL_HEADER_FAMILY,URL_HEADER_COLORS, STATUS, URL_HEADER_CLAN

class ServiceStatusTest(unittest.TestCase):

    #Validating the server_is_up_and_running status

    def test_server_is_up_and_running(self):
        print "\n"
        print "started"
        print "This test will be used to check status for each services up/running or not"
        service_family = URL_HEADER_FAMILY+STATUS
        service_colors = URL_HEADER_COLORS+STATUS
        service_clan = URL_HEADER_CLAN + STATUS
        family_response = urllib2.urlopen(service_family)
        colors_response = urllib2.urlopen(service_colors)
        clan_response = urllib2.urlopen(service_colors)
        self.assertEqual(family_response.code, 200, "Family service for getting family details is Down")
        print "Family service for getting family details is Up/Running"
        self.assertEqual(colors_response.code, 200, "Color service for getting color details is Down")
        print "Color service for getting color details is Up/Running"
        self.assertEqual(clan_response.code, 200, "Clan service for getting clan details is Down")
        print "Clan service for getting Clan details is Up/Running"
        # try:
        #     self.assertEqual(family_response.code, 200, "Family service for getting family details is Up/Running")
        # except:
        #     self.assertEqual(colors_response.code, 200, "Color service for getting family details is Up/Running")
        print "End"

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(ServiceStatusTest)
    unittest.TextTestRunner(verbosity=2).run(suite)
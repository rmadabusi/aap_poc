import urllib2
from aap.confg.config import URL_HEADER_FAMILY, GET_FAMILY_DETAILS

__author__ = "Treselle"


#---------------------------------For getting family details based on Pet family ids-----------------------------------#
def get_family_details(id):
    service = URL_HEADER_FAMILY+GET_FAMILY_DETAILS+str(id)
    result = urllib2.urlopen(service).read()
    return result
#----------------------------------------------------------------------------------------------------------------------#


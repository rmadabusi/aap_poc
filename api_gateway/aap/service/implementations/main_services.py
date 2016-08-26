import urllib2
from api_gateway.aap.config.config import STATUS, URL_HEADER_FAMILY, URL_HEADER_COLORS, GET_FAMILY_DETAILS


#--------------------------------------------Get all service status----------------------------------------------------#
def get_status():
    result = {}
    service_family = URL_HEADER_FAMILY+STATUS
    service_colors = URL_HEADER_COLORS+STATUS
    service_family_result = urllib2.urlopen(service_family).read()
    service_colors_result = urllib2.urlopen(service_colors).read()
    result["family_service"] = service_family_result
    result["color_service"] = service_colors_result
    return result
#----------------------------------------------------------------------------------------------------------------------#


#---------------------------------For getting family details based on Pet family ids-----------------------------------#
def get_family_details(id):
    service = URL_HEADER_FAMILY+GET_FAMILY_DETAILS+str(id)
    result = urllib2.urlopen(service).read()
    return result
#----------------------------------------------------------------------------------------------------------------------#


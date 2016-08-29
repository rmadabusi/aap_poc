FROM ubuntu:14.04
MAINTAINER TRESELLE
FROM java:8

#Copy required directories
COPY dist/lib/ /micro_services/pet_color_service/lib/
COPY dist/pet_color_service.jar /micro_services/pet_color_service/

EXPOSE 2002

WORKDIR /micro_services/pet_color_service/

ENTRYPOINT ["java", "-jar",  "pet_color_service.jar"]
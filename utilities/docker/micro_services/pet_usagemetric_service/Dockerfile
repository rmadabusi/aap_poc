FROM ubuntu:14.04
MAINTAINER TRESELLE
FROM java:8
COPY dist/lib/ /micro_services/pet_usagemetric_service/lib/
COPY dist/pet_usagemetric_service.jar /micro_services/pet_usagemetric_service/

EXPOSE 2000
WORKDIR /micro_services/pet_usagemetric_service/
ENTRYPOINT ["java", "-jar",  "pet_usagemetric_service.jar"]
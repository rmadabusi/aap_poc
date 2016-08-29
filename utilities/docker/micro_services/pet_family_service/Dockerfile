FROM ubuntu:14.04
MAINTAINER TRESELLE
FROM java:8
COPY dist/lib/ /micro_services/pet_family_service/lib/
COPY dist/pet_family_service.jar /micro_services/pet_family_service/

EXPOSE 2001
WORKDIR /micro_services/pet_family_service/
ENTRYPOINT ["java", "-jar",  "pet_family_service.jar"]
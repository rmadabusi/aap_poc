FROM ubuntu:14.04
MAINTAINER TRESELLE
FROM java:8

#Copy required directories
COPY dist/lib/ /micro_services/pet_clan_service/lib/
COPY dist/pet_clan_service.jar /micro_services/pet_clan_service/

EXPOSE 2003

WORKDIR /micro_services/pet_clan_service/

ENTRYPOINT ["java", "-jar",  "pet_clan_service.jar"]
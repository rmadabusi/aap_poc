FROM ubuntu:14.04
MAINTAINER TRESELLE

###  Installation of apache2 package ###
RUN apt-get update
RUN apt-get -y install apache2 php5 
RUN apt-get -y install libapache2-mod-php5 curl php5-curl libcurl3 libcurl3-dev

###  Installation of Nodejs and npm packages  ###
RUN apt-get -y install nodejs ruby-full npm
RUN gem install sass
RUN npm install bootstrap

###  Ensure the apache service port ###
EXPOSE 80

###  Copy the app.conf file into apache2  ###
COPY app.conf /etc/apache2/sites-available/
RUN a2dissite 000-default.conf
RUN a2ensite app.conf
RUN a2enmod ssl

###  Restart the apache2 service,After enable the app.conf sites available ###
RUN service apache2 restart

###  Copy the app files into /app docker container  ###
RUN mkdir /app
COPY dist /app
CMD ["/usr/sbin/apache2", "-D",  "FOREGROUND"]





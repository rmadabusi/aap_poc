FROM ubuntu:14.04
MAINTAINER TRESELLE
#Installation of apache2 package
RUN apt-get update
RUN apt-get -y install apache2 php5 
RUN apt-get -y install libapache2-mod-php5 curl 
RUN apt-get -y install nodejs ruby-full npm
RUN gem install sass
RUN npm install bootstrap
RUN service apache2 restart
EXPOSE 80
COPY dist /app
COPY app.conf /etc/apache2/sites-available/
RUN a2dissite 000-default.conf
RUN a2ensite app.conf
RUN a2enmod ssl
RUN mkdir /app
COPY dist /app
EXPOSE 80
CMD ["/usr/sbin/apache2", "-D",  "FOREGROUND"]





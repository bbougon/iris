FROM frolvlad/alpine-oraclejdk8:cleaned

# install bash gcc openssl
RUN apk add --update bash openssl curl libxtst libxrender &&  rm /var/cache/apk/*

# expose port
EXPOSE 8080

# Add sources
ENV destination /usr/src
COPY . ${destination}
WORKDIR ${destination}

#Download Idea
ENV idea ideaIC-2016.1.1.tar.gz
RUN curl -O https://d1opms6zj7jotq.cloudfront.net/idea/${idea} \
    tar -xzf idea*.tar.gz && \
    rm -fr ${idea} && \
    mv ${idea} idea && \
    apk del --purge curl


# RUN
CMD bash
FROM yoshinorin/docker-sbt:1.3.13

MAINTAINER YoshinoriN

RUN apt-get update -y \
  && apt-get upgrade -y \
  && apt-get install -y git vim \
  && apt-get autoremove \
  && apt-get clean

WORKDIR /usr/opt

# TODO: download latest from GitHub releases after first release.
RUN git clone https://github.com/YoshinoriN/lv-fox.git

WORKDIR /usr/opt/lv-fox

RUN rm -rf .git docs \
  && rm .gitignore .editorconfig LICENSE README.md \
  && mv src-backend/* . \
  && rm -rf scripts \
  && rm -rf src-backend docker \
  && sbt clean stage

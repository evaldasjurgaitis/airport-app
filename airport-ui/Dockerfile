FROM node:latest

RUN mkdir /airport-ui

WORKDIR /airport-ui

ENV PATH /airport-ui/node_modules/.bin:$PATH

COPY package.json /airport-ui
COPY package-lock.json /airport-ui

RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

COPY . ./

CMD ["npm", "start"]
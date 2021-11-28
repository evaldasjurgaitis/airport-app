FROM node:12.18.3

RUN mkdir /airport-ui

WORKDIR /airport-ui

ENV PATH /airport-ui/node_modules/.bin:$PATH

COPY package.json /airport-ui
COPY package-lock.json /airport-ui

RUN npm install
RUN npm install react-scripts@3.4.1 -g

COPY . ./

CMD ["npm", "start"]
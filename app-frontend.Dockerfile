FROM node:12-alpine
RUN mkdir -p /app
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build
EXPOSE 8080
CMD [ "npm", "start" ]
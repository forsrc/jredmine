FROM node:latest as builder

COPY . /app
RUN rm -rf /app/node_modules/
RUN rm -rf /app/dist/

WORKDIR /app
RUN npm install
RUN npm run build

FROM nginx:latest

RUN mkdir /app/

WORKDIR /app

ARG APP_PORT=80

#COPY ${JAR_FILE} /app/app.jar

COPY --from=builder /app/dist/* /app/

COPY ./nginx_default.conf /etc/nginx/conf.d/default.conf

#RUN rm -rf /usr/share/nginx/html/*

EXPOSE $APP_PORT

CMD ["nginx", "-g", "daemon off;"]

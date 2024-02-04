Сервисы для работы с сообщениями через Kafka

Данный проект состоит из двух сервисов:
1. Сервис приема сообщений - принимает и отправляет сообщения через Kafka. https://github.com/writeb/producerKafka
2. Сервис отправки сообщений - прослушивает Kafka, отправляет сообщения по электронной почте и сохраняет их в базе данных. https://github.com/writeb/consumerKafka

У вас должен быть скачен Kafka
Клонируйте репозиторий сервиса приема сообщений.
Настройте соединение с базой данных в application.properties.
Настройте Kafka broker в application.properties.
Запустите приложение с помощью команды в терминале - mvn spring-boot:run

API
POST http://localhost:8000/api/messages - принимает сообщение и отправляет его в Kafka.
  Body - '<Message>
          <sender>Test</sender>
          <message>it is test</message>
        </Message>'
GET http://localhost:8000/api/messages - возвращает последние 10 сообщений или сообщения определенного пользователя.

GET http://localhost:8001/api/sent-messages - возвращает список отправленных сообщений и код ответа сервера email.

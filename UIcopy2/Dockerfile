FROM python:3.9-slim-buster

WORKDIR /UI

COPY requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

COPY . .

EXPOSE 8088

CMD [ "python3", "app.py" ]
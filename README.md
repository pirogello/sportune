# Инструкция по запуску

## Оглавление
0. [Установка необходимых программ](#Установка-необходимых-программ)
    1. [java](#Установка-JAVA)
    2. [maven](#Установка-maven) 
    3. [postgres](#Установка-postgres) 
    4. [Node.js + npm](#установка-nodejs--npm) 
1. [Запуск](#Запуск)
    1. [База данных](#База-данных)
    2. [Backend](#Backend) 
    3. [Frontend](#Frontend) 

## Установка необходимых программ

#### Установка JAVA:

___
Linux:
```
sudo apt-get install openjdk-11-jdk
```
#### Установка maven
____
Linux:
```
sudo apt-get install maven
```

#### Установка postgres

____
Linux:
```
apt install postgresql postgresql-contrib
```

#### Установка node.js + npm

____
Linux:
```
curl -fsSl https:/deb.nodesource.com/setup_14.x | sudo -E bash -
sudo apt-get install -y nodejs
sudo apt install npm
```




## Запуск

#### База данных.

Для этого в терминале выполните следующие команды:
```
sudo -u postgres psql
/password postgres
```

После того как вошли в базу данных и задали пароль для пользователя, создайте базу данных:
```
create database sport;
```
#### Backend
*`Работает на 9002 порту, он должен быть свободен.`*

Для запуска backend-части приложения:

Откройте новое окна терминала в корне проекта и введите:
```
mvn spring-boot:run
```
#### Frontend
*`Работает на 8000 порту, он должен быть свободен.`*

Для запуска frontend-части приложения:

Откройте новое окна терминала в корне проекта и введите:
```
npm start
```
##### Перейдите в браузере по адресу http://localhost:9002

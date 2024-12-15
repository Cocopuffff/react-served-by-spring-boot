# React Served By Spring Boot

## Introduction

This project is my personal exploration into the process of using Spring Boot Web framework to serve React frontend, with a twist in configuring React with Vite. 

## Key Features

### Modified Proxy API For Development

Used vite.config.js file to configure proxy port to forward API calls from React frontend to Spring Boot backend during development

### Customised Maven Script To build React Before compiling Spring Boot 

Added new build processes in pom.xml to enable Maven to perform NPM installation, build React, copy distribution files to target folder before compiling Java code.


## Technology

**Front-end:**

- React + Vite

**Back-end:**

- Spring Boot (Web + Data)
- Maven for configuration


## References

- Spring boot and React Web Tutorial: https://www.linkedin.com/learning/spring-boot-and-react-build-scalable-and-dynamic-web-apps?u=0
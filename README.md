## Task Manager 
> Java implementation of the Task Manager

## Table of Contents
* [General Info](#general-information)
* [Prerequisites](#prerequisites)
* [Dependencies](#dependencies)
* [Setup](#setup)
* [Usage](#usage)
* [Contact](#contact)


## General Information
Task Manager is a software component that is designed to handle multiple
processes.

Task Manager exposes the following functionality:

• add a process according to naïve, FIFO and priority approaches

• list running processes

• kill a process 
| kill group of processes 
| kill all processes

## Prerequisites
* JDK 17 or later
* gradle 7.4.1

## Dependencies
Currently the following dependencies are in use.

|Group ID                               |Artifact ID                            |Version            | 
|---                                    |---                                    |---                |
|org.springframework.boot               |spring-boot-starter-web                   |2.6.7		   	    |
|org.springframework.boot               |spring-boot-devtools          |2.6.7		   	    |
|org.springframework                    |spring-boot-starter-validation                |2.6.7	        |
|com.fasterxml.jackson.datatype         |jackson-datatype-jsr310    	        |2.13.2	        |
|org.springframework.boot               |spring-boot-starter-test               |31.1-jre		        |
|com.google.guava                       |guava                       	|2.0.1.Final        |
|org.projectlombok               |lombok            |1.18.24              |





## Setup

### Docker:
1. clone repository
2. docker build -t taskmanager .
3. docker run -d -p 8080:8080 taskmanager


## Usage
### curl
To create a new process in one of the three approaches:
```bash 
curl -X POST http://localhost:8080/task-manager/addProcess/{approach} -H 'Content-Type: application/json' 
```
To retrieve a process in one of the three approaches using process id:
```bash
curl -X GET http://localhost:8080/task-manager/getProcess/{approach}/{pid}
```
To retrieve a list of processes in one of the three approaches by a certain order (process id/creation time/priority) :
```bash
curl -X GET http://localhost:8080/task-manager/listAll/{approach}/{order-type}
```
To kill a process in one of the three approaches using process id:
```bash
curl -X DELETE http://localhost:8080/task-manager/deleteProcess/{approach}/{pid}
```
To kill a group of processes in one of the three approaches using priority:
```bash
curl -X DELETE http://localhost:8080/task-manager/deleteGroup/{approach}/{priority}
```
To kill all processes in one of the three approaches:
```bash
curl -X DELETE http://localhost:8080/task-manager/killAll/{approach}
```



## Contact
Created by Dani Simkin - feel free to contact me!

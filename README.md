         
<div align="center">
    <h1> CT6049: Assignment 1</h1>
    <p>Distributed Database Management & Data Warehousing</p>
    <p><strong>Grade Awarded:</strong> 82/100</p>
</div>

> [!IMPORTANT] 
> This repository contains the finalised, submitted works for Assignment 1 of the CT6049 Module. The content here is preserved "as is" in its submitted state, with no further corrections or feedback applied.
>
> Intended for Archival and Portfolio purposes [^1]**only**

---

## Overview 
This submission features a completely implemented demonstration of traditional **3 Tier Architecture** principles. The core objective of this assignment was to build a single application that featured a clear separation between the tiers; facilitating replacing the Data Layer at will to observe the benefits of properly designed systems.  

Alongside the demonstration, a technical report was also submitted, which focuses on critically evaluating the different data modelling paradigms as well as walking through the implementation of each stage of 3-Tier Architecture. 

To achieve a clean demonstration, the application was developed using **Spring Boot**, utilizing Spring Profiles to easily swap Adapter implementations. The application also focused on **MongoDB** and **PostgreSQL** as the chosen databases. Frontend implementation required nothing fancy, however to get ahead on [Assignment 2](https://github.com/PeaterPita/CT6049A2), I challenged myself to implement a full **Svelte** Frontend with a JWT-Based Authentication System.

## Relevant Skills  
- Database Modelling & Normalisation 
- 3-Tier Design Principles, and programatic dynamic implementations in OOP.
- Learned how to properly design RESTful APIs and assemble consumers to use the designed APIs.
- Learned how to successfully implement tooling like Containerization and Caching to improve end user experiences.

<div align="center">
    <p><strong>Tools Used</strong></p>
    [ Java, SpringBoot, Docker, Svelte, Vite ]
</div>

# Preserved README. 
As this assignment was submitted with its own README, what follows below is the preserved instructions. 

---

## Requirements
* Docker / Docker Compose   (All databases are hosted in containers)
* Java 17
* Npm / Node.JS

## Features
* **Interchangeable** database connection, through Spring Profiles. (sql and mongo)
* **3-Tier Architecture** design
* **GUI** Web based interface.
* **Login** system thats persistent through JWT tokens
* **Complete data seeding** on empty databases
* **External API calls** for cover art fetching
* **Redis** caching of book covers for 24 hours

## How to run
```cmd
./run.sh or .\run.bat
```

The supplied run scripts *should* work. However if they do fail:

- Install frontend dependencies (cd frontend && npm install)
- Spin up docker containers (docker compose up -d)

- Open a terminal each in `frontend/` and `backend/` and run the commands:
frontend:   npm run dev
backend:    mvn spring-boot:run -Dspring-boot.run.profiles=<profile>    (where <profile> == sql | mongo)

- Navigate to localhost:5173
- Log in as 
    username: `user`          password: `password`  or
    username: `sql | mongo`   password: `sql | mongo`

---

[^1]: All rights reserved. Reproduction or use of this material for academic submissions, wholly or in part, is strictly prohibited. All works from copyright holders other than myself, such as University Of Gloucestershire, have been omitted, and or, summarised.

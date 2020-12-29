# Project1
## Project Description
An expense reimbursement system. Users are either a manager or an employee. The feature of each case is listed below in the features section.

The program does not run correctly in it's current state as the database has been shut down, so there is nowhere to store and retrieve the data from.
In the usage tab below, there is information to create a mock database for use with this program, however it has not been tested so results may not be desirable

## Technologies Used
* Eclipse IDE
* Java
* PostgreSQL
* AWS
* JUnit
* HTML
* CSS
* JDBC

## Features
#### Users:
* Are either managers, or employees
* Can sign in and out

#### Employees:
* Can view their own account
* Can view all of their own requests
* Can submit a reimbursement request

#### Managers:
* Can create new users
* Can view employees by ID
* Can view all Employees
* Can view requests by User ID
* Can view all requests

### To-do list:
#### Users:
* Can update their information

#### Managers:
* View the requests of a single employee


## Getting Started
Git clone command: https://github.com/rbooch6/project1.git

The project used a database to access, however the database has since been shut down so that aspect of the code does not run anymore.

The code is output in the system console as it never got connected to the HTML, the html is still included, just not attached to the java backend.

## Usage
In order to run the project, the database needs to be in place which it is not. Information about the database:
* It is a PostgreSQL database
* It contains two tables
* Users table with (int user_id, varchar username, varchar password, boolean is_manager, float money_in_account)
* Requests table with (int user_id, long reqiest_id, boolean pending, long amount)

The three variables at the top of the Main.java class (username, password, url) also will need to be changed to accommodate this new database

## Contributors
Solo project, created by Roger Bucci

## License
This project uses the following license: <BSD>.
BSD 2-Clause License

Copyright (c) [2020], [Roger Bucci]
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

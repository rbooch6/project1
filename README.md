# Project1
## Project Description
An expense reimbursement system

## Technologies Used
* Eclipse IDE
* Java
* PostgreSQL
* AWS
* JUnit

## Features
Client
* View balance
* Deposit money
* Withdraw money

Employee
* Create new user account

Both
* Sign in
* sign out

To-do list:
Client
* Transfer money
* View transaction history
* Apply for line of credit

Employee
* Review credit requests

## Getting Started
* Git clone command: https://github.com/rbooch6/project0.git (include all environment setup steps)

The program was written using the eclipse IDE with the the package name being project0. It imports imports:
* java.sql.Connection;
* java.sql.Driver;
* java.sql.DriverManager;
* java.sql.SQLException;
* java.util.Scanner;
* java.util.InputMismatchException;

It also connected to a PostgreSQL database running on AWS, however the database has since been stopped so there are currently test variables to show the program running

* All the code required to get started
* Images of what it should look like

## Usage
The program is run within the system output using a scanner. Below is a list of user information in order to get the desired output. If the input is incorrect the program
will tell inform the user.
Client info:
* username: client
* password: password
* account number: 1234

Employee info:
* username: employee
* password: password
* employee number: 123

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

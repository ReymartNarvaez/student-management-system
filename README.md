Java EE - Laboration 1

1.Create Student

Endpoint: /student-management-system/api/v1/student/new

JSON: 
{
   "firstName": "StudentFirstName",
   "lastName": "StudentLastName",
   "email": "StudentEmail@email.test",
   "phoneNumber": 1234567
}

2. Get All Student

Endpoint: /student-management-system/api/v1/student/getall

3. Get Student By Name

EndPoint: 
/student-management-system/api/v1/student/getstudent?name=StudentLastName

4. Update Last Name

EndPoint: 
/student-management-system/api/v1/student/updatelastname/1?lastName=StudentLastNameChanged

5. Delete Student By Id

Endpoint: /student-management-system/api/v1/student/1

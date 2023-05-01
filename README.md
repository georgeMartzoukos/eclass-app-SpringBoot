# eclass-app-SpringBoot

This is a Java Spring Boot REST API for managing teachers and students of an e-learning platform. The API includes two controllers, one for managing students and one for managing teachers.

The StudentController includes three endpoints:

"/api/students/": POST endpoint for adding a new student. It accepts a JSON payload in the request body in the form of a StudentDTO object, and returns a ResponseEntity with the same object in the response body, along with a CREATED HTTP status code (201). If the student already exists, it returns a BAD_REQUEST (400) status code.
"/api/students/{studentId}/{courseId}": POST endpoint for adding a course to a student. It accepts the studentId and courseId as path variables, and returns a ResponseEntity with a String message in the response body, along with an OK (200) HTTP status code. If the student is not found or already has that course, it returns a BAD_REQUEST (400) status code.
"/api/students/{studentId}": GET endpoint for retrieving the courses of a student. It accepts the studentId as a path variable, and returns a List of CourseDTO objects in the response body, along with an OK (200) HTTP status code. If the student is not found, it returns a NOT_FOUND (404) status code.
The TeacherController includes four endpoints:

"/api/teachers?lastname={lastname}": GET endpoint for retrieving a list of teachers by their lastname. It accepts the lastname as a query parameter, and returns a List of TeacherDTO objects in the response body, along with an OK (200) HTTP status code. If no teachers are found, it returns a BAD_REQUEST (400) status code.
"/api/teachers/{id}": GET endpoint for retrieving a teacher by their id. It accepts the id as a path variable, and returns a TeacherDTO object in the response body, along with an OK (200) HTTP status code. If the teacher is not found, it returns a NOT_FOUND (404) status code.
"/api/teachers/{id}": DELETE endpoint for deleting a teacher by their id. It accepts the id as a path variable, and returns a ResponseEntity with a String message in the response body, along with an OK (200) HTTP status code. If the teacher is not found, it returns a NOT_FOUND (404) status code.
"/api/teachers/": POST endpoint for adding a new teacher. It accepts a JSON payload in the request body in the form of a TeacherDTO object, and returns a ResponseEntity with the same object in the response body, along with a CREATED HTTP status code (201). If the teacher already exists, it returns a BAD_REQUEST (400) status code.

GET /users: This endpoint retrieves a list of all users in the system. The response is an array of JSON objects, where each object represents a user. Each user object contains the following fields:

id: A unique identifier for the user.
username: The user's username.
email: The user's email address.
created_at: The date and time that the user was created, in ISO 8601 format.
updated_at: The date and time that the user was last updated, in ISO 8601 format.
GET /users/:id: This endpoint retrieves information about a specific user, based on their ID. The response is a JSON object that represents the user. The user object contains the same fields as the objects returned by the GET /users endpoint.

POST /users: This endpoint creates a new user in the system. The request body should be a JSON object that contains the following fields:

username: The user's desired username.
email: The user's email address.
password: The user's desired password.
The response is a JSON object that represents the newly created user. The user object contains the same fields as the objects returned by the GET /users endpoint.

PUT /users/:id: This endpoint updates information about a specific user, based on their ID. The request body should be a JSON object that contains one or more of the following fields:

username: The user's new username.
email: The user's new email address.
password: The user's new password.
The response is a JSON object that represents the updated user. The user object contains the same fields as the objects returned by the GET /users endpoint.

DELETE /users/:id: This endpoint deletes a specific user from the system, based on their ID. The response is an empty JSON object.
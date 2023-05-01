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

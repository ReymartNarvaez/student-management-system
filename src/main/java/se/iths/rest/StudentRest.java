package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("new")
    @POST
    public Response createStudent(Student student) {
        studentService.createStudent(student);
        return Response.ok(student).build();
    }

    @Path("getall")
    @GET
    public Response getAll() {
        List<Student> foundStudents = studentService.getAllStudent();
        return Response.ok(foundStudents).build();
    }

    @Path("getstudent")
    @GET
    public Response getStudent(@QueryParam("name") String name) {
        List<Student> foundStudents = studentService.findStudent(name);
        return Response.ok(foundStudents).build();
    }

    @Path("updatelastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastName") String lastName) {
        Student updateLastName = studentService.updateLastName(id, lastName);
        return Response.ok(updateLastName).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}

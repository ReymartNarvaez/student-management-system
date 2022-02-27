package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
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
        try {
            studentService.createStudent(student);
        } catch (Exception e) {
            throw new BadRequestException("All Fields Required Except Phone Number");
        }
        return Response.ok(student).build();
    }

    @Path("getall")
    @GET
    public Response getAll() {
        List<Student> foundStudents = studentService.getAllStudent();

        if (foundStudents.isEmpty()){
            throw new NotFoundException("No Students Found");
        }
        return Response.ok(foundStudents).build();
    }

    @Path("getstudent")
    @GET
    public Response getStudent(@QueryParam("name") String name) {
        List<Student> foundStudents = studentService.findStudent(name);

        if (foundStudents.isEmpty()) {
            throw new NotFoundException("Student Does Not Exist");
        }
        return Response.ok(foundStudents).build();
    }

    @Path("updatelastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastName") String lastName) {
        Student updateLastName = null;
        try {
            updateLastName = studentService.updateLastName(id, lastName);
        } catch (Exception e) {
           throw new BadRequestException("Student Id Does Not Exist");
        }
        return Response.ok(updateLastName).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
        } catch (Exception e) {
            throw new BadRequestException("Delete Failed, Student Id Does Not Exist");
        }
        return Response.ok().build();
    }
}

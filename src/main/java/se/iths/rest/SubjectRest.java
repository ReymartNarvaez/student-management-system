package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("newSubject")
    @POST
    public Response createSubject(Subject subject) {
        try {
            subjectService.createSubject(subject);
        } catch (Exception e) {
            throw new BadRequestException("WrongInput");
        }

        return Response.ok(subject).build();
    }

    @Path("addStudent/{studentId}/{subjectId}")
    @PUT
    public Response addStudent(@PathParam("studentId") Long studentId, @PathParam("subjectId") Long subjectId) {
        Subject addStudent = subjectService.addStudent(studentId, subjectId);

        return Response.ok(addStudent).build();
    }

    @Path("addTeacher/{teacherId}/{subjectId}")
    @PUT
    public Response addTeacher(@PathParam("teacherId") Long teacherId, @PathParam("subjectId") Long subjectId) {
        Subject addTeacher = subjectService.addTeacher(teacherId, subjectId);

        return Response.ok(addTeacher).build();
    }

    @Path("getAllSubjects")
    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubjects = subjectService.getAllSubject();

        if (foundSubjects.isEmpty()) {
            throw new NotFoundException("No Subjects Found");
        }
        return Response.ok(foundSubjects).build();
    }

    @Path("getSubject")
    @GET
    public Response getSubject(@QueryParam("name") String name) {
        List<Subject> foundSubject = subjectService.getSubject(name);

        if (foundSubject.isEmpty()) {
            throw new NotFoundException("No Subjects Found");
        }
        return Response.ok(foundSubject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectService.deleteSubject(id);
        return Response.ok().build();
    }
}

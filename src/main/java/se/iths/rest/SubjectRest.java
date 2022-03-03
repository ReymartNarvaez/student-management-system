package se.iths.rest;

import se.iths.entity.Subject;
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

    @Path("new")
    @POST
    public Response createSubject(Subject subject) {
        subjectService.createSubject(subject);
        return Response.ok(subject).build();
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

package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.exception.BadRequestException;
import se.iths.exception.NotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("newTeacher")
    @POST
    public Response createTeacher(Teacher teacher) {
        try {
            teacherService.createTeacher(teacher);
        } catch (Exception e) {
            throw new BadRequestException("All Fields Required Except Phone Number");
        }
        return Response.ok(teacher).build();
    }

    @Path("getAllTeachers")
    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = teacherService.getAllTeachers();

        if (foundTeachers.isEmpty()) {
            throw new NotFoundException("No Teachers Found");
        }
        return Response.ok(foundTeachers).build();
    }

    @Path("getTeacher")
    @GET
    public Response getTeacher(@QueryParam("name") String name) {
        List<Teacher> foundTeachers = teacherService.findTeacher(name);

        if (foundTeachers.isEmpty()) {
            throw new NotFoundException("Teacher Does Not Exist");
        }
        return Response.ok(foundTeachers).build();
    }

    @Path("updateTeacherLastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastName") String lastName) {
        Teacher updateLastName = null;
        try {
            updateLastName = teacherService.updateTeacherLastName(id, lastName);
        } catch (Exception e) {
            throw new BadRequestException("TeacherIdDoesNotExist");
        }
        return Response.ok(updateLastName).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
        } catch (Exception e) {
            throw new BadRequestException("DeleteFailed,Student Id Does Not Exist");
        }
        return Response.ok().build();
    }
}

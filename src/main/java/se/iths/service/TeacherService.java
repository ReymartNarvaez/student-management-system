package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public List<Teacher> getAllTeachers() {
    return entityManager.createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
    }

    public List<Teacher> findTeacher(String name) {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t FROM Teacher t WHERE CONCAT(t.firstName, ' ', t.lastName) LIKE :name", Teacher.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public Teacher updateTeacherLastName(Long id, String lastName) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        foundTeacher.setLastName(lastName);
        return foundTeacher;
    }

    public void deleteTeacher(Long id){
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }

}

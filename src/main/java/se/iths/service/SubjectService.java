package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public List<Subject> getAllSubject() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    public List<Subject> getSubject(String name) {
        TypedQuery<Subject> query = entityManager.createQuery("SELECT s FROM Subject s WHERE s.name LIKE :name", Subject.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Subject updateSubject(Long id, String name) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        foundSubject.setName(name);
        return foundSubject;
    }

    public void deleteSubject(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
    }

    public Subject addStudent(Long studentId, Long subjectId) {
        Student foundStudent = entityManager.find(Student.class, studentId);
        Subject foundSubject = entityManager.find(Subject.class, subjectId);
        foundSubject.setStudents(foundStudent);
        return foundSubject;
    }

    public Subject addTeacher(Long teacherId, Long subjectId) {
        Teacher foundTeacher = entityManager.find(Teacher.class, teacherId);
        Subject foundSubject = entityManager.find(Subject.class, subjectId);
        foundSubject.setTeacher(foundTeacher);
        return foundSubject;
    }

}

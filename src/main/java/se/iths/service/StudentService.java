package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public List<Student> getAllStudent() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public List<Student> findStudent(String name) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE CONCAT(s.firstName, ' ', s.lastName) LIKE :name", Student.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    public Student updateLastName(Long id, String lastName) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(lastName);
        return foundStudent;
    }

    public void deleteStudent(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }
}

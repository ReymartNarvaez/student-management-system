package se.iths.service;

import se.iths.entity.Subject;

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

    public List<Subject> getSubject(String subjectName) {
        TypedQuery<Subject> query = entityManager.createQuery("SELECT s FROM Subject s WHERE s.subject LIKE :subjectName", Subject.class);
        query.setParameter("subjectName", subjectName);
        return query.getResultList();
    }

    public Subject updateSubject(Long id, String subjectName) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        foundSubject.setSubject(subjectName);
        return foundSubject;
    }

    public void deleteSubject(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
    }

}

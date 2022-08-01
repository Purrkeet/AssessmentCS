package com.test.demo.service;

import com.test.demo.entity.EventEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class EventDaoImp implements EventDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventEntity> getEvents() {
        String query = "SELECT e FROM EventEntity e";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public EventEntity addEvent(EventEntity event) {
        entityManager.persist(event);
        return event;
    }

    @Override
    public void removeEvent(String eventId) {
        String query = "DELETE FROM EventEntity e WHERE e.eventId = :eventId";
        entityManager.createQuery(query).setParameter("eventId", eventId).executeUpdate();
    }

}

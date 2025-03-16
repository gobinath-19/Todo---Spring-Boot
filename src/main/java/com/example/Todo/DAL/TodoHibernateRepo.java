package com.example.Todo.DAL;


import com.example.Todo.Model.TodoJPA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoHibernateRepo implements HibernateRepo {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TodoJPA> findAll() {
        Session session = sessionFactory.openSession();
        List<TodoJPA> todos = session.createQuery("FROM TodoJPA", TodoJPA.class).list();
        session.close();
        return todos;
    }

    @Override
    public Optional<TodoJPA> findById(Long id) {
        Session session = sessionFactory.openSession();
        TodoJPA todo = session.get(TodoJPA.class, id);
        session.close();
        return Optional.ofNullable(todo);
    }

    @Override
    public TodoJPA save(TodoJPA todo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(todo);
        transaction.commit();
        session.close();
        return todo;
    }

    @Override
    public TodoJPA update(TodoJPA todo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(todo);
        transaction.commit();
        session.close();
        return todo;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TodoJPA todo = session.get(TodoJPA.class, id);
        if (todo != null) {
            session.remove(todo);
        }
        transaction.commit();
        session.close();
    }

}
package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    private EntityManager em;


    @Override
    public Role findRoleById(long id) {
        if (em.find(Role.class, id) == null) {
            throw new NullPointerException("Role with id=" + id + " is not find");
        }
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> findAllRole() {
        return em.createQuery("FROM Role").getResultList();
    }


}

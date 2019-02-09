package it.alessiorossato.sagra.dao;

import it.alessiorossato.sagra.domain.security.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}

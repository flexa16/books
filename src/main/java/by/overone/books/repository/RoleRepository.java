package by.overone.books.repository;

import by.overone.books.model.entitity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends CrudRepository<UserRole, Long> {

}

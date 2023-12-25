package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}

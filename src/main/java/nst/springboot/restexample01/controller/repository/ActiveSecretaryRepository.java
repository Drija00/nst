package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.ActiveSecretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActiveSecretaryRepository extends JpaRepository<ActiveSecretary,Long> {
    Optional<ActiveSecretary> findByMemberIdAndDepartmentId(Long idM, Long idD);
    Optional<ActiveSecretary> findByDepartmentId(Long idD);
}

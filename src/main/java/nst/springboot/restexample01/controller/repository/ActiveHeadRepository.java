package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.ActiveHead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActiveHeadRepository extends JpaRepository<ActiveHead, Long> {

    Optional<ActiveHead> findByMemberIdAndDepartmentId(Long idM, Long idD);
    Optional<ActiveHead> findByDepartmentId(Long idD);
}

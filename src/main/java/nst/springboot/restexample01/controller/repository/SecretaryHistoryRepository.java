package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecretaryHistoryRepository extends JpaRepository<SecretaryHistory,Long> {
    Optional<SecretaryHistory> findByDepartmentId(Long id);
}

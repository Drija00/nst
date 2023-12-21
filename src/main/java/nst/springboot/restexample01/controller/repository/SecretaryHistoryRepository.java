package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryHistoryRepository extends JpaRepository<SecretaryHistory,Long> {
}

package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.HeadHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadHistoryRepository extends JpaRepository<HeadHistory, Long> {
}

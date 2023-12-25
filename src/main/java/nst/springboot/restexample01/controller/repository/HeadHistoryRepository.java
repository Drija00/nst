package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeadHistoryRepository extends JpaRepository<HeadHistory, Long> {
    Optional<HeadHistory> findByDepartmentId(Long id);
}

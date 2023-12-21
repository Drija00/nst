package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeadHistoryRepository extends JpaRepository<HeadHistory, Long> {
    List<HeadHistory> findAllByDepartmentId(Long id);
}

package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HeadHistoryRepository extends JpaRepository<HeadHistory, Long> {
    Optional<HeadHistory> findByDepartmentIdAndEndDateNull(Long id);

    List<HeadHistory> findAllByMemberIdAndDepartmentIdOrderByEndDateDesc(Long idM, Long idD);

    List<HeadHistory> findAllByMemberId(Long id);
}

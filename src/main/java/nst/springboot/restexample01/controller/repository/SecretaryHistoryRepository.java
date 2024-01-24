package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.domain.SecretaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecretaryHistoryRepository extends JpaRepository<SecretaryHistory,Long> {
    Optional<SecretaryHistory> findByDepartmentIdAndEndDateNull(Long id);
    List<SecretaryHistory> findAllByMemberIdAndDepartmentIdOrderByEndDateDesc(Long idM, Long idD);
    List<SecretaryHistory> findAllByMemberId(Long id);
}

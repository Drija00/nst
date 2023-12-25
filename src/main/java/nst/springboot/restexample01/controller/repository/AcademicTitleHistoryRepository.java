package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory,Long> {
    List<AcademicTitleHistory> findAllByMemberIdAndAcademicTitleIdOrderByStartDateDesc(Long idM, Long idAT);
}

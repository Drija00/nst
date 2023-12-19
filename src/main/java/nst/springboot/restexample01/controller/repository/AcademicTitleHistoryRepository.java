package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory,Long> {
}

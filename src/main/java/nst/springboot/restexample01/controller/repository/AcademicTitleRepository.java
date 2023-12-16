package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.AcademicTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicTitleRepository extends JpaRepository<AcademicTitle,Long> {
}

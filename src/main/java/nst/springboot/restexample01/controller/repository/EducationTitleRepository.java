package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationTitleRepository extends JpaRepository<EducationTitle,Long> {
}

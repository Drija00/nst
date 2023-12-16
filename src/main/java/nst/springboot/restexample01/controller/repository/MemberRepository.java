package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MemberRepository extends JpaRepository<Member, Long> {
}

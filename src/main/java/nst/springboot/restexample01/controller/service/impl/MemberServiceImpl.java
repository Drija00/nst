package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.*;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.controller.service.SecretaryHistoryService;
import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberConverter memberConverter;

    private MemberRepository memberRepository;

    private DepartmentRepository departmentRepository;

    private AcademicTitleRepository academicTitleRepository;

    private AcademicTitleHistoryRepository academicTitleHistoryRepository;

    private HeadHistoryRepository headHistoryRepository;

    private SecretaryHistoryRepository secretaryHistoryRepository;
    private RoleRepository roleRepository;

    public MemberServiceImpl(MemberConverter memberConverter, MemberRepository memberRepository, DepartmentRepository departmentRepository, AcademicTitleRepository academicTitleRepository, AcademicTitleHistoryRepository academicTitleHistoryRepository, HeadHistoryRepository headHistoryRepository, SecretaryHistoryRepository secretaryHistoryRepository, RoleRepository roleRepository) {
        this.memberConverter = memberConverter;
        this.memberRepository = memberRepository;
        this.departmentRepository = departmentRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
        this.headHistoryRepository = headHistoryRepository;
        this.secretaryHistoryRepository = secretaryHistoryRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) throws Exception {
        Department dep = departmentRepository.findById(memberDTO.getDepartment().getId()).orElseThrow(() -> new Exception("Department doesn't exist!"));
        Role r = roleRepository.findById(memberDTO.getRole().getId()).orElseThrow(() -> new Exception("Role doesn't exist!"));
        LocalDate date = LocalDate.now();
        if(r.getName().equals("Head")){
            Optional<Member> memberSH = memberRepository.findByRoleIdAndDepartmentId(r.getId(),dep.getId());
            if (memberSH.isPresent()){
                throw new Exception("The department already has a member with the specified role.");
            }
            Member mH = memberRepository.save(memberConverter.toEntity(memberDTO));
            headHistoryRepository.save(new HeadHistory(null, date,date.plusYears(1),mH,dep));
            return memberConverter.toDto(mH);
        } else if (r.getName().equals("Secretary")) {
            Optional<Member> memberSH = memberRepository.findByRoleIdAndDepartmentId(r.getId(),dep.getId());
            if (memberSH.isPresent()){
                throw new Exception("The department already has a member with the specified role.");
            }
            Member mS = memberRepository.save(memberConverter.toEntity(memberDTO));
            secretaryHistoryRepository.save(new SecretaryHistory(null,date,date.plusYears(1),mS,dep));
            return memberConverter.toDto(mS);
        }
        Member m = memberRepository.save(memberConverter.toEntity(memberDTO));
        academicTitleHistoryRepository.save(new AcademicTitleHistory(null,date,date.plusYears(1),m,m.getAcademicTitle(),m.getScientificField()));
        return memberConverter.toDto(m);
        //ako department ne postoji sacuvaj i department zajedno sa Subject/om
    }

    @Override
    public List<MemberDTO> getAll() {
        return memberRepository
                .findAll()
                .stream().map(entity -> memberConverter.toDto(entity))
                .collect(Collectors.toList());
    }
    public List<MemberDTO> getAllByDepartmentId(Long id) {
        return memberRepository
                .findAllByDepartmentId(id)
                .stream().map(entity -> memberConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member member1 = member.get();
            memberRepository.delete(member1);
        } else {
            throw new Exception("Member does not exist!");
        }
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO, Long id) throws Exception {
        Optional<Member> mem = memberRepository.findById(id);
        if(mem.isEmpty()){
            throw new Exception("Member doesn't exist!");
        }
        memberDTO.setId(id);
        save(memberDTO);
        return memberDTO;
    }

    @Override
    public MemberDTO findById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member member1 = member.get();
            return memberConverter.toDto(member1);
        } else {
            throw new Exception("Member does not exist!");
        }
    }
}

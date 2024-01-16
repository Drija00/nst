package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.*;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.controller.service.SecretaryHistoryService;
import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.MemberHeadSecDTO;
import nst.springboot.restexample01.dto.RoleDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private MemberConverter memberConverter;
    private MemberHeadSecConverter memberHeadSecConverter;

    private MemberRepository memberRepository;

    private DepartmentRepository departmentRepository;

    private AcademicTitleRepository academicTitleRepository;

    private AcademicTitleHistoryRepository academicTitleHistoryRepository;

    private HeadHistoryRepository headHistoryRepository;

    private SecretaryHistoryRepository secretaryHistoryRepository;
    private RoleRepository roleRepository;
    private RoleConverter roleConverter;

    public MemberServiceImpl(MemberConverter memberConverter, MemberHeadSecConverter memberHeadSecConverter, MemberRepository memberRepository, DepartmentRepository departmentRepository, AcademicTitleRepository academicTitleRepository, AcademicTitleHistoryRepository academicTitleHistoryRepository, HeadHistoryRepository headHistoryRepository, SecretaryHistoryRepository secretaryHistoryRepository, RoleRepository roleRepository, RoleConverter roleConverter) {
        this.memberConverter = memberConverter;
        this.memberHeadSecConverter = memberHeadSecConverter;
        this.memberRepository = memberRepository;
        this.departmentRepository = departmentRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
        this.headHistoryRepository = headHistoryRepository;
        this.secretaryHistoryRepository = secretaryHistoryRepository;
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public MemberDTO save(MemberHeadSecDTO memberDTO) throws Exception {
        Department dep = departmentRepository.findById(memberDTO.getDepartment()).orElseThrow(() -> new Exception("Department doesn't exist!"));
        memberDTO.setRoleDTO(new RoleDTO(3L,null));
        Member m = memberRepository.save(memberHeadSecConverter.toEntity(memberDTO));
        academicTitleHistoryRepository.save(new AcademicTitleHistory(null,LocalDate.now(),null,m,m.getAcademicTitle(),m.getScientificField()));
        return memberConverter.toDto(m);
    }

    @Override
    public MemberDTO update(MemberHeadSecDTO memberDTO, Long id) throws Exception {
        Member member = memberRepository.findById(id).orElseThrow(()->new Exception("Member doesn't exist"));
        Department dep = departmentRepository.findById(memberDTO.getDepartment()).orElseThrow(() -> new Exception("Department doesn't exist!"));
        memberDTO.setId(id);
        if(memberDTO.getDepartment().equals(member.getDepartment().getId())){
            memberDTO.setRoleDTO(roleConverter.toDto(member.getRole()));
        }else {
            memberDTO.setRoleDTO(new RoleDTO(3L,null));
            if(member.getRole().getId().equals(2L)){
                removeSec(member.getId());
            } else if (member.getRole().getId().equals(1L)) {
                removeHead(member.getId());
            }
        }
        Long atId = member.getAcademicTitle().getId();
        Member m = memberRepository.save(memberHeadSecConverter.toEntity(memberDTO));
        if(!memberDTO.getAcademicTitle().getId().equals(atId)){
            List<AcademicTitleHistory> academicTitles = academicTitleHistoryRepository.findAllByMemberIdAndAcademicTitleIdOrderByStartDateDesc(member.getId(),atId);
            academicTitles.get(0).setEndDate(LocalDate.now());
            academicTitleHistoryRepository.save(academicTitles.get(0));

            academicTitleHistoryRepository.save(new AcademicTitleHistory(null,LocalDate.now(),null,m,m.getAcademicTitle(),m.getScientificField()));
        }
        return memberConverter.toDto(m);
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
    public MemberDTO setSec(Long idM) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        Optional<Member> memberSH = memberRepository.findByDepartmentIdAndRoleId(mem.getDepartment().getId(),2L);
        if(memberSH.isPresent()){
            throw  new Exception("The department already has a secretary member.");
        }
        if(mem.getRole().getId().equals(1L)){
            removeHead(idM);
        }
        mem.setRole(new Role(2L,null));
        Member mH = memberRepository.save(mem);
        secretaryHistoryRepository.save(new SecretaryHistory(null, LocalDate.now(),null,mH,mem.getDepartment()));
        return memberConverter.toDto(mH);
    }

    @Override
    public MemberDTO setHead(Long idM) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        Optional<Member> memberSH = memberRepository.findByDepartmentIdAndRoleId(mem.getDepartment().getId(),1L);
        if(memberSH.isPresent()){
            throw  new Exception("The department already has a head member.");
        }
        if(mem.getRole().getId().equals(2L)){
            removeSec(idM);
        }
        mem.setRole(new Role(1L,null));
        Member mH = memberRepository.save(mem);
        headHistoryRepository.save(new HeadHistory(null, LocalDate.now(),null,mH,mem.getDepartment()));
        return memberConverter.toDto(mH);
    }

    @Override
    public MemberDTO removeHead(Long idM) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        if(mem.getRole().getId().equals(1L)) {
            mem.setRole(new Role(3L, null));
            Member mH = memberRepository.save(mem);
            List<HeadHistory> history = headHistoryRepository.findAllByMemberIdAndDepartmentIdOrderByStartDateDesc(mem.getId(), mem.getDepartment().getId());
            ;
            if (!history.isEmpty()) {
                history.get(0).setEndDate(LocalDate.now());
                headHistoryRepository.save(history.get(0));
            }
            return memberConverter.toDto(mH);
        }else {
            throw new Exception("Member doesn't have Head role.");
        }
    }

    @Override
    public MemberDTO removeSec(Long idM) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        if(mem.getRole().getId().equals(2L)) {
        mem.setRole(new Role(3L,null));
        Member mH = memberRepository.save(mem);
        List<SecretaryHistory> history = secretaryHistoryRepository.findAllByMemberIdAndDepartmentIdOrderByStartDateDesc(mem.getId(),mem.getDepartment().getId());;
        if(!history.isEmpty()) {
            history.get(0).setEndDate(LocalDate.now());
            secretaryHistoryRepository.save(history.get(0));
        }
        return memberConverter.toDto(mH);
        }else {
            throw new Exception("Member doesn't have Secretary role.");
        }
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

package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.*;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.controller.service.SecretaryHistoryService;
import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.dto.*;
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
    private SecretaryHistoryConverter secretaryHistoryConverter;
    private HeadHistoryConverter headHistoryConverter;
    private RoleRepository roleRepository;
    private RoleConverter roleConverter;
    private ATHConverter athConverter;
    private ActiveHeadRepository activeHeadRepository;
    private ActiveSecretaryRepository activeSecretaryRepository;

    public MemberServiceImpl(ActiveSecretaryRepository activeSecretaryRepository, ActiveHeadRepository activeHeadRepository, MemberConverter memberConverter,ATHConverter athConverter,HeadHistoryConverter headHistoryConverter, SecretaryHistoryConverter secretaryHistoryConverter, MemberHeadSecConverter memberHeadSecConverter, MemberRepository memberRepository, DepartmentRepository departmentRepository, AcademicTitleRepository academicTitleRepository, AcademicTitleHistoryRepository academicTitleHistoryRepository, HeadHistoryRepository headHistoryRepository, SecretaryHistoryRepository secretaryHistoryRepository, RoleRepository roleRepository, RoleConverter roleConverter) {
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
        this.secretaryHistoryConverter = secretaryHistoryConverter;
        this.headHistoryConverter = headHistoryConverter;
        this.athConverter = athConverter;
        this.activeHeadRepository = activeHeadRepository;
        this.activeSecretaryRepository = activeSecretaryRepository;
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
            if(member.getRole().getId().equals(2L) || member.getRole().getId().equals(1L)){
                regularRole(member.getId());
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
    public MemberDTO secRole(Long idM, DatesDTO datesDTO) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        Optional<ActiveSecretary> activeSecretary= activeSecretaryRepository.findByDepartmentId(mem.getDepartment().getId());
        //Optional<Member> memberSH = memberRepository.findByDepartmentIdAndRoleId(mem.getDepartment().getId(),2L);
        if(activeSecretary.isPresent()){
            throw  new Exception("The department already has a secretary member. Member with id: "+
                    activeSecretary.get().getMember().getId()+
                    " is active secretary for department with id: "+
                    activeSecretary.get().getDepartment().getId()+ " !");
        }
        if(datesDTO.getStartDate()==null || datesDTO.getEndDate()==null || datesDTO.getStartDate().isAfter(datesDTO.getEndDate()))
        {
            throw new Exception("The startdate and enddate are required parameters and startdate must be before enddate!");
        }
        if(mem.getRole().getId().equals(1L)){
            throw new Exception("This member is already a head member for the department he is part of Department with id: " +mem.getDepartment().getId() + " and cannot be selected as secretary!");
        }
        mem.setRole(new Role(2L,null));
        Member mH = memberRepository.save(mem);
        SecretaryHistory sh = secretaryHistoryRepository.save(new SecretaryHistory(null,datesDTO.getStartDate(),datesDTO.getEndDate(),mH,mH.getDepartment()));
        activeSecretaryRepository.save(new ActiveSecretary(null,sh,mH,mH.getDepartment()));
        return memberConverter.toDto(mH);
    }

    @Override
    public MemberDTO headRole(Long idM, DatesDTO datesDTO) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        Optional<ActiveHead> activeHead = activeHeadRepository.findByDepartmentId(mem.getDepartment().getId());
        //Optional<Member> memberSH = memberRepository.findByDepartmentIdAndRoleId(mem.getDepartment().getId(),1L);
        if(activeHead.isPresent()){
            throw  new Exception("The department already has a head member. Member with id: "+
                    activeHead.get().getMember().getId()+
                    " is active head for department with id: "+
                    activeHead.get().getDepartment().getId()+ " !");
        }
        if(datesDTO.getStartDate()==null || datesDTO.getEndDate()==null || datesDTO.getStartDate().isAfter(datesDTO.getEndDate()))
        {
            throw new Exception("The startdate and enddate are required parameters and startdate must be before enddate!");
        }
        if(mem.getRole().getId().equals(2L)){
            throw new Exception("This member is already a secretary member for the department he is part of Department with id: " +mem.getDepartment().getId() + " and cannot be selected as head!");
        }
        mem.setRole(new Role(1L,null));
        Member mH = memberRepository.save(mem);
        HeadHistory hh = headHistoryRepository.save(new HeadHistory(null,datesDTO.getStartDate(),datesDTO.getEndDate(),mH,mH.getDepartment()));
        activeHeadRepository.save(new ActiveHead(null,hh,mH,mH.getDepartment()));
        return memberConverter.toDto(mH);
    }

    @Override
    public MemberDTO regularRole(Long idM) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        if(mem.getRole().getId().equals(1L)) {
            mem.setRole(new Role(3L, null));
            Member mH = memberRepository.save(mem);
            Optional<ActiveHead> activeHead = activeHeadRepository.findByMemberIdAndDepartmentId(mH.getId(),mH.getDepartment().getId());
            if(activeHead.isPresent()){
                HeadHistory hh = activeHead.get().getHeadHistory();
                hh.setEndDate(LocalDate.now());
                headHistoryRepository.save(hh);
                activeHeadRepository.delete(activeHead.get());
            }
            return memberConverter.toDto(mH);
        }else if(mem.getRole().getId().equals(2L)) {
            mem.setRole(new Role(3L,null));
            Member mH = memberRepository.save(mem);
            Optional<ActiveSecretary> activeSecretary = activeSecretaryRepository.findByMemberIdAndDepartmentId(mH.getId(),mH.getDepartment().getId());
            if(activeSecretary.isPresent()){
                SecretaryHistory sh = activeSecretary.get().getSecretaryHistory();
                sh.setEndDate(LocalDate.now());
                secretaryHistoryRepository.save(sh);
                activeSecretaryRepository.delete(activeSecretary.get());
            }
            return memberConverter.toDto(mH);
        } else {
            throw new Exception("Member doesn't have "+ mem.getRole().getName() +" role.");
        }
    }

   /* @Override
    public MemberDTO removeSec(Long idM) throws Exception {
        Member mem = memberRepository.findById(idM).orElseThrow(() -> new Exception("Member doesn't exist!"));
        if(mem.getRole().getId().equals(2L)) {
        mem.setRole(new Role(3L,null));
        Member mH = memberRepository.save(mem);
        List<SecretaryHistory> history = secretaryHistoryRepository.findAllByMemberIdAndDepartmentIdOrderByEndDateDesc(mem.getId(),mem.getDepartment().getId());;
        if(!history.isEmpty()) {
            history.get(0).setEndDate(LocalDate.now());
            secretaryHistoryRepository.save(history.get(0));
        }
        return memberConverter.toDto(mH);
        }else {
            throw new Exception("Member doesn't have Secretary role.");
        }
    }*/

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

    @Override
    public MemberHistoriesDTO findAllHistories(Long id) throws Exception {
        memberRepository.findById(id).orElseThrow(() -> new Exception("Member doesn't exist!"));
    List<ATHDto> athDtos = academicTitleHistoryRepository.findAllByMemberId(id)
                .stream().map(entity -> athConverter.toDto(entity))
                .toList();
    List<HeadHistoryDTO> headHistoryDTOS = headHistoryRepository.findAllByMemberId(id)
            .stream().map(entity -> headHistoryConverter.toDto(entity))
            .toList();
    List<SecretaryHistoryDTO> secretaryHistoryDTOS = secretaryHistoryRepository.findAllByMemberId(id)
            .stream().map(entity -> secretaryHistoryConverter.toDto(entity))
            .toList();

    return new MemberHistoriesDTO(athDtos,secretaryHistoryDTOS, headHistoryDTOS);
    }
}

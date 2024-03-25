package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.*;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.converter.impl.MemberHeadSecConverter;
import nst.springboot.restexample01.converter.impl.RoleConverter;
import nst.springboot.restexample01.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceImplTest {

    @Mock
    private MemberConverter memberConverter;

    @Mock
    private MemberHeadSecConverter memberHeadSecConverter;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private AcademicTitleRepository academicTitleRepository;

    @Mock
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;

    @Mock
    private HeadHistoryRepository headHistoryRepository;

    @Mock
    private SecretaryHistoryRepository secretaryHistoryRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ActiveHeadRepository activeHeadRepository;

    @Mock
    private RoleConverter roleConverter;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveMember() throws Exception {
        MemberHeadSecDTO memberDTO = new MemberHeadSecDTO();
        memberDTO.setId(1L);
        memberDTO.setDepartment(1L);
        memberDTO.setRoleDTO(new RoleDTO(3L, null));

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setRole(new RoleDTO(3L, null));

        Department department = new Department();
        department.setId(1L);

        Member member = new Member();
        member.setId(1L);
        member.setDepartment(department);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(memberHeadSecConverter.toEntity(memberDTO)).thenReturn(member);
        when(memberRepository.save(member)).thenReturn(member);
        when(memberConverter.toDto(member)).thenReturn(memberDTO1);

        MemberDTO savedMember = memberService.save(memberDTO);

        assertEquals(savedMember.getId(), memberDTO.getId());
        assertEquals(savedMember.getDepartment().getId(), memberDTO.getDepartment());
    }

    @Test
    public void testSaveMemberWithNullId() throws Exception {
        MemberHeadSecDTO memberDTO = new MemberHeadSecDTO();
        memberDTO.setId(null); // Setting id to null
        memberDTO.setDepartment(1L);
        memberDTO.setRoleDTO(new RoleDTO(3L, null));

        Department department = new Department();
        department.setId(1L);

        Member member = new Member();
        member.setId(1L);
        member.setDepartment(department);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(memberHeadSecConverter.toEntity(memberDTO)).thenReturn(member);

        Exception exception = assertThrows(Exception.class, () -> {
            memberService.save(memberDTO);
        });
    }

    @Test
    public void testUpdate() throws Exception {

        Role role = new Role();
        role.setId(1L);

        MemberHeadSecDTO memberDTO = new MemberHeadSecDTO();
        memberDTO.setId(1L);
        memberDTO.setDepartment(1L);
        memberDTO.setAcademicTitle(new AcademicTitleDTO(1L, "Title"));
        memberDTO.setRoleDTO(new RoleDTO(1L, "Role"));

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setAcademicTitle(new AcademicTitleDTO(1L, "Title"));
        memberDTO1.setRole(new RoleDTO(1L, "Role"));

        Department department = new Department();
        Member member = new Member();
        member.setId(1L);
        member.setDepartment(department);
        member.setAcademicTitle(new AcademicTitle(2L, "Original Title"));
        member.setRole(role);

        AcademicTitleHistory academicTitleHistory = new AcademicTitleHistory();

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(roleConverter.toDto(role)).thenReturn(new RoleDTO(1L,""));
        when(memberHeadSecConverter.toEntity(memberDTO)).thenReturn(member);
        when(memberRepository.save(member)).thenReturn(member);
        when(academicTitleHistoryRepository.findAllByMemberIdAndAcademicTitleIdOrderByStartDateDesc(1L, 2L)).thenReturn(List.of(academicTitleHistory));
        when(academicTitleHistoryRepository.save(new AcademicTitleHistory())).thenReturn(new AcademicTitleHistory());
        when(memberConverter.toDto(member)).thenReturn(memberDTO1);

        MemberDTO updatedMemberDTO = memberService.update(memberDTO, 1L);

        assertEquals(updatedMemberDTO,memberDTO1);
    }

    @Test
    public void testUpdateMemberMemberNotFound() {
        Long memberId = 1L;
        MemberHeadSecDTO memberDTO = new MemberHeadSecDTO();
        memberDTO.setId(memberId);

        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> memberService.update(memberDTO, memberId),
                "Member doesn't exist");
    }

    @Test
    public void testGetAll(){
        Member member1 = new Member();
        member1.setId(1L);
        member1.setDepartment(new Department(1L,null,null));
        member1.setAcademicTitle(new AcademicTitle(1L, null));
        member1.setRole(new Role(1L,null));

        Member member2 = new Member();
        member2.setId(2L);
        member2.setDepartment(new Department(2L,null,null));
        member2.setAcademicTitle(new AcademicTitle(2L, null));
        member2.setRole(new Role(2L,null));

        List<Member> members = Arrays.asList(member1,member2);

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setAcademicTitle(new AcademicTitleDTO(1L, null));
        memberDTO1.setRole(new RoleDTO(1L, null));

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setId(2L);
        memberDTO2.setDepartment(new DepartmentDto(2L,null,null));
        memberDTO2.setAcademicTitle(new AcademicTitleDTO(2L, null));
        memberDTO2.setRole(new RoleDTO(2L, null));

        List<MemberDTO> memberDTOS = Arrays.asList(memberDTO1,memberDTO2);

        when(memberRepository.findAll()).thenReturn(members);
        when(memberConverter.toDto(member1)).thenReturn(memberDTO1);
        when(memberConverter.toDto(member2)).thenReturn(memberDTO2);

        List<MemberDTO> memberDTOList = memberService.getAll();

        assertEquals(memberDTOList.size(), memberDTOS.size());
        for (int i = 0; i < memberDTOList.size(); i++) {
            assertEquals(memberDTOList.get(i), memberDTOS.get(i));
        }
    }

    @Test
    public void testGetAllByDepartmentId(){
        Member member1 = new Member();
        member1.setId(1L);
        member1.setDepartment(new Department(1L,null,null));
        member1.setAcademicTitle(new AcademicTitle(1L, null));
        member1.setRole(new Role(1L,null));

        Member member2 = new Member();
        member2.setId(2L);
        member2.setDepartment(new Department(2L,null,null));
        member2.setAcademicTitle(new AcademicTitle(2L, null));
        member2.setRole(new Role(2L,null));

        List<Member> members = Arrays.asList(member1);

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setAcademicTitle(new AcademicTitleDTO(1L, null));
        memberDTO1.setRole(new RoleDTO(1L, null));

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setId(2L);
        memberDTO2.setDepartment(new DepartmentDto(2L,null,null));
        memberDTO2.setAcademicTitle(new AcademicTitleDTO(2L, null));
        memberDTO2.setRole(new RoleDTO(2L, null));

        List<MemberDTO> memberDTOS = Arrays.asList(memberDTO1);

        when(memberRepository.findAllByDepartmentId(1L)).thenReturn(members);
        when(memberConverter.toDto(member1)).thenReturn(memberDTO1);
        when(memberConverter.toDto(member2)).thenReturn(memberDTO2);

        List<MemberDTO> memberDTOList = memberService.getAllByDepartmentId(1L);

        assertEquals(memberDTOList.size(), memberDTOS.size());
        for (int i = 0; i < memberDTOList.size(); i++) {
            assertEquals(memberDTOList.get(i), memberDTOS.get(i));
        }
    }

    @Test
    public void testDelete() throws Exception {
        Member member1 = new Member();
        member1.setId(1L);
        member1.setDepartment(new Department(1L,null,null));
        member1.setAcademicTitle(new AcademicTitle(1L, null));
        member1.setRole(new Role(1L,null));

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member1));

        memberService.delete(1L);

        verify(memberRepository, times(1)).delete(member1);

    }

    @Test
    public void testDeleteNonExistingId(){
        Long badId = 2L;

        when(memberRepository.findById(badId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> memberService.delete(badId),
                "Member does not exist!");
    }

    @Test
    public void testFindById() throws Exception {
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setRole(new RoleDTO(3L, null));

        Department department = new Department();
        department.setId(1L);

        Member member = new Member();
        member.setId(1L);
        member.setDepartment(department);

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(memberConverter.toDto(member)).thenReturn(memberDTO1);

        MemberDTO newDto = memberService.findById(1L);

        assertEquals(newDto.getId(),memberDTO1.getId());
    }

    @Test
    public void testFindByIdWithNonExistingId() throws Exception{
        Long nonExistingId = 2L;

        when(memberRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> memberService.findById(nonExistingId),
                "Member does not exist!");
    }


}

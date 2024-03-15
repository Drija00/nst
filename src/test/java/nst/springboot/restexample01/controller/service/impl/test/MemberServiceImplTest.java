package nst.springboot.restexample01.controller.service.impl.test;

import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.*;
import nst.springboot.restexample01.controller.service.impl.MemberServiceImpl;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.converter.impl.MemberHeadSecConverter;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.MemberHeadSecDTO;
import nst.springboot.restexample01.dto.RoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveMember() throws Exception {
        // Arrange
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

        // Act
        MemberDTO savedMember = memberService.save(memberDTO);

        // Assert
        assertEquals(savedMember.getId(), memberDTO.getId());
        assertEquals(savedMember.getDepartment().getId(), memberDTO.getDepartment());
        verify(memberRepository, times(1)).save(member);
    }

    // Add more test methods for other methods in MemberServiceImpl
}

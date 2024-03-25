package nst.springboot.restexample01.controller;

import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MemberControllerTest {
    @Mock
    private MemberService memberService;
    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        MemberHeadSecDTO memberDTO = new MemberHeadSecDTO();
        memberDTO.setId(1L);
        memberDTO.setDepartment(1L);
        memberDTO.setRoleDTO(new RoleDTO(3L, null));

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setRole(new RoleDTO(3L, null));

        when(memberService.save(any())).thenReturn(memberDTO1);

        ResponseEntity<MemberDTO> response = memberController.save(memberDTO);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(response.getBody(),memberDTO1);
    }

    @Test
    public void testGetAll(){
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setRole(new RoleDTO(3L, null));

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setId(2L);
        memberDTO2.setDepartment(new DepartmentDto(2L,null,null));
        memberDTO2.setRole(new RoleDTO(3L, null));

        List<MemberDTO> memberDTOS = Arrays.asList(memberDTO1,memberDTO2);

        when(memberService.getAll()).thenReturn(memberDTOS);

        ResponseEntity<List<MemberDTO>> listResponseEntity = memberController.getAll();

        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        List<MemberDTO> memberDTOList = listResponseEntity.getBody();
        for (int i = 0; i < memberDTOList.size(); i++) {
            assertEquals(memberDTOList.get(i), memberDTOS.get(i));
        }
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(id);
        memberDTO1.setDepartment(new DepartmentDto(1L,null,null));
        memberDTO1.setRole(new RoleDTO(3L, null));

        when(memberService.findById(id)).thenReturn(memberDTO1);

        MemberDTO newDTO = memberController.findById(id);

        assertEquals(newDTO.getId(),memberDTO1.getId());
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;
        ResponseEntity<String> response = new ResponseEntity<>("Member removed!", HttpStatus.OK);

        ResponseEntity<String> responseEntity = memberController.delete(id);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals("Member removed!", responseEntity.getBody());
    }

    @Test
    public void testUpdate() throws Exception{
        Long id = 1L;

        MemberHeadSecDTO memberDTO = new MemberHeadSecDTO();
        memberDTO.setId(1L);
        memberDTO.setDepartment(2L);
        memberDTO.setRoleDTO(new RoleDTO(2L, null));

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId(1L);
        memberDTO1.setDepartment(new DepartmentDto(2L,null,null));
        memberDTO1.setRole(new RoleDTO(2L, null));

        when(memberService.update(memberDTO,id)).thenReturn(memberDTO1);

        ResponseEntity<MemberDTO> response = memberController.update(memberDTO,id);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(response.getBody(),memberDTO1);
    }
}

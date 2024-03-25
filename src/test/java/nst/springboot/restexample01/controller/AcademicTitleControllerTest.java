package nst.springboot.restexample01.controller;

import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.service.AcademicTitleService;
import nst.springboot.restexample01.controller.service.impl.AcademicTitleServiceImpl;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
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

public class AcademicTitleControllerTest {
    @Mock
    private AcademicTitleService academicTitleService;
    @InjectMocks
    private AcademicTitleController academicTitleController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() throws Exception {
        AcademicTitle title = new AcademicTitle(1L,"Bechalors");
        AcademicTitleDTO titleDTO = new AcademicTitleDTO(1L,"Bechalors");

        when(academicTitleService.save(any())).thenReturn(titleDTO);

        ResponseEntity<AcademicTitleDTO> response = academicTitleController.save(titleDTO);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(response.getBody(),titleDTO);
    }

    @Test
    public void testGetAll(){
        AcademicTitleDTO titleDTO1 = new AcademicTitleDTO(1L,"Bechalors");
        AcademicTitleDTO titleDTO2 = new AcademicTitleDTO(2L,"Master");

        List<AcademicTitleDTO> titles = Arrays.asList(titleDTO1,titleDTO2);

        when(academicTitleService.getAll()).thenReturn(titles);

        ResponseEntity<List<AcademicTitleDTO>> listResponseEntity = academicTitleController.getAll();

        assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        List<AcademicTitleDTO> titleDTOS = listResponseEntity.getBody();
        for (int i = 0; i < titleDTOS.size(); i++) {
            assertEquals(titleDTOS.get(i), titles.get(i));
        }
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        AcademicTitleDTO titleDTO = new AcademicTitleDTO(id,"Bechalors");

        when(academicTitleService.findById(id)).thenReturn(titleDTO);

        AcademicTitleDTO newDTO = academicTitleController.findById(id);

        assertEquals(newDTO.getId(),titleDTO.getId());
    }

    @Test
    public void testDelete() throws Exception {
        Long id = 1L;
        ResponseEntity<String> response = new ResponseEntity<>("Academic title removed!", HttpStatus.OK);

        ResponseEntity<String> responseEntity = academicTitleController.delete(id);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals("Academic title removed!", responseEntity.getBody());
    }
}

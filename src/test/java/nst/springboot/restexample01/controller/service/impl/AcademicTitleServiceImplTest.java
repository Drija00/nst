package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.AcademicTitleController;
import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.repository.AcademicTitleRepository;
import nst.springboot.restexample01.controller.service.AcademicTitleService;
import nst.springboot.restexample01.converter.impl.AcademicTitleConverter;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AcademicTitleServiceImplTest {
    @Mock
    private AcademicTitleConverter academicTitleConverter;

    @Mock
    private AcademicTitleRepository academicTitleRepository;

    @InjectMocks
    private AcademicTitleServiceImpl academicTitleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveAcademicTitle() throws Exception {
        AcademicTitleDTO academicTitleDTO = new AcademicTitleDTO(1L,"Bechalors");
        AcademicTitle title = new AcademicTitle(1L,"Bechalors");

        when(academicTitleConverter.toEntity(academicTitleDTO)).thenReturn(title);
        when(academicTitleRepository.save(title)).thenReturn(title);
        when(academicTitleConverter.toDto(title)).thenReturn(academicTitleDTO);

        AcademicTitleDTO newDTO = academicTitleService.save(academicTitleDTO);

        assertEquals(newDTO.getId(),academicTitleDTO.getId());
    }
}

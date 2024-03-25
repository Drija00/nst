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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testGetAll(){
        AcademicTitle title1 = new AcademicTitle(1L, "Bachelor");
        AcademicTitle title2 = new AcademicTitle(2L, "Master");
        List<AcademicTitle> academicTitles = Arrays.asList(title1, title2);

        AcademicTitleDTO dto1 = new AcademicTitleDTO(1L, "Bachelor");
        AcademicTitleDTO dto2 = new AcademicTitleDTO(2L, "Master");
        List<AcademicTitleDTO> expectedDTOs = Arrays.asList(dto1, dto2);

        when(academicTitleRepository.findAll()).thenReturn(academicTitles);
        when(academicTitleConverter.toDto(title1)).thenReturn(dto1);
        when(academicTitleConverter.toDto(title2)).thenReturn(dto2);

        List<AcademicTitleDTO> actualDTOs = academicTitleService.getAll();

        assertEquals(expectedDTOs.size(), actualDTOs.size());
        for (int i = 0; i < expectedDTOs.size(); i++) {
            assertEquals(expectedDTOs.get(i), actualDTOs.get(i));
        }
    }

    @Test
    public void testDeleteExistingTitle() throws Exception {
        Long existingId = 1L;
        AcademicTitle existingTitle = new AcademicTitle(existingId, "Bachelor");

        when(academicTitleRepository.findById(existingId)).thenReturn(Optional.of(existingTitle));

        academicTitleService.delete(existingId);

        verify(academicTitleRepository, times(1)).delete(existingTitle);
    }

    @Test
    public void testDeleteNonExistingTitle() {
        Long nonExistingId = 2L;

        when(academicTitleRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> academicTitleService.delete(nonExistingId),
                "Academic title does not exist!");
    }

    @Test
    public void testFindById() throws Exception{
        Long existingId = 1L;
        AcademicTitle existingTitle = new AcademicTitle(existingId, "Bachelor");

        AcademicTitleDTO academicTitleDTO = new AcademicTitleDTO(existingId,"Bachelor");

        when(academicTitleRepository.findById(existingId)).thenReturn(Optional.of(existingTitle));
        when(academicTitleConverter.toDto(existingTitle)).thenReturn(academicTitleDTO);

        AcademicTitleDTO newDto = academicTitleService.findById(existingId);

        assertEquals(newDto.getId(),existingTitle.getId());
    }

    @Test
    public void testFindByIdWithNonExistingId() throws Exception{
        Long nonExistingId = 2L;

        when(academicTitleRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> academicTitleService.findById(nonExistingId),
                "Academic title does not exist!");
    }



}

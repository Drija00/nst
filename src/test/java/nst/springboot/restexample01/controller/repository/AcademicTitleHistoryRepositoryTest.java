package nst.springboot.restexample01.controller.repository;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.service.impl.ATHServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AcademicTitleHistoryRepositoryTest {

    @Mock
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;

    @InjectMocks
    private ATHServiceImpl athService;

    @Test
    public void testFindAllByMemberIdAndAcademicTitleIdOrderByStartDateDesc() {
        Long memberId = 1L;
        Long academicTitleId = 2L;

        AcademicTitleHistory ath1 = new AcademicTitleHistory(1L,null,null,null,null,null);
        AcademicTitleHistory ath2 = new AcademicTitleHistory(2L,null,null,null,null,null);

        List<AcademicTitleHistory> histories = Arrays.asList(ath1,ath2);

        when(academicTitleHistoryRepository.findAllByMemberIdAndAcademicTitleIdOrderByStartDateDesc(memberId,academicTitleId)).thenReturn(histories);
        List<AcademicTitleHistory> histories1 = academicTitleHistoryRepository.findAllByMemberIdAndAcademicTitleIdOrderByStartDateDesc(memberId, academicTitleId);

        assertEquals(histories1.size(), histories.size());
        for (int i = 0; i < histories1.size(); i++) {
            assertEquals(histories1.get(i), histories.get(i));
        }
    }

    @Test
    public void testFindAllByMemberId() {
        Long memberId = 1L;

        AcademicTitleHistory ath1 = new AcademicTitleHistory(1L,null,null,null,null,null);
        AcademicTitleHistory ath2 = new AcademicTitleHistory(2L,null,null,null,null,null);

        List<AcademicTitleHistory> histories = Arrays.asList(ath1,ath2);

        when(academicTitleHistoryRepository.findAllByMemberId(memberId)).thenReturn(histories);
        List<AcademicTitleHistory> histories1 = academicTitleHistoryRepository.findAllByMemberId(memberId);

        assertEquals(histories1.size(), histories.size());
        for (int i = 0; i < histories1.size(); i++) {
            assertEquals(histories1.get(i), histories.get(i));
        }
    }
}

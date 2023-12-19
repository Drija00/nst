package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.AcademicTitleHistoryRepository;
import nst.springboot.restexample01.controller.repository.AcademicTitleRepository;
import nst.springboot.restexample01.controller.service.ATHService;
import nst.springboot.restexample01.converter.impl.ATHConverter;
import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ATHServiceImpl implements ATHService {
    private ATHConverter athConverter;
    private AcademicTitleHistoryRepository athRepository;

    public ATHServiceImpl(ATHConverter athConverter, AcademicTitleHistoryRepository athRepository) {
        this.athConverter = athConverter;
        this.athRepository = athRepository;
    }

    @Override
    public ATHDto save(ATHDto athDto) throws Exception {
        AcademicTitleHistory history = athConverter.toEntity(athDto);
        history = athRepository.save(history);
        return athConverter.toDto(history);
    }

    @Override
    public List<ATHDto> getAll() {
        return athRepository
                .findAll()
                .stream().map(entity -> athConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<AcademicTitleHistory> history = athRepository.findById(id);
        if (history.isPresent()) {
            AcademicTitleHistory history1 = history.get();
            athRepository.delete(history1);
        } else {
            throw new Exception("Academic title history does not exist!");
        }
    }

    @Override
    public void update(ATHDto athDto) throws Exception {
        throw new Exception();
    }

    @Override
    public ATHDto findById(Long id) throws Exception {
        Optional<AcademicTitleHistory> history = athRepository.findById(id);
        if (history.isPresent()) {
            AcademicTitleHistory history1 = history.get();
            return athConverter.toDto(history1);
        } else {
            throw new Exception("Academic title history not exist!");
        }
    }
}

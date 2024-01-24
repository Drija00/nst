package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.HeadHistoryController;
import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.HeadHistoryRepository;
import nst.springboot.restexample01.controller.repository.MemberRepository;
import nst.springboot.restexample01.controller.service.HeadHistoryService;
import nst.springboot.restexample01.converter.impl.HeadHistoryConverter;
import nst.springboot.restexample01.converter.impl.MemberHeadSecConverter;
import nst.springboot.restexample01.dto.HeadHistoryDTO;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.MemberHeadSecDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeadHistoryServiceImpl implements HeadHistoryService {

    private HeadHistoryRepository repository;

    private HeadHistoryConverter headHistoryConverter;
    private MemberRepository memberRepository;

    public HeadHistoryServiceImpl(HeadHistoryRepository repository, HeadHistoryConverter headHistoryConverter, MemberRepository memberRepository) {
        this.repository = repository;
        this.headHistoryConverter = headHistoryConverter;
        this.memberRepository = memberRepository;
    }

    @Override
    public HeadHistoryDTO save(HeadHistoryDTO headHistory) throws Exception {
        HeadHistory history = headHistoryConverter.toEntity(headHistory);
        history = repository.save(history);
        return headHistoryConverter.toDto(history);
    }

    @Override
    public List<HeadHistoryDTO> getAll() {
        return repository
                .findAll()
                .stream().map(entity -> headHistoryConverter.toDto(entity))
                .collect(Collectors.toList());
    }
    @Override
    public HeadHistoryDTO getByMemberIdAndDepartmentId(Long idD) throws Exception {
        Member member = memberRepository.findByDepartmentIdAndRoleId(idD,1L).orElseThrow(() -> new Exception("Department doesn't have active head!"));
        List<HeadHistory> headHistories = repository.findAllByMemberIdAndDepartmentIdOrderByEndDateDesc(member.getId(),idD);
        return headHistoryConverter.toDto(headHistories.get(0));
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<HeadHistory> history = repository.findById(id);
        if (history.isPresent()) {
            HeadHistory history1 = history.get();
            repository.delete(history1);
        } else {
            throw new Exception("Head history does not exist!");
        }
    }

    @Override
    public void update(HeadHistoryDTO headHistory) throws Exception {

    }

    @Override
    public HeadHistoryDTO findById(Long id) throws Exception {
        Optional<HeadHistory> history = repository.findById(id);
        if (history.isPresent()) {
            HeadHistory history1 = history.get();
            return headHistoryConverter.toDto(history1);
        } else {
            throw new Exception("Head history not exist!");
        }
    }
}

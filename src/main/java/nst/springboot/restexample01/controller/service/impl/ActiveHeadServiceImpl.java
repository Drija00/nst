package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.ActiveHead;
import nst.springboot.restexample01.controller.domain.Role;
import nst.springboot.restexample01.controller.repository.ActiveHeadRepository;
import nst.springboot.restexample01.controller.service.ActiveHeadService;
import nst.springboot.restexample01.converter.impl.ActiveHeadConverter;
import nst.springboot.restexample01.dto.ActiveHeadDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiveHeadServiceImpl implements ActiveHeadService {

    private ActiveHeadConverter activeHeadConverter;
    private ActiveHeadRepository activeHeadRepository;

    public ActiveHeadServiceImpl(ActiveHeadConverter activeHeadConverter, ActiveHeadRepository activeHeadRepository) {
        this.activeHeadConverter = activeHeadConverter;
        this.activeHeadRepository = activeHeadRepository;
    }

    @Override
    public ActiveHeadDTO save(ActiveHeadDTO activeHeadDTO) throws Exception {
        ActiveHead activeHead = activeHeadConverter.toEntity(activeHeadDTO);
        activeHead = activeHeadRepository.save(activeHead);
        return activeHeadConverter.toDto(activeHead);
    }

    @Override
    public List<ActiveHeadDTO> getAll() {
        return activeHeadRepository
                .findAll()
                .stream().map(entity -> activeHeadConverter.toDto(entity))
                .collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ActiveHead> activeHead = activeHeadRepository.findById(id);
        if (activeHead.isPresent()) {
            ActiveHead activeHead1 = activeHead.get();
            activeHeadRepository.delete(activeHead1);
        } else {
            throw new Exception("Active head does not exist!");
        }
    }

    @Override
    public void update(ActiveHeadDTO activeHeadDTO) throws Exception {

    }

    @Override
    public ActiveHeadDTO findById(Long id) throws Exception {
        Optional<ActiveHead> activeHead = activeHeadRepository.findById(id);
        if (activeHead.isPresent()) {
            ActiveHead activeHead1 = activeHead.get();
            return activeHeadConverter.toDto(activeHead1);
        } else {
            throw new Exception("Active head does not exist!");
        }
    }
}

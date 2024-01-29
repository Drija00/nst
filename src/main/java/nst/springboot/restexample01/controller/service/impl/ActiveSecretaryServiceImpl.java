package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.ActiveSecretary;
import nst.springboot.restexample01.controller.repository.ActiveSecretaryRepository;
import nst.springboot.restexample01.controller.service.ActiveSecretaryService;
import nst.springboot.restexample01.converter.impl.ActiveSecretaryConverter;
import nst.springboot.restexample01.dto.ActiveSecretaryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiveSecretaryServiceImpl implements ActiveSecretaryService {

    private ActiveSecretaryConverter activeSecretaryConverter;
    private ActiveSecretaryRepository activeSecretaryRepository;

    public ActiveSecretaryServiceImpl(ActiveSecretaryConverter activeSecretaryConverter, ActiveSecretaryRepository activeSecretaryRepository) {
        this.activeSecretaryConverter = activeSecretaryConverter;
        this.activeSecretaryRepository = activeSecretaryRepository;
    }


    @Override
    public ActiveSecretaryDTO save(ActiveSecretaryDTO activeSecretaryDTO) throws Exception {
        ActiveSecretary activeSecretary = activeSecretaryConverter.toEntity(activeSecretaryDTO);
        activeSecretary = activeSecretaryRepository.save(activeSecretary);
        return activeSecretaryConverter.toDto(activeSecretary);
    }

    @Override
    public List<ActiveSecretaryDTO> getAll() {
        return activeSecretaryRepository
                .findAll()
                .stream().map(entity -> activeSecretaryConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ActiveSecretary> activeSecretary = activeSecretaryRepository.findById(id);
        if (activeSecretary.isPresent()) {
            ActiveSecretary activeSecretary1 = activeSecretary.get();
            activeSecretaryRepository.delete(activeSecretary1);
        } else {
            throw new Exception("Active secretary does not exist!");
        }
    }

    @Override
    public void update(ActiveSecretaryDTO activeSecretaryDTO) throws Exception {

    }

    @Override
    public ActiveSecretaryDTO findById(Long id) throws Exception {
        Optional<ActiveSecretary> activeSecretary = activeSecretaryRepository.findById(id);
        if (activeSecretary.isPresent()) {
            ActiveSecretary activeSecretary1 = activeSecretary.get();
            return activeSecretaryConverter.toDto(activeSecretary1);
        } else {
            throw new Exception("Active secretary does not exist!");
        }
    }
}


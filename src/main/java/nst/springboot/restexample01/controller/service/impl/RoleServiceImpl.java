package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.AcademicTitle;
import nst.springboot.restexample01.controller.domain.Role;
import nst.springboot.restexample01.controller.repository.RoleRepository;
import nst.springboot.restexample01.controller.service.RoleService;
import nst.springboot.restexample01.converter.impl.RoleConverter;
import nst.springboot.restexample01.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleConverter roleConverter;
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleConverter roleConverter, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) throws Exception {
        Role role = roleConverter.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleConverter.toDto(role);
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleRepository
                .findAll()
                .stream().map(entity -> roleConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role role1 = role.get();
            roleRepository.delete(role1);
        } else {
            throw new Exception("Role does not exist!");
        }
    }

    @Override
    public void update(RoleDTO roleDTO) throws Exception {
        throw new Exception();
    }

    @Override
    public RoleDTO findById(Long id) throws Exception {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role role1 = role.get();
            return roleConverter.toDto(role1);
        } else {
            throw new Exception("Role does not exist!");
        }
    }
}

package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.service.RoleService;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import nst.springboot.restexample01.dto.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO roleDTO) throws Exception {
        //ResponseEntity
        RoleDTO dto = roleService.save(roleDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        List<RoleDTO> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public RoleDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return roleService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public RoleDTO queryById(@RequestParam("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        roleService.delete(id);
        return new ResponseEntity<>("Role removed!", HttpStatus.OK);

    }
}

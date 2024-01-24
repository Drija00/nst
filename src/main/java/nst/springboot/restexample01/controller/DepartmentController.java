/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import java.util.List;
import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.repository.SecretaryHistoryRepository;
import nst.springboot.restexample01.controller.service.DepartmentService;
import nst.springboot.restexample01.controller.service.HeadHistoryService;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.controller.service.SecretaryHistoryService;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.HeadHistoryDTO;
import nst.springboot.restexample01.dto.MemberDTO;
import nst.springboot.restexample01.dto.SecretaryHistoryDTO;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import nst.springboot.restexample01.exception.MyErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author student2
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;
    private MemberService memberService;
    private SecretaryHistoryService secretaryHistoryService;
    private HeadHistoryService headHistoryService;

    public DepartmentController(DepartmentService departmentService, MemberService memberService, SecretaryHistoryService secretaryHistoryService, HeadHistoryService headHistoryService) {
        this.departmentService = departmentService;
        this.memberService = memberService;
        this.secretaryHistoryService = secretaryHistoryService;
        this.headHistoryService = headHistoryService;
    }

    //dodaj novi department
    @PostMapping
    public ResponseEntity<DepartmentDto> save(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        //ResponseEntity
        DepartmentDto deptDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }
    /*@PutMapping
    public ResponseEntity<DepartmentDto> update(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        //ResponseEntity
        DepartmentDto deptDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptDto, HttpStatus.CREATED);
    }*/

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    /*@GetMapping("/paging")
    public ResponseEntity<List<DepartmentDto>> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        
        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<DepartmentDto> departments = departmentService.getAll(pageable);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }*/

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public DepartmentDto findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return departmentService.findById(id);
    }
    @GetMapping("/{id}/members")
    public List<MemberDTO> findMembers(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return memberService.getAllByDepartmentId(id);
    }
    @GetMapping("/{id}/head")
    public HeadHistoryDTO findHeadHistories(@PathVariable("id") Long idDepartment) throws Exception {
        return headHistoryService.getByMemberIdAndDepartmentId(idDepartment);
    }
    @GetMapping("/{id}/secretary")
    public SecretaryHistoryDTO findSecretaryHistories(@PathVariable("id") Long idDepartment) throws Exception {
        return secretaryHistoryService.getByMemberIdAndDepartmentId(idDepartment);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public DepartmentDto queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        throw new Exception("Nije implementirana.");
    }

    //azuriraj
    //obrisi
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        /*
        try {
            departmentService.delete(id);
            return new ResponseEntity<>("Department removed!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(">>" + e.getMessage(), HttpStatus.NOT_FOUND);
        }*/

        departmentService.delete(id);
        return new ResponseEntity<>("Department removed!", HttpStatus.OK);

    }

    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> handleException(Exception e){
        System.out.println("nst.springboot.restexample01.controller.DepartmentController.handleException()");
        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");
        
        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());
        
        return new ResponseEntity<>(myErrorDetails, HttpStatus.NOT_FOUND);

    }*/
    @ExceptionHandler(DepartmentAlreadyExistException.class)
    public ResponseEntity<MyErrorDetails> handleException(DepartmentAlreadyExistException e) {
        System.out.println("nst.springboot.restexample01.controller.DepartmentController.handleException()");
        System.out.println("-----------pozvana metoda za obradu izuzetka u kontroleru -------------");

        MyErrorDetails myErrorDetails = new MyErrorDetails(e.getMessage());

        return new ResponseEntity<>(myErrorDetails, HttpStatus.BAD_REQUEST);

    }
}

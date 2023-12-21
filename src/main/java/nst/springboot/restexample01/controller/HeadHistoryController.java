package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.domain.HeadHistory;
import nst.springboot.restexample01.controller.service.HeadHistoryService;
import nst.springboot.restexample01.dto.AcademicTitleDTO;
import nst.springboot.restexample01.dto.HeadHistoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/head")
public class HeadHistoryController {

    private HeadHistoryService service;

    public HeadHistoryController(HeadHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HeadHistoryDTO> save(@Valid @RequestBody HeadHistoryDTO headHistory) throws Exception {
        //ResponseEntity
        HeadHistoryDTO history = service.save(headHistory);
        return new ResponseEntity<>(history, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HeadHistoryDTO>> getAll() {
        List<HeadHistoryDTO> histories = service.getAll();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public HeadHistoryDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return service.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public HeadHistoryDTO queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        System.out.println("Controller: " + id);
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        /*
        try {
            departmentService.delete(id);
            return new ResponseEntity<>("Department removed!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(">>" + e.getMessage(), HttpStatus.NOT_FOUND);
        }*/

        service.delete(id);
        return new ResponseEntity<>("Head history removed!", HttpStatus.OK);

    }
}

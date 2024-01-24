package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.service.ATHService;
import nst.springboot.restexample01.dto.ATHDto;
import nst.springboot.restexample01.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic_title_history")
public class ATHController {

    private ATHService athService;

    public ATHController(ATHService athService) {
        this.athService = athService;
    }

    @PostMapping
    public ResponseEntity<ATHDto> save(@Valid @RequestBody ATHDto athDto) throws Exception {
        //ResponseEntity
        ATHDto ath = athService.save(athDto);
        return new ResponseEntity<>(ath, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ATHDto>> getAll() {
        List<ATHDto> athDtos = athService.getAll();
        for (ATHDto ath: athDtos){
            System.out.println(ath);
        }
        return new ResponseEntity<>(athDtos, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public ATHDto findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return athService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public ATHDto queryById(@RequestParam("id") Long id) throws Exception {
        //return departmentService.findById(id);
        System.out.println("Controller: " + id);
        return athService.findById(id);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        /*
        try {
            departmentService.delete(id);
            return new ResponseEntity<>("Department removed!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(">>" + e.getMessage(), HttpStatus.NOT_FOUND);
        }

        athService.delete(id);
        return new ResponseEntity<>("Academic title history removed!", HttpStatus.OK);

    }*/
}

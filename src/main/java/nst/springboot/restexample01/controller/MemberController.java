package nst.springboot.restexample01.controller;

import jakarta.validation.Valid;
import nst.springboot.restexample01.controller.domain.AcademicTitleHistory;
import nst.springboot.restexample01.controller.domain.Member;
import nst.springboot.restexample01.controller.service.ATHService;
import nst.springboot.restexample01.controller.service.MemberService;
import nst.springboot.restexample01.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDTO> save(@Valid @RequestBody MemberHeadSecDTO memberDTO) throws Exception {
        //ResponseEntity
        MemberDTO member = memberService.save(memberDTO);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(@Valid @RequestBody MemberHeadSecDTO memberDTO, @PathVariable Long id) throws Exception {
        MemberDTO member = memberService.update(memberDTO,id);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/to-secretary-role")
    public ResponseEntity<MemberDTO> secRole(@PathVariable Long id, @RequestBody DatesDTO datesDTO) throws Exception {
        MemberDTO member = memberService.secRole(id, datesDTO);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PatchMapping("/{id}/to-head-role")
    public ResponseEntity<MemberDTO> headRole(@PathVariable Long id, @RequestBody DatesDTO datesDTO) throws Exception {
        MemberDTO member = memberService.headRole(id, datesDTO);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    @PatchMapping("/{id}/to-regular-role")
    public ResponseEntity<MemberDTO> regularRole(@PathVariable Long id) throws Exception {
        MemberDTO member = memberService.regularRole(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    /*@PatchMapping("/{id}/secretary/remove")
    public ResponseEntity<MemberDTO> removeSec(@PathVariable Long id) throws Exception {
        MemberDTO member = memberService.removeSec(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAll() {
        List<MemberDTO> members = memberService.getAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{memberId}/histories")
    public ResponseEntity<MemberHistoriesDTO> getAllMemberHistories(@PathVariable Long memberId) throws Exception {
        MemberHistoriesDTO hietories = memberService.findAllHistories(memberId);
        return new ResponseEntity<>(hietories, HttpStatus.OK);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/1
    @GetMapping("/{id}")
    public MemberDTO findById(@PathVariable("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return memberService.findById(id);
    }

    //pronadji na osnovu ID/a
    //localhost:8080/department/query?id=1
    @GetMapping("/query")
    public MemberDTO queryById(@RequestParam("id") Long id) throws Exception {
        System.out.println("Controller: " + id);
        return memberService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        memberService.delete(id);
        return new ResponseEntity<>("Member removed!", HttpStatus.OK);

    }
}

package com.enigma.controller;

import com.enigma.constants.UrlMappings;
import com.enigma.model.Customer;
import com.enigma.model.Member;
import com.enigma.model.request.CustomerRequest;
import com.enigma.model.request.MemberRequest;
import com.enigma.model.response.SuccessResponse;
import com.enigma.repository.MemberRepository;
import com.enigma.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMappings.MEMBER_URL)
public class MemberController {

    private ModelMapper modelMapper;

    private MemberService service;
    private final MemberRepository memberRepository;

    public MemberController(
            @Autowired ModelMapper modelMapper,
            @Autowired MemberService service,
            MemberRepository memberRepository) {
        this.modelMapper = modelMapper;
        this.service = service;
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public ResponseEntity createMember(@RequestBody MemberRequest request){
        Member newMember = modelMapper.map(request, Member.class);

        Member result = service.create(newMember);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success add member",result));
    }

    @GetMapping
    public ResponseEntity getAllMember() {

        List<Member> result= service.getAll();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>( "Success get all members",result));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Member member = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find ID", member));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@Valid @RequestBody MemberRequest request, @PathVariable("id") Long id) {
        Member existingMember = modelMapper.map(request, Member.class);
        existingMember.setId(id);
        service.update(existingMember, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully update", request));
    }


}

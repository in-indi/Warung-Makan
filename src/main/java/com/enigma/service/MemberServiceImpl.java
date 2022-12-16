package com.enigma.service;

import com.enigma.exception.NotFoundException;
import com.enigma.model.Customer;
import com.enigma.model.Member;
import com.enigma.model.MenuPrice;
import com.enigma.repository.MemberRepository;
import com.enigma.repository.MenuPriceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    private ModelMapper modelMapper;

    public MemberServiceImpl(
            @Autowired MemberRepository memberRepository,
            @Autowired ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Member create(Member member) {
        try{
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e ){
            throw new EntityExistsException("Member Already Exist");
        }
    }

    @Transactional
    public List<Member> getAll() {
        List<Member> members = memberRepository.findAll();
        if( members.isEmpty()){
            throw new NotFoundException("No Member Found");
        }
        return members;
    }

    @Override
    public Member get(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return member.get();
    }

    @Override
    public void update(Member member, Long id) {
        try {
            Member existingMember = get(id);
            modelMapper.map(member, existingMember);
            memberRepository.save(existingMember);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed, ID not found");
        }
    }
}

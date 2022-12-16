package com.enigma.service;

import com.enigma.model.Member;
import com.enigma.model.MenuPrice;

import java.util.List;

public interface MemberService {

    Member create(Member member);

    List<Member> getAll();

    Member get(Long id);

    void update(Member member, Long id);

}

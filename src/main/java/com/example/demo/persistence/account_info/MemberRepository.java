package com.example.demo.persistence.account_info;

import com.example.demo.model.account_info.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository <Member, Long> {

    List<Member> findMemberByEmailOrId (String Email, String Id);

    Member findFirstById(String Id);

}

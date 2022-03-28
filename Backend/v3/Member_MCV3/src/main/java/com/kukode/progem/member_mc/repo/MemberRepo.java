package com.kukode.progem.member_mc.repo;

import com.kukode.progem.member_mc.models.entities.Member;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemberRepo extends ReactiveCrudRepository<Member,String> {
}

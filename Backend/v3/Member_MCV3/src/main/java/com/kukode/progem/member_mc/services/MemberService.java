package com.kukode.progem.member_mc.services;

import com.kukode.progem.member_mc.models.entities.Member;
import com.kukode.progem.member_mc.repo.MemberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemberService {
    final Logger log = LoggerFactory.getLogger("Member service");
    final MemberRepo memberRepo;

    public MemberService(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    /**
     * returns a member entity from db, if not found will create one and return it
     *
     * @param id the id of the member
     * @return a member entity object.
     */
    public Mono<Member> getMemberEntityFromDB(String id) {
        log.info("GETTING Member entity from db with ID {}",id);
        return memberRepo.findById(id)
                .defaultIfEmpty(new Member(true))
                .flatMap(member ->
                        {
                            if (!member.isValid()) {
                                log.info("no Member found, creating a new one");
                                //Create a new record in database
                                Member newMember = new Member(true, id, "");
                                newMember.setMembers("");
                                newMember.setId(id);
                                return memberRepo.save(newMember);

                            } else {
                                log.info("GetMemberEntityFromDB member found with values {}", member);
                                //Record found in database so we can return it
                                return Mono.just(member);
                            }
                        }
                );
    }

    public Mono<Member> updateMember(Member member) {
        return memberRepo.save(member);
    }
}

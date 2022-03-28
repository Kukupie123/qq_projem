package com.kukode.progem.project_mc.services;

import com.kukode.progem.project_mc.models.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemberService {

    public Mono<ResponseEntity<BaseResponse<Void>>> addLeader(int projectID){
        return null;
    }
}

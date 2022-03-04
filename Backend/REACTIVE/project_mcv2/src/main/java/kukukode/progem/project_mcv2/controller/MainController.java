package kukukode.progem.project_mcv2.controller;

import kukukode.progem.project_mcv2.entity.ProjectEntity;
import kukukode.progem.project_mcv2.response.BaseResponse;
import kukukode.progem.project_mcv2.response.BaseResponseImp;
import kukukode.progem.project_mcv2.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/project")
public class MainController {
    final ProjectService projectService;

    public MainController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //Creates the project and returns it
    @PostMapping("/root")
    public Mono<ResponseEntity<BaseResponse<ProjectEntity>>> createRootProject(@RequestBody ProjectEntity projectEntity) {
        return projectService.createRootProject(projectEntity)
                .flatMap(projectEntity1 -> {
                    BaseResponseImp<ProjectEntity> baseResponseImp = new BaseResponseImp<>(projectEntity1, "success");
                    return Mono.just(ResponseEntity.ok().body(baseResponseImp));
                });
    }
}

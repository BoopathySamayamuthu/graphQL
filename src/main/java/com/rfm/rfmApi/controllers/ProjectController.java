package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.entities.Project;
import com.rfm.rfmApi.exceptions.ResourceNotFoundException;
import com.rfm.rfmApi.repositories.ProjectRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/projects")
@Api(value = "Simplified Account Management System", description = "Operations pertaining to project in Simplified Account Management System")
@RestController
public class ProjectController {
    private static final Logger logger = LogManager.getLogger(ProjectController.class);

    @Autowired
    private ProjectRepository projectRepository;


    //Get All Projects
    @ApiOperation(value = "View a list of available projects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 403, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "The vendors were not found, ensure the query parameters are correct.", response = String.class),
            @ApiResponse(code = 405, message = "Validation exception for the API invocation.", response = String.class)})
    @GetMapping("/getProjects")
    public ResponseEntity<List<Project>> index() {
        logger.info("Requesting details of all projects ");
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    //Get Project By sow project Id
    @ApiOperation(value = "Return a project containing the give SOW ID", response = Object.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class),
            @ApiResponse(code = 400, message = "Invalid data combination", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 403, message = "Forbidden", response = String.class),
            @ApiResponse(code = 404, message = "The vendors were not found, ensure the query parameters are correct.", response = String.class),
            @ApiResponse(code = 405, message = "Validation exception for the API invocation.", response = String.class)})
    @GetMapping("/findBy")
    public Project getProject(@RequestParam(value = "sowProjectId") String sowProjectId) throws ResourceNotFoundException {

        logger.info("Requesting details of project {} ", sowProjectId);
        return projectRepository.findBySowProjectId(sowProjectId).orElseThrow(() -> new ResourceNotFoundException("SOW project ID Not found " + sowProjectId));
    }

    @PostMapping("/addProject")
    public Project create(@Valid @RequestBody Project project) {

        logger.info("Request received to add a project with ESA name {} and SOW name {} ", project.getEsaProjectName(), project.getSowProjectName());

        return projectRepository.save(project);

    }

    @PutMapping("/update/{sowProjectId}")
    public ResponseEntity<Project> update(@PathVariable(value = "sowProjectId") String sowProjectId, @RequestBody Project project) throws ResourceNotFoundException {

        Project p;
        p = (projectRepository.findBySowProjectId(sowProjectId).orElseThrow(() -> new ResourceNotFoundException("SOW project ID Not found" + sowProjectId)));

        p.setEsaProjectId(project.getEsaProjectId());
        p.setEsaProjectName(project.getEsaProjectName());
        p.setAccountName(project.getAccountName());
        p.setSowProjectId(project.getSowProjectId());
        p.setSowProjectName(project.getSowProjectName());
        p.setProjectActiveStatus(project.getProjectActiveStatus());
        p.setDepartment(project.getDepartment());
        p.setProjectType(project.getProjectType());
        p.setSbu(project.getSbu());

        final Project updatedProject = projectRepository.save(p);
        return ResponseEntity.ok(updatedProject);

    }

    @PostMapping("/bulkUpload")
    public List<Project> createBatch(@Valid @RequestBody List<Project> projectList) {
        logger.info("Request received to add a batch of projects containing {} entries ", projectList.size());

        batchInsert(projectList, projectRepository);

        return projectList;

    }

    public static void batchInsert(@RequestBody @Valid List<Project> projectList, ProjectRepository projectRepository) {
        int size = projectList.size();
        int counter = 0;
        List<Project> temp = new ArrayList<>();
        for (Project project : projectList) {
            temp.add(project);
            projectRepository.save(temp.get(counter));
            temp.clear();

        }
    }

}

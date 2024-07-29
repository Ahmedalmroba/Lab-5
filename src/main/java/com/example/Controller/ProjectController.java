package com.example.Controller;

import com.example.ApiResponse.Api;
import com.example.Model.Project;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

        private List<Project> projects = new ArrayList<>();

        @GetMapping("/get")
        public List<Project> getAllProjects() {
            return projects;
        }

        @PostMapping("/add")
        public Api createProject(@RequestBody Project project) {
            projects.add(project);
            return new Api("Project created");
        }

        @PutMapping("/update/{index}")
        public Api updateProject(@PathVariable int index, @RequestBody Project project) {

                projects.set(index, project);
            return new Api("Project updated");}

        @DeleteMapping("/delete/{index}")
        public Api deleteProject(@PathVariable int index) {

            projects.remove(index);
          return new Api("Project deleted");}



        @PutMapping("/status/{index}")
        public Api changeProjectStatus(@PathVariable int index){
            Project project = projects.get(index);
            if (project.getStatus().equalsIgnoreCase("not Done"))
            { project.setStatus("done")
            ;}
            else return new Api("project already done " );
            return new Api("project status changed");
        }

        @GetMapping("/search")
        public List<Project> searchProjectsByTitle(@RequestParam String title) {
            List<Project> foundProjects = new ArrayList<>();
            for (Project project : projects) {
                if (project.getTitle().equals(title)) {
                    foundProjects.add(project);
                }
            }
            return foundProjects;
        }

        @GetMapping("/company/{companyName}")
        public List<Project> getProjectsByCompany(@PathVariable String companyName) {
            List<Project> companyProjects = new ArrayList<>();
            for (Project project : projects) {
                if (project.getCompanyName().equals(companyName)) {
                    companyProjects.add(project);
                }
            }
            return companyProjects;
}}


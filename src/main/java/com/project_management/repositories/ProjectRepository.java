package com.project_management.repositories;

import com.project_management.entities.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project , Long> {
//    @Query("SELECT p FROM Project p JOIN FETCH p.tasks") // this will not fetch the project where there is no tasks
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.tasks")
//    @EntityGraph(attributePaths = {"tasks"})
    List<Project> findAllWithTasks();

    @EntityGraph(attributePaths = {"tasks"})
    @Override
    List<Project> findAll();
}

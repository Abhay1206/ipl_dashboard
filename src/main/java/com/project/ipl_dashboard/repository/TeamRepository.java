package com.project.ipl_dashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.ipl_dashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);
}

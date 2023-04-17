package com.project.ipl_dashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.ipl_dashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.team1= :teamName or m.team2= :teamName) and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesbyTeamBetweenDates(@Param("teamName") String teamName,
            @Param("dateStart") LocalDate datestart, @Param("dateEnd") LocalDate dateEnd);

    List<Match> getByTeam1OrTeam2AndDateBetweenOrderByDateDesc(String teamName1, String teamName2, LocalDate date1,
            LocalDate date2);

    default List<Match> findLatestMatches(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }

}

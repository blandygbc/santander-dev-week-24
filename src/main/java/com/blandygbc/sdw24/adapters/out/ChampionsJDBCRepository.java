package com.blandygbc.sdw24.adapters.out;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.blandygbc.sdw24.domain.model.ChampionRec;
import com.blandygbc.sdw24.domain.ports.ChampionsRepository;

@Repository
public class ChampionsJDBCRepository implements ChampionsRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<ChampionRec> championsRecRowMapper;

    public ChampionsJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.championsRecRowMapper = (rs, rowNum) -> new ChampionRec(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("role"),
                rs.getString("lore"),
                rs.getString("image_url"));
    }

    @Override
    public List<ChampionRec> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", championsRecRowMapper);
    }

    @Override
    public Optional<ChampionRec> findById(Long id) {
        String query = "SELECT * FROM CHAMPIONS WHERE ID = ?";
        List<ChampionRec> champs = jdbcTemplate.query(query, championsRecRowMapper, id);
        return champs.stream().findFirst();
    }

}

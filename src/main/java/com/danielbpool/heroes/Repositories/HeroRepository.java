package com.danielbpool.heroes.Repositories;

import com.danielbpool.heroes.model.Hero;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HeroRepository {

    class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("id"));
            hero.setName(rs.getString("name"));
            return hero;
        }
    }
    private final JdbcTemplate jdbc;
    private final HeroMapper heroMapper;

    public HeroRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        heroMapper = new HeroMapper();
    }

    public void storeHero(Hero hero) {
        if (hero.getId() == -1) {
            String insertQuery = "INSERT INTO heroes VALUES (NULL, ?)";
            jdbc.update(insertQuery, hero.getName());
        } else {
            String updateQuery = "update heroes set name = ? where id = ?";
            jdbc.update(updateQuery, hero.getName(), hero.getId());
        }
    }

    public List<Hero> fetchHeroes() {
        String sql = "SELECT * FROM heroes";
        return jdbc.query(sql, this.heroMapper);
    }

    public Hero fetchHero(int id) {
        String sql = "SELECT * FROM heroes where id = ?";
        return jdbc.queryForObject(sql, heroMapper, id);
    }
}

package spring;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where email = ?",
                new MemberRowMapper(),
                email);

        return results.isEmpty() ? null : results.get(0);
    }

    public void insert(Member member) {
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail()
        );
    }

    public List<Member> selectAll() {
        return jdbcTemplate.query("select * from MEMBER",
                new MemberRowMapper());
    }

    public int count() {
        return jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class
        );
    }
}

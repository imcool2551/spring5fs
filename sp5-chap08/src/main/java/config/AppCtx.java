package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberDao;

@Configuration
public class AppCtx {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc://mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("root");
        ds.setPassword("1234");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true); // 유휴 커넥션 검사 활성화
        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3); // 3분이 지나면 유휴 커넥션 끊기
        ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초마다 검사
        return ds;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }
}

package com.istz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DokkanbattleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DokkanbattleApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbc;

    // アプリ起動時に実行される。
    @Override public void run(String... args) throws Exception {
        jdbc.execute("create table task (id serial, name varchar(255))");
        jdbc.update("insert into task (name) values (?)", "First Task.");
    }
}

package com.istz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DokkanbattleApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DokkanbattleApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbc;

    // アプリ起動時に実行される。
    @Override
    public void run(String... args) throws Exception {
        // ゲームマスタ
        jdbc.execute("create table GAME_MASTER ("
        		+ "   game_cd     varchar(3)    "
        		+ ",  game_name   varchar(255)  "
        		+ ",  image_name  varchar(255)  "
        		+ ")");
        jdbc.update("insert into GAME_MASTER (game_cd, game_name, image_name) values (?, ?, ?)", "MAG", "マギレコ", "img/01_magiReco.jpg");
        jdbc.update("insert into GAME_MASTER (game_cd, game_name, image_name) values (?, ?, ?)", "GRB", "グラブル", "img/02_gruble.jpg");
        // レアリティマスタ
        jdbc.execute("create table RARITY_MASTER (  "
        		+ "   game_cd          varchar(3)   "
        		+ ",  rarity_seq_no    smallint     "
        		+ ",  rarity_name      varchar(255) "
        		+ ",  rate_appearance  tinyint      "
        		+ ")");
        // ガチャ種マスタ
        jdbc.execute("create table GACHA_KIND_MASTER ("
        		+ "   game_cd            varchar(3)   "
        		+ ",  gacha_kind_seq_no  smallint     "
        		+ ",  gacha_kind_name    varchar(255) "
        		+ ",  java_class_name    varchar(30)  "
        		+ ")");
        jdbc.update("insert into GACHA_KIND_MASTER (game_cd, gacha_kind_seq_no, gacha_kind_name, java_class_name) "
        		+   "values (?, ?, ?, ?)", "MAG", 1, "単発", "MAG01");
        jdbc.update("insert into GACHA_KIND_MASTER (game_cd, gacha_kind_seq_no, gacha_kind_name, java_class_name) "
        		+   "values (?, ?, ?, ?)", "MAG", 10, "十連", "MAG10");
        jdbc.update("insert into GACHA_KIND_MASTER (game_cd, gacha_kind_seq_no, gacha_kind_name, java_class_name) "
        		+   "values (?, ?, ?, ?)", "GRB", 1, "単発", "GRB01");
        jdbc.update("insert into GACHA_KIND_MASTER (game_cd, gacha_kind_seq_no, gacha_kind_name, java_class_name) "
        		+   "values (?, ?, ?, ?)", "GRB", 10, "十連", "GRB10");
        // ガチャデータマスタ
        jdbc.execute("create table GACHA_DATA_MASTER ("
        		+ "   gacha_data_id               varchar(6)   "
        		+ ",  gacha_data_name             varchar(5)   "
        		+ ",  game_cd                     varchar(3)   "
        		+ ",  gacha_kind_seq_no           smallint     "
        		+ ",  gacha_kind_name             varchar(255) "
        		+ ",  gacha_data_rate_appearance  tinyint      "
        		+ ")");

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DokkanbattleApplication.class);
    }
}

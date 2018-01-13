package com.istz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;

import com.istz.service.gachaKind.GachaKind;
import com.istz.type.InstanceBeans;
import com.istz.type.StaticBeans;

@SpringBootApplication
public class DokkanbattleApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DokkanbattleApplication.class);

	public static List<StaticBeans.GachaClasses> gachaKindBeanList;

	public static void main(String[] args) {
		SpringApplication.run(DokkanbattleApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbc;

    // アプリ起動時に実行される。
    @Override
    public void run(String... args) throws Exception {
    	// テーブル作成
    	createTable();
    	// レコード作成
    	insertRecord();

    	gachaKindBeanList = new ArrayList<StaticBeans.GachaClasses>();

        try {
        	List<Map<String, Object>> ret = jdbc.queryForList(
        			  "select"
        			+ "  *"
        			+ "from"
        			+ "  GACHA_KIND_MASTER"
        			);
        	for (Map<String, Object> record : ret) {
        		StaticBeans parentBean = new StaticBeans();
        		String gameCd    = (String)record.get("game_cd");
        		Short  times       = (Short)record.get("gacha_kind_times");
        		String className = (String)record.get("java_class_name");
        		gachaKindBeanList.add(parentBean.new GachaClasses(gameCd, times, (GachaKind)Class.forName(className).newInstance()));
        	}
        } catch(ClassNotFoundException e) {
        	logger.error("ガチャ種マスタテーブルに設定しているクラス名が違う、またはクラスが実装されていない可能性があります");
        	throw e;
        }
    }

    public void createTable() {
        // ゲームマスタ
        jdbc.execute("create table GAME_MASTER ("
        		+ "     game_cd     varchar(3)    "
        		+ ",    game_name   varchar(255)  "
        		+ ",    image_name  varchar(255)  "
        		+ "   )");
        // レアリティマスタ
        jdbc.execute("create table RARITY_MASTER (  "
        		+ "     game_cd          varchar(3)   "
        		+ ",    rarity_seq_no    smallint     "
        		+ ",    rarity_name      varchar(255) "
        		+ ",    rate_appearance  tinyint      "
        		+ "   )");
        // ガチャ種マスタ
        jdbc.execute("create table GACHA_KIND_MASTER ("
        		+ "     game_cd            varchar(3)   "
        		+ ",    gacha_kind_times   smallint     "
        		+ ",    gacha_kind_name    varchar(255) "
        		+ ",    java_class_name    varchar(255)  "
        		+ "   )");
        // ガチャデータマスタ
        jdbc.execute("create table GACHA_DATA_MASTER ("
        		+ "     gacha_data_id               varchar(6)   "
        		+ ",    gacha_data_name             varchar(5)   "
        		+ ",    game_cd                     varchar(3)   "
        		+ ",    gacha_kind_times            smallint     "
        		+ ",    gacha_kind_name             varchar(255) "
        		+ ",    gacha_data_rate_appearance  tinyint      "
        		+ "   )");
    }
    public void insertRecord() {
    	InstanceBeans beans = new InstanceBeans();
    	List<InstanceBeans.GameMaster> gameMasterList = new ArrayList<>();
    	gameMasterList.add(beans.new GameMaster("MAG", "マギレコ"));
    	gameMasterList.add(beans.new GameMaster("GRB", "グラブル"));

    	gameMasterList.forEach((i) -> {
    		jdbc.update("insert into GAME_MASTER (game_cd, game_name,image_name) values (?,?,?) "
    				,                             i.getGameCd(), i.getGameName(), null);
    	});

//    	Map<String, String> rarityMaster = new HashMap<String, String>();



//    	Map<String, int> gachaKindMaster = new HashMap<String, String>();
//    	gameMaster.forEach((cd, name) -> {
//    		jdbc.update("insert into GACHA_KIND_MASTER (game_cd, gacha_kind_times, gacha_kind_name, java_class_name) values (?,?,?,?)",
//    				                                         cd, );
//    	});

    	List<InstanceBeans.GachaKindMaster> gachaKindMasterList = new ArrayList<>();
    	gachaKindMasterList.add(beans.new GachaKindMaster("MAG", (short) 1, "単発", "com.istz.service.gachaKind.magiReco.MAG01"));
    	gachaKindMasterList.add(beans.new GachaKindMaster("MAG", (short)10, "10連", "com.istz.service.gachaKind.magiReco.MAG10"));
    	gachaKindMasterList.add(beans.new GachaKindMaster("GRB", (short) 1, "単発", "com.istz.service.gachaKind.grablu.GRB01"));
    	gachaKindMasterList.add(beans.new GachaKindMaster("GRB", (short)10, "10連", "com.istz.service.gachaKind.grablu.GRB10"));
    	gachaKindMasterList.forEach((i) -> {
    		jdbc.update("insert into GACHA_KIND_MASTER (game_cd,gacha_kind_times,gacha_kind_name,java_class_name) values (?,?,?,?) "
    				,                                   i.getGameCd(), i.getGachaKindTimes(), i.getGachaKindName(), i.getJavaClassName());
    	});
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DokkanbattleApplication.class);
    }
}

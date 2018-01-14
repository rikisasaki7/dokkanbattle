package com.istz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;

import com.istz.entities.GachaDataMaster;
import com.istz.service.gachaKind.GachaKind;
import com.istz.type.StaticBeans;

@SpringBootApplication
public class DokkanbattleApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DokkanbattleApplication.class);

	public static List<StaticBeans.GachaClasses> gachaKindBeanList;

	public static Map<String, Map<Short, Integer>> rarityMasterMap;

	public static Map<String, List<GachaDataMaster>> gachaDataMasterMap;

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
    	// ガチャとクラスのマッピング
    	mappingGachaAndJavaClass();
    	// レアリティマスタを取得　※DBアクセスを最小限にするために起動時にメモリに格納
    	getRarityMaster();
    	// ガチャデータマスターを取得　※DBアクセスを最小限にするために起動時にメモリに格納
    	getGachaDataMasterMap();

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
        		+ "     gacha_data_id               varchar(10)   "
        		+ ",    gacha_data_name             varchar(255)   "
        		+ ",    game_cd                     varchar(3)   "
        		+ ",    rarity_seq_no               smallint     "
        		+ ",    gacha_data_rate_appearance  tinyint      "
        		+ "   )");
    }
    public void insertRecord() {
    	// ゲームマスタ
    	String insertSqlStrGameMaster =
    			"insert into GAME_MASTER (game_cd, game_name,image_name) values (?,?,?) ";
    	jdbc.update(insertSqlStrGameMaster, "MAG", "マギレコ", "img/01_magiReco.jpg");
    	jdbc.update(insertSqlStrGameMaster, "GRB", "グラブル", "img/02_gruble.jpg");

    	// レアリティマスタ
    	String insertSqlStrRarityMaster =
    			"insert into RARITY_MASTER (game_cd, rarity_seq_no, rarity_name, rate_appearance) values (?, ?, ?, ?)";
    	jdbc.update(insertSqlStrRarityMaster, "MAG",  1, "★２メモリア", 54);
    	jdbc.update(insertSqlStrRarityMaster, "MAG",  2, "★２魔法少女", 24);
    	jdbc.update(insertSqlStrRarityMaster, "MAG",  3, "★３メモリア", 13);
    	jdbc.update(insertSqlStrRarityMaster, "MAG",  4, "★３魔法少女",  4);
    	jdbc.update(insertSqlStrRarityMaster, "MAG",  5, "★４メモリア",  4);
    	jdbc.update(insertSqlStrRarityMaster, "MAG",  6, "★４魔法少女",  1);
    	jdbc.update(insertSqlStrRarityMaster, "GRB",  1, "R",   67);
    	jdbc.update(insertSqlStrRarityMaster, "GRB",  2, "SR",  30);
    	jdbc.update(insertSqlStrRarityMaster, "GRB",  3, "SSR",  3);

    	// ガチャ種マスタ
    	String insertSqlStrGachaKindMaster =
    			"insert into GACHA_KIND_MASTER (game_cd, gacha_kind_times, gacha_kind_name, java_class_name) values (?, ?, ?, ?)";
    	jdbc.update(insertSqlStrGachaKindMaster, "MAG",  1, "単発", "com.istz.service.gachaKind.magiReco.MAG01");
    	jdbc.update(insertSqlStrGachaKindMaster, "MAG", 10, "10連", "com.istz.service.gachaKind.magiReco.MAG10");
    	jdbc.update(insertSqlStrGachaKindMaster, "GRB",  1, "単発", "com.istz.service.gachaKind.grablu.GRB01");
    	jdbc.update(insertSqlStrGachaKindMaster, "GRB", 10, "10連", "com.istz.service.gachaKind.grablu.GRB10");

    	// ガチャデータマスタ
    	String insertSqlStrGachaDataMaster =
    			"insert into GACHA_DATA_MASTER (gacha_data_id, gacha_data_name, game_cd, rarity_seq_no, gacha_data_rate_appearance) values (?, ?, ?, ?, ?)";
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0101",  "★２メモリア_揺れるまどかの心",     "MAG", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0102",  "★２メモリア_近くにいるのはだれ？", "MAG", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0103",  "★２メモリア_人間が一番怖い",       "MAG", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0104",  "★２メモリア_メガネとは？",         "MAG", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0105",  "★２メモリア_味覚の正体とは？",     "MAG", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0201",  "★２魔法少女_つるの",     "MAG", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0202",  "★２魔法少女_やちよ",     "MAG", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0203",  "★２魔法少女_久野ちゃん", "MAG", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0204",  "★２魔法少女_鈴木絵里",   "MAG", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0205",  "★２魔法少女_リエリー",   "MAG", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0301",  "★３メモリア_氷は冷たい",       "MAG", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0302",  "★３メモリア_ジョブズ？",       "MAG", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0303",  "★３メモリア_苦いお寿司",       "MAG", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0304",  "★３メモリア_左利き用ハサミ",   "MAG", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0305",  "★３メモリア_とある女性の内臓", "MAG", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0401",  "★３魔法少女_オリコ",   "MAG", 4, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0402",  "★３魔法少女_まさら",   "MAG", 4, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0403",  "★３魔法少女_かえで",   "MAG", 4, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0404",  "★３魔法少女_セットン", "MAG", 4, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0405",  "★３魔法少女_赤い人",   "MAG", 4, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0501",  "★４メモリア_無味無臭",          "MAG", 5, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0502",  "★４メモリア_サイフの中身は0円", "MAG", 5, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0503",  "★４メモリア_下顎へのアッパー",  "MAG", 5, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0504",  "★４メモリア_水曜どうでしょう",  "MAG", 5, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0505",  "★４メモリア_青汁",              "MAG", 5, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0601",  "★４魔法少女_杏子",    "MAG", 6, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0602",  "★４魔法少女_まどか",  "MAG", 6, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0603",  "★４魔法少女_ほむら",  "MAG", 6, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0604",  "★４魔法少女_さやか",  "MAG", 6, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "MAG0605",  "★４魔法少女_マミさん","MAG", 6, 20);

    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0101",  "R_バロワ",  "GRB", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0102",  "R_カルバ",  "GRB", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0103",  "R_クムエ",  "GRB", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0104",  "R_アンナ",  "GRB", 1, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0105",  "R_マリー",  "GRB", 1, 10);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0106",  "R_筋肉先生","GRB", 1, 5);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0107",  "R_メリット","GRB", 1, 5);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0201",  "SR_ダーント","GRB", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0202",  "SR_スーテラ","GRB", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0203",  "SR_ライアン","GRB", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0204",  "SR_アリーザ","GRB", 2, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0205",  "SR_ジェシカ","GRB", 2, 10);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0206",  "SR_髭長おじさん","GRB", 2, 5);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0207",  "SR_クジンシー"  ,"GRB", 2, 5);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0301",  "SSR_エッセル","GRB", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0302",  "SSR_ドランク","GRB", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0303",  "SSR_サラーサ","GRB", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0304",  "SSR_アンチラ","GRB", 3, 20);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0305",  "SSR_ジェシカ","GRB", 3, 10);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0306",  "SSR_東山奈央","GRB", 3,  2);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0307",  "SSR_早見沙織","GRB", 3,  2);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0308",  "SSR_加隈亜衣","GRB", 3,  2);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0309",  "SSR_中田譲二","GRB", 3,  2);
    	jdbc.update(insertSqlStrGachaDataMaster, "GRB0309",  "SSR_藤原啓治","GRB", 3,  2);


    }
    private void getRarityMaster() throws Exception {
		rarityMasterMap = new HashMap<String, Map<Short,Integer>>();
    	getEachRarity("MAG");
    	getEachRarity("GRB");
	}

	private void getEachRarity(String gameCd) throws Exception {
		List<Map<String, Object>> ret = jdbc.queryForList(
  			  "select"
  			+ "  *"
  			+ "from "
  			+ "  RARITY_MASTER "
  			+ "where"
  			+ "  game_cd = ? "
  			+ "order by "
  			+ "  rarity_seq_no"
  			, gameCd
  			);

		int rateSum = 0;
		Map <Short, Integer> rarity = new TreeMap<Short, Integer>();
    	for (Map<String, Object> record : ret) {
    		Short raritySeqNo    = (Short)record.get("rarity_seq_no");
    		Byte  rateAppearance = (Byte)record.get("rate_appearance");
    		rateSum = rateSum + rateAppearance;
    		rarity.put(raritySeqNo, rateSum - 1);
    	}
    	rarityMasterMap.put(gameCd, rarity);
    	if (rateSum != 100) {
    		throw new Exception(gameCd + "のレアリティが不正です。rate_appearanceの合計が100かどうか確かめて下さい。[RARITY_MASTER]");
    	}
	}

	private void mappingGachaAndJavaClass()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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

	private void getGachaDataMasterMap() throws Exception {
		gachaDataMasterMap = new HashMap<String, List<GachaDataMaster>>();
    	getEachGachaDataMasterMap("MAG", 1);
    	getEachGachaDataMasterMap("MAG", 2);
    	getEachGachaDataMasterMap("MAG", 3);
    	getEachGachaDataMasterMap("MAG", 4);
    	getEachGachaDataMasterMap("MAG", 5);
    	getEachGachaDataMasterMap("MAG", 6);

    	getEachGachaDataMasterMap("GRB", 1);
    	getEachGachaDataMasterMap("GRB", 2);
    	getEachGachaDataMasterMap("GRB", 3);
	}

	private void getEachGachaDataMasterMap(String gameCd, int gachaKindSeqNo) throws Exception {
		List<Map<String, Object>> ret = jdbc.queryForList(
    			  "select"
    			+ "  *"
    			+ "from "
    			+ "  GACHA_DATA_MASTER "
    			+ "where"
    			+ "  game_cd = ? and"
    			+ "  rarity_seq_no = ?"
    			+ "order by "
    			+ "  rarity_seq_no"
    			, gameCd, gachaKindSeqNo
    			);
    	List<GachaDataMaster> gachaDataMasterList = new ArrayList<GachaDataMaster>();
    	int rateSum = 0;
    	for (Map<String, Object> record : ret) {
    		String gachaDataId               = (String)record.get("gacha_data_id");
    		String gachaDataName             = (String)record.get("gacha_data_name");
    		Byte gachaDataRateAppearance  = (Byte)record.get("gacha_data_rate_appearance");
    		rateSum = rateSum + gachaDataRateAppearance;
    		GachaDataMaster gachaDataMaster = new GachaDataMaster(gachaDataId, gachaDataName, gameCd,
    				gachaKindSeqNo, null, rateSum - 1);
    		gachaDataMasterList.add(gachaDataMaster);
    	}
    	gachaDataMasterMap.put(gameCd + gachaKindSeqNo, gachaDataMasterList);
    	if (rateSum != 100) {
    		throw new Exception(gameCd + gachaKindSeqNo + "のレアリティが不正です。rate_appearanceの合計が100かどうか確かめて下さい。[GACHA_DATA_MASTER]");
    	}
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DokkanbattleApplication.class);
    }
}

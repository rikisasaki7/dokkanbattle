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

	public static Map<String, Map<Integer, Integer>> rarityMasterMap;

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
//    	createTable();
    	// レコード作成
//    	insertRecord();
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

	private void mappingGachaAndJavaClass()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		gachaKindBeanList = new ArrayList<StaticBeans.GachaClasses>();

        try {
//        	List<Map<String, Object>> ret = jdbc.queryForList(
//        			  "select"
//        			+ "  *"
//        			+ "from"
//        			+ "  GACHA_KIND_MASTER"
//        			);
        	List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
        	createGachaKindMaster(ret, "MAG",  1, "com.istz.service.gachaKind.magiReco.MAG01");
        	createGachaKindMaster(ret, "MAG", 10, "com.istz.service.gachaKind.magiReco.MAG10");
        	createGachaKindMaster(ret, "GRB",  1, "com.istz.service.gachaKind.grablu.GRB01"  );
        	createGachaKindMaster(ret, "GRB", 10, "com.istz.service.gachaKind.grablu.GRB10"  );
        	for (Map<String, Object> record : ret) {
        		StaticBeans parentBean = new StaticBeans();
        		String gameCd        = (String)record.get("game_cd");
        		Integer times       = (Integer)record.get("gacha_kind_times");
        		String className    = (String)record.get("java_class_name");
        		gachaKindBeanList.add(parentBean.new GachaClasses(gameCd, times, (GachaKind)Class.forName(className).newInstance()));
        	}
        } catch(ClassNotFoundException e) {
        	logger.error("ガチャ種マスタテーブルに設定しているクラス名が違う、またはクラスが実装されていない可能性があります");
        	throw e;
        }
	}

	private void createGachaKindMaster(List<Map<String, Object>> ret, String gameCd, int gachaKindTimes, String javaClassName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("game_cd", gameCd);
		map.put("gacha_kind_times", gachaKindTimes);
		map.put("java_class_name", javaClassName);
		ret.add(map);
	}

    private void getRarityMaster() throws Exception {
		rarityMasterMap = new HashMap<String, Map<Integer,Integer>>();
		// TODO DBの代わり
		List<Map<String, Object>> retMAG = new ArrayList<Map<String, Object>>();
		createRarityMaster(retMAG, 1, 54);
		createRarityMaster(retMAG, 2, 24);
		createRarityMaster(retMAG, 3, 13);
		createRarityMaster(retMAG, 4,  4);
		createRarityMaster(retMAG, 5,  4);
		createRarityMaster(retMAG, 6,  1);
    	getEachRarity("MAG" ,retMAG);
    	// TODO DBの代わり
    	List<Map<String, Object>> retGRB = new ArrayList<Map<String, Object>>();
		createRarityMaster(retGRB, 1, 67);
		createRarityMaster(retGRB, 2, 30);
		createRarityMaster(retGRB, 3,  3);
    	getEachRarity("GRB" ,retGRB);
	}

	private void getEachRarity(String gameCd ,List<Map<String, Object>> ret) throws Exception {
		// TODO DBは後回し
//		List<Map<String, Object>> ret = jdbc.queryForList(
//  			  "select"
//  			+ "  *"
//  			+ "from "
//  			+ "  RARITY_MASTER "
//  			+ "where"
//  			+ "  game_cd = ? "
//  			+ "order by "
//  			+ "  rarity_seq_no"
//  			, gameCd
//  			);

		int rateSum = 0;
		Map <Integer, Integer> rarity = new TreeMap<Integer, Integer>();

		for (Map<String, Object> record : ret) {
//			Short raritySeqNo    = record.get("rarity_seq_no");
			Integer raritySeqNo    = (Integer)record.get("rarity_seq_no");
			Integer  rateAppearance = (Integer)record.get("rate_appearance");
			rateSum = rateSum + rateAppearance;
			rarity.put(raritySeqNo, rateSum - 1);
		}
    	rarityMasterMap.put(gameCd, rarity);
    	if (rateSum != 100) {
    		throw new Exception(gameCd + "のレアリティが不正です。rate_appearanceの合計が100かどうか確かめて下さい。[RARITY_MASTER]");
    	}
	}


	private void createRarityMaster(List<Map<String, Object>> ret, int raritySeqNo, int rateAppearance) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rarity_seq_no", raritySeqNo);
		map.put("rate_appearance", rateAppearance);
		ret.add(map);
	}

	private void getGachaDataMasterMap() throws Exception {
		gachaDataMasterMap = new HashMap<String, List<GachaDataMaster>>();

		List<Map<String, Object>> retMAG01 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retMAG01, "MAG0101", "２メモリア_揺れるまどかの心 ",    20);
		createGachaDataMaster(retMAG01, "MAG0102", "２メモリア_近くにいるのはだれ？", 20);
		createGachaDataMaster(retMAG01, "MAG0103", "２メモリア_人間が一番怖い",       20);
		createGachaDataMaster(retMAG01, "MAG0104", "２メモリア_メガネとは？",         20);
		createGachaDataMaster(retMAG01, "MAG0105", "２メモリア_味覚の正体とは？",     20);
		List<Map<String, Object>> retMAG02 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retMAG02, "MAG0201", "２魔法少女_つるの",     20);
		createGachaDataMaster(retMAG02, "MAG0202", "２魔法少女_やちよ",     20);
		createGachaDataMaster(retMAG02, "MAG0203", "２魔法少女_久野ちゃん", 20);
		createGachaDataMaster(retMAG02, "MAG0204", "２魔法少女_鈴木絵里",   20);
		createGachaDataMaster(retMAG02, "MAG0205", "２魔法少女_リエリー",   20);
		List<Map<String, Object>> retMAG03 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retMAG03, "MAG0301", "３メモリア_氷は冷たい",       20);
		createGachaDataMaster(retMAG03, "MAG0302", "３メモリア_ジョブズ？",       20);
		createGachaDataMaster(retMAG03, "MAG0303", "３メモリア_苦いお寿司",       20);
		createGachaDataMaster(retMAG03, "MAG0304", "３メモリア_左利き用ハサミ",   20);
		createGachaDataMaster(retMAG03, "MAG0305", "３メモリア_とある女性の内臓", 20);
		List<Map<String, Object>> retMAG04 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retMAG04, "MAG0401","３魔法少女_オリコ",   20);
		createGachaDataMaster(retMAG04, "MAG0402","３魔法少女_まさら",   20);
		createGachaDataMaster(retMAG04, "MAG0403","３魔法少女_かえで",   20);
		createGachaDataMaster(retMAG04, "MAG0404","３魔法少女_セットン", 20);
		createGachaDataMaster(retMAG04, "MAG0405","３魔法少女_赤い人",   20);
		List<Map<String, Object>> retMAG05 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retMAG05, "MAG0501","４メモリア_無味無臭",          20);
		createGachaDataMaster(retMAG05, "MAG0502","４メモリア_サイフの中身は0円", 20);
		createGachaDataMaster(retMAG05, "MAG0503","４メモリア_下顎へのアッパー",  20);
		createGachaDataMaster(retMAG05, "MAG0504","４メモリア_水曜どうでしょう",  20);
		createGachaDataMaster(retMAG05, "MAG0505","４メモリア_青汁",              20);
		List<Map<String, Object>> retMAG06 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retMAG06, "MAG0601", "★★４魔法少女_杏子",     10);
		createGachaDataMaster(retMAG06, "MAG0602", "★★４魔法少女_まどか",   10);
		createGachaDataMaster(retMAG06, "MAG0603", "★★４魔法少女_ほむら",   10);
		createGachaDataMaster(retMAG06, "MAG0604", "★★４魔法少女_さやか",   10);
		createGachaDataMaster(retMAG06, "MAG0605", "★★４魔法少女_マミさん", 10);
		createGachaDataMaster(retMAG06, "MAG0605", "★４魔法少女_レンちゃん", 16);
		createGachaDataMaster(retMAG06, "MAG0605", "★４魔法少女_ももこ",     16);
		createGachaDataMaster(retMAG06, "MAG0605", "★４魔法少女_つくよ",     18);

    	getEachGachaDataMasterMap("MAG", 1, retMAG01);
    	getEachGachaDataMasterMap("MAG", 2, retMAG02);
    	getEachGachaDataMasterMap("MAG", 3, retMAG03);
    	getEachGachaDataMasterMap("MAG", 4, retMAG04);
    	getEachGachaDataMasterMap("MAG", 5, retMAG05);
    	getEachGachaDataMasterMap("MAG", 6, retMAG06);

    	List<Map<String, Object>> retGRB01 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retGRB01, "GRB0101", "R_バロワ",                  20);
		createGachaDataMaster(retGRB01, "GRB0102", "R_カルバ",                  20);
		createGachaDataMaster(retGRB01, "GRB0103", "R_クムエ",                  20);
		createGachaDataMaster(retGRB01, "GRB0104", "R_アンナ",                  20);
		createGachaDataMaster(retGRB01, "GRB0105", "R_マリー",                  10);
		createGachaDataMaster(retGRB01, "GRB0106", "R_筋肉先生",                5);
		createGachaDataMaster(retGRB01, "GRB0107", "R_ラーメン大好き小泉さん",  5);

		List<Map<String, Object>> retGRB02 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retGRB02, "GRB0201", "SR_ダーント",      20);
		createGachaDataMaster(retGRB02, "GRB0202", "SR_スーテラ",      20);
		createGachaDataMaster(retGRB02, "GRB0203", "SR_ライアン",      20);
		createGachaDataMaster(retGRB02, "GRB0204", "SR_アリーザ",      20);
		createGachaDataMaster(retGRB02, "GRB0205", "SR_ジェシカ",      10);
		createGachaDataMaster(retGRB02, "GRB0206", "SR_髭長おじさん",  5);
		createGachaDataMaster(retGRB02, "GRB0207", "SR_クジンシー"  ,  5);

		List<Map<String, Object>> retGRB03 = new ArrayList<Map<String, Object>>();
		createGachaDataMaster(retGRB03, "GRB0301",  "★SSR_エッセル",  20);
		createGachaDataMaster(retGRB03, "GRB0302",  "★SSR_ドランク",  20);
		createGachaDataMaster(retGRB03, "GRB0303",  "★SSR_サラーサ",  20);
		createGachaDataMaster(retGRB03, "GRB0304",  "★SSR_アンチラ",  20);
		createGachaDataMaster(retGRB03, "GRB0305",  "★SSR_ジェシカ",  10);
		createGachaDataMaster(retGRB03, "GRB0306",  "★★SSR_東山奈央",   2);
		createGachaDataMaster(retGRB03, "GRB0307",  "★★SSR_早見沙織",   2);
		createGachaDataMaster(retGRB03, "GRB0307",  "★★SSR_加隈亜衣",   2);
		createGachaDataMaster(retGRB03, "GRB0307",  "★★SSR_中田譲二",   2);
		createGachaDataMaster(retGRB03, "GRB0307",  "★★SSR_藤原啓治",   2);

    	getEachGachaDataMasterMap("GRB", 1, retGRB01);
    	getEachGachaDataMasterMap("GRB", 2, retGRB02);
    	getEachGachaDataMasterMap("GRB", 3, retGRB03);
	}

	private void getEachGachaDataMasterMap(String gameCd, int gachaKindSeqNo, List<Map<String, Object>> ret) throws Exception {
//		List<Map<String, Object>> ret = jdbc.queryForList(
//    			  "select"
//    			+ "  *"
//    			+ "from "
//    			+ "  GACHA_DATA_MASTER "
//    			+ "where"
//    			+ "  game_cd = ? and"
//    			+ "  rarity_seq_no = ?"
//    			+ "order by "
//    			+ "  rarity_seq_no"
//    			, gameCd, gachaKindSeqNo
//    			);
    	List<GachaDataMaster> gachaDataMasterList = new ArrayList<GachaDataMaster>();
    	int rateSum = 0;
    	for (Map<String, Object> record : ret) {
    		String gachaDataId               = (String)record.get("gacha_data_id");
    		String gachaDataName             = (String)record.get("gacha_data_name");
    		Integer gachaDataRateAppearance  = (Integer)record.get("gacha_data_rate_appearance");
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

	private void createGachaDataMaster(List<Map<String, Object>> ret, String gachaDataId, String gacha_data_name, int gachaDataRateAppearance) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gacha_data_id", gachaDataId);
		map.put("gacha_data_name", gacha_data_name);
		map.put("gacha_data_rate_appearance", gachaDataRateAppearance);
		ret.add(map);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DokkanbattleApplication.class);
    }
}

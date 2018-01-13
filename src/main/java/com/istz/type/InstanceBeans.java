package com.istz.type;

public class InstanceBeans {
	// ゲームマスタ
	public class GameMaster{
		private String gameCd;
		private String gameName;

		public GameMaster(String gameCd, String gameName) {
			this.gameCd   = gameCd;
			this.gameName = gameName;
		}

		public String getGameCd   (){return this.gameCd;}
		public String getGameName (){return this.gameName;}

		public void setGameCd   (String gameCd)  {	this.gameCd   = gameCd;}
		public void setGameName (String gameName){	this.gameName = gameName;}
	}
	// レアリティマスタ
	public class RarityMaster{
		private String  gameCd;
		private Short   raritySeqNo;
		private String  rarityName;
		private Byte    rateAppearance;

		public RarityMaster(String gameCd, Short raritySeqNo, String rarityName, Byte rateAppearance) {
			this.gameCd         = gameCd;
			this.raritySeqNo    = raritySeqNo;
			this.rarityName     = rarityName;
			this.rateAppearance = rateAppearance;
		}
		public String  getGameCd         (){return this.gameCd;}
		public Short   getRaritySeqNo    (){return this.raritySeqNo;}
		public String  getRarityName     (){return this.rarityName;}
		public Byte    getRateAppearance (){return this.rateAppearance;}

		public void setGameCd         (String  gameCd        ) {this.gameCd         = gameCd;}
		public void setRaritySeqNo    (Short   raritySeqNo   ) {this.raritySeqNo    = raritySeqNo;}
		public void setRarityName     (String  rarityName    ) {this.rarityName     = rarityName;}
		public void setRateAppearance (Byte    rateAppearance) {this.rateAppearance = rateAppearance;}
	}

	// ガチャ種マスタ
	public class GachaKindMaster{
		private String  gameCd;
		private Short gachaKindTimes;
		private String  gachaKindName;
		private String  javaClassName;

		public GachaKindMaster(String gameCd, Short gachaKindTimes, String gachaKindName, String javaClassName) {
			this.gameCd         = gameCd;
			this.gachaKindTimes = gachaKindTimes;
			this.gachaKindName  = gachaKindName;
			this.javaClassName  = javaClassName;
		}
		public String  getGameCd         (){return this.gameCd;}
		public Short getGachaKindTimes   (){return this.gachaKindTimes;}
		public String  getGachaKindName  (){return this.gachaKindName;}
		public String  getJavaClassName  (){return this.javaClassName;}

		public void setGameCd        (String  gameCd        ) {this.gameCd         = gameCd;}
		public void setGachaKindTimes(Short   gachaKindTimes) {this.gachaKindTimes = gachaKindTimes;}
		public void setGachaKindName (String  gachaKindName ) {this.gachaKindName  = gachaKindName;}
		public void setJavaClassName (String  javaClassName ) {this.javaClassName  = javaClassName;}
	}

	// ガチャデータマスタ
	public class GachaDataMaster{
		private String  gachaDataId;
		private String  gachaDataName;
		private String  gameCd;
		private Short gachaKindTimes;
		private String  gachaKindName;
		private Byte    gachaDataRateAppearance;

		public GachaDataMaster(String gachaDataId, String gachaDataName, String gameCd,
				Short gachaKindTimes, String  gachaKindName, Byte gachaDataRateAppearance) {
			this.gachaDataId             = gachaDataId;
			this.gachaDataName           = gachaDataName;
			this.gameCd                  = gameCd;
			this.gachaKindTimes          = gachaKindTimes;
			this.gachaKindName           = gachaKindName;
			this.gachaDataRateAppearance = gachaDataRateAppearance;
		}
		public String  getGachaDataId            (){return this.gachaDataId;}
		public String  getGachaDataName          (){return this.gachaDataName;}
		public String  getGameCd                 (){return this.gameCd;}
		public Short   getGachaKindTimes         (){return this.gachaKindTimes;}
		public String  getGachaKindName          (){return this.gachaKindName;}
		public Byte    getGchaDataRateAppearance (){return this.gachaDataRateAppearance;}

		public void setGachaDataId            (String  gachaDataId            ) {this.gachaDataId               = gachaDataId;}
		public void setGachaDataName          (String  gachaDataName          ) {this.gachaDataName             = gachaDataName;}
		public void setGameCd                 (String  gameCd                 ) {this.gameCd                    = gameCd;}
		public void setGachaKindTimes         (Short   gachaKindTimes         ) {this.gachaKindTimes            = gachaKindTimes;}
		public void setGachaKindName          (String  gachaKindName          ) {this.gachaKindName             = gachaKindName;}
		public void setGchaDataRateAppearance (Byte    gachaDataRateAppearance) {this.gachaDataRateAppearance   = gachaDataRateAppearance;}
	}
}

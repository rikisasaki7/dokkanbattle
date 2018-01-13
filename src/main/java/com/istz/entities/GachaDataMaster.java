package com.istz.entities;

/**
 * @author Riki
 * ガチャの結果を表します。
 */
public class GachaDataMaster {

	/**
	 * デフォルトコンストラクタ
	 */
	private String gachaDataId;
	private String gachaDataName;
	private String gameCd;
	private int gachaKindSeqNo;
	private String gachaKindName;
	private int gachaDataRateAppearance;
	public GachaDataMaster(
			String gachaDataId,
			String gachaDataName,
			String gameCd,
			int gachaKindSeqNo,
			String gachaKindName,
			int gachaDataRateAppearance){
		this.gachaDataId = gachaDataId;
		this.gachaDataName = gachaDataName;
		this.gameCd = gameCd;
		this.gachaKindSeqNo = gachaKindSeqNo;
		this.gachaKindName = gachaKindName;
		this.gachaDataRateAppearance = gachaDataRateAppearance;
	}
	/**
	 * @return gachaDataId
	 */
	public String getGachaDataId() {
		return gachaDataId;
	}
	/**
	 * @param gachaDataId セットする gachaDataId
	 */
	public void setGachaDataId(String gachaDataId) {
		this.gachaDataId = gachaDataId;
	}
	/**
	 * @return gachaDataName
	 */
	public String getGachaDataName() {
		return gachaDataName;
	}
	/**
	 * @param gachaDataName セットする gachaDataName
	 */
	public void setGachaDataName(String gachaDataName) {
		this.gachaDataName = gachaDataName;
	}
	/**
	 * @return gameCd
	 */
	public String getGameCd() {
		return gameCd;
	}
	/**
	 * @param gameCd セットする gameCd
	 */
	public void setGameCd(String gameCd) {
		this.gameCd = gameCd;
	}
	/**
	 * @return gachaKindSeqNo
	 */
	public int getGachaKindSeqNo() {
		return gachaKindSeqNo;
	}
	/**
	 * @param gachaKindSeqNo セットする gachaKindSeqNo
	 */
	public void setGachaKindSeqNo(int gachaKindSeqNo) {
		this.gachaKindSeqNo = gachaKindSeqNo;
	}
	/**
	 * @return gachaKindName
	 */
	public String getGachaKindName() {
		return gachaKindName;
	}
	/**
	 * @param gachaKindName セットする gachaKindName
	 */
	public void setGachaKindName(String gachaKindName) {
		this.gachaKindName = gachaKindName;
	}
	/**
	 * @return gachaDataRateAppearance
	 */
	public int getGachaDataRateAppearance() {
		return gachaDataRateAppearance;
	}
	/**
	 * @param gachaDataRateAppearance セットする gachaDataRateAppearance
	 */
	public void setGachaDataRateAppearance(int gachaDataRateAppearance) {
		this.gachaDataRateAppearance = gachaDataRateAppearance;
	}
}

/**
 * 
 */
package com.istz.entities;


/**
 * @author Riki
 * ゲームマスターマスタを表すエンティティです。
 */
public class GameMaster {
	private String gameCd;
	private String gameName;
	private String imageName;
	/**
	 * デフォルトコンストラクタ
	 */
	public GameMaster(String gameCd, String gameName, String imageName){
		this.gameCd = gameCd;
		this.gameName = gameName;
		this.imageName = imageName;
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
	 * @return gameName
	 */
	public String getGameName() {
		return gameName;
	}
	/**
	 * @param gameName セットする gameName
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	/**
	 * @return imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName セットする imageName
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}

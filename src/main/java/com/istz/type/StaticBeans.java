package com.istz.type;

import com.istz.service.gachaKind.GachaKind;

public class StaticBeans {
	// ゲームマスタ
	public class GachaClasses{
		private String    gameCd;
		private Integer     gachaKindTimes;
		private GachaKind gachaClass;

		public GachaClasses(String gameCd, Integer gachaKindTimes, GachaKind gachaClass) {
			this.gameCd         = gameCd;
			this.gachaKindTimes = gachaKindTimes;
			this.gachaClass     = gachaClass;
		}

		public String     getGameCd         (){return this.gameCd;}
		public Integer    getGachaKindTimes (){return this.gachaKindTimes;}
		public GachaKind  getGachaClass (){return this.gachaClass;}

		public void setGameCd         (String gameCd)         {this.gameCd   = gameCd;}
		public void setGachaKindTimes (Integer gachaKindTimes){this.gachaKindTimes = gachaKindTimes;}
		public void setGachaClass     (GachaKind gachaClass)  {this.gachaClass = gachaClass;}

	}
}

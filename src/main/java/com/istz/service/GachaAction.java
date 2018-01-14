package com.istz.service;

import java.util.List;

import com.istz.DokkanbattleApplication;
import com.istz.entities.GachaDataMaster;
import com.istz.service.gachaKind.GachaKind;
import com.istz.type.StaticBeans;

public class GachaAction {

	public List<GachaDataMaster> simulate (String gameCd, String gachaKind) throws Exception {
		// 回す対象のガチャを取得
		GachaKind gachaKinClass = null;
		for (StaticBeans.GachaClasses gachaKindBean: DokkanbattleApplication.gachaKindBeanList){
			if (gachaKindBean.getGameCd().equals(gameCd)
					&& gachaKindBean.getGachaKindTimes().equals(Integer.parseInt(gachaKind))){
				gachaKinClass = gachaKindBean.getGachaClass();
			}
		}
		if (gachaKinClass == null) {
			throw new Exception("マスタに存在しないゲームとガチャが組み合わされました。");
		}
		List<GachaDataMaster> gachaResult = gachaKinClass.doGacha(gameCd, gachaKind);
		return gachaResult;
	}
}

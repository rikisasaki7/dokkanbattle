package com.istz.service;

import java.util.List;

import com.istz.DokkanbattleApplication;
import com.istz.service.gachaKind.GachaKind;
import com.istz.type.StaticBeans;

public class GachaAction {

	public List<String> simulate (String gameCd, String gachaKind) {
		// 回す対象のガチャを取得
		System.out.println(gameCd + ":" + gachaKind);
		GachaKind gachaKinClass = null;
		for (StaticBeans.GachaClasses gachaKindBean: DokkanbattleApplication.gachaKindBeanList){
			if (gachaKindBean.getGameCd().equals(gameCd)
					&& gachaKindBean.getGachaKindTimes().equals(Short.parseShort(gachaKind))){
				gachaKinClass = gachaKindBean.getGachaClass();
			}
		}
		List<String> gachaResult = gachaKinClass.doGacha();




		// 使うガチャの種類を取得



		return gachaResult;

	}



}

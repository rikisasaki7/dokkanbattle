package com.istz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.istz.DokkanbattleApplication;
import com.istz.entities.GachaDataMaster;

public class GachaAction {

	public List<GachaDataMaster> simulate (String gameCd, String gachaKind) throws Exception {
		// 回す対象のガチャを取得
//		GachaKind gachaKinClass = null;
//		for (StaticBeans.GachaClasses gachaKindBean: DokkanbattleApplication.gachaKindBeanList){
//			if (gachaKindBean.getGameCd().equals(gameCd)
//					&& gachaKindBean.getGachaKindTimes().equals(Short.parseShort(gachaKind))){
//				gachaKinClass = gachaKindBean.getGachaClass();
//			}
//		}
//		if (gachaKinClass == null) {
//			throw new Exception("マスタに存在しないゲームとガチャが組み合わされました。");
//		}
//		List<GachaDataMaster> gachaResult = gachaKinClass.doGacha(gameCd);
		List<GachaDataMaster> gachaResult = new ArrayList<GachaDataMaster>();
		Random rnd = new Random();
		for (int i = 0; i < Integer.parseInt(gachaKind); i++) {

			int getRarity = 0;

			int preRateAppearanceForRarityMaster = 0;
			int randomNumForRarityMaster = rnd.nextInt(100);
			for (Map.Entry<Short, Integer> entry : DokkanbattleApplication.rarityMasterMap.get(gameCd).entrySet()) {
				int rateAppearance = entry.getValue();
				if (preRateAppearanceForRarityMaster <= randomNumForRarityMaster && randomNumForRarityMaster <= rateAppearance) {
					getRarity = entry.getKey().intValue();
				}
				preRateAppearanceForRarityMaster = rateAppearance;
			}

			int preRateAppearanceForGachaDataMaster = 0;
			int randomNumForGachaDataMaster = rnd.nextInt(100);

			for (GachaDataMaster gachaDataMaster : DokkanbattleApplication.gachaDataMasterMap.get(gameCd + getRarity)) {
				int rateAppearance = gachaDataMaster.getGachaDataRateAppearance();
				if (preRateAppearanceForGachaDataMaster <= randomNumForGachaDataMaster && randomNumForGachaDataMaster <= rateAppearance) {
					gachaResult.add(gachaDataMaster);
				}
				preRateAppearanceForGachaDataMaster = rateAppearance;
			}
		}
		return gachaResult;
	}
}

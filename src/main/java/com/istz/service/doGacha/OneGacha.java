package com.istz.service.doGacha;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.istz.DokkanbattleApplication;
import com.istz.entities.GachaDataMaster;

public class OneGacha {

	public static int decideRarity (String gameCd, String gachaKind, Random rnd) {
		return decideRarityFix(gameCd, gachaKind, 0, rnd.nextInt(100));
	}

	public static int decideRarityFix (String gameCd, String gachaKind, int rndNumFrom, int rndNumTo) {
		int getRarity = 0;

		int preRateAppearanceForRarityMaster = rndNumFrom;
		int randomNumForRarityMaster = rndNumTo;
		for (Map.Entry<Integer, Integer> entry : DokkanbattleApplication.rarityMasterMap.get(gameCd).entrySet()) {
			int rateAppearance = entry.getValue();
			if (preRateAppearanceForRarityMaster <= randomNumForRarityMaster && randomNumForRarityMaster <= rateAppearance) {
				getRarity = entry.getKey();
				break;
			}
			preRateAppearanceForRarityMaster = rateAppearance;
		}
		return getRarity;
	}

	public static void supriiiise (String gameCd, int getRarity, Random rnd, List<GachaDataMaster> gachaResult){
		int preRateAppearanceForGachaDataMaster = 0;
		int randomNumForGachaDataMaster = rnd.nextInt(100);

		for (GachaDataMaster gachaDataMaster : DokkanbattleApplication.gachaDataMasterMap.get(gameCd + getRarity)) {
			int rateAppearance = gachaDataMaster.getGachaDataRateAppearance();
			if (preRateAppearanceForGachaDataMaster <= randomNumForGachaDataMaster && randomNumForGachaDataMaster <= rateAppearance) {
				gachaResult.add(gachaDataMaster);
				break;
			}
			preRateAppearanceForGachaDataMaster = rateAppearance;
		}
	}


}

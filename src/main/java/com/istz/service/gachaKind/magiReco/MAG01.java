package com.istz.service.gachaKind.magiReco;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.istz.DokkanbattleApplication;
import com.istz.entities.GachaDataMaster;
import com.istz.service.gachaKind.GachaKind;

public class MAG01 extends GachaKind{

	@Override
	public void horeeMawase (List<GachaDataMaster> result, String gameCd) {
		Random rnd = new Random();
		int getRarity = 0;

		//
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
				result.add(gachaDataMaster);
			}
			preRateAppearanceForGachaDataMaster = rateAppearance;
		}
		System.out.println("GachaKind.MAG01");
	}

}

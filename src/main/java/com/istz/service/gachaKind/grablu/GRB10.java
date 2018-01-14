package com.istz.service.gachaKind.grablu;

import java.util.List;
import java.util.Random;

import com.istz.entities.GachaDataMaster;
import com.istz.service.doGacha.OneGacha;
import com.istz.service.gachaKind.GachaKind;

public class GRB10 extends GachaKind{

	@Override
	public void horeeMawase (List<GachaDataMaster> result, String gameCd, String gachaKind, Random rnd) {
		for (int i = 0; i < Integer.parseInt(gachaKind); i++) {
			int getRarity = 0;
			if (i == Integer.parseInt(gachaKind) - 1){
				getRarity = OneGacha.decideRarityFix(gameCd, gachaKind, 67, rnd.nextInt(33) + 67);
			} else {
				getRarity = OneGacha.decideRarity(gameCd, gachaKind, rnd);
			}
			OneGacha.supriiiise(gameCd, getRarity, rnd, result);
		}
	}

}

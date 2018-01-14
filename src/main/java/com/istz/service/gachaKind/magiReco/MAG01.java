package com.istz.service.gachaKind.magiReco;

import java.util.List;
import java.util.Random;

import com.istz.entities.GachaDataMaster;
import com.istz.service.doGacha.OneGacha;
import com.istz.service.gachaKind.GachaKind;

public class MAG01 extends GachaKind{

	@Override
	public void horeeMawase (List<GachaDataMaster> result, String gameCd, String gachaKind, Random rnd) {
		for (int i = 0; i < Integer.parseInt(gachaKind); i++) {
			int getRarity = OneGacha.decideRarity(gameCd, gachaKind, rnd);
			OneGacha.supriiiise(gameCd, getRarity, rnd, result);
		}
	}
}

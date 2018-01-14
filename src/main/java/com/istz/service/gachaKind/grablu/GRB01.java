package com.istz.service.gachaKind.grablu;

import java.util.List;
import java.util.Random;

import com.istz.entities.GachaDataMaster;
import com.istz.service.doGacha.OneGacha;
import com.istz.service.gachaKind.GachaKind;

public class GRB01 extends GachaKind{

	@Override
	public void horeeMawase (List<GachaDataMaster> result, String gameCd, String gachaKind, Random rnd) {
		int getRarity = OneGacha.decideRarity(gameCd, gachaKind, rnd);
		OneGacha.supriiiise(gameCd, getRarity, rnd, result);
	}
}

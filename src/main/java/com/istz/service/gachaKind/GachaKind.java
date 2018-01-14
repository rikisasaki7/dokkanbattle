package com.istz.service.gachaKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.istz.entities.GachaDataMaster;

public abstract class GachaKind {

	public List<GachaDataMaster> doGacha (String gameCd, String gachaKind){
		List<GachaDataMaster> result = new ArrayList<GachaDataMaster>();
		Random rnd = new Random();
		horeeMawase(result, gameCd, gachaKind, rnd);
		return result;

	}

	abstract public void horeeMawase (List<GachaDataMaster> result, String gameCd, String gachaKind, Random rnd);

}

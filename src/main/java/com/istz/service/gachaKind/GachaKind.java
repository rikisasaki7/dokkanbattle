package com.istz.service.gachaKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.istz.entities.GachaDataMaster;

public abstract class GachaKind {

	public List<GachaDataMaster> doGacha (String gameCd){
		List<GachaDataMaster> result = new ArrayList<GachaDataMaster>();

		Random rnd = new Random();
		for (int i = 0; i < 10000;i++) {
			System.out.println(rnd.nextInt(100));
		}
		horeeMawase(result, gameCd);
		return result;

	}

	abstract public void horeeMawase (List<GachaDataMaster> result, String gameCd);

}

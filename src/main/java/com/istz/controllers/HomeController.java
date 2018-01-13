package com.istz.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.istz.entities.GachaDataMaster;
import com.istz.entities.GameMaster;
import com.istz.forms.GachaForm;

/**
 *
 * @author sasaki
 * RESTをサポートしないレガシーなコントローラーです。
 * Requstに対するreturn値は遷移先のjsp名称を返却してください。
 *
 * RequestMapping
 * <li>public String home(Locale, Model) => http://localhost:8080</li>
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @ModelAttribute
    GachaForm setUpForm() {
        return new GachaForm();
    }
    /**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		// TODO risasaki テスト用のため、いずれ消す
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		// ゲームマスタのセット
		// TODO DBから取得したリストを設定するように修正
		List<GameMaster> gameMasterList = new ArrayList<>();
		gameMasterList.add(new GameMaster("MAG", "マギレコ", "img/01_magiReco.jpg"));
		gameMasterList.add(new GameMaster("GRB", "グラブル", "img/02_gruble.jpg"));
		model.addAttribute("gameMasterList", gameMasterList);

		return "home";
	}
	
	@RequestMapping(value = "/simulate", method = RequestMethod.POST)
	public String simulate(@Validated GachaForm form, BindingResult result, Model model) {
		logger.info("simulate.");
		System.out.println(form.getGacha());
		System.out.println(form.getTimes());

		// ゲームマスタのセット
		// TODO DBから取得したリストを設定するように修正
		List<GameMaster> gameMasterList = new ArrayList<>();
		gameMasterList.add(new GameMaster("MAG", "マギレコ", "img/01_magiReco.jpg"));
		gameMasterList.add(new GameMaster("GRB", "グラブル", "img/02_gruble.jpg"));
		model.addAttribute("gameMasterList", gameMasterList);
		
		// ガチャ結果のセット
		// TODO DBから取得したリストを設定するように修正
		List<GachaDataMaster> gachaResultList = new ArrayList<GachaDataMaster>();
		gachaResultList.add(new GachaDataMaster("MAG0001", "1個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0002", "2個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0003", "3個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "4個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "5個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "6個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "7個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "8個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "9個目", "MAG", 10, "十連", 1));
		gachaResultList.add(new GachaDataMaster("MAG0004", "10個目", "MAG", 10, "十連", 1));
		model.addAttribute("gachaResultList", gachaResultList);
		return "home";
	}
}

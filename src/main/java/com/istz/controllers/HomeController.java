package com.istz.controllers;

import java.text.DateFormat;
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

import com.istz.forms.GachaForm;
import com.istz.service.GachaAction;

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

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/simulate", method = RequestMethod.POST)
	public String simulate(@Validated GachaForm form, BindingResult result, Model model) {
		logger.info("simulate.");
		GachaAction gachaAction = new GachaAction();
		List<String> cards = gachaAction.simulate(form.getGacha(), form.getTimes());
//		cards.add("yuno");
//		cards.add("hogehoge");
//		model.addAttribute("cards", cards);
		return "home";
	}
}

package com.istz.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.istz.DokkanbattleApplication;
import com.istz.forms.GachaForm;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {



	@Test
	public void debug() throws Exception {
		DokkanbattleApplication aa = new DokkanbattleApplication();
		aa.run(null);

		HomeController testClass = new HomeController();
		GachaForm form = new GachaForm();
		form.setGacha("MAG");
		form.setTimes("10");
		testClass.simulate(form, null, null);

	}

}

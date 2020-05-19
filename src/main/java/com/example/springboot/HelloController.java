package com.example.springboot;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The class is flagged as a @RestController, meaning it is ready
 * for use by Spring MVC to handle web requests.
 * @RequestMapping maps / to the index() method.
 * When invoked from a browser or by using curl on the command line,
 * the method returns pure text. That is because @RestController
 * combines @Controller and @ResponseBody, two annotations that
 * results in web requests returning data rather than a view.
 */
@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/hello")
	public String hello(@RequestParam(value = "city", defaultValue = "Rome") String city) {
		String message = "<h4>Hello!</h4><p>I love " + city + "!</p>" +
				"<p>It's " + getCurrentTimeUsingDate() + " (using Date object).</p>" +
				"<p>It's " + getCurrentTimeUsingCalendar() + " (using Calendar object).</p>";
		return message;
	}

	private String getCurrentTimeUsingDate() {
		Date date = new Date();
		String stringDateFormat = "hh:mm:ss a";
		DateFormat dateFormat = new SimpleDateFormat(stringDateFormat);
		return dateFormat.format(date);
	}

	private String getCurrentTimeUsingCalendar() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return dateFormat.format(date);
	}
}

package de.lhug.utils;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class UtilityClass {

	private UtilityClass() {
	}

	public static ViewResolver viewResolver() {
		return new InternalResourceViewResolver(null, ".html");
	}
}

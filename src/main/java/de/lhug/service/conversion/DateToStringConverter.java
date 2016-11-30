package de.lhug.service.conversion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class DateToStringConverter implements Converter<Date, String> {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String convert(Date source) {
		return new SimpleDateFormat(messageSource.getMessage("date.format", null, LocaleContextHolder.getLocale()))
				.format(source);
	}

}

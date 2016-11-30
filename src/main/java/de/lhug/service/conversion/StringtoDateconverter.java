package de.lhug.service.conversion;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class StringtoDateconverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		Assert.isTrue(StringUtils.isNotEmpty(source));
		int[] values = convertToInt(source.split("\\."));
		Calendar cal = Calendar.getInstance();
		cal.set(values[2], values[1], values[0]);
		return cal.getTime();
	}

	private int[] convertToInt(String[] values) {
		int[] result = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = Integer.valueOf(values[i]);
		}
		return result;
	}

}

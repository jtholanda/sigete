package br.portaldotenis.portal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.converter.Converter;

/**
 * Converte automaticamente uma data recebida no formato dd/MM/yyyy para um objeto Calendar
 * 
 * @author Gabriel Soares
 *
 */
@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Convert(Calendar.class)
public class CalendarConverter implements Converter<Calendar> {

	@Override
	public Calendar convert(String value, Class<? extends Calendar> clazz) {
		try {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(value));
			return calendar;
		} catch (ParseException e) {
			return null;
		}
	}
}
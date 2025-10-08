package br.com.banco.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	private DateTimeUtil() {}
	
	public static LocalDateTime dataAtual()
	{
		return LocalDateTime.now();
	}
	
	public static String formataHora(LocalDateTime data)
	{
		DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		return data.format(fmt);
		
	}
}

package br.com.banco.exceptions;

public class AutenticacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AutenticacaoException(String msg)
	{
		super(msg);
	}

}

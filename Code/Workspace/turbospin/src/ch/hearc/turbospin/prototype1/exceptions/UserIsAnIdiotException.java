
package ch.hearc.turbospin.prototype1.exceptions;

public class UserIsAnIdiotException extends Exception
	{

	public UserIsAnIdiotException()
		{
		}

	public UserIsAnIdiotException(String message)
		{
		super(message);
		}

	public UserIsAnIdiotException(Throwable cause)
		{
		super(cause);
		}

	public UserIsAnIdiotException(String message, Throwable cause)
		{
		super(message, cause);
		}

	public UserIsAnIdiotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
		{
		super(message, cause, enableSuppression, writableStackTrace);
		}
	}

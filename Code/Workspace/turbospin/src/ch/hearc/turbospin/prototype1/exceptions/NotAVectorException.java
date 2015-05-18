
package ch.hearc.turbospin.prototype1.exceptions;

public class NotAVectorException extends Exception
	{

	public NotAVectorException()
		{
		}

	public NotAVectorException(String message)
		{
		super(message);
		}

	public NotAVectorException(Throwable cause)
		{
		super(cause);
		}

	public NotAVectorException(String message, Throwable cause)
		{
		super(message, cause);
		}

	public NotAVectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
		{
		super(message, cause, enableSuppression, writableStackTrace);
		}
	}

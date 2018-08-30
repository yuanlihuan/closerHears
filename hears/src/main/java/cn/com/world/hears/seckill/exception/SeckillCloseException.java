package cn.com.world.hears.seckill.exception;

/**
 * 秒杀关闭异常
 * @author liulai
 */
public class SeckillCloseException extends SeckillException {

	private static final long serialVersionUID = 1L;

	public SeckillCloseException(String message){
		super(message);
	};
	
	public SeckillCloseException(String message, Throwable cause){
		super(message,cause);
	};
}

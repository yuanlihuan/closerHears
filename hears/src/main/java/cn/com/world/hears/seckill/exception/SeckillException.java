package cn.com.world.hears.seckill.exception;

/**
 * 秒杀业务异常
 * @author liulai
 *
 */
public class SeckillException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeckillException(String message){
		super(message);
	};
	
	public SeckillException(String message, Throwable cause){
		super(message,cause);
	};
}

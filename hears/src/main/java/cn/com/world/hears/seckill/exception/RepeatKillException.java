package cn.com.world.hears.seckill.exception;

/**
 * 重复秒杀异常
 * @author liulai
 */
public class RepeatKillException extends SeckillException{

	private static final long serialVersionUID = 1L;
	
	public RepeatKillException(String message) {
		super(message);
	}
	public RepeatKillException(String message,Throwable cause) {
		super(message,cause);
	}
	
}

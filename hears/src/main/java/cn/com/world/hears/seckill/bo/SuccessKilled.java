package cn.com.world.hears.seckill.bo;

import java.util.Date;

public class SuccessKilled {
	
 	private Long seckillId;

    private Long userPhone;

    private Byte state;

    private Date createTime;
    
    private Seckill seckill;

    public Byte getState() {
        return state;
    }
   
    public void setState(Byte state) {
        this.state = state;
    }
    
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public Long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
}
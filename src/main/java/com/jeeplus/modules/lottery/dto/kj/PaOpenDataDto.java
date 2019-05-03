package com.jeeplus.modules.lottery.dto.kj;

import lombok.Data;

@Data
public class PaOpenDataDto {
    private String beginDate;
    private String endDate;
    /**
     * 锁盘时间秒
     */
    private String lockSeconds;
    /**
     * 彩票的标识
     */
    private String lotteryId;

}

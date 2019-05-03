package com.jeeplus.common.base;

import com.jeeplus.modules.lottery.entity.wf.WfMain;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageBean<T> {
    protected Integer pageNo = 1; // 当前页码
    protected Integer pageSize = 10; // 页面大小，设置为“-1”表示不进行分页（分页无效）
    protected long count;// 总记录数，设置为“-1”表示不查询总数

    private List<T> list = new ArrayList<T>();//返回数据的时候存放实体类

    public PageBean(){

    }
    public PageBean(Integer pageNo,Integer pageSize){
        if(null==pageNo || null== pageSize){
            this.pageNo=1;
            this.pageSize=10;
        }else {
            this.pageNo=pageNo;
            this.pageSize=pageSize;
        }

    }
}

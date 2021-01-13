package com.lvchao.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lvchao.easyexcel.entity.ExcelTotalVo;
import com.lvchao.easyexcel.entity.User;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */
public class ExcelTotalDataListener  extends AnalysisEventListener<User> {

    private ExcelTotalVo excelTotalVo;

    public ExcelTotalDataListener(ExcelTotalVo excelTotalVo){
        System.out.println("走构造方法");
        this.excelTotalVo = excelTotalVo;
    }
    @Override
    public void invoke(User data, AnalysisContext context) {
     //   Thread.sleep(1000);
        excelTotalVo.setTotal(excelTotalVo.getTotal() + 1);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("统计数量导入数量结束");
    }
}

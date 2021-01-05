package com.lvchao.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lvchao.easyexcel.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2021/1/5
 */
public class ExcelListener extends AnalysisEventListener<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<User> list = new ArrayList<User>();

    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(user));
        list.add(user);
        if (list.size() >= BATCH_COUNT) {
            //saveData();
            System.out.println("保存数据并清空数据");
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("保存数据并清空数据");
        System.out.println("结束时触发此方法");
    }
}

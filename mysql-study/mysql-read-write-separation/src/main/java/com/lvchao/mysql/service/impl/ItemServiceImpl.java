package com.lvchao.mysql.service.impl;

import com.lvchao.mysql.entity.Item;
import com.lvchao.mysql.mapper.ItemMapper;
import com.lvchao.mysql.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lvchao
 * @since 2020-12-16
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}

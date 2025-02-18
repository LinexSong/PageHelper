package com.wolfhouse.pagehelper.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linexsong
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {
    private int pageNo = 1;
    private int pageSize = 5;
    private String orderBy;
    private boolean isAsc = true;

    public <VO> Page<VO> toPage(OrderItem... orderItems) {
        Page<VO> page = Page.of(pageNo, pageSize);
        if (StrUtil.isNotEmpty(orderBy)) {
            page.addOrder(new OrderItem().setAsc(isAsc).setColumn(orderBy));
        }
        if (orderItems != null) {
            page.addOrder(orderItems);
        }
        return page;
    }

    public <VO> Page<VO> toPage(String defaultOrderBy, Boolean defaultAsc) {
        return toPage(new OrderItem().setAsc(defaultAsc).setColumn(defaultOrderBy));
    }
}

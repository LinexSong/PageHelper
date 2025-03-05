package com.wolfhouse.pagehelper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author linexsong
 */

@Data
@Builder
public class PageResult<T> {
    protected Long total;
    protected Long pages;
    protected List<T> list;

    public static <VO, PO> PageResult<VO> of(Page<PO> p, Class<VO> clazz) {
        PageResult<VO> pr = getBases(p);
        List<PO> records = p.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            pr.setList(Collections.emptyList());
            return pr;
        }
        pr.setList(BeanUtil.copyToList(records, clazz));
        return pr;
    }

    public static <VO, PO> PageResult<VO> of(Page<PO> p, Function<PO, VO> convertor) {
        PageResult<VO> pr = getBases(p);
        List<PO> records = p.getRecords();
        if (CollectionUtil.isEmpty(records)) {
            pr.setList(Collections.emptyList());
            return pr;
        }
        List<VO> vos = records.stream().map(convertor).toList();
        pr.setList(vos);
        return pr;
    }

    public static <T, V> PageResult<T> getBases(Page<V> p) {
        return PageResult.<T>builder().total(p.getTotal()).pages(p.getPages()).build();
    }
}

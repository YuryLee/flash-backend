package com.flash.flash.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class PageUtils<T> {

    private long pageNum;

    private long pageSize;

    private long totalCount;

    private List<T> list;

    public PageUtils(IPage<T> page) {
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
    }

    public PageUtils() {
    }
}

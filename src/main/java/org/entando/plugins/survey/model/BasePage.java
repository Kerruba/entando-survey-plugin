package org.entando.plugins.survey.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class BasePage<T> {

    private List<T> list;
    private long count;
    private long page;
    private long pages;

    @Builder
    public BasePage(final List<T> list, final long count, final long page, final long pageSize) {
        this.list = list;
        this.count = list.size();
        this.page = page;
        this.pages = (long) Math.ceil((double)count / (double)Math.max(1, pageSize));
    }

}
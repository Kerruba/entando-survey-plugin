package org.entando.plugins.survey.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePage<T> {

    private List<T> list;
    private long count;
    private long page;
    private long pages;

    public static <R> BasePage<R> build(final long page, final long pageSize, final long count, final List<R> list) {
        return new BasePage<>(list, count, page, (long) Math.ceil((double)count / (double)Math.max(1, pageSize)));
    }

}
package com.jarry.demo1.java3y.ProxyMode.DesignMode.ChainOfResponsPa;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.ChainOfResponsPa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 16:26
 */
public class FilterChain {

    List<Filter> filters = new ArrayList<>(10);

    public FilterChain() {
        filters.add(new FilterEgg());
        filters.add(new FilterAoBing());
        filters.add(new FilterBaiCai());
    }

    public void processData(String data) {
        for (Filter f :
                filters) {
            f.doFilter(data);
        }
    }
}

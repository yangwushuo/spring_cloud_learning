package com.jason.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author：yangwushuo
 * @time：2022/10/11 21:11
 */
public class ProductVo {

    private Integer id;
    private List<Integer> ids;
    private String name;
    private Long count;
    private BigDecimal price;

    public ProductVo(Integer id, List<Integer> ids, String name, Long count, BigDecimal price) {
        this.id = id;
        this.ids = ids;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}

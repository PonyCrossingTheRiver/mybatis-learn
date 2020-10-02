package com.wpp.pojo;

import java.io.Serializable;

/**
 * @author by wpp25
 * @Classname AnnotationOrder
 * @Description
 * @Date 2020/9/29 23:04
 */
public class AnnotationOrder implements Serializable {
    private Integer id;
    private String orderTime;
    private Double total;

    private AnnotationUser annotationUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public AnnotationUser getAnnotationUser() {
        return annotationUser;
    }

    public void setAnnotationUser(AnnotationUser annotationUser) {
        this.annotationUser = annotationUser;
    }

    @Override
    public String toString() {
        return "AnnotationOrder{" +
                "id=" + id +
                ", orderTime='" + orderTime + '\'' +
                ", total=" + total +
                ", annotationUser=" + annotationUser +
                '}';
    }
}

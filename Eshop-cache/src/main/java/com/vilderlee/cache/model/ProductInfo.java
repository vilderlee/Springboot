package com.vilderlee.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.model
 * @auther vilderlee
 * @date 2019/11/9 5:04 下午
 */
@Data
@AllArgsConstructor
@ToString
public class ProductInfo {

    private Long id;
    private String name;
    private Double price;
    private String pictureList;
    private String specification;
    private String service;
    private String color;
    private String size;
    private Long shopId;
}

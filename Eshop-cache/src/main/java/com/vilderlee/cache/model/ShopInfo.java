package com.vilderlee.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 功能描述:
 * 店铺信息
 *
 * @package com.vilderlee.cache.model
 * @auther vilderlee
 * @date 2019/11/9 10:06 下午
 */
@Data
@AllArgsConstructor
@ToString
public class ShopInfo {

    private Long id;
    private String name;
    private Integer level;
    private Double goodCommentRate;
}

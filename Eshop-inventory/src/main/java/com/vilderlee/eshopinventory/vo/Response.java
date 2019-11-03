package com.vilderlee.eshopinventory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.vo
 * @auther vilderlee
 * @date 2019/11/3 12:22 下午
 */
@Data
@AllArgsConstructor
public class Response {

    public static final String SUCCESS = "success";

    public static final String FAILURE = "failure";

    public Response(String status) {
        this.status = status;
    }

    private String status;

    private String message;

}

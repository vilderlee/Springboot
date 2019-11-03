package com.vilderlee.elasticjob.entity;

import lombok.Data;

import java.sql.Time;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/23      Create this file
 * </pre>
 */
@Data
public class Batch {

    private int id;

    private Time updateDate;
}

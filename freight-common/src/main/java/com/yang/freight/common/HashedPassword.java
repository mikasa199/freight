package com.yang.freight.common;

import lombok.*;

/**
 * @description:
 * @author：杨超
 * @date: 2023/11/17
 * @Copyright：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HashedPassword {

    private byte[] hashedPassword;
    private byte[] salt;

}

package com.zhskg.bag.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jean
 * @date 2018/10/7
 * desc: jwt param
 */
@Data
public class JwtParam implements Serializable{


    private Long registerId;

    private String account;

    private String userName;

    private String email;

    private String nice;

    private String head;

    private Integer status;
}

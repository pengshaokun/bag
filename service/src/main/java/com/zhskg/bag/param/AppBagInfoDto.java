package com.zhskg.bag.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: qiuw
 * @date: 2018/10/15 15:29
 * @description:
 */
@Data
public class AppBagInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 箱包Id
     */
    private Long bagInfoId;

    /**
     * 箱包唯一编号（）
     */
    @NotNull(message = "箱包编号不能为空")
    private String bagId;

    /**
     * 箱包MAC地址（添加MAC地址校验）
     */
    @NotNull(message = "MAC地址不能为空")
    @ApiModelProperty(value = "MAC地址", name = "bleMac", required = true)
    private String bleMac;

    /**
     * 箱包名称
     */
    @Length(max = 10, message = "名称长度不能超过10个字符")
    private String productName;

    /**
     * 当前使用用户账号id
     */
    private Long currentUserId;

    /**
     * 传输密码
     */
    private String connPassword;

}

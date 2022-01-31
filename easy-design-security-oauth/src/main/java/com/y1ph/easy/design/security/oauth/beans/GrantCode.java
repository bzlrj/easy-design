package com.y1ph.easy.design.security.oauth.beans;

import com.y1ph.easy.design.security.beans.Principal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 授权码对象
 *
 * @author WFT
 * @since 2022/1/6
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrantCode implements Serializable {

    /**
     * 用户主体信息
     */
    private Principal<?> principal;

    /**
     * 客户端信息
     */
    private OauthClient<?> client;

}

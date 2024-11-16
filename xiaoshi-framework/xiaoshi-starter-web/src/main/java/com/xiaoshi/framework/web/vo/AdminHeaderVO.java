package com.xiaoshi.framework.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminHeaderVO {

    private String traceId;

    private String authorization;

}

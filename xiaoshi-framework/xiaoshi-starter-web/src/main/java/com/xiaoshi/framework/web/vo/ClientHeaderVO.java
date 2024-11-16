package com.xiaoshi.framework.web.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientHeaderVO {

    private String traceId;

    private String language;

    private String appId;

    private String uid;

    private String authToken;

    private String appVer;

    private String sdkVer;

    private String userToken;

    private String uuid;

}

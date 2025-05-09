package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.BaseRequest;
import com.huawei.agconnect.https.annotation.Field;
import com.huawei.agconnect.https.annotation.Url;
import com.huawei.operation.jsoperation.JsUtil;

/* loaded from: classes2.dex */
public class au extends BaseRequest {

    @Url
    private static final String REQUEST_URL = "http://localhost/agc/apigw/router";

    @Field("appId")
    private String appId;

    @Field("appVersion")
    private String appVersion;

    @Field("clientId")
    private String clientId;

    @Field("packageName")
    private String packageName;

    @Field("productId")
    private String productId;

    @Field(JsUtil.SERVICE_NAME)
    String serviceName;

    public void a(String str) {
        if (str != null) {
            this.serviceName = str;
        }
    }

    public au(AGConnectInstance aGConnectInstance) {
        super(aGConnectInstance);
        this.productId = getHeaderProductId();
        this.appId = getHeaderAppId();
        this.appVersion = getAppVersion();
        this.packageName = getPackageName();
        this.clientId = getHeaderClientId();
        this.serviceName = "agconnect-credential";
    }
}

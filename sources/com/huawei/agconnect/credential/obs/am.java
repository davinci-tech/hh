package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.common.api.BaseRequest;
import com.huawei.agconnect.https.annotation.Field;
import com.huawei.agconnect.https.annotation.Header;
import com.huawei.agconnect.https.annotation.Url;
import com.huawei.hms.mlsdk.common.AgConnectInfo;

/* loaded from: classes8.dex */
public class am extends BaseRequest {

    @Url
    private static final String REQUEST_URL = "http://localhost/agc/apigw/oauth2/v1/token";

    @Header("app_id")
    private String appId;

    @Field("client_id")
    private String clientId;

    @Field("client_secret")
    private String clientSecret;

    @Field("grant_type")
    private String grantType;

    @Field("useJwt")
    private int useJwt;

    public void setClientSecret(String str) {
        this.clientSecret = str;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getGrantType() {
        return this.grantType;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getAppId() {
        return this.appId;
    }

    public am(AGConnectInstance aGConnectInstance) {
        super(aGConnectInstance);
        this.grantType = "client_credentials";
        this.useJwt = 1;
        setSdkServiceName("agconnect-credential");
        setSdkVersion("1.9.1.304");
        AGConnectOptions options = aGConnectInstance.getOptions();
        this.appId = options.getString(AgConnectInfo.AgConnectKey.APPLICATION_ID);
        this.clientId = options.getString("client/client_id");
        this.clientSecret = options.getString("client/client_secret");
    }
}

package com.huawei.codevalueplatform.auth;

import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.anno.GET;
import com.huawei.hms.network.restclient.anno.QueryMap;
import java.util.Map;

/* loaded from: classes8.dex */
interface OAuthApi {
    @GET("/rest.php?")
    Submit<Object> getOAuthInfo(@QueryMap Map<String, String> map);
}

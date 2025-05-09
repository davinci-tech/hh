package com.huawei.hwidauth.utils;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.util.Map;

/* loaded from: classes5.dex */
public interface a {
    @GET
    Submit<ResponseBody> a(@Url String str);

    @POST
    Submit<ResponseBody> a(@Url String str, @Body RequestBody requestBody);

    @POST
    Submit<ResponseBody> a(@Url String str, @Body RequestBody requestBody, @HeaderMap Map<String, String> map);
}

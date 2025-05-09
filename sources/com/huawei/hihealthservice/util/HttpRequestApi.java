package com.huawei.hihealthservice.util;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.DELETE;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.PUT;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.util.Map;

/* loaded from: classes4.dex */
public interface HttpRequestApi {
    @DELETE
    Submit<String> deleteReq(@Url String str, @HeaderMap Map<String, Object> map);

    @POST
    Submit<String> getPostReq(@Url String str, @HeaderMap Map<String, Object> map, @Body RequestBody requestBody);

    @POST
    Submit<ResponseBody> getPostReq(@Url String str, @HeaderMap Map<String, Object> map, @Body String str2);

    @GET
    Submit<String> getReq(@Url String str, @HeaderMap Map<String, Object> map);

    @GET
    Submit<String> getReq(@Url String str, @HeaderMap Map<String, Object> map, @QueryMap Map<String, Object> map2);

    @PUT
    Submit<String> putReq(@Url String str, @HeaderMap Map<String, Object> map, @Body RequestBody requestBody);
}

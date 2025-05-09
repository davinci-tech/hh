package com.huawei.hwcloudmodel.contentcenter;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.PUT;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.util.Map;

/* loaded from: classes9.dex */
public interface ContentCenterApi {
    @POST
    Submit<String> postToContentCenter(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @PUT
    Submit<String> putToContentCenter(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);
}

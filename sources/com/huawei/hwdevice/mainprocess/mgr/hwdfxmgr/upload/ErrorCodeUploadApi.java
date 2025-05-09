package com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.upload;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.PUT;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.util.Map;

/* loaded from: classes5.dex */
public interface ErrorCodeUploadApi {
    @POST
    Submit<String> errorCodeUploadPost(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);

    @POST
    Submit<ResponseBody> errorCodeUploadPost(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @PUT
    Submit<String> errorCodeUploadPut(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);
}

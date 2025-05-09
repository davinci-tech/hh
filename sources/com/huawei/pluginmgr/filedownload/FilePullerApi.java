package com.huawei.pluginmgr.filedownload;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import java.util.Map;

/* loaded from: classes.dex */
public interface FilePullerApi {
    @GET
    Submit<ResponseBody> getFileUrlGet(@Url String str, @HeaderMap Map<String, String> map);

    @POST
    Submit<ResponseBody> getFileUrlPost(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);
}

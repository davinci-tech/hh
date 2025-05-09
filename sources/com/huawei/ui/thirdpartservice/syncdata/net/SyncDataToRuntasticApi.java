package com.huawei.ui.thirdpartservice.syncdata.net;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.util.Map;

/* loaded from: classes7.dex */
public interface SyncDataToRuntasticApi {
    @POST
    Submit<String> uploadGpxToRuntastic(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);
}

package com.huawei.ui.thirdpartservice.syncdata.net;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.util.Map;

/* loaded from: classes7.dex */
public interface SyncDataToKomootApi {
    @GET
    Submit<String> getRoad(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @GET
    Submit<String> getRoadList(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);

    @POST
    Submit<String> uploadGpxToKomoot(@Url String str, @HeaderMap Map<String, String> map, @Body RequestBody requestBody);
}

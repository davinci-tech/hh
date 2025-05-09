package com.huawei.hwdevice.mainprocess.mgr.hwwatchfacemgr.touchtransfer.restclienthttp;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import java.util.Map;

/* loaded from: classes5.dex */
public interface StringGetRestClientService {
    @GET
    Submit<String> executeRestClientRequest(@HeaderMap Map<String, String> map, @Url String str, @QueryMap Map<String, String> map2);
}

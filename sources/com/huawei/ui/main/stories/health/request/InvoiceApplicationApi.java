package com.huawei.ui.main.stories.health.request;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import java.util.Map;

/* loaded from: classes8.dex */
public interface InvoiceApplicationApi {
    @GET
    Submit<RespDownloadInvoice> getDownloadInvoice(@Url String str, @HeaderMap Map<String, String> map);

    @POST
    Submit<InvoiceResponse> getInvoiceApply(@Url String str, @HeaderMap Map<String, String> map, @Body String str2);

    @GET
    Submit<RespQueryResult> getQueryResult(@Url String str, @HeaderMap Map<String, String> map);
}

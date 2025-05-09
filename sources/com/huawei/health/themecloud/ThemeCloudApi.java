package com.huawei.health.themecloud;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.FieldMap;
import com.huawei.hms.framework.network.restclient.annotate.FormUrlEncoded;
import com.huawei.hms.framework.network.restclient.annotate.GET;
import com.huawei.hms.framework.network.restclient.annotate.HeaderMap;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.QueryMap;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import defpackage.gix;
import defpackage.giz;
import java.util.Map;

/* loaded from: classes4.dex */
public interface ThemeCloudApi {
    @POST
    @FormUrlEncoded
    Submit<gix> getSign(@Url String str, @HeaderMap Map<String, String> map, @FieldMap Map<String, String> map2);

    @GET
    Submit<giz> productQueryByType(@Url String str, @HeaderMap Map<String, String> map, @QueryMap Map<String, String> map2);
}

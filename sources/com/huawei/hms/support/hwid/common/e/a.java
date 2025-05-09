package com.huawei.hms.support.hwid.common.e;

import com.huawei.hms.framework.network.restclient.Submit;
import com.huawei.hms.framework.network.restclient.annotate.Body;
import com.huawei.hms.framework.network.restclient.annotate.POST;
import com.huawei.hms.framework.network.restclient.annotate.Url;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;

/* loaded from: classes9.dex */
public interface a {
    @POST
    Submit<ResponseBody> a(@Url String str, @Body RequestBody requestBody);
}

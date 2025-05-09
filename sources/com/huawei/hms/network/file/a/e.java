package com.huawei.hms.network.file.a;

import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.task.k;
import com.huawei.hms.network.file.core.task.l;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.download.api.GetRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Response;

/* loaded from: classes4.dex */
public class e implements com.huawei.hms.network.file.core.task.g<k<GetRequest>, l> {

    /* renamed from: a, reason: collision with root package name */
    com.huawei.hms.network.file.core.task.f f5599a;
    com.huawei.hms.network.file.core.task.b b;
    GlobalRequestConfig c;
    HttpClient d;

    @Override // com.huawei.hms.network.file.core.task.g
    public void a(com.huawei.hms.network.file.core.task.f fVar) {
        this.f5599a = fVar;
    }

    @Override // com.huawei.hms.network.file.core.task.g
    public l a(k<GetRequest> kVar) {
        GlobalRequestConfig globalRequestConfig = this.c;
        if (kVar.p().getConfig() != null) {
            globalRequestConfig = globalRequestConfig.genMergedRequestConfig(kVar.p().getConfig());
        }
        this.b = new a(globalRequestConfig, this.d);
        FLogger.i("Downloader", "process task:" + kVar, new Object[0]);
        Response response = (Response) this.b.a(kVar, this.f5599a);
        l lVar = new l(Constants.ErrorCode.SUCCESS);
        lVar.setTask(kVar);
        kVar.a(lVar);
        lVar.setRawResponse(response);
        kVar.c(true);
        return lVar;
    }

    public e(GlobalRequestConfig globalRequestConfig, HttpClient httpClient) {
        this.c = globalRequestConfig;
        this.d = httpClient;
    }
}

package com.huawei.hms.network.file.b;

import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.task.l;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.upload.api.BodyRequest;
import com.huawei.hms.network.httpclient.HttpClient;
import java.io.Closeable;

/* loaded from: classes4.dex */
public class h implements com.huawei.hms.network.file.core.task.g<f, l> {

    /* renamed from: a, reason: collision with root package name */
    com.huawei.hms.network.file.core.task.f f5621a;
    GlobalRequestConfig b;
    com.huawei.hms.network.file.core.task.b c;
    HttpClient d;

    @Override // com.huawei.hms.network.file.core.task.g
    public void a(com.huawei.hms.network.file.core.task.f fVar) {
        this.f5621a = fVar;
    }

    @Override // com.huawei.hms.network.file.core.task.g
    public l a(f fVar) {
        FLogger.i("Uploader", "process task:" + fVar, new Object[0]);
        GlobalRequestConfig globalRequestConfig = this.b;
        if (((BodyRequest) fVar.p()).getConfig() != null) {
            globalRequestConfig = globalRequestConfig.genMergedRequestConfig(((BodyRequest) fVar.p()).getConfig());
        }
        b bVar = new b(globalRequestConfig, this.d);
        this.c = bVar;
        Closeable a2 = bVar.a((b) fVar, this.f5621a);
        l lVar = new l(Constants.ErrorCode.SUCCESS);
        lVar.setTask(fVar);
        lVar.setRawResponse(a2);
        fVar.a(lVar);
        lVar.a(fVar.f() + " finished success");
        fVar.c(true);
        return lVar;
    }

    public h(GlobalRequestConfig globalRequestConfig, HttpClient httpClient) {
        this.b = globalRequestConfig;
        this.d = httpClient;
    }
}

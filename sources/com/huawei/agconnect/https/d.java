package com.huawei.agconnect.https;

import com.huawei.agconnect.https.connector.HttpsPlatform;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class d implements Service {

    /* renamed from: a, reason: collision with root package name */
    private OkHttpClient f1804a;
    private Executor b;

    @Override // com.huawei.agconnect.https.Service
    public Task<HttpsResult> execute(final Method method) {
        return Tasks.callInBackground(this.b, new Callable<HttpsResult>() { // from class: com.huawei.agconnect.https.d.1
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public HttpsResult call() throws Exception {
                if (!HttpsPlatform.getInstance().getConnector().hasActiveNetwork()) {
                    throw new HttpsException(false, "There's no network");
                }
                try {
                    Response execute = d.this.f1804a.newCall(method.create().build()).execute();
                    return new HttpsResult(true, execute.code(), execute);
                } catch (IOException e) {
                    throw new HttpsException(true, e);
                }
            }
        });
    }

    d(OkHttpClient okHttpClient, Executor executor) {
        this.f1804a = okHttpClient;
        this.b = executor;
    }
}

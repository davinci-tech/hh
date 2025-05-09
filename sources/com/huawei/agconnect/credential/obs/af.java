package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.core.service.auth.CredentialsProvider;
import com.huawei.agconnect.core.service.auth.Token;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hmf.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* loaded from: classes2.dex */
final class af implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1743a = "ClientTokenInterceptor";
    private AGConnectInstance b;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        try {
            Token token = (Token) Tasks.await(((CredentialsProvider) this.b.getService(CredentialsProvider.class)).getTokens(), 5L, TimeUnit.SECONDS);
            return chain.proceed(chain.request().newBuilder().addHeader("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + token.getTokenString()).build());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IOException(e.getMessage());
        }
    }

    public af(AGConnectInstance aGConnectInstance) {
        this.b = aGConnectInstance;
    }
}

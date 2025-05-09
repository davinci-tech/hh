package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.core.service.auth.AuthProvider;
import com.huawei.agconnect.core.service.auth.Token;
import com.huawei.hmf.tasks.Tasks;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
final class aa implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private AGConnectInstance f1722a;
    private boolean b;
    private boolean c;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        if (((AuthProvider) this.f1722a.getService(AuthProvider.class)) == null) {
            if (this.b) {
                throw new IOException("Please intergrate agconnect-auth in project");
            }
            return chain.proceed(chain.request());
        }
        try {
            Token token = (Token) Tasks.await(((AuthProvider) this.f1722a.getService(AuthProvider.class)).getTokens(), 5L, TimeUnit.SECONDS);
            if (token != null) {
                Request request = chain.request();
                return chain.proceed((this.c ? request.newBuilder().addHeader(Constants.PARAM_ACCESS_TOKEN, token.getTokenString()).addHeader("accessToken", token.getTokenString()) : request.newBuilder().addHeader(Constants.PARAM_ACCESS_TOKEN, token.getTokenString())).build());
            }
            if (this.b) {
                throw new IOException("no user is signed");
            }
            return chain.proceed(chain.request());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IOException(e.getMessage());
        }
    }

    public aa(AGConnectInstance aGConnectInstance, boolean z, boolean z2) {
        this.f1722a = aGConnectInstance;
        this.b = z;
        this.c = z2;
    }
}

package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.BackendService;
import okhttp3.Authenticator;

/* loaded from: classes2.dex */
final class y implements Authenticator {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1786a = "AGCAuthenticator";
    private AGConnectInstance b;
    private BackendService.Options c;

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00dd, code lost:
    
        if (r0 != false) goto L38;
     */
    @Override // okhttp3.Authenticator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Request authenticate(okhttp3.Route r9, okhttp3.Response r10) {
        /*
            r8 = this;
            java.lang.String r9 = "access_token"
            java.lang.String r0 = "Authorization"
            java.lang.String r1 = "AGCAuthenticator"
            java.lang.String r2 = "authenticate"
            com.huawei.agconnect.common.api.Logger.i(r1, r2)
            com.huawei.agconnect.https.adapter.JsonAdapterFactory r1 = new com.huawei.agconnect.https.adapter.JsonAdapterFactory
            r1.<init>()
            java.lang.Class<com.huawei.agconnect.common.api.BaseResponse> r2 = com.huawei.agconnect.common.api.BaseResponse.class
            com.huawei.agconnect.https.Adapter r1 = r1.responseBodyAdapter(r2)
            okhttp3.ResponseBody r2 = r10.body()
            java.lang.Object r1 = r1.adapter(r2)
            com.huawei.agconnect.common.api.BaseResponse r1 = (com.huawei.agconnect.common.api.BaseResponse) r1
            okhttp3.Request r10 = r10.request()
            okhttp3.Request$Builder r10 = r10.newBuilder()
            if (r1 == 0) goto Le4
            com.huawei.agconnect.credential.obs.ao r1 = r1.getRet()
            int r1 = r1.getCode()
            r2 = 205524993(0xc401001, float:1.4795958E-31)
            r3 = 3
            r5 = 1
            if (r1 != r2) goto L86
            com.huawei.agconnect.common.api.BackendService$Options r2 = r8.c
            boolean r2 = r2.isClientTokenRefreshed()
            if (r2 != 0) goto L86
            com.huawei.agconnect.common.api.BackendService$Options r2 = r8.c
            r2.setClientTokenRefreshed(r5)
            com.huawei.agconnect.AGConnectInstance r2 = r8.b
            java.lang.Class<com.huawei.agconnect.core.service.auth.CredentialsProvider> r6 = com.huawei.agconnect.core.service.auth.CredentialsProvider.class
            java.lang.Object r2 = r2.getService(r6)
            com.huawei.agconnect.core.service.auth.CredentialsProvider r2 = (com.huawei.agconnect.core.service.auth.CredentialsProvider) r2
            com.huawei.hmf.tasks.Task r2 = r2.getTokens(r5)
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            java.lang.Object r2 = com.huawei.hmf.tasks.Tasks.await(r2, r3, r6)     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            com.huawei.agconnect.core.service.auth.Token r2 = (com.huawei.agconnect.core.service.auth.Token) r2     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            java.lang.String r2 = r2.getTokenString()     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            r10.removeHeader(r0)     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            java.lang.String r7 = "Bearer "
            r6.<init>(r7)     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            r6.append(r2)     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            java.lang.String r2 = r6.toString()     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            r10.addHeader(r0, r2)     // Catch: java.util.concurrent.TimeoutException -> L77 java.lang.InterruptedException -> L79 java.util.concurrent.ExecutionException -> L7b
            r0 = r5
            goto L87
        L77:
            r9 = move-exception
            goto L7c
        L79:
            r9 = move-exception
            goto L7c
        L7b:
            r9 = move-exception
        L7c:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r9 = r9.getMessage()
            r10.<init>(r9)
            throw r10
        L86:
            r0 = 0
        L87:
            r2 = 205524994(0xc401002, float:1.4795959E-31)
            if (r1 != r2) goto Ldd
            com.huawei.agconnect.common.api.BackendService$Options r1 = r8.c
            boolean r1 = r1.isAccessTokenRefreshed()
            if (r1 != 0) goto Ldd
            com.huawei.agconnect.AGConnectInstance r0 = r8.b
            java.lang.Class<com.huawei.agconnect.core.service.auth.AuthProvider> r1 = com.huawei.agconnect.core.service.auth.AuthProvider.class
            java.lang.Object r0 = r0.getService(r1)
            com.huawei.agconnect.core.service.auth.AuthProvider r0 = (com.huawei.agconnect.core.service.auth.AuthProvider) r0
            if (r0 == 0) goto Ld5
            com.huawei.agconnect.common.api.BackendService$Options r0 = r8.c
            r0.setAccessTokenRefreshed(r5)
            com.huawei.agconnect.AGConnectInstance r0 = r8.b
            java.lang.Class<com.huawei.agconnect.core.service.auth.AuthProvider> r1 = com.huawei.agconnect.core.service.auth.AuthProvider.class
            java.lang.Object r0 = r0.getService(r1)
            com.huawei.agconnect.core.service.auth.AuthProvider r0 = (com.huawei.agconnect.core.service.auth.AuthProvider) r0
            com.huawei.hmf.tasks.Task r0 = r0.getTokens(r5)
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.util.concurrent.TimeoutException -> Lc6 java.lang.InterruptedException -> Lc8 java.util.concurrent.ExecutionException -> Lca
            java.lang.Object r0 = com.huawei.hmf.tasks.Tasks.await(r0, r3, r1)     // Catch: java.util.concurrent.TimeoutException -> Lc6 java.lang.InterruptedException -> Lc8 java.util.concurrent.ExecutionException -> Lca
            com.huawei.agconnect.core.service.auth.Token r0 = (com.huawei.agconnect.core.service.auth.Token) r0     // Catch: java.util.concurrent.TimeoutException -> Lc6 java.lang.InterruptedException -> Lc8 java.util.concurrent.ExecutionException -> Lca
            r10.removeHeader(r9)     // Catch: java.util.concurrent.TimeoutException -> Lc6 java.lang.InterruptedException -> Lc8 java.util.concurrent.ExecutionException -> Lca
            java.lang.String r0 = r0.getTokenString()     // Catch: java.util.concurrent.TimeoutException -> Lc6 java.lang.InterruptedException -> Lc8 java.util.concurrent.ExecutionException -> Lca
            r10.addHeader(r9, r0)     // Catch: java.util.concurrent.TimeoutException -> Lc6 java.lang.InterruptedException -> Lc8 java.util.concurrent.ExecutionException -> Lca
            goto Ldf
        Lc6:
            r9 = move-exception
            goto Lcb
        Lc8:
            r9 = move-exception
            goto Lcb
        Lca:
            r9 = move-exception
        Lcb:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r9 = r9.getMessage()
            r10.<init>(r9)
            throw r10
        Ld5:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Please intergrate agconnect-auth in project"
            r9.<init>(r10)
            throw r9
        Ldd:
            if (r0 == 0) goto Le4
        Ldf:
            okhttp3.Request r9 = r10.build()
            goto Le5
        Le4:
            r9 = 0
        Le5:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.credential.obs.y.authenticate(okhttp3.Route, okhttp3.Response):okhttp3.Request");
    }

    public y(BackendService.Options options) {
        this.b = options.getApp();
        this.c = options;
    }
}

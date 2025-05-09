package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.common.api.PackageUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class ab implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1723a = "ApiKeyInterceptor";
    private AGConnectInstance b;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        String installedAppSign256 = PackageUtils.getInstalledAppSign256(this.b.getContext(), this.b.getContext().getPackageName());
        String string = this.b.getOptions().getString("/client/api_key");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(installedAppSign256)) {
            Logger.e(f1723a, "no apikey or fingerPrinter");
        }
        return chain.proceed(chain.request().newBuilder().addHeader("x-apik", string).addHeader("x-cert-fp", installedAppSign256).removeHeader("client_id").removeHeader("Authorization").build());
    }

    public ab(AGConnectInstance aGConnectInstance) {
        this.b = aGConnectInstance;
    }
}

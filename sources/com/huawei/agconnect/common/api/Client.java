package com.huawei.agconnect.common.api;

import android.content.Context;
import com.huawei.agconnect.credential.obs.ae;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/* loaded from: classes8.dex */
public class Client {
    public static OkHttpClient build(Context context, List<Interceptor> list, boolean z) {
        return new ae(context, list, z).a();
    }

    public static OkHttpClient build(Context context, List<Interceptor> list) {
        return new ae(context, list, false).a();
    }
}

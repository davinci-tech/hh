package com.huawei.openalliance.ad.analysis;

import android.content.Context;
import com.huawei.hms.ads.analysis.DynamicLoaderAnalysis;
import com.huawei.hms.ads.analysis.IDynamicLoaderAnalysis;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.inter.HiAd;

/* loaded from: classes5.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static a f6637a;

    static class a implements IDynamicLoaderAnalysis {

        /* renamed from: a, reason: collision with root package name */
        private Context f6638a;

        @Override // com.huawei.hms.ads.analysis.IDynamicLoaderAnalysis
        public void onLoaderSuccess(String str, String str2, long j) {
            if (HiAd.getInstance(this.f6638a).isEnableUserInfo()) {
                new h(this.f6638a).a(str, str2, j);
            }
        }

        @Override // com.huawei.hms.ads.analysis.IDynamicLoaderAnalysis
        public void onLoaderException(String str, String str2, int i, String str3) {
            if (HiAd.getInstance(this.f6638a).isEnableUserInfo()) {
                new h(this.f6638a).a(str, str2, i, str3);
            }
        }

        public a(Context context) {
            this.f6638a = context.getApplicationContext();
        }
    }

    public static void a(Context context) {
        try {
            if (f6637a == null) {
                f6637a = new a(context);
            }
            DynamicLoaderAnalysis.getInstance().registerDynamicLoaderAnalysis("hiadsdk", f6637a);
        } catch (Throwable th) {
            ho.c("DyLoaderAnalysisUtil", "init analysis err: %s", th.getClass().getSimpleName());
        }
    }
}

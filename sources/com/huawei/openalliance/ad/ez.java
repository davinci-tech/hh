package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.common.inter.LoaderCommonInter;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;

/* loaded from: classes5.dex */
public class ez implements LoaderCommonInter {

    /* renamed from: a, reason: collision with root package name */
    private static ez f6857a;
    private static final byte[] c = new byte[0];
    private Context b;

    @Override // com.huawei.hms.ads.common.inter.LoaderCommonInter
    public void saveReportPoint(int i, Integer num, Integer num2) {
        new com.huawei.openalliance.ad.analysis.c(this.b).a(i, num, num2);
    }

    @Override // com.huawei.hms.ads.common.inter.LoaderCommonInter
    public boolean isTrustApp(String str, String str2) {
        return WhiteListPkgList.isTrustApp(this.b, str, str2);
    }

    private static ez b(Context context) {
        ez ezVar;
        synchronized (c) {
            if (f6857a == null) {
                f6857a = new ez(context);
            }
            ezVar = f6857a;
        }
        return ezVar;
    }

    public static ez a(Context context) {
        return b(context);
    }

    private ez(Context context) {
        this.b = context;
    }
}

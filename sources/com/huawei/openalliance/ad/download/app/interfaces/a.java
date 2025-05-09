package com.huawei.openalliance.ad.download.app.interfaces;

import android.content.Context;
import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.jk;
import com.huawei.openalliance.ad.views.AppDownloadButton;

/* loaded from: classes5.dex */
public interface a {
    int a(Context context, IAd iAd);

    int a(Context context, jk jkVar, IAd iAd);

    void a();

    void a(int i);

    void a(Context context, IAd iAd, int i);

    void a(AppDownloadButton.OnResolutionRequiredListener onResolutionRequiredListener);

    void a(Integer num);

    boolean a(Context context, IAd iAd, boolean z);

    int b(Context context, IAd iAd);

    void b();

    int c(Context context, IAd iAd);

    void d(Context context, IAd iAd);

    int e(Context context, IAd iAd);

    AppStatus f(Context context, IAd iAd);
}

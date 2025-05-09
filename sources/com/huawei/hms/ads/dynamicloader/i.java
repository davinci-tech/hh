package com.huawei.hms.ads.dynamicloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.huawei.hms.ads.uiengineloader.ad;
import java.io.File;

/* loaded from: classes4.dex */
public final class i extends e {
    @Override // android.content.ContextWrapper, android.content.Context
    public final String getPackageResourcePath() {
        return ((e) this).b;
    }

    @Override // com.huawei.hms.ads.dynamicloader.e, android.content.ContextWrapper, android.content.Context
    public final String getPackageName() {
        return getApplicationInfo().packageName;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final String getPackageCodePath() {
        return ((e) this).b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final File getFilesDir() {
        return new File(ad.c(((e) this).b));
    }

    @Override // com.huawei.hms.ads.dynamicloader.e, android.content.ContextWrapper, android.content.Context
    public final ApplicationInfo getApplicationInfo() {
        return ((e) this).f4310a.applicationInfo;
    }

    private void a() {
        ((e) this).f4310a.applicationInfo.processName = getBaseContext().getApplicationInfo().processName;
    }

    public i(Context context, String str, int i) {
        super(context, str, i);
        ((e) this).f4310a.applicationInfo.processName = getBaseContext().getApplicationInfo().processName;
    }
}

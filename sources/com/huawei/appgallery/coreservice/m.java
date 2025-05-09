package com.huawei.appgallery.coreservice;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.huawei.secure.android.common.intent.SafeBundle;

/* loaded from: classes2.dex */
public abstract class m {
    public static boolean a(PackageInfo packageInfo) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        return (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey("sdk_install_issupport") || new SafeBundle(bundle).getInt("sdk_install_issupport") != 1) ? false : true;
    }
}

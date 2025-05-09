package com.huawei.appgallery.marketinstallerservice.a;

import android.app.Activity;
import android.content.Context;
import com.huawei.appgallery.marketinstallerservice.api.BaseParamSpec;
import com.huawei.appgallery.marketinstallerservice.api.InstallCallback;
import com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec;
import com.huawei.appgallery.marketinstallerservice.b.a.a.a;

/* loaded from: classes3.dex */
public interface c extends a {
    void a(Activity activity, InstallParamSpec installParamSpec, InstallCallback installCallback);

    void a(Context context, BaseParamSpec baseParamSpec, InstallCallback installCallback);
}

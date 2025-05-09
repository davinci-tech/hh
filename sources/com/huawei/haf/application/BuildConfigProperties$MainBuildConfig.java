package com.huawei.haf.application;

import android.text.TextUtils;
import com.huawei.haf.common.base.BuildConfigReader;
import health.compact.a.ReflectionUtils;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public class BuildConfigProperties$MainBuildConfig extends BuildConfigReader {
    private volatile Class e;

    private BuildConfigProperties$MainBuildConfig() {
    }

    @Override // com.huawei.haf.common.base.BuildConfigReader
    public Class getBuildConfigClass() {
        if (this.e == null) {
            d(null);
        }
        return this.e;
    }

    public void d(String str) {
        if (this.e != null) {
            return;
        }
        synchronized (this) {
            if (this.e != null) {
                return;
            }
            Class a2 = !TextUtils.isEmpty(str) ? a(str) : null;
            if (a2 == null) {
                a2 = a(BaseApplication.d());
            }
            if (a2 == null) {
                a2 = a(BaseApplication.e().getClass().getPackage().getName());
            }
            this.e = a2;
            ReleaseLogUtil.b("HAF_BuildConfig", "main BuildConfig is: ", this.e);
        }
    }

    private static Class a(String str) {
        return ReflectionUtils.b(str + ".BuildConfig");
    }
}

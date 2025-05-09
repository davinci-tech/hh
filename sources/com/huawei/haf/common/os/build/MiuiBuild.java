package com.huawei.haf.common.os.build;

import android.text.TextUtils;
import health.compact.a.BaseBuild;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SystemProperties;

/* loaded from: classes.dex */
public final class MiuiBuild extends BaseBuild {
    public static final boolean c;
    public static final String e = SystemProperties.b("ro.miui.ui.version.code");

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f2103a = d();

    static {
        c = !TextUtils.isEmpty(r0);
    }

    private MiuiBuild() {
    }

    private static boolean d() {
        if (!c) {
            return false;
        }
        try {
            String str = e;
            boolean z = Integer.parseInt(str) >= 8;
            ReleaseLogUtil.b("HAF_SystemBuild", "MIUI_VERSION = ", str);
            return z;
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("HAF_SystemBuild", "MIUI_VERSION NumberFormat error: MIUI_VERSION = ", e);
            return false;
        }
    }
}

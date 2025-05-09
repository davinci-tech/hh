package com.huawei.phoneservice.faq.base.network;

import android.app.Application;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClientGlobalInstance;
import com.huawei.phoneservice.faq.base.util.i;
import defpackage.uib;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public final class SdkAppInfo {
    public static final c Companion = new c(null);
    private static volatile SdkAppInfo INSTANCE;

    @JvmStatic
    public static final SdkAppInfo initAppInfo(Application application) {
        return Companion.ccW_(application);
    }

    public static final class c {
        @JvmStatic
        public final SdkAppInfo ccW_(Application application) {
            if (SdkAppInfo.INSTANCE == null) {
                SdkAppInfo.INSTANCE = new SdkAppInfo(application, null);
                i.a(application);
            }
            return SdkAppInfo.INSTANCE;
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }

        private c() {
        }
    }

    public /* synthetic */ SdkAppInfo(Application application, uib uibVar) {
        this(application);
    }

    private SdkAppInfo(Application application) {
        HttpClientGlobalInstance.getInstance().init(application);
        com.huawei.phoneservice.faq.base.tracker.a.e(application, false);
    }
}

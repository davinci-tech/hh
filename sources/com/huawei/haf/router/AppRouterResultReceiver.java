package com.huawei.haf.router;

import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;

/* loaded from: classes.dex */
public class AppRouterResultReceiver implements NaviPluginCallback, NaviCallback {

    /* renamed from: a, reason: collision with root package name */
    private final NaviCallback f2138a;

    public AppRouterResultReceiver() {
        this(null);
    }

    private AppRouterResultReceiver(NaviCallback naviCallback) {
        this.f2138a = naviCallback;
    }

    public static void zl_(Context context, Bundle bundle) {
        Guidepost zx_ = Guidepost.zx_(bundle);
        if (zx_ == null) {
            LogUtil.a("HAF_AppRouterResult", "guidepost == null, bundle=", bundle);
            return;
        }
        String zn_ = zn_(bundle);
        if (!ProcessUtil.b().equals(zn_)) {
            LogUtil.c("HAF_AppRouterResult", "remote navigate, path=", zx_.m(), ", source=", zn_);
        }
        zx_.b(context, zm_(bundle));
    }

    public static Bundle zr_(Guidepost guidepost, NaviCallback naviCallback) {
        Bundle zo_ = zo_(guidepost);
        zp_(zo_, naviCallback);
        return zo_;
    }

    @Override // com.huawei.haf.router.NaviPluginCallback
    public void onPluginNone(Guidepost guidepost) {
        c(guidepost, 0, null);
    }

    @Override // com.huawei.haf.router.NaviPluginCallback
    public void onPluginFound(Guidepost guidepost, String str) {
        c(guidepost, 1, str);
    }

    @Override // com.huawei.haf.router.NaviPluginCallback
    public void onPluginLost(Guidepost guidepost, String str) {
        c(guidepost, 2, str);
    }

    @Override // com.huawei.haf.router.NaviCallback
    public void onFound(Guidepost guidepost) {
        NaviCallback naviCallback = this.f2138a;
        if (naviCallback != null) {
            naviCallback.onFound(guidepost);
        } else {
            a(guidepost, 10);
        }
    }

    @Override // com.huawei.haf.router.NaviCallback
    public void onLost(Guidepost guidepost) {
        NaviCallback naviCallback = this.f2138a;
        if (naviCallback != null) {
            naviCallback.onLost(guidepost);
        } else {
            a(guidepost, 11);
        }
    }

    @Override // com.huawei.haf.router.NaviCallback
    public void onArrival(Guidepost guidepost) {
        NaviCallback naviCallback = this.f2138a;
        if (naviCallback != null) {
            naviCallback.onArrival(guidepost);
        } else {
            a(guidepost, 12);
        }
    }

    @Override // com.huawei.haf.router.NaviCallback
    public void onInterrupt(Guidepost guidepost) {
        NaviCallback naviCallback = this.f2138a;
        if (naviCallback != null) {
            naviCallback.onInterrupt(guidepost);
        } else {
            a(guidepost, 13);
        }
    }

    protected void c(Guidepost guidepost, int i, String str) {
        LogUtil.c("HAF_AppRouterResult", "onPluginResult, result=", a(i), ", pluginName=", str);
    }

    protected void a(Guidepost guidepost, int i) {
        LogUtil.c("HAF_AppRouterResult", "onRouteResult, result=", c(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle zo_(Guidepost guidepost) {
        Bundle zA_ = Guidepost.zA_(guidepost);
        zq_(zA_);
        return zA_;
    }

    private static void zp_(Bundle bundle, NaviCallback naviCallback) {
        AppRouterResultReceiver appRouterResultReceiver;
        if (naviCallback instanceof AppRouterResultReceiver) {
            appRouterResultReceiver = (AppRouterResultReceiver) naviCallback;
        } else {
            appRouterResultReceiver = naviCallback != null ? new AppRouterResultReceiver(naviCallback) : null;
        }
        if (appRouterResultReceiver != null) {
            bundle.putParcelable("ResultReceiver", new MyResultReceiver(appRouterResultReceiver));
        }
    }

    private static NaviCallback zm_(Bundle bundle) {
        ResultReceiver resultReceiver = (ResultReceiver) bundle.getParcelable("ResultReceiver");
        if (resultReceiver != null) {
            return new MyNaviCallback(resultReceiver);
        }
        return null;
    }

    private static void zq_(Bundle bundle) {
        bundle.putString("runningProcess", ProcessUtil.b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zn_(Bundle bundle) {
        return bundle.getString("runningProcess");
    }

    static class MyResultReceiver extends ResultReceiver {
        private final AppRouterResultReceiver mResultReceiver;

        MyResultReceiver(AppRouterResultReceiver appRouterResultReceiver) {
            super(null);
            this.mResultReceiver = appRouterResultReceiver;
        }

        @Override // android.os.ResultReceiver
        protected void onReceiveResult(int i, Bundle bundle) {
            Guidepost zx_ = Guidepost.zx_(bundle);
            String c = AppRouterResultReceiver.c(i);
            if (zx_ == null) {
                LogUtil.a("HAF_AppRouterResult", "guidepost == null, bundle=", bundle, ", result=", c);
            }
            LogUtil.c("HAF_AppRouterResult", "onReceiveResult, path=", zx_.m(), ", result=", c, ", source=", AppRouterResultReceiver.zn_(bundle));
            switch (i) {
                case 10:
                    this.mResultReceiver.onFound(zx_);
                    break;
                case 11:
                    this.mResultReceiver.onLost(zx_);
                    break;
                case 12:
                    this.mResultReceiver.onArrival(zx_);
                    break;
                case 13:
                    this.mResultReceiver.onInterrupt(zx_);
                    break;
            }
        }
    }

    static class MyNaviCallback implements NaviCallback {
        private final ResultReceiver e;

        MyNaviCallback(ResultReceiver resultReceiver) {
            this.e = resultReceiver;
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onFound(Guidepost guidepost) {
            this.e.send(10, AppRouterResultReceiver.zo_(guidepost));
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onLost(Guidepost guidepost) {
            this.e.send(11, AppRouterResultReceiver.zo_(guidepost));
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onArrival(Guidepost guidepost) {
            this.e.send(12, AppRouterResultReceiver.zo_(guidepost));
        }

        @Override // com.huawei.haf.router.NaviCallback
        public void onInterrupt(Guidepost guidepost) {
            this.e.send(13, AppRouterResultReceiver.zo_(guidepost));
        }
    }

    protected static String c(int i) {
        switch (i) {
            case 10:
                return "FOUND";
            case 11:
                return "LOST";
            case 12:
                return "ARRIVAL";
            case 13:
                return "INTERRUPT";
            default:
                return "NONE";
        }
    }

    protected static String a(int i) {
        return i != 1 ? i != 2 ? "NONE" : "LOST" : "FOUND";
    }
}

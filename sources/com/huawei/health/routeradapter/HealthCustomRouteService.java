package com.huawei.health.routeradapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.router.facade.service.ExtendRouteService;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import com.huawei.operation.utils.Constants;
import com.huawei.operation.utils.WebViewHelp;
import defpackage.fbi;
import defpackage.gso;
import defpackage.kwx;
import defpackage.lsp;
import defpackage.oxa;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.Utils;
import java.net.URISyntaxException;
import java.util.Map;

/* loaded from: classes.dex */
public final class HealthCustomRouteService implements ExtendRouteService {
    private static boolean e = false;

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.ExtendRouteService
    public ExtendRouteService.RouteInfo tryExtractRouteInfo(Guidepost guidepost) {
        char c2;
        if (guidepost.zN_() == null) {
            return null;
        }
        String m = guidepost.m();
        m.hashCode();
        int hashCode = m.hashCode();
        if (hashCode == -1405805462) {
            if (m.equals("/router/startService")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -354535048) {
            if (hashCode == 931197178 && m.equals("/router/startPage")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (m.equals("/HuaweiHealth/startComponent")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            return b(guidepost);
        }
        if (c2 == 1) {
            return c(guidepost);
        }
        if (c2 != 2) {
            return null;
        }
        return e(guidepost);
    }

    @Override // com.huawei.haf.router.facade.service.ExtendRouteService
    public boolean handleRunningProcess(Guidepost guidepost, NaviCallback naviCallback, String str) {
        if (BaseApplication.d().equals(str)) {
            a(guidepost, naviCallback);
            LogUtil.c("Login_CustomRoute", "startMainProcessRunDeepLink, path=", guidepost.m());
            return false;
        }
        LogUtil.a("Login_CustomRoute", "handleRunningProcess, process=", str, ", path=", guidepost.m());
        naviCallback.onLost(guidepost);
        return false;
    }

    @Override // com.huawei.haf.router.facade.service.ExtendRouteService
    public boolean handleStartComponent(Guidepost guidepost, NaviCallback naviCallback, String str) {
        char c2;
        if (guidepost.zN_() == null) {
            return true;
        }
        String m = guidepost.m();
        m.hashCode();
        int hashCode = m.hashCode();
        if (hashCode == -1405805462) {
            if (m.equals("/router/startService")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -354535048) {
            if (hashCode == 931197178 && m.equals("/router/startPage")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (m.equals("/HuaweiHealth/startComponent")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            return a(guidepost, naviCallback, str);
        }
        if (c2 == 1) {
            return b(guidepost, naviCallback, str);
        }
        if (c2 != 2) {
            return true;
        }
        return e(guidepost, naviCallback, str);
    }

    @Override // com.huawei.haf.router.facade.service.ExtendRouteService
    public boolean handleRoute(Guidepost guidepost, NaviCallback naviCallback, String str) {
        if (!ProcessUtil.d() || (b(guidepost.a(), guidepost, naviCallback) && d(guidepost.a(), guidepost, naviCallback, str))) {
            return b(guidepost.a(), guidepost);
        }
        return false;
    }

    private String b(Guidepost guidepost, String str) {
        String string = guidepost.zB_().getString(str);
        return (!TextUtils.isEmpty(string) || guidepost.zN_() == null) ? string : guidepost.zN_().getQueryParameter(str);
    }

    private void a(Guidepost guidepost, NaviCallback naviCallback) {
        Context e2 = BaseApplication.e();
        Intent intent = new Intent("com.huawei.health.track.broadcast");
        intent.setPackage(e2.getPackageName());
        intent.putExtra("command_type", "PULL_RUN_DEEPLINK");
        AppRouterUtils.zw_(intent, guidepost, naviCallback);
        e2.sendBroadcast(intent, SecurityConstant.d);
    }

    private boolean e(Guidepost guidepost, NaviCallback naviCallback, String str) {
        Intent avt_ = avt_(guidepost, true);
        if (avt_ == null) {
            naviCallback.onLost(guidepost);
            return false;
        }
        Context a2 = guidepost.a();
        ResolveInfo resolveActivity = a2.getPackageManager().resolveActivity(avt_, 131072);
        if (!avp_(guidepost, resolveActivity)) {
            naviCallback.onLost(guidepost);
            return false;
        }
        naviCallback.onFound(guidepost);
        avt_.setClassName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
        if (!TextUtils.isEmpty(str)) {
            avt_.putExtra("moduleName", str);
        }
        new e(this, a2, avt_).d();
        LogUtil.c("Login_CustomRoute", "startActivity success, info=", resolveActivity.activityInfo);
        naviCallback.onArrival(guidepost);
        return false;
    }

    private boolean avp_(Guidepost guidepost, ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.activityInfo == null) {
            LogUtil.a("Login_CustomRoute", "startActivity fail, uri=", guidepost.zN_());
            return false;
        }
        if (!resolveInfo.activityInfo.exported) {
            LogUtil.a("Login_CustomRoute", "startActivity fail, not exported, info=", resolveInfo.activityInfo);
            return false;
        }
        if (!resolveInfo.activityInfo.enabled) {
            LogUtil.a("Login_CustomRoute", "startActivity fail, not enabled, info=", resolveInfo.activityInfo);
            return false;
        }
        if (TextUtils.isEmpty(resolveInfo.activityInfo.permission)) {
            return true;
        }
        LogUtil.a("Login_CustomRoute", "startActivity fail, has permission, info=", resolveInfo.activityInfo);
        return false;
    }

    private boolean a(Guidepost guidepost, NaviCallback naviCallback, String str) {
        Intent avt_ = avt_(guidepost, false);
        if (avt_ == null) {
            naviCallback.onLost(guidepost);
            return false;
        }
        Context a2 = guidepost.a();
        ResolveInfo resolveService = a2.getPackageManager().resolveService(avt_, 131072);
        if (!avr_(guidepost, resolveService)) {
            naviCallback.onLost(guidepost);
            return false;
        }
        naviCallback.onFound(guidepost);
        avt_.setClassName(resolveService.serviceInfo.packageName, resolveService.serviceInfo.name);
        avs_(a2, avt_, str, "startService", resolveService.serviceInfo.toString());
        return false;
    }

    private boolean avr_(Guidepost guidepost, ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.serviceInfo == null) {
            LogUtil.a("Login_CustomRoute", "startService fail, uri=", guidepost.zN_());
            return false;
        }
        if (!resolveInfo.serviceInfo.exported) {
            LogUtil.a("Login_CustomRoute", "startService fail, not exported, info=", resolveInfo.serviceInfo);
            return false;
        }
        if (!resolveInfo.serviceInfo.enabled) {
            LogUtil.a("Login_CustomRoute", "startService fail, not enabled, info=", resolveInfo.serviceInfo);
            return false;
        }
        if (TextUtils.isEmpty(resolveInfo.serviceInfo.permission)) {
            return true;
        }
        LogUtil.a("Login_CustomRoute", "startService fail, has permission, info=", resolveInfo.serviceInfo);
        return false;
    }

    private boolean b(Guidepost guidepost, NaviCallback naviCallback, String str) {
        Map<String, String> zv_ = AppRouterUtils.zv_(guidepost.zN_());
        String str2 = zv_.get("type");
        if (!"activity".equals(str2) && !"service".equals(str2)) {
            LogUtil.a("Login_CustomRoute", "startComponent type error, uri=", guidepost.zN_());
            naviCallback.onLost(guidepost);
            return false;
        }
        boolean parseBoolean = Boolean.parseBoolean(zv_.get("onlyPlugin"));
        if (!parseBoolean) {
            LogUtil.a("Login_CustomRoute", "startComponent need plugin, uri=", guidepost.zN_());
            naviCallback.onLost(guidepost);
            return false;
        }
        String str3 = zv_.get("className");
        if (TextUtils.isEmpty(str3)) {
            LogUtil.a("Login_CustomRoute", "startComponent class name error, uri=", guidepost.zN_());
            naviCallback.onLost(guidepost);
            return false;
        }
        if (TextUtils.isEmpty(AppBundle.c().getModuleForComponent(str3)) && parseBoolean) {
            LogUtil.a("Login_CustomRoute", "startComponent not plugin, uri=", guidepost.zN_());
            naviCallback.onLost(guidepost);
            return false;
        }
        Intent intent = new Intent();
        String str4 = zv_.get("package");
        Context a2 = guidepost.a();
        if (TextUtils.isEmpty(str4)) {
            str4 = a2.getPackageName();
        }
        intent.setClassName(str4, str3);
        if (!avq_(guidepost, intent, str4, str2)) {
            LogUtil.c("Login_CustomRoute", "startComponent check fail, info=", guidepost.zN_());
            naviCallback.onLost(guidepost);
            return false;
        }
        naviCallback.onFound(guidepost);
        if ("activity".equals(str2)) {
            new e(this, a2, intent).d();
            LogUtil.c("Login_CustomRoute", "startComponent success, info=", guidepost.zN_());
            naviCallback.onArrival(guidepost);
        } else {
            avs_(a2, intent, str, "startComponent", guidepost.zN_().toString());
        }
        return false;
    }

    private boolean avq_(Guidepost guidepost, Intent intent, String str, String str2) {
        Context a2 = guidepost.a();
        if (a2.getPackageName().equals(str)) {
            return true;
        }
        if ("activity".equals(str2)) {
            return avp_(guidepost, a2.getPackageManager().resolveActivity(intent, 131072));
        }
        return avr_(guidepost, a2.getPackageManager().resolveService(intent, 131072));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void avs_(Context context, Intent intent, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            try {
                context.startService(intent);
                LogUtil.c("Login_CustomRoute", str2, " success, info=", str3);
                return;
            } catch (IllegalStateException | SecurityException e2) {
                LogUtil.c("Login_CustomRoute", str2, " error. ex=", LogUtil.a(e2));
                return;
            }
        }
        intent.putExtra("moduleName", str);
        new e(context, intent, str2, str3).d();
    }

    private ExtendRouteService.RouteInfo e(Guidepost guidepost) {
        guidepost.zB_().remove("GpIt7wO5vki3");
        Intent avt_ = avt_(guidepost, true);
        if (avt_ == null) {
            return null;
        }
        guidepost.zB_().putParcelable("GpIt7wO5vki3", avt_);
        ResolveInfo resolveActivity = guidepost.a().getPackageManager().resolveActivity(avt_, 131072);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            return null;
        }
        c cVar = new c();
        cVar.b = AppBundle.c().getModuleForComponent(resolveActivity.activityInfo.name);
        if (TextUtils.isEmpty(cVar.b)) {
            cVar.b = b(guidepost, "moduleOrGroup");
        }
        cVar.c = AppRouterUtils.a(b(guidepost, "pathExtras"));
        cVar.c |= 131072;
        cVar.d = resolveActivity.activityInfo.processName;
        return cVar;
    }

    private ExtendRouteService.RouteInfo b(Guidepost guidepost) {
        guidepost.zB_().remove("GpIt7wO5vki3");
        Intent avt_ = avt_(guidepost, false);
        if (avt_ == null) {
            return null;
        }
        guidepost.zB_().putParcelable("GpIt7wO5vki3", avt_);
        ResolveInfo resolveService = guidepost.a().getPackageManager().resolveService(avt_, 131072);
        if (resolveService == null || resolveService.serviceInfo == null) {
            return null;
        }
        c cVar = new c();
        cVar.b = AppBundle.c().getModuleForComponent(resolveService.serviceInfo.name);
        if (TextUtils.isEmpty(cVar.b)) {
            cVar.b = b(guidepost, "moduleOrGroup");
        }
        cVar.c = AppRouterUtils.a(b(guidepost, "pathExtras"));
        cVar.c |= 131072;
        cVar.d = resolveService.serviceInfo.processName;
        return cVar;
    }

    private ExtendRouteService.RouteInfo c(Guidepost guidepost) {
        String b = b(guidepost, "className");
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        c cVar = new c();
        cVar.b = AppBundle.c().getModuleForComponent(b);
        cVar.c |= 131072;
        return cVar;
    }

    private Intent avt_(Guidepost guidepost, boolean z) {
        Intent intent = (Intent) guidepost.zB_().getParcelable("GpIt7wO5vki3");
        if (intent != null) {
            return intent;
        }
        Uri zN_ = guidepost.zN_();
        String queryParameter = zN_.getQueryParameter(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK);
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                intent = Intent.parseUri(queryParameter, 1);
                intent.removeFlags(1);
                intent.removeFlags(2);
                if (z) {
                    intent.addCategory("android.intent.category.BROWSABLE");
                }
                intent.setComponent(null);
                intent.setSelector(null);
            } catch (URISyntaxException e2) {
                LogUtil.a("Login_CustomRoute", "Intent parseUri error. ex=", LogUtil.a(e2), ", uri=", zN_.toString());
            }
            return intent;
        }
        String queryParameter2 = zN_.getQueryParameter("action");
        if (TextUtils.isEmpty(queryParameter2)) {
            LogUtil.a("Login_CustomRoute", "Intent action error. uri=", zN_.toString());
            return intent;
        }
        String queryParameter3 = zN_.getQueryParameter("package");
        Intent intent2 = new Intent(queryParameter2);
        if (TextUtils.isEmpty(queryParameter3)) {
            queryParameter3 = BaseApplication.d();
        }
        intent2.setPackage(queryParameter3);
        intent2.putExtras(guidepost.zB_());
        intent2.putExtra("originalUri", zN_.toString());
        return intent2;
    }

    private boolean b(Context context, Guidepost guidepost, NaviCallback naviCallback) {
        int f = guidepost.f();
        if (f == 1) {
            if (gso.e().getAdapter() == null) {
                gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(BaseApplication.e()));
            }
            return true;
        }
        if (f == 2 || f != 3) {
            return true;
        }
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            return false;
        }
        LogUtil.c("Login_CustomRoute", "uri is ", zN_.toString());
        String queryParameter = zN_.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        String queryParameter2 = zN_.getQueryParameter("address");
        LogUtil.c("Login_CustomRoute", "uri is ", zN_.toString(), " from is ", queryParameter, " address is ", queryParameter2);
        if (DeviceTypeConsts.WATCH.equals(queryParameter) && !e && HealthRouterExtras.c().contains(fbi.a(queryParameter2)) && !MainInteractors.a()) {
            LogUtil.c("Login_CustomRoute", "coldStart and sync");
            e = true;
            oxa.a().b(2);
        }
        return true;
    }

    private boolean d(Context context, Guidepost guidepost, NaviCallback naviCallback, String str) {
        boolean d = fbi.d(context);
        boolean c2 = fbi.c(guidepost);
        if (!d && !TextUtils.isEmpty(str) && !a(context, str)) {
            fbi.b(guidepost, naviCallback, c2);
            return false;
        }
        if (d && fbi.a(context)) {
            if (Utils.o() && fbi.b(guidepost)) {
                naviCallback.onFound(guidepost);
                fbi.c(context);
                naviCallback.onInterrupt(guidepost);
                LogUtil.c("Login_CustomRoute", "oversea user not used, path=", guidepost.m());
                return false;
            }
        } else if (c2) {
            fbi.b(guidepost, naviCallback, c2);
            return false;
        }
        if (!fbi.d(guidepost)) {
            return true;
        }
        fbi.b(guidepost, naviCallback, c2);
        return false;
    }

    private boolean b(Context context, Guidepost guidepost) {
        String m = guidepost.m();
        m.hashCode();
        if (!m.equals("/home/main")) {
            return true;
        }
        if (kwx.c()) {
            return false;
        }
        d(context, guidepost);
        return true;
    }

    private void d(Context context, Guidepost guidepost) {
        String str;
        guidepost.a(536870912);
        if (!(context instanceof Activity)) {
            guidepost.b(268435456);
        } else {
            guidepost.b(AppRouterExtras.COLDSTART);
        }
        Uri zN_ = guidepost.zN_();
        if (zN_ != null) {
            str = zN_.getQueryParameter(Constants.HOME_TAB_NAME);
            if (!TextUtils.isEmpty(str)) {
                guidepost.e(Constants.HOME_TAB_NAME, str);
            }
        } else {
            str = null;
        }
        LogUtil.c("Login_CustomRoute", "start path=", guidepost.m(), ", tabName=", str, ", flags=0x", Integer.toHexString(guidepost.i()));
    }

    private static boolean a(Context context, String str) {
        return AppBundle.c().isExistLocalModule(str) || AppBundle.c().isBuiltInModule(context, str);
    }

    @Override // com.huawei.haf.router.NaviPluginCallback
    public void onPluginNone(Guidepost guidepost) {
        LogUtil.d("Login_CustomRoute", "onPluginNone");
    }

    @Override // com.huawei.haf.router.NaviPluginCallback
    public void onPluginFound(Guidepost guidepost, String str) {
        lsp.d().e(str);
        LogUtil.c("Login_CustomRoute", "onPluginFound, pluginName=", str);
    }

    @Override // com.huawei.haf.router.NaviPluginCallback
    public void onPluginLost(Guidepost guidepost, String str) {
        if (AppRouterUtils.a(guidepost)) {
            lsp.d().d(str);
        }
        LogUtil.c("Login_CustomRoute", "onPluginLost, pluginName=", str);
    }

    /* loaded from: classes7.dex */
    class e implements Runnable, AppBundleLauncher.InstallCallback {

        /* renamed from: a, reason: collision with root package name */
        private final Intent f2962a;
        private final Context b;
        private final String c;
        private final String d;

        e(HealthCustomRouteService healthCustomRouteService, Context context, Intent intent) {
            this(context, intent, null, null);
        }

        e(Context context, Intent intent, String str, String str2) {
            this.b = context;
            this.f2962a = intent;
            this.d = str;
            this.c = str2;
        }

        void d() {
            HandlerExecutor.e(this);
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            if (TextUtils.isEmpty(this.d)) {
                return true;
            }
            HealthCustomRouteService.this.avs_(context, intent, null, this.d, this.c);
            return false;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppBundle.e().launchActivity(this.b, this.f2962a, this);
        }
    }

    /* loaded from: classes7.dex */
    static class c implements ExtendRouteService.RouteInfo {
        String b;
        int c;
        String d;

        private c() {
        }

        @Override // com.huawei.haf.router.facade.service.ExtendRouteService.RouteInfo
        public String getModuleOrGroup() {
            return this.b;
        }

        @Override // com.huawei.haf.router.facade.service.ExtendRouteService.RouteInfo
        public int getPathExtras() {
            return this.c;
        }

        @Override // com.huawei.haf.router.facade.service.ExtendRouteService.RouteInfo
        public String getRunningProcess() {
            return this.d;
        }
    }
}

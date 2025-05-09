package com.huawei.ui.homehealth.qrcode;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwadpaterhealthmgr.PluginAchieveAdapterImpl;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import defpackage.mcv;
import defpackage.nrh;
import defpackage.opf;
import health.compact.a.GRSManager;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class QrCodePretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        if (guidepost.zN_() == null) {
            LogUtil.a("QrCodePretreatmentService", "uri == null");
            return false;
        }
        if (context instanceof Activity) {
            ddo_((Activity) context, guidepost.zB_());
        }
        if (guidepost.zB_().getBoolean("needValidateUrl", true)) {
            guidepost.zB_().putBoolean("needValidateUrl", false);
            d(context, guidepost);
            return false;
        }
        return c(context, guidepost);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final Context context, final Guidepost guidepost) {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.qrcode.QrCodePretreatmentService.3
                @Override // java.lang.Runnable
                public void run() {
                    QrCodePretreatmentService.this.d(context, guidepost);
                }
            });
            return;
        }
        GRSManager a2 = GRSManager.a(context);
        String commonCountryCode = a2.getCommonCountryCode();
        final String noCheckUrl = a2.getNoCheckUrl("domainMessagecenterHicloud", commonCountryCode);
        final ArrayList arrayList = new ArrayList(5);
        arrayList.add("huaweischeme://healthapp");
        arrayList.add(a2.getNoCheckUrl("domainUrlCloudHuawei", commonCountryCode));
        arrayList.add(noCheckUrl);
        arrayList.add(a2.getNoCheckUrl("domainUrlDreCloudHuawei", commonCountryCode));
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.qrcode.QrCodePretreatmentService.1
            @Override // java.lang.Runnable
            public void run() {
                if (QrCodePretreatmentService.this.ddp_(guidepost.zN_(), arrayList, noCheckUrl)) {
                    QrCodePretreatmentService.this.c(context, guidepost);
                    return;
                }
                if (BaseApplication.c("com.huawei.health.MainActivity") == -1) {
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()));
                }
                LogUtil.a("QrCodePretreatmentService", "qrcode format error");
                nrh.b(context, R.string.IDS_device_wifi_my_qrcode_error_qrcode);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ddp_(Uri uri, List<String> list, String str) {
        String str2 = uri.getScheme() + "://" + uri.getHost();
        if (!list.contains(str2)) {
            LogUtil.a("QrCodePretreatmentService", "wrong uri: not in the right uri list");
            return false;
        }
        if (uri.getQuery() == null) {
            if (str2.equals(str)) {
                return true;
            }
            LogUtil.a("QrCodePretreatmentService", "wrong uri with null parameter");
            return false;
        }
        if (!str2.equals(str)) {
            return true;
        }
        LogUtil.a("QrCodePretreatmentService", "wrong uri for download apps ");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Context context, Guidepost guidepost) {
        Uri zN_ = guidepost.zN_();
        if (zN_ == null) {
            return false;
        }
        String query = zN_.getQuery();
        LogUtil.c("QrCodePretreatmentService", " handleQrCode queryParameter:", query);
        if (query == null) {
            PluginAchieveAdapter adapter = mcv.d(context).getAdapter();
            if (adapter == null) {
                mcv.d(context).setAdapter(new PluginAchieveAdapterImpl());
                adapter = mcv.d(context).getAdapter();
            }
            adapter.gotoAllDeviceBinding(context);
            return false;
        }
        String queryParameter = zN_.getQueryParameter(VideoPlayFlag.PLAY_IN_ALL);
        if ("deeplink".equals(queryParameter) || "dl".equals(queryParameter)) {
            opf.deT_(context, zN_, query);
            return false;
        }
        AppRouter.b("/HomeHealth/QrCodeSchemeActivity").e("schemeQrCode", query).e("isAddSourceInfo", true).c(context);
        return false;
    }

    private void ddo_(Activity activity, Bundle bundle) {
        Uri referrer;
        if (bundle.containsKey("src") || (referrer = activity.getReferrer()) == null) {
            return;
        }
        bundle.putString("src", referrer.getHost());
    }
}

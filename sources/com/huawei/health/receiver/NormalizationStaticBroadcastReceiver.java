package com.huawei.health.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.MainActivity;
import com.huawei.health.developerkit.TrackDeveloperKitProxy;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.thirdpartservice.runtasticapi.RuntasticProviderApi;
import defpackage.cun;
import defpackage.dss;
import defpackage.gnm;
import defpackage.gso;
import defpackage.gve;
import defpackage.lds;
import defpackage.ppy;
import defpackage.qkc;
import defpackage.siq;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes8.dex */
public class NormalizationStaticBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        if (intent == null || context == null) {
            LogUtil.h("Track_NormalizationStaticBroadcastReceiver", "intent == null || context == null");
            return;
        }
        String action = intent.getAction();
        if (!"com.huawei.health.track.broadcast".equals(action)) {
            LogUtil.h("Track_NormalizationStaticBroadcastReceiver", "action is not equals TRACK_BROADCAST");
            return;
        }
        try {
            str = intent.getStringExtra("command_type");
        } catch (Exception e) {
            LogUtil.b("Track_NormalizationStaticBroadcastReceiver", LogAnonymous.b((Throwable) e));
            str = null;
        }
        ReleaseLogUtil.e("Track_NormalizationStaticBroadcastReceiver", "onReceive = ", action, " msg = ", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        aun_(context, str, intent);
    }

    private boolean c(Context context) {
        try {
        } catch (NullPointerException unused) {
            ReleaseLogUtil.c("Track_NormalizationStaticBroadcastReceiver", "isStepClassOne exception! ");
        }
        return dss.c(context.getApplicationContext()).d().c() == 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void aun_(Context context, String str, Intent intent) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1552264064:
                if (str.equals("com.huawei.health.background.start.sport")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1179621959:
                if (str.equals("com.huawei.health.track.exit_running")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1062370620:
                if (str.equals("com.huawei.health.track.running")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -745318545:
                if (str.equals("com.huawei.health.AUTO_TRACK_END")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -403738590:
                if (str.equals("com.huawei.health.track.fastwalking")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -187930894:
                if (str.equals("com.huawei.track.restart")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            gso.e().init(context);
            gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(context.getApplicationContext()));
            gve.aUu_(new TrackDeveloperKitProxy());
            return;
        }
        if (c == 1) {
            gso.e().a(context);
            return;
        }
        if (c == 2) {
            auq_(context, intent.getBundleExtra("paramsBundle"));
            return;
        }
        if (c == 3) {
            gso.e();
            gso.a(true);
            return;
        }
        if (c == 4) {
            aup_(context, intent.getBundleExtra("paramsBundle"));
            return;
        }
        if (c == 5) {
            if (gso.e().m()) {
                return;
            }
            Intent intent2 = new Intent(context, (Class<?>) MainActivity.class);
            intent2.addFlags(268435456);
            intent2.putExtra("mLaunchSource", 4);
            gnm.aPB_(context, intent2);
            return;
        }
        auo_(context, str, intent);
    }

    private void auq_(Context context, Bundle bundle) {
        if (gso.e().k()) {
            LogUtil.a("Track_NormalizationStaticBroadcastReceiver", "sport already started,fast running");
            return;
        }
        gso.e().init(context);
        gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(context.getApplicationContext()));
        gso.e().aTr_(258, c(context), true, bundle);
    }

    private void aup_(Context context, Bundle bundle) {
        if (gso.e().k()) {
            LogUtil.a("Track_NormalizationStaticBroadcastReceiver", "sport already started,fast walking");
            return;
        }
        gso.e().init(context);
        gso.e().setAdapter(PluginHealthTrackAdapterImpl.getInstance(context.getApplicationContext()));
        gso.e().aTr_(257, c(context), true, bundle);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void auo_(Context context, String str, Intent intent) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -642194892:
                if (str.equals("MY_PACKAGE_REPLACED_UPDATE_MODULES")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -385697666:
                if (str.equals(RuntasticProviderApi.ACTION_RUNTASTIC_SYNC_DATA)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -201293475:
                if (str.equals("com.huawei.health.sync.coresleep")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 115482100:
                if (str.equals("PULL_RUN_DEEPLINK")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 828307173:
                if (str.equals("SYNC_FITNESS_DATA")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2036416040:
                if (str.equals("REVERSE_LINKAGE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            e(context, true);
            return;
        }
        if (c == 1) {
            LogUtil.a("Track_NormalizationStaticBroadcastReceiver", RuntasticProviderApi.ACTION_RUNTASTIC_SYNC_DATA);
            siq.a().e();
            return;
        }
        if (c != 2) {
            if (c == 3) {
                AppRouterUtils.zu_(context, intent);
                return;
            }
            if (c == 4) {
                cun.c().registerDataCallback(null, DataCallbackType.SUGGESTION_AIDL, "Track_NormalizationStaticBroadcastReceiver");
                return;
            } else {
                if (c != 5) {
                    return;
                }
                LogUtil.a("Track_NormalizationStaticBroadcastReceiver", "reverse linkage");
                lds.c().handleRemoteException((ContentValues) intent.getParcelableExtra("key_broadcast_info"));
                return;
            }
        }
        LogUtil.a("Track_NormalizationStaticBroadcastReceiver", "BROADCAST_COMMAND_TYPE_SYNC_CORE_SLEEP Start!");
        long longExtra = intent.getLongExtra("fallAsleepTime", 0L);
        long longExtra2 = intent.getLongExtra("wakeTime", 0L);
        int intExtra = intent.getIntExtra(JsUtil.SCORE, 0);
        if (longExtra <= 0 || longExtra2 <= 0) {
            return;
        }
        LogUtil.a("Track_NormalizationStaticBroadcastReceiver", "handleSleepEvent Start!");
        ppy.a(longExtra, longExtra2, intExtra);
        qkc.e(longExtra, longExtra2);
        LogUtil.a("Track_NormalizationStaticBroadcastReceiver", "handleSleepEvent Stop!");
    }

    private void e(Context context, boolean z) {
        if (z) {
            AppBundle.c().getSetting().setAllowDownloadModule(context, "PluginHiAiEngine", true);
        }
    }
}

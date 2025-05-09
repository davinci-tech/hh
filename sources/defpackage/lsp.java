package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogUtil;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class lsp {
    private static lsp c;
    private static HashSet<String> d = new HashSet<>();

    private lsp() {
        d.add("PluginWearAbility");
    }

    public static lsp d() {
        lsp lspVar;
        synchronized (lsp.class) {
            if (c == null) {
                c = new lsp();
            }
            lspVar = c;
        }
        return lspVar;
    }

    public boolean a(String str) {
        return (TextUtils.isEmpty(str) || !AppBundle.c().isBundleModule(str) || AppBundle.c().isExistLocalModule(str) || AppBundle.c().isBuiltInModule(BaseApplication.e(), str)) ? false : true;
    }

    public String e() {
        Iterator<String> it = d.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if ("download_plugin".equals(KeyValDbManager.b(BaseApplication.e()).e(next))) {
                boolean a2 = a(next);
                LogUtil.c("PremisesServiceManager", "queryIsShowPluginDownload name:", next, ", isDownload:", Boolean.valueOf(a2));
                if (a2) {
                    return next;
                }
                e(next);
            }
        }
        return "";
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PremisesServiceManager", "resetPluginDownloadFlag moduleName isEmpty");
            return;
        }
        Context e = BaseApplication.e();
        if ("".equals(KeyValDbManager.b(e).e(str))) {
            return;
        }
        KeyValDbManager.b(e).e(str, "");
        NotificationManagerCompat.from(e).cancel(str.hashCode());
        LogUtil.c("PremisesServiceManager", "leave resetPluginDownloadFlag");
    }

    public String c(String str) {
        String string;
        Context e = BaseApplication.e();
        str.hashCode();
        if (str.equals("PluginHiAiEngine")) {
            string = BaseApplication.e().getResources().getString(R.string.IDS_hi_ai_voice_title, nsn.b(e, AppBundle.c().getModuleZipSize(e, "PluginHiAiEngine")));
        } else if (str.equals("PluginWearAbility")) {
            string = e.getResources().getString(R.string._2130845529_res_0x7f021f59);
        } else {
            LogUtil.a("PremisesServiceManager", "getDownloadTitle moduleName:", str);
            string = "";
        }
        LogUtil.c("PremisesServiceManager", "getDownloadTitle title:", string);
        return string;
    }

    public void d(Context context, final String str) {
        LogUtil.c("PremisesServiceManager", "enter downloadPlugin");
        if (!AppBundle.c().isBundleModule(str)) {
            LogUtil.c("PremisesServiceManager", "moduleName is not bundleModule");
            e(str);
            nrh.e(context, R.string._2130843721_res_0x7f021849);
        } else {
            Intent intent = new Intent();
            intent.putExtra("moduleName", str);
            AppBundle.e().launchActivity(context, intent, new AppBundleLauncher.InstallCallback() { // from class: lsp.1
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public boolean call(Context context2, Intent intent2) {
                    lsp.this.e(str);
                    nrh.e(context2, R.string._2130843721_res_0x7f021849);
                    LogUtil.c("PremisesServiceManager", "leave downloadPlugin");
                    return false;
                }
            });
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PremisesServiceManager", "saveDownloadPluginFlag moduleName is empty");
        } else {
            KeyValDbManager.b(BaseApplication.e()).e(str, "download_plugin");
            b(str);
        }
    }

    private void b(String str) {
        Context e = BaseApplication.e();
        RemoteViews remoteViews = new RemoteViews(e.getPackageName(), R.layout.download_plugin_notify_layout);
        remoteViews.setTextViewText(R.id.des_textview, c(str));
        remoteViews.setTextViewText(R.id.download_textview, e.getResources().getString(R.string.IDS_bundle_download_button));
        int hashCode = str.hashCode();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.d(), "com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity"));
        intent.putExtra(CommonUtil.DETAIL_URI, "messagecenter://download_plugin");
        intent.putExtra("msgId", "download_plugin");
        intent.putExtra("moduleName", str);
        PendingIntent activity = PendingIntent.getActivity(e, hashCode, intent, 201326592);
        LogUtil.c("PremisesServiceManager", "sendNotify.");
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
        xf_.setAutoCancel(true);
        if (EnvironmentInfo.j()) {
            LogUtil.c("PremisesServiceManager", "sendNotify isHarmony4AndLater.");
            xf_.setStyle(new Notification.BigTextStyle());
            xf_.setContentTitle(nsf.h(R.string.IDS_scan_device));
            xf_.setContentText(c(str));
            xf_.addAction(new Notification.Action.Builder((Icon) null, nsf.h(R.string.IDS_bundle_download_button), activity).build());
        } else {
            xf_.setCustomContentView(remoteViews);
            xf_.setStyle(new Notification.DecoratedCustomViewStyle());
        }
        xf_.setContentIntent(activity);
        xf_.setPriority(0);
        jdh.c().xh_(hashCode, xf_.build());
    }
}

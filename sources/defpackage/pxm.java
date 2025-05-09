package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.main.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes6.dex */
public class pxm {
    public static void duZ_(final Activity activity, final String str) {
        HandlerExecutor.e(new Runnable() { // from class: pxo
            @Override // java.lang.Runnable
            public final void run() {
                pxm.dvi_(activity, str);
            }
        });
    }

    static /* synthetic */ void dvi_(Activity activity, final String str) {
        final Context e = BaseApplication.e();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R$string.IDS_active_task_dialog_subscribe_title).d(R$string.IDS_active_task_dialog_subscribe_text).cyU_(R$string.IDS_active_task_dialog_subscribe_button, new View.OnClickListener() { // from class: pxs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pxm.dvg_(e, str, view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: pxt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pxm.dvh_(view);
            }
        });
        builder.a().show();
        pxp.i(0);
    }

    static /* synthetic */ void dvg_(final Context context, String str, View view) {
        phn.e(new ResultCallback<phq>() { // from class: pxm.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(phq phqVar) {
                ReleaseLogUtil.e("SCUI_ActiveTaskUtils", "subscribe response: ", Integer.valueOf(phqVar.b()));
                if (phqVar.b() != 0) {
                    nrh.b(context, R$string.IDS_active_task_get_network_error);
                } else {
                    ObserverManagerUtil.c("observer_refresh_active_record_provider", "");
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                Object[] objArr = new Object[2];
                objArr[0] = "subscribe error ";
                objArr[1] = th == null ? null : th.getMessage();
                ReleaseLogUtil.c("SCUI_ActiveTaskUtils", objArr);
                nrh.b(context, R$string.IDS_active_task_get_network_error);
            }
        }, str);
        pxp.i(2);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dvh_(View view) {
        pxp.i(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void dva_(final Activity activity, final phs phsVar) {
        final Context e = BaseApplication.e();
        phn.a(new ResultCallback<phq>() { // from class: pxm.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(phq phqVar) {
                int b = phqVar.b();
                ReleaseLogUtil.e("SCUI_ActiveTaskUtils", "handleGetPoints : ", Integer.valueOf(b));
                pxp.c(b);
                if (b == 12038139) {
                    nrh.b(e, R$string.IDS_active_task_today_get_over_error);
                } else if (b == 12038138) {
                    nrh.b(e, R$string.IDS_active_task_all_get_over_error);
                } else if (b == 12038140) {
                    nrh.b(e, R$string.IDS_active_task_get_account_error);
                } else if (b == 12030004) {
                    nrh.b(e, R$string.IDS_active_task_get_network_error);
                } else if (b == 0) {
                    pxm.duX_(activity, phsVar.e());
                } else {
                    nrh.b(e, R$string.IDS_active_task_get_vmall_error);
                }
                ObserverManagerUtil.c("observer_refresh_active_record_provider", "");
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                Object[] objArr = new Object[2];
                objArr[0] = "handleGetPoints error ";
                objArr[1] = th == null ? null : th.getMessage();
                ReleaseLogUtil.c("SCUI_ActiveTaskUtils", objArr);
                nrh.b(e, R$string.IDS_active_task_get_network_error);
            }
        }, phsVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void duX_(final Activity activity, final int i) {
        HandlerExecutor.e(new Runnable() { // from class: pxr
            @Override // java.lang.Runnable
            public final void run() {
                pxm.dvd_(i, activity);
            }
        });
    }

    static /* synthetic */ void dvd_(int i, Activity activity) {
        String a2 = nsf.a(R.plurals._2130903396_res_0x7f030164, i, c(i));
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R$string.IDS_active_task_dialog_get_title).e(nsf.b(R$string.IDS_active_task_dialog_get_text, a2)).cyU_(R$string.IDS_active_task_dialog_get_button, new View.OnClickListener() { // from class: pxv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                pxm.dvb_(view);
            }
        }).cyR_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: pxw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    static /* synthetic */ void dvb_(View view) {
        j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void j() {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.external-task?h5pro=true&urlType=4&pName=com.huawei.health&isImmerse&path=mypoints"));
        intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
        jdw.bGh_(intent, BaseApplication.e());
    }

    public static void duY_(final Activity activity, final int i, final int i2) {
        if (i < 0) {
            LogUtil.b("SCUI_ActiveTaskUtils", "error point with ", Integer.valueOf(i));
        } else {
            HandlerExecutor.e(new Runnable() { // from class: pxq
                @Override // java.lang.Runnable
                public final void run() {
                    pxm.dvf_(i, i2, activity);
                }
            });
        }
    }

    static /* synthetic */ void dvf_(int i, int i2, Activity activity) {
        String a2 = nsf.a(R.plurals._2130903396_res_0x7f030164, i, c(i));
        String a3 = nsf.a(R.plurals._2130903355_res_0x7f03013b, i2, c(i2));
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(activity);
        builder.b(R$string.IDS_active_task_dialog_description_title).e(a(a3, a2)).cyU_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: pxu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private static String a(String str, String str2) {
        int i = R$string.IDS_active_task_dialog_description_text_new2;
        if (Utils.o()) {
            i = R$string.IDS_active_task_dialog_description_text_oversea_new;
        }
        return nsf.b(i, c(1), str, str2, c(2), c(3), c(4), c(3), c(5));
    }

    private static String c(int i) {
        return UnitUtil.e(i, 1, 0);
    }

    public static boolean c() {
        if (!e()) {
            LogUtil.h("SCUI_ActiveTaskUtils", "isOversea");
            return false;
        }
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("SCUI_ActiveTaskUtils", "NetworkConnected is invalid");
            return false;
        }
        if (!f()) {
            LogUtil.h("SCUI_ActiveTaskUtils", "PrivacyHealth is close");
            return false;
        }
        return d();
    }

    private static boolean f() {
        return "true".equals(gmz.d().c(7));
    }

    public static boolean d() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200021");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        njh.c(arrayList, new IBaseResponseCallback() { // from class: pxn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                pxm.c(countDownLatch, atomicBoolean, i, obj);
            }
        });
        try {
            if (!countDownLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("SCUI_ActiveTaskUtils", "RequestData wait timeout, set mIsTaskRemindOpen");
            }
        } catch (InterruptedException unused) {
            ReleaseLogUtil.c("SCUI_ActiveTaskUtils", "interrupted while waiting for getSwitchStatusFromDb");
        }
        return atomicBoolean.get();
    }

    static /* synthetic */ void c(CountDownLatch countDownLatch, AtomicBoolean atomicBoolean, int i, Object obj) {
        if (!(obj instanceof HashMap)) {
            LogUtil.h("SCUI_ActiveTaskUtils", "onResponse: objData is not instanceof HashMap");
            countDownLatch.countDown();
        } else {
            HashMap hashMap = (HashMap) obj;
            if (hashMap.containsKey("900200021")) {
                atomicBoolean.set("1".equals(hashMap.get("900200021")));
            }
            countDownLatch.countDown();
        }
    }

    public static boolean e(String str) {
        String e = SharedPreferenceManager.e("threeCircleSp", "taskSubscribeShow", "");
        int c = DateFormatUtil.c(System.currentTimeMillis(), jdl.b());
        if (TextUtils.isEmpty(e)) {
            e(c, 1, str);
            return false;
        }
        String[] split = e.split("#");
        LogUtil.a("SCUI_ActiveTaskUtils", "valText is ", Arrays.toString(split));
        if (split.length != 3) {
            e(c, 1, str);
            return false;
        }
        if (!str.equals(split[2])) {
            e(c, 1, str);
            return false;
        }
        int m = CommonUtil.m(BaseApplication.e(), split[0]);
        int m2 = CommonUtil.m(BaseApplication.e(), split[1]);
        if (m2 > 2) {
            return true;
        }
        if (c != m) {
            m2++;
            e(c, m2, str);
        }
        return m2 > 2;
    }

    private static void e(int i, int i2, String str) {
        SharedPreferenceManager.c("threeCircleSp", "taskSubscribeShow", i + "#" + i2 + "#" + str);
    }

    public static boolean e() {
        return ActiveTipStringUtils.b();
    }

    public static void a() {
        if (Utils.o()) {
            b();
        } else {
            mle.b("&cid=71885050");
        }
    }

    public static void b() {
        if (jdm.b(BaseApplication.e(), "com.huawei.phoneservice")) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("myhuawei://dispatch/shop/"));
            intent.setPackage("com.huawei.phoneservice");
            intent.setFlags(268435456);
            dvj_(intent, true);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setClassName(BaseApplication.d(), "com.huawei.operation.activity.WebViewActivity");
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
        if ("GB".equals(accountInfo)) {
            accountInfo = "uk";
        }
        intent2.putExtra("url", GRSManager.a(BaseApplication.e()).getUrl("domainMyHuawei") + "/" + accountInfo.toLowerCase(Locale.ENGLISH) + "/offer/");
        dvj_(intent2, false);
    }

    public static void dvj_(Intent intent, boolean z) {
        Context e;
        PackageManager packageManager;
        ResolveInfo resolveActivity;
        if (intent == null || (packageManager = (e = BaseApplication.e()).getPackageManager()) == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
        intent.setComponent(componentName);
        if (z) {
            nsn.cLM_(intent, componentName.getPackageName(), e, nsf.h(R$string.IDS_myhuawei_app));
        } else {
            intent.addFlags(268435456);
            gnm.aPB_(e, intent);
        }
    }
}

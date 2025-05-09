package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import defpackage.qqe;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class qqe {
    public static void a() {
        jdx.b(new Runnable() { // from class: qqg
            @Override // java.lang.Runnable
            public final void run() {
                qqe.h();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void h() {
        final boolean z = qif.b(BaseApplication.e()) > 0;
        final boolean e = BloodPressureSyncManager.e();
        LogUtil.a("BloodPressureMeasureUtils", "isBondedProducts： ", Boolean.valueOf(z), ", isBloodPressureWatch: ", Boolean.valueOf(e));
        HandlerExecutor.a(new Runnable() { // from class: qqh
            @Override // java.lang.Runnable
            public final void run() {
                qqe.d(z, e);
            }
        });
    }

    static /* synthetic */ void d(boolean z, boolean z2) {
        if (z || z2) {
            c(z2);
        } else {
            g();
        }
    }

    private static void c(boolean z) {
        Bundle bundle = new Bundle();
        if (z) {
            AppRouter.b("/Main/BloodMeasureHintActivity").zF_(bundle).a(268435456).c(BaseApplication.e());
            qqb.b(3);
        } else {
            bundle.putString("kind", "HDK_BLOOD_PRESSURE");
            bundle.putString("view", "MeasureDevice");
            AppRouter.b("/PluginDevice/DeviceMainActivity").zF_(bundle).a(268435456).c(BaseApplication.e());
            qqb.b(4);
        }
    }

    private static void g() {
        if (Utils.o() && BloodPressureUtil.d()) {
            b();
        } else {
            f();
        }
    }

    private static void b() {
        LogUtil.a("BloodPressureMeasureUtils", "showDialogIfBPDeviceConnectedEver");
        dHt_(BaseApplication.e().getString(R$string.IDS_hw_plugin_device_selection_click_rebind_my_device_oversea_unsupported), new View.OnClickListener() { // from class: qqf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qqe.dHv_(view);
            }
        }).show();
    }

    static /* synthetic */ void dHv_(View view) {
        AppRouter.b("/HomeHealth/DeviceMoreListActivity").c(BaseApplication.e());
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void f() {
        String string;
        final Context e = BaseApplication.e();
        LogUtil.a("BloodPressureMeasureUtils", "bondedProductCount: ", Integer.valueOf(qif.b(e)), ", isBloodPressureWatch: ", Boolean.valueOf(BloodPressureSyncManager.e()), ", isChineseSimplifiedLocal: ", Boolean.valueOf(LanguageUtil.m(e)), ", isEnglish: ", Boolean.valueOf(LanguageUtil.p(e)), ", isHasWearDevice: ", Boolean.valueOf(jpp.a()), ", isBluetoothEnabled: ", Boolean.valueOf(ggx.a()));
        if (d() && ggx.a()) {
            if (Utils.o()) {
                string = e.getString(R$string.IDS_hw_plugin_device_selection_click_bind_my_device_oversea_unsupported);
            } else {
                string = e.getString(R$string.IDS_hw_plugin_device_selection_click_bind_my_device_unsupported, c());
            }
        } else {
            string = e.getString(R$string.IDS_hw_plugin_device_selection_click_rebind_my_device_oversea_unsupported);
        }
        dHt_(string, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.util.BloodPressureMeasureUtils$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qqe.dHw_(e, view);
            }
        }).show();
    }

    public static /* synthetic */ void dHw_(Context context, View view) {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_DETAIL_BIND_2030024.value();
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(context, value, hashMap, 0);
        dks.e(BaseApplication.wa_(), "HDK_BLOOD_PRESSURE");
        ViewClickInstrumentation.clickOnView(view);
    }

    private static NoTitleCustomAlertDialog dHt_(String str, View.OnClickListener onClickListener) {
        Context wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            wa_ = BaseApplication.e();
        }
        String string = BaseApplication.e().getString(R$string.IDS_tips_connect_go);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(wa_);
        builder.e(str).czE_(string, onClickListener).czA_(wa_.getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: qqd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qqe.dHu_(view);
            }
        });
        return builder.e();
    }

    static /* synthetic */ void dHu_(View view) {
        LogUtil.a("BloodPressureMeasureUtils", "onClick negative button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private static boolean d() {
        ArrayList<cpm> a2 = jfv.a();
        if (koq.b(a2)) {
            LogUtil.a("BloodPressureMeasureUtils", "deviceInfoForWears is null");
            return false;
        }
        Iterator<cpm> it = a2.iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            if (next != null && next.e() == 2) {
                return true;
            }
        }
        return false;
    }

    private static String c() {
        ArrayList<cpm> a2 = jfv.a();
        if (koq.b(a2)) {
            LogUtil.a("BloodPressureMeasureUtils", "deviceInfoForWears is null");
            return "";
        }
        Iterator<cpm> it = a2.iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            if (next != null && next.e() == 2) {
                return next.d();
            }
        }
        return "";
    }
}

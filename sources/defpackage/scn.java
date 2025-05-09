package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.common.utils.PowerUtil;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class scn {
    private static int e = BaseApplication.getContext().getResources().getColor(R.color._2131297032_res_0x7f090308);

    /* renamed from: a, reason: collision with root package name */
    private static int f17022a = BaseApplication.getContext().getResources().getColor(R.color._2131299381_res_0x7f090c35);
    private static int c = BaseApplication.getContext().getResources().getColor(R.color._2131299378_res_0x7f090c32);

    public static int b(int i) {
        return (i < 0 || i >= 70) ? 73 : 72;
    }

    public static int c(int i) {
        return (i < 0 || i <= 360 || i >= 1200) ? 72 : 73;
    }

    public static int d(int i) {
        return (i < 0 || i < 1200 || i >= 1440) ? 71 : 73;
    }

    public static int e(int i) {
        if (i > 630.0f) {
            return 71;
        }
        return i < 360 ? 72 : 73;
    }

    public static int f(int i) {
        if (i < 0 || i >= 360) {
            return i > 600 ? 71 : 73;
        }
        return 72;
    }

    public static int g(int i) {
        return (i < 0 || i >= 55) ? 71 : 73;
    }

    public static int h(int i) {
        if (i >= 0 && i < 20) {
            return -1;
        }
        if ((i < 20 || i >= 70) && i != 0) {
            return i > 100 ? 71 : 73;
        }
        return 72;
    }

    public static int i(int i) {
        if (i < 0 || i >= 20) {
            return i > 60 ? 71 : 73;
        }
        return 72;
    }

    public static int j(int i) {
        if (i < 0 || i >= 10) {
            return i > 30 ? 71 : 73;
        }
        return 72;
    }

    public static int k(int i) {
        return (i < 0 || i >= 70) ? 73 : 72;
    }

    public static int l(int i) {
        if (i > 600) {
            return 71;
        }
        return i < 360 ? 72 : 73;
    }

    public static int n(int i) {
        return (i < 85 || i > 100) ? 72 : 73;
    }

    public static int o(int i) {
        return i >= 30 ? 71 : 73;
    }

    public static int q(int i) {
        return (i < 0 || i >= 70) ? 73 : 72;
    }

    public static int r(int i) {
        return i > 360 ? 73 : 72;
    }

    public static int s(int i) {
        return (i < 0 || i > 1) ? 71 : 73;
    }

    public static void d(HealthTextView healthTextView, int i) {
        e(healthTextView, i, false);
    }

    public static void e(HealthTextView healthTextView, int i, boolean z) {
        if (healthTextView == null) {
        }
        switch (i) {
            case 71:
                if (z) {
                    healthTextView.setText(R$string.IDS_details_sleep_grade_late);
                } else {
                    healthTextView.setText(R$string.IDS_details_sleep_grade_high);
                }
                healthTextView.setTextColor(f17022a);
                break;
            case 72:
                if (z) {
                    healthTextView.setText(R$string.IDS_details_sleep_grade_early);
                } else {
                    healthTextView.setText(R$string.IDS_details_sleep_grade_low);
                }
                healthTextView.setTextColor(c);
                break;
            case 73:
                healthTextView.setText(R$string.IDS_details_sleep_grade_normal);
                healthTextView.setTextColor(e);
                break;
        }
    }

    public static int b(int i, int[] iArr) {
        if (iArr.length < 2) {
            ReleaseLogUtil.c("R_Sleep_FitnessSleepUtils", "itemBaseline.length < 2");
            return -1;
        }
        if (i < iArr[0]) {
            return 72;
        }
        return i > iArr[1] ? 71 : 73;
    }

    public static String d(Context context, int i) {
        int i2 = i / 60;
        int i3 = i % 60;
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(i3, 1, 0));
        if (i2 == 0) {
            return quantityString;
        }
        return BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0)), quantityString);
    }

    public static void c(HealthTextView healthTextView) {
        if (healthTextView == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (c()) {
            layoutParams.gravity = 16;
        } else {
            layoutParams.gravity = 80;
        }
        healthTextView.setLayoutParams(layoutParams);
    }

    public static boolean c() {
        if ("ar".equalsIgnoreCase(Locale.getDefault().getLanguage())) {
            LogUtil.a("FitnessSleepUtils", "is Arab language");
            return true;
        }
        LogUtil.a("FitnessSleepUtils", "is not Arab language");
        return false;
    }

    public static float b(float f) {
        return f * BaseApplication.getContext().getResources().getDisplayMetrics().density;
    }

    public static String c(long j) {
        return new SimpleDateFormat("HH:mm").format(new Date(j));
    }

    public static String b(long j) {
        String format = new SimpleDateFormat("HH:mm").format(new Date(j));
        String[] split = format.split(":");
        LogUtil.a("FitnessSleepUtils", "timeArray: " + Arrays.toString(split));
        try {
            return split.length == 2 ? d(Integer.parseInt(split[0]), Integer.parseInt(split[1])) : format;
        } catch (NumberFormatException unused) {
            LogUtil.a("FitnessSleepUtils", "parseInt fail, timeArray: " + Arrays.toString(split));
            return format;
        }
    }

    public static String d(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(jec.d(), jec.a(), 1, i, i2);
        return UnitUtil.a(calendar.getTime(), 1);
    }

    public static String a(int i) {
        return String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_h_min_unit), UnitUtil.e(i / 60, 1, 0), UnitUtil.e(i % 60, 1, 0));
    }

    public static String t(int i) {
        if (i < 10) {
            return UnitUtil.e(0.0d, 1, 0) + UnitUtil.e(i, 1, 0);
        }
        return UnitUtil.e(i, 1, 0);
    }

    public static String a(String str) {
        try {
            return UnitUtil.a(new SimpleDateFormat("HH:mm", Locale.getDefault()).parse(str), 129);
        } catch (ParseException unused) {
            LogUtil.a("FitnessSleepUtils", "formatTime.ParseException");
            return str;
        }
    }

    public static boolean a() {
        DeviceInfo a2 = jpt.a("FitnessSleepUtils");
        if (a2 == null) {
            LogUtil.h("FitnessSleepUtils", "hasCoreSleepDevice currentDeviceInfo is null");
            return false;
        }
        if (a2.getDeviceConnectState() != 2) {
            LogUtil.h("FitnessSleepUtils", "device not connected");
            return false;
        }
        DeviceCapability d = cvs.d();
        return d != null && d.isDeviceSupportCoreSleep();
    }

    public static String a(Context context) {
        return LanguageUtil.b(context) ? " - " : Constants.LINK;
    }

    public static List<nkz> c(Context context, List<Integer> list, boolean z, int i) {
        String string;
        String string2;
        String string3;
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            string = context.getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep);
            string2 = context.getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep);
            string3 = context.getString(R$string.IDS_fitness_core_sleep_rem_sleep);
        } else if (i == 4) {
            string = context.getString(R$string.IDS_details_sleep_avg_deep_sleep);
            string2 = context.getString(R$string.IDS_details_sleep_avg_light_sleep);
            string3 = context.getString(R$string.IDS_fitness_core_sleep_avg_rem_sleep);
        } else {
            string = context.getString(R$string.IDS_core_sleep_average_deep_sleep);
            string2 = context.getString(R$string.IDS_core_sleep_average_light_sleep);
            string3 = context.getString(R$string.IDS_core_sleep_average_eye_movement);
        }
        arrayList.add(string);
        arrayList.add(string2);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298920_res_0x7f090a68)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298921_res_0x7f090a69)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298959_res_0x7f090a8f)));
        arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298960_res_0x7f090a90)));
        if (z) {
            arrayList.add(string3);
            arrayList3.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298950_res_0x7f090a86)));
            arrayList2.add(Integer.valueOf(ContextCompat.getColor(context, R.color._2131298951_res_0x7f090a87)));
        }
        int size = list != null ? list.size() : 0;
        ArrayList arrayList4 = new ArrayList(size);
        int i2 = 0;
        while (i2 < size) {
            arrayList4.add(new nkz(i2 < arrayList.size() ? (String) arrayList.get(i2) : "", list.get(i2).intValue(), i2 < arrayList2.size() ? ((Integer) arrayList2.get(i2)).intValue() : 0, i2 < arrayList3.size() ? ((Integer) arrayList3.get(i2)).intValue() : 0));
            i2++;
        }
        return arrayList4;
    }

    public static int m(int i) {
        if (i == -1) {
            return R$string.IDS_fitness_core_sleep_no_device_connected;
        }
        if (i == 0) {
            return R$string.IDS_fitness_core_sleep_no_new_data;
        }
        if (i == 1) {
            return R$string.IDS_fitness_core_sleep_no_new_data;
        }
        if (i == 2) {
            return R$string.IDS_fitness_core_sleep_sync_failed;
        }
        if (i == 3) {
            return R$string.IDS_fitness_core_sleep_calculate_failed;
        }
        if (i == 5) {
            return R$string.IDS_fitness_core_sleep_no_sleep_data;
        }
        if (i != 6) {
            return 0;
        }
        return R$string.IDS_device_ota_later_note;
    }

    public static void a(final WeakReference<Context> weakReference) {
        ReleaseLogUtil.b("R_Sleep_FitnessSleepUtils", "handleDetectionDialog start");
        if (!efb.b(weakReference.get())) {
            LogUtil.a("FitnessSleepUtils", "handleDetectionDialog not supported");
            return;
        }
        final mtp d = mtp.d();
        if (!d.isPluginAvaiable()) {
            ReleaseLogUtil.a("R_Sleep_FitnessSleepUtils", "showDetectionDialog is Unavailable");
            d.loadPlugin(new AppBundleLauncher.InstallCallback() { // from class: scs
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return scn.dVT_(weakReference, d, context, intent);
                }
            });
        } else {
            e(weakReference, d);
        }
    }

    static /* synthetic */ boolean dVT_(WeakReference weakReference, mtp mtpVar, Context context, Intent intent) {
        ReleaseLogUtil.b("R_Sleep_FitnessSleepUtils", "PluginSleepDetectionProxy loadPlugin success");
        e(weakReference, mtpVar);
        return true;
    }

    private static void e(final WeakReference<Context> weakReference, final mtp mtpVar) {
        mtpVar.sleepRecordStatus(new IBaseResponseCallback() { // from class: scq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                scn.b(weakReference, mtpVar, i, obj);
            }
        });
    }

    static /* synthetic */ void b(WeakReference weakReference, mtp mtpVar, int i, Object obj) {
        ReleaseLogUtil.b("R_Sleep_FitnessSleepUtils", "sleepRecordStatus: ", Integer.valueOf(i));
        Context context = (Context) weakReference.get();
        if (context == null) {
            return;
        }
        if (i == 5) {
            b(context, mtpVar);
        } else if (i == 3) {
            mtpVar.d(context, true, "4");
        } else {
            mtpVar.disconnect();
        }
    }

    public static void b(Context context, mtp mtpVar) {
        if (PowerUtil.b(context) < 10) {
            LogUtil.h("FitnessSleepUtils", "showDetectionDialog batteryPercent < 10");
        } else {
            HandlerExecutor.e(new AnonymousClass4(context, mtpVar));
        }
    }

    /* renamed from: scn$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ mtp c;
        final /* synthetic */ Context e;

        AnonymousClass4(Context context, mtp mtpVar) {
            this.e = context;
            this.c = mtpVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.e);
            NoTitleCustomAlertDialog.Builder e = builder.e(this.e.getString(R$string.IDS_sleep_record_interrupt_description));
            String string = this.e.getString(R$string.IDS_sleep_record_restores);
            final Context context = this.e;
            final mtp mtpVar = this.c;
            NoTitleCustomAlertDialog.Builder czE_ = e.czE_(string, new View.OnClickListener() { // from class: scr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    scn.AnonymousClass4.dVU_(context, mtpVar, view);
                }
            });
            String string2 = this.e.getString(R$string.IDS_sleep_record_terminate);
            final Context context2 = this.e;
            final mtp mtpVar2 = this.c;
            czE_.czA_(string2, new View.OnClickListener() { // from class: scp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    scn.AnonymousClass4.dVV_(context2, mtpVar2, view);
                }
            });
            NoTitleCustomAlertDialog e2 = builder.e();
            e2.setCancelable(false);
            e2.setOnShowListener(new b(e2));
            e2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: sct
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    scn.AnonymousClass4.dVW_(dialogInterface);
                }
            });
            e2.show();
        }

        static /* synthetic */ void dVU_(Context context, mtp mtpVar, View view) {
            scn.c(context, "2");
            mtpVar.d(context, true, "4");
            ViewClickInstrumentation.clickOnView(view);
        }

        static /* synthetic */ void dVV_(Context context, mtp mtpVar, View view) {
            scn.c(context, "1");
            scn.d(context, mtpVar);
            ViewClickInstrumentation.clickOnView(view);
        }

        static /* synthetic */ void dVW_(DialogInterface dialogInterface) {
            LogUtil.a("FitnessSleepUtils", "onDismiss");
            ObserverManagerUtil.e("DISMISS_DETECTION_DIALOG");
        }
    }

    static class b implements DialogInterface.OnShowListener {
        public WeakReference<NoTitleCustomAlertDialog> e;

        b(NoTitleCustomAlertDialog noTitleCustomAlertDialog) {
            this.e = new WeakReference<>(noTitleCustomAlertDialog);
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            LogUtil.a("FitnessSleepUtils", "onShow");
            ObserverManagerUtil.d(new Observer() { // from class: scn.b.5
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    NoTitleCustomAlertDialog noTitleCustomAlertDialog = b.this.e.get();
                    if (noTitleCustomAlertDialog == null) {
                        LogUtil.a("FitnessSleepUtils", "onShow null");
                    } else {
                        LogUtil.a("FitnessSleepUtils", "detection dialog dismiss");
                        noTitleCustomAlertDialog.dismiss();
                    }
                }
            }, "DISMISS_DETECTION_DIALOG");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", str);
        ixx.d().d(context, AnalyticsValue.SLEEP_RECORD_21300039.value(), hashMap, 0);
    }

    public static void d(Context context, mtp mtpVar) {
        if (context == null) {
            return;
        }
        mtpVar.stopSleepRecord(context);
    }
}

package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes5.dex */
public class jlk {
    private static jlk b;
    private static final Object e = new Object();
    private jfq c = jfq.c();
    private DeviceCapability d = cvs.d();

    /* renamed from: a, reason: collision with root package name */
    private jhg f13937a = jhg.c(BaseApplication.getContext());

    private jlk() {
    }

    public static jlk a() {
        jlk jlkVar;
        synchronized (e) {
            if (b == null) {
                b = new jlk();
            }
            jlkVar = b;
        }
        return jlkVar;
    }

    public void b(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwTemperatureManager", "isHeartRateModeFromCloud callback is null");
            return;
        }
        DeviceCapability d = cvs.d();
        this.d = d;
        if (d == null) {
            LogUtil.h("HwTemperatureManager", "isHeartRateModeFromCloud mDeviceCapability is null");
            return;
        }
        if (d.isSupportContinueHeartRate()) {
            jqi.a().getSwitchSetting("continue_heart_rate", new IBaseResponseCallback() { // from class: jlk.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    int b2 = (i == 0 && (obj instanceof String)) ? CommonUtil.b(BaseApplication.getContext(), (String) obj, 0) : 0;
                    boolean z = b2 != 0;
                    LogUtil.a("HwTemperatureManager", "CONTINUE_MEASURE_HEART_RATE status : ", Integer.valueOf(b2));
                    iBaseResponseCallback.d(i, Boolean.valueOf(z));
                }
            });
        } else if (this.d.isSupportHeartRateEnable() && !this.d.isSupportContinueHeartRate()) {
            jqi.a().getSwitchSetting("heart_rate_button", new IBaseResponseCallback() { // from class: jlk.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HwTemperatureManager", "HEART_RATE_BUTTON err_code is ", Integer.valueOf(i));
                    boolean equals = (i == 0 && (obj instanceof String)) ? "1".equals((String) obj) : false;
                    LogUtil.a("HwTemperatureManager", "updateSwitchStatus isEnable = ", Boolean.valueOf(equals));
                    iBaseResponseCallback.d(i, Boolean.valueOf(equals));
                }
            });
        } else {
            LogUtil.h("HwTemperatureManager", "isHeartRateModeFromCloud, else.");
            iBaseResponseCallback.d(-1, false);
        }
    }

    public void b(boolean z) {
        jqy.d(jpt.d("HwTemperatureManager"), "custom.temperature_unit", String.valueOf(z));
    }

    public void a(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        int i;
        if (Utils.o() || !jll.c()) {
            LogUtil.h("HwTemperatureManager", "createCloseHeartRemindDialog, isOversea or isn't sport temp.");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        if (d()) {
            LogUtil.h("HwTemperatureManager", "createCloseHeartRemindDialog, isTemperatureClosed.");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, null);
                return;
            }
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        if (jll.d()) {
            i = R$string.IDS_temp_slightly_upper_closed_remind_once;
        } else {
            i = R$string.IDS_temp_closed_remind_once;
        }
        NoTitleCustomAlertDialog e2 = builder.e(i).czz_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: jlk.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(-1, null);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R$string.IDS_settings_AI_Health_Alarm_close, new View.OnClickListener() { // from class: jlk.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwTemperatureManager", "createCloseHeartRemindDialog, close");
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, null);
                }
                jlk.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        if (e2.isShowing()) {
            return;
        }
        e2.show();
    }

    public void c() {
        if (Utils.o()) {
            return;
        }
        this.f13937a.e(1, 0, -1);
        this.f13937a.e(2, 0, 0);
        this.f13937a.e(3, 0, 0);
    }

    private boolean d() {
        String c = jqy.c("temperature_upper_remind");
        String c2 = jqy.c("temperature_lower_remind");
        LogUtil.a("HwTemperatureManager", "isTemperatureClosed upperValue : ", c, ", lowerValue : ", c2);
        return ((c(c) > 0.0f ? 1 : (c(c) == 0.0f ? 0 : -1)) <= 0) && ((c(c2) > 0.0f ? 1 : (c(c2) == 0.0f ? 0 : -1)) <= 0);
    }

    private float c(String str) {
        if (str == null) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("HwTemperatureManager", "NumberFormatException");
            return 0.0f;
        }
    }

    public void b() {
        LogUtil.a("HwTemperatureManager", "sendStudyCommandToDevice");
        jho.e(4, 0, 0);
    }

    public String a(boolean z) {
        int i;
        String e2 = UnitUtil.e(!z ? 98.96f : 37.2f, 1, 1);
        if (z) {
            i = R$string.IDS_settings_health_temperature_unit;
        } else {
            i = R$string.IDS_temp_unit_fahrenheit;
        }
        return nsf.b(i, e2);
    }

    public String[] c(boolean z, int i) {
        String[] b2 = b(z, i);
        String[] strArr = new String[b2.length + 1];
        int i2 = 0;
        strArr[0] = BaseApplication.getContext().getResources().getString(R$string.IDS_settings_about_huawei_cloud_service_action_turn_off);
        if (z) {
            while (i2 < b2.length) {
                int i3 = i2 + 1;
                strArr[i3] = BaseApplication.getContext().getResources().getString(R$string.IDS_settings_health_temperature_unit, b2[i2]);
                i2 = i3;
            }
        } else {
            while (i2 < b2.length) {
                int i4 = i2 + 1;
                strArr[i4] = BaseApplication.getContext().getResources().getString(R$string.IDS_temp_unit_fahrenheit, b2[i2]);
                i2 = i4;
            }
        }
        return strArr;
    }

    private String[] b(boolean z, int i) {
        int i2;
        int i3;
        boolean z2 = true;
        if (z) {
            if (i == 0) {
                i2 = 372;
                i3 = 385;
                z2 = false;
            } else {
                i2 = 340;
                i3 = 360;
            }
        } else if (i == 0) {
            i2 = 990;
            i3 = 1013;
            z2 = false;
        } else {
            i2 = 932;
            i3 = 968;
        }
        return d(i2, i3, z2);
    }

    private String[] d(int i, int i2, boolean z) {
        float[] d = jll.d(i, i2, z);
        String[] strArr = new String[d.length];
        for (int i3 = 0; i3 <= d.length - 1; i3++) {
            strArr[i3] = UnitUtil.e(d[i3], 1, 1);
        }
        return strArr;
    }

    public int c(int i, String str, boolean z) {
        int i2;
        LogUtil.a("HwTemperatureManager", "getPosition tempDialogType : ", Integer.valueOf(i), ", tempData : ", str);
        int i3 = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            float parseFloat = Float.parseFloat(str);
            if (parseFloat < 340.0f) {
                parseFloat *= 10.0f;
            }
            i2 = (int) parseFloat;
        } catch (NumberFormatException unused) {
            LogUtil.b("HwTemperatureManager", "getPosition NumberFormatException");
            i2 = 0;
        }
        if (z) {
            if (i2 >= 932) {
                i2 = jll.a(i2);
            }
        } else if (i2 < 932) {
            try {
                i2 = (int) (Float.parseFloat(jll.d(String.valueOf(i2 / 10.0f))) * 10.0f);
            } catch (NumberFormatException unused2) {
                LogUtil.b("HwTemperatureManager", "getPosition fahrenheit NumberFormatException");
            }
        }
        LogUtil.a("HwTemperatureManager", "getPosition intValue : ", Integer.valueOf(i2));
        int[] a2 = a(z, i);
        int i4 = 0;
        while (true) {
            if (i4 >= a2.length) {
                break;
            }
            if (i2 == a2[i4]) {
                i3 = i4 + 1;
                break;
            }
            i4++;
        }
        LogUtil.a("HwTemperatureManager", "getPosition position : ", Integer.valueOf(i3));
        return i3;
    }

    public int[] a(boolean z, int i) {
        int i2;
        int i3;
        boolean z2 = true;
        if (z) {
            if (i == 0) {
                i2 = 372;
                i3 = 385;
                z2 = false;
            } else {
                i2 = 340;
                i3 = 360;
            }
        } else if (i == 0) {
            i2 = 990;
            i3 = 1013;
            z2 = false;
        } else {
            i2 = 932;
            i3 = 968;
        }
        return jll.e(i2, i3, z2);
    }
}

package com.huawei.health.ecologydevice.manager;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.manager.BloodPressureStartFromDeviceHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import defpackage.cpp;
import defpackage.dcz;
import defpackage.dfd;
import health.compact.a.Services;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class BloodPressureStartFromDeviceHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f2294a;
    private static final Object b = new Object();
    private static String c;
    private static BloodPressureStartFromDeviceHelper d;
    private int e;
    private int f = 2;
    private String g;
    private CustomViewDialog i;
    private StartFromDeviceListen j;

    public interface StartFromDeviceListen {
        void onMeasureChanged(int i);

        void onStatusChanged(int i);
    }

    static {
        HashSet hashSet = new HashSet(10);
        f2294a = hashSet;
        d = null;
        hashSet.add("O008");
        hashSet.add("O007");
        hashSet.add("O006");
        hashSet.add("O03S");
    }

    public static BloodPressureStartFromDeviceHelper d() {
        BloodPressureStartFromDeviceHelper bloodPressureStartFromDeviceHelper;
        synchronized (b) {
            if (d == null) {
                d = new BloodPressureStartFromDeviceHelper();
            }
            bloodPressureStartFromDeviceHelper = d;
        }
        return bloodPressureStartFromDeviceHelper;
    }

    public static void e() {
        synchronized (b) {
            d = null;
        }
    }

    public static boolean c() {
        return !TextUtils.isEmpty(c) && "1".equals(c);
    }

    public static void e(dcz dczVar) {
        if (b(dczVar)) {
            c = dczVar.v().d();
        } else {
            LogUtil.a("BloodPressureStartFromDeviceHelper", "productInfo or Resolution or getResolution is null");
            c = "";
        }
    }

    private static boolean b(dcz dczVar) {
        return (dczVar == null || dczVar.v() == null) ? false : true;
    }

    public static boolean b(String str) {
        return f2294a.contains(str);
    }

    public static boolean a(String str) {
        return c() && b(str);
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public void e(int i) {
        this.f = i;
    }

    public void d(String str, String str2, StartFromDeviceListen startFromDeviceListen) {
        this.g = str2;
        this.j = startFromDeviceListen;
        if (!c()) {
            LogUtil.h("BloodPressureStartFromDeviceHelper", "Enter initResolution no SupportStartMeasureFromDevice");
        } else {
            LogUtil.a("BloodPressureStartFromDeviceHelper", "Enter initResolution");
            ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).startMeasure(str, str2, new e(), Tq_());
        }
    }

    public void a(dcz dczVar) {
        MeasureKit measureKit;
        if (dczVar != null && (measureKit = ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getMeasureKit(dczVar.s())) != null) {
            measureKit.getMeasureController().cleanup();
        }
        this.j = null;
        e();
    }

    public void d(Context context, String str) {
        if (context == null) {
            return;
        }
        if (this.i == null) {
            View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.blood_pressure_connect_timeout_dialog, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
            builder.a(str).czg_(inflate).cze_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: dbo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodPressureStartFromDeviceHelper.this.Tr_(view);
                }
            });
            CustomViewDialog e2 = builder.e();
            this.i = e2;
            e2.setCancelable(false);
        }
        this.i.show();
    }

    public /* synthetic */ void Tr_(View view) {
        LogUtil.a("BloodPressureStartFromDeviceHelper", "showConnectionNoteDialog onclick PositiveButton");
        this.i.dismiss();
        this.i = null;
        ViewClickInstrumentation.clickOnView(view);
    }

    private Bundle Tq_() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", -1);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthData healthData) {
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.h("BloodPressureStartFromDeviceHelper", "saveBloodPressureData UniqueId is null");
            return;
        }
        HealthDevice bondedDeviceByUniqueId = ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getBondedDeviceByUniqueId(this.g);
        if (bondedDeviceByUniqueId == null) {
            LogUtil.a("BloodPressureStartFromDeviceHelper", "saveBloodPressureData device is null");
            return;
        }
        dfd dfdVar = new dfd(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
        dfdVar.e(new IBaseResponseCallback() { // from class: dbm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BloodPressureStartFromDeviceHelper.this.a(i, obj);
            }
        });
        dfdVar.onDataChanged(bondedDeviceByUniqueId, healthData);
    }

    public /* synthetic */ void a(int i, Object obj) {
        StartFromDeviceListen startFromDeviceListen;
        LogUtil.a("BloodPressureStartFromDeviceHelper", "BloodPressureResultFragment saveBloodPressureData() onResponse() err_code:", Integer.valueOf(i));
        if (i != 0 || (startFromDeviceListen = this.j) == null) {
            return;
        }
        startFromDeviceListen.onMeasureChanged(10002);
    }

    class e implements IHealthDeviceCallback {
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        }

        private e() {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
            LogUtil.a("BloodPressureStartFromDeviceHelper", "InnerHealthDeviceCallback onDataChanged");
            BloodPressureStartFromDeviceHelper.this.d(healthData);
            BloodPressureStartFromDeviceHelper.this.f = 2;
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
            LogUtil.a("BloodPressureStartFromDeviceHelper", "InnerHealthDeviceCallback onProgressChanged");
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
            LogUtil.a("BloodPressureStartFromDeviceHelper", "InnerHealthDeviceCallback onStatusChanged status = ", Integer.valueOf(i));
            BloodPressureStartFromDeviceHelper.this.e = i;
            if (BloodPressureStartFromDeviceHelper.this.j != null) {
                BloodPressureStartFromDeviceHelper.this.j.onStatusChanged(i);
            }
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
            LogUtil.h("BloodPressureStartFromDeviceHelper", "InnerHealthDeviceCallback onFailed code=", Integer.valueOf(i));
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onMeasureChanged(int i) {
            BloodPressureStartFromDeviceHelper.this.f = i;
            LogUtil.a("BloodPressureStartFromDeviceHelper", "InnerHealthDeviceCallback onMeasureChange status=", Integer.valueOf(i));
            if (BloodPressureStartFromDeviceHelper.this.j != null) {
                if (i == 0) {
                    BloodPressureStartFromDeviceHelper.this.j.onMeasureChanged(10004);
                } else if (i != 1) {
                    BloodPressureStartFromDeviceHelper.this.j.onMeasureChanged(i);
                } else {
                    BloodPressureStartFromDeviceHelper.this.j.onMeasureChanged(10003);
                }
            }
        }
    }
}

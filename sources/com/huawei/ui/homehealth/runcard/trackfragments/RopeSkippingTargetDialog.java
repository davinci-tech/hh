package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog;
import com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog;
import com.huawei.ui.main.R$string;
import defpackage.ixx;
import defpackage.owp;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class RopeSkippingTargetDialog {
    private static final float[] c = {0.5f, 1.0f, 3.0f, 5.0f, 10.0f, 15.0f, 30.0f, 60.0f};
    private static final int[] e = {80, 100, 200, 300, 500, 1000, 2000, 3000, 5000};
    private Context b;
    private CustomViewDialog d;
    private List<String> f;
    private Map<String, ArrayList<String>> g;
    private int[] i;
    private int l;
    private float[] m;

    /* renamed from: a, reason: collision with root package name */
    private int f9555a = 0;
    private int j = 0;
    private int h = 0;
    private int n = 0;
    private float o = -1.0f;

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i) {
        if (i != 1) {
            return i != 2 ? 6 : 5;
        }
        return 0;
    }

    private int d(int i) {
        if (i != 0) {
            return i != 5 ? 0 : 2;
        }
        return 1;
    }

    public RopeSkippingTargetDialog(Context context) {
        this.b = context;
    }

    private List<String> d() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(this.b.getResources().getString(R.string._2130842655_res_0x7f02141f));
        arrayList.add(a(0));
        arrayList.add(a(5));
        return arrayList;
    }

    private Map<String, ArrayList<String>> b(HealthMultiNumberPicker healthMultiNumberPicker, int i) {
        if (healthMultiNumberPicker == null) {
            healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        }
        c(i);
        ArrayList<String> arrayList = new ArrayList<>(7);
        ArrayList<String> arrayList2 = new ArrayList<>(6);
        arrayList.add(this.b.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        arrayList2.add(this.b.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        ArrayList<String> d = d(healthMultiNumberPicker, arrayList, this.m);
        ArrayList<String> b = b(arrayList2, this.i);
        ArrayList arrayList3 = new ArrayList(1);
        arrayList3.add("");
        HashMap hashMap = new HashMap();
        hashMap.put(this.b.getResources().getString(R.string._2130842655_res_0x7f02141f), arrayList3);
        hashMap.put(a(0), d);
        hashMap.put(a(5), b);
        return hashMap;
    }

    private void c(int i) {
        if (i == 283) {
            this.m = c;
            this.i = e;
        }
    }

    private ArrayList<String> d(HealthMultiNumberPicker healthMultiNumberPicker, ArrayList<String> arrayList, float[] fArr) {
        if (arrayList == null || fArr == null || fArr.length == 0) {
            LogUtil.h("Track_RopeSkippingTargetDialog", "times array is null");
            return arrayList;
        }
        for (float f : fArr) {
            arrayList.add(a(healthMultiNumberPicker, f));
        }
        return arrayList;
    }

    private String a(HealthMultiNumberPicker healthMultiNumberPicker, float f) {
        int i = (int) (60.0f * f);
        if (i <= 59) {
            return this.b.getResources().getQuantityString(R.plurals._2130903271_res_0x7f0300e7, i, Integer.valueOf(i));
        }
        return healthMultiNumberPicker.d(f, this.b.getResources().getQuantityString(R.plurals._2130903233_res_0x7f0300c1, (int) f));
    }

    private ArrayList<String> b(ArrayList<String> arrayList, int[] iArr) {
        if (arrayList == null || iArr.length == 0) {
            LogUtil.h("Track_RopeSkippingTargetDialog", "times array is null");
            return arrayList;
        }
        for (int i : iArr) {
            arrayList.add(this.b.getResources().getQuantityString(R.plurals._2130903315_res_0x7f030113, i, Integer.valueOf(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b(int i, int i2) {
        if (i == 0) {
            return 0.0f;
        }
        if (i2 == 0) {
            return this.m[i - 1] * 60.0f;
        }
        if (i2 != 5) {
            return 0.0f;
        }
        return this.i[i - 1];
    }

    private int[] c() {
        int[] iArr = new int[this.m.length];
        int i = 0;
        while (true) {
            float[] fArr = this.m;
            if (i >= fArr.length) {
                return iArr;
            }
            iArr[i] = (int) (fArr[i] * 60.0f);
            i++;
        }
    }

    private int b(int i, float f) {
        if (i == 0) {
            int[] c2 = c();
            for (int i2 = 0; i2 < c2.length; i2++) {
                if (((int) f) == c2[i2]) {
                    return i2 + 1;
                }
            }
        } else if (i == 5) {
            int i3 = 0;
            while (true) {
                int[] iArr = this.i;
                if (i3 >= iArr.length) {
                    break;
                }
                if (((int) f) == iArr[i3]) {
                    return i3 + 1;
                }
                i3++;
            }
        }
        return 0;
    }

    public void dfR_(int i, Handler handler) {
        this.l = i;
        this.n = owp.e(this.b, i);
        this.o = owp.a(this.b, this.l);
        LogUtil.a("Track_RopeSkippingTargetDialog", "showTargetChoiceWheelPickerDialog() mTargetType: ", Integer.valueOf(this.n), ", mTargetValue: ", Float.valueOf(this.o));
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        this.f = d();
        this.g = b(healthMultiNumberPicker, i);
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, false});
        List<String> list = this.f;
        healthMultiNumberPicker.setDisplayedValues(0, (String[]) list.toArray(new String[list.size()]), 0);
        int d = d(this.n);
        int b = b(this.n, this.o);
        ArrayList<String> arrayList = this.g.get(this.f.get(d));
        healthMultiNumberPicker.setDisplayedValues(1, (String[]) arrayList.toArray(new String[arrayList.size()]), 0);
        healthMultiNumberPicker.a(new int[]{d, b}, arrayList.size());
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog.2
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i2, HealthMultiNumberPicker healthMultiNumberPicker2, int i3, int i4) {
                if (i2 == 0) {
                    if (i4 < RopeSkippingTargetDialog.this.f.size() && RopeSkippingTargetDialog.this.g.containsKey(RopeSkippingTargetDialog.this.f.get(i4))) {
                        ArrayList arrayList2 = (ArrayList) RopeSkippingTargetDialog.this.g.get(RopeSkippingTargetDialog.this.f.get(i4));
                        healthMultiNumberPicker2.setDisplayedValues(1, (String[]) arrayList2.toArray(new String[arrayList2.size()]), 0);
                    } else {
                        LogUtil.h("Track_RopeSkippingTargetDialog", "the sport target is not valid");
                    }
                }
            }
        });
        dfN_(healthMultiNumberPicker, handler);
    }

    private void dfN_(final HealthMultiNumberPicker healthMultiNumberPicker, final Handler handler) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.b);
        builder.d(R.string._2130842526_res_0x7f02139e).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                RopeSkippingTargetDialog ropeSkippingTargetDialog = RopeSkippingTargetDialog.this;
                ropeSkippingTargetDialog.n = ropeSkippingTargetDialog.b(selectedLocations[0]);
                RopeSkippingTargetDialog ropeSkippingTargetDialog2 = RopeSkippingTargetDialog.this;
                ropeSkippingTargetDialog2.o = ropeSkippingTargetDialog2.b(selectedLocations[1], ropeSkippingTargetDialog2.n);
                if (selectedLocations[0] == 0 || selectedLocations[1] != 0) {
                    LogUtil.a("Track_RopeSkippingTargetDialog", "mTargetType: ", Integer.valueOf(RopeSkippingTargetDialog.this.n), ", mTargetValue: ", Float.valueOf(RopeSkippingTargetDialog.this.o));
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.arg1 = RopeSkippingTargetDialog.this.n;
                    obtainMessage.arg2 = 2;
                    obtainMessage.obj = Float.valueOf(RopeSkippingTargetDialog.this.o);
                    handler.sendMessage(obtainMessage);
                    RopeSkippingTargetDialog.this.e();
                } else if (RopeSkippingTargetDialog.this.n == 0) {
                    RopeSkippingTargetDialog.this.dfP_(handler);
                } else {
                    RopeSkippingTargetDialog ropeSkippingTargetDialog3 = RopeSkippingTargetDialog.this;
                    ropeSkippingTargetDialog3.dfQ_(ropeSkippingTargetDialog3.b, RopeSkippingTargetDialog.this.n, handler);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.d = e2;
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("goalType", Integer.valueOf(this.n));
        if (!Utils.o()) {
            hashMap.put("goalValue", Integer.valueOf((int) this.o));
            hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.l));
        }
        ixx.d().d(this.b, AnalyticsValue.BI_TRACK_SPORT_GOAL_ACTION_KEY.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dfP_(Handler handler) {
        int i;
        int i2;
        int i3;
        if (owp.e(this.b, this.l) == 0) {
            int a2 = (int) owp.a(this.b, this.l);
            i2 = a2 / 3600;
            i3 = (a2 % 3600) / 60;
            i = a2 % 60;
            if (i2 == 0) {
                i3--;
            }
            LogUtil.a("Track_RopeSkippingTargetDialog", "createCustomTimeDialog() indexHour: ", Integer.valueOf(i2), ", indexMinute: ", Integer.valueOf(i3), ", indexSecond: ", Integer.valueOf(i));
        } else {
            i = 0;
            i2 = 0;
            i3 = 0;
        }
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        healthMultiNumberPicker.setPickerCount(3, new boolean[]{false, true, true});
        healthMultiNumberPicker.setDisplayedValues(0, healthMultiNumberPicker.d(0, 2, this.b.getResources().getString(R$string.IDS_band_data_sleep_unit_h)), i2);
        int i4 = i2 == 0 ? 1 : 0;
        int i5 = i2 == 2 ? 0 : 59;
        healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(i4, i5, this.b.getResources().getString(R$string.IDS_band_data_sleep_unit_m)), i3);
        healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, i5, this.b.getResources().getString(R$string.IDS_second)), i);
        healthMultiNumberPicker.setOnValueChangeListener(a());
        dfO_(healthMultiNumberPicker, handler);
    }

    private HealthMultiNumberPicker.OnValueChangeListener a() {
        return new HealthMultiNumberPicker.OnValueChangeListener() { // from class: oqz
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public final void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
                RopeSkippingTargetDialog.this.a(i, healthMultiNumberPicker, i2, i3);
            }
        };
    }

    public /* synthetic */ void a(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
        LogUtil.a("Track_RopeSkippingTargetDialog", "onValueChange() pickerIndex: ", Integer.valueOf(i), ", oldVal: ", Integer.valueOf(i2), ", newVal: ", Integer.valueOf(i3), ", numberPicker: ", healthMultiNumberPicker);
        if (i == 0) {
            if (i3 == 2) {
                healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(0, 0, this.b.getResources().getString(R$string.IDS_band_data_sleep_unit_m)), 0);
                healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, 0, this.b.getResources().getString(R$string.IDS_second)), 0);
            } else if (i3 == 0) {
                healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(1, 59, this.b.getResources().getString(R$string.IDS_band_data_sleep_unit_m)), 0);
                healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, 59, this.b.getResources().getString(R$string.IDS_second)), 0);
            } else {
                healthMultiNumberPicker.setDisplayedValues(1, healthMultiNumberPicker.d(0, 59, this.b.getResources().getString(R$string.IDS_band_data_sleep_unit_m)), 0);
                healthMultiNumberPicker.setDisplayedValues(2, healthMultiNumberPicker.d(0, 59, this.b.getResources().getString(R$string.IDS_second)), 0);
            }
        }
    }

    private void dfO_(final HealthMultiNumberPicker healthMultiNumberPicker, final Handler handler) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.b);
        builder.d(R.string._2130843700_res_0x7f021834).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                RopeSkippingTargetDialog.this.f9555a = selectedLocations[0];
                RopeSkippingTargetDialog.this.j = selectedLocations[1];
                RopeSkippingTargetDialog.this.h = selectedLocations[2];
                if (RopeSkippingTargetDialog.this.f9555a == 0) {
                    RopeSkippingTargetDialog.this.j++;
                }
                LogUtil.a("Track_RopeSkippingTargetDialog", "custom time onClick() mDurationHour: ", Integer.valueOf(RopeSkippingTargetDialog.this.f9555a), ", mDurationMinute: ", Integer.valueOf(RopeSkippingTargetDialog.this.j), ", mDurationSecond: ", Integer.valueOf(RopeSkippingTargetDialog.this.h));
                RopeSkippingTargetDialog.this.o = (r0.f9555a * 3600) + (RopeSkippingTargetDialog.this.j * 60) + RopeSkippingTargetDialog.this.h;
                LogUtil.a("Track_RopeSkippingTargetDialog", "custom time mTargetType: ", Integer.valueOf(RopeSkippingTargetDialog.this.n), ", mTargetValue: ", Float.valueOf(RopeSkippingTargetDialog.this.o));
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.arg1 = RopeSkippingTargetDialog.this.n;
                obtainMessage.arg2 = 2;
                obtainMessage.obj = Float.valueOf(RopeSkippingTargetDialog.this.o);
                handler.sendMessage(obtainMessage);
                RopeSkippingTargetDialog.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkippingTargetDialog.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.d = e2;
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dfQ_(Context context, int i, Handler handler) {
        if (context == null) {
            LogUtil.h("Track_RopeSkippingTargetDialog", "context is null");
            return;
        }
        SportCustomTargetDialog.b bVar = new SportCustomTargetDialog.b(context, handler);
        bVar.d(owp.a(this.b, this.l, i));
        SportCustomTargetDialog d = bVar.d(i, this.l);
        if (d != null) {
            d.show();
        }
    }

    public String a(int i) {
        Context context = this.b;
        if (context == null) {
            ReleaseLogUtil.c("Track_RopeSkippingTargetDialog", "getTargetString() mContext is null");
            return "";
        }
        if (i == 0) {
            return context.getResources().getString(R.string._2130839988_res_0x7f0209b4);
        }
        if (i == 5) {
            return context.getResources().getString(R.string._2130839989_res_0x7f0209b5);
        }
        return context.getResources().getString(R.string._2130840000_res_0x7f0209c0);
    }
}

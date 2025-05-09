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
import com.huawei.ui.homehealth.runcard.trackfragments.SportCustomTargetDialog;
import defpackage.ixx;
import defpackage.koq;
import defpackage.owp;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class SportTargetDialog {
    private int[] f;
    private CustomViewDialog i;
    private Context j;
    private List<String> l;
    private Map<String, ArrayList<String>> m;
    private int n;
    private float[] o;
    private int[] s;

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f9576a = {1.0f, 3.0f, 5.0f, 10.0f, 21.0975f, 42.195f};
    private static final float[] d = {5.0f, 20.0f, 30.0f, 40.0f, 120.0f, 180.0f};
    private static final float[] e = {1.0f, 3.0f, 5.0f, 10.0f, 20.0f, 40.0f};
    private static final int[] g = {10, 20, 30, 60, 120, 180};
    private static final int[] h = {30, 60, 90, 120, 150, 180};
    private static final int[] b = {100, 200, 300, 500, 600, 800};
    private static final int[] c = {50, 100, 200, 300, 500, 600};
    private int k = 0;
    private float q = -1.0f;

    private int c(int i) {
        if (i == 0) {
            return 2;
        }
        if (i != 1) {
            return i != 2 ? 0 : 3;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i) {
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? -1 : 2;
        }
        return 0;
    }

    public SportTargetDialog(Context context) {
        this.j = context;
    }

    private List<String> e() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(d(-1));
        arrayList.add(d(1));
        arrayList.add(d(0));
        arrayList.add(d(2));
        return arrayList;
    }

    private Map<String, ArrayList<String>> c(HealthMultiNumberPicker healthMultiNumberPicker, int i) {
        if (healthMultiNumberPicker == null) {
            healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        }
        b(i);
        ArrayList<String> arrayList = new ArrayList<>(7);
        ArrayList<String> arrayList2 = new ArrayList<>(7);
        ArrayList arrayList3 = new ArrayList(7);
        arrayList.add(this.j.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        arrayList2.add(this.j.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        arrayList3.add(this.j.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        ArrayList<String> b2 = b(arrayList, this.o);
        ArrayList<String> b3 = b(healthMultiNumberPicker, arrayList2, this.s);
        Collections.addAll(arrayList3, healthMultiNumberPicker.c(this.f, this.j.getResources().getString(R.string._2130837659_res_0x7f02009b)));
        ArrayList arrayList4 = new ArrayList(1);
        arrayList4.add("");
        HashMap hashMap = new HashMap();
        hashMap.put(d(-1), arrayList4);
        hashMap.put(d(1), b2);
        hashMap.put(d(0), b3);
        hashMap.put(d(2), arrayList3);
        return hashMap;
    }

    private void b(int i) {
        if (i == 257 || i == 282 || i == 260) {
            this.o = e;
            this.f = c;
            this.s = g;
        } else if (i == 259) {
            this.o = d;
            this.f = b;
            this.s = h;
        } else {
            this.o = f9576a;
            this.f = b;
            this.s = g;
        }
    }

    private ArrayList<String> b(ArrayList<String> arrayList, float[] fArr) {
        String str;
        if (arrayList == null || fArr.length == 0) {
            LogUtil.h("Track_SportTargetDialog", "distances array is null");
            return arrayList;
        }
        if (UnitUtil.h()) {
            String string = this.j.getResources().getString(R.string._2130841383_res_0x7f020f27);
            for (float f : fArr) {
                arrayList.add(UnitUtil.e(UnitUtil.e(f, 3), 1, 2) + string);
            }
        } else {
            String string2 = this.j.getResources().getString(R.string._2130837660_res_0x7f02009c);
            for (double d2 : fArr) {
                if (Math.abs(d2 - 42.195d) < 9.999999974752427E-7d) {
                    str = this.j.getResources().getString(R.string._2130841793_res_0x7f0210c1);
                } else if (Math.abs(d2 - 21.0975d) < 9.999999974752427E-7d) {
                    str = this.j.getResources().getString(R.string._2130841792_res_0x7f0210c0);
                } else {
                    str = UnitUtil.e(d2, 1, 0) + string2;
                }
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private ArrayList<String> b(HealthMultiNumberPicker healthMultiNumberPicker, ArrayList<String> arrayList, int[] iArr) {
        if (arrayList == null || iArr.length == 0) {
            LogUtil.h("Track_SportTargetDialog", "times array is null");
            return arrayList;
        }
        for (int i : iArr) {
            arrayList.add(healthMultiNumberPicker.d(i, this.j.getResources().getQuantityString(R.plurals._2130903233_res_0x7f0300c1, i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float d(int i, int i2) {
        if (i == 0) {
            return 0.0f;
        }
        if (i2 == 0) {
            return this.s[i - 1] * 60;
        }
        if (i2 == 1) {
            return this.o[i - 1];
        }
        if (i2 != 2) {
            return 0.0f;
        }
        return this.f[i - 1] * 1000;
    }

    private int a(int i, float f) {
        if (i == 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.s;
                if (i2 >= iArr.length) {
                    return 0;
                }
                if (((int) f) / 60 == iArr[i2]) {
                    return i2 + 1;
                }
                i2++;
            }
        } else if (i == 1) {
            int i3 = 0;
            while (true) {
                float[] fArr = this.o;
                if (i3 >= fArr.length) {
                    return 0;
                }
                if (Math.abs(f - fArr[i3]) < 1.0E-6f) {
                    return i3 + 1;
                }
                i3++;
            }
        } else {
            if (i != 2) {
                return 0;
            }
            int i4 = 0;
            while (true) {
                int[] iArr2 = this.f;
                if (i4 >= iArr2.length) {
                    return 0;
                }
                if (((int) f) / 1000 == iArr2[i4]) {
                    return i4 + 1;
                }
                i4++;
            }
        }
    }

    public void dgr_(int i, Handler handler) {
        this.n = i;
        this.k = owp.e(this.j, i);
        this.q = owp.a(this.j, this.n);
        LogUtil.a("Track_SportTargetDialog", "showTargetChoiceWheelPickerDialog() mTargetType: ", Integer.valueOf(this.k), ", mTargetValue: ", Float.valueOf(this.q));
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        this.l = e();
        this.m = c(healthMultiNumberPicker, i);
        healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, false});
        List<String> list = this.l;
        healthMultiNumberPicker.setDisplayedValues(0, (String[]) list.toArray(new String[list.size()]), 0);
        int c2 = c(this.k);
        int a2 = a(this.k, this.q);
        if (koq.b(this.l, c2)) {
            return;
        }
        ArrayList<String> arrayList = this.m.get(this.l.get(c2));
        healthMultiNumberPicker.setDisplayedValues(1, (String[]) arrayList.toArray(new String[arrayList.size()]), 0);
        healthMultiNumberPicker.a(new int[]{c2, a2}, arrayList.size());
        healthMultiNumberPicker.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportTargetDialog.3
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i2, HealthMultiNumberPicker healthMultiNumberPicker2, int i3, int i4) {
                if (i2 == 0) {
                    if (i4 < SportTargetDialog.this.l.size() && SportTargetDialog.this.m.containsKey(SportTargetDialog.this.l.get(i4))) {
                        ArrayList arrayList2 = (ArrayList) SportTargetDialog.this.m.get(SportTargetDialog.this.l.get(i4));
                        healthMultiNumberPicker2.setDisplayedValues(1, (String[]) arrayList2.toArray(new String[arrayList2.size()]), 0);
                    } else {
                        LogUtil.h("Track_SportTargetDialog", "the sport target is not valid");
                    }
                }
            }
        });
        dgp_(healthMultiNumberPicker, handler);
    }

    private void dgp_(final HealthMultiNumberPicker healthMultiNumberPicker, final Handler handler) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.j);
        builder.d(R.string._2130842526_res_0x7f02139e).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportTargetDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
                SportTargetDialog sportTargetDialog = SportTargetDialog.this;
                sportTargetDialog.k = sportTargetDialog.e(selectedLocations[0]);
                SportTargetDialog sportTargetDialog2 = SportTargetDialog.this;
                sportTargetDialog2.q = sportTargetDialog2.d(selectedLocations[1], sportTargetDialog2.k);
                if (selectedLocations[0] == 0 || selectedLocations[1] != 0) {
                    LogUtil.a("Track_SportTargetDialog", "mTargetType: ", Integer.valueOf(SportTargetDialog.this.k), ", mTargetValue: ", Float.valueOf(SportTargetDialog.this.q));
                    Message obtainMessage = handler.obtainMessage();
                    obtainMessage.arg1 = SportTargetDialog.this.k;
                    obtainMessage.arg2 = 2;
                    obtainMessage.obj = Float.valueOf(SportTargetDialog.this.q);
                    handler.sendMessage(obtainMessage);
                    SportTargetDialog.this.c();
                } else if (SportTargetDialog.this.k != 1) {
                    if (SportTargetDialog.this.k != 0) {
                        if (SportTargetDialog.this.k == 2) {
                            SportTargetDialog sportTargetDialog3 = SportTargetDialog.this;
                            sportTargetDialog3.dgq_(sportTargetDialog3.j, SportTargetDialog.this.k, handler);
                        } else {
                            LogUtil.a("Track_SportTargetDialog", "unknown target type");
                        }
                    } else {
                        SportTargetDialog sportTargetDialog4 = SportTargetDialog.this;
                        sportTargetDialog4.dgq_(sportTargetDialog4.j, SportTargetDialog.this.k, handler);
                    }
                } else {
                    SportTargetDialog sportTargetDialog5 = SportTargetDialog.this;
                    sportTargetDialog5.dgq_(sportTargetDialog5.j, SportTargetDialog.this.k, handler);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.SportTargetDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.i = e2;
        e2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("goalType", Integer.valueOf(this.k));
        if (!Utils.o()) {
            hashMap.put("goalValue", Integer.valueOf((int) this.q));
            hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(this.n));
        }
        ixx.d().d(this.j, AnalyticsValue.BI_TRACK_SPORT_GOAL_ACTION_KEY.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dgq_(Context context, int i, Handler handler) {
        if (context == null) {
            LogUtil.h("Track_SportTargetDialog", "context is null");
            return;
        }
        SportCustomTargetDialog.b bVar = new SportCustomTargetDialog.b(context, handler);
        bVar.d(owp.a(this.j, this.n, i));
        SportCustomTargetDialog d2 = bVar.d(i, this.n);
        if (d2 != null) {
            d2.show();
        }
    }

    public String d(int i) {
        if (i == 0) {
            return this.j.getResources().getString(R.string._2130842047_res_0x7f0211bf);
        }
        if (i == 1) {
            return this.j.getResources().getString(R.string._2130842046_res_0x7f0211be);
        }
        if (i == 2) {
            return this.j.getResources().getString(R.string._2130842048_res_0x7f0211c0);
        }
        return this.j.getResources().getString(R.string._2130842655_res_0x7f02141f);
    }
}

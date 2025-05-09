package com.huawei.ui.homehealth.runcard.utils;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class TargetChoicePickerUtils {
    private Context f;
    private int[] g;
    private float[] j;
    private int[] k;
    private static final float[] d = {1.0f, 3.0f, 5.0f, 10.0f, 21.0975f, 42.195f};

    /* renamed from: a, reason: collision with root package name */
    private static final float[] f9584a = {5.0f, 20.0f, 30.0f, 40.0f, 120.0f, 180.0f};
    private static final float[] b = {1.0f, 3.0f, 5.0f, 10.0f, 20.0f, 40.0f};
    private static final int[] h = {10, 20, 30, 60, 120, 180};
    private static final int[] i = {30, 60, 90, 120, 150, 180};
    private static final int[] c = {100, 200, 300, 500, 600, 800};
    private static final int[] e = {50, 100, 200, 300, 500, 600};

    public int c(int i2) {
        if (i2 == 1) {
            return 1;
        }
        if (i2 != 2) {
            return i2 != 3 ? -1 : 2;
        }
        return 0;
    }

    public int d(int i2) {
        if (i2 == 0) {
            return 2;
        }
        if (i2 != 1) {
            return i2 != 2 ? 0 : 3;
        }
        return 1;
    }

    public TargetChoicePickerUtils(Context context) {
        this.f = context;
    }

    public List<String> a() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(this.f.getResources().getString(R.string._2130842655_res_0x7f02141f));
        arrayList.add(this.f.getResources().getString(R.string._2130842046_res_0x7f0211be));
        arrayList.add(this.f.getResources().getString(R.string._2130842047_res_0x7f0211bf));
        arrayList.add(this.f.getResources().getString(R.string._2130842048_res_0x7f0211c0));
        return arrayList;
    }

    public Map<String, ArrayList<String>> b(HealthMultiNumberPicker healthMultiNumberPicker, int i2) {
        if (healthMultiNumberPicker == null) {
            healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        }
        e(i2);
        ArrayList<String> arrayList = new ArrayList<>(7);
        ArrayList arrayList2 = new ArrayList(7);
        ArrayList<String> arrayList3 = new ArrayList<>(7);
        arrayList.add(this.f.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        arrayList2.add(this.f.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        arrayList3.add(this.f.getResources().getString(R.string._2130841788_res_0x7f0210bc));
        ArrayList<String> b2 = b(arrayList, this.j);
        Collections.addAll(arrayList2, healthMultiNumberPicker.c(this.g, this.f.getResources().getString(R.string._2130837659_res_0x7f02009b)));
        ArrayList<String> b3 = b(healthMultiNumberPicker, arrayList3, this.k);
        ArrayList arrayList4 = new ArrayList(1);
        arrayList4.add("");
        HashMap hashMap = new HashMap();
        hashMap.put(this.f.getResources().getString(R.string._2130842655_res_0x7f02141f), arrayList4);
        hashMap.put(this.f.getResources().getString(R.string._2130842046_res_0x7f0211be), b2);
        hashMap.put(this.f.getResources().getString(R.string._2130842047_res_0x7f0211bf), b3);
        hashMap.put(this.f.getResources().getString(R.string._2130842048_res_0x7f0211c0), arrayList2);
        return hashMap;
    }

    public float e(int i2, int i3) {
        if (i2 == 0) {
            return 0.0f;
        }
        if (i3 == 0) {
            return this.k[i2 - 1] * 60;
        }
        if (i3 == 1) {
            return this.j[i2 - 1];
        }
        if (i3 != 2) {
            return 0.0f;
        }
        return this.g[i2 - 1] * 1000;
    }

    public int a(int i2, double d2) {
        if (i2 == 0) {
            int i3 = 0;
            while (true) {
                int[] iArr = this.k;
                if (i3 >= iArr.length) {
                    return 0;
                }
                if (((int) d2) / 60 == iArr[i3]) {
                    return i3 + 1;
                }
                i3++;
            }
        } else if (i2 == 1) {
            int i4 = 0;
            while (true) {
                if (i4 >= this.j.length) {
                    return 0;
                }
                if (Math.abs(d2 - r2[i4]) < 9.999999974752427E-7d) {
                    return i4 + 1;
                }
                i4++;
            }
        } else {
            if (i2 != 2) {
                return 0;
            }
            int i5 = 0;
            while (true) {
                int[] iArr2 = this.g;
                if (i5 >= iArr2.length) {
                    return 0;
                }
                if (((int) d2) / 1000 == iArr2[i5]) {
                    return i5 + 1;
                }
                i5++;
            }
        }
    }

    public static String a(double d2, Context context) {
        if (LanguageUtil.m(context)) {
            return b(d2);
        }
        return UnitUtil.e(d2, 1, 2);
    }

    public static String b(double d2) {
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(d2);
    }

    private void e(int i2) {
        if (i2 == 257 || i2 == 282 || i2 == 260) {
            this.j = b;
            this.g = e;
            this.k = h;
        } else if (i2 == 259) {
            this.j = f9584a;
            this.g = c;
            this.k = i;
        } else {
            this.j = d;
            this.g = c;
            this.k = h;
        }
    }

    private ArrayList<String> b(ArrayList<String> arrayList, float[] fArr) {
        String str;
        if (arrayList == null || fArr.length == 0) {
            LogUtil.h("TargetChoicePickerUtils", "distances array is null");
            return arrayList;
        }
        if (UnitUtil.h()) {
            String string = this.f.getResources().getString(R.string._2130841383_res_0x7f020f27);
            for (float f : fArr) {
                arrayList.add(UnitUtil.e(UnitUtil.e(f, 3), 1, 2) + string);
            }
        } else {
            String string2 = this.f.getResources().getString(R.string._2130837660_res_0x7f02009c);
            for (double d2 : fArr) {
                if (Math.abs(d2 - 42.195d) < 9.999999974752427E-7d) {
                    str = this.f.getResources().getString(R.string._2130841793_res_0x7f0210c1);
                } else if (Math.abs(d2 - 21.0975d) < 9.999999974752427E-7d) {
                    str = this.f.getResources().getString(R.string._2130841792_res_0x7f0210c0);
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
            LogUtil.h("TargetChoicePickerUtils", "times array is null");
            return arrayList;
        }
        for (int i2 : iArr) {
            arrayList.add(healthMultiNumberPicker.d(i2, this.f.getResources().getQuantityString(R.plurals._2130903233_res_0x7f0300c1, i2)));
        }
        return arrayList;
    }
}

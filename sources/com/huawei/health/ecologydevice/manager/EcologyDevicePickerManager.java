package com.huawei.health.ecologydevice.manager;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.callback.PickerChooseCallback;
import com.huawei.health.ecologydevice.manager.EcologyDevicePickerManager;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.edittext.HealthErrorTipTextLayout;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.UnitUtil;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class EcologyDevicePickerManager implements HealthMultiNumberPicker.OnValueChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private final Context f2296a;
    private final String ab;
    private CustomViewDialog b;
    private final double c;
    private final double d;
    private double e;
    private int f;
    private int g;
    private int h;
    private double i;
    private final int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private final int o;
    private CustomViewDialog p;
    private int q;
    private final int r;
    private int s;
    private int t;
    private int u;
    private int[] v = {0, 0};
    private final PickerChooseCallback w;
    private double x;
    private CustomViewDialog y;

    private boolean b(int i, int i2, int i3, int i4) {
        return (i == i3 && i2 == i4) ? false : true;
    }

    public EcologyDevicePickerManager(Builder builder) {
        this.f2296a = builder.d;
        this.d = builder.f2297a;
        this.c = builder.c;
        this.e = builder.b;
        this.ab = builder.g;
        this.j = builder.e;
        this.o = builder.f;
        this.r = builder.h;
        this.i = builder.i;
        this.w = builder.j;
    }

    public void b(final int[] iArr, final int i, final int i2, int i3) {
        if (this.f2296a == null) {
            LogUtil.h("EcologyDevicePickerManager", "showNormalPickerDialog() mContext == null");
            return;
        }
        CustomViewDialog customViewDialog = this.y;
        if (customViewDialog != null) {
            customViewDialog.show();
            return;
        }
        if (iArr == null || iArr.length == 0) {
            LogUtil.h("EcologyDevicePickerManager", "showNormalPickerDialog() numbers array is null or length is 0");
            return;
        }
        this.u = i;
        String[] e = e(iArr, i2);
        if (i3 > e.length - 1 || i3 < 0) {
            LogUtil.h("EcologyDevicePickerManager", "showNormalPickerDialog() defalutSelectIndex is outOfIndex");
            i3 = 0;
        }
        HealthNumberPicker e2 = e(e, i3);
        e2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        e2.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: dce
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public final void onValuePicked(int i4, int i5) {
                EcologyDevicePickerManager.this.e(i4, i5);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f2296a);
        builder.czg_(e2);
        builder.a(this.ab);
        builder.cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: dcc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EcologyDevicePickerManager.this.TD_(i, i2, iArr, view);
            }
        });
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: dcd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EcologyDevicePickerManager.this.TE_(view);
            }
        });
        CustomViewDialog e3 = builder.e();
        this.y = e3;
        e3.show();
    }

    public /* synthetic */ void e(int i, int i2) {
        this.q = i2;
    }

    public /* synthetic */ void TD_(int i, int i2, int[] iArr, View view) {
        c(this.y);
        if (this.q != 0) {
            a(iArr, i);
        } else if (i == 0) {
            c();
        } else {
            b(i2);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void TE_(View view) {
        c(this.y);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(int[] iArr, int i) {
        int intValue;
        PickerChooseCallback pickerChooseCallback = this.w;
        if (pickerChooseCallback != null) {
            double d = iArr[this.q - 1];
            this.x = d;
            if (d == -2.0d) {
                this.e = 21.0975d;
            } else if (d == -3.0d) {
                this.e = 42.195d;
            } else {
                this.e = d;
            }
            this.i = 60.0d * d;
            if (i == 1) {
                pickerChooseCallback.onValueSelected(Double.valueOf(d), this.q);
                return;
            }
            if (i == 0) {
                intValue = Double.valueOf(d).intValue() * 60;
            } else {
                intValue = Double.valueOf(d).intValue();
            }
            pickerChooseCallback.onValueSelected(Integer.valueOf(intValue), this.q);
        }
    }

    private HealthNumberPicker e(String[] strArr, int i) {
        HealthNumberPicker healthNumberPicker = new HealthNumberPicker(this.f2296a);
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMaxValue(strArr.length - 1);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setValue(i);
        this.q = i;
        healthNumberPicker.setWrapSelectorWheel(false);
        return healthNumberPicker;
    }

    private String[] e(int[] iArr, int i) {
        String[] strArr = new String[iArr.length + 1];
        strArr[0] = this.f2296a.getString(R.string._2130841788_res_0x7f0210bc);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 == -2) {
                strArr[i2 + 1] = this.f2296a.getString(R.string._2130841792_res_0x7f0210c0);
            } else if (i3 == -3) {
                strArr[i2 + 1] = this.f2296a.getString(R.string._2130841793_res_0x7f0210c1);
            } else {
                Resources resources = this.f2296a.getResources();
                int i4 = iArr[i2];
                strArr[i2 + 1] = resources.getQuantityString(i, i4, Integer.valueOf(i4));
            }
        }
        return strArr;
    }

    private void b(int i) {
        View inflate = LayoutInflater.from(this.f2296a).inflate(R.layout.skiping_custom_target_item, (ViewGroup) null);
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit);
        b(healthEditText);
        final HealthErrorTipTextLayout healthErrorTipTextLayout = (HealthErrorTipTextLayout) inflate.findViewById(R.id.error_tip_text_layout);
        Resources resources = this.f2296a.getResources();
        Object[] objArr = new Object[3];
        objArr[0] = UnitUtil.e(this.d, 1, this.u == 1 ? 1 : 0);
        objArr[1] = Integer.valueOf(Double.valueOf(this.c).intValue());
        objArr[2] = this.f2296a.getResources().getQuantityString(i, Double.valueOf(this.c).intValue(), "");
        healthErrorTipTextLayout.setErrorImmediately(resources.getString(R.string._2130842530_res_0x7f0213a2, objArr));
        healthErrorTipTextLayout.setErrorColor(false);
        final CustomViewDialog.Builder Tx_ = Tx_(this.f2296a, inflate);
        this.b.show();
        a(Tx_, healthErrorTipTextLayout);
        healthEditText.addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.ecologydevice.manager.EcologyDevicePickerManager.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable == null) {
                    return;
                }
                if (EcologyDevicePickerManager.this.u == 1) {
                    EcologyDevicePickerManager.this.Ty_(editable);
                }
                String obj = editable.toString();
                EcologyDevicePickerManager.this.x = CommonUtil.a(obj);
                EcologyDevicePickerManager.this.a(Tx_, healthErrorTipTextLayout);
            }
        });
    }

    private void b(HealthEditText healthEditText) {
        String string;
        if (this.u != 1) {
            healthEditText.setInputType(2);
        }
        double d = this.e;
        if (d == 0.0d && this.x == 0.0d) {
            if (this.u == 1) {
                string = this.f2296a.getString(R.string._2130850490_res_0x7f0232ba);
            } else {
                string = this.f2296a.getResources().getString(R.string._2130843704_res_0x7f021838, Integer.valueOf(WorkoutRecord.COURSE_TYPE_FREE_RUN));
            }
            healthEditText.setHint(string);
        } else {
            double d2 = this.x;
            if (d2 == 0.0d) {
                this.x = d;
            } else if (d2 == -2.0d) {
                this.x = 21.0975d;
            } else if (d2 == -3.0d) {
                this.x = 42.195d;
            }
            healthEditText.setText(this.u == 1 ? String.valueOf(this.x) : String.valueOf(Double.valueOf(this.x).intValue()));
        }
        int length = String.valueOf(this.c).length();
        InputFilter[] inputFilterArr = new InputFilter[1];
        if (this.u == 1) {
            length++;
        }
        inputFilterArr[0] = new InputFilter.LengthFilter(length);
        healthEditText.setFilters(inputFilterArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CustomViewDialog.Builder builder, HealthErrorTipTextLayout healthErrorTipTextLayout) {
        double d = this.x;
        boolean z = d <= this.c && d >= this.d;
        healthErrorTipTextLayout.setErrorColor(!z);
        builder.a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Ty_(Editable editable) {
        String obj = editable.toString();
        if (obj.length() > 1 && !e(obj)) {
            editable.delete(obj.length() - 1, obj.length());
            return;
        }
        int c = c(obj);
        if (c >= 0 && (obj.length() - c) - 1 > 2) {
            editable.delete(c + 3, c + 4);
        }
    }

    private static boolean e(String str) {
        return a(str) || d(str);
    }

    private static boolean a(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static boolean d(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            int i2 = i + 1;
            if (!a(str.substring(i, i2))) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    private CustomViewDialog.Builder Tx_(Context context, View view) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.czg_(view);
        builder.d(this.j);
        builder.cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: dbz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                EcologyDevicePickerManager.this.Tz_(view2);
            }
        });
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: dbx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                EcologyDevicePickerManager.this.TA_(view2);
            }
        });
        this.b = builder.e();
        return builder;
    }

    public /* synthetic */ void Tz_(View view) {
        PickerChooseCallback pickerChooseCallback = this.w;
        if (pickerChooseCallback != null) {
            double d = this.x;
            this.e = d;
            if (this.u == 1) {
                pickerChooseCallback.onValueSelected(Double.valueOf(d), this.q);
            } else {
                pickerChooseCallback.onValueSelected(Integer.valueOf(Double.valueOf(d).intValue()), this.q);
            }
        }
        c(this.b);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void TA_(View view) {
        c(this.b);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c() {
        if (this.f2296a == null) {
            LogUtil.h("EcologyDevicePickerManager", "showMutilPickerDialog() mContext == null");
            return;
        }
        if (!e()) {
            LogUtil.b("EcologyDevicePickerManager", "params is error");
            return;
        }
        a();
        final HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        if (this.o < 3600) {
            j(healthMultiNumberPicker);
        } else {
            f(healthMultiNumberPicker);
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f2296a);
        builder.a(this.ab).czg_(healthMultiNumberPicker).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: dca
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EcologyDevicePickerManager.this.TB_(healthMultiNumberPicker, view);
            }
        }).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: dcb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EcologyDevicePickerManager.this.TC_(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.p = e;
        e.show();
    }

    public /* synthetic */ void TB_(HealthMultiNumberPicker healthMultiNumberPicker, View view) {
        int c;
        if (this.o < 3600) {
            c = b(healthMultiNumberPicker);
        } else {
            c = c(healthMultiNumberPicker);
        }
        if (c == 0) {
            nrh.b(this.f2296a, R.string._2130842087_res_0x7f0211e7);
        } else {
            PickerChooseCallback pickerChooseCallback = this.w;
            if (pickerChooseCallback != null) {
                this.i = c;
                pickerChooseCallback.onValueSelected(Integer.valueOf(c), 0);
            }
            c(this.p);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void TC_(View view) {
        c(this.p);
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean e() {
        int i;
        int i2 = this.r;
        if (i2 > 0 && (i = this.o) > 0) {
            double d = this.i;
            if (d > 0.0d) {
                if (i2 < 86400 && i < 86400) {
                    return i2 <= i && d <= ((double) i) && d >= ((double) i2);
                }
                LogUtil.b("EcologyDevicePickerManager", "mMinTimeMillis or mMaxTimeMillis bigger than one day");
                return false;
            }
        }
        LogUtil.b("EcologyDevicePickerManager", "mMinTimeMillis or mMaxTimeMillis or mDefaultMillis <= 0");
        return false;
    }

    private void j(HealthMultiNumberPicker healthMultiNumberPicker) {
        this.v = new int[]{0, 0};
        HashMap hashMap = new HashMap(2);
        String[] d = healthMultiNumberPicker.d(this.t, this.m, this.f2296a.getResources().getString(R.string._2130841436_res_0x7f020f5c));
        this.v[0] = e(this.g, d);
        String[] h = h(healthMultiNumberPicker);
        this.v[1] = e(this.h, h);
        hashMap.put(0, d);
        hashMap.put(1, h);
        healthMultiNumberPicker.setDataContent(2, hashMap, new boolean[]{true, true}, this.v);
        healthMultiNumberPicker.setOnValueChangeListener(this);
    }

    private String[] h(HealthMultiNumberPicker healthMultiNumberPicker) {
        int i = this.g;
        return healthMultiNumberPicker.d(i == this.t ? this.s : 0, i == this.m ? this.l : 59, this.f2296a.getString(R.string._2130843202_res_0x7f021642));
    }

    private void f(HealthMultiNumberPicker healthMultiNumberPicker) {
        this.v = new int[3];
        String[] d = healthMultiNumberPicker.d(this.n, this.k, this.f2296a.getString(R.string._2130841434_res_0x7f020f5a));
        this.v[0] = e(this.f, d);
        String[] g = g(healthMultiNumberPicker);
        this.v[1] = e(this.g, g);
        String[] i = i(healthMultiNumberPicker);
        this.v[2] = e(this.h, i);
        HashMap hashMap = new HashMap(3);
        hashMap.put(0, d);
        hashMap.put(1, g);
        hashMap.put(2, i);
        LogUtil.a("EcologyDevicePickerManager", "showThreePickerDialog() mThreePickerLocation", Arrays.toString(this.v));
        healthMultiNumberPicker.setDataContent(3, hashMap, new boolean[]{true, true, true}, this.v);
        healthMultiNumberPicker.setOnValueChangeListener(this);
    }

    private String[] g(HealthMultiNumberPicker healthMultiNumberPicker) {
        int i = this.f;
        return healthMultiNumberPicker.d(i == this.n ? this.t : 0, i == this.k ? this.m : 59, this.f2296a.getString(R.string._2130841436_res_0x7f020f5c));
    }

    private String[] i(HealthMultiNumberPicker healthMultiNumberPicker) {
        int i = this.f;
        return healthMultiNumberPicker.d((i == this.n && this.g == this.t) ? this.s : 0, (i == this.k && this.g == this.m) ? this.l : 59, this.f2296a.getString(R.string._2130843202_res_0x7f021642));
    }

    private void a() {
        this.k = d(this.o);
        this.m = e(this.o);
        this.l = a(this.o);
        LogUtil.a("EcologyDevicePickerManager", "maxHour is", Integer.valueOf(this.k), " mMaxMinute is ", Integer.valueOf(this.m), " mMaxSecond is ", Integer.valueOf(this.l));
        this.n = d(this.r);
        this.t = e(this.r);
        this.s = a(this.r);
        LogUtil.a("EcologyDevicePickerManager", "mMinHour is", Integer.valueOf(this.n), " mMinMinute is ", Integer.valueOf(this.t), " mMinSecond is ", Integer.valueOf(this.s));
        this.f = d(Double.valueOf(this.i).intValue());
        this.g = e(Double.valueOf(this.i).intValue());
        this.h = a(Double.valueOf(this.i).intValue());
        LogUtil.a("EcologyDevicePickerManager", "mDefaultHour is", Integer.valueOf(this.f), " mDefaultMinute is ", Integer.valueOf(this.g), " mDefaultSecond is ", Integer.valueOf(this.h));
    }

    private int d(int i) {
        return i / 3600;
    }

    private int e(int i) {
        return (i % 3600) / 60;
    }

    private int a(int i) {
        return i % 60;
    }

    private int b(HealthMultiNumberPicker healthMultiNumberPicker) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 2) {
            LogUtil.c("EcologyDevicePickerManager", "the minute and second SelectedLocation Value is empty");
            return 0;
        }
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        this.v = selectedLocations;
        return (CommonUtil.h(healthMultiNumberPicker.e(0)[i]) * 60) + CommonUtil.h(healthMultiNumberPicker.e(1)[i2]);
    }

    private int c(HealthMultiNumberPicker healthMultiNumberPicker) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        if (selectedLocations.length < 3) {
            LogUtil.c("EcologyDevicePickerManager", "hour minute second SelectedLocation Value is empty");
            return 0;
        }
        LogUtil.a("EcologyDevicePickerManager", "formateThreePickerSelectTime selectedLocations is ", Arrays.toString(selectedLocations));
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        int i3 = selectedLocations[2];
        this.v = selectedLocations;
        return (CommonUtil.h(healthMultiNumberPicker.e(0)[i]) * 3600) + (CommonUtil.h(healthMultiNumberPicker.e(1)[i2]) * 60) + CommonUtil.h(healthMultiNumberPicker.e(2)[i3]);
    }

    private void a(HealthMultiNumberPicker healthMultiNumberPicker) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        String[] e = healthMultiNumberPicker.e(0);
        int i = selectedLocations[0];
        if (i == 0) {
            c(healthMultiNumberPicker, 59, this.s);
        } else if (i == e.length - 1) {
            c(healthMultiNumberPicker, this.l, 0);
        } else {
            c(healthMultiNumberPicker, 59, 0);
        }
    }

    private void c(HealthMultiNumberPicker healthMultiNumberPicker, int i, int i2) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        String[] e = healthMultiNumberPicker.e(1);
        int h = CommonUtil.h(e[0]);
        int h2 = CommonUtil.h(e[e.length - 1]);
        int i3 = selectedLocations[1];
        if (b(h2, h, i, i2)) {
            String[] d = healthMultiNumberPicker.d(i2, i, this.f2296a.getString(R.string._2130843202_res_0x7f021642));
            healthMultiNumberPicker.setDisplayedValues(1, d, e(CommonUtil.h(e[i3]), d));
        }
    }

    private void d(HealthMultiNumberPicker healthMultiNumberPicker) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        String[] e = healthMultiNumberPicker.e(0);
        String[] e2 = healthMultiNumberPicker.e(1);
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        if (CommonUtil.h(e2[i2]) == this.t && i == 0) {
            d(healthMultiNumberPicker, 2, 59, this.s);
        } else if (CommonUtil.h(e2[i2]) == this.m && i == e.length - 1) {
            d(healthMultiNumberPicker, 2, this.l, 0);
        } else {
            d(healthMultiNumberPicker, 2, 59, 0);
        }
    }

    private void e(HealthMultiNumberPicker healthMultiNumberPicker) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        String[] e = healthMultiNumberPicker.e(0);
        String[] e2 = healthMultiNumberPicker.e(1);
        int i = selectedLocations[0];
        int i2 = selectedLocations[1];
        if (CommonUtil.h(e[i]) == this.k) {
            d(healthMultiNumberPicker, 1, this.m, 0);
            if (CommonUtil.h(e2[i2]) >= this.m) {
                d(healthMultiNumberPicker, 2, this.l, 0);
                return;
            } else {
                d(healthMultiNumberPicker, 2, 59, 0);
                return;
            }
        }
        if (CommonUtil.h(e[i]) == this.n) {
            d(healthMultiNumberPicker, 1, 59, this.t);
            if (CommonUtil.h(e2[i2]) <= this.t) {
                d(healthMultiNumberPicker, 2, 59, this.s);
                return;
            } else {
                d(healthMultiNumberPicker, 2, 59, 0);
                return;
            }
        }
        d(healthMultiNumberPicker, 1, 59, 0);
        d(healthMultiNumberPicker, 2, 59, 0);
    }

    private void d(HealthMultiNumberPicker healthMultiNumberPicker, int i, int i2, int i3) {
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        String[] e = healthMultiNumberPicker.e(1);
        String[] e2 = healthMultiNumberPicker.e(2);
        int i4 = selectedLocations[1];
        int i5 = selectedLocations[2];
        int h = CommonUtil.h(e[e.length - 1]);
        int h2 = CommonUtil.h(e[0]);
        int h3 = CommonUtil.h(e2[e2.length - 1]);
        int h4 = CommonUtil.h(e2[0]);
        if (i == 1) {
            if (b(h, h2, i2, i3)) {
                String[] d = healthMultiNumberPicker.d(i3, i2, this.f2296a.getString(R.string._2130841436_res_0x7f020f5c));
                healthMultiNumberPicker.setDisplayedValues(i, d, e(CommonUtil.h(e[i4]), d));
                return;
            }
            return;
        }
        if (b(h3, h4, i2, i3)) {
            String[] d2 = healthMultiNumberPicker.d(i3, i2, this.f2296a.getString(R.string._2130843202_res_0x7f021642));
            healthMultiNumberPicker.setDisplayedValues(i, d2, e(CommonUtil.h(e2[i5]), d2));
        }
    }

    private int e(int i, String[] strArr) {
        if (i > CommonUtils.h(strArr[strArr.length - 1])) {
            return strArr.length - 1;
        }
        if (i < CommonUtils.h(strArr[0])) {
            return 0;
        }
        return i - CommonUtils.h(strArr[0]);
    }

    private void c(CustomViewDialog customViewDialog) {
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
    }

    @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
    public void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
        if (this.o < 3600) {
            a(healthMultiNumberPicker);
            return;
        }
        if (i == 2) {
            return;
        }
        if (i == 0) {
            e(healthMultiNumberPicker);
        } else if (i == 1) {
            d(healthMultiNumberPicker);
        }
    }

    public static class Builder {
        private double b;
        private final Context d;
        private int e;
        private String g;
        private PickerChooseCallback j;

        /* renamed from: a, reason: collision with root package name */
        private double f2297a = 50.0d;
        private double c = 10000.0d;
        private int f = 86399;
        private int h = 60;
        private int i = 60;

        public Builder(Context context) {
            this.d = context;
        }

        public Builder a(double d) {
            this.f2297a = d;
            return this;
        }

        public Builder e(double d) {
            this.c = d;
            return this;
        }

        public Builder c(double d) {
            this.b = d;
            return this;
        }

        public Builder c(String str) {
            this.g = str;
            return this;
        }

        public Builder e(int i) {
            this.e = i;
            return this;
        }

        public Builder b(int i) {
            this.f = i;
            return this;
        }

        public Builder c(int i) {
            this.h = i;
            return this;
        }

        public Builder a(int i) {
            this.i = i;
            return this;
        }

        public Builder c(PickerChooseCallback pickerChooseCallback) {
            this.j = pickerChooseCallback;
            return this;
        }

        public EcologyDevicePickerManager e() {
            return new EcologyDevicePickerManager(this);
        }
    }
}

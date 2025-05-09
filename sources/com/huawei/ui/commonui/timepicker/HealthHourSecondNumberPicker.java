package com.huawei.ui.commonui.timepicker;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import health.compact.a.CommonUtils;

/* loaded from: classes6.dex */
public class HealthHourSecondNumberPicker extends LinearLayout implements HealthMultiNumberPicker.OnValueChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private int f8959a;
    private int b;
    private HealthMultiNumberPicker c;
    private int d;
    private Context e;
    private LinearLayout f;

    private boolean b(int i, int i2) {
        if (i2 == 1) {
            return true;
        }
        return i2 >= 2 && i == 0;
    }

    private int c(int i, int i2, int i3) {
        if ((i3 < 2 || i2 != 0) && (i3 <= 2 || i2 == i3 - 1)) {
            return i;
        }
        return 59;
    }

    private boolean c(int i, int i2) {
        if (i2 == 1) {
            return true;
        }
        return i2 >= 2 && i == i2 - 1;
    }

    private int d(int i, int i2, int i3) {
        if (i3 >= 2 && i2 == i3 - 1) {
            return 0;
        }
        if (i3 <= 2 || i2 == 0 || i2 == i3 - 1) {
            return i;
        }
        return 0;
    }

    public HealthHourSecondNumberPicker(Context context) {
        super(context);
    }

    public HealthHourSecondNumberPicker(Context context, int i, int i2, int i3) {
        super(context, null);
        if (i3 < i || i3 > i2) {
            LogUtil.b("HealthHourSecondNumberPicker", "setTimeRange, the input parameter is incorrect.");
            return;
        }
        this.e = context;
        this.f = (LinearLayout) View.inflate(context, R.layout.health_hour_second_number_picker, this).findViewById(R.id.hour_second_number_picker);
        this.b = i;
        this.d = i2;
        this.f8959a = i3;
        d();
    }

    private void d() {
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(this.e);
        this.c = healthMultiNumberPicker;
        if (this.d < 3600) {
            healthMultiNumberPicker.setPickerCount(2, new boolean[]{false, true});
            this.c.setColonAndUnit(2);
            String[] strArr = get2ColumnMinuteValues();
            int h = (this.f8959a / 60) - CommonUtils.h(strArr[0]);
            this.c.setDisplayedValues(0, strArr, h);
            String[] d = d(h, strArr.length);
            int i = this.f8959a;
            this.c.setDisplayedValues(1, d, (i % 60) - CommonUtils.h(d[0]));
        } else {
            healthMultiNumberPicker.setPickerCount(3, new boolean[]{false, true, true});
            this.c.setColonAndUnit(3);
            String[] strArr2 = get3ColumnHourValues();
            int h2 = (this.f8959a / 3600) - CommonUtils.h(strArr2[0]);
            this.c.setDisplayedValues(0, strArr2, h2);
            String[] a2 = a(h2, strArr2.length);
            int h3 = ((this.f8959a % 3600) / 60) - CommonUtils.h(a2[0]);
            this.c.setDisplayedValues(1, a2, h3);
            String[] b = b(h2, strArr2.length, h3, a2.length);
            int i2 = this.f8959a;
            this.c.setDisplayedValues(2, b, (i2 % 60) - CommonUtils.h(b[0]));
        }
        this.c.setOnValueChangeListener(this);
        this.f.addView(this.c);
    }

    private String[] get2ColumnMinuteValues() {
        return this.c.d(this.b / 60, this.d / 60, this.e.getString(R$string.IDS_messagecenter_time_minute_value));
    }

    private String[] d(int i, int i2) {
        int i3 = this.b;
        int i4 = this.d;
        return this.c.d(d(i3 % 60, i, i2), c(i4 % 60, i, i2), this.e.getString(R$string.IDS_second));
    }

    private String[] get3ColumnHourValues() {
        return this.c.d(this.b / 3600, this.d / 3600, this.e.getString(R$string.IDS_messagecenter_time_hour_value));
    }

    private String[] a(int i, int i2) {
        int i3 = (this.b % 3600) / 60;
        int c = c((this.d % 3600) / 60, i, i2);
        return this.c.d(d(i3, i, i2), c, this.e.getString(R$string.IDS_messagecenter_time_minute_value));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        if (r9 == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0039, code lost:
    
        if (r7 == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
    
        if (r5 != false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String[] b(int r7, int r8, int r9, int r10) {
        /*
            r6 = this;
            int r0 = r6.b
            int r0 = r0 % 60
            int r1 = r6.d
            int r1 = r1 % 60
            boolean r2 = r6.c(r7, r8)
            boolean r3 = r6.c(r9, r10)
            r4 = 0
            if (r2 == 0) goto L16
            if (r3 == 0) goto L16
            r0 = r4
        L16:
            boolean r7 = r6.b(r7, r8)
            boolean r9 = r6.b(r9, r10)
            r10 = 59
            if (r7 == 0) goto L25
            if (r9 == 0) goto L25
            r1 = r10
        L25:
            r5 = 1
            if (r8 != r5) goto L2d
            if (r3 != 0) goto L3e
            if (r9 != 0) goto L3e
            goto L40
        L2d:
            if (r2 == 0) goto L31
            if (r3 == 0) goto L37
        L31:
            if (r7 == 0) goto L36
            if (r9 != 0) goto L36
            goto L37
        L36:
            r5 = r4
        L37:
            if (r2 != 0) goto L3b
            if (r7 == 0) goto L40
        L3b:
            if (r5 == 0) goto L3e
            goto L40
        L3e:
            r4 = r0
            r10 = r1
        L40:
            com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker r7 = r6.c
            android.content.Context r8 = r6.e
            int r9 = com.huawei.ui.commonui.R$string.IDS_second
            java.lang.String r8 = r8.getString(r9)
            java.lang.String[] r7 = r7.d(r4, r10, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.timepicker.HealthHourSecondNumberPicker.b(int, int, int, int):java.lang.String[]");
    }

    @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
    public void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
        String[] b;
        if (this.d < 3600) {
            b(i, healthMultiNumberPicker);
            return;
        }
        String[] e = healthMultiNumberPicker.e(0);
        if (i == 2 || (i == 0 && e.length <= 1)) {
            LogUtil.a("HealthHourSecondNumberPicker", "other pickers do not need to be changed.");
            return;
        }
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        int i4 = selectedLocations[0];
        int i5 = selectedLocations[1];
        String[] e2 = healthMultiNumberPicker.e(1);
        int h = CommonUtils.h(e2[0]);
        int i6 = selectedLocations[2];
        String[] e3 = healthMultiNumberPicker.e(2);
        int h2 = CommonUtils.h(e3[0]);
        if (i == 0) {
            String[] a2 = a(i4, e.length);
            int a3 = a(h + i5, a2);
            if (!e2[0].equals(a2[0]) || !e2[e2.length - 1].equals(a2[a2.length - 1])) {
                healthMultiNumberPicker.setDisplayedValues(1, a2, a3);
            }
            b = b(i4, e.length, a3, a2.length);
        } else {
            b = b(i4, e.length, i5, e2.length);
        }
        int a4 = a(i6 + h2, b);
        if (e3[0].equals(b[0]) && e3[e3.length - 1].equals(b[b.length - 1])) {
            return;
        }
        healthMultiNumberPicker.setDisplayedValues(2, b, a4);
    }

    private void b(int i, HealthMultiNumberPicker healthMultiNumberPicker) {
        String[] e = healthMultiNumberPicker.e(0);
        if (i == 1 || (i == 0 && e.length <= 1)) {
            LogUtil.a("HealthHourSecondNumberPicker", "other pickers do not need to be changed.");
            return;
        }
        int[] selectedLocations = healthMultiNumberPicker.getSelectedLocations();
        int i2 = selectedLocations[0];
        int i3 = selectedLocations[1];
        int h = CommonUtils.h(healthMultiNumberPicker.e(1)[0]);
        String[] d = d(i2, e.length);
        int a2 = a(i3 + h, d);
        if (i2 == 0 || i2 == e.length - 1) {
            healthMultiNumberPicker.setDisplayedValues(1, d, a2);
        }
    }

    private int a(int i, String[] strArr) {
        if (i > CommonUtils.h(strArr[strArr.length - 1])) {
            return strArr.length - 1;
        }
        if (i < CommonUtils.h(strArr[0])) {
            return 0;
        }
        return i - CommonUtils.h(strArr[0]);
    }

    public int getSelectedTime() {
        int h;
        int i;
        int[] selectedLocations = this.c.getSelectedLocations();
        if (this.d < 3600) {
            int i2 = selectedLocations[0];
            int h2 = CommonUtils.h(this.c.e(0)[0]);
            h = selectedLocations[1] + CommonUtils.h(this.c.e(1)[0]);
            i = (i2 + h2) * 60;
        } else {
            int i3 = selectedLocations[0];
            int h3 = CommonUtils.h(this.c.e(0)[0]);
            int i4 = selectedLocations[1];
            int h4 = CommonUtils.h(this.c.e(1)[0]);
            h = selectedLocations[2] + CommonUtils.h(this.c.e(2)[0]);
            i = ((i3 + h3) * 3600) + ((i4 + h4) * 60);
        }
        return i + h;
    }
}

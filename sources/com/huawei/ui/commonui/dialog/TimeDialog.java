package com.huawei.ui.commonui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker;
import defpackage.nsl;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes9.dex */
public class TimeDialog extends DialogFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f8823a;
    private int b;
    private int c;
    private CustomViewDialog d;
    private HealthMultiNumberPicker e;
    private String[] f;
    private String[] g;
    private int h;
    private String[] i;
    private OnSelectTimeListener j;
    private nsl k;
    private int l;
    private int m;
    private int o;

    public interface OnSelectTimeListener {
        void onSelectTime(int i);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.d == null) {
            this.d = c();
        }
        if (this.k == null) {
            this.k = new nsl();
        }
        b();
        return this.d;
    }

    private CustomViewDialog c() {
        int i = getArguments().getInt("title");
        HealthMultiNumberPicker healthMultiNumberPicker = new HealthMultiNumberPicker(BaseApplication.getContext());
        this.e = healthMultiNumberPicker;
        healthMultiNumberPicker.setPickerCount(3, new boolean[]{false, false, false});
        return new CustomViewDialog.Builder(getActivity()).d(i).czg_(this.e).czc_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.TimeDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R$string.IDS_contact_confirm_ios_btn, new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.TimeDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TimeDialog.this.j != null) {
                    TimeDialog.this.j.onSelectTime(TimeDialog.this.l);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
    }

    private void b() {
        d();
        a();
        h();
        this.e.setOnValueChangeListener(new HealthMultiNumberPicker.OnValueChangeListener() { // from class: com.huawei.ui.commonui.dialog.TimeDialog.5
            @Override // com.huawei.ui.commonui.numberpicker.HealthMultiNumberPicker.OnValueChangeListener
            public void onValueChange(int i, HealthMultiNumberPicker healthMultiNumberPicker, int i2, int i3) {
                if (i == 0) {
                    TimeDialog.this.b(i3, i2);
                    return;
                }
                if (i == 1) {
                    TimeDialog.this.d(i3, i2);
                } else if (i == 2) {
                    TimeDialog.this.a(i3, i2);
                } else {
                    LogUtil.b("TimeDialog", "the change of numberpicker is invalid");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2) {
        if (i > i2) {
            this.l += 3600;
        } else {
            this.l -= 3600;
        }
        int c = this.k.c(this.l);
        this.l = c;
        this.k.f(c);
        a();
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        if (i > i2) {
            this.l += 60;
        } else {
            this.l -= 60;
        }
        int c = this.k.c(this.l);
        this.l = c;
        this.k.f(c);
        h();
        d();
        String d = this.k.d(this.i, i);
        if (d == null) {
            return;
        }
        if (d.equals(c(0, 5)) || d.equals(c(59, 5))) {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        if (i > i2) {
            this.l++;
        } else {
            this.l--;
        }
        int c = this.k.c(this.l);
        this.l = c;
        this.k.f(c);
        d();
        a();
        String d = this.k.d(this.f, i);
        if (d == null) {
            return;
        }
        if (d.equals(c(0, 6)) || d.equals(c(59, 6))) {
            h();
        }
    }

    private void d() {
        String[] e = e();
        this.g = e;
        this.e.setDisplayedValues(0, e, this.k.c());
    }

    private void a() {
        int e = this.k.e();
        String[] a2 = a(e);
        this.i = a2;
        this.e.setDisplayedValues(1, a2, this.k.e(e));
    }

    private void h() {
        int a2 = this.k.a();
        String[] d = d(a2);
        this.f = d;
        this.e.setDisplayedValues(2, d, this.k.i(a2));
    }

    private String[] e() {
        int i = (this.f8823a - this.h) + 1;
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            strArr[i2] = c(this.h + i2, 4);
        }
        return strArr;
    }

    private String[] a(int i) {
        String[] strArr;
        int i2 = 0;
        if (i == 1) {
            int i3 = this.m;
            int i4 = 65 - i3;
            strArr = new String[i4];
            while (i2 < i4) {
                if (i2 < 63 - i3) {
                    strArr[i2] = c((this.m + i2) % 60, 5);
                } else {
                    strArr[i2] = "";
                }
                i2++;
            }
        } else if (i == 3) {
            int i5 = this.b + 6;
            strArr = new String[i5];
            while (i2 < i5) {
                if (i2 < 2) {
                    strArr[i2] = "";
                } else {
                    strArr[i2] = c((i2 + 55) % 60, 5);
                }
                i2++;
            }
        } else {
            strArr = new String[70];
            while (i2 < 70) {
                strArr[i2] = c((i2 + 55) % 60, 5);
                i2++;
            }
        }
        return strArr;
    }

    private String[] d(int i) {
        String[] strArr;
        int i2 = 0;
        if (i == 1) {
            int i3 = this.o;
            int i4 = 65 - i3;
            strArr = new String[i4];
            while (i2 < i4) {
                if (i2 < 63 - i3) {
                    strArr[i2] = c((this.o + i2) % 60, 6);
                } else {
                    strArr[i2] = "";
                }
                i2++;
            }
        } else if (i == 3) {
            int i5 = this.c + 6;
            strArr = new String[i5];
            while (i2 < i5) {
                if (i2 < 2) {
                    strArr[i2] = "";
                } else {
                    strArr[i2] = c((i2 + 55) % 60, 6);
                }
                i2++;
            }
        } else {
            strArr = new String[70];
            while (i2 < 70) {
                strArr[i2] = c((i2 + 55) % 60, 6);
                i2++;
            }
        }
        return strArr;
    }

    private String c(int i, int i2) {
        String e = UnitUtil.e(i, 1, 0);
        if (i2 == 4) {
            return getResources().getQuantityString(R.plurals._2130903304_res_0x7f030108, i, e);
        }
        if (i2 == 5) {
            return getResources().getQuantityString(R.plurals._2130903270_res_0x7f0300e6, i, Integer.valueOf(i));
        }
        return getResources().getQuantityString(R.plurals._2130903271_res_0x7f0300e7, i, Integer.valueOf(i));
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}

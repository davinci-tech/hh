package com.huawei.ui.main.stories.exhibitioninfo.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import defpackage.pgw;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class ExhibitionBloodFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private TextView f9714a;
    private TextView b;
    private HealthSeekBarExtend c;
    private HealthSeekBarExtend d;
    private TextView e;
    private HealthSeekBarExtend f;
    private TextView g;
    private View h;
    private HiHealthData i = null;
    private Context j;
    private LinearLayout k;
    private double l;
    private LinearLayout m;
    private LinearLayout n;
    private double o;
    private RelativeLayout q;
    private RelativeLayout s;
    private RelativeLayout t;

    public static int c(double d) {
        return d < 70.0d ? (int) ((d / 70.0d) * 34.0d) : d < 90.0d ? ((int) (((d - 70.0d) * 210.0d) / 20.0d)) + 36 : (int) (248 + ((102 * (d - 90.0d)) / 10.0d));
    }

    public static int e(double d) {
        return d < 4.4d ? (int) ((d / 4.4d) * 34.0d) : d < 7.8d ? ((int) (((d - 4.4d) * 210.0d) / 3.3999999999999995d)) + 36 : (int) (248 + ((102 * (d - 7.8d)) / 25.2d));
    }

    private void doM_(View view) {
        ((ImageView) view.findViewById(R.id.blood_oxygen_empty_bar)).setImageDrawable(pgw.dpr_(106));
        if (this.c != null) {
            return;
        }
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) view.findViewById(R.id.blood_oxygen_seek_bar);
        this.c = healthSeekBarExtend;
        healthSeekBarExtend.setThumb(ContextCompat.getDrawable(this.j, R.drawable._2131430049_res_0x7f0b0aa1));
        this.c.setThumbOffset(15);
        this.c.setProgressDrawable(ContextCompat.getDrawable(this.j, R.drawable.health_halthdata_weight_seekbar_background));
        this.c.setRulerSrc(pgw.dpr_(106));
        this.c.setMax(350);
        this.c.setSeekBarPadding(0, 0, 0, 0);
    }

    private void doN_(View view) {
        if (this.d != null) {
            return;
        }
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) view.findViewById(R.id.blood_pressure_seek_bar);
        this.d = healthSeekBarExtend;
        healthSeekBarExtend.setThumb(ContextCompat.getDrawable(this.j, R.drawable._2131430049_res_0x7f0b0aa1));
        this.d.setThumbOffset(15);
        this.d.setProgressDrawable(ContextCompat.getDrawable(this.j, R.drawable.health_halthdata_weight_seekbar_background));
        this.d.setRulerSrc(ContextCompat.getDrawable(this.j, R.drawable._2131427737_res_0x7f0b0199));
        this.d.setMax(100);
        this.d.setSeekBarPadding(0, 0, 0, 0);
    }

    private void doO_(View view) {
        ((ImageView) view.findViewById(R.id.blood_sugar_empty_bar)).setImageDrawable(pgw.dpr_(105));
        if (this.f != null) {
            return;
        }
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) view.findViewById(R.id.blood_sugar_seek_bar);
        this.f = healthSeekBarExtend;
        healthSeekBarExtend.setThumb(ContextCompat.getDrawable(this.j, R.drawable._2131430049_res_0x7f0b0aa1));
        this.f.setThumbOffset(15);
        this.f.setProgressDrawable(ContextCompat.getDrawable(this.j, R.drawable.health_halthdata_weight_seekbar_background));
        this.f.setRulerSrc(pgw.dpr_(105));
        this.f.setMax(350);
        this.f.setSeekBarPadding(0, 0, 0, 0);
    }

    public void d(Context context, double d, HiHealthData hiHealthData, double d2) {
        if (context == null) {
            this.j = BaseApplication.e();
            LogUtil.h("ExhibitionBloodFragment", "initViewInActivity Activity is null");
        } else {
            this.j = context;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.report_blood_layout, (ViewGroup) null);
        this.h = inflate;
        this.o = d;
        this.i = hiHealthData;
        this.l = d2;
        doP_(inflate);
    }

    public void b() {
        g();
    }

    private void g() {
        c();
        a();
        e();
    }

    private void c() {
        if (Double.isNaN(this.o) || this.o == 0.0d) {
            return;
        }
        this.e.setText(Math.round(this.o) + "%");
        int c = c(this.o);
        int c2 = c(c);
        LogUtil.a("ExhibitionBloodFragment", "initBloodOxygenData progress = ", Integer.valueOf(c), ", finalProgress = ", Integer.valueOf(c2));
        this.n.setVisibility(8);
        this.t.setVisibility(0);
        this.c.setProgress(c2);
    }

    private void a() {
        HiHealthData hiHealthData = this.i;
        if (hiHealthData != null) {
            double d = hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC");
            double d2 = this.i.getDouble("BLOOD_PRESSURE_DIASTOLIC");
            LogUtil.a("ExhibitionBloodFragment", "initBloodPressureData highData = ", Double.valueOf(d), ", lowData = ", Double.valueOf(d2));
            this.m.setVisibility(8);
            this.s.setVisibility(0);
            this.f9714a.setText(String.valueOf((int) d));
            this.b.setText(String.valueOf((int) d2));
            this.d.setProgress(pgw.c((short) UnitUtil.a(d, 0), (short) UnitUtil.a(d2, 0)));
        }
    }

    private void e() {
        if (Double.isNaN(this.l)) {
            return;
        }
        double d = this.l;
        if (d != 0.0d) {
            int e = e(d);
            int c = c(e);
            LogUtil.a("ExhibitionBloodFragment", "initBloodSugarData progressSugar = ", Integer.valueOf(e), ", finalProgress = ", Integer.valueOf(c));
            this.k.setVisibility(8);
            this.q.setVisibility(0);
            this.g.setText(UnitUtil.e(this.l, 1, 1));
            this.f.setProgress(c);
        }
    }

    private void doP_(View view) {
        pgw.dpt_((RelativeLayout) view.findViewById(R.id.report_blood_pdf), 101);
        this.t = (RelativeLayout) view.findViewById(R.id.blood_oxygen_data);
        this.e = (TextView) view.findViewById(R.id.report_blood_oxygen_date);
        doM_(view);
        this.n = (LinearLayout) view.findViewById(R.id.blood_oxygen_empty);
        this.s = (RelativeLayout) view.findViewById(R.id.blood_pressure_date);
        this.f9714a = (TextView) view.findViewById(R.id.report_blood_pressure_hight_date);
        this.b = (TextView) view.findViewById(R.id.report_blood_pressure_low_date);
        doN_(view);
        this.m = (LinearLayout) view.findViewById(R.id.blood_pressure_empty);
        this.q = (RelativeLayout) view.findViewById(R.id.blood_sugar_data);
        this.g = (TextView) view.findViewById(R.id.report_blood_sugar_date);
        doO_(view);
        this.k = (LinearLayout) view.findViewById(R.id.blood_sugar_empty);
    }

    private int c(int i) {
        LogUtil.c("ExhibitionBloodFragment", "the last progress data is ", Integer.valueOf(i));
        int i2 = 15;
        if (i >= 15) {
            i2 = 335;
            if (i <= 335) {
                LogUtil.c("ExhibitionBloodFragment", "the progress is normal");
                return i;
            }
        }
        return i2;
    }

    public Bitmap doQ_() {
        return pgw.dps_(this.h);
    }
}

package com.huawei.ui.homewear21.home.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class WearHomeStatusHolder extends RecyclerView.ViewHolder {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9675a;
    private HealthTextView b;
    private LinearLayout c;
    private HealthTextView d;
    private HealthTextView e;
    private LinearLayout f;
    private HealthTextView g;
    private HealthTextView h;
    private LinearLayout i;
    private HealthTextView j;
    private HealthTextView k;
    private LinearLayout l;
    private LinearLayout m;
    private HealthTextView n;
    private HealthTextView o;
    private ImageView p;
    private LinearLayout q;
    private HealthTextView s;
    private ImageView t;

    public WearHomeStatusHolder(View view) {
        super(view);
        if (view == null) {
            return;
        }
        this.h = (HealthTextView) nsy.cMd_(view, R.id.connect_status);
        this.j = (HealthTextView) nsy.cMd_(view, R.id.connect_tip);
        this.i = (LinearLayout) nsy.cMd_(view, R.id.device_progress);
        this.c = (LinearLayout) nsy.cMd_(view, R.id.battery_layout);
        this.n = (HealthTextView) nsy.cMd_(view, R.id.reconnect);
        this.f9675a = (ImageView) nsy.cMd_(view, R.id.battery_icon);
        this.d = (HealthTextView) nsy.cMd_(view, R.id.battery_number);
        this.p = (ImageView) nsy.cMd_(view, R.id.wear_home_fill_background);
        this.t = (ImageView) nsy.cMd_(view, R.id.wear_home_background);
        this.s = (HealthTextView) nsy.cMd_(view, R.id.tip_power_saving);
        this.f = (LinearLayout) nsy.cMd_(view, R.id.connect_status_layout);
        this.q = (LinearLayout) nsy.cMd_(view, R.id.wear_place_layout);
        this.l = (LinearLayout) nsy.cMd_(view, R.id.status_end_layout);
        this.e = (HealthTextView) nsy.cMd_(view, R.id.bolt_wear_status);
        this.b = (HealthTextView) nsy.cMd_(view, R.id.bolt_wear_place);
        this.m = (LinearLayout) nsy.cMd_(view, R.id.earphone_power_layout);
        this.o = (HealthTextView) nsy.cMd_(view, R.id.left_earphone_power_size);
        this.k = (HealthTextView) nsy.cMd_(view, R.id.right_earphone_power_size);
        this.g = (HealthTextView) nsy.cMd_(view, R.id.earphone_battery_number);
    }

    public HealthTextView j() {
        return this.h;
    }

    public HealthTextView i() {
        return this.j;
    }

    public LinearLayout dmA_() {
        return this.i;
    }

    public LinearLayout dmy_() {
        return this.c;
    }

    public HealthTextView k() {
        return this.n;
    }

    public ImageView dmx_() {
        return this.f9675a;
    }

    public HealthTextView e() {
        return this.d;
    }

    public ImageView dmE_() {
        return this.p;
    }

    public ImageView dmD_() {
        return this.t;
    }

    public HealthTextView q() {
        return this.s;
    }

    public LinearLayout dmz_() {
        return this.f;
    }

    public LinearLayout dmF_() {
        return this.q;
    }

    public LinearLayout dmC_() {
        return this.l;
    }

    public HealthTextView b() {
        return this.e;
    }

    public HealthTextView c() {
        return this.b;
    }

    public LinearLayout dmB_() {
        return this.m;
    }

    public HealthTextView o() {
        return this.o;
    }

    public HealthTextView l() {
        return this.k;
    }

    public HealthTextView g() {
        return this.g;
    }
}

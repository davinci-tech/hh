package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class JumpDataView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f3781a;
    private int b;
    private HealthTextView c;
    private RelativeLayout d;
    private HealthDivider e;
    private ImageView f;
    private HealthTextView g;
    private int h;
    private ImageView i;
    private int j;
    private HealthTextView k;
    private HealthDivider l;
    private HealthTextView m;
    private HealthDivider n;
    private RelativeLayout o;

    public JumpDataView(Context context) {
        super(context);
        this.h = 0;
        a(context);
    }

    public JumpDataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0;
        a(context);
    }

    public JumpDataView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 0;
        a(context);
    }

    public RelativeLayout getAverageJumpDurationLayout() {
        return this.d;
    }

    public RelativeLayout getHeightLayout() {
        return this.f3781a;
    }

    public void setJumpDuration(int i) {
        if (i == 0) {
            this.d.setVisibility(8);
            this.n.setVisibility(8);
        } else {
            this.b = i;
            this.c.setText(UnitUtil.e(i, 1, 0));
        }
    }

    public void setJumpTimes(int i) {
        if (i == 0) {
            this.l.setVisibility(8);
            this.o.setVisibility(8);
        } else {
            this.j = i;
            this.k.setText(UnitUtil.e(i, 1, 0));
            this.m.setText(getContext().getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, i));
        }
    }

    public void setJumpHeight(int i) {
        String e;
        if (i == 0) {
            this.e.setVisibility(8);
            this.f3781a.setVisibility(8);
            return;
        }
        if (UnitUtil.h()) {
            e = UnitUtil.e((int) UnitUtil.e(i, 0), 1, 0);
            ((HealthTextView) findViewById(R.id.average_jump_height_unit)).setText(getContext().getString(R.string._2130841897_res_0x7f021129));
        } else {
            e = UnitUtil.e(i, 1, 0);
        }
        this.g.setText(e);
    }

    private void a(Context context) {
        if (context == null) {
            LogUtil.b("Track_JumpDataView", "context is null");
            return;
        }
        View.inflate(context, R.layout.track_detail_jump_data, this);
        this.e = (HealthDivider) findViewById(R.id.view_first);
        this.l = (HealthDivider) findViewById(R.id.view_second);
        this.n = (HealthDivider) findViewById(R.id.view_third);
        this.g = (HealthTextView) findViewById(R.id.average_jump_height_value);
        this.k = (HealthTextView) findViewById(R.id.track_jump_times_value);
        this.c = (HealthTextView) findViewById(R.id.average_jump_time_full_value);
        this.m = (HealthTextView) findViewById(R.id.track_jump_times_unit);
        this.g.setText(UnitUtil.e(this.b, 1, 0));
        this.k.setText(UnitUtil.e(this.j, 1, 0));
        this.c.setText(UnitUtil.e(this.h, 1, 0));
        this.f3781a = (RelativeLayout) findViewById(R.id.average_height_layout);
        this.o = (RelativeLayout) findViewById(R.id.jump_times);
        this.d = (RelativeLayout) findViewById(R.id.jump_duration);
        this.i = (ImageView) findViewById(R.id.jump_time);
        this.f = (ImageView) findViewById(R.id.average_jump_height_ic);
        ((HealthSubHeader) findViewById(R.id.new_header)).setPaddingStartEnd(0.0f, 0.0f);
        if (LanguageUtil.bc(context)) {
            this.i.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.f.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.i.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.f.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
    }
}

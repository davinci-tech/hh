package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.view.IRouteDetail;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.enf;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class RouteHintView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f3788a;
    private HealthTextView b;
    private List<Integer> c;
    private HealthTextView d;
    private String e;
    private View i;
    private IRouteDetail j;

    public RouteHintView(Context context) {
        this(context, null);
    }

    public RouteHintView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RouteHintView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RouteHintView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setOnClickListener(this);
        b(context);
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.route_hint_layout, this);
        this.i = inflate;
        this.d = (HealthTextView) inflate.findViewById(R.id.route_in_sight_length);
        this.b = (HealthTextView) this.i.findViewById(R.id.route_in_sight_name);
    }

    public void a(enf enfVar, IRouteDetail iRouteDetail) {
        double m = enfVar.m() / 1000.0d;
        if (UnitUtil.h()) {
            nsy.cMr_(this.d, BaseApplication.e().getResources().getQuantityString(R.plurals._2130903253_res_0x7f0300d5, (int) m, UnitUtil.e(UnitUtil.e(m, 3), 1, 2)));
        } else {
            nsy.cMr_(this.d, BaseApplication.e().getResources().getQuantityString(R.plurals._2130903301_res_0x7f030105, (int) m, UnitUtil.e(m, 1, 2)));
        }
        nsy.cMr_(this.b, enfVar.h());
        this.f3788a = enfVar.a();
        this.c = enfVar.l();
        this.j = iRouteDetail;
        this.e = enfVar.i();
    }

    public String getPathId() {
        return this.e;
    }

    public List<Integer> getPathDistanceType() {
        return Collections.singletonList(Integer.valueOf(this.f3788a));
    }

    public List<Integer> getPathType() {
        return this.c;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (TextUtils.isEmpty(this.e)) {
            LogUtil.a("RouteHintView", "mPathId is or mRouteDetail is invalid");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.j.show(this.e);
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}

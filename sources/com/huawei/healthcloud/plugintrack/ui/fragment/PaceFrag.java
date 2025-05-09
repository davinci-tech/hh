package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.PaceMapRecyclerViewAdapter;
import com.huawei.healthcloud.plugintrack.ui.fragmentutils.SpeedMapRecyclerViewAdapter;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.gvv;
import defpackage.hji;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PaceFrag extends SportResultBaseFragment {
    private String d;
    private String e;
    private boolean g;
    private float h;
    private float l;
    private Map<Integer, Float> m;
    private List<Map.Entry<Integer, Float>> n;
    private Activity j = null;
    private HealthRecycleView k = null;
    private LinearLayout f = null;
    private boolean i = false;
    private PaceMapRecyclerViewAdapter o = null;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater not found.");
        }
        LogUtil.a("Track_PaceFrag", "onCreateView");
        if (o()) {
            return null;
        }
        h();
        this.j = getActivity();
        View inflate = layoutInflater.inflate(R.layout.track_show_pace_fragment, viewGroup, false);
        this.b = inflate;
        if (m()) {
            this.g = this.f3732a.aw();
            this.m = this.f3732a.e(this.f3732a.e().requestSportType());
            HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.recycler_view);
            this.k = healthRecycleView;
            healthRecycleView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.c);
            linearLayoutManager.setOrientation(1);
            this.k.setLayoutManager(linearLayoutManager);
            this.f = (LinearLayout) inflate.findViewById(R.id.no_pace_layout);
            Map<Integer, Float> a2 = gvv.a(this.m);
            gvv.a(a2, this.m, this.f3732a);
            if (this.f3732a.b(1) || a2 == null) {
                this.k.setVisibility(8);
                this.f.setVisibility(0);
            } else {
                b(a2);
                c(a2);
            }
        }
        return inflate;
    }

    private void c(Map<Integer, Float> map) {
        float d = hji.d(map, this.f3732a);
        this.j.getWindowManager().getDefaultDisplay().getSize(new Point());
        d(d, gvv.b(this.c, beG_(r1)) - 78.0f, gvv.a(this.f3732a) ? 120.0f : 80.0f);
    }

    private float beG_(Point point) {
        int min;
        if (this.c == null) {
            LogUtil.b("Track_PaceFrag", "mContext is null");
            return 0.0f;
        }
        if (nsn.ag(this.c)) {
            min = point.x;
        } else {
            min = Math.min(point.x, point.y);
        }
        return min;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        Map<Integer, Float> map;
        super.onConfigurationChanged(configuration);
        LogUtil.a("Track_PaceFrag", "onConfigurationChanged isTahitiModel: ", Boolean.valueOf(nsn.ag(getContext())));
        if (this.o != null && (map = this.m) != null) {
            c(gvv.a(map));
        } else {
            LogUtil.h("Track_PaceFrag", "mPaceMapRecyclerViewAdapter is null");
        }
    }

    private void d(float f, float f2, float f3) {
        if (this.g) {
            this.k.setAdapter(new SpeedMapRecyclerViewAdapter(this.c, this.n, this.d, this.e, this.l, this.h, f2, f3, f, false, this.f3732a));
            return;
        }
        MotionPathSimplify e = this.f3732a.e();
        Map<Double, Double> requestPartTimeMap = e.requestPartTimeMap();
        double d = 0.0d;
        double doubleValue = (requestPartTimeMap == null || !requestPartTimeMap.containsKey(Double.valueOf(21.0975d))) ? 0.0d : requestPartTimeMap.get(Double.valueOf(21.0975d)).doubleValue();
        if (requestPartTimeMap != null && requestPartTimeMap.containsKey(Double.valueOf(42.195d))) {
            d = requestPartTimeMap.get(Double.valueOf(42.195d)).doubleValue();
        }
        PaceMapRecyclerViewAdapter paceMapRecyclerViewAdapter = new PaceMapRecyclerViewAdapter(this.c, this.n, this.d, this.e, this.l, this.h, f2, f3, f, false, doubleValue, d, e.requestSportType(), this.i);
        this.o = paceMapRecyclerViewAdapter;
        this.k.setAdapter(paceMapRecyclerViewAdapter);
    }

    private void b(Map<Integer, Float> map) {
        if (!k()) {
            LogUtil.h("Track_PaceFrag", "initData() mTrackDetailDataManager is null");
            return;
        }
        Float[] e = gvv.e(map);
        this.l = e[0].floatValue();
        this.h = e[1].floatValue();
        if (this.g) {
            this.e = hji.g(this.f3732a.e().requestAvgPace());
            this.d = hji.o(this.l);
        } else {
            this.e = gvv.a(e());
            this.d = hji.a(this.l);
        }
        float e2 = e();
        float f = this.l;
        if (e2 < f) {
            this.i = true;
        }
        this.n = hji.d(map, f);
    }

    private float e() {
        if (a(this.f3732a.e())) {
            if (UnitUtil.h()) {
                return ((float) UnitUtil.d(this.f3732a.e().requestAvgPace(), 2)) / 5.0f;
            }
            return this.f3732a.e().requestAvgPace();
        }
        if (UnitUtil.h()) {
            return this.f3732a.e().requestAvgPace() * 1.609344f;
        }
        return this.f3732a.e().requestAvgPace();
    }

    private boolean a(MotionPathSimplify motionPathSimplify) {
        return motionPathSimplify.requestSportType() == 274 && motionPathSimplify.requestSportDataSource() == 5;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected String d() {
        return "Track_PaceFrag";
    }
}

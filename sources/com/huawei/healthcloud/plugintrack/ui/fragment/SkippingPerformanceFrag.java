package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.SkippingPerformanceAdapter;
import com.huawei.healthcloud.plugintrack.ui.fragment.SkippingPerformanceFrag;
import com.huawei.healthcloud.plugintrack.ui.view.SkippingPerformanceView;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.gxt;
import defpackage.hjw;
import defpackage.hjy;
import health.compact.a.Services;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class SkippingPerformanceFrag extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private float[] f3731a;
    private float[] b;
    private EcologyDeviceApi c;
    private SkippingPerformanceAdapter d;
    private float[] e;
    private SkippingPerformanceView f;
    private String g;
    private hjw h;
    private float[] i;
    private TrackDetailActivity j;

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof TrackDetailActivity) {
            this.j = (TrackDetailActivity) activity;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.g = arguments.getString("skipping_performance_title");
            this.f3731a = arguments.getFloatArray("skipping_performance_history_value");
            this.b = arguments.getFloatArray("skipping_performance_history_rank");
            if (this.g.equals(getString(R.string._2130847656_res_0x7f0227a8))) {
                this.i = arguments.getFloatArray("skipping_performance_value");
                this.e = arguments.getFloatArray("skipping_performance_rank");
                return;
            }
            return;
        }
        LogUtil.h("Track_SkippingPerformanceFrag", "onCreate bundle is null");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.track_skipping_performance_fragment, viewGroup, false);
        if (inflate != null) {
            bfw_(inflate);
            d();
        } else {
            LogUtil.h("Track_SkippingPerformanceFrag", "onCreateView, view is null");
        }
        return inflate;
    }

    private void d() {
        TrackDetailActivity trackDetailActivity = this.j;
        if (trackDetailActivity != null) {
            this.h = trackDetailActivity.e();
            EcologyDeviceApi ecologyDeviceApi = (EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class);
            this.c = ecologyDeviceApi;
            ecologyDeviceApi.requestPerformanceRank(new IBaseResponseCallback() { // from class: hii
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SkippingPerformanceFrag.this.c(i, obj);
                }
            });
        }
        if (getParentFragmentManager().findFragmentByTag("Track_SkippingPerformanceFrag") != null) {
            this.f.setIsSingleRecord(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, Object obj) {
        if (i != 0) {
            LogUtil.a("Track_SkippingPerformanceFrag", "Failed to request performance result");
        }
        this.c.quaryPerformanceOfBest(new IBaseResponseCallback() { // from class: him
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj2) {
                SkippingPerformanceFrag.this.e(i2, obj2);
            }
        }, DateType.DATE_ALL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, Object obj) {
        if (i != 0) {
            LogUtil.a("Track_SkippingPerformanceFrag", "Failed to query best of history");
            a(null);
        } else if (!(obj instanceof Pair)) {
            LogUtil.a("Track_SkippingPerformanceFrag", "objData is not instance of Pair");
            a(null);
        } else {
            a((Pair) obj);
        }
    }

    private void a(final Pair<float[], float[]> pair) {
        this.j.runOnUiThread(new Runnable() { // from class: hik
            @Override // java.lang.Runnable
            public final void run() {
                SkippingPerformanceFrag.this.b(pair);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void b(Pair pair) {
        LogUtil.a("Track_SkippingPerformanceFrag", "refreshDataOnUiThread");
        Pair<float[], float[]> c = this.h.c();
        if (c != null) {
            this.i = c.first;
            this.e = c.second;
        }
        if (pair != null) {
            this.f3731a = (float[]) pair.first;
            this.b = (float[]) pair.second;
            this.h.d((Pair<float[], float[]>) pair);
        } else {
            this.f3731a = this.i;
            this.b = this.e;
            this.h.d(c);
        }
        this.f.setIsSingleRecord(true);
        b();
    }

    private void bfw_(View view) {
        this.f = new SkippingPerformanceView(requireContext());
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.skipping_performance_detail_rv);
        if (this.d == null) {
            this.d = new SkippingPerformanceAdapter(requireContext());
        }
        this.d.bdG_(this.f);
        healthRecycleView.setIsScroll(false);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        healthRecycleView.setAdapter(this.d);
    }

    private void a() {
        this.f.setTitle(this.g);
        this.f.setData(this.i, this.e, false);
        this.f.setData(this.f3731a, this.b, true);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        b();
    }

    private void b() {
        float[] fArr = this.b;
        if (fArr != null && fArr.length > 0) {
            float[] fArr2 = this.e;
            if (fArr2 != null && fArr2.length > 0) {
                d(this.i, fArr2);
            } else {
                d(this.f3731a, fArr);
            }
        } else {
            float[] fArr3 = this.e;
            if (fArr3 != null && fArr3.length > 0) {
                d(this.i, fArr3);
            }
        }
        a();
    }

    private void d(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null || fArr.length != fArr2.length) {
            LogUtil.b("Track_SkippingPerformanceFrag", "input not allowed");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new gxt("maxSkipSpeed", fArr[0], fArr2[0]));
        arrayList.add(new gxt("enduranceTimeAbility", fArr[1], fArr2[1]));
        arrayList.add(new gxt("maxSkippingTimes", fArr[2], fArr2[2]));
        arrayList.add(new gxt("skipNum", fArr[3], fArr2[3]));
        arrayList.add(new gxt("enduranceAbility", fArr[4], fArr2[4]));
        this.d.a(arrayList, hjy.a(fArr2));
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        SkippingPerformanceAdapter skippingPerformanceAdapter = this.d;
        if (skippingPerformanceAdapter != null) {
            skippingPerformanceAdapter.c();
        }
    }
}

package com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseListAdapter;
import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.czl;
import defpackage.dsu;
import defpackage.dsv;
import defpackage.dsw;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class RateControlFragment extends BaseFragment {
    private static final String TAG = "RateControlFragment";
    private RateControlCourseListAdapter mAdapter;
    private ArrayList<dsu> mCourseLists = new ArrayList<>();
    private HealthColumnRelativeLayout mLoading;
    private HealthColumnRelativeLayout mLoadingError;
    private HealthTextView mLoadingErrorText;
    private HealthRecycleView mRecycleView;
    private int mType;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mType = arguments.getInt("deviceType");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ViewGroup viewGroup2 = onCreateView instanceof ViewGroup ? (ViewGroup) onCreateView : null;
        this.child = layoutInflater.inflate(R.layout.fragment_rate_control, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
            initData();
            return viewGroup2;
        }
        return this.child;
    }

    private void initView(View view) {
        this.mRecycleView = (HealthRecycleView) view.findViewById(R.id.rate_control_recycle_view);
        this.mLoading = (HealthColumnRelativeLayout) view.findViewById(R.id.rate_control_loading);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.performance_no_data_tv);
        this.mLoadingErrorText = healthTextView;
        healthTextView.setText(getString(R.string._2130845085_res_0x7f021d9d));
        HealthColumnRelativeLayout healthColumnRelativeLayout = (HealthColumnRelativeLayout) view.findViewById(R.id.rope_performance_no_data_layout);
        this.mLoadingError = healthColumnRelativeLayout;
        healthColumnRelativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RateControlFragment.this.m417xf0f30281(view2);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlFragment, reason: not valid java name */
    /* synthetic */ void m417xf0f30281(View view) {
        obtainConfigInfo();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initData() {
        setTitle(getString(this.mType == 265 ? R.string._2130850367_res_0x7f02323f : R.string._2130850369_res_0x7f023241));
        this.mCustomTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.mRecycleView.setLayoutManager(new LinearLayoutManager(this.mainActivity));
        RateControlCourseListAdapter rateControlCourseListAdapter = new RateControlCourseListAdapter(this.mainActivity, this.mType, new RateControlCourseListAdapter.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlFragment$$ExternalSyntheticLambda2
            @Override // com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseListAdapter.OnItemClickListener
            public final void onItemClick(dsu dsuVar) {
                RateControlFragment.this.m416x7de1a1c7(dsuVar);
            }
        });
        this.mAdapter = rateControlCourseListAdapter;
        this.mRecycleView.setAdapter(rateControlCourseListAdapter);
        obtainConfigInfo();
    }

    /* renamed from: lambda$initData$1$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlFragment, reason: not valid java name */
    /* synthetic */ void m416x7de1a1c7(dsu dsuVar) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        arguments.putString("course_id", dsuVar.getId());
        RateControlCourseDetailFragment rateControlCourseDetailFragment = new RateControlCourseDetailFragment();
        rateControlCourseDetailFragment.setArguments(arguments);
        addFragment(rateControlCourseDetailFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        LogUtil.c(TAG, "initViewTahiti");
        initData();
    }

    private void obtainConfigInfo() {
        if (!CommonUtil.aa(this.mainActivity)) {
            LogUtil.c(TAG, "network is error");
            this.mLoading.setVisibility(8);
            this.mLoadingError.setVisibility(0);
            nrh.e(this.mainActivity, R.string._2130844160_res_0x7f021a00);
            return;
        }
        this.mLoading.setVisibility(0);
        this.mLoadingError.setVisibility(8);
        czl.a(this.mType, (ConfigCallBack<dsw>) new ConfigCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj) {
                RateControlFragment.this.m419x81067b04((dsw) obj);
            }
        });
    }

    /* renamed from: lambda$obtainConfigInfo$3$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlFragment, reason: not valid java name */
    /* synthetic */ void m419x81067b04(final dsw dswVar) {
        if (dswVar == null || koq.b(dswVar.getGroupLists())) {
            LogUtil.c(TAG, "heartRateControlInfo is null");
            this.mLoading.setVisibility(8);
            this.mLoadingError.setVisibility(0);
            return;
        }
        new Handler().post(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RateControlFragment.this.m418x127f69c3(dswVar);
            }
        });
    }

    /* renamed from: lambda$obtainConfigInfo$2$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlFragment, reason: not valid java name */
    /* synthetic */ void m418x127f69c3(dsw dswVar) {
        this.mLoading.setVisibility(8);
        this.mLoadingError.setVisibility(8);
        setGroupNameToCourseInfo(dswVar);
        this.mAdapter.setData(this.mCourseLists);
    }

    private void setGroupNameToCourseInfo(dsw dswVar) {
        for (dsv dsvVar : dswVar.getGroupLists()) {
            if (dsvVar != null && !koq.b(dsvVar.getCourseInfoList())) {
                for (int i = 0; i < dsvVar.getCourseInfoList().size(); i++) {
                    dsu dsuVar = dsvVar.getCourseInfoList().get(i);
                    if (i == 0) {
                        dsuVar.setGroupName(dsvVar.getGroupName());
                    }
                    this.mCourseLists.add(dsuVar);
                }
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        czl.d();
    }
}

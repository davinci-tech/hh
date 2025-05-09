package com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.czl;

/* loaded from: classes8.dex */
public class RateControlStageListFragment extends BaseFragment {
    public static final String COURSE_ID = "course_id";
    private static final String TAG = "RateControlStageListFragment";
    private RateControlStageListAdapter mActionsListAdapter;
    private Activity mContext;
    private String mCourseId;
    private HealthRecycleView mRvActions;
    private int mType;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mType = arguments.getInt("deviceType");
            this.mCourseId = arguments.getString("course_id");
        }
        this.mContext = getActivity();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_rate_control_stage_list, viewGroup, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View view) {
        HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.rv_actions_list);
        this.mRvActions = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.mContext));
    }

    private void initData() {
        this.mRvActions.setLayoutManager(new LinearLayoutManager(this.mContext));
        RateControlStageListAdapter rateControlStageListAdapter = new RateControlStageListAdapter(this.mContext, this.mType);
        this.mActionsListAdapter = rateControlStageListAdapter;
        this.mRvActions.setAdapter(rateControlStageListAdapter);
        czl.a(this.mType, this.mCourseId, (ConfigCallBack<FitWorkout>) new ConfigCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlStageListFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj) {
                RateControlStageListFragment.lambda$initData$0((FitWorkout) obj);
            }
        });
    }

    static /* synthetic */ void lambda$initData$0(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.h(TAG, "courseInfo is null");
        }
    }
}

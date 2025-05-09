package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView;
import com.huawei.ui.main.stories.health.fragment.rqpackage.StateIndexDayHelper;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.nsn;
import java.io.Serializable;

/* loaded from: classes6.dex */
public abstract class BaseStateIndexDetailFragment extends BaseFragment {
    protected static final String TAG = "BaseStateIndexDetailFragment";
    private HealthCalendar mCalendar;
    protected Context mContext;
    protected HealthTextView mCurrentFragmentTopText;
    protected HealthTextView mCurrentLevelText;
    protected LinearLayout mCurrentRawLayout;
    protected DataInfos mDataInfo;
    protected LinearLayout mDayViewLinearLayout;
    protected HealthTextView mFatigueIndexText;
    protected HealthTextView mFitnessIndexText;
    protected HealthLevelIndicator mHealthLevelIndicator;
    protected HealthRecycleView mHealthRecyclerView;
    protected boolean mIsLoadingState = false;
    protected ImageView mJumpHorizontalImg;
    protected RelativeLayout mPolyLineDetailLayout;
    protected HealthProgressBar mProgressBar;
    private RqLineChartHolderView mRqLineChartHolderView;
    protected RunningStateIndexData mRunningStateIndexData;
    protected HealthTextView mStateIndexDayTextView;
    protected HealthTextView mStateIndexViewLevel;
    protected HealthTextView mStateIndexViewValue;
    protected HealthSubHeader mStateMainCard;
    protected HealthTextView mStateMainCardExplain;
    protected View mView;

    protected abstract DataInfos getDataInfo();

    protected abstract void initFragmentView(View view, int i);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (nsn.s()) {
            if (this.mView == null) {
                this.mView = layoutInflater.inflate(R.layout.fragment_health_state_index_three_layout, viewGroup, false);
            }
        } else if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_health_state_index_layout, viewGroup, false);
        }
        this.mContext = getActivity();
        initView();
        initFragmentView(this.mView, 0);
        return this.mView;
    }

    protected void initView() {
        this.mStateIndexViewValue = (HealthTextView) this.mView.findViewById(R.id.state_index_view_value);
        this.mStateIndexViewLevel = (HealthTextView) this.mView.findViewById(R.id.state_index_view_level);
        this.mCurrentRawLayout = (LinearLayout) this.mView.findViewById(R.id.current_fragment_ll);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.mView.findViewById(R.id.state_main_fragment_card);
        this.mStateMainCard = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        this.mStateMainCardExplain = (HealthTextView) this.mView.findViewById(R.id.state_main_fragment_card_explain);
        this.mPolyLineDetailLayout = (RelativeLayout) this.mView.findViewById(R.id.running_line_detail_rl);
        this.mDayViewLinearLayout = (LinearLayout) this.mView.findViewById(R.id.state_day_params);
        this.mHealthRecyclerView = (HealthRecycleView) this.mView.findViewById(R.id.state_index_horizontal_recycler);
        this.mHealthLevelIndicator = (HealthLevelIndicator) this.mView.findViewById(R.id.current_state_level_indicator);
        this.mRqLineChartHolderView = (RqLineChartHolderView) this.mView.findViewById(R.id.rq_chart_line_view_holder);
        this.mStateIndexDayTextView = (HealthTextView) this.mView.findViewById(R.id.state_index_day_text_view);
        StateIndexDayHelper stateIndexDayHelper = new StateIndexDayHelper(this.mContext);
        if (((int) this.mStateIndexDayTextView.getPaint().measureText(String.valueOf(stateIndexDayHelper.dEx_(false)))) > (getResources().getDisplayMetrics().widthPixels * 2) / 3) {
            this.mStateIndexDayTextView.setText(stateIndexDayHelper.dEx_(true));
        } else {
            this.mStateIndexDayTextView.setText(stateIndexDayHelper.dEx_(false));
        }
        initStartViewListener();
        Bundle arguments = getArguments();
        if (arguments == null || !(arguments.getParcelable("state_index_level_data") instanceof RunningStateIndexData)) {
            return;
        }
        this.mRunningStateIndexData = (RunningStateIndexData) arguments.getParcelable("state_index_level_data");
    }

    private void initStartViewListener() {
        this.mRqLineChartHolderView.setStartCalendarViewListener(new RqLineChartHolderView.StartCalendarViewListener() { // from class: com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment.2
            @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView.StartCalendarViewListener
            public void onStartCalendarView() {
                BaseStateIndexDetailFragment baseStateIndexDetailFragment = BaseStateIndexDetailFragment.this;
                HealthCalendarActivity.e(baseStateIndexDetailFragment, baseStateIndexDetailFragment.mCalendar);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1 || intent == null) {
            return;
        }
        LogUtil.a(TAG, "mCalendar=" + this.mCalendar);
        Serializable serializableExtra = intent.getSerializableExtra("selectedDate");
        if (serializableExtra instanceof HealthCalendar) {
            HealthCalendar healthCalendar = (HealthCalendar) serializableExtra;
            this.mCalendar = healthCalendar;
            this.mRqLineChartHolderView.a(healthCalendar.transformCalendar().getTimeInMillis(), this.mCalendar);
        }
    }
}

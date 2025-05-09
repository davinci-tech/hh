package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqRunningHelper;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.jdx;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes6.dex */
public abstract class BaseRunningDetailFragment extends BaseFragment {
    protected static final int SHOW_LOADING_ANIMATION_UI = 3;
    protected static final String TAG = "Track_BaseRunningDetailFragment";
    protected static final int UPDATE_RUNNING_WEEK_VIEW = 2;
    protected static final int UPDATE_VO2MAX_PROGRESS = 1;
    protected boolean isCurrentFragment;
    protected HealthTextView mActivityRqrunningLeveltext;
    private HealthCalendar mCalendar;
    protected Context mContext;
    protected HealthTextView mCurrentFragmentTopText;
    protected LinearLayout mCurrentRawLayout;
    protected int mGender;
    protected HealthLevelIndicator mHealthLevelIndicator;
    protected RelativeLayout mPolyLineDetailLayout;
    protected HealthProgressBar mProgressBar;
    protected HealthTextView mRateCurrentText;
    private RqLineChartHolderView mRqLineChartHolderView;
    protected RunningStateIndexData mRunningStateIndexData;
    private HealthSubHeader mRunningTitleText;
    protected View mView;
    private int mVo2maxValue;
    protected Handler mRequestHandler = new Handler() { // from class: com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof GetRunLevelRsp) {
                    BaseRunningDetailFragment.this.updateLayout((GetRunLevelRsp) message.obj);
                }
            } else if (i == 2) {
                LogUtil.a(BaseRunningDetailFragment.TAG, "Running_data_receiver");
                BaseRunningDetailFragment.this.updateBar();
            } else if (i == 3 && BaseRunningDetailFragment.this.mIsLoadingState && BaseRunningDetailFragment.this.mProgressBar != null) {
                BaseRunningDetailFragment.this.mProgressBar.setVisibility(0);
            }
        }
    };
    protected int mChangeDateCount = 0;
    protected boolean mIsLoadingState = false;

    protected abstract DataInfos getDataInfo();

    protected abstract void initFragmentView();

    public void requestData() {
    }

    protected abstract void updateBar();

    protected void updateGenderView(int i) {
    }

    public void updateLayout(GetRunLevelRsp getRunLevelRsp) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_health_running_detail, viewGroup, false);
        }
        this.mContext = getActivity();
        initTextViews();
        initFragmentView();
        initAchievementPredictionFragment();
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment.2
            @Override // java.lang.Runnable
            public void run() {
                BaseRunningDetailFragment.this.requestData();
            }
        });
        return this.mView;
    }

    protected void initChartConfig() {
        this.mRqLineChartHolderView = (RqLineChartHolderView) this.mView.findViewById(R.id.rq_chart_line_view_holder);
        initStartViewListener();
        this.mRqLineChartHolderView.d(getDataInfo(), 0, 0);
    }

    private void initTextViews() {
        this.mCurrentRawLayout = (LinearLayout) this.mView.findViewById(R.id.current_fragment_ll);
        this.mPolyLineDetailLayout = (RelativeLayout) this.mView.findViewById(R.id.running_line_detail_rl);
        this.mActivityRqrunningLeveltext = (HealthTextView) this.mView.findViewById(R.id.activity_rq_running_help_text_view);
        RqRunningHelper rqRunningHelper = new RqRunningHelper(this.mContext);
        if (((int) this.mActivityRqrunningLeveltext.getPaint().measureText(String.valueOf(rqRunningHelper.dEv_(false)))) > (getResources().getDisplayMetrics().widthPixels * 2) / 3) {
            this.mActivityRqrunningLeveltext.setText(rqRunningHelper.dEv_(true));
        } else {
            this.mActivityRqrunningLeveltext.setText(rqRunningHelper.dEv_(false));
        }
        this.mCurrentRawLayout.setVisibility(this.isCurrentFragment ? 0 : 8);
        this.mPolyLineDetailLayout.setVisibility(this.isCurrentFragment ? 8 : 0);
        HealthSubHeader healthSubHeader = (HealthSubHeader) this.mView.findViewById(R.id.running_is_running_title);
        this.mRunningTitleText = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        this.mHealthLevelIndicator = (HealthLevelIndicator) this.mView.findViewById(R.id.current_running_level_indicator);
        Bundle arguments = getArguments();
        if (arguments != null && (arguments.getParcelable("running_level_data") instanceof RunningStateIndexData)) {
            this.mRunningStateIndexData = (RunningStateIndexData) arguments.getParcelable("running_level_data");
        }
        if (arguments != null) {
            this.mGender = arguments.getInt("vo2max_gender");
            this.mVo2maxValue = arguments.getInt("vo2max_value");
        }
        checkNullObject();
        this.mProgressBar = (HealthProgressBar) this.mView.findViewById(R.id.loading_iv);
        this.mChangeDateCount = 0;
    }

    private void checkNullObject() {
        if (this.mRunningStateIndexData == null) {
            this.mRunningStateIndexData = new RunningStateIndexData();
        }
        if (this.mRunningStateIndexData.getRunningLevelCurrentData() == null) {
            this.mRunningStateIndexData.setRunningLevelCurrentData(new RunningLevelCurrentData());
        }
        if (this.mRunningStateIndexData.getData() == null) {
            this.mRunningStateIndexData.setData(new HashMap());
        }
    }

    protected void isCurrentFragment(boolean z) {
        this.isCurrentFragment = z;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mRequestHandler.removeCallbacksAndMessages(null);
        super.onDestroyView();
    }

    private void initStartViewListener() {
        this.mRqLineChartHolderView.setStartCalendarViewListener(new RqLineChartHolderView.StartCalendarViewListener() { // from class: com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment.3
            @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView.StartCalendarViewListener
            public void onStartCalendarView() {
                BaseRunningDetailFragment baseRunningDetailFragment = BaseRunningDetailFragment.this;
                HealthCalendarActivity.e(baseRunningDetailFragment, baseRunningDetailFragment.mCalendar);
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

    private void initAchievementPredictionFragment() {
        AchievementPredictionFragment achievementPredictionFragment = new AchievementPredictionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("running_level_data", this.mRunningStateIndexData);
        bundle.putInt("vo2max_value", this.mVo2maxValue);
        achievementPredictionFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, achievementPredictionFragment).commit();
    }
}

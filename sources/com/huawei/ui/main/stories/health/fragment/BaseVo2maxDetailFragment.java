package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView;
import com.huawei.ui.main.stories.health.fragment.rqpackage.Vo2maxLevelHelper;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.jdx;
import defpackage.kor;
import defpackage.nsy;
import defpackage.pfe;
import defpackage.qrv;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes6.dex */
public abstract class BaseVo2maxDetailFragment extends BaseFragment {
    private static final int GENDER_MAN = 0;
    protected static final int SHOW_LOADING_ANIMATION_UI = 3;
    private static final String TAG = "Track_BaseVo2maxDetailFragment";
    protected static final int UPDATE_VO2MAX_BAR = 2;
    protected static final int UPDATE_VO2MAX_PROGRESS = 1;
    protected int mAge;
    private HealthCalendar mCalendar;
    private LinearLayout mConfiguredLayout;
    protected Context mContext;
    protected HealthTextView mCurrentRanking;
    protected LinearLayout mCurrentRawLayout;
    protected HashMap<Long, Integer> mDataMap;
    protected HealthTextView mDayViewData;
    protected int mGender;
    protected HealthLevelIndicator mHealthLevelIndicator;
    protected boolean mIsDayFragment;
    private HealthTextView mLevelTextView;
    protected LinearLayout mMessageService;
    protected Integer[] mOxRange;
    protected RelativeLayout mPolyLineDetailLayout;
    protected HealthProgressBar mProgressBar;
    private RqLineChartHolderView mRqLineChartHolderView;
    private RunningStateIndexData mRunningStateIndexData;
    protected View mView;
    private LinearLayout mVo2maxApiLayout;
    protected long mVo2maxTime;
    private LinearLayout mVo2maxTimeLayout;
    private HealthTextView mVo2maxTimeTextView;
    protected int mVo2maxValue;
    protected boolean mIsLoadingState = false;
    protected Handler mRequestHandler = new Handler() { // from class: com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof Vo2maxDetail) {
                    BaseVo2maxDetailFragment.this.updateLayout((Vo2maxDetail) message.obj);
                    return;
                }
                return;
            }
            if (i == 2) {
                LogUtil.a(BaseVo2maxDetailFragment.TAG, "UPDATE_VO2MAX_BAR");
                BaseVo2maxDetailFragment.this.updateBar();
            } else if (i == 3 && BaseVo2maxDetailFragment.this.mIsLoadingState && BaseVo2maxDetailFragment.this.mProgressBar != null) {
                BaseVo2maxDetailFragment.this.mProgressBar.setVisibility(0);
            }
        }
    };
    protected kor mDataManager = kor.a();

    protected abstract DataInfos getDataInfo();

    protected abstract void initFragmentView();

    public void requestData() {
    }

    protected void updateBar() {
    }

    protected void updateDateArrowVisible(int i, int i2) {
    }

    public void updateLayout(Vo2maxDetail vo2maxDetail) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(R.layout.fragment_health_vo2max_detail, viewGroup, false);
        }
        this.mContext = getActivity();
        this.mIsLoadingState = false;
        initTextViews();
        initViewPagerData();
        initFragmentView();
        initConfiguredPageData();
        initAchievementPredictionFragment();
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment.2
            @Override // java.lang.Runnable
            public void run() {
                BaseVo2maxDetailFragment.this.requestData();
            }
        });
        return this.mView;
    }

    protected void isCurrentFragment(boolean z) {
        this.mIsDayFragment = z;
    }

    private void initTextViews() {
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(this.mView, R.id.message_service);
        this.mMessageService = linearLayout;
        linearLayout.setVisibility(0);
        this.mVo2maxTimeLayout = (LinearLayout) nsy.cMd_(this.mView, R.id.hw_show_health_data_vo2max_mid_layout);
        this.mVo2maxTimeTextView = (HealthTextView) nsy.cMd_(this.mView, R.id.hw_show_health_data_vo2max_mid_time);
        this.mVo2maxApiLayout = (LinearLayout) nsy.cMd_(this.mView, R.id.vo2max_marketing_layout);
        this.mCurrentRawLayout = (LinearLayout) this.mView.findViewById(R.id.current_fragment_ll);
        this.mCurrentRanking = (HealthTextView) this.mView.findViewById(R.id.current_fragment_bottom_text);
        this.mPolyLineDetailLayout = (RelativeLayout) this.mView.findViewById(R.id.v2max_main_line_detail_rl);
        this.mCurrentRawLayout.setVisibility(this.mIsDayFragment ? 0 : 8);
        this.mPolyLineDetailLayout.setVisibility(this.mIsDayFragment ? 8 : 0);
        ((HealthSubHeader) this.mView.findViewById(R.id.vo2max_sub_header)).setSubHeaderBackgroundColor(getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        this.mHealthLevelIndicator = (HealthLevelIndicator) this.mView.findViewById(R.id.current_state_level_indicator);
        this.mDayViewData = (HealthTextView) this.mView.findViewById(R.id.current_fragment_top_text);
        this.mLevelTextView = (HealthTextView) this.mView.findViewById(R.id.vo2max_level_text_view);
        Vo2maxLevelHelper vo2maxLevelHelper = new Vo2maxLevelHelper(this.mContext);
        if (((int) this.mLevelTextView.getPaint().measureText(String.valueOf(vo2maxLevelHelper.dEz_(false)))) > (getResources().getDisplayMetrics().widthPixels * 2) / 3) {
            this.mLevelTextView.setText(vo2maxLevelHelper.dEz_(true));
        } else {
            this.mLevelTextView.setText(vo2maxLevelHelper.dEz_(false));
        }
    }

    private void initViewPagerData() {
        this.mProgressBar = (HealthProgressBar) nsy.cMd_(this.mView, R.id.loading_iv);
        HealthScrollView healthScrollView = (HealthScrollView) nsy.cMd_(this.mView, R.id.vo2max_scrollview);
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        ScrollUtil.cKx_(healthScrollView, getActivity().getWindow().getDecorView(), 3041);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mGender = arguments.getInt("vo2max_gender", 0);
            int i = arguments.getInt("vo2max_age", 0);
            this.mAge = i;
            this.mOxRange = qrv.a(this.mGender, i);
            this.mVo2maxValue = arguments.getInt("vo2max_value");
            this.mVo2maxTime = arguments.getLong("vo2max_time");
            Parcelable parcelable = arguments.getParcelable("running_level_data");
            if (parcelable instanceof RunningStateIndexData) {
                this.mRunningStateIndexData = (RunningStateIndexData) parcelable;
                return;
            }
            return;
        }
        this.mGender = 0;
        this.mOxRange = qrv.a(0, 0);
    }

    protected void initChartConfig() {
        this.mRqLineChartHolderView = (RqLineChartHolderView) this.mView.findViewById(R.id.rq_chart_line_view_holder);
        initStartViewListener();
        this.mRqLineChartHolderView.d(getDataInfo(), 2, 0);
    }

    protected void initConfiguredPageData() {
        if (CommonUtil.bu()) {
            return;
        }
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        this.mConfiguredLayout = linearLayout;
        linearLayout.setId(R.id.vo2max_month_configure_page_detail);
        this.mConfiguredLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mConfiguredLayout.setOrientation(1);
        this.mMessageService.addView(this.mConfiguredLayout);
        pfe.doh_(17, this.mConfiguredLayout, null);
        initMarketing();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LinearLayout linearLayout = this.mConfiguredLayout;
        if (linearLayout != null) {
            pfe.dok_(17, linearLayout);
        }
    }

    private void initMarketing() {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.h(TAG, "initMarketing marketingApi is null");
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put(4039, this.mVo2maxApiLayout);
        marketingApi.requestMarketingResource(new MarketingOption.Builder().setContext(getActivity()).setPageId(17).setLayoutMap(hashMap).build());
        this.mVo2maxApiLayout.setVisibility(0);
    }

    private void initStartViewListener() {
        this.mRqLineChartHolderView.setStartCalendarViewListener(new RqLineChartHolderView.StartCalendarViewListener() { // from class: com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment.1
            @Override // com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineChartHolderView.StartCalendarViewListener
            public void onStartCalendarView() {
                BaseVo2maxDetailFragment baseVo2maxDetailFragment = BaseVo2maxDetailFragment.this;
                HealthCalendarActivity.e(baseVo2maxDetailFragment, baseVo2maxDetailFragment.mCalendar);
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

package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.huawei.agconnect.apms.Agent;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoReport;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.manager.model.WeekAndMonthRecord;
import com.huawei.pluginachievement.ui.adapter.BreakInfoRecyclerAdapter;
import com.huawei.pluginachievement.ui.adapter.ReportMedalRecyclerAdapter;
import com.huawei.pluginachievement.ui.report.BaseReportActivity;
import com.huawei.pluginachievement.ui.views.ReportTypeCardView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.reportchart.HwHealthReportBarChart;
import com.huawei.ui.commonui.scrollview.HealthNestedScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.undertextview.ReportUnderlineTextView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.up.model.UserInfomation;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mct;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mdn;
import defpackage.meh;
import defpackage.mer;
import defpackage.mex;
import defpackage.mfc;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mkn;
import defpackage.mkw;
import defpackage.mla;
import defpackage.mlb;
import defpackage.mlg;
import defpackage.mlh;
import defpackage.mmb;
import defpackage.nip;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public abstract class BaseReportActivity extends BaseActivity implements AchieveObserver {
    private static final String DATA_QUERY_END_TIME = "dataQueryEndTime";
    private static final String DATA_QUERY_START_TIME = "dataQueryStartTime";
    private static final int KILOBYTES = 1024;
    private static final int MAP_INITIAL_SIZE = 4;
    private static final int MAX_SIZE_MB = 1;
    private static final int MSG_GET_USER_INFO_FAIL = 1001;
    private static final int MSG_GET_USER_INFO_SUCCESS = 1000;
    private static final int RECORD_MIN_NUM = 2;
    private static final String TAG = "PLGACHIEVE_BaseReportActivity";
    private static final int WHAT_GET_ALL_MEDAL_CONFIGURE = 1002;
    private static final int WHAT_GET_DATA_EMPTY = 1009;
    private static final int WHAT_GET_DATA_SUCCESS = 1007;
    private static final int WHAT_GET_DATA_SUCCESS_CONTINUE = 1008;
    private static final int WHAT_REFRESH_BREAK_INFO = 1005;
    private static final int WHAT_REFRESH_DATA_FROM_NET = 1006;
    private static final int WHAT_REFRESH_WEEK_AND_MONTH_VIEW = 1004;
    private HealthTextView mBreakInfoDescText;
    private HealthCardView mBreakInfoItemLayout;
    private BreakInfoRecyclerAdapter mBreakInfoRecyclerAdapter;
    private HealthRecycleView mBreakInfoRecyclerView;
    private HealthTextView mComplianceDaysDescText;
    private ImageView mComplianceDaysImageView;
    private HealthTextView mComplianceDaysValueText;
    protected LinearLayout mContentLinearLayout;
    protected Context mContext;
    protected CustomTitleBar mCustomTitleBar;
    protected ExecutorService mExecutor;
    protected HealthTextView mExerciseAvgCaloriesTitleText;
    protected HealthTextView mExerciseAvgCaloriesValueText;
    protected HealthTextView mExerciseComparedCaloriesDescText;
    protected HealthTextView mExerciseComparedTimeValueText;
    protected HealthTextView mExerciseDescText;
    protected ImageView mExerciseHeatImage;
    private HealthCardView mExerciseItemLayout;
    protected ImageView mExerciseTimeImage;
    protected HealthTextView mExerciseTotalCaloriesTitleText;
    protected HealthTextView mExerciseTotalCaloriesValueText;
    protected HealthTextView mExerciseTotalTimeTitleText;
    protected HealthTextView mExerciseTotalTimeValueText;
    protected HealthCardView mExerciseTypeItemLayout;
    private HealthProgressBar mLoadingView;
    protected HealthTextView mMedalDescTextView;
    private ImageView mMedalItemBackground;
    private HealthCardView mMedalItemLayout;
    private HealthTextView mMedalNumbersTextView;
    private ReportMedalRecyclerAdapter mMedalRecyclerAdapter;
    protected HealthRecycleView mMedalRecyclerView;
    private HealthTextView mNoReportLayout;
    protected ImageView mPersonalHeaderIcon;
    private HealthTextView mReportAvgDailyStepText;
    private HealthTextView mReportAvgDailyStepTitleText;
    private LinearLayout mReportCardLayout;
    private HealthTextView mReportComparedStepText;
    private HealthTextView mReportComparedStepTitleText;
    private FrameLayout mReportContentLayout;
    private long mReportEndTime;
    protected HealthTextView mReportExerciseTypeDec;
    protected ReportUnderlineTextView mReportExerciseTypeTitle;
    protected ImageView mReportHeadRootBackground;
    protected RelativeLayout mReportHeadRootLayout;
    protected HealthTextView mReportIntervalDateNoText;
    protected HealthTextView mReportIntervalDateText;
    private FrameLayout mReportLoadingView;
    protected HwHealthReportBarChart mReportStepBarChart;
    private HealthTextView mReportStepDescText;
    protected HealthTextView mReportStepGoalText;
    protected ReportTypeCardView mReportTypePieChart;
    protected View mReportUserView;
    protected Resources mResource;
    private meh mService;
    protected LinearLayout mShareLogoLayout;
    protected HealthNestedScrollView mShareView;
    protected HealthCardView mStepItemLayout;
    private int mStepTarget;
    private UserInfomation mUserInfo;
    private UserProfileMgrApi mUserProfileMgrApi;
    protected HealthTextView mUsernameText;
    protected int mUsernameTextColor;
    protected ImageView mVipImage;
    private ImageView mWalkingMan;
    protected int mMaxReportNo = 0;
    protected int mMinReportNo = 0;
    protected boolean mIsRtlLanguage = false;
    protected boolean mIsImperialUnit = false;
    private boolean mIsShare = false;
    private boolean mIsGetNewData = false;
    private boolean mIsFirstGetData = false;
    private List<mcz> mMedalConfigInfoList = new ArrayList(10);
    private Handler mHandler = new d(Looper.getMainLooper(), this);
    private boolean mIsGetAllMedalData = false;
    private boolean isDoRefreshing = false;

    protected abstract void findViewsById();

    protected abstract String getAvgDailyStepTitleText();

    protected abstract long getBarChartEndTime();

    protected abstract long getBarChartStartTime();

    protected abstract String getCachedMaxReportNo();

    protected abstract String getCachedMinReportNo();

    protected abstract String getClassName();

    protected abstract String getComparedStepTitleText();

    protected abstract int getContentType();

    protected abstract String getCustomTitleBarText();

    protected abstract String getExerciseAvgCaloriesTitle();

    protected abstract String getExerciseCaloriesTitleText();

    protected abstract int getLayoutId();

    protected abstract CharSequence getMedalNumbersText(int i);

    protected abstract String getNoReportHint();

    protected abstract String getReportRecentType();

    protected abstract int getReportType();

    protected abstract String getSuccessShareBiValue();

    protected abstract void refreshAchieveReportView(WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, Context context, mkn mknVar);

    protected abstract void refreshBarChart(WeekAndMonthRecord weekAndMonthRecord);

    protected abstract void refreshExerciseTypeView(WeekAndMonthRecord weekAndMonthRecord);

    protected abstract void refreshExerciseView(Context context, WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2, mkn mknVar);

    protected abstract void refreshMedalView(WeekAndMonthRecord weekAndMonthRecord);

    protected abstract void setBreakInfoBg(ImageView imageView);

    protected abstract void setMedalImageHeight(ImageView imageView, int i);

    protected abstract void startHistoricalActivityForResult();

    protected void initStepBarChartSet(List<BarEntry> list, ArrayList<String> arrayList, int i, int i2) {
        BarDataSet barDataSet = new BarDataSet(list, Agent.OS_NAME);
        barDataSet.setColor(getResources().getColor(R.color._2131296485_res_0x7f0900e5));
        barDataSet.setDrawValues(false);
        barDataSet.setBarBorderWidth(2.0f);
        ArrayList arrayList2 = new ArrayList(2);
        arrayList2.add(barDataSet);
        this.mReportStepBarChart.setData(new BarData(arrayList2));
        this.mReportStepBarChart.setCustomValue(this.mContext, arrayList, getResources().getColor(R.color._2131296486_res_0x7f0900e6), this.mStepTarget, i2, i, false);
        this.mReportStepBarChart.invalidate();
    }

    /* loaded from: classes8.dex */
    static class d extends BaseHandler<BaseReportActivity> {
        private d(Looper looper, BaseReportActivity baseReportActivity) {
            super(looper, baseReportActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cjT_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BaseReportActivity baseReportActivity, Message message) {
            if (message == null) {
                LogUtil.h(BaseReportActivity.TAG, "handleMessageWhenReferenceNotNull activity or message is null.");
                return;
            }
            LogUtil.a(BaseReportActivity.TAG, "showReport_handleMessage message = ", Integer.valueOf(message.what));
            switch (message.what) {
                case 1000:
                    if (message.obj instanceof String) {
                        baseReportActivity.showUserHeadImage((String) message.obj);
                        return;
                    } else {
                        LogUtil.h(BaseReportActivity.TAG, "MSG_GET_USER_INFO_SUCCESS is not user information!");
                        return;
                    }
                case 1001:
                    baseReportActivity.mPersonalHeaderIcon.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
                    return;
                case 1002:
                    baseReportActivity.mIsGetAllMedalData = true;
                    return;
                case 1003:
                default:
                    return;
                case 1004:
                    if (!(message.obj instanceof List)) {
                        baseReportActivity.showNoReportDataView();
                        return;
                    }
                    List list = (List) message.obj;
                    Bundle data = message.getData();
                    if (data != null) {
                        baseReportActivity.refreshStepAndExerciseView(data.getLong(BaseReportActivity.DATA_QUERY_START_TIME), data.getLong(BaseReportActivity.DATA_QUERY_END_TIME), baseReportActivity.removeDuplicateData(list));
                        return;
                    } else {
                        LogUtil.h(BaseReportActivity.TAG, "message = WHAT_REFRESH_WEEK_AND_MONTH_VIEW, data is null.");
                        baseReportActivity.showNoReportDataView();
                        return;
                    }
                case 1005:
                    if (message.obj instanceof Map) {
                        baseReportActivity.refreshBreakInfoViews((Map) message.obj);
                        return;
                    }
                    return;
                case 1006:
                    LogUtil.a(BaseReportActivity.TAG, "refresh WHAT_REFRESH_DATA_FROM_NET");
                    baseReportActivity.refreshDataFromNet(message);
                    return;
                case 1007:
                    break;
                case 1008:
                    LogUtil.a(BaseReportActivity.TAG, "refresh WHAT_GET_DATA_SUCCESS_CONTINUE");
                    if (baseReportActivity.mIsFirstGetData) {
                        LogUtil.a(BaseReportActivity.TAG, "NO refresh");
                        return;
                    }
                    break;
                case 1009:
                    LogUtil.a(BaseReportActivity.TAG, "refresh WHAT_GET_DATA_FAIL");
                    baseReportActivity.showNoReportDataView();
                    return;
            }
            LogUtil.a(BaseReportActivity.TAG, "refresh WHAT_GET_DATA_SUCCESS");
            baseReportActivity.refreshDataFromDb(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDataFromDb(Message message) {
        this.mIsGetNewData = true;
        closeProgress();
        if (!(message.obj instanceof long[])) {
            LogUtil.h(TAG, "refreshDataFromDb() message is null.return.");
            return;
        }
        long[] jArr = (long[]) message.obj;
        final long j = jArr[0];
        final long j2 = jArr[1];
        this.mExecutor.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.3
            @Override // java.lang.Runnable
            public void run() {
                BaseReportActivity.this.refreshDataFromDbInner(j, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDataFromDbInner(long j, long j2) {
        LogUtil.a(TAG, "refreshDataFromDb() startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2));
        List<mcz> b2 = meh.c(this.mContext).b(20, getReportParamMap(j, j2));
        if (koq.c(b2)) {
            LogUtil.a(TAG, "refreshDataFromDb achieveDataList size ", Integer.valueOf(b2.size()));
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1004;
            obtainMessage.obj = b2;
            Bundle bundle = new Bundle();
            bundle.putLong(DATA_QUERY_START_TIME, j);
            bundle.putLong(DATA_QUERY_END_TIME, j2);
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        LogUtil.a(TAG, "refreshDataFromDb achieveDataList is empty.");
        Message obtainMessage2 = this.mHandler.obtainMessage();
        obtainMessage2.what = 1009;
        this.mHandler.sendMessage(obtainMessage2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDataFromNet(Message message) {
        if (message == null) {
            LogUtil.h(TAG, "refreshDataFromNet() message is null.return.");
            return;
        }
        if (this.isDoRefreshing) {
            refreshViewByNetData(message.arg1, message.arg2);
        }
        this.isDoRefreshing = false;
    }

    private void refreshViewByNetData(int i, int i2) {
        if (i == -1) {
            LogUtil.h(TAG, "onDataChanged error=", Integer.valueOf(i));
        } else if (i2 == 0) {
            initUserInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBreakInfoViews(Map<Integer, Pair<Double, Long>> map) {
        if (map == null || map.size() <= 0) {
            LogUtil.a(TAG, "refreshBreakView data is null");
            initBreakInfoView(8);
            return;
        }
        initBreakInfoView(0);
        BreakInfoRecyclerAdapter breakInfoRecyclerAdapter = this.mBreakInfoRecyclerAdapter;
        if (breakInfoRecyclerAdapter == null) {
            this.mBreakInfoRecyclerAdapter = new BreakInfoRecyclerAdapter(this.mContext, map);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mContext, 1);
            this.mBreakInfoRecyclerView.setAdapter(this.mBreakInfoRecyclerAdapter);
            this.mBreakInfoRecyclerView.setLayoutManager(gridLayoutManager);
            this.mBreakInfoRecyclerView.a(false);
            this.mBreakInfoRecyclerView.d(false);
            LogUtil.a(TAG, "refreshBreakInfoViews() breakInfoMap = ", Integer.valueOf(map.size()));
        } else {
            breakInfoRecyclerAdapter.c(map);
        }
        this.mBreakInfoDescText.setText(mlh.e(this.mContext, map.size()));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mContext = this;
        this.mResource = getResources();
        if (!CommonUtil.aa(this.mContext)) {
            nsn.a(this.mContext, getClassName(), getCustomTitleBarText(), false);
            finish();
            return;
        }
        this.mIsRtlLanguage = LanguageUtil.bc(this.mContext);
        this.mIsImperialUnit = UnitUtil.h();
        this.mUserProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        registerAchieveObserver();
        initReportNumber();
        setContentView(getLayoutId());
        initView();
        findViewsById();
        BaseActivity.cancelLayoutById(this.mReportHeadRootLayout);
        getStepGoal();
        initData();
    }

    private void initReportNumber() {
        Intent intent = getIntent();
        if (intent != null) {
            this.mMaxReportNo = mlb.l(intent.getStringExtra("max_report_no"));
            this.mMinReportNo = mlb.l(intent.getStringExtra("min_report_no"));
        }
        clearBackgroundDrawable();
        int i = 0;
        try {
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "NumberFormatException");
        }
        if (getCachedMaxReportNo() != null && getCachedMinReportNo() != null) {
            i = Integer.parseInt(getCachedMaxReportNo());
            this.mMinReportNo = Integer.parseInt(getCachedMinReportNo());
            int max = Math.max(i, this.mMaxReportNo);
            this.mMaxReportNo = max;
            LogUtil.a(TAG, "maxReportNo:", Integer.valueOf(max), " minReportNo:", Integer.valueOf(this.mMinReportNo));
            int i2 = this.mMaxReportNo;
            if (i2 < this.mMinReportNo) {
                this.mMinReportNo = i2;
                return;
            }
            return;
        }
        LogUtil.h(TAG, "getCachedMaxReportNo or getCachedMinReportNo is null!");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtil.a(TAG, "onStart");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.a(TAG, "onStop");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(1004);
        }
        meh mehVar = this.mService;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        mmb.d(this.mExecutor);
        super.onDestroy();
    }

    private void registerAchieveObserver() {
        this.mService = meh.c(BaseApplication.getContext());
        LogUtil.a(TAG, "getData()");
        this.mService.b((AchieveObserver) this);
    }

    protected void initData() {
        initUserInfo();
        initStepData(getBarChartStartTime(), getBarChartEndTime());
        getAllMedalData();
    }

    private void getAllMedalData() {
        this.mExecutor.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.5
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap(2);
                BaseReportActivity baseReportActivity = BaseReportActivity.this;
                baseReportActivity.mMedalConfigInfoList = meh.c(baseReportActivity.mContext).b(9, hashMap);
                if (!koq.b(BaseReportActivity.this.mMedalConfigInfoList)) {
                    LogUtil.a(BaseReportActivity.TAG, "getMedalData mMedalConfigInfoList = ", Integer.valueOf(BaseReportActivity.this.mMedalConfigInfoList.size()));
                    if (BaseReportActivity.this.mHandler != null) {
                        BaseReportActivity.this.mHandler.sendEmptyMessage(1002);
                        return;
                    }
                    return;
                }
                LogUtil.h(BaseReportActivity.TAG, "getMedalData mMedalConfigInfoList is empty.");
            }
        });
    }

    protected void initBreakInfo(final long j, final int i) {
        this.mExecutor.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.4
            @Override // java.lang.Runnable
            public void run() {
                Map<Integer, Pair<Double, Long>> b2 = mer.b(BaseReportActivity.this.mContext).b(j, i);
                if (BaseReportActivity.this.mHandler != null) {
                    Message obtainMessage = BaseReportActivity.this.mHandler.obtainMessage();
                    obtainMessage.what = 1005;
                    obtainMessage.obj = b2;
                    BaseReportActivity.this.mHandler.sendMessage(obtainMessage);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshStepAndExerciseView(long j, long j2, List<mcz> list) {
        LogUtil.a(TAG, "refreshStepAndExerciseView() startTime = ", Long.valueOf(j), ", endTime = ", Long.valueOf(j2));
        if (koq.b(list)) {
            showNoReportDataView();
            return;
        }
        LogUtil.a(TAG, "refreshStepAndExerciseView() size = ", Integer.valueOf(list.size()));
        if (this.mReportEndTime != j2) {
            LogUtil.h(TAG, "endTime = ", Long.valueOf(j2), " mReportEndTime= ", Long.valueOf(this.mReportEndTime));
            return;
        }
        WeekAndMonthRecord weekAndMonthRecord = null;
        WeekAndMonthRecord weekAndMonthRecord2 = null;
        for (mcz mczVar : list) {
            if (mczVar == null) {
                LogUtil.h(TAG, "refreshStepAndExerciseView() achieveData is null.");
            } else {
                WeekAndMonthRecord weekAndMonthRecord3 = mczVar instanceof WeekAndMonthRecord ? (WeekAndMonthRecord) mczVar : null;
                if (weekAndMonthRecord3 == null) {
                    LogUtil.h(TAG, "refreshStepAndExerciseView weekAndMonthRecord is null");
                } else {
                    long startTimestamp = weekAndMonthRecord3.getStartTimestamp();
                    long endTimestamp = weekAndMonthRecord3.getEndTimestamp();
                    if (j2 == endTimestamp) {
                        weekAndMonthRecord = weekAndMonthRecord3;
                    } else if (j == startTimestamp) {
                        weekAndMonthRecord2 = weekAndMonthRecord3;
                    } else {
                        LogUtil.h(TAG, "refreshStepAndExerciseView() startTimestamp = ", Long.valueOf(startTimestamp), ", endTimestamp = ", Long.valueOf(endTimestamp));
                    }
                }
            }
        }
        if (weekAndMonthRecord == null) {
            LogUtil.h(TAG, "refreshStepAndExerciseView() currentRecord is null.");
            showNoReportDataView();
        } else {
            if (this.mUserInfo == null) {
                initUserInfo();
            }
            closeProgress();
            showReportDataView(weekAndMonthRecord, weekAndMonthRecord2);
        }
    }

    private void showReportDataView(WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2) {
        this.mNoReportLayout.setVisibility(8);
        this.mShareView.setVisibility(0);
        this.mCustomTitleBar.setRightSoftkeyVisibility(0);
        refreshStepTarget();
        refreshBarChart(weekAndMonthRecord);
        refreshStepOtherView(weekAndMonthRecord, weekAndMonthRecord2);
        refreshExerciseOtherView(weekAndMonthRecord, weekAndMonthRecord2);
        refreshExerciseTypeView(weekAndMonthRecord);
        if (this.mIsGetAllMedalData) {
            refreshMedalView(weekAndMonthRecord);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoReportDataView() {
        if (!this.mIsGetNewData) {
            showProgress();
            return;
        }
        this.mNoReportLayout.setVisibility(0);
        this.mNoReportLayout.setText(getNoReportHint());
        this.mShareView.setVisibility(8);
        this.mCustomTitleBar.setRightSoftkeyVisibility(8);
    }

    private void closeProgress() {
        LogUtil.a(TAG, "closeProgressDialog");
        this.mReportContentLayout.setVisibility(0);
        this.mReportLoadingView.setVisibility(8);
    }

    private void showProgress() {
        LogUtil.a(TAG, "showProgressDialog");
        this.mReportContentLayout.setVisibility(8);
        this.mReportLoadingView.setVisibility(0);
    }

    protected void refreshExerciseOtherView(WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2) {
        mkn mknVar = new mkn();
        mknVar.g(this.mExerciseDescText);
        mknVar.o(this.mExerciseTotalTimeValueText);
        mknVar.f(this.mExerciseComparedTimeValueText);
        mknVar.cjK_(this.mExerciseTimeImage);
        mknVar.cjJ_(this.mExerciseHeatImage);
        mknVar.l(this.mExerciseTotalCaloriesValueText);
        mknVar.h(this.mExerciseComparedCaloriesDescText);
        mknVar.j(this.mExerciseAvgCaloriesValueText);
        refreshExerciseView(this.mContext, weekAndMonthRecord, weekAndMonthRecord2, mknVar);
    }

    private void refreshStepOtherView(WeekAndMonthRecord weekAndMonthRecord, WeekAndMonthRecord weekAndMonthRecord2) {
        mkn mknVar = new mkn();
        mknVar.b(this.mIsImperialUnit);
        mknVar.k(this.mReportStepDescText);
        mknVar.a(this.mReportAvgDailyStepText);
        mknVar.d(this.mReportComparedStepText);
        mknVar.d(this.mStepTarget);
        mknVar.cjI_(this.mComplianceDaysImageView);
        mknVar.i(this.mComplianceDaysValueText);
        mknVar.c(this.mComplianceDaysDescText);
        refreshAchieveReportView(weekAndMonthRecord, weekAndMonthRecord2, this.mContext, mknVar);
    }

    private void refreshStepTarget() {
        HealthTextView healthTextView;
        int i = this.mStepTarget;
        if (i != 0) {
            mct.b(this.mContext, "stepGoal", String.valueOf(i));
        } else {
            String b2 = mct.b(this.mContext, "stepGoal");
            if (TextUtils.isEmpty(b2)) {
                getStepGoal();
            } else {
                this.mStepTarget = mlb.l(b2);
            }
        }
        if (this.mStepTarget == 0 || (healthTextView = this.mReportStepGoalText) == null) {
            return;
        }
        healthTextView.setVisibility(0);
        this.mReportStepGoalText.setText(this.mResource.getString(R.string._2130840938_res_0x7f020d6a, UnitUtil.e(this.mStepTarget, 1, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<mcz> removeDuplicateData(List<mcz> list) {
        LogUtil.a(TAG, "removeDuplicateData enter");
        if (list == null || list.size() <= 4) {
            return list;
        }
        ArrayList arrayList = new ArrayList(4);
        ArrayList arrayList2 = new ArrayList(4);
        for (mcz mczVar : list) {
            if (mczVar instanceof WeekAndMonthRecord) {
                long startTimestamp = ((WeekAndMonthRecord) mczVar).getStartTimestamp();
                LogUtil.a(TAG, "removeDuplicateData achieveListData startTimestamp == ", Long.valueOf(startTimestamp));
                if (!arrayList2.contains(Long.valueOf(startTimestamp))) {
                    arrayList.add(mczVar);
                    arrayList2.add(Long.valueOf(startTimestamp));
                }
            }
        }
        Collections.sort(arrayList, new Comparator<mcz>() { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.2
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(mcz mczVar2, mcz mczVar3) {
                if (!(mczVar2 instanceof WeekAndMonthRecord)) {
                    return -1;
                }
                WeekAndMonthRecord weekAndMonthRecord = (WeekAndMonthRecord) mczVar2;
                if (mczVar3 instanceof WeekAndMonthRecord) {
                    return (int) (((WeekAndMonthRecord) mczVar3).getStartTimestamp() - weekAndMonthRecord.getStartTimestamp());
                }
                return -1;
            }
        });
        return arrayList;
    }

    protected void initStepData(final long j, final long j2) {
        this.mIsGetNewData = false;
        this.mExecutor.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseReportActivity.this.initStepDataInner(j, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStepDataInner(long j, long j2) {
        this.mReportEndTime = j2;
        HashMap<String, String> reportParamMap = getReportParamMap(j, j2);
        meh c = meh.c(this.mContext);
        if (c != null) {
            List<mcz> b2 = c.b(20, reportParamMap);
            if (koq.c(b2) && b2.size() >= 2) {
                LogUtil.a(TAG, "initStepData achieveDataList size ", Integer.valueOf(b2.size()));
                Message obtainMessage = this.mHandler.obtainMessage();
                obtainMessage.what = 1004;
                obtainMessage.obj = b2;
                Bundle bundle = new Bundle();
                bundle.putLong(DATA_QUERY_START_TIME, j);
                bundle.putLong(DATA_QUERY_END_TIME, j2);
                obtainMessage.setData(bundle);
                this.mHandler.sendMessage(obtainMessage);
                this.mIsFirstGetData = true;
            } else {
                this.mIsFirstGetData = false;
                LogUtil.h(TAG, "initStepData achieveDataList is empty. ");
                Message obtainMessage2 = this.mHandler.obtainMessage();
                obtainMessage2.what = 1009;
                this.mHandler.sendMessage(obtainMessage2);
            }
            mex.b(this.mContext).b(j, j2, 11, new a(this, j, j2));
        }
    }

    private HashMap<String, String> getReportParamMap(long j, long j2) {
        HashMap<String, String> hashMap = new HashMap<>(4);
        hashMap.put("startTimestamp", String.valueOf(j));
        hashMap.put("endTimeStamp", String.valueOf(j2));
        hashMap.put("dataSource", String.valueOf(2));
        hashMap.put("recentType", getReportRecentType());
        return hashMap;
    }

    protected void setMedalData(ArrayList<String> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h(TAG, "setMedalData medalIdList is empty.");
            return;
        }
        int size = arrayList.size();
        LogUtil.h(TAG, "setMedalData medalIdListSize = ", Integer.valueOf(size));
        this.mMedalNumbersTextView.setText(getMedalNumbersText(size));
        this.mMedalDescTextView.setText(mlh.e(this.mContext, getReportType(), size));
        setMedalImageHeight(this.mMedalItemBackground, size);
        ArrayList arrayList2 = new ArrayList(size);
        Map<String, MedalInfo> c = Utils.o() ? mla.e().c(true) : null;
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                LogUtil.h(TAG, "setMedalData medalId is empty.");
            } else {
                MedalInfoReport medalInfoReport = new MedalInfoReport();
                Iterator<mcz> it2 = this.mMedalConfigInfoList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        mcz next2 = it2.next();
                        if (next2 instanceof MedalConfigInfo) {
                            MedalConfigInfo medalConfigInfo = (MedalConfigInfo) next2;
                            if (next.equals(medalConfigInfo.acquireMedalID())) {
                                medalInfoReport.setMedalId(next);
                                preMedalAdapter(medalInfoReport, medalConfigInfo, c);
                                medalInfoReport.setGrayDetailStyle(medalConfigInfo.acquireGrayDetailStyle());
                                medalInfoReport.setMedalTypeLevel(mdn.d(medalConfigInfo.acquireMedalType(), String.valueOf(medalConfigInfo.acquireMedalLevel())));
                                arrayList2.add(medalInfoReport);
                                break;
                            }
                        }
                    }
                }
            }
        }
        ReportMedalRecyclerAdapter reportMedalRecyclerAdapter = this.mMedalRecyclerAdapter;
        if (reportMedalRecyclerAdapter == null) {
            ReportMedalRecyclerAdapter reportMedalRecyclerAdapter2 = new ReportMedalRecyclerAdapter(this.mContext, arrayList2);
            this.mMedalRecyclerAdapter = reportMedalRecyclerAdapter2;
            this.mMedalRecyclerView.setAdapter(reportMedalRecyclerAdapter2);
            return;
        }
        reportMedalRecyclerAdapter.a(arrayList2);
    }

    private void preMedalAdapter(MedalInfoReport medalInfoReport, MedalConfigInfo medalConfigInfo, Map<String, MedalInfo> map) {
        if (map == null) {
            medalInfoReport.setMedalName(medalConfigInfo.acquireMedalName());
            return;
        }
        int l = mlb.l(medalConfigInfo.acquireMedalID());
        if (l <= 0 || l > 19) {
            medalInfoReport.setMedalName(medalConfigInfo.acquireMedalName());
            return;
        }
        MedalInfo medalInfo = map.get(mdn.d(medalConfigInfo.acquireMedalType(), String.valueOf(medalConfigInfo.acquireMedalLevel())));
        if (medalInfo == null) {
            medalInfoReport.setMedalName(medalConfigInfo.acquireMedalName());
        } else {
            medalInfoReport.setMedalName(medalInfo.getText());
        }
    }

    private void initUserInfo() {
        Bitmap cgv_;
        ImageView imageView;
        if (this.mUserInfo == null) {
            UserInfomation userInfo = this.mUserProfileMgrApi.getUserInfo();
            this.mUserInfo = userInfo;
            if (userInfo == null) {
                return;
            }
        }
        this.mUsernameText.setTextColor(this.mUsernameTextColor);
        this.mUsernameText.setText(this.mUserInfo.getName());
        String picPath = this.mUserInfo.getPicPath();
        if (!TextUtils.isEmpty(picPath) && (cgv_ = mfc.cgv_(BaseApplication.getContext(), picPath)) != null && (imageView = this.mPersonalHeaderIcon) != null) {
            imageView.setImageBitmap(cgv_);
            return;
        }
        LogUtil.h(TAG, "initUserInfo() imgUrl is empty.");
        String portraitUrl = this.mUserInfo.getPortraitUrl();
        if (!TextUtils.isEmpty(portraitUrl)) {
            showUserHeadImage(portraitUrl);
        } else {
            LogUtil.a(TAG, "initUserInfo() portraitUrl is empty.");
            getHeadIconFromCloud();
        }
    }

    private void getHeadIconFromCloud() {
        LogUtil.a(TAG, "get User head url from db");
        this.mUserProfileMgrApi.getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.8
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                if (i != 0) {
                    BaseReportActivity.this.mHandler.sendEmptyMessage(1001);
                    return;
                }
                if (userInfomation == null) {
                    LogUtil.h(BaseReportActivity.TAG, "get user info success but obtain null objData");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.obj = userInfomation.getPortraitUrl();
                obtain.what = 1000;
                BaseReportActivity.this.mHandler.sendMessage(obtain);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUserHeadImage(String str) {
        mcx.cfM_(str, this.mPersonalHeaderIcon);
    }

    protected void initView() {
        initCustomTitleBarView();
        initPersonalView();
        initStepView();
        initExerciseView();
        initExerciseTypeView();
        initLoading();
    }

    private void initLoading() {
        this.mReportContentLayout = (FrameLayout) mfm.cgL_(this, R.id.report_content_layout);
        this.mReportLoadingView = (FrameLayout) mfm.cgL_(this, R.id.report_loading_view);
        HealthProgressBar healthProgressBar = (HealthProgressBar) mfm.cgL_(this, R.id.hw_report_loading_view);
        this.mLoadingView = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
    }

    private void initExerciseTypeView() {
        HealthCardView healthCardView = (HealthCardView) mfm.cgL_(this, R.id.exercise_type_item_layout);
        this.mExerciseTypeItemLayout = healthCardView;
        this.mReportTypePieChart = (ReportTypeCardView) mfm.cgM_(healthCardView, R.id.reportTypePieChart);
        this.mReportExerciseTypeTitle = (ReportUnderlineTextView) mfm.cgM_(this.mExerciseTypeItemLayout, R.id.report_exercise_type_title);
        this.mReportExerciseTypeDec = (HealthTextView) mfm.cgM_(this.mExerciseTypeItemLayout, R.id.report_exercise_type_desc);
    }

    private void initExerciseView() {
        HealthCardView healthCardView = (HealthCardView) mfm.cgL_(this, R.id.exercise_item_layout);
        this.mExerciseItemLayout = healthCardView;
        this.mExerciseDescText = (HealthTextView) mfm.cgM_(healthCardView, R.id.report_exercise_desc);
        HealthTextView healthTextView = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_exercise_total_time);
        this.mExerciseTotalTimeTitleText = healthTextView;
        healthTextView.setText(this.mResource.getString(R.string._2130840946_res_0x7f020d72));
        this.mExerciseTotalTimeValueText = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_exercise_total_time_value);
        this.mExerciseComparedTimeValueText = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_compared_exercise_total_value);
        this.mExerciseTimeImage = (ImageView) mfm.cgM_(this.mExerciseItemLayout, R.id.exercise_time_image);
        this.mExerciseHeatImage = (ImageView) mfm.cgM_(this.mExerciseItemLayout, R.id.exercise_heat_image);
        HealthTextView healthTextView2 = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_calorie_total_title);
        this.mExerciseTotalCaloriesTitleText = healthTextView2;
        healthTextView2.setText(getExerciseCaloriesTitleText());
        this.mExerciseTotalCaloriesValueText = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_calorie_total_value);
        this.mExerciseComparedCaloriesDescText = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.compared_last_calories);
        HealthTextView healthTextView3 = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_avg_calorie_total);
        this.mExerciseAvgCaloriesTitleText = healthTextView3;
        healthTextView3.setText(getExerciseAvgCaloriesTitle());
        this.mExerciseAvgCaloriesValueText = (HealthTextView) mfm.cgM_(this.mExerciseItemLayout, R.id.report_avg_calorie_total_value);
    }

    private void initStepView() {
        HealthCardView healthCardView = (HealthCardView) mfm.cgL_(this, R.id.step_item_layout);
        this.mStepItemLayout = healthCardView;
        this.mReportStepDescText = (HealthTextView) mfm.cgM_(healthCardView, R.id.report_step_desc);
        this.mReportAvgDailyStepTitleText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_avg_daily_steps_title);
        this.mReportAvgDailyStepText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_avg_daily_steps);
        this.mReportComparedStepTitleText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_compared_last_title);
        this.mReportComparedStepText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_compared_last_steps);
        this.mReportStepGoalText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_step_goal);
        this.mReportStepBarChart = (HwHealthReportBarChart) mfm.cgM_(this.mStepItemLayout, R.id.bar_chart_report_step);
        this.mComplianceDaysValueText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_step_compliance_days);
        this.mComplianceDaysImageView = (ImageView) mfm.cgM_(this.mStepItemLayout, R.id.report_step_compliance_days_icon);
        this.mComplianceDaysDescText = (HealthTextView) mfm.cgM_(this.mStepItemLayout, R.id.report_step_compliance_days_desc);
        this.mReportAvgDailyStepTitleText.setText(getAvgDailyStepTitleText());
        this.mReportComparedStepTitleText.setText(getComparedStepTitleText());
    }

    private void initBreakInfoView(int i) {
        if (this.mBreakInfoItemLayout == null) {
            if (i == 8) {
                return;
            }
            ViewStub viewStub = (ViewStub) findViewById(R.id.break_info_item_layout);
            if (viewStub == null) {
                LogUtil.a(TAG, "initBreakInfoView ViewStub is loaded fail.");
                return;
            }
            HealthCardView healthCardView = (HealthCardView) viewStub.inflate();
            this.mBreakInfoItemLayout = healthCardView;
            this.mBreakInfoDescText = (HealthTextView) mfm.cgM_(healthCardView, R.id.report_break_info_desc);
            this.mBreakInfoRecyclerView = (HealthRecycleView) mfm.cgM_(this.mBreakInfoItemLayout, R.id.break_info_view);
            setBreakInfoBg((ImageView) mfm.cgM_(this.mBreakInfoItemLayout, R.id.report_break_info_background));
        }
        this.mBreakInfoItemLayout.setVisibility(i);
    }

    protected void initMedalView(int i) {
        if (this.mMedalItemLayout == null) {
            if (i == 8) {
                return;
            }
            ViewStub viewStub = (ViewStub) findViewById(R.id.medal_item_layout);
            if (viewStub == null) {
                LogUtil.a(TAG, "initMedalView ViewStub is loaded fail.");
                return;
            }
            HealthCardView healthCardView = (HealthCardView) viewStub.inflate();
            this.mMedalItemLayout = healthCardView;
            this.mMedalItemBackground = (ImageView) mfm.cgM_(healthCardView, R.id.medal_item_background);
            this.mMedalDescTextView = (HealthTextView) mfm.cgM_(this.mMedalItemLayout, R.id.report_medal_desc);
            this.mMedalNumbersTextView = (HealthTextView) mfm.cgM_(this.mMedalItemLayout, R.id.report_medal_numbers);
            this.mMedalRecyclerView = (HealthRecycleView) mfm.cgM_(this.mMedalItemLayout, R.id.report_medal_recycler_view);
            this.mMedalRecyclerView.setLayoutManager(new GridLayoutManager(this.mContext, 3));
            this.mMedalRecyclerView.a(false);
            this.mMedalRecyclerView.d(false);
        }
        this.mMedalItemLayout.setVisibility(i);
    }

    private void initPersonalView() {
        this.mReportHeadRootLayout = (RelativeLayout) mfm.cgL_(this, R.id.report_head_root_layout);
        this.mReportHeadRootBackground = (ImageView) mfm.cgL_(this, R.id.report_head_root_background);
        this.mReportUserView = mfm.cgL_(this, R.id.report_user_view);
        setReportHeadLayout(getMarginStartAndEnd());
        this.mPersonalHeaderIcon = (ImageView) mfm.cgL_(this, R.id.report_personal_header_icon);
        this.mUsernameText = (HealthTextView) mfm.cgL_(this, R.id.report_username);
        this.mReportIntervalDateText = (HealthTextView) mfm.cgL_(this, R.id.report_period_date);
        this.mReportIntervalDateNoText = (HealthTextView) mfm.cgL_(this, R.id.report_period_date_no);
        BaseActivity.setViewSafeRegion(true, this.mPersonalHeaderIcon);
        this.mReportCardLayout = (LinearLayout) mfm.cgL_(this, R.id.report_data);
        setReportCardLayout();
        this.mWalkingMan = (ImageView) mfm.cgL_(this, R.id.walking_man);
        if (LanguageUtil.bc(this)) {
            this.mWalkingMan.setImageDrawable(nrz.cKn_(this, R.drawable._2131431191_res_0x7f0b0f17));
        } else {
            this.mWalkingMan.setImageDrawable(ContextCompat.getDrawable(this, R.drawable._2131431191_res_0x7f0b0f17));
        }
    }

    private void setReportCardLayout() {
        LinearLayout linearLayout = this.mReportCardLayout;
        if (linearLayout == null) {
            LogUtil.h(TAG, "setReportCardLayout() mReportCardLayout is null.");
            return;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        int i = 0;
        if (nsn.ag(this.mContext)) {
            i = (int) (r1.c() + new HealthColumnSystem(this.mContext, 0).g() + r1.a());
        }
        if (layoutParams2 != null) {
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            this.mReportCardLayout.setLayoutParams(layoutParams2);
        }
    }

    private void setReportHeadLayout(int i) {
        RelativeLayout relativeLayout = this.mReportHeadRootLayout;
        if (relativeLayout != null) {
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                layoutParams2.setMarginStart(i);
                layoutParams2.setMarginEnd(i);
                this.mReportHeadRootLayout.setLayoutParams(layoutParams2);
            }
        }
        View view = this.mReportUserView;
        if (view != null) {
            ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
            if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
                layoutParams4.setMarginStart(i);
                layoutParams4.setMarginEnd(i);
                this.mReportUserView.setLayoutParams(layoutParams4);
            }
        }
    }

    private int getMarginStartAndEnd() {
        if (!nsn.ag(this.mContext)) {
            return 0;
        }
        return (int) (r0.c() + new HealthColumnSystem(this.mContext, 0).g() + nrr.b(this.mContext));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "onConfigurationChanged()");
        setReportHeadLayout(getMarginStartAndEnd());
        setReportCardLayout();
    }

    private void initCustomTitleBarView() {
        this.mNoReportLayout = (HealthTextView) mfm.cgL_(this, R.id.empty_report_text);
        this.mShareView = (HealthNestedScrollView) mfm.cgL_(this, R.id.report_scrollview);
        LinearLayout linearLayout = (LinearLayout) mfm.cgL_(this, R.id.report_linear_layout);
        this.mContentLinearLayout = linearLayout;
        linearLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: mkt
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                BaseReportActivity.this.m767x3dcfa1e5(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        this.mShareLogoLayout = (LinearLayout) mfm.cgL_(this, R.id.share_logo_layout);
        this.mVipImage = (ImageView) mfm.cgL_(this, R.id.vip_image);
        this.mShareLogoLayout.setVisibility(8);
        this.mVipImage.setVisibility(8);
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.txt_report_title);
        this.mCustomTitleBar = customTitleBar;
        customTitleBar.setTitleText(getCustomTitleBarText());
        this.mCustomTitleBar.setRightSoftkeyVisibility(8);
        if (LanguageUtil.bc(this.mContext)) {
            this.mCustomTitleBar.setRightSoftkeyBackground(nrz.cKn_(this.mContext, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
            this.mCustomTitleBar.setRightButtonDrawable(nrz.cKn_(this.mContext, R.drawable._2131430087_res_0x7f0b0ac7), nsf.h(R.string._2130842598_res_0x7f0213e6));
        } else {
            this.mCustomTitleBar.setRightSoftkeyBackground(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
            this.mCustomTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430087_res_0x7f0b0ac7), nsf.h(R.string._2130842598_res_0x7f0213e6));
        }
        this.mCustomTitleBar.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: mkq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseReportActivity.this.m768x4e856ea6(view);
            }
        });
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: mkr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseReportActivity.this.m769x5f3b3b67(view);
            }
        });
    }

    /* renamed from: lambda$initCustomTitleBarView$0$com-huawei-pluginachievement-ui-report-BaseReportActivity, reason: not valid java name */
    public /* synthetic */ void m767x3dcfa1e5(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.mIsShare) {
            this.mIsShare = false;
            share(true);
        }
    }

    /* renamed from: lambda$initCustomTitleBarView$1$com-huawei-pluginachievement-ui-report-BaseReportActivity, reason: not valid java name */
    public /* synthetic */ void m768x4e856ea6(View view) {
        if (PermissionUtil.c()) {
            sharePage();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.mContext, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.mContext) { // from class: com.huawei.pluginachievement.ui.report.BaseReportActivity.6
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    BaseReportActivity.this.sharePage();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: lambda$initCustomTitleBarView$2$com-huawei-pluginachievement-ui-report-BaseReportActivity, reason: not valid java name */
    public /* synthetic */ void m769x5f3b3b67(View view) {
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            startHistoricalActivityForResult();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sharePage() {
        if (!mcx.d(BaseApplication.getContext())) {
            mlg.e(this.mContext);
            return;
        }
        if (this.mShareView.getHeight() == 0) {
            LogUtil.a(TAG, "sharePage shareView height == 0");
            return;
        }
        this.mContentLinearLayout.setBackgroundResource(R.color._2131296690_res_0x7f0901b2);
        this.mShareLogoLayout.setVisibility(0);
        if (mkw.e()) {
            this.mVipImage.setVisibility(0);
        } else {
            this.mVipImage.setVisibility(8);
        }
        this.mIsShare = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mContentLinearLayout.setBackgroundResource(R.color._2131296971_res_0x7f0902cb);
    }

    private void share(boolean z) {
        boolean e = mcx.e();
        if (z && e) {
            this.mUsernameText.setText(mcx.b());
            this.mUsernameText.post(new Runnable() { // from class: mks
                @Override // java.lang.Runnable
                public final void run() {
                    BaseReportActivity.this.m770x2cfcd24c();
                }
            });
            return;
        }
        LinearLayout linearLayout = this.mContentLinearLayout;
        View[] viewArr = new View[1];
        viewArr[0] = e ? this.mReportUserView : this.mReportIntervalDateNoText;
        Bitmap cgG_ = mfp.cgG_(linearLayout, viewArr);
        this.mShareLogoLayout.setVisibility(8);
        this.mVipImage.setVisibility(8);
        UserInfomation userInfomation = this.mUserInfo;
        if (userInfomation != null) {
            this.mUsernameText.setText(userInfomation.getName());
        }
        if (cgG_ == null) {
            nrh.b(this.mContext, R.string._2130840737_res_0x7f020ca1);
            return;
        }
        ixx d2 = ixx.d();
        HashMap hashMap = new HashMap(2);
        if (mcv.e()) {
            d2.d(this.mContext, getSuccessShareBiValue(), hashMap, 0);
        }
        if ((mfp.cgy_(cgG_) / 1024) / 1024 >= 1) {
            mcx.d(this.mContext, mfp.cgF_(this.mContext, cgG_, "all_medal_share.jpg"), getSuccessShareBiValue(), null);
        } else {
            mcx.cfN_(this.mContext, cgG_, getSuccessShareBiValue(), null);
        }
        LogUtil.a(TAG, "sharePage end");
    }

    /* renamed from: lambda$share$3$com-huawei-pluginachievement-ui-report-BaseReportActivity, reason: not valid java name */
    public /* synthetic */ void m770x2cfcd24c() {
        share(false);
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        Object obj;
        LogUtil.a(TAG, "onDataChanged->error:", Integer.valueOf(i));
        Message obtain = Message.obtain();
        obtain.what = 1006;
        obtain.arg1 = i;
        if (userAchieveWrapper != null) {
            LogUtil.a(TAG, "onDataChanged->resultCode:", userAchieveWrapper.getResultCode());
            if (i == 200) {
                int reportType = getReportType();
                int contentType = getContentType();
                if (reportType == 0 && contentType == userAchieveWrapper.getContentType()) {
                    obj = userAchieveWrapper.acquireRecentMonthRecord();
                } else if (reportType != 1 || contentType != userAchieveWrapper.getContentType()) {
                    return;
                } else {
                    obj = userAchieveWrapper.acquireRecentWeekRecord();
                }
            } else {
                obj = null;
            }
            obtain.arg2 = Integer.parseInt(userAchieveWrapper.getResultCode());
            obtain.obj = obj;
            this.mHandler.sendMessage(obtain);
        }
    }

    /* loaded from: classes8.dex */
    static class a implements AchieveCallback {
        private long[] b = new long[2];
        WeakReference<BaseReportActivity> e;

        a(BaseReportActivity baseReportActivity, long j, long j2) {
            this.e = new WeakReference<>(baseReportActivity);
            long[] jArr = this.b;
            jArr[0] = j;
            jArr[1] = j2;
        }

        @Override // com.huawei.pluginachievement.impl.AchieveCallback
        public void onResponse(int i, Object obj) {
            BaseReportActivity baseReportActivity = this.e.get();
            if (baseReportActivity != null) {
                if (i != 0) {
                    LogUtil.h(BaseReportActivity.TAG, "FetchCurrentCallback onResponse ret = ", Integer.valueOf(i));
                }
                Message obtainMessage = baseReportActivity.mHandler.obtainMessage();
                obtainMessage.what = 1007;
                if (obj != null && ((Boolean) obj).booleanValue()) {
                    obtainMessage.what = 1008;
                }
                obtainMessage.obj = this.b;
                baseReportActivity.mHandler.sendMessage(obtainMessage);
                return;
            }
            LogUtil.a(BaseReportActivity.TAG, "reportActivity is null");
        }
    }

    private void getStepGoal() {
        nip.d("900200006", new b(this.mContext));
    }

    /* loaded from: classes8.dex */
    static class b implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<Context> f8460a;

        b(Context context) {
            this.f8460a = new WeakReference<>(context);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Context context = this.f8460a.get();
            if ((context instanceof BaseReportActivity) && (obj instanceof Integer)) {
                ((BaseReportActivity) context).mStepTarget = ((Integer) obj).intValue();
            } else {
                LogUtil.h(BaseReportActivity.TAG, "fetchGoalInfo data is null");
            }
        }
    }
}

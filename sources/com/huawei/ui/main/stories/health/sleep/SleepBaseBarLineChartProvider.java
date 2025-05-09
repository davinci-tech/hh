package com.huawei.ui.main.stories.health.sleep;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.BarChartRangeShowCallback;
import com.huawei.health.knit.section.listener.ChartMarkChangeCallback;
import com.huawei.health.knit.section.listener.ChartMarkTextCallback;
import com.huawei.health.knit.section.listener.ClickChartDataCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.model.SleepModuleBarChartHolder;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.health.sport.utils.FitnessDataQueryDefine;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.utils.HarmonyOsTypefaceSpan;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepViewCallback;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepBarChartView;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView;
import com.huawei.ui.main.stories.health.views.FitnessDataOriginView;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import defpackage.fdp;
import defpackage.jec;
import defpackage.koq;
import defpackage.nom;
import defpackage.pqr;
import defpackage.pwb;
import defpackage.pwd;
import defpackage.qnl;
import defpackage.qrp;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public abstract class SleepBaseBarLineChartProvider extends MajorProvider<fdp> implements BarChartRangeShowCallback, ChartMarkChangeCallback, ChartMarkTextCallback, SleepViewCallback {
    private static final String BED_TIME_TEXT = "BED_TIME_TEXT";
    private static final String CHART_BOTTOM_LINE = "CHART_BOTTOM_LINE";
    private static final String CHART_CALENDAR_LISTENER = "CHART_CALENDAR_LISTENER";
    private static final String CHART_DATA_INFO = "CHART_DATA_INFO";
    private static final String CHART_REFLESH_TIME = "CHART_REFLESH_TIME";
    private static final String FITNESSCORE_SLEEP_DETAIL_INTERACTOR = "FITNESSCORE_SLEEP_DETAIL_INTERACTOR";
    private static final String HELP_ICON_IMAGE = "HELP_ICON_IMAGE";
    private static final String HELP_ICON_IMAGE_VISIBILITY = "HELP_ICON_IMAGE_VISIBILITY";
    private static final String HOUR_UNIT_VISIBILITY = "HOUR_UNIT_VISIBILITY";
    private static final String HOUR_VALUE_TEXT = "HOUR_VALUE_TEXT";
    private static final String HOUR_VALUE_VISIBILITY = "HOUR_VALUE_VISIBILITY";
    private static final String HW_CHINESE_MEDIUM = "HwChinese-medium";
    protected static final String IS_RESET_BAR_CHART = "IS_RESET_BAR_CHART";
    private static final String JUMP_TO_DAY_CLICK_EVENT = "JUMP_TO_DAY_CLICK_EVENT";
    private static final String LEFT_ARROW_CALL_BACK = "LEFT_ARROW_CALL_BACK";
    private static final Object LOCK = new Object();
    private static final String MARK_CHANGE_CALL_BACK = "MARK_CHANGE_CALL_BACK";
    private static final String MARK_TEXT_CALL_BACK = "MARK_TEXT_CALL_BACK";
    private static final String MEDIUM_ASSETS_PATH = "font/HarmonyOSCondensedClockProportional-Medium.ttf";
    public static final int MESSAGE_JUMP_TO_DAY_TAB = 6010;
    public static final int MILLISECONDS_IN_SECOND = 1000;
    public static final int MINS_IN_HOUR = 60;
    private static final String MINUTE_UNIT_TEXT = "MINUTE_UNIT_TEXT";
    private static final String MINUTE_VALUE_TEXT = "MINUTE_VALUE_TEXT";
    public static final int MSG_UPDATE_DATA_ORIGINAL_ICON = 6011;
    private static final int NOON_TO_NORMAL_TIME = 3;
    public static final int NO_DATA = 0;
    private static final String PROCESS_TEXT_VISIBILITY = "PROCESS_TEXT_VISIBILITY";
    private static final String RANGE_SHOW_CALL_BACK = "RANGE_SHOW_CALL_BACK";
    private static final String RELEASE_TAG = "R_Sleep_SleepBaseBarLineChartProvider";
    private static final String RIGHT_ARROW_CALL_BACK = "RIGHT_ARROW_CALL_BACK";
    public static final int SHOW_RECOMMEND_SERVICE_UI = 6008;
    private static final String SLEEP_TIME_TEXT = "SLEEP_TIME_TEXT";
    private static final String TAG = "SleepBaseBarLineChartProvider";
    private static final String TIME_LAYOUT_CLICKABLE = "TIME_LAYOUT_CLICKABLE";
    private static final int TIME_TYPE_HAS_DATA = 1;
    private static final int TIME_TYPE_NO_DATA = 0;
    public static final int UPDATE_BAR_CHART_DATA_UI = 6001;
    public static final int UPDATE_TOTAL_DATA_UI = 6002;
    protected static final String YEAR_JUMP_TO_MONTH_TIME = "YEAR_JUMP_TO_MONTH_TIME";
    private Date lastMarkDate;
    private Date lastReqDate;
    private String[] mBiTypeArray;
    private HealthCalendar mCalendar;
    private e mCalendarSelectCallback;
    private WeakReference<Context> mContextReference;
    private CustomViewDialog mCustomViewDialog;
    private List<Double> mDeepSleepValueList;
    private List<Double> mDreamSleepValueList;
    private Date mEndDay;
    private int mEndX;
    private FitnessDataOriginView mFitnessDataOriginView;
    protected HealthScrollView mHealthScrollView;
    private pwd mInteractor;
    private boolean mIsLoadingState;
    protected long mLastTimestamp;
    private List<Double> mLightSleepValueList;
    private List<Double> mNoonSleepValueList;
    protected SectionBean mSectionBean;
    private Date mStartDay;
    private int mStartX;
    private Typeface mTypeface;
    protected View mView;
    private List<Double> mWakeSleepValueList;
    private Date nowMarkDate;
    protected WeakReference<Activity> sleepActivityWeakReference;
    protected fdp mSleepChartData = new fdp(SleepViewConstants.ViewTypeEnum.UNKNOWN);
    protected Map<String, Object> mUiDataMap = new HashMap();
    private int mTimeType = 0;
    private SleepModuleBarChartHolder mSleepHolder = null;
    private List<pwb> mFitnessOriginListData = new ArrayList();
    private List<HiHealthClient> mClientList = new ArrayList();
    private fdp mSleepReportData = new fdp(SleepViewConstants.ViewTypeEnum.UNKNOWN);
    private String mRecommendId = "0";
    protected Handler mUpdateUiHandler = new a(this);
    private DataInfos mDataInfos = DataInfos.NoDataPlaceHolder;
    private Map<String, SleepViewConstants.SleepTypeEnum> mDateIntegerMap = new ConcurrentHashMap();

    protected abstract LinearLayout getConfiguredLayout();

    protected abstract int getCoreSleepType();

    protected abstract DataInfos getDataInfo();

    protected abstract int getLayoutId();

    protected abstract LinearLayout getMarketingLayout();

    protected abstract Date getNextEndDay();

    protected abstract Date getNextStartDay();

    protected abstract Date getPreviousEndDay();

    protected abstract Date getPreviousStartDay();

    protected abstract void initOtherView();

    protected abstract void requestData(boolean z);

    protected abstract void showCurrentDate();

    protected abstract void showLastSleepData(CoreSleepBarChartView coreSleepBarChartView);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (fdp) obj);
    }

    protected static class a extends BaseHandler<SleepBaseBarLineChartProvider> {
        a(SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider) {
            super(sleepBaseBarLineChartProvider);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dFX_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider, Message message) {
            if (sleepBaseBarLineChartProvider == null || message == null) {
                return;
            }
            ReleaseLogUtil.e(SleepBaseBarLineChartProvider.RELEASE_TAG, "Msg:", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 6001) {
                sleepBaseBarLineChartProvider.updateBarChartUi();
                sleepBaseBarLineChartProvider.mUpdateUiHandler.sendEmptyMessageDelayed(6002, 1L);
                return;
            }
            if (i == 6002) {
                sleepBaseBarLineChartProvider.updateDatasUi(null);
                return;
            }
            if (i == 6008) {
                sleepBaseBarLineChartProvider.mRecommendId = message.arg1 + "";
                return;
            }
            if (i != 6010) {
                if (i != 6011) {
                    return;
                }
                sleepBaseBarLineChartProvider.processMsgUpdateDataOriginal(message);
            } else if (message.obj instanceof String) {
                sleepBaseBarLineChartProvider.jumpToDetailDay((String) message.obj);
            }
        }
    }

    protected View initView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (this.mView == null) {
            this.mView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        }
        Bundle extra = getExtra();
        if (extra != null) {
            long j = extra.getLong("key_bundle_health_last_data_time", 0L);
            this.mLastTimestamp = j;
            LogUtil.a(TAG, "mLastTimestamp= ", Long.valueOf(j));
        } else {
            LogUtil.b(TAG, "getArguments is null ");
        }
        HealthScrollView healthScrollView = (HealthScrollView) this.mView.findViewById(R.id.core_sleep_year_scrollview);
        this.mHealthScrollView = healthScrollView;
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        Activity activity = this.sleepActivityWeakReference.get();
        if (activity != null) {
            ScrollUtil.cKx_(this.mHealthScrollView, activity.getWindow().getDecorView(), IEventListener.EVENT_ID_DEVICE_DLNA_CONN_SUCC);
        }
        ((CoreSleepServiceView) this.mView.findViewById(R.id.core_sleep_service_view)).setSleepViewCallback(this);
        return this.mView;
    }

    static class b implements Observer {
        private WeakReference<SleepBaseBarLineChartProvider> c;

        private b(SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider) {
            this.c = new WeakReference<>(sleepBaseBarLineChartProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider = this.c.get();
            if (sleepBaseBarLineChartProvider == null) {
                LogUtil.h(SleepBaseBarLineChartProvider.TAG, "InnerHiDataClientListener provider is null");
                return;
            }
            ReleaseLogUtil.e(SleepBaseBarLineChartProvider.RELEASE_TAG, "Devices got ", objArr[0]);
            if (objArr.length == 1 && koq.e(objArr[0], pwb.class)) {
                Message obtain = Message.obtain();
                obtain.what = SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON;
                obtain.obj = (ArrayList) objArr[0];
                sleepBaseBarLineChartProvider.mUpdateUiHandler.sendMessage(obtain);
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        ReleaseLogUtil.e(RELEASE_TAG, "Load data");
        this.mSectionBean = sectionBean;
        this.mContextReference = new WeakReference<>(context);
        initViews(context);
        initOtherView();
        this.mCalendarSelectCallback = new e(this);
        this.mUiDataMap.put(RANGE_SHOW_CALL_BACK, this);
        this.mUiDataMap.put(MARK_CHANGE_CALL_BACK, this);
        this.mUiDataMap.put(MARK_TEXT_CALL_BACK, this);
        initClickListener();
        initJumpToDay();
        initCalendar();
        ObserverManagerUtil.d(new d(this), "FINISH_SLEEP_TAG");
    }

    static class d implements Observer {
        private WeakReference<SleepBaseBarLineChartProvider> e;

        d(SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider) {
            this.e = new WeakReference<>(sleepBaseBarLineChartProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider = this.e.get();
            if (sleepBaseBarLineChartProvider != null && sleepBaseBarLineChartProvider.mUpdateUiHandler != null) {
                sleepBaseBarLineChartProvider.clearList(sleepBaseBarLineChartProvider.mLightSleepValueList);
                sleepBaseBarLineChartProvider.clearList(sleepBaseBarLineChartProvider.mDeepSleepValueList);
                sleepBaseBarLineChartProvider.clearList(sleepBaseBarLineChartProvider.mDreamSleepValueList);
                sleepBaseBarLineChartProvider.clearList(sleepBaseBarLineChartProvider.mNoonSleepValueList);
                sleepBaseBarLineChartProvider.clearList(sleepBaseBarLineChartProvider.mWakeSleepValueList);
                sleepBaseBarLineChartProvider.requestData(true);
                return;
            }
            ReleaseLogUtil.c(SleepBaseBarLineChartProvider.TAG, "provider is null");
        }
    }

    private void initClickListener() {
        this.mUiDataMap.put(LEFT_ARROW_CALL_BACK, new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                SleepBaseBarLineChartProvider.this.processLeftClick();
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mUiDataMap.put(RIGHT_ARROW_CALL_BACK, new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                SleepBaseBarLineChartProvider.this.processRightClick();
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processLeftClick() {
        this.mUiDataMap.put(PROCESS_TEXT_VISIBILITY, 4);
        this.mStartDay = getPreviousStartDay();
        this.mEndDay = getPreviousEndDay();
        clearList(this.mLightSleepValueList);
        clearList(this.mDeepSleepValueList);
        clearList(this.mDreamSleepValueList);
        clearList(this.mNoonSleepValueList);
        clearList(this.mWakeSleepValueList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRightClick() {
        this.mUiDataMap.put(PROCESS_TEXT_VISIBILITY, 4);
        this.mStartDay = getNextStartDay();
        this.mEndDay = getNextEndDay();
        clearList(this.mLightSleepValueList);
        clearList(this.mDeepSleepValueList);
        clearList(this.mDreamSleepValueList);
        clearList(this.mNoonSleepValueList);
        clearList(this.mWakeSleepValueList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearList(List<?> list) {
        if (list != null) {
            list.clear();
        }
    }

    private void initJumpToDay() {
        this.mUiDataMap.put(JUMP_TO_DAY_CLICK_EVENT, new ClickChartDataCallback() { // from class: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.2
            @Override // com.huawei.health.knit.section.listener.ClickChartDataCallback
            public void onTimeChanged(long j) {
                SleepBaseBarLineChartProvider.this.processJump(j);
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        DataInfos dataInfos;
        HashMap<String, Object> hashMap = new HashMap<>();
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        if (serializable instanceof DataInfos) {
            dataInfos = (DataInfos) serializable;
        } else {
            dataInfos = DataInfos.NoDataPlaceHolder;
        }
        this.mDataInfos = dataInfos;
        hashMap.put("BAR_COMMON_START_TIME", dataInfos);
        Object obj = DataInfos.NoDataPlaceHolder;
        Bundle extra2 = getExtra();
        long j = 0;
        if (extra2 != null) {
            long j2 = extra2.getLong("key_bundle_health_last_data_time", 0L);
            j = j2 == 0 ? System.currentTimeMillis() : j2;
            if (extra2.getSerializable("key_bundle_health_line_chart_data_infos") instanceof DataInfos) {
                obj = (DataInfos) extra2.getSerializable("key_bundle_health_line_chart_data_infos");
            }
        }
        hashMap.put("BAR_COMMON_START_TIME", Long.valueOf(j));
        hashMap.put("BAR_COMMON_DATA_INFO", obj);
        SleepModuleBarChartHolder sleepModuleBarChartHolder = new SleepModuleBarChartHolder(BaseApplication.getContext());
        this.mSleepHolder = sleepModuleBarChartHolder;
        hashMap.put("BAR_COMMON_CHART_HOLDER", sleepModuleBarChartHolder);
        hashMap.put("BAR_HOUR_UNIT_TEXT", BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_main_home_page_hours));
        initLegend(hashMap);
        hashMap.put("BAR_DALIY_STRING", BaseApplication.getContext().getResources().getString(R$string.IDS_hw_health_show_healthdata_weight_average));
        sectionBean.a(hashMap);
        ObserverManagerUtil.d(new b(), "SLEEP_DEVICE_LIST" + getTag());
    }

    private void initLegend(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_LEGEND_ONE", Integer.valueOf(R.drawable._2131427991_res_0x7f0b0297));
        hashMap.put("BAR_COMMON_LEGEND_TWO", Integer.valueOf(R.drawable._2131430785_res_0x7f0b0d81));
        hashMap.put("BAR_COMMON_LEGEND_THREE", Integer.valueOf(R.drawable._2131431175_res_0x7f0b0f07));
        hashMap.put("BAR_COMMON_LEGEND_FOUR", Integer.valueOf(R.drawable._2131432003_res_0x7f0b1243));
        hashMap.put("BAR_COMMON_LEGEND_FIVE", Integer.valueOf(R.drawable._2131431987_res_0x7f0b1233));
        hashMap.put("BAR_COMMON_LEGEND_SIX", Integer.valueOf(R.drawable._2131430814_res_0x7f0b0d9e));
        hashMap.put("BAR_COMMON_LEGEND_SEVEN", Integer.valueOf(R.drawable._2131430816_res_0x7f0b0da0));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_ONE", BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_deepsleep));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_TWO", BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_sleep_shallowsleep));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_THREE", BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_core_sleep_rem_sleep));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_FOUR", BaseApplication.getContext().getResources().getString(R$string.IDS_details_sleep_sleep_latency));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_FIVE", BaseApplication.getContext().getResources().getString(R$string.IDS_fitness_core_sleep_noontime_sleep));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_SIX", BaseApplication.getContext().getResources().getString(R$string.IDS_manual_sleep_bed));
        hashMap.put("BAR_COMMON_LEGEND_TEXT_SEVEN", BaseApplication.getContext().getResources().getString(R$string.IDS_manual_sleep_sleep));
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, fdp fdpVar) {
        this.sleepActivityWeakReference = new WeakReference<>(qrp.dHL_(context));
        synchronized (LOCK) {
            dealTotalData(hashMap, fdpVar);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        ObserverManagerUtil.e("SLEEP_BASE_BAR_DREAM_VISIBILITY");
        ObserverManagerUtil.e("SLEEP_WEEK_MONTH_YEAR_AVERAGE_DAILY");
        ObserverManagerUtil.e("SLEEP_AVERAGE_ICON_OFFSET");
        ObserverManagerUtil.e("SLEEP_DEVICE_LIST" + getLogTag());
        ObserverManagerUtil.e("FINISH_SLEEP_TAG");
        this.mUiDataMap.clear();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void dealTotalData(HashMap<String, Object> hashMap, fdp fdpVar) {
        char c2;
        if (fdpVar.l() instanceof HashMap) {
            for (Map.Entry entry : ((HashMap) fdpVar.l()).entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                str.hashCode();
                switch (str.hashCode()) {
                    case -550381908:
                        if (str.equals(CHART_REFLESH_TIME)) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 610528870:
                        if (str.equals(MINUTE_VALUE_TEXT)) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 628239586:
                        if (str.equals(CHART_DATA_INFO)) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1286886996:
                        if (str.equals(CHART_CALENDAR_LISTENER)) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1449755293:
                        if (str.equals(MINUTE_UNIT_TEXT)) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1693506519:
                        if (str.equals(SLEEP_TIME_TEXT)) {
                            c2 = 5;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 1857296641:
                        if (str.equals(BED_TIME_TEXT)) {
                            c2 = 6;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                switch (c2) {
                    case 0:
                        if (value instanceof Long) {
                            hashMap.put("BAR_COMMON_REFLESH_TIME", value);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (value instanceof String) {
                            hashMap.put("BAR_MINUTE_VALUE_TEXT", value);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (value instanceof DataInfos) {
                            hashMap.put("BAR_COMMON_DATA_INFO", value);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (value instanceof OnClickSectionListener) {
                            hashMap.put("BAR_COMMON_CALENDER_EVENT", value);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (value instanceof String) {
                            hashMap.put("BAR_MINUTE_UNIT_TEXT", value);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (value instanceof SpannableString) {
                            hashMap.put("BAR_TIME_VALUE_TEXT", value);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (value instanceof SpannableString) {
                            hashMap.put("BAR_BED_TIME_VALUE_TEXT", value);
                            break;
                        } else {
                            break;
                        }
                    default:
                        continueDeal1(hashMap, value, str);
                        break;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void continueDeal1(HashMap<String, Object> hashMap, Object obj, String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -1456116853:
                if (str.equals(MARK_CHANGE_CALL_BACK)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -844598643:
                if (str.equals(JUMP_TO_DAY_CLICK_EVENT)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -256100421:
                if (str.equals(HOUR_VALUE_VISIBILITY)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 13094216:
                if (str.equals(MARK_TEXT_CALL_BACK)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 532959007:
                if (str.equals(TIME_LAYOUT_CLICKABLE)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 745353928:
                if (str.equals(RANGE_SHOW_CALL_BACK)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1873179730:
                if (str.equals(HOUR_UNIT_VISIBILITY)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 2061834390:
                if (str.equals(HOUR_VALUE_TEXT)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                if (obj instanceof ChartMarkChangeCallback) {
                    hashMap.put("BAR_COMMON_MARK_CHANGE_CALL_BACK", obj);
                    break;
                }
                break;
            case 1:
                if (obj instanceof ClickChartDataCallback) {
                    hashMap.put("BAR_CHART_MID_DATA_CLICK_EVENT", obj);
                    break;
                }
                break;
            case 2:
                if (obj instanceof Integer) {
                    hashMap.put("BAR_HOUR_VALUE_VISIBILITY", obj);
                    break;
                }
                break;
            case 3:
                if (obj instanceof ChartMarkTextCallback) {
                    hashMap.put("BAR_COMMON_MARK_TEXT_CALL_BACK", obj);
                    break;
                }
                break;
            case 4:
                if (obj instanceof Boolean) {
                    hashMap.put("BAR_TIME_LAYOUT_CLICKABLE", obj);
                    break;
                }
                break;
            case 5:
                if (obj instanceof BarChartRangeShowCallback) {
                    hashMap.put("BAR_COMMON_RANGE_SHOW_CALL_BACK", obj);
                    break;
                }
                break;
            case 6:
                if (obj instanceof Integer) {
                    hashMap.put("BAR_HOUR_UNIT_VISIBILITY", obj);
                    break;
                }
                break;
            case 7:
                if (obj instanceof String) {
                    hashMap.put("BAR_HOUR_VALUE_TEXT", obj);
                    break;
                }
                break;
            default:
                continueDeal(hashMap, obj, str);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void continueDeal(HashMap<String, Object> hashMap, Object obj, String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2132092044:
                if (str.equals(PROCESS_TEXT_VISIBILITY)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1375486899:
                if (str.equals(IS_RESET_BAR_CHART)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1179159270:
                if (str.equals(LEFT_ARROW_CALL_BACK)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -1093958015:
                if (str.equals(YEAR_JUMP_TO_MONTH_TIME)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -410552909:
                if (str.equals(HELP_ICON_IMAGE)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 810097935:
                if (str.equals(RIGHT_ARROW_CALL_BACK)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 854427271:
                if (str.equals(CHART_BOTTOM_LINE)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1365422366:
                if (str.equals(HELP_ICON_IMAGE_VISIBILITY)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                if (obj instanceof Integer) {
                    hashMap.put("BAR_COMMON_PROCESS_TEXT_VISIBILITY", obj);
                    break;
                }
                break;
            case 1:
                if (obj instanceof Boolean) {
                    hashMap.put("BAR_COMMON_RESET_BAR_CHART", obj);
                    break;
                }
                break;
            case 2:
                if (obj instanceof OnClickSectionListener) {
                    hashMap.put("BAR_COMMON_LEFT_ARROW_LISTENER", obj);
                    break;
                }
                break;
            case 3:
                if (obj instanceof Integer) {
                    hashMap.put("BAR_COMMON_JUMP_TO_MONTH_TIME", obj);
                    break;
                }
                break;
            case 4:
                if (obj instanceof Drawable) {
                    hashMap.put("BAR_CHART_HELP_ICON", obj);
                    break;
                }
                break;
            case 5:
                if (obj instanceof OnClickSectionListener) {
                    hashMap.put("BAR_COMMON_RIGHT_ARROW_LISTENER", obj);
                    break;
                }
                break;
            case 6:
                if (obj instanceof Boolean) {
                    hashMap.put("BAR_COMMON_BOTTOM_LINE", obj);
                    break;
                }
                break;
            case 7:
                if (obj instanceof Integer) {
                    hashMap.put("BAR_CHART_HELP_ICON_VISIBILITY", obj);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToDetailDay(String str) {
        ObserverManagerUtil.c(TAG, str);
        Activity activity = this.sleepActivityWeakReference.get();
        if (activity instanceof KnitSleepDetailActivity) {
            ((KnitSleepDetailActivity) activity).getViewPager().setCurrentItem(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDatasUi(Map<String, Object> map) {
        health.compact.a.util.LogUtil.d(TAG, "updateDatasUi");
        pwd pwdVar = this.mInteractor;
        if (pwdVar == null) {
            return;
        }
        fdp f = pwdVar.f();
        this.mSleepReportData = f;
        f.c(getStartDay().getTime());
        this.mDateIntegerMap = this.mInteractor.c();
        if (map != null) {
            this.mUiDataMap.put(FITNESSCORE_SLEEP_DETAIL_INTERACTOR, this.mInteractor);
            this.mSleepReportData.b(map);
        }
        notifyDateType();
    }

    private String getDataDifference() {
        if (this.mSleepReportData.e() != SleepViewConstants.ViewTypeEnum.MONTH && this.mSleepReportData.e() != SleepViewConstants.ViewTypeEnum.WEEK) {
            return "";
        }
        fdp d2 = this.mInteractor.d();
        int i = AnonymousClass10.f10222a[this.mSleepReportData.i().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return "";
            }
            return pqr.c(d2.f().c(), d2.f().l(), d2.f().h(), this.mSleepReportData.f().c(), this.mSleepReportData.f().l(), this.mSleepReportData.f().h(), this.mSleepReportData.e() == SleepViewConstants.ViewTypeEnum.WEEK);
        }
        if (d2.d().h() == 0) {
            return pqr.d(this.mSleepReportData.e() == SleepViewConstants.ViewTypeEnum.WEEK);
        }
        return pqr.c(d2.d().c(), d2.d().l(), d2.d().h(), this.mSleepReportData.d().c(), this.mSleepReportData.d().l(), this.mSleepReportData.d().h(), this.mSleepReportData.e() == SleepViewConstants.ViewTypeEnum.WEEK);
    }

    private void getBiType() {
        if (this.mBiTypeArray == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, SleepViewConstants.SleepTypeEnum>> it = this.mDateIntegerMap.entrySet().iterator();
        while (it.hasNext()) {
            String typeString = getTypeString(it.next().getValue());
            if (typeString != "" && !arrayList.contains(typeString)) {
                arrayList.add(typeString);
            }
        }
        this.mBiTypeArray = (String[]) arrayList.toArray(new String[arrayList.size()]);
        if (this.mContextReference.get() instanceof KnitSleepDetailActivity) {
            int i = AnonymousClass10.d[getDataInfo().ordinal()];
            if (i == 1) {
                ((KnitSleepDetailActivity) this.mContextReference.get()).d(this.mBiTypeArray);
            } else if (i == 2) {
                ((KnitSleepDetailActivity) this.mContextReference.get()).c(this.mBiTypeArray);
            } else {
                if (i != 3) {
                    return;
                }
                ((KnitSleepDetailActivity) this.mContextReference.get()).a(this.mBiTypeArray);
            }
        }
    }

    /* renamed from: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f10222a;
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[DataInfos.values().length];
            d = iArr;
            try {
                iArr[DataInfos.CoreSleepWeekDetail.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[DataInfos.CoreSleepMonthDetail.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[DataInfos.CoreSleepYearDetail.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SleepViewConstants.SleepTypeEnum.values().length];
            f10222a = iArr2;
            try {
                iArr2[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10222a[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10222a[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10222a[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private String getTypeString(SleepViewConstants.SleepTypeEnum sleepTypeEnum) {
        int i = AnonymousClass10.f10222a[sleepTypeEnum.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "normal" : "scientific" : "phone" : "manual";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBarChartUi() {
        if (isDataValid(this.mInteractor)) {
            this.mUiDataMap.put(PROCESS_TEXT_VISIBILITY, 0);
            setSleepDataToCommonSleep(0, 0);
            this.mUiDataMap.put(CHART_BOTTOM_LINE, false);
        } else {
            this.mUiDataMap.put(PROCESS_TEXT_VISIBILITY, 4);
            this.mUiDataMap.put(CHART_BOTTOM_LINE, true);
            this.mSleepChartData.b(this.mUiDataMap);
            this.mSectionBean.e(this.mSleepChartData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prossCalendarSelect(HealthCalendar healthCalendar) {
        health.compact.a.util.LogUtil.e("debugt", "prossCalendarSelect calendar is" + healthCalendar);
        this.mUiDataMap.put(CHART_REFLESH_TIME, Long.valueOf(healthCalendar.transformCalendar().getTimeInMillis()));
        this.mSleepReportData.b(this.mUiDataMap);
        this.mSectionBean.e(this.mSleepReportData);
    }

    private void initCalendar() {
        this.mUiDataMap.put(CHART_CALENDAR_LISTENER, new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (str.equals("BAR_CHART_CALENDAR_CLICK_EVENT")) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("calendar", SleepBaseBarLineChartProvider.this.mCalendar);
                    bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(new int[]{44105, 44004, 44108, 44109}));
                    Activity activity = SleepBaseBarLineChartProvider.this.sleepActivityWeakReference.get();
                    if (activity != null) {
                        HealthCalendarActivity.cxj_(activity, bundle, SleepBaseBarLineChartProvider.this.mCalendarSelectCallback);
                        return;
                    }
                    return;
                }
                if (str.equals("BAR_COMMON_HELP_ICON_LISTENER")) {
                    SleepBaseBarLineChartProvider.this.showFitnessDataOriginDialog();
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mSleepChartData.b(this.mUiDataMap);
        this.mSectionBean.e(this.mSleepChartData);
    }

    private void initViews(Context context) {
        this.mInteractor = new pwd();
        this.mUiDataMap.put(CHART_DATA_INFO, getDataInfo());
        this.mIsLoadingState = false;
        showCurrentDate();
        this.mSleepChartData.b(this.mUiDataMap);
        this.mSectionBean.e(this.mSleepChartData);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0060, code lost:
    
        if (r2.get(2) == r8.get(2)) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0088, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0086, code lost:
    
        if (r2.get(6) == r8.get(6)) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @Override // com.huawei.health.knit.section.listener.BarChartRangeShowCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onRangeChange(int r12, int r13) {
        /*
            r11 = this;
            long r0 = (long) r12
            java.util.Date r2 = new java.util.Date
            r3 = 60000(0xea60, double:2.9644E-319)
            long r5 = r0 * r3
            r2.<init>(r5)
            r11.mStartDay = r2
            long r5 = (long) r13
            java.util.Date r2 = new java.util.Date
            long r3 = r3 * r5
            r7 = 1
            long r3 = r3 - r7
            r2.<init>(r3)
            r11.mEndDay = r2
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MINUTES
            long r2 = r2.toMillis(r0)
            java.util.Date r4 = new java.util.Date
            r4.<init>(r2)
            java.util.Date r2 = r11.lastReqDate
            java.lang.String r3 = "SleepBaseBarLineChartProvider"
            r7 = 1
            if (r2 != 0) goto L35
            java.lang.String r2 = "first entry"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            health.compact.a.util.LogUtil.d(r3, r2)
            goto L8a
        L35:
            com.huawei.ui.commonui.linechart.common.DataInfos r2 = r11.getDataInfo()
            com.huawei.ui.commonui.linechart.common.DataInfos r8 = com.huawei.ui.commonui.linechart.common.DataInfos.CoreSleepYearDetail
            if (r2 != r8) goto L63
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            r2.setTime(r4)
            java.util.Calendar r8 = java.util.Calendar.getInstance()
            java.util.Date r9 = r11.lastReqDate
            r8.setTime(r9)
            int r9 = r2.get(r7)
            int r10 = r8.get(r7)
            if (r9 != r10) goto L8a
            r9 = 2
            int r2 = r2.get(r9)
            int r8 = r8.get(r9)
            if (r2 != r8) goto L8a
            goto L88
        L63:
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            java.util.Calendar r8 = java.util.Calendar.getInstance()
            r2.setTime(r4)
            java.util.Date r9 = r11.lastReqDate
            r8.setTime(r9)
            int r9 = r2.get(r7)
            int r10 = r8.get(r7)
            if (r9 != r10) goto L8a
            r9 = 6
            int r2 = r2.get(r9)
            int r8 = r8.get(r9)
            if (r2 != r8) goto L8a
        L88:
            r2 = r7
            goto L8b
        L8a:
            r2 = 0
        L8b:
            int r8 = r4.getHours()
            if (r8 != 0) goto Lb5
            int r8 = r4.getMinutes()
            if (r8 != 0) goto Lb5
            int r4 = r4.getSeconds()
            if (r4 != 0) goto Lb5
            if (r2 != 0) goto Lb5
            java.util.Date r2 = r11.getStartDay()
            r11.lastReqDate = r2
            java.lang.String r2 = "Current day is "
            java.util.Date r4 = r11.getStartDay()
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r3, r2)
            r11.requestData(r7)
        Lb5:
            r11.mStartX = r12
            r11.mEndX = r13
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.MINUTES
            long r0 = r13.toMillis(r0)
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.MINUTES
            long r2 = r13.toMillis(r5)
            boolean r13 = defpackage.jdl.d(r0, r2)
            if (r13 == 0) goto Lce
            r11.updateCalendar(r12)
        Lce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.onRangeChange(int, int):void");
    }

    @Override // com.huawei.health.knit.section.listener.ChartMarkChangeCallback
    public void onMarkChange(float f, float f2) {
        if (this.mTimeType == 1) {
            setSleepDataToCommonSleep((int) f, (int) f2);
        }
    }

    @Override // com.huawei.health.knit.section.listener.ChartMarkTextCallback
    public void onMarkTextChange(String str, List<HwHealthMarkerView.a> list, int i) {
        if (list != null) {
            if ("--".equals(this.mSleepHolder.c(list.get(list.size() - 1).b))) {
                this.mTimeType = 0;
                setSleepDataToCommonSleep(0, 0);
            } else {
                this.mTimeType = 1;
            }
        } else {
            setSleepDataToCommonSleep(0, 0);
        }
        health.compact.a.util.LogUtil.d(TAG, "minute is " + i);
        updateCalendar(nom.h(i));
        long millis = TimeUnit.MINUTES.toMillis((long) nom.h(i));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        this.nowMarkDate = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        if (getDataInfo() == DataInfos.CoreSleepYearDetail) {
            return;
        }
        if (this.lastMarkDate == null || !simpleDateFormat.format(this.nowMarkDate).equals(simpleDateFormat.format(this.lastMarkDate))) {
            health.compact.a.util.LogUtil.d(TAG, "real go " + this.nowMarkDate.toString());
            this.lastMarkDate = this.nowMarkDate;
            if (getDataInfo() == DataInfos.CoreSleepYearDetail) {
                return;
            }
            notifyDateType();
        }
    }

    protected void processJump(long j) {
        health.compact.a.util.LogUtil.d(TAG, "processJump");
        String e2 = jec.e(nom.l(j));
        Message message = new Message();
        message.what = MESSAGE_JUMP_TO_DAY_TAB;
        message.obj = e2;
        this.mUpdateUiHandler.sendMessage(message);
    }

    protected boolean getIfVerify(long j) {
        return !new Date(j).after(new Date(System.currentTimeMillis())) || FitnessUtils.c(System.currentTimeMillis(), j);
    }

    private boolean isDataValid(pwd pwdVar) {
        this.mNoonSleepValueList = FitnessUtils.c(pwdVar.e());
        this.mWakeSleepValueList = FitnessUtils.c(pwdVar.i());
        return isSleepDataValid(pwdVar) || this.mWakeSleepValueList == null || this.mNoonSleepValueList == null;
    }

    public Date getStartDay() {
        Date date = this.mStartDay;
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public void setStartDay(Date date) {
        this.mStartDay = date == null ? null : (Date) date.clone();
    }

    public void setLoadingState(boolean z) {
        this.mIsLoadingState = z;
    }

    public void setEndDay(Date date) {
        this.mEndDay = date == null ? null : (Date) date.clone();
    }

    public Date getEndDay() {
        Date date = this.mEndDay;
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public Handler getBaseHandler() {
        return this.mUpdateUiHandler;
    }

    public pwd getInteractor() {
        return this.mInteractor;
    }

    private boolean isSleepDataValid(pwd pwdVar) {
        this.mLightSleepValueList = FitnessUtils.c(pwdVar.h());
        this.mDeepSleepValueList = FitnessUtils.c(pwdVar.b());
        List<Double> c2 = FitnessUtils.c(pwdVar.j());
        this.mDreamSleepValueList = c2;
        return this.mLightSleepValueList == null || this.mDeepSleepValueList == null || c2 == null;
    }

    private void setSleepDataToCommonSleep(final int i, final int i2) {
        HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.3
            @Override // java.lang.Runnable
            public void run() {
                SleepBaseBarLineChartProvider.this.setSleepMins(i);
                if (SleepBaseBarLineChartProvider.this.getDataInfo() == DataInfos.CoreSleepYearDetail) {
                    SleepBaseBarLineChartProvider.this.setBedMins(0);
                } else {
                    SleepBaseBarLineChartProvider.this.setBedMins(i2);
                }
                SleepBaseBarLineChartProvider.this.mSleepChartData.b(SleepBaseBarLineChartProvider.this.mUiDataMap);
                SleepBaseBarLineChartProvider.this.mSectionBean.e(SleepBaseBarLineChartProvider.this.mSleepChartData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBedMins(int i) {
        SpannableString spannableString;
        if (i == 0) {
            this.mUiDataMap.put(BED_TIME_TEXT, "");
        }
        int i2 = i / 60;
        int i3 = i % 60;
        double d2 = i2;
        String e2 = UnitUtil.e(d2, 1, 0);
        double d3 = i3;
        String e3 = UnitUtil.e(d3, 1, 0);
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(d3, 1, 0));
        if (i2 == 0) {
            spannableString = new SpannableString("");
            int indexOf = i3 != 0 ? "".indexOf(e3) : -1;
            int length = i3 != 0 ? e3.length() : 2;
            if (indexOf >= 0) {
                setTypefaceSpan(spannableString, indexOf, length);
                spannableString.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363006_res_0x7f0a04be)), indexOf, length + indexOf, 17);
            }
        } else {
            String string = BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(d2, 1, 0)), quantityString);
            int lastIndexOf = string.lastIndexOf(e3);
            int indexOf2 = string.indexOf(e2);
            spannableString = new SpannableString(string);
            setSleepTextSpan(lastIndexOf, indexOf2, spannableString, e3, e2);
        }
        this.mUiDataMap.put(BED_TIME_TEXT, spannableString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSleepMins(int i) {
        SpannableString spannableString;
        this.mTypeface = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), MEDIUM_ASSETS_PATH);
        int i2 = i / 60;
        int i3 = i % 60;
        double d2 = i2;
        String e2 = UnitUtil.e(d2, 1, 0);
        double d3 = i3;
        String e3 = UnitUtil.e(d3, 1, 0);
        String quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(d3, 1, 0));
        if (i2 == 0) {
            if (i3 == 0) {
                quantityString = quantityString.replace(e3, "--");
                this.mUiDataMap.put(TIME_LAYOUT_CLICKABLE, false);
            } else {
                this.mUiDataMap.put(TIME_LAYOUT_CLICKABLE, true);
            }
            spannableString = new SpannableString(quantityString);
            int indexOf = i3 != 0 ? quantityString.indexOf(e3) : quantityString.indexOf("--");
            int length = i3 != 0 ? e3.length() : 2;
            if (indexOf >= 0) {
                setTypefaceSpan(spannableString, indexOf, length);
                spannableString.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363006_res_0x7f0a04be)), indexOf, length + indexOf, 17);
            }
        } else {
            this.mUiDataMap.put(TIME_LAYOUT_CLICKABLE, true);
            String string = BaseApplication.getContext().getString(com.huawei.ui.commonui.R$string.IDS_two_parts, BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(d2, 1, 0)), quantityString);
            int lastIndexOf = string.lastIndexOf(e3);
            int indexOf2 = string.indexOf(e2);
            spannableString = new SpannableString(string);
            setSleepTextSpan(lastIndexOf, indexOf2, spannableString, e3, e2);
        }
        this.mUiDataMap.put(SLEEP_TIME_TEXT, spannableString);
    }

    private void setTypefaceSpan(SpannableString spannableString, int i, int i2) {
        if (this.mTypeface == null) {
            LogUtil.c(TAG, "custom typeface is null.,return");
        } else if (Build.VERSION.SDK_INT >= 28) {
            spannableString.setSpan(new HarmonyOsTypefaceSpan(this.mTypeface), i, i2 + i, 17);
        } else {
            spannableString.setSpan(new TypefaceSpan("HwChinese-medium"), i, i2 + i, 17);
        }
    }

    private void setSleepTextSpan(int i, int i2, SpannableString spannableString, String str, String str2) {
        if (i >= 0) {
            setTypefaceSpan(spannableString, i, str.length());
            spannableString.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363006_res_0x7f0a04be)), i, str.length() + i, 17);
        }
        if (i2 >= 0) {
            setTypefaceSpan(spannableString, i2, str2.length());
            spannableString.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363006_res_0x7f0a04be)), i2, str2.length() + i2, 17);
        }
    }

    private void updateCalendar(int i) {
        long millis = TimeUnit.MINUTES.toMillis(i);
        long d2 = jec.d(millis);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        health.compact.a.util.LogUtil.e("debugtime", "updateCalendar timeMills is " + millis);
        if (this.mCalendar == null) {
            this.mCalendar = new HealthCalendar();
        }
        this.mCalendar = this.mCalendar.transformFromCalendar(calendar);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(d2);
        this.nowMarkDate = calendar2.getTime();
    }

    private void notifyDateType() {
        health.compact.a.util.LogUtil.d(TAG, " notifyDateType dateIntegerMap is ", this.mDateIntegerMap.toString());
        if (CollectionUtils.e(this.mDateIntegerMap) || this.nowMarkDate == null) {
            health.compact.a.util.LogUtil.d(TAG, "mDateIntegerMap is null or nowMarkDate is null");
            notifyMinorProviders(null);
            return;
        }
        getBiType();
        ReleaseLogUtil.e(RELEASE_TAG, " dateIntegerMap size is ", Integer.valueOf(this.mDateIntegerMap.size()));
        String format = new SimpleDateFormat("yyyy-MM-dd").format(this.nowMarkDate);
        SleepViewConstants.SleepTypeEnum sleepTypeEnum = this.mDateIntegerMap.containsKey(format) ? this.mDateIntegerMap.get(format) : SleepViewConstants.SleepTypeEnum.UNKNOWN;
        if ((sleepTypeEnum != SleepViewConstants.SleepTypeEnum.UNKNOWN || this.mSleepReportData.e() == SleepViewConstants.ViewTypeEnum.YEAR) && sleepTypeEnum != null) {
            this.mSleepReportData.c(sleepTypeEnum);
            if (getDataInfo() == DataInfos.CoreSleepYearDetail) {
                this.mUiDataMap.put("wear_core_data", 0);
            }
            this.mUiDataMap.put(FITNESSCORE_SLEEP_DETAIL_INTERACTOR, this.mInteractor);
            this.mUiDataMap.put("DATA_DIFFERENCE", getDataDifference());
            this.mSleepReportData.b(this.mUiDataMap);
            notifyMinorProviders(this.mSleepReportData);
        }
    }

    protected void getNormalWearList(long j, FitnessDataQueryDefine.FitnessQueryId fitnessQueryId, List<HiHealthClient> list) {
        HiHealthManager.d(com.huawei.haf.application.BaseApplication.e()).fetchDataSourceByType(22000, new HiTimeInterval(j * 1000, ((((FitnessDataQueryDefine.a(j, fitnessQueryId) * FitnessDataQueryDefine.e(fitnessQueryId)) * 60) + j) * 1000) - 1), new c(this, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceList(List<HiHealthClient> list, List<HiHealthClient> list2) {
        ArrayList<pwb> d2 = qnl.d(list, list2);
        Message obtain = Message.obtain();
        obtain.what = MSG_UPDATE_DATA_ORIGINAL_ICON;
        obtain.obj = d2;
        this.mUpdateUiHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMsgUpdateDataOriginal(Message message) {
        if (!koq.e(message.obj, pwb.class)) {
            health.compact.a.util.LogUtil.c(TAG, "msg.obj not instanceof List");
            this.mSleepChartData.b(this.mUiDataMap);
            this.mSectionBean.e(this.mSleepChartData);
            return;
        }
        this.mFitnessOriginListData.clear();
        this.mFitnessOriginListData.addAll((List) message.obj);
        health.compact.a.util.LogUtil.d(TAG, "mFitnessOriginListData is ", this.mFitnessOriginListData);
        if (koq.d(this.mFitnessOriginListData, 0)) {
            this.mUiDataMap.put(HELP_ICON_IMAGE, BaseApplication.getContext().getDrawable(R.drawable._2131430282_res_0x7f0b0b8a));
        }
        this.mUiDataMap.put(HELP_ICON_IMAGE_VISIBILITY, 0);
        updateDatasUi(this.mUiDataMap);
    }

    private void processMsgUpdateDevicePriority(Message message) {
        List<HiHealthClient> list = (List) message.obj;
        health.compact.a.util.LogUtil.d(TAG, "clientList = ", list.toString());
        this.mClientList = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFitnessDataOriginDialog() {
        String string;
        health.compact.a.util.LogUtil.d(TAG, "showFitnessDataOriginDialog()");
        this.mUiDataMap.put(HELP_ICON_IMAGE, BaseApplication.getContext().getDrawable(R.drawable._2131430282_res_0x7f0b0b8a));
        this.mSleepChartData.b(this.mUiDataMap);
        this.mSectionBean.e(this.mSleepChartData);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10006), "SLEEP_DEVICE_ICON_RED_DOT_KEY", "false", new StorageParams());
        CustomViewDialog customViewDialog = this.mCustomViewDialog;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.mCustomViewDialog.dismiss();
            return;
        }
        Activity activity = this.sleepActivityWeakReference.get();
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(activity);
        FitnessDataOriginView fitnessDataOriginView = new FitnessDataOriginView((Context) activity, (Boolean) true);
        this.mFitnessDataOriginView = fitnessDataOriginView;
        fitnessDataOriginView.setIsSleepType();
        if (koq.b(this.mFitnessOriginListData, 1)) {
            string = BaseApplication.getContext().getString(R$string.IDS_sleep_data_source_info_average_instruction, 3);
        } else {
            string = BaseApplication.getContext().getString(R$string.IDS_sleep_data_source_info_week, 3);
        }
        this.mFitnessDataOriginView.setInfoText(string);
        this.mFitnessDataOriginView.setmListdata(this.mFitnessOriginListData);
        builder.a(BaseApplication.getContext().getString(R$string.IDS_sleep_data_source)).czg_(this.mFitnessDataOriginView).cze_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                health.compact.a.util.LogUtil.d(SleepBaseBarLineChartProvider.TAG, "showFitnessDataOriginDialog() PositiveButton onClick.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.mCustomViewDialog = e2;
        e2.show();
    }

    static class e implements CommonUiBaseResponse {
        private WeakReference<SleepBaseBarLineChartProvider> d;

        e(SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider) {
            this.d = new WeakReference<>(sleepBaseBarLineChartProvider);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider = this.d.get();
            if (sleepBaseBarLineChartProvider != null && i == 1 && (obj instanceof HealthCalendar)) {
                sleepBaseBarLineChartProvider.prossCalendarSelect((HealthCalendar) obj);
            }
        }
    }

    static class c implements HiDataClientListener {
        private List<HiHealthClient> c;
        private WeakReference<SleepBaseBarLineChartProvider> d;

        c(SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider, List<HiHealthClient> list) {
            this.d = new WeakReference<>(sleepBaseBarLineChartProvider);
            this.c = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            health.compact.a.util.LogUtil.d(SleepBaseBarLineChartProvider.TAG, "on result normallist");
            SleepBaseBarLineChartProvider sleepBaseBarLineChartProvider = this.d.get();
            if (sleepBaseBarLineChartProvider == null) {
                return;
            }
            sleepBaseBarLineChartProvider.getDeviceList(this.c, list);
        }
    }

    protected String getTag() {
        return TAG;
    }
}

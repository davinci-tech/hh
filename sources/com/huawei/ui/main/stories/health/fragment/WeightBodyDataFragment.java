package com.huawei.ui.main.stories.health.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightBodyDataActivity;
import com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment;
import defpackage.cfe;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nmj;
import defpackage.nmm;
import defpackage.nrf;
import defpackage.nsk;
import defpackage.qku;
import defpackage.qrp;
import defpackage.qsj;
import defpackage.rar;
import defpackage.scf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WeightBodyDataFragment extends BaseFragment {
    public static final String AI_BODY_SHAPE_BEAN = "AI_BODY_SHAPE_BEAN";
    protected static final float MAX_LEVEL_PERCENT = 100.0f;
    protected static final float PRECISION_VALUE_ONE = 0.1f;
    protected static final float PRECISION_VALUE_TWO = 0.01f;
    private static final String TAG = "HealthWeight_WeightBodyDataFragment";
    private static final int TEXT_SIZE = 10;
    private static final String WEIGHT_BODY_DATA_QUERY_KEY = "test";
    protected HealthImageView mAIBodyDataImageView;
    protected View mAIBodyDataImageViewLayout;
    private HealthCardView mAboutCardView;
    private HealthTextView mAboutDescription;
    protected ImageView mAboutImage;
    private HealthSubHeader mAboutSubHeader;
    protected WeightBodyDataActivity mActivity;
    protected qku mAiBodyShapeBean;
    private HealthTextView mBalanced;
    private HealthCardView mBalancedCardView;
    private HealthTextView mBalancedDescription;
    private HealthSubHeader mBalancedSubHeader;
    private HealthTextView mBalancedSuggest;
    protected Context mContext;
    private HealthCardView mDataCardView;
    private HealthTextView mDataSubTitle;
    protected HealthTextView mDataUnit;
    private HealthTextView mDataValue;
    protected byte mGender;
    private HealthTextView mIdeal;
    private HealthCardView mIdealCardView;
    protected HealthTextView mIdealDescription;
    private HealthSubHeader mIdealSubHeader;
    private HealthLevelIndicator mIndicator;
    private HealthCardView mIndicatorCardView;
    private ViewStub mIndicatorLevelLayout;
    protected Resources mResources;
    private HealthCardView mResultCardView;
    private HealthTextView mResultDescription;
    private HealthSubHeader mResultSubHeader;
    private HealthTextView mResultSuggest;
    protected LinearLayout mSegmental;
    private HealthCardView mSegmentalCardView;
    protected HealthSubHeader mSegmentalSubHeader;
    protected cfe mWeightBean;
    private rar mWeightTrendChartViewHolder;
    protected int mStartType = 0;
    protected long mStartTime = 0;
    protected boolean mIsShowChange = false;
    protected boolean mIsOversea = false;
    private boolean mHasScrollBi = false;
    private boolean mHadScrollBottomBi = false;
    private final Handler mWeightBodyDataHandler = new c(this);

    protected int getBiType() {
        return 0;
    }

    protected int getLayoutId() {
        return R.layout.fragment_weight_body_data;
    }

    protected void initAnalysisAndInterpretation() {
    }

    protected void initData() {
    }

    protected void initSpecification() {
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = BaseApplication.getContext();
        this.mContext = context;
        this.mResources = context.getResources();
        FragmentActivity activity = getActivity();
        if (activity instanceof WeightBodyDataActivity) {
            this.mActivity = (WeightBodyDataActivity) activity;
        } else {
            LogUtil.h(TAG, "onCreate fragmentActivity instanceof WeightBodyDataActivity is false");
        }
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h(TAG, "onCreate Bundle is null");
            return;
        }
        Serializable serializable = arguments.getSerializable("WeightBean");
        if (serializable instanceof cfe) {
            this.mWeightBean = (cfe) serializable;
        } else {
            LogUtil.h(TAG, "onCreate Serializable not WeightBean");
        }
        Serializable serializable2 = arguments.getSerializable(AI_BODY_SHAPE_BEAN);
        if (serializable2 instanceof qku) {
            this.mAiBodyShapeBean = (qku) serializable2;
        } else {
            LogUtil.h(TAG, "onCreate Serializable not AiBodyShapeBean");
        }
        this.mStartType = arguments.getInt("start_type", this.mStartType);
        this.mStartTime = arguments.getLong("start_time", this.mStartTime);
        this.mIsShowChange = arguments.getBoolean("is_show_change", this.mIsShowChange);
        this.mIsOversea = arguments.getBoolean("is_oversea", this.mIsOversea);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null || (this.mWeightBean == null && this.mAiBodyShapeBean == null)) {
            LogUtil.h(TAG, "onCreateView LayoutInflater or WeightBean AibodyShapeBean is null");
            return null;
        }
        View inflate = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        initView(inflate);
        initLineChartView(inflate);
        initData();
        initSpecification();
        initAnalysisAndInterpretation();
        initScrollBiListener(inflate);
        initAIBodyDataImage();
        return inflate;
    }

    private void initLineChartView(View view) {
        boolean booleanValue = isShowHistoricalData().booleanValue();
        boolean z = getArguments().getBoolean("isGuestMeasure");
        LogUtil.a(TAG, "initLineChartView() mIsGuestMeasure = ", Boolean.valueOf(z), ", showHistoricalData = ", Boolean.valueOf(booleanValue));
        if (!booleanValue || z) {
            return;
        }
        rar rarVar = new rar(view, this.mWeightBean);
        this.mWeightTrendChartViewHolder = rarVar;
        rarVar.e(getBodyDataKey());
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.historical_data_subHeader);
        healthSubHeader.setSubHeaderBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
        this.mWeightTrendChartViewHolder.dJC_(healthSubHeader);
        getChartData();
    }

    protected void getChartData() {
        HiHealthManager.d(getContext()).aggregateHiHealthData(getHiAggregateOption(), new d(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTrendView() {
        Message obtainMessage = this.mWeightBodyDataHandler.obtainMessage();
        obtainMessage.what = 18;
        this.mWeightBodyDataHandler.sendMessage(obtainMessage);
    }

    public void updateTrendData(List<Pair<Long, Float>> list) {
        if (koq.b(list) || list.size() == 1) {
            LogUtil.h(TAG, "readCardData onResult no data");
            hideTrendView();
        } else {
            Message obtainMessage = this.mWeightBodyDataHandler.obtainMessage();
            obtainMessage.what = 10;
            obtainMessage.obj = list;
            this.mWeightBodyDataHandler.sendMessage(obtainMessage);
        }
    }

    protected HiAggregateOption getHiAggregateOption() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(getTrendStartTime(), getTrendEndTime());
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setFilter(qsj.a(MultiUsersManager.INSTANCE.getCurrentUser()));
        hiAggregateOption.setConstantsKey(new String[]{"test"});
        LogUtil.a(TAG, "getChartData() aggregateOption = ", hiAggregateOption.toString());
        return hiAggregateOption;
    }

    public void initView(View view) {
        this.mDataCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_data_card_view);
        this.mDataSubTitle = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_sub_title);
        this.mDataValue = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_value);
        this.mDataUnit = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_unit);
        this.mIndicatorCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_specification_card_view);
        this.mIndicator = (HealthLevelIndicator) view.findViewById(R.id.fragment_weight_body_data_specification_indicator);
        this.mIndicatorLevelLayout = (ViewStub) view.findViewById(R.id.view_stub_weight_body_level_rang);
        this.mSegmentalSubHeader = (HealthSubHeader) view.findViewById(R.id.fragment_weight_body_data_segmental_sub_header);
        this.mSegmentalCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_body_data_segmental_card_view);
        this.mSegmental = (LinearLayout) view.findViewById(R.id.fragment_weight_body_data_segmental);
        this.mIdealSubHeader = (HealthSubHeader) view.findViewById(R.id.fragment_weight_body_data_ideal_header);
        this.mIdealCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_body_data_ideal_card_view);
        this.mIdeal = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_ideal);
        this.mIdealDescription = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_ideal_description);
        this.mBalancedSubHeader = (HealthSubHeader) view.findViewById(R.id.fragment_weight_body_data_balanced_header);
        this.mBalancedCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_body_data_balanced_card_view);
        this.mBalanced = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_balanced);
        this.mBalancedDescription = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_balanced_description);
        this.mBalancedSuggest = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_balanced_suggest);
        initResultViewAndAboutView(view);
        this.mAboutImage = (ImageView) view.findViewById(R.id.fragment_weight_body_data_about_image);
        this.mAIBodyDataImageView = (HealthImageView) view.findViewById(R.id.ai_body_shape_image);
        this.mAIBodyDataImageViewLayout = view.findViewById(R.id.ai_body_shape_image_layout);
    }

    private void initScrollBiListener(View view) {
        HealthScrollView healthScrollView = (HealthScrollView) view.findViewById(R.id.fragment_weight_scrollView);
        healthScrollView.setIsForceScrollListener(true);
        healthScrollView.setScrollViewListener(new ScrollViewListener() { // from class: qix
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public final void onScrollChanged(HealthScrollView healthScrollView2, int i, int i2, int i3, int i4) {
                WeightBodyDataFragment.this.m829xab120c4c(healthScrollView2, i, i2, i3, i4);
            }
        });
    }

    /* renamed from: lambda$initScrollBiListener$0$com-huawei-ui-main-stories-health-fragment-WeightBodyDataFragment, reason: not valid java name */
    public /* synthetic */ void m829xab120c4c(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
        if (!this.mHasScrollBi) {
            this.mHasScrollBi = true;
            biScrollViewEvent(1);
        }
        if (this.mHadScrollBottomBi || i2 != healthScrollView.getChildAt(0).getMeasuredHeight() - healthScrollView.getMeasuredHeight()) {
            return;
        }
        this.mHadScrollBottomBi = true;
        biScrollViewEvent(2);
    }

    private void biScrollViewEvent(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("type", Integer.valueOf(getBiType()));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.WEIGHT_PAGE_DATA_DETAIL_BOTOOM_2160115.value(), hashMap, 0);
    }

    public void updateTitleTextStyle(String str, SpannableString spannableString, int i) {
        int length = spannableString.length();
        int length2 = str.length();
        if (length2 > length) {
            LogUtil.h(TAG, "initData bodyAgeLength is error");
            length2 = length;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(13, true), 0, length, 17);
        int indexOf = spannableString.toString().indexOf(str);
        if (LanguageUtil.ai(this.mContext)) {
            indexOf = length - length2;
        } else {
            length = indexOf + length2;
        }
        if (indexOf != -1) {
            spannableString.setSpan(new AbsoluteSizeSpan(40, true), indexOf, length, 17);
        }
        spannableString.setSpan(new nmj(nsk.cKN_()), 0, spannableString.length(), 17);
        setDataInfo(getString(i), spannableString);
    }

    protected Boolean isShowHistoricalData() {
        return false;
    }

    protected Boolean isSetUnit() {
        return false;
    }

    private void initAIBodyDataImage() {
        String aIBodyShapeImageKey = getAIBodyShapeImageKey();
        if (TextUtils.isEmpty(aIBodyShapeImageKey)) {
            LogUtil.a(TAG, "imagekey is empty, return");
        } else {
            scf.e(aIBodyShapeImageKey, new AnonymousClass3());
        }
    }

    /* renamed from: com.huawei.ui.main.stories.health.fragment.WeightBodyDataFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements DownloadCallback<File> {
        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onProgress(long j, long j2, boolean z, String str) {
        }

        AnonymousClass3() {
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onFinish(final File file) {
            HandlerExecutor.e(new Runnable() { // from class: qiy
                @Override // java.lang.Runnable
                public final void run() {
                    WeightBodyDataFragment.AnonymousClass3.this.d(file);
                }
            });
        }

        public /* synthetic */ void d(File file) {
            WeightBodyDataFragment.this.mAIBodyDataImageViewLayout.setVisibility(0);
            nrf.b(file, WeightBodyDataFragment.this.mAIBodyDataImageView);
            WeightBodyDataFragment.this.mWeightTrendChartViewHolder.b(0);
        }

        @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
        public void onFail(int i, Throwable th) {
            LogUtil.b(WeightBodyDataFragment.TAG, "loadImage fail ", th);
        }
    }

    protected long getTrendEndTime() {
        cfe cfeVar = this.mWeightBean;
        if (cfeVar == null) {
            LogUtil.b(TAG, "weight bean is null, return");
            return 0L;
        }
        return cfeVar.av();
    }

    protected long getTrendStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getTrendEndTime());
        calendar.add(1, -1);
        return calendar.getTime().getTime();
    }

    protected void initResultViewAndAboutView(View view) {
        this.mResultSubHeader = (HealthSubHeader) view.findViewById(R.id.fragment_weight_body_data_result);
        this.mResultCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_body_data_result_card_view);
        this.mResultDescription = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_result_description);
        this.mResultSuggest = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_result_suggest);
        this.mAboutSubHeader = (HealthSubHeader) view.findViewById(R.id.fragment_weight_body_data_about);
        this.mAboutCardView = (HealthCardView) view.findViewById(R.id.fragment_weight_body_data_about_card_view);
        this.mAboutDescription = (HealthTextView) view.findViewById(R.id.fragment_weight_body_data_about_description);
    }

    protected void setDataInfo(String str, String str2, String str3) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        HealthTextView healthTextView3;
        HealthCardView healthCardView = this.mDataCardView;
        if (healthCardView == null) {
            LogUtil.h(TAG, "setDataInfo, mDataCardView is null.");
            return;
        }
        healthCardView.setVisibility(0);
        if (!TextUtils.isEmpty(str) && (healthTextView3 = this.mDataSubTitle) != null) {
            healthTextView3.setText(str);
        }
        if (!TextUtils.isEmpty(str2) && (healthTextView2 = this.mDataValue) != null) {
            healthTextView2.setText(str2);
        }
        if (TextUtils.isEmpty(str3) || (healthTextView = this.mDataUnit) == null) {
            return;
        }
        healthTextView.setText(str3);
    }

    protected void setDataInfo(String str, SpannableString spannableString) {
        HealthTextView healthTextView;
        HealthTextView healthTextView2;
        HealthCardView healthCardView = this.mDataCardView;
        if (healthCardView == null) {
            LogUtil.h(TAG, "setDataInfo, mDataCardView is null.");
            return;
        }
        healthCardView.setVisibility(0);
        if (!TextUtils.isEmpty(str) && (healthTextView2 = this.mDataSubTitle) != null) {
            healthTextView2.setText(str);
        }
        if (spannableString == null || (healthTextView = this.mDataValue) == null) {
            return;
        }
        healthTextView.setText(spannableString);
    }

    protected void setIndicatorCardViewVisible() {
        HealthCardView healthCardView = this.mIndicatorCardView;
        if (healthCardView == null) {
            LogUtil.h(TAG, "setIndicatorCardViewVisible, mIndicatorCardView is null.");
        } else {
            healthCardView.setVisibility(0);
        }
    }

    protected void setAllLevelsData(List<nmm> list, double[] dArr, double[] dArr2) {
        qsj.a(this.mIndicator, list, dArr, dArr2);
    }

    protected void setLevelSpannableString(double d2, String str) {
        if (this.mIndicator == null) {
            LogUtil.h(TAG, "setLevel, mIndicator is null.");
        } else {
            this.mIndicator.setLevel((float) d2, UnitUtil.bCR_(this.mContext, "[\\.\\d]", str, R.style.health_weight_body_data_value, R.style.health_weight_body_data_unit));
        }
    }

    protected void setLevelUnitString(double d2, String str, String str2) {
        HealthLevelIndicator healthLevelIndicator = this.mIndicator;
        if (healthLevelIndicator == null) {
            LogUtil.h(TAG, "setLevel, mIndicator is null.");
        } else {
            healthLevelIndicator.setLevel((float) d2, str);
            this.mIndicator.setLevelUnit(str2);
        }
    }

    protected void setLevel(double d2, String str) {
        HealthLevelIndicator healthLevelIndicator = this.mIndicator;
        if (healthLevelIndicator == null) {
            LogUtil.h(TAG, "setLevel, mIndicator is null.");
        } else {
            healthLevelIndicator.setLevel((float) d2, str);
        }
    }

    protected void setLevelInfo(List<b> list) {
        ViewStub viewStub;
        if (koq.b(list) || (viewStub = this.mIndicatorLevelLayout) == null) {
            LogUtil.h(TAG, "setLevelInfo, levelInfoBeans is empty or mIndicatorLevelLayout is null.");
            return;
        }
        View inflate = viewStub.inflate();
        for (int i = 0; i < list.size(); i++) {
            b bVar = list.get(i);
            if (bVar != null) {
                if (i == 0) {
                    initLevelItem(inflate.findViewById(R.id.weight_body_level_rang_low), bVar);
                } else if (i == 1) {
                    initLevelItem(inflate.findViewById(R.id.weight_body_level_rang_stand), bVar);
                } else if (i == 2) {
                    initLevelItem(inflate.findViewById(R.id.weight_body_level_rang_high), bVar);
                } else if (i == 3) {
                    initLevelItem(inflate.findViewById(R.id.weight_body_level_rang_super_high), bVar);
                }
            }
        }
    }

    private void initLevelItem(View view, b bVar) {
        view.setVisibility(0);
        ImageView imageView = (ImageView) view.findViewById(R.id.weight_body_level_rang_item_icon);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(bVar.d());
        imageView.setImageDrawable(gradientDrawable);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.weight_body_level_rang_item_name);
        healthTextView.setSplittable(true);
        healthTextView.setText(bVar.a());
        if (LanguageUtil.u(this.mContext) || LanguageUtil.ar(this.mContext) || LanguageUtil.bj(this.mContext)) {
            healthTextView.setTextSize(1, 10.0f);
        }
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.weight_body_level_rang_item_content);
        if (TextUtils.isEmpty(bVar.e())) {
            healthTextView2.setVisibility(8);
        } else {
            healthTextView2.setText(bVar.e());
        }
    }

    protected int getColor(int i) {
        return ContextCompat.getColor(this.mContext, i);
    }

    protected String getComRangContent(float f, float f2) {
        return this.mContext.getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(f, 1, 1), UnitUtil.e(f2, 1, 1));
    }

    protected String getMinRangContent(float f) {
        if (LanguageUtil.b(this.mContext)) {
            return this.mContext.getString(R$string.IDS_less_than, UnitUtil.e(f, 1, 1));
        }
        return this.mContext.getString(R$string.IDS_bp_min_scope_value, UnitUtil.e(f, 1, 1));
    }

    protected String getMaxRangContent(float f) {
        return this.mContext.getString(R$string.IDS_bp_max_explain, UnitUtil.e(f, 1, 1));
    }

    protected float getCalculateRangValue(float f, float f2, boolean z) {
        BigDecimal valueOf = BigDecimal.valueOf(f);
        BigDecimal valueOf2 = BigDecimal.valueOf(f2);
        if (z) {
            return valueOf.add(valueOf2).floatValue();
        }
        return valueOf.subtract(valueOf2).floatValue();
    }

    private void setSubHeaderTopMargin(HealthSubHeader healthSubHeader, int i) {
        ViewGroup.LayoutParams layoutParams = healthSubHeader.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin = i;
            healthSubHeader.setLayoutParams(layoutParams2);
        }
    }

    protected void setSegmentalCardViewState(int i) {
        HealthSubHeader healthSubHeader = this.mSegmentalSubHeader;
        if (healthSubHeader != null) {
            setSubHeaderTopMargin(healthSubHeader, (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363171_res_0x7f0a0563));
            this.mSegmentalSubHeader.setVisibility(i);
            this.mSegmentalSubHeader.setSubHeaderBackgroundColor(0);
        }
        HealthCardView healthCardView = this.mSegmentalCardView;
        if (healthCardView == null) {
            LogUtil.h(TAG, "setSegmentalCardViewState, mSegmentalCardView is null.");
        } else {
            healthCardView.setVisibility(i);
        }
    }

    protected void initIdeal(String str, String str2, String str3) {
        if (!qrp.d()) {
            LogUtil.h(TAG, "initIdeal, is not support.");
        } else {
            initIdealForGlobal(str, str2, str3);
        }
    }

    protected void initIdealForGlobal(String str, String str2, String str3) {
        HealthSubHeader healthSubHeader;
        boolean isEmpty = TextUtils.isEmpty(str2);
        boolean isEmpty2 = TextUtils.isEmpty(str3);
        HealthTextView healthTextView = this.mIdeal;
        if (healthTextView != null && !isEmpty) {
            healthTextView.setText(str2);
            this.mIdeal.setVisibility(0);
        }
        HealthTextView healthTextView2 = this.mIdealDescription;
        if (healthTextView2 != null && !isEmpty2) {
            healthTextView2.setText(str3);
            this.mIdealDescription.setVisibility(0);
        }
        if (isEmpty && isEmpty2) {
            LogUtil.h(TAG, "initIdealForGlobal, isNameEmpty or isDescriptionEmpty.");
            return;
        }
        if (this.mIdealSubHeader != null && !TextUtils.isEmpty(str)) {
            this.mIdealSubHeader.setHeadTitleText(str);
            this.mIdealSubHeader.setSubHeaderBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
            if (this.mResultSubHeader != null && (healthSubHeader = this.mSegmentalSubHeader) != null && healthSubHeader.getVisibility() == 8 && this.mResultSubHeader.getVisibility() == 8) {
                setSubHeaderTopMargin(this.mIdealSubHeader, (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363171_res_0x7f0a0563));
            }
            this.mIdealSubHeader.setVisibility(0);
        }
        HealthCardView healthCardView = this.mIdealCardView;
        if (healthCardView != null) {
            healthCardView.setVisibility(0);
        }
    }

    protected void initResult(String str, String str2) {
        if (!qrp.d()) {
            LogUtil.h(TAG, "initResult, is not support.");
        } else {
            initResultForGlobal(str, str2);
        }
    }

    protected void initResultForGlobal(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        HealthTextView healthTextView = this.mResultDescription;
        if (healthTextView != null && !isEmpty) {
            healthTextView.setText(str);
            this.mResultDescription.setVisibility(0);
        }
        HealthTextView healthTextView2 = this.mResultSuggest;
        if (healthTextView2 != null && !isEmpty2) {
            healthTextView2.setText(str2);
            this.mResultSuggest.setVisibility(0);
        }
        if (isEmpty && isEmpty2) {
            LogUtil.h(TAG, "initResultForGlobal, isDescriptionEmpty or isSuggestionEmpty.");
            return;
        }
        HealthSubHeader healthSubHeader = this.mResultSubHeader;
        if (healthSubHeader != null) {
            healthSubHeader.setSubHeaderBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
            HealthSubHeader healthSubHeader2 = this.mSegmentalSubHeader;
            if (healthSubHeader2 != null && healthSubHeader2.getVisibility() == 8) {
                setSubHeaderTopMargin(this.mResultSubHeader, (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363171_res_0x7f0a0563));
            }
            this.mResultSubHeader.setVisibility(0);
        }
        HealthCardView healthCardView = this.mResultCardView;
        if (healthCardView != null) {
            healthCardView.setVisibility(0);
        }
    }

    protected void setBalanceResultViewState(int i) {
        HealthSubHeader healthSubHeader = this.mBalancedSubHeader;
        if (healthSubHeader != null) {
            healthSubHeader.setVisibility(i);
        }
        HealthCardView healthCardView = this.mBalancedCardView;
        if (healthCardView != null) {
            healthCardView.setVisibility(i);
        }
        HealthTextView healthTextView = this.mBalanced;
        if (healthTextView != null) {
            healthTextView.setVisibility(i);
        }
        HealthTextView healthTextView2 = this.mBalancedDescription;
        if (healthTextView2 != null) {
            healthTextView2.setVisibility(i);
        }
        HealthTextView healthTextView3 = this.mBalancedSuggest;
        if (healthTextView3 != null) {
            healthTextView3.setVisibility(i);
        }
    }

    protected void setBalanceInfo(boolean z, String str, String str2, String str3, String str4) {
        if (z || !qrp.d()) {
            setBalanceResultViewState(8);
            return;
        }
        setBalanceResultViewState(0);
        if (this.mBalanced != null && !TextUtils.isEmpty(str2)) {
            this.mBalanced.setText(str2);
        }
        if (this.mBalancedDescription != null && !TextUtils.isEmpty(str3)) {
            this.mBalancedDescription.setText(str3);
        }
        if (this.mBalancedSuggest != null && !TextUtils.isEmpty(str4)) {
            this.mBalancedSuggest.setText(str4);
        }
        if (this.mBalancedSubHeader == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.mBalancedSubHeader.setHeadTitleText(str);
        this.mBalancedSubHeader.setSubHeaderBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
    }

    protected void initAbout(String str, String str2) {
        if (this.mAboutSubHeader != null && !TextUtils.isEmpty(str)) {
            HealthSubHeader healthSubHeader = this.mAboutSubHeader;
            String string = this.mResources.getString(R$string.IDS_weight_about);
            Object[] objArr = new Object[1];
            if (!LanguageUtil.j(this.mContext)) {
                str = str.toLowerCase(Locale.ENGLISH);
            }
            objArr[0] = str;
            healthSubHeader.setHeadTitleText(String.format(string, objArr));
            this.mAboutSubHeader.setSubHeaderBackgroundColor(getColor(R.color._2131299296_res_0x7f090be0));
            this.mAboutSubHeader.setVisibility(0);
        }
        if (this.mAboutDescription == null || TextUtils.isEmpty(str2)) {
            LogUtil.h(TAG, "initAbout, mAboutDescription is null or description is empty.");
            return;
        }
        this.mAboutDescription.setText(str2);
        this.mAboutDescription.setVisibility(0);
        HealthCardView healthCardView = this.mAboutCardView;
        if (healthCardView != null) {
            healthCardView.setVisibility(0);
        }
    }

    protected void initAboutImage(int i) {
        ImageView imageView = this.mAboutImage;
        if (imageView == null) {
            LogUtil.h(TAG, "initAboutImage, mAboutImage is null.");
        } else {
            imageView.setImageResource(i);
            this.mAboutImage.setVisibility(0);
        }
    }

    protected List<Pair<Long, Float>> buildTrendData(List<HiHealthData> list) {
        double d2;
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h(TAG, "dataList is empty", getBodyDataKey());
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            if (isSetUnit().booleanValue()) {
                d2 = UnitUtil.a(hiHealthData.getDouble(getBodyDataKey()));
            } else {
                d2 = hiHealthData.getDouble(getBodyDataKey());
            }
            float f = (float) d2;
            if (f > 0.0f) {
                arrayList.add(new Pair(Long.valueOf(hiHealthData.getStartTime()), Float.valueOf(f)));
            }
        }
        LogUtil.a(TAG, "dataList size is: ", Integer.valueOf(list.size()), " trendDataList size is: ", Integer.valueOf(arrayList.size()), " getBodyDataKey is: ", getBodyDataKey());
        return arrayList;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private final String f10182a;
        private final int b;
        private final String c;

        public b(String str, String str2, int i) {
            this.f10182a = str;
            this.c = str2;
            this.b = i;
        }

        public String a() {
            return this.f10182a;
        }

        public String e() {
            return this.c;
        }

        public int d() {
            return this.b;
        }
    }

    static class d implements HiAggregateListener {
        private final WeakReference<WeightBodyDataFragment> b;

        d(WeightBodyDataFragment weightBodyDataFragment) {
            this.b = new WeakReference<>(weightBodyDataFragment);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeightBodyDataFragment weightBodyDataFragment = this.b.get();
            if (weightBodyDataFragment == null || weightBodyDataFragment.isDetached()) {
                LogUtil.h(WeightBodyDataFragment.TAG, "ChartDataListener onResult fragment ", weightBodyDataFragment);
            } else {
                weightBodyDataFragment.updateTrendData(weightBodyDataFragment.buildTrendData(list));
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            WeightBodyDataFragment weightBodyDataFragment = this.b.get();
            if (weightBodyDataFragment != null && !weightBodyDataFragment.isDetached()) {
                weightBodyDataFragment.hideTrendView();
            } else {
                LogUtil.h(WeightBodyDataFragment.TAG, "ChartDataListener onResultIntent fragment ", weightBodyDataFragment);
            }
        }
    }

    public class c extends BaseHandler<WeightBodyDataFragment> {
        c(WeightBodyDataFragment weightBodyDataFragment) {
            super(weightBodyDataFragment);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dEl_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightBodyDataFragment weightBodyDataFragment, Message message) {
            ArrayList arrayList;
            if (weightBodyDataFragment == null) {
                LogUtil.h(WeightBodyDataFragment.TAG, "handleMessageWhenReferenceNotNull obj is null");
                return;
            }
            int i = message.what;
            if (i != 10) {
                if (i == 18) {
                    if (WeightBodyDataFragment.this.mWeightTrendChartViewHolder != null) {
                        WeightBodyDataFragment.this.mWeightTrendChartViewHolder.a(new ArrayList());
                        return;
                    }
                    return;
                }
                LogUtil.h(WeightBodyDataFragment.TAG, "other msg");
                return;
            }
            if (koq.e(message.obj, Pair.class)) {
                arrayList = (ArrayList) message.obj;
            } else {
                LogUtil.h(WeightBodyDataFragment.TAG, "msg.obj is not instanceof List Pair");
                arrayList = new ArrayList();
            }
            if (WeightBodyDataFragment.this.mWeightTrendChartViewHolder != null) {
                WeightBodyDataFragment.this.mWeightTrendChartViewHolder.a(arrayList);
            }
        }
    }

    protected String getBodyDataKey() {
        return "bodyWeight";
    }

    protected String getAIBodyShapeImageKey() {
        return "";
    }
}

package com.huawei.health.section.section;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.widget.TextViewCompat;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.bzs;
import defpackage.fbl;
import defpackage.fbp;
import defpackage.koq;
import defpackage.mpj;
import defpackage.now;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class MeasureCardItemHolder {
    private static final String TAG = "CardHolder";
    private static final float Y_AXIS_MAXIMUM_OFFSET = 10.0f;
    private static final float Y_AXIS_MINIMUM_OFFSET = 10.0f;
    private ImageView mCenterArrow;
    protected HwHealthLineChart mChart;
    protected final LinearLayout mChartContainer;
    protected HwHealthLineDataSet mChartDataSet;
    private View mContentRootLayout;
    protected final Context mContext;
    private boolean mHasGotoDetailPage = false;
    protected final ImageView mIcon;
    protected View mItemView;
    protected HwHealthLineDataSet mMaskDataSet;
    private ImageView mNewDataDot;
    private View.OnClickListener mOnClickListener;
    protected final HealthTextView mPrimaryTipTextView;
    protected final HealthTextView mSecondaryTipTextView;
    private View mTimeAndArrowArea;
    protected final HealthTextView mTimeTextView;
    protected String mTitle;
    private LinearLayout mTitleDotArea;
    protected HealthTextView mTitleTextView;

    public enum MeasureType {
        ITEM_TYPE_ECG_NORMAL,
        ITEM_TYPE_ECG_ARRHYTHMIA,
        ITEM_TYPE_ECG_VASCULAR
    }

    protected abstract void bindDesc(fbp fbpVar);

    protected Pair<Integer, Integer> getChartGradientColors() {
        return null;
    }

    protected int getChartLineColor(Context context) {
        return 0;
    }

    protected abstract long getNewDataTimeStamp(fbp fbpVar);

    protected void preBindChart(fbp fbpVar) {
    }

    protected abstract void syncEcgData();

    public MeasureCardItemHolder(View view) {
        this.mItemView = view;
        Context context = view.getContext();
        this.mContext = context;
        this.mIcon = (ImageView) view.findViewById(R.id.icon);
        this.mTimeTextView = (HealthTextView) view.findViewById(R.id.time_text);
        this.mPrimaryTipTextView = (HealthTextView) view.findViewById(R.id.primary_tip);
        this.mSecondaryTipTextView = (HealthTextView) view.findViewById(R.id.secondary_tip);
        this.mTitleDotArea = (LinearLayout) view.findViewById(R.id.title_dot_area);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.card_line_chart_container);
        this.mChartContainer = linearLayout;
        if (LanguageUtil.bc(context)) {
            linearLayout.setRotationY(180.0f);
        }
        this.mTimeAndArrowArea = view.findViewById(R.id.time_arrow_area);
        this.mCenterArrow = (ImageView) view.findViewById(R.id.center_arrow);
        this.mNewDataDot = (ImageView) view.findViewById(R.id.new_data_dot);
        this.mContentRootLayout = view.findViewById(R.id.root_relative_layout);
        addTitleTextView();
    }

    private void addTitleTextView() {
        if (this.mTitleTextView != null) {
            return;
        }
        this.mTitleDotArea.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.section.section.MeasureCardItemHolder.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = MeasureCardItemHolder.this.mNewDataDot.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    MeasureCardItemHolder.this.mTitleTextView = new HealthTextView(MeasureCardItemHolder.this.mContext);
                    nsy.cMr_(MeasureCardItemHolder.this.mTitleTextView, MeasureCardItemHolder.this.mTitle);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    int width = MeasureCardItemHolder.this.mTitleDotArea.getWidth();
                    int width2 = MeasureCardItemHolder.this.mNewDataDot.getWidth();
                    int marginStart = marginLayoutParams.getMarginStart();
                    int marginEnd = marginLayoutParams.getMarginEnd();
                    int dimensionPixelSize = MeasureCardItemHolder.this.mContext.getResources().getDimensionPixelSize(R.dimen._2131365062_res_0x7f0a0cc6);
                    MeasureCardItemHolder.this.mTitleTextView.setTextSize(0, dimensionPixelSize);
                    MeasureCardItemHolder.this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                    MeasureCardItemHolder.this.mTitleTextView.setTextColor(ContextCompat.getColor(MeasureCardItemHolder.this.mContext, R.color._2131299236_res_0x7f090ba4));
                    MeasureCardItemHolder.this.mTitleTextView.setMaxWidth(((width - width2) - marginStart) - marginEnd);
                    MeasureCardItemHolder.this.mTitleTextView.setMaxLines(1);
                    MeasureCardItemHolder.this.mTitleTextView.setGravity(16);
                    MeasureCardItemHolder.this.mTitleTextView.setTextAlignment(1);
                    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(MeasureCardItemHolder.this.mTitleTextView, MeasureCardItemHolder.this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363229_res_0x7f0a059d), dimensionPixelSize, MeasureCardItemHolder.this.mContext.getResources().getDimensionPixelSize(R.dimen._2131365052_res_0x7f0a0cbc), 0);
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    MeasureCardItemHolder.this.mTitleTextView.setLayoutParams(layoutParams2);
                    MeasureCardItemHolder.this.mTitleDotArea.addView(MeasureCardItemHolder.this.mTitleTextView, 0);
                    MeasureCardItemHolder.this.mTitleDotArea.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    protected void showNewDataDot() {
        LogUtil.a(TAG, "showNewDataDot");
        this.mNewDataDot.setVisibility(0);
        setNewDataDotExists(true);
    }

    protected void hideNewDataDot() {
        LogUtil.a(TAG, "hideNewDataDot");
        this.mNewDataDot.setVisibility(8);
        setNewDataDotExists(false);
    }

    public void bindAll(fbp fbpVar) {
        bindClickEvent(fbpVar);
        bindDesc(fbpVar);
        bindNewDataDot(fbpVar);
        bindChart(fbpVar);
    }

    private void setNewDataDotExists(boolean z) {
        SharedPreferenceManager.e(Integer.toString(10052), getNewDataDotExistsKey(), z);
    }

    private boolean getNewDataDotExists() {
        return SharedPreferenceManager.a(Integer.toString(10052), getNewDataDotExistsKey(), false);
    }

    private long getNewDataDotTimeStamp() {
        return SharedPreferenceManager.b(Integer.toString(10052), getNewDataDotTimeStampKey(), 0L);
    }

    private void setNewDataDotTimeStamp(long j) {
        SharedPreferenceManager.e(Integer.toString(10052), getNewDataDotTimeStampKey(), j);
    }

    private void bindNewDataDot(fbp fbpVar) {
        long newDataTimeStamp = getNewDataTimeStamp(fbpVar);
        LogUtil.a(TAG, "bindNewDataDot newDataTimeStamp ", Long.valueOf(newDataTimeStamp));
        long newDataDotTimeStamp = getNewDataDotTimeStamp();
        LogUtil.a(TAG, "bindNewDataDot lastDataTimeStamp ", Long.valueOf(newDataDotTimeStamp));
        boolean newDataDotExists = getNewDataDotExists();
        LogUtil.a(TAG, "bindNewDataDot isNewDataDotExists ", Boolean.valueOf(newDataDotExists));
        if (newDataDotExists || (newDataTimeStamp != 0 && newDataTimeStamp > newDataDotTimeStamp)) {
            showNewDataDot();
            if (newDataTimeStamp != 0 && newDataTimeStamp > newDataDotTimeStamp) {
                syncEcgData();
            }
        }
        if ((newDataTimeStamp == 0 || this.mHasGotoDetailPage || newDataTimeStamp == newDataDotTimeStamp) && !newDataDotExists) {
            hideNewDataDot();
        }
        if (newDataTimeStamp != newDataDotTimeStamp) {
            setNewDataDotTimeStamp(newDataTimeStamp);
        }
        this.mHasGotoDetailPage = false;
    }

    private void bindClickEvent(final fbp fbpVar) {
        if (this.mOnClickListener == null) {
            this.mOnClickListener = new View.OnClickListener() { // from class: fbu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MeasureCardItemHolder.this.m433x5b50a07b(fbpVar, view);
                }
            };
        }
        this.mItemView.setOnClickListener(this.mOnClickListener);
    }

    /* renamed from: lambda$bindClickEvent$1$com-huawei-health-section-section-MeasureCardItemHolder, reason: not valid java name */
    public /* synthetic */ void m433x5b50a07b(fbp fbpVar, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        final String j = fbpVar.j();
        LogUtil.a(TAG, "goto H5: pkgH5Name = ", j);
        final H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(fbpVar.c());
        if (TextUtils.isEmpty(j)) {
            LogUtil.b(TAG, "goto H5 failed: pkgH5Name = ", j);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (j.contains("com.huawei.health.h5.ppg")) {
            mpj.a().launchActivity(this.mContext, new Intent(), new AppBundleLauncher.InstallCallback() { // from class: fbs
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return MeasureCardItemHolder.this.m432x35bc977a(j, builder, context, intent);
                }
            });
        } else {
            bzs.e().loadH5ProApp(this.mContext, j, builder);
        }
        this.mHasGotoDetailPage = true;
        hideNewDataDot();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$bindClickEvent$0$com-huawei-health-section-section-MeasureCardItemHolder, reason: not valid java name */
    public /* synthetic */ boolean m432x35bc977a(String str, H5ProLaunchOption.Builder builder, Context context, Intent intent) {
        bzs.e().loadH5ProApp(this.mContext, str, builder);
        return false;
    }

    protected void onDataAvailable(boolean z) {
        adjustArrowPosition(z);
        adjustBottomMargin(z);
        adjustChartVisibility(z);
    }

    private void adjustChartVisibility(boolean z) {
        this.mChartContainer.setVisibility(z ? 0 : 8);
    }

    protected void adjustArrowPosition(boolean z) {
        this.mCenterArrow.setVisibility(z ? 8 : 0);
        this.mTimeAndArrowArea.setVisibility(z ? 0 : 8);
    }

    private void adjustBottomMargin(boolean z) {
        ViewGroup.LayoutParams layoutParams = this.mContentRootLayout.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.bottomMargin = this.mContext.getResources().getDimensionPixelSize(z ? R.dimen._2131362906_res_0x7f0a045a : R.dimen._2131362886_res_0x7f0a0446);
            this.mContentRootLayout.setLayoutParams(marginLayoutParams);
        }
    }

    private String getNewDataDotTimeStampKey() {
        return getTag() + "_newDataDotTimeStamp";
    }

    private String getNewDataDotExistsKey() {
        return getTag() + "_newDataDotExists";
    }

    public void bindChart(fbp fbpVar) {
        List<fbl> f = fbpVar.f();
        if (!fbpVar.g()) {
            f = null;
        }
        refreshChart(f);
    }

    private void refreshDataSet(List<fbl> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        LogUtil.a(getTag(), "initLineChart dataSize = ", Integer.valueOf(size));
        for (int i = 0; i < size; i++) {
            fbl fblVar = list.get(i);
            if (!fblVar.c()) {
                arrayList.add(new HwHealthBaseEntry(i, (float) fblVar.e()));
            }
        }
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, "line brief", "line label", "line unit");
        this.mChartDataSet = hwHealthLineDataSet;
        hwHealthLineDataSet.e(1);
        this.mChartDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: com.huawei.health.section.section.MeasureCardItemHolder.1
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i2, int i3, int i4) {
                return i3 - i2 <= i4;
            }
        });
        this.mChartDataSet.setLineWidth(this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362855_res_0x7f0a0427));
        this.mChartDataSet.setColor(getChartLineColor(this.mContext));
        Pair<Integer, Integer> chartGradientColors = getChartGradientColors();
        if (chartGradientColors != null) {
            this.mChartDataSet.b(chartGradientColors.first.intValue(), chartGradientColors.second.intValue(), true);
        }
        this.mChartDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
    }

    private void refreshMaskDataSet(List<fbl> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        LogUtil.a(getTag(), "initLineChart dataSize = ", Integer.valueOf(size));
        boolean z = false;
        for (int i = 0; i < size; i++) {
            if (list.get(i).c()) {
                arrayList.add(new HwHealthBaseEntry(i, z ? getYMin() : getYMax()));
                z = false;
            } else {
                if (!z && i > 0) {
                    arrayList.set(arrayList.size() - 1, new HwHealthBaseEntry(i - 1, getYMin()));
                }
                z = true;
            }
        }
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, "line brief", "line label", "line unit");
        this.mMaskDataSet = hwHealthLineDataSet;
        hwHealthLineDataSet.e(1);
        this.mMaskDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: com.huawei.health.section.section.MeasureCardItemHolder.5
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i2, int i3, int i4) {
                return true;
            }
        });
        this.mMaskDataSet.setLineWidth(this.mContext.getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c));
        this.mMaskDataSet.setColor(ContextCompat.getColor(this.mContext, R.color._2131299296_res_0x7f090be0));
        this.mMaskDataSet.setDrawFilled(true);
        this.mMaskDataSet.b(ContextCompat.getColor(this.mContext, R.color._2131297318_res_0x7f090426), ContextCompat.getColor(this.mContext, R.color._2131297318_res_0x7f090426), true);
        this.mMaskDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
    }

    protected float getYMin() {
        return this.mChartDataSet.getYMin();
    }

    protected float getYMax() {
        return this.mChartDataSet.getYMax();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: refreshChartData, reason: merged with bridge method [inline-methods] */
    public void m434xf2e32770(List<fbl> list) {
        refreshDataSet(list);
        refreshMaskDataSet(list);
        ArrayList arrayList = new ArrayList();
        HwHealthYAxis axisFirstParty = this.mChart.getAxisFirstParty();
        axisFirstParty.setAxisMinimum(getYMin() - 10.0f);
        axisFirstParty.setAxisMaximum(getYMax() + 10.0f);
        arrayList.add(this.mChartDataSet);
        arrayList.add(this.mMaskDataSet);
        this.mChart.setData(new now(arrayList));
        this.mChart.refresh();
    }

    private void refreshChart(final List<fbl> list) {
        if (koq.b(list)) {
            HwHealthLineChart hwHealthLineChart = this.mChart;
            if (hwHealthLineChart == null) {
                return;
            }
            hwHealthLineChart.setVisibility(8);
            return;
        }
        this.mChartContainer.setVisibility(0);
        HwHealthLineChart hwHealthLineChart2 = this.mChart;
        if (hwHealthLineChart2 == null) {
            HwHealthLineChart hwHealthLineChart3 = (HwHealthLineChart) LayoutInflater.from(this.mContext).inflate(R.layout.measure_card_chart_layout, (ViewGroup) this.mChartContainer, false).findViewById(R.id.linechart);
            this.mChart = hwHealthLineChart3;
            hwHealthLineChart3.setAllowMultiSetDrawFillEnabled(true);
            this.mChart.post(new Runnable() { // from class: fbt
                @Override // java.lang.Runnable
                public final void run() {
                    MeasureCardItemHolder.this.m434xf2e32770(list);
                }
            });
            this.mChart.setTouchEnabled(false);
            this.mChart.getXAxis().setEnabled(false);
            this.mChart.getDescription().setEnabled(false);
            HwHealthYAxis axisFirstParty = this.mChart.getAxisFirstParty();
            axisFirstParty.setDrawLabels(false);
            axisFirstParty.setDrawGridLines(false);
            axisFirstParty.setDrawAxisLine(false);
            this.mChart.disableLabelsForce();
            this.mChartContainer.addView(this.mChart);
            return;
        }
        hwHealthLineChart2.setVisibility(0);
        m434xf2e32770(list);
    }

    void hide() {
        this.mItemView.setVisibility(8);
    }

    void show() {
        this.mItemView.setVisibility(0);
    }

    protected String getTag() {
        return TAG;
    }
}

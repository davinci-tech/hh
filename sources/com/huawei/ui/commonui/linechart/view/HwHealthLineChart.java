package com.huawei.ui.commonui.linechart.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import com.github.mikephil.charting.components.XAxis;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener;
import com.huawei.ui.commonui.linechart.utils.CustomChartTitleBar;
import defpackage.nmr;
import defpackage.nmx;
import defpackage.nng;
import defpackage.nos;
import defpackage.now;
import defpackage.npg;
import defpackage.nrn;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class HwHealthLineChart extends HwHealthBaseLineChart {
    private static final int DATA_SET_SIZE = 2;
    private static final int DP_PER_POINT = 3;
    private static final int FIRST_INDEX = 1;
    private static final float LINE_WIDTH = 0.5f;
    private static final long MIN_DOUBLE_TAP = 2300;
    private static final int START_VALUE = 0;
    private static final String TAG = "HealthChart_HwHealthLineChart";
    private static final float TEXT_SIZE = 10.0f;
    private Context mContext;
    private CustomChartTitleBar mCustomChartTitleBar;
    private DataParser mDataParser;
    private DataParser mDefaultDataParser;
    private long mDoubleTapTimeMills;
    private boolean mIsAllowMultiSetDrawFillEnabled;
    private boolean mIsDisabledLabelsForce;
    private int mLabelLineColor;
    private int mLabelTextColor;
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
    private TimeValueMode mMode;
    private OnDoubleTapListener mOnDoubleTapListener;
    private HwHealthBarLineChartTouchListener.OnScaleListener mOnScaleListener;
    private int mOneScreenShowCounts;

    public interface DataParser {
        void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet);

        int onSamping(List<HwHealthBaseEntry> list, int i, HwHealthLineDataSet hwHealthLineDataSet);

        boolean supportOverlaying();

        boolean supportSamping();
    }

    public interface OnDoubleTapListener {
        boolean onDoubleTap(MotionEvent motionEvent);
    }

    /* loaded from: classes9.dex */
    public interface OnSingleTapConfirmedListener {
        boolean onSingleTapConfirmed(MotionEvent motionEvent);
    }

    public enum TimeValueMode {
        DEFAULT,
        MINUTES
    }

    public HwHealthLineChart(Context context) {
        super(context);
        this.mDoubleTapTimeMills = 0L;
        this.mOneScreenShowCounts = 0;
        this.mIsAllowMultiSetDrawFillEnabled = false;
        this.mMode = TimeValueMode.DEFAULT;
        this.mCustomChartTitleBar = null;
        this.mOnDoubleTapListener = null;
        this.mLabelTextColor = nrn.d(BaseApplication.getContext(), R$color.textColorSecondary);
        this.mLabelLineColor = nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color);
        this.mIsDisabledLabelsForce = false;
        b bVar = new b();
        this.mDefaultDataParser = bVar;
        this.mDataParser = bVar;
        this.mOnScaleListener = new HwHealthBarLineChartTouchListener.OnScaleListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.1
            @Override // com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener.OnScaleListener
            public void onScale(float f) {
                HwHealthLineChart.this.reCalculate((int) ((HwHealthLineChart.this.getWidth() * f) / (HwHealthLineChart.this.mContext.getResources().getDisplayMetrics().density * 3.0f)));
            }
        };
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwHealthLineChart.this.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a(HwHealthLineChart.TAG, "width:", Integer.valueOf(HwHealthLineChart.this.getWidth()));
                        if (HwHealthLineChart.this.getWidth() != 0) {
                            HwHealthLineChart.this.mOneScreenShowCounts = (int) (HwHealthLineChart.this.getWidth() / (HwHealthLineChart.this.mContext.getResources().getDisplayMetrics().density * 3.0f));
                            HwHealthLineChart.this.reCalculate(HwHealthLineChart.this.mOneScreenShowCounts);
                            HwHealthLineChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(HwHealthLineChart.this.mLayoutListener);
                        }
                    }
                });
            }
        };
        initCustomData();
    }

    public HwHealthLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDoubleTapTimeMills = 0L;
        this.mOneScreenShowCounts = 0;
        this.mIsAllowMultiSetDrawFillEnabled = false;
        this.mMode = TimeValueMode.DEFAULT;
        this.mCustomChartTitleBar = null;
        this.mOnDoubleTapListener = null;
        this.mLabelTextColor = nrn.d(BaseApplication.getContext(), R$color.textColorSecondary);
        this.mLabelLineColor = nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color);
        this.mIsDisabledLabelsForce = false;
        b bVar = new b();
        this.mDefaultDataParser = bVar;
        this.mDataParser = bVar;
        this.mOnScaleListener = new HwHealthBarLineChartTouchListener.OnScaleListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.1
            @Override // com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener.OnScaleListener
            public void onScale(float f) {
                HwHealthLineChart.this.reCalculate((int) ((HwHealthLineChart.this.getWidth() * f) / (HwHealthLineChart.this.mContext.getResources().getDisplayMetrics().density * 3.0f)));
            }
        };
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwHealthLineChart.this.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a(HwHealthLineChart.TAG, "width:", Integer.valueOf(HwHealthLineChart.this.getWidth()));
                        if (HwHealthLineChart.this.getWidth() != 0) {
                            HwHealthLineChart.this.mOneScreenShowCounts = (int) (HwHealthLineChart.this.getWidth() / (HwHealthLineChart.this.mContext.getResources().getDisplayMetrics().density * 3.0f));
                            HwHealthLineChart.this.reCalculate(HwHealthLineChart.this.mOneScreenShowCounts);
                            HwHealthLineChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(HwHealthLineChart.this.mLayoutListener);
                        }
                    }
                });
            }
        };
        initCustomData();
    }

    public HwHealthLineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDoubleTapTimeMills = 0L;
        this.mOneScreenShowCounts = 0;
        this.mIsAllowMultiSetDrawFillEnabled = false;
        this.mMode = TimeValueMode.DEFAULT;
        this.mCustomChartTitleBar = null;
        this.mOnDoubleTapListener = null;
        this.mLabelTextColor = nrn.d(BaseApplication.getContext(), R$color.textColorSecondary);
        this.mLabelLineColor = nrn.d(BaseApplication.getContext(), R$color.health_chart_default_line_color);
        this.mIsDisabledLabelsForce = false;
        b bVar = new b();
        this.mDefaultDataParser = bVar;
        this.mDataParser = bVar;
        this.mOnScaleListener = new HwHealthBarLineChartTouchListener.OnScaleListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.1
            @Override // com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener.OnScaleListener
            public void onScale(float f) {
                HwHealthLineChart.this.reCalculate((int) ((HwHealthLineChart.this.getWidth() * f) / (HwHealthLineChart.this.mContext.getResources().getDisplayMetrics().density * 3.0f)));
            }
        };
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwHealthLineChart.this.post(new Runnable() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a(HwHealthLineChart.TAG, "width:", Integer.valueOf(HwHealthLineChart.this.getWidth()));
                        if (HwHealthLineChart.this.getWidth() != 0) {
                            HwHealthLineChart.this.mOneScreenShowCounts = (int) (HwHealthLineChart.this.getWidth() / (HwHealthLineChart.this.mContext.getResources().getDisplayMetrics().density * 3.0f));
                            HwHealthLineChart.this.reCalculate(HwHealthLineChart.this.mOneScreenShowCounts);
                            HwHealthLineChart.this.getViewTreeObserver().removeOnGlobalLayoutListener(HwHealthLineChart.this.mLayoutListener);
                        }
                    }
                });
            }
        };
        initCustomData();
    }

    private void initCustomData() {
        Context context = getContext();
        this.mContext = context;
        LogUtil.a(TAG, "mContext = ", context);
        setLayerType(1, null);
        this.mLegend.setXOffset(0.0f);
        setDrawGridBackground(true);
        setTouchEnabled(true);
        setDragEnabled(true);
        setScaleEnabled(true);
        setScaleYEnabled(false);
        setDoubleTapToZoomEnabled(false);
        setScaletMaxima(4.0f);
        setGridBackgroundColor(0);
        nos.a(this.mContext, getLegend());
        this.mLegendRenderer = new npg(this.mContext, this, this.mViewPortHandler, this.mLegend);
        setDrawBorders(false);
        getDescription().setEnabled(true);
        getDescription().setText(getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_time));
        if (!nng.d(this.mContext)) {
            getDescription().setTextAlign(Paint.Align.RIGHT);
        } else {
            getDescription().setTextAlign(Paint.Align.LEFT);
        }
        getDescription().setTextSize(10.0f);
        getDescription().setTextColor(this.mLabelTextColor);
        setExtraTopOffset(13.0f);
        setNeedListener();
        if (this.mChartTouchListener instanceof HwHealthBarLineChartTouchListener) {
            ((HwHealthBarLineChartTouchListener) this.mChartTouchListener).a(this.mOnScaleListener);
        }
        customSettingAxisY();
        XAxis xAxis = getXAxis();
        xAxis.setAxisLineWidth(0.5f);
        xAxis.setAxisLineColor(-7829368);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(this.mLabelTextColor);
        xAxis.setTextSize(10.0f);
        if (this.mMode == TimeValueMode.MINUTES) {
            xAxis.setValueFormatter(new nmx());
        } else {
            xAxis.setValueFormatter(new nmr());
        }
        xAxis.setAxisMinimum(0.0f);
        HwHealthMarkerView hwHealthMarkerView = new HwHealthMarkerView(this.mContext, R.layout.custom_marker_view, this);
        hwHealthMarkerView.setChartView(this);
        setMarker(hwHealthMarkerView);
        setVisibility(8);
        invalidate();
    }

    private void setNeedListener() {
        setMaxHighlightDistance(1000.0f);
        final GestureDetector gestureDetector = new GestureDetector(this.mContext, new GestureDetector.SimpleOnGestureListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.5
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (HwHealthLineChart.this.mOnDoubleTapListener == null) {
                    return false;
                }
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (Math.abs(elapsedRealtime - HwHealthLineChart.this.mDoubleTapTimeMills) < HwHealthLineChart.MIN_DOUBLE_TAP) {
                    return false;
                }
                HwHealthLineChart.this.mDoubleTapTimeMills = elapsedRealtime;
                return HwHealthLineChart.this.mOnDoubleTapListener.onDoubleTap(motionEvent);
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.ui.commonui.linechart.view.HwHealthLineChart.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!HwHealthLineChart.this.mTouchEnabled) {
                    gestureDetector.onTouchEvent(motionEvent);
                    return false;
                }
                return HwHealthLineChart.this.onTouchEvent(motionEvent);
            }
        });
    }

    private void customSettingAxisY() {
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisFirstParty.setAxisLineWidth(0.5f);
        this.mAxisFirstParty.setAxisLineColor(Color.argb(127, 0, 0, 0));
        this.mAxisFirstParty.setDrawGridLines(true);
        this.mAxisFirstParty.setGridColor(this.mLabelLineColor);
        this.mAxisFirstParty.setGridLineWidth(0.5f);
        this.mAxisFirstParty.setTextColor(this.mLabelTextColor);
        this.mAxisFirstParty.setTextSize(10.0f);
        this.mAxisFirstParty.setLabelCount(5, true);
        this.mAxisSecondParty.setEnabled(false);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisSecondParty.setAxisLineWidth(0.5f);
        this.mAxisSecondParty.setAxisLineColor(-7829368);
        this.mAxisSecondParty.setDrawGridLines(false);
        this.mAxisSecondParty.setTextColor(this.mLabelTextColor);
        this.mAxisSecondParty.setTextSize(10.0f);
        this.mAxisSecondParty.setLabelCount(5, true);
    }

    private void fillOriginalDataOne(int i) {
        if (i == 0) {
            LogUtil.h(TAG, "fillOriginalData showCounts zero,why?,return");
            return;
        }
        List<T> dataSets = ((now) this.mData).getDataSets();
        if (dataSets.size() != 1) {
            LogUtil.h(TAG, "only support 1 lines");
            return;
        }
        List<HwHealthBaseEntry> acquireEntryVals = ((IHwHealthLineDataSet) dataSets.get(0)).acquireEntryVals();
        ArrayList arrayList = new ArrayList();
        for (HwHealthBaseEntry hwHealthBaseEntry : acquireEntryVals) {
            if (hwHealthBaseEntry != null) {
                arrayList.add(new HwHealthBaseEntry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY(), hwHealthBaseEntry.getData()));
            }
        }
        if (this.mDataParser.supportOverlaying() && (dataSets.get(0) instanceof HwHealthLineDataSet)) {
            this.mDataParser.onOverlaying(arrayList, (HwHealthLineDataSet) dataSets.get(0));
            ((HwHealthLineDataSet) dataSets.get(0)).j();
        }
        if (this.mDataParser.supportSamping() && (dataSets.get(0) instanceof HwHealthLineDataSet)) {
            ((HwHealthLineDataSet) dataSets.get(0)).e(this.mDataParser.onSamping(arrayList, i, (HwHealthLineDataSet) dataSets.get(0)));
        }
        if (dataSets.get(0) instanceof HwHealthLineDataSet) {
            HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) dataSets.get(0);
            hwHealthLineDataSet.setValues(arrayList);
            hwHealthLineDataSet.makeDataCalculated(true);
        }
    }

    private void fillOriginalDataTwo(int i) {
        List<T> dataSets = ((now) this.mData).getDataSets();
        if (dataSets.size() != 2) {
            LogUtil.h(TAG, "only support 2 lines");
            return;
        }
        List<HwHealthBaseEntry> acquireEntryVals = ((IHwHealthLineDataSet) dataSets.get(0)).acquireEntryVals();
        List<HwHealthBaseEntry> acquireEntryVals2 = ((IHwHealthLineDataSet) dataSets.get(1)).acquireEntryVals();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (HwHealthBaseEntry hwHealthBaseEntry : acquireEntryVals) {
            arrayList.add(new HwHealthBaseEntry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY(), hwHealthBaseEntry.getData()));
        }
        for (HwHealthBaseEntry hwHealthBaseEntry2 : acquireEntryVals2) {
            arrayList2.add(new HwHealthBaseEntry(hwHealthBaseEntry2.getX(), hwHealthBaseEntry2.getY(), hwHealthBaseEntry2.getData()));
        }
        if (this.mDataParser.supportOverlaying() && (dataSets.get(0) instanceof HwHealthLineDataSet) && (dataSets.get(1) instanceof HwHealthLineDataSet)) {
            this.mDataParser.onOverlaying(arrayList, (HwHealthLineDataSet) dataSets.get(0));
            this.mDataParser.onOverlaying(arrayList2, (HwHealthLineDataSet) dataSets.get(1));
            ((HwHealthLineDataSet) dataSets.get(0)).j();
            ((HwHealthLineDataSet) dataSets.get(1)).j();
        }
        if (this.mDataParser.supportSamping() && (dataSets.get(0) instanceof HwHealthLineDataSet) && (dataSets.get(1) instanceof HwHealthLineDataSet)) {
            int onSamping = this.mDataParser.onSamping(arrayList, i, (HwHealthLineDataSet) dataSets.get(0));
            int onSamping2 = this.mDataParser.onSamping(arrayList2, i, (HwHealthLineDataSet) dataSets.get(1));
            ((HwHealthLineDataSet) dataSets.get(0)).e(onSamping);
            ((HwHealthLineDataSet) dataSets.get(1)).e(onSamping2);
        }
        if ((dataSets.get(0) instanceof HwHealthLineDataSet) && (dataSets.get(1) instanceof HwHealthLineDataSet)) {
            HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) dataSets.get(0);
            HwHealthLineDataSet hwHealthLineDataSet2 = (HwHealthLineDataSet) dataSets.get(1);
            hwHealthLineDataSet.setValues(arrayList);
            hwHealthLineDataSet2.setValues(arrayList2);
            hwHealthLineDataSet.makeDataCalculated(true);
            hwHealthLineDataSet2.makeDataCalculated(true);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public void refresh() {
        if (getWidth() == 0) {
            setVisibility(0);
            getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
            return;
        }
        int i = this.mOneScreenShowCounts;
        if (i == 0) {
            LogUtil.h(TAG, "mOneScreenShowCounts null,width not null");
        } else {
            reCalculate(i);
        }
    }

    public void reCalculate(int i) {
        fillOriginalData(i);
        notifyDataSetChanged();
        invalidate();
        if (this.mData == 0 || ((now) this.mData).getDataSets() == null) {
            return;
        }
        for (T t : ((now) this.mData).getDataSets()) {
            if (t != null) {
                t.checkIfNeedReload();
            }
        }
    }

    public void setAllowMultiSetDrawFillEnabled(boolean z) {
        this.mIsAllowMultiSetDrawFillEnabled = z;
    }

    private void fillOriginalData(int i) {
        if (this.mData == 0) {
            return;
        }
        customAxisShow();
        List<IHwHealthLineDataSet> dataSets = ((now) this.mData).getDataSets();
        LogUtil.a(TAG, "fillOriginalData mData size = ", Integer.valueOf(dataSets.size()), " dataCounts ", Integer.valueOf(i));
        if (dataSets.size() == 0) {
            return;
        }
        boolean z = true;
        for (IHwHealthLineDataSet iHwHealthLineDataSet : dataSets) {
            if (iHwHealthLineDataSet != null) {
                if (z) {
                    LogUtil.a(TAG, "firData setDrawFilled true");
                    iHwHealthLineDataSet.setDrawFilled(true);
                    z = false;
                } else if (!this.mIsAllowMultiSetDrawFillEnabled) {
                    LogUtil.a(TAG, "not firData setDrawFilled false");
                    iHwHealthLineDataSet.setDrawFilled(false);
                }
            }
        }
        setFillOriginalData(dataSets, i);
        if (this.mCustomChartTitleBar == null) {
            return;
        }
        List<IHwHealthLineDataSet> dataSets2 = ((now) this.mData).getDataSets();
        if (dataSets2 == null) {
            LogUtil.h(TAG, "setTitle not find data,lineDataSets size zero,return");
        } else {
            setCustomCharBar(dataSets2, "");
        }
    }

    public void customAxisShow() {
        List<T> dataSets = ((now) this.mData).getDataSets();
        if (dataSets.size() == 0) {
            return;
        }
        this.mAxisFirstParty.setEnabled(true);
        this.mAxisSecondParty.setEnabled(true);
        this.mAxisThirdParty.setEnabled(true);
        this.mAxisFirstParty.setDrawAxisLine(false);
        this.mAxisSecondParty.setDrawAxisLine(false);
        this.mAxisThirdParty.setDrawAxisLine(false);
        if (this.mIsDisabledLabelsForce) {
            this.mAxisFirstParty.setDrawLabels(false);
            this.mAxisSecondParty.setDrawLabels(false);
            this.mAxisThirdParty.setDrawLabels(false);
            return;
        }
        if (dataSets.size() == 1) {
            this.mAxisFirstParty.setDrawLabels(true);
            this.mAxisSecondParty.setDrawLabels(false);
            this.mAxisThirdParty.setDrawLabels(false);
        }
        if (dataSets.size() == 2) {
            this.mAxisFirstParty.setDrawLabels(true);
            this.mAxisSecondParty.setDrawLabels(true);
            this.mAxisThirdParty.setDrawLabels(false);
        }
        if (dataSets.size() == 3) {
            this.mAxisFirstParty.setDrawLabels(false);
            this.mAxisSecondParty.setDrawLabels(false);
            this.mAxisThirdParty.setDrawLabels(false);
        }
    }

    private void fillOriginalDataThree(int i) {
        List<IHwHealthLineDataSet> dataSets = ((now) this.mData).getDataSets();
        if (dataSets.size() != 3) {
            LogUtil.h(TAG, "only support 3 lines");
            return;
        }
        List<HwHealthBaseEntry> acquireEntryVals = dataSets.get(0).acquireEntryVals();
        List<HwHealthBaseEntry> acquireEntryVals2 = dataSets.get(1).acquireEntryVals();
        List<HwHealthBaseEntry> acquireEntryVals3 = dataSets.get(2).acquireEntryVals();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (HwHealthBaseEntry hwHealthBaseEntry : acquireEntryVals) {
            if (hwHealthBaseEntry != null) {
                arrayList.add(new HwHealthBaseEntry(hwHealthBaseEntry.getX(), hwHealthBaseEntry.getY(), hwHealthBaseEntry.getData()));
            }
        }
        for (HwHealthBaseEntry hwHealthBaseEntry2 : acquireEntryVals2) {
            if (hwHealthBaseEntry2 != null) {
                arrayList2.add(new HwHealthBaseEntry(hwHealthBaseEntry2.getX(), hwHealthBaseEntry2.getY(), hwHealthBaseEntry2.getData()));
            }
        }
        for (HwHealthBaseEntry hwHealthBaseEntry3 : acquireEntryVals3) {
            if (hwHealthBaseEntry3 != null) {
                arrayList3.add(new HwHealthBaseEntry(hwHealthBaseEntry3.getX(), hwHealthBaseEntry3.getY(), hwHealthBaseEntry3.getData()));
            }
        }
        if (this.mDataParser.supportOverlaying() && (dataSets.get(0) instanceof HwHealthLineDataSet) && (dataSets.get(1) instanceof HwHealthLineDataSet) && (dataSets.get(2) instanceof HwHealthLineDataSet)) {
            this.mDataParser.onOverlaying(arrayList, (HwHealthLineDataSet) dataSets.get(0));
            this.mDataParser.onOverlaying(arrayList2, (HwHealthLineDataSet) dataSets.get(1));
            this.mDataParser.onOverlaying(arrayList3, (HwHealthLineDataSet) dataSets.get(2));
            ((HwHealthLineDataSet) dataSets.get(0)).j();
            ((HwHealthLineDataSet) dataSets.get(1)).j();
            ((HwHealthLineDataSet) dataSets.get(2)).j();
        }
        setMakeDataCalculated(dataSets, arrayList, arrayList2, arrayList3, i);
    }

    private void setFillOriginalData(List<IHwHealthLineDataSet> list, int i) {
        if (list.size() == 2) {
            fillOriginalDataTwo(i);
        } else if (list.size() == 1) {
            fillOriginalDataOne(i);
        } else if (list.size() == 3) {
            fillOriginalDataThree(i);
        }
    }

    private void setCustomCharBar(List<IHwHealthLineDataSet> list, String str) {
        int size = list.size();
        IHwHealthLineDataSet iHwHealthLineDataSet = list.get(0);
        IHwHealthLineDataSet iHwHealthLineDataSet2 = list.get(1);
        if (size != 1) {
            if (size != 2) {
                if (size == 3) {
                    String string = this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_title_three);
                    IHwHealthLineDataSet iHwHealthLineDataSet3 = list.get(2);
                    if ((iHwHealthLineDataSet instanceof HwHealthLineDataSet) && (iHwHealthLineDataSet2 instanceof HwHealthLineDataSet) && (iHwHealthLineDataSet3 instanceof HwHealthLineDataSet)) {
                        str = str + String.format(Locale.ENGLISH, string, ((HwHealthLineDataSet) iHwHealthLineDataSet).c().d(), ((HwHealthLineDataSet) iHwHealthLineDataSet2).c().d(), ((HwHealthLineDataSet) iHwHealthLineDataSet3).c().d());
                    }
                }
            } else if ((iHwHealthLineDataSet instanceof HwHealthLineDataSet) && (iHwHealthLineDataSet2 instanceof HwHealthLineDataSet)) {
                str = str + String.format(Locale.ENGLISH, this.mContext.getResources().getString(R$string.IDS_hwh_motiontrack_detail_chart_title_two), ((HwHealthLineDataSet) iHwHealthLineDataSet).c().d(), ((HwHealthLineDataSet) iHwHealthLineDataSet2).c().d());
            }
        } else if (iHwHealthLineDataSet instanceof HwHealthLineDataSet) {
            str = str + ((HwHealthLineDataSet) iHwHealthLineDataSet).c().d();
        }
        this.mCustomChartTitleBar.setTitle(str);
    }

    private void setMakeDataCalculated(List<IHwHealthLineDataSet> list, List<HwHealthBaseEntry> list2, List<HwHealthBaseEntry> list3, List<HwHealthBaseEntry> list4, int i) {
        if (this.mDataParser.supportSamping() && (list.get(0) instanceof HwHealthLineDataSet) && (list.get(1) instanceof HwHealthLineDataSet) && (list.get(2) instanceof HwHealthLineDataSet)) {
            int onSamping = this.mDataParser.onSamping(list2, i, (HwHealthLineDataSet) list.get(0));
            int onSamping2 = this.mDataParser.onSamping(list3, i, (HwHealthLineDataSet) list.get(1));
            int onSamping3 = this.mDataParser.onSamping(list4, i, (HwHealthLineDataSet) list.get(2));
            ((HwHealthLineDataSet) list.get(0)).e(onSamping);
            ((HwHealthLineDataSet) list.get(1)).e(onSamping2);
            ((HwHealthLineDataSet) list.get(2)).e(onSamping3);
        }
        if ((list.get(0) instanceof HwHealthLineDataSet) && (list.get(1) instanceof HwHealthLineDataSet) && (list.get(2) instanceof HwHealthLineDataSet)) {
            HwHealthLineDataSet hwHealthLineDataSet = (HwHealthLineDataSet) list.get(0);
            HwHealthLineDataSet hwHealthLineDataSet2 = (HwHealthLineDataSet) list.get(1);
            HwHealthLineDataSet hwHealthLineDataSet3 = (HwHealthLineDataSet) list.get(2);
            hwHealthLineDataSet.setValues(list2);
            hwHealthLineDataSet2.setValues(list3);
            hwHealthLineDataSet3.setValues(list4);
            hwHealthLineDataSet.makeDataCalculated(true);
            hwHealthLineDataSet2.makeDataCalculated(true);
            hwHealthLineDataSet3.makeDataCalculated(true);
        }
    }

    public void disableLabelsForce() {
        this.mIsDisabledLabelsForce = true;
    }

    public void setLabelColor(int i) {
        this.mLabelTextColor = this.mContext.getResources().getColor(R$color.textColorTertiary);
        getDescription().setTextColor(this.mLabelTextColor);
        getXAxis().setTextColor(this.mLabelTextColor);
        this.mAxisFirstParty.setTextColor(this.mLabelTextColor);
        this.mAxisSecondParty.setTextColor(this.mLabelTextColor);
        this.mLegend.setTextColor(this.mLabelTextColor);
        this.mLabelLineColor = Color.argb(51, Color.red(i), Color.green(i), Color.blue(i));
        this.mAxisFirstParty.setGridColor(this.mLabelLineColor);
    }

    public void injectDataParser(DataParser dataParser) {
        if (dataParser == null) {
            this.mDataParser = this.mDefaultDataParser;
        } else {
            this.mDataParser = dataParser;
        }
    }

    static class b implements DataParser {
        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public void onOverlaying(List<HwHealthBaseEntry> list, HwHealthLineDataSet hwHealthLineDataSet) {
        }

        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public int onSamping(List<HwHealthBaseEntry> list, int i, HwHealthLineDataSet hwHealthLineDataSet) {
            return 0;
        }

        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public boolean supportOverlaying() {
            return false;
        }

        @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineChart.DataParser
        public boolean supportSamping() {
            return false;
        }

        private b() {
        }
    }
}

package com.huawei.ui.commonui.linechart.common;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.HwEntrys;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineData;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun;
import defpackage.jdl;
import defpackage.jec;
import defpackage.koq;
import defpackage.nmy;
import defpackage.nng;
import defpackage.nnh;
import defpackage.nni;
import defpackage.nnk;
import defpackage.nnl;
import defpackage.nnm;
import defpackage.nnn;
import defpackage.nno;
import defpackage.nnp;
import defpackage.nnq;
import defpackage.nnt;
import defpackage.nnu;
import defpackage.nok;
import defpackage.nol;
import defpackage.nom;
import defpackage.nop;
import defpackage.nsj;
import defpackage.skh;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public abstract class HwHealthBaseScrollBarLineChart<T extends HwHealthBaseBarLineData<? extends IHwHealthBarLineDataSet<? extends HwHealthBaseEntry>>> extends HwHealthBaseBarLineChart<T> {
    private static final float DAY_ALLOWABLE_ERROR = 5.0f;
    private static final int HALF_HOUR = 30;
    private static final long MINUTE_TO_MILLIS = 60000;
    private static final String TAG = "HealthChart_HwHealthBaseScrollBarLineChart";
    protected nol mAnimatorMarkerViewChange;
    protected nol mAnimatorPagerChange;
    private boolean mCanScrollNewerPager;
    private boolean mCanScrollOlderPager;
    protected ChartShowMode mChartShowMode;
    private AnimateTimeComputer mDefaultAnimateTimeComputer;
    private AnimateValueTransfer mDefaultAnimateValueTransfer;
    private long mDefaultTimeMillis;
    private HwHealthAxisValueFormatter mHwHealthAxisValueFormatter;
    protected boolean mIsDisableDrawSelf;
    private boolean mIsMoveToLastDataStamp;
    private boolean mIsRefreshToExpectedTime;
    private HwHealthBaseScrollBarLineChart<T>.a mOnScrollPagerAnimateMgr;
    private List<OnXRangeSet> mOnXRangeSets;
    private PagerNoMoreListener mPagerNoMoreListener;
    protected float mRangeBoardMax;
    protected float mRangeBoardMin;
    protected ScrollAdapterInterface mScrollAdapter;
    private int mShowRange;
    private UnixChartType mUnixChartType;

    public interface AnimateTimeComputer {
        int computeTime(int i);
    }

    public interface AnimateValueTransfer {
        float transferValue(float f);
    }

    public enum ChartShowMode {
        NORMAL,
        SCROLL_MODE,
        SCROLL_SCALE_MODE
    }

    public interface DataRatioProvider {
        float computeRatio(int i);
    }

    public static abstract class HwHealthAxisValueFormatter implements IAxisValueFormatter {

        public enum HealthDeviceKindType {
            HDK_UNKNOWN,
            HDK_BLOOD_SUGAR,
            HDK_ACTIVE_HOUR,
            HDK_TEMPERATURE
        }

        public abstract void enableMarkerViewShowRange(boolean z);

        public abstract String getFormattedValueForMarkerView(float f, AxisBase axisBase);

        public abstract String getRangeText(double d, double d2);

        public abstract void setHealthType(HealthDeviceKindType healthDeviceKindType);
    }

    public interface OnDataFillCallback {
        void onResult();
    }

    public interface OnXRangeSet {
        void onRangeShow(int i, int i2);
    }

    public interface PagerNoMoreListener {
        void notifyNewerPager(boolean z);

        void notifyOlderPager(boolean z);
    }

    public interface ScrollAdapterInterface {
        public static final int NEW_DATA_FILLED = 1;

        int acquireDefaultStartX();

        int acquireRange();

        int acquireRange(long j);

        int acquireRangeCenterValue(int i);

        int acquireValuePresentRangeMax(int i);

        int acquireValuePresentRangeMin(int i);

        HwHealthAxisValueFormatter acquireXAxisValueFormatter();

        float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2);

        float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2);

        int getFlag();

        void injectStorage(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, IChartStorageHelper iChartStorageHelper, DataInfos dataInfos, HwHealthChartHolder.b bVar);

        void invalidateBuff();

        boolean isTotalClearInShowRange();

        void manageDataSetAsProxy(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, IChartStorageHelper iChartStorageHelper, DataInfos dataInfos, HwHealthChartHolder.b bVar);

        void onRangeShow(int i, int i2);

        List<IChartStorageHelper> parseAssociatedStorageHelper();

        DataInfos queryDataInfos(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet);

        HwHealthChartHolder.b queryShowMode(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet);

        IChartStorageHelper queryStorage(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet);

        boolean querySupportTouchScroll();

        void reverseInjectStorage(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet);

        void setBuffRang(int i);

        void setDefaultTime(long j);

        void setFlag(int i);

        void unmanageDataSetAsProxy(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet);
    }

    public int getAnimateTime() {
        return 1200;
    }

    protected boolean isSupportScaled() {
        return false;
    }

    public HwHealthBaseScrollBarLineChart(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mChartShowMode = ChartShowMode.NORMAL;
        this.mScrollAdapter = null;
        this.mRangeBoardMin = -3.4028235E38f;
        this.mRangeBoardMax = -3.4028235E38f;
        this.mAnimatorPagerChange = new nol();
        this.mAnimatorMarkerViewChange = new nol();
        this.mIsDisableDrawSelf = true;
        this.mOnScrollPagerAnimateMgr = new a();
        this.mUnixChartType = UnixChartType.UNSET;
        this.mShowRange = 0;
        this.mCanScrollOlderPager = false;
        this.mCanScrollNewerPager = false;
        this.mOnXRangeSets = new ArrayList();
        this.mPagerNoMoreListener = null;
        this.mDefaultAnimateValueTransfer = new AnimateValueTransfer() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.1
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateValueTransfer
            public float transferValue(float f2) {
                return f2;
            }
        };
        this.mDefaultAnimateTimeComputer = new AnimateTimeComputer() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.7
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateTimeComputer
            public int computeTime(int i3) {
                return HwHealthBaseScrollBarLineChart.this.getAnimateTime();
            }
        };
        this.mIsMoveToLastDataStamp = false;
        this.mDefaultTimeMillis = System.currentTimeMillis();
    }

    public HwHealthBaseScrollBarLineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChartShowMode = ChartShowMode.NORMAL;
        this.mScrollAdapter = null;
        this.mRangeBoardMin = -3.4028235E38f;
        this.mRangeBoardMax = -3.4028235E38f;
        this.mAnimatorPagerChange = new nol();
        this.mAnimatorMarkerViewChange = new nol();
        this.mIsDisableDrawSelf = true;
        this.mOnScrollPagerAnimateMgr = new a();
        this.mUnixChartType = UnixChartType.UNSET;
        this.mShowRange = 0;
        this.mCanScrollOlderPager = false;
        this.mCanScrollNewerPager = false;
        this.mOnXRangeSets = new ArrayList();
        this.mPagerNoMoreListener = null;
        this.mDefaultAnimateValueTransfer = new AnimateValueTransfer() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.1
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateValueTransfer
            public float transferValue(float f2) {
                return f2;
            }
        };
        this.mDefaultAnimateTimeComputer = new AnimateTimeComputer() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.7
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateTimeComputer
            public int computeTime(int i3) {
                return HwHealthBaseScrollBarLineChart.this.getAnimateTime();
            }
        };
        this.mIsMoveToLastDataStamp = false;
        this.mDefaultTimeMillis = System.currentTimeMillis();
    }

    public HwHealthBaseScrollBarLineChart(Context context) {
        super(context);
        this.mChartShowMode = ChartShowMode.NORMAL;
        this.mScrollAdapter = null;
        this.mRangeBoardMin = -3.4028235E38f;
        this.mRangeBoardMax = -3.4028235E38f;
        this.mAnimatorPagerChange = new nol();
        this.mAnimatorMarkerViewChange = new nol();
        this.mIsDisableDrawSelf = true;
        this.mOnScrollPagerAnimateMgr = new a();
        this.mUnixChartType = UnixChartType.UNSET;
        this.mShowRange = 0;
        this.mCanScrollOlderPager = false;
        this.mCanScrollNewerPager = false;
        this.mOnXRangeSets = new ArrayList();
        this.mPagerNoMoreListener = null;
        this.mDefaultAnimateValueTransfer = new AnimateValueTransfer() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.1
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateValueTransfer
            public float transferValue(float f2) {
                return f2;
            }
        };
        this.mDefaultAnimateTimeComputer = new AnimateTimeComputer() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.7
            @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateTimeComputer
            public int computeTime(int i3) {
                return HwHealthBaseScrollBarLineChart.this.getAnimateTime();
            }
        };
        this.mIsMoveToLastDataStamp = false;
        this.mDefaultTimeMillis = System.currentTimeMillis();
    }

    public boolean isInPagerPosition() {
        return isInPagerPosition((int) this.mRangeBoardMin);
    }

    public boolean isInPagerPosition(int i2) {
        UnixChartType unixChartType = this.mUnixChartType;
        return unixChartType == UnixChartType.DAY ? ((float) Math.abs(i2 - getClosetDayZeroUnixTime((float) i2))) < 5.0f : unixChartType == UnixChartType.WEEK ? ((float) Math.abs(i2 - getClosetWeekZeroUnixTime((float) i2))) < 5.0f : unixChartType == UnixChartType.MONTH ? ((float) Math.abs(i2 - getClosetMonthZeroUnixTime((float) i2))) < 5.0f : unixChartType == UnixChartType.YEAR ? ((float) Math.abs(i2 - getClosetYearZeroUnixTime((float) i2))) < 5.0f : unixChartType == UnixChartType.ALL;
    }

    public void setDefaultTimeMillis(long j) {
        this.mDefaultTimeMillis = j;
    }

    public long getDefaultTimeMillis() {
        return this.mDefaultTimeMillis;
    }

    public void enableScrollMode(UnixChartType unixChartType, DynamicBorderSupportable dynamicBorderSupportable) {
        if (this.mChartShowMode == ChartShowMode.SCROLL_MODE || this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE) {
            return;
        }
        this.mChartShowMode = isSupportScaled() ? ChartShowMode.SCROLL_SCALE_MODE : ChartShowMode.SCROLL_MODE;
        this.mUnixChartType = unixChartType;
        ScrollAdapterInterface b2 = nok.b(unixChartType, this, dynamicBorderSupportable);
        this.mScrollAdapter = b2;
        this.mHwHealthAxisValueFormatter = b2.acquireXAxisValueFormatter();
        this.mXAxis.setValueFormatter(this.mHwHealthAxisValueFormatter);
        if (this.mChartTouchListener instanceof HwHealthBarLineChartTouchListener) {
            if (isSupportScaled()) {
                ((HwHealthBarLineChartTouchListener) this.mChartTouchListener).b();
            } else {
                ((HwHealthBarLineChartTouchListener) this.mChartTouchListener).d();
            }
        }
        nnt nntVar = new nnt(getContext(), this, this.mViewPortHandler, this.mXAxis, this.mFirstAxisTransformer);
        nntVar.a(unixChartType);
        if (this.mXAxisRenderer instanceof HwHealthXAxisRenderer) {
            nntVar.c(((HwHealthXAxisRenderer) this.mXAxisRenderer).b());
        }
        this.mXAxisRenderer = nntVar;
        setShowRange(this.mScrollAdapter.acquireDefaultStartX(), this.mScrollAdapter.acquireRange());
        this.mAxisFirstParty.setAxisMaximum(2000000.0f);
    }

    public ScrollAdapterInterface acquireScrollAdapter() {
        return this.mScrollAdapter;
    }

    public boolean querySupportTouchScroll() {
        return this.mScrollAdapter.querySupportTouchScroll();
    }

    public void setShowRange(int i2, int i3) {
        this.mShowRange = i3;
        if ((this.mChartShowMode == ChartShowMode.SCROLL_MODE || this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE) && (this.mXAxisRenderer instanceof nnt)) {
            ((nnt) this.mXAxisRenderer).d(i2, i3);
        }
    }

    public void show(long j) {
        this.mViewPortHandler.getMatrixTouch().set(new Matrix());
        setShowRange(getCurrentPagerStartX(j), acquireScrollAdapter().acquireRange(j));
    }

    public static abstract class ScrollAdapter implements ScrollAdapterInterface {
        private HwHealthBaseScrollBarLineChart mChart;
        private int mFlag = 0;
        protected b mScrollBuffer = new b();

        protected abstract void fillBuffer(b bVar, List<? extends IHwHealthBarLineDataSet<? extends HwHealthBaseEntry>> list, OnDataFillCallback onDataFillCallback);

        public ScrollAdapter(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
            this.mChart = hwHealthBaseScrollBarLineChart;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
        public void setFlag(int i) {
            this.mFlag = i;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
        public int getFlag() {
            return this.mFlag;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
        public void setBuffRang(int i) {
            b bVar = this.mScrollBuffer;
            if (bVar != null) {
                bVar.e(i);
            }
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
        public void onRangeShow(int i, int i2) {
            if (this.mChart.mIsRefreshToExpectedTime) {
                HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = this.mChart;
                Objects.requireNonNull(hwHealthBaseScrollBarLineChart);
                hwHealthBaseScrollBarLineChart.animateBorderYAuto(new e(), 1);
                this.mChart.mIsRefreshToExpectedTime = false;
            }
            if (this.mScrollBuffer.c(nom.h(i), nom.h(i2))) {
                this.mScrollBuffer.b(nom.h(i), nom.h(i2));
                fillBuffer(this.mScrollBuffer, this.mChart.getData().getDataSets(), new OnDataFillCallback() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapter.1
                    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.OnDataFillCallback
                    public void onResult() {
                        ScrollAdapter.this.mScrollBuffer.d(ScrollAdapter.this.mScrollBuffer.e(), ScrollAdapter.this.mScrollBuffer.d());
                        ScrollAdapter scrollAdapter = ScrollAdapter.this;
                        scrollAdapter.setFlag(scrollAdapter.getFlag() | 1);
                        ScrollAdapter.this.mChart.refresh();
                        HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart2 = ScrollAdapter.this.mChart;
                        HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart3 = ScrollAdapter.this.mChart;
                        Objects.requireNonNull(hwHealthBaseScrollBarLineChart3);
                        hwHealthBaseScrollBarLineChart2.animateBorderYAuto(new e(), 1);
                    }
                });
            }
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
        public boolean isTotalClearInShowRange() {
            float f = this.mChart.mRangeBoardMin;
            float f2 = this.mChart.mRangeBoardMax;
            return (f2 - f) + ((float) (this.mScrollBuffer.d - this.mScrollBuffer.e)) <= ((f2 > ((float) this.mScrollBuffer.d) ? 1 : (f2 == ((float) this.mScrollBuffer.d) ? 0 : -1)) >= 0 ? f2 : (float) this.mScrollBuffer.d) - ((f > ((float) this.mScrollBuffer.e) ? 1 : (f == ((float) this.mScrollBuffer.e) ? 0 : -1)) <= 0 ? f : (float) this.mScrollBuffer.e);
        }

        protected void notifyDataChanged() {
            this.mChart.refresh();
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.ScrollAdapterInterface
        public void invalidateBuff() {
            this.mScrollBuffer.b();
        }

        public class b {
            private int b = 4;
            private int i = 0;
            private int g = 0;
            private int h = 0;
            private int c = 0;
            private int e = 0;
            private int d = 0;
            private boolean j = true;

            public b() {
            }

            public void d(boolean z) {
                this.j = z;
            }

            public void e(int i) {
                if (i >= 0) {
                    this.b = i;
                }
            }

            public void b(int i, int i2) {
                this.i = i;
                this.g = i2;
                int i3 = (i2 - i) * this.b;
                this.h = i - i3;
                this.c = i2 + i3;
            }

            public void d(int i, int i2) {
                this.e = i;
                this.d = i2;
            }

            public int e() {
                if (this.j) {
                    return this.h;
                }
                return this.i;
            }

            public int d() {
                if (this.j) {
                    return this.c;
                }
                return this.g;
            }

            public int c() {
                return this.i;
            }

            public int a() {
                return this.g;
            }

            public boolean c(int i, int i2) {
                if (!ScrollAdapter.this.mChart.mIsDisableDrawSelf) {
                    ScrollAdapter.this.mChart.mIsDisableDrawSelf = true;
                    return true;
                }
                if (!this.j) {
                    return (this.i == i && this.g == i2) ? false : true;
                }
                if (this.b == 0) {
                    return i < this.i || i2 > this.g;
                }
                int i3 = this.i;
                int i4 = this.g;
                int i5 = i4 - i3;
                return i < i3 - i5 || i2 > i4 + i5;
            }

            public void b() {
                this.c = 0;
                this.h = 0;
                this.g = 0;
                this.i = 0;
            }
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float[] fArr = {this.mViewPortHandler.contentLeft(), 0.0f, this.mViewPortHandler.contentRight(), 0.0f};
        this.mFirstAxisTransformer.pixelsToValue(fArr);
        float f2 = fArr[0];
        float f3 = fArr[2];
        this.mFirstAxisTransformer.pointValuesToPixel(new float[]{2.540688E7f, 0.0f});
        if (f2 > f3) {
            f3 = f2;
            f2 = f3;
        }
        if (isScrollChart()) {
            f2 = correctRangeEdgeByDay((int) f2);
            f3 = getRangeBoardMaxNew((int) f2, correctRangeEdgeByDay((int) f3));
        }
        if (this.mChartShowMode == ChartShowMode.SCROLL_MODE) {
            if (Math.abs(f2 - this.mRangeBoardMin) < 1.0E-6d && Math.abs(f3 - this.mRangeBoardMax) < 1.0E-6d && (this.mScrollAdapter.getFlag() & 1) != 1) {
                return;
            } else {
                this.mScrollAdapter.setFlag(this.mScrollAdapter.getFlag() & (-2));
            }
        }
        if (this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE && (!isDayRangeEqual((int) f2, (int) f3) || (this.mScrollAdapter.getFlag() & 1) == 1)) {
            this.mScrollAdapter.setFlag(this.mScrollAdapter.getFlag() & (-2));
        }
        this.mRangeBoardMin = f2;
        this.mRangeBoardMax = f3;
        notifyPager();
        onXRangeSetCallBack();
    }

    private void onXRangeSetCallBack() {
        float f2 = this.mRangeBoardMin;
        float f3 = this.mRangeBoardMax;
        if (isScrollChart()) {
            f2 = nom.h((int) this.mRangeBoardMin);
            f3 = nom.h((int) this.mRangeBoardMax);
        }
        for (OnXRangeSet onXRangeSet : this.mOnXRangeSets) {
            if (onXRangeSet != null) {
                onXRangeSet.onRangeShow((int) f2, (int) f3);
            }
        }
    }

    private boolean isScrollChart() {
        return this.mChartShowMode == ChartShowMode.SCROLL_MODE || this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE;
    }

    private float getRangeBoardMaxNew(int i2, float f2) {
        if (this.mUnixChartType != UnixChartType.ALL && this.mUnixChartType != UnixChartType.YEAR) {
            return f2;
        }
        long millis = TimeUnit.MINUTES.toMillis(nom.h(i2));
        return (nom.y(millis) || (this.mUnixChartType == UnixChartType.YEAR && nom.q(millis))) ? correctRangeEdgeByMonth((int) f2) : f2;
    }

    private void notifyPager() {
        if (this.mChartShowMode == ChartShowMode.SCROLL_MODE || this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE) {
            if (this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE) {
                this.mScrollAdapter.onRangeShow(getCurrentPagerStartX(), getNextPagerStartX());
            } else {
                this.mScrollAdapter.onRangeShow((int) this.mRangeBoardMin, (int) this.mRangeBoardMax);
            }
            if (this.mPagerNoMoreListener != null) {
                boolean canScrollNewerPagerByCompute = canScrollNewerPagerByCompute();
                boolean z = this.mCanScrollNewerPager;
                if (canScrollNewerPagerByCompute != z) {
                    boolean z2 = !z;
                    this.mCanScrollNewerPager = z2;
                    this.mPagerNoMoreListener.notifyNewerPager(z2);
                }
                boolean canScrollOlderPagerByCompute = canScrollOlderPagerByCompute();
                boolean z3 = this.mCanScrollOlderPager;
                if (canScrollOlderPagerByCompute != z3) {
                    boolean z4 = !z3;
                    this.mCanScrollOlderPager = z4;
                    this.mPagerNoMoreListener.notifyOlderPager(z4);
                }
            }
        }
    }

    private boolean isDayRangeEqual(int i2, int i3) {
        return nom.b(TimeUnit.MINUTES.toMillis((long) i2)) == nom.b(TimeUnit.MINUTES.toMillis((long) this.mRangeBoardMin)) && nom.b(TimeUnit.MINUTES.toMillis((long) i3)) == nom.b(TimeUnit.MINUTES.toMillis((long) this.mRangeBoardMax));
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    protected void computeAxis() {
        int i2;
        int i3;
        if (this.mChartShowMode == ChartShowMode.NORMAL) {
            super.computeAxis();
            return;
        }
        float[] fArr = {this.mViewPortHandler.contentLeft(), 0.0f, this.mViewPortHandler.contentRight(), 0.0f};
        this.mFirstAxisTransformer.pixelsToValue(fArr);
        float f2 = fArr[0];
        float f3 = fArr[2];
        if (f2 <= f3) {
            i2 = (int) f2;
            i3 = (int) f3;
        } else {
            int i4 = (int) f2;
            i2 = (int) f3;
            i3 = i4;
        }
        utilComputeAxis();
        if (this.mXAxis.isEnabled()) {
            this.mXAxisRenderer.computeAxis(i2, i3, false);
        }
    }

    public ChartShowMode acquireChartShowMode() {
        return this.mChartShowMode;
    }

    public class f extends d {

        /* renamed from: a, reason: collision with root package name */
        private HwHealthAxisValueFormatter.HealthDeviceKindType f8874a;

        public f() {
            super();
            this.f8874a = HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_UNKNOWN;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.d, com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getRangeText(double d, double d2) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis((long) d));
            long timeInMillis = calendar.getTimeInMillis();
            if (calendar.get(5) == 1) {
                return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), timeInMillis, 65568);
            }
            return nsj.c(HwHealthBaseScrollBarLineChart.this.getContext(), timeInMillis, TimeUnit.MINUTES.toMillis((long) d2));
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.d, com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
            int h = nom.h((int) f);
            if (this.f8874a == HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_BLOOD_SUGAR) {
                return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), h * 60000, 153);
            }
            return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), h * 60000, 24);
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.d, com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void setHealthType(HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
            this.f8874a = healthDeviceKindType;
        }
    }

    public class d extends HwHealthAxisValueFormatter {
        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void enableMarkerViewShowRange(boolean z) {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void setHealthType(HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
        }

        public d() {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
            return e(f);
        }

        public String e(float f) {
            return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), TimeUnit.MINUTES.toMillis(nom.h((int) f)), 131080);
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getRangeText(double d, double d2) {
            return DateUtils.formatDateRange(HwHealthBaseScrollBarLineChart.this.getContext(), TimeUnit.MINUTES.toMillis((long) d), TimeUnit.MINUTES.toMillis((long) d2), 131076);
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            return e(f);
        }
    }

    public class i extends HwHealthAxisValueFormatter {
        private boolean c = true;
        private HwHealthAxisValueFormatter.HealthDeviceKindType b = HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_UNKNOWN;

        public i() {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void enableMarkerViewShowRange(boolean z) {
            this.c = z;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
            long timeInMillis;
            long j;
            int i = (int) f;
            int h = nom.h(i);
            if (i == ((int) HwHealthBaseScrollBarLineChart.this.mRangeBoardMax)) {
                h--;
            }
            if (!this.c) {
                return a(f, axisBase);
            }
            if (this.b == HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_ACTIVE_HOUR || this.b == HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_TEMPERATURE) {
                long j2 = h * 60000;
                return DateUtils.formatDateRange(HwHealthBaseScrollBarLineChart.this.getContext(), jdl.r(j2), jdl.e(j2, TimeZone.getDefault(), 1), 129);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            if (calendar.get(12) < 30) {
                calendar.set(12, 0);
                long timeInMillis2 = calendar.getTimeInMillis();
                calendar.set(12, 30);
                timeInMillis = calendar.getTimeInMillis();
                j = timeInMillis2;
            } else {
                calendar.set(12, 30);
                j = calendar.getTimeInMillis();
                int i2 = calendar.get(5);
                calendar.set(12, 0);
                calendar.add(11, 1);
                if (calendar.get(5) != i2) {
                    calendar.add(12, -1);
                }
                timeInMillis = calendar.getTimeInMillis();
            }
            return DateUtils.formatDateRange(HwHealthBaseScrollBarLineChart.this.getContext(), j, timeInMillis, 129);
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            return a(f, axisBase);
        }

        private String a(float f, AxisBase axisBase) {
            int h = nom.h((int) f);
            String d = d(h, axisBase);
            if (f != ((int) HwHealthBaseScrollBarLineChart.this.mRangeBoardMax)) {
                return d;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            if (calendar.get(11) != 0) {
                return d;
            }
            String[] split = d.split(":");
            String str = split.length > 0 ? split[0] : null;
            LogUtil.a(HwHealthBaseScrollBarLineChart.TAG, "time: ", d, "hourStr: ", str);
            return d.replaceFirst(str != null ? str.replace("[", "") : "", skh.b(24));
        }

        public String d(int i, AxisBase axisBase) {
            return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), i * 60000, 129);
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void setHealthType(HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
            this.b = healthDeviceKindType;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getRangeText(double d, double d2) {
            return nsj.d(HwHealthBaseScrollBarLineChart.this.getContext(), TimeUnit.MINUTES.toMillis((long) d));
        }
    }

    public class h extends HwHealthAxisValueFormatter {

        /* renamed from: a, reason: collision with root package name */
        private HwHealthAxisValueFormatter.HealthDeviceKindType f8875a = HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_UNKNOWN;
        private HwHealthBaseScrollBarLineChart<T>.d c;

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void enableMarkerViewShowRange(boolean z) {
        }

        public h() {
            this.c = new d();
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
            int h = nom.h((int) f);
            if (this.f8875a == HwHealthAxisValueFormatter.HealthDeviceKindType.HDK_BLOOD_SUGAR) {
                return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), h * 60000, 153);
            }
            return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), h * 60000, 24);
        }

        public String e(float f, AxisBase axisBase, boolean z) {
            if (z) {
                return this.c.getFormattedValue(f, axisBase);
            }
            int h = nom.h((int) f);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            if (calendar.get(7) != nop.c()) {
                return null;
            }
            return this.c.getFormattedValue(h, axisBase);
        }

        public boolean a(float f) {
            int h = nom.h((int) f);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            return calendar.get(7) == nop.e();
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void setHealthType(HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
            this.f8875a = healthDeviceKindType;
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getRangeText(double d, double d2) {
            return nsj.c(HwHealthBaseScrollBarLineChart.this.getContext(), TimeUnit.MINUTES.toMillis((long) d), TimeUnit.MINUTES.toMillis((long) d2));
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            int h = nom.h((int) f);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            int i = calendar.get(7);
            Context context = HwHealthBaseScrollBarLineChart.this.getContext();
            if (i == 1) {
                return context.getResources().getString(R$string.IDS_sunday);
            }
            if (i == 2) {
                return context.getResources().getString(R$string.IDS_monday);
            }
            if (i == 3) {
                return context.getResources().getString(R$string.IDS_tuesday);
            }
            if (i == 4) {
                return context.getResources().getString(R$string.IDS_wednesday);
            }
            if (i == 5) {
                return context.getResources().getString(R$string.IDS_thursday);
            }
            if (i == 6) {
                return context.getResources().getString(R$string.IDS_friday);
            }
            return i == 7 ? context.getResources().getString(R$string.IDS_saturday) : "";
        }
    }

    public class g extends HwHealthAxisValueFormatter {
        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void enableMarkerViewShowRange(boolean z) {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void setHealthType(HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
        }

        public g() {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
            return DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), TimeUnit.MINUTES.toMillis(nom.h((int) f)), 65568);
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getRangeText(double d, double d2) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis((long) d));
            calendar.add(12, 1);
            long timeInMillis = calendar.getTimeInMillis();
            calendar.setTimeInMillis(TimeUnit.MINUTES.toMillis((long) d2));
            calendar.add(12, -1);
            long timeInMillis2 = calendar.getTimeInMillis();
            if (nsj.b(timeInMillis, timeInMillis2)) {
                return nsj.c(HwHealthBaseScrollBarLineChart.this.getContext(), timeInMillis);
            }
            return nsj.e(HwHealthBaseScrollBarLineChart.this.getContext(), timeInMillis, timeInMillis2);
        }

        public String a(float f, XAxis xAxis, boolean z) {
            if (z) {
                return a(f);
            }
            int h = nom.h((int) f);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            if (calendar.get(2) != 0) {
                return null;
            }
            return a(f);
        }

        private String a(float f) {
            return new SimpleDateFormat("yyyy").format(new Date(TimeUnit.MINUTES.toMillis(nom.h((int) f))));
        }

        public boolean c(float f) {
            int h = nom.h((int) f);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h * 60000);
            return calendar.get(2) == 11;
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            return new SimpleDateFormat("M").format(new Date(nom.h((int) f) * 60000));
        }
    }

    public class b extends HwHealthAxisValueFormatter {
        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void enableMarkerViewShowRange(boolean z) {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public void setHealthType(HwHealthAxisValueFormatter.HealthDeviceKindType healthDeviceKindType) {
        }

        public b() {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getFormattedValueForMarkerView(float f, AxisBase axisBase) {
            return getFormattedValue(f, axisBase);
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.HwHealthAxisValueFormatter
        public String getRangeText(double d, double d2) {
            return HwHealthBaseScrollBarLineChart.this.getContext().getString(R$string.IDS_hwh_motiontrack_sport_data_share_all, DateUtils.formatDateTime(HwHealthBaseScrollBarLineChart.this.getContext(), TimeUnit.MINUTES.toMillis((long) d), 36));
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        public String getFormattedValue(float f, AxisBase axisBase) {
            return new SimpleDateFormat("yyyy").format(new Date(nom.h((int) f) * 60000));
        }
    }

    public float acquireShowRangeMinimum() {
        return this.mRangeBoardMin;
    }

    public float acquireShowRangeMaximum() {
        return this.mRangeBoardMax;
    }

    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f2, float f3) {
        if (this.mAxisRendererFirstParty.f()) {
            f2 = Math.max(f2, this.mAxisRendererFirstParty.e_());
        }
        ScrollAdapterInterface scrollAdapterInterface = this.mScrollAdapter;
        if (scrollAdapterInterface != null) {
            return scrollAdapterInterface.computeDynamicBorderMax(hwHealthBaseBarLineDataSet, f2, f3);
        }
        return 0.0f;
    }

    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f2, float f3) {
        return this.mScrollAdapter.computeDynamicBorderMin(hwHealthBaseBarLineDataSet, f2, f3);
    }

    public void animateBorderY(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency, float f2, float f3, boolean z, HwHealthBaseScrollBarLineChart<T>.e eVar, AnimateValueTransfer animateValueTransfer, int i2, int i3) {
        this.mOnScrollPagerAnimateMgr.b(eVar);
        if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY) {
            this.mAxisRendererFirstParty.a(f2, f3, z, eVar, animateValueTransfer, i2, i3);
        } else if (hwHealthAxisDependency == HwHealthYAxis.HwHealthAxisDependency.SECOND_PARTY) {
            this.mAxisRendererSecondParty.a(f2, f3, z, eVar, animateValueTransfer, i2, i3);
        } else {
            this.mAxisRendererThirdParty.a(f2, f3, z, eVar, animateValueTransfer, i2, i3);
        }
    }

    public void animateBorderYAuto() {
        animateBorderYAuto(1);
    }

    public void animateBorderYAuto(int i2) {
        animateBorderYAuto(new e(), this.mDefaultAnimateValueTransfer, i2);
    }

    public void animateBorderYAuto(HwHealthBaseScrollBarLineChart<T>.e eVar, int i2) {
        animateBorderYAuto(eVar, this.mDefaultAnimateValueTransfer, i2);
    }

    public void animateBorderYAuto(HwHealthBaseScrollBarLineChart<T>.e eVar, AnimateValueTransfer animateValueTransfer, int i2) {
        this.mOnScrollPagerAnimateMgr.b(eVar);
        animateBorderYAuto(false, eVar, animateValueTransfer, 1200, i2);
    }

    public void animateBorderYAuto(boolean z, final HwHealthBaseScrollBarLineChart<T>.e eVar, AnimateValueTransfer animateValueTransfer, int i2, int i3) {
        if (eVar == null) {
            LogUtil.b(TAG, "animateBorderYAuto scrollPagerAnimateListener == null");
            return;
        }
        this.mOnScrollPagerAnimateMgr.b(eVar);
        AsyncSelectorMultiRun asyncSelectorMultiRun = new AsyncSelectorMultiRun(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.8
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onFailed(int i4) {
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onSuccess() {
                eVar.d();
            }
        };
        AsyncSelectorMultiRun.e eVar2 = new AsyncSelectorMultiRun.e();
        asyncSelectorMultiRun.add(eVar2);
        for (IHwHealthBarLineDataSet iHwHealthBarLineDataSet : getData().getDataSets()) {
            if (iHwHealthBarLineDataSet != null) {
                float acquireShowRangeMaxValue = iHwHealthBarLineDataSet.acquireShowRangeMaxValue(this);
                float acquireShowRangeMinValue = iHwHealthBarLineDataSet.acquireShowRangeMinValue(this);
                float computeDynamicBorderMax = iHwHealthBarLineDataSet.computeDynamicBorderMax(this, acquireShowRangeMaxValue, acquireShowRangeMinValue);
                float computeDynamicBorderMin = iHwHealthBarLineDataSet.computeDynamicBorderMin(this, acquireShowRangeMaxValue, acquireShowRangeMinValue);
                AsyncSelectorMultiRun.e eVar3 = new AsyncSelectorMultiRun.e();
                asyncSelectorMultiRun.add(eVar3);
                animateBorderY(iHwHealthBarLineDataSet.getAxisDependencyExt(), computeDynamicBorderMax, computeDynamicBorderMin, z, new nnm(this, asyncSelectorMultiRun, eVar3), animateValueTransfer, i2, i3);
            }
        }
        asyncSelectorMultiRun.run();
        asyncSelectorMultiRun.actionEnd(eVar2);
    }

    public void addOnXRangeSet(OnXRangeSet onXRangeSet) {
        this.mOnXRangeSets.add(onXRangeSet);
    }

    public float getShowDataAverage() {
        List dataSets = getData().getDataSets();
        if (koq.b(dataSets)) {
            return 0.0f;
        }
        return ((IHwHealthBarLineDataSet) dataSets.get(0)).acquireShowRangeAverageValue(this);
    }

    public float getShowDataByRatio(DataRatioProvider dataRatioProvider) {
        List dataSets = getData().getDataSets();
        if (koq.b(dataSets)) {
            return 0.0f;
        }
        return ((IHwHealthBarLineDataSet) dataSets.get(0)).acquireShowRangeRatioedValue(this, dataRatioProvider);
    }

    public String formatRangeText(int i2, int i3) {
        HwHealthAxisValueFormatter hwHealthAxisValueFormatter = this.mHwHealthAxisValueFormatter;
        return hwHealthAxisValueFormatter != null ? hwHealthAxisValueFormatter.getRangeText(i2, i3) : "";
    }

    public void scrollOnePageOlder(AnimateValueTransfer animateValueTransfer, AnimateTimeComputer animateTimeComputer) {
        scrollOnePageOlder(new e(), animateValueTransfer, animateTimeComputer);
    }

    public void scrollOnePageOlder(HwHealthBaseScrollBarLineChart<T>.e eVar) {
        scrollOnePageOlder(eVar, this.mDefaultAnimateValueTransfer, this.mDefaultAnimateTimeComputer);
    }

    public void scrollOnePageOlder(HwHealthBaseScrollBarLineChart<T>.e eVar, AnimateValueTransfer animateValueTransfer, AnimateTimeComputer animateTimeComputer) {
        this.mOnScrollPagerAnimateMgr.b(eVar);
        boolean z = this.mUnixChartType == UnixChartType.DAY;
        if (!(this.mXAxis instanceof nnu)) {
            LogUtil.h(TAG, "scrollOnePageOlder mXAxis not instanceof HwHealthXAxis");
            return;
        }
        if (z) {
            ((nnu) this.mXAxis).b(true);
        }
        int pagerToScrollEndPosition = getPagerToScrollEndPosition(false);
        AsyncSelectorMultiRun asyncSelectorMultiRun = getAsyncSelectorMultiRun(z, eVar, pagerToScrollEndPosition);
        AsyncSelectorMultiRun.e eVar2 = new AsyncSelectorMultiRun.e();
        asyncSelectorMultiRun.add(eVar2);
        processScrollPagerAnimateEnd(animateValueTransfer, animateTimeComputer, pagerToScrollEndPosition, asyncSelectorMultiRun, getData().getDataSets());
        AsyncSelectorMultiRun.e eVar3 = new AsyncSelectorMultiRun.e();
        asyncSelectorMultiRun.add(eVar3);
        scrollPagerToUnixTimeOnlyX(pagerToScrollEndPosition, new nnp(this, asyncSelectorMultiRun, eVar3), animateTimeComputer.computeTime(Math.abs(pagerToScrollEndPosition - ((int) this.mRangeBoardMin))), animateValueTransfer);
        asyncSelectorMultiRun.run();
        asyncSelectorMultiRun.actionEnd(eVar2);
    }

    public void updateMatrixTouch() {
        this.mViewPortHandler.getMatrixTouch().set(new Matrix());
    }

    private void processScrollPagerAnimateEnd(AnimateValueTransfer animateValueTransfer, AnimateTimeComputer animateTimeComputer, int i2, AsyncSelectorMultiRun asyncSelectorMultiRun, List<? extends IHwHealthBarLineDataSet<? extends Entry>> list) {
        for (IHwHealthBarLineDataSet<? extends Entry> iHwHealthBarLineDataSet : list) {
            if (iHwHealthBarLineDataSet != null) {
                float f2 = i2;
                float acquireRangePagerMaxValue = iHwHealthBarLineDataSet.acquireRangePagerMaxValue(f2, this.mShowRange + i2);
                float acquireRangePagerMinValue = iHwHealthBarLineDataSet.acquireRangePagerMinValue(f2, this.mShowRange + i2);
                float computeDynamicBorderMax = iHwHealthBarLineDataSet.computeDynamicBorderMax(this, acquireRangePagerMaxValue, acquireRangePagerMinValue);
                float computeDynamicBorderMin = iHwHealthBarLineDataSet.computeDynamicBorderMin(this, acquireRangePagerMaxValue, acquireRangePagerMinValue);
                AsyncSelectorMultiRun.e eVar = new AsyncSelectorMultiRun.e();
                asyncSelectorMultiRun.add(eVar);
                animateBorderY(iHwHealthBarLineDataSet.getAxisDependencyExt(), computeDynamicBorderMax, computeDynamicBorderMin, false, new nno(this, asyncSelectorMultiRun, eVar), animateValueTransfer, animateTimeComputer.computeTime(Math.abs(i2 - ((int) this.mRangeBoardMin))), 2);
            }
        }
    }

    private int getPagerToScrollEndPosition(boolean z) {
        if (isInPagerPosition() || isSupportScaled()) {
            if (!z) {
                return getLastPagerStartX();
            }
            return getNextPagerStartX();
        }
        if (!z) {
            return getCurrentPagerStartX();
        }
        return getNextPagerStartX();
    }

    public void scrollOnePageNewer(HwHealthBaseScrollBarLineChart<T>.e eVar) {
        scrollOnePageNewer(eVar, this.mDefaultAnimateValueTransfer, this.mDefaultAnimateTimeComputer);
    }

    private AsyncSelectorMultiRun getAsyncSelectorMultiRun(final boolean z, final HwHealthBaseScrollBarLineChart<T>.e eVar, final int i2) {
        return new AsyncSelectorMultiRun(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.6
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onFailed(int i3) {
                if (z) {
                    ((nnu) HwHealthBaseScrollBarLineChart.this.mXAxis).b(false);
                    HwHealthBaseScrollBarLineChart.this.invalidateForce();
                }
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onSuccess() {
                HwHealthBaseScrollBarLineChart.this.success(eVar, z, i2);
            }
        };
    }

    public void scrollOnePageNewer(HwHealthBaseScrollBarLineChart<T>.e eVar, AnimateValueTransfer animateValueTransfer, AnimateTimeComputer animateTimeComputer) {
        if (eVar == null || animateValueTransfer == null || animateTimeComputer == null) {
            LogUtil.b(TAG, "scrollOnePageNewer input is null");
            return;
        }
        this.mOnScrollPagerAnimateMgr.b(eVar);
        boolean z = this.mUnixChartType == UnixChartType.DAY;
        if (!(this.mXAxis instanceof nnu)) {
            LogUtil.h(TAG, "scrollOnePageNewer mXAxis not instanceof HwHealthXAxis");
            return;
        }
        if (z) {
            ((nnu) this.mXAxis).b(true);
        }
        int pagerToScrollEndPosition = getPagerToScrollEndPosition(true);
        AsyncSelectorMultiRun acquireAsyncSelectorMultiRun = acquireAsyncSelectorMultiRun(eVar, z, pagerToScrollEndPosition);
        AsyncSelectorMultiRun.e eVar2 = new AsyncSelectorMultiRun.e();
        acquireAsyncSelectorMultiRun.add(eVar2);
        for (IHwHealthBarLineDataSet iHwHealthBarLineDataSet : getData().getDataSets()) {
            float f2 = pagerToScrollEndPosition;
            float acquireRangePagerMaxValue = iHwHealthBarLineDataSet.acquireRangePagerMaxValue(f2, this.mShowRange + pagerToScrollEndPosition);
            float acquireRangePagerMinValue = iHwHealthBarLineDataSet.acquireRangePagerMinValue(f2, this.mShowRange + pagerToScrollEndPosition);
            float computeDynamicBorderMax = iHwHealthBarLineDataSet.computeDynamicBorderMax(this, acquireRangePagerMaxValue, acquireRangePagerMinValue);
            float computeDynamicBorderMin = iHwHealthBarLineDataSet.computeDynamicBorderMin(this, acquireRangePagerMaxValue, acquireRangePagerMinValue);
            AsyncSelectorMultiRun.e eVar3 = new AsyncSelectorMultiRun.e();
            acquireAsyncSelectorMultiRun.add(eVar3);
            animateBorderY(iHwHealthBarLineDataSet.getAxisDependencyExt(), computeDynamicBorderMax, computeDynamicBorderMin, false, new nnn(this, acquireAsyncSelectorMultiRun, eVar3), animateValueTransfer, animateTimeComputer.computeTime(Math.abs(pagerToScrollEndPosition - ((int) this.mRangeBoardMin))), 2);
        }
        AsyncSelectorMultiRun.e eVar4 = new AsyncSelectorMultiRun.e();
        acquireAsyncSelectorMultiRun.add(eVar4);
        scrollPagerToUnixTimeOnlyX(pagerToScrollEndPosition, new nnq(this, acquireAsyncSelectorMultiRun, eVar4), animateTimeComputer.computeTime(Math.abs(pagerToScrollEndPosition - ((int) this.mRangeBoardMin))), animateValueTransfer);
        acquireAsyncSelectorMultiRun.run();
        acquireAsyncSelectorMultiRun.actionEnd(eVar2);
    }

    private AsyncSelectorMultiRun acquireAsyncSelectorMultiRun(final HwHealthBaseScrollBarLineChart<T>.e eVar, final boolean z, final int i2) {
        return new AsyncSelectorMultiRun(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.5
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onFailed(int i3) {
                HwHealthBaseScrollBarLineChart.this.isFailed(z);
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onSuccess() {
                HwHealthBaseScrollBarLineChart.this.success(eVar, z, i2);
            }
        };
    }

    class a {
        private List<HwHealthBaseScrollBarLineChart<T>.e> e;

        private a() {
            this.e = new ArrayList();
        }

        public void b(HwHealthBaseScrollBarLineChart<T>.e eVar) {
            if (this.e.contains(eVar)) {
                return;
            }
            this.e.add(eVar);
        }

        public void c(HwHealthBaseScrollBarLineChart<T>.e eVar) {
            this.e.remove(eVar);
        }

        public boolean d() {
            return this.e.size() != 0;
        }
    }

    public class e {
        public e() {
        }

        public void d() {
            HwHealthBaseScrollBarLineChart.this.mOnScrollPagerAnimateMgr.c(this);
        }
    }

    public abstract class AbstractAnimateTimeComputer implements AnimateTimeComputer {
        public abstract int computeTimeByPixel(float f);

        public AbstractAnimateTimeComputer() {
        }

        @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.AnimateTimeComputer
        public int computeTime(int i) {
            float[] fArr = {0.0f, 0.0f, i, 0.0f};
            HwHealthBaseScrollBarLineChart.this.mFirstAxisTransformer.pointValuesToPixel(fArr);
            return computeTimeByPixel(Math.abs(fArr[2] - fArr[0]));
        }
    }

    public boolean isAnimating() {
        return this.mOnScrollPagerAnimateMgr.d();
    }

    public boolean canScrollOlderPager() {
        return this.mCanScrollOlderPager;
    }

    public boolean canScrollOlderPagerByCompute() {
        float acquireShowRangeMinimum = acquireShowRangeMinimum();
        if (Math.abs(Float.MAX_VALUE + acquireShowRangeMinimum) < 1.0E-6d) {
            acquireShowRangeMinimum = this.mXAxis.getAxisMinimum();
        }
        return acquireShowRangeMinimum > ((float) nom.f((int) TimeUnit.MILLISECONDS.toMinutes(1388505600000L)));
    }

    public boolean canScrollNewerPager() {
        return this.mCanScrollNewerPager;
    }

    public boolean canScrollNewerPagerByCompute() {
        float acquireShowRangeMinimum = acquireShowRangeMinimum();
        if (Math.abs(Float.MAX_VALUE + acquireShowRangeMinimum) < 1.0E-6d) {
            acquireShowRangeMinimum = this.mXAxis.getAxisMinimum();
        }
        return acquireShowRangeMinimum < ((float) this.mScrollAdapter.acquireDefaultStartX());
    }

    public void reCalculateDynamicBoardForManualRefLine() {
        if (((HwHealthBaseBarLineData) this.mData).getDataSets().size() < 1) {
            return;
        }
        IHwHealthBarLineDataSet iHwHealthBarLineDataSet = (IHwHealthBarLineDataSet) ((HwHealthBaseBarLineData) this.mData).getDataSets().get(0);
        this.mAxisFirstParty.setAxisMaximum(iHwHealthBarLineDataSet.computeDynamicBorderMax(this, iHwHealthBarLineDataSet.acquireShowRangeMaxValue(this), iHwHealthBarLineDataSet.acquireShowRangeMinValue(this)));
        invalidate();
    }

    public void correctViewPosition(HwHealthBaseScrollBarLineChart<T>.e eVar) {
        if (!(this.mViewPortHandler instanceof HwHealthViewPortHandler)) {
            LogUtil.h(TAG, "correctViewPosition mViewPortHandler not instanceof HwHealthViewPortHandler");
            return;
        }
        if (((HwHealthViewPortHandler) this.mViewPortHandler).c() && this.mRangeBoardMin > this.mScrollAdapter.acquireDefaultStartX()) {
            scrollPagerToUnixTimeOnlyX(this.mScrollAdapter.acquireDefaultStartX(), eVar);
            return;
        }
        if (this.mRangeBoardMin < nom.f((int) TimeUnit.MILLISECONDS.toMinutes(1388505600000L))) {
            scrollPagerToUnixTimeOnlyX(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(1388505600000L)), eVar);
            return;
        }
        if (this.mUnixChartType == UnixChartType.DAY || this.mUnixChartType == UnixChartType.WEEK || this.mUnixChartType == UnixChartType.MONTH) {
            scrollPagerToUnixTimeOnlyX(getClosetDayZeroUnixTime(this.mRangeBoardMin), eVar);
        } else if (this.mUnixChartType == UnixChartType.YEAR) {
            scrollPagerToUnixTimeOnlyX(getClosetMonthZeroUnixTime(this.mRangeBoardMin), eVar);
        } else if (this.mUnixChartType == UnixChartType.ALL) {
            scrollPagerToUnixTimeOnlyX(getClosetYearZeroUnixTime(this.mRangeBoardMin), eVar);
        }
    }

    protected int getClosetDayZeroUnixTime(float f2) {
        int h2 = nom.h((int) f2);
        int c2 = nom.c(h2);
        int o = nom.o(h2);
        if (Math.abs(c2 - h2) <= Math.abs(o - h2)) {
            return nom.f(c2);
        }
        return nom.f(o);
    }

    protected int getClosetMonthZeroUnixTime(float f2) {
        int h2 = nom.h((int) f2);
        int l = nom.l(h2);
        int k = nom.k(h2);
        if (Math.abs(l - h2) <= Math.abs(k - h2)) {
            return nom.f(l);
        }
        return nom.f(k);
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    public int getMonthDaysOfMinTime() {
        if (this.mUnixChartType != UnixChartType.MONTH) {
            return 0;
        }
        return nom.i(TimeUnit.MINUTES.toMillis(nom.h((int) this.mRangeBoardMin)));
    }

    protected int getClosetWeekZeroUnixTime(float f2) {
        int h2 = nom.h((int) f2);
        int p = nom.p(h2);
        int n = nom.n(h2);
        if (Math.abs(p - h2) <= Math.abs(n - h2)) {
            return nom.f(p);
        }
        return nom.f(n);
    }

    protected int getClosetYearZeroUnixTime(float f2) {
        int h2 = nom.h((int) f2);
        int r = nom.r(h2);
        int s = nom.s(h2);
        if (Math.abs(r - h2) <= Math.abs(s - h2)) {
            return nom.f(r);
        }
        return nom.f(s);
    }

    public boolean blinkToUnixTime(int i2) {
        return enableChangeToUnixTimeOnlyX(i2, new nnk(this), this.mDefaultAnimateValueTransfer);
    }

    public boolean blinkToUnixTime(int i2, AnimateValueTransfer animateValueTransfer) {
        return enableChangeToUnixTimeOnlyX(i2, new nni(this), animateValueTransfer);
    }

    protected void scrollPagerToUnixTimeOnlyX(int i2, HwHealthBaseScrollBarLineChart<T>.e eVar) {
        scrollPagerToUnixTimeOnlyX(i2, eVar, 300, this.mDefaultAnimateValueTransfer);
    }

    protected boolean scrollPagerToUnixTimeOnlyX(int i2, HwHealthBaseScrollBarLineChart<T>.e eVar, int i3, AnimateValueTransfer animateValueTransfer) {
        if (eVar == null || animateValueTransfer == null) {
            LogUtil.b(TAG, "scrollPagerToUnixTimeOnlyX scrollPagerAnimateListener == null || animateValueTransfer == null");
            return false;
        }
        this.mOnScrollPagerAnimateMgr.b(eVar);
        float f2 = i2;
        if (Math.abs(this.mRangeBoardMin - f2) < 1.0f) {
            eVar.d();
            return false;
        }
        this.mOnScrollPagerAnimateMgr.b(eVar);
        float f3 = this.mRangeBoardMin;
        boolean z = this.mUnixChartType == UnixChartType.DAY;
        if (!(this.mXAxis instanceof nnu)) {
            LogUtil.h(TAG, "scrollPagerToUnixTimeOnlyX mXAxis not instanceof HwHealthXAxis");
            return false;
        }
        AsyncSelectorMultiRun selectorMultiRun = getSelectorMultiRun(eVar, z);
        AsyncSelectorMultiRun.e eVar2 = new AsyncSelectorMultiRun.e();
        selectorMultiRun.add(eVar2);
        Matrix matrix = new Matrix();
        matrix.set(this.mViewPortHandler.getMatrixTouch());
        AsyncSelectorMultiRun.e eVar3 = new AsyncSelectorMultiRun.e();
        selectorMultiRun.add(eVar3);
        setAnimatorPagerChange(animateValueTransfer, matrix, getMonthShowRange(i2), Math.abs(f2 - f3), f2 >= this.mRangeBoardMin, i3, selectorMultiRun, eVar3);
        selectorMultiRun.run();
        selectorMultiRun.actionEnd(eVar2);
        return true;
    }

    public boolean enableChangeToUnixTimeOnlyX(int i2, HwHealthBaseScrollBarLineChart<T>.e eVar, AnimateValueTransfer animateValueTransfer) {
        this.mOnScrollPagerAnimateMgr.b(eVar);
        return scrollPagerToUnixTimeOnlyX(i2, eVar, 0, animateValueTransfer);
    }

    private c getMonthShowRange(int i2) {
        boolean isInPagerPosition = isInPagerPosition(i2);
        c cVar = new c();
        if (isInPagerPosition && this.mUnixChartType == UnixChartType.MONTH) {
            cVar.f8873a = true;
            cVar.d = nom.c(((int) (this.mRangeBoardMax - this.mRangeBoardMin)) * 60000);
            cVar.b = nom.i(i2 * 60000);
            cVar.c = cVar.d * (1.0f / cVar.b);
        }
        return cVar;
    }

    private AsyncSelectorMultiRun getSelectorMultiRun(final HwHealthBaseScrollBarLineChart<T>.e eVar, final boolean z) {
        if (z) {
            ((nnu) this.mXAxis).b(true);
        }
        return new AsyncSelectorMultiRun(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.2
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onFailed(int i2) {
                HwHealthBaseScrollBarLineChart.this.isFailed(z);
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorMultiRun
            public void onSuccess() {
                eVar.d();
                if (z) {
                    ((nnu) HwHealthBaseScrollBarLineChart.this.mXAxis).b(false);
                    HwHealthBaseScrollBarLineChart.this.invalidateForce();
                }
            }
        };
    }

    public void setPagerNoMoreListener(PagerNoMoreListener pagerNoMoreListener) {
        this.mPagerNoMoreListener = pagerNoMoreListener;
    }

    private void setAnimatorPagerChange(final AnimateValueTransfer animateValueTransfer, final Matrix matrix, final c cVar, final float f2, final boolean z, int i2, final AsyncSelectorMultiRun asyncSelectorMultiRun, final AsyncSelectorMultiRun.e eVar) {
        this.mAnimatorPagerChange.cCw_(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float transferValue = animateValueTransfer.transferValue(HwHealthBaseScrollBarLineChart.this.mAnimatorPagerChange.b());
                Matrix matrix2 = new Matrix();
                matrix2.set(matrix);
                if (cVar.f8873a) {
                    matrix2.postScale(((cVar.c - 1.0f) * transferValue) + 1.0f, 1.0f, 0.0f, 0.0f);
                }
                if (!(HwHealthBaseScrollBarLineChart.this.getViewPortHandler() instanceof HwHealthViewPortHandler)) {
                    LogUtil.h(HwHealthBaseScrollBarLineChart.TAG, "getViewPortHandler() not instanceof HwHealthViewPortHandler");
                    return;
                }
                ((HwHealthViewPortHandler) HwHealthBaseScrollBarLineChart.this.getViewPortHandler()).cBC_(matrix2);
                float[] fArr = {0.0f, 0.0f, f2, 0.0f};
                HwHealthBaseScrollBarLineChart.this.getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).pointValuesToPixel(fArr);
                float abs = Math.abs(fArr[2] - fArr[0]);
                if ((!nng.d(HwHealthBaseScrollBarLineChart.this.getContext()) && z) || (nng.d(HwHealthBaseScrollBarLineChart.this.getContext()) && !z)) {
                    matrix2.postTranslate((-abs) * transferValue, 0.0f);
                } else {
                    matrix2.postTranslate(abs * transferValue, 0.0f);
                }
                ((HwHealthViewPortHandler) HwHealthBaseScrollBarLineChart.this.getViewPortHandler()).cBC_(matrix2);
                HwHealthBaseScrollBarLineChart.this.postInvalidate();
                if (transferValue == 1.0f) {
                    asyncSelectorMultiRun.actionEnd(eVar);
                }
            }
        }, i2);
    }

    public int getCurrentPagerStartX() {
        if (this.mUnixChartType == UnixChartType.DAY) {
            return nom.f(nom.c(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.WEEK) {
            return nom.f(nom.p(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.MONTH) {
            return nom.f(nom.l(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.YEAR) {
            return nom.f(nom.r(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.ALL) {
            return nom.f(nom.d(nom.h((int) this.mRangeBoardMin)));
        }
        LogUtil.b(TAG, "error mUnixChartType: ", this.mUnixChartType);
        return nom.f(nom.c(nom.h((int) this.mRangeBoardMin)));
    }

    /* renamed from: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8865a;

        static {
            int[] iArr = new int[UnixChartType.values().length];
            f8865a = iArr;
            try {
                iArr[UnixChartType.DAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8865a[UnixChartType.WEEK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8865a[UnixChartType.MONTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8865a[UnixChartType.YEAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8865a[UnixChartType.ALL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public int getCurrentPagerStartX(long j) {
        int a2;
        int i2 = AnonymousClass10.f8865a[this.mUnixChartType.ordinal()];
        if (i2 == 1) {
            a2 = nom.a(j);
        } else if (i2 == 2) {
            a2 = nom.m(j);
        } else if (i2 == 3) {
            a2 = nom.f(j);
        } else if (i2 == 4) {
            a2 = nom.t(j);
        } else {
            LogUtil.b(TAG, "error mUnixChartType: ", this.mUnixChartType);
            a2 = 0;
        }
        return nom.f(a2);
    }

    public int getNextPagerStartX() {
        if (this.mUnixChartType == UnixChartType.DAY) {
            return nom.f(nom.o(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.WEEK) {
            return nom.f(nom.n(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.MONTH) {
            return nom.f(nom.k(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.YEAR) {
            return nom.f(nom.s(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.ALL) {
            return nom.f(nom.m(nom.h((int) this.mRangeBoardMin)));
        }
        throw new RuntimeException("unsupport showMode in getNextPagerStartX");
    }

    public int getLastPagerStartX() {
        if (this.mUnixChartType == UnixChartType.DAY) {
            return nom.f(nom.e(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.WEEK) {
            return nom.f(nom.g(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.MONTH) {
            return nom.f(nom.j(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.YEAR) {
            return nom.f(nom.i(nom.h((int) this.mRangeBoardMin)));
        }
        if (this.mUnixChartType == UnixChartType.ALL) {
            return nom.f(nom.a(nom.h((int) this.mRangeBoardMin)));
        }
        throw new RuntimeException("unsupport showMode in getLastPagerStartX");
    }

    private int correctRangeEdgeByDay(int i2) {
        int h2 = nom.h(i2);
        int c2 = nom.c(h2);
        int o = nom.o(h2);
        int abs = Math.abs(c2 - h2);
        int abs2 = Math.abs(o - h2);
        return abs <= abs2 ? ((float) abs) < 5.0f ? nom.f(c2) : i2 : ((float) abs2) < 5.0f ? nom.f(o) : i2;
    }

    private int correctRangeEdgeByMonth(int i2) {
        int h2 = nom.h(i2);
        int l = nom.l(h2);
        int k = nom.k(h2);
        int abs = Math.abs(l - h2);
        int abs2 = Math.abs(k - h2);
        return abs <= abs2 ? (((float) abs) * 1.0f) / ((float) nom.b()) < 5.0f ? nom.f(l) : i2 : (((float) abs2) * 1.0f) / ((float) nom.b()) < 5.0f ? nom.f(k) : i2;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        boolean f8873a;
        int b;
        float c;
        int d;

        private c() {
            this.f8873a = false;
            this.d = 30;
            this.b = 30;
            this.c = 1.0f;
        }
    }

    public boolean isSupportFling() {
        return this.mUnixChartType != UnixChartType.ALL;
    }

    public float[] getShowMinMaxRangeByRatio() {
        List dataSets = getData().getDataSets();
        if (koq.b(dataSets) || dataSets.get(0) == null) {
            return new float[]{0.0f, 0.0f};
        }
        IHwHealthBarLineDataSet iHwHealthBarLineDataSet = (IHwHealthBarLineDataSet) dataSets.get(0);
        return new float[]{iHwHealthBarLineDataSet.acquireShowRangeMinValue(this), iHwHealthBarLineDataSet.acquireShowRangeMaxValue(this)};
    }

    public void fillBuffer(ScrollAdapter.b bVar, OnDataFillCallback onDataFillCallback) {
        if (bVar == null) {
            LogUtil.b(TAG, "buffer is null");
            return;
        }
        int e2 = bVar.e();
        int d2 = bVar.d();
        int c2 = bVar.c();
        int a2 = bVar.a();
        HwHealthBaseBarLineData hwHealthBaseBarLineData = (HwHealthBaseBarLineData) getData();
        if (hwHealthBaseBarLineData == null) {
            LogUtil.b(TAG, "data is null");
            return;
        }
        List<T> dataSets = hwHealthBaseBarLineData.getDataSets();
        if (dataSets == 0) {
            LogUtil.b(TAG, "dataSets is null");
            return;
        }
        for (T t : dataSets) {
            t.fillSetByDatabase(BaseApplication.getContext(), this, new nnh(e2, d2, c2, a2, this.mScrollAdapter.queryShowMode(t), this.mScrollAdapter.queryDataInfos(t)), onDataFillCallback, this.mScrollAdapter.queryStorage(t));
        }
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getLowestVisibleX() {
        if (this.mChartShowMode != ChartShowMode.SCROLL_MODE) {
            return super.getLowestVisibleX();
        }
        return this.mRangeBoardMin;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart, com.github.mikephil.charting.charts.BarLineChartBase, com.github.mikephil.charting.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider
    public float getHighestVisibleX() {
        if (this.mChartShowMode != ChartShowMode.SCROLL_MODE) {
            return super.getHighestVisibleX();
        }
        return this.mRangeBoardMax;
    }

    public void adsorbMarkerViewToSelectedDataByDataArea() {
        adsorbMarkerViewToSelectedDataByDataArea(true, new e());
    }

    public void adsorbMarkerViewToSelectedDataByDataArea(boolean z) {
        adsorbMarkerViewToSelectedDataByDataArea(z, new e());
    }

    public void adsorbMarkerViewToSelectedDataByDataArea(boolean z, HwHealthBaseScrollBarLineChart<T>.e eVar) {
        if (eVar == null) {
            LogUtil.b(TAG, "adsorbMarkerViewToSelectedDataByDataArea scrollPagerAnimateListener == null");
            return;
        }
        this.mOnScrollPagerAnimateMgr.b(eVar);
        if (this.mMarkerViewPosition == MARKER_VIEW_POSITION_UNSET) {
            eVar.d();
            return;
        }
        float[] floats = getFloats(eVar);
        if (floats == null) {
            LogUtil.b(TAG, "adsorbMarkerViewToSelectedDataByDataArea points == null");
            return;
        }
        nmy hwHealthUnixBarDataSet = getHwHealthUnixBarDataSet(eVar, floats);
        if (hwHealthUnixBarDataSet == null) {
            LogUtil.h(TAG, "adsorbMarkerViewToSelectedDataByDataArea baseData == null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Highlight(this.mMarkerViewPosition.e, 0, -1));
        Entry entryForXValue = ((HwHealthBaseBarLineData) this.mData).getEntryForXValue(this.mIndicesToHighlight[0].getX(), this);
        if (!(entryForXValue instanceof HwEntrys)) {
            throw new RuntimeException("adsorb2SelectedDataByDataArea:entry not instanceof HwEntrys");
        }
        if (arrayList.size() == 0) {
            eVar.d();
            return;
        }
        float[] fArr = {((Highlight) arrayList.get(0)).getX(), 0.0f};
        this.mFirstAxisTransformer.pointValuesToPixel(fArr);
        if (!this.mViewPortHandler.isInBoundsX(fArr[0])) {
            eVar.d();
        } else if (getSelectedBarData(hwHealthUnixBarDataSet, ((HwEntrys) entryForXValue).getEntries()) == null) {
            eVar.d();
        } else {
            scrollMarkerViewToUnixTime(z, getEndUnixTime(hwHealthUnixBarDataSet), eVar);
        }
    }

    private int getEndUnixTime(nmy nmyVar) {
        int i2 = (int) this.mMarkerViewPosition.e;
        int i3 = (int) this.mRangeBoardMax;
        if (i2 >= i3) {
            i2 = i3 - 1;
        }
        int i4 = (int) this.mRangeBoardMin;
        if (i2 <= i4) {
            i2 = i4 + 1;
        }
        return nom.f(nmyVar.acquireRangeCenterValue(nom.h(i2)));
    }

    private nmy getHwHealthUnixBarDataSet(HwHealthBaseScrollBarLineChart<T>.e eVar, float[] fArr) {
        nmy nmyVar;
        HwHealthBaseBarLineData hwHealthBaseBarLineData = (HwHealthBaseBarLineData) getData();
        if (hwHealthBaseBarLineData == null) {
            LogUtil.b(TAG, "getHwHealthUnixBarDataSet data null");
            return null;
        }
        List<T> dataSets = hwHealthBaseBarLineData.getDataSets();
        if (dataSets == 0) {
            LogUtil.b(TAG, "dataSets is null");
            return null;
        }
        Iterator it = dataSets.iterator();
        while (true) {
            if (!it.hasNext()) {
                nmyVar = null;
                break;
            }
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet = (HwHealthBaseBarLineDataSet) it.next();
            if (hwHealthBaseBarLineDataSet instanceof nmy) {
                nmyVar = (nmy) hwHealthBaseBarLineDataSet;
                break;
            }
        }
        if (nmyVar == null) {
            eVar.d();
            return null;
        }
        if (this.mMarkerViewPosition == MARKER_VIEW_POSITION_UNSET) {
            eVar.d();
            return null;
        }
        this.mMarkerViewPosition.e = fArr[0];
        return nmyVar;
    }

    private float[] getFloats(HwHealthBaseScrollBarLineChart<T>.e eVar) {
        float[] fArr = {this.mMarkerViewPosition.f8860a, 0.0f};
        getTransformer(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY).pixelsToValue(fArr);
        if (!HwHealthBaseBarLineChart.MarkerViewSlidingMode.ACCORDING_DATA.equals(this.mMarkerViewSlidingMode) && !HwHealthBaseBarLineChart.MarkerViewSlidingMode.FORCE_ACCORDING_DATA.equals(this.mMarkerViewSlidingMode)) {
            return fArr;
        }
        float nearestValueForXValue = ((HwHealthBaseBarLineData) this.mData).getNearestValueForXValue(fArr[0], this, this.mMarkerViewSlidingMode);
        if (Math.abs(nearestValueForXValue - fArr[0]) < 1.0E-6d) {
            eVar.d();
            return null;
        }
        scrollMarkerViewToUnixTime(true, Math.round(nearestValueForXValue), eVar);
        return null;
    }

    private HwEntrys.HwDataEntry getSelectedBarData(nmy nmyVar, List<HwEntrys.HwDataEntry> list) {
        for (HwEntrys.HwDataEntry hwDataEntry : list) {
            if (hwDataEntry != null && hwDataEntry.getDataSet() == nmyVar) {
                return hwDataEntry;
            }
        }
        return null;
    }

    protected void scrollMarkerViewToUnixTime(boolean z, int i2, final HwHealthBaseScrollBarLineChart<T>.e eVar) {
        if (eVar == null) {
            LogUtil.b(TAG, "scrollMarkerViewToUnixTime scrollPagerAnimateListener == null");
            return;
        }
        this.mOnScrollPagerAnimateMgr.b(eVar);
        float[] fArr = {i2, 0.0f};
        this.mFirstAxisTransformer.pointValuesToPixel(fArr);
        final float f2 = fArr[0];
        final float f3 = this.mMarkerViewPosition.f8860a;
        if (f3 == f2) {
            eVar.d();
        } else if (z) {
            this.mAnimatorMarkerViewChange.cCw_(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float b2 = HwHealthBaseScrollBarLineChart.this.mAnimatorMarkerViewChange.b();
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart = HwHealthBaseScrollBarLineChart.this;
                    float f4 = f3;
                    hwHealthBaseScrollBarLineChart.highlightValuePx(f4 + ((f2 - f4) * b2), false);
                    HwHealthBaseScrollBarLineChart.this.postInvalidate();
                    if (b2 == 1.0f) {
                        eVar.d();
                    }
                }
            }, (int) ((Math.abs(f3 - f2) * 1000.0f) / HeartRateThresholdConfig.HEART_RATE_LIMIT));
        } else {
            eVar.d();
            highlightValuePx(f2, false);
        }
    }

    public void focusArea(List<nnl> list) {
        if (this.mRenderer instanceof IHwHealthDataRender) {
            ((IHwHealthDataRender) this.mRenderer).focusArea(list);
        }
    }

    public void disableFocusArea() {
        if (this.mRenderer instanceof IHwHealthDataRender) {
            ((IHwHealthDataRender) this.mRenderer).disableFocusArea();
        }
    }

    @Override // android.view.View
    public void setWillNotDraw(boolean z) {
        super.setWillNotDraw(z);
        this.mIsDisableDrawSelf = z;
    }

    @Override // com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart
    protected boolean isNeedLoadingAnimation() {
        if (this.mChartShowMode == ChartShowMode.NORMAL) {
            return false;
        }
        if (this.mScrollAdapter == null) {
            return true;
        }
        return super.isNeedLoadingAnimation() && this.mScrollAdapter.isTotalClearInShowRange();
    }

    public void reflesh(long j) {
        this.mIsRefreshToExpectedTime = true;
        setShowRange(getCurrentPagerStartX(j), acquireScrollAdapter().acquireRange(j));
        updateMatrixTouch();
        refresh();
        highlightValue(acquireHighLightTimeStamp(j), false);
        invalidateForce();
    }

    public int acquireHighLightTimeStamp(long j) {
        int i2 = AnonymousClass10.f8865a[this.mUnixChartType.ordinal()];
        if (i2 == 2) {
            j = jec.g(j);
        } else if (i2 == 3) {
            j = jec.g(j);
        } else if (i2 == 4) {
            j = nom.g(j);
        }
        return nom.f((int) TimeUnit.MILLISECONDS.toMinutes(j));
    }

    public boolean isMoveToLastDataStamp() {
        return this.mIsMoveToLastDataStamp;
    }

    public void setMoveToLastDataStamp(boolean z) {
        this.mIsMoveToLastDataStamp = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isFailed(boolean z) {
        if (z) {
            ((nnu) this.mXAxis).b(false);
            invalidateForce();
        }
    }

    public void resetYaxisAnimateValue() {
        this.mAxisRendererFirstParty.i();
        this.mAxisRendererSecondParty.i();
        this.mAxisRendererThirdParty.i();
    }

    protected void success(HwHealthBaseScrollBarLineChart<T>.e eVar, boolean z, int i2) {
        eVar.d();
        if (z) {
            ((nnu) this.mXAxis).b(false);
            invalidateForce();
            if (this.mChartShowMode == ChartShowMode.SCROLL_SCALE_MODE) {
                setShowRange(i2, this.mShowRange);
                this.mViewPortHandler.getMatrixTouch().set(new Matrix());
            }
        }
    }
}

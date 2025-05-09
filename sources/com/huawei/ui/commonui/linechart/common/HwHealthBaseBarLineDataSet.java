package com.huawei.ui.commonui.linechart.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthDatasContainer;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import defpackage.nng;
import defpackage.nnh;
import defpackage.nom;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public abstract class HwHealthBaseBarLineDataSet<T extends HwHealthBaseEntry> extends BarLineScatterCandleBubbleDataSet<T> implements IHwHealthBarLineDataSet<T> {
    private static final long MINUTE_TO_MILLIS = 60000;
    public static final long RING_ANIMATION_CHECK_DELAY = 500;
    private static final String STEP_DAY_DATA = "stepDayData";
    private static final String TAG = "HwHealthBaseBarLineDataSet";
    private static final String TIME_STAMP_SHOW_END = "timeStampShowEnd";
    private static final String TIME_STAMP_SHOW_START = "timeStampShowStart";
    private d mDataStatus;
    private List<T> mEntryVals;
    private float[] mForcedLabels;
    private HwHealthYAxis.HwHealthAxisDependency mHwHealthAxisDependency;
    private boolean mIsDataCalculated;
    private boolean mIsForcedLabels;
    private boolean mIsMaxForcedX;
    private boolean mIsMinForcedX;
    protected boolean mIsNeedLoad;
    private int mLabelCount;
    private float mMaxForcedValueX;
    private float mMinForcedValueX;
    private HwHealthBaseScrollBarLineChart.OnDataFillCallback mOnDataFillCallback;
    private Map<Long, IStorageModel> mOriginalVals;

    public abstract IHwHealthDatasContainer cacheDataContainer(HwHealthBaseBarLineChart hwHealthBaseBarLineChart);

    protected abstract T constructEntry(float f, IStorageModel iStorageModel);

    protected abstract void flushCachedDataContainer();

    public HwHealthBaseBarLineDataSet(List<T> list, String str) {
        super(list, str);
        this.mIsNeedLoad = false;
        this.mEntryVals = new ArrayList();
        this.mIsForcedLabels = false;
        this.mLabelCount = 0;
        this.mIsMinForcedX = false;
        this.mIsMaxForcedX = false;
        this.mDataStatus = new d();
        this.mOriginalVals = new HashMap();
        this.mIsDataCalculated = false;
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private boolean f8864a;
        private int b;

        private d() {
            this.f8864a = false;
            this.b = 0;
        }

        public int a() {
            int i;
            synchronized (this) {
                this.f8864a = true;
                i = this.b + 1;
                this.b = i;
            }
            return i;
        }

        public boolean e(int i) {
            synchronized (this) {
                if (i != this.b) {
                    return false;
                }
                this.f8864a = false;
                return true;
            }
        }

        public boolean d() {
            boolean z;
            synchronized (this) {
                z = this.f8864a;
            }
            return z;
        }
    }

    static class c implements ResponseCallback<Map<Long, IStorageModel>> {

        /* renamed from: a, reason: collision with root package name */
        private nnh f8863a;
        private WeakReference<HwHealthBaseScrollBarLineChart> b;
        private int c;
        private WeakReference<HwHealthBaseBarLineDataSet> d;
        private WeakReference<HwHealthBaseScrollBarLineChart.OnDataFillCallback> e;

        c(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, nnh nnhVar, int i, HwHealthBaseScrollBarLineChart.OnDataFillCallback onDataFillCallback) {
            this.d = new WeakReference<>(hwHealthBaseBarLineDataSet);
            this.b = new WeakReference<>(hwHealthBaseScrollBarLineChart);
            this.f8863a = nnhVar;
            this.c = i;
            this.e = new WeakReference<>(onDataFillCallback);
        }

        @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResult(int i, Map<Long, IStorageModel> map) {
            HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet = this.d.get();
            if (hwHealthBaseBarLineDataSet == null) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("err_code", Integer.valueOf(i));
            hashMap.put(HwHealthBaseBarLineDataSet.STEP_DAY_DATA, map);
            hashMap.put("queryId", Integer.valueOf(this.c));
            AsyncSelectorSerialize asyncSelectorSerialize = hwHealthBaseBarLineDataSet.getAsyncSelectorSerialize(this.e.get());
            asyncSelectorSerialize.add(hwHealthBaseBarLineDataSet.getAction(this.f8863a.c(), this.f8863a.e(), this.b.get(), asyncSelectorSerialize));
            asyncSelectorSerialize.add(hwHealthBaseBarLineDataSet.getAction(this.b.get(), hwHealthBaseBarLineDataSet.acquireEntryVals(), asyncSelectorSerialize));
            asyncSelectorSerialize.run(hashMap);
        }
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public boolean isQuerying() {
        return this.mDataStatus.d();
    }

    public void fillSetByDatabase(Context context, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, nnh nnhVar, HwHealthBaseScrollBarLineChart.OnDataFillCallback onDataFillCallback, IChartStorageHelper iChartStorageHelper) {
        DataInfos b = nnhVar.b();
        int h = nnhVar.h();
        int d2 = nnhVar.d();
        if (b.isMaintenancedBySportGroup() && b.isDayData() && !nom.p(h * 60000)) {
            return;
        }
        int a2 = this.mDataStatus.a();
        if (hwHealthBaseScrollBarLineChart != null) {
            hwHealthBaseScrollBarLineChart.acquireRingAnimationMgr().e(500L);
        }
        HwHealthChartHolder.b a3 = nnhVar.a();
        this.mOnDataFillCallback = onDataFillCallback;
        iChartStorageHelper.queryStepDayData(context, h * 60000, d2 * 60000, b, a3, new c(this, hwHealthBaseScrollBarLineChart, nnhVar, a2, onDataFillCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncSelectorSerialize.BaseAction getAction(final HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, final List<T> list, final AsyncSelectorSerialize asyncSelectorSerialize) {
        return new AsyncSelectorSerialize.BaseAction() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet.1
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.BaseAction, com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map == null) {
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart2 = hwHealthBaseScrollBarLineChart;
                    if (hwHealthBaseScrollBarLineChart2 != null) {
                        if (hwHealthBaseScrollBarLineChart2.getGlobalPointFlag()) {
                            if (nng.d(BaseApplication.getContext())) {
                                HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart3 = hwHealthBaseScrollBarLineChart;
                                hwHealthBaseScrollBarLineChart3.highlightValuePx(hwHealthBaseScrollBarLineChart3.acquireChartAnchor().f() + Utils.convertDpToPixel(26.0f), false);
                            } else {
                                HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart4 = hwHealthBaseScrollBarLineChart;
                                hwHealthBaseScrollBarLineChart4.highlightValuePx(hwHealthBaseScrollBarLineChart4.acquireChartAnchor().j() - Utils.convertDpToPixel(26.0f), false);
                            }
                        } else if (hwHealthBaseScrollBarLineChart.isMarkViewCenteredWhenEmpty()) {
                            HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart5 = hwHealthBaseScrollBarLineChart;
                            hwHealthBaseScrollBarLineChart5.highlightValuePx((hwHealthBaseScrollBarLineChart5.getViewPortHandler().contentLeft() + hwHealthBaseScrollBarLineChart.getViewPortHandler().contentRight()) / 2.0f, false);
                        } else if (hwHealthBaseScrollBarLineChart.getMarkerViewPosition() == null) {
                            hwHealthBaseScrollBarLineChart.highlightValue(hwHealthBaseScrollBarLineChart.acquireHighLightTimeStamp(System.currentTimeMillis()), false);
                        }
                        hwHealthBaseScrollBarLineChart.refresh();
                    }
                    asyncSelectorSerialize.postError();
                    return;
                }
                if (!(map.get(HwHealthBaseBarLineDataSet.STEP_DAY_DATA) instanceof Map)) {
                    LogUtil.h(HwHealthBaseBarLineDataSet.TAG, "execute arg.get(STEP_DAY_DATA) not instanceof Map");
                    return;
                }
                if (!map.containsKey(HwHealthBaseBarLineDataSet.TIME_STAMP_SHOW_START) || !map.containsKey(HwHealthBaseBarLineDataSet.TIME_STAMP_SHOW_END)) {
                    LogUtil.h(HwHealthBaseBarLineDataSet.TAG, "execute arg doesn't contain timeStampShowStart or timeStampShowEnd");
                    return;
                }
                HwHealthBaseBarLineDataSet.this.fillMoreData(list, (Map) map.get(HwHealthBaseBarLineDataSet.STEP_DAY_DATA), ((Integer) map.get(HwHealthBaseBarLineDataSet.TIME_STAMP_SHOW_START)).intValue(), ((Integer) map.get(HwHealthBaseBarLineDataSet.TIME_STAMP_SHOW_END)).intValue(), hwHealthBaseScrollBarLineChart);
                asyncSelectorSerialize.next(null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncSelectorSerialize.Action getAction(final int i, final int i2, final HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, final AsyncSelectorSerialize asyncSelectorSerialize) {
        return new AsyncSelectorSerialize.Action() { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet.5
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public int getFailedValue() {
                return 0;
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map == null) {
                    asyncSelectorSerialize.next(null);
                    return;
                }
                int intValue = ((Integer) map.get("err_code")).intValue();
                if (HwHealthBaseBarLineDataSet.this.mDataStatus.e(((Integer) map.get("queryId")).intValue())) {
                    HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart2 = hwHealthBaseScrollBarLineChart;
                    if (hwHealthBaseScrollBarLineChart2 != null) {
                        hwHealthBaseScrollBarLineChart2.acquireRingAnimationMgr().e();
                    }
                    Object obj = map.get(HwHealthBaseBarLineDataSet.STEP_DAY_DATA);
                    if (intValue != 0 || !(obj instanceof Map)) {
                        LogUtil.h(HwHealthBaseBarLineDataSet.TAG, "queryStepDayData failed");
                        asyncSelectorSerialize.next(null);
                        return;
                    }
                    HashMap hashMap = new HashMap(3);
                    hashMap.put(HwHealthBaseBarLineDataSet.STEP_DAY_DATA, (Map) obj);
                    hashMap.put(HwHealthBaseBarLineDataSet.TIME_STAMP_SHOW_START, Integer.valueOf(i));
                    hashMap.put(HwHealthBaseBarLineDataSet.TIME_STAMP_SHOW_END, Integer.valueOf(i2));
                    asyncSelectorSerialize.next(hashMap);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncSelectorSerialize getAsyncSelectorSerialize(final HwHealthBaseScrollBarLineChart.OnDataFillCallback onDataFillCallback) {
        return new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet.3
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
            public void onFailed(int i) {
                LogUtil.c(HwHealthBaseBarLineDataSet.TAG, "process fillBuffer end onFailed");
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                LogUtil.c(HwHealthBaseBarLineDataSet.TAG, "process fillBuffer end onSuccess");
                HwHealthBaseScrollBarLineChart.OnDataFillCallback onDataFillCallback2 = onDataFillCallback;
                if (onDataFillCallback2 != null) {
                    onDataFillCallback2.onResult();
                }
            }
        };
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x006b, code lost:
    
        if (r3 < r10) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x006d, code lost:
    
        if (r3 > r11) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
    
        r1 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void fillMoreData(java.util.List<T> r8, java.util.Map<java.lang.Long, com.huawei.ui.commonui.linechart.icommon.IStorageModel> r9, int r10, int r11, com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart r12) {
        /*
            r7 = this;
            java.util.Map<java.lang.Long, com.huawei.ui.commonui.linechart.icommon.IStorageModel> r0 = r7.mOriginalVals
            r0.clear()
            java.util.Map<java.lang.Long, com.huawei.ui.commonui.linechart.icommon.IStorageModel> r0 = r7.mOriginalVals
            r0.putAll(r9)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Set r9 = r9.entrySet()
            java.util.Iterator r9 = r9.iterator()
            r1 = -2147483647(0xffffffff80000001, float:-1.4E-45)
        L1a:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L71
            java.lang.Object r2 = r9.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            if (r2 != 0) goto L29
            goto L1a
        L29:
            java.lang.Object r3 = r2.getKey()
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            java.lang.Object r2 = r2.getValue()
            com.huawei.ui.commonui.linechart.icommon.IStorageModel r2 = (com.huawei.ui.commonui.linechart.icommon.IStorageModel) r2
            r5 = 60000(0xea60, double:2.9644E-319)
            long r3 = r3 / r5
            int r3 = (int) r3
            boolean r4 = r7 instanceof com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
            if (r4 == 0) goto L49
            r5 = r7
            com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet r5 = (com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet) r5
            int r3 = r5.acquireRangeCenterValue(r3)
        L49:
            int r5 = defpackage.nom.f(r3)
            float r5 = (float) r5
            com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry r5 = r7.constructEntry(r5, r2)
            r0.add(r5)
            if (r4 == 0) goto L69
            float r4 = defpackage.noy.c(r2)
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L69
            float r2 = defpackage.noy.a(r2)
            int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r2 != 0) goto L69
            goto L1a
        L69:
            if (r3 <= r1) goto L1a
            if (r3 < r10) goto L1a
            if (r3 > r11) goto L1a
            r1 = r3
            goto L1a
        L71:
            java.util.Collections.sort(r0)
            r8.clear()
            r8.addAll(r0)
            r8 = 1
            r7.mIsNeedLoad = r8
            r7.moveMarkerViewPos(r12, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet.fillMoreData(java.util.List, java.util.Map, int, int, com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart):void");
    }

    private void moveMarkerViewPos(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i) {
        if (hwHealthBaseScrollBarLineChart != null) {
            if (hwHealthBaseScrollBarLineChart.getGlobalPointFlag()) {
                if (nng.d(BaseApplication.getContext())) {
                    hwHealthBaseScrollBarLineChart.highlightValuePx(hwHealthBaseScrollBarLineChart.acquireChartAnchor().f() + Utils.convertDpToPixel(26.0f), false);
                } else {
                    hwHealthBaseScrollBarLineChart.highlightValuePx(hwHealthBaseScrollBarLineChart.acquireChartAnchor().j() - Utils.convertDpToPixel(26.0f), false);
                }
            } else if (hwHealthBaseScrollBarLineChart.getMarkerViewPosition() != null) {
                hwHealthBaseScrollBarLineChart.highlightValue(hwHealthBaseScrollBarLineChart.getMarkerViewPosition().e, false);
            } else if (i != -2147483647 && (!hwHealthBaseScrollBarLineChart.containsMarkViewShow() || hwHealthBaseScrollBarLineChart.isMoveToLastDataStamp())) {
                hwHealthBaseScrollBarLineChart.highlightValue(nom.f(i), false);
            } else if (i == -2147483647 && !hwHealthBaseScrollBarLineChart.containsMarkViewShow()) {
                hwHealthBaseScrollBarLineChart.highlightValuePx((hwHealthBaseScrollBarLineChart.getViewPortHandler().contentLeft() + hwHealthBaseScrollBarLineChart.getViewPortHandler().contentRight()) / 2.0f, false);
            }
            hwHealthBaseScrollBarLineChart.refresh();
            flushCachedDataContainer();
        }
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public Map<Long, IStorageModel> acquireOriginalVals() {
        return this.mOriginalVals;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public List<T> acquireEntryVals() {
        return this.mEntryVals;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        return acquireShowRangeValue(hwHealthBaseScrollBarLineChart, -3.4028235E38f, true);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        return acquireShowRangeValue(hwHealthBaseScrollBarLineChart, Float.MAX_VALUE, false);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireOlderPagerMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        return acquireOlderPagerValue(-3.4028235E38f, hwHealthBaseScrollBarLineChart, true);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireOlderPagerMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        return acquireOlderPagerValue(Float.MAX_VALUE, hwHealthBaseScrollBarLineChart, false);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireNewerPagerMaxValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        return acquireNewerPagerValue(hwHealthBaseScrollBarLineChart, -3.4028235E38f);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireRangePagerMaxValue(float f, float f2) {
        float f3 = -3.4028235E38f;
        for (T t : this.mEntries) {
            if (t != null && t.getX() >= f && t.getX() <= f2 && t.getY() > f3) {
                f3 = t.getY();
            }
        }
        return f3;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireRangePagerMinValue(float f, float f2) {
        float f3 = Float.MAX_VALUE;
        for (T t : this.mEntryVals) {
            if (t != null && t.getX() >= f && t.getX() <= f2 && t.getY() < f3) {
                f3 = t.getY();
            }
        }
        return f3;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireNewerPagerMinValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        return acquireNewerPagerValue(hwHealthBaseScrollBarLineChart, Float.MAX_VALUE);
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float computeDynamicBorderMax(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f, float f2) {
        if (hwHealthBaseScrollBarLineChart != null) {
            return hwHealthBaseScrollBarLineChart.computeDynamicBorderMax(this, f, f2);
        }
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float computeDynamicBorderMin(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f, float f2) {
        if (hwHealthBaseScrollBarLineChart != null) {
            return hwHealthBaseScrollBarLineChart.computeDynamicBorderMin(this, f, f2);
        }
        return 0.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeAverageValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return 0.0f;
        }
        float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
        float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
        int i = 0;
        float f = 0.0f;
        for (T t : this.mEntries) {
            if (t != null && t.getX() >= acquireShowRangeMinimum && t.getX() <= acquireShowRangeMaximum) {
                f += t.getY();
                i++;
            }
        }
        if (i == 0) {
            return 0.0f;
        }
        return f / i;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float acquireShowRangeRatioedValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, HwHealthBaseScrollBarLineChart.DataRatioProvider dataRatioProvider) {
        float f = 0.0f;
        if (hwHealthBaseScrollBarLineChart != null && dataRatioProvider != null) {
            float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
            float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
            for (T t : this.mEntries) {
                if (t != null && t.getX() >= acquireShowRangeMinimum && t.getX() <= acquireShowRangeMaximum) {
                    f += t.getY() * dataRatioProvider.computeRatio((int) t.getX());
                }
            }
        }
        return f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public boolean isDataCalculated() {
        return this.mIsDataCalculated;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void makeDataCalculated(boolean z) {
        this.mIsDataCalculated = z;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float calculateLogicByShowRange(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, LogicalUnit logicalUnit) {
        if (hwHealthBaseScrollBarLineChart == null || logicalUnit == null) {
            return 0.0f;
        }
        float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
        float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
        ArrayList arrayList = new ArrayList();
        for (T t : acquireEntryVals()) {
            if (t != null) {
                if (acquireShowRangeMaximum - acquireShowRangeMinimum < 1440.0f) {
                    Date date = new Date((((long) acquireShowRangeMinimum) * 60000) + 1388505600000L);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int i = calendar.get(1);
                    int i2 = calendar.get(2);
                    int i3 = calendar.get(5);
                    Date date2 = new Date((((long) t.getX()) * 60000) + 1388505600000L);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(date2);
                    int i4 = calendar2.get(1);
                    int i5 = calendar2.get(2);
                    int i6 = calendar2.get(5);
                    if (i == i4 && i2 == i5 && i3 == i6) {
                        arrayList.add(t);
                    }
                } else if (t.getX() >= acquireShowRangeMinimum && t.getX() <= acquireShowRangeMaximum) {
                    arrayList.add(t);
                }
            }
        }
        return logicalUnit.calculate(arrayList);
    }

    public float acquireOlderPagerValue(float f, HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, boolean z) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return f;
        }
        float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
        float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
        float f2 = acquireShowRangeMaximum - acquireShowRangeMinimum;
        for (T t : this.mEntries) {
            if (t != null && t.getX() >= acquireShowRangeMinimum - f2 && t.getX() <= acquireShowRangeMaximum - f2) {
                if (z) {
                    if (t.getY() > f) {
                        f = t.getY();
                    }
                } else if (t.getY() < f) {
                    f = t.getY();
                }
            }
        }
        return f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public int getLabelCount() {
        return this.mLabelCount;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void setLabelCount(int i, boolean z) {
        this.mLabelCount = i;
        this.mIsForcedLabels = z;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void setForcedLabels(float[] fArr) {
        if (fArr != null) {
            this.mForcedLabels = (float[]) fArr.clone();
        }
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float[] getForcedLabels() {
        float[] fArr = this.mForcedLabels;
        if (fArr != null) {
            return (float[]) fArr.clone();
        }
        return null;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public boolean isForcedLabelsCount() {
        return this.mIsForcedLabels;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public HwHealthYAxis.HwHealthAxisDependency getAxisDependencyExt() {
        return this.mHwHealthAxisDependency;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void setAxisDependency(HwHealthYAxis.HwHealthAxisDependency hwHealthAxisDependency) {
        this.mHwHealthAxisDependency = hwHealthAxisDependency;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void setXMaxForcedValue(float f) {
        this.mIsMaxForcedX = true;
        this.mMaxForcedValueX = f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float getXMaxForcedValue() {
        return this.mMaxForcedValueX;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public void setXMinForcedValue(float f) {
        this.mIsMinForcedX = true;
        this.mMinForcedValueX = f;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public float getXMinForcedValue() {
        return this.mMinForcedValueX;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public boolean isXMaxForced() {
        return this.mIsMaxForcedX;
    }

    @Override // com.huawei.ui.commonui.linechart.icommon.IHwHealthBarLineDataSet
    public boolean isXMinForced() {
        return this.mIsMinForcedX;
    }

    public float acquireShowRangeValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f, boolean z) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return f;
        }
        float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
        float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
        for (T t : this.mEntries) {
            if (t != null && t.getX() >= acquireShowRangeMinimum && t.getX() <= acquireShowRangeMaximum) {
                if (z) {
                    if (t.getY() > f) {
                        f = t.getY();
                    }
                } else if (t.getY() < f) {
                    f = t.getY();
                }
            }
        }
        return f;
    }

    public float acquireNewerPagerValue(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, float f) {
        if (hwHealthBaseScrollBarLineChart == null) {
            return f;
        }
        float acquireShowRangeMinimum = hwHealthBaseScrollBarLineChart.acquireShowRangeMinimum();
        float acquireShowRangeMaximum = hwHealthBaseScrollBarLineChart.acquireShowRangeMaximum();
        float f2 = acquireShowRangeMaximum - acquireShowRangeMinimum;
        for (T t : this.mEntries) {
            if (t != null && t.getX() >= acquireShowRangeMinimum + f2 && t.getX() <= acquireShowRangeMaximum + f2 && t.getY() > f) {
                f = t.getY();
            }
        }
        return f;
    }
}

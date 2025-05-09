package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.KnitBloodOxygenDetailActivity;
import com.tencent.open.apireq.BaseResp;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/* loaded from: classes9.dex */
public class oim extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private static final int f15700a = 2131430049;
    private final b b;
    private View c;
    private List<HwHealthBarEntry> d;
    private List<Integer> e;
    private boolean f;
    private final d g;
    private boolean h;
    private boolean i;
    private boolean j;
    private HwHealthLineDataSet k;
    private boolean l;
    private int m;
    private HealthSeekBarExtend n;
    private long o;

    static /* synthetic */ boolean e(int i, int i2, int i3) {
        return i2 - i <= i3;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839618_res_0x7f020842;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131427614_res_0x7f0b011e : R.drawable.marketing_default_img_bloodoxygen;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == 18;
    }

    static class b extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f15701a;
        private List<Integer> c;
        private final WeakReference<oim> d;
        private List<Integer> e;

        b(oim oimVar) {
            super("FunctionSetBloodOxygenCardReader", null);
            this.d = new WeakReference<>(oimVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(HiHealthData hiHealthData, List<Integer> list, int i, List<Integer> list2) {
            LogUtil.a("FunctionSetBloodOxygenCardReader", BleConstants.SAVE_HEALTH_DATA);
            if (hiHealthData == null) {
                oim.setHasCardData(this.d, false);
                oim.saveDataFromHealthApi("FunctionSetBloodOxygenCardReader", this.d, (HiHealthData) null);
                return;
            }
            oim.setHasCardData(this.d, true);
            HiHealthData hiHealthData2 = new HiHealthData();
            hiHealthData2.putInt("point_value", hiHealthData.getIntValue());
            hiHealthData2.setStartTime(hiHealthData.getStartTime());
            hiHealthData2.putInt("_u", hiHealthData.toString().hashCode());
            hiHealthData2.putInt("_t", i);
            this.c = list;
            if (list != null) {
                hiHealthData2.putString("_", marshallListToString(list));
            }
            this.e = list2;
            if (koq.c(list2)) {
                hiHealthData2.putString("_a", marshallListToString(list2));
            }
            oim.saveDataFromHealthApi("FunctionSetBloodOxygenCardReader", this.d, hiHealthData2);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            LogUtil.a("FunctionSetBloodOxygenCardReader", "handleCacheData");
            oim oimVar = this.d.get();
            if (oimVar == null) {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "handleCacheData bloodOxygenCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                oim.setHasCardData(this.d, false);
                if (this.f15701a && z) {
                    LogUtil.a("FunctionSetBloodOxygenCardReader", "handleCacheData data is null and mIsShowData is true and isNewData is true");
                    Message obtainMessage = oimVar.g.obtainMessage();
                    obtainMessage.what = 4;
                    obtainMessage.obj = oimVar.buildEmptyCardBean();
                    oimVar.g.sendMessage(obtainMessage);
                    oimVar.o = 0L;
                    return;
                }
                return;
            }
            String b = SharedPreferenceManager.b(oimVar.mContext, String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS");
            oimVar.j = (TextUtils.isEmpty(b) || "false".equals(b)) ? false : true;
            this.f15701a = true;
            if (oimVar.h) {
                if (!z) {
                    this.c = new ArrayList(0);
                    unmarshallListFromString(hiHealthData.getString("_"), this.c);
                    if (oimVar.j) {
                        this.e = new ArrayList(0);
                        unmarshallListFromString(hiHealthData.getString("_a"), this.e);
                    }
                }
                oimVar.d(this.c);
                oimVar.a(this.e);
            }
            oimVar.setHasCardData(true);
            oimVar.d(hiHealthData.getInt("point_value"), hiHealthData.getStartTime(), hiHealthData.getInt("_u"), hiHealthData.getInt("_t"), z);
        }
    }

    public static class d extends BaseHandler<oim> {
        private d(oim oimVar) {
            super(Looper.getMainLooper(), oimVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: daX_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oim oimVar, Message message) {
            int i = message.what;
            if (i == 4) {
                LogUtil.a("FunctionSetBloodOxygenCardReader", "handleMessageWhenReferenceNotNull MSG_UPDATE_BLOOD_OXYGEN");
                oimVar.notifyItemChanged((FunctionSetBean) message.obj);
            } else if (i == 2333) {
                oimVar.f();
            } else {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "handleMessageWhenReferenceNotNull what ", Integer.valueOf(i));
            }
        }
    }

    public oim(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetBloodOxygenCardReader", cardConfig);
        this.e = null;
        this.h = false;
        this.j = false;
        this.l = false;
        this.b = new b(this);
        this.i = true;
        this.f = true;
        LogUtil.a("FunctionSetBloodOxygenCardReader", "FunctionSetBloodOxygenCardReader create, ", Integer.valueOf(hashCode()));
        this.g = new d();
        LogUtil.a("FunctionSetBloodOxygenCardReader", "FunctionSetBloodOxygenCardReader create finish, ", Integer.valueOf(hashCode()));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.i = false;
        ReleaseLogUtil.e("TimeEat_FunctionSetBloodOxygenCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isNeedRefreshOnResume() {
        if (!this.i) {
            String b2 = SharedPreferenceManager.b(this.mContext, String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS");
            boolean z = (TextUtils.isEmpty(b2) || "false".equals(b2)) ? false : true;
            boolean z2 = this.j;
            if (z2 != z) {
                LogUtil.a("FunctionSetBloodOxygenCardReader", "BLOOD_OXYGEN_CURVE_STATUS, mIsCurveOpen=", Boolean.valueOf(z2));
                readCardData();
                this.j = z;
                return false;
            }
        }
        return super.isNeedRefreshOnResume();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public int getExtraWidth() {
        return ContextCompat.getDrawable(this.mContext, f15700a).getIntrinsicWidth();
    }

    private void g() {
        LogUtil.a("FunctionSetBloodOxygenCardReader", "initSeekBar mSeekBarExtend is null");
        HealthSeekBarExtend healthSeekBarExtend = new HealthSeekBarExtend(this.mContext);
        this.n = healthSeekBarExtend;
        healthSeekBarExtend.setThumb(ContextCompat.getDrawable(this.mContext, f15700a));
        this.n.setProgressDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.health_halthdata_weight_seekbar_background));
        this.n.setRulerSrc(ContextCompat.getDrawable(this.mContext, R.drawable._2131428453_res_0x7f0b0465));
        this.n.setMax(460);
        this.n.setProgress(this.m);
    }

    private void c() {
        LogUtil.a("FunctionSetBloodOxygenCardReader", "initChartView mCardBloodOxygenView");
        if (this.c == null) {
            this.c = View.inflate(this.mContext, R.layout.card_blood_oxygen_view, null);
        }
        d();
        a();
    }

    private void d() {
        if (this.d == null) {
            LogUtil.h("FunctionSetBloodOxygenCardReader", "configBarChart mBarEntryValues is null");
            return;
        }
        HwHealthBarChart hwHealthBarChart = (HwHealthBarChart) this.c.findViewById(R.id.blood_oxygen_barchart);
        if (hwHealthBarChart != null) {
            hwHealthBarChart.setTouchEnabled(false);
        }
        HwHealthBarDataSet hwHealthBarDataSet = new HwHealthBarDataSet(this.d, "bar breif", "bar label", "bar unit");
        hwHealthBarDataSet.b(HwHealthBarDataSet.DrawColorMode.DATA_COLOR);
        hwHealthBarDataSet.setBarDrawWidth(0.5f);
        hwHealthBarDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        if (hwHealthBarChart != null) {
            hwHealthBarChart.getXAxis().setEnabled(false);
            HwHealthYAxis axisFirstParty = hwHealthBarChart.getAxisFirstParty();
            axisFirstParty.setDrawLabels(false);
            axisFirstParty.setDrawGridLines(false);
            axisFirstParty.setDrawAxisLine(false);
            axisFirstParty.setAxisMinimum(0.0f);
            axisFirstParty.setAxisMaximum(100.0f);
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(hwHealthBarDataSet);
            hwHealthBarChart.setData(new nmz(arrayList));
            hwHealthBarChart.refresh();
        }
    }

    private void a() {
        HwHealthLineChart hwHealthLineChart = (HwHealthLineChart) this.c.findViewById(R.id.blood_oxygen_linechart);
        if (hwHealthLineChart == null) {
            LogUtil.b("FunctionSetBloodOxygenCardReader", "lineChart is null");
            return;
        }
        Object[] objArr = new Object[4];
        objArr[0] = "configLineChart mIsCurveOpen:";
        objArr[1] = Boolean.valueOf(this.j);
        objArr[2] = " mLineDataSet is null?";
        objArr[3] = Boolean.valueOf(this.k == null);
        LogUtil.a("FunctionSetBloodOxygenCardReader", objArr);
        if (this.k == null || !this.j) {
            hwHealthLineChart.setVisibility(8);
            return;
        }
        hwHealthLineChart.setVisibility(0);
        hwHealthLineChart.setTouchEnabled(false);
        hwHealthLineChart.getXAxis().setEnabled(false);
        hwHealthLineChart.getDescription().setEnabled(false);
        HwHealthYAxis axisFirstParty = hwHealthLineChart.getAxisFirstParty();
        axisFirstParty.setDrawLabels(false);
        axisFirstParty.setDrawGridLines(false);
        axisFirstParty.setDrawAxisLine(false);
        hwHealthLineChart.disableLabelsForce();
        ArrayList arrayList = new ArrayList(0);
        arrayList.add(this.k);
        hwHealthLineChart.setData(new now(arrayList));
        hwHealthLineChart.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("FunctionSetBloodOxygenCardReader", "initViewData mIsContinuousBloodOxygen is ", Boolean.valueOf(this.h));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(18);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new FunctionSetBeanReader.c("FunctionSetBloodOxygenCardReader", this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(double d2, long j, int i, int i2, boolean z) {
        this.o = j;
        setBiHasData(j);
        setBiHasDataType(this.h ? "continuous" : "single");
        String b2 = owm.b(j);
        LogUtil.a("FunctionSetBloodOxygenCardReader", "refreshBloodOxygenDataAndTime mIsContinuousBloodOxygen= ", Boolean.valueOf(this.h), " ,mLastDataTime= ", Long.valueOf(j), " ,lastBloodOxygen= ", Double.valueOf(d2), " ,mIsCurveOpen= ", Boolean.valueOf(this.j), ", altitude= ", Integer.valueOf(i2), ", uniqueCode= ", Integer.valueOf(i), ", isNewData= ", Boolean.valueOf(z));
        if (!this.h) {
            this.m = b(nrm.e(d2));
        }
        String e = UnitUtil.e(d2, 2, 0);
        FunctionSetBean.a aVar = new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_hw_health_blood_oxygen));
        aVar.a(c(e));
        aVar.c(b2);
        aVar.a(FunctionSetType.BLOOD_OXYGEN_CARD);
        aVar.b(FunctionSetBean.ViewType.DATA_VIEW);
        aVar.d(this.mContext);
        aVar.e(this.h + "," + this.j);
        if (this.h && this.j && i2 > -1001) {
            aVar.b(this.mContext.getResources().getQuantityString(R.plurals._2130903093_res_0x7f030035, i2, UnitUtil.e(i2, 1, 0)));
            this.l = true;
        }
        FunctionSetBean c2 = aVar.c();
        c2.c(i);
        if (z) {
            Message obtainMessage = this.g.obtainMessage();
            obtainMessage.what = 4;
            obtainMessage.obj = c2;
            this.g.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    private CharSequence c(String str) {
        return owm.e("[\\d\\-]", str);
    }

    private int b(int i) {
        LogUtil.c("FunctionSetBloodOxygenCardReader", "the last progress data is ", Integer.valueOf(i));
        if (i < 0) {
            return 0;
        }
        if (i > 460) {
            return 460;
        }
        LogUtil.c("FunctionSetBloodOxygenCardReader", "the progress is normal");
        return i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetBloodOxygenCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetBloodOxygenCardReader", "readCardData");
        if (this.f) {
            this.f = false;
            jqi.a().getSwitchSetting("custom.blood.oxygen.switch", new IBaseResponseCallback() { // from class: oio
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    oim.this.b(i, obj);
                }
            });
        } else if (this.h) {
            e();
        } else {
            b();
        }
    }

    /* synthetic */ void b(int i, Object obj) {
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            LogUtil.a("FunctionSetBloodOxygenCardReader", "getSwitchSetting buttonSwitch ", str);
            this.h = "1".equals(str);
        } else {
            LogUtil.h("FunctionSetBloodOxygenCardReader", "getSwitchSetting errorCode ", Integer.valueOf(i));
        }
        this.g.sendEmptyMessage(2333);
        readCardData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiHealthData hiHealthData, List<HiHealthData> list, int i, List<HiHealthData> list2) {
        List<Long> d2 = d(hiHealthData);
        TreeMap<Long, Integer> a2 = a(d2, list);
        ArrayList arrayList = null;
        List<List<Integer>> e = koq.c(list2) ? e(d2, a2, list2) : null;
        ArrayList arrayList2 = new ArrayList(a2.values());
        d(arrayList2, e);
        if (koq.c(e)) {
            arrayList = new ArrayList(0);
            for (List<Integer> list3 : e) {
                for (int size = list3.size() - 1; size >= 0; size--) {
                    arrayList.add(Integer.valueOf(list3.get(size).intValue() + 1001));
                }
            }
        }
        this.b.c(hiHealthData, arrayList2, i, arrayList);
    }

    private void d(List<Integer> list, List<List<Integer>> list2) {
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext() && it.next().intValue() == 0) {
            it.remove();
            i++;
        }
        for (int i2 = 0; i2 < i; i2++) {
            list.add(0);
        }
        if (koq.c(list2)) {
            Collections.reverse(list2);
            Iterator<List<Integer>> it2 = list2.iterator();
            for (int i3 = i; i3 > 0 && it2.hasNext(); i3--) {
                it2.next();
                it2.remove();
            }
            for (int i4 = 0; i4 < i; i4++) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(BaseResp.CODE_QQ_LOW_VERSION));
                list2.add(arrayList);
            }
        }
    }

    private List<Long> d(HiHealthData hiHealthData) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hiHealthData.getStartTime());
        if (calendar.get(12) >= 30) {
            calendar.set(12, 30);
        } else {
            calendar.set(12, 0);
        }
        calendar.set(13, 0);
        calendar.set(14, 0);
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        ArrayList arrayList = new ArrayList(16);
        BigDecimal valueOf = BigDecimal.valueOf(calendar.getTimeInMillis());
        arrayList.add(Long.valueOf(valueOf.longValue()));
        for (int i = 1; i < 16; i++) {
            valueOf = valueOf.subtract(BigDecimal.valueOf(1800000L));
            long longValue = valueOf.longValue();
            if (longValue < t) {
                break;
            }
            arrayList.add(Long.valueOf(longValue));
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < 16 - size; i2++) {
            calendar.add(12, 30);
            arrayList.add(0, Long.valueOf(calendar.getTimeInMillis()));
        }
        return arrayList;
    }

    private TreeMap<Long, Integer> a(List<Long> list, List<HiHealthData> list2) {
        TreeMap<Long, Integer> treeMap = new TreeMap<>((Comparator<? super Long>) new Comparator() { // from class: oiq
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((Long) obj).longValue(), ((Long) obj2).longValue());
                return compare;
            }
        });
        Iterator<Long> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            int i2 = 0;
            int i3 = 0;
            while (i < list2.size()) {
                HiHealthData hiHealthData = list2.get(i);
                if (hiHealthData.getStartTime() < longValue) {
                    break;
                }
                i++;
                i2 += hiHealthData.getIntValue();
                i3++;
            }
            if (i3 == 0) {
                treeMap.put(Long.valueOf(longValue), 0);
            } else {
                treeMap.put(Long.valueOf(longValue), Integer.valueOf(Math.round(i2 / i3)));
            }
        }
        return treeMap;
    }

    private List<List<Integer>> e(List<Long> list, TreeMap<Long, Integer> treeMap, List<HiHealthData> list2) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(0);
        Iterator<Long> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            while (i < list2.size()) {
                HiHealthData hiHealthData = list2.get(i);
                if (hiHealthData.getStartTime() < longValue) {
                    break;
                }
                i++;
                arrayList2.add(Integer.valueOf(hiHealthData.getIntValue()));
            }
            ArrayList arrayList3 = new ArrayList(3);
            if (arrayList2.isEmpty()) {
                arrayList3.add(Integer.valueOf(BaseResp.CODE_QQ_LOW_VERSION));
                arrayList.add(arrayList3);
            } else if (arrayList2.size() > 3) {
                arrayList3.add((Integer) arrayList2.get(0));
                arrayList3.add((Integer) arrayList2.get(arrayList2.size() / 2));
                arrayList3.add((Integer) arrayList2.get(arrayList2.size() - 1));
                arrayList.add(arrayList3);
            } else {
                arrayList3.addAll(arrayList2);
                arrayList.add(arrayList3);
            }
            arrayList2.clear();
        }
        return arrayList;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        if (koq.b(list)) {
            LogUtil.h("FunctionSetBloodOxygenCardReader", "updateSuccessList successList is empty");
        } else {
            this.e = list;
            LogUtil.a("FunctionSetBloodOxygenCardReader", "updateSuccessList mBloodOxygenSuccessList size ", Integer.valueOf(list.size()));
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetBloodOxygenCardReader", "createCardView mIsContinuousBloodOxygen ", Boolean.valueOf(this.h));
        if (this.h) {
            c();
            return this.c;
        }
        g();
        return this.n;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (nsn.a(500)) {
            LogUtil.h("FunctionSetBloodOxygenCardReader", "onCardViewClickListener is fast click");
            return;
        }
        LogUtil.a("FunctionSetBloodOxygenCardReader", "onCardViewClickListener");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("from", "1");
        String value = AnalyticsValue.HEALTH_HOME_BLOOD_OXYGEN_DETAIL_2010099.value();
        if (!this.hasCardData) {
            hashMap.put("click", "0");
        } else {
            hashMap.put("click", "1");
        }
        if (this.l) {
            hashMap.put("click", "2");
        }
        ixx.d().d(this.mContext, value, hashMap, 0);
        try {
            ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).clickRecord(Integer.parseInt(value), getHuid());
        } catch (NumberFormatException e) {
            LogUtil.b("FunctionSetBloodOxygenCardReader", "onCardViewClickListener exception ", LogAnonymous.b((Throwable) e));
        }
        Intent intent = new Intent(this.mContext, (Class<?>) KnitBloodOxygenDetailActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("key_bundle_health_last_data_time", this.o);
        gnm.aPB_(this.mContext, intent);
        nsn.q();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.e)) {
            LogUtil.h("FunctionSetBloodOxygenCardReader", "onDestroy mBloodOxygenSuccessList is empty");
        } else {
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.e, new FunctionSetBeanReader.b("FunctionSetBloodOxygenCardReader", "onDestroy unSubscribeBloodOxygenData isSuccess :"));
        }
        d dVar = this.g;
        if (dVar == null) {
            LogUtil.h("FunctionSetBloodOxygenCardReader", "onDestroy mHandler is null");
        } else {
            dVar.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<Integer> list) {
        HwHealthBarEntry hwHealthBarEntry;
        if (koq.b(list)) {
            return;
        }
        LogUtil.a("FunctionSetBloodOxygenCardReader", "initBarChart size ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(16);
        Pair<Integer, Integer> daW_ = daW_(list);
        for (int i = 0; i < list.size(); i++) {
            int intValue = list.get(i).intValue();
            if (intValue > 0) {
                int e = e(intValue);
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(daV_(daW_, list.get(i).intValue()), e, e));
                LogUtil.c("FunctionSetBloodOxygenCardReader", "initBarChart data value ", Integer.valueOf(intValue));
            } else {
                int color = ContextCompat.getColor(this.mContext, R.color._2131297799_res_0x7f090607);
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(20.0f, color, color));
            }
            arrayList.add(hwHealthBarEntry);
        }
        this.d = arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Integer> list) {
        if (!this.j || koq.b(list)) {
            this.k = null;
            return;
        }
        LogUtil.a("FunctionSetBloodOxygenCardReader", "setLineDataSet size ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(0);
        for (int i = 0; i < list.size(); i++) {
            float intValue = list.get(i).intValue();
            if (intValue > 0.0f) {
                arrayList.add(new HwHealthBaseEntry(i, intValue));
            }
        }
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, "line brief", "line label", "line unit");
        hwHealthLineDataSet.e(1);
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: oip
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public final boolean drawLine(int i2, int i3, int i4) {
                return oim.e(i2, i3, i4);
            }
        });
        hwHealthLineDataSet.setColor(ContextCompat.getColor(this.mContext, R.color._2131296501_res_0x7f0900f5));
        hwHealthLineDataSet.b(Color.argb(102, 0, 199, a.A), Color.argb(0, 0, 199, a.A), true);
        hwHealthLineDataSet.setDrawFilled(true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        this.k = hwHealthLineDataSet;
    }

    private float daV_(Pair<Integer, Integer> pair, int i) {
        if (((Integer) pair.first).intValue() == ((Integer) pair.second).intValue()) {
            return 100;
        }
        return 40 + ((i - r0) * (60 / (r3 - r0)));
    }

    private Pair<Integer, Integer> daW_(List<Integer> list) {
        int i = 100;
        int i2 = 0;
        for (Integer num : list) {
            if (num.intValue() != 0) {
                if (num.intValue() < i) {
                    i = num.intValue();
                }
                if (num.intValue() > i2) {
                    i2 = num.intValue();
                }
            }
        }
        return new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
    }

    private int e(int i) {
        return ContextCompat.getColor(this.mContext, i >= 90 ? R.color._2131296506_res_0x7f0900fa : i >= 70 ? R.color._2131296510_res_0x7f0900fe : R.color._2131296512_res_0x7f090100);
    }

    private void b() {
        HiDataReadOption e = e(0L, System.currentTimeMillis(), 1);
        e.setType(new int[]{2103});
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(e, new c(0));
    }

    private void e() {
        HiDataReadOption e = e(0L, System.currentTimeMillis(), 0);
        e.setType(new int[]{2103, DicDataTypeUtil.DataType.ALTITUDE_TYPE.value()});
        HiHealthManager.d(this.mContext).readHiHealthDataPro(HiDataReadProOption.builder().e(e).d(true).e(), new c(1));
    }

    private HiDataReadOption e(long j, long j2, int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(i);
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        hiDataReadOption.setSortOrder(1);
        return hiDataReadOption;
    }

    static class c implements HiDataReadResultListener {
        private final WeakReference<oim> b;
        private final int e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private c(oim oimVar, int i) {
            this.b = new WeakReference<>(oimVar);
            this.e = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            oim oimVar = this.b.get();
            if (oimVar == null) {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "onResult reader is null");
                return;
            }
            LogUtil.a("FunctionSetBloodOxygenCardReader", "onResult, errorCode= ", Integer.valueOf(i));
            boolean z = obj instanceof SparseArray;
            int i3 = BaseResp.CODE_QQ_LOW_VERSION;
            if (!z) {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "data not instanceof SparseArray");
                oimVar.b.c(null, null, BaseResp.CODE_QQ_LOW_VERSION, null);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            Object obj2 = sparseArray.get(2103);
            if (!(obj2 instanceof List)) {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "bloodOxygenData not instanceof List");
                oimVar.b.c(null, null, BaseResp.CODE_QQ_LOW_VERSION, null);
                return;
            }
            List list = (List) obj2;
            if (list.isEmpty()) {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "bloodOxygenData isEmpty");
                oimVar.b.c(null, null, BaseResp.CODE_QQ_LOW_VERSION, null);
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            SharedPreferenceManager.c("privacy_center", "blood_oxygen", String.valueOf(hiHealthData.getStartTime()));
            if (this.e == 0) {
                oimVar.b.c(hiHealthData, null, BaseResp.CODE_QQ_LOW_VERSION, null);
                return;
            }
            Object obj3 = sparseArray.get(DicDataTypeUtil.DataType.ALTITUDE_TYPE.value());
            List list2 = obj3 instanceof List ? (List) obj3 : null;
            if (koq.c(list2)) {
                i3 = ((HiHealthData) list2.get(0)).getIntValue();
            } else {
                LogUtil.h("FunctionSetBloodOxygenCardReader", "altitudes is null");
            }
            oimVar.b(hiHealthData, list, i3, list2);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_blood_oxygen);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(this.mContext) ? R.color._2131297758_res_0x7f0905de : R.color._2131297757_res_0x7f0905dd;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.b;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetBloodOxygenCardReader";
    }
}

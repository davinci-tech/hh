package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
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
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.main.stories.fitness.activity.heartrate.HeartRateDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.KnitHeartRateActivity;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class ojc extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private HwHealthLineDataSet f15716a;
    private HwHealthLineChart b;
    private final a c;
    private final c d;
    private List<Float> e;
    private long g;
    private final Resources h;
    private List<Integer> j;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839600_res_0x7f020830;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable.heart_default_img_big : R.drawable.marketing_default_img_heartrate;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == 5;
    }

    static class e implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<ojc> f15718a;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public e(ojc ojcVar) {
            this.f15718a = new WeakReference<>(ojcVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (this.f15718a.get() == null) {
                LogUtil.b("FunctionSetHeartRateReader", "mRef is null");
                return;
            }
            ojc ojcVar = this.f15718a.get();
            HashMap hashMap = new HashMap(10);
            d(hashMap, ojcVar.getList(obj, 2018));
            d(hashMap, ojcVar.getList(obj, 2105));
            d(hashMap, ojcVar.getList(obj, 2002));
            ArrayList arrayList = new ArrayList(10);
            arrayList.addAll(hashMap.values());
            LogUtil.h("FunctionSetHeartRateReader", "readCardData onResult dataList size = ", Integer.valueOf(arrayList.size()));
            if (koq.b(arrayList)) {
                LogUtil.h("FunctionSetHeartRateReader", "readCardData onResult dataList is empty");
                ojcVar.c.b(null, null, 0);
                return;
            }
            LogUtil.a("FunctionSetHeartRateReader", "readCardData success");
            Collections.sort(arrayList, new Comparator() { // from class: oje
                @Override // java.util.Comparator
                public final int compare(Object obj2, Object obj3) {
                    int compare;
                    compare = Long.compare(((HiHealthData) obj2).getStartTime(), ((HiHealthData) obj3).getStartTime());
                    return compare;
                }
            });
            HiHealthData hiHealthData = (HiHealthData) arrayList.get(arrayList.size() - 1);
            long t = HiDateUtil.t(hiHealthData.getStartTime());
            ojcVar.dbf_((SparseArray) obj, t);
            if (arrayList.size() == 1) {
                ojcVar.e(hiHealthData, arrayList, t);
                return;
            }
            long t2 = HiDateUtil.t(((HiHealthData) arrayList.get(arrayList.size() - 2)).getStartTime());
            if (t != t2) {
                arrayList.remove(0);
            }
            ojcVar.e(hiHealthData, arrayList, t2);
        }

        private void d(Map<Long, HiHealthData> map, List<HiHealthData> list) {
            if (koq.b(list)) {
                LogUtil.h("FunctionSetHeartRateReader", "processHeartDate heartRateLists isEmpty");
                return;
            }
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData == null) {
                    LogUtil.h("FunctionSetHeartRateReader", "loadDayValue healthData is null");
                } else {
                    map.put(Long.valueOf(hiHealthData.getStartTime()), hiHealthData);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dbf_(SparseArray<List<HiHealthData>> sparseArray, long j) {
        SharedPreferenceManager.c("privacy_center", "heart_health", String.valueOf(j));
        String b = nsj.b(j);
        if (!prz.dsD_(prz.dsB_(b), sparseArray)) {
            LogUtil.a("FunctionSetHeartRateReader", "setHeartRateCache same cache");
        } else {
            prz.dsE_(b, sparseArray);
        }
    }

    static class a extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private List<Float> f15717a;
        private boolean d;
        private final WeakReference<ojc> e;

        a(ojc ojcVar) {
            super("FunctionSetHeartRateReader", null);
            this.e = new WeakReference<>(ojcVar);
        }

        void b(HiHealthData hiHealthData, List<Float> list, int i) {
            HiHealthData hiHealthData2;
            if (hiHealthData == null || koq.b(list)) {
                ojc.setHasCardData(this.e, false);
                hiHealthData2 = null;
                this.f15717a = null;
            } else {
                ojc.setHasCardData(this.e, true);
                this.f15717a = list;
                String marshallListToString = marshallListToString(list);
                hiHealthData2 = new HiHealthData();
                hiHealthData2.putDouble("point_value", hiHealthData.getValue());
                hiHealthData2.setStartTime(hiHealthData.getStartTime());
                hiHealthData2.putString("_", marshallListToString);
                hiHealthData2.putInt("_u", i);
            }
            ojc.saveDataFromHealthApi("FunctionSetHeartRateReader", this.e, hiHealthData2);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            ojc ojcVar = this.e.get();
            if (ojcVar == null) {
                LogUtil.h("FunctionSetHeartRateReader", "handleCacheData heartRateReader is null");
                return;
            }
            if (hiHealthData == null) {
                if (this.d && z) {
                    ojcVar.c();
                    return;
                }
                return;
            }
            this.d = true;
            List list = this.f15717a;
            if (!z || list == null) {
                list = new ArrayList();
                unmarshallListFromString(hiHealthData.getString("_"), list);
            } else {
                this.f15717a = null;
            }
            if (!list.isEmpty()) {
                ojcVar.a((List<Float>) list);
                ojcVar.c(hiHealthData.getDouble("point_value"), hiHealthData.getStartTime(), hiHealthData.getInt("_u"), z);
            } else {
                LogUtil.h("FunctionSetHeartRateReader", "handleCacheData heartRateValueList is empty");
                if (z) {
                    return;
                }
                ojcVar.refreshCardBySp(ojcVar.buildEmptyCardBean());
            }
        }
    }

    public static class c extends BaseHandler<ojc> {
        public c(ojc ojcVar) {
            super(Looper.getMainLooper(), ojcVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbg_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ojc ojcVar, Message message) {
            if (ojcVar == null || message == null) {
                LogUtil.h("FunctionSetHeartRateReader", "handleMessageWhenReferenceNotNull reader or message is null");
                return;
            }
            int i = message.what;
            LogUtil.a("FunctionSetHeartRateReader", "handleMessageWhenReferenceNotNull what ", Integer.valueOf(i));
            if (i == 4) {
                Object obj = message.obj;
                if (obj instanceof FunctionSetBean) {
                    ojcVar.notifyItemChanged((FunctionSetBean) obj);
                } else {
                    LogUtil.h("FunctionSetHeartRateReader", "handleMessageWhenReferenceNotNull object instanceof FunctionSetBean is false");
                }
            }
        }
    }

    public ojc(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetHeartRateReader", cardConfig);
        this.c = new a(this);
        this.j = null;
        this.h = this.mContext.getResources();
        this.d = new c(this);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(5);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new FunctionSetBeanReader.c("FunctionSetHeartRateReader", this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(double d, long j, int i, boolean z) {
        LogUtil.a("FunctionSetHeartRateReader", "refreshHeartRateDataAndTime isNewData=", Boolean.valueOf(z), " lastHeartRate:", Double.valueOf(d), " time:", Long.valueOf(j));
        setHasCardData(true);
        this.g = j;
        setBiHasData(j);
        String b = owm.b(j);
        String e2 = UnitUtil.e(d, 1, 0);
        FunctionSetBean c2 = new FunctionSetBean.a(this.h.getString(R.string.IDS_main_heart_health_string)).a(owm.b(nsf.a(R.plurals._2130903250_res_0x7f0300d2, (int) d, e2), e2)).d(this.h.getString(R.string._2130837666_res_0x7f0200a2)).a(FunctionSetType.HEART_RATE_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).c(b).d(this.mContext).c();
        c2.c(i);
        if (z) {
            Message obtainMessage = this.d.obtainMessage();
            obtainMessage.what = 4;
            obtainMessage.obj = c2;
            this.d.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetHeartRateReader", "readCardData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2002, 2018, 2105});
        hiDataReadOption.setSortOrder(1);
        HiHealthManager.d(this.mContext).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).d(true).e(), new e(this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        LogUtil.a("FunctionSetHeartRateReader", "initCardData");
        ohy.c().a(new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("FunctionSetHeartRateReader", "showEmptyView");
        setHasCardData(false);
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.d.obtainMessage();
        obtainMessage.what = 4;
        obtainMessage.obj = buildEmptyCardBean;
        this.d.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.h.getString(R.string.IDS_main_heart_health_string)).e(this.h.getString(R.string.IDS_hw_health_show_healthdata_heart_description)).a(FunctionSetType.HEART_RATE_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(this.mContext).c();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetHeartRateReader", "updateSuccessList");
        if (koq.b(list)) {
            LogUtil.h("FunctionSetHeartRateReader", "updateSuccessList successList is empty");
        } else {
            this.j = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HiHealthData hiHealthData, List<HiHealthData> list, long j) {
        ArrayList arrayList = new ArrayList(2);
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null && j == HiDateUtil.t(hiHealthData2.getStartTime())) {
                arrayList.add(hiHealthData2);
            }
        }
        LogUtil.a("FunctionSetHeartRateReader", "getCardViewLastDayData size ", Integer.valueOf(arrayList.size()));
        if (koq.b(arrayList)) {
            this.c.b(null, null, 0);
            return;
        }
        this.c.b(hiHealthData, ojq.a(arrayList), (hiHealthData.toString() + arrayList.size()).hashCode());
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        List dataSets;
        if (this.b == null) {
            HwHealthLineChart hwHealthLineChart = (HwHealthLineChart) View.inflate(this.mContext, R.layout.card_heart_view, null).findViewById(R.id.linechart);
            this.b = hwHealthLineChart;
            hwHealthLineChart.setTouchEnabled(false);
            this.b.getXAxis().setEnabled(false);
            this.b.getDescription().setEnabled(false);
            HwHealthYAxis axisFirstParty = this.b.getAxisFirstParty();
            axisFirstParty.setDrawLabels(false);
            axisFirstParty.setDrawGridLines(false);
            axisFirstParty.setDrawAxisLine(false);
            this.b.disableLabelsForce();
        }
        HwHealthLineDataSet hwHealthLineDataSet = this.f15716a;
        if (hwHealthLineDataSet == null) {
            LogUtil.h("FunctionSetHeartRateReader", "createCardView heartRateSet is null");
            return null;
        }
        now nowVar = (now) this.b.getData();
        if (nowVar == null) {
            dataSets = new ArrayList(1);
            this.b.setData(new now(dataSets));
        } else {
            dataSets = nowVar.getDataSets();
        }
        if (dataSets == null) {
            dataSets = new ArrayList(1);
        }
        if (koq.c(dataSets) && dataSets.get(0) == hwHealthLineDataSet) {
            LogUtil.a("FunctionSetHeartRateReader", "createCardView same chart data");
            return this.b;
        }
        HwHealthYAxis axisFirstParty2 = this.b.getAxisFirstParty();
        axisFirstParty2.setAxisMinimum(hwHealthLineDataSet.getYMin() - 10.0f);
        axisFirstParty2.setAxisMaximum(hwHealthLineDataSet.getYMax() + 10.0f);
        dataSets.clear();
        dataSets.add(hwHealthLineDataSet);
        this.b.setData(new now(dataSets));
        this.b.refresh();
        LogUtil.a("FunctionSetHeartRateReader", "createCardView mHeartRateChart refresh");
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Float> list) {
        if (koq.b(list)) {
            this.c.b(null, null, 0);
            return;
        }
        if (koq.b(list, this.e, true)) {
            LogUtil.h("FunctionSetHeartRateReader", "initLineChart same list no need to refresh chart");
            return;
        }
        this.e = list;
        ArrayList arrayList = new ArrayList(16);
        int size = list.size();
        LogUtil.a("FunctionSetHeartRateReader", "initLineChart valueSize ", Integer.valueOf(size));
        for (int i = 0; i < size; i++) {
            float floatValue = list.get(i).floatValue();
            if (floatValue != 0.0f) {
                arrayList.add(new HwHealthBaseEntry(i, floatValue));
            }
        }
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, "line brief", "line label", "line unit");
        hwHealthLineDataSet.e(1);
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: ojc.2
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i2, int i3, int i4) {
                return i3 - i2 <= i4;
            }
        });
        hwHealthLineDataSet.setColor(ContextCompat.getColor(this.mContext, R.color._2131297764_res_0x7f0905e4));
        hwHealthLineDataSet.b(Color.parseColor("#4cFF4065"), Color.parseColor("#00FF4065"), true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        this.f15716a = hwHealthLineDataSet;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        if (efb.f()) {
            e(hashMap);
        } else {
            a(hashMap);
        }
    }

    private void e(Map<String, Object> map) {
        if (this.mContext != null) {
            LogUtil.a("FunctionSetHeartRateReader", "arkui OHProBridge start EntryEntryAbilityActivity");
            map.put("arkUI", "1");
            ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_HEART_RATE_DETAIL_2010029.value(), map, 0);
            Intent intent = new Intent();
            intent.setClassName(this.mContext.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
            IntentParams build = IntentParams.builder().addPageId(ArkUIXConstants.HEART_RATE).addPageType("0").addBiFrom("Today_0001", "home_card", String.valueOf(this.mCardViewHolder.getLayoutPosition())).addTimeStamp(String.valueOf(this.g)).build();
            LogUtil.a("FunctionSetHeartRateReader", "params.toString()", build.toString());
            intent.putExtra(com.alipay.sdk.m.p.e.n, build.toString());
            gnm.aPC_(this.mContext, intent);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetHeartRateReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void a(Map<String, Object> map) {
        Intent intent;
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_HEART_RATE_DETAIL_2010029.value(), map, 0);
        try {
            ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).clickRecord(Integer.parseInt(AnalyticsValue.HEALTH_HOME_HEART_RATE_DETAIL_2010029.value()), getHuid());
        } catch (NumberFormatException e2) {
            LogUtil.b("FunctionSetHeartRateReader", "gotoHeartRateActivity exception ", LogAnonymous.b((Throwable) e2));
        }
        if (efb.a(this.mContext)) {
            intent = new Intent(this.mContext, (Class<?>) HeartRateDetailActivity.class);
        } else {
            intent = new Intent(this.mContext, (Class<?>) KnitHeartRateActivity.class);
        }
        intent.putExtra("key_bundle_health_last_data_time", this.g);
        intent.setFlags(268435456);
        gnm.aPB_(this.mContext, intent);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.j)) {
            LogUtil.h("FunctionSetHeartRateReader", "onDestroy mHeartRateSuccessList is empty");
        } else {
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.j, new FunctionSetBeanReader.b("FunctionSetHeartRateReader", "unSubscribeHeartRateData, isSuccess :"));
        }
        c cVar = this.d;
        if (cVar == null) {
            LogUtil.h("FunctionSetHeartRateReader", "onDestroy mHandler is null");
        } else {
            cVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_heart_rate);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(this.mContext) ? R.color._2131297758_res_0x7f0905de : R.color._2131297757_res_0x7f0905dd;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.c;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetHeartRateReader";
    }
}

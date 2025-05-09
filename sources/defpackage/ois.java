package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.IntentParams;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.homehealth.functionsetcard.view.EmotionChartView;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/* loaded from: classes9.dex */
public class ois extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private final d f15708a;
    private List<Integer> b;
    private a c;
    private EmotionChartView d;
    private c e;
    private String f;
    private final int[] g;
    private long h;
    private View i;
    private HwHealthBarChart j;
    private List<Integer> k;
    private List<HwHealthBarEntry> n;
    private String o;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return R.color._2131297759_res_0x7f0905df;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839686_res_0x7f020886;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131431095_res_0x7f0b0eb7 : R.drawable.marketing_default_img_pressure;
    }

    public ois(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetEmotionCardReader", cardConfig);
        this.g = new int[]{ContextCompat.getColor(BaseApplication.getContext(), R.color._2131297103_res_0x7f09034f), ContextCompat.getColor(BaseApplication.getContext(), R.color._2131297102_res_0x7f09034e), ContextCompat.getColor(BaseApplication.getContext(), R.color._2131297104_res_0x7f090350)};
        this.k = new ArrayList(2);
        this.n = new ArrayList();
        this.b = new ArrayList();
        this.f15708a = new d(this);
        this.e = new c(this);
        b();
        a aVar = new a(this);
        this.c = aVar;
        ObserverManagerUtil.d(aVar, ObserveLabels.SUMMARY_DATA_INIT);
    }

    static class a implements Observer {
        private final WeakReference<ois> c;

        a(ois oisVar) {
            this.c = new WeakReference<>(oisVar);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ois oisVar = this.c.get();
            if (oisVar == null || objArr == null || !ObserveLabels.SUMMARY_DATA_INIT.equals(str)) {
                ReleaseLogUtil.d("FunctionSetEmotionCardReader", "InnerObserver notify functionSetEmotionCardReader ", oisVar, " objects ", objArr, " label ", str);
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Integer) || !(objArr[1] instanceof HashMap)) {
                ReleaseLogUtil.d("FunctionSetEmotionCardReader", "InnerObserver notify is not legal");
                return;
            }
            int intValue = ((Integer) obj).intValue();
            HashMap hashMap = (HashMap) objArr[1];
            LogUtil.a("FunctionSetEmotionCardReader", "initSummary errorCode： ", Integer.valueOf(intValue));
            Message obtainMessage = oisVar.f15708a.obtainMessage();
            obtainMessage.what = 100;
            obtainMessage.obj = hashMap.get("emotionalHealth");
            oisVar.f15708a.sendMessage(obtainMessage);
        }
    }

    static class c extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private List<Integer> f15710a;
        private final WeakReference<ois> b;
        private boolean d;

        c(ois oisVar) {
            super("FunctionSetEmotionCardReader", null);
            this.b = new WeakReference<>(oisVar);
        }

        void b(String str, HiHealthData hiHealthData, List<Integer> list, int i, boolean z) {
            HiHealthData hiHealthData2;
            if (hiHealthData == null || koq.b(list)) {
                ois.setHasCardData(this.b, false);
                hiHealthData2 = null;
                this.f15710a = null;
            } else {
                ois.setHasCardData(this.b, true);
                this.f15710a = list;
                String marshallListToString = marshallListToString(list);
                hiHealthData2 = new HiHealthData();
                hiHealthData2.setStartTime(hiHealthData.getStartTime());
                hiHealthData2.putString("_c", str);
                hiHealthData2.putLong("_t", hiHealthData.getStartTime());
                hiHealthData2.putInt("_s", hiHealthData.getIntValue());
                hiHealthData2.putInt("_u", i);
                hiHealthData2.putBoolean("_es", z);
                hiHealthData2.putString("_", marshallListToString);
            }
            ois.saveDataFromHealthApi("FunctionSetEmotionCardReader", this.b, hiHealthData2);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            FunctionSetBean c;
            LogUtil.a("FunctionSetEmotionCardReader", "handleCacheData isNewData:", Boolean.valueOf(z));
            ois oisVar = this.b.get();
            if (oisVar == null) {
                LogUtil.h("FunctionSetEmotionCardReader", "handleCacheData reader is null");
                return;
            }
            if (hiHealthData == null) {
                LogUtil.h("FunctionSetEmotionCardReader", "handleCacheData data is null");
                ois.setHasCardData(this.b, false);
                if (this.d && z) {
                    oisVar.d(oisVar.buildEmptyCardBean());
                    return;
                }
                return;
            }
            LogUtil.a("FunctionSetEmotionCardReader", "handleCacheData data is:", hiHealthData.toString());
            this.d = true;
            String string = hiHealthData.getString("_c");
            oisVar.f = string;
            List list = this.f15710a;
            if (!z || list == null) {
                list = new ArrayList();
                unmarshallListFromString(hiHealthData.getString("_"), list);
            } else {
                this.f15710a = null;
            }
            if (!koq.b(list)) {
                oisVar.setHasCardData(true);
                if ("emotionType".equals(string)) {
                    oisVar.b((List<Integer>) list);
                    oisVar.b = list;
                    c = oisVar.a(hiHealthData.getLong("_t"), oisVar.d(hiHealthData.getInt("_s")), hiHealthData.getBoolean("_es"));
                } else {
                    oisVar.n = oisVar.c((List<Integer>) list);
                    int i = hiHealthData.getInt("_s");
                    c = oisVar.c(hiHealthData.getLong("_t"), i, sdh.e(i));
                }
                c.c(hiHealthData.getInt("_u"));
                if (z) {
                    if (oisVar.a()) {
                        c.a(oisVar.o);
                    }
                    oisVar.d(c);
                    return;
                }
                oisVar.refreshCardBySp(c);
                return;
            }
            LogUtil.h("FunctionSetEmotionCardReader", "handleCacheData pressureValueList is empty");
            if (z) {
                return;
            }
            oisVar.refreshCardBySp(oisVar.buildEmptyCardBean());
        }
    }

    private void b() {
        LogUtil.a("FunctionSetEmotionCardReader", "subscribeStressData");
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(14);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.EMOTION_SET.value()));
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new FunctionSetBeanReader.c("FunctionSetEmotionCardReader", this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.e;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean == null) {
            return;
        }
        if (a()) {
            functionSetBean.a(this.o);
        } else {
            functionSetBean.a("");
        }
        notifyItemChanged(functionSetBean);
        ReleaseLogUtil.e("TimeEat_FunctionSetEmotionCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return (TextUtils.isEmpty(this.o) || nhj.m()) ? false : true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_pressure);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetEmotionCardReader", "readCardData");
        ArrayList arrayList = new ArrayList();
        arrayList.add(a(new int[]{44307}));
        arrayList.add(a(new int[]{DicDataTypeUtil.DataType.EMOTION_STATUS_ALL_COUNT.value()}));
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthDataEx(arrayList, new b(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, List<HiHealthData> list2) {
        final int i;
        final int i2;
        HiHealthData hiHealthData = koq.c(list) ? list.get(0) : null;
        HiHealthData hiHealthData2 = koq.c(list2) ? list2.get(0) : null;
        if (hiHealthData == null && hiHealthData2 == null) {
            this.e.b("noDataType", null, null, 0, false);
            return;
        }
        long startTime = hiHealthData != null ? hiHealthData.getStartTime() : 0L;
        long startTime2 = hiHealthData2 != null ? hiHealthData2.getStartTime() : 0L;
        final int b2 = DateFormatUtil.b(startTime);
        int b3 = DateFormatUtil.b(startTime2);
        if (b3 >= b2) {
            i = DicDataTypeUtil.DataType.EMOTION_STATUS.value();
            this.h = startTime2;
            i2 = 3;
            b2 = b3;
        } else {
            this.h = startTime;
            i = 2034;
            i2 = 2;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: oiw
            @Override // java.lang.Runnable
            public final void run() {
                ois.this.b(b2, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(int i, int i2, int i3) {
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(e(new int[]{i2}, i), new e(this, i2, i3));
    }

    private HiDataReadOption e(int[] iArr, int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(String.valueOf(i), String.valueOf(i), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        return hiDataReadOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list) {
        if (koq.b(list)) {
            this.e.b("emotionType", null, null, 0, false);
            return;
        }
        LogUtil.a("FunctionSetEmotionCardReader", "handleEmotionData dataList size:", Integer.valueOf(list.size()));
        HiHealthData hiHealthData = list.get(0);
        ArrayList arrayList = new ArrayList(d(a(hiHealthData), list).values());
        LogUtil.a("FunctionSetEmotionCardReader", "handleEmotionData emotionValues:", arrayList.toString());
        this.e.b("emotionType", hiHealthData, arrayList, (list.toString() + list.size()).hashCode(), e(list));
    }

    private List<Long> a(HiHealthData hiHealthData) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hiHealthData.getStartTime());
        LogUtil.a("FunctionSetEmotionCardReader", "getEmotionTimeList lastHiHealthData time :", Long.valueOf(hiHealthData.getStartTime()));
        calendar.set(12, (calendar.get(12) / 10) * 10);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long t = jdl.t(hiHealthData.getStartTime());
        long timeInMillis = calendar.getTimeInMillis() - 21000000;
        if (timeInMillis >= t) {
            t = timeInMillis;
        }
        ArrayList arrayList = new ArrayList(36);
        for (int i = 0; i < 36; i++) {
            arrayList.add(Long.valueOf(t));
            t += 600000;
        }
        Collections.reverse(arrayList);
        LogUtil.a("FunctionSetEmotionCardReader", "getEmotionTimeList result :", arrayList.toString());
        return arrayList;
    }

    private TreeMap<Long, Integer> d(List<Long> list, List<HiHealthData> list2) {
        TreeMap<Long, Integer> treeMap = new TreeMap<>(new oiy());
        Iterator<Long> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            long longValue = it.next().longValue();
            while (i < list2.size()) {
                HiHealthData hiHealthData = list2.get(i);
                if (hiHealthData.getStartTime() < longValue) {
                    break;
                }
                if (!treeMap.containsKey(Long.valueOf(longValue))) {
                    treeMap.put(Long.valueOf(longValue), Integer.valueOf(hiHealthData.getIntValue()));
                }
                i++;
            }
            if (!treeMap.containsKey(Long.valueOf(longValue))) {
                treeMap.put(Long.valueOf(longValue), 0);
            }
        }
        LogUtil.a("FunctionSetEmotionCardReader", "getEmotionValues result :", treeMap.toString());
        return treeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(int i) {
        if (1 == i) {
            return nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_unhappy);
        }
        if (2 == i) {
            return nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_peace);
        }
        return nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_happy);
    }

    private boolean e(List<HiHealthData> list) {
        if (koq.b(list) || list.size() < 12) {
            return false;
        }
        int size = list.size();
        Iterator<HiHealthData> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getIntValue() == 1) {
                i++;
            }
        }
        return ((double) i) > ((double) size) * 0.3d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<HiHealthData> list) {
        if (koq.b(list)) {
            this.e.b("stressType", null, null, 0, false);
            return;
        }
        HiHealthData hiHealthData = list.get(0);
        ArrayList arrayList = new ArrayList(e(b(hiHealthData), list).values());
        b(arrayList);
        this.e.b("stressType", hiHealthData, arrayList, (list.toString() + list.size()).hashCode(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext() && it.next().intValue() == 0) {
            it.remove();
            i++;
        }
        for (int i2 = 0; i2 < i; i2++) {
            list.add(0);
        }
    }

    private List<Long> b(HiHealthData hiHealthData) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hiHealthData.getStartTime());
        if (calendar.get(12) >= 30) {
            calendar.set(12, 30);
        } else {
            calendar.set(12, 0);
        }
        calendar.set(13, 0);
        calendar.set(14, 0);
        long t = jdl.t(hiHealthData.getStartTime());
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

    private TreeMap<Long, Integer> e(List<Long> list, List<HiHealthData> list2) {
        TreeMap<Long, Integer> treeMap = new TreeMap<>(new oiy());
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

    private HiDataReadProOption a(int[] iArr) {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setType(iArr);
        return HiDataReadProOption.builder().e(hiDataReadOption).e();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        if (!(viewHolder instanceof FunctionSetBeanReader.MyHolder) || "stressType".equals(this.f)) {
            return;
        }
        FunctionSetBeanReader.MyHolder myHolder = (FunctionSetBeanReader.MyHolder) viewHolder;
        myHolder.e.setAutoTextSize(2, 20.0f);
        myHolder.e.setAutoTextInfo(14, 1, 2);
        myHolder.e.setTypeface(Typeface.create(BaseApplication.getContext().getString(R.string._2130851581_res_0x7f0236fd), 0));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetEmotionCardReader", "createCardView start");
        if (this.i == null) {
            try {
                this.i = LayoutInflater.from(this.mContext).inflate(R.layout.card_emotion_view, (ViewGroup) null);
            } catch (InflateException e2) {
                LogUtil.h("FunctionSetEmotionCardReader", e2.getClass().getSimpleName() + "inflate mStressView fail!");
            }
            LogUtil.a("FunctionSetEmotionCardReader", "createCardView mStressView");
        }
        if ("stressType".equals(this.f)) {
            e();
        } else {
            c();
        }
        return this.i;
    }

    private void e() {
        ViewStub viewStub;
        if (this.j == null && (viewStub = (ViewStub) this.i.findViewById(R.id.barchart)) != null) {
            LogUtil.a("FunctionSetEmotionCardReader", "createCardView mStressChart");
            View inflate = viewStub.inflate();
            if (inflate instanceof HwHealthBarChart) {
                this.j = (HwHealthBarChart) inflate;
            }
        }
        if (this.j != null) {
            LogUtil.a("FunctionSetEmotionCardReader", "show mStressChart");
            b(this.n, this.j);
            this.j.setVisibility(0);
        } else {
            LogUtil.h("FunctionSetEmotionCardReader", "show STRESS_TYPE, but mStressChart is null");
        }
        EmotionChartView emotionChartView = this.d;
        if (emotionChartView != null) {
            emotionChartView.setVisibility(8);
        }
    }

    private void c() {
        ViewStub viewStub;
        if (this.d == null && (viewStub = (ViewStub) this.i.findViewById(R.id.emotion_chart)) != null) {
            LogUtil.a("FunctionSetEmotionCardReader", "createCardView mEmotionChart");
            View inflate = viewStub.inflate();
            if (inflate instanceof EmotionChartView) {
                this.d = (EmotionChartView) inflate;
            }
        }
        if (this.d != null) {
            LogUtil.a("FunctionSetEmotionCardReader", "show mEmotionChart");
            this.d.c(this.g);
            this.d.setEmotionChartDataList(this.b);
            this.d.setVisibility(0);
        } else {
            LogUtil.h("FunctionSetEmotionCardReader", "show EMOTION_TYPE, but mEmotionChart is null");
        }
        HwHealthBarChart hwHealthBarChart = this.j;
        if (hwHealthBarChart != null) {
            hwHealthBarChart.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HwHealthBarEntry> c(List<Integer> list) {
        HwHealthBarEntry hwHealthBarEntry;
        ArrayList arrayList = new ArrayList(16);
        int size = list.size();
        LogUtil.a("FunctionSetEmotionCardReader", "mPressureMeasureValueList , size ", Integer.valueOf(size));
        for (int i = 0; i < size; i++) {
            int intValue = list.get(i).intValue();
            if (intValue > 0) {
                int c2 = c(intValue);
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(list.get(i).intValue(), c2, c2));
            } else {
                int color = ContextCompat.getColor(this.mContext, R.color._2131297799_res_0x7f090607);
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(20.0f, color, color));
            }
            arrayList.add(hwHealthBarEntry);
        }
        return arrayList;
    }

    private int c(int i) {
        if (i >= 80) {
            return this.mContext.getResources().getColor(R.color._2131296880_res_0x7f090270);
        }
        if (i >= 60) {
            return this.mContext.getResources().getColor(R.color._2131296879_res_0x7f09026f);
        }
        if (i >= 30) {
            return this.mContext.getResources().getColor(R.color._2131296878_res_0x7f09026e);
        }
        return this.mContext.getResources().getColor(R.color._2131296877_res_0x7f09026d);
    }

    private void b(List<HwHealthBarEntry> list, HwHealthBarChart hwHealthBarChart) {
        if (hwHealthBarChart == null) {
            LogUtil.h("FunctionSetEmotionCardReader", "initSet barChart is null");
            return;
        }
        float f = 0.0f;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getY() >= f) {
                f = list.get(i).getY();
            }
        }
        hwHealthBarChart.setTouchEnabled(false);
        HwHealthBarDataSet hwHealthBarDataSet = new HwHealthBarDataSet(list, "bar breif", "bar label", "bar unit");
        hwHealthBarDataSet.b(HwHealthBarDataSet.DrawColorMode.DATA_COLOR);
        hwHealthBarDataSet.setBarDrawWidth(0.5f);
        hwHealthBarDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        hwHealthBarChart.getXAxis().setEnabled(false);
        HwHealthYAxis axisFirstParty = hwHealthBarChart.getAxisFirstParty();
        axisFirstParty.setDrawLabels(false);
        axisFirstParty.setDrawGridLines(false);
        axisFirstParty.setDrawAxisLine(false);
        float f2 = f / 2.0f;
        axisFirstParty.setAxisMinimum(f2 - 50.0f);
        axisFirstParty.setAxisMaximum(f2 + 50.0f);
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(hwHealthBarDataSet);
        hwHealthBarChart.setData(new nmz(arrayList));
        hwHealthBarChart.refresh();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == 14 || i == DicDataTypeUtil.DataType.EMOTION_SET.value();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetEmotionCardReader", "subscribeStressData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetEmotionCardReader", "registerStressListener success");
        this.k = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(FunctionSetBean functionSetBean) {
        LogUtil.a("FunctionSetEmotionCardReader", "updateUi with ", functionSetBean);
        Message obtainMessage = this.f15708a.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = functionSetBean;
        this.f15708a.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_title)).a(FunctionSetType.STRESS_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion)).d(this.mContext).c();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        String str;
        String str2;
        if (this.mContext != null) {
            if ("stressType".equals(this.f)) {
                str = "1";
                str2 = "stress";
            } else {
                str = "0";
                str2 = "emotion";
            }
            Intent intent = new Intent();
            intent.setClassName(this.mContext.getPackageName(), "com.huawei.hwarkuix.EntryAbilityActivity");
            IntentParams build = IntentParams.builder().addPageId(ArkUIXConstants.KNIT_EMOTION).addPageType("8").addTimeStamp(String.valueOf(this.h)).addRecord("{'cardType':" + str + ", 'entrance':\"" + str2 + "\"}").addBiFrom("Today_0001", "home_card", String.valueOf(this.mCardViewHolder.getLayoutPosition())).build();
            LogUtil.a("FunctionSetEmotionCardReader", "params start with: ", build.toString());
            intent.putExtra(com.alipay.sdk.m.p.e.n, build.toString());
            intent.putExtra(ArkUIXConstants.ARKUIX_MODULE_TYPE_KEY, 1);
            gnm.aPC_(this.mContext, intent);
            nhj.t();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.k)) {
            LogUtil.h("FunctionSetEmotionCardReader", "onDestroy mSubscribeTypeList is empty");
        } else {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.k, new FunctionSetBeanReader.b("FunctionSetEmotionCardReader", "unSubscribeEmotionData, isSuccess :"));
        }
        d dVar = this.f15708a;
        if (dVar == null) {
            LogUtil.h("FunctionSetEmotionCardReader", "onDestroy mHandler is null");
        } else {
            dVar.removeCallbacksAndMessages(null);
        }
        ObserverManagerUtil.c(this.c);
    }

    static class d extends BaseHandler<ois> {
        d(ois oisVar) {
            super(Looper.getMainLooper(), oisVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbc_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ois oisVar, Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.a("FunctionSetEmotionCardReader", "MSG_UPDATE_UI");
                oisVar.notifyItemChanged((FunctionSetBean) message.obj);
                return;
            }
            if (i == 2) {
                LogUtil.a("FunctionSetEmotionCardReader", "MSG_HANDLE_DETAIL_STRESS_DATA");
                if (message.obj instanceof List) {
                    oisVar.a((List<HiHealthData>) message.obj);
                    return;
                }
                return;
            }
            if (i == 3) {
                LogUtil.a("FunctionSetEmotionCardReader", "MSG_HANDLE_DETAIL_EMOTION_DATA");
                if (message.obj instanceof List) {
                    oisVar.d((List<HiHealthData>) message.obj);
                    return;
                }
                return;
            }
            if (i != 100) {
                return;
            }
            boolean j = nhj.j();
            LogUtil.a("FunctionSetEmotionCardReader", "handleMessageWhenReferenceNotNull isOpenActiveRecord ", Boolean.valueOf(j));
            if (j) {
                return;
            }
            Object obj = message.obj;
            if (obj instanceof Pair) {
                Pair pair = (Pair) obj;
                if (!(pair.first instanceof Integer) || !(pair.second instanceof String)) {
                    LogUtil.h("FunctionSetEmotionCardReader", "handleMessageWhenReferenceNotNull emotionHealthText is error");
                    return;
                }
                int intValue = ((Integer) pair.first).intValue();
                String str = (String) pair.second;
                LogUtil.a("FunctionSetEmotionCardReader", "cardId is：", Integer.valueOf(intValue), "emotionHealthText is：", str);
                oisVar.o = str;
                if (TextUtils.isEmpty(str)) {
                    LogUtil.h("FunctionSetEmotionCardReader", "handleMessageWhenReferenceNotNull emotionHealthText ", str, " cardId ", Integer.valueOf(intValue));
                    return;
                }
                FunctionSetBean functionSetBean = oisVar.getFunctionSetBean();
                if (functionSetBean == null) {
                    ReleaseLogUtil.d("FunctionSetEmotionCardReader", "handleMessageWhenReferenceNotNull functionSetBean is null");
                    return;
                }
                LogUtil.a("FunctionSetEmotionCardReader", "activeRecordText: ", str);
                functionSetBean.a(str);
                oisVar.notifyItemChanged(functionSetBean);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FunctionSetBean a(long j, String str, boolean z) {
        setBiHasData(j);
        setBiHasDataType("emotion");
        FunctionSetBean.a d2 = new FunctionSetBean.a(nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_title)).c(owm.b(j)).a(str).a(FunctionSetType.STRESS_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).d(this.mContext);
        if (z) {
            if (!jdl.ac(j)) {
                d2.c(nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_more_unhappy) + " ↑");
            } else {
                d2.d(nsf.h(R.string.IDS_hw_show_main_home_page_card_emotion_more_unhappy) + " ↑");
            }
        }
        return d2.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FunctionSetBean c(long j, int i, String str) {
        setBiHasData(j);
        setBiHasDataType("stress");
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_settings_one_level_menu_settings_item_text_id14)).c(owm.b(j)).a(a(i, str)).a(FunctionSetType.STRESS_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).d(this.mContext).c();
    }

    private CharSequence a(int i, String str) {
        String e2 = UnitUtil.e(i, 1, 0);
        return owm.b(this.mContext.getResources().getString(R.string._2130839243_res_0x7f0206cb, this.mContext.getResources().getString(R.string.IDS_settings_one_level_menu_settings_item_text_id14), e2, str), e2);
    }

    static class b implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ois> f15709a;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        b(ois oisVar) {
            this.f15709a = new WeakReference<>(oisVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            ois oisVar = this.f15709a.get();
            if (oisVar == null) {
                LogUtil.h("FunctionSetEmotionCardReader", "FunctionSetEmotionCardReader cardReader is null");
                return;
            }
            if (!(obj instanceof SparseArray)) {
                LogUtil.b("FunctionSetEmotionCardReader", "data is not instanceof SparseArray");
                oisVar.e.b("noDataType", null, null, 0, false);
            } else {
                ReleaseLogUtil.c("FunctionSetEmotionCardReader", "readCardData with ", obj);
                SparseArray sparseArray = (SparseArray) obj;
                oisVar.c((List<HiHealthData>) sparseArray.get(44307), (List<HiHealthData>) sparseArray.get(DicDataTypeUtil.DataType.EMOTION_STATUS_ALL_COUNT.value()));
            }
        }
    }

    static class e implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final int f15711a;
        private final int b;
        private final WeakReference<ois> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        e(ois oisVar, int i, int i2) {
            this.d = new WeakReference<>(oisVar);
            this.f15711a = i;
            this.b = i2;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("FunctionSetEmotionCardReader", "readStressData is ", obj);
            ois oisVar = this.d.get();
            if (oisVar != null) {
                List list = oisVar.getList(obj, this.f15711a);
                Message obtainMessage = oisVar.f15708a.obtainMessage();
                obtainMessage.what = this.b;
                obtainMessage.obj = list;
                oisVar.f15708a.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("FunctionSetEmotionCardReader", "FunctionSetEmotionCardReader cardReader is null");
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetEmotionCardReader";
    }
}

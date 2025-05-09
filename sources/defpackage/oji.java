package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes9.dex */
public class oji extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private a f15731a;
    private boolean b;
    private View c;
    private List<HwHealthBarEntry> d;
    private e e;
    private boolean f;
    private long g;
    private HiDataReadResultListener h;
    private boolean i;
    private long j;
    private List<Integer> l;
    private List<Integer> n;

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

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == 14;
    }

    static final class d implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<oji> f15733a;
        private WeakReference<e> b;
        private WeakReference<a> c;

        d(a aVar, e eVar, oji ojiVar) {
            this.c = new WeakReference<>(aVar);
            this.b = new WeakReference<>(eVar);
            this.f15733a = new WeakReference<>(ojiVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            a aVar = this.c.get();
            e eVar = this.b.get();
            oji ojiVar = this.f15733a.get();
            LogUtil.a("FunctionSetStressCardReader", "StressHiDataReadResultListener onResult, runnable: ", aVar, ", handler", eVar, ", reader: ", ojiVar);
            if (ojiVar != null) {
                List list = ojiVar.getList(obj, 2034);
                if (eVar == null) {
                    LogUtil.b("FunctionSetStressCardReader", "handler is null");
                    return;
                }
                if (koq.b(list)) {
                    LogUtil.h("FunctionSetStressCardReader", "dataList is empty!");
                    if (aVar == null) {
                        LogUtil.b("FunctionSetStressCardReader", "runnable is null");
                        return;
                    } else {
                        aVar.c(false);
                        eVar.sendEmptyMessage(5);
                        return;
                    }
                }
                LogUtil.a("FunctionSetStressCardReader", "readCardData success");
                Message obtainMessage = eVar.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.obj = list;
                eVar.sendMessage(obtainMessage);
                return;
            }
            LogUtil.b("FunctionSetStressCardReader", "reader is null");
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("FunctionSetStressCardReader", "onResultIntent : read failed errorCode is", Integer.valueOf(i2));
        }
    }

    static class a extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<oji> f15732a;
        private HiHealthData b;
        private List<Integer> d;
        private boolean e;

        a(oji ojiVar) {
            super("FunctionSetStressCardReader", null);
            this.f15732a = new WeakReference<>(ojiVar);
        }

        void b(List<Integer> list) {
            this.d = list;
            if (list != null) {
                a(true).putString("_", marshallListToString(list));
            }
        }

        void d(long j, int i, int i2) {
            HiHealthData a2 = a(true);
            a2.putLong("_t", j);
            a2.putInt("_s", i);
            a2.putInt("_u", i2);
        }

        void c(boolean z) {
            HiHealthData hiHealthData;
            if (z) {
                oji.setHasCardData(this.f15732a, true);
                hiHealthData = a(false);
            } else {
                oji.setHasCardData(this.f15732a, false);
                this.d = null;
                hiHealthData = null;
            }
            this.b = null;
            oji.saveDataFromHealthApi("FunctionSetStressCardReader", this.f15732a, hiHealthData);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            oji ojiVar = this.f15732a.get();
            if (ojiVar == null) {
                LogUtil.h("FunctionSetStressCardReader", "handleCacheData stressCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                oji.setHasCardData(this.f15732a, false);
                if (this.e && z) {
                    ojiVar.c();
                    return;
                }
                return;
            }
            this.e = true;
            List list = this.d;
            if (!z || list == null) {
                list = new ArrayList();
                unmarshallListFromString(hiHealthData.getString("_"), list);
            } else {
                this.d = null;
            }
            if (!list.isEmpty()) {
                ojiVar.e(list);
                ojiVar.b = true;
                ojiVar.b(hiHealthData.getLong("_t"), hiHealthData.getInt("_s"), hiHealthData.getInt("_u"), z);
            } else {
                LogUtil.h("FunctionSetStressCardReader", "handleCacheData pressureValueList is empty");
                if (z) {
                    return;
                }
                ojiVar.refreshCardBySp(ojiVar.buildEmptyCardBean());
            }
        }

        private HiHealthData a(boolean z) {
            if (z && this.b == null) {
                this.b = new HiHealthData();
            }
            return this.b;
        }
    }

    public oji(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetStressCardReader", cardConfig);
        this.j = 0L;
        this.i = false;
        this.l = new ArrayList(1);
        this.n = null;
        this.b = false;
        this.f = true;
        this.e = new e(this);
        a aVar = new a(this);
        this.f15731a = aVar;
        this.h = new d(aVar, this.e, this);
        b();
    }

    private void b() {
        LogUtil.a("FunctionSetStressCardReader", "subscribeStressData");
        this.l.add(14);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(this.l, new FunctionSetBeanReader.c("FunctionSetStressCardReader", this));
    }

    public void e() {
        LogUtil.a("FunctionSetStressCardReader", "unSubscribeStressData");
        List<Integer> list = this.n;
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.n, new FunctionSetBeanReader.b("FunctionSetStressCardReader", "unSubscribeStressData, isSuccess:"));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        e();
        e eVar = this.e;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        LogUtil.a("FunctionSetStressCardReader", "readCardData start");
        HiHealthManager.d(this.mContext).readHiHealthData(d(), new b(this.f15731a, this.e, this));
    }

    private HiDataReadOption d() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setType(new int[]{44307});
        return hiDataReadOption;
    }

    public static class b implements HiDataReadResultListener {
        private WeakReference<a> c;
        private final WeakReference<oji> d;
        private WeakReference<e> e;

        private b(a aVar, e eVar, oji ojiVar) {
            this.d = new WeakReference<>(ojiVar);
            this.c = new WeakReference<>(aVar);
            this.e = new WeakReference<>(eVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            oji ojiVar = this.d.get();
            if (ojiVar == null) {
                LogUtil.b("FunctionSetStressCardReader", "reader is null");
                c(this.c, this.e);
                return;
            }
            if (!(obj instanceof SparseArray)) {
                c(this.c, this.e);
                return;
            }
            SparseArray<List<HiHealthData>> sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                c(this.c, this.e);
                return;
            }
            HiHealthData dbk_ = dbk_(sparseArray, new int[]{44307});
            if (dbk_ != null) {
                ojiVar.j = dbk_.getStartTime();
                LogUtil.a("FunctionSetStressCardReader", "last data time=", Long.valueOf(ojiVar.j));
                if (ojiVar.j == 0) {
                    c(this.c, this.e);
                    return;
                }
                HiDataReadOption hiDataReadOption = new HiDataReadOption();
                hiDataReadOption.setTimeInterval(HiDateUtil.t(ojiVar.j), HiDateUtil.f(ojiVar.j));
                hiDataReadOption.setType(new int[]{2034});
                hiDataReadOption.setSortOrder(1);
                HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).d(true).e(), ojiVar.h);
                return;
            }
            c(this.c, this.e);
        }

        private void c(WeakReference<a> weakReference, WeakReference<e> weakReference2) {
            a aVar = weakReference.get();
            e eVar = weakReference2.get();
            if (eVar == null) {
                LogUtil.b("FunctionSetStressCardReader", "handler is null");
            } else if (aVar == null) {
                LogUtil.b("FunctionSetStressCardReader", "runnable is null");
            } else {
                aVar.c(false);
                eVar.sendEmptyMessage(5);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("FunctionSetStressCardReader", "onResultIntent");
        }

        private HiHealthData dbk_(SparseArray<List<HiHealthData>> sparseArray, int[] iArr) {
            ArrayList arrayList = new ArrayList(sparseArray.size());
            for (int i : iArr) {
                List<HiHealthData> list = sparseArray.get(i);
                if (list instanceof List) {
                    arrayList.addAll(list);
                }
            }
            if (arrayList.size() == 0) {
                return null;
            }
            Collections.sort(arrayList, new Comparator() { // from class: ojj
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(((HiHealthData) obj2).getEndTime(), ((HiHealthData) obj).getEndTime());
                    return compare;
                }
            });
            return (HiHealthData) arrayList.get(0);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        ohy.c().a(this.h);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetStressCardReader", "subscribeStressData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetStressCardReader", "registerStressListener success");
        this.n = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, int i, int i2, boolean z) {
        setHasCardData(true);
        this.g = j;
        String b2 = owm.b(j);
        String e2 = sdh.e(i);
        LogUtil.a("FunctionSetStressCardReader", "requestDatas", "pressureMeasureBean", b2, "---", "---", Integer.valueOf(i));
        if (i == 0) {
            if (z) {
                return;
            }
            refreshCardBySp(buildEmptyCardBean());
        } else {
            if (e2 == null) {
                if (z) {
                    return;
                }
                refreshCardBySp(buildEmptyCardBean());
                return;
            }
            FunctionSetBean a2 = a(b2, i, e2);
            a2.c(i2);
            if (z) {
                Message obtainMessage = this.e.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = a2;
                this.e.sendMessage(obtainMessage);
                return;
            }
            refreshCardBySp(a2);
        }
    }

    static class e extends BaseHandler<oji> {
        e(oji ojiVar) {
            super(Looper.getMainLooper(), ojiVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbj_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oji ojiVar, Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.a("FunctionSetStressCardReader", "handleMessageWhenReferenceNotNull");
                ojiVar.notifyItemChanged((FunctionSetBean) message.obj);
                return;
            }
            if (i == 3) {
                ojiVar.d(message.obj);
                ojiVar.c(message.obj);
                ojiVar.f15731a.c(true);
            } else if (i == 4) {
                LogUtil.a("FunctionSetStressCardReader", "MSG_HAVE_STRESS_DATA:");
                ojiVar.b(true);
            } else if (i == 5) {
                LogUtil.a("FunctionSetStressCardReader", "MSG_NOT_HAVE_STRESS_DATA:");
                ojiVar.b(false);
            } else {
                LogUtil.h("FunctionSetStressCardReader", "unkonw msg");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("FunctionSetStressCardReader", "show empty view!");
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = buildEmptyCardBean;
        this.e.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_settings_one_level_menu_settings_item_text_id14)).a(FunctionSetType.STRESS_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(this.mContext.getResources().getString(R.string.IDS_hw_show_main_home_page_card_stress)).d(this.mContext).c();
    }

    private FunctionSetBean a(String str, int i, String str2) {
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_settings_one_level_menu_settings_item_text_id14)).c(str).a(UnitUtil.e(i, 1, 0)).b(str2).a(FunctionSetType.STRESS_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).d(this.mContext).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        List arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        if (koq.e(obj, HiHealthData.class)) {
            arrayList = (List) obj;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((HiStressMetaData) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), HiStressMetaData.class));
        }
        int size = arrayList2.size();
        LogUtil.a("FunctionSetStressCardReader", "size = ", Integer.valueOf(size), ", metaDataList: ", arrayList2);
        if (size <= 0 || koq.b(this.f15731a.d)) {
            return;
        }
        long fetchStressStartTime = ((HiStressMetaData) arrayList2.get(0)).fetchStressStartTime();
        int intValue = ((Integer) this.f15731a.d.get(this.f15731a.d.size() - 1)).intValue();
        LogUtil.a("FunctionSetStressCardReader", "startTime: ", Long.valueOf(fetchStressStartTime), ", stressScore: ", Integer.valueOf(intValue));
        this.f15731a.d(fetchStressStartTime, intValue, (String.valueOf(fetchStressStartTime) + intValue + size).hashCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        if (obj instanceof List) {
            List<HiHealthData> list = (List) obj;
            HiHealthData hiHealthData = list.get(0);
            if (hiHealthData == null) {
                LogUtil.h("FunctionSetStressCardReader", "data is empty");
            } else {
                SharedPreferenceManager.c("privacy_center", "pressure", String.valueOf(hiHealthData.getStartTime()));
                b(list, HiDateUtil.t(((HiStressMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiStressMetaData.class)).fetchStressStartTime()));
            }
        }
    }

    private void b(List<HiHealthData> list, long j) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            HiStressMetaData hiStressMetaData = (HiStressMetaData) HiJsonUtil.e(list.get((size - i) - 1).getMetaData(), HiStressMetaData.class);
            if (j == HiDateUtil.t(hiStressMetaData.fetchStressStartTime())) {
                arrayList.add(hiStressMetaData);
            }
        }
        this.f15731a.b(b(arrayList));
    }

    private List<Integer> b(List<HiStressMetaData> list) {
        ArrayList arrayList = new ArrayList(list);
        LogUtil.a("FunctionSetStressCardReader", "totalList size:", Integer.valueOf(arrayList.size()), ", metaDataList: ", list.toString());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        ArrayList<HiStressMetaData> arrayList3 = new ArrayList(arrayList.size());
        ArrayList arrayList4 = new ArrayList(arrayList.size());
        for (HiStressMetaData hiStressMetaData : list) {
            int fetchStressMeasureType = hiStressMetaData.fetchStressMeasureType();
            if (fetchStressMeasureType == 0 || fetchStressMeasureType == 2) {
                arrayList2.add(hiStressMetaData);
            } else {
                arrayList3.add(hiStressMetaData);
            }
        }
        LogUtil.a("FunctionSetStressCardReader", "activeList size:", Integer.valueOf(arrayList2.size()));
        if (arrayList2.size() == 0) {
            return a(arrayList4, arrayList);
        }
        for (HiStressMetaData hiStressMetaData2 : arrayList3) {
            long fetchStressStartTime = hiStressMetaData2.fetchStressStartTime();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                if (b(((HiStressMetaData) it.next()).fetchStressStartTime(), fetchStressStartTime)) {
                    arrayList4.add(hiStressMetaData2);
                }
            }
        }
        LogUtil.a("FunctionSetStressCardReader", "filter size:", Integer.valueOf(arrayList4.size()), ", filterList: ", arrayList4.toString());
        return a(arrayList4, arrayList);
    }

    private List<Integer> a(List<HiStressMetaData> list, List<HiStressMetaData> list2) {
        ArrayList arrayList = new ArrayList();
        for (HiStressMetaData hiStressMetaData : list2) {
            if (!list.contains(hiStressMetaData)) {
                arrayList.add(hiStressMetaData);
            }
        }
        TreeMap treeMap = new TreeMap();
        d(treeMap, arrayList);
        ArrayList arrayList2 = new ArrayList(list2.size());
        Iterator<Map.Entry<Long, eda>> it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf((int) UnitUtil.a(it.next().getValue().a(), 0)));
        }
        int size = arrayList2.size();
        LogUtil.a("FunctionSetStressCardReader", "pressureValueList size:", Integer.valueOf(size), ", pressureValueList: ", arrayList2);
        return size <= 16 ? arrayList2 : arrayList2.subList(size - 16, size);
    }

    private void d(Map<Long, eda> map, List<HiStressMetaData> list) {
        for (int i = 0; i < list.size(); i++) {
            Calendar d2 = d(list.get(i).fetchStressStartTime());
            eda edaVar = map.get(Long.valueOf(d2.getTimeInMillis()));
            if (edaVar == null) {
                eda edaVar2 = new eda(r1.fetchStressScore());
                edaVar2.d(1);
                map.put(Long.valueOf(d2.getTimeInMillis()), edaVar2);
            } else {
                int e2 = edaVar.e();
                float f = e2;
                int i2 = e2 + 1;
                float a2 = ((edaVar.a() * f) + r1.fetchStressScore()) / i2;
                edaVar.d(i2);
                edaVar.d(a2);
                map.put(Long.valueOf(d2.getTimeInMillis()), edaVar);
            }
        }
    }

    private Calendar d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(12) / 30 == 0) {
            calendar.set(12, 14);
            calendar.set(13, 30);
        } else {
            calendar.set(12, 44);
            calendar.set(13, 30);
        }
        calendar.set(14, 0);
        return calendar;
    }

    private boolean b(long j, long j2) {
        return Math.abs(j - j2) <= 600000;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetStressCardReader", "createCardView");
        if (this.b) {
            LogUtil.a("FunctionSetStressCardReader", "mIsDataUpdated");
            if (this.c == null) {
                try {
                    this.c = LayoutInflater.from(this.mContext).inflate(R.layout.card_stress_view, (ViewGroup) null);
                } catch (InflateException e2) {
                    LogUtil.h("FunctionSetStressCardReader", e2.getClass().getSimpleName() + "inflate mCardStressView fail!");
                }
            }
            c(this.d);
            this.b = false;
        }
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Integer> list) {
        HwHealthBarEntry hwHealthBarEntry;
        ArrayList arrayList = new ArrayList(16);
        int size = list.size();
        LogUtil.a("FunctionSetStressCardReader", "mPressureMeasureValueList , size ", Integer.valueOf(size));
        for (int i = 0; i < 16; i++) {
            if (i < size) {
                int a2 = a(list.get(i).intValue());
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(list.get(i).intValue(), a2, a2));
            } else {
                int color = this.mContext.getResources().getColor(R.color._2131298049_res_0x7f090701);
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(20.0f, color, color));
            }
            arrayList.add(hwHealthBarEntry);
        }
        this.d = arrayList;
        c((List<HwHealthBarEntry>) arrayList);
    }

    private int a(int i) {
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

    private void c(List<HwHealthBarEntry> list) {
        if (this.c == null) {
            LogUtil.h("FunctionSetStressCardReader", "mCardStressView is null");
            return;
        }
        LogUtil.a("FunctionSetStressCardReader", "mCardStressView ID: " + this.c.getId() + ", layout: 2131165706");
        HwHealthBarChart hwHealthBarChart = (HwHealthBarChart) this.c.findViewById(R.id.barchart);
        if (hwHealthBarChart == null) {
            LogUtil.h("FunctionSetStressCardReader", "initSet barChart is null");
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

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (viewType == FunctionSetBean.ViewType.DATA_VIEW) {
            LogUtil.a("FunctionSetStressCardReader", "go to STRESS_CARD record");
            Intent intent = new Intent();
            intent.setClass(this.mContext, KnitPressureActivity.class);
            intent.setFlags(268435456);
            intent.putExtra("key_bundle_health_last_data_time", this.g);
            intent.putExtra("isHaveData", 1);
            intent.putExtra("type", 1);
            gnm.aPB_(this.mContext, intent);
            nsn.q();
            return;
        }
        LogUtil.a("FunctionSetStressCardReader", "go to STRESS_CARD record");
        Intent intent2 = new Intent();
        intent2.setFlags(268435456);
        intent2.setClass(this.mContext, KnitPressureActivity.class);
        intent2.putExtra("pressure_is_have_datas", this.i);
        if (this.i) {
            intent2.putExtra("isHaveData", 1);
            LogUtil.a("FunctionSetStressCardReader", "go to HaveData-STRESS_CARD record");
        } else {
            intent2.putExtra("isHaveData", 0);
            LogUtil.a("FunctionSetStressCardReader", "go to notHaveData-STRESS_CARD record");
        }
        intent2.putExtra("type", 1);
        gnm.aPB_(this.mContext, intent2);
        nsn.q();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetStressCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.i = z;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_pressure);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.f15731a;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetStressCardReader";
    }
}

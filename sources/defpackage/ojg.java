package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitTemperatureActivity;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public class ojg extends FunctionSetBeanReader {
    private static final int b = DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE.value();
    private static final int c = R.drawable._2131430049_res_0x7f0b0aa1;

    /* renamed from: a, reason: collision with root package name */
    private String f15722a;
    private String d;
    private final d e;
    private final e f;
    private boolean g;
    private boolean h;
    private boolean i;
    private ImageView j;
    private final boolean k;
    private double l;
    private final AtomicBoolean m;
    private boolean n;
    private volatile long o;
    private final qpk p;
    private HealthSeekBarExtend q;
    private List<Integer> s;
    private long t;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return R.color._2131297759_res_0x7f0905df;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839619_res_0x7f020843;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131431747_res_0x7f0b1143 : R.drawable.marketing_default_img_temperature;
    }

    static class d extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ojg> f15725a;
        private boolean c;

        d(ojg ojgVar) {
            super("FunctionSetTemperatureCardReader", null);
            this.f15725a = new WeakReference<>(ojgVar);
        }

        void a(HiHealthData hiHealthData) {
            HiHealthData hiHealthData2;
            if (hiHealthData != null) {
                ojg.setHasCardData(this.f15725a, true);
                hiHealthData2 = new HiHealthData();
                hiHealthData2.setStartTime(hiHealthData.getStartTime());
                hiHealthData2.setEndTime(hiHealthData.getEndTime());
                hiHealthData2.setType(hiHealthData.getType());
                hiHealthData2.putLong("_t", hiHealthData.getStartTime());
                hiHealthData2.putDouble("point_value", hiHealthData.getDouble("point_value"));
                hiHealthData2.putInt("_u", hiHealthData.toString().hashCode());
            } else {
                ojg.setHasCardData(this.f15725a, false);
                hiHealthData2 = null;
            }
            ojg.saveDataFromHealthApi("FunctionSetTemperatureCardReader", this.f15725a, hiHealthData2);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            String b;
            ojg ojgVar = this.f15725a.get();
            if (ojgVar == null) {
                LogUtil.h("FunctionSetTemperatureCardReader", "handleCacheData reader is null");
                return;
            }
            if (hiHealthData == null) {
                if (this.c && z) {
                    ojgVar.c();
                    return;
                }
                return;
            }
            this.c = true;
            ojgVar.l = hiHealthData.getDouble("point_value");
            LogUtil.a("FunctionSetTemperatureCardReader", "handleCacheData showTemperatureDataView : last = ", Long.valueOf(hiHealthData.getStartTime()), ", mLastTemperature = ", Double.valueOf(ojgVar.l));
            ojgVar.o = hiHealthData.getStartTime();
            if (hiHealthData.getType() == ojg.b) {
                ojgVar.g = true;
                b = "";
            } else {
                ojgVar.g = false;
                b = qpr.b((float) hiHealthData.getDouble("point_value"));
            }
            ojgVar.d(b, hiHealthData.getStartTime(), hiHealthData.getInt("_u"), z);
        }
    }

    public ojg(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetTemperatureCardReader", cardConfig);
        this.s = null;
        this.o = 0L;
        this.l = 0.0d;
        this.h = true;
        this.e = new d(this);
        boolean o = Utils.o();
        this.k = o;
        this.p = qpk.d();
        this.m = new AtomicBoolean(false);
        this.f = new e();
        if (o) {
            this.f15722a = this.mContext.getResources().getString(R.string.IDS_health_skin_temperature);
            this.d = this.mContext.getResources().getString(R.string.IDS_skin_temperature_desc);
        } else {
            this.f15722a = this.mContext.getResources().getString(R.string.IDS_settings_health_temperature);
            this.d = this.mContext.getResources().getString(R.string.IDS_health_temperature_desc);
        }
        LogUtil.c("FunctionSetTemperatureCardReader", "TemperatureCardReader mCardTitle is ", this.f15722a);
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, long j, int i, boolean z) {
        LogUtil.a("FunctionSetTemperatureCardReader", "refreshTemperatureDataAndTime called", "lastTemperature:", str, ", time = ", Long.valueOf(j), ", uniqueCode = ", Integer.valueOf(i));
        setHasCardData(true);
        setBiHasData(j);
        String b2 = owm.b(j);
        CharSequence a2 = a(str);
        if (this.g) {
            a2 = a();
        }
        FunctionSetBean c2 = new FunctionSetBean.a(this.f15722a).c(b2).a(a2).a(FunctionSetType.TEMPERATURE_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).d(this.mContext).c();
        c2.e(this.m.get());
        c2.c(i);
        setFunctionSetBean(c2);
        if (z) {
            LogUtil.a("FunctionSetTemperatureCardReader", "async show data card");
            Message obtainMessage = this.f.obtainMessage();
            obtainMessage.what = 101;
            obtainMessage.obj = c2;
            this.f.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    private CharSequence a() {
        String string;
        String b2 = qpr.b((float) this.l);
        if (UnitUtil.d()) {
            string = BaseApplication.e().getString(R.string._2130846689_res_0x7f0223e1, b2);
        } else {
            string = BaseApplication.e().getString(R.string._2130846690_res_0x7f0223e2, b2);
        }
        return owm.b(string, b2);
    }

    public static class e extends BaseHandler<ojg> {
        private e(ojg ojgVar) {
            super(Looper.getMainLooper(), ojgVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbp_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ojg ojgVar, Message message) {
            LogUtil.a("FunctionSetTemperatureCardReader", "handleMessageWhenReferenceNotNull()");
            if (ojgVar == null) {
                LogUtil.h("FunctionSetTemperatureCardReader", "handleMessageWhenReferenceNotNull temperatureCardReader == null!");
                return;
            }
            int i = message.what;
            if (i != 101) {
                if (i == 102) {
                    ojgVar.f();
                    return;
                } else {
                    LogUtil.h("FunctionSetTemperatureCardReader", "FunctionSetTemperatureCardReaderHandler unknown msg");
                    return;
                }
            }
            LogUtil.a("FunctionSetTemperatureCardReader", "FunctionSetTemperatureCardReaderHandler: refreshData");
            if (message.obj instanceof FunctionSetBean) {
                ojgVar.notifyItemChanged((FunctionSetBean) message.obj);
            }
        }
    }

    private void d() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.card_blood_pressure_blood_sugar, (ViewGroup) null);
        LogUtil.a("FunctionSetTemperatureCardReader", "initTemperatureSeekBar inflated view: ", inflate);
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) inflate.findViewById(R.id.blood_sugar_seek_bar);
        this.q = healthSeekBarExtend;
        if (healthSeekBarExtend == null) {
            LogUtil.b("FunctionSetTemperatureCardReader", "initTemperatureSeekBar mSeekBarExtend is null");
            return;
        }
        int i = 136;
        healthSeekBarExtend.setMax(136);
        this.q.setThumb(this.mContext.getResources().getDrawable(c));
        int e2 = qpr.e((float) this.l);
        LogUtil.c("FunctionSetTemperatureCardReader", "progress = ", Integer.valueOf(e2), "mLastTemperature ", Double.valueOf(this.l));
        if (e2 < 0) {
            i = 0;
        } else if (e2 <= 136) {
            LogUtil.c("FunctionSetTemperatureCardReader", "The progress is normal. No special handling is required.");
            i = e2;
        }
        this.q.setProgress(i);
        this.q.setRulerSrc(this.mContext.getResources().getDrawable(R.drawable._2131428459_res_0x7f0b046b));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public int getExtraWidth() {
        return ContextCompat.getDrawable(this.mContext, c).getIntrinsicWidth();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (viewType == FunctionSetBean.ViewType.DATA_VIEW) {
            LogUtil.a("FunctionSetTemperatureCardReader", "go to setViewForTemperatureCards record");
            if (this.mContext != null) {
                e(0);
                long j = this.t;
                if (j != 0) {
                    qpm.e(j);
                }
                long max = Math.max(this.o, this.t);
                Intent intent = new Intent(this.mContext, (Class<?>) KnitTemperatureActivity.class);
                intent.putExtra("key_bundle_health_last_data_time", max);
                gnm.aPB_(this.mContext, intent);
                nsn.q();
                if (this.m.get()) {
                    this.n = true;
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.a("FunctionSetTemperatureCardReader", "go to setEmptyViewInTemperatureCards record ", Boolean.valueOf(this.m.get()));
        if (this.mContext != null) {
            e(0);
            long max2 = Math.max(this.o, this.t);
            if (efb.j()) {
                gnm.aPB_(this.mContext, new Intent(this.mContext, (Class<?>) KnitTemperatureActivity.class));
                nsn.q();
            } else {
                HealthDataDetailActivity.c(this.mContext, "BodyTemperatureCardConstructor", 24, max2);
            }
            if (this.m.get()) {
                this.n = true;
            }
        }
    }

    private void e(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this.mContext.getApplicationContext(), AnalyticsValue.TEMPERATURE_2060073.value(), hashMap, 0);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
        int[] iArr;
        super.readCardData();
        LogUtil.a("FunctionSetTemperatureCardReader", "temperature readCardData called");
        final HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        if (this.k) {
            iArr = new int[]{this.p.o(), this.p.h(), this.p.j()};
        } else {
            iArr = new int[]{2104, this.p.b(), this.p.h(), this.p.j(), this.p.s(), this.p.k()};
        }
        hiDataReadOption.setCount(1);
        hiDataReadOption.setType(iArr);
        if (this.mCardRefreshType == 0) {
            this.mCardRefreshType = -1;
            this.f.postDelayed(new Runnable() { // from class: ojg.2
                @Override // java.lang.Runnable
                public void run() {
                    HiHealthManager.d(ojg.this.mContext).readHiHealthData(hiDataReadOption, new a(ojg.this));
                }
            }, 100L);
        } else {
            HiHealthManager.d(this.mContext).readHiHealthData(hiDataReadOption, new a(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("FunctionSetTemperatureCardReader", "show temperature empty view!");
        setHasCardData(false);
        setFunctionSetBean(buildEmptyCardBean());
        Message obtainMessage = this.f.obtainMessage();
        obtainMessage.what = 101;
        obtainMessage.obj = getFunctionSetBean();
        this.f.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.f15722a).a(FunctionSetType.TEMPERATURE_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(this.d).d(this.mContext).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dbn_(SparseArray<Object> sparseArray) {
        ArrayList arrayList = new ArrayList();
        if (this.k) {
            Object obj = sparseArray.get(this.p.o());
            if (obj instanceof List) {
                arrayList.addAll((List) obj);
            }
            LogUtil.a("FunctionSetTemperatureCardReader", "dealWithData skin size ", Integer.valueOf(arrayList.size()));
        } else {
            Object obj2 = sparseArray.get(this.p.b());
            if (obj2 instanceof List) {
                arrayList.addAll((List) obj2);
            }
            Object obj3 = sparseArray.get(2104);
            if (obj3 instanceof List) {
                arrayList.addAll((List) obj3);
            }
            Object obj4 = sparseArray.get(this.p.s());
            if (obj4 instanceof List) {
                arrayList.addAll((List) obj4);
            }
            LogUtil.a("FunctionSetTemperatureCardReader", "dealWithData body size ", Integer.valueOf(arrayList.size()));
        }
        if (koq.b(arrayList)) {
            LogUtil.h("FunctionSetTemperatureCardReader", "dealWithData temperatureValueList is empty");
            this.e.a(null);
            return;
        }
        HiHealthData d2 = d(arrayList);
        if (d2 != null) {
            SharedPreferenceManager.c("privacy_center", "body_temperature", String.valueOf(d2.getStartTime()));
            this.e.a(d2);
            LogUtil.a("FunctionSetTemperatureCardReader", "getBarChartData healthData:", Long.valueOf(d2.getStartTime()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dbo_(SparseArray<Object> sparseArray) {
        if (Utils.o()) {
            LogUtil.h("FunctionSetTemperatureCardReader", "isOversea no remind data");
            this.t = 0L;
            this.m.set(false);
            this.f.sendEmptyMessage(102);
            return;
        }
        List<HiHealthData> arrayList = new ArrayList<>(16);
        Object obj = sparseArray.get(this.p.h());
        Object obj2 = sparseArray.get(this.p.j());
        Object obj3 = sparseArray.get(this.p.k());
        if (obj instanceof List) {
            List list = (List) obj;
            LogUtil.a("FunctionSetTemperatureCardReader", "highData data size ", Integer.valueOf(list.size()));
            arrayList.addAll(list);
        }
        if (obj2 instanceof List) {
            List list2 = (List) obj2;
            LogUtil.a("FunctionSetTemperatureCardReader", "lowData data size ", Integer.valueOf(list2.size()));
            arrayList.addAll(list2);
        }
        if (obj3 instanceof List) {
            List list3 = (List) obj3;
            LogUtil.a("FunctionSetTemperatureCardReader", "newHighData data size ", Integer.valueOf(list3.size()));
            arrayList.addAll(list3);
        }
        if (koq.b(arrayList)) {
            LogUtil.h("FunctionSetTemperatureCardReader", "getRemindDataList remind List is null");
            this.t = 0L;
            this.m.set(false);
            this.f.sendEmptyMessage(102);
            return;
        }
        HiHealthData d2 = d(arrayList);
        if (d2 == null) {
            LogUtil.h("FunctionSetTemperatureCardReader", "getRemindDataList remindData is null");
            this.t = 0L;
            this.m.set(false);
            this.f.sendEmptyMessage(102);
            return;
        }
        qpm.d(true);
        long b2 = qpm.b();
        this.t = d2.getEndTime();
        LogUtil.a("FunctionSetTemperatureCardReader", "remindData lastTime is ", Long.valueOf(b2), " remindTime ", Long.valueOf(this.t));
        this.m.set(this.t > b2);
        this.f.sendEmptyMessage(102);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (getFunctionSetBean() == null) {
            LogUtil.h("FunctionSetTemperatureCardReader", "updateRedDot cardBean is null");
            return;
        }
        LogUtil.a("FunctionSetTemperatureCardReader", "updateRedDot ", Boolean.valueOf(this.m.get()));
        getFunctionSetBean().e(this.m.get());
        notifyItemChanged(getFunctionSetBean());
    }

    private HiHealthData d(List<HiHealthData> list) {
        HiHealthData hiHealthData;
        if (koq.b(list)) {
            hiHealthData = null;
        } else {
            Collections.sort(list, new Comparator() { // from class: ojk
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(((HiHealthData) obj2).getEndTime(), ((HiHealthData) obj).getEndTime());
                    return compare;
                }
            });
            hiHealthData = list.get(0);
        }
        LogUtil.c("FunctionSetTemperatureCardReader", "the last healthData ", hiHealthData);
        return hiHealthData;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        if (this.k) {
            ImageView imageView = new ImageView(this.mContext);
            this.j = imageView;
            imageView.setImageResource(R.drawable._2131427757_res_0x7f0b01ad);
            return this.j;
        }
        d();
        return this.q;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.h = false;
        ReleaseLogUtil.e("TimeEat_FunctionSetTemperatureCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isNeedRefreshOnResume() {
        if (super.isNeedRefreshOnResume()) {
            return true;
        }
        if (this.h) {
            return false;
        }
        long b2 = qpm.b();
        this.m.set(this.t > b2);
        LogUtil.a("FunctionSetTemperatureCardReader", "onResume mIsUpdateView ", Boolean.valueOf(this.n), " mIsShowRedDot ", Boolean.valueOf(this.m.get()), " endTime ", Long.valueOf(b2));
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean != null) {
            if (this.n) {
                LogUtil.c("FunctionSetTemperatureCardReader", "mIsShowRedDot ", Boolean.valueOf(this.m.get()));
                functionSetBean.e(this.m.get());
                this.n = false;
            }
            if (this.i != UnitUtil.d()) {
                if (this.g) {
                    functionSetBean.e(a());
                } else {
                    functionSetBean.e(a(qpr.b((float) this.l)));
                }
            }
            notifyItemChanged(functionSetBean);
        }
        return false;
    }

    private CharSequence a(String str) {
        String b2;
        if (UnitUtil.d()) {
            this.i = true;
            b2 = nsf.b(R.string._2130843630_res_0x7f0217ee, str);
        } else {
            this.i = false;
            b2 = nsf.b(R.string._2130838454_res_0x7f0203b6, str);
        }
        return owm.b(b2, str);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == this.p.g() || i == this.p.m() || i == this.p.a() || i == this.p.e() || i == this.p.p() || i == this.p.t();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetTemperatureCardReader", "subscribeTemperatureData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetTemperatureCardReader", "registerTemperatureListener success");
        this.s = list;
    }

    private void g() {
        LogUtil.a("FunctionSetTemperatureCardReader", "subscribeTemperatureData");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(this.p.g()));
        arrayList.add(Integer.valueOf(this.p.m()));
        arrayList.add(Integer.valueOf(this.p.a()));
        arrayList.add(Integer.valueOf(this.p.e()));
        arrayList.add(Integer.valueOf(this.p.p()));
        arrayList.add(Integer.valueOf(this.p.t()));
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new FunctionSetBeanReader.c("FunctionSetTemperatureCardReader", this));
    }

    public void b() {
        LogUtil.a("FunctionSetTemperatureCardReader", "unSubscribeTemperatureData");
        List<Integer> list = this.s;
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.s, new FunctionSetBeanReader.b("FunctionSetTemperatureCardReader", "unSubscribeTemperatureData, isSuccess :"));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        b();
        e eVar = this.f;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }

    static class a implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ojg> f15724a;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        a(ojg ojgVar) {
            this.f15724a = new WeakReference<>(ojgVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("FunctionSetTemperatureCardReader", "showTemperature onResult called ", Integer.valueOf(i));
            ojg ojgVar = this.f15724a.get();
            if (ojgVar == null) {
                LogUtil.h("FunctionSetTemperatureCardReader", "TemperatureHiDataReadResultListener cardReader is null");
                return;
            }
            LogUtil.c("FunctionSetTemperatureCardReader", "data ", obj);
            if (!(obj instanceof SparseArray)) {
                ojgVar.e.a(null);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() > 0) {
                ojgVar.dbn_(sparseArray);
                ojgVar.dbo_(sparseArray);
            } else {
                LogUtil.h("FunctionSetTemperatureCardReader", "Temperature data none");
                ojgVar.e.a(null);
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_temperature);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.e;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetTemperatureCardReader";
    }
}

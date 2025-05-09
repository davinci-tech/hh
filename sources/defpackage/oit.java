package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import defpackage.kor;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class oit extends FunctionSetBeanReader {
    private static final int b = 2131430049;
    private List<Integer> c;
    private List<Integer> d;
    private b f;
    private a g;
    private int h;
    private long i;
    private double j;
    private d k;
    private List<HiHealthData> l;
    private HwHealthLineDataSet m;
    private HwHealthLineChart n;
    private String o;

    /* renamed from: a, reason: collision with root package name */
    private static final int f15712a = Color.parseColor("#4cFF4065");
    private static final int e = Color.parseColor("#00FF4065");

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839620_res_0x7f020844;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131427649_res_0x7f0b0141 : R.drawable.marketing_default_img_bloodsugar;
    }

    static class b extends HandleCacheDataRunnable {
        private List<Float> c;
        private boolean d;
        private final WeakReference<oit> e;

        b(oit oitVar) {
            super("FunctionSetBloodSugarCardReader", null);
            this.e = new WeakReference<>(oitVar);
        }

        void b(HiHealthData hiHealthData, List<Float> list) {
            oit oitVar = this.e.get();
            if (oitVar == null) {
                LogUtil.h("FunctionSetBloodSugarCardReader", "saveHealthData bloodSugarCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                oit.setHasCardData(this.e, false);
                oit.saveDataFromHealthApi("FunctionSetBloodSugarCardReader", this.e, (HiHealthData) null);
                return;
            }
            oit.setHasCardData(this.e, true);
            HiHealthData hiHealthData2 = new HiHealthData();
            hiHealthData2.setStartTime(hiHealthData.getStartTime());
            hiHealthData2.setEndTime(hiHealthData.getEndTime());
            hiHealthData2.putInt("_u", hiHealthData.toString().hashCode());
            hiHealthData2.putDouble("point_value", hiHealthData.getDouble("point_value"));
            hiHealthData2.putString("_t", qjv.c(oitVar.mContext, hiHealthData.getType()));
            hiHealthData2.putInt("_l", hiHealthData.getType());
            if (hiHealthData.getType() == 2108) {
                hiHealthData2.putString("_", marshallListToString(list));
                this.c = list;
            }
            LogUtil.a("FunctionSetBloodSugarCardReader", "showBloodSugar onResult called save, ", hiHealthData2.toString());
            oit.saveDataFromHealthApi("FunctionSetBloodSugarCardReader", this.e, hiHealthData2);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            oit oitVar = this.e.get();
            if (oitVar == null) {
                LogUtil.h("FunctionSetBloodSugarCardReader", "handleCacheData bloodSugarCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                oit.setHasCardData(this.e, false);
                if (this.d && z) {
                    oitVar.i();
                    return;
                }
                return;
            }
            this.d = true;
            oitVar.j = hiHealthData.getDouble("point_value");
            oitVar.h = hiHealthData.getInt("_l");
            String string = hiHealthData.getString("_t");
            if (oitVar.h == 2108) {
                oitVar.o = "BLOOD_SUGAR_CONTINUE";
                if (!z) {
                    ArrayList arrayList = new ArrayList(0);
                    unmarshallListFromString(hiHealthData.getString("_"), arrayList);
                    this.c = arrayList;
                }
                oitVar.a(this.c);
            } else {
                oitVar.o = "BLOOD_SUGAR_FINGER_TIP";
                LogUtil.a("FunctionSetBloodSugarCardReader", "handleCacheData showBloodSugarDataView : timePeriod = ", string, ", mLastDataType = ", Integer.valueOf(oitVar.h), ", mLastBloodSugar = ", Double.valueOf(oitVar.j));
            }
            LogUtil.a("FunctionSetBloodSugarCardReader", "showBloodSugar onResult called refresh, ", hiHealthData.toString());
            oitVar.b(hiHealthData.getDouble("point_value"), hiHealthData.getEndTime(), hiHealthData.getInt("_u"), string, z);
        }
    }

    public static class a extends BaseHandler<oit> {
        public a(oit oitVar) {
            super(Looper.getMainLooper(), oitVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbb_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oit oitVar, Message message) {
            LogUtil.a("FunctionSetBloodSugarCardReader", "handleMessageWhenReferenceNotNull()");
            if (oitVar == null || message == null) {
                LogUtil.a("FunctionSetBloodSugarCardReader", "handleMessageWhenReferenceNotNull obj or msg == null !");
                return;
            }
            int i = message.what;
            if (i == 0) {
                LogUtil.a("FunctionSetBloodSugarCardReader", "handleMessageWhenReferenceNotNull : refreshData");
                oitVar.notifyItemChanged((FunctionSetBean) message.obj);
                return;
            }
            if (i == 1) {
                if (message.obj instanceof List) {
                    oitVar.i = message.getData().getLong("key_last_time");
                    oitVar.l = (List) message.obj;
                    LogUtil.a("FunctionSetBloodSugarCardReader", "MSG_UPDATE_DAY_DATA, size=", Integer.valueOf(oitVar.l.size()));
                    return;
                } else {
                    oitVar.i = 0L;
                    oitVar.l = null;
                    LogUtil.a("FunctionSetBloodSugarCardReader", "MSG_UPDATE_DAY_DATA, size= ");
                    return;
                }
            }
            LogUtil.h("FunctionSetBloodSugarCardReader", "unkonw msg");
        }
    }

    public oit(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetBloodSugarCardReader", cardConfig);
        this.c = new ArrayList(3);
        this.f = new b(this);
        this.d = null;
        this.h = 0;
        this.i = 0L;
        this.j = 0.0d;
        this.g = new a(this);
        g();
        f();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: oiu
            @Override // java.lang.Runnable
            public final void run() {
                oit.d();
            }
        });
    }

    static /* synthetic */ void d() {
        HiUserPreference d2 = kor.a().d(BaseApplication.e(), "BLOOD_SUGAR_CONTINUE_MAX_THRESHOLD");
        HiUserPreference d3 = kor.a().d(BaseApplication.e(), "BLOOD_SUGAR_CONTINUE_MIN_THRESHOLD");
        String valueOf = String.valueOf(10.0f);
        if (d2 != null && StringUtils.i(d2.getValue())) {
            valueOf = d2.getValue();
        }
        String valueOf2 = String.valueOf(3.9f);
        if (d3 != null && StringUtils.i(d3.getValue())) {
            valueOf2 = d3.getValue();
        }
        deb.b("BLOOD_SUGAR_CONTINUE_MAX_THRESHOLD", valueOf);
        deb.b("BLOOD_SUGAR_CONTINUE_MIN_THRESHOLD", valueOf2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(double d2, long j, int i, String str, boolean z) {
        String d3;
        CommonUtil.a("FunctionSetBloodSugarCardReader", "-refreshBloodSugarDataAndTime enter");
        setHasCardData(true);
        setBiHasData(j);
        if (jdl.g(j, System.currentTimeMillis())) {
            d3 = DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_MD) + " " + str;
            LogUtil.a("FunctionSetBloodSugarCardReader", "shortDate", d3);
        } else {
            d3 = DateFormatUtil.d(j, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD);
        }
        double a2 = UnitUtil.a(Math.abs(d2), 1);
        String e2 = UnitUtil.e(a2, 1, 1);
        FunctionSetBean c2 = new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_hw_show_main_home_page_bloodsugar)).c(d3).a(owm.b(BaseApplication.e().getResources().getQuantityString(R.plurals._2130903076_res_0x7f030024, UnitUtil.e(a2), e2), e2)).a(FunctionSetType.BLOOD_SUGAR_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).d(this.mContext).c();
        c2.c(i);
        if (z) {
            Message obtainMessage = this.g.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.obj = c2;
            this.g.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetBloodSugarCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void d(String str) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.mContext.getApplicationContext(), str, hashMap, 0);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return this.c.contains(Integer.valueOf(i));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetBloodSugarCardReader", "showBloodSugar readCardData called");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(qjv.b());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setReadType(0);
        kor.a().d(this.mContext, HiDataReadProOption.builder().e(hiDataReadOption).d(true).e(), new c(this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        ohy.c().a(new kor.a(new c(this)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list) {
        long currentTimeMillis = System.currentTimeMillis();
        int size = list.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            HiHealthData hiHealthData = list.get(size);
            if (hiHealthData.getEndTime() <= currentTimeMillis) {
                break;
            } else {
                LogUtil.h("FunctionSetBloodSugarCardReader", "bloodSugarTime=", Long.valueOf(hiHealthData.getEndTime()), ", currentTime=", Long.valueOf(currentTimeMillis));
            }
        }
        HiHealthData hiHealthData2 = list.get(size);
        SharedPreferenceManager.c("privacy_center", "blood_sugar", String.valueOf(hiHealthData2.getStartTime()));
        LogUtil.a("FunctionSetBloodSugarCardReader", "showBloodSugar onResult called data, ", hiHealthData2.toString());
        this.f.b(hiHealthData2, hiHealthData2.getType() == 2108 ? b(list.subList(0, size)) : null);
    }

    private List<Float> b(List<HiHealthData> list) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((HiHealthData) it.next()).getType() != 2108) {
                it.remove();
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (koq.b(arrayList)) {
            LogUtil.h("FunctionSetBloodSugarCardReader", "tempList is empty");
            return arrayList2;
        }
        if (arrayList.size() > 50.0f) {
            i = Math.round(arrayList.size() / 50.0f);
            LogUtil.a("FunctionSetBloodSugarCardReader", "size = ", Integer.valueOf(arrayList.size()), ", interval = ", Integer.valueOf(i));
        } else {
            i = 1;
        }
        int i2 = 0;
        long j = 0;
        while (true) {
            int i3 = i * i2;
            if (i3 >= arrayList.size() - 1) {
                break;
            }
            HiHealthData hiHealthData = (HiHealthData) arrayList.get(i3);
            if (hiHealthData.getEndTime() - j > 2100000) {
                arrayList2.add(Float.valueOf(0.0f));
            }
            arrayList2.add(Float.valueOf(hiHealthData.getFloatValue()));
            j = hiHealthData.getEndTime();
            i2++;
        }
        HiHealthData hiHealthData2 = (HiHealthData) arrayList.get(arrayList.size() - 1);
        if (hiHealthData2.getEndTime() - j > 2100000) {
            arrayList2.add(Float.valueOf(0.0f));
        }
        arrayList2.add(Float.valueOf(hiHealthData2.getFloatValue()));
        return arrayList2;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetBloodSugarCardReader", "subscribeBloodSugarData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetBloodSugarCardReader", "registerBloodSugarListener success");
        this.d = list;
    }

    private void g() {
        LogUtil.a("FunctionSetBloodSugarCardReader", "subscribeBloodSugarData");
        this.k = new d("FunctionSetBloodSugarCardReader", this);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(102, this.k);
        qqk.a().d(this.k);
        this.c.add(102);
        this.c.add(10);
        this.c.add(Integer.valueOf(HiSubscribeType.e));
    }

    public void e() {
        LogUtil.a("FunctionSetBloodSugarCardReader", "unSubscribeBloodSugarData");
        List<Integer> list = this.d;
        if (list != null && !list.isEmpty()) {
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.d, new FunctionSetBeanReader.b("FunctionSetBloodSugarCardReader", "unSubscribeBloodSugarData, isSuccess :"));
        }
        qqk.a().a(this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.a("FunctionSetBloodSugarCardReader", "show empty view!");
        setHasCardData(false);
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.g.obtainMessage();
        obtainMessage.what = 0;
        obtainMessage.obj = buildEmptyCardBean;
        this.g.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_hw_show_main_home_page_bloodsugar)).a(FunctionSetType.BLOOD_SUGAR_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(this.mContext.getResources().getString(R.string.IDS_hw_show_main_home_page_bloodsugar_description)).d(this.mContext).c();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        if ("BLOOD_SUGAR_CONTINUE".equals(this.o)) {
            return daZ_();
        }
        return dba_();
    }

    private View daZ_() {
        HwHealthLineChart hwHealthLineChart = this.n;
        if (hwHealthLineChart == null) {
            hwHealthLineChart = (HwHealthLineChart) View.inflate(this.mContext, R.layout.card_heart_view, null).findViewById(R.id.linechart);
            this.n = hwHealthLineChart;
            int intrinsicWidth = ContextCompat.getDrawable(this.mContext, b).getIntrinsicWidth() / 2;
            this.n.setPadding(intrinsicWidth, 0, intrinsicWidth, 0);
            hwHealthLineChart.setTouchEnabled(false);
            hwHealthLineChart.getXAxis().setEnabled(false);
            hwHealthLineChart.getDescription().setEnabled(false);
            HwHealthYAxis axisFirstParty = hwHealthLineChart.getAxisFirstParty();
            axisFirstParty.setDrawLabels(false);
            axisFirstParty.setDrawGridLines(false);
            axisFirstParty.setDrawAxisLine(false);
            hwHealthLineChart.disableLabelsForce();
        }
        HwHealthLineDataSet hwHealthLineDataSet = this.m;
        if (hwHealthLineDataSet == null) {
            LogUtil.h("FunctionSetBloodSugarCardReader", "createCardView bloodSugarSet is null");
            return null;
        }
        now nowVar = (now) hwHealthLineChart.getData();
        if (nowVar == null) {
            nowVar = new now(new ArrayList(1));
            hwHealthLineChart.setData(nowVar);
        }
        List dataSets = nowVar.getDataSets();
        if (dataSets == null) {
            dataSets = new ArrayList(1);
        }
        if (dataSets.size() == 0 || dataSets.get(0) != hwHealthLineDataSet) {
            HwHealthYAxis axisFirstParty2 = hwHealthLineChart.getAxisFirstParty();
            axisFirstParty2.setAxisMinimum(hwHealthLineDataSet.getYMin() - 10.0f);
            axisFirstParty2.setAxisMaximum(hwHealthLineDataSet.getYMax() + 10.0f);
            dataSets.clear();
            dataSets.add(hwHealthLineDataSet);
            hwHealthLineChart.refresh();
        }
        return hwHealthLineChart;
    }

    private View dba_() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.card_blood_pressure_blood_sugar, (ViewGroup) null);
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) inflate.findViewById(R.id.blood_sugar_seek_bar);
        int i = 460;
        healthSeekBarExtend.setMax(460);
        healthSeekBarExtend.setThumb(this.mContext.getResources().getDrawable(b));
        int a2 = qjv.a(this.h, (float) this.j);
        LogUtil.c("FunctionSetBloodSugarCardReader", "progress = ", Integer.valueOf(a2));
        if (a2 < 0) {
            i = 0;
        } else if (a2 <= 460) {
            LogUtil.c("FunctionSetBloodSugarCardReader", "The progress is normal. No special handling is required.");
            i = a2;
        }
        healthSeekBarExtend.setProgress(i);
        if (this.h == 2008) {
            healthSeekBarExtend.setRulerSrc(this.mContext.getResources().getDrawable(R.drawable._2131427738_res_0x7f0b019a));
        } else {
            healthSeekBarExtend.setRulerSrc(this.mContext.getResources().getDrawable(R.drawable._2131427739_res_0x7f0b019b));
        }
        return inflate;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public int getExtraWidth() {
        return ContextCompat.getDrawable(this.mContext, b).getIntrinsicWidth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Float> list) {
        if (koq.b(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            float floatValue = list.get(i).floatValue();
            if (floatValue > 0.0f) {
                arrayList.add(new HwHealthBaseEntry(i, floatValue));
            }
        }
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.mContext, arrayList, "line brief", "line label", "line unit");
        hwHealthLineDataSet.e(1);
        hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: oit.2
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public boolean drawLine(int i2, int i3, int i4) {
                return i3 - i2 <= i4;
            }
        });
        hwHealthLineDataSet.setColor(ContextCompat.getColor(this.mContext, R.color._2131297764_res_0x7f0905e4));
        hwHealthLineDataSet.b(f15712a, e, true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        hwHealthLineDataSet.setLineWidth(2.0f);
        this.m = hwHealthLineDataSet;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (viewType == FunctionSetBean.ViewType.DATA_VIEW) {
            LogUtil.a("FunctionSetBloodSugarCardReader", "go to bloodDataView BLOOD_SUGAR_CARD record");
            d(AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_DETAIL_2010027.value());
            HashMap hashMap = new HashMap(16);
            hashMap.put("type", 0);
            String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_JUMP_DETAIL_2040132.value();
            hashMap.put("click", "1");
            gge.e(value, hashMap);
            if (this.l != null) {
                pyw.e().c(HiDateUtil.t(this.i), this.l);
            }
            HealthDataDetailActivity.c(this.mContext, "BloodSugarCardConstructor", 8, this.i);
            return;
        }
        LogUtil.a("FunctionSetBloodSugarCardReader", "go to forEmptyView BLOOD_SUGAR_CARD record");
        d(AnalyticsValue.HEALTH_HOME_BLOOD_SUGAR_DETAIL_2010027.value());
        HealthDataDetailActivity.a(this.mContext, "BloodSugarCardConstructor", 8);
    }

    private void c() {
        ArrayList arrayList = new ArrayList();
        String b2 = qjv.b(this.mContext, "key_day_data");
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("FunctionSetBloodSugarCardReader", "data is null");
            return;
        }
        try {
            jdr.d(b2, getClass().getClassLoader(), null, arrayList);
            if (!arrayList.isEmpty()) {
                Object obj = arrayList.get(arrayList.size() - 1);
                if (obj instanceof HiHealthData) {
                    this.i = ((HiHealthData) obj).getStartTime();
                }
            }
            this.l = arrayList;
            LogUtil.a("FunctionSetBloodSugarCardReader", "refreshDayDataFromSp, size=", Integer.valueOf(arrayList.size()));
        } catch (ClassCastException unused) {
            LogUtil.b("FunctionSetBloodSugarCardReader", "ClassCastException");
            this.l = Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list) {
        qjv.a(this.mContext, "key_day_data", (list == null || list.isEmpty()) ? "" : jdr.b(list, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list) {
        Message obtain = Message.obtain();
        if (list != null && !list.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putLong("key_last_time", list.get(list.size() - 1).getStartTime());
            obtain.setData(bundle);
            obtain.obj = list;
        }
        obtain.what = 1;
        this.g.sendMessage(obtain);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        e();
        a aVar = this.g;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
    }

    static class d extends FunctionSetBeanReader.c {
        private WeakReference<FunctionSetBeanReader> c;

        public d(String str, FunctionSetBeanReader functionSetBeanReader) {
            super(str, functionSetBeanReader);
            this.c = new WeakReference<>(functionSetBeanReader);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.c, com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            FunctionSetBeanReader functionSetBeanReader = this.c.get();
            if (functionSetBeanReader instanceof oit) {
                oit oitVar = (oit) functionSetBeanReader;
                if (i == 102) {
                    if ("BLOOD_SUGAR_CONTINUE_MAX_THRESHOLD".equals(str) || "BLOOD_SUGAR_CONTINUE_MIN_THRESHOLD".equals(str)) {
                        oitVar.f();
                        return;
                    }
                    return;
                }
                if (i == HiSubscribeType.e || i == 10) {
                    oitVar.i = 0L;
                }
                super.onChange(i, hiHealthClient, str, hiHealthData, j);
            }
        }
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference<oit> b;

        c(oit oitVar) {
            this.b = new WeakReference<>(oitVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("FunctionSetBloodSugarCardReader", "showBloodSugar onResult called, error=", Integer.valueOf(i), ", data: ", obj);
            oit oitVar = this.b.get();
            if (oitVar == null) {
                LogUtil.h("FunctionSetBloodSugarCardReader", "BloodSugarHiDataReadResultListener cardReader is null");
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                if (koq.c(list)) {
                    LogUtil.a("FunctionSetBloodSugarCardReader", "list: ", list.toString());
                    Collections.sort(list, new Comparator() { // from class: oiv
                        @Override // java.util.Comparator
                        public final int compare(Object obj2, Object obj3) {
                            int compare;
                            compare = Long.compare(((HiHealthData) obj2).getStartTime(), ((HiHealthData) obj3).getStartTime());
                            return compare;
                        }
                    });
                    oitVar.d((List<HiHealthData>) list);
                    oitVar.c((List<HiHealthData>) list);
                    oitVar.e((List<HiHealthData>) list);
                    return;
                }
            }
            LogUtil.h("FunctionSetBloodSugarCardReader", "data none");
            oitVar.d((List<HiHealthData>) null);
            oitVar.c((List<HiHealthData>) null);
            oitVar.f.b(null, null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(this.mContext) ? R.color._2131297758_res_0x7f0905de : R.color._2131297757_res_0x7f0905dd;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_blood_sugar);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetBloodSugarCardReader";
    }
}

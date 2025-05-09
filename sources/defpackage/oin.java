package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
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
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarChart;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveHoursActivity;
import health.compact.a.HiDateUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class oin extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private View f15702a;
    private List<HwHealthBarEntry> b;
    private final c c;
    private List<Integer> d;
    private List<Integer> e;
    private final HiDataReadResultListener f;
    private boolean g;
    private boolean h;
    private long i;
    private b j;

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

    static class c extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<oin> f15703a;
        private boolean b;
        private List<Integer> c;
        private HiHealthData d;

        c(oin oinVar) {
            super("FunctionSetActiveHourCardReader", null);
            this.f15703a = new WeakReference<>(oinVar);
        }

        void c(List<Integer> list) {
            this.c = list;
            if (list != null) {
                d(true).putString("_", marshallListToString(list));
            }
        }

        void e(boolean z) {
            HiHealthData hiHealthData;
            if (z) {
                oin.setHasCardData(this.f15703a, true);
                hiHealthData = d(false);
            } else {
                oin.setHasCardData(this.f15703a, false);
                this.c = null;
                hiHealthData = null;
            }
            this.d = null;
            oin.saveDataFromHealthApi("FunctionSetActiveHourCardReader", this.f15703a, hiHealthData);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            oin oinVar = this.f15703a.get();
            if (oinVar == null) {
                LogUtil.h("FunctionSetActiveHourCardReader", "handleCacheData activeCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                oin.setHasCardData(this.f15703a, false);
                if (this.b && z) {
                    oinVar.b();
                    return;
                }
                return;
            }
            this.b = true;
            List list = this.c;
            if (!z || list == null) {
                list = new ArrayList();
                unmarshallListFromString(hiHealthData.getString("_"), list);
            } else {
                this.c = null;
            }
            if (!list.isEmpty()) {
                oinVar.d((List<Integer>) list);
                oinVar.h = true;
                oinVar.e(hiHealthData.getLong("_t"), hiHealthData.getInt("_s"), hiHealthData.getInt("_u"), z);
            } else {
                LogUtil.h("FunctionSetActiveHourCardReader", "handleCacheData activeHourValueList is empty");
                if (z) {
                    return;
                }
                oinVar.refreshCardBySp(oinVar.buildEmptyCardBean());
            }
        }

        private HiHealthData d(boolean z) {
            if (z && this.d == null) {
                this.d = new HiHealthData();
            }
            return this.d;
        }
    }

    public oin(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetActiveHourCardReader", cardConfig);
        this.g = false;
        this.d = new ArrayList(1);
        this.e = null;
        this.h = false;
        this.c = new c(this);
        this.f = new HiDataReadResultListener() { // from class: oin.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                List list = oin.this.getList(obj, DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value());
                if (koq.b(list)) {
                    LogUtil.h("FunctionSetActiveHourCardReader", "dataList is empty!");
                    oin.this.c.e(false);
                    oin.this.j.sendEmptyMessage(5);
                } else {
                    LogUtil.a("FunctionSetActiveHourCardReader", "readCardData success");
                    Message obtainMessage = oin.this.j.obtainMessage();
                    obtainMessage.what = 3;
                    obtainMessage.obj = list;
                    oin.this.j.sendMessage(obtainMessage);
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("FunctionSetActiveHourCardReader", "onResultIntent : read failed errorCode is", Integer.valueOf(i2));
            }
        };
        this.j = new b(this);
        e();
    }

    private void e() {
        LogUtil.a("FunctionSetActiveHourCardReader", "subscribeActiveHourData");
        this.d.add(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()));
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(this.d, new FunctionSetBeanReader.c("FunctionSetActiveHourCardReader", this));
    }

    public void c() {
        LogUtil.a("FunctionSetActiveHourCardReader", "unSubscribeActiveHourData");
        List<Integer> list = this.e;
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.e, new FunctionSetBeanReader.b("FunctionSetActiveHourCardReader", "unSubscribeActiveHourData, isSuccess:"));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        c();
        b bVar = this.j;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        readCardData();
        ReleaseLogUtil.e("TimeEat_FunctionSetActiveHourCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        LogUtil.a("FunctionSetActiveHourCardReader", "readCardData start");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(24);
        HiHealthNativeApi.a(this.mContext).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).d(true).e(), this.f);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        ohy.c().a(this.f);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetActiveHourCardReader", "unSubscribeActiveHourData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetActiveHourCardReader", "registerActiveHourListener success");
        this.e = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, int i, int i2, boolean z) {
        setHasCardData(true);
        this.i = j;
        String b2 = owm.b(j);
        LogUtil.a("FunctionSetActiveHourCardReader", "requestDatas", "activeHourMeasureBean", b2, "---", "---", Integer.valueOf(i));
        if (i == 0) {
            if (z) {
                return;
            }
            refreshCardBySp(buildEmptyCardBean());
            return;
        }
        FunctionSetBean c2 = c(b2, i);
        c2.c(i2);
        if (z) {
            Message obtainMessage = this.j.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = c2;
            this.j.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    static class b extends BaseHandler<oin> {
        b(oin oinVar) {
            super(Looper.getMainLooper(), oinVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: daU_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oin oinVar, Message message) {
            int i = message.what;
            if (i == 1) {
                LogUtil.a("FunctionSetActiveHourCardReader", "handleMessageWhenReferenceNotNull");
                if (message.obj instanceof FunctionSetBean) {
                    oinVar.notifyItemChanged((FunctionSetBean) message.obj);
                    return;
                }
                return;
            }
            if (i == 3) {
                oinVar.d(message.obj);
                oinVar.c.e(true);
            } else if (i == 4) {
                LogUtil.a("FunctionSetActiveHourCardReader", "MSG_HAVE_ACTIVE_HOUR_DATA:");
                oinVar.a(true);
            } else if (i == 5) {
                LogUtil.a("FunctionSetActiveHourCardReader", "MSG_NOT_HAVE_ACTIVE_HOUR_DATA:");
                oinVar.a(false);
            } else {
                LogUtil.h("FunctionSetActiveHourCardReader", "unkonw msg");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("FunctionSetActiveHourCardReader", "show empty view!");
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.j.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = buildEmptyCardBean;
        this.j.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string._2130845999_res_0x7f02212f)).a(FunctionSetType.ACTIVE_HOUR_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(this.mContext.getResources().getString(R.string._2130846075_res_0x7f02217b)).d(this.mContext).c();
    }

    private FunctionSetBean c(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(UnitUtil.e(i, 1, 0));
        stringBuffer.append("/");
        stringBuffer.append(UnitUtil.e(12.0d, 1, 0));
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string._2130845999_res_0x7f02212f)).c(str).a(stringBuffer.toString()).b(this.mContext.getResources().getString(R.string._2130837657_res_0x7f020099)).a(FunctionSetType.ACTIVE_HOUR_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).d(this.mContext).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        if (obj instanceof List) {
            List<HiHealthData> list = (List) obj;
            HiHealthData hiHealthData = list.get(0);
            if (hiHealthData == null) {
                LogUtil.h("FunctionSetActiveHourCardReader", "data is empty");
            } else {
                a(list, HiDateUtil.t(hiHealthData.getStartTime()));
            }
        }
    }

    private void a(List<HiHealthData> list, long j) {
        if (list.isEmpty()) {
            return;
        }
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            HiHealthData hiHealthData = list.get((size - i) - 1);
            if (TimeUtil.b(hiHealthData.getStartTime(), j)) {
                arrayList.add(Integer.valueOf(jec.b(hiHealthData.getStartTime())));
            }
        }
        this.c.c(arrayList);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetActiveHourCardReader", "createCardView");
        if (this.h) {
            LogUtil.a("FunctionSetActiveHourCardReader", "mIsDataUpdated");
            if (this.f15702a == null) {
                try {
                    this.f15702a = LayoutInflater.from(this.mContext).inflate(R.layout.card_active_hour_view, (ViewGroup) null);
                } catch (InflateException e) {
                    LogUtil.h("FunctionSetActiveHourCardReader", e.getClass().getSimpleName() + "inflate mCardActiveView fail!");
                }
            }
            e(this.b);
            this.h = false;
        }
        return this.f15702a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<Integer> list) {
        HwHealthBarEntry hwHealthBarEntry;
        ArrayList arrayList = new ArrayList(16);
        int size = list.size();
        LogUtil.a("FunctionSetActiveHourCardReader", "mPressureMeasureValueList , size ", Integer.valueOf(size));
        for (int i = 0; i < 16; i++) {
            if (i < size) {
                int b2 = b(list.get(i).intValue());
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(list.get(i).intValue(), b2, b2));
            } else {
                int color = this.mContext.getResources().getColor(R.color._2131297799_res_0x7f090607);
                hwHealthBarEntry = new HwHealthBarEntry(i, new nnc(20.0f, color, color));
            }
            arrayList.add(hwHealthBarEntry);
        }
        this.b = arrayList;
        e(arrayList);
    }

    private int b(int i) {
        return this.mContext.getResources().getColor(R.color._2131299143_res_0x7f090b47);
    }

    private void e(List<HwHealthBarEntry> list) {
        View view = this.f15702a;
        if (view == null) {
            LogUtil.h("FunctionSetActiveHourCardReader", "mCardActiveView is null");
            return;
        }
        LogUtil.a("FunctionSetActiveHourCardReader", "mCardActiveView ID: ", Integer.valueOf(view.getId()));
        HwHealthBarChart hwHealthBarChart = (HwHealthBarChart) this.f15702a.findViewById(R.id.barchart);
        if (hwHealthBarChart == null) {
            LogUtil.h("FunctionSetActiveHourCardReader", "initSet barChart is null");
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
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setClass(this.mContext, KnitActiveHoursActivity.class);
        gnm.aPB_(this.mContext, intent);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetActiveHourCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.g = z;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_pressure);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.c;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetActiveHourCardReader";
    }
}

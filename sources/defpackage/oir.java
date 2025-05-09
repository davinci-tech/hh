package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBarExtend;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class oir extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private long f15705a;
    private final c b;
    private List<Integer> c;
    private String d;
    private boolean e;
    private final d f;
    private long g;
    private List<HiHealthData> h;
    private a i;
    private Observer j;
    private i l;
    private HealthSeekBarExtend n;
    private final Resources o;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839618_res_0x7f020842;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131427619_res_0x7f0b0123 : R.drawable.marketing_default_img_bloodpressure;
    }

    public oir(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetBloodPressureReader", cardConfig);
        this.e = false;
        this.b = new c(this);
        this.c = null;
        this.h = new ArrayList(3);
        this.o = b().getResources();
        this.f = new d(this);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()));
        HiHealthNativeApi.a(b()).subscribeHiHealthData(arrayList, new FunctionSetBeanReader.c("FunctionSetBloodPressureReader", this));
        qhc.b();
        d(arrayList);
        drl.e(null);
        BloodPressureUtil.f();
        a aVar = new a(this);
        this.i = aVar;
        ObserverManagerUtil.d(aVar, ObserveLabels.SUMMARY_DATA_INIT);
    }

    static class a implements Observer {
        private final WeakReference<oir> e;

        a(oir oirVar) {
            this.e = new WeakReference<>(oirVar);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            oir oirVar = this.e.get();
            if (oirVar == null || objArr == null || !ObserveLabels.SUMMARY_DATA_INIT.equals(str)) {
                ReleaseLogUtil.d("FunctionSetBloodPressureReader", "InnerObserver notify functionSetBloodPressureReader ", oirVar, " objects ", objArr, " label ", str);
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Integer) || !(objArr[1] instanceof HashMap)) {
                ReleaseLogUtil.d("FunctionSetBloodPressureReader", "InnerObserver notify is not legal");
                return;
            }
            int intValue = ((Integer) obj).intValue();
            HashMap hashMap = (HashMap) objArr[1];
            LogUtil.a("FunctionSetBloodPressureReader", "initSummary errorCode： ", Integer.valueOf(intValue));
            Message obtainMessage = oirVar.f.obtainMessage();
            obtainMessage.what = 7;
            obtainMessage.obj = hashMap.get(MessageConstant.BLOOD_PRESSURE_TYPE);
            oirVar.f.sendMessage(obtainMessage);
        }
    }

    private void d(final List<Integer> list) {
        LogUtil.c("FunctionSetBloodPressureReader", "registerSyncDataBroadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        this.l = new i(this);
        LocalBroadcastManager.getInstance(b()).registerReceiver(this.l, intentFilter);
        Observer observer = new Observer() { // from class: oir.2
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (StringUtils.g(str) || !oir.this.e) {
                    LogUtil.b("FunctionSetBloodPressureReader", "label is null || isRegSuccess");
                } else if ("com.huawei.plugin.account.login".equals(str)) {
                    HiHealthNativeApi.a(oir.this.b()).subscribeHiHealthData(list, new FunctionSetBeanReader.c("FunctionSetBloodPressureReader", oir.this));
                }
            }
        };
        this.j = observer;
        ObserverManagerUtil.d(observer, "com.huawei.plugin.account.login");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(double d2, double d3, long j, int i2, boolean z) {
        setHasCardData(true);
        this.g = j;
        setBiHasData(j);
        setBiHasDataType("single");
        String b2 = owm.b(j);
        LogUtil.a("FunctionSetBloodPressureReader", "refreshBloodPressureDataAndTime shortDate ", b2);
        FunctionSetBean c2 = new FunctionSetBean.a(this.o.getString(R.string.IDS_hw_show_main_home_page_bloodpressure)).a(owm.e("[\\d\\/]", nsf.a(R.plurals._2130903433_res_0x7f030189, UnitUtil.e(d2, Locale.getDefault()), UnitUtil.e(d3, 1, 0), UnitUtil.e(d2, 1, 0)))).d(eeu.b((int) d3, (int) d2)).a(FunctionSetType.BLOOD_PRESSURE_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).c(b2).d(b()).d(a(j)).c();
        if (e()) {
            c2.a(this.d);
        } else {
            c2.a("");
        }
        c2.c(i2);
        setFunctionSetBean(c2);
        if (z) {
            Message obtainMessage = this.f.obtainMessage();
            obtainMessage.what = 5;
            obtainMessage.obj = c2;
            this.f.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(c2);
    }

    public boolean a(long j) {
        return j > SharedPreferenceManager.b("BloodPressure", "save_time", 0L);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        FunctionSetBean functionSetBean = functionSetSubCardData.getFunctionSetBean();
        if (!(viewHolder instanceof FunctionSetBeanReader.MyHolder) || functionSetBean == null) {
            LogUtil.h("FunctionSetBloodPressureReader", "onBindViewHolder viewHolder ", viewHolder);
            return;
        }
        View view = ((FunctionSetBeanReader.MyHolder) viewHolder).itemView;
        LogUtil.a("FunctionSetBloodPressureReader", "bloodPressureView: " + view);
        if (functionSetBean.q() == FunctionSetBean.ViewType.EMPTY_VIEW) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.function_set_empty_items_title);
            if (healthTextView == null) {
                LogUtil.b("FunctionSetBloodPressureReader", "cardView is null");
                return;
            }
            a(healthTextView);
        }
        ReleaseLogUtil.e("TimeEat_FunctionSetBloodPressureReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void a(HealthTextView healthTextView) {
        int dimensionPixelSize;
        int i2;
        if (LanguageUtil.p(b()) || LanguageUtil.q(b()) || LanguageUtil.w(b()) || LanguageUtil.r(b()) || LanguageUtil.au(b()) || LanguageUtil.f(b()) || LanguageUtil.bb(b()) || LanguageUtil.ag(b()) || LanguageUtil.v(b())) {
            dimensionPixelSize = this.o.getDimensionPixelSize(R.dimen._2131362375_res_0x7f0a0247);
        } else {
            dimensionPixelSize = this.o.getDimensionPixelSize(R.dimen._2131365061_res_0x7f0a0cc5);
        }
        float f = dimensionPixelSize;
        if (LanguageUtil.p(b())) {
            f = this.o.getDisplayMetrics().density * 13.0f;
            i2 = 2;
        } else {
            i2 = 0;
        }
        float f2 = this.o.getDisplayMetrics().density;
        if (healthTextView != null) {
            if (Math.abs(f2) > 1.0E-6f) {
                healthTextView.setTextSize(1, f / f2);
            }
            healthTextView.setPadding(0, i2, 0, 0);
        }
    }

    private void e(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        hashMap.put("from", "1");
        ixx.d().d(b(), str, hashMap, 0);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onBottomLayoutClickListener(RelativeLayout relativeLayout) {
        if (relativeLayout == null) {
            LogUtil.h("FunctionSetBloodPressureReader", "relativeLayout is null");
        } else {
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: oir.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o()) {
                        LogUtil.h("FunctionSetBloodPressureReader", "onBottomLayoutClickListener is fast click");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        oir oirVar = oir.this;
                        oirVar.b(oirVar.e());
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i2) {
        return i2 == HiSubscribeType.f4119a;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        a(System.currentTimeMillis(), new ArrayList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, List<HiHealthData> list) {
        LogUtil.a("FunctionSetBloodPressureReader", "start queryLastData endTime: ", Long.valueOf(j), " list size: ", Integer.valueOf(list.size()));
        HiAggregateOption a2 = a();
        a2.setCount(1);
        a2.setTimeRange(0L, j);
        HiHealthManager.d(b()).aggregateHiHealthData(a2, new e(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, long j2, List<HiHealthData> list) {
        LogUtil.a("FunctionSetBloodPressureReader", "start queryTimeRange startTime: ", Long.valueOf(j), " endTime: ", Long.valueOf(j2));
        HiAggregateOption a2 = a();
        a2.setTimeRange(j, j2);
        HiHealthManager.d(b()).aggregateHiHealthData(a2, new b(j, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiHealthData e(List<HiHealthData> list) {
        HiHealthData hiHealthData = new HiHealthData();
        double d2 = 0.0d;
        long j = 0;
        double d3 = 0.0d;
        for (HiHealthData hiHealthData2 : list) {
            d2 += hiHealthData2.getDouble("BLOOD_PRESSURE_SYSTOLIC");
            d3 += hiHealthData2.getDouble("BLOOD_PRESSURE_DIASTOLIC");
            j = Math.max(j, hiHealthData2.getStartTime());
        }
        double size = list.size();
        hiHealthData.putDouble("BLOOD_PRESSURE_SYSTOLIC", Math.round(d2 / size));
        hiHealthData.putDouble("BLOOD_PRESSURE_DIASTOLIC", Math.round(d3 / size));
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j);
        return hiHealthData;
    }

    private HiAggregateOption a() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{2006, 2007});
        hiAggregateOption.setConstantsKey(new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("FunctionSetBloodPressureReader", "showEmptyView");
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.f.obtainMessage();
        obtainMessage.what = 5;
        obtainMessage.obj = buildEmptyCardBean;
        this.f.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.o.getString(R.string.IDS_hw_show_main_home_page_bloodpressure)).e(this.o.getString(R.string.IDS_hw_show_main_home_page_bloodpressure_description)).a(FunctionSetType.BLOOD_PRESSURE_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(b()).d(false).c();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetBloodPressureReader", "updateSuccessList");
        if (koq.b(list)) {
            LogUtil.h("FunctionSetBloodPressureReader", "updateSuccessList successList is empty");
            this.e = true;
        } else {
            this.c = list;
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        c();
        return this.n;
    }

    private void c() {
        View inflate = LayoutInflater.from(b()).inflate(R.layout.card_blood_pressure_blood_sugar, (ViewGroup) null);
        LogUtil.a("FunctionSetBloodPressureReader", "initBloodPressureSeekBar inflated view: ", inflate);
        HealthSeekBarExtend healthSeekBarExtend = (HealthSeekBarExtend) inflate.findViewById(R.id.blood_sugar_seek_bar);
        this.n = healthSeekBarExtend;
        if (healthSeekBarExtend == null) {
            LogUtil.b("FunctionSetBloodPressureReader", "initBloodPressureSeekBar mSeekBarExtend is null");
            return;
        }
        int i2 = 100;
        healthSeekBarExtend.setMax(100);
        this.n.setThumb(ContextCompat.getDrawable(b(), R.drawable._2131430049_res_0x7f0b0aa1));
        HiHealthData hiHealthData = this.h.get(r0.size() - 1);
        double d2 = hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC");
        double d3 = hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC");
        int a2 = BloodPressureUtil.a((int) d2, (int) d3);
        LogUtil.c("FunctionSetBloodPressureReader", "progress = ", Integer.valueOf(a2), "systolic ", Double.valueOf(d2), ", diastolic", Double.valueOf(d3));
        if (a2 < 0) {
            i2 = 0;
        } else if (a2 <= 100) {
            LogUtil.c("FunctionSetBloodPressureReader", "The progress is normal. No special handling is required.");
            i2 = a2;
        }
        int c2 = eeu.c();
        if (c2 == 6) {
            this.n.setRulerSrc(ContextCompat.getDrawable(b(), R.drawable._2131428312_res_0x7f0b03d8));
        } else if (c2 == 5) {
            this.n.setRulerSrc(ContextCompat.getDrawable(b(), R.drawable._2131428310_res_0x7f0b03d6));
        } else {
            this.n.setRulerSrc(ContextCompat.getDrawable(b(), R.drawable._2131428311_res_0x7f0b03d7));
        }
        this.n.setProgress(i2);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (nsn.a(500)) {
            return;
        }
        b(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        long j = this.g;
        if (j > 0) {
            SharedPreferenceManager.e("BloodPressure", "save_time", j);
        }
        refreshRedDotSyncNotify(false);
        e(AnalyticsValue.HEALTH_HOME_BLOOD_PRESSURE_DETAIL_2010025.value());
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) KnitBloodPressureActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("key_bundle_health_last_data_time", this.g);
        if (z) {
            intent.putExtra("is_scroll_to_summary", true);
        }
        gnm.aPB_(BaseApplication.getContext(), intent);
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("FunctionSetBloodPressureReader", "activity is null when onCardViewClickListener.");
        } else {
            activity.overridePendingTransition(R.anim._2130772060_res_0x7f01005c, R.anim._2130771981_res_0x7f01000d);
            nsn.ai(activity);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.c)) {
            LogUtil.h("FunctionSetBloodPressureReader", "onDestroy mBloodPressureSuccessList is empty");
        } else {
            HiHealthNativeApi.a(b()).unSubscribeHiHealthData(this.c, new FunctionSetBeanReader.b("FunctionSetBloodPressureReader", "unSubscribeBloodPressureData, isSuccess :"));
        }
        d dVar = this.f;
        if (dVar == null) {
            LogUtil.h("FunctionSetBloodPressureReader", "onDestroy mHandler is null");
        } else {
            dVar.removeCallbacksAndMessages(null);
        }
        LocalBroadcastManager.getInstance(b()).unregisterReceiver(this.l);
        ObserverManagerUtil.e(this.j, "com.huawei.plugin.account.login");
        ObserverManagerUtil.c(this.i);
    }

    public static class d extends BaseHandler<oir> {
        d(oir oirVar) {
            super(Looper.getMainLooper(), oirVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: daY_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oir oirVar, Message message) {
            Object obj = message.obj;
            int i = message.what;
            if (i == 5) {
                if (obj instanceof FunctionSetBean) {
                    oirVar.notifyItemChanged((FunctionSetBean) obj);
                    return;
                }
                return;
            }
            if (i == 6) {
                if (obj instanceof List) {
                    List<HiHealthData> list = (List) obj;
                    if (koq.c(list)) {
                        oirVar.b.b(list);
                        return;
                    }
                    return;
                }
                return;
            }
            if (i == 7) {
                boolean k = nhj.k();
                LogUtil.a("FunctionSetBloodPressureReader", "handleMessageWhenReferenceNotNull isOpenBloodPressure ", Boolean.valueOf(k));
                if (!k && (obj instanceof Pair)) {
                    Pair pair = (Pair) obj;
                    if (!(pair.first instanceof Integer) || !(pair.second instanceof String)) {
                        LogUtil.h("FunctionSetBloodPressureReader", "handleMessageWhenReferenceNotNull bloodPressureText is error");
                        return;
                    }
                    int intValue = ((Integer) pair.first).intValue();
                    String str = (String) pair.second;
                    LogUtil.a("FunctionSetBloodPressureReader", "cardId is：", Integer.valueOf(intValue), "bloodPressureText is：", str);
                    if (!TextUtils.isEmpty(str)) {
                        oirVar.d = str;
                        FunctionSetBean functionSetBean = oirVar.getFunctionSetBean();
                        if (functionSetBean == null) {
                            ReleaseLogUtil.d("FunctionSetBloodPressureReader", "handleMessageWhenReferenceNotNull functionSetBean is null");
                            return;
                        } else {
                            functionSetBean.a(str);
                            oirVar.notifyItemChanged(functionSetBean);
                            return;
                        }
                    }
                    LogUtil.h("FunctionSetBloodPressureReader", "handleMessageWhenReferenceNotNull bloodPressureText ", str);
                    return;
                }
                return;
            }
            LogUtil.h("FunctionSetBloodPressureReader", "case default");
        }
    }

    static class c extends HandleCacheDataRunnable {
        private List<HiHealthData> c;
        private boolean d;
        private final WeakReference<oir> e;

        c(oir oirVar) {
            super("FunctionSetBloodPressureReader", null);
            this.e = new WeakReference<>(oirVar);
        }

        void b(List<HiHealthData> list) {
            HiHealthData hiHealthData;
            this.c = list;
            if (koq.c(list)) {
                oir.setHasCardData(this.e, true);
                hiHealthData = new HiHealthData();
                HiHealthData hiHealthData2 = list.get(list.size() - 1);
                hiHealthData.putDouble("BLOOD_PRESSURE_SYSTOLIC", hiHealthData2.getDouble("BLOOD_PRESSURE_SYSTOLIC"));
                hiHealthData.putDouble("BLOOD_PRESSURE_DIASTOLIC", hiHealthData2.getDouble("BLOOD_PRESSURE_DIASTOLIC"));
                hiHealthData.setStartTime(hiHealthData2.getStartTime());
                hiHealthData.putInt("_u", hiHealthData2.toString().hashCode());
                hiHealthData.putString("_", marshallListToString(list));
                hiHealthData.putInt("trackdata_deviceType", hiHealthData2.getInt("trackdata_deviceType"));
            } else {
                oir.setHasCardData(this.e, false);
                hiHealthData = null;
            }
            oir.saveDataFromHealthApi("FunctionSetBloodPressureReader", this.e, hiHealthData);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            oir oirVar = this.e.get();
            if (oirVar == null) {
                LogUtil.h("FunctionSetBloodPressureReader", "handleCacheData bloodPressureReader is null");
                return;
            }
            if (hiHealthData == null) {
                oir.setHasCardData(this.e, false);
                if (this.d && z) {
                    oirVar.g = 0L;
                    oirVar.d();
                    return;
                }
                return;
            }
            this.d = true;
            SharedPreferenceManager.c("privacy_center", BleConstants.BLOOD_PRESSURE, String.valueOf(hiHealthData.getStartTime()));
            List list = this.c;
            if (!z || list == null) {
                list = new ArrayList(16);
                unmarshallListFromString(hiHealthData.getString("_"), list);
            } else {
                this.c = null;
            }
            oirVar.h = list;
            if (list.isEmpty()) {
                LogUtil.a("FunctionSetBloodPressureReader", "lineChartDataList is empty");
                oirVar.g = 0L;
                if (z) {
                    return;
                }
                oirVar.refreshCardBySp(oirVar.buildEmptyCardBean());
                return;
            }
            double d = hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC");
            double d2 = hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC");
            long startTime = hiHealthData.getStartTime();
            oirVar.setBiDataSource(hiHealthData.getInt("trackdata_deviceType"));
            oirVar.d(d2, d, startTime, hiHealthData.getInt("_u"), z);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean == null) {
            return;
        }
        if (e()) {
            functionSetBean.a(this.d);
        } else {
            functionSetBean.a("");
        }
        notifyItemChanged(functionSetBean);
        ReleaseLogUtil.e("TimeEat_FunctionSetBloodPressureReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        return (TextUtils.isEmpty(this.d) || nhj.k()) ? false : true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_blood_pressure);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(b()) ? R.color._2131297758_res_0x7f0905de : R.color._2131297757_res_0x7f0905dd;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.b;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void refreshRedDotSyncNotify(boolean z) {
        qgx qgxVar = new qgx();
        if (z) {
            qgxVar.a(true, jdl.b(System.currentTimeMillis(), -60), System.currentTimeMillis(), new IBaseResponseCallback() { // from class: oir.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    List emptyList;
                    qkg qkgVar;
                    List emptyList2 = obj instanceof List ? (List) obj : Collections.emptyList();
                    try {
                        try {
                            emptyList = (List) nrv.c(SharedPreferenceManager.b(BaseApplication.getContext(), "BLOOD_PRESSURE_REFRESH_SP_KEY", "BLOOD_PRESSURE_REFRESH_SP_KEY"), new TypeToken<List<qkg>>() { // from class: oir.3.4
                            }.getType());
                        } catch (JsonSyntaxException unused) {
                            LogUtil.b("FunctionSetBloodPressureReader", "JsonSyntaxException");
                            emptyList = Collections.emptyList();
                        }
                        boolean e2 = oir.this.e((List<qkg>) emptyList, (List<qkg>) emptyList2);
                        LogUtil.a("FunctionSetBloodPressureReader", "sync, is have new bloodpressure data: ", Boolean.valueOf(e2));
                        if (!koq.b(emptyList2) && (qkgVar = (qkg) emptyList2.get(0)) != null && qkgVar.h() > 0) {
                            oir.this.f15705a = qkgVar.h();
                        }
                        oir.this.d(e2);
                    } catch (Exception unused2) {
                        LogUtil.b("FunctionSetBloodPressureReader", "refreshRedDotSyncNotify Exception");
                        oir.this.d(false);
                    }
                    SharedPreferenceManager.e(BaseApplication.getContext(), "BLOOD_PRESSURE_REFRESH_SP_KEY", "BLOOD_PRESSURE_REFRESH_SP_KEY", nrv.a(emptyList2), new StorageParams());
                }
            });
        } else {
            d(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(List<qkg> list, List<qkg> list2) {
        if (koq.b(list) && !koq.b(list2)) {
            return true;
        }
        if (koq.b(list2) || BloodPressureUtil.c(list, list2)) {
            LogUtil.a("FunctionSetBloodPressureReader", "isHaveNewData newData.size() < oldData.size() or is same");
            return false;
        }
        LogUtil.a("FunctionSetBloodPressureReader", "isHaveNewData oldDatas = ", list.toString());
        LogUtil.a("FunctionSetBloodPressureReader", "isHaveNewData newDatas = ", list2.toString());
        long b2 = SharedPreferenceManager.b(Integer.toString(30002), "last_open_data_start_time", 0L);
        LogUtil.a("FunctionSetBloodPressureReader", "isHaveNewData lastDataStartTime = ", Long.valueOf(b2));
        if (list2.get(0) != null && list2.get(0).h() != b2 && !"-1".equals(list2.get(0).j())) {
            return true;
        }
        for (qkg qkgVar : list2) {
            if (qkgVar != null && !"-1".equals(qkgVar.j()) && qkgVar.h() != b2) {
                for (qkg qkgVar2 : list) {
                    if (qkgVar2 != null && qkgVar2.a(qkgVar)) {
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    protected Context b() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        setIsShowRedDot(z);
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean == null) {
            LogUtil.h("FunctionSetBloodPressureReader", "refreshRedDotSyncNotify readData bean is null");
            return;
        }
        functionSetBean.e(z);
        notifyItemChanged(functionSetBean);
        if (z) {
            return;
        }
        long j = this.f15705a;
        if (j > 0) {
            SharedPreferenceManager.e("BloodPressure", "save_time", j);
        }
    }

    static class i extends BroadcastReceiver {
        private WeakReference<oir> e;

        i(oir oirVar) {
            this.e = new WeakReference<>(oirVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("FunctionSetBloodPressureReader", "SyncBroadcastReceiver intent is null");
            } else if (intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6) == 2) {
                qhc.b();
            }
        }
    }

    static class e implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private final List<HiHealthData> f15707a;
        private final WeakReference<oir> c;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private e(oir oirVar, List<HiHealthData> list) {
            this.c = new WeakReference<>(oirVar);
            this.f15707a = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            oir oirVar = this.c.get();
            if (oirVar == null) {
                LogUtil.h("FunctionSetBloodPressureReader", "FunctionSetBloodPressureReader is null in InnerHiAggregateListener");
            } else if (koq.b(list)) {
                LogUtil.h("FunctionSetBloodPressureReader", "queryLastData dataList is empty");
                oirVar.b.b(this.f15707a);
            } else {
                HiHealthData hiHealthData = list.get(0);
                oirVar.c(BloodPressureUtil.b(hiHealthData.getStartTime()), BloodPressureUtil.a(hiHealthData.getStartTime()), this.f15707a);
            }
        }
    }

    static class b implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private final long f15706a;
        private final List<HiHealthData> b;
        private final WeakReference<oir> c;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        private b(oir oirVar, long j, List<HiHealthData> list) {
            this.c = new WeakReference<>(oirVar);
            this.b = list;
            this.f15706a = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            oir oirVar = this.c.get();
            if (oirVar == null) {
                LogUtil.h("FunctionSetBloodPressureReader", "FunctionSetBloodPressureReader is null in InnerHiAggregateListener");
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("FunctionSetBloodPressureReader", "queryTimeRange dataList is empty");
                oirVar.b.b(this.b);
                return;
            }
            LogUtil.a("FunctionSetBloodPressureReader", "queryTimeRange dataList size: ", Integer.valueOf(list.size()));
            this.b.add(0, oirVar.e(list));
            if (this.b.size() < 3) {
                oirVar.a(this.f15706a - 1, this.b);
            } else {
                Message.obtain(oirVar.f, 6, this.b).sendToTarget();
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetBloodPressureReader";
    }
}

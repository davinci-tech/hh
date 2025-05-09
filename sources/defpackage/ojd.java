package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.h5pro.jsmodules.MenstrualModule;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.homehealth.functionsetcard.view.PhysiologicalCycleDotMatrixView;
import defpackage.ojd;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ojd extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private List<HiHealthData> f15719a;
    private PhysiologicalCycleDotMatrixView b;
    private boolean c;
    private int d;
    private List<HiHealthData> e;
    private List<Integer> f;
    private long g;
    private long h;
    private d i;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839617_res_0x7f020841;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131431172_res_0x7f0b0f04 : R.drawable.marketing_default_img_physiological_cycle;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isNeedRefreshOnResume() {
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return false;
    }

    public ojd(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetPhysiologicalCycleCardReader", cardConfig);
        this.d = -1;
        this.f15719a = new ArrayList();
        this.i = new d();
        this.c = true;
        setFunctionSetBean(buildEmptyCardBean());
        sqp.c("900300050", new c(this));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(103);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new b("FunctionSetPhysiologicalCycleCardReader", this));
        ReleaseLogUtil.e("TimeEat_FunctionSetPhysiologicalCycleCardReader", "FunctionSetPhysiologicalCycleCardReader typeList ", arrayList);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "createCardView");
        if (!koq.b(this.f15719a)) {
            PhysiologicalCycleDotMatrixView physiologicalCycleDotMatrixView = new PhysiologicalCycleDotMatrixView(this.mContext, this.f15719a);
            this.b = physiologicalCycleDotMatrixView;
            physiologicalCycleDotMatrixView.setDrawingCacheEnabled(true);
            this.b.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            PhysiologicalCycleDotMatrixView physiologicalCycleDotMatrixView2 = this.b;
            physiologicalCycleDotMatrixView2.layout(0, 0, physiologicalCycleDotMatrixView2.getMeasuredWidth(), this.b.getMeasuredHeight());
            this.b.buildDrawingCache();
            ImageView imageView = new ImageView(this.mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setImageBitmap(this.b.getDrawingCache());
            return imageView;
        }
        return this.b;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "readCardData");
        j();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "updateSuccessList successList ", list);
        if (koq.b(list)) {
            return;
        }
        this.f = list;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public int getCardType() {
        return this.d;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        if (a()) {
            LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "onBindViewHolder is fast click");
        } else {
            LoginInit.getInstance(this.mContext).browsingToLogin(new IBaseResponseCallback() { // from class: ojd.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0) {
                        ojd.this.e();
                    } else {
                        LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "browsingToLogin errorCode is not success", Integer.valueOf(i));
                    }
                }
            }, AnalyticsValue.HEALTH_HOME_PHYSIOLOGICAL_CYCLE_CARD_2010102.value());
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        if (!(viewHolder instanceof FunctionSetBeanReader.MyHolder)) {
            LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "onBindViewHolder viewHolder ", viewHolder);
            return;
        }
        FunctionSetBeanReader.MyHolder myHolder = (FunctionSetBeanReader.MyHolder) viewHolder;
        myHolder.e.setSingleLine(false);
        myHolder.e.setMaxLines(2);
        ReleaseLogUtil.e("TimeEat_FunctionSetPhysiologicalCycleCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j) {
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "requestData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(j);
        hiDataReadOption.setType(new int[]{47401, 47405});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(720);
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "readHiHealthData : ", hiDataReadOption.toString());
        HiHealthNativeApi.a(this.mContext).readHiHealthData(hiDataReadOption, new a(this, 0));
    }

    private void j() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{47401});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        HiDataReadProOption e = HiDataReadProOption.builder().e(hiDataReadOption).d(HiJsonUtil.e(new HiDataFilter(new HiDataFilter.DataFilterExpression(47401, "=", 101.0d)))).e();
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "requestLastData : ", e.toString());
        HiHealthNativeApi.a(this.mContext).readHiHealthDataPro(e, new a(this, 1));
    }

    private long b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.setFirstDayOfWeek(1);
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        c();
    }

    private void c() {
        g();
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HOME_PHYSIOLOGICAL_CYCLE_CARD_2010102.value(), hashMap, 0);
    }

    private void g() {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/?from=0").setAnim("none");
        HandlerExecutor.d(new Runnable() { // from class: ojd.5
            @Override // java.lang.Runnable
            public void run() {
                bzs.e().initH5Pro();
            }
        }, 6000L);
        builder.setImmerse().showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true).enableOnPauseCallback().enableOnResumeCallback().setNeedSoftInputAdapter();
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.addCustomizeJsModule("menstrual", MenstrualModule.class);
        bzs.e().loadH5ProApp(this.mContext, "com.huawei.health.h5.cycle-calendar", builder);
        d();
    }

    private void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ojb
            @Override // java.lang.Runnable
            public final void run() {
                ojd.b();
            }
        });
    }

    static /* synthetic */ void b() {
        try {
            String g = owp.g(BaseApplication.getContext());
            if (TextUtils.isEmpty(g)) {
                LogUtil.b("FunctionSetPhysiologicalCycleCardReader", "getMenstrualSwitch is null.");
                return;
            }
            JSONObject jSONObject = new JSONObject(g);
            int i = jSONObject.getInt("masterSwitch");
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "getMenstrualSwitch is ", Integer.valueOf(i));
            if (i == 1) {
                return;
            }
            jSONObject.put("masterSwitch", 1);
            HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("com.huawei.health.mc");
            if (userPreference == null) {
                ReleaseLogUtil.d("FunctionSetPhysiologicalCycleCardReader", "activateMenstrual userPreference is null");
                return;
            }
            userPreference.setValue(jSONObject.toString());
            userPreference.setSyncStatus(0);
            HiHealthManager.d(BaseApplication.getContext()).setUserPreference(userPreference);
        } catch (JSONException unused) {
            LogUtil.b("FunctionSetPhysiologicalCycleCardReader", "activateMenstrual JSONException.");
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        Context context = BaseApplication.getContext();
        Resources resources = context.getResources();
        return new FunctionSetBean.a(resources.getString(R.string.IDS_physiological_cycle)).e(resources.getString(R.string.IDS_physiological_cycle_description)).a(FunctionSetType.PHYSIOLOGICAL_CYCLE_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(context).c();
    }

    private FunctionSetBean e(List<HiHealthData> list, String str, CharSequence charSequence) {
        setBiHasData(this.g);
        Context context = BaseApplication.getContext();
        Resources resources = context.getResources();
        FunctionSetBean c2 = new FunctionSetBean.a(resources.getString(R.string.IDS_physiological_cycle)).e(resources.getString(R.string.IDS_physiological_cycle_description)).a(charSequence).a(FunctionSetType.PHYSIOLOGICAL_CYCLE_CARD).d(context).b(FunctionSetBean.ViewType.DATA_VIEW).c(str).c(list).c();
        c2.c(String.valueOf(scz.e()).hashCode());
        return c2;
    }

    private boolean a() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.h < 500) {
            return true;
        }
        this.h = elapsedRealtime;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list) {
        String str;
        boolean z;
        FunctionSetBean e;
        int d2;
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "handleData data size ", Integer.valueOf(list.size()));
        a(list);
        long d3 = jec.d(System.currentTimeMillis());
        int i = -1;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            if (i3 >= list.size()) {
                str = "101.0";
                z = true;
                break;
            } else {
                if (d3 == list.get(i3).getStartTime()) {
                    z = false;
                    int i4 = i3;
                    str = String.valueOf(list.get(i3).getDouble("point_value"));
                    i = i4;
                    break;
                }
                if (this.g == list.get(i3).getStartTime()) {
                    i2 = i3;
                }
                i3++;
            }
        }
        if (!z && (d2 = scz.d("FunctionSetPhysiologicalCycleCardReader", list, i, this.g)) >= 0) {
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "handleData lastIndexTemp ", Integer.valueOf(d2), " lastIndex ", Integer.valueOf(i2), " isLong ", Boolean.valueOf(z));
            z = true;
            i2 = d2;
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "M/d"));
        if (z) {
            long j = this.g;
            long d4 = d(list, i2);
            date.setTime(j);
            String format = simpleDateFormat.format(date);
            date.setTime(d4);
            String format2 = simpleDateFormat.format(date);
            this.f15719a.clear();
            this.f15719a.addAll(list.subList((i2 - 21) + e(this.g), i2 + e(this.g)));
            e = e(list, String.format(Locale.ROOT, this.mContext.getString(R.string._2130845539_res_0x7f021f63), format, format2), owm.b(this.mContext.getResources().getString(R.string._2130845538_res_0x7f021f62), ""));
            setFunctionSetBean(e);
            this.d = 6;
        } else {
            date.setTime(System.currentTimeMillis());
            String format3 = simpleDateFormat.format(date);
            int e2 = (i - 21) + e(d3);
            int e3 = e(d3);
            this.f15719a.clear();
            if (e2 < 0) {
                int i5 = 0;
                while (i5 < Math.abs(e2)) {
                    HiHealthData hiHealthData = new HiHealthData();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(list.get(0).getStartTime());
                    i5++;
                    calendar.add(10, i5 * 24);
                    hiHealthData.setStartTime(calendar.getTimeInMillis());
                    list.add(0, hiHealthData);
                }
                this.f15719a.addAll(list.subList(0, 21));
            } else {
                this.f15719a.addAll(list.subList(e2, e3 + i));
            }
            if (e2 < 0) {
                i -= e2;
            }
            int c2 = scz.c("FunctionSetPhysiologicalCycleCardReader", str, list, i);
            Pair<Integer, CharSequence> dWi_ = scz.dWi_("FunctionSetPhysiologicalCycleCardReader", str, list, i);
            this.d = ((Integer) dWi_.first).intValue();
            e = e(list, format3, owm.b(String.valueOf(dWi_.second), UnitUtil.e(c2, 1, 0)));
            setFunctionSetBean(e);
        }
        Object[] objArr = new Object[2];
        objArr[0] = "handleData, bean: ";
        objArr[1] = e != null ? e.toString() : "";
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", objArr);
    }

    private void a(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "data is invalid");
            return;
        }
        while (list.size() > 0 && Math.abs(list.get(0).getDouble("point_value")) < 1.0E-6d) {
            list.remove(0);
        }
    }

    private long d(List<HiHealthData> list, int i) {
        long b2 = b(this.g) + 1728000000;
        while (true) {
            if (i < 0) {
                break;
            }
            if ("102.0".equals(String.valueOf(list.get(i).getDouble("point_value")))) {
                b2 = list.get(i).getStartTime();
                break;
            }
            i--;
        }
        LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "getEndMenstrual : ", Long.valueOf(b2));
        return b2;
    }

    private int e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(7);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (this.c) {
            this.c = false;
            return;
        }
        readCardData();
        ReleaseLogUtil.e("TimeEat_FunctionSetPhysiologicalCycleCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        if (koq.c(this.f)) {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.f, new FunctionSetBeanReader.b("FunctionSetPhysiologicalCycleCardReader", "onDestroy unSubscribeHiHealthData"));
        }
    }

    static class d extends HandleCacheDataRunnable {
        private final WeakReference<ojd> c;
        private boolean e;

        private d(ojd ojdVar) {
            super("FunctionSetPhysiologicalCycleCardReader", null);
            this.c = new WeakReference<>(ojdVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(List<HiHealthData> list) {
            if (koq.b(list)) {
                ojd.saveDataFromHealthApi("FunctionSetPhysiologicalCycleCardReader", this.c, (HiHealthData) null);
                return;
            }
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "dataList size: ", Integer.valueOf(list.size()));
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.putString("_", marshallListToString(list));
            ojd.saveDataFromHealthApi("FunctionSetPhysiologicalCycleCardReader", this.c, hiHealthData);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            ojd ojdVar = this.c.get();
            if (ojdVar == null) {
                LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "handleCacheData bloodPressureReader is null");
                return;
            }
            if (hiHealthData == null) {
                ojd.setHasCardData(this.c, false);
                if (!z) {
                    ojdVar.refreshCardBySp(ojdVar.buildEmptyCardBean());
                }
                if (this.e && z) {
                    ojdVar.notifyItemChanged(ojdVar.buildEmptyCardBean());
                    return;
                }
                return;
            }
            this.e = true;
            ArrayList arrayList = new ArrayList(16);
            unmarshallListFromString(hiHealthData.getString("_"), arrayList);
            if (arrayList.isEmpty()) {
                LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "lineChartDataList is empty");
                ojd.setHasCardData(this.c, false);
                if (!z) {
                    ojdVar.refreshCardBySp(ojdVar.buildEmptyCardBean());
                    return;
                } else {
                    ojdVar.notifyItemChanged(ojdVar.buildEmptyCardBean());
                    return;
                }
            }
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "refresh data card");
            ojdVar.e = arrayList;
            ojdVar.b(arrayList);
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "handleData end");
            ojd.setHasCardData(this.c, true);
            if (!z) {
                ojdVar.refreshCardBySp(ojdVar.getFunctionSetBean());
            } else {
                ojdVar.notifyItemChanged(ojdVar.getFunctionSetBean());
            }
        }
    }

    /* loaded from: classes9.dex */
    static class b extends FunctionSetBeanReader.c {
        private final WeakReference<FunctionSetBeanReader> d;

        public b(String str, FunctionSetBeanReader functionSetBeanReader) {
            super(str, functionSetBeanReader);
            this.d = new WeakReference<>(functionSetBeanReader);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.c, com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, final String str, HiHealthData hiHealthData, long j) {
            if (i == 103 && "900300050".equals(str)) {
                LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "InnerSubscribeListener onChange changeKey ", str);
                ThreadPoolManager.d().execute(new Runnable() { // from class: ojf
                    @Override // java.lang.Runnable
                    public final void run() {
                        ojd.b.this.d(str);
                    }
                });
            }
            super.onChange(i, hiHealthClient, str, hiHealthData, j);
        }

        /* synthetic */ void d(String str) {
            sqp.c(str, new c(this.d.get()));
        }
    }

    /* loaded from: classes9.dex */
    static class c implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<FunctionSetBeanReader> f15721a;

        c(FunctionSetBeanReader functionSetBeanReader) {
            this.f15721a = new WeakReference<>(functionSetBeanReader);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "InnerSampleConfigListener onResult errorCode ", Integer.valueOf(i), " object ", obj);
            if (i == 0 && scz.d("FunctionSetPhysiologicalCycleCardReader", obj) != null) {
                FunctionSetBeanReader functionSetBeanReader = this.f15721a.get();
                if (!(functionSetBeanReader instanceof ojd)) {
                    ReleaseLogUtil.d("TimeEat_FunctionSetPhysiologicalCycleCardReader", "InnerSampleConfigListener onResult reader ", functionSetBeanReader);
                    return;
                }
                ojd ojdVar = (ojd) functionSetBeanReader;
                if (ojdVar.e != null) {
                    ojdVar.b((List<HiHealthData>) ojdVar.e);
                    ojdVar.notifyItemChanged(functionSetBeanReader.getFunctionSetBean());
                } else {
                    ReleaseLogUtil.d("TimeEat_FunctionSetPhysiologicalCycleCardReader", "InnerSampleConfigListener onResult cardReader.mDataList is null");
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            ReleaseLogUtil.d("TimeEat_FunctionSetPhysiologicalCycleCardReader", "InnerSampleConfigListener onResultIntent errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3), " intentType ", Integer.valueOf(i), " object ", obj);
        }
    }

    static class a implements HiDataReadResultListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<ojd> f15720a;
        private int e;

        a(ojd ojdVar, int i) {
            this.f15720a = new WeakReference<>(ojdVar);
            this.e = i;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "onResult, errorCode: ", Integer.valueOf(i), ", mType: ", Integer.valueOf(this.e));
            ojd ojdVar = this.f15720a.get();
            if (ojdVar == null) {
                LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "reader is null!");
                return;
            }
            if (obj == null) {
                LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "data is null!");
                ojdVar.i.b(null);
                return;
            }
            if (!(obj instanceof SparseArray)) {
                ojdVar.i.b(null);
                return;
            }
            SparseArray<List<HiHealthData>> sparseArray = (SparseArray) obj;
            if (sparseArray.size() == 0) {
                LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "map.size() == 0");
                ojdVar.i.b(null);
                return;
            }
            int i3 = this.e;
            if (i3 == 1) {
                e(sparseArray.get(47401).get(0), ojdVar);
            } else {
                if (i3 == 0) {
                    List<HiHealthData> list = sparseArray.get(47401);
                    dbh_(sparseArray, list);
                    ojdVar.i.b(list);
                    return;
                }
                LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "mType is error");
            }
        }

        private void dbh_(SparseArray<List<HiHealthData>> sparseArray, List<HiHealthData> list) {
            if (sparseArray == null) {
                LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "insertOvulation: map is null");
                return;
            }
            List<HiHealthData> list2 = sparseArray.get(47405);
            Object[] objArr = new Object[5];
            objArr[0] = "insertOvulation: ";
            objArr[1] = "list size: ";
            Object obj = Constants.NULL;
            objArr[2] = list != null ? Integer.valueOf(list.size()) : Constants.NULL;
            objArr[3] = ", ovulationList size: ";
            if (list2 != null) {
                obj = Integer.valueOf(list2.size());
            }
            objArr[4] = obj;
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", objArr);
            if (list == null || list2 == null) {
                return;
            }
            int min = Math.min(list.size(), list2.size());
            for (int i = 0; i < min; i++) {
                String valueOf = String.valueOf(list.get(i).getDouble("point_value"));
                if (("400.0".equals(valueOf) || "401.0".equals(valueOf) || "402.0".equals(valueOf) || "403.0".equals(valueOf)) && "2.0".equals(String.valueOf(list2.get(i).getDouble("point_value")))) {
                    LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "ovulation day: ", new Date(list2.get(i).getStartTime()));
                    list.get(i).putDouble("point_value", list2.get(i).getDouble("point_value"));
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("FunctionSetPhysiologicalCycleCardReader", "onResultIntent : read failed errorCode is", Integer.valueOf(i2));
        }

        private void e(HiHealthData hiHealthData, ojd ojdVar) {
            if (hiHealthData == null || !String.valueOf(hiHealthData.getDouble("point_value")).equals("101.0")) {
                LogUtil.h("FunctionSetPhysiologicalCycleCardReader", "handleLastData : list is error");
                return;
            }
            long j = hiHealthData.getLong("start_time");
            ojdVar.g = j;
            SharedPreferenceManager.c("privacy_center", "physiological_cycle", String.valueOf(j));
            ojdVar.c(j + 31104000000L);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable._2131430831_res_0x7f0b0daf);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(this.mContext) ? R.color._2131297758_res_0x7f0905de : R.color._2131297757_res_0x7f0905dd;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetPhysiologicalCycleCardReader";
    }
}

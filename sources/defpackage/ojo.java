package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.components.XAxis;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.device.wifi.interfaces.CommBaseCallback;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthYAxis;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.ruler.circlescaleruler.CircleScaleRuler;
import health.compact.a.CommonUtils;
import health.compact.a.CompileParameterUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class ojo extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f15737a = c();
    private final Context b;
    private final nqg c;
    private String d;
    private CircleScaleRuler e;
    private boolean f;
    private final e g;
    private int h;
    private final a i;
    private View j;
    private double k;
    private long l;
    private boolean m;
    private HwHealthLineChart n;
    private long o;
    private List<Integer> p;
    private kwc q;
    private int r;
    private UserLabelServiceApi s;
    private ArrayList<Float> t;

    private float c(float f, float f2) {
        return f > f2 ? f + 5.0f : f < f2 ? f - 5.0f : f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839603_res_0x7f020833;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131432066_res_0x7f0b1282 : R.drawable.marketing_default_img_weight;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    static class a extends HandleCacheDataRunnable {
        private boolean c;
        private ArrayList<Float> d;
        private final WeakReference<ojo> e;

        a(ojo ojoVar) {
            super("FunctionSetWeightCardReader", null);
            this.e = new WeakReference<>(ojoVar);
        }

        void d(HiHealthData hiHealthData, ArrayList<Float> arrayList) {
            if (arrayList == null || hiHealthData == null) {
                ojo.setHasCardData(this.e, false);
                this.d = null;
            } else {
                ojo.setHasCardData(this.e, true);
                this.d = arrayList;
                if (koq.c(arrayList)) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        hiHealthData.putFloat("w" + i, arrayList.get(i).floatValue());
                    }
                }
            }
            ojo.saveDataFromHealthApi("FunctionSetWeightCardReader", this.e, hiHealthData);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            ojo ojoVar = this.e.get();
            if (ojoVar == null) {
                LogUtil.h("FunctionSetWeightCardReader", "handleCacheData weightCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                if (this.c && z) {
                    ojoVar.g();
                    return;
                }
                return;
            }
            this.c = true;
            SharedPreferenceManager.c("privacy_center", "health_weight", String.valueOf(hiHealthData.getStartTime()));
            ArrayList<Float> arrayList = this.d;
            if (!z || arrayList == null) {
                arrayList = new ArrayList<>(16);
                int i = 0;
                while (true) {
                    String str = "w" + i;
                    if (hiHealthData.getFloat(str) == 0.0f) {
                        break;
                    }
                    arrayList.add(Float.valueOf(hiHealthData.getFloat(str)));
                    i++;
                }
            } else {
                this.d = null;
            }
            ojoVar.t = arrayList;
            if (arrayList.isEmpty()) {
                boolean a2 = gsd.a();
                ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "handleCacheData fatReductionShapingState ", Boolean.valueOf(a2), " isNewData ", Boolean.valueOf(z));
                if (a2) {
                    ojoVar.e(gsd.e());
                    return;
                } else {
                    if (z) {
                        return;
                    }
                    ojoVar.refreshCardBySp(ojoVar.buildEmptyCardBean());
                    return;
                }
            }
            double d = hiHealthData.getDouble("bodyWeight");
            int i2 = hiHealthData.getInt("trackdata_deviceType");
            long startTime = hiHealthData.getStartTime();
            ojoVar.a(d, startTime, String.valueOf(startTime) + d, i2, z);
        }
    }

    public static class e extends BaseHandler<ojo> {
        private e(ojo ojoVar) {
            super(Looper.getMainLooper(), ojoVar);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbw_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ojo ojoVar, Message message) {
            LogUtil.a("FunctionSetWeightCardReader", "handleMessageWhenReferenceNotNull()");
            if (ojoVar == null || message == null) {
                LogUtil.h("FunctionSetWeightCardReader", "handleMessageWhenReferenceNotNull WeightCardReader or message is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                LogUtil.a("FunctionSetWeightCardReader", "handleMessageWhenReferenceNotNull IS_HAVE_NEW_DATA");
                FunctionSetBean functionSetBean = ojoVar.getFunctionSetBean();
                if (functionSetBean != null) {
                    boolean d = ojo.d();
                    functionSetBean.e(d);
                    ojoVar.notifyItemChanged(functionSetBean);
                    ojoVar.m = d;
                    return;
                }
                LogUtil.h("FunctionSetWeightCardReader", "handleMessageWhenReferenceNotNull functionSetBean is null");
                return;
            }
            if (i == 2) {
                LogUtil.a("FunctionSetWeightCardReader", "handleMessageWhenReferenceNotNull MSG_UPDATE_WEIGHT");
                FunctionSetBean functionSetBean2 = (FunctionSetBean) message.obj;
                ojoVar.notifyItemChanged(functionSetBean2);
                ojoVar.setFunctionSetBean(functionSetBean2);
                return;
            }
            if (i == 3) {
                ojoVar.e((List<HiHealthData>) message.obj);
            } else if (i == 4) {
                ojoVar.e((quh) message.obj);
            } else {
                LogUtil.h("FunctionSetWeightCardReader", "handleMessageWhenReferenceNotNull default");
            }
        }
    }

    public static class c extends CommBaseCallback<ojo> {
        private c(ojo ojoVar) {
            super(ojoVar);
        }

        @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResult(ojo ojoVar, int i, String str, Object obj) {
            if (ojoVar == null || ojoVar.g == null) {
                return;
            }
            ojoVar.g.sendEmptyMessage(1);
        }
    }

    public ojo(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetWeightCardReader", cardConfig);
        Context e2 = BaseApplication.e();
        this.b = e2;
        this.c = new nqg(false);
        this.t = new ArrayList<>(10);
        this.p = null;
        this.f = true;
        this.i = new a(this);
        this.g = new e();
        this.s = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        if (Utils.o()) {
            this.d = nsf.h(R.string.IDS_hw_show_main_home_page_weight);
        } else {
            this.d = nsf.h(R.string.IDS_health_weight_manager);
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            ClaimWeightDataManager.INSTANCE.registerCallBack(getClass().getSimpleName(), new c());
        }
        i();
        this.q = kwc.e(e2);
        qvz.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d2, long j, String str, int i, boolean z) {
        LogUtil.a("FunctionSetWeightCardReader", "refreshWeightDataAndTime");
        setHasCardData(true);
        setBiHasData(j);
        setBiHasDataType("weight");
        setBiDataSource(i);
        this.o = j;
        this.k = d2;
        this.h = i;
        this.q.d(d2, j, true);
        String b2 = owm.b(this.o);
        int c2 = qsj.c(this.k, this.h);
        double b3 = UnitUtil.b(this.k, c2);
        FunctionSetBean c3 = new FunctionSetBean.a(this.d).d(this.b).a(FunctionSetType.WEIGHT_CARD).c(b2).b(FunctionSetBean.ViewType.DATA_VIEW).a(UnitUtil.e(b3, 1, c2)).b(a(b3)).c();
        boolean d3 = d();
        this.m = d3;
        c3.e(d3);
        c3.c((str + b3 + c3.h()).hashCode());
        setFunctionSetBean(c3);
        if (z) {
            Message obtainMessage = this.g.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = c3;
            this.g.sendMessage(obtainMessage);
        } else {
            refreshCardBySp(c3);
        }
        if (gsd.a()) {
            e(gsd.e());
            return;
        }
        if (this.e == null) {
            ReleaseLogUtil.d("FunctionSetWeightCardReader", "mCircleScaleRuler is null");
            return;
        }
        HwHealthLineChart hwHealthLineChart = this.n;
        if (hwHealthLineChart != null && this.k > 0.0d) {
            hwHealthLineChart.setVisibility(0);
        }
        this.e.setVisibility(8);
        c3.a(null);
        notifyItemChanged(c3);
    }

    private String a(double d2) {
        this.r = UnitUtil.e();
        return qsj.d(d2);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        FunctionSetBean functionSetBean = functionSetSubCardData.getFunctionSetBean();
        if (!(viewHolder instanceof FunctionSetBeanReader.MyHolder) || functionSetBean == null) {
            LogUtil.b("FunctionSetWeightCardReader", "MyHolder is invalid");
            return;
        }
        View view = ((FunctionSetBeanReader.MyHolder) viewHolder).itemView;
        LogUtil.a("FunctionSetWeightCardReader", "weightView: " + view);
        if (functionSetBean.q() == FunctionSetBean.ViewType.EMPTY_VIEW) {
            ImageView imageView = (ImageView) view.findViewById(R.id.function_set_empty_red_dot);
            if (imageView == null) {
                return;
            }
            boolean d2 = d();
            this.m = d2;
            if (d2) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(8);
            }
        }
        ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void e() {
        if (!(this.mCardViewHolder instanceof FunctionSetBeanReader.MyHolder)) {
            LogUtil.a("FunctionSetWeightCardReader", "mCardViewHolder is not MyHolder instance");
            return;
        }
        View view = ((FunctionSetBeanReader.MyHolder) this.mCardViewHolder).itemView;
        if (view == null) {
            LogUtil.a("FunctionSetWeightCardReader", "view is null");
            return;
        }
        if (f15737a) {
            if (SharedPreferenceManager.e(this.b, Integer.toString(10000), "IS_CARD_USED_KEY", "card_is_used", new StorageParams()) == 0) {
                f15737a = false;
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.function_set_empty_red_dot);
            if (imageView != null) {
                imageView.setVisibility(8);
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == HiSubscribeType.c || i == DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value() || i == DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM_SET.value() || i == DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE_SET.value() || i == 40003;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.c("FunctionSetWeightCardReader", "readCardData start ", Long.valueOf(currentTimeMillis));
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(gsd.h(), currentTimeMillis);
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setFilter("NULL");
        if (gsd.a()) {
            int b2 = DateFormatUtil.b(currentTimeMillis);
            qvz.d(b2, b2, new d(this));
            hiAggregateOption.setCount(1);
        }
        hiAggregateOption.setConstantsKey(new String[]{BleConstants.WEIGHT_KEY});
        HiHealthManager.d(this.b).aggregateHiHealthData(hiAggregateOption, new b(this));
    }

    static class b implements HiAggregateListener {
        private WeakReference<ojo> c;

        b(ojo ojoVar) {
            this.c = new WeakReference<>(ojoVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("FunctionSetWeightCardReader", "readCardData onResult ", list);
            ojo ojoVar = this.c.get();
            if (ojoVar == null) {
                LogUtil.h("FunctionSetWeightCardReader", "read is null, not refresh");
                return;
            }
            if (list != null && list.size() > 0) {
                ArrayList c = ojoVar.c(list);
                WeightDataManager.INSTANCE.setDataList(true, c);
                LogUtil.a("FunctionSetWeightCardReader", "readCardData onResult dataList size is ", Integer.valueOf(list.size()), ",filteredHealthDataList size is ", Integer.valueOf(c.size()));
                Message obtainMessage = ojoVar.g.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.obj = c;
                ojoVar.g.sendMessage(obtainMessage);
                return;
            }
            LogUtil.h("FunctionSetWeightCardReader", "readCardData onResult no data");
            ojoVar.i.d(null, null);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("FunctionSetWeightCardReader", "readCardData onResultIntent");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<HiHealthData> c(List<HiHealthData> list) {
        ArrayList<HiHealthData> arrayList = new ArrayList<>(10);
        for (HiHealthData hiHealthData : list) {
            if (e(hiHealthData.getDouble("bodyWeight")) && a(hiHealthData.getStartTime()) && a(hiHealthData.getEndTime())) {
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private boolean e(double d2) {
        return Double.compare(d2, 0.0d) > 0 && Double.compare(d2, 250.0d) <= 0;
    }

    private boolean a(long j) {
        return j > 0 && j <= System.currentTimeMillis();
    }

    static class d implements ResponseCallback<List<quh>> {
        private final WeakReference<ojo> d;

        d(ojo ojoVar) {
            this.d = new WeakReference<>(ojoVar);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            LogUtil.a("FunctionSetWeightCardReader", "InnerResponseCallback errorCode ", Integer.valueOf(i), " list ", list);
            ojo ojoVar = this.d.get();
            if (ojoVar != null) {
                e eVar = ojoVar.g;
                if (eVar == null) {
                    ReleaseLogUtil.d("TimeEat_FunctionSetWeightCardReader", "InnerResponseCallback handler is null");
                    return;
                }
                quh quhVar = koq.c(list) ? list.get(0) : null;
                Message obtainMessage = eVar.obtainMessage();
                obtainMessage.what = 4;
                obtainMessage.obj = quhVar;
                eVar.sendMessage(obtainMessage);
                return;
            }
            ReleaseLogUtil.d("TimeEat_FunctionSetWeightCardReader", "InnerResponseCallback reader is null");
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetWeightCardReader", "updateSuccessList");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetWeightCardReader", "updateSuccessList successList is not null");
        this.p = list;
    }

    private void i() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(HiSubscribeType.c));
        arrayList.add(103);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM_SET.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE_SET.value()));
        arrayList.add(40003);
        HiHealthNativeApi.a(this.b).subscribeHiHealthData(arrayList, new h("FunctionSetWeightCardReader", this));
        ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "subscribeWeightData typeList ", arrayList);
    }

    private void h() {
        LogUtil.a("FunctionSetWeightCardReader", "unSubscribeWeightData");
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            ClaimWeightDataManager.INSTANCE.unRegisterCallBack(getClass().getSimpleName());
        }
        List<Integer> list = this.p;
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthNativeApi.a(this.b).unSubscribeHiHealthData(this.p, new FunctionSetBeanReader.b("FunctionSetWeightCardReader", "unSubscribeWeightData, isSuccess:"));
    }

    private boolean c(String str) {
        return (str == null || str.length() == 0) || (Constants.NULL.equalsIgnoreCase(str) || "0".equals(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        boolean a2 = gsd.a();
        ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "showEmptyView fatReductionShapingState ", Boolean.valueOf(a2));
        if (a2) {
            this.k = 0.0d;
            e(gsd.e());
            return;
        }
        setHasCardData(false);
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.g.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = buildEmptyCardBean;
        this.g.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.d).e(nsf.h(R.string.IDS_hw_show_main_home_page_weight_description)).a(FunctionSetType.WEIGHT_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(this.b).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list) {
        ArrayList<Float> arrayList = new ArrayList<>(3);
        ArrayList arrayList2 = new ArrayList(3);
        HiHealthData hiHealthData = new HiHealthData();
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        long j = 0;
        double d2 = 0.0d;
        for (HiHealthData hiHealthData2 : list) {
            String metaData = hiHealthData2.getMetaData();
            if (c(metaData) || metaData.equals(i)) {
                if (arrayList.size() < 3) {
                    arrayList.add(Float.valueOf((float) hiHealthData2.getDouble("bodyWeight")));
                    arrayList2.add(Long.valueOf(hiHealthData2.getStartTime()));
                }
                if (d2 == 0.0d || j == 0) {
                    d2 = hiHealthData2.getDouble("bodyWeight");
                    j = hiHealthData2.getStartTime();
                    if (e(d2) && a(j)) {
                        hiHealthData.putDouble("bodyWeight", d2);
                        hiHealthData.putInt("trackdata_deviceType", hiHealthData2.getInt("trackdata_deviceType"));
                        hiHealthData.setStartTime(j);
                    }
                }
            }
        }
        long j2 = 0;
        if (j == 0 || d2 == 0.0d) {
            arrayList.clear();
            hiHealthData = null;
        }
        this.i.d(hiHealthData, arrayList);
        this.t = arrayList;
        int size = arrayList2.size();
        if (size >= 3 && arrayList.size() >= 3) {
            j2 = ((Long) arrayList2.get(size - 1)).longValue();
        }
        gsd.a(j2);
        LogUtil.a("FunctionSetWeightCardReader", "showDataView startTime ", Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(final quh quhVar) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: ojr
                @Override // java.lang.Runnable
                public final void run() {
                    ojo.this.e(quhVar);
                }
            });
            return;
        }
        LogUtil.a("FunctionSetWeightCardReader", "showDietRecord dietRecord ", quhVar);
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean == null) {
            ReleaseLogUtil.d("FunctionSetWeightCardReader", "showDietRecord functionSetBean is null");
            return;
        }
        if (this.e == null) {
            ReleaseLogUtil.d("FunctionSetWeightCardReader", "showDietRecord mCircleScaleRuler is null initCardView");
            b();
        }
        if (this.e == null) {
            ReleaseLogUtil.d("FunctionSetWeightCardReader", "showDietRecord mCircleScaleRuler is null");
            return;
        }
        qts c2 = c(quhVar);
        if (c2 == null) {
            boolean a2 = gsd.a();
            ReleaseLogUtil.d("TimeEat_FunctionSetWeightCardReader", "showDietRecord dietCalorieOverview is null isOpenFatReductionShaping ", Boolean.valueOf(a2));
            if (!a2) {
                HwHealthLineChart hwHealthLineChart = this.n;
                if (hwHealthLineChart != null && this.k > 0.0d) {
                    hwHealthLineChart.setVisibility(0);
                }
                this.e.setVisibility(8);
                functionSetBean.a(null);
            }
            notifyItemChanged(functionSetBean);
            return;
        }
        setHasCardData(true);
        HwHealthLineChart hwHealthLineChart2 = this.n;
        if (hwHealthLineChart2 != null) {
            hwHealthLineChart2.setVisibility(8);
        }
        this.e.setVisibility(0);
        this.c.c(dpe.a(c2));
        Pair<Float, Float> Yx_ = dpe.Yx_(c2);
        if (Yx_ == null) {
            this.c.b(0.0f, 0.0f);
        } else {
            this.c.b(((Float) Yx_.first).floatValue(), ((Float) Yx_.second).floatValue());
        }
        this.e.setCircleScaleRulerData(this.c);
        c(functionSetBean, c2);
    }

    private qts c(quh quhVar) {
        if (quhVar == null) {
            ReleaseLogUtil.d("FunctionSetWeightCardReader", "getDietCalorieOverview dietRecord is null");
            return null;
        }
        return quhVar.d();
    }

    private void c(FunctionSetBean functionSetBean, qts qtsVar) {
        int b2 = dpe.b(qtsVar);
        String b3 = nsf.b(qtsVar.j() < 0.0d ? R.string._2130846769_res_0x7f022431 : R.string._2130846767_res_0x7f02242f, nsf.a(R.plurals._2130903380_res_0x7f030154, b2, UnitUtil.e(b2, 1, 0)));
        setBiHasDataType(qtsVar.j() < 0.0d ? "caloric_surplus" : "calorie_deficit");
        functionSetBean.a(b3);
        functionSetBean.c(BaseApplication.e().getResources().getString(R.string._2130847193_res_0x7f0225d9));
        functionSetBean.b(FunctionSetBean.ViewType.DATA_VIEW);
        int c2 = qsj.c(this.k, this.h);
        double b4 = UnitUtil.b(this.k, c2);
        functionSetBean.e(b4 <= 0.0d ? "--" : UnitUtil.e(b4, 1, c2));
        functionSetBean.b((CharSequence) a(b4));
        notifyItemChanged(functionSetBean);
        this.l = qtsVar.g();
    }

    private void b() {
        if (this.j == null) {
            LogUtil.h("FunctionSetWeightCardReader", "initCardView mHealthViewLineChart is null");
            this.j = LayoutInflater.from(this.b).inflate(R.layout.health_view_line_chart, (ViewGroup) null);
        }
        if (this.e == null) {
            ReleaseLogUtil.d("TimeEat_FunctionSetWeightCardReader", "initCardView mCircleScaleRuler is null");
            this.e = (CircleScaleRuler) this.j.findViewById(R.id.circle_scale_ruler);
        }
        dpe.c(this.c, true);
        this.j.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: ojo.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ojo.this.j.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (!gsd.a()) {
                    ReleaseLogUtil.d("TimeEat_FunctionSetWeightCardReader", "initCardView onGlobalLayout getFatReductionShapingState false");
                    return;
                }
                int max = Math.max(ojo.this.j.getHeight(), nsf.b(R.dimen._2131362996_res_0x7f0a04b4));
                int b2 = nsf.b(R.dimen._2131363078_res_0x7f0a0506);
                if (max <= 0 || max >= b2) {
                    return;
                }
                ViewGroup.LayoutParams layoutParams = ojo.this.e.getLayoutParams();
                layoutParams.width = ojo.this.e.getWidth();
                layoutParams.height = max;
                ojo.this.e.setLayoutParams(layoutParams);
                float f = max / b2;
                ojo.this.c.a(f);
                ojo.this.e.setCircleScaleRulerData(ojo.this.c);
                ReleaseLogUtil.e("FunctionSetWeightCardReader", "initCardView onGlobalLayout viewHeight ", Integer.valueOf(max), " circleScaleRulerHeight ", Integer.valueOf(b2), " zoomScale ", Float.valueOf(f));
            }
        });
        if (this.n == null) {
            LogUtil.h("FunctionSetWeightCardReader", "initCardView mLineChart is null");
            this.n = (HwHealthLineChart) this.j.findViewById(R.id.line_chart);
        }
        if (this.n.getRenderer() instanceof nov) {
            ((nov) this.n.getRenderer()).a(1);
        }
        this.n.disableLabelsForce();
        this.n.setTouchEnabled(false);
        this.n.getDescription().setEnabled(false);
    }

    private void d(float f, float f2) {
        HwHealthLineChart hwHealthLineChart = this.n;
        if (hwHealthLineChart == null) {
            LogUtil.h("FunctionSetWeightCardReader", "initAxis mLineChart is null");
            return;
        }
        XAxis xAxis = hwHealthLineChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setAxisMinimum(-4.0f);
        xAxis.setAxisMaximum(104.0f);
        HwHealthYAxis axisFirstParty = this.n.getAxisFirstParty();
        axisFirstParty.setDrawLabels(false);
        axisFirstParty.setDrawAxisLine(false);
        axisFirstParty.setDrawGridLines(false);
        axisFirstParty.setAxisMinimum(f - 100.0f);
        axisFirstParty.setAxisMaximum(f2 + 100.0f);
    }

    private void b(List<HwHealthBaseEntry> list) {
        if (this.n == null) {
            LogUtil.h("FunctionSetWeightCardReader", "initLineDataSet mLineChart is null");
            return;
        }
        LogUtil.a("FunctionSetWeightCardReader", "initLineDataSet valueList size is ", Integer.valueOf(list.size()));
        HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.b, list, "line brief", "line label", "line unit");
        hwHealthLineDataSet.setColor(Color.parseColor("#3EB09E"));
        hwHealthLineDataSet.b(Color.parseColor("#333EB09E"), Color.parseColor("#003EB09E"), true);
        hwHealthLineDataSet.setAxisDependency(HwHealthYAxis.HwHealthAxisDependency.FIRST_PARTY);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(hwHealthLineDataSet);
        this.n.setData(new now(arrayList));
        this.n.refresh();
    }

    private void a(ArrayList<Float> arrayList) {
        boolean a2 = gsd.a();
        ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "refreshCardView isOpenFatReductionShaping ", Boolean.valueOf(a2));
        if (a2) {
            HwHealthLineChart hwHealthLineChart = this.n;
            if (hwHealthLineChart != null) {
                hwHealthLineChart.setVisibility(8);
            }
            CircleScaleRuler circleScaleRuler = this.e;
            if (circleScaleRuler != null) {
                circleScaleRuler.setVisibility(0);
                this.e.setCircleScaleRulerData(this.c);
                return;
            }
            return;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            LogUtil.h("FunctionSetWeightCardReader", "refreshCardView list is empty");
            return;
        }
        List<HwHealthBaseEntry> arrayList2 = new ArrayList<>(3);
        int size = arrayList.size();
        LogUtil.a("FunctionSetWeightCardReader", "refreshCardView lineChartDataListSize is ", Integer.valueOf(size));
        if (size <= 0) {
            LogUtil.h("FunctionSetWeightCardReader", "refreshCardView lineChartDataListSize is zero");
            return;
        }
        if (size == 1) {
            float floatValue = arrayList.get(0).floatValue();
            d(floatValue, floatValue);
            arrayList2.add(new HwHealthBaseEntry(-100.0f, floatValue));
            arrayList2.add(new HwHealthBaseEntry(100.0f, floatValue));
        } else if (size == 2) {
            float floatValue2 = arrayList.get(0).floatValue();
            float floatValue3 = arrayList.get(1).floatValue();
            if (floatValue2 > floatValue3) {
                floatValue2 += 5.0f;
                floatValue3 -= 5.0f;
                d(floatValue3, floatValue2);
            } else if (floatValue2 < floatValue3) {
                floatValue2 -= 5.0f;
                floatValue3 += 5.0f;
                d(floatValue2, floatValue3);
            } else {
                d(floatValue2, floatValue3);
            }
            arrayList2.add(new HwHealthBaseEntry(0.0f, floatValue3));
            arrayList2.add(new HwHealthBaseEntry(100.0f, floatValue2));
        } else {
            ArrayList arrayList3 = new ArrayList(3);
            arrayList3.add(arrayList.get(0));
            arrayList3.add(arrayList.get(1));
            arrayList3.add(arrayList.get(2));
            d(((Float) Collections.min(arrayList3)).floatValue() - 5.0f, ((Float) Collections.max(arrayList3)).floatValue() + 5.0f);
            arrayList2 = d(arrayList3);
        }
        b(arrayList2);
    }

    private List<HwHealthBaseEntry> d(List<Float> list) {
        if (list == null || list.size() < 3) {
            LogUtil.h("FunctionSetWeightCardReader", "initDataOffset list is null or size less than three");
            return null;
        }
        float floatValue = list.get(0).floatValue();
        float floatValue2 = list.get(1).floatValue();
        float floatValue3 = list.get(2).floatValue();
        float f = ((floatValue + floatValue2) + floatValue3) / 3.0f;
        float c2 = c(floatValue, f);
        float c3 = c(floatValue2, f);
        float c4 = c(floatValue3, f);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new HwHealthBaseEntry(0.0f, c4));
        arrayList.add(new HwHealthBaseEntry(50.0f, c3));
        arrayList.add(new HwHealthBaseEntry(100.0f, c2));
        return arrayList;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        boolean a2 = gsd.a();
        ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "createCardView fatReductionShapingState ", Boolean.valueOf(a2));
        ArrayList<Float> arrayList = this.t;
        if ((arrayList == null || arrayList.isEmpty()) && !a2) {
            LogUtil.h("FunctionSetWeightCardReader", "createCardView mLineChartDataList is empty");
            return null;
        }
        b();
        a(this.t);
        LogUtil.a("FunctionSetWeightCardReader", "createCardView mHealthViewLineChart is ", this.j);
        return this.j;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.f = false;
        ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime), " mLastUpdateDietRecordTime ", Long.valueOf(this.l));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isNeedRefreshOnResume() {
        if (super.isNeedRefreshOnResume()) {
            return true;
        }
        if (this.f) {
            return false;
        }
        FunctionSetBean functionSetBean = getFunctionSetBean();
        if (functionSetBean != null) {
            boolean d2 = d();
            LogUtil.a("FunctionSetWeightCardReader", "onResume mIsShowCardRedDot ", Boolean.valueOf(this.m), " isShowCardRedDot ", Boolean.valueOf(d2));
            boolean z = this.m;
            if ((z && !d2) || (!z && d2)) {
                functionSetBean.e(d2);
                this.m = d2;
            }
            if (this.r != UnitUtil.e() && e(this.k)) {
                int c2 = qsj.c(this.k, this.h);
                double b2 = UnitUtil.b(this.k, c2);
                functionSetBean.e(UnitUtil.e(b2, 1, c2));
                functionSetBean.b((CharSequence) a(b2));
            }
            notifyItemChanged(functionSetBean);
        }
        if (gsd.a()) {
            quh e2 = gsd.e();
            long currentTimeMillis = System.currentTimeMillis();
            if (e2 == null || !jdl.ac(this.l) || Math.abs(currentTimeMillis - this.l) > 60000) {
                int b3 = DateFormatUtil.b(currentTimeMillis);
                qvz.d(b3, b3, new d(this));
            } else {
                e(e2);
            }
        }
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        h();
        e eVar = this.g;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        e();
        this.s.clickRecord(CommonUtils.h(AnalyticsValue.HEALTH_HOME_WIGHT_DETAIL_2010023.value()), getHuid());
        Bundle bundle = new Bundle();
        bundle.putString("entranceType", gsd.a() ? "heat_gap" : this.k > 0.0d ? "trend_chart" : "empty_state");
        bundle.putLong("lastDataTime", this.o);
        bundle.putString("from", "1");
        rag.dJz_(bundle);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onBottomLayoutClickListener(RelativeLayout relativeLayout) {
        if (relativeLayout == null) {
            LogUtil.h("FunctionSetWeightCardReader", "relativeLayout is null");
        } else {
            relativeLayout.setClickable(true);
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: ojo.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nsn.o()) {
                        LogUtil.h("FunctionSetWeightCardReader", "onBottomLayoutClickListener is fast click");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        LogUtil.a("FunctionSetWeightCardReader", "goToDietDiaryFastRecord");
                        grz.f("FunctionSetWeightCardReader");
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d() {
        if (f15737a) {
            return true;
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            return ClaimWeightDataManager.INSTANCE.isShowRedTip();
        }
        return false;
    }

    private static boolean c() {
        if (!"".equals(SharedPreferenceManager.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), String.valueOf(10000), "IS_CARD_USED_KEY"))) {
            return false;
        }
        LogUtil.a("FunctionSetWeightCardReader", "card is not used before");
        return true;
    }

    static class h extends FunctionSetBeanReader.c {
        h(String str, FunctionSetBeanReader functionSetBeanReader) {
            super(str, functionSetBeanReader);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader.c, com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            ReleaseLogUtil.e("TimeEat_FunctionSetWeightCardReader", "WeightSubscribeListener onChange type ", Integer.valueOf(i), " changeKey ", str);
            if (i == HiSubscribeType.c && ArkUIXConstants.DELETE.equals(str)) {
                gsd.a(0L);
            }
            if (i == 103) {
                if ("900300022".equals(str)) {
                    qvz.b();
                    return;
                }
                return;
            }
            if (i == HiSubscribeType.c) {
                gsd.p();
                gsd.r();
            }
            if (i == DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value() || i == DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM_SET.value() || i == DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE_SET.value()) {
                gsd.t();
            }
            super.onChange(i, hiHealthClient, str, hiHealthData, j);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_weight);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(this.b) ? R.color._2131297763_res_0x7f0905e3 : R.color._2131297762_res_0x7f0905e2;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetWeightCardReader";
    }
}

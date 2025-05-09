package defpackage;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.HealthRecordCbk;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class oiz extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private b f15713a;
    private d b;
    private List<Integer> c;
    private List<Integer> d;
    private View e;
    private boolean f;
    private long g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private HiDataReadResultListener o;
    private boolean r;
    private long t;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return R.color._2131297759_res_0x7f0905df;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130839618_res_0x7f020842;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131428363_res_0x7f0b040b : R.drawable.health_card_default_img_small;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == 700017;
    }

    static class b extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<oiz> f15714a;
        private boolean e;

        b(oiz oizVar) {
            super("FunctionSetHealthCardReader", null);
            this.f15714a = new WeakReference<>(oizVar);
        }

        void c(HiHealthData hiHealthData) {
            HiHealthData hiHealthData2;
            if (hiHealthData != null) {
                oiz.setHasCardData(this.f15714a, true);
                hiHealthData2 = new HiHealthData();
                hiHealthData2.setStartTime(hiHealthData.getStartTime());
                hiHealthData2.putBoolean("isShowRedDot", hiHealthData.getBoolean("isShowRedDot"));
                hiHealthData2.setEndTime(hiHealthData.getEndTime());
                hiHealthData2.putLong("_t", hiHealthData.getStartTime());
                hiHealthData2.putLong("ReportTime", hiHealthData.getLong("ReportTime"));
                hiHealthData2.putInt("Report_Number", hiHealthData.getInt("Report_Number"));
                hiHealthData2.putInt("CardType", hiHealthData.getInt("CardType"));
                hiHealthData2.putLong("testReportTime", hiHealthData.getLong("testReportTime"));
                hiHealthData2.putInt("TestExceptionNumber", hiHealthData.getInt("TestExceptionNumber"));
                hiHealthData2.putInt("TestTotalNumber", hiHealthData.getInt("TestTotalNumber"));
                hiHealthData2.putInt("_u", hiHealthData.toString().hashCode());
            } else {
                oiz.setHasCardData(this.f15714a, false);
                hiHealthData2 = null;
            }
            oiz.saveDataFromHealthApi("FunctionSetHealthCardReader", this.f15714a, hiHealthData2);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            oiz oizVar = this.f15714a.get();
            if (oizVar == null) {
                LogUtil.h("FunctionSetHealthCardReader", "handleCacheData reader is null");
                return;
            }
            if (hiHealthData == null) {
                if (this.e && z) {
                    oizVar.g();
                    return;
                }
                return;
            }
            this.e = true;
            oizVar.n = hiHealthData.getInt("CardType");
            oizVar.m = hiHealthData.getInt("Report_Number");
            oizVar.l = hiHealthData.getInt("TestExceptionNumber");
            oizVar.k = hiHealthData.getInt("TestTotalNumber");
            oizVar.g = hiHealthData.getLong("ReportTime");
            oizVar.t = hiHealthData.getLong("testReportTime");
            oizVar.f = hiHealthData.getBoolean("isShowRedDot");
            oizVar.a(z, hiHealthData.getInt("_u"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("FunctionSetHealthCardReader", "show empty view!");
        FunctionSetBean buildEmptyCardBean = buildEmptyCardBean();
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = buildEmptyCardBean;
        this.b.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, int i) {
        setHasCardData(true);
        FunctionSetBean e = e();
        e.e(this.f);
        e.c(i);
        setFunctionSetBean(e);
        if (z) {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = e;
            this.b.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(e);
    }

    public oiz(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetHealthCardReader", cardConfig);
        this.i = true;
        this.h = false;
        this.n = 0;
        this.j = false;
        this.m = 0;
        this.r = false;
        this.l = 0;
        this.k = 0;
        this.d = new ArrayList(1);
        this.c = null;
        this.b = new d(this);
        b bVar = new b(this);
        this.f15713a = bVar;
        this.o = new c(bVar, this.b, this);
        h();
    }

    private void h() {
        LogUtil.a("FunctionSetHealthCardReader", "subscribeHealthRecordData");
        this.d.add(700017);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(this.d, new FunctionSetBeanReader.c("FunctionSetHealthCardReader", this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetHealthCardReader", "readCardData start");
        HiHealthManager.d(this.mContext).readHiHealthDataPro(HiDataReadProOption.builder().e(c()).b(1).e(), new c(this.f15713a, this.b, this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.i = false;
        ReleaseLogUtil.e("TimeEat_FunctionSetHealthCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isNeedRefreshOnResume() {
        return !this.i;
    }

    static class d extends BaseHandler<oiz> {
        d(oiz oizVar) {
            super(Looper.getMainLooper(), oizVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbd_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oiz oizVar, Message message) {
            if (message.what == 1) {
                LogUtil.a("FunctionSetHealthCardReader", "handleMessageWhenReferenceNotNull");
                oizVar.notifyItemChanged((FunctionSetBean) message.obj);
            } else {
                LogUtil.h("FunctionSetHealthCardReader", "unkonw msg");
            }
        }
    }

    public static class c implements HiDataReadResultListener {
        private final WeakReference<oiz> b;
        private WeakReference<d> d;
        private WeakReference<b> e;

        private c(b bVar, d dVar, oiz oizVar) {
            this.b = new WeakReference<>(oizVar);
            this.e = new WeakReference<>(bVar);
            this.d = new WeakReference<>(dVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            oiz oizVar = this.b.get();
            if (oizVar == null) {
                LogUtil.h("FunctionSetHealthCardReader", "handleCacheData reader is null");
                return;
            }
            HiHealthData hiHealthData = new HiHealthData();
            if (i == 0) {
                List list = oizVar.getList(obj, 700017);
                if (bkz.e(list)) {
                    LogUtil.h("FunctionSetHealthCardReader", "handleCacheData reader is null");
                    if (Utils.o() || LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
                        c(this.e, this.d, oizVar);
                        return;
                    } else {
                        b(oizVar, hiHealthData);
                        return;
                    }
                }
                HiHealthData hiHealthData2 = (HiHealthData) list.get(0);
                SharedPreferenceManager.c("privacy_center", "health_record", String.valueOf(hiHealthData2.getStartTime()));
                c(oizVar, hiHealthData, hiHealthData2);
                return;
            }
            LogUtil.h("FunctionSetHealthCardReader", "microTest data errorCode is : ", Integer.valueOf(i));
            c(this.e, this.d, oizVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void c(defpackage.oiz r11, com.huawei.hihealth.HiHealthData r12, com.huawei.hihealth.HiHealthData r13) {
            /*
                r10 = this;
                java.lang.String r0 = "abnormalNum"
                java.lang.String r1 = "totalNum"
                java.lang.String r13 = r13.getMetaData()
                boolean r2 = android.text.TextUtils.isEmpty(r13)
                java.lang.String r3 = "FunctionSetHealthCardReader"
                if (r2 == 0) goto L1a
                java.lang.String r11 = "microTest metaData is empty"
                java.lang.Object[] r11 = new java.lang.Object[]{r11}
                com.huawei.hwlogsmodel.LogUtil.h(r3, r11)
                return
            L1a:
                r2 = 0
                r4 = 0
                org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> L60
                r6.<init>(r13)     // Catch: org.json.JSONException -> L60
                java.lang.String r13 = "generateTime"
                long r4 = r6.optLong(r13)     // Catch: org.json.JSONException -> L60
                r7 = 1000(0x3e8, double:4.94E-321)
                long r4 = r4 * r7
                java.lang.String r13 = "extendData"
                java.lang.String r13 = r6.optString(r13)     // Catch: org.json.JSONException -> L60
                boolean r13 = android.text.TextUtils.isEmpty(r13)     // Catch: org.json.JSONException -> L60
                boolean r7 = r6.has(r1)     // Catch: org.json.JSONException -> L61
                if (r7 == 0) goto L4c
                boolean r7 = r6.has(r0)     // Catch: org.json.JSONException -> L61
                if (r7 == 0) goto L4c
                int r0 = r6.optInt(r0)     // Catch: org.json.JSONException -> L61
                int r1 = r6.optInt(r1)     // Catch: org.json.JSONException -> L62
                r2 = r0
                r0 = r1
                goto L56
            L4c:
                int[] r0 = r10.e(r6)     // Catch: org.json.JSONException -> L61
                r1 = r0[r2]     // Catch: org.json.JSONException -> L61
                r6 = 1
                r0 = r0[r6]     // Catch: org.json.JSONException -> L5e
                r2 = r1
            L56:
                defpackage.oiz.c(r11, r2, r0)     // Catch: org.json.JSONException -> L5a
                goto L6e
            L5a:
                r9 = r2
                r2 = r0
                r0 = r9
                goto L62
            L5e:
                r0 = r1
                goto L62
            L60:
                r13 = r2
            L61:
                r0 = r2
            L62:
                java.lang.String r11 = "healthRecordMetaData get fail"
                java.lang.Object[] r11 = new java.lang.Object[]{r11}
                com.huawei.hwlogsmodel.LogUtil.b(r3, r11)
                r9 = r2
                r2 = r0
                r0 = r9
            L6e:
                r11 = 2
                java.lang.String r1 = "CardType"
                r12.putInt(r1, r11)
                if (r2 <= 0) goto L7a
                r11 = 3
                r12.putInt(r1, r11)
            L7a:
                java.lang.String r11 = "testReportTime"
                r12.putLong(r11, r4)
                java.lang.String r11 = "TestExceptionNumber"
                r12.putInt(r11, r2)
                java.lang.String r11 = "TestTotalNumber"
                r12.putInt(r11, r0)
                java.lang.String r11 = "isShowRedDot"
                r12.putBoolean(r11, r13)
                java.lang.ref.WeakReference<oiz$b> r11 = r10.e
                java.lang.ref.WeakReference<oiz$d> r13 = r10.d
                r10.d(r11, r13, r12)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: oiz.c.c(oiz, com.huawei.hihealth.HiHealthData, com.huawei.hihealth.HiHealthData):void");
        }

        private int[] e(JSONObject jSONObject) {
            int e;
            Iterator<String> keys = jSONObject.keys();
            int i = 0;
            int i2 = 0;
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.contains("AbnormalFlag")) {
                    int optInt = jSONObject.optInt(next);
                    if (next.equals("vascularAbnormalFlag")) {
                        i2 = jSONObject.optInt("vascularType") == 0 ? i2 + 2 : i2 + 1;
                        if (optInt > 1) {
                            e = e(optInt);
                            i += e;
                        }
                    } else if (next.equals("breathAbnormalFlag")) {
                        i2 = jSONObject.optInt("breathType") == 0 ? i2 + 3 : i2 + 1;
                        if (optInt > 1) {
                            e = e(optInt);
                            i += e;
                        }
                    } else {
                        i2++;
                        if (optInt > 1) {
                            i++;
                        }
                    }
                }
            }
            return new int[]{i, i2};
        }

        private int e(int i) {
            String substring = Integer.toBinaryString(i).substring(0, Integer.toBinaryString(i).length() - 1);
            int i2 = 0;
            for (int i3 = 0; i3 < substring.length(); i3++) {
                if (substring.charAt(i3) == '1') {
                    i2++;
                }
            }
            return i2;
        }

        private void b(final oiz oizVar, final HiHealthData hiHealthData) {
            ((UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class)).queryHealthRecords(0, HiDateUtil.c(System.currentTimeMillis()), new HealthRecordCbk<List<gmt>>() { // from class: oiz.c.3
                @Override // com.huawei.health.userprofilemgr.model.HealthRecordCbk
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<gmt> list) {
                    boolean z;
                    if (list.size() == 0) {
                        c cVar = c.this;
                        cVar.c((WeakReference<b>) cVar.e, (WeakReference<d>) c.this.d, oizVar);
                        return;
                    }
                    LogUtil.a("FunctionSetHealthCardReader", "report number is ", Integer.valueOf(list.size()));
                    Collections.sort(list, new Comparator<gmt>() { // from class: oiz.c.3.2
                        @Override // java.util.Comparator
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public int compare(gmt gmtVar, gmt gmtVar2) {
                            return Long.compare(gmtVar2.b(), gmtVar.b());
                        }
                    });
                    Iterator<gmt> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        gmt next = it.next();
                        if (next.d() != null && !next.d().contains(CommonUtil.READ_FLAG)) {
                            z = true;
                            break;
                        }
                    }
                    long b = list.get(0).b();
                    SharedPreferenceManager.c("privacy_center", "health_record", String.valueOf(HiDateUtil.a((int) b)));
                    hiHealthData.putLong("ReportTime", b);
                    hiHealthData.putInt("CardType", 1);
                    hiHealthData.putInt("Report_Number", list.size());
                    hiHealthData.putBoolean("isShowRedDot", z);
                    oizVar.d(list.size());
                    c cVar2 = c.this;
                    cVar2.d(cVar2.e, c.this.d, hiHealthData);
                }

                @Override // com.huawei.health.userprofilemgr.model.HealthRecordCbk
                public void onFailure(int i, String str) {
                    LogUtil.a("FunctionSetHealthCardReader", "readReportData onFailure");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(WeakReference<b> weakReference, WeakReference<d> weakReference2, oiz oizVar) {
            oizVar.n = 0;
            b bVar = weakReference.get();
            if (weakReference2.get() == null) {
                LogUtil.b("FunctionSetHealthCardReader", "handler is null");
            } else if (bVar == null) {
                LogUtil.b("FunctionSetHealthCardReader", "runnable is null");
            } else {
                bVar.c(null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(WeakReference<b> weakReference, WeakReference<d> weakReference2, HiHealthData hiHealthData) {
            b bVar = weakReference.get();
            if (weakReference2.get() == null) {
                LogUtil.b("FunctionSetHealthCardReader", "handler is null");
            } else if (bVar == null) {
                LogUtil.b("FunctionSetHealthCardReader", "runnable is null");
            } else {
                bVar.c(hiHealthData);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
            LogUtil.a("FunctionSetHealthCardReader", "onResultIntent");
        }
    }

    private HiDataReadOption c() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setType(new int[]{700017});
        return hiDataReadOption;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        d();
        d dVar = this.b;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
        }
    }

    public void d() {
        LogUtil.a("FunctionSetHealthCardReader", "unSubscribeHealthRecordData");
        List<Integer> list = this.c;
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.c, new FunctionSetBeanReader.b("FunctionSetHealthCardReader", "unSubscribeHealthRecordData, isSuccess:"));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        ohy.c().a(this.o);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetHealthCardReader", "subscribeHealthRecordData, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetHealthCardReader", "registerHealthRecordListener success");
        this.c = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        this.l = i;
        this.k = i2;
        if (i == 0) {
            this.n = 2;
        } else {
            this.n = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        this.m = i;
        this.n = 1;
    }

    public FunctionSetBean e() {
        String str;
        String str2;
        setBiHasData(this.g);
        if (this.n == 1) {
            long c2 = DateFormatUtil.c((int) this.g);
            int i = this.m;
            String b2 = owm.b(c2);
            StringBuffer stringBuffer = new StringBuffer(1024);
            stringBuffer.append(b2);
            stringBuffer.append(" ");
            stringBuffer.append(this.mContext.getResources().getString(R.string._2130846399_res_0x7f0222bf));
            str2 = stringBuffer.toString();
            str = nsf.a(R.plurals._2130903377_res_0x7f030151, i, UnitUtil.e(i, 1, 0));
        } else {
            str = "";
            str2 = str;
        }
        if (this.n == 3) {
            String b3 = owm.b(this.t);
            int i2 = this.l;
            String a2 = nsf.a(R.plurals._2130903375_res_0x7f03014f, i2, UnitUtil.e(i2, 1, 0));
            StringBuffer stringBuffer2 = new StringBuffer(1024);
            stringBuffer2.append(b3);
            stringBuffer2.append(" ");
            stringBuffer2.append(this.mContext.getResources().getString(R.string.IDS_health_examination));
            str2 = stringBuffer2.toString();
            str = a2;
        }
        if (this.n == 2) {
            String b4 = owm.b(this.t);
            int i3 = this.k;
            String a3 = nsf.a(R.plurals._2130903376_res_0x7f030150, i3, UnitUtil.e(i3, 1, 0));
            StringBuffer stringBuffer3 = new StringBuffer(1024);
            stringBuffer3.append(b4);
            stringBuffer3.append(" ");
            stringBuffer3.append(this.mContext.getResources().getString(R.string.IDS_health_examination));
            str2 = stringBuffer3.toString();
            str = a3;
        }
        return new FunctionSetBean.a("").d(this.mContext).a(FunctionSetType.HEALTH_RECORD_CARD).c(str2).b(FunctionSetBean.ViewType.DATA_VIEW).a(c(str)).c();
    }

    private CharSequence c(String str) {
        int i = this.n;
        if (i == 1) {
            return a(str, this.m);
        }
        if (i == 3) {
            return a(str, this.l);
        }
        return i == 2 ? a(str, this.k) : str;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(this.mContext.getResources().getString(R.string.IDS_health_record)).a(FunctionSetType.HEALTH_RECORD_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).e(this.mContext.getResources().getString(R.string.IDS_health_onekey_examination)).d(this.mContext).c();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetHealthCardReader", "createCardView");
        if (this.n == 1) {
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.card_healthrecord_view, (ViewGroup) null);
            this.e = inflate;
            ((ImageView) inflate.findViewById(R.id.barchart)).setImageResource(R.drawable._2131428367_res_0x7f0b040f);
        }
        if (this.n == 3) {
            View inflate2 = LayoutInflater.from(this.mContext).inflate(R.layout.card_healthrecord_view, (ViewGroup) null);
            this.e = inflate2;
            ((ImageView) inflate2.findViewById(R.id.barchart)).setImageResource(R.drawable._2131428365_res_0x7f0b040d);
        }
        if (this.n == 2) {
            View inflate3 = LayoutInflater.from(this.mContext).inflate(R.layout.card_healthrecord_view, (ViewGroup) null);
            this.e = inflate3;
            ((ImageView) inflate3.findViewById(R.id.barchart)).setImageResource(R.drawable._2131428365_res_0x7f0b040d);
        }
        return this.e;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        LogUtil.a("FunctionSetHealthCardReader", "go to Health Record CARD record");
        if (nsn.a(500)) {
            return;
        }
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: oja
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    oiz.this.e(i, obj);
                }
            }, "");
        } else {
            b();
        }
    }

    /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            b();
        }
    }

    private void b() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).gotoH5HealthRecord(this.mContext, "0", String.valueOf(this.n));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetHealthCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private CharSequence a(String str, int i) {
        return owm.b(str, UnitUtil.e(i, 1, 0));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.health_card_oversea);
    }

    private long a() {
        int i = this.n;
        if (i == 0) {
            return 0L;
        }
        if (i == 1) {
            return this.g;
        }
        return HiDateUtil.c(this.t);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void reportCustomBiEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("cardType", Integer.valueOf(this.n));
        hashMap.put("reportDate", Long.valueOf(a()));
        LogUtil.a("FunctionSetHealthCardReader", "uploadBiEvent card visible, FUNCTION_CARD_2010226: ", hashMap.toString());
        ixx.d().d(this.mContext, AnalyticsValue.FUNCTION_CARD_2010226.value(), hashMap, 0);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.f15713a;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetHealthCardReader";
    }
}

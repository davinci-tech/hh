package defpackage;

import android.content.Context;
import android.content.Intent;
import androidx.exifinterface.media.ExifInterface;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureCalibrateQuestionActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper.CustTimerHelper;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper.TimerHelper;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class psm {

    /* renamed from: a, reason: collision with root package name */
    private static psm f16249a;
    private static final byte[] b = new byte[1];
    private HiStressMetaData ah;
    private long aj;
    private WeakReference<PressureCalibrateQuestionActivity> as;
    private long g;
    private int h;
    private Intent m;
    private JSONObject z;
    private List<Integer> af = new ArrayList(16);
    private List<Integer> ai = new ArrayList(16);
    private List<JSONObject> aa = new ArrayList(16);
    private boolean r = false;
    private Semaphore ak = new Semaphore(0);
    private Semaphore i = new Semaphore(0);
    private Semaphore ae = new Semaphore(0);
    private int al = 1;
    private int am = 1;
    private int ac = 0;
    private boolean l = false;
    private boolean q = false;
    private boolean o = false;
    private boolean u = false;
    private boolean w = false;
    private boolean v = false;
    private boolean n = false;
    private boolean p = false;
    private boolean y = false;
    private boolean x = false;
    private boolean ad = false;
    private boolean ab = false;
    private TimerHelper j = null;
    private CustTimerHelper f = null;
    private boolean s = false;
    private HiStressMetaData k = null;
    private float[] ag = null;
    private boolean t = false;
    private IBaseResponseCallback an = new IBaseResponseCallback() { // from class: psm.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj == null) {
                LogUtil.h("PressureUtil", "Message is null!");
            } else {
                if (i == 100000) {
                    dwo.d().s(psm.this.e);
                    psm.this.k(true);
                    LogUtil.a("PressureUtil", "callback is unRegisterNotificationPress");
                    return;
                }
                LogUtil.h("PressureUtil", "Message is not right!");
            }
        }
    };
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: psm.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj == null) {
                LogUtil.h("PressureUtil", "objData is null");
                return;
            }
            if (i == 0) {
                try {
                    psm.this.z = new JSONObject((String) obj);
                    LogUtil.a("PressureUtil", "Message is ", psm.this.z);
                    if (psm.c() - psm.this.x() <= 60000) {
                        psm.this.aa.add(psm.this.z);
                        LogUtil.a("PressureUtil", "BackList add jsonObject!");
                        return;
                    }
                    return;
                } catch (JSONException unused) {
                    LogUtil.b("PressureUtil", "objData change to json not right");
                    return;
                }
            }
            if (i == 125002 || i == 125001 || i == 126008) {
                LogUtil.a("PressureUtil", "err_code is bad = ", Integer.valueOf(i), " getIsPressureMeasureActivity() = ", Boolean.valueOf(psm.this.n()), " getIsPressureCalibrateActivity() = ", Boolean.valueOf(psm.this.h()));
                if (i == 126008) {
                    psm.this.j(126008);
                } else {
                    psm.this.j(1000);
                    psm.this.j(1001);
                }
                psm.this.z();
                return;
            }
            LogUtil.h("PressureUtil", "IBaseResponseCallback onResponse() = err");
        }
    };
    private CommonUiBaseResponse c = new CommonUiBaseResponse() { // from class: psm.1
        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            if (obj == null || i != 0) {
                psm.this.o(false);
            } else {
                LogUtil.a("PressureUtil", "data storage success. pressureMeasureDetailInteractor setStressData err_code = ", Integer.valueOf(i));
                psm.this.o(true);
                if (((Integer) obj).intValue() == 2) {
                    psm.this.ak();
                }
                ObserverManagerUtil.c("PRESSURE_MEASURED", new Object[0]);
            }
            psm.this.ae.release();
            LogUtil.a("PressureUtil", "measureSemaphore.release()");
        }
    };
    private Context d = BaseApplication.getContext();

    private int al() {
        return 1;
    }

    private int an() {
        return 1;
    }

    private psm() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        jqi a2 = jqi.a();
        final jld b2 = jld.b();
        a2.getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: psm.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && (obj instanceof String)) {
                    if ("true".equals((String) obj)) {
                        b2.a(1);
                        return;
                    } else {
                        b2.a(2);
                        return;
                    }
                }
                b2.a(2);
            }
        });
    }

    public static psm e() {
        psm psmVar;
        synchronized (b) {
            if (f16249a == null) {
                f16249a = new psm();
            }
            psmVar = f16249a;
        }
        return psmVar;
    }

    public List<Integer> r() {
        return this.ai;
    }

    public List<Integer> s() {
        return this.af;
    }

    public List<JSONObject> m() {
        return this.aa;
    }

    private void n(float[] fArr) {
        if (fArr != null) {
            this.ag = (float[]) fArr.clone();
        } else {
            this.ag = null;
        }
    }

    public float[] t() {
        float[] fArr = this.ag;
        return fArr != null ? (float[]) fArr.clone() : new float[0];
    }

    private void a(long j) {
        this.aj = j;
    }

    public long x() {
        return this.aj;
    }

    private void e(long j) {
        this.g = j;
    }

    public long a() {
        return this.g;
    }

    public void h(int i) {
        this.al = i;
    }

    public int q() {
        return this.al;
    }

    public void f(int i) {
        this.am = i;
    }

    public int y() {
        return this.am;
    }

    public void a(int i) {
        this.ac = i;
    }

    public int p() {
        return this.ac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(boolean z) {
        this.p = z;
    }

    private boolean ai() {
        return this.p;
    }

    public void e(boolean z) {
        this.l = z;
    }

    private boolean ab() {
        return this.l;
    }

    public void b(boolean z) {
        this.r = z;
    }

    public boolean g() {
        return this.r;
    }

    public void d(boolean z) {
        this.q = z;
    }

    public boolean f() {
        return this.q;
    }

    public void h(boolean z) {
        this.u = z;
    }

    private boolean af() {
        return this.u;
    }

    public void g(boolean z) {
        this.w = z;
    }

    private boolean ag() {
        return this.w;
    }

    public void i(boolean z) {
        this.v = z;
    }

    public boolean l() {
        return this.v;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public boolean j() {
        return this.n;
    }

    public void j(boolean z) {
        this.y = z;
    }

    public boolean n() {
        return this.y;
    }

    public void c(boolean z) {
        this.x = z;
    }

    public boolean h() {
        return this.x;
    }

    public void f(boolean z) {
        this.ab = z;
    }

    public boolean k() {
        return this.ab;
    }

    private void m(boolean z) {
        this.s = z;
    }

    public boolean i() {
        return this.s;
    }

    private void n(boolean z) {
        this.o = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean ae() {
        return this.o;
    }

    public void u() {
        LogUtil.a("PressureUtil", "removeHelperTimer");
        TimerHelper timerHelper = this.j;
        if (timerHelper == null || timerHelper.dtw_() == null) {
            return;
        }
        this.j.dtw_().cancel();
        this.j = null;
    }

    public void z() {
        e(c());
        LogUtil.a("PressureUtil", "stopTimer mEndMeasureTime = ", Long.valueOf(a()));
        if (this.e != null) {
            dwo.d().s(this.e);
        }
        h(true);
        g(true);
        TimerHelper timerHelper = this.j;
        if (timerHelper != null && timerHelper.dtw_() != null) {
            this.j.dtw_().cancel();
            this.j = null;
        }
        CustTimerHelper custTimerHelper = this.f;
        if (custTimerHelper == null || custTimerHelper.dtu_() == null) {
            return;
        }
        this.f.dtu_().cancel();
        this.f = null;
    }

    public void b(final int i, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("PressureUtil", "callback is null");
            return;
        }
        a(c());
        ReleaseLogUtil.e("PressureUtil", "openMeasureOrCalibrate mStartMeasureTime = ", Long.valueOf(x()), " openMeasureOrCalibrate = ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        this.z = jSONObject;
        try {
            jSONObject.put("status", 3);
        } catch (JSONException unused) {
            LogUtil.b("PressureUtil", "jsonObject fail");
        }
        dwo.d().g(this.e);
        dwo.d().b(this.z, new IBaseResponseCallback() { // from class: psm.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                ReleaseLogUtil.e("PressureUtil", "errCode:", Integer.valueOf(i2), "flag: ", Integer.valueOf(i));
                if (obj == null) {
                    LogUtil.h("PressureUtil", "Message is null!");
                } else {
                    iBaseResponseCallback.d(i2, null);
                }
            }
        });
        c(this.aa);
    }

    public void c(int i, final int i2) {
        if (!ab() && ptt.d().m() && !ptt.d().o() && !ptt.d().f()) {
            nrh.c(this.d, this.d.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_answer_question_out_time_notify));
        }
        n(true);
        i(true);
        ad();
        e(c());
        LogUtil.a("PressureUtil", "closeMeasureOrCalibrate mEndMeasureTime = ", Long.valueOf(a()), " closeMeasureOrCalibrate = ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        this.z = jSONObject;
        try {
            jSONObject.put("status", i);
            LogUtil.a("PressureUtil", "closeMeasureOrCalibrate jsonObject = ", this.z);
        } catch (JSONException unused) {
            LogUtil.b("PressureUtil", "closeMeasureOrCalibrate jsonObject fail");
        }
        dwo.d().b(this.z, this.an);
        jdx.b(new Runnable() { // from class: psm.9
            @Override // java.lang.Runnable
            public void run() {
                psm.this.c(i2, true);
                LogUtil.a("PressureUtil", "ThreadPoolUtils startParser!");
            }
        });
        if (ai()) {
            return;
        }
        dwo.d().s(this.e);
    }

    public boolean c(int i, boolean z) {
        List<JSONObject> list;
        if (i() || (list = this.aa) == null || list.size() < 1) {
            return false;
        }
        c(this.aa, i, z);
        return true;
    }

    private void c(List<JSONObject> list, int i, boolean z) {
        m(true);
        LogUtil.a("PressureUtil", "startParser() isParsered == ", Boolean.valueOf(this.s));
        boolean z2 = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            JSONObject jSONObject = list.get(i2);
            this.z = jSONObject;
            a(jSONObject);
            if (i2 == list.size() - 1) {
                z2 = true;
            }
        }
        LogUtil.a("PressureUtil", "JsonObject is Parser end!");
        if (af() || ag()) {
            LogUtil.a("PressureUtil", "STOP_FLAG");
            j(-101010);
            j(-101011);
            h(false);
            g(false);
            return;
        }
        if (z2) {
            LogUtil.a("PressureUtil", "openLibMeasure, json over!");
            if (i == 0) {
                n(b(i));
                LogUtil.a("PressureUtil", "openLibMeasure, MeassureSOBack!");
            } else if (i == 2 && ptt.d().j()) {
                LogUtil.a("PressureUtil", "openLibMeasure, CalibrateSOBack!");
            } else {
                LogUtil.a("PressureUtil", "libType is other type!");
            }
            if (z) {
                j(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        LogUtil.a("PressureUtil", "startBroadcast libType = ", Integer.valueOf(i));
        if (i == 0) {
            a("com.huawei.ui.pressure.measure");
            return;
        }
        if (i == 2) {
            a("com.huawei.ui.pressure.calibrate");
            return;
        }
        if (i == -101010) {
            a("com.huawei.ui.pressure.measure.suddenness");
            return;
        }
        if (i == -101011) {
            a("com.huawei.ui.pressure.measure.calibrate.stop");
            return;
        }
        if (i == 1000) {
            LogUtil.a("PressureUtil", "measure err!");
            d("com.huawei.ui.pressure.measure.err", "mIsFromNoData", "");
        } else if (i == 1001) {
            LogUtil.a("PressureUtil", "calibrate err!");
            d("com.huawei.ui.pressure.calibrate.err", "mIsFromNoData", "");
        } else {
            if (i == 126008) {
                LogUtil.a("PressureUtil", "no privacy err!");
                d("com.huawei.ui.pressure.measure.err", "mIsFromNoData", "no_privacy");
                d("com.huawei.ui.pressure.calibrate.err", "mIsFromNoData", "no_privacy");
                return;
            }
            LogUtil.c("PressureUtil", "libType is no match");
        }
    }

    private void a(String str) {
        d(str, "", "");
    }

    private void d(String str, String str2, String str3) {
        Intent intent = new Intent(str);
        this.m = intent;
        intent.setPackage(this.d.getPackageName());
        if (StringUtils.i(str2)) {
            this.m.putExtra(str2, this.r);
        }
        if (StringUtils.i(str3)) {
            this.m.putExtra(str3, true);
        }
        this.d.sendOrderedBroadcast(this.m, LocalBroadcast.c);
    }

    private void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("rri_list");
            LogUtil.a("PressureUtil", "jsonParser jsonArray: ", jSONArray);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                a(jSONObject2.getInt("rri_value"), jSONObject2.getInt("rri_sqi"));
            }
            LogUtil.a("PressureUtil", "JSON parser Success!");
        } catch (JSONException unused) {
            LogUtil.b("PressureUtil", "JSON parser Fail!");
        }
    }

    private void a(int i, int i2) {
        if (i != 0) {
            this.af.add(Integer.valueOf(i));
            this.ai.add(Integer.valueOf(i2));
        }
    }

    public float[] b(int i) {
        LogUtil.a("PressureUtil", "start openLibMeasure flag = ", Integer.valueOf(i));
        int a2 = ((int) (a() - x())) / 1000;
        LogUtil.a("PressureUtil", "getEndMeasureTime() is ", Long.valueOf(a()), ", getStartMeasureTime is ", Long.valueOf(x()), ", signalTime is ", Integer.valueOf(a2));
        float[] i2 = i(i);
        LogUtil.a("PressureUtil", "newCalibrateData is ", Arrays.toString(i2));
        return kep.d().a(a2, i2, s(), r());
    }

    private float[] i(int i) {
        float[] fArr = new float[18];
        LogUtil.a("PressureUtil", "initCalibrateData flag:", Integer.valueOf(i));
        if (i == 0) {
            return e().c(i);
        }
        if (i == 2) {
            b(i, fArr);
            return fArr;
        }
        if (i == 1) {
            b(i, fArr);
            return fArr;
        }
        LogUtil.h("PressureUtil", "initCalibrateData flag is error");
        return fArr;
    }

    private void b(int i, float[] fArr) {
        for (int i2 = 0; i2 < 15; i2++) {
            fArr[i2] = 0.0f;
        }
        if (i == 2) {
            fArr[16] = ptt.d().e();
        } else if (i == 1) {
            fArr[16] = 0.0f;
        } else {
            LogUtil.h("PressureUtil", "createNewCalibrateData flag is error");
        }
        fArr[17] = i;
    }

    public String d(int i) {
        if (i == 1) {
            return this.d.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_1);
        }
        if (i == 2) {
            return this.d.getResources().getString(R$string.IDS_hw_pressure_normal);
        }
        if (i == 3) {
            return this.d.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_item_3);
        }
        if (i == 4) {
            return this.d.getResources().getString(R$string.IDS_hw_pressure_highly);
        }
        LogUtil.c("PressureUtil", "grade is no match!");
        return "";
    }

    public static long c() {
        return System.currentTimeMillis();
    }

    public void c(final int i, int i2, final int i3) {
        LogUtil.a("PressureUtil", "Current Timer start! time === ", Integer.valueOf(i2), ExifInterface.LATITUDE_SOUTH);
        TimerHelper timerHelper = new TimerHelper(i2, 1, i3);
        this.j = timerHelper;
        timerHelper.e(new TimerHelper.OnFinishListener() { // from class: psm.10
            @Override // com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper.TimerHelper.OnFinishListener
            public void finish() {
                LogUtil.a("PressureUtil", "setTimer closeMeasureOrCalibrate");
                psm.this.c(i, i3);
                psm.this.i(true);
            }
        });
        this.j.d();
    }

    public void a(int i, Context context, float[] fArr) {
        if (context == null || fArr == null) {
            LogUtil.h("PressureUtil", " context isnull or libRetValue is null !!!");
            return;
        }
        LogUtil.a("PressureUtil", " PressureUtil setDataToData!");
        pwr pwrVar = new pwr();
        if (i == 0) {
            LogUtil.a("PressureUtil", " setDataToData getMeasureHiStressMetaData()!");
            this.ah = j(fArr);
        } else if (i == 2) {
            this.ah = f(fArr);
            kpl.c().c(context, this.ah);
        } else {
            LogUtil.c("PressureUtil", "measure flag is no match");
        }
        pwrVar.d(this.ah, this.c, i);
        try {
            if (this.ae.tryAcquire(2L, TimeUnit.SECONDS)) {
                LogUtil.a("PressureUtil", "measureSemaphore isTryAcquire()");
            } else {
                LogUtil.h("PressureUtil", "measureSemaphore isTryAcquire() fail");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("PressureUtil", "measureSemaphore.tryAcquire InterruptedException");
        }
        if (ah()) {
            return;
        }
        pwrVar.d(this.ah, this.c, i);
        try {
            if (this.ae.tryAcquire(2L, TimeUnit.SECONDS)) {
                LogUtil.a("PressureUtil", "measureSemaphore tryAcquire2()");
            } else {
                LogUtil.h("PressureUtil", "measureSemaphore tryAcquire2() fail");
            }
        } catch (InterruptedException unused2) {
            LogUtil.b("PressureUtil", "measureSemaphore.tryAcquire2 InterruptedException");
        }
    }

    private HiStressMetaData j(float[] fArr) {
        LogUtil.a("PressureUtil", "getMeasureHiStressMetaData start ");
        HiStressMetaData hiStressMetaData = new HiStressMetaData();
        this.ah = hiStressMetaData;
        hiStressMetaData.configStressStartTime(x());
        this.ah.configStressEndTime(a());
        this.ah.configStressAlgorithmVer(i(fArr));
        this.ah.configStressDevNo(am());
        this.ah.configStressScore(m(fArr));
        this.ah.configStressGrade(l(fArr));
        this.ah.configStressCalibFlag(o(fArr));
        this.ah.configStressMeasureType(0);
        this.ah.configStressAccFlag(an());
        this.ah.configStressPpgFlag(al());
        this.ah.configStressFeature(g(fArr));
        this.ah.configStressFeatureAtts(ac());
        LogUtil.a("PressureUtil", "getMeasureHiStressMetaData end ");
        return this.ah;
    }

    private HiStressMetaData f(float[] fArr) {
        LogUtil.a("PressureUtil", "getCalibrateHiStressMetaData");
        HiStressMetaData hiStressMetaData = new HiStressMetaData();
        this.ah = hiStressMetaData;
        hiStressMetaData.configStressStartTime(x());
        this.ah.configStressEndTime(a());
        this.ah.configStressAlgorithmVer(i(fArr));
        this.ah.configStressDevNo(am());
        this.ah.configStressScore(m(fArr));
        this.ah.configStressGrade(l(fArr));
        this.ah.configStressCalibFlag(o(fArr));
        this.ah.configStressMeasureType(2);
        this.ah.configStressAccFlag(an());
        this.ah.configStressPpgFlag(al());
        this.ah.configStressFeature(g(fArr));
        this.ah.configStressFeatureAtts(ac());
        return this.ah;
    }

    private int i(float[] fArr) {
        return (int) fArr[10];
    }

    private List<Float> g(float[] fArr) {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < 12; i++) {
            if (i >= 0 && i < 10) {
                arrayList.add(Float.valueOf(fArr[i]));
            }
            if (i >= 10 && i < 12) {
                arrayList.add(Float.valueOf(0.0f));
            }
        }
        return arrayList;
    }

    private List<String> ac() {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < 12; i++) {
            arrayList.add("0");
        }
        return arrayList;
    }

    private int m(float[] fArr) {
        return (int) fArr[15];
    }

    private int l(float[] fArr) {
        return (int) fArr[16];
    }

    private int o(float[] fArr) {
        int i = (int) fArr[17];
        if (i == 0) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        LogUtil.c("PressureUtil", "StressCalibFlag no match");
        return (int) fArr[17];
    }

    public boolean b() {
        return jpt.a("PressureUtil") != null;
    }

    private int am() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "PressureUtil");
        if (deviceInfo == null) {
            LogUtil.h("PressureUtil", "getStress_dev_no fail");
        } else {
            this.h = deviceInfo.getProductType();
        }
        return this.h;
    }

    public boolean h(float[] fArr) {
        if (fArr != null) {
            float f = fArr[18];
            if (f == 1.0f) {
                LogUtil.a("PressureUtil", "libRetValue[18] = ", Float.valueOf(f));
                return true;
            }
        }
        return false;
    }

    public int d(float[] fArr) {
        return (int) fArr[15];
    }

    public int a(float[] fArr) {
        return (int) fArr[16];
    }

    public boolean b(float[] fArr) {
        boolean z = false;
        if (fArr != null && fArr.length > 1 && fArr[18] == 1.0f) {
            float f = fArr[15];
            if (f >= 1.0f && f <= 99.0f) {
                z = true;
            }
            LogUtil.a("PressureUtil", "isCalibrateSucceed =", Boolean.valueOf(z));
        }
        return z;
    }

    public int e(float[] fArr) {
        return (int) fArr[15];
    }

    public int c(float[] fArr) {
        return (int) fArr[16];
    }

    private void a(List<?> list) {
        if (list != null) {
            list.clear();
            LogUtil.a("PressureUtil", "clearList");
        }
    }

    public void e(int i) {
        LogUtil.a("PressureUtil", "closeMeasureOrCalibrateAll");
        JSONObject jSONObject = new JSONObject();
        this.z = jSONObject;
        try {
            jSONObject.put("status", i);
            LogUtil.a("PressureUtil", this.z.toString());
        } catch (JSONException unused) {
            LogUtil.b("PressureUtil", "jsonObject fail");
        }
        dwo.d().b(this.z, this.an);
    }

    public float[] c(int i) {
        float[] g;
        synchronized (this) {
            g = g(i);
            if (kep.d().b(g)) {
                LogUtil.a("PressureUtil", "newCalibData first is all 0 ");
                g = g(i);
            }
        }
        return g;
    }

    private float[] g(final int i) {
        final float[] fArr = new float[18];
        kpl.c().a(new IBaseResponseCallback() { // from class: psm.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (i2 == 0 && obj != null) {
                    LogUtil.a("PressureUtil", "objData == ", obj);
                    psm.this.k = jlg.c((String) obj);
                    psm.this.a(fArr, i);
                } else {
                    LogUtil.h("PressureUtil", "No data from Datas !");
                }
                psm.this.ak.release();
                LogUtil.a("PressureUtil", "semaphore.release()");
            }
        });
        try {
            if (this.ak.tryAcquire(2L, TimeUnit.SECONDS)) {
                LogUtil.a("PressureUtil", "semaphore isTryAcquire()");
            } else {
                LogUtil.a("PressureUtil", "semaphore isTryAcquire() fail");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("PressureUtil", "semaphore fail");
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float[] fArr, int i) {
        HiStressMetaData hiStressMetaData = this.k;
        if (hiStressMetaData == null) {
            return;
        }
        List<Float> fetchStressFeature = hiStressMetaData.fetchStressFeature();
        for (int i2 = 0; i2 < 18; i2++) {
            if (i2 >= 0 && i2 < 10) {
                fArr[i2] = fetchStressFeature.get(i2).floatValue();
            }
            if (i2 >= 10 && i2 <= 13) {
                fArr[i2] = 0.0f;
            }
            if (i2 > 13 && i2 <= 15) {
                fArr[i2] = 55.0f;
            }
            if (i2 == 16) {
                fArr[i2] = this.k.fetchStressScore();
            }
            if (i2 == 17) {
                fArr[i2] = i;
            }
        }
    }

    private void c(final List<JSONObject> list) {
        LogUtil.a("PressureUtil", "gameOver()");
        CustTimerHelper custTimerHelper = new CustTimerHelper(60, 12);
        this.f = custTimerHelper;
        custTimerHelper.a(new CustTimerHelper.OnFinishListener() { // from class: psm.6
            @Override // com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.helper.CustTimerHelper.OnFinishListener
            public void finish() {
                if (psm.this.ae()) {
                    LogUtil.a("PressureUtil", "mIsCompletedGameOver = ", Boolean.valueOf(psm.this.ae()));
                    psm.this.ad();
                } else if (list.size() < 1) {
                    psm.this.j(-101010);
                    psm.this.j(-101011);
                } else {
                    psm.this.ad();
                }
            }
        });
        this.f.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        e(this.f);
        LogUtil.a("PressureUtil", "closeGameOver");
    }

    public void w() {
        CustTimerHelper custTimerHelper = this.f;
        if (custTimerHelper != null) {
            if (custTimerHelper.dtu_() != null) {
                this.f.dtu_().cancel();
            }
            this.f = null;
        }
        a(0);
        LogUtil.a("PressureUtil", "Anything is reset!!");
        b(this.j);
        e(this.f);
        if (!ai()) {
            dwo.d().s(this.e);
        }
        f(1);
        f(false);
        e(0L);
        a(0L);
        e(false);
        h(1);
        n(false);
        j(false);
        c(false);
        i(false);
        h(false);
        g(false);
        a(false);
        b(false);
        d(false);
        m(false);
        k(false);
        a(this.aa);
        a(this.af);
        a(this.ai);
        this.m = null;
        n((float[]) null);
        ptt.d().u();
    }

    public void v() {
        e(4);
        if (!ai()) {
            dwo.d().s(this.e);
        }
        b(this.j);
        e(this.f);
        LogUtil.a("PressureUtil", "stopAllTimer");
    }

    private void b(TimerHelper timerHelper) {
        if (timerHelper == null || timerHelper.dtw_() == null) {
            return;
        }
        timerHelper.dtw_().cancel();
        timerHelper.dtx_(null);
    }

    private void e(CustTimerHelper custTimerHelper) {
        if (custTimerHelper != null) {
            if (custTimerHelper.dtu_() != null) {
                custTimerHelper.dtu_().cancel();
                custTimerHelper.dtv_(null);
            }
            LogUtil.a("PressureUtil", "helper = null");
        }
    }

    public void b(PressureCalibrateQuestionActivity pressureCalibrateQuestionActivity) {
        this.as = new WeakReference<>(pressureCalibrateQuestionActivity);
    }

    public PressureCalibrateQuestionActivity d() {
        WeakReference<PressureCalibrateQuestionActivity> weakReference = this.as;
        if (weakReference == null) {
            LogUtil.b("PressureUtil", "mWeakReference is null");
            return null;
        }
        PressureCalibrateQuestionActivity pressureCalibrateQuestionActivity = weakReference.get();
        if (pressureCalibrateQuestionActivity != null) {
            return pressureCalibrateQuestionActivity;
        }
        LogUtil.b("PressureUtil", "activity is null");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(boolean z) {
        LogUtil.a("PressureUtil", "setIsDataStoredSucceed = ", Boolean.valueOf(z));
        this.t = z;
    }

    private boolean ah() {
        LogUtil.a("PressureUtil", "getIsDataStoredSucceed=", Boolean.valueOf(this.t));
        return this.t;
    }

    public long o() {
        HiStressMetaData hiStressMetaData;
        if (!this.t || (hiStressMetaData = this.ah) == null) {
            return 0L;
        }
        this.t = false;
        return hiStressMetaData.fetchStressStartTime();
    }

    public void b(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("PressureUtil", "readData callback is null");
            return;
        }
        HiAggregateOption aa = aa();
        aa.setCount(1);
        aa.setTimeRange(0L, System.currentTimeMillis());
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(aa, new HiAggregateListener() { // from class: psm.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                if (koq.b(list)) {
                    LogUtil.h("PressureUtil", "datas is empty");
                    iBaseResponseCallback.d(100001, new ArrayList());
                } else {
                    LogUtil.a("PressureUtil", "readData data size = ", Integer.valueOf(list.size()));
                    iBaseResponseCallback.d(0, list);
                }
            }
        });
    }

    private static HiAggregateOption aa() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{2034});
        hiAggregateOption.setConstantsKey(new String[]{"pressure"});
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setSortOrder(1);
        return hiAggregateOption;
    }
}

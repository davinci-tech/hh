package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.vip.api.EquityApi;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.RepeatResourceBenefitInfo;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kci {

    /* renamed from: a, reason: collision with root package name */
    private static kci f14280a;
    private static final Object e = new Object();
    private DataReceiveCallback d = new DataReceiveCallback() { // from class: kci.3
        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "receive device event errorCode is： ", Integer.valueOf(i));
            if (cvrVar instanceof cvp) {
                cvp cvpVar = (cvp) cvrVar;
                LogUtil.a("HwSyncHealthGlanceManager", "message instanceof SampleEvent is: ", cvpVar.toString());
                kci.this.a(keb.b("HwSyncHealthGlanceManager"), cvpVar);
                return;
            }
            ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "health glance flag receive unrecognized message.");
        }
    };

    public static kci c() {
        kci kciVar;
        synchronized (e) {
            if (f14280a == null) {
                LogUtil.a("HwSyncHealthGlanceManager", "getInstance()");
                f14280a = new kci();
            }
            kciVar = f14280a;
        }
        return kciVar;
    }

    private kci() {
        ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "HwSyncHealthGlanceManager");
        b();
        e();
    }

    public void b() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.healthGlanceMgr", this.d);
    }

    public void e() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.benefit", this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo, cvp cvpVar) {
        long e2 = cvpVar.e();
        LogUtil.a("HwSyncHealthGlanceManager", "onDataReceived eventId : ", Long.valueOf(e2));
        if (e2 == 80030006) {
            gnk.b(deviceInfo);
        } else if (e2 == 80030009) {
            b(deviceInfo);
        } else {
            LogUtil.h("HwSyncHealthGlanceManager", "no invalid event id");
        }
    }

    private void b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.d("R_HwSyncHealthGlanceManager", "getBenefit deviceInfo is null");
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        e eVar = new e();
        b(countDownLatch, eVar);
        e eVar2 = new e();
        e(countDownLatch, eVar2);
        try {
            LogUtil.a("HwSyncHealthGlanceManager", "getBenefitAndDoctorInfo result：", Boolean.valueOf(countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b("HwSyncHealthGlanceManager", "getBenefitAndDoctorInfo InterruptedException");
        }
        ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "sendBenefitTagCommand onSuccess isSuccess = ", Boolean.valueOf(a(deviceInfo, eVar.b(), eVar2.b(), (eVar.c() && eVar2.c()) ? 0 : 101)));
    }

    private void b(final CountDownLatch countDownLatch, final e eVar) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("HwSyncHealthGlanceManager", "handleReviewStatus payApi is null");
            countDownLatch.countDown();
        } else {
            payApi.queryBenefitInfo(9, "", new IBaseResponseCallback() { // from class: kcp
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    kci.e(countDownLatch, eVar, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void e(CountDownLatch countDownLatch, e eVar, int i, Object obj) {
        if (i != 0) {
            LogUtil.h("HwSyncHealthGlanceManager", "queryBenefitInfo fail");
            countDownLatch.countDown();
            return;
        }
        if (!(obj instanceof RepeatResourceBenefitInfo)) {
            LogUtil.h("HwSyncHealthGlanceManager", "queryBenefitInfo fail ：objectData is not RepeatResourceBenefitInfo");
            eVar.c(0);
            eVar.e(true);
            countDownLatch.countDown();
            return;
        }
        LogUtil.a("HwSyncHealthGlanceManager", "queryBenefitInfo objectData = ", HiJsonUtil.e(obj));
        RepeatResourceBenefitInfo repeatResourceBenefitInfo = (RepeatResourceBenefitInfo) obj;
        long nowTime = repeatResourceBenefitInfo.getNowTime() != 0 ? repeatResourceBenefitInfo.getNowTime() : System.currentTimeMillis();
        LogUtil.a("HwSyncHealthGlanceManager", "queryBenefitInfo currentTime = ", Long.valueOf(nowTime));
        if (repeatResourceBenefitInfo.getExpireTime().longValue() == 0) {
            eVar.c(0);
        } else if (repeatResourceBenefitInfo.getExpireTime().longValue() >= nowTime) {
            eVar.c(1);
        } else {
            eVar.c(2);
        }
        eVar.e(true);
        countDownLatch.countDown();
    }

    /* loaded from: classes9.dex */
    public static class e {
        public boolean c;
        public int d;

        public int b() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }

        public boolean c() {
            return this.c;
        }

        public void e(boolean z) {
            this.c = z;
        }
    }

    private void e(final CountDownLatch countDownLatch, final e eVar) {
        EquityApi equityApi = (EquityApi) Services.a("Main", EquityApi.class);
        if (equityApi == null) {
            LogUtil.h("HwSyncHealthGlanceManager", "handleReviewStatus equityApi is null");
            countDownLatch.countDown();
        } else {
            equityApi.getDoctorBasicInfo(new DataCallback() { // from class: kci.1
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "getDoctorBasicInfo onFailure errorCode is: ", Integer.valueOf(i));
                    if (i == 23008) {
                        eVar.e(true);
                        eVar.c(0);
                    }
                    countDownLatch.countDown();
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("HwSyncHealthGlanceManager", "getDoctorBasicInfo data = " + jSONObject.toString());
                    if (jSONObject.optInt("resultCode") != 0) {
                        countDownLatch.countDown();
                        return;
                    }
                    if (TextUtils.isEmpty(jSONObject.optString("doctorId")) || "0".equals(jSONObject.optString("doctorId"))) {
                        LogUtil.a("HwSyncHealthGlanceManager", "getDoctorBasicInfo data empty");
                        eVar.e(true);
                        eVar.c(0);
                        countDownLatch.countDown();
                        return;
                    }
                    eVar.e(true);
                    eVar.c(1);
                    countDownLatch.countDown();
                }
            });
        }
    }

    private static boolean a(DeviceInfo deviceInfo, int i, int i2, int i3) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.benefit");
        cvpVar.setWearPkgName("hw.watch.health.bp.benefit");
        cvpVar.setCommandId(2);
        cvpVar.a(80030009L);
        cvpVar.b(0);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("resultCode", Integer.valueOf(i3));
        jsonObject.addProperty("isHasBenefit", Integer.valueOf(i));
        jsonObject.addProperty("isHasSignDoctor", Integer.valueOf(i2));
        String jsonObject2 = jsonObject.toString();
        cvpVar.e(cvx.a(cvx.c(jsonObject2)));
        ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "sendBenefitTagCommand object: ", jsonObject2, "sendBenefitTagCommand sampleEvent: ", cvpVar.toString());
        return cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, "HwSyncHealthGlanceManager");
    }

    public static void d() {
        ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "releaseMgr");
        synchronized (e) {
            if (f14280a == null) {
                ReleaseLogUtil.e("R_HwSyncHealthGlanceManager", "sInstance is null");
                return;
            }
            cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.healthGlanceMgr");
            cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.benefit");
            f14280a = null;
        }
    }
}

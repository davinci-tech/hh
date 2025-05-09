package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.basesport.wearengine.DataReceiver;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.wearengine.constant.TrackHiWearBusinessType;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cba;
import defpackage.spn;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes8.dex */
public class hpz {

    /* renamed from: a, reason: collision with root package name */
    private static hpz f13313a;
    private static final Object c = new Object();
    private e e = new e();
    private hqd d = hqd.a();

    private hpz() {
    }

    public static hpz b() {
        hpz hpzVar;
        synchronized (c) {
            if (f13313a == null) {
                LogUtil.a("Track_HeartZoneDeliveryManager", "getInstance");
                f13313a = new hpz();
            }
            hpzVar = f13313a;
        }
        return hpzVar;
    }

    public static boolean e() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_HeartZoneDeliveryManager");
        if (deviceInfo == null) {
            LogUtil.h("Track_HeartZoneDeliveryManager", "device info null");
            return false;
        }
        return cwi.c(deviceInfo, 54);
    }

    public boolean a() {
        boolean e2 = e();
        LogUtil.a("Track_HeartZoneDeliveryManager", "sentWearEngine isSupport ", Boolean.valueOf(e2));
        if (!e2) {
            return false;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("userPreference_HeartRate_all_posture_data");
        if (userPreference != null && !TextUtils.isEmpty(userPreference.getValue())) {
            String value = userPreference.getValue();
            try {
                HeartRateZoneMgr heartRateZoneMgr = (HeartRateZoneMgr) new Gson().fromJson(value, HeartRateZoneMgr.class);
                LogUtil.a("Track_HeartZoneDeliveryManager", "getHeartRateZoneMgr = ", value);
                b(heartRateZoneMgr, new IBaseResponseCallback() { // from class: hpy
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        LogUtil.a("Track_HeartZoneDeliveryManager", "splicingMessages errorCode ", Integer.valueOf(i));
                    }
                });
            } catch (JsonSyntaxException | NumberFormatException unused) {
                LogUtil.b("Track_HeartZoneDeliveryManager", " updateAllPostureData parse value fail ");
                d();
                return true;
            }
        } else {
            HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
            if (healthDataMgrApi == null) {
                LogUtil.h("Track_HeartZoneDeliveryManager", "getHeartZoneConf : healthDataMgrApi is null.");
                return false;
            }
            b(healthDataMgrApi.getHeartRateZoneMgr(), new IBaseResponseCallback() { // from class: hqe
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("Track_HeartZoneDeliveryManager", "splicingMessages errorCode ", Integer.valueOf(i));
                }
            });
        }
        return true;
    }

    public void d() {
        LogUtil.a("Track_HeartZoneDeliveryManager", "set Default HeartData");
        HeartRateZoneMgr heartRateZoneMgr = new HeartRateZoneMgr(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getLocalUserInfo().getAgeOrDefaultValue());
        if (hpw.d(heartRateZoneMgr)) {
            ReleaseLogUtil.e("Track_HeartZoneDeliveryManager", "the default heart rate config is not valid");
        } else {
            b(heartRateZoneMgr, new IBaseResponseCallback() { // from class: hpv
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("Track_HeartZoneDeliveryManager", "splicingMessages errorCode ", Integer.valueOf(i));
                }
            });
        }
    }

    public void b(HeartRateZoneMgr heartRateZoneMgr, IBaseResponseCallback iBaseResponseCallback) {
        int a2 = this.e.a();
        int typeValue = TrackHiWearBusinessType.RUN_PLAN_INFO_FILE.getTypeValue();
        byte[] e2 = hpw.e(heartRateZoneMgr);
        int length = e2.length;
        if (length == 0) {
            LogUtil.h("Track_HeartZoneDeliveryManager", "length == 0");
            return;
        }
        cba.b bVar = new cba.b();
        bVar.e(typeValue).a(length).b(1).c(a2);
        cba a3 = bVar.a();
        ByteBuffer allocate = ByteBuffer.allocate(a3.j());
        allocate.put(a3.e());
        allocate.put(e2);
        allocate.flip();
        spn.b bVar2 = new spn.b();
        bVar2.c(allocate.array());
        c("heart ", bVar2.e(), new d(this.d, typeValue, a2, iBaseResponseCallback), iBaseResponseCallback);
    }

    private void c(final String str, spn spnVar, final d dVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (dVar != null) {
            this.d.registerCallback(dVar, dVar.b);
        }
        this.d.sendCommandToDevice(spnVar, new SendCallback() { // from class: hpz.2
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                if (i != 500000 && i != 207) {
                    ReleaseLogUtil.c("Track_HeartZoneDeliveryManager", str, " send failed, resultCode is", Integer.valueOf(i));
                    iBaseResponseCallback.d(1, Integer.valueOf(i));
                } else {
                    if (i == 207) {
                        ReleaseLogUtil.d("Track_HeartZoneDeliveryManager", str, " send success, resultCode is", Integer.valueOf(i));
                        if (dVar == null) {
                            iBaseResponseCallback.d(0, Integer.valueOf(i));
                            return;
                        }
                        return;
                    }
                    LogUtil.a("Track_HeartZoneDeliveryManager", str, " send called success, resultCode is", Integer.valueOf(i));
                }
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("Track_HeartZoneDeliveryManager", str, " send progress is", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.a("Track_HeartZoneDeliveryManager", "mHiWearEngineFitnessManager.sendCommandToDevice onFileTransferReport transferWay: ", str2);
            }
        });
    }

    static class d implements DataReceiver {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<hqd> f13314a;
        private int b;
        private IBaseResponseCallback d;
        private int e;

        d(hqd hqdVar, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
            this.f13314a = new WeakReference<>(hqdVar);
            this.b = i;
            this.e = i2;
            this.d = iBaseResponseCallback;
        }

        @Override // com.huawei.health.basesport.wearengine.DataReceiver
        public void onDataReceived(cba cbaVar, int i, byte[] bArr) {
            if (cbaVar == null) {
                LogUtil.h("Track_HeartZoneDeliveryManager", "SendMsgDataReceiver onDataReceived msg head is null, pls check.");
                return;
            }
            if (cbaVar.d() != this.b || cbaVar.a() != this.e) {
                LogUtil.h("Track_HeartZoneDeliveryManager", "SendMsgDataReceiver onDataReceived msg not match, this type is : ", Integer.valueOf(this.b), " received is: ", Integer.valueOf(cbaVar.d()), " this msg id is:", Integer.valueOf(this.e), " received msg id is: ", Integer.valueOf(cbaVar.a()));
                return;
            }
            hqd hqdVar = this.f13314a.get();
            if (hqdVar == null) {
                LogUtil.h("Track_HeartZoneDeliveryManager", "SendMsgDataReceiver onDataReceived HiWearEngineFitnessManager is null, pls check");
                return;
            }
            if (bArr != null && bArr.length >= 4) {
                LogUtil.a("Track_HeartZoneDeliveryManager", "SendMsgDataReceiver onDataReceived type is : ", Integer.valueOf(this.b), "session id is: ", Integer.valueOf(this.e), " dataContents length is: ", Integer.valueOf(bArr.length));
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i2 = wrap.getInt();
                LogUtil.a("Track_HeartZoneDeliveryManager", "SendMsgDataReceiver onDataReceived type is: ", Integer.valueOf(this.b), " session id is: ", Integer.valueOf(this.e), " response code is: ", Integer.valueOf(i2));
                this.d.d(i2 != 1 ? 0 : 1, Integer.valueOf(i2));
            } else {
                LogUtil.h("Track_HeartZoneDeliveryManager", "SendMsgDataReceiver onDataReceived type is: ", Integer.valueOf(this.b), " session id is: ", Integer.valueOf(this.e), " dataContents length error");
            }
            hqdVar.unregisterCallback(this, this.b);
        }
    }

    static class e {
        int c;

        private e() {
            this.c = 0;
        }

        protected int a() {
            int i;
            synchronized (this) {
                int i2 = this.c + 1;
                this.c = i2;
                i = i2 % 10000;
                this.c = i;
            }
            return i;
        }
    }
}

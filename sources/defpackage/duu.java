package defpackage;

import android.content.ContentValues;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.gson.reflect.TypeToken;
import com.huawei.callback.BindPhoneServiceCallback;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.hwservicesmgr.LinkageDeviceCommandListener;
import com.huawei.linkage.sportlinkage.AppLinkageApi;
import com.huawei.linkage.sportlinkage.DataListener;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class duu {

    /* renamed from: a, reason: collision with root package name */
    private final Set<DataListener> f11860a;
    private AppLinkageApi b;
    IBaseCallback.Stub c;
    LinkageDeviceCommandListener.Stub e;

    private duu() {
        this.e = new LinkageDeviceCommandListener.Stub() { // from class: duu.5
            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void startLinkage(int i, int i2) throws RemoteException {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener startLinkage");
                if (duu.this.b != null) {
                    duu.this.b.startLinkage(duu.this.a(i), i2);
                    dwm.c(true);
                    return;
                }
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener mAppLinkageApi ", duu.this.b);
            }

            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void resumeLinkage(int i, int i2) {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener resumeLinkage");
                duu.this.b.resumeLinkage(duu.this.a(i), i2);
            }

            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void stopLinkage() {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener stop");
                duu.this.b.stopLinkage();
                dwm.c(false);
            }

            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void start(int i) {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener start");
                duu.this.b.start(duu.this.a(i));
            }

            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void pause() {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener pause");
                duu.this.b.pause();
            }

            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void resume() {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener resume");
                duu.this.b.resume();
            }

            @Override // com.huawei.hwservicesmgr.LinkageDeviceCommandListener
            public void stop() {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener stop");
                duu.this.b.stop();
                dwm.c(false);
            }
        };
        this.c = new IBaseCallback.Stub() { // from class: duu.3
            @Override // com.huawei.hwservicesmgr.IBaseCallback
            public void onResponse(int i, String str) throws RemoteException {
                ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener code is", Integer.valueOf(i));
                LogUtil.a("LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener dataJson is ", str);
                if (i != 0) {
                    return;
                }
                HashMap hashMap = (HashMap) HiJsonUtil.b(str, new TypeToken<HashMap<Integer, Double>>() { // from class: duu.3.3
                }.getType());
                ldo ldoVar = new ldo();
                if (hashMap.isEmpty()) {
                    ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener convertLinkageData isEmpty");
                    return;
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    Integer num = (Integer) entry.getKey();
                    Double d = (Double) entry.getValue();
                    LogUtil.a("LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener parse dicId=", num, ", dicValue =", d);
                    if (ldr.f14767a.contains(num)) {
                        duw.e(num.intValue()).handle(d.doubleValue(), ldoVar);
                    }
                }
                LogUtil.h("LINKAGE_ReverseLinkagePlatform", "mLinkageDeviceDataListener linkageData = ", ldoVar.toString());
                Iterator it = new HashSet(duu.this.f11860a).iterator();
                while (it.hasNext()) {
                    ((DataListener) it.next()).onResponse(ldoVar);
                }
            }
        };
        this.f11860a = new HashSet();
        this.b = null;
    }

    public static duu a() {
        return e.b;
    }

    public void e(AppLinkageApi appLinkageApi) {
        LogUtil.a("LINKAGE_ReverseLinkagePlatform", "initReverseLinkage");
        this.b = appLinkageApi;
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 == null) {
            LogUtil.h("LINKAGE_ReverseLinkagePlatform", "initReverseLinkage binder == null");
            jez.a(BaseApplication.getContext());
            return;
        }
        try {
            ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "initReverseLinkage register mDeviceCommandListenerAidl: ", this.e, " mLinkageDeviceDataListener:", this.c);
            e2.registerLinkageDeviceCommandListener(this.e);
            e2.registerLinkageDeviceDataListener(this.c);
        } catch (RemoteException e3) {
            ReleaseLogUtil.c("R_LINKAGE_ReverseLinkagePlatform", "initReverseLinkage JSONException: ", ExceptionUtils.d(e3));
        }
    }

    public void aal_(final ContentValues contentValues) {
        if (contentValues == null) {
            ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "handleRemoteException content null");
        } else {
            if (jez.e() == null) {
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                jez.a(BaseApplication.getContext());
                jez.d(new BindPhoneServiceCallback() { // from class: duu.1
                    @Override // com.huawei.callback.BindPhoneServiceCallback
                    public void onBind() {
                        ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "handleRemoteException bindPhoneService delay: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                        jez.b(this);
                        duu.this.aak_(contentValues);
                    }
                });
                return;
            }
            aak_(contentValues);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aak_(ContentValues contentValues) {
        int intValue = contentValues.getAsInteger("workoutTypeKey").intValue();
        int intValue2 = contentValues.getAsInteger("currentSportStatusKey").intValue();
        int intValue3 = contentValues.getAsInteger("linkageOperationKey").intValue();
        ReleaseLogUtil.e("R_LINKAGE_ReverseLinkagePlatform", "handleRemoteException workoutType: ", Integer.valueOf(intValue), ", currentSportStatus: ", Integer.valueOf(intValue2), ", linkageOperation: ", Integer.valueOf(intValue3));
        if (this.b == null) {
            ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "handleRemoteException mAppLinkageApi null");
            return;
        }
        int a2 = a(intValue);
        if (intValue3 == 100) {
            this.b.startLinkage(a2, intValue2);
            return;
        }
        if (intValue3 == 101) {
            this.b.resumeLinkage(a2, intValue2);
        } else if (intValue3 == 201) {
            this.b.start(a2);
        } else {
            ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "handleLinkageAction mDeviceCommandListener == null");
        }
    }

    public void c(DataListener dataListener) {
        this.f11860a.add(dataListener);
    }

    public void d(DataListener dataListener) {
        this.f11860a.remove(dataListener);
    }

    public void h() {
        this.f11860a.clear();
    }

    public void c(int i, String str) {
        LogUtil.a("LINKAGE_ReverseLinkagePlatform", "replyDevice linkageOperationType=", Integer.valueOf(i), " replyCode=", str);
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 == null) {
            ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "replyDevice binder null");
            String d = DfxUtils.d(Thread.currentThread(), null);
            if (CommonUtil.as()) {
                sqo.w("replyDevice binder null, stackTraceInfo: " + d);
            }
            jez.a(BaseApplication.getContext());
            return;
        }
        try {
            e2.notifyPhoneService("reverseLinkageReply", cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "LINKAGE_ReverseLinkagePlatform"), i, str);
        } catch (RemoteException e3) {
            sqo.w("replyDevice RemoteException: " + ExceptionUtils.d(e3));
            ReleaseLogUtil.c("R_LINKAGE_ReverseLinkagePlatform", "replyDevice RemoteException: ", ExceptionUtils.d(e3));
        }
    }

    public void a(JSONObject jSONObject, DeviceInfo deviceInfo) {
        cvu cvuVar = new cvu();
        cvuVar.c(50004L);
        cvuVar.d(kiz.b(System.currentTimeMillis()));
        cvuVar.b(kiz.b(System.currentTimeMillis()));
        cvuVar.setSrcPkgName("hw.sport.linkage.app");
        cvuVar.setWearPkgName("hw.sport.linkage.device");
        cvuVar.e(b(jSONObject));
        LogUtil.a("LINKAGE_ReverseLinkagePlatform", "samplePoint = ", cvuVar);
        cuk.b().sendSamplePointCommand(deviceInfo, cvuVar, "LINKAGE_ReverseLinkagePlatform");
    }

    private List<cvv> b(JSONObject jSONObject) {
        Integer num;
        ArrayList arrayList = new ArrayList(10);
        for (String str : dwk.f11866a) {
            if (jSONObject.has(str) && (num = dwk.e.get(str)) != null) {
                arrayList.add(duw.e(num.intValue()).packageSamplePointInfo(jSONObject, str, num.intValue()));
            }
        }
        return arrayList;
    }

    public void b() {
        b(202);
    }

    public void d() {
        b(203);
    }

    public void e() {
        b(204);
    }

    public void c() {
        b(102);
    }

    private void b(int i) {
        IWearPhoneServiceAIDL e2 = jez.e();
        if (e2 == null) {
            ReleaseLogUtil.d("R_LINKAGE_ReverseLinkagePlatform", "sendCommandToDevice binder null");
            sqo.w("sendCommandToDevice binder null, stackTraceInfo: " + DfxUtils.d(Thread.currentThread(), null));
            jez.a(BaseApplication.getContext());
            return;
        }
        try {
            e2.notifyPhoneService("reverseLinkage", cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "LINKAGE_ReverseLinkagePlatform"), i, null);
        } catch (RemoteException e3) {
            sqo.w("sendCommandToDevice RemoteException: " + ExceptionUtils.d(e3));
            ReleaseLogUtil.c("R_LINKAGE_ReverseLinkagePlatform", "sendCommandToDevice RemoteException: ", ExceptionUtils.d(e3));
        }
    }

    static class e {
        private static final duu b = new duu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i) {
        int i2 = i == 7 ? OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE : i;
        if (i == 3) {
            i2 = 259;
        }
        LogUtil.a("LINKAGE_ReverseLinkagePlatform", "workoutTypeToSportType workoutType: ", Integer.valueOf(i), ", sportType", Integer.valueOf(i2));
        return i2;
    }
}

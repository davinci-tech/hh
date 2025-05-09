package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.datatype.NotificationData;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwExerciseDeviceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kcy extends HwBaseManager implements ParserInterface {
    private static kcy j;
    private int f;
    private Context g;
    private int h;
    private BroadcastReceiver i;
    private List<Object> k;
    private ITransferSleepAndDFXFileCallback.Stub l;
    private ITransferSleepAndDFXFileCallback.Stub m;
    private int n;
    private List<IBaseResponseCallback> o;
    private final HwDeviceMgrInterface p;
    private IBaseResponseCallback q;
    private Gson r;
    private IBaseResponseCallback s;
    private JSONArray t;
    private IBaseResponseCallback w;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14296a = new Object();
    private static final Object e = new Object();
    private static Map<Integer, List<IBaseResponseCallback>> d = new HashMap(16);
    private static final Object b = new Object();

    private boolean b(int i) {
        return (i & 1) == 1;
    }

    private boolean e(int i) {
        return (i & 2) == 2;
    }

    private kcy(Context context) {
        super(context);
        this.n = 0;
        this.h = 0;
        this.f = 0;
        this.k = new ArrayList(16);
        this.o = new ArrayList(16);
        this.r = new Gson();
        this.t = null;
        this.q = null;
        this.s = new IBaseResponseCallback() { // from class: kcy.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                kcy.this.b();
            }
        };
        this.i = new BroadcastReceiver() { // from class: kcy.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "deviceStatusReceiver intent is null");
                    return;
                }
                String action = intent.getAction();
                if (action == null) {
                    ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "deviceStatusReceiver action is null");
                    return;
                }
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    DeviceInfo deviceInfo = intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo ? (DeviceInfo) intent.getParcelableExtra("deviceinfo") : null;
                    if (deviceInfo == null) {
                        ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "deviceStatusReceiver deviceInfo is null");
                        return;
                    }
                    if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                        ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "This device does not have the correspond capability.");
                        return;
                    } else {
                        if (deviceInfo.getDeviceConnectState() == 3) {
                            ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "Data sync bt bt disconnect.");
                            kcy.this.b(300004, "");
                            synchronized (kcy.e) {
                                kcy.d.clear();
                            }
                            return;
                        }
                        return;
                    }
                }
                LogUtil.a("HwRriServiceMgr", "deviceStatusReceiver other action");
            }
        };
        this.l = new ITransferSleepAndDFXFileCallback.Stub() { // from class: kcy.2
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "mFileCallback onSuccess errorCode: ", Integer.valueOf(i));
                kcv.b(str, new IBaseResponseCallback() { // from class: kcy.2.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "saveEcgFileData errorCode: ", Integer.valueOf(i2));
                        if (i2 != 0) {
                            kcy.this.c(100001);
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "save info error.");
                            return;
                        }
                        if (!(obj instanceof Long)) {
                            kcy.this.c(100007);
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "Object Type is error.");
                            return;
                        }
                        long longValue = ((Long) obj).longValue();
                        if (longValue > 2147483647L) {
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "ecg time is over.");
                            kcy.this.c(100007);
                            return;
                        }
                        kcv.d((int) longValue);
                        kcy.this.c(0);
                        NotificationData d2 = jrn.d();
                        if (d2 == null) {
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "notificationData is null, error, please check code.");
                        } else {
                            jrn.b(BaseApplication.getContext()).d(d2, jdh.d());
                        }
                        nsd.a(true);
                    }
                });
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mFileCallback onFailure: ", Integer.valueOf(i));
                kcy.this.c(i);
            }
        };
        this.m = new ITransferSleepAndDFXFileCallback.Stub() { // from class: kcy.3
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str, String str2) throws RemoteException {
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback errorCode: ", Integer.valueOf(i));
                kcv.b(str, new IBaseResponseCallback() { // from class: kcy.3.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback saveEcgFileData errorCode: ", Integer.valueOf(i2));
                        if (i2 != 0) {
                            kcy.this.c(100001);
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback save info error.");
                            return;
                        }
                        if (!(obj instanceof Long)) {
                            kcy.this.c(100007);
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback Object Type is error.");
                            return;
                        }
                        long longValue = ((Long) obj).longValue();
                        if (longValue > 2147483647L) {
                            ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback ecg time is over.");
                            kcy.this.c(100007);
                            return;
                        }
                        kcv.c((int) longValue);
                        kcy.this.c(0);
                        NotificationData c2 = jrn.c();
                        if (c2 == null) {
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback notificationData is null, error, please check code.");
                        } else {
                            jrn.b(BaseApplication.getContext()).a(c2, true, jdh.d());
                        }
                        kcy.this.e();
                    }
                });
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mEcgAnalysisFileCallback onFailure: ", Integer.valueOf(i));
                kcy.this.c(i);
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.c("HwRriServiceMgr", "mEcgAnalysisFileCallback onProgress");
            }
        };
        LogUtil.a("HwRriServiceMgr", "HwRriServiceMgr Constructor");
        this.g = context;
        HwDeviceMgrInterface b2 = jsz.b(BaseApplication.getContext());
        this.p = b2;
        if (b2 == null) {
            LogUtil.h("HwRriServiceMgr", "mHwDeviceMgr is null");
        } else {
            i();
        }
    }

    private void i() {
        BroadcastManagerUtil.bFC_(this.g, this.i, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.g.unregisterReceiver(this.i);
        List<Object> list = this.k;
        if (list != null) {
            list.clear();
        }
    }

    public static kcy e(Context context) {
        kcy kcyVar;
        synchronized (f14296a) {
            if (j == null) {
                j = new kcy(BaseApplication.getContext());
            }
            kcyVar = j;
        }
        return kcyVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 35;
    }

    private void b(int i, int i2, String str) {
        if (i == 6) {
            IBaseResponseCallback iBaseResponseCallback = this.w;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(i2, str);
                return;
            }
            return;
        }
        if (i == 7) {
            IBaseResponseCallback iBaseResponseCallback2 = this.s;
            if (iBaseResponseCallback2 != null) {
                iBaseResponseCallback2.d(i2, str);
                return;
            }
            return;
        }
        LogUtil.h("HwRriServiceMgr", "procCallback error commandId");
    }

    public void e(String str, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (c) {
            if (iBaseResponseCallback != null) {
                if (this.p != null) {
                    DeviceInfo d2 = d();
                    if (d2 == null) {
                        ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgMeasureAuthAccountCommand get device info error");
                        iBaseResponseCallback.d(300004, null);
                        return;
                    } else {
                        this.w = iBaseResponseCallback;
                        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "setEcgMeasureAuthAccountCommand enter");
                        kcz.a(str, d2);
                        return;
                    }
                }
            }
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgMeasureAuthAccountCommand callback or mHwDeviceMgr is null");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.String] */
    private void m(byte[] bArr) {
        int i;
        int i2 = 201000;
        String str = null;
        try {
            i = 0;
            if (b(bArr) && bArr[2] == Byte.MAX_VALUE) {
                int a2 = kdb.a(bArr);
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "proSetEcgMeasureAuth return errorCode: ", Integer.valueOf(a2));
                i = a2;
                i2 = a2;
            } else {
                str = kdb.d(bArr);
                try {
                    LogUtil.a("HwRriServiceMgr", "proSetEcgMeasureAuth: ", str);
                    i2 = "HwRriServiceMgr";
                } catch (cwg e2) {
                    e = e2;
                    ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "proSetEcgMeasureAuth exception: ", ExceptionUtils.d(e));
                    b(6, i, str);
                }
            }
        } catch (cwg e3) {
            e = e3;
            i = i2;
        }
        b(6, i, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        int i = 0;
        this.f = 0;
        this.n = 0;
        this.h = 0;
        List<Object> list = this.k;
        if (list == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mEcgWholeFrameList is null");
            return;
        }
        list.clear();
        int i2 = 201000;
        try {
            if (b(bArr) && bArr[2] == Byte.MAX_VALUE) {
                int a2 = kdb.a(bArr);
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "proGetEcgFrameCount return errCode: ", Integer.valueOf(a2));
                i = a2;
                i2 = a2;
            } else {
                int c2 = kdb.c(bArr);
                this.n = c2;
                try {
                    Object[] objArr = {"proGetEcgFrameCount get ecg frame count: ", Integer.valueOf(c2)};
                    ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", objArr);
                    if (this.n > 0) {
                        kcz.b(this.h, deviceInfo);
                        this.h++;
                        i2 = objArr;
                    } else {
                        i = 600001;
                        i2 = objArr;
                    }
                } catch (cwg e2) {
                    e = e2;
                    ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "proGetEcgFrameCount exception: ", ExceptionUtils.d(e));
                    b(7, i, String.valueOf(this.n));
                }
            }
        } catch (cwg e3) {
            e = e3;
            i = i2;
        }
        b(7, i, String.valueOf(this.n));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent("com.huawei.bone.action.CORE_ECG_DATA_SYNC_COMPLETED");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("HwRriServiceMgr", "notifyNewEcgData");
        BaseApplication.getContext().sendBroadcast(new Intent("com.huawei.bone.action.NEW_ECG_DATA_RECEIVED"), LocalBroadcast.c);
    }

    public void b(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (c) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setAtrialSingleMeasure callback is null");
                return;
            }
            if (this.p == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setAtrialSingleMeasure mHwDeviceMgr is null");
                return;
            }
            DeviceInfo d2 = d();
            if (d2 != null && d2.getDeviceConnectState() == 2) {
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "setAtrialSingleMeasure openOrClose:", Integer.valueOf(i));
                c(3, iBaseResponseCallback);
                kcz.b(i, i2, d2);
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setAtrialSingleMeasure get device info error");
            iBaseResponseCallback.d(300004, null);
        }
    }

    public void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (c) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setAtrialAutoMeasure callback is null");
                return;
            }
            if (this.p == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setAtrialAutoMeasure mHwDeviceMgr is null");
                return;
            }
            DeviceInfo d2 = d();
            if (d2 != null && d2.getDeviceConnectState() == 2) {
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "setAtrialAutoMeasure send command");
                c(2, iBaseResponseCallback);
                kcz.c(i, d2);
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setAtrialAutoMeasure get device info error");
            iBaseResponseCallback.d(300004, null);
        }
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo deviceInfo;
        synchronized (c) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "clearAtrialData callback is null");
                return;
            }
            if (this.p == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "clearAtrialData mHwDeviceMgr is null");
                return;
            }
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwRriServiceMgr");
            if (deviceList.size() > 0) {
                Iterator<DeviceInfo> it = deviceList.iterator();
                while (it.hasNext()) {
                    deviceInfo = it.next();
                    if (!cvt.c(deviceInfo.getProductType())) {
                        break;
                    }
                }
            }
            deviceInfo = null;
            if (deviceInfo == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "clearAtrialData get device info error");
                iBaseResponseCallback.d(300004, null);
            } else {
                ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "clearAtrialData send command");
                c(4, iBaseResponseCallback);
                kcz.a(deviceInfo);
            }
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "registerSingleAtrialCallback callback is null");
                return;
            }
            List<IBaseResponseCallback> list = this.o;
            if (list == null) {
                ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "mDeviceAtrialReportCallbacks is null");
            } else {
                if (list.contains(iBaseResponseCallback)) {
                    return;
                }
                LogUtil.a("HwRriServiceMgr", "registerSingleAtrialCallback", iBaseResponseCallback);
                this.o.add(iBaseResponseCallback);
            }
        }
    }

    public void j(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            List<IBaseResponseCallback> list = this.o;
            if (list != null) {
                if (iBaseResponseCallback == null) {
                    ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "unregisterNotificationAtrialCallback callback is null");
                } else if (list.size() > 0) {
                    this.o.clear();
                    iBaseResponseCallback.d(0, "");
                } else {
                    iBaseResponseCallback.d(-1, "");
                }
            }
        }
    }

    private void i(byte[] bArr) {
        int i;
        try {
            i = kdb.a(bArr);
        } catch (cwg e2) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "proSetAtrialSingleMeasureCode exception: ", ExceptionUtils.d(e2));
            i = 201000;
        }
        d(3, i, "");
    }

    private void h(byte[] bArr) {
        int i;
        try {
            i = kdb.a(bArr);
        } catch (cwg e2) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "proSetAtrialAutoMeasureCode exception: ", ExceptionUtils.d(e2));
            i = 201000;
        }
        d(2, i, "");
    }

    private void e(byte[] bArr) {
        int i;
        try {
            i = kdb.a(bArr);
        } catch (cwg e2) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "proClearAtrialDataCode exception: ", ExceptionUtils.d(e2));
            i = 201000;
        }
        d(4, i, "");
    }

    private void t(byte[] bArr) {
        b(300, cvx.d(bArr));
    }

    private void d(byte[] bArr) {
        int i;
        if (bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
            try {
                i = kdb.a(bArr);
            } catch (cwg e2) {
                e = e2;
                i = 201000;
            }
            try {
                b(i, "");
                return;
            } catch (cwg e3) {
                e = e3;
                ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "proDeviceReportAtrialDataCode exception: ", ExceptionUtils.d(e));
                b(i, "");
                return;
            }
        }
        List<kde> c2 = c(bArr);
        if (c2 == null) {
            b(700001, "");
        } else {
            b(0, this.r.toJson(c2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, Object obj) {
        synchronized (b) {
            List<IBaseResponseCallback> list = this.o;
            if (list != null && list.size() != 0) {
                for (IBaseResponseCallback iBaseResponseCallback : this.o) {
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(i, obj);
                    }
                }
            }
        }
    }

    private List<kde> c(byte[] bArr) {
        ArrayList arrayList = new ArrayList(16);
        if (!cwl.b(bArr)) {
            String d2 = cvx.d(bArr);
            if (d2.length() > 4) {
                try {
                    d(arrayList, new cwl().a(d2.substring(4, d2.length())).a());
                } catch (cwg | NumberFormatException e2) {
                    ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "createSingleAtrialDataEntity exception: ", ExceptionUtils.d(e2));
                    return Collections.emptyList();
                }
            }
        }
        return arrayList;
    }

    private void d(List<kde> list, List<cwe> list2) {
        for (cwe cweVar : list2) {
            kde kdeVar = new kde();
            List<cwd> e2 = cweVar.e();
            List<Integer> arrayList = new ArrayList<>(16);
            List<Integer> arrayList2 = new ArrayList<>(16);
            long j2 = 0;
            for (cwd cwdVar : e2) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 3) {
                    j2 = Long.parseLong(cwdVar.c(), 16);
                } else if (w != 4) {
                    if (w == 5) {
                        if (cwdVar.c().length() < 8) {
                            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "ppg data error");
                        } else {
                            arrayList2 = e(arrayList2, cwdVar);
                        }
                    } else {
                        LogUtil.c("05", 1, "HwRriServiceMgr", "createAtrialData else");
                    }
                } else if (cwdVar.c().length() < 4) {
                    ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "acc data error");
                } else {
                    arrayList = c(arrayList, cwdVar);
                }
            }
            kdeVar.b(j2);
            kdeVar.b(arrayList);
            kdeVar.e(arrayList2);
            list.add(kdeVar);
        }
    }

    private List<Integer> e(List<Integer> list, cwd cwdVar) {
        int i = 0;
        while (i < cwdVar.c().length()) {
            int i2 = i + 8;
            list.add(Integer.valueOf((int) Long.parseLong(cwdVar.c().substring(i, i2), 16)));
            i = i2;
        }
        return list;
    }

    private List<Integer> c(List<Integer> list, cwd cwdVar) {
        for (int i = 0; i < cwdVar.c().length(); i += 4) {
            list.add(Integer.valueOf((short) (Long.parseLong(cwdVar.c().substring(i, r2), 16) & 65535)));
        }
        return list;
    }

    private void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = d.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(16);
                    d.put(Integer.valueOf(i), list);
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    private void d(int i, int i2, Object obj) {
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "procCallback callback commandId is ", Integer.valueOf(i), " errorCode is ", Integer.valueOf(i2));
        synchronized (e) {
            List<IBaseResponseCallback> list = d.get(Integer.valueOf(i));
            if (list != null) {
                int size = list.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    IBaseResponseCallback iBaseResponseCallback = list.get(size);
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(i2, obj);
                        list.remove(size);
                        break;
                    } else {
                        list.remove(size);
                        size--;
                    }
                }
            }
        }
    }

    public void c(IBaseResponseCallback iBaseResponseCallback, JSONObject jSONObject) {
        int i;
        String str;
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgAuthSwitch callback is null.");
            return;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgAuthSwitch json is null.");
            iBaseResponseCallback.d(100007, "json is empty.");
            return;
        }
        LogUtil.a("HwRriServiceMgr", "setEcgAuthSwitch json : ", jSONObject.toString());
        try {
            i = jSONObject.getInt("ecg_authorization_switch");
        } catch (JSONException unused) {
            i = -1;
        }
        try {
            str = jSONObject.getString("package_name");
        } catch (JSONException unused2) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "checkJson authSwitch JSONException");
            str = "";
            if (i != -1) {
            }
            iBaseResponseCallback.d(100007, "json is error.");
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgAuthSwitch json is error.");
        }
        if (i != -1 || TextUtils.isEmpty(str)) {
            iBaseResponseCallback.d(100007, "json is error.");
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgAuthSwitch json is error.");
        } else {
            ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "setEcgAuthSwitch enter authSwitch：", Integer.valueOf(i));
            c(11, iBaseResponseCallback);
            kcz.a(i, str);
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback, JSONObject jSONObject) {
        int i;
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setEcgAuthSwitch callback is null.");
            return;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setForbiddenSwitch json is null.");
            iBaseResponseCallback.d(100007, "json is null.");
            return;
        }
        LogUtil.a("HwRriServiceMgr", "setForbiddenSwitch json : ", jSONObject.toString());
        try {
            i = jSONObject.getInt("ecg_forbidden_switch");
        } catch (JSONException unused) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "checkJson authSwitch JSONException");
            i = -1;
        }
        if (i == -1) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "setForbiddenSwitch json is error.");
            iBaseResponseCallback.d(100007, "json is error.");
        } else {
            ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "setForbiddenSwitch enter");
            c(12, iBaseResponseCallback);
            kcz.d(i);
        }
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "getAuthSwitch callback is null.");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "enter getAuthSwitch");
        c(14, iBaseResponseCallback);
        kcz.d();
    }

    public void a(String str, final IBaseCallback iBaseCallback) {
        if (iBaseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "sendBlockList callback is null.");
            return;
        }
        try {
            b(new JSONObject(str), new IBaseResponseCallback() { // from class: kcy.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        if (obj instanceof String) {
                            iBaseCallback.onResponse(i, (String) obj);
                        } else {
                            iBaseCallback.onResponse(i, "");
                        }
                    } catch (RemoteException unused) {
                        ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "sendEcgBlockList RemoteException");
                    }
                }
            });
        } catch (JSONException unused) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "sendEcgBlockList json exception.");
            try {
                iBaseCallback.onResponse(100007, "");
            } catch (RemoteException unused2) {
                ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "JSONException sendEcgBlockList RemoteException");
            }
        }
    }

    public void b(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwRriServiceMgr", "5.35.15 sendBlockList enter.");
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "callback is null");
            return;
        }
        if (jSONObject == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "json object is null.");
            iBaseResponseCallback.d(100007, "");
            return;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("list");
            if (jSONArray != null && jSONArray.length() != 0) {
                this.t = jSONArray;
                c(15, iBaseResponseCallback);
                j();
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "readySendList is null or length is 0");
            iBaseResponseCallback.d(100007, "");
        } catch (JSONException unused) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "sendBlockList JSONException");
            iBaseResponseCallback.d(100007, "");
        }
    }

    private void j() {
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "sendRemainingBlockList enter");
        JSONArray jSONArray = this.t;
        if (jSONArray != null && jSONArray.length() != 0) {
            Object remove = jSONArray.remove(0);
            JSONArray jSONArray2 = new JSONArray();
            boolean z = jSONArray.length() == 0;
            jSONArray2.put(remove);
            try {
                kcz.e(jSONArray2, z);
                return;
            } catch (JSONException unused) {
                ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "sendRemainingBlockList JSONException");
                j();
                return;
            }
        }
        ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "sendRemainingBlockList blockArray is error");
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "getSwitchStatus callback is null");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "enter getSwitchStatus");
        c(9, iBaseResponseCallback);
        kcz.c();
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "getEcgServiceIv callback is null.");
            return;
        }
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "enter getEcgServiceIv");
        c(17, iBaseResponseCallback);
        kcz.b();
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 2) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "onResponse error data");
            return;
        }
        blt.d("HwRriServiceMgr", bArr, "onResponse receive bt dataInfos: ");
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "getResult commandId:", Byte.valueOf(bArr[1]));
        switch (bArr[1]) {
            case 2:
                h(bArr);
                break;
            case 3:
                i(bArr);
                break;
            case 4:
                e(bArr);
                break;
            case 5:
                d(bArr);
                break;
            case 6:
                m(bArr);
                break;
            case 7:
                b(bArr, deviceInfo);
                break;
            case 8:
            case 9:
            default:
                a(bArr);
                break;
            case 10:
                t(bArr);
                break;
            case 11:
                g(bArr);
                break;
            case 12:
                n(bArr);
                break;
            case 13:
                j(bArr);
                break;
            case 14:
                f(bArr);
                break;
        }
    }

    private void a(byte[] bArr) {
        byte b2 = bArr[1];
        if (b2 == 9) {
            k(bArr);
            return;
        }
        if (b2 == 15) {
            o(bArr);
        } else if (b2 == 17) {
            l(bArr);
        } else {
            LogUtil.h("HwRriServiceMgr", "onResponse recv bt error data");
        }
    }

    private void l(byte[] bArr) {
        String str;
        List<cwd> e2;
        String str2 = "";
        try {
            e2 = new cwl().a(cvx.d(bArr).substring(4)).e();
        } catch (cwg unused) {
            str = "";
        }
        if (e2 != null && !e2.isEmpty()) {
            String str3 = "";
            for (cwd cwdVar : e2) {
                try {
                    int w = CommonUtil.w(cwdVar.e());
                    if (w == 1) {
                        str2 = cwdVar.c();
                    } else if (w != 2) {
                        if (w == 127) {
                            ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "processGetEcgServiceIv has error code : ", Integer.valueOf(CommonUtil.w(cwdVar.c())));
                            return;
                        }
                        LogUtil.c("HwRriServiceMgr", "parseEcgIVTlv receive other type", Integer.valueOf(w));
                    } else {
                        str3 = cwdVar.c();
                    }
                } catch (cwg unused2) {
                    str = str2;
                    str2 = str3;
                    LogUtil.b("HwRriServiceMgr", "parseEcgIVTlv TlvException");
                    str3 = str2;
                    str2 = str;
                    a(str2, str3);
                    return;
                }
            }
            LogUtil.a("HwRriServiceMgr", "parseEcgIVTlv ecgIv = ", str2, ", ecgGenerate = ", str3);
            a(str2, str3);
            return;
        }
        ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "Tlv is error.");
    }

    private void a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                jSONObject.put("ecgServiceIv", str);
                jSONObject.put("ecgServiceIvPsd", str2);
            } catch (JSONException unused) {
                ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "processGetSwitchStatus JSONException");
            }
        }
        synchronized (e) {
            List<IBaseResponseCallback> list = d.get(17);
            if (list != null && !list.isEmpty()) {
                if (TextUtils.isEmpty(jSONObject.toString())) {
                    d(17, -1, "json put is error");
                    return;
                } else {
                    d(17, 0, jSONObject.toString());
                    return;
                }
            }
            Intent intent = new Intent("com.huawei.health.action.ecgiv.received");
            intent.putExtra("dataIv", jSONObject.toString());
            BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
            ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "processGetEcgServiceIv sendBroadcast");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void k(byte[] r10) {
        /*
            r9 = this;
            java.lang.String r0 = "DEVMGR_HwRriServiceMgr"
            java.lang.String r10 = defpackage.cvx.d(r10)
            r1 = 4
            java.lang.String r10 = r10.substring(r1)
            r1 = 0
            r2 = 9
            r3 = -1
            cwl r4 = new cwl     // Catch: defpackage.cwg -> L75
            r4.<init>()     // Catch: defpackage.cwg -> L75
            cwe r10 = r4.a(r10)     // Catch: defpackage.cwg -> L75
            java.util.List r10 = r10.e()     // Catch: defpackage.cwg -> L75
            r4 = 1
            if (r10 == 0) goto L6a
            int r5 = r10.size()     // Catch: defpackage.cwg -> L75
            if (r5 != 0) goto L26
            goto L6a
        L26:
            java.util.Iterator r10 = r10.iterator()     // Catch: defpackage.cwg -> L75
            r5 = r3
        L2b:
            boolean r6 = r10.hasNext()     // Catch: defpackage.cwg -> L68
            if (r6 == 0) goto L84
            java.lang.Object r6 = r10.next()     // Catch: defpackage.cwg -> L68
            cwd r6 = (defpackage.cwd) r6     // Catch: defpackage.cwg -> L68
            java.lang.String r7 = r6.e()     // Catch: defpackage.cwg -> L68
            int r7 = health.compact.a.CommonUtil.w(r7)     // Catch: defpackage.cwg -> L68
            if (r7 == r4) goto L5f
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 == r8) goto L51
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L68
            java.lang.String r7 = "no support Tag"
            r6[r1] = r7     // Catch: defpackage.cwg -> L68
            java.lang.String r7 = "HwRriServiceMgr"
            com.huawei.hwlogsmodel.LogUtil.c(r7, r6)     // Catch: defpackage.cwg -> L68
            goto L2b
        L51:
            java.lang.String r10 = r6.c()     // Catch: defpackage.cwg -> L68
            int r10 = health.compact.a.CommonUtil.w(r10)     // Catch: defpackage.cwg -> L68
            java.lang.String r4 = "5.35.9 tlv send errorCode"
            r9.d(r2, r10, r4)     // Catch: defpackage.cwg -> L68
            return
        L5f:
            java.lang.String r6 = r6.c()     // Catch: defpackage.cwg -> L68
            int r5 = health.compact.a.CommonUtil.w(r6)     // Catch: defpackage.cwg -> L68
            goto L2b
        L68:
            r10 = move-exception
            goto L77
        L6a:
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L75
            java.lang.String r4 = "tlvList is error"
            r10[r1] = r4     // Catch: defpackage.cwg -> L75
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r10)     // Catch: defpackage.cwg -> L75
            return
        L75:
            r10 = move-exception
            r5 = r3
        L77:
            java.lang.String r4 = "processGetSwitchStatus tlv exception: "
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r4, r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r10)
        L84:
            java.lang.String r10 = "processGetSwitchStatus bitmap:"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r10)
            if (r5 != r3) goto L99
            java.lang.String r10 = "device send tlv is wrong"
            r9.d(r2, r3, r10)
            goto Lc4
        L99:
            org.json.JSONObject r10 = new org.json.JSONObject
            r10.<init>()
            java.lang.String r4 = "bitmap"
            r10.put(r4, r5)     // Catch: org.json.JSONException -> La4
            goto Lad
        La4:
            java.lang.String r4 = "processGetSwitchStatus JSONException"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r4)
        Lad:
            java.lang.String r0 = r10.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Lbd
            java.lang.String r10 = "json put is error"
            r9.d(r2, r3, r10)
            goto Lc4
        Lbd:
            java.lang.String r10 = r10.toString()
            r9.d(r2, r1, r10)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kcy.k(byte[]):void");
    }

    private void o(byte[] bArr) {
        try {
            List<cwd> e2 = new cwl().a(cvx.d(bArr).substring(4)).e();
            if (e2 != null && e2.size() != 0) {
                for (cwd cwdVar : e2) {
                    if (CommonUtil.w(cwdVar.e()) == 127) {
                        int w = CommonUtil.w(cwdVar.c());
                        LogUtil.c("HwRriServiceMgr", "5.35.15 errorCode is ", Integer.valueOf(w));
                        d(15, w, "5.35.15 tlv send errorCode");
                        j();
                        return;
                    }
                    LogUtil.c("HwRriServiceMgr", "no support Tag");
                }
                return;
            }
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "processSendBlockList tlvList is error");
        } catch (cwg unused) {
            ReleaseLogUtil.c("DEVMGR_HwRriServiceMgr", "processSendBlockList TlvException");
        }
    }

    private boolean b(byte[] bArr) {
        return bArr.length >= 3;
    }

    private void g(byte[] bArr) {
        int i;
        try {
            i = kdb.a(bArr);
        } catch (cwg e2) {
            LogUtil.b("HwRriServiceMgr", "proSetEcgAuthSwitch exception: ", e2.getMessage());
            i = 201000;
        }
        d(11, i, "");
    }

    private void n(byte[] bArr) {
        int i;
        try {
            i = kdb.a(bArr);
        } catch (cwg e2) {
            LogUtil.b("HwRriServiceMgr", "proSetEcgAuthSwitch exception: ", e2.getMessage());
            i = 201000;
        }
        d(12, i, "");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j(byte[] r10) {
        /*
            r9 = this;
            java.lang.String r0 = "HwRriServiceMgr"
            java.lang.String r1 = "DEVMGR_HwRriServiceMgr"
            java.lang.String r10 = defpackage.cvx.d(r10)
            r2 = 4
            java.lang.String r10 = r10.substring(r2)
            r2 = -1
            cwl r3 = new cwl     // Catch: defpackage.cwg -> L7e
            r3.<init>()     // Catch: defpackage.cwg -> L7e
            cwe r10 = r3.a(r10)     // Catch: defpackage.cwg -> L7e
            java.util.List r10 = r10.e()     // Catch: defpackage.cwg -> L7e
            r3 = 0
            r4 = 1
            if (r10 == 0) goto L73
            int r5 = r10.size()     // Catch: defpackage.cwg -> L7e
            if (r5 != 0) goto L26
            goto L73
        L26:
            java.util.Iterator r10 = r10.iterator()     // Catch: defpackage.cwg -> L7e
            r5 = r2
        L2b:
            boolean r6 = r10.hasNext()     // Catch: defpackage.cwg -> L71
            if (r6 == 0) goto L8d
            java.lang.Object r6 = r10.next()     // Catch: defpackage.cwg -> L71
            cwd r6 = (defpackage.cwd) r6     // Catch: defpackage.cwg -> L71
            java.lang.String r7 = r6.e()     // Catch: defpackage.cwg -> L71
            int r7 = health.compact.a.CommonUtil.w(r7)     // Catch: defpackage.cwg -> L71
            if (r7 == r4) goto L68
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 == r8) goto L4f
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L71
            java.lang.String r7 = "no support tag"
            r6[r3] = r7     // Catch: defpackage.cwg -> L71
            com.huawei.hwlogsmodel.LogUtil.c(r0, r6)     // Catch: defpackage.cwg -> L71
            goto L2b
        L4f:
            java.lang.String r10 = r6.c()     // Catch: defpackage.cwg -> L71
            int r10 = health.compact.a.CommonUtil.w(r10)     // Catch: defpackage.cwg -> L71
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: defpackage.cwg -> L71
            java.lang.String r7 = "5.35.13 error code is "
            r6[r3] = r7     // Catch: defpackage.cwg -> L71
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch: defpackage.cwg -> L71
            r6[r4] = r10     // Catch: defpackage.cwg -> L71
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r6)     // Catch: defpackage.cwg -> L71
            return
        L68:
            java.lang.String r6 = r6.c()     // Catch: defpackage.cwg -> L71
            int r5 = health.compact.a.CommonUtil.w(r6)     // Catch: defpackage.cwg -> L71
            goto L2b
        L71:
            r10 = move-exception
            goto L80
        L73:
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L7e
            java.lang.String r4 = "tlv is error."
            r10[r3] = r4     // Catch: defpackage.cwg -> L7e
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r10)     // Catch: defpackage.cwg -> L7e
            return
        L7e:
            r10 = move-exception
            r5 = r2
        L80:
            java.lang.String r3 = "proEcgAction tlv exception: "
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r3, r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r10)
        L8d:
            java.lang.String r10 = "proEcgAction bitmap:"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r10)
            if (r5 == r2) goto Lbb
            boolean r10 = r9.b(r5)
            if (r10 == 0) goto Lab
            ket r10 = defpackage.ket.a()
            java.lang.String r0 = "ECG_SYNC_TASK"
            r10.b(r0)
        Lab:
            boolean r10 = r9.e(r5)
            if (r10 == 0) goto Lc4
            ket r10 = defpackage.ket.a()
            java.lang.String r0 = "ECG_ANALY_SYNC_TASK"
            r10.b(r0)
            goto Lc4
        Lbb:
            java.lang.String r10 = "5.35.13 tlv is error."
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r10)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kcy.j(byte[]):void");
    }

    public void i(IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo currentDeviceInfo = HwExerciseDeviceUtil.getCurrentDeviceInfo();
        if (currentDeviceInfo == null || currentDeviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "startFileTask no device connect.");
            iBaseResponseCallback.d(-1, "");
            return;
        }
        int[] iArr = {kcv.a(), (int) (System.currentTimeMillis() / 1000)};
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "startFileTask last time: ", Integer.valueOf(iArr[0]), ", now time: ", Integer.valueOf(iArr[1]));
        if (iArr[0] > iArr[1]) {
            LogUtil.h("HwRriServiceMgr", "wrong, start time > end time");
            int i = iArr[1] - 61;
            if (i > 0) {
                iArr[0] = i;
            } else {
                iArr[0] = 0;
            }
            kcv.d(iArr[0]);
        }
        this.q = iBaseResponseCallback;
        jqj jqjVar = new jqj();
        jqjVar.a("ecg_data_file");
        jqjVar.d(8);
        jqjVar.a(false);
        jqjVar.e(iArr);
        jqjVar.c((String) null);
        jyp.c().b(jqjVar, this.l);
    }

    public void f(IBaseResponseCallback iBaseResponseCallback) {
        DeviceInfo currentDeviceInfo = HwExerciseDeviceUtil.getCurrentDeviceInfo();
        if (currentDeviceInfo == null || currentDeviceInfo.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "startEcgAnaFileTask no device connect.");
            iBaseResponseCallback.d(-1, "");
            return;
        }
        int[] iArr = {kcv.c(), (int) (System.currentTimeMillis() / 1000)};
        ReleaseLogUtil.e("DEVMGR_HwRriServiceMgr", "startEcgAnaFileTask last time: ", Integer.valueOf(iArr[0]), ", now time: ", Integer.valueOf(iArr[1]));
        if (iArr[0] > iArr[1]) {
            LogUtil.h("HwRriServiceMgr", "wrong, start time > end time");
            int i = iArr[1] - 61;
            if (i > 0) {
                iArr[0] = i;
            } else {
                iArr[0] = 0;
            }
            kcv.c(iArr[0]);
        }
        this.q = iBaseResponseCallback;
        jqj jqjVar = new jqj();
        jqjVar.a("ecg_analysis_data.bin");
        jqjVar.d(24);
        jqjVar.a(false);
        jqjVar.e(iArr);
        jqjVar.c((String) null);
        jyp.c().b(jqjVar, this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        IBaseResponseCallback iBaseResponseCallback = this.q;
        this.q = null;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, "");
        } else {
            ReleaseLogUtil.d("DEVMGR_HwRriServiceMgr", "callback is null.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f(byte[] r10) {
        /*
            r9 = this;
            java.lang.String r0 = "DEVMGR_HwRriServiceMgr"
            java.lang.String r10 = defpackage.cvx.d(r10)
            r1 = 4
            java.lang.String r10 = r10.substring(r1)
            r1 = 0
            r2 = 14
            r3 = -1
            cwl r4 = new cwl     // Catch: defpackage.cwg -> L75
            r4.<init>()     // Catch: defpackage.cwg -> L75
            cwe r10 = r4.a(r10)     // Catch: defpackage.cwg -> L75
            java.util.List r10 = r10.e()     // Catch: defpackage.cwg -> L75
            r4 = 1
            if (r10 == 0) goto L6a
            int r5 = r10.size()     // Catch: defpackage.cwg -> L75
            if (r5 != 0) goto L26
            goto L6a
        L26:
            java.util.Iterator r10 = r10.iterator()     // Catch: defpackage.cwg -> L75
            r5 = r3
        L2b:
            boolean r6 = r10.hasNext()     // Catch: defpackage.cwg -> L68
            if (r6 == 0) goto L84
            java.lang.Object r6 = r10.next()     // Catch: defpackage.cwg -> L68
            cwd r6 = (defpackage.cwd) r6     // Catch: defpackage.cwg -> L68
            java.lang.String r7 = r6.e()     // Catch: defpackage.cwg -> L68
            int r7 = health.compact.a.CommonUtil.w(r7)     // Catch: defpackage.cwg -> L68
            if (r7 == r4) goto L5f
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 == r8) goto L51
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L68
            java.lang.String r7 = "no support tag"
            r6[r1] = r7     // Catch: defpackage.cwg -> L68
            java.lang.String r7 = "HwRriServiceMgr"
            com.huawei.hwlogsmodel.LogUtil.c(r7, r6)     // Catch: defpackage.cwg -> L68
            goto L2b
        L51:
            java.lang.String r10 = r6.c()     // Catch: defpackage.cwg -> L68
            int r10 = health.compact.a.CommonUtil.w(r10)     // Catch: defpackage.cwg -> L68
            java.lang.String r4 = "5.35.14 tlv send error code."
            r9.d(r2, r10, r4)     // Catch: defpackage.cwg -> L68
            return
        L5f:
            java.lang.String r6 = r6.c()     // Catch: defpackage.cwg -> L68
            int r5 = health.compact.a.CommonUtil.w(r6)     // Catch: defpackage.cwg -> L68
            goto L2b
        L68:
            r10 = move-exception
            goto L77
        L6a:
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L75
            java.lang.String r4 = "tlv is error."
            r10[r1] = r4     // Catch: defpackage.cwg -> L75
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r0, r10)     // Catch: defpackage.cwg -> L75
            return
        L75:
            r10 = move-exception
            r5 = r3
        L77:
            java.lang.String r4 = "proEcgAction tlv exception:"
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r4, r10}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r10)
        L84:
            java.lang.String r10 = "proGetEcgAuthSwitch bitmap:"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r10)
            if (r5 != r3) goto L99
            java.lang.String r10 = "device send tlv is wrong."
            r9.d(r2, r3, r10)
            goto Lc4
        L99:
            org.json.JSONObject r10 = new org.json.JSONObject
            r10.<init>()
            java.lang.String r4 = "bitmap"
            r10.put(r4, r5)     // Catch: org.json.JSONException -> La4
            goto Lad
        La4:
            java.lang.String r4 = "proGetEcgAuthSwitch JSONException"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r4)
        Lad:
            java.lang.String r0 = r10.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto Lbd
            java.lang.String r10 = "json put is error."
            r9.d(r2, r3, r10)
            goto Lc4
        Lbd:
            java.lang.String r10 = r10.toString()
            r9.d(r2, r1, r10)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kcy.f(byte[]):void");
    }

    private DeviceInfo d() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwRriServiceMgr");
        if (deviceList == null || deviceList.size() <= 0) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                return deviceInfo;
            }
        }
        return null;
    }
}

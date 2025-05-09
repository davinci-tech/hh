package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.WatchFaceInfo;
import com.huawei.hwcommonmodel.datatypes.WatchResourcesInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwpaymgr.HwPayManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.utils.callback.WatchFaceHealthResponseListener;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes5.dex */
public class jlo extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static volatile jlo f;
    private int g;
    private Context h;
    private jfq i;
    private final BroadcastReceiver k;
    private ArrayList<WatchResourcesInfo> l;
    private boolean m;
    private int n;
    private WatchFaceHealthResponseListener o;
    private WatchFaceInfo p;
    private ArrayList<WatchResourcesInfo> q;
    private cwl r;
    private jlq s;
    private Map<String, String> t;
    private static final List<String> c = Arrays.asList("2174240537", "2174240579");

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f13941a = Arrays.asList("Phoinix-B19F", "Phoinix-B19L", "Phoinix-B19W", "Phoinix-B19M", "Phoinix-B19F2B");
    private static final Object d = new Object();
    private static final Object b = new Object();
    private static Map<Integer, List<IBaseResponseCallback>> j = new HashMap(20);
    private static IBaseResponseCallback e = null;

    private jlo(Context context) {
        super(context);
        this.t = new HashMap(0);
        this.r = new cwl();
        this.p = new WatchFaceInfo();
        this.s = new jlq();
        this.m = false;
        this.g = 0;
        this.q = new ArrayList<>(20);
        this.l = new ArrayList<>(20);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: jlo.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    ReleaseLogUtil.d("R_HwWatchBtFaceManager", "mConnectStateChangedReceiver intent is null");
                    return;
                }
                LogUtil.a("HwWatchBtFaceManager", "connectedChanged mNonLocalBroadcastReceiver: intent : ", intent.getAction());
                String action = intent.getAction();
                if (action == null) {
                    return;
                }
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                    try {
                        Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                        if (!(parcelableExtra instanceof DeviceInfo)) {
                            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "deviceInfo is null!");
                            return;
                        }
                        DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                        if (deviceInfo.getRelationship() != null && "followed_relationship".equals(deviceInfo.getRelationship())) {
                            ReleaseLogUtil.e("R_HwWatchBtFaceManager", "This device does not have the correspond capability.");
                            return;
                        }
                        int deviceConnectState = deviceInfo.getDeviceConnectState();
                        if (deviceConnectState == 2) {
                            jlo.this.b(deviceInfo);
                        }
                        if (jlo.this.o != null) {
                            ReleaseLogUtil.e("R_HwWatchBtFaceManager", "connectedChanged response to aar,state:", Integer.valueOf(deviceConnectState));
                            jlo.this.o.onResponse(2, deviceConnectState, Integer.valueOf(deviceInfo.getProductType()));
                        }
                        if (deviceConnectState == 2) {
                            ReleaseLogUtil.e("HwWatchBtFaceManager", "mNonLocalBroadcastReceiver is connect, clear mWatchFaceDeviceMap.");
                            jlo.this.t.clear();
                            return;
                        } else {
                            LogUtil.h("HwWatchBtFaceManager", "onConnectStateChange state is not connect");
                            return;
                        }
                    } catch (BadParcelableException e2) {
                        ReleaseLogUtil.c("R_HwWatchBtFaceManager", "onReceive() BadParcelableException:", ExceptionUtils.d(e2));
                        return;
                    } catch (RuntimeException e3) {
                        ReleaseLogUtil.c("R_HwWatchBtFaceManager", "RuntimeException:", ExceptionUtils.d(e3));
                        return;
                    }
                }
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "deviceInfo is null!");
            }
        };
        this.k = broadcastReceiver;
        this.h = context;
        this.i = jfq.c();
        jfs.d().b("HwWatchBtFaceManager", this);
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "HwWatchBtFaceManager registerManagerCallback");
        BroadcastManagerUtil.bFC_(this.h, broadcastReceiver, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    public static jlo d(Context context) {
        jlo jloVar;
        synchronized (b) {
            if (f == null && context != null) {
                LogUtil.a("HwWatchBtFaceManager", "getInstance() context:", context);
                f = new jlo(BaseApplication.getContext());
            }
            jloVar = f;
        }
        return jloVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo) {
        LogUtil.a("HwWatchBtFaceManager", "searchWatchFace to adapter wrong watch face deviceInfo.getProductType() : ", Integer.valueOf(deviceInfo.getProductType()));
        if (deviceInfo.getProductType() == 72) {
            String softVersion = deviceInfo.getSoftVersion();
            LogUtil.a("HwWatchBtFaceManager", "deviceInfo.getSoftVersion() : ", deviceInfo.getSoftVersion());
            if (TextUtils.isEmpty(softVersion)) {
                return;
            }
            if (softVersion.startsWith("4.0.0.68") || softVersion.startsWith("4.0.0.65")) {
                String deviceModel = deviceInfo.getDeviceModel();
                LogUtil.a("HwWatchBtFaceManager", "deviceInfo.getDeviceModel() : ", deviceModel);
                if (f13941a.contains(deviceModel)) {
                    this.m = true;
                    this.g = 1;
                    g();
                }
            }
        }
    }

    private void l() {
        WatchResourcesInfo watchResourcesInfo;
        boolean z;
        String str;
        if (this.g != 1) {
            return;
        }
        this.g = 0;
        ArrayList<WatchResourcesInfo> arrayList = this.q;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        LogUtil.a("HwWatchBtFaceManager", "ready adapter wrong watch face : ", Integer.valueOf(arrayList.size()));
        Iterator<WatchResourcesInfo> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                watchResourcesInfo = null;
                z = false;
                str = null;
                break;
            } else {
                watchResourcesInfo = it.next();
                if (c.contains(watchResourcesInfo.getWatchInfoId())) {
                    z = (watchResourcesInfo.getWatchInfoType() & 1) == 1;
                    str = watchResourcesInfo.getWatchInfoVersion();
                }
            }
        }
        if (str == null) {
            return;
        }
        if (z) {
            LogUtil.a("HwWatchBtFaceManager", "no deal.");
            return;
        }
        LogUtil.a("HwWatchBtFaceManager", "delete wrong watchFace.");
        if (this.m) {
            LogUtil.a("HwWatchBtFaceManager", "lowestValidVersion : ", "2.1.1");
            if ("2.1.1".compareTo(str) >= 0) {
                e(watchResourcesInfo, 2);
            } else {
                LogUtil.a("HwWatchBtFaceManager", "valid watch face, no operation.");
            }
        }
    }

    private static Object i() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (jlo.class) {
            map = j;
        }
        return map;
    }

    private static IBaseResponseCallback h() {
        return e;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 39;
    }

    private void c(byte[] bArr) {
        LogUtil.a("HwWatchBtFaceManager", "getResult():", cvx.d(bArr));
        if (bArr == null || bArr.length <= 1) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "data illegal");
            return;
        }
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "getResult() id commandData 1:", Byte.valueOf(bArr[1]));
        switch (bArr[1]) {
            case 1:
                b(bArr);
                break;
            case 2:
                d(bArr);
                break;
            case 3:
                a(bArr);
                break;
            case 4:
                e(bArr, 4);
                break;
            case 5:
                f(bArr);
                break;
            case 6:
                e(bArr);
                break;
            case 7:
            default:
                LogUtil.h("HwWatchBtFaceManager", "getResult nothing to do");
                break;
            case 8:
                j(bArr);
                break;
            case 9:
                h(bArr);
                break;
            case 10:
                g(bArr);
                break;
        }
    }

    private void b(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.1 handleDeviceInfo ", d2);
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "5.39.1 handleDeviceInfo is ok.");
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleDeviceInfo data is error");
            return;
        }
        try {
            List<cwd> e2 = this.r.a(d2.substring(4)).e();
            if (e2 != null && !e2.isEmpty()) {
                for (cwd cwdVar : e2) {
                    e(CommonUtil.a(cwdVar.e(), 16), cwdVar.c());
                }
                k();
                return;
            }
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleDeviceInfo tlv error");
        } catch (cwg e3) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleDeviceInfo error:", ExceptionUtils.d(e3));
        }
    }

    private void e(int i, String str) {
        this.p.setOtherWatchFaceVersion("");
        switch (i) {
            case 1:
                this.p.setWatchFaceMaxVersion(cvx.e(str));
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleDeviceInfo setWatchFace_MaxVersion:", this.p.getWatchFaceMaxVersion());
                break;
            case 2:
                this.p.setWatchFaceWidth(CommonUtil.a(str, 16));
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleDeviceInfo setWatchFace_Width:", Integer.valueOf(this.p.getWatchFaceWidth()));
                break;
            case 3:
                this.p.setWatchFaceHeight(CommonUtil.a(str, 16));
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleDeviceInfo setWatchFace_Height:", Integer.valueOf(CommonUtil.a(str, 16)));
                break;
            case 4:
                int a2 = CommonUtil.a(str, 16);
                ArrayList arrayList = new ArrayList(20);
                if ((a2 & 1) == 1) {
                    arrayList.add(1);
                }
                this.p.setWatchFaceSupportFileType(arrayList);
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleDeviceInfo setWatchFace_SupportFileType:", this.p.getWatchFaceSupportFileType());
                break;
            case 5:
                this.p.setWatchFaceSort(CommonUtil.a(str, 16) == 1);
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleDeviceInfo setWatchFace_Sort:", Boolean.valueOf(this.p.isWatchFaceSort()));
                break;
            case 6:
                this.p.setOtherWatchFaceVersion(cvx.e(str));
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleDeviceInfo OtherThemeVersion:", this.p.getOtherWatchFaceVersion());
                break;
            default:
                LogUtil.h("HwWatchBtFaceManager", "handleDeviceInfoTlv default type:", Integer.valueOf(i));
                break;
        }
    }

    private void k() {
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(1);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(101, null);
                }
                j.remove(1);
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleDeviceInfo callback error");
            }
        }
    }

    private void d(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.2 handleFaceInfo:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleFaceInfo data is error");
            return;
        }
        this.l.clear();
        try {
            cwe cweVar = this.r.a(d2.substring(4)).a().get(0);
            LogUtil.a("HwWatchBtFaceManager", "getTlvfathers size:", Integer.valueOf(cweVar.a().size()));
            for (cwe cweVar2 : cweVar.a()) {
                WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
                for (cwd cwdVar : cweVar2.e()) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    if (a2 == 3) {
                        watchResourcesInfo.setWatchInfoId(cvx.e(cwdVar.c()));
                        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleFaceInfo setWatchInfo_Id:", watchResourcesInfo.getWatchInfoId());
                    } else if (a2 == 4) {
                        watchResourcesInfo.setWatchInfoVersion(cvx.e(cwdVar.c()));
                        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleFaceInfo setWatchInfo_Version:", watchResourcesInfo.getWatchInfoVersion());
                    } else if (a2 == 5) {
                        watchResourcesInfo.setWatchInfoType(CommonUtil.a(cwdVar.c(), 16));
                        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handleFaceInfo setWatchInfo_Type:", Integer.valueOf(watchResourcesInfo.getWatchInfoType()));
                    } else {
                        LogUtil.h("HwWatchBtFaceManager", "handleFaceInfo, nothing to do");
                    }
                }
                this.l.add(watchResourcesInfo);
            }
            m();
            l();
            o();
        } catch (cwg e2) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleFaceInfo TlvException:", ExceptionUtils.d(e2));
        } catch (IndexOutOfBoundsException e3) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleFaceInfo IndexOutOfBoundsException:", ExceptionUtils.d(e3));
        }
    }

    private void m() {
        synchronized (d) {
            this.q.clear();
            this.q.addAll(this.l);
            LogUtil.a("HwWatchBtFaceManager", "handleFaceInfo End, watchFace size:", Integer.valueOf(this.q.size()));
        }
    }

    private void o() {
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(2);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(101, null);
                }
                j.remove(2);
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleFaceInfo callback error");
            }
        }
    }

    private void a(byte[] bArr) {
        boolean z;
        int i;
        List<cwd> e2;
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.3 handleApply:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleApply data is error");
            return;
        }
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        boolean z2 = false;
        try {
            e2 = this.r.a(d2.substring(4)).e();
        } catch (cwg e3) {
            e = e3;
            z = false;
        }
        if (e2 != null && !e2.isEmpty()) {
            z = false;
            i = 0;
            for (cwd cwdVar : e2) {
                try {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    if (a2 == 1) {
                        watchResourcesInfo.setWatchInfoId(cvx.e(c2));
                        LogUtil.a("HwWatchBtFaceManager", "handleApply setWatchInfo_Id:", watchResourcesInfo.getWatchInfoId());
                    } else if (a2 == 2) {
                        watchResourcesInfo.setWatchInfoVersion(cvx.e(c2));
                        LogUtil.a("HwWatchBtFaceManager", "handleApply setWatchInfo_Version:", watchResourcesInfo.getWatchInfoVersion());
                    } else if (a2 == 4) {
                        z = CommonUtil.a(c2, 16) == 1;
                        LogUtil.a("HwWatchBtFaceManager", "handleApply isNeedTransfer:", Boolean.valueOf(z));
                    } else if (a2 == 127) {
                        i = CommonUtil.a(c2, 16);
                        LogUtil.a("HwWatchBtFaceManager", "handleApply errorCode:", Integer.valueOf(i));
                    } else {
                        LogUtil.h("HwWatchBtFaceManager", "handleApply, nothing to do");
                    }
                } catch (cwg e4) {
                    e = e4;
                    z2 = i;
                    ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleApply TlvException:", ExceptionUtils.d(e));
                    i = z2;
                    z2 = z;
                    a(z2, i, watchResourcesInfo.getWatchInfoId() + "_" + watchResourcesInfo.getWatchInfoVersion());
                }
            }
            z2 = z;
            a(z2, i, watchResourcesInfo.getWatchInfoId() + "_" + watchResourcesInfo.getWatchInfoVersion());
        }
        ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleApply tlv error");
        i = 0;
        a(z2, i, watchResourcesInfo.getWatchInfoId() + "_" + watchResourcesInfo.getWatchInfoVersion());
    }

    private void a(boolean z, int i, String str) {
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(3);
            if (list != null && !list.isEmpty()) {
                LogUtil.h("HwWatchBtFaceManager", "reportForUi, callbackList isn't null");
                j.remove(3);
                if (i != 0) {
                    list.get(list.size() - 1).d(104, str);
                } else if (z) {
                    list.get(list.size() - 1).d(105, str);
                } else {
                    list.get(list.size() - 1).d(103, str);
                }
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleApply callback error");
            }
        }
    }

    private void e(byte[] bArr, int i) {
        int i2;
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "handleErrorCode, info:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleErrorCode data is error");
            return;
        }
        try {
            i2 = CommonUtil.a(this.r.a(d2.substring(4)).e().get(0).c(), 16);
        } catch (cwg e2) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleErrorCode TlvException:", ExceptionUtils.d(e2));
            i2 = 0;
        }
        LogUtil.a("HwWatchBtFaceManager", "handleErrorCode:", Integer.valueOf(i2));
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(Integer.valueOf(i));
            if (list != null && !list.isEmpty()) {
                if (i2 == 100000 && h() != null) {
                    list.get(list.size() - 1).d(101, null);
                    h().d(106, null);
                } else {
                    list.get(list.size() - 1).d(102, null);
                }
                j.remove(Integer.valueOf(i));
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleErrorCode callback error");
            }
        }
    }

    private void f(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.5 handleReportStatus, info:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleReportStatus data is error");
            return;
        }
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        int i = 0;
        try {
            List<cwd> e2 = this.r.a(d2.substring(4)).e();
            if (e2 != null && !e2.isEmpty()) {
                int i2 = 0;
                for (cwd cwdVar : e2) {
                    try {
                        int a2 = CommonUtil.a(cwdVar.e(), 16);
                        String c2 = cwdVar.c();
                        if (a2 == 1) {
                            watchResourcesInfo.setWatchInfoId(cvx.e(c2));
                            LogUtil.a("HwWatchBtFaceManager", "handleReportStatus setWatchInfo_Id:", watchResourcesInfo.getWatchInfoId());
                        } else if (a2 == 2) {
                            watchResourcesInfo.setWatchInfoVersion(cvx.e(c2));
                            LogUtil.a("HwWatchBtFaceManager", "handleReportStatus setWatchInfo_Version:", watchResourcesInfo.getWatchInfoVersion());
                        } else if (a2 == 3) {
                            i2 = CommonUtil.a(c2, 16);
                            LogUtil.a("HwWatchBtFaceManager", "handleReportStatus reportType:", Integer.valueOf(i2));
                        } else if (a2 == 4) {
                            watchResourcesInfo.setFailedNum(CommonUtil.a(c2, 16));
                            LogUtil.a("HwWatchBtFaceManager", "handleReportStatus failed num:", Integer.valueOf(watchResourcesInfo.getFailedNum()));
                        } else {
                            LogUtil.h("HwWatchBtFaceManager", "handleReportStatus, nothing to do");
                        }
                    } catch (cwg e3) {
                        e = e3;
                        i = i2;
                        ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleReportStatus TlvException:", ExceptionUtils.d(e));
                        d(watchResourcesInfo, 100000);
                        e(i, watchResourcesInfo);
                    }
                }
                i = i2;
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleReportStatus tlvList error");
            }
        } catch (cwg e4) {
            e = e4;
        }
        d(watchResourcesInfo, 100000);
        e(i, watchResourcesInfo);
    }

    private void e(int i, WatchResourcesInfo watchResourcesInfo) {
        if (h() == null) {
            c(i, watchResourcesInfo);
            return;
        }
        if (i == 1) {
            h().d(106, watchResourcesInfo);
            return;
        }
        if (i == 2) {
            h().d(107, watchResourcesInfo);
            return;
        }
        if (i == 3) {
            h().d(108, watchResourcesInfo);
        } else if (i == 4) {
            c(i, watchResourcesInfo);
        } else {
            LogUtil.h("HwWatchBtFaceManager", "reportStatusForUi error");
        }
    }

    private void c(int i, WatchResourcesInfo watchResourcesInfo) {
        if (i != 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "reportPhotoReceivedStatus, reportType not STATUS_PHOTO_RECEIVE");
            return;
        }
        LogUtil.a("HwWatchBtFaceManager", "normal watch received");
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(109);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(101, Integer.valueOf(watchResourcesInfo.getFailedNum()));
                    LogUtil.a("HwWatchBtFaceManager", "send File callback success, filed number:", Integer.valueOf(watchResourcesInfo.getFailedNum()));
                }
                j.remove(109);
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "send file callback error");
            }
        }
    }

    private void e(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.6 handleNameInfo:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleNameInfo data is error");
            return;
        }
        try {
            cwe cweVar = this.r.a(d2.substring(4)).a().get(0);
            LogUtil.a("HwWatchBtFaceManager", "handleNameInfo size:", Integer.valueOf(cweVar.a().size()));
            HashMap<String, WatchResourcesInfo> hashMap = new HashMap<>(20);
            Iterator<cwe> it = cweVar.a().iterator();
            while (it.hasNext()) {
                b(hashMap, it.next());
            }
            LogUtil.a("HwWatchBtFaceManager", "handleNameInfo End, nameMap size:", Integer.valueOf(hashMap.size()));
            e(hashMap);
        } catch (cwg e2) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleNameInfo TlvException:", ExceptionUtils.d(e2));
        } catch (IndexOutOfBoundsException e3) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleNameInfo IndexOutOfBoundsException:", ExceptionUtils.d(e3));
        } catch (NumberFormatException e4) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleNameInfo NumberFormatException:", ExceptionUtils.d(e4));
        }
    }

    private void b(HashMap<String, WatchResourcesInfo> hashMap, cwe cweVar) {
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        String str = "";
        for (cwd cwdVar : cweVar.e()) {
            int a2 = CommonUtil.a(cwdVar.e(), 16);
            if (a2 == 4) {
                str = cvx.e(cwdVar.c());
                LogUtil.a("HwWatchBtFaceManager", "handleNameInfo watchFaceId:", str);
            } else if (a2 == 5) {
                watchResourcesInfo.setWatchInfoName(cvx.e(cwdVar.c()));
                LogUtil.a("HwWatchBtFaceManager", "handleNameInfo watchFaceName:", watchResourcesInfo.getWatchInfoName());
            } else if (a2 == 6) {
                watchResourcesInfo.setWatchInfoBrief(cvx.e(cwdVar.c()));
                LogUtil.a("HwWatchBtFaceManager", "handleNameInfo watchFaceBrief:", watchResourcesInfo.getWatchInfoBrief());
            } else if (a2 == 7) {
                watchResourcesInfo.setWatchInfoSize(CommonUtil.a(cwdVar.c(), 16));
                LogUtil.a("HwWatchBtFaceManager", "handleNameInfo watchFaceSize:", Integer.valueOf(watchResourcesInfo.getWatchInfoSize()));
            } else {
                LogUtil.h("HwWatchBtFaceManager", "handleOneTlv default:", Integer.valueOf(CommonUtil.a(cwdVar.e(), 16)));
            }
        }
        LogUtil.a("HwWatchBtFaceManager", "handleNameInfo putMap, watchFaceId:", str, "watchFaceInfo:", watchResourcesInfo.toString());
        hashMap.put(str, watchResourcesInfo);
    }

    private void e(HashMap<String, WatchResourcesInfo> hashMap) {
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(6);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).d(101, hashMap);
                j.remove(6);
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleNameInfo callback error");
            }
        }
    }

    private void j(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.8 handlePhotoInfo, info:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.e("R_HwWatchBtFaceManager", "handlePhotoInfo data is error");
            return;
        }
        try {
            cwe a2 = this.r.a(d2.substring(4));
            List<cwd> e2 = a2.e();
            if (e2 != null && !e2.isEmpty()) {
                for (cwd cwdVar : e2) {
                    b(CommonUtil.a(cwdVar.e(), 16), cwdVar.c());
                }
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handlePhotoInfo tlvList error");
            }
            LogUtil.a("HwWatchBtFaceManager", "5.39.8 handlePhotoInfo struct size:", Integer.valueOf(a2.a().size()));
            DeviceInfo a3 = jpt.a("HwWatchBtFaceManager");
            if (a3 != null) {
                e(a3);
            }
            if (!a2.a().isEmpty()) {
                d(a2.a().get(0));
            } else {
                this.s.b(new ArrayList<>(20));
            }
        } catch (cwg e3) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handlePhotoInfo TlvException:", ExceptionUtils.d(e3));
        }
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(8);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).d(101, this.s);
                j.remove(8);
            } else {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handlePhotoInfo callback error");
            }
        }
    }

    private void e(DeviceInfo deviceInfo) {
        this.t.put(deviceInfo.getDeviceIdentify() + deviceInfo.getSoftVersion(), this.s.b() + Constants.LINK + this.s.f() + Constants.LINK + this.s.e() + Constants.LINK + this.s.c());
    }

    private void d(cwe cweVar) {
        LogUtil.a("HwWatchBtFaceManager", "enter handleBackground");
        TreeMap treeMap = new TreeMap();
        Iterator<cwe> it = cweVar.a().iterator();
        String str = "";
        int i = -1;
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 6) {
                    i = CommonUtil.w(cwdVar.c());
                } else if (w == 7) {
                    str = cvx.e(cwdVar.c());
                } else {
                    LogUtil.h("HwWatchBtFaceManager", "handleBackground error");
                }
            }
            if (i != -1 && !TextUtils.isEmpty(str)) {
                treeMap.put(Integer.valueOf(i), str);
            }
        }
        LogUtil.a("HwWatchBtFaceManager", "handleBackground map.size", Integer.valueOf(treeMap.size()));
        Iterator it2 = treeMap.keySet().iterator();
        ArrayList<String> arrayList = new ArrayList<>(20);
        while (it2.hasNext()) {
            arrayList.add((String) treeMap.get(it2.next()));
        }
        LogUtil.a("HwWatchBtFaceManager", "handleBackground backgroundList.size", Integer.valueOf(arrayList.size()));
        this.s.b(arrayList);
    }

    private void b(int i, String str) {
        if (i == 1) {
            this.s.e(CommonUtil.a(str, 16));
            LogUtil.a("HwWatchBtFaceManager", "handlePhotoInfoTlv setMaxBackgroundImages:", Integer.valueOf(this.s.b()));
            return;
        }
        if (i == 2) {
            if (CommonUtil.a(str, 16) == 1) {
                this.s.d(true);
            } else {
                this.s.d(false);
            }
            LogUtil.a("HwWatchBtFaceManager", "handlePhotoInfoTlv setSupportIntellectColor:", Boolean.valueOf(this.s.f()));
            return;
        }
        if (i == 3) {
            this.s.a(CommonUtil.a(str, 16));
            LogUtil.a("HwWatchBtFaceManager", "handlePhotoInfoTlv setBackgroundType:", Integer.valueOf(this.s.e()));
            return;
        }
        if (i == 8) {
            this.s.c(CommonUtil.a(str, 16));
            LogUtil.a("HwWatchBtFaceManager", "handlePhotoInfoTlv setPositionIndex:", Integer.valueOf(this.s.d()));
        } else if (i == 9) {
            this.s.b(CommonUtil.a(str, 16));
            LogUtil.a("HwWatchBtFaceManager", "handlePhotoInfoTlv setStyleIndex:", Integer.valueOf(this.s.g()));
        } else if (i == 11) {
            this.s.d(CommonUtil.a(str, 16));
            LogUtil.a("HwWatchBtFaceManager", "handlePhotoInfoTlv setBackgroundImageOption:", Integer.valueOf(this.s.c()));
        } else {
            LogUtil.h("HwWatchBtFaceManager", "handlePhotoInfoTlv default type:", Integer.valueOf(i));
        }
    }

    private void h(byte[] bArr) {
        int i;
        int i2;
        List<cwd> e2;
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.9 handlePhotoStatus ", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handlePhotoStatus data is error");
            return;
        }
        int i3 = 0;
        try {
            e2 = this.r.a(d2.substring(4)).e();
        } catch (cwg e3) {
            e = e3;
            i = 0;
        }
        if (e2 != null && !e2.isEmpty()) {
            i = 0;
            i2 = 0;
            for (cwd cwdVar : e2) {
                try {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c2 = cwdVar.c();
                    if (a2 == 8) {
                        i2 = CommonUtil.a(c2, 16);
                        LogUtil.a("HwWatchBtFaceManager", "handlePhotoStatus transferNum:", Integer.valueOf(i2));
                    } else if (a2 == 127) {
                        i = CommonUtil.a(c2, 16);
                        LogUtil.a("HwWatchBtFaceManager", "handlePhotoStatus ERROR_CODE:", Integer.valueOf(i));
                    } else {
                        LogUtil.h("HwWatchBtFaceManager", "handlePhotoStatus, nothing to do");
                    }
                } catch (cwg e4) {
                    e = e4;
                    i3 = i2;
                    ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handlePhotoStatus error:", ExceptionUtils.d(e));
                    i2 = i3;
                    i3 = i;
                    e(i3, i2);
                }
            }
            i3 = i;
            e(i3, i2);
        }
        ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handlePhotoStatus tlv error");
        i2 = 0;
        e(i3, i2);
    }

    private void e(int i, int i2) {
        synchronized (i()) {
            List<IBaseResponseCallback> list = j.get(109);
            if (list == null || list.isEmpty()) {
                ReleaseLogUtil.d("R_HwWatchBtFaceManager", "reportPhotoStatus callback error");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).d(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "reportPhotoStatus need transfer num:", Integer.valueOf(i2));
                list.get(list.size() - 1).d(111, Integer.valueOf(i2));
            } else {
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "reportPhotoStatus errorCode is failed");
                list.get(list.size() - 1).d(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    private void g(byte[] bArr) {
        String d2 = cvx.d(bArr);
        LogUtil.a("HwWatchBtFaceManager", "5.39.10 handleTagActivate info:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "handleTagActivate data is error");
            return;
        }
        try {
            if ((CommonUtil.w(this.r.a(d2.substring(4)).e().get(0).e()) & 1) == 1) {
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "5.39.10 start activate tag!");
                jln.c(BaseApplication.getContext()).d();
            }
            String str = cvx.e(127) + cvx.e(4) + cvx.b(100000L);
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(39);
            deviceCommand.setCommandID(10);
            deviceCommand.setDataContent(cvx.a(str));
            deviceCommand.setDataLen(cvx.a(str).length);
            this.i.b(deviceCommand);
        } catch (cwg e2) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "handleDeviceInfo TlvException:", ExceptionUtils.d(e2));
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter getDeviceInfoForUi");
        synchronized (i()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = j.get(1);
                if (list == null) {
                    ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter getDeviceInfoForUI have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    j.put(1, arrayList);
                } else {
                    ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter getDeviceInfoForUI have callback,add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        f();
    }

    private void f() {
        String str = (cvx.e(1) + cvx.e(0)) + (cvx.e(2) + cvx.e(0)) + (cvx.e(3) + cvx.e(0)) + (cvx.e(4) + cvx.e(0)) + (cvx.e(5) + cvx.e(0));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWatchBtFaceManager", "getDeviceInfo, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    private void g() {
        String str = cvx.e(1) + cvx.e(0);
        int i = CommonUtil.cc() ? 2 : 1;
        if (Utils.o() && !TextUtils.equals("CN", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode())) {
            i = 3;
        }
        String str2 = str + (cvx.e(6) + cvx.e(1) + cvx.e(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        LogUtil.a("HwWatchBtFaceManager", "getDeviceWatchInfo, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    private void e(WatchResourcesInfo watchResourcesInfo, int i) {
        if (watchResourcesInfo == null) {
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "sendOperateType watchResourcesInfo == null");
            return;
        }
        String c2 = cvx.c(watchResourcesInfo.getWatchInfoId());
        String str = cvx.e(1) + cvx.e(c2.length() / 2) + c2;
        String c3 = cvx.c(watchResourcesInfo.getWatchInfoVersion());
        String str2 = str + (cvx.e(2) + cvx.e(c3.length() / 2) + c3) + (cvx.e(3) + cvx.e(1) + cvx.e(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        LogUtil.a("HwWatchBtFaceManager", "sendOperateType, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    private void d(WatchResourcesInfo watchResourcesInfo, int i) {
        String c2 = cvx.c(watchResourcesInfo.getWatchInfoId());
        String str = cvx.e(1) + cvx.e(c2.length() / 2) + c2;
        String c3 = cvx.c(watchResourcesInfo.getWatchInfoVersion());
        String str2 = str + (cvx.e(2) + cvx.e(c3.length() / 2) + c3) + (cvx.e(127) + cvx.e(4) + cvx.b(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(5);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        LogUtil.a("HwWatchBtFaceManager", "reportACK, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    private void a(int i) {
        String str = cvx.e(1) + cvx.e(0);
        String str2 = cvx.e(2) + cvx.e(0);
        String str3 = cvx.e(3) + cvx.e(0);
        String str4 = cvx.e(4) + cvx.e(0);
        StringBuilder sb = new StringBuilder(20);
        if ((i & 1) == 1) {
            sb.append(cvx.e(8) + cvx.e(0));
        }
        if ((i & 2) == 2) {
            sb.append(cvx.e(9) + cvx.e(0));
        }
        String str5 = str + str2 + str3 + str4 + sb.toString();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(8);
        deviceCommand.setDataContent(cvx.a(str5));
        deviceCommand.setDataLen(cvx.a(str5).length);
        LogUtil.a("HwWatchBtFaceManager", "getPhotoWatchInfo, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    private void d(jlq jlqVar) {
        String str;
        StringBuilder sb = new StringBuilder(20);
        Iterator<String> it = jlqVar.a().iterator();
        int i = 1;
        while (it.hasNext()) {
            String next = it.next();
            String str2 = cvx.e(4) + cvx.e(1) + cvx.e(i);
            String str3 = cvx.e(5) + cvx.e(cvx.c(next).length() / 2) + cvx.c(next);
            bms bmsVar = new bms();
            bmsVar.j(21, 1);
            bmsVar.j(22, 1);
            bmsVar.j(23, 1);
            String str4 = str2 + str3 + cvx.d(bmsVar.d());
            sb.append(cvx.e(131) + cvx.e(str4.length() / 2) + str4);
            i++;
        }
        StringBuilder sb2 = new StringBuilder(20);
        LogUtil.a("HwWatchBtFaceManager", "makePhotoInfoCommand type:", Integer.valueOf(this.n));
        if ((this.n & 1) == 1) {
            sb2.append(cvx.e(6) + cvx.e(1) + cvx.e(jlqVar.d()));
        }
        if ((this.n & 2) == 2) {
            sb2.append(cvx.e(7) + cvx.e(1) + cvx.e(jlqVar.g()));
        }
        bms bmsVar2 = new bms();
        bmsVar2.j(28, 0);
        sb2.append(cvx.d(bmsVar2.d()));
        int size = jlqVar.a().size();
        String str5 = cvx.e(1) + cvx.e(1) + cvx.e(size);
        if (size == 0) {
            str = str5 + ((Object) sb2);
        } else {
            str = str5 + (cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.d(sb.length() / 2) + ((Object) sb)) + ((Object) sb2);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(9);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWatchBtFaceManager", "makePhotoInfoCommand, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    public void e(int i) {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter sendTagResult");
        String str = cvx.e(1) + cvx.e(4) + cvx.b(i);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(11);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWatchBtFaceManager", "sendTagResult, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    public void c(int i) {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter sendClipMessage");
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(13);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a("HwWatchBtFaceManager", "sendClipMessage, deviceCommand:", deviceCommand.toString());
        this.i.b(deviceCommand);
    }

    public WatchFaceInfo d() {
        return this.p;
    }

    public void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (i()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = j.get(8);
                if (list == null) {
                    ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter getWatchFacePhotoInfo have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    j.put(8, arrayList);
                } else {
                    LogUtil.a("HwWatchBtFaceManager", "enter getWatchFacePhotoInfo have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        LogUtil.a("HwWatchBtFaceManager", "getWatchFacePhotoInfo type:", Integer.valueOf(i));
        this.n = i;
        a(i);
    }

    public void b(jlq jlqVar, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter sendResourceToDevice");
        if (jlqVar == null) {
            iBaseResponseCallback.d(102, "");
            ReleaseLogUtil.d("R_HwWatchBtFaceManager", "sendResourceToDevice error");
            return;
        }
        synchronized (i()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = j.get(109);
                if (list == null) {
                    ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter sendResourceToDevice have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    j.put(109, arrayList);
                } else {
                    LogUtil.a("HwWatchBtFaceManager", "enter sendResourceToDevice have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        d(jlqVar);
    }

    public int a() {
        DeviceInfo a2 = jpt.a("HwWatchBtFaceManager");
        if (a2 != null && this.t != null) {
            String str = this.t.get(a2.getDeviceIdentify() + a2.getSoftVersion());
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(Constants.LINK);
                if (split.length > 0) {
                    return CommonUtil.e(split[0], 0);
                }
            }
        }
        return 0;
    }

    public int e() {
        DeviceInfo a2 = jpt.a("HwWatchBtFaceManager");
        if (a2 != null && this.t != null) {
            String str = this.t.get(a2.getDeviceIdentify() + a2.getSoftVersion());
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(Constants.LINK);
                if (split.length > 1) {
                    return CommonUtil.e(split[2], 2);
                }
            }
        }
        return 2;
    }

    public int b() {
        DeviceInfo a2 = jpt.a("HwWatchBtFaceManager");
        if (a2 == null || this.t == null) {
            return 1;
        }
        String str = this.t.get(a2.getDeviceIdentify() + a2.getSoftVersion());
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        String[] split = str.split(Constants.LINK);
        if (split.length > 3) {
            return CommonUtil.e(split[3], 2);
        }
        return 1;
    }

    public boolean c() {
        DeviceInfo a2 = jpt.a("HwWatchBtFaceManager");
        if (a2 == null || this.t == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a2.getDeviceIdentify());
        sb.append(a2.getSoftVersion());
        return !TextUtils.isEmpty(this.t.get(sb.toString()));
    }

    public void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "enter setTagStatus cardState:", Integer.valueOf(i));
        HwPayManager.a().c(i, iBaseResponseCallback);
    }

    public void d(WatchFaceHealthResponseListener watchFaceHealthResponseListener) {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "registerHealthResponseListener");
        this.o = watchFaceHealthResponseListener;
    }

    public void j() {
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "unRegisterHealthResponseListener");
        this.o = null;
    }

    private boolean b(int i, Object obj) {
        WatchFaceHealthResponseListener watchFaceHealthResponseListener = this.o;
        if (watchFaceHealthResponseListener == null) {
            return false;
        }
        watchFaceHealthResponseListener.onResponse(1, i, obj);
        return true;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        try {
            ReleaseLogUtil.e("R_HwWatchBtFaceManager", "Enter unregisterNonLocalBroadcast!");
            this.h.unregisterReceiver(this.k);
        } catch (IllegalArgumentException e2) {
            ReleaseLogUtil.c("R_HwWatchBtFaceManager", "onDestroy IllegalArgumentException:", ExceptionUtils.d(e2));
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr != null) {
            ReleaseLogUtil.e("R_HwWatchBtFaceManager", "onDataReceived serviceId is,", Integer.valueOf(bArr[0]), "commandId is,", Integer.valueOf(bArr[1]));
            if ((jqx.b(bArr) || jqx.c(bArr)) && b(i, bArr)) {
                ReleaseLogUtil.e("R_HwWatchBtFaceManager", "watchface appId");
                if (jqx.b(bArr, this.g)) {
                    ReleaseLogUtil.e("R_HwWatchBtFaceManager", "TouchTransferCommand is true");
                    c(bArr);
                    return;
                }
                return;
            }
            c(bArr);
            return;
        }
        ReleaseLogUtil.e("R_HwWatchBtFaceManager", "data is null");
    }
}

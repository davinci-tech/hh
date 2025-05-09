package defpackage;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.handler.BaseHandlerCallback;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.wifi.lib.db.dbtable.DeviceListManager;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.AuthorizeSubUserInfo;
import com.huawei.hwcloudmodel.model.unite.DevInfo;
import com.huawei.hwcloudmodel.model.unite.DeviceDetailInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAllDeviceRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAuthorizeSubUserRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CompileParameterUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class csb {
    private static long b;
    private static final Object d = new Object();
    private static volatile csb e;
    private List<DeviceDetailInfo> f;
    private List<DeviceDetailInfo> g;
    private List<DeviceDetailInfo> i = new ArrayList(16);

    /* renamed from: a, reason: collision with root package name */
    private boolean f11424a = false;
    private boolean c = false;
    private int j = 0;
    private ArrayList<crw> h = new ArrayList<>(16);
    private ExtendHandler l = HandlerCenter.yt_(new c(this), "update_device");

    static /* synthetic */ int e(csb csbVar) {
        int i = csbVar.j;
        csbVar.j = i + 1;
        return i;
    }

    private csb() {
    }

    public static csb a() {
        if (e == null) {
            synchronized (d) {
                if (e == null) {
                    e = new csb();
                }
            }
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        long currentTimeMillis = System.currentTimeMillis();
        cpw.a(false, "UpdateDeviceControl", "isSync sNextSyncTime:", Long.valueOf(b), " mCurrentTime ,", Long.valueOf(currentTimeMillis));
        if (b >= currentTimeMillis - 60000) {
            return false;
        }
        b = currentTimeMillis;
        return true;
    }

    static class c extends BaseHandlerCallback<csb> {
        c(csb csbVar) {
            super(csbVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandlerCallback
        /* renamed from: LE_, reason: merged with bridge method [inline-methods] */
        public boolean handleMessageWhenReferenceNotNull(csb csbVar, Message message) {
            return LD_(csbVar, message);
        }

        private boolean LD_(csb csbVar, Message message) {
            LogUtil.a("UpdateDeviceControl", "msg.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 265) {
                if (csbVar.k()) {
                    csbVar.q();
                    return true;
                }
                csbVar.i();
                return true;
            }
            if (i == 272) {
                if (!(message.obj instanceof String)) {
                    return true;
                }
                csbVar.i((String) message.obj);
                return true;
            }
            if (i != 273) {
                switch (i) {
                    case 256:
                        csbVar.p();
                        return true;
                    case 257:
                        if (!csbVar.n()) {
                            return true;
                        }
                        csbVar.p();
                        return true;
                    case 258:
                        csbVar.r();
                        return true;
                    case 259:
                        csbVar.l();
                        return true;
                    case 260:
                        csbVar.o();
                        return true;
                    case 261:
                        csbVar.j();
                        return true;
                    case 262:
                        if (!(message.obj instanceof List)) {
                            return true;
                        }
                        csbVar.b((List<DeviceDetailInfo>) message.obj);
                        return true;
                    case 263:
                        csbVar.m();
                        return true;
                    default:
                        cpw.a(false, "UpdateDeviceControl", "unknown Message");
                        return false;
                }
            }
            cst.c(cpp.a());
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        h();
        ArrayList<crw> arrayList = this.h;
        if (arrayList == null || arrayList.size() <= 0) {
            cpw.d(false, "UpdateDeviceControl", "saveSubUser() mSubUsers is null:");
            return;
        }
        Iterator<crw> it = this.h.iterator();
        while (it.hasNext()) {
            ctq.c(it.next());
        }
    }

    private void h() {
        cpw.a(false, "UpdateDeviceControl", "deleteSubUser");
        ctq.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        List<DeviceDetailInfo> list = this.g;
        if (list == null || list.size() <= 0) {
            h();
            cpw.d(false, "UpdateDeviceControl", "downloadSubUser() mDeviceInfoList is null");
            return;
        }
        this.h.clear();
        this.j = 0;
        Message obtain = Message.obtain();
        obtain.obj = this.g;
        obtain.what = 262;
        this.l.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp, String str) {
        List<AuthorizeSubUserInfo> authorizeSubUserList = wifiDeviceGetAuthorizeSubUserRsp.getAuthorizeSubUserList();
        if (authorizeSubUserList == null || authorizeSubUserList.size() <= 0) {
            return;
        }
        crw crwVar = new crw();
        for (AuthorizeSubUserInfo authorizeSubUserInfo : authorizeSubUserList) {
            crwVar.e(authorizeSubUserInfo.getSubHuid(), authorizeSubUserInfo.getNickName(), authorizeSubUserInfo.getUserAccount());
        }
        crwVar.d(str);
        this.h.add(crwVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<DeviceDetailInfo> list) {
        if (this.j >= list.size()) {
            this.l.sendEmptyMessage(263);
            return;
        }
        final String devId = list.get(this.j).getDevId();
        if (TextUtils.isEmpty(devId)) {
            this.j++;
            Message obtain = Message.obtain();
            obtain.obj = list;
            obtain.what = 262;
            this.l.sendMessage(obtain);
            cpw.d(false, "UpdateDeviceControl", "downloadSingDeviceUser() deviceId is null");
            return;
        }
        cpw.a(false, "UpdateDeviceControl", "downloadSingDeviceUser() deviceId:", cpw.d(devId));
        WifiDeviceGetAuthorizeSubUserReq wifiDeviceGetAuthorizeSubUserReq = new WifiDeviceGetAuthorizeSubUserReq();
        wifiDeviceGetAuthorizeSubUserReq.setDevId(devId);
        jbs.a(cpp.a()).a(wifiDeviceGetAuthorizeSubUserReq, new ICloudOperationResult<WifiDeviceGetAuthorizeSubUserRsp>() { // from class: csb.2
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceGetAuthorizeSubUserRsp wifiDeviceGetAuthorizeSubUserRsp, String str, boolean z) {
                int i;
                String str2;
                if (!z) {
                    if (wifiDeviceGetAuthorizeSubUserRsp != null) {
                        i = wifiDeviceGetAuthorizeSubUserRsp.getResultCode().intValue();
                        str2 = wifiDeviceGetAuthorizeSubUserRsp.getResultDesc();
                    } else {
                        i = Constants.CODE_UNKNOWN_ERROR;
                        str2 = "downloadSingDeviceUser() unknown error";
                    }
                    cpw.d(false, "UpdateDeviceControl", "downloadSingDeviceUser() errCode = ", Integer.valueOf(i), ", downloadSingDeviceUser() resultDesc:", str2);
                } else if (wifiDeviceGetAuthorizeSubUserRsp != null) {
                    csb.this.c(wifiDeviceGetAuthorizeSubUserRsp, devId);
                } else {
                    cpw.d(false, "UpdateDeviceControl", "downloadSingDeviceUser() get Sub User is null ,deviceId:", cpw.d(devId));
                }
                csb.e(csb.this);
                Message obtain2 = Message.obtain();
                obtain2.obj = list;
                obtain2.what = 262;
                csb.this.l.sendMessage(obtain2);
            }
        });
    }

    public static boolean b() {
        return CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (ResourceManager.e().d("a8ba095d-4123-43c4-a30a-0240011c58de") == null) {
            ResourceManager.e().j("a8ba095d-4123-43c4-a30a-0240011c58de");
        }
        if (ResourceManager.e().d("e4b0b1d5-2003-4d88-8b5f-c4f64542040b") == null) {
            ResourceManager.e().j("e4b0b1d5-2003-4d88-8b5f-c4f64542040b");
        }
        if (ResourceManager.e().d("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4") == null) {
            ResourceManager.e().j("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
        }
        if (ResourceManager.e().d("b29df4e3-b1f7-4e40-960d-4cfb63ccca05") == null) {
            ResourceManager.e().j("b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
        }
        if (ResourceManager.e().d("e835d102-af95-48a6-ae13-2983bc06f5c0") == null) {
            ResourceManager.e().j("e835d102-af95-48a6-ae13-2983bc06f5c0");
        }
    }

    public void e() {
        cpw.a(false, "UpdateDeviceControl", "syncDelayedDevice in");
        this.l.sendEmptyMessage(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
        this.l.sendEmptyMessage(260);
        this.l.sendEmptyMessage(257, 3000L);
    }

    public void d() {
        if (b()) {
            cpw.a(false, "UpdateDeviceControl", "syncDevice in");
            this.l.sendEmptyMessage(257);
        }
    }

    public void g() {
        if (b()) {
            cpw.a(false, "UpdateDeviceControl", "syncNowDevice in");
            this.l.sendEmptyMessage(256);
        }
    }

    public void e(String str) {
        if (b()) {
            cpw.a(false, "UpdateDeviceControl", "syncNowDevice in and remove duplicate device");
            Message obtain = Message.obtain();
            obtain.obj = str;
            obtain.what = 272;
            this.l.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        cpw.a(false, "UpdateDeviceControl", "syncWiFiDevice in");
        synchronized (d) {
            this.g = null;
            this.f = null;
        }
        jbs.a(cpp.a()).d(new ICloudOperationResult<WifiDeviceGetAllDeviceRsp>() { // from class: csb.4
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str, boolean z) {
                String str2;
                int i;
                if (z) {
                    cpw.a(false, "UpdateDeviceControl", "syncWiFiDevice reg device success :", wifiDeviceGetAllDeviceRsp.toString());
                    synchronized (csb.d) {
                        csb.this.g = wifiDeviceGetAllDeviceRsp.getDeviceDetailInfoList();
                        csb.this.f = wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList();
                    }
                    csb.this.l.sendEmptyMessage(261);
                    csb.this.l.sendEmptyMessage(258);
                    return;
                }
                ArrayList<String> d2 = ctq.d();
                if (d2 != null) {
                    Iterator<String> it = d2.iterator();
                    while (it.hasNext()) {
                        csb.this.a(it.next());
                    }
                }
                if (wifiDeviceGetAllDeviceRsp != null) {
                    i = wifiDeviceGetAllDeviceRsp.getResultCode().intValue();
                    str2 = wifiDeviceGetAllDeviceRsp.getResultDesc();
                } else {
                    str2 = "syncWiFiDevice() unknown error";
                    i = Constants.CODE_UNKNOWN_ERROR;
                }
                cpw.d(false, "UpdateDeviceControl", "syncWiFiDevice() errCode = ", Integer.valueOf(i), ", syncWiFiDevice() resultDesc:", str2);
            }
        });
    }

    public void b(final String str, String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UpdateDeviceControl", "syncWiFiDevice product is null");
            iBaseResponseCallback.d(-1, "illegal productId");
        } else {
            MeasurableDevice d2 = ceo.d().d(str2, false);
            final String serialNumber = d2 != null ? d2.getSerialNumber() : "";
            jbs.a(cpp.a()).d(new ICloudOperationResult<WifiDeviceGetAllDeviceRsp>() { // from class: csb.1
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void operationResult(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str3, boolean z) {
                    String str4;
                    int i;
                    if (z) {
                        cpw.a(false, "UpdateDeviceControl", "syncWiFiDevice with callBack success :", wifiDeviceGetAllDeviceRsp.toString());
                        synchronized (csb.d) {
                            csb.this.g = wifiDeviceGetAllDeviceRsp.getDeviceDetailInfoList();
                            csb.this.f = wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList();
                        }
                        csb.this.l.sendEmptyMessage(261);
                        csb.this.l.sendEmptyMessage(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
                        csb csbVar = csb.this;
                        if (!csbVar.b((List<DeviceDetailInfo>) csbVar.g, serialNumber)) {
                            csb csbVar2 = csb.this;
                            if (!csbVar2.b((List<DeviceDetailInfo>) csbVar2.f, serialNumber)) {
                                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                                if (iBaseResponseCallback2 != null) {
                                    iBaseResponseCallback2.d(-1, "no such product");
                                    return;
                                } else {
                                    LogUtil.h("UpdateDeviceControl", "syncWiFiDevice success callback is null");
                                    return;
                                }
                            }
                        }
                        if (ResourceManager.e().d(str) == null) {
                            csb.this.a(3, str, iBaseResponseCallback);
                            return;
                        } else {
                            iBaseResponseCallback.d(0, "");
                            return;
                        }
                    }
                    if (wifiDeviceGetAllDeviceRsp != null) {
                        i = wifiDeviceGetAllDeviceRsp.getResultCode().intValue();
                        str4 = wifiDeviceGetAllDeviceRsp.getResultDesc();
                    } else {
                        str4 = "syncWiFiDevice() unknown error";
                        i = Constants.CODE_UNKNOWN_ERROR;
                    }
                    IBaseResponseCallback iBaseResponseCallback3 = iBaseResponseCallback;
                    if (iBaseResponseCallback3 != null) {
                        iBaseResponseCallback3.d(i, str4);
                    } else {
                        LogUtil.h("UpdateDeviceControl", "syncWiFiDevice with callback fail, callback is null");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(final String str) {
        cpw.a(false, "UpdateDeviceControl", "syncWiFiDevice in");
        synchronized (d) {
            this.g = null;
            this.f = null;
        }
        jbs.a(cpp.a()).d(new ICloudOperationResult<WifiDeviceGetAllDeviceRsp>() { // from class: csb.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str2, boolean z) {
                String str3;
                int i;
                if (z) {
                    cpw.a(false, "UpdateDeviceControl", "syncWiFiDevice reg device success :", wifiDeviceGetAllDeviceRsp.toString());
                    synchronized (csb.d) {
                        csb.this.g = wifiDeviceGetAllDeviceRsp.getDeviceDetailInfoList();
                        csb.this.f = wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList();
                    }
                    csb.this.l.sendEmptyMessage(261);
                    csb.this.l.sendEmptyMessage(258);
                    csb.this.d(str);
                    return;
                }
                ArrayList<String> d2 = ctq.d();
                if (d2 != null) {
                    Iterator<String> it = d2.iterator();
                    while (it.hasNext()) {
                        csb.this.a(it.next());
                    }
                }
                if (wifiDeviceGetAllDeviceRsp != null) {
                    i = wifiDeviceGetAllDeviceRsp.getResultCode().intValue();
                    str3 = wifiDeviceGetAllDeviceRsp.getResultDesc();
                } else {
                    str3 = "syncWiFiDeviceDeleteDuplicateDeviceId() unknown error";
                    i = Constants.CODE_UNKNOWN_ERROR;
                }
                cpw.d(false, "UpdateDeviceControl", "syncWiFiDevice() errCode = ", Integer.valueOf(i), ", syncWiFiDeviceDeleteDuplicateDeviceId() resultDesc:", str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        List<DeviceDetailInfo> list = this.g;
        if (list == null) {
            return;
        }
        for (DeviceDetailInfo deviceDetailInfo : list) {
            if (cpa.t(deviceDetailInfo.getDevInfo().getProdId()).equals(str) && deviceDetailInfo.getServices() == null) {
                cpw.a(false, "UpdateDeviceControl", "start to delete bluetooth device, deviceID = ", deviceDetailInfo.getDevId());
                csf.i(deviceDetailInfo.getDevId());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        synchronized (d) {
            List<DeviceDetailInfo> list = this.f;
            if (list != null && list.size() > 0) {
                return true;
            }
            List<DeviceDetailInfo> list2 = this.g;
            return list2 != null && list2.size() > 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.l.sendEmptyMessage(259);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        LogUtil.a("UpdateDeviceControl", "updateResourceFile");
        synchronized (d) {
            if (koq.b(this.g) && koq.b(this.f)) {
                this.l.sendEmptyMessage(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
            } else {
                this.i.clear();
                List<DeviceDetailInfo> list = this.g;
                if (list != null) {
                    this.i.addAll(list);
                } else {
                    LogUtil.h("UpdateDeviceControl", "updateResourceFile mDeviceInfoList is null");
                }
                List<DeviceDetailInfo> list2 = this.f;
                if (list2 != null) {
                    this.i.addAll(list2);
                } else {
                    LogUtil.h("UpdateDeviceControl", "updateResourceFile mSubUserInfoList is null");
                }
                c(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i) {
        if (koq.b(this.i)) {
            return;
        }
        LogUtil.a("UpdateDeviceControl", "updateResourceFileByList, infos size:", Integer.valueOf(this.i.size()), "index is:", Integer.valueOf(i));
        DevInfo devInfo = this.i.get(i).getDevInfo();
        if (devInfo == null || TextUtils.isEmpty(devInfo.getProdId())) {
            return;
        }
        a(3, cpa.t(devInfo.getProdId()), new IBaseResponseCallback() { // from class: csb.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.c("UpdateDeviceControl", "updateResourceFileByList");
                if (i2 == 0 && i + 1 < csb.this.i.size()) {
                    csb.this.c(i + 1);
                } else {
                    LogUtil.h("UpdateDeviceControl", "updateResourceFileWithRetry not success");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final String str, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("UpdateDeviceControl", "updateResourceFileWithRetry ", Integer.valueOf(i), " productId:", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("UpdateDeviceControl", "updateResourceFile: product id is null");
            return;
        }
        if (EzPluginManager.a().g(str)) {
            this.l.sendEmptyMessage(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
            LogUtil.a("UpdateDeviceControl", "updateResourceFile: isPluginAvaiable is true");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, "");
                return;
            }
            return;
        }
        if (i <= 0) {
            this.l.sendEmptyMessage(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
            LogUtil.b("UpdateDeviceControl", "updateResourceFileWithRetry fail");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, "load resource fail");
                return;
            }
            return;
        }
        cpe.c(str, new PullListener() { // from class: csb.8
            @Override // com.huawei.pluginmgr.filedownload.PullListener
            public void onPullingChange(msq msqVar, mso msoVar) {
                if (msoVar == null) {
                    LogUtil.h("UpdateDeviceControl", "onPullingChange result is null");
                    return;
                }
                LogUtil.a("UpdateDeviceControl", "pull resource status, ", Integer.valueOf(msoVar.i()));
                int i2 = msoVar.i();
                if (i2 == 1 && iBaseResponseCallback != null) {
                    csb.this.l.sendEmptyMessage(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
                    iBaseResponseCallback.d(0, "");
                } else if (i2 != 0) {
                    csb.this.a(i - 1, str, iBaseResponseCallback);
                } else {
                    LogUtil.c("UpdateDeviceControl", "pull resource processing");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        cpw.a(false, "UpdateDeviceControl", "replaceDevice in");
        ArrayList arrayList = new ArrayList();
        List<DeviceDetailInfo> list = this.g;
        if (list != null && !list.isEmpty()) {
            arrayList.addAll(this.g);
        }
        List<DeviceDetailInfo> list2 = this.f;
        if (list2 != null && !list2.isEmpty()) {
            arrayList.addAll(this.f);
        }
        d(arrayList);
        synchronized (d) {
            b(this.g, 1);
            b(this.f, 2);
        }
        EventBus.d(new EventBus.b("wifi_scale_auth_refresh"));
        t();
        s();
    }

    private void t() {
        LogUtil.a("UpdateDeviceControl", "sendUserInfoAfterUpdateLocalDb in");
        if (koq.c(this.f)) {
            for (DeviceDetailInfo deviceDetailInfo : this.f) {
                if (deviceDetailInfo != null && deviceDetailInfo.getDevInfo() != null) {
                    csf.c(deviceDetailInfo.getDevId(), cpa.u(deviceDetailInfo.getDevInfo().getProdId()));
                }
            }
        }
    }

    private void d(List<DeviceDetailInfo> list) {
        boolean z;
        cpw.a(false, "UpdateDeviceControl", "deleteLocalDevice DeviceDetailInfo size ", Integer.valueOf(list.size()));
        ArrayList<ctk> e2 = ctq.e();
        if (e2 == null || e2.size() <= 0) {
            return;
        }
        cpw.a(false, "UpdateDeviceControl", "deleteLocalDevice WiFiDevice size ", Integer.valueOf(e2.size()));
        Iterator<ctk> it = e2.iterator();
        while (it.hasNext()) {
            ctk next = it.next();
            cpw.a(false, "UpdateDeviceControl", "deleteLocalDevice WiFiDevice productId ", cpw.d(next.getProductId()));
            Iterator<DeviceDetailInfo> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                if (next.d().equals(it2.next().getDevId())) {
                    cpw.a(false, "UpdateDeviceControl", "deleteLocalDevice getProductId = ", next.getDeviceName());
                    z = true;
                    break;
                }
            }
            boolean a2 = a(next);
            if (!z && !a2) {
                boolean f = cjx.e().f(next.d());
                cpw.a(false, "UpdateDeviceControl", "deleteDevice deviceId:", cpw.d(next.d()), "deleteLocalDevice isSuccess:", Boolean.valueOf(f));
                if (!f) {
                    cpw.a(false, "UpdateDeviceControl", "deleteDevice ProductId:", cpw.d(next.getProductId()), "deleteLocalDevice isSuccess:", Boolean.valueOf(cjx.e().m(next.getProductId())));
                }
            }
        }
    }

    private void f() {
        ArrayList<String> c2 = cjx.e().c();
        if (c2 != null && c2.size() > 0) {
            cpw.a(true, "UpdateDeviceControl", "deleteDevice deviceIds ", c2.toString());
            Iterator<String> it = c2.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        } else {
            cpw.a(false, "UpdateDeviceControl", "deleteDevice device is null ");
        }
        HealthDevice a2 = cjx.e().a("e4b0b1d5-2003-4d88-8b5f-c4f64542040b");
        Object[] objArr = new Object[2];
        objArr[0] = "deleteDevice check huawei device ";
        objArr[1] = a2 == null ? "is null" : a2.getUniqueId();
        cpw.a(false, "UpdateDeviceControl", objArr);
        if (a2 != null) {
            cjx.e().m("e4b0b1d5-2003-4d88-8b5f-c4f64542040b");
        }
        HealthDevice a3 = cjx.e().a("a8ba095d-4123-43c4-a30a-0240011c58de");
        Object[] objArr2 = new Object[2];
        objArr2[0] = "deleteDevice check honor device ";
        objArr2[1] = a3 != null ? a3.getUniqueId() : "is null";
        cpw.a(false, "UpdateDeviceControl", objArr2);
        if (a3 != null) {
            cjx.e().m("a8ba095d-4123-43c4-a30a-0240011c58de");
        }
    }

    private void b(String str) {
        ctk c2 = ctq.c(str);
        if (c2 == null) {
            cpw.d(false, "UpdateDeviceControl", "current device is null");
            return;
        }
        cpw.a(true, "UpdateDeviceControl", "deleteDevice productId ", cpw.d(c2.getProductId()));
        if (a(c2)) {
            return;
        }
        cjx.e().k(c2.getSerialNumber());
        cpw.a(false, "UpdateDeviceControl", "deleteDevice deviceId:", cpw.d(str), " isSuccess:", Boolean.valueOf(cjx.e().f(str)));
    }

    private boolean a(ctk ctkVar) {
        boolean z = ctkVar.b().k() == 1;
        cpw.a(false, "UpdateDeviceControl", "isMainUser is ", Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        cpw.a(false, "UpdateDeviceControl", "deleteAllDevice in ");
        f();
        v();
    }

    private void b(List<DeviceDetailInfo> list, int i) {
        ArrayList<DeviceDetailInfo> arrayList = new ArrayList(16);
        if (list != null && list.size() > 0) {
            cpw.a(false, "UpdateDeviceControl", "updateLocalDevice detailInfoList size ", Integer.valueOf(list.size()));
            for (int size = list.size() - 1; size >= 0; size--) {
                DeviceDetailInfo deviceDetailInfo = list.get(size);
                if (deviceDetailInfo != null) {
                    if (cpa.u(deviceDetailInfo.getDevInfo().getProdId()) && !e(deviceDetailInfo)) {
                        cpw.a(false, "UpdateDeviceControl", "updateLocalDevice hagrid device illegal device detail info： ", cpw.d(deviceDetailInfo.getDevId()));
                        arrayList.add(deviceDetailInfo);
                    } else {
                        cpw.a(false, "UpdateDeviceControl", "updateLocalDevice deviceId", cpw.d(deviceDetailInfo.getDevId()));
                        e(deviceDetailInfo, i);
                    }
                } else {
                    cpw.d(false, "UpdateDeviceControl", "updateLocalDevice deviceDetailInfo is null");
                }
            }
        } else {
            cpw.a(false, "UpdateDeviceControl", "updateLocalDevice detailInfoList is null source:", Integer.valueOf(i));
        }
        cpw.a(false, "UpdateDeviceControl", "updateLocalDevice dirtyDataList.size()： ", Integer.valueOf(arrayList.size()));
        if (koq.c(arrayList)) {
            for (DeviceDetailInfo deviceDetailInfo2 : arrayList) {
                if (deviceDetailInfo2 != null) {
                    String devId = deviceDetailInfo2.getDevId();
                    if (i == 1) {
                        cpw.a(false, "UpdateDeviceControl", "updateLocalDevice unBindDevice devId： ", cpw.d(devId));
                        csf.i(devId);
                    } else {
                        csf.a(devId);
                    }
                }
            }
        }
        if (i == 1) {
            this.f11424a = true;
        }
        if (i == 2) {
            this.c = true;
        }
        if (this.f11424a && this.c) {
            v();
        }
    }

    private void v() {
        cpw.a(false, "UpdateDeviceControl", "workFinish");
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.DEVICE_THIRD_DELETE");
        cpp.a().sendBroadcast(intent, LocalBroadcast.c);
        csf.e();
        this.c = false;
        this.f11424a = false;
    }

    private dcz c(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "UpdateDeviceControl", "getProduct productId is null");
            return null;
        }
        return ResourceManager.e().d(str);
    }

    public void e(DeviceDetailInfo deviceDetailInfo, int i) {
        dcz c2;
        if (deviceDetailInfo == null) {
            LogUtil.h("UpdateDeviceControl", "saveWiFiDevice info is null");
            return;
        }
        if (!ctm.b(deviceDetailInfo.getDevInfo().getProdId())) {
            LogUtil.h("UpdateDeviceControl", "saveWiFiDevice not wifi device");
            return;
        }
        cpw.a(false, "UpdateDeviceControl", "saveWiFiDevice deviceId", cpw.d(deviceDetailInfo.getDevId()));
        dkz e2 = DeviceListManager.c(cpp.a()).e(deviceDetailInfo.getDevInfo().getProdId());
        if (e2 != null) {
            c2 = c(e2.d);
        } else {
            LogUtil.a("UpdateDeviceControl", "saveWiFiDevice table is null and ProdId:", deviceDetailInfo.getDevInfo().getProdId());
            String t = cpa.t(deviceDetailInfo.getDevInfo().getProdId());
            if (TextUtils.isEmpty(t)) {
                LogUtil.h("UpdateDeviceControl", "saveWiFiDevice productId is null");
                c2 = null;
            } else {
                c2 = c(t);
            }
        }
        a(c2, deviceDetailInfo, i);
    }

    private void a(dcz dczVar, final DeviceDetailInfo deviceDetailInfo, final int i) {
        if (dczVar == null || deviceDetailInfo == null) {
            LogUtil.h("UpdateDeviceControl", "bindDevice product or info is null");
            return;
        }
        boolean z = ceo.d().c(deviceDetailInfo.getDevInfo() != null ? deviceDetailInfo.getDevInfo().getSn() : "", false) != null;
        LogUtil.a("UpdateDeviceControl", "bindDevice isExist ", Boolean.valueOf(z));
        ctk ctkVar = new ctk();
        ctkVar.a(deviceDetailInfo);
        ctkVar.setAutoDevice(false);
        ctkVar.setKind(HealthDevice.HealthDeviceKind.HDK_WEIGHT);
        ctkVar.setProductId(dczVar.t());
        ctkVar.b().d(i);
        LogUtil.a("UpdateDeviceControl", "bindDevice source ", Integer.valueOf(ctkVar.b().k()));
        IDeviceEventHandler iDeviceEventHandler = new IDeviceEventHandler() { // from class: csb.7
            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onDeviceFound(HealthDevice healthDevice) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onScanFailed(int i2) {
            }

            @Override // com.huawei.health.device.callback.IDeviceEventHandler
            public void onStateChanged(int i2) {
                LogUtil.a("UpdateDeviceControl", "onStateChanged code ", Integer.valueOf(i2));
                if (i == 1) {
                    csb.this.a(deviceDetailInfo.getDevId());
                }
            }
        };
        if (z) {
            LogUtil.a("UpdateDeviceControl", "bindDevice isSuccess ", Boolean.valueOf(cjx.e().a(dczVar.t(), dczVar.s(), ctkVar, iDeviceEventHandler)));
        } else {
            LogUtil.a("UpdateDeviceControl", "bindDevice isSuccess ", Boolean.valueOf(cjx.e().b(dczVar.t(), dczVar.s(), ctkVar, iDeviceEventHandler)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HealthDevice e2 = cjx.e().e(str);
        boolean d2 = ctv.d(BaseApplication.getContext());
        cpw.c(false, "UpdateDeviceControl", "wifi: device: ", e2, ", network connect: ", Boolean.valueOf(d2));
        if (e2 != null && (e2 instanceof ctk) && d2) {
            csc.d().d(str);
            csf.c(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(List<DeviceDetailInfo> list, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UpdateDeviceControl", "isNeedDownloadResource illegal uniqueId true");
            return true;
        }
        if (koq.b(list)) {
            return false;
        }
        LogUtil.a("UpdateDeviceControl", "isNeedDownloadResource, infos size:", Integer.valueOf(list.size()));
        Iterator<DeviceDetailInfo> it = list.iterator();
        while (it.hasNext()) {
            DevInfo devInfo = it.next().getDevInfo();
            if (devInfo != null && !TextUtils.isEmpty(devInfo.getProdId()) && str.equals(devInfo.getSn())) {
                return true;
            }
        }
        return false;
    }

    private boolean e(DeviceDetailInfo deviceDetailInfo) {
        if (deviceDetailInfo == null || deviceDetailInfo.getDevInfo() == null) {
            LogUtil.h("UpdateDeviceControl", "isValidWiFiDeviceInfo deviceDetailInfo is null");
            return false;
        }
        if (TextUtils.isEmpty(deviceDetailInfo.getDevId()) || koq.b(deviceDetailInfo.getServices())) {
            LogUtil.h("UpdateDeviceControl", "isValidWiFiDeviceInfo devceId or service is null or empty");
            return false;
        }
        DevInfo devInfo = deviceDetailInfo.getDevInfo();
        if (TextUtils.isEmpty(devInfo.getSn()) || TextUtils.isEmpty(devInfo.getModel()) || TextUtils.isEmpty(devInfo.getDevType())) {
            LogUtil.h("UpdateDeviceControl", "isValidWiFiDeviceInfo illegal device info");
            return false;
        }
        if (!TextUtils.isEmpty(devInfo.getManu()) && !TextUtils.isEmpty(devInfo.getProdId()) && !TextUtils.isEmpty(devInfo.getHiv())) {
            return true;
        }
        LogUtil.h("UpdateDeviceControl", "isValidWiFiDeviceInfo illegal device info");
        return false;
    }

    private void s() {
        if (this.g == null) {
            LogUtil.h("UpdateDeviceControl", "sendOtaUrlWithWifiProfile mDeviceInfoList is null");
            return;
        }
        LogUtil.a("UpdateDeviceControl", "[grs][syncOtaUrl] is not same user.begin check grs");
        for (DeviceDetailInfo deviceDetailInfo : this.g) {
            String prodId = deviceDetailInfo.getDevInfo().getProdId();
            if (TextUtils.isEmpty(prodId)) {
                LogUtil.a("UpdateDeviceControl", "[grs][syncOtaUrl] productId is null.");
                return;
            } else if (!TextUtils.equals("M00D", prodId)) {
                LogUtil.a("UpdateDeviceControl", "[grs][syncOtaUrl] is not a herm.");
                return;
            } else {
                String devId = deviceDetailInfo.getDevId();
                LogUtil.a("UpdateDeviceControl", "[grs][syncOtaUrl] begin sendOtaUrlWithWifi:", devId);
                csf.e(devId, new ICloudOperationResult<Object>() { // from class: csb.10
                    @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                    public void operationResult(Object obj, String str, boolean z) {
                        if (z) {
                            LogUtil.a("UpdateDeviceControl", "[grs][syncOtaUrl] syncGrsUrlSuccess.", str);
                        } else {
                            LogUtil.a("UpdateDeviceControl", "[grs][syncOtaUrl] syncGrsUrlFailed.", str);
                        }
                    }
                });
            }
        }
    }
}

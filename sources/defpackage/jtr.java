package defpackage;

import android.os.RemoteException;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class jtr implements ParserInterface {

    /* renamed from: a, reason: collision with root package name */
    private static jtr f14081a;
    private DeviceInfo e = null;
    private final HwDeviceMgrInterface i;
    private jtt j;
    private static final Object d = new Object();
    private static final Object b = new Object();
    private static Map<Integer, List<IBaseResponseCallback>> c = new HashMap(16);

    private jtr() {
        HwDeviceMgrInterface b2 = jsz.b(BaseApplication.getContext());
        this.i = b2;
        if (b2 == null) {
            LogUtil.h("HiHealthManager", "mHwDeviceMgr is null");
        }
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            LogUtil.h("HiHealthManager", "data illegal");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 2) {
            jtt c2 = jtt.c();
            this.j = c2;
            c2.e(bArr);
        } else if (b2 == 3) {
            b(bArr);
        } else {
            LogUtil.h("HiHealthManager", "getResult nothing to do");
        }
    }

    public static jtr b() {
        jtr jtrVar;
        LogUtil.a("HiHealthManager", "enter HiHealthManager");
        synchronized (d) {
            if (f14081a == null) {
                f14081a = new jtr();
            }
            jtrVar = f14081a;
        }
        return jtrVar;
    }

    public void e() {
        jtt jttVar = this.j;
        if (jttVar != null) {
            jttVar.d();
        }
        d();
    }

    private static void d() {
        synchronized (d) {
            f14081a = null;
        }
    }

    private boolean c() {
        this.e = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HiHealthManager");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    this.e = next;
                    break;
                }
            }
        }
        DeviceInfo deviceInfo = this.e;
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
            LogUtil.a("HiHealthManager", "device connected");
            return true;
        }
        LogUtil.h("HiHealthManager", "device not connected");
        return false;
    }

    private boolean a() {
        DeviceInfo deviceInfo = this.e;
        if (deviceInfo == null) {
            LogUtil.h("HiHealthManager", "isSupportDriverRemind mCurrentDeviceInfo is null");
            return false;
        }
        DeviceCapability e = e(deviceInfo);
        boolean isSupportHiCarDriverRemind = e != null ? e.isSupportHiCarDriverRemind() : false;
        LogUtil.a("HiHealthManager", "get Device Support isSupportDriverRemind :", Boolean.valueOf(isSupportHiCarDriverRemind));
        return isSupportHiCarDriverRemind;
    }

    private boolean g() {
        DeviceCapability e = e(this.e);
        boolean isSupportWearStatus = e != null ? e.isSupportWearStatus() : false;
        LogUtil.a("HiHealthManager", "get Device Support isSupportDriverRemind :", Boolean.valueOf(isSupportWearStatus));
        return isSupportWearStatus;
    }

    private DeviceCapability e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = this.i.queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "HiHealthManager");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("HiHealthManager", "getCapability deviceCapabilityHashMaps is null");
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    private void d(int i, List<Integer> list) {
        String e;
        if (i < 0 || i > 4) {
            LogUtil.h("HiHealthManager", "remind type is invaluable :", Integer.valueOf(i));
            return;
        }
        if (list == null || list.isEmpty() || list.size() != 5) {
            LogUtil.h("HiHealthManager", "vibration effect is invaluable");
            return;
        }
        LogUtil.a("HiHealthManager", "enter wrapTLV process");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(46);
        deviceCommand.setCommandID(1);
        LogUtil.a("HiHealthManager", "vibration effect is valuable :", Integer.valueOf(list.size()));
        String d2 = cvx.d(1);
        String d3 = cvx.d(2);
        String e2 = cvx.e(1);
        String e3 = cvx.e(i);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e2);
        sb.append(d2);
        sb.append(e3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            String e4 = cvx.e(i2 + 2);
            if (i2 == 0 || i2 == 3) {
                e = cvx.e(list.get(i2).intValue());
            } else {
                e = cvx.a(list.get(i2).intValue());
            }
            sb.append(e4);
            if (i2 == 0 || i2 == 3) {
                sb.append(d2);
            } else {
                sb.append(d3);
            }
            sb.append(e);
        }
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        this.i.sendDeviceData(deviceCommand);
        LogUtil.a("HiHealthManager", "send tlv successfully");
    }

    public void a(String str, IBaseCommonCallback iBaseCommonCallback) {
        if (!NotificationContentProviderUtil.e()) {
            LogUtil.h("HiHealthManager", "the notification is close");
            return;
        }
        if (!c()) {
            LogUtil.h("HiHealthManager", "the device is not connected");
            return;
        }
        if (!a()) {
            LogUtil.h("HiHealthManager", "the device is not support HiCar");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("content");
            LogUtil.a("HiHealthManager", "the content is :", Integer.valueOf(optInt));
            JSONObject jSONObject2 = jSONObject.getJSONObject("vibration");
            LogUtil.a("HiHealthManager", " the vibration is :", jSONObject2);
            ArrayList arrayList = new ArrayList(16);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                LogUtil.c("HiHealthManager", "the vibration key is :", next);
                int optInt2 = jSONObject2.optInt(next);
                LogUtil.c("HiHealthManager", "the vibration value is :", Integer.valueOf(optInt2));
                arrayList.add(Integer.valueOf(optInt2));
            }
            if (optInt == 1) {
                d(optInt, arrayList);
                LogUtil.a("HiHealthManager", "pushDeviceCommand message successfully");
            } else {
                iBaseCommonCallback.onResponse(100001, VastAttribute.ERROR);
            }
        } catch (RemoteException unused) {
            LogUtil.b("HiHealthManager", "checkMessageContent RemoteException");
            sqo.s("checkMessageContent RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("HiHealthManager", "hicarOperate JSONException");
            sqo.s("json parse failed");
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HiHealthManager", "queryWearStatus callback is null.");
            return;
        }
        if (!c()) {
            LogUtil.h("HiHealthManager", "Device supporting wear status is not connected..");
            iBaseResponseCallback.d(-2, "Device supporting wear status is not connected.");
            return;
        }
        if (!g()) {
            LogUtil.h("HiHealthManager", "capability is not support.");
            iBaseResponseCallback.d(-3, "capability is not support.");
            return;
        }
        LogUtil.a("HiHealthManager", "enter requryWearStatus tlv process.");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(46);
        deviceCommand.setCommandID(3);
        String e = cvx.e(1);
        String e2 = cvx.e(0);
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(e2);
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        deviceCommand.setNeedAck(true);
        a(3, iBaseResponseCallback);
        this.i.sendDeviceData(deviceCommand);
        LogUtil.a("HiHealthManager", "send tlv successfully.");
    }

    private void a(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (b) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = c.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(16);
                    c.put(Integer.valueOf(i), list);
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(byte[] r10) {
        /*
            r9 = this;
            java.lang.String r0 = "processGetSwitchStatus enter."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HiHealthManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.lang.String r10 = defpackage.cvx.d(r10)
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto Lb6
            int r0 = r10.length()
            r2 = 4
            if (r0 >= r2) goto L1f
            goto Lb6
        L1f:
            java.lang.String r10 = r10.substring(r2)
            r0 = 3
            r2 = 0
            r3 = -1
            cwl r4 = new cwl     // Catch: defpackage.cwg -> L97
            r4.<init>()     // Catch: defpackage.cwg -> L97
            cwe r10 = r4.a(r10)     // Catch: defpackage.cwg -> L97
            java.util.List r10 = r10.e()     // Catch: defpackage.cwg -> L97
            r4 = 1
            if (r10 == 0) goto L8c
            int r5 = r10.size()     // Catch: defpackage.cwg -> L97
            if (r5 != 0) goto L3d
            goto L8c
        L3d:
            java.util.Iterator r10 = r10.iterator()     // Catch: defpackage.cwg -> L97
            r5 = r3
        L42:
            boolean r6 = r10.hasNext()     // Catch: defpackage.cwg -> L98
            if (r6 == 0) goto La2
            java.lang.Object r6 = r10.next()     // Catch: defpackage.cwg -> L98
            cwd r6 = (defpackage.cwd) r6     // Catch: defpackage.cwg -> L98
            java.lang.String r7 = r6.e()     // Catch: defpackage.cwg -> L98
            int r7 = health.compact.a.CommonUtil.w(r7)     // Catch: defpackage.cwg -> L98
            if (r7 == r4) goto L83
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 == r8) goto L67
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L98
            java.lang.String r7 = "no support tag"
            r6[r2] = r7     // Catch: defpackage.cwg -> L98
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)     // Catch: defpackage.cwg -> L98
            goto L42
        L67:
            java.lang.String r10 = r6.c()     // Catch: defpackage.cwg -> L98
            int r10 = health.compact.a.CommonUtil.w(r10)     // Catch: defpackage.cwg -> L98
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: defpackage.cwg -> L98
            java.lang.String r7 = "5.46.3 errorCode is : "
            r6[r2] = r7     // Catch: defpackage.cwg -> L98
            java.lang.Integer r7 = java.lang.Integer.valueOf(r10)     // Catch: defpackage.cwg -> L98
            r6[r4] = r7     // Catch: defpackage.cwg -> L98
            com.huawei.hwlogsmodel.LogUtil.h(r1, r6)     // Catch: defpackage.cwg -> L98
            r9.c(r0, r3, r10)     // Catch: defpackage.cwg -> L98
            return
        L83:
            java.lang.String r6 = r6.c()     // Catch: defpackage.cwg -> L98
            int r5 = health.compact.a.CommonUtil.w(r6)     // Catch: defpackage.cwg -> L98
            goto L42
        L8c:
            java.lang.Object[] r10 = new java.lang.Object[r4]     // Catch: defpackage.cwg -> L97
            java.lang.String r4 = "tlvList is error."
            r10[r2] = r4     // Catch: defpackage.cwg -> L97
            com.huawei.hwlogsmodel.LogUtil.h(r1, r10)     // Catch: defpackage.cwg -> L97
            return
        L97:
            r5 = r3
        L98:
            java.lang.String r10 = "processGetSwitchStatus tlv exception."
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r10)
        La2:
            java.lang.String r10 = "processGetSwitchStatus callback."
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r10)
            if (r5 != r3) goto Lb2
            r9.c(r0, r3, r5)
            goto Lb5
        Lb2:
            r9.c(r0, r2, r5)
        Lb5:
            return
        Lb6:
            java.lang.String r10 = "processWearStatus tlvString is error."
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jtr.b(byte[]):void");
    }

    private void c(int i, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i3);
        } catch (JSONException unused) {
            LogUtil.b("HiHealthManager", "processGetSwitchStatus JSONException.");
        }
        a(i, i2, jSONObject.toString());
    }

    private void a(int i, int i2, Object obj) {
        synchronized (b) {
            List<IBaseResponseCallback> list = c.get(Integer.valueOf(i));
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
}

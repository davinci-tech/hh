package defpackage;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.HiAppInfo;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import health.compact.a.CommonUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jtt {
    private static final Object b = new Object();
    private static jtt e;

    /* renamed from: a, reason: collision with root package name */
    private String f14082a;
    private DeviceInfo c;
    private DeviceCapability d;
    private HwDeviceMgrInterface g;
    private Map<String, IBaseCommonCallback> f = new HashMap(16);
    private String j = null;
    private int i = -1;
    private cwl h = new cwl();

    private jtt() {
        HwDeviceMgrInterface b2 = jsz.b(BaseApplication.getContext());
        this.g = b2;
        if (b2 == null) {
            LogUtil.h("MessageFeedbackManager", "mHwDeviceMgr is null");
        }
    }

    public static jtt c() {
        jtt jttVar;
        LogUtil.a("MessageFeedbackManager", "enter MessageFeedbackManager ");
        synchronized (b) {
            if (e == null) {
                e = new jtt();
            }
            jttVar = e;
        }
        return jttVar;
    }

    public void d() {
        b();
    }

    private static void b() {
        synchronized (b) {
            e = null;
        }
    }

    private boolean a() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "MessageFeedbackManager");
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    this.c = next;
                    break;
                }
            }
        }
        if (this.c != null) {
            LogUtil.a("MessageFeedbackManager", "device connected");
            return true;
        }
        LogUtil.a("MessageFeedbackManager", "device not connected");
        return false;
    }

    private boolean f() {
        DeviceInfo deviceInfo = this.c;
        if (deviceInfo == null) {
            LogUtil.h("MessageFeedbackManager", "isSupportMessageFeedback mDeviceInfo is null");
            return false;
        }
        Map<String, DeviceCapability> queryDeviceCapability = this.g.queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "MessageFeedbackManager");
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            this.d = queryDeviceCapability.get(this.c.getDeviceIdentify());
        }
        DeviceCapability deviceCapability = this.d;
        boolean isSupportMessageFeedback = deviceCapability != null ? deviceCapability.isSupportMessageFeedback() : false;
        LogUtil.a("MessageFeedbackManager", "get Device Support isSupportMessageFeedback :", Boolean.valueOf(isSupportMessageFeedback));
        return isSupportMessageFeedback;
    }

    public void d(HiAppInfo hiAppInfo, String str, IBaseCommonCallback iBaseCommonCallback) {
        try {
            if (!a()) {
                iBaseCommonCallback.onResponse(100001, "noDeviceConnect");
                return;
            }
            if (!f()) {
                iBaseCommonCallback.onResponse(100001, "capabilityNotSupport");
                return;
            }
            String appName = hiAppInfo.getAppName();
            LogUtil.a("MessageFeedbackManager", " the app name is:", appName);
            JSONObject jSONObject = new JSONObject(str);
            this.f.put(jSONObject.optString("remindID"), iBaseCommonCallback);
            ArrayList arrayList = new ArrayList(5);
            arrayList.add(appName);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                LogUtil.a("MessageFeedbackManager", " the message key is:", next);
                String optString = jSONObject.optString(next);
                LogUtil.a("MessageFeedbackManager", " the message value is:", optString);
                arrayList.add(optString);
            }
            if (!arrayList.isEmpty() && arrayList.size() == 5) {
                d(arrayList);
                return;
            }
            LogUtil.h("MessageFeedbackManager", "messageList is illegal");
            iBaseCommonCallback.onResponse(100001, "messageInputError");
        } catch (RemoteException unused) {
            LogUtil.b("MessageFeedbackManager", "checkMessageContent RemoteException");
        } catch (JSONException unused2) {
            LogUtil.b("MessageFeedbackManager", "checkMessageContent JSONException");
        }
    }

    private void d(List<String> list) {
        LogUtil.a("MessageFeedbackManager", "enter wrapTLV process");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(46);
        deviceCommand.setCommandID(2);
        String d = cvx.d(1);
        String e2 = cvx.e(1);
        String e3 = cvx.e(100);
        StringBuilder sb = new StringBuilder(18);
        sb.append(e2);
        sb.append(d);
        sb.append(e3);
        for (int i = 0; i < list.size(); i++) {
            sb.append(cvx.e(i + 2));
            sb.append(a(list)[1][i]);
            sb.append(a(list)[0][i]);
        }
        deviceCommand.setDataLen(sb.length() / 2);
        deviceCommand.setDataContent(cvx.a(sb.toString()));
        this.g.sendDeviceData(deviceCommand);
        LogUtil.a("MessageFeedbackManager", "send tlv successfully");
    }

    private String[][] a(List<String> list) {
        String[][] strArr = (String[][]) Array.newInstance((Class<?>) String.class, 2, 5);
        for (int i = 0; i < list.size(); i++) {
            String c = cvx.c(list.get(i));
            String d = cvx.d(c.length() / 2);
            strArr[0][i] = c;
            strArr[1][i] = d;
        }
        return strArr;
    }

    public void e(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("MessageFeedbackManager", "handleUserOperationReport is:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("MessageFeedbackManager", "handleUserOperationReport dataHex is error");
            return;
        }
        if (bArr[1] == 2) {
            b(d.substring(4));
            try {
                if (!TextUtils.isEmpty(this.j) && this.i != -1) {
                    IBaseCommonCallback iBaseCommonCallback = this.f.get(this.j);
                    String e2 = e();
                    this.f14082a = e2;
                    if (!"".equals(e2) && iBaseCommonCallback != null) {
                        iBaseCommonCallback.onResponse(100000, this.f14082a);
                    } else {
                        LogUtil.h("MessageFeedbackManager", "handleUserOperationReport callback is null");
                    }
                }
                return;
            } catch (RemoteException unused) {
                LogUtil.b("MessageFeedbackManager", "handleUserOperationReport RemoteException");
                return;
            }
        }
        LogUtil.h("MessageFeedbackManager", "handleUserOperationReport is illegal");
    }

    private void b(String str) {
        try {
            List<cwd> e2 = this.h.a(str).e();
            if (e2 == null || e2.size() <= 0) {
                return;
            }
            for (cwd cwdVar : e2) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 3) {
                    String e3 = cvx.e(cwdVar.c());
                    this.j = e3;
                    LogUtil.a("MessageFeedbackManager", "handleUserOperationReport mResultId is:", e3);
                } else if (w == 7) {
                    int w2 = CommonUtil.w(cwdVar.c());
                    this.i = w2;
                    LogUtil.a("MessageFeedbackManager", "handleUserOperationReport mResultValue is:", Integer.valueOf(w2));
                } else {
                    LogUtil.a("MessageFeedbackManager", "handleUserOperationReport default");
                }
            }
        } catch (cwg unused) {
            LogUtil.b("MessageFeedbackManager", "handleUserOperationReport TlvException");
        } catch (IndexOutOfBoundsException unused2) {
            LogUtil.b("MessageFeedbackManager", "handleUserOperationReport IndexOutOfBoundsException");
        } catch (NumberFormatException unused3) {
            LogUtil.b("MessageFeedbackManager", "handleUserOperationReport NumberFormatException");
        }
    }

    private String e() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("remindID", this.j);
            jSONObject.put("remindFeedback", this.i);
            LogUtil.h("MessageFeedbackManager", "getResponseString is:", jSONObject.toString());
            this.j = null;
            this.i = -1;
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.b("MessageFeedbackManager", TrackConstants$Events.EXCEPTION);
            LogUtil.a("MessageFeedbackManager", "getResponseString is empty");
            return "";
        }
    }
}

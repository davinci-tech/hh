package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.text.TextUtils;
import com.huawei.datatype.DataDeviceIntelligentInfo;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jff {
    private static final Object e = new Object();
    private Context d;

    private jff() {
        this.d = BaseApplication.getContext();
    }

    public static jff e() {
        return b.c;
    }

    public void bGT_(Intent intent) {
        DeviceInfo deviceInfo;
        if (intent == null) {
            LogUtil.h("ConfigMgrConnectState", "intent is null");
            return;
        }
        try {
            deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
        } catch (BadParcelableException unused) {
            LogUtil.b("ConfigMgrConnectState", "handleConnectionStateChanged() occur BadParcelableException");
            deviceInfo = null;
        }
        if (deviceInfo == null) {
            LogUtil.h("ConfigMgrConnectState", "handleConnectionStateChanged() deviceInfo is null");
            return;
        }
        kxz.c(BaseApplication.getContext(), deviceInfo);
        kxz.a(BaseApplication.getContext(), deviceInfo);
        LogUtil.a("ConfigMgrConnectState", "handleConnectionStateChanged() ConnectState():", Integer.valueOf(deviceInfo.getDeviceConnectState()));
        jqh.c(deviceInfo);
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        if (deviceConnectState == 2) {
            e(deviceInfo);
        } else if (deviceConnectState == 3) {
            h(deviceInfo);
        } else {
            LogUtil.b("ConfigMgrConnectState", "handleConnectionStateChanged() enter default branch. connect state is invalid");
        }
        jpn.a(1, false);
    }

    private void e(DeviceInfo deviceInfo) {
        jfu.e(this.d);
        d(deviceInfo);
        jez.b();
        a();
        c();
        LogUtil.a("ConfigMgrConnectState", "getHwMusicAccountSendToWear when luetooth connect");
        if (System.currentTimeMillis() - SharedPreferenceManager.b(Integer.toString(10000), "setMusicInfo" + deviceInfo.getSecurityDeviceId(), 0L) >= 86400000) {
            LogUtil.a("ConfigMgrConnectState", "start getHwMusicAccountSendToWear");
            jki.d();
            jki.c(this.d, true);
        }
        String e2 = kxz.e(deviceInfo.getSecurityDeviceId(), this.d);
        LogUtil.a("ConfigMgrConnectState", "processDeviceConnected packageName ", e2, " deviceInfo.getDeviceOtaPackageName()", deviceInfo.getDeviceOtaPackageName());
        if (TextUtils.isEmpty(e2) || !e2.equals(deviceInfo.getDeviceOtaPackageName())) {
            kxz.k(deviceInfo.getSecurityDeviceId(), deviceInfo.getDeviceOtaPackageName(), this.d);
        }
        g(deviceInfo);
        b(deviceInfo);
        joq.b().d();
    }

    private void d(final DeviceInfo deviceInfo) {
        LogUtil.a("ConfigMgrConnectState", "Enter generateConnectedMessage method");
        if (deviceInfo == null) {
            LogUtil.h("ConfigMgrConnectState", "Enter generateConnectedMessage btDeviceInfo is null");
            return;
        }
        final MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        if (messageCenterApi == null) {
            LogUtil.h("ConfigMgrConnectState", "messageCenterApi is null");
        } else {
            messageCenterApi.getMessages(String.valueOf(19), "device_type_connected", new IBaseResponseCallback() { // from class: jfj
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jff.this.e(deviceInfo, messageCenterApi, i, obj);
                }
            });
        }
    }

    /* synthetic */ void e(final DeviceInfo deviceInfo, final MessageCenterApi messageCenterApi, int i, Object obj) {
        LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage errorCode :", Integer.valueOf(i));
        if (i != 0 || !(obj instanceof List)) {
            LogUtil.h("ConfigMgrConnectState", "generateConnectedMessage,getMessages failed");
            return;
        }
        final List list = (List) obj;
        LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, delete messageList, messageList.size() :", Integer.valueOf(list.size()));
        jdx.b(new Runnable() { // from class: jfn
            @Override // java.lang.Runnable
            public final void run() {
                jff.this.d(deviceInfo, list, messageCenterApi);
            }
        });
    }

    /* synthetic */ void d(DeviceInfo deviceInfo, List list, MessageCenterApi messageCenterApi) {
        if (CommonUtil.ar() || CommonUtil.bf()) {
            LogUtil.a("ConfigMgrConnectState", "initHiAiEngine");
            bzo.c().initHiAiEngine();
        }
        if (!a(deviceInfo)) {
            LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, isNeedGenerateMessage false");
            return;
        }
        if (d()) {
            LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, isConnectLastTwoDevice");
            return;
        }
        if (c(deviceInfo) && !e(deviceInfo, list)) {
            LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, isDeviceIdEquals");
            return;
        }
        f(deviceInfo);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            messageCenterApi.deleteMessage(((MessageObject) it.next()).getMsgId());
        }
        c(messageCenterApi, deviceInfo);
    }

    private boolean a(DeviceInfo deviceInfo) {
        if (deviceInfo.getProductType() == -2 || deviceInfo.getProductType() == 0 || deviceInfo.getProductType() == 2) {
            LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, W1,B1,AF500 don't need generate message");
            return false;
        }
        if (deviceInfo.getProductType() != 4 && deviceInfo.getProductType() != -1 && deviceInfo.getProductType() != 9) {
            return jfu.c(deviceInfo.getProductType()).z();
        }
        LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, N1，W2，don't need generate message");
        return false;
    }

    private boolean d() {
        String b2 = SharedPreferenceManager.b(this.d, String.valueOf(1), "double_device_connect");
        if (!TextUtils.isEmpty(b2) && b2.contains("%")) {
            DeviceInfo d = jpu.d("ConfigMgrConnectState");
            DeviceInfo a2 = jpt.a("ConfigMgrConnectState");
            boolean z = d != null && d.getDeviceConnectState() == 2 && b2.contains(d.getSecurityDeviceId());
            boolean z2 = a2 != null && a2.getDeviceConnectState() == 2 && b2.contains(a2.getSecurityDeviceId());
            if (z && z2) {
                LogUtil.a("ConfigMgrConnectState", "Connect the same device in last two devices");
                return true;
            }
        }
        return false;
    }

    private boolean c(DeviceInfo deviceInfo) {
        String b2 = SharedPreferenceManager.b(this.d, String.valueOf(1), "kStorage_DeviceCfgMgr_Identify");
        if (deviceInfo.getSecurityDeviceId() == null) {
            LogUtil.h("ConfigMgrConnectState", "securityDeviceId is null ");
            return false;
        }
        LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, getDeviceIdentify():", iyl.d().e(deviceInfo.getSecurityDeviceId()), ", getSharedPreference deviceIdentify:", iyl.d().e(b2));
        if (!deviceInfo.getSecurityDeviceId().equalsIgnoreCase(b2)) {
            return false;
        }
        LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage, same device, don't need generate message...");
        return true;
    }

    private boolean e(DeviceInfo deviceInfo, List<MessageObject> list) {
        Map<String, String> r = deviceInfo != null ? jfu.c(deviceInfo.getProductType()).r() : null;
        LogUtil.a("ConfigMgrConnectState", "isHonorAndNeedMessage isHonorDevice:", Boolean.valueOf(cvz.c(deviceInfo)), " honorHelpUrlMap:", r, " messageList size:", Integer.valueOf(list.size()));
        return cvz.c(deviceInfo) && r != null && list.size() == 0;
    }

    private void f(DeviceInfo deviceInfo) {
        DeviceInfo e2 = jpu.e("ConfigMgrConnectState");
        DeviceInfo d = jpt.d("ConfigMgrConnectState");
        if (d != null && e2 != null) {
            c("double_device_connect", d.getSecurityDeviceId() + "%" + e2.getSecurityDeviceId(), null);
            c("kStorage_DeviceCfgMgr_Identify", "", null);
            return;
        }
        c("kStorage_DeviceCfgMgr_Identify", deviceInfo.getSecurityDeviceId(), null);
    }

    private void c(final MessageCenterApi messageCenterApi, final DeviceInfo deviceInfo) {
        messageCenterApi.getMessageId(String.valueOf(19), "device_type_connected", new IBaseResponseCallback() { // from class: jff.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage requestMessageId errorCode :", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof String)) {
                    LogUtil.h("ConfigMgrConnectState", "generateConnectedMessage requestMessageId failed");
                    return;
                }
                String str = (String) obj;
                LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage messageId:", str);
                String string = jff.this.d.getString(R$string.IDS_messagecenter_device_bind_success_title);
                String deviceName = deviceInfo.getDeviceName();
                DeviceInfo a2 = jpt.a("ConfigMgrConnectState");
                if (TextUtils.isEmpty(deviceName) && a2 != null) {
                    deviceName = a2.getDeviceName();
                }
                if (TextUtils.isEmpty(deviceName)) {
                    deviceName = "";
                }
                String format = String.format(Locale.ENGLISH, string, deviceName);
                MessageObject a3 = jff.this.a(str);
                a3.setMsgTitle(format);
                a3.setMetadata(format);
                LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage mstTitle:", format);
                a3.setMsgContent("");
                LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage mstContent:", "");
                a3.setCreateTime(System.currentTimeMillis());
                a3.setExpireTime(0L);
                a3.setReadFlag(0);
                if (deviceInfo.getProductType() < 0) {
                    LogUtil.a("ConfigMgrConnectState", "useless product type");
                    return;
                }
                if (cvt.c(deviceInfo.getProductType())) {
                    a3.setDetailUri("messagecenter://aw70_device_guide?key=" + deviceInfo.getProductType());
                } else {
                    a3.setDetailUri("messagecenter://device_guide?key=" + deviceInfo.getProductType() + "&hilink=" + deviceInfo.getHiLinkDeviceId());
                }
                a3.setImgUri("assets://localMessageIcon/ic_band_connected.webp");
                messageCenterApi.insertMessage(a3);
                LogUtil.a("ConfigMgrConnectState", "generateConnectedMessage leave");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MessageObject a(String str) {
        MessageObject messageObject = new MessageObject();
        messageObject.setMsgId(str);
        messageObject.setModule(String.valueOf(19));
        messageObject.setType("device_type_connected");
        messageObject.setMsgType(2);
        messageObject.setPosition(1);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(SharedPreferenceUtil.getInstance(this.d).getUserID());
        return messageObject;
    }

    private int c(String str, String str2, StorageParams storageParams) {
        return SharedPreferenceManager.e(this.d, String.valueOf(1), str, str2, storageParams);
    }

    private void a() {
        LogUtil.a("ConfigMgrConnectState", "sendBroadcast auto_send_command. start");
        new jfe().a();
    }

    private void c() {
        jjd.b(BaseApplication.getContext()).c(jjd.b(BaseApplication.getContext()).c(), false);
    }

    private void g(DeviceInfo deviceInfo) {
        if (!cwi.c(deviceInfo, 27)) {
            LogUtil.h("ConfigMgrConnectState", "device not support ecg nmpa");
        } else {
            jfz.d().d(jfz.d().a(), new IBaseResponseCallback() { // from class: jfl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("ConfigMgrConnectState", "setDefaultEcgDuration errorCode ", Integer.valueOf(i));
                }
            });
        }
    }

    private void b(DeviceInfo deviceInfo) {
        if (!cwi.c(deviceInfo, 45)) {
            LogUtil.h("ConfigMgrConnectState", "device not support get ecg iv");
        } else {
            jgc.a().c(deviceInfo);
        }
    }

    private void h(DeviceInfo deviceInfo) {
        jkv.b().b(deviceInfo);
        synchronized (e) {
            if (cvt.c(deviceInfo.getProductType())) {
                jot.a().d();
            } else {
                jot.a().c();
            }
            jog.c().a((DataDeviceIntelligentInfo) null);
        }
        jfh.b();
        joq.b().c();
    }

    static class b {
        private static jff c = new jff();
    }
}

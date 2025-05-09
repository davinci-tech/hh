package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.DataPromptData;
import com.huawei.hwcommonmodel.datatypes.MsgImage;
import com.huawei.hwcommonmodel.datatypes.MsgText;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import com.huawei.up.model.UserInfomation;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.wearmgr.outofprocess.datatype.BluetoothType;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class khu implements ParserInterface {
    private static khu b = null;
    private static int c = 0;
    private static int e = 127;
    private iyl i;
    private boolean q;
    private long u;
    private ExtendHandler w;
    private long y;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14377a = new Object();
    private DeviceCommand o = null;
    private int v = 0;
    private int h = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
    private boolean s = true;
    private boolean t = false;
    private int z = 30;
    private int f = 30;
    private int l = 30;
    private boolean x = false;
    private boolean p = false;
    private boolean r = false;
    private int ab = 2;
    private int g = 2;
    private int n = 2;
    private d m = new d();
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: khu.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo deviceInfo;
            if (intent == null) {
                LogUtil.h("NotifySendData", "mConnectStateChangedReceiver intent is null");
                return;
            }
            String str = (String) Objects.requireNonNull(intent.getAction());
            str.hashCode();
            if (str.equals("android.intent.action.LOCALE_CHANGED")) {
                LogUtil.a("NotifySendData", "ACTION_LOCALE_CHANGED");
                khg.d().e();
                return;
            }
            if (str.equals("com.huawei.bone.action.CONNECTION_STATE_CHANGED")) {
                khu.this.q = true;
                try {
                    deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                } catch (ClassCastException unused) {
                    LogUtil.b("NotifySendData", "ClassCastException");
                    deviceInfo = null;
                }
                if (deviceInfo == null) {
                    LogUtil.h("NotifySendData", "mConnectStateChangedReceiver deviceInfo is null");
                    return;
                }
                LogUtil.a("NotifySendData", "mConnectStateChangedReceiver ConnectState is: ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                if (deviceConnectState == 2) {
                    khu.this.c(deviceInfo);
                } else if (deviceConnectState == 3) {
                    if (khu.this.l() == null) {
                        LogUtil.a("NotifySendData", "connectedDevice is null, delete contact_wear");
                        NotificationContentProviderUtil.b("connect_wear", 0);
                    }
                    khu.this.i();
                }
                NotificationContentProviderUtil.e(khu.this.a());
            }
        }
    };
    private BroadcastReceiver ac = new BroadcastReceiver() { // from class: khu.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("NotifySendData", "mPackageAddReceiver intent is null");
            } else if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                LogUtil.a("NotifySendData", "ACTION_PACKAGE_ADDED");
                khg.d().bNT_(intent);
            }
        }
    };
    private HwDeviceMgrInterface k = jsz.b(BaseApplication.getContext());

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (!khs.i()) {
            SharedPreferenceManager.d("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE");
            SharedPreferenceManager.d("SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY", "SUPPORT_NOTIFY_LIVE_LEVEL_CAPABILITY");
        }
        if (!khs.g()) {
            SharedPreferenceManager.d("SUPPORT_NOTIFY_LIVE_TYPE", "SUPPORT_NOTIFY_LIVE_TYPE");
        }
        LogUtil.a("NotifySendData", "disConnected reset level and capability");
    }

    private khu() {
        this.q = true;
        this.i = null;
        this.i = iyl.d();
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.j, intentFilter, LocalBroadcast.c, null);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addDataScheme("package");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.ac, intentFilter2, LocalBroadcast.c, null);
        DeviceInfo l = l();
        if (l != null) {
            LogUtil.a("NotifySendData", "try constraint when device connected");
            this.q = true;
            DeviceCapability d2 = khs.d(l);
            e(d2, l);
            c(d2, l);
        }
        ExtendHandler yt_ = HandlerCenter.yt_(this.m, "NotifySendData");
        this.w = yt_;
        yt_.sendEmptyMessage(100, 3600000L);
        this.w.sendEmptyMessage(100);
        LogUtil.a("NotifySendData", "start check notification service");
        q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(DeviceInfo deviceInfo) {
        e(khs.d(deviceInfo), deviceInfo);
        SharedPreferenceManager.e("SUPPORT_NOTIFY_VOIP_TYPE", "SUPPORT_NOTIFY_VOIP_TYPE", khs.o());
        kho.b().f();
        if (kke.h()) {
            kke.d();
        }
        khg.d().a();
        khk.c().c(3);
    }

    private void q() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: khu.3
            @Override // java.lang.Runnable
            public void run() {
                khu.this.y = khs.c(BaseApplication.getContext());
                khu.this.u = khs.a(BaseApplication.getContext());
                LogUtil.a("NotifySendData", "initUpdateAndReportTime mNotifyUpdateTime: ", Long.valueOf(khu.this.y), " mNotifyReportBiTime: ", Long.valueOf(khu.this.u));
                if (khu.this.y == 0) {
                    khu.this.y = System.currentTimeMillis();
                    khs.d(BaseApplication.getContext(), khu.this.y);
                }
                if (khu.this.u == 0) {
                    khu.this.u = System.currentTimeMillis();
                    khs.a(BaseApplication.getContext(), khu.this.u);
                }
            }
        });
    }

    public boolean c(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.y >= 3600000) {
            khs.d(BaseApplication.getContext(), currentTimeMillis);
            this.y = currentTimeMillis;
            khs.e(BaseApplication.getContext(), i);
        }
        if (currentTimeMillis - this.u >= 86400000) {
            khs.a(BaseApplication.getContext(), currentTimeMillis);
            this.u = currentTimeMillis;
            khs.b(BaseApplication.getContext());
        }
        return currentTimeMillis - this.y >= 3600000;
    }

    public static khu a(Context context) {
        khu khuVar;
        synchronized (d) {
            if (b == null && context != null) {
                b = new khu();
            }
            khuVar = b;
        }
        return khuVar;
    }

    private void e(DeviceCapability deviceCapability, DeviceInfo deviceInfo) {
        if (deviceCapability == null || deviceInfo == null) {
            LogUtil.h("NotifySendData", "sendCapabilityCommand parameter is null");
            return;
        }
        int isPromptPush = deviceCapability.isPromptPush();
        int c2 = c();
        LogUtil.a("NotifySendData", "sendCapabilityCommand promptPush: ", Integer.valueOf(isPromptPush), " deviceProtocol: ", Integer.valueOf(c2));
        if (isPromptPush == -1 && c2 == 2) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(2);
            deviceCommand.setCommandID(5);
            String str = cvx.e(1) + cvx.e(0);
            deviceCommand.setDataContent(cvx.a(str));
            deviceCommand.setDataLen(cvx.a(str).length);
            deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
            this.k.sendDeviceData(deviceCommand);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    public void e(boolean z, boolean z2, boolean z3, boolean z4) {
        LogUtil.a("NotifySendData", "sendNegotiateMiddleWareCommand isAllStateSend: ", Boolean.valueOf(z4));
        if (z4) {
            LogUtil.a("NotifySendData", "sendNegotiateMiddleWareCommand isNotification: ", Boolean.valueOf(z), " isPrompt: ", Boolean.valueOf(z2), " isClockState: ", Boolean.valueOf(z3));
        } else {
            LogUtil.a("NotifySendData", "sendNegotiateMiddleWareCommand isClockState: ", Boolean.valueOf(z3));
        }
        StringBuilder sb = new StringBuilder(32);
        int i = 0;
        ?? r9 = z;
        if (!z4) {
            r9 = 0;
        } else if (z2) {
            i = 1;
            r9 = z;
        }
        if (z4) {
            sb.append(cvx.e(2));
            sb.append(cvx.e(2));
            sb.append(cvx.e(1));
            sb.append(cvx.e((int) r9));
            sb.append(cvx.e(2));
            sb.append(cvx.e(2));
            sb.append(cvx.e(2));
            sb.append(cvx.e(1));
            sb.append(cvx.e(2));
            sb.append(cvx.e(2));
            sb.append(cvx.e(3));
            sb.append(cvx.e(i));
            boolean a2 = khs.a();
            ReleaseLogUtil.e("R_NotifySendData", "sendNegotiateMiddleWareCommand isSupportClockStateDeal: ", Boolean.valueOf(a2));
            if (a2) {
                sb.append(cvx.e(2));
                sb.append(cvx.e(2));
                sb.append(cvx.e(4));
                sb.append(cvx.e(z3 ? 1 : 0));
            }
        } else {
            sb.append(cvx.e(2));
            sb.append(cvx.e(2));
            sb.append(cvx.e(4));
            sb.append(cvx.e(z3 ? 1 : 0));
        }
        String str = cvx.e(129) + cvx.e(sb.toString().length() / 2) + sb.toString();
        LogUtil.a("NotifySendData", "sendNegotiateMiddleWareCommand packageCommand: ", str);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(33);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataLen(cvx.a(str).length);
        deviceCommand.setDataContent(cvx.a(str));
        this.k.sendDeviceData(deviceCommand);
    }

    public void c(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            LogUtil.h("NotifySendData", "handleCapability dataInfos is null or length < NORMAL_CAPABILITY_LENGTH");
            return;
        }
        byte b2 = bArr[1];
        if (b2 == 5) {
            LogUtil.a("NotifySendData", "handleCapability supportValue: ", Byte.valueOf(bArr[4]));
            byte b3 = bArr[4];
            this.s = ((b3 >> 1) & 1) != 0;
            this.t = ((b3 >> 2) & 1) != 0;
            return;
        }
        if (b2 == 2) {
            try {
                int e2 = d(bArr).e();
                LogUtil.a("NotifySendData", "handleCapability getContentLength: ", Integer.valueOf(e2));
                int i = (e2 / 6) * 6;
                if (i > 0) {
                    this.h = i;
                }
                LogUtil.a("NotifySendData", "handleCapability mContentLength: ", Integer.valueOf(this.h));
                SharedPreferenceManager.b("CONTENT_LENGTH", "CONTENT_LENGTH_SIZE", this.h);
                b(this.h);
                e(bArr);
                return;
            } catch (cwg unused) {
                LogUtil.b("NotifySendData", "handleCapability TlvException");
                return;
            }
        }
        LogUtil.c("NotifySendData", "handleCapability, need not deal");
    }

    private void b(int i) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.ACTION_NOTIFICATION_PUSH");
        intent.putExtra("action_type", "CONTENT_LENGTH");
        intent.putExtra("CONTENT_LENGTH_SIZE", i);
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), intent);
        LogUtil.a("NotifySendData", "contentLength sendBroadcastPackage finished!");
    }

    private kht d(byte[] bArr) throws cwg {
        String str;
        int i;
        blt.d("NotifySendData", bArr, "unNotificationConstraint data: ");
        cwl cwlVar = new cwl();
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < bArr.length; i4++) {
            try {
                if (bArr[i4] == 18) {
                    byte b2 = bArr[i4 + 1];
                    if (b2 == 1 && bArr[i4 + 2] == 1) {
                        i2 = i4;
                    }
                    if (b2 == 1 && bArr[i4 + 2] == 2) {
                        i3 = i4;
                    }
                }
            } catch (Exception unused) {
                LogUtil.b("NotifySendData", "unNotificationConstraint, Exception");
                str = "";
            }
        }
        if (i2 != -1 && i3 != -1 && (i = i3 - i2) > 5) {
            int i5 = i - 2;
            byte[] bArr2 = new byte[i5];
            System.arraycopy(bArr, i2, bArr2, 0, i5);
            str = cvx.d(bArr2);
        } else {
            byte[] bArr3 = new byte[9];
            System.arraycopy(bArr, i2, bArr3, 0, 9);
            str = cvx.d(bArr3);
        }
        LogUtil.a("NotifySendData", "unGetOtaParametersV2 enter, tlvString: ", str);
        return c(cwlVar.a(str));
    }

    private kht c(cwe cweVar) {
        kht khtVar = new kht();
        List<cwd> e2 = cweVar.e();
        if (e2 != null && !e2.isEmpty()) {
            for (cwd cwdVar : e2) {
                int w = CommonUtil.w(cwdVar.e());
                String c2 = cwdVar.c();
                if (w == 20) {
                    khtVar.a(CommonUtil.w(c2));
                    LogUtil.a("NotifySendData", "constraint.setContentLength: ", Long.valueOf(CommonUtil.y(c2)));
                } else {
                    LogUtil.h("NotifySendData", "unknown tlv type");
                }
            }
        }
        return khtVar;
    }

    public void c(DeviceCapability deviceCapability, DeviceInfo deviceInfo) {
        r();
        if (deviceCapability == null || deviceInfo == null) {
            LogUtil.h("NotifySendData", "sendNotificationConstraint parameter is null");
            return;
        }
        boolean isMessageAlert = deviceCapability.isMessageAlert();
        LogUtil.a("NotifySendData", "sendNotificationConstraint isMessageAlert: ", Boolean.valueOf(isMessageAlert));
        if (isMessageAlert) {
            this.h = GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            if (c() == 2) {
                LogUtil.a("NotifySendData", "sendNotificationConstraint send");
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(2);
                deviceCommand.setCommandID(2);
                String str = cvx.e(1) + cvx.e(0);
                deviceCommand.setDataContent(cvx.a(str));
                deviceCommand.setDataLen(cvx.a(str).length);
                deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
                this.k.sendDeviceData(deviceCommand);
            }
        }
    }

    private void r() {
        LogUtil.a("NotifySendData", " resetAbility() ");
        this.z = 30;
        this.f = 30;
        this.l = 30;
        this.x = false;
        this.p = false;
        this.r = false;
        this.ab = 2;
        this.g = 2;
        this.n = 2;
    }

    public void b() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.j);
            BaseApplication.getContext().unregisterReceiver(this.ac);
        } catch (RuntimeException unused) {
            LogUtil.b("NotifySendData", "destroy(), RuntimeException");
        } catch (Exception unused2) {
            LogUtil.b("NotifySendData", "destroy(), Exception");
        }
        n();
        o();
    }

    private static void n() {
        synchronized (d) {
            b = null;
        }
    }

    private void o() {
        synchronized (d) {
            ExtendHandler extendHandler = this.w;
            if (extendHandler != null) {
                extendHandler.removeTasksAndMessages();
                this.w = null;
            }
        }
    }

    private void f() {
        int i = c + 1;
        c = i;
        if (i == 256) {
            c = 0;
        }
    }

    public DeviceCommand b(int i, boolean z, List<MsgImage> list, List<MsgText> list2, String str) {
        LogUtil.a("NotifySendData", "pushNotificationToDevice notificationMessageToTlv packageName: ", str, " msgType:", Integer.valueOf(i));
        jcw a2 = a(i, z, list, list2, str);
        f();
        int d2 = a2.d();
        String str2 = cvx.e(1) + cvx.e(2) + cvx.a(c);
        String str3 = cvx.e(2) + cvx.e(1) + cvx.e(d2);
        String str4 = cvx.e(3) + cvx.e(1) + cvx.e(a2.c());
        LogUtil.a("NotifySendData", "pushNotificationToDevice notificationMessageToTlv messageIdTlv: ", str2, " messageTypeTlvHex:", str3, " motorEnableTlvHex: ", str4);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(1);
        String b2 = b(a2, str, str2, str3, str4);
        deviceCommand.setDataContent(cvx.a(b2));
        deviceCommand.setDataLen(cvx.a(b2).length);
        return deviceCommand;
    }

    private String b(jcw jcwVar, String str, String str2, String str3, String str4) {
        String str5;
        String str6;
        ArrayList<jcz> e2 = jcwVar.e();
        s();
        t();
        StringBuilder sb = new StringBuilder(16);
        StringBuilder sb2 = new StringBuilder(16);
        String d2 = khs.d(sb2, e2);
        String d3 = cvx.d(("" + ((Object) sb) + d2 + ((Object) sb2)).length() / 2);
        if (d3.length() > 0) {
            str5 = cvx.e(UserInfomation.WEIGHT_DEFAULT_ENGLISH) + d3;
        } else {
            str5 = cvx.e(4) + cvx.e(0);
        }
        LogUtil.a("NotifySendData", "pushNotificationToDevice getNotificationCommand messageContentStructTlvHex: ", str5);
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("NotifySendData", "pushNotificationToDevice getNotificationCommand packageName is null");
            str6 = str2 + str3 + str4 + str5 + "" + ((Object) sb) + d2 + ((Object) sb2);
        } else {
            String c2 = cvx.c(jcwVar.a());
            int i = e;
            if (c2.length() / 2 > i) {
                c2 = str3.substring(0, i * 2);
                LogUtil.a("NotifySendData", "pushNotificationToDevice getNotificationCommand substring messagePkgNameHex: ", c2);
            }
            String str7 = cvx.e(17) + cvx.e(c2.length() / 2) + c2;
            LogUtil.a("NotifySendData", "pushNotificationToDevice getNotificationCommand messagePkgNameHex: ", c2, " messagePkgNameTlv:", str7);
            str6 = str2 + str3 + str4 + str5 + "" + ((Object) sb) + d2 + ((Object) sb2) + str7;
        }
        LogUtil.a("NotifySendData", "pushNotificationToDevice getNotificationCommand command: ", str6);
        return str6;
    }

    public DeviceCommand bOk_(Bundle bundle, int i, DeviceCommand deviceCommand) {
        String str;
        String str2;
        String str3;
        String str4;
        long j;
        if (deviceCommand == null || bundle == null) {
            LogUtil.h("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex deviceCommand or bundle is null,so return");
            return deviceCommand;
        }
        try {
            str = bundle.getString("data_category");
            try {
                str2 = bundle.getString(Constant.TEXT);
                try {
                    str3 = bundle.getString("title");
                    try {
                        str4 = bundle.getString("notification_key");
                        try {
                            j = bundle.getLong("data_extra_noty_when");
                        } catch (Exception unused) {
                            LogUtil.b("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex messageType error");
                            j = 0;
                            long j2 = j;
                            StringBuffer stringBuffer = new StringBuffer(16);
                            String a2 = a(str);
                            String d2 = d(str3, str2, i, j2);
                            String p = p();
                            String c2 = c(str4);
                            String string = bundle.getString("data_extra_noty_key", "");
                            String d3 = cvx.d(deviceCommand.getDataContent());
                            String bOg_ = bOg_(19, bundle);
                            LogUtil.a("NotifySendData", string, " pushNotificationToDevice notificationMessageExtendToHex reminderTlv(1302000F=vibrate): ", bOg_, ",categoryTlv: ", a2, ",replyKeyTlv: ", c2);
                            stringBuffer.append(d3).append(a2).append(bOg_).append(d2).append(p).append(c2).append(d(25, string)).append(bOe_(26, bundle)).append(bNX_(27, bundle)).append(bOd_(29, bundle)).append(bOi_(41, bundle)).append(bOf_(bundle)).append(e(j2));
                            byte[] a3 = cvx.a(stringBuffer.toString());
                            LogUtil.a("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex command: ", stringBuffer.toString());
                            deviceCommand.setDataContent(a3);
                            deviceCommand.setDataLen(a3.length);
                            return deviceCommand;
                        }
                    } catch (Exception unused2) {
                        str4 = "";
                    }
                } catch (Exception unused3) {
                    str3 = "";
                    str4 = str3;
                    LogUtil.b("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex messageType error");
                    j = 0;
                    long j22 = j;
                    StringBuffer stringBuffer2 = new StringBuffer(16);
                    String a22 = a(str);
                    String d22 = d(str3, str2, i, j22);
                    String p2 = p();
                    String c22 = c(str4);
                    String string2 = bundle.getString("data_extra_noty_key", "");
                    String d32 = cvx.d(deviceCommand.getDataContent());
                    String bOg_2 = bOg_(19, bundle);
                    LogUtil.a("NotifySendData", string2, " pushNotificationToDevice notificationMessageExtendToHex reminderTlv(1302000F=vibrate): ", bOg_2, ",categoryTlv: ", a22, ",replyKeyTlv: ", c22);
                    stringBuffer2.append(d32).append(a22).append(bOg_2).append(d22).append(p2).append(c22).append(d(25, string2)).append(bOe_(26, bundle)).append(bNX_(27, bundle)).append(bOd_(29, bundle)).append(bOi_(41, bundle)).append(bOf_(bundle)).append(e(j22));
                    byte[] a32 = cvx.a(stringBuffer2.toString());
                    LogUtil.a("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex command: ", stringBuffer2.toString());
                    deviceCommand.setDataContent(a32);
                    deviceCommand.setDataLen(a32.length);
                    return deviceCommand;
                }
            } catch (Exception unused4) {
                str2 = "";
                str3 = "";
                str4 = str3;
                LogUtil.b("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex messageType error");
                j = 0;
                long j222 = j;
                StringBuffer stringBuffer22 = new StringBuffer(16);
                String a222 = a(str);
                String d222 = d(str3, str2, i, j222);
                String p22 = p();
                String c222 = c(str4);
                String string22 = bundle.getString("data_extra_noty_key", "");
                String d322 = cvx.d(deviceCommand.getDataContent());
                String bOg_22 = bOg_(19, bundle);
                LogUtil.a("NotifySendData", string22, " pushNotificationToDevice notificationMessageExtendToHex reminderTlv(1302000F=vibrate): ", bOg_22, ",categoryTlv: ", a222, ",replyKeyTlv: ", c222);
                stringBuffer22.append(d322).append(a222).append(bOg_22).append(d222).append(p22).append(c222).append(d(25, string22)).append(bOe_(26, bundle)).append(bNX_(27, bundle)).append(bOd_(29, bundle)).append(bOi_(41, bundle)).append(bOf_(bundle)).append(e(j222));
                byte[] a322 = cvx.a(stringBuffer22.toString());
                LogUtil.a("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex command: ", stringBuffer22.toString());
                deviceCommand.setDataContent(a322);
                deviceCommand.setDataLen(a322.length);
                return deviceCommand;
            }
        } catch (Exception unused5) {
            str = "";
        }
        long j2222 = j;
        StringBuffer stringBuffer222 = new StringBuffer(16);
        String a2222 = a(str);
        String d2222 = d(str3, str2, i, j2222);
        String p222 = p();
        String c2222 = c(str4);
        String string222 = bundle.getString("data_extra_noty_key", "");
        String d3222 = cvx.d(deviceCommand.getDataContent());
        String bOg_222 = bOg_(19, bundle);
        LogUtil.a("NotifySendData", string222, " pushNotificationToDevice notificationMessageExtendToHex reminderTlv(1302000F=vibrate): ", bOg_222, ",categoryTlv: ", a2222, ",replyKeyTlv: ", c2222);
        stringBuffer222.append(d3222).append(a2222).append(bOg_222).append(d2222).append(p222).append(c2222).append(d(25, string222)).append(bOe_(26, bundle)).append(bNX_(27, bundle)).append(bOd_(29, bundle)).append(bOi_(41, bundle)).append(bOf_(bundle)).append(e(j2222));
        byte[] a3222 = cvx.a(stringBuffer222.toString());
        LogUtil.a("NotifySendData", "pushNotificationToDevice notificationMessageExtendToHex command: ", stringBuffer222.toString());
        deviceCommand.setDataContent(a3222);
        deviceCommand.setDataLen(a3222.length);
        return deviceCommand;
    }

    private String e(long j) {
        if (j == 0) {
            ReleaseLogUtil.d("NotifySendData", "packagingNotificationWhen receiveTime is null");
            return "";
        }
        String c2 = cvx.c(j);
        return cvx.e(50) + cvx.e(c2.length() / 2) + c2;
    }

    private String bOf_(Bundle bundle) {
        int i;
        if (!khs.e()) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingPingTlvHexByJson device not support deepLink process");
            return "";
        }
        String string = bundle.getString("pingList");
        if (TextUtils.isEmpty(string)) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingPingTlvHexByJson pingList is empty");
            return "";
        }
        LogUtil.a("NotifySendData", "packagingPingTlvHexByJson pingList=", string);
        bms bmsVar = new bms();
        bmsVar.b(42);
        try {
            JSONArray jSONArray = new JSONArray(string);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                bmsVar.c(43);
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                boolean has = jSONObject.has("ping_open_remote");
                LogUtil.a("NotifySendData", "packagingPingTlvHexByJson isExistOpenRemote=", Boolean.valueOf(has));
                if (has) {
                    bmsVar.d(44, jSONObject.getInt("ping_open_remote"));
                }
                boolean has2 = jSONObject.has("ping_open_type");
                LogUtil.a("NotifySendData", "packagingPingTlvHexByJson isExistOpenType=", Boolean.valueOf(has2));
                if (has2) {
                    bmsVar.d(45, jSONObject.getInt("ping_open_type"));
                }
                boolean has3 = jSONObject.has("ping_package_name");
                LogUtil.a("NotifySendData", "packagingPingTlvHexByJson isExistPackageName=", Boolean.valueOf(has3));
                if (has3) {
                    String string2 = jSONObject.getString("ping_package_name");
                    if (!TextUtils.isEmpty(string2)) {
                        bmsVar.e(46, string2);
                    }
                }
                boolean has4 = jSONObject.has("ping_class_name");
                LogUtil.a("NotifySendData", "packagingPingTlvHexByJson isExistClassName=", Boolean.valueOf(has4));
                if (has4) {
                    String string3 = jSONObject.getString("ping_class_name");
                    if (!TextUtils.isEmpty(string3)) {
                        bmsVar.e(47, string3);
                    }
                }
                boolean has5 = jSONObject.has("ping_url");
                LogUtil.a("NotifySendData", "packagingPingTlvHexByJson isExistUrl=", Boolean.valueOf(has5));
                if (has5) {
                    String string4 = jSONObject.getString("ping_url");
                    if (!TextUtils.isEmpty(string4)) {
                        bmsVar.e(48, string4);
                    }
                }
                boolean has6 = jSONObject.has("button_content");
                LogUtil.a("NotifySendData", "packagingPingTlvHexByJson isExistButtonContent=", Boolean.valueOf(has6));
                if (has6) {
                    String string5 = jSONObject.getString("button_content");
                    if (!TextUtils.isEmpty(string5)) {
                        bmsVar.e(49, string5);
                    }
                }
                bmsVar.c(bmsVar.c());
            }
            String d2 = cvx.d(bmsVar.b(bmsVar.b()).d());
            Object[] objArr = new Object[2];
            objArr[0] = "packagingPingTlvHexByJson allLinkCommand=";
            try {
                objArr[1] = d2;
                LogUtil.a("NotifySendData", objArr);
                return d2;
            } catch (JSONException unused) {
                i = 1;
                Object[] objArr2 = new Object[i];
                objArr2[0] = "packagingPingTlvHexByJson occur JSONException.";
                LogUtil.b("NotifySendData", objArr2);
                return "";
            }
        } catch (JSONException unused2) {
            i = 1;
        }
    }

    private String bOi_(int i, Bundle bundle) {
        int i2 = bundle.getInt("voip_type", 0);
        if (i2 == 0) {
            ReleaseLogUtil.d("R_NotifySendData", "pushNotificationToDevice voip type FAILED: it not is a voip call.");
            return "";
        }
        if (!khs.o()) {
            return "";
        }
        String e2 = cvx.e(i2);
        return cvx.e(i) + khs.b(e2.length() / 2) + e2;
    }

    private String d(int i, String str) {
        if (!khs.l() && !khs.d()) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingNotificationKeyTlv isSupportRepeatedNotifyProcess false");
            return "";
        }
        String c2 = cvx.c(str);
        return cvx.e(i) + khs.b(c2.length() / 2) + c2;
    }

    private String bOe_(int i, Bundle bundle) {
        if (!khs.l() && !khs.d()) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingNotificationKeyTlv isSupportRepeatedNotifyProcess false");
            return "";
        }
        String b2 = cvx.b(bundle.getInt("data_noty_id", -1));
        return cvx.e(i) + khs.b(b2.length() / 2) + b2;
    }

    private String bNX_(int i, Bundle bundle) {
        if (!khs.l() && !khs.d()) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingChannelIdHex isSupportRepeatedNotifyProcess false");
            return "";
        }
        String c2 = cvx.c(bundle.getString("data_channel_id", ""));
        return cvx.e(i) + khs.b(c2.length() / 2) + c2;
    }

    private String bOd_(int i, Bundle bundle) {
        int i2 = bundle.getInt("liveType", 0);
        String string = bundle.getString("liveEvent", "OTHER");
        if (i2 == 0) {
            ReleaseLogUtil.d("R_NotifySendData", "pushNotificationToDevice packagingLiveInfoHex FAILED: LIVETYPE_NOT_MATCH!");
            return "";
        }
        if (!bff.b.contains(string.toUpperCase(Locale.ROOT))) {
            ReleaseLogUtil.d("R_NotifySendData", "pushNotificationToDevice packagingLiveInfoHex FAILED: LIVEEVENT_NOT_IN_WHITEFLAGS!");
            return "";
        }
        if (!khs.g()) {
            ReleaseLogUtil.d("R_NotifySendData", "pushNotificationToDevice packagingLiveInfoHex FAILED: DEVICE_CAPABILITY_INCAPABLE!");
            return "";
        }
        int i3 = bundle.getInt("liveOperation", 0);
        int i4 = bundle.getInt("liveKeepTime", 0);
        String string2 = bundle.getString("featureTitleText", "");
        String string3 = bundle.getString("featureContentText", "");
        String string4 = bundle.getString("titleOverlay", "");
        String string5 = bundle.getString("contentOverlay", "");
        bms bmsVar = new bms();
        bmsVar.c(i).d(30, i3).d(32, i2).b(33, i4).e(31, string);
        a(string2, string3, string4, string5, bmsVar);
        boolean z = bundle.getBoolean("externalEnable", false);
        bmsVar.d(38, z ? 1 : 0);
        bNV_(bundle, bmsVar, z);
        return cvx.d(bmsVar.b(bmsVar.c()).d());
    }

    private void a(String str, String str2, String str3, String str4, bms bmsVar) {
        if (!str.isEmpty()) {
            bmsVar.e(36, str);
        }
        if (!str2.isEmpty()) {
            bmsVar.e(37, str2);
        }
        if (!str3.isEmpty()) {
            bmsVar.e(34, str3);
        }
        if (str4.isEmpty()) {
            return;
        }
        bmsVar.e(35, str4);
    }

    private void bNV_(Bundle bundle, bms bmsVar, boolean z) {
        if (z) {
            String string = bundle.getString("externalTitle", "");
            String string2 = bundle.getString("externalBody", "");
            if (!string.isEmpty()) {
                bmsVar.e(39, string);
            }
            if (string2.isEmpty()) {
                return;
            }
            bmsVar.e(40, string2);
        }
    }

    private String bNY_(int i, Bundle bundle) {
        String string = bundle.getString("data_category");
        if (TextUtils.isEmpty(string)) {
            LogUtil.a("NotifySendData", "packagingCategoryHex category is empty");
            return "";
        }
        if (!khs.d() && (!khs.o() || !NotificationCompat.CATEGORY_CALL.equals(string))) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingDeleteCategoryHex isSupportDeleteSingleNotification false");
            return "";
        }
        String c2 = cvx.c(string);
        return cvx.e(i) + khs.b(c2.length() / 2) + c2;
    }

    private String c(String str) {
        if (!kiq.c()) {
            ReleaseLogUtil.d("R_NotifySendData", "pushNotificationToDevice quickReply FAILED: DEVICE_INCAPABLE");
            return "";
        }
        String c2 = cvx.c(str);
        return cvx.e(24) + khs.b(c2.length() / 2) + c2;
    }

    private String p() {
        if (!khg.d().b()) {
            ReleaseLogUtil.d("R_NotifySendData", "packagingTimeHex device is not support timestamp.");
            return "";
        }
        return cvx.e(21) + cvx.e(4) + cvx.b(System.currentTimeMillis() / 1000);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("NotifySendData", "pushNotificationToDevice WARNING: category is empty!");
            return "";
        }
        DeviceInfo k = k();
        if (k == null || k.getProductType() < 34) {
            LogUtil.h("NotifySendData", "pushNotificationToDevice WARNING: productType<34,don't push category: ", k);
            return "";
        }
        String c2 = cvx.c(str);
        return cvx.e(18) + cvx.e(c2.length() / 2) + c2;
    }

    private String bOg_(int i, Bundle bundle) {
        String a2;
        String string = bundle.getString("data_extra_noty_harmony_alert");
        int i2 = bundle.getInt("data_extra_reminder_status", 15);
        LogUtil.a("NotifySendData", "pushNotificationToDevice packagingReminderHex isReminderStatus(15=0x000F=vibrate): ", Integer.valueOf(i2));
        if (khs.l() && i2 == 0) {
            String a3 = cvx.a(0);
            LogUtil.h("NotifySendData", "pushNotificationToDevice packagingReminderHex reminder no value");
            return cvx.e(i) + cvx.e(2) + a3;
        }
        ReleaseLogUtil.e("R_NotifySendData", "pushNotificationToDevice packagingReminderHex alertListenerPkg: ", string);
        if (kho.b().a()) {
            kho.b().e();
            if (!kho.b().d() || BaseApplication.getContext().getPackageName().equals(string)) {
                a2 = cvx.a(15);
            } else {
                a2 = cvx.a(0);
            }
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
            if (TextUtils.isEmpty(string)) {
                linkedHashMap.put("notificationReminderAlertPkg", Constants.NULL);
            } else {
                linkedHashMap.put("notificationReminderAlertPkg", string);
            }
            if (a2.toLowerCase(Locale.ROOT).equals("000f")) {
                linkedHashMap.put("notificationReminderState", "true");
            } else {
                linkedHashMap.put("notificationReminderState", "false");
            }
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
            return cvx.e(i) + cvx.e(2) + a2;
        }
        return bOh_(i, bundle, i2);
    }

    private String bOh_(int i, Bundle bundle, int i2) {
        if (!kho.b().g()) {
            if (khs.l()) {
                ReleaseLogUtil.e("R_NotifySendData", "pushNotificationToDevice packagingReminderHexOld isSupportRepeatedNotifyProcess true");
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
                linkedHashMap.put("notificationReminderOldType", String.valueOf(i));
                linkedHashMap.put("notificationReminderOldValue", "");
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
                return cvx.e(i) + cvx.e(2) + cvx.a(i2);
            }
            LinkedHashMap<String, String> linkedHashMap2 = new LinkedHashMap<>(2);
            linkedHashMap2.put("notificationReminderOldType", String.valueOf(i));
            linkedHashMap2.put("notificationReminderOldValue", Constants.NULL);
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap2);
            ReleaseLogUtil.e("R_NotifySendData", "pushNotificationToDevice packagingReminderHexOld is not support");
            return "";
        }
        boolean e2 = kho.b().e();
        ReleaseLogUtil.e("R_NotifySendData", "pushNotificationToDevice packagingReminderHexOld isCategoryReminderStatus: ", Boolean.valueOf(e2), ",isReminderStatus(true-vibrate): ", Boolean.valueOf(i2 == 15));
        String a2 = cvx.a(e2 ? 15 : 0);
        LinkedHashMap<String, String> linkedHashMap3 = new LinkedHashMap<>(2);
        linkedHashMap3.put("notificationReminderOldTypeByPhone", String.valueOf(i));
        if (e2) {
            linkedHashMap3.put("notificationReminderOldResultByPhone", "true");
        } else {
            linkedHashMap3.put("notificationReminderOldResultByPhone", "false");
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap3);
        return cvx.e(i) + cvx.e(2) + a2;
    }

    private String d(String str, String str2, int i, long j) {
        boolean b2 = khs.b();
        LogUtil.a("NotifySendData", "pushNotificationToDevice packagingSmsReplyHex messageType: ", Integer.valueOf(i), ",isDeviceSupportSmsReply: ", Boolean.valueOf(b2));
        String str3 = "";
        if (i != 2 || !b2) {
            return "";
        }
        if (!jeg.d().c(BaseApplication.getContext(), "android.permission.READ_SMS")) {
            ReleaseLogUtil.d("R_NotifySendData", "pushNotificationToDevice packagingSmsReplyHex HAS_NO_PERMISSION READ_SMS!");
            return (cvx.e(20) + cvx.e(1) + cvx.e(-1)) + (cvx.e(23) + cvx.e(0));
        }
        LogUtil.a("NotifySendData", "pushNotificationToDevice packagingSmsReplyHex title: ", bky.b(str), " content:", bky.e(str2));
        kil c2 = c(str, str2, j);
        if (c2 != null) {
            int j2 = c2.j();
            String d2 = c2.d();
            ReleaseLogUtil.e("R_NotifySendData", "pushNotificationToDevice packagingSmsReplyHex subId: ", Integer.valueOf(j2), ",senderNumber: ", blt.b(d2));
            if (j2 == -1) {
                ReleaseLogUtil.c("R_NotifySendData", "pushNotificationToDevice packagingSmsReplyHex subId is -1");
                return "";
            }
            String str4 = cvx.e(20) + cvx.e(1) + cvx.e(j2);
            String c3 = cvx.c(d2);
            str3 = str4 + (cvx.e(23) + cvx.e(c3.length() / 2) + c3);
        }
        LogUtil.a("NotifySendData", "pushNotificationToDevice packagingSmsReplyHex smsReplyTlv(0x14,0x17): ", str3);
        return str3;
    }

    private kil c(String str, String str2, long j) {
        kil kilVar = new kil();
        kilVar.d(str2);
        kilVar.e(j);
        kil c2 = khs.c(kilVar);
        if (c2 != null) {
            return c2;
        }
        List<String> d2 = khs.d(str);
        LogUtil.a("NotifySendData", "pushNotificationToDevice packagingSmsReplyHex numberList size is ", Integer.valueOf(d2.size()));
        Iterator<String> it = d2.iterator();
        while (it.hasNext() && (c2 = khs.c(it.next())) == null) {
        }
        if (c2 != null || TextUtils.isEmpty(str2)) {
            return c2;
        }
        LogUtil.a("NotifySendData", "pushNotificationToDevice packagingSmsReplyHex query sms contain subject");
        String[] split = str2.split(System.lineSeparator());
        return split.length == 2 ? khs.d(split[0], split[1]) : c2;
    }

    private jcw a(int i, boolean z, List<MsgImage> list, List<MsgText> list2, String str) {
        khq khqVar = new khq();
        khqVar.b(i);
        khqVar.e(this.ab);
        khqVar.d(this.g);
        khqVar.c(this.n);
        return khs.c(z, list, list2, str, khqVar);
    }

    private void s() {
        ReleaseLogUtil.e("R_NotifySendData", "notificationMessageToTlv, IsSupportYellow: ", Boolean.valueOf(this.x), " ; YellowPagesLength : ", Integer.valueOf(this.z), " ; YellowPagesFomat : ", Integer.valueOf(this.ab), " ; IsSupportSign : ", Boolean.valueOf(this.p), " ; ContentSignLength : ", Integer.valueOf(this.f), " ; ContentSignFormat : ", Integer.valueOf(this.g), "; IsSupportIncoming : ", Boolean.valueOf(this.r), " ; IncomingNumberLength : ", Integer.valueOf(this.l), " ; IncomingNumberFormat : ", Integer.valueOf(this.n));
    }

    private void t() {
        khz khzVar = new khz();
        khzVar.a(this.x);
        khzVar.e(this.p);
        khzVar.c(this.r);
        khzVar.e(this.h);
        khzVar.a(this.z);
        khzVar.d(this.f);
        khzVar.b(this.l);
        khs.a(khzVar);
    }

    public DeviceCommand c(DataPromptData dataPromptData, jcy jcyVar) {
        String a2 = khs.a(jcyVar, dataPromptData);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(a2));
        deviceCommand.setDataLen(cvx.a(a2).length);
        return deviceCommand;
    }

    public void a(DeviceCommand deviceCommand, int i) {
        if (this.i.g() != 3) {
            LogUtil.h("NotifySendData", "sendNotificationCommand error, Bluetooth switch is turn off");
            return;
        }
        DeviceInfo k = k();
        Object[] objArr = new Object[2];
        objArr[0] = "sendNotificationCommand ConnectState: ";
        objArr[1] = k == null ? "deviceInfo is null" : Integer.valueOf(k.getDeviceConnectState());
        ReleaseLogUtil.e("R_NotifySendData", objArr);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("notificationPushCommandType", String.valueOf(i));
        if (k == null) {
            linkedHashMap.put("notificationPushCommandResult", "false");
        } else {
            linkedHashMap.put("notificationPushCommandResult", "true");
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_MESSAGE_PUSH_85070006.value(), linkedHashMap);
        if (k == null) {
            return;
        }
        if (k.getDeviceConnectState() != 2 && k.getDeviceConnectState() != 1 && k.getDeviceConnectState() != 5) {
            if (this.o == null) {
                this.o = deviceCommand;
                blt.d("NotifySendData", deviceCommand.getDataContent(), "mDeviceCommand data: ");
            }
            LogUtil.a("NotifySendData", "sendNotificationCommand mIsForceConnect: ", Boolean.valueOf(this.q));
            if (this.q) {
                this.k.connectDevice(null, BluetoothType.BR.getValue() | BluetoothType.AW.getValue());
                this.q = false;
            }
            this.v = i;
            return;
        }
        e(i, deviceCommand, k);
    }

    private void e(int i, DeviceCommand deviceCommand, DeviceInfo deviceInfo) {
        this.v = i;
        LogUtil.a("NotifySendData", "sendNotificationCommand parsePromptAbility mMessageType: ", Integer.valueOf(i));
        if (this.v == 1) {
            DeviceCapability d2 = khs.d(deviceInfo);
            if (d2 == null) {
                LogUtil.h("NotifySendData", "sendNotificationCommand parsePromptAbility, deviceCapability is null");
                return;
            }
            LogUtil.a("NotifySendData", "sendNotificationCommand parsePromptAbility isPromptPush: ", Integer.valueOf(d2.isPromptPush()), " mIsSupportPrompt: ", Boolean.valueOf(this.s));
            if (d2.isPromptPush() == -1) {
                if (!this.s) {
                    return;
                }
            } else {
                if (((d2.isPromptPush() >> 1) & 1) != 1) {
                    LogUtil.h("NotifySendData", "sendNotificationCommand pushPromptToDevice, deviceCapability.isPromptPush() is false,device not support prompt");
                    return;
                }
                LogUtil.h("NotifySendData", "sendNotificationCommand parsePromptAbility, do not need deal");
            }
        }
        iyv.h();
        deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
        this.k.sendDeviceData(deviceCommand);
        ReleaseLogUtil.e("R_NotifySendData", "sendNotificationCommand finished pushing tlv deviceCommand");
    }

    private DeviceInfo k() {
        synchronized (f14377a) {
            DeviceInfo deviceInfo = null;
            try {
                try {
                    LogUtil.a("NotifySendData", "getCurrentDeviceInfo() enter...");
                    DeviceInfo l = l();
                    if (l == null) {
                        try {
                            List<DeviceInfo> deviceList = this.k.getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "NotifySendData");
                            if (!deviceList.isEmpty()) {
                                l = deviceList.get(0);
                            }
                        } catch (Exception unused) {
                            deviceInfo = l;
                            LogUtil.b("NotifySendData", "getCurrentDeviceInfo() error");
                            return deviceInfo;
                        }
                    }
                    return l;
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Exception unused2) {
            }
        }
    }

    public int c() {
        DeviceInfo k = k();
        if (k != null) {
            return k.getDeviceProtocol();
        }
        return -1;
    }

    public void b(String str) {
        LogUtil.a("NotifySendData", "sendThirdPartyCallingOnMessage phoneNumber is: ", Boolean.valueOf(TextUtils.isEmpty(str)));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(1);
        byte[] a2 = cvx.a(str);
        if (a2 != null) {
            deviceCommand.setDataLen(a2.length);
            deviceCommand.setDataContent(a2);
        }
        this.k.sendDeviceData(deviceCommand);
    }

    public void j() {
        LogUtil.a("NotifySendData", "enter sendThirdPartyCallingOffMessage");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataLen(0);
        deviceCommand.setDataContent(null);
        this.k.sendDeviceData(deviceCommand);
    }

    private void a(byte[] bArr, int i) {
        try {
            String d2 = cvx.d(bArr);
            LogUtil.a("NotifySendData", "parseSupportData tlvsString: ", d2);
            List<cwd> e2 = new cwl().a(d2).e();
            if (e2 != null && !e2.isEmpty()) {
                d(e2, i);
            }
        } catch (cwg unused) {
            LogUtil.b("NotifySendData", "parseSupportData TlvException");
        }
    }

    private void d(List<cwd> list, int i) {
        for (cwd cwdVar : list) {
            try {
                d(i, Integer.parseInt(cwdVar.e(), 16), cwdVar.c());
            } catch (NumberFormatException unused) {
                LogUtil.b("NotifySendData", "valve is not number");
            }
        }
    }

    private void d(int i, int i2, String str) throws NumberFormatException {
        if (i2 != 19) {
            if (i2 == 20) {
                c(str, i);
                return;
            } else {
                LogUtil.c("NotifySendData", "canNotFindLength");
                return;
            }
        }
        if (i == 5) {
            int parseInt = Integer.parseInt(str, 16);
            if (parseInt == 3) {
                this.ab = 2;
            } else {
                this.ab = parseInt;
            }
            LogUtil.c("NotifySendData", "unNotificationContent mYellowPagesFormat: ", Integer.valueOf(this.ab));
            return;
        }
        if (i == 6) {
            int parseInt2 = Integer.parseInt(str, 16);
            if (parseInt2 == 3) {
                this.g = 2;
            } else {
                this.g = parseInt2;
            }
            LogUtil.c("NotifySendData", "unNotificationContent mContentSignFormat: ", Integer.valueOf(this.g));
            return;
        }
        if (i == 7) {
            int parseInt3 = Integer.parseInt(str, 16);
            if (parseInt3 == 3) {
                this.n = 2;
            } else {
                this.n = parseInt3;
            }
            LogUtil.c("NotifySendData", "unNotificationContent mIncomingNumberFormat: ", Integer.valueOf(this.n));
            return;
        }
        LogUtil.c("NotifySendData", "canNotFindFormat");
    }

    private void e(byte[] bArr) {
        blt.d("NotifySendData", bArr, "unNotificationContent enter, data: ");
        for (int i = 0; i < bArr.length; i++) {
            try {
                if (bArr[i] == 18) {
                    byte b2 = bArr[i + 2];
                    if (b2 == 5) {
                        byte[] bArr2 = new byte[9];
                        System.arraycopy(bArr, i, bArr2, 0, 9);
                        this.x = true;
                        LogUtil.a("NotifySendData", "unNotificationContent mIsSupportYellow is true");
                        a(bArr2, 5);
                    } else if (b2 == 6) {
                        byte[] bArr3 = new byte[9];
                        System.arraycopy(bArr, i, bArr3, 0, 9);
                        this.p = true;
                        LogUtil.a("NotifySendData", "unNotificationContent mIsSupportSign is true");
                        a(bArr3, 6);
                    } else if (b2 == 7) {
                        byte[] bArr4 = new byte[9];
                        System.arraycopy(bArr, i, bArr4, 0, 9);
                        this.r = true;
                        ReleaseLogUtil.e("R_NotifySendData", "unNotificationContent mIsSupportIncoming is true");
                        a(bArr4, 7);
                    } else {
                        LogUtil.c("NotifySendData", "can not find Content");
                    }
                }
            } catch (Exception unused) {
                LogUtil.b("NotifySendData", "unNotificationContent, Exception");
                return;
            }
        }
    }

    public DeviceCommand d(int i) {
        String e2 = cvx.e(i);
        String str = cvx.e(1) + cvx.e(e2.length() / 2) + e2;
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(2);
        deviceCommand.setCommandID(6);
        deviceCommand.setDataContent(cvx.a(str));
        LogUtil.a("NotifySendData", "deleteMessageToTlv command: ", str);
        deviceCommand.setDataLen(cvx.a(str).length);
        return deviceCommand;
    }

    public DeviceCommand bOj_(Bundle bundle, DeviceCommand deviceCommand) {
        if (deviceCommand == null || bundle == null) {
            LogUtil.h("NotifySendData", "deleteMessageExtendToHex deviceCommand or bundle is null");
            return deviceCommand;
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(cvx.d(deviceCommand.getDataContent())).append(bOc_(bundle)).append(bOb_(3, bundle)).append(bOa_(4, bundle)).append(bNZ_(5, bundle)).append(bNY_(6, bundle));
        byte[] a2 = cvx.a(stringBuffer.toString());
        LogUtil.a("NotifySendData", "deleteMessageExtendToHex command: ", stringBuffer.toString());
        deviceCommand.setDataContent(a2);
        deviceCommand.setDataLen(a2.length);
        return deviceCommand;
    }

    private boolean bNW_(Bundle bundle) {
        return (khs.l() || khs.d() || (khs.o() && NotificationCompat.CATEGORY_CALL.equals(bundle.getString("data_category")))) ? false : true;
    }

    private String bOc_(Bundle bundle) {
        if (bNW_(bundle)) {
            LogUtil.a("NotifySendData", "packagingDeletePackageNameTlv isSupportRepeatedNotifyProcess false");
            return "";
        }
        String c2 = cvx.c(bundle.getString("packagename", ""));
        return cvx.e(2) + khs.b(c2.length() / 2) + c2;
    }

    private String bOb_(int i, Bundle bundle) {
        if (bNW_(bundle)) {
            LogUtil.a("NotifySendData", "packagingDeleteNotificationKeyTlv isSupportRepeatedNotifyProcess false");
            return "";
        }
        return cvx.d(new bms().d(i, bundle.getString("data_extra_noty_key", "")).d());
    }

    private String bOa_(int i, Bundle bundle) {
        if (bNW_(bundle)) {
            LogUtil.a("NotifySendData", "packagingDeleteNotificationIdTlv isSupportRepeatedNotifyProcess false");
            return "";
        }
        String b2 = cvx.b(bundle.getInt("data_noty_id", -1));
        return cvx.e(i) + khs.b(b2.length() / 2) + b2;
    }

    private String bNZ_(int i, Bundle bundle) {
        if (bNW_(bundle)) {
            LogUtil.a("NotifySendData", "packagingDeleteNotificationIdTlv isSupportRepeatedNotifyProcess false");
            return "";
        }
        String c2 = cvx.c(bundle.getString("data_channel_id", ""));
        return cvx.e(i) + khs.b(c2.length() / 2) + c2;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        byte b2 = bArr[0];
        if (b2 == 2) {
            c(bArr);
            b(bArr);
        } else if (b2 == 33) {
            jrg.b(bArr, deviceInfo);
        } else {
            LogUtil.c("NotifySendData", "getResult else");
        }
    }

    /* loaded from: classes5.dex */
    class d implements Handler.Callback {
        private d() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("NotifySendData", "message is null");
                return false;
            }
            LogUtil.a("NotifySendData", "OneHourHandler message.what: ", Integer.valueOf(message.what));
            if (message.what != 100) {
                return false;
            }
            khu.this.m();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        synchronized (d) {
            ExtendHandler extendHandler = this.w;
            if (extendHandler != null) {
                extendHandler.removeMessages(100);
                extendHandler.sendEmptyMessage(100, 3600000L);
            }
        }
        boolean b2 = jrg.b();
        LogUtil.a("NotifySendData", "handleCheckService isAuthorizeEnabled: ", Boolean.valueOf(b2));
        if (b2) {
            ThreadPoolManager.d().execute(new b());
        }
    }

    /* loaded from: classes5.dex */
    class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean ab = CommonUtil.ab(BaseApplication.getContext());
            LogUtil.a("NotifySendData", "NotifyAlert NotificationListener is: ", Boolean.valueOf(ab));
            if (ab) {
                khu.this.a(1);
            } else {
                khu.this.a(0);
                khu.this.c(BaseApplication.getContext());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        ReleaseLogUtil.e("R_NotifySendData", "toggleNotificationListenerService enter");
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.setComponentEnabledSetting(new ComponentName(context, "com.huawei.bone.ui.setting.NotificationPushListener"), 2, 1);
            packageManager.setComponentEnabledSetting(new ComponentName(context, "com.huawei.bone.ui.setting.NotificationPushListener"), 1, 1);
        } catch (SecurityException e2) {
            LogUtil.b("NotifySendData", "toggleNotificationListenerService Exception", ExceptionUtils.d(e2));
        }
    }

    public boolean e() {
        return this.s;
    }

    public boolean d() {
        return this.t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("NotifySendData", "enter reportBi() type: ", Integer.valueOf(i));
        Intent intent = new Intent("com.huawei.health.nitification_service_bi_change");
        intent.putExtra("BIStatus", i);
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    private void c(String str, int i) {
        int w = (CommonUtil.w(str) / 6) * 6;
        if (w > 0) {
            if (i == 5) {
                this.z = w;
                LogUtil.a("NotifySendData", "unNotificationContent mYellowPagesLength: ", Integer.valueOf(w));
            } else if (i == 6) {
                this.f = w;
                LogUtil.a("NotifySendData", "unNotificationContent mContentSignLength: ", Integer.valueOf(w));
            } else if (i == 7) {
                this.l = w;
                LogUtil.a("NotifySendData", "unNotificationContent mIncomingNumberLength: ", Integer.valueOf(w));
            } else {
                LogUtil.h("NotifySendData", "can not find length");
            }
        }
    }

    public void b(byte[] bArr) {
        byte b2 = bArr[1];
        if (b2 == 16) {
            if (khs.b()) {
                kir.d().d(bArr);
            }
        } else if (b2 == 17) {
            kje.c(bArr);
        } else {
            khg.d().e(bArr);
        }
    }

    public void e(String str) {
        khg.d().b(str);
    }

    public void g() {
        khg.d().f();
    }

    public boolean a() {
        List<DeviceInfo> deviceList = this.k.getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "NotifySendData");
        LogUtil.h("NotifySendData", "isMessageAlert getDeviceList: ", Integer.valueOf(deviceList.size()));
        if (deviceList.isEmpty()) {
            return false;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (it.hasNext()) {
            if (khs.a(it.next())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInfo l() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendData");
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

    public boolean h() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "NotifySendData");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("NotifySendData", "isSplitAnswer deviceInfo is null");
            return false;
        }
        return cwi.c(deviceInfo, 74);
    }

    public DeviceCommand d(DeviceCommand deviceCommand) {
        if (deviceCommand == null) {
            LogUtil.h("NotifySendData", "incomingCallMessageExtendToHex deviceCommand is null");
            return deviceCommand;
        }
        try {
            List<BluetoothDevice> i = iyl.d().i();
            if (!bkz.e(i)) {
                StringBuffer stringBuffer = new StringBuffer();
                for (BluetoothDevice bluetoothDevice : i) {
                    stringBuffer.append("incomingCallMessageExtendToHex devicename: ").append(bluetoothDevice.getName()).append(", mac:").append(khs.a(bluetoothDevice.toString()));
                }
                LogUtil.h("NotifySendData", stringBuffer.toString());
            }
            StringBuffer stringBuffer2 = new StringBuffer(16);
            stringBuffer2.append(cvx.d(deviceCommand.getDataContent())).append(cvx.e(28)).append(cvx.e(1)).append(cvx.e(i.size()));
            byte[] a2 = cvx.a(stringBuffer2.toString());
            LogUtil.a("NotifySendData", "incomingCallMessageExtendToHex command: ", stringBuffer2.toString());
            deviceCommand.setDataContent(a2);
            deviceCommand.setDataLen(a2.length);
            return deviceCommand;
        } catch (SecurityException e2) {
            LogUtil.b("NotifySendData", "discoverClassicDevice SecurityException", ExceptionUtils.d(e2));
            return null;
        }
    }
}

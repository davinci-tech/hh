package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.android.internal.telephony.ITelephony;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class kis implements ParserInterface {
    private static kis e;
    private Context c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14403a = {"android.permission.CALL_PHONE", "android.permission.READ_PHONE_STATE"};

    private kis(Context context) {
        this.c = context;
    }

    public static kis d(Context context) {
        kis kisVar;
        synchronized (d) {
            if (e == null && context != null) {
                e = new kis(context);
            }
            kisVar = e;
        }
        return kisVar;
    }

    private void c(byte[] bArr) {
        ReleaseLogUtil.e("Notify_HwRejectCallManager", "handleCallingOperationReport() enter");
        if (bArr == null || bArr.length < 5) {
            LogUtil.h("HwRejectCallManager", "handleCallingOperationReport, dataInfos is null or length less than 5, return");
            return;
        }
        if (kke.h() && bArr[1] == 2) {
            e(bArr);
            return;
        }
        if (bArr[1] != 1) {
            LogUtil.h("HwRejectCallManager", "handleCallingOperationReport, commandId is not calling operation");
            return;
        }
        byte b = bArr[4];
        if (b != 1) {
            LogUtil.h("HwRejectCallManager", "handleCallingOperationReport, operation is not end call");
            return;
        }
        LogUtil.a("HwRejectCallManager", "operation :", Byte.valueOf(b), ", end call");
        if (Build.VERSION.SDK_INT >= 28) {
            d();
        } else {
            b();
        }
    }

    private void d() {
        LogUtil.a("HwRejectCallManager", "executing API28 phone hang up");
        if (ContextCompat.checkSelfPermission(this.c, "android.permission.ANSWER_PHONE_CALLS") != 0) {
            LogUtil.h("HwRejectCallManager", "API28 hangs up the phone is not enough permission, finish");
            return;
        }
        Object systemService = this.c.getSystemService("telecom");
        TelecomManager telecomManager = systemService instanceof TelecomManager ? (TelecomManager) systemService : null;
        if (telecomManager == null) {
            LogUtil.h("HwRejectCallManager", "telephoneCommonManager is null, finish");
            return;
        }
        try {
            if (telecomManager.endCall()) {
                LogUtil.a("HwRejectCallManager", "executing API28 phone hang up finish");
                return;
            }
        } catch (SecurityException e2) {
            LogUtil.b("HwRejectCallManager", "SecurityException ", e2.getMessage());
        } catch (Exception e3) {
            LogUtil.b("HwRejectCallManager", "Exception ", e3.getMessage());
        }
        LogUtil.h("HwRejectCallManager", "hanging up is terminated, finish");
    }

    private void b() {
        try {
            Object systemService = this.c.getSystemService("phone");
            TelephonyManager telephonyManager = systemService instanceof TelephonyManager ? (TelephonyManager) systemService : null;
            if (telephonyManager == null) {
                LogUtil.h("HwRejectCallManager", "telephonyManager is null, finish");
                return;
            }
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getITelephony", null);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(telephonyManager, null);
            ITelephony iTelephony = invoke instanceof ITelephony ? (ITelephony) invoke : null;
            if (iTelephony != null) {
                LogUtil.a("HwRejectCallManager", "sdk level:", Integer.valueOf(Build.VERSION.SDK_INT));
                if (iTelephony.endCall()) {
                    LogUtil.h("HwRejectCallManager", "ITelephony.endCall() Succeed");
                    return;
                } else {
                    c(iTelephony);
                    return;
                }
            }
            LogUtil.h("HwRejectCallManager", "telephoneAidl is null!");
        } catch (RuntimeException unused) {
            LogUtil.b("HwRejectCallManager", "endCall RuntimeException");
            a();
        } catch (Exception unused2) {
            LogUtil.b("HwRejectCallManager", "endCall Exception");
        }
    }

    private void c(ITelephony iTelephony) {
        try {
            Object systemService = this.c.getSystemService("telephony_subscription_service");
            SubscriptionManager subscriptionManager = systemService instanceof SubscriptionManager ? (SubscriptionManager) systemService : null;
            if (subscriptionManager == null) {
                LogUtil.h("HwRejectCallManager", "subscriptionManager is null!");
                return;
            }
            List<SubscriptionInfo> activeSubscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            if (activeSubscriptionInfoList != null && !activeSubscriptionInfoList.isEmpty()) {
                for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                    if (subscriptionInfo != null) {
                        boolean endCallForSubscriber = iTelephony.endCallForSubscriber(subscriptionInfo.getSubscriptionId());
                        LogUtil.a("HwRejectCallManager", "endCallForSubscriber result:", Boolean.valueOf(endCallForSubscriber));
                        if (endCallForSubscriber) {
                            return;
                        }
                    }
                }
                a();
                return;
            }
            LogUtil.h("HwRejectCallManager", "endCallForSubscriber Get Active Subscription Info List Failed");
        } catch (RuntimeException unused) {
            LogUtil.b("HwRejectCallManager", "endCall RuntimeException");
            a();
        } catch (Exception unused2) {
            LogUtil.b("HwRejectCallManager", "endCall Exception");
        }
    }

    private void a() {
        boolean c = jdi.c(this.c, f14403a);
        LogUtil.a("HwRejectCallManager", "check call phone and read phone state permission result :", Boolean.valueOf(c));
        if (c) {
            return;
        }
        LogUtil.a("HwRejectCallManager", "no call reject permission, send broadcast");
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.NO_CALL_REJECT_PERMISION");
        this.c.sendBroadcast(intent, LocalBroadcast.c);
    }

    private void e(byte[] bArr) {
        if (!jdi.c(this.c, new String[]{"android.permission.SEND_SMS"})) {
            LogUtil.h("HwRejectCallManager", "handleSmsReject not send sms permission");
            return;
        }
        String d2 = cvx.d(bArr);
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4, d2.length())).e();
            if (e2 == null || e2.isEmpty()) {
                LogUtil.h("HwRejectCallManager", "handleSmsReject tlvList is empty");
                return;
            }
            String str = "";
            String str2 = "";
            for (cwd cwdVar : e2) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    str = cvx.e(cwdVar.c());
                } else if (w == 3) {
                    str2 = cvx.e(cwdVar.c());
                }
            }
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                LogUtil.h("HwRejectCallManager", "handleSmsReject incomingNumber or smsContent is empty");
            } else {
                bOz_(bOy_(str), str, str2);
            }
        } catch (cwg unused) {
            LogUtil.b("HwRejectCallManager", "handleSmsReject TlvException");
        }
    }

    private SmsManager bOy_(String str) {
        if (!kke.a()) {
            LogUtil.a("HwRejectCallManager", "getSmsManager send default smsManager");
            return SmsManager.getDefault();
        }
        int e2 = kke.e(str);
        kke.b(str);
        LogUtil.a("HwRejectCallManager", "getSmsManager subscriptionId: ", Integer.valueOf(e2));
        if (e2 != -1) {
            return SmsManager.getSmsManagerForSubscriptionId(e2);
        }
        return null;
    }

    private void bOz_(SmsManager smsManager, String str, String str2) {
        if (smsManager == null) {
            LogUtil.h("HwRejectCallManager", "sendSms smsManager is null");
            sqo.ah("sendSms smsManager is null");
            return;
        }
        ArrayList<String> divideMessage = smsManager.divideMessage(str2);
        if (divideMessage == null || divideMessage.isEmpty()) {
            LogUtil.h("HwRejectCallManager", "sendSms messages is null");
            sqo.ah("sendSms messages is null");
            return;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(this.c, 0, new Intent("device_reject_send_sms_action"), 201326592);
        ArrayList<PendingIntent> arrayList = new ArrayList<>();
        for (int i = 0; i < divideMessage.size(); i++) {
            arrayList.add(broadcast);
        }
        try {
            smsManager.sendMultipartTextMessage(str, null, divideMessage, arrayList, null);
        } catch (SecurityException unused) {
            LogUtil.b("HwRejectCallManager", "sendSms occur SecurityException");
            sqo.r("sendSms occur SecurityException");
        }
        e();
    }

    private void e() {
        byte[] d2 = new bms().i(127, 100000).d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(4);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(d2);
        deviceCommand.setDataLen(d2.length);
        jsz.b(this.c).sendDeviceData(deviceCommand);
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        c(bArr);
    }
}

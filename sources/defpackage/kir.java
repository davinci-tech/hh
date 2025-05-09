package defpackage;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kir {

    /* renamed from: a, reason: collision with root package name */
    private static kir f14401a;
    private static final Object e = new Object();
    private BroadcastReceiver b;
    private Map<String, kil> d = new HashMap(16);
    private Context c = BaseApplication.getContext();

    private kir() {
        b();
    }

    public static kir d() {
        kir kirVar;
        synchronized (e) {
            if (f14401a == null) {
                f14401a = new kir();
            }
            kirVar = f14401a;
        }
        return kirVar;
    }

    public void d(byte[] bArr) {
        kil a2 = a(bArr);
        if (a2 == null) {
            return;
        }
        if (a2.e() == 2) {
            e(a2.d(), a2.c());
            return;
        }
        if (a2.e() != 1) {
            LogUtil.h("SmsSendManager", "parseSmsReply not support other sender");
            return;
        }
        LogUtil.a("SmsSendManager", "deviceReplyClassify encodeFormat: ", Integer.valueOf(a2.b()), ",subId: ", Integer.valueOf(a2.j()), ",senderNumber: ", khs.a(a2.d()), ",attachmentId: ", a2.a(), ",smsContent: ", bky.e(a2.c()));
        if (!TextUtils.isEmpty(a2.a()) && !TextUtils.isEmpty(a2.d())) {
            this.d.put(a2.a(), a2);
        }
        if (TextUtils.isEmpty(a2.d()) || TextUtils.isEmpty(a2.c())) {
            return;
        }
        if (!jeg.d().c(BaseApplication.getContext(), "android.permission.SEND_SMS")) {
            ReleaseLogUtil.b("SmsSendManager", "deviceReplyClassify HAS_NO_PERMISSION: Manifest.permission.SEND_SMS");
            kiq.c(127);
        } else {
            bOx_(kke.bPk_(a2.j()), a2.d(), a2.c());
        }
    }

    private kil a(byte[] bArr) {
        List<cwd> d = kiq.d(bArr);
        if (d == null) {
            LogUtil.h("SmsSendManager", "generateSmsReceivedInfo tlvList is null");
            return null;
        }
        String str = "";
        int i = 2;
        int i2 = -1;
        String str2 = "";
        int i3 = 1;
        String str3 = str2;
        for (cwd cwdVar : d) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 1:
                    i3 = CommonUtil.w(cwdVar.c());
                    break;
                case 2:
                    i = CommonUtil.w(cwdVar.c());
                    break;
                case 3:
                    i2 = CommonUtil.w(cwdVar.c());
                    break;
                case 4:
                    str = cvx.e(cwdVar.c());
                    break;
                case 5:
                    str3 = cvx.e(cwdVar.c());
                    break;
                case 6:
                    str2 = cwdVar.c();
                    break;
            }
        }
        kil kilVar = new kil(str, i2);
        kilVar.e(i3);
        kilVar.b(i);
        kilVar.a(str3);
        if (!TextUtils.isEmpty(str2)) {
            kilVar.d(e(i, str2));
        }
        return kilVar;
    }

    private void bOx_(SmsManager smsManager, String str, String str2) {
        ArrayList<String> divideMessage = smsManager.divideMessage(str2);
        if (divideMessage == null || divideMessage.isEmpty()) {
            LogUtil.h("SmsSendManager", "sendSmsReply messages is null");
            return;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(this.c, 0, new Intent("action_device_reject_send_sms"), 201326592);
        ArrayList<PendingIntent> arrayList = new ArrayList<>();
        for (int i = 0; i < divideMessage.size(); i++) {
            arrayList.add(broadcast);
        }
        try {
            smsManager.sendMultipartTextMessage(str, null, divideMessage, arrayList, null);
        } catch (SecurityException e2) {
            ReleaseLogUtil.c("R_SmsSendManager", "sendSms occur SecurityException ", ExceptionUtils.d(e2));
        }
        LogUtil.a("SmsSendManager", "sendSmsReply messages finished");
    }

    private void b() {
        if (this.b != null) {
            LogUtil.h("SmsSendManager", "registerSmsSendReceiver mBroadcastReceiver already register");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_device_reject_send_sms");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: kir.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                int resultCode = getResultCode();
                LogUtil.a("SmsSendManager", "registerSmsSendReceiver resultCode:", Integer.valueOf(resultCode));
                kiq.c(resultCode);
            }
        };
        this.b = broadcastReceiver;
        BroadcastManagerUtil.bFC_(this.c, broadcastReceiver, intentFilter, LocalBroadcast.c, null);
    }

    private String e(int i, String str) {
        if (i == 3) {
            return new String(cvx.a(str), Charset.forName("utf16"));
        }
        return cvx.e(str);
    }

    private void e(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.action.ACTION_NOTIFICATION_PUSH");
        intent.putExtra("action_type", "key_replay");
        intent.putExtra("key_replay_destination", str);
        intent.putExtra("key_replay_content", str2);
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), intent);
        LogUtil.a("SmsSendManager", "deviceReply sendBroadcastPackage finished!");
    }
}

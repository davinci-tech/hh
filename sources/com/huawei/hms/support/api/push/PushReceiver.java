package com.huawei.hms.support.api.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.d;
import com.huawei.hms.push.q;
import com.huawei.hms.push.r;
import com.huawei.hms.push.utils.JsonUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public final class PushReceiver extends BroadcastReceiver {

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Context f5980a;
        private Intent b;

        @Override // java.lang.Runnable
        public void run() {
            Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
            intent.setPackage(this.b.getPackage());
            try {
                JSONObject b = PushReceiver.b(this.b);
                String string = JsonUtil.getString(b, "moduleName", "");
                int i = JsonUtil.getInt(b, "msgType", 0);
                int i2 = JsonUtil.getInt(b, "status", 0);
                if (ErrorEnum.SUCCESS.getInternalCode() != i2) {
                    i2 = ErrorEnum.ERROR_APP_SERVER_NOT_ONLINE.getInternalCode();
                }
                Bundle bundle = new Bundle();
                if ("Push".equals(string) && i == 1) {
                    bundle.putString(RemoteMessageConst.MSGTYPE, VastAttribute.DELIVERY);
                    bundle.putString("message_id", JsonUtil.getString(b, "msgId", ""));
                    bundle.putInt(VastAttribute.ERROR, i2);
                    bundle.putString("transaction_id", JsonUtil.getString(b, "transactionId", ""));
                } else {
                    if (this.b.getExtras() != null) {
                        bundle.putAll(this.b.getExtras());
                    }
                    bundle.putString(RemoteMessageConst.MSGTYPE, "received_message");
                    bundle.putString("message_id", this.b.getStringExtra("msgIdStr"));
                    bundle.putByteArray(RemoteMessageConst.MSGBODY, this.b.getByteArrayExtra("msg_data"));
                    bundle.putString(RemoteMessageConst.DEVICE_TOKEN, com.huawei.hms.push.a.a(this.b.getByteArrayExtra(RemoteMessageConst.DEVICE_TOKEN)));
                    bundle.putInt(RemoteMessageConst.INPUT_TYPE, 1);
                    bundle.putString("message_proxy_type", this.b.getStringExtra("message_proxy_type"));
                }
                if (new r().a(this.f5980a, bundle, intent)) {
                    HMSLog.i("PushReceiver", "receive " + this.b.getAction() + " and start service success");
                    return;
                }
                HMSLog.e("PushReceiver", "receive " + this.b.getAction() + " and start service failed");
            } catch (RuntimeException unused) {
                HMSLog.e("PushReceiver", "handle push message occur exception.");
            }
        }

        private b(Context context, Intent intent) {
            this.f5980a = context;
            this.b = intent;
        }
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Context f5981a;
        private Intent b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] byteArrayExtra = this.b.getByteArrayExtra(RemoteMessageConst.DEVICE_TOKEN);
                if (byteArrayExtra != null && byteArrayExtra.length != 0) {
                    HMSLog.i("PushReceiver", "receive a push token: " + this.f5981a.getPackageName());
                    Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
                    intent.setPackage(this.b.getPackage());
                    Bundle bundle = new Bundle();
                    bundle.putString(RemoteMessageConst.MSGTYPE, "new_token");
                    bundle.putString(RemoteMessageConst.DEVICE_TOKEN, com.huawei.hms.push.a.a(byteArrayExtra));
                    bundle.putString("transaction_id", this.b.getStringExtra("transaction_id"));
                    bundle.putString("subjectId", this.b.getStringExtra("subjectId"));
                    bundle.putInt(VastAttribute.ERROR, this.b.getIntExtra(VastAttribute.ERROR, ErrorEnum.SUCCESS.getInternalCode()));
                    bundle.putString("belongId", this.b.getStringExtra("belongId"));
                    if (new r().a(this.f5981a, bundle, intent)) {
                        return;
                    }
                    HMSLog.e("PushReceiver", "receive " + this.b.getAction() + " and start service failed");
                    return;
                }
                HMSLog.i("PushReceiver", "get a deviceToken, but it is null or empty");
            } catch (RejectedExecutionException unused) {
                HMSLog.e("PushReceiver", "execute task error");
            } catch (Exception unused2) {
                HMSLog.e("PushReceiver", "handle push token error");
            }
        }

        private c(Context context, Intent intent) {
            this.f5981a = context;
            this.b = intent;
        }
    }

    private void b(Context context, Intent intent) {
        try {
            if (intent.hasExtra(RemoteMessageConst.DEVICE_TOKEN)) {
                q.a().execute(new c(context, intent));
            } else {
                HMSLog.i("PushReceiver", "This message dose not sent by hwpush.");
            }
        } catch (RuntimeException unused) {
            HMSLog.e("PushReceiver", "handlePushMessageEvent execute task runtime exception.");
        } catch (Exception unused2) {
            HMSLog.e("PushReceiver", "handlePushTokenEvent execute task error");
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        HMSLog.i("PushReceiver", "push receive broadcast message, Intent:" + intent.getAction() + " pkgName:" + context.getPackageName());
        try {
            intent.getStringExtra("TestIntent");
            String action = intent.getAction();
            if (ResourceLoaderUtil.getmContext() == null) {
                ResourceLoaderUtil.setmContext(context.getApplicationContext());
            }
            if ("com.huawei.android.push.intent.REGISTRATION".equals(action)) {
                b(context, intent);
            } else if ("com.huawei.android.push.intent.RECEIVE".equals(action)) {
                a(context, intent);
            } else {
                HMSLog.i("PushReceiver", "message can't be recognised.");
            }
        } catch (Exception unused) {
            HMSLog.e("PushReceiver", "intent has some error");
        }
    }

    private void a(Context context, Intent intent) {
        try {
            if (intent.hasExtra("msg_data")) {
                q.a().execute(new b(context, intent));
            } else {
                HMSLog.i("PushReceiver", "This push message dose not sent by hwpush.");
            }
        } catch (RuntimeException unused) {
            HMSLog.e("PushReceiver", "handlePushMessageEvent execute task runtime exception.");
        } catch (Exception unused2) {
            HMSLog.e("PushReceiver", "handlePushMessageEvent execute task error");
        }
    }

    private static JSONObject b(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject(RemoteMessageConst.MessageBody.PS_CONTENT);
        }
        return null;
    }

    private static JSONObject a(byte[] bArr) {
        try {
            return new JSONObject(com.huawei.hms.push.a.a(bArr));
        } catch (JSONException unused) {
            HMSLog.w("PushReceiver", "JSONException:parse message body failed.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject b(Intent intent) throws RuntimeException {
        JSONObject a2 = a(intent.getByteArrayExtra("msg_data"));
        JSONObject a3 = a(a2);
        String string = JsonUtil.getString(a3, "data", null);
        if (d.a(a3, b(a3), string)) {
            return a2;
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            return null;
        }
    }

    private static JSONObject a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("msgContent");
        }
        return null;
    }
}

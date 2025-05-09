package defpackage;

import android.content.Intent;
import android.os.BadParcelableException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LogAnonymous;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class joa {
    private static boolean b(String str, String str2, int i, String str3, int i2) {
        return str.length() <= 1000 && ("09C".equals(str2) || "02E".equals(str2)) && i > 0 && i < 255 && str3.matches("[a-zA-Z0-9]+") && (i2 == 0 || i2 == 1);
    }

    public static void bIz_(Intent intent) {
        try {
            JSONObject jSONObject = new JSONObject(intent.getStringExtra("device_link_req"));
            final String string = jSONObject.getString("reqId");
            final String string2 = jSONObject.getString("devType");
            final String string3 = jSONObject.getString("targetDevice");
            final int i = jSONObject.getInt("timeout");
            final int i2 = jSONObject.getInt("reconnectAble");
            LogUtil.a("ScreenRaceToControlUtil", "get param from content, requestId: ", string, " deviceType: ", string2, "timeout: ", Integer.valueOf(i), " targetDevice: ", string3, "tempReconnectAble: ", Integer.valueOf(i2));
            if (b(string, string2, i, string3, i2)) {
                ThreadPoolManager.d().d("ScreenRaceToControlUtil", new Runnable() { // from class: joe
                    @Override // java.lang.Runnable
                    public final void run() {
                        joa.e(string, string2, i, string3, i2);
                    }
                });
            } else {
                LogUtil.a("ScreenRaceToControlUtil", "check value is Illegal.");
            }
        } catch (BadParcelableException unused) {
            LogUtil.b("ScreenRaceToControlUtil", "parseJsonAndSendBroadcast getStringExtra catch BadParcelableException");
        } catch (JSONException e) {
            LogUtil.b("ScreenRaceToControlUtil", "parseJsonAndSendBroadcast Exception : ", LogAnonymous.b((Throwable) e));
        }
    }

    static /* synthetic */ void e(String str, String str2, int i, String str3, int i2) {
        Intent intent = new Intent();
        intent.setAction("com.huawei.health.ACTION_NOTIFY_PUSH_MULTI_LINK");
        intent.putExtra("reqId", str);
        intent.putExtra("devType", str2);
        intent.putExtra("timeout", i);
        intent.putExtra("targetDevice", str3);
        intent.putExtra("reconnectAble", i2);
        BroadcastManagerUtil.bFI_(BaseApplication.e(), intent);
    }
}

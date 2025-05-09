package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LocalBroadcast;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class gnp {
    public static Bundle aPD_(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
        } catch (JSONException unused) {
            LogUtil.b("YoYoWidgetUtil", "jsonToBundle is errors");
        }
        return bundle;
    }

    public static void a(Context context) {
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setAction("com.huawei.bone.action.UNITSWITCH");
        if (LocalBroadcastManager.getInstance(context) != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
        if (context != null) {
            LogUtil.a("YoYoWidgetUtil", "----send broadcast to social----");
            context.sendBroadcast(intent, LocalBroadcast.c);
        } else {
            LogUtil.a("YoYoWidgetUtil", "----mContext is null----");
        }
    }

    public static void d(String str, Context context, long j) {
        LogUtil.a("YoYoWidgetUtil", "yoyowidget removeCard", str);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setAction("com.huawei.bone.action.REMOVECARD");
        intent.putExtra("type", str);
        intent.putExtra("EndTime", j);
        if (LocalBroadcastManager.getInstance(context) != null) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
        if (context != null) {
            LogUtil.a("YoYoWidgetUtil", "----removeCard send broadcast to social----");
            context.sendBroadcast(intent, LocalBroadcast.c);
        } else {
            LogUtil.a("YoYoWidgetUtil", "----removeCard mContext is null----");
        }
    }
}

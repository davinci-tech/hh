package defpackage;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class bvz {
    private static final Object e = new Object();
    private static ArrayList<IBaseResponseCallback> c = new ArrayList<>();

    public static void a(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            if (!c.contains(iBaseResponseCallback)) {
                c.add(iBaseResponseCallback);
            }
        }
    }

    public static void b(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            c.remove(iBaseResponseCallback);
        }
    }

    public static void e(Context context, String str) {
        LogUtil.a("LocalBroadcastManager", "sendBroadcast:", str);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
        if (localBroadcastManager == null) {
            LogUtil.h("LocalBroadcastManager", "sendBroadcast, but localBroadcastManager is null");
            return;
        }
        if (str.equals("com.huawei.bone.action.FITNESS_USERINFO_UPDATED")) {
            UserInfomation e2 = bqi.c(context).e();
            if (e2 == null) {
                LogUtil.h("LocalBroadcastManager", "userInfo not useful,do not send localBR-ACTION_FITNESS_USERINFO_UPDATED");
                return;
            }
            Intent intent = new Intent(str);
            intent.putExtra("userInfo", e2);
            localBroadcastManager.sendBroadcast(intent);
            b();
            return;
        }
        localBroadcastManager.sendBroadcast(new Intent(str));
    }

    public static void b() {
        LogUtil.a("LocalBroadcastManager", "enter notifyCallback.");
        synchronized (e) {
            Iterator<IBaseResponseCallback> it = c.iterator();
            while (it.hasNext()) {
                it.next().d(0, null);
            }
        }
    }
}

package defpackage;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes5.dex */
public class kyx {
    public static void b(int i) {
        try {
            LogUtil.a("DataSyncCycleUtil", "initDataSyncCycle type:", Integer.valueOf(i));
            Class<?> cls = Class.forName("com.huawei.health.receiver.DataSyncActivityCycle");
            cls.getDeclaredMethod("registerDataSyncReceiver", Integer.TYPE).invoke(cls.newInstance(), Integer.valueOf(i));
        } catch (ClassNotFoundException unused) {
            LogUtil.b("DataSyncCycleUtil", "initDataSyncCycle ClassNotFoundException");
        } catch (IllegalAccessException unused2) {
            LogUtil.b("DataSyncCycleUtil", "initDataSyncCycle IllegalAccessException");
        } catch (InstantiationException unused3) {
            LogUtil.b("DataSyncCycleUtil", "initDataSyncCycle InstantiationException");
        } catch (NoSuchMethodException unused4) {
            LogUtil.b("DataSyncCycleUtil", "initDataSyncCycle NoSuchMethodException");
        } catch (InvocationTargetException unused5) {
            LogUtil.b("DataSyncCycleUtil", "initDataSyncCycle InvocationTargetException");
        }
    }

    public static void bSY_(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.DataSyncActivityCycle.Sleep");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void bTa_(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(broadcastReceiver);
    }

    public static void b() {
        BaseApplication.getContext().sendBroadcast(new Intent("com.huawei.health.DataSyncActivityCycle.Exercise"), LocalBroadcast.c);
    }

    public static void bSX_(BroadcastReceiver broadcastReceiver) {
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), broadcastReceiver, new IntentFilter("com.huawei.health.DataSyncActivityCycle.Exercise"), LocalBroadcast.c, null);
    }

    public static void bSZ_(BroadcastReceiver broadcastReceiver) {
        BaseApplication.getContext().unregisterReceiver(broadcastReceiver);
    }
}

package defpackage;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import java.util.List;

/* loaded from: classes4.dex */
public class gye {
    public static boolean e(Context context, String str) {
        if (context == null || str == null || !(context.getSystemService("activity") instanceof ActivityManager)) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        ComponentName componentName = koq.c(runningTasks) ? runningTasks.get(0).topActivity : null;
        if (componentName == null) {
            return false;
        }
        return componentName.getClassName().contains(str);
    }
}

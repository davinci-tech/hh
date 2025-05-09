package health.compact.a;

import android.app.Notification;
import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes.dex */
public class NotificationHelper {
    private static final String d = "NotificationHelper";

    private NotificationHelper() {
    }

    public static void aOt_(Context context, Notification.Builder builder) {
        if (context == null || builder == null) {
            com.huawei.hwlogsmodel.LogUtil.h(d, "context or builder is null");
        } else if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            builder.setSmallIcon(R.drawable.ic_wear_notification);
        } else {
            builder.setSmallIcon(R.drawable.healthlogo_ic_notification);
        }
    }
}

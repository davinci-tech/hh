package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.activity.notification.NotificationBaseActivity;
import com.huawei.ui.device.activity.notification.NotificationTipActivity;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class nxa extends Handler {
    private WeakReference<NotificationBaseActivity> c;

    public nxa(NotificationBaseActivity notificationBaseActivity) {
        this.c = new WeakReference<>(notificationBaseActivity);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        NotificationBaseActivity notificationBaseActivity = this.c.get();
        if (notificationBaseActivity == null || message == null) {
            return;
        }
        int i = message.what;
        if (i == 0) {
            if (!notificationBaseActivity.isFinishing() && !notificationBaseActivity.isDestroyed() && notificationBaseActivity.mNotificationAppAdapter != null) {
                notificationBaseActivity.mNotificationAppAdapter.notifyDataSetChanged();
            }
            notificationBaseActivity.updateUi();
            return;
        }
        if (i == 1) {
            LogUtil.a("UpdateListHandler", "handleMessage SHOW_TIP");
            Intent intent = new Intent();
            intent.setClass(notificationBaseActivity.mContext, NotificationTipActivity.class);
            notificationBaseActivity.startActivity(intent);
            return;
        }
        LogUtil.a("UpdateListHandler", "handleMessage default");
    }
}

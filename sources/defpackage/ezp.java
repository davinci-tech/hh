package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.receiver.GprsAbstractPushProcess;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ezq;
import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class ezp extends GprsAbstractPushProcess {
    @Override // com.huawei.health.receiver.GprsAbstractPushProcess
    public void process(Context context, ezq.e eVar, GprsAbstractPushProcess.ProcessResult processResult) {
        e(eVar.c);
        processResult.onResult();
    }

    private void e(int i) {
        if (!CommonUtil.bh() && !NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            LogUtil.h("GprsBloodPressurePushReceiver", "sendGprsBloodPressureNotification permission is not enabled.");
            return;
        }
        LogUtil.a("GprsBloodPressurePushReceiver", "sendGprsBloodPressureNotification dataNum:", Integer.valueOf(i));
        Intent intent = new Intent();
        intent.setData(Uri.parse("huaweischeme://healthapp/basicHealth?healthType=9"));
        PendingIntent activity = PendingIntent.getActivity(cpp.a(), 0, intent, AppRouterExtras.COLDSTART);
        Resources resources = cpp.a().getResources();
        String string = resources.getString(R.string.IDS_device_recive_blood_pressure_content_two);
        Notification build = jdh.c().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setContentTitle(resources.getString(R.string.IDS_device_recive_blood_pressure_title)).setContentText(string).setStyle(new Notification.BigTextStyle().bigText(string)).setContentIntent(activity).setAutoCancel(true).build();
        build.flags |= 16;
        jdh.c().xh_(20210924, build);
    }
}

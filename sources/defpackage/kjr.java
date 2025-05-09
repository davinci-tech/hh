package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.huawei.health.R;
import com.huawei.health.manager.util.UnitUtilExt;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.NotificationHelper;
import health.compact.a.NotificationUtil;

/* loaded from: classes5.dex */
public class kjr {
    private Notification.Builder d = null;
    private Notification b = null;

    public void a(Context context, int i, int i2, int i3) {
        LogUtil.a("NotificationUtil", "upDateHealthNotification()", Integer.valueOf(i), "|", Integer.valueOf(i2), "|", Integer.valueOf(i3));
        if (CommonUtil.ck() || CommonUtil.bf()) {
            this.b = bOQ_(context, i, i2, i3);
        } else {
            this.b = bOR_(context, i, i2);
        }
        LogUtil.a("NotificationUtil", "startNotification");
        if (context instanceof Service) {
            ((Service) context).startForeground(10010, this.b);
        }
    }

    private Notification bOR_(Context context, int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        String b = NotificationUtil.b(context, i + "");
        String a2 = NotificationUtil.a(context, UnitUtilExt.e(context, (double) Math.round(((float) i2) / 1000.0f), 1, 0));
        if (this.d == null) {
            Notification.Builder xf_ = jdh.c().xf_();
            this.d = xf_;
            NotificationHelper.aOt_(context, xf_);
            this.d.setWhen(System.currentTimeMillis());
            this.d.setShowWhen(false);
            this.d.setContentIntent(NotificationUtil.aOw_(context));
            Intent intent = new Intent("steps_notify_delete");
            intent.setPackage(context.getPackageName());
            this.d.setDeleteIntent(PendingIntent.getBroadcast(context, 10010, intent, 201326592));
            this.d.setPriority(0);
            this.d.setOngoing(true);
        }
        this.d.setContentTitle(b).setContentText(a2);
        return this.d.build();
    }

    private Notification bOQ_(Context context, int i, int i2, int i3) {
        b(context);
        RemoteViews bOS_ = bOS_(context);
        bOS_.setImageViewResource(R.id.icon, R.drawable.healthlogo_ic_notification);
        String string = context.getResources().getString(R$string.IDS_plugindameon_hw_app_name);
        if (i < 0) {
            i = 0;
        }
        String d = NotificationUtil.d(context, i);
        String e = NotificationUtil.e(context, i2);
        bOS_.setTextViewText(R.id.app_name_text, string);
        bOS_.setTextViewText(R.id.textStep, d);
        bOS_.setTextViewText(R.id.textStepUnit, context.getResources().getString(R$string.IDS_plugindameon_hw_phonecounter_widget_step_unit));
        bOS_.setTextViewText(R.id.textKcal, e);
        bOS_.setTextViewText(R.id.textKcalUnit, context.getResources().getString(R$string.IDS_plugindameon_hw_phonecounter_widget_kalo_unit));
        if (i3 > 0) {
            int round = (int) Math.round((i / i3) * 100.0d);
            if (round == 100 && i < i3) {
                round = 99;
            } else if (round > 100) {
                round = 100;
            } else {
                LogUtil.h("NotificationUtil", "completedGoal");
            }
            LogUtil.a("NotificationUtil", "completedGoal:", Integer.valueOf(round), ",", "stepGoal:", Integer.valueOf(i3));
            bOS_.setTextViewText(R.id.right_icon_text, NotificationUtil.c(context, round));
            bOS_.setImageViewResource(R.id.right_icon, NotificationUtil.b(round));
        }
        Notification build = this.d.build();
        build.contentView = bOS_;
        return build;
    }

    private RemoteViews bOS_(Context context) {
        if (CommonUtil.ar()) {
            if (d()) {
                return new RemoteViews(context.getPackageName(), R.layout.notify_large_font_scale);
            }
            return new RemoteViews(context.getPackageName(), R.layout.notify);
        }
        if (CommonUtil.co()) {
            if (d()) {
                return new RemoteViews(context.getPackageName(), R.layout.notify_emui9_large_font_scale);
            }
            return new RemoteViews(context.getPackageName(), R.layout.notify_emui9);
        }
        if (d()) {
            return new RemoteViews(context.getPackageName(), R.layout.notify_large_font_scale);
        }
        return new RemoteViews(context.getPackageName(), R.layout.notify);
    }

    private void b(Context context) {
        if (this.d == null) {
            Notification.Builder xf_ = jdh.c().xf_();
            this.d = xf_;
            xf_.setSmallIcon(R.drawable.healthlogo_ic_notification);
            this.d.setWhen(System.currentTimeMillis());
            this.d.setShowWhen(false);
            this.d.setContentIntent(NotificationUtil.aOw_(context));
            Intent intent = new Intent("steps_notify_delete");
            intent.setPackage(context.getPackageName());
            this.d.setDeleteIntent(PendingIntent.getBroadcast(context, 10010, intent, 201326592));
            this.d.setPriority(0);
            this.d.setOngoing(true);
            this.d.setGroup("NotificationUtil");
        }
    }

    private boolean d() {
        return BaseApplication.getContext().getResources().getConfiguration().fontScale >= 1.75f;
    }
}

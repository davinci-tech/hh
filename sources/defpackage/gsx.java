package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.common.security.SecurityConstant;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.manager.service.TrackJobService;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import health.compact.a.CommonUtil;

/* loaded from: classes4.dex */
public class gsx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile gsx f12917a;
    private static volatile PluginSportTrackAdapter d;
    private static final Object e = new Object();
    private boolean c;
    private JobInfo g;
    private JobScheduler i;
    private int j = 0;
    private boolean b = false;

    private gsx() {
        this.c = false;
        this.c = !CommonUtil.bh();
    }

    public static gsx a() {
        if (f12917a == null || d == null) {
            synchronized (e) {
                if (f12917a == null) {
                    f12917a = new gsx();
                }
                if (d == null) {
                    d = gso.e().c();
                }
            }
        }
        return f12917a;
    }

    public void e(Context context) {
        if (this.b) {
            return;
        }
        LogUtil.a("Track_TrackAliveUtil", "start KeepAlive");
        this.b = true;
        if (d != null) {
            this.j = 0;
            d.startTickTrackDog();
        }
        i(context);
    }

    private void i(Context context) {
        if (CommonUtil.bh()) {
            LogUtil.a("Track_TrackAliveUtil", "emui no need start JobService.");
            return;
        }
        LogUtil.a("Track_TrackAliveUtil", "track jobservice start , mIsForceCircle ", Boolean.valueOf(this.c));
        try {
            if (context == null) {
                LogUtil.h("Track_TrackAliveUtil", "startJobService context is null");
                return;
            }
            JobInfo.Builder requiresDeviceIdle = new JobInfo.Builder(1, new ComponentName(context.getPackageName(), TrackJobService.class.getName())).setRequiredNetworkType(0).setRequiresDeviceIdle(false);
            if (this.c) {
                this.g = requiresDeviceIdle.setOverrideDeadline(25000L).setMinimumLatency(20000L).build();
            } else {
                this.g = requiresDeviceIdle.setPeriodic(5000L).build();
            }
            if (context.getSystemService("jobscheduler") instanceof JobScheduler) {
                this.i = (JobScheduler) context.getSystemService("jobscheduler");
            }
            c();
        } catch (IllegalArgumentException e2) {
            LogUtil.a("Track_TrackAliveUtil", "startJobService ", e2.getMessage());
        }
    }

    public void c() {
        JobScheduler jobScheduler = this.i;
        if (jobScheduler != null) {
            jobScheduler.schedule(this.g);
        }
    }

    public boolean d() {
        return this.c;
    }

    public void c(Context context) {
        if (this.b) {
            LogUtil.a("Track_TrackAliveUtil", "stop KeepAlive");
            d(context);
            this.b = false;
        }
    }

    public void d(Context context) {
        if (context != null) {
            h(context.getApplicationContext());
        }
        if (d != null) {
            d.stopTickTrackDog();
        }
    }

    private void h(Context context) {
        if (context != null) {
            LogUtil.a("Track_TrackAliveUtil", "track jobservice stop");
            Object systemService = context.getSystemService("jobscheduler");
            if (systemService instanceof JobScheduler) {
                ((JobScheduler) systemService).cancel(1);
            }
        }
    }

    public boolean e() {
        return this.b;
    }

    public void b() {
        if (d == null || !this.b) {
            return;
        }
        int i = this.j + 1;
        this.j = i;
        if (i == 20) {
            this.j = 0;
            d.tickTrackDog();
        }
    }

    public static void b(Context context) {
        LogUtil.a("Track_TrackAliveUtil", "restart track");
        if (context == null) {
            LogUtil.h("Track_TrackAliveUtil", "restartTrack context is null");
            return;
        }
        String packageName = context.getPackageName();
        Intent intent = new Intent();
        intent.setPackage(packageName);
        intent.setAction("com.huawei.health.track.broadcast");
        intent.putExtra("command_type", "com.huawei.track.restart");
        context.sendBroadcast(intent, SecurityConstant.d);
    }

    public void d(boolean z) {
        this.b = z;
    }

    public static void a(Context context) {
        if (CommonUtil.bd() || context == null) {
            return;
        }
        new CustomViewDialog.Builder(context).czg_(View.inflate(context, R.layout.track_crash_dialog, null)).czc_(R.string._2130839808_res_0x7f020900, new View.OnClickListener() { // from class: gsx.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }
}

package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hwadpaterhealthmgr.PluginHealthTrackAdapterImpl;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class ouf {
    public static void e(final Context context) {
        ReleaseLogUtil.e("Track_checkRestartHelper", "enter checkRestart");
        final gso e = gso.e();
        e.w();
        e.setAdapter(PluginHealthTrackAdapterImpl.getInstance(context));
        e.b(context);
        HandlerExecutor.a(new Runnable() { // from class: ouj
            @Override // java.lang.Runnable
            public final void run() {
                ouf.e(gso.this, context);
            }
        });
    }

    static /* synthetic */ void e(gso gsoVar, Context context) {
        if (gsoVar.e(context)) {
            CommonUtil.a("Track_checkRestartHelper", "-Restart sport");
            ReleaseLogUtil.e("Track_checkRestartHelper", "Restart now");
            gtx.c(context).f(true);
            gsoVar.c(context);
            opj.b(2);
        }
        d(context);
    }

    public static void a(Context context) {
        long d = jfa.d(Integer.toString(20002), "last_unsaved_track", 0L);
        ReleaseLogUtil.e("Track_checkRestartHelper", "lastUnsavedTrackStartTime:", Long.valueOf(d));
        if (d > 0) {
            PluginHealthTrackAdapterImpl.getInstance(context).saveBackTrackDataToDb(d);
        }
    }

    private static void d(Context context) {
        if (context == null) {
            return;
        }
        if (Utils.o()) {
            ReleaseLogUtil.d("Track_checkRestartHelper", "updateAutoTrackStateToDaemonService the version is oversea");
            return;
        }
        String num = Integer.toString(20002);
        String b = SharedPreferenceManager.b(context, num, "com.huawei.auto_track_refresh_sp.track.config");
        if (b == null || "".equals(b)) {
            SharedPreferenceManager.e(context, num, "com.huawei.auto_track_refresh_sp.track.config", "1", new StorageParams());
            guz guzVar = new guz();
            guzVar.d(context);
            try {
                Intent intent = new Intent("com.huawei.health.track.config");
                intent.setPackage(context.getPackageName());
                intent.setClassName(context.getPackageName(), "com.huawei.health.manager.DaemonService");
                intent.putExtra("autotrack_enable", guzVar.e());
                intent.putExtra("stop_delay", guzVar.a());
                intent.putExtra("start_delay", guzVar.d());
                context.startService(intent);
            } catch (SecurityException e) {
                ReleaseLogUtil.c("Track_checkRestartHelper", "updateAutoTrackStateToDaemonService", ExceptionUtils.d(e));
            }
        }
    }
}

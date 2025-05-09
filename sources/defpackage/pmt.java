package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepJobService;
import java.util.List;

/* loaded from: classes6.dex */
public class pmt {

    /* renamed from: a, reason: collision with root package name */
    private static volatile pmt f16188a;

    private pmt() {
    }

    public static pmt d() {
        if (f16188a == null) {
            synchronized (pmt.class) {
                if (f16188a == null) {
                    f16188a = new pmt();
                }
            }
        }
        return f16188a;
    }

    public void a() {
        long s;
        long a2;
        long currentTimeMillis = System.currentTimeMillis();
        long x = (jdl.x(currentTimeMillis) - currentTimeMillis) - b();
        if (x > PreConnectManager.CONNECT_INTERNAL) {
            LogUtil.a("SleepJobServiceUtil", "schedule job at interval: ", Long.valueOf(x));
            c(x);
            return;
        }
        if (jdl.e(jdl.s(currentTimeMillis), currentTimeMillis) < 15) {
            s = jdl.u(currentTimeMillis);
            a2 = jdl.f(currentTimeMillis);
        } else {
            s = jdl.s(currentTimeMillis);
            a2 = jdl.a(currentTimeMillis);
        }
        pob.d((SleepJobService.e) null, s, a2);
        long j = (jdl.j(currentTimeMillis) - b()) - currentTimeMillis;
        LogUtil.a("SleepJobServiceUtil", "requestMonthProcessResult now, and schedule job at interval: ", Long.valueOf(j));
        c(j);
    }

    public static long b() {
        return (nsg.b(120) + GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN) * 60000;
    }

    public void c(long j) {
        try {
            JobScheduler drg_ = drg_();
            if (drg_ != null) {
                drg_.schedule(new JobInfo.Builder(20221110, new ComponentName(BaseApplication.e().getPackageName(), SleepJobService.class.getName())).setRequiredNetworkType(1).setRequiresDeviceIdle(false).setPersisted(true).setOverrideDeadline(1000 + j).setMinimumLatency(j).build());
                LogUtil.a("SleepJobServiceUtil", "start job success");
            }
        } catch (IllegalArgumentException e) {
            LogUtil.a("SleepJobServiceUtil", "startJobService ", e.getMessage());
        }
    }

    private JobScheduler drg_() {
        Object systemService = BaseApplication.e().getSystemService("jobscheduler");
        if (systemService instanceof JobScheduler) {
            return (JobScheduler) systemService;
        }
        return null;
    }

    public void e() {
        try {
            JobScheduler drg_ = drg_();
            if (drg_ != null) {
                drg_.cancel(20221110);
                LogUtil.a("SleepJobServiceUtil", "stop job success");
            }
        } catch (IllegalArgumentException e) {
            LogUtil.a("SleepJobServiceUtil", "stopJobService ", e.getMessage());
        }
        f16188a = null;
    }

    public boolean c() {
        JobScheduler drg_ = drg_();
        if (drg_ == null) {
            return false;
        }
        List<JobInfo> allPendingJobs = drg_.getAllPendingJobs();
        if (koq.b(allPendingJobs)) {
            return false;
        }
        for (JobInfo jobInfo : allPendingJobs) {
            if (jobInfo.getId() == 20221110) {
                LogUtil.a("SleepJobServiceUtil", "sleep jobservice is running, min latency: ", Long.valueOf(jobInfo.getMinLatencyMillis()), ", max delay: ", Long.valueOf(jobInfo.getMaxExecutionDelayMillis()));
                return true;
            }
        }
        return false;
    }
}

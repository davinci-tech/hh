package defpackage;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.provider.ContactsContract;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kai {
    private static ContentObserver c;
    private static ContentResolver d;

    public static boolean b(Context context, int i) {
        List<JobInfo> allPendingJobs;
        if (context == null) {
            LogUtil.h("ContactsListenUtils", "isJobIdRunning: context is null");
            return false;
        }
        Object systemService = context.getSystemService("jobscheduler");
        JobScheduler jobScheduler = systemService instanceof JobScheduler ? (JobScheduler) systemService : null;
        if (jobScheduler != null && (allPendingJobs = jobScheduler.getAllPendingJobs()) != null) {
            Iterator<JobInfo> it = allPendingJobs.iterator();
            while (it.hasNext()) {
                if (it.next().getId() == i) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void b(Context context, Class<? extends JobService> cls) {
        LogUtil.a("startListen: start", new Object[0]);
        if (context == null || cls == null) {
            LogUtil.h("ContactsListenUtils", "startListen: failure, parameter is null");
        } else {
            d(context, cls);
        }
    }

    public static void bMo_(ContentResolver contentResolver, ContentObserver contentObserver) {
        if (contentResolver == null || contentObserver == null) {
            LogUtil.h("ContactsListenUtils", "startListen: failure, parameter is null");
        } else {
            bMn_(contentResolver, contentObserver);
        }
    }

    public static void b(Context context) {
        ContentObserver contentObserver;
        LogUtil.a("ContactsListenUtils", "stopListen: start");
        if (context == null) {
            LogUtil.h("ContactsListenUtils", "stopListen: context is null.");
            return;
        }
        if (kae.e()) {
            Object systemService = context.getSystemService("jobscheduler");
            if (!(systemService instanceof JobScheduler)) {
                LogUtil.h("ContactsListenUtils", "stopListen: service is not type of JobService.");
                return;
            }
            ((JobScheduler) systemService).cancel(65281);
        } else {
            ContentResolver contentResolver = d;
            if (contentResolver == null || (contentObserver = c) == null) {
                LogUtil.h("ContactsListenUtils", "stopListen: sContactsResolver or sContactsObserver is null.");
                return;
            }
            contentResolver.unregisterContentObserver(contentObserver);
        }
        jze.a().d();
    }

    private static void d(Context context, Class<? extends JobService> cls) {
        LogUtil.a("ContactsListenUtils", "listenContactsOnApi24OrLater: start");
        Object systemService = context.getSystemService("jobscheduler");
        if (!(systemService instanceof JobScheduler)) {
            LogUtil.h("ContactsListenUtils", "listenContactsOnApi24OrLater: service is not the type of JobScheduler");
            return;
        }
        JobInfo.TriggerContentUri triggerContentUri = new JobInfo.TriggerContentUri(ContactsContract.RawContacts.CONTENT_URI, 1);
        JobInfo.Builder builder = new JobInfo.Builder(65281, new ComponentName(context, cls));
        builder.addTriggerContentUri(triggerContentUri);
        builder.setTriggerContentUpdateDelay(OpAnalyticsConstants.H5_LOADING_DELAY);
        builder.setTriggerContentMaxDelay(60000L);
        ((JobScheduler) systemService).schedule(builder.build());
    }

    private static void bMn_(ContentResolver contentResolver, ContentObserver contentObserver) {
        LogUtil.a("ContactsListenUtils", "listenContactsEarlierApi24: start");
        d = contentResolver;
        c = contentObserver;
        contentResolver.registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, c);
    }
}

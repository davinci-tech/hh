package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes4.dex */
public class gol {
    public void b(final Context context) {
        if (Utils.o() || Utils.f()) {
            LogUtil.c("LoginDateHelper", "scene that dont upload");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: goi
                @Override // java.lang.Runnable
                public final void run() {
                    gol.this.d(context);
                }
            });
        }
    }

    /* synthetic */ void d(Context context) {
        String b = SharedPreferenceManager.b(context, String.valueOf(10000), "user_last_login_date");
        LogUtil.c("LoginDateHelper", "Try to get date record for marketing.");
        if (TextUtils.isEmpty(b)) {
            LogUtil.c("LoginDateHelper", "loginDate is null. start login date record for marketing.");
            String valueOf = String.valueOf(System.currentTimeMillis());
            LogUtil.c("LoginDateHelper", "set date record for marketing: ", valueOf);
            SharedPreferenceManager.e(context, String.valueOf(10000), "user_last_login_date", valueOf, (StorageParams) null);
            SharedPreferenceManager.e(context, String.valueOf(10000), "user_last_login_days", String.valueOf(1), (StorageParams) null);
        } else {
            LogUtil.c("LoginDateHelper", "date record for marketing: has the time. record count");
            long currentTimeMillis = System.currentTimeMillis();
            if (!a(Long.parseLong(b), currentTimeMillis)) {
                int m = CommonUtil.m(context, SharedPreferenceManager.b(context, String.valueOf(10000), "user_last_login_days")) + 1;
                SharedPreferenceManager.e(context, String.valueOf(10000), "user_last_login_days", String.valueOf(m), (StorageParams) null);
                SharedPreferenceManager.e(context, String.valueOf(10000), "user_last_login_date", String.valueOf(currentTimeMillis), (StorageParams) null);
                LogUtil.c("LoginDateHelper", "date record for marketing: loginCount: ", Integer.valueOf(m));
            }
            LogUtil.c("LoginDateHelper", "date record for marketing: ", b);
        }
        HiUserPreference userPreference = HiHealthManager.d(context).getUserPreference("login_timestamp");
        if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
            LogUtil.c("LoginDateHelper", "start upload login_timestamp");
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey("login_timestamp");
            hiUserPreference.setValue(String.valueOf(System.currentTimeMillis()));
            LogUtil.c("LoginDateHelper", "upload login_timestamp status:", Boolean.valueOf(HiHealthManager.d(context).setUserPreference(hiUserPreference)));
        }
    }

    private Calendar c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        return calendar;
    }

    private boolean a(long j, long j2) {
        Calendar c = c(j);
        Calendar c2 = c(j2);
        return c.get(1) == c2.get(1) && c.get(2) == c2.get(2) && c.get(5) == c2.get(5);
    }
}

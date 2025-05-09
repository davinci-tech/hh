package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class kvx {

    /* renamed from: a, reason: collision with root package name */
    private static volatile kvx f14645a;
    private static final Object c = new Object();
    private HiUserPreference d;
    private Context e;

    private boolean b(char c2) {
        return c2 == '1';
    }

    private kvx(Context context) {
        if (context == null) {
            LogUtil.a("SMART_UserPrefHelper", "context is null");
        } else {
            this.e = context.getApplicationContext();
        }
    }

    public static kvx c(Context context) {
        if (f14645a == null) {
            synchronized (c) {
                if (f14645a == null) {
                    if (context == null) {
                        f14645a = new kvx(BaseApplication.getContext());
                    } else {
                        f14645a = new kvx(context);
                    }
                }
            }
        }
        return f14645a;
    }

    public void c() {
        this.d = HiHealthManager.d(this.e).getUserPreference("custom.onboarding_concern_status");
    }

    public boolean b(int i) {
        if (this.d == null) {
            c();
        }
        HiUserPreference hiUserPreference = this.d;
        String value = hiUserPreference == null ? "" : hiUserPreference.getValue();
        if (value == null || value.length() <= i) {
            return false;
        }
        return b(value.charAt(i));
    }
}

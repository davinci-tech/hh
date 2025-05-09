package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.KeyValDbManager;

/* loaded from: classes.dex */
public class jah {

    /* renamed from: a, reason: collision with root package name */
    private static volatile jah f13700a;
    private static final Object c = new Object();
    private Context b = BaseApplication.getContext();
    private volatile boolean e;

    private jah() {
    }

    public static jah c() {
        if (f13700a == null) {
            synchronized (c) {
                if (f13700a == null) {
                    f13700a = new jah();
                }
            }
        }
        return f13700a;
    }

    public String e(String str) {
        String e = KeyValDbManager.b(this.b).e("grs_third_party_" + str);
        LogUtil.c("GRS_GrsThirdPartyManager", "key is ", str, ", value is ", e);
        if (TextUtils.isEmpty(e) && jaa.e(this.b)) {
            LogUtil.a("GRS_GrsThirdPartyManager", "getValue value is empty, isNeedUpdateGrsConfig = true");
            if (this.e) {
                return e;
            }
            this.e = true;
            ThreadPoolManager.d().execute(new Runnable() { // from class: jah.2
                @Override // java.lang.Runnable
                public void run() {
                    jaa.e(jah.this.b, 2);
                    jah.this.e = false;
                }
            });
        }
        return e;
    }

    public void a(String str, String str2) {
        KeyValDbManager.b(this.b).d("grs_third_party_" + str, str2, null);
    }

    public void e() {
        KeyValDbManager.b(this.b).c("grs_third_party_%");
    }
}

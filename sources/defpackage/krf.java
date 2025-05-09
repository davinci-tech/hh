package defpackage;

import android.content.Context;
import com.huawei.hwidauth.c.j;
import com.huawei.hwidauth.c.k;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public final class krf {
    private static final ThreadPoolExecutor b = new krr(1, 2, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), "Picker-Oauth-Pool");
    private static final krf d = new krf();

    private krf() {
    }

    public static krf c() {
        return d;
    }

    public void c(Context context, String str, k kVar, j jVar) {
        new kqx(context, str, kVar, jVar).executeOnExecutor(b, kVar);
    }
}

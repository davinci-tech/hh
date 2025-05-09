package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.File;

/* loaded from: classes5.dex */
public class hz {

    /* renamed from: a, reason: collision with root package name */
    private File f6932a;
    private gc b;

    public long d() {
        return this.b.aB();
    }

    public long c() {
        return this.b.au() * 1048576;
    }

    public String b() {
        File file = this.f6932a;
        if (file == null) {
            return null;
        }
        return file.getPath();
    }

    public void a(long j) {
        this.b.c(j);
    }

    public File a() {
        return this.f6932a;
    }

    public hz(Context context, String str) {
        File file = new File(com.huawei.openalliance.ad.utils.x.i(context).getCacheDir(), File.separator + Constants.PPS_ROOT_PATH + File.separator + str + "_diskcache");
        this.f6932a = file;
        if (!file.exists() && !this.f6932a.mkdirs()) {
            ho.d("CacheConfig", "Create cache dir failed");
        }
        this.b = fh.b(context);
    }
}

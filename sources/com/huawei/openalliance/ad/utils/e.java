package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.AdSource;
import com.huawei.openalliance.ad.fb;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import java.util.List;

/* loaded from: classes5.dex */
public class e {
    public static void a(final Context context, final List<AdSource> list) {
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.utils.e.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!bg.a(list) && context != null) {
                        for (AdSource adSource : list) {
                            if (adSource != null) {
                                rt rtVar = new rt();
                                rtVar.c(true);
                                rtVar.b(false);
                                rtVar.c(adSource.b());
                                ru a2 = fb.a(context.getApplicationContext()).a(rtVar);
                                Object[] objArr = new Object[1];
                                objArr[0] = a2 != null ? dl.a(a2.b()) : null;
                                ho.b("AdSourceUtil", "downloadDspLogo result= %s", objArr);
                            }
                        }
                    }
                } catch (Throwable unused) {
                    ho.d("AdSourceUtil", "downloadDspLogo error");
                }
            }
        });
    }
}

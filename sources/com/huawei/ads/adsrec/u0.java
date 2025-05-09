package com.huawei.ads.adsrec;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.wc;
import defpackage.wk;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class u0 {
    private static final SparseArray<String> b = new SparseArray<>();

    /* renamed from: a, reason: collision with root package name */
    private Context f1685a;
    private wc e;

    public wk b(String str, String str2) {
        String valueOf;
        int j = this.e.j(str2);
        HiAdLog.i("REC_STR_SEL", "pkg: %s slotId: %s, selected strategy: %d", str, str2, Integer.valueOf(j));
        String str3 = b.get(j);
        String[] strArr = null;
        if (str3 == null) {
            HiAdLog.w("REC_STR_SEL", "strategy %d not found", Integer.valueOf(j));
            return null;
        }
        wk wkVar = new wk();
        wkVar.a(str3);
        long currentTimeMillis = System.currentTimeMillis();
        String valueOf2 = String.valueOf(currentTimeMillis);
        int c = this.e.c(str2);
        long i = currentTimeMillis - (this.e.i(str2) * 60000);
        switch (j) {
            case 0:
            case 2:
                valueOf = String.valueOf(0);
                strArr = new String[]{str, str2, valueOf2, valueOf2, valueOf, String.valueOf(c), String.valueOf(i)};
                break;
            case 1:
            case 3:
                valueOf = String.valueOf(1);
                strArr = new String[]{str, str2, valueOf2, valueOf2, valueOf, String.valueOf(c), String.valueOf(i)};
                break;
            case 4:
            case 5:
            case 6:
                strArr = new String[]{str, str2, valueOf2, valueOf2, String.valueOf(c), String.valueOf(i)};
                break;
        }
        Arrays.toString(strArr);
        wkVar.c(strArr);
        return wkVar;
    }

    public u0(Context context) {
        SparseArray<String> sparseArray = b;
        String str = c1.d;
        sparseArray.put(0, str);
        sparseArray.put(1, str);
        String str2 = c1.e;
        sparseArray.put(2, str2);
        sparseArray.put(3, str2);
        sparseArray.put(4, c1.f);
        sparseArray.put(5, c1.g);
        sparseArray.put(6, c1.h);
        Context applicationContext = context.getApplicationContext();
        this.f1685a = applicationContext;
        this.e = wc.d(applicationContext);
    }
}

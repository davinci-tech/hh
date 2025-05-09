package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class bc {

    /* renamed from: a, reason: collision with root package name */
    private Context f917a;

    public bc(Context context) {
        this.f917a = context;
    }

    public final void a(av avVar) {
        b(avVar);
    }

    private boolean b(av avVar) {
        if (avVar != null) {
            String pinyin = avVar.getPinyin();
            boolean a2 = a(pinyin, this.f917a, "vmap/");
            if (pinyin.equals("quanguogaiyaotu")) {
                pinyin = "quanguo";
            }
            boolean z = true;
            boolean z2 = a(pinyin, this.f917a, "map/") || a2;
            if (!b(bt.b(avVar.getUrl()), this.f917a, "map/") && !z2) {
                z = false;
            }
            if (z) {
                avVar.i();
                return z;
            }
            avVar.h();
        }
        return false;
    }

    private static boolean a(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String b = dv.b(context);
        try {
            File file = new File(b + str2 + str + ".dat");
            if (file.exists()) {
                if (!bt.b(file)) {
                    return false;
                }
            }
            try {
                bt.a(b + str2);
                bt.b(str, context);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e4) {
            e4.printStackTrace();
            return false;
        }
    }

    private static boolean b(String str, Context context, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String a2 = dv.a(context);
        try {
            File file = new File(a2 + str2 + str);
            if (file.exists()) {
                if (!bt.b(file)) {
                    return false;
                }
            }
            try {
                bt.a(a2 + str2);
                bt.b(str, context);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e4) {
            e4.printStackTrace();
            return false;
        }
    }
}

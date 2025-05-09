package defpackage;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class agm {
    private static agm b;

    /* renamed from: a, reason: collision with root package name */
    private String f198a;
    private int d;

    public String a() {
        return this.f198a;
    }

    public int d() {
        return this.d;
    }

    public static agm b() {
        agm agmVar;
        synchronized (agm.class) {
            if (b == null) {
                b = new agm();
            }
            agmVar = b;
        }
        return agmVar;
    }

    private String c() {
        return agv.c("ro.build.version.emui", "");
    }

    private String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("_");
            if (split.length == 2) {
                return split[1];
            }
        }
        return "";
    }

    private agm() {
        this.d = 0;
        this.f198a = "";
        this.d = agv.e("ro.build.hw_emui_api_level", 0);
        this.f198a = a(c());
    }
}

package com.huawei.hms.hatool;

import android.content.Context;
import android.os.UserManager;

/* loaded from: classes4.dex */
public class q1 {
    private static q1 c = new q1();

    /* renamed from: a, reason: collision with root package name */
    private boolean f4613a = false;
    private Context b = q0.i();

    public boolean a() {
        if (!this.f4613a) {
            Context context = this.b;
            if (context == null) {
                return false;
            }
            UserManager userManager = (UserManager) context.getSystemService("user");
            if (userManager != null) {
                this.f4613a = userManager.isUserUnlocked();
            } else {
                this.f4613a = false;
            }
        }
        return this.f4613a;
    }

    public static q1 b() {
        return c;
    }

    private q1() {
    }
}

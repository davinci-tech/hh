package com.huawei.hms.iapfull;

import android.content.Context;
import android.widget.Toast;

/* loaded from: classes4.dex */
public class e1 {

    /* renamed from: a, reason: collision with root package name */
    private Toast f4705a;

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        public static final e1 f4706a = new e1();
    }

    public void a(Context context, int i) {
        Toast makeText = Toast.makeText(context.getApplicationContext(), context.getResources().getString(i), 0);
        this.f4705a = makeText;
        makeText.show();
    }

    public void a(Context context, String str) {
        Toast makeText = Toast.makeText(context.getApplicationContext(), str, 0);
        this.f4705a = makeText;
        makeText.show();
    }

    public static e1 a() {
        return b.f4706a;
    }

    private e1() {
    }
}

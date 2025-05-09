package com.huawei.watchface;

import android.content.Context;
import com.huawei.watchface.api.PluginBase;

/* loaded from: classes7.dex */
public class ba extends PluginBase {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ba f10915a;
    private static final Object b = new Object();
    private Context c;

    private ba(Context context) {
        this.c = context;
    }

    public static ba a(Context context) {
        if (f10915a == null) {
            synchronized (b) {
                if (f10915a == null) {
                    f10915a = new ba(context.getApplicationContext());
                }
            }
        }
        return f10915a;
    }

    public boolean a() {
        return dp.a("current_user_is_designer", "goal_steps_perference", false);
    }
}

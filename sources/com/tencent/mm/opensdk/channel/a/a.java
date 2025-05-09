package com.tencent.mm.opensdk.channel.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.d;

/* loaded from: classes7.dex */
public final class a {

    /* renamed from: com.tencent.mm.opensdk.channel.a.a$a, reason: collision with other inner class name */
    public static final class C0289a {

        /* renamed from: a, reason: collision with root package name */
        public String f11313a;
        public String action;
        public long b;
        public Bundle bundle;
        public String content;
    }

    public static boolean a(Context context, C0289a c0289a) {
        String str;
        String str2;
        if (context == null) {
            str2 = "send fail, invalid argument";
        } else {
            if (!d.b(c0289a.action)) {
                if (d.b(c0289a.f11313a)) {
                    str = null;
                } else {
                    str = c0289a.f11313a + ".permission.MM_MESSAGE";
                }
                Intent intent = new Intent(c0289a.action);
                if (c0289a.bundle != null) {
                    intent.putExtras(c0289a.bundle);
                }
                String packageName = context.getPackageName();
                intent.putExtra(ConstantsAPI.SDK_VERSION, Build.SDK_INT);
                intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
                intent.putExtra(ConstantsAPI.CONTENT, c0289a.content);
                intent.putExtra(ConstantsAPI.APP_SUPORT_CONTENT_TYPE, c0289a.b);
                intent.putExtra(ConstantsAPI.CHECK_SUM, b.a(c0289a.content, Build.SDK_INT, packageName));
                context.sendBroadcast(intent, str);
                Log.d("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str);
                return true;
            }
            str2 = "send fail, action is null";
        }
        Log.e("MicroMsg.SDK.MMessage", str2);
        return false;
    }
}

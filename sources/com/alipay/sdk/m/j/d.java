package com.alipay.sdk.m.j;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import defpackage.kl;
import defpackage.kr;
import defpackage.lv;
import defpackage.md;
import java.util.Collections;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    public static a f862a;

    public interface a {
        void a(boolean z, JSONObject jSONObject, String str);
    }

    public static boolean b(lv lvVar, Context context) {
        return md.e(lvVar, context, Collections.singletonList(new kr.e("com.taobao.taobao", 0, "")), false);
    }

    public static boolean aT_(lv lvVar, Activity activity, int i, String str, String str2, a aVar) {
        try {
            kl.b(lvVar, "biz", "TbStart");
            activity.startActivityForResult(new Intent(str2, Uri.parse(str)), i);
            f862a = aVar;
            return true;
        } catch (Throwable th) {
            aVar.a(false, null, "UNKNOWN_ERROR");
            kl.e(lvVar, "biz", "TbActFail", th);
            return false;
        }
    }

    public static boolean aS_(lv lvVar, int i, int i2, Intent intent) {
        if (i != 1010 || intent == null) {
            return false;
        }
        a aVar = f862a;
        if (aVar == null) {
            return true;
        }
        f862a = null;
        if (i2 != -1) {
            if (i2 != 0) {
                kl.c(lvVar, "biz", "TbUnknown", "" + i2);
            } else {
                kl.a(lvVar, "biz", "TbCancel", intent != null ? intent.toUri(1) : "");
                aVar.a(false, null, "CANCELED");
            }
        } else {
            kl.a(lvVar, "biz", "TbOk", intent.toUri(1));
            aVar.a(true, md.bd_(intent), "OK");
        }
        return true;
    }
}

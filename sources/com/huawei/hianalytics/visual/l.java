package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import java.lang.reflect.Field;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    public final k f3933a = new k();

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final l f3934a = new l();
    }

    public boolean a(Activity activity) {
        String str;
        Uri uri;
        Uri data;
        if (activity == null) {
            return false;
        }
        try {
            Field declaredField = Activity.class.getDeclaredField("mReferrer");
            declaredField.setAccessible(true);
            str = (String) declaredField.get(activity);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            HiLog.w("JumpManager", "get invoker failed");
            str = "";
        }
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            try {
                uri = activity.getReferrer();
            } catch (Throwable unused2) {
                HiLog.w("JumpManager", "get referrer error");
                uri = null;
            }
            if (uri != null) {
                str = uri.toString();
            }
        }
        if (!TextUtils.isEmpty(str)) {
            String str3 = this.f3933a.f3931a;
            if (TextUtils.isEmpty(str3) || !Objects.equals(str, str3)) {
                this.f3933a.f3931a = str;
            }
        }
        if (TextUtils.isEmpty(this.f3933a.f3931a)) {
            return false;
        }
        Intent intent = activity.getIntent();
        if (intent != null) {
            try {
                str2 = intent.getStringExtra("HASDKSourceId");
            } catch (Throwable unused3) {
                HiLog.w("JumpManager", "update notification source failed");
            }
            if (!TextUtils.isEmpty(str2)) {
                this.f3933a.b = str2;
            }
        }
        Intent intent2 = activity.getIntent();
        if (intent2 != null && (data = intent2.getData()) != null && !data.isOpaque()) {
            JSONObject jSONObject = new JSONObject();
            try {
                for (String str4 : data.getQueryParameterNames()) {
                    jSONObject.put(str4, data.getQueryParameter(str4));
                }
            } catch (Exception unused4) {
                HiLog.w("JumpManager", "update channel params error");
            }
            this.f3933a.c = jSONObject;
        }
        k0 k0Var = k0.b;
        Intent intent3 = activity.getIntent();
        k0Var.getClass();
        if (intent3 != null && intent3.hasExtra("$sessionid") && intent3.hasExtra("$sessiontime")) {
            try {
                String stringExtra = intent3.getStringExtra("$sessionid");
                long longExtra = intent3.getLongExtra("$sessiontime", 0L);
                j0 j0Var = k0Var.f3932a;
                j0Var.getClass();
                if (TextUtils.isEmpty(stringExtra) || longExtra <= 0) {
                    HiLog.w("SessionManager", "invalid session to update");
                } else {
                    j0Var.f3930a = stringExtra;
                    j0Var.b = longExtra;
                }
            } catch (Throwable unused5) {
                HiLog.w("SessionManager", "updateSession error");
            }
        }
        return true;
    }
}

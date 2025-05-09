package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.multisimsdk.cardpartmanager.simauth.SimAuthController;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class loe {

    /* renamed from: a, reason: collision with root package name */
    private static int f14800a = Build.VERSION.SDK_INT;
    private static HashMap<String, String> c = new HashMap<>();
    private static final String e = "AuthenHandler";

    static boolean bYf_(Context context, AuthParam authParam, Message message) {
        String str = e;
        loq.c(str, "Start Auth Method");
        if (message == null) {
            loq.c(str, "Start Auth Method failed, message is null");
            return false;
        }
        if (!bYe_(context, authParam, message) || !lop.bYn_(context, message, authParam.getSlotId())) {
            return false;
        }
        if (!lni.e("android.permission.READ_PHONE_STATE", context)) {
            lop.bYp_(message, 1010);
            loq.c(str, "Start Authen Method failed, do not has READ_PHONE_STATE permission");
            return false;
        }
        if (2 == authParam.getAuthType()) {
            new lof(context, message).c(new SimAuthController(context).d());
            return true;
        }
        if (3 == authParam.getAuthType()) {
            String d = lnc.d(context, authParam.getSlotId());
            if (d(context, d)) {
                message.obj = lop.a(context, d, "Tag");
                message.arg1 = 1000;
                lop.bYq_(message);
                loq.c(str, "Auth token is exist.");
                return true;
            }
            if (!bYd_(context, authParam, message)) {
                return false;
            }
            new lom(context, authParam, message).startAuthLogin();
            return true;
        }
        lop.bYp_(message, 1011);
        loq.c(str, "Auth type is invalidate");
        return false;
    }

    private static boolean bYe_(Context context, AuthParam authParam, Message message) {
        if (context == null) {
            lop.bYp_(message, 1013);
            loq.c(e, "Check is valid input param and network failed, context is null");
            return false;
        }
        if (authParam == null) {
            lop.bYp_(message, 1013);
            loq.c(e, "Auth param is invalidate");
            return false;
        }
        if (lop.c(context)) {
            return true;
        }
        lop.bYp_(message, 1008);
        loq.c(e, "Network no Connected");
        return false;
    }

    private static boolean bYd_(Context context, AuthParam authParam, Message message) {
        if (f14800a < 24) {
            lop.bYp_(message, 1014);
            loq.c(e, "SDK is lower than N");
            return false;
        }
        String d = lnc.d(context, authParam.getSlotId());
        if (d == null) {
            lop.bYp_(message, 1013);
            loq.c(e, "getImsi result is null");
            return false;
        }
        if (authParam.getImsi() == null || b(authParam, d)) {
            return true;
        }
        lop.bYp_(message, 1013);
        loq.c(e, "Imsi is invalid");
        return false;
    }

    private static boolean b(AuthParam authParam, String str) {
        return str != null && str.equals(authParam.getImsi());
    }

    private static boolean d(Context context, String str) {
        return !TextUtils.isEmpty(lop.a(context, str, "authen_Token"));
    }

    static void a(String str, String str2) {
        loq.c(e, "setCmccSsoParam");
        if (c == null) {
            c = new HashMap<>();
        }
        c.clear();
        c.put("appid", str);
        c.put("appkey", str2);
    }

    public static HashMap<String, String> e() {
        return c;
    }
}

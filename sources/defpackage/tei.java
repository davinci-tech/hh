package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.secure.android.common.intent.IntentUtils;

/* loaded from: classes9.dex */
public class tei {
    public static void eYX_(Context context, Intent intent) {
        eYY_(context, intent, null, null);
    }

    public static void eYY_(Context context, Intent intent, Bundle bundle, String str) {
        if (context == null) {
            stq.e("CommonBase:SafeStartActivityUtil", "startActivity context ==  null .", false);
            return;
        }
        if (intent == null) {
            stq.e("CommonBase:SafeStartActivityUtil", "startActivity intent ==  null .", false);
            return;
        }
        intent.addFlags(268435456);
        if (IntentUtils.safeStartActivity(context, intent, bundle)) {
            return;
        }
        stq.e("CommonBase:SafeStartActivityUtil", "safeStartActivityForResultStatic fail", false);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        stq.e("CommonBase:SafeStartActivityUtil", "startActivity err tag:" + str, false);
    }

    public static void eYV_(Activity activity, Intent intent) {
        eYW_(activity, intent, null, null);
    }

    public static void eYW_(Activity activity, Intent intent, Bundle bundle, String str) {
        if (activity == null) {
            stq.e("CommonBase:SafeStartActivityUtil", "startActivity activity ==  null .", false);
            return;
        }
        if (intent == null) {
            stq.e("CommonBase:SafeStartActivityUtil", "startActivity intent ==  null .", false);
            return;
        }
        if (IntentUtils.safeStartActivity(activity, intent, bundle)) {
            return;
        }
        stq.e("CommonBase:SafeStartActivityUtil", "safeStartActivityForResultStatic fail", false);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        stq.e("CommonBase:SafeStartActivityUtil", "startActivity err tag:" + str, false);
    }
}

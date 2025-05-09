package defpackage;

import android.text.TextUtils;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;

/* loaded from: classes5.dex */
public class iza {
    private static SmartWatchInfo d;

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.endsWith("smart_watch") ? str.substring(0, str.length() - 11) : str;
    }

    public static SmartWatchInfo c() {
        return d;
    }
}

package androidx.core.text;

import android.text.TextUtils;
import defpackage.uhy;
import java.util.Locale;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"layoutDirection", "", "Ljava/util/Locale;", "getLayoutDirection", "(Ljava/util/Locale;)I", "core-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class LocaleKt {
    public static final int getLayoutDirection(Locale locale) {
        uhy.e((Object) locale, "");
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }
}

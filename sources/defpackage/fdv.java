package defpackage;

import android.graphics.Typeface;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes4.dex */
public class fdv {
    private static Typeface c;
    public static final String e = jcu.f + "Thumbnail_Share.png";

    public static Typeface awk_() {
        if (c == null) {
            c = Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "fonts/hw-italic.ttf");
        }
        return c;
    }
}

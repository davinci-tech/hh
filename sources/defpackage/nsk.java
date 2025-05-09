package defpackage;

import android.graphics.Typeface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.ui.commonui.R$string;

/* loaded from: classes6.dex */
public class nsk {
    public static Typeface cKN_() {
        return Typeface.createFromAsset(BaseApplication.e().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
    }

    public static Typeface cKM_() {
        return Typeface.createFromAsset(BaseApplication.e().getAssets(), "font/hw_digit_medium.otf");
    }

    public static Typeface cKO_() {
        return Typeface.create(BaseApplication.e().getResources().getString(R$string.textFontFamilyMedium), 0);
    }

    public static Typeface cKP_() {
        return Typeface.create(BaseApplication.e().getResources().getString(R$string.textFontFamilyRegular), 0);
    }
}

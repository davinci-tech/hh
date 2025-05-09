package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import java.text.NumberFormat;
import java.util.Locale;

/* loaded from: classes7.dex */
public class skh {
    private static final Typeface b = Typeface.create(Constants.FONT, 0);
    private static final Typeface e = Typeface.create("sans-serif", 0);

    public static String b(int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
        numberInstance.setGroupingUsed(false);
        return numberInstance.format(i);
    }

    public static boolean d(Context context) {
        if (context == null || context.getResources() == null) {
            return false;
        }
        Configuration configuration = context.getResources().getConfiguration();
        String language = configuration.locale.getLanguage();
        String country = configuration.locale.getCountry();
        if ("my".equals(language)) {
            return "MM".equals(country) || "ZG".equals(country);
        }
        return false;
    }

    private static Activity dYS_(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return dYS_(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void dYV_(Typeface typeface, Paint paint) {
        if (paint != null) {
            paint.setTypeface(typeface);
        }
    }

    public static boolean b(Context context) {
        Activity dYS_;
        if (context == null || (dYS_ = dYS_(context)) == null) {
            return false;
        }
        return dYS_.isInMultiWindowMode();
    }

    public static Typeface dYT_(Context context) {
        return Typeface.create(context.getResources().getString(R.string._2130850836_res_0x7f023414), 0);
    }

    public static Typeface dYU_(Context context) {
        return Typeface.create(context.getResources().getString(R.string._2130850837_res_0x7f023415), 0);
    }
}

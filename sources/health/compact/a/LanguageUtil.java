package health.compact.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.Locale;

/* loaded from: classes.dex */
public class LanguageUtil {
    private static final String c = "com.huawei.hwbasemgr.LanguageUtil";

    private LanguageUtil() {
    }

    public static boolean m(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isChineseSimplifiedLocal() context is null");
            return false;
        }
        Locale locale = context.getResources().getConfiguration().locale;
        if (!MLAsrConstants.LAN_ZH.equalsIgnoreCase(locale.getLanguage())) {
            return false;
        }
        String script = locale.getScript();
        return "Hans".equalsIgnoreCase(script) || TextUtils.isEmpty(script);
    }

    public static boolean h(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isChinese() context is null");
            return false;
        }
        return MLAsrConstants.LAN_ZH.equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean p(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isEnglish() context is null");
            return false;
        }
        return "en".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean d(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isAmerican() context is null");
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "en".equalsIgnoreCase(language) || "en-us".equalsIgnoreCase(language);
    }

    public static boolean l(Context context) {
        if (context != null) {
            return MLAsrConstants.LAN_ZH.equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) && "TW".equalsIgnoreCase(context.getResources().getConfiguration().locale.getCountry());
        }
        com.huawei.hwlogsmodel.LogUtil.h(c, "isChineseTaiwan() context is null");
        return false;
    }

    public static boolean al(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isLanguageChinesePeopleUsed() context is null");
            return false;
        }
        if (m(context) || p(context) || l(context)) {
            return true;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        if ((MLAsrConstants.LAN_ZH.equalsIgnoreCase(language) && "HK".equalsIgnoreCase(context.getResources().getConfiguration().locale.getCountry())) || "bo".equalsIgnoreCase(language)) {
            return true;
        }
        return bn(context);
    }

    public static boolean bc(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isRTLLanguage() context is null");
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        if ("ar".equalsIgnoreCase(language) || "iw".equalsIgnoreCase(language) || "fa".equalsIgnoreCase(language) || Constants.URDU_LANG.equalsIgnoreCase(language)) {
            return true;
        }
        return "ug".equalsIgnoreCase(language);
    }

    public static boolean x(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isGeTiOrKaLanguage() context is null");
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "ka".equalsIgnoreCase(language) || "bo".equalsIgnoreCase(language) || "kk".equalsIgnoreCase(language);
    }

    public static boolean ae(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isJapaneseLanguage() context is null");
            return false;
        }
        return "ja".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean r(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isDeutschLanguage() context is null");
            return false;
        }
        return "de".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean au(Context context) {
        if (context == null) {
            return false;
        }
        return "fr".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean t(Context context) {
        if (context == null) {
            return false;
        }
        return "et".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean w(Context context) {
        if (context == null) {
            return false;
        }
        return "eu".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        return "bg".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean aw(Context context) {
        if (context == null) {
            return false;
        }
        return "pl".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean n(Context context) {
        if (context == null) {
            return false;
        }
        return "da".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ba(Context context) {
        if (context == null) {
            return false;
        }
        return "ru".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean af(Context context) {
        if (context == null) {
            return false;
        }
        return "km".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean v(Context context) {
        if (context == null) {
            return false;
        }
        return "ka".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ap(Context context) {
        if (context == null) {
            return false;
        }
        return "ms".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bb(Context context) {
        if (context == null) {
            return false;
        }
        return "pt".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bl(Context context) {
        if (context == null) {
            return false;
        }
        return "th".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bq(Context context) {
        if (context == null) {
            return false;
        }
        return "uk".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean q(Context context) {
        if (context == null) {
            return false;
        }
        return "es".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean br(Context context) {
        if (context == null) {
            return false;
        }
        return "vi".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bd(Context context) {
        if (context == null) {
            return false;
        }
        return "fi".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ab(Context context) {
        if (context == null) {
            return false;
        }
        return "hi".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bf(Context context) {
        if (context == null) {
            return false;
        }
        return "sk".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bp(Context context) {
        if (context == null) {
            return false;
        }
        return Constants.URDU_LANG.equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bh(Context context) {
        if (context == null) {
            return false;
        }
        return "si".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bi(Context context) {
        if (context == null) {
            return false;
        }
        return "bo".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean f(Context context) {
        if (context == null) {
            return false;
        }
        return "my".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean aj(Context context) {
        if (context == null) {
            return false;
        }
        return "mk".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean b(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isArabicLanguage() context is null");
            return false;
        }
        return "ar".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean y(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isFarsiLanguage() context is null");
            return false;
        }
        return "fa".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ac(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isHebrewLanguage() context is null");
            return false;
        }
        return "iw".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ao(Context context) {
        if (context == null) {
            return false;
        }
        return "nl".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean az(Context context) {
        if (context == null) {
            return false;
        }
        return "ro".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ah(Context context) {
        if (context == null) {
            return false;
        }
        return "kn".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        return "az".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean av(Context context) {
        if (context == null) {
            return false;
        }
        return "nb".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        return "be".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean i(Context context) {
        if (context == null) {
            return false;
        }
        return "ca".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean k(Context context) {
        if (context == null) {
            return false;
        }
        return "cs".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean s(Context context) {
        if (context == null) {
            return false;
        }
        return "et".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean be(Context context) {
        if (context == null) {
            return false;
        }
        return "es".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bk(Context context) {
        if (context == null) {
            return false;
        }
        return "tl".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) || "fil".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean u(Context context) {
        if (context == null) {
            return false;
        }
        return "gl".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean aa(Context context) {
        if (context == null) {
            return false;
        }
        return "el".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ad(Context context) {
        if (context == null) {
            return false;
        }
        return "in".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ag(Context context) {
        if (context == null) {
            return false;
        }
        return "it".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bg(Context context) {
        return context != null && "es".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage()) && "US".equalsIgnoreCase(context.getResources().getConfiguration().locale.getCountry());
    }

    public static boolean ar(Context context) {
        if (context == null) {
            return false;
        }
        return "ml".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean aq(Context context) {
        if (context == null) {
            return false;
        }
        return "mi".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean as(Context context) {
        if (context == null) {
            return false;
        }
        return "mn".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ay(Context context) {
        if (context == null) {
            return false;
        }
        return "sr".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bj(Context context) {
        if (context == null) {
            return false;
        }
        return "ta".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bt(Context context) {
        if (context == null) {
            return false;
        }
        return "uz".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bo(Context context) {
        if (context == null) {
            return false;
        }
        return "tr".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean j(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isChineseLocal() context is null");
            return false;
        }
        return MLAsrConstants.LAN_ZH.equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ax(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "ar".equalsIgnoreCase(language) || "as".equalsIgnoreCase(language) || "bn".equalsIgnoreCase(language) || "fa".equalsIgnoreCase(language) || "ne".equalsIgnoreCase(language) || Constants.URDU_LANG.equalsIgnoreCase(language);
    }

    public static boolean at(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "uz".equalsIgnoreCase(language) || "sk".equalsIgnoreCase(language) || "uk".equalsIgnoreCase(language) || "sl".equalsIgnoreCase(language);
    }

    public static boolean an(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isLatvijas() context is null");
            return false;
        }
        return "lv".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ai(Context context) {
        if (context == null) {
            return false;
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        return "sw".equalsIgnoreCase(language) || "swc".equalsIgnoreCase(language);
    }

    public static boolean o(Context context) {
        if (context == null) {
            return false;
        }
        return "hr".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean bn(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isUygurLanguage() context is null");
            return false;
        }
        return "ug".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
    
        if ("HK".equalsIgnoreCase(r1) == false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String e() {
        /*
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            android.os.LocaleList r1 = r0.getLocales()
            boolean r1 = r1.isEmpty()
            java.lang.String r2 = ""
            if (r1 != 0) goto L6a
            android.os.LocaleList r0 = r0.getLocales()
            r1 = 0
            java.util.Locale r0 = r0.get(r1)
            if (r0 != 0) goto L30
            java.lang.String r0 = health.compact.a.LanguageUtil.c
            java.lang.String r1 = "locale is null"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r1)
            return r2
        L30:
            java.lang.String r2 = r0.getLanguage()
            java.lang.String r1 = r0.getCountry()
            java.lang.String r0 = r0.getScript()
            java.lang.String r3 = "my"
            boolean r3 = r3.equals(r2)
            if (r3 == 0) goto L4f
            java.lang.String r3 = "Qaag"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L4f
            java.lang.String r1 = "ZG"
        L4f:
            java.lang.String r3 = "Hant"
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 == 0) goto L68
            java.lang.String r0 = "TW"
            boolean r3 = r0.equalsIgnoreCase(r1)
            if (r3 != 0) goto L68
            java.lang.String r3 = "HK"
            boolean r3 = r3.equalsIgnoreCase(r1)
            if (r3 != 0) goto L68
            goto L76
        L68:
            r0 = r1
            goto L76
        L6a:
            java.lang.String r0 = health.compact.a.LanguageUtil.c
            java.lang.String r1 = "configuration locales is empty"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r1)
            r0 = r2
        L76:
            java.lang.String r1 = "fil"
            boolean r1 = r1.equalsIgnoreCase(r2)
            if (r1 == 0) goto L81
            java.lang.String r2 = "tl"
        L81:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            java.lang.String r2 = "_"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.LanguageUtil.e():java.lang.String");
    }

    public static boolean z(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isHungarianLanguage() context is null");
            return false;
        }
        return "hu".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean g(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isHungarianLanguage() context is null");
            return false;
        }
        Locale locale = context.getResources().getConfiguration().locale;
        return "my".equals(locale.getLanguage()) && "Qaag".equals(locale.getScript());
    }

    public static boolean bm(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isSwedishLanguage() context is null");
            return false;
        }
        return "sv".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean am(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h(c, "isKoreanLanguage() context is null");
            return false;
        }
        return "ko".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
    }

    public static boolean ak(Context context) {
        if (context != null) {
            return h(context) || "bo".equalsIgnoreCase(context.getResources().getConfiguration().locale.getLanguage());
        }
        com.huawei.hwlogsmodel.LogUtil.h(c, "isLanguageDomesticUsed() context is null");
        return false;
    }
}

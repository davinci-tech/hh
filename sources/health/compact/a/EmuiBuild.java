package health.compact.a;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes.dex */
public final class EmuiBuild extends BaseBuild {
    public static final boolean d = j();

    /* renamed from: a, reason: collision with root package name */
    public static final int f13113a = a();
    public static final String c = d();

    private EmuiBuild() {
    }

    private static boolean j() {
        if (e() || !c() || b()) {
            return false;
        }
        String b = SystemProperties.b("ro.build.version.emui");
        return !TextUtils.isEmpty(b) && b.contains("EmotionUI");
    }

    private static String d() {
        if (!c()) {
            return "";
        }
        String b = SystemProperties.b("ro.build.version.emui");
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        try {
            Matcher matcher = Pattern.compile("EmotionUI[ _]([0-9][0-9.]*)").matcher(b);
            return matcher.find() ? matcher.group(1) : "";
        } catch (PatternSyntaxException e) {
            LogUtil.e("HAF_SystemBuild", "getEmuiVersionImpl ex=", LogUtil.a(e));
            return "";
        }
    }
}

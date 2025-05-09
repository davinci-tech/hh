package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes4.dex */
public class gyr {
    private gyr() {
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final gyr f13008a = new gyr();
    }

    public static gyr e() {
        return c.f13008a;
    }

    public Bitmap aWN_(String str) {
        LogUtil.a("Track_SportExamResManager", "getAssetsBitmap imagePath: ", str);
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Track_SportExamResManager", "getAssetsBitmap failed, imagePath is null", str);
            return null;
        }
        try {
            InputStream open = BaseApplication.e().getAssets().open(str);
            try {
                bitmap = BitmapFactory.decodeStream(open);
                if (open != null) {
                    open.close();
                }
            } finally {
            }
        } catch (IOException e) {
            LogUtil.b("Track_SportExamResManager", "getAssetsBitmap ioException: ", LogAnonymous.b((Throwable) e));
        }
        LogUtil.a("Track_SportExamResManager", "getAssetsBitmap bitmap: ", bitmap);
        return bitmap;
    }

    public String c(String str, String str2) {
        return a(str, str2, "");
    }

    public String a(String str, String str2, String str3) {
        LogUtil.a("Track_SportExamResManager", "getSportExamFilePath fileName: ", str, ", fileType: ", str2, ", languageType: ", str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "zh-CN";
        }
        String e = e(str, str2, str3);
        if (TextUtils.isEmpty(e) && str3.equals("en") && c(str2)) {
            e = e(str, str2, "zh-CN");
        }
        StringBuilder sb = new StringBuilder(e);
        if (!str.contains(".")) {
            sb.append(str);
            sb.append(".");
            sb.append(str2);
        }
        return sb.toString();
    }

    public String e(String str, String str2, String str3) {
        LogUtil.a("Track_SportExamResManager", "getSportExamDirectory fileName = ", str, ", fileType = ", str2, ", languageType = ", str3);
        StringBuilder sb = new StringBuilder("sport_exam_res");
        sb.append(File.separator);
        sb.append("1.0.0");
        sb.append(File.separator);
        sb.append(c(str2) ? "png" : str2);
        sb.append(File.separator);
        if (str2.equals("mp3") || str2.equals("ogg")) {
            sb.append(str3);
            sb.append(File.separator);
            if (str3.equals("zh-CN")) {
                sb.append("female");
                sb.append(File.separator);
                sb.append("mandarin");
                sb.append(File.separator);
            } else {
                sb.append("male");
                sb.append(File.separator);
            }
        }
        if (c(str2) && !i(str)) {
            sb.append(str3);
            sb.append(File.separator);
        }
        if (d(str)) {
            sb.append("frame");
            sb.append(File.separator);
        }
        return sb.toString();
    }

    private boolean c(String str) {
        return str.equals("png") || str.equals("webp");
    }

    private boolean i(String str) {
        if (!TextUtils.isEmpty(str)) {
            return d(str) || str.startsWith("light_beam") || e(str) || str.startsWith("public_") || "camera_bg".equals(str);
        }
        LogUtil.h("Track_SportExamResManager", "isPublicUseImage fileName null");
        return false;
    }

    private boolean e(String str) {
        return "pic_standflex_introduce_first".equals(str) || "pic_standflex_introduce_second".equals(str) || "pic_supineleg_introduce_first".equals(str) || "pic_supineleg_introduce_second".equals(str);
    }

    private boolean d(String str) {
        return str.startsWith("frame_");
    }

    public Bitmap aWO_(String str) {
        return aWP_(str, "png");
    }

    public Bitmap aWP_(String str, String str2) {
        String c2 = c(str, str2);
        LogUtil.a("Track_SportExamResManager", "getSportExamBitmap() imagePath: ", c2);
        return aWN_(c2);
    }
}

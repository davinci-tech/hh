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
public class gyl {
    private gyl() {
    }

    static class d {
        private static final gyl b = new gyl();
    }

    public static gyl b() {
        return d.b;
    }

    public Bitmap aWM_(String str) {
        LogUtil.a("Track_AiActionTrainResManager", "getAssetsBitmap imagePath: ", str);
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("Track_AiActionTrainResManager", "getAssetsBitmap failed, imagePath is null", str);
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
            LogUtil.b("Track_AiActionTrainResManager", "getAssetsBitmap ioException: ", LogAnonymous.b((Throwable) e));
        }
        LogUtil.a("Track_AiActionTrainResManager", "getAssetsBitmap bitmap: ", bitmap);
        return bitmap;
    }

    public String d(String str, String str2) {
        return e(str, str2, "");
    }

    public String e(String str, String str2, String str3) {
        LogUtil.a("Track_AiActionTrainResManager", "getAiActionTrainFilePath fileName: ", str, ", fileType: ", str2, ", languageType: ", str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(a(str, str2, str3));
        if (!str.contains(".")) {
            sb.append(str);
            sb.append(".");
            sb.append(str2);
        }
        return sb.toString();
    }

    private String a(String str, String str2, String str3) {
        LogUtil.a("Track_AiActionTrainResManager", "getAiActionTrainDirectory fileName = ", str, ", fileType = ", str2, ", languageType = ", str3);
        StringBuilder sb = new StringBuilder();
        if (str2.equals("mp3") || str2.equals("wav")) {
            sb.append(str3);
            sb.append(File.separator);
        }
        if (b(str2)) {
            sb.append("ai_fitness_sport_img");
            sb.append(File.separator);
        }
        return sb.toString();
    }

    public Bitmap aWK_(String str) {
        return aWL_(str, "png");
    }

    public Bitmap aWL_(String str, String str2) {
        String d2 = d(str, str2);
        LogUtil.a("Track_AiActionTrainResManager", "getAiActionTrainBitmap() imagePath: ", d2);
        return aWM_(d2);
    }

    private boolean b(String str) {
        return str.equals("png") || str.equals("webp");
    }
}

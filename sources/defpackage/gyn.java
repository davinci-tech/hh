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
public class gyn {
    private gyn() {
    }

    static class a {
        private static final gyn e = new gyn();
    }

    public static gyn d() {
        return a.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.lang.Object[]] */
    public Bitmap aWQ_(String str) {
        Throwable th;
        IOException e;
        InputStream inputStream;
        LogUtil.a("Track_SkipRopeResManager", "getAssetsBitmap() imagePath: ", str);
        InputStream inputStream2 = null;
        r3 = null;
        r3 = null;
        Bitmap bitmap = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                inputStream = BaseApplication.e().getAssets().open(str);
                try {
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                            LogUtil.b("Track_SkipRopeResManager", "getAssetsBitmap() close InputStream error");
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    LogUtil.b("Track_SkipRopeResManager", "getAssetsBitmap() ioException: ", LogAnonymous.b((Throwable) e));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused2) {
                            LogUtil.b("Track_SkipRopeResManager", "getAssetsBitmap() close InputStream error");
                        }
                    }
                    str = new Object[]{"getAssetsBitmap() bitmap: ", bitmap};
                    LogUtil.a("Track_SkipRopeResManager", (Object[]) str);
                    return bitmap;
                }
            } catch (IOException e3) {
                e = e3;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException unused3) {
                        LogUtil.b("Track_SkipRopeResManager", "getAssetsBitmap() close InputStream error");
                    }
                }
                throw th;
            }
            str = new Object[]{"getAssetsBitmap() bitmap: ", bitmap};
            LogUtil.a("Track_SkipRopeResManager", (Object[]) str);
            return bitmap;
        } catch (Throwable th3) {
            inputStream2 = str;
            th = th3;
        }
    }

    public String b(String str, String str2) {
        return d(str, str2, "");
    }

    public String d(String str, String str2, String str3) {
        LogUtil.a("Track_SkipRopeResManager", "getSkipRopeFilePath() fileName: ", str, ", fileType: ", str2, ", languageType: ", str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "zh-CN";
        }
        String a2 = a(str, str2, str3);
        if (TextUtils.isEmpty(a2) && str3.equals("en") && str2.equals("png")) {
            a2 = a(str, str2, "zh-CN");
        }
        StringBuilder sb = new StringBuilder(a2);
        if (!str.contains(".")) {
            sb.append(str);
            sb.append(".");
            sb.append(str2);
        }
        return sb.toString();
    }

    public String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder("skip_rope_res");
        sb.append(File.separator);
        sb.append("1.0.0");
        sb.append(File.separator);
        sb.append(str2);
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
        } else if (str2.equals("png") && !e(str)) {
            sb.append(str3);
            sb.append(File.separator);
        } else {
            LogUtil.h("Track_SkipRopeResManager", "getSkipRopeDirectory() wrong fileType: ", str2);
        }
        return sb.toString();
    }

    private boolean e(String str) {
        return str.startsWith("particle") || str.equals("pic_home_skipping") || str.equals("pic_ripple_big") || str.equals("pic_rope_introduce_first") || str.equals("pic_rope_introduce_second");
    }
}

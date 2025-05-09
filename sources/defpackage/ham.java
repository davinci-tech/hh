package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.watchface.videoedit.gles.Constant;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes4.dex */
public class ham {
    private static final String d = mrv.d + "dynamic_track_music_resource" + File.separator;

    public static String e() {
        try {
            return new File(d).getCanonicalPath();
        } catch (IOException e) {
            ReleaseLogUtil.c("DynamicConfigParam", "getDynamicCloudConfigPathPrefix exception ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String d() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(d);
        sb.append("package resource");
        sb.append(File.separator);
        sb.append(Constant.TYPE_PHOTO);
        sb.append(File.separator);
        sb.append("1.1.1");
        sb.append(File.separator);
        return sb.toString();
    }

    public static String a() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(d);
        sb.append("package resource");
        sb.append(File.separator);
        sb.append("default_music");
        sb.append(File.separator);
        sb.append("1.1.1");
        sb.append(File.separator);
        return sb.toString();
    }

    public static String c() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(d);
        sb.append("package resource");
        sb.append(File.separator);
        sb.append(NetworkService.Constants.CONFIG_SERVICE);
        sb.append(File.separator);
        sb.append("1.1.1");
        sb.append(File.separator);
        sb.append("resource_config.json");
        return sb.toString();
    }

    public static String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(d);
        sb.append("package resource");
        sb.append(File.separator);
        sb.append(NetworkService.Constants.CONFIG_SERVICE);
        sb.append(File.separator);
        return sb.toString();
    }

    public static String a(String str) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(d);
        sb.append("package resource");
        sb.append(File.separator);
        sb.append(Constant.TYPE_PHOTO);
        sb.append(File.separator);
        sb.append("1.1.1");
        sb.append(File.separator);
        sb.append(str);
        sb.append(".png");
        return sb.toString();
    }

    public static String c(String str, boolean z) {
        StringBuilder sb = new StringBuilder(16);
        sb.append(d);
        if (z) {
            sb.append("package resource");
            sb.append(File.separator);
            sb.append("default_music");
            sb.append(File.separator);
        } else {
            sb.append(str);
            sb.append(File.separator);
        }
        sb.append("1.1.1");
        sb.append(File.separator);
        sb.append(str);
        sb.append(".aac");
        return sb.toString();
    }
}

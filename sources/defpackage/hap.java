package defpackage;

import android.text.TextUtils;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.File;

/* loaded from: classes4.dex */
public class hap {
    private static final String e = mrv.d + "dynamic_track_map_resource" + File.separator;

    public static String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append("language");
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        sb.append(str2);
        sb.append(File.separator);
        sb.append(str3);
        sb.append(".json");
        return sb.toString();
    }

    public static String d(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(NetworkService.Constants.CONFIG_SERVICE);
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        sb.append(str2);
        sb.append(".json");
        return sb.toString();
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(str);
        sb.append(File.separator);
        return sb.toString();
    }

    public static String b(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(Constant.TYPE_PHOTO);
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        sb.append(str2);
        sb.append(".png");
        return sb.toString();
    }

    public static String a(String str, String str2, String str3, int i) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(str2);
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        if (i == 1) {
            sb.append("8.0");
            sb.append(File.separator);
        }
        sb.append(str3);
        sb.append(File.separator);
        if (i == 1) {
            sb.append("style.data");
        } else {
            sb.append(str3);
            sb.append(".json");
        }
        return sb.toString();
    }

    public static String b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append(e);
        sb.append(str2);
        sb.append(File.separator);
        sb.append(str);
        sb.append(File.separator);
        sb.append("8.0");
        sb.append(File.separator);
        sb.append(str3);
        sb.append(File.separator);
        sb.append("style_extra.data");
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String c(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1542515789:
                if (str.equals("gaode_map")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1534824874:
                if (str.equals("google_map")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1497224444:
                if (str.equals("huawei_map")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1216698124:
                if (str.equals("mark_config")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return "GaoDeMapCustomStyle";
        }
        if (c == 1) {
            return "GoogleMapCustomStyle";
        }
        if (c == 2) {
            return "HmsCustomStyle";
        }
        if (c == 3) {
            return "CustomMarkStyle";
        }
        LogUtil.h("DynamicCustomMapConfigParam", "mapType is exception ", str);
        return "";
    }

    public static String a(int i) {
        if (i == 1) {
            return "gaode_map";
        }
        if (i == 2) {
            return "google_map";
        }
        if (i == 3) {
            return "huawei_map";
        }
        LogUtil.h("DynamicCustomMapConfigParam", "getMapFileName is exception ", Integer.valueOf(i));
        return "";
    }
}

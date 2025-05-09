package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public class cos {
    public static final String b = c(new String[]{"healthdevice"});

    /* renamed from: a, reason: collision with root package name */
    public static final String f11394a = c(new String[]{"healthdevice", "catch"});
    public static final String e = c(new String[]{"plugins"});
    public static final String d = c(new String[]{"healthdevice", "products"});

    private static String c(String[] strArr) {
        String str;
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            sb.append(File.separator);
            sb.append(str2);
        }
        if (strArr.length != 0) {
            sb.append(File.separator);
            str = sb.toString();
        } else {
            str = "";
        }
        try {
            return cpp.a().getFilesDir().getCanonicalPath() + str;
        } catch (IOException unused) {
            LogUtil.a("PluginDevice_Constants", "failed to process file path");
            return str;
        }
    }
}

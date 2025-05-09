package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public class jzi {

    /* renamed from: a, reason: collision with root package name */
    public static final String f14224a;
    public static final String b;
    public static final String c;
    public static final String e;

    static {
        String str = a() + File.separator + "cache" + File.separator + "contacts_sync" + File.separator;
        e = str;
        c = str + File.separator + "vcard" + File.separator;
        b = str + File.separator + "compressed" + File.separator;
        f14224a = str + File.separator + "csv" + File.separator;
    }

    private static String a() {
        try {
            File filesDir = BaseApplication.getContext().getFilesDir();
            if (filesDir != null) {
                return filesDir.getCanonicalPath();
            }
        } catch (IOException unused) {
            LogUtil.b("ContactSync", "getSourcePath catch IOException");
        }
        return "";
    }
}

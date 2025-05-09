package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public class jyn {
    public static final String d;
    public static final String e;

    static {
        String str = a() + File.separator + "cache" + File.separator + "calendar_sync" + File.separator;
        d = str;
        e = str + File.separator + "update" + File.separator;
    }

    private static String a() {
        try {
            File filesDir = BaseApplication.getContext().getFilesDir();
            if (filesDir != null) {
                return filesDir.getCanonicalPath();
            }
        } catch (IOException unused) {
            LogUtil.b("CalendarSyncConstants", "getSourcePath catch IOException");
        }
        return "";
    }
}

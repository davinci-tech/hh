package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.haf.common.os.FileUtils;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import org.json.JSONException;

/* loaded from: classes8.dex */
public final class yg {
    private static final yg e = new yg();

    /* renamed from: a, reason: collision with root package name */
    private yc f17756a;
    private boolean b;
    private final yh d = new yh();

    private yg() {
    }

    public static yg a() {
        return e;
    }

    public String d(Context context) {
        yc c = c(context);
        if (c != null) {
            return c.a();
        }
        return null;
    }

    public String b(Context context) {
        yc c = c(context);
        if (c != null) {
            return c.d();
        }
        return null;
    }

    public yi e(Context context, String str) {
        yc c = c(context);
        if (c != null) {
            return c.c(str);
        }
        return null;
    }

    public Collection<yi> a(Context context) {
        yc c = c(context);
        if (c != null) {
            return c.e();
        }
        return Collections.emptyList();
    }

    public yc d(String str) {
        File file = new File(str);
        if (file.exists()) {
            return d(file);
        }
        return null;
    }

    public String e() {
        return this.d.b();
    }

    public boolean c(String str, File file) {
        return this.d.c(str, file);
    }

    private yc a(Context context, String str) {
        yc ycVar = null;
        try {
            String str2 = "bundle_plugins/bundle_" + str + ".json";
            LogUtil.c("Bundle_InfoManager", "Default module file name: ", str2);
            long currentTimeMillis = System.currentTimeMillis();
            ycVar = b(context, str2);
            LogUtil.c("Bundle_InfoManager", "Parse default module info, cost time:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
            return ycVar;
        } catch (IOException | JSONException e2) {
            LogUtil.e("Bundle_InfoManager", "Failed to create default module info! ex=", LogUtil.a(e2));
            return ycVar;
        }
    }

    private yc d(File file) {
        yc ycVar = null;
        try {
            LogUtil.c("Bundle_InfoManager", "Updated module file : ", file.getName());
            long currentTimeMillis = System.currentTimeMillis();
            ycVar = e(file);
            LogUtil.c("Bundle_InfoManager", "Parse updated module info, cost time:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), "ms");
            return ycVar;
        } catch (IOException | JSONException e2) {
            LogUtil.e("Bundle_InfoManager", "Failed to create updated module info! ex=", LogUtil.a(e2));
            return ycVar;
        }
    }

    private yc c(Context context) {
        yc a2;
        yc ycVar = this.f17756a;
        if (ycVar != null) {
            return ycVar;
        }
        String b = this.d.b();
        String d = this.d.d();
        LogUtil.c("Bundle_InfoManager", "currentVersion:", b, ", defaultVersion:", d, ", allowUpdate:", Boolean.valueOf(this.b));
        if (!this.b || d.equals(b)) {
            a2 = a(context, d);
        } else {
            a2 = d(new File(this.d.c(), "bundle_" + b + ".json"));
        }
        yc ycVar2 = a2;
        this.f17756a = ycVar2;
        return ycVar2;
    }

    private static yc b(Context context, String str) throws IOException, JSONException {
        return yc.d(c(c(context, str)));
    }

    private yc e(File file) throws IOException, JSONException {
        if (file == null || !file.exists()) {
            return null;
        }
        return yc.d(c(new FileInputStream(file)));
    }

    private static InputStream c(Context context, String str) {
        Resources resources = context.getResources();
        if (resources != null) {
            try {
                return resources.getAssets().open(str);
            } catch (IOException e2) {
                LogUtil.e("Bundle_InfoManager", "createInputStreamFromAssets ex=", LogUtil.a(e2));
            }
        }
        return null;
    }

    private static String c(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return "";
        }
        try {
            return FileUtils.a(inputStream, 5242880L);
        } finally {
            FileUtils.d(inputStream);
        }
    }
}

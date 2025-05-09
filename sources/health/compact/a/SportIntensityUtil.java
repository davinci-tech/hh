package health.compact.a;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.drd;
import defpackage.mrv;
import defpackage.mrx;
import defpackage.mxd;
import defpackage.mxf;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public class SportIntensityUtil {
    private static volatile mxf b;
    private static final Object e = new Object();
    private static final String d = mrv.d + "index_sport_intensity" + File.separator;

    private static int b(int i, int i2, int i3, int i4) {
        return (i < i2 || i > i3) ? i4 : i;
    }

    private SportIntensityUtil() {
    }

    private static void c() {
        e();
        b.o(b(b.l(), 0, 200, 110));
        b.b(b(b.c(), 0, 200, 60));
        b.j(b(b.g(), 0, 100, 64));
        b.a(b(b.d(), 0, 255, 80));
        b.f(b(b.h(), 1, 10, 3));
        b.c(Math.max(b(b.a(), 1, 10, 5), b.h()));
        b.l(b(b.k(), 0, 200, 105));
        b.e(b(b.e(), 0, 200, 60));
        b.g(b(b.j(), 0, 100, 25));
        b.h(b(b.i(), 0, 255, 88));
        b.n(b(b.n(), 0, 255, 88));
        b.i(b(b.f(), 1, 10, 1));
        b.d(Math.max(b(b.b(), 1, 10, 1), b.f()));
    }

    public static mxf b() {
        if (b == null) {
            synchronized (e) {
                if (b == null) {
                    c();
                }
            }
        }
        b.l(110);
        com.huawei.hwlogsmodel.LogUtil.a("Step_SportIntensityUtil", "sSportIntensityConfigBean", b.toString());
        return b;
    }

    private static void e() {
        String c = CommonUtil.c(drd.d("com.huawei.health_Sport_Common", "sport_intensity_index", "json"));
        if (TextUtils.isEmpty(c)) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_SportIntensityUtil", "cloudConfigPath is illegal!");
            b = new mxf();
            return;
        }
        String e2 = mrx.e(new File(c));
        com.huawei.hwlogsmodel.LogUtil.h("Step_SportIntensityUtil", "EzPluginHelper.readFileToData jsonData = ", e2);
        if (TextUtils.isEmpty(e2)) {
            e2 = c("local_sport_intensity_index.json");
            com.huawei.hwlogsmodel.LogUtil.h("Step_SportIntensityUtil", "parseAssestsJsonFile jsonData = ", e2);
        }
        if (TextUtils.isEmpty(e2)) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_SportIntensityUtil", "getCloudConfigScriptJsonObject jsonData is empty ");
            b = new mxf();
            return;
        }
        try {
            b = ((mxd) new Gson().fromJson(e2, mxd.class)).a();
        } catch (JsonSyntaxException e3) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_SportIntensityUtil", ExceptionUtils.d(e3));
            b = new mxf();
        }
    }

    public static void d() {
        StringBuilder sb = new StringBuilder();
        String str = d;
        sb.append(str);
        sb.append("index_sport_intensity.json");
        String c = CommonUtil.c(sb.toString());
        File file = c != null ? new File(c) : null;
        if (file == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_SportIntensityUtil", "updateDownloadToLocalConfig tempFile is null");
            return;
        }
        if (!file.exists()) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_SportIntensityUtil", "already renamed");
            return;
        }
        String c2 = CommonUtil.c(str + "index_sport_intensity_config.json");
        if (c2 == null || !file.renameTo(new File(c2))) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_SportIntensityUtil", "rename fail");
        }
    }

    private static String c(String str) {
        try {
            return a(BaseApplication.getContext().getAssets().open(str));
        } catch (IOException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_SportIntensityUtil", "getString IOException");
            return "";
        }
    }

    private static String a(InputStream inputStream) {
        String str;
        str = "";
        if (inputStream == null) {
            return "";
        }
        try {
            try {
                byte[] bArr = new byte[inputStream.available()];
                str = inputStream.read(bArr) > 0 ? new String(bArr, StandardCharsets.UTF_8) : "";
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    com.huawei.hwlogsmodel.LogUtil.b("Step_SportIntensityUtil", "getString ioException", LogAnonymous.b((Throwable) e2));
                }
            } catch (IOException unused) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_SportIntensityUtil", "getString IOException");
            }
            return str;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e3) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_SportIntensityUtil", "getString ioException", LogAnonymous.b((Throwable) e3));
            }
        }
    }
}

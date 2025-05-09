package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BuildConfigProperties;
import health.compact.a.IoUtils;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class cdg {
    private static final Set<String> c = Collections.unmodifiableSet(new HashSet<String>() { // from class: cdg.2
        private static final long serialVersionUID = -5972676969371795713L;

        {
            add("f6f6eb8d-6168-4d42-ae08-27da0efda3a9");
            add("8df8e0d8-e4bf-42f0-b0f3-0e11bf16caff");
            add("c7d28238-fec2-4985-b2a4-f29c96637ac4");
        }
    });

    public static boolean e() {
        boolean e = BuildConfigProperties.e("IS_HTY_VERSION", false);
        ReleaseLogUtil.e("R_HtyUtils", "isHtyVersion: ", Boolean.valueOf(e));
        return e;
    }

    public static void c() {
        for (String str : c) {
            a(str);
            c(str);
        }
        SharedPreferenceManager.e(Integer.toString(10008), "resource_Preset_Hty", true);
        ReleaseLogUtil.e("R_HtyUtils", "presetDeviceResourceHty end");
    }

    public static void a() {
        d("index_all.json", cvy.c());
        ReleaseLogUtil.e("R_HtyUtils", "presetIndexAll end");
    }

    public static void d() {
        d("index.json", cdr.a());
        ReleaseLogUtil.e("R_HtyUtils", "presetIndex end");
    }

    public static void e(cuy cuyVar) {
        b("img_index_all", cvy.b("img_index_all") + cuyVar.a());
        ReleaseLogUtil.e("R_HtyUtils", "presetImgIndexAll end");
    }

    public static void d(cuy cuyVar) {
        b("lang_index_all", cvy.b("lang_index_all") + cuyVar.d());
        ReleaseLogUtil.e("R_HtyUtils", "presetLongIndexAll end");
    }

    private static void a(String str) {
        b(str, cdv.b().d(str));
        d(str);
    }

    private static void c(String str) {
        String pluginIndexOobe = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginIndexOobe(str);
        if (TextUtils.isEmpty(pluginIndexOobe)) {
            return;
        }
        b(pluginIndexOobe, cdv.b().d(pluginIndexOobe));
        d(pluginIndexOobe);
    }

    private static void b(String str, String str2) {
        try {
            String[] list = BaseApplication.getContext().getAssets().list(str);
            if (list.length > 0) {
                for (String str3 : list) {
                    b(str + File.separator + str3, str2 + File.separator + str3);
                }
                return;
            }
            d(str, str2);
        } catch (IOException e) {
            ReleaseLogUtil.c("R_HtyUtils", "addResourceLangByUuid IOException: ", ExceptionUtils.d(e));
        }
    }

    private static void d(String str) {
        FileOutputStream fileOutputStream;
        String valueOf;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File file = new File(cdv.b().d(str) + File.separator + "done");
                if (!file.getParentFile().exists()) {
                    LogUtil.a("HtyUtils", "createDoneMarkFile isDirMade is ", Boolean.valueOf(file.mkdir()));
                }
                if (!file.exists()) {
                    LogUtil.a("HtyUtils", "createDoneMarkFile isNewFileCreated is ", Boolean.valueOf(file.createNewFile()));
                }
                valueOf = String.valueOf(new Date().getTime() / 1000);
                LogUtil.a("HtyUtils", "createDoneMarkFile timeStamp is =", valueOf);
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            byte[] bytes = valueOf.getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(bytes, 0, bytes.length);
            IoUtils.e(fileOutputStream);
        } catch (IOException e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            ReleaseLogUtil.c("R_HtyUtils", "addDoneForResource IOException: ", ExceptionUtils.d(e));
            IoUtils.e(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileOutputStream);
            throw th;
        }
    }

    private static void d(String str, String str2) {
        try {
            InputStream open = BaseApplication.getContext().getAssets().open(str);
            File file = new File(str2);
            File file2 = new File(file.getPath().replace(file.getName(), ""));
            if (!file2.exists()) {
                LogUtil.a("HtyUtils", "copyFile mkdir isSuccess: ", Boolean.valueOf(file2.mkdirs()), ", destPath: ", str2);
            }
            FileUtils.e(open, new FileOutputStream(file));
        } catch (IOException e) {
            ReleaseLogUtil.c("R_HtyUtils", "copyFile IOException: ", ExceptionUtils.d(e));
        }
    }
}

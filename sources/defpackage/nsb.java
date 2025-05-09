package defpackage;

import android.content.Context;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.ui.commonui.utils.ResInfo;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes9.dex */
public class nsb {
    private static final String d = File.separator + "picture" + File.separator;

    public static List<String> b(Context context, String str, ResInfo.Location location) {
        return c(context, str, location, false);
    }

    private static List<String> c(Context context, String str, ResInfo.Location location, boolean z) {
        ArrayList arrayList = new ArrayList();
        String location2 = location.getLocation();
        try {
            String str2 = context.getFilesDir().getCanonicalPath() + File.separator + location2 + d;
            nri d2 = d(jbj.c(GRSManager.a(context).getNoCheckUrl("getNewBatchPluginUrl", GRSManager.a(context).getCommonCountryCode()) + "com.huawei.health_common_config", "resources=" + location2 + "_resources"), str);
            String str3 = str2 + str;
            if (d2 == null) {
                a(str3, str, arrayList);
                return arrayList;
            }
            String str4 = str3 + ".zip";
            String a2 = d2.a();
            String e = SharedPreferenceManager.e("picture_resources_module", str, "");
            if (z || !e.equals(a2)) {
                if (!jbj.b(d2.b(), str4)) {
                    LogUtil.e("PicResUtils", "fetchAnimHeader for animResName = ", str, "failed!");
                    a(str3, str, arrayList);
                    return arrayList;
                }
                SharedPreferenceManager.c("picture_resources_module", str, a2);
            }
            b(str4, str3);
            a(str3, str, arrayList);
            return arrayList;
        } catch (IOException e2) {
            LogUtil.e("PicResUtils", "fetchAnimation failed, cause exception: ", e2.getMessage(), ", cause: ", e2.getCause());
            return arrayList;
        }
    }

    private static void b(String str, String str2) {
        if (new File(str).exists()) {
            if (nsh.d(str, str2 + File.separator) != -1) {
                FileUtils.d(new File(str));
            } else {
                LogUtil.e("PicResUtils", "unzipAndParseFile failed!");
            }
        }
    }

    private static void a(String str, String str2, List<String> list) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            LogUtil.e("PicResUtils", "unzipAndParseFile for ", str2, ", picResName failed! cause picDir not exists or not dir");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            LogUtil.e("PicResUtils", "unzipAndParseFile for ", str2, ", picResName failed! cause files is null!");
            return;
        }
        Arrays.sort(listFiles, new Comparator<File>() { // from class: nsb.1
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(File file2, File file3) {
                if (file2 == null || file3 == null) {
                    return 0;
                }
                String name = file2.getName();
                String name2 = file3.getName();
                if (name == null || name2 == null) {
                    return 0;
                }
                return name.compareTo(name2);
            }
        });
        for (File file2 : listFiles) {
            if (file2 != null) {
                try {
                    list.add(file2.getCanonicalPath());
                } catch (IOException e) {
                    LogUtil.e("PicResUtils", "unzipAndParseFile for ", str2, ", picResName failed! cause exception happened = ", e.getMessage(), ", cause = ", e.getCause());
                }
            }
        }
    }

    private static nri d(String str, String str2) {
        String d2;
        nri nriVar = null;
        try {
            List<nri> list = (List) nrv.c(str, new TypeToken<ArrayList<nri>>() { // from class: nsb.4
            }.getType());
            if (list == null) {
                LogUtil.e("PicResUtils", "extractAnimDownloadUrls failed, configEntityList is null!");
                return null;
            }
            for (nri nriVar2 : list) {
                if (nriVar2 != null && (d2 = nriVar2.d()) != null && d2.equals(str2)) {
                    nriVar = nriVar2;
                }
            }
            return nriVar;
        } catch (JsonSyntaxException e) {
            LogUtil.e("PicResUtils", "extractConfigEntity JsonSyntaxException", e.getMessage());
            return null;
        }
    }
}

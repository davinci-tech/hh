package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nyw {
    /* JADX INFO: Access modifiers changed from: private */
    public static int b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("VersionUtil", "version1 or version2 is null");
            return -2;
        }
        if (str.equals(str2)) {
            return 0;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split.length - split2.length != 0) {
            LogUtil.a("VersionUtil", "The lengths of version1 and version2 are different");
            return -2;
        }
        try {
            int parseInt = Integer.parseInt(split[0]) - Integer.parseInt(split2[0]);
            for (int i = 0; i < split.length && parseInt == 0; i++) {
                parseInt = Integer.parseInt(split[i]) - Integer.parseInt(split2[i]);
            }
            return parseInt > 0 ? 1 : -1;
        } catch (NumberFormatException unused) {
            LogUtil.b("VersionUtil", "compareVersion NumberFormatException");
            return -2;
        }
    }

    private static int c(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.h("VersionUtil", "dir is not exists");
            return -1;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            LogUtil.h("VersionUtil", "dir is null");
            return -1;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (File file2 : listFiles) {
            if (file2 != null) {
                if (file2.isFile()) {
                    String path = file2.getPath();
                    if (path.substring(path.lastIndexOf(".") + 1).equals(str2)) {
                        i3++;
                    }
                    i2++;
                } else {
                    i++;
                }
            }
        }
        LogUtil.a("VersionUtil", "dirCount", Integer.valueOf(i), "fileCount", Integer.valueOf(i2), str2, "extCount", Integer.valueOf(i3));
        return i3 == 1 ? 1 : 0;
    }

    private static void d(List<nza> list) {
        Collections.sort(list, new Comparator<nza>() { // from class: nyw.5
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(nza nzaVar, nza nzaVar2) {
                if (nzaVar != null && nzaVar2 != null) {
                    return nyw.b(nzaVar.d(), nzaVar2.d());
                }
                LogUtil.h("VersionUtil", "v1 or v2 is null, just return.");
                return -2;
            }
        });
    }

    public static nyt e(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("VersionUtil", "featureDirPath is empty");
            return null;
        }
        File file = new File(str, "config.json");
        if (!file.exists()) {
            LogUtil.h("VersionUtil", "configJsonFile is not exists");
            return null;
        }
        String e = mrx.e(file);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("VersionUtil", "configJsonText is null");
            return null;
        }
        try {
            return (nyt) new Gson().fromJson(new JSONObject(CommonUtil.z(e)).toString(), new TypeToken<nyt>() { // from class: nyw.2
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("VersionUtil", "loadConfigJson JsonSyntaxException");
            return null;
        } catch (JSONException unused2) {
            LogUtil.b("VersionUtil", "loadConfigJson - JSONException");
            return null;
        }
    }

    public static String c(String str, String str2, nyt nytVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || nytVar == null) {
            LogUtil.h("VersionUtil", "emuiVersion or featureDirPath or configJson is null");
            return null;
        }
        int b = b(str, nytVar.a());
        LogUtil.a("VersionUtil", "scene1Result", Integer.valueOf(b));
        if (b != -1) {
            LogUtil.a("VersionUtil", "SCENE: delete scenario, not display");
            return null;
        }
        return d(str, str2, nytVar);
    }

    private static String d(String str, String str2, nyt nytVar) {
        int c = c(str2, "xml");
        String str3 = null;
        if (c == -1) {
            LogUtil.a("VersionUtil", "isDirOneXmlFile is null");
            return null;
        }
        if (c == 1) {
            LogUtil.a("VersionUtil", "SCENE: new and existing scenarios, display");
            int b = b(str, nytVar.d());
            LogUtil.a("VersionUtil", "scene2Result", Integer.valueOf(b));
            if (b != -1) {
                return nytVar.c();
            }
            LogUtil.a("VersionUtil", "scene2 not config valid versoin");
            return null;
        }
        LogUtil.a("VersionUtil", "SCENE: update and re-signing scenarios, display");
        List<nza> e = nytVar.e();
        if (e == null) {
            LogUtil.h("VersionUtil", "listVersion is null");
            return null;
        }
        d(e);
        for (nza nzaVar : e) {
            if (nzaVar != null && b(nzaVar.d(), str) != 1) {
                str3 = nzaVar.e();
            }
        }
        if (!TextUtils.isEmpty(str3)) {
            return nytVar.c() + "_" + str3;
        }
        LogUtil.a("VersionUtil", "scene3 not config valid versoin");
        return str3;
    }
}

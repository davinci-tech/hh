package defpackage;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class meb {
    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            str2 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "achievemedal" + File.separator + str;
        } catch (IOException e) {
            LogUtil.b("PLGACHIEVE_TexturePathManager", "FileNotFoundException:", e.getMessage());
            str2 = "";
        }
        StringBuilder sb = new StringBuilder(str2);
        sb.append(File.separator);
        String sb2 = sb.toString();
        sb.append("texture.jpg");
        return mlb.d(sb.toString()) ? sb2 : "";
    }

    public static boolean e(String str, String str2, String str3) {
        if (d(str2)) {
            return false;
        }
        if (mlb.o(str3) && !TextUtils.isEmpty(str)) {
            try {
                return c(str2, new JSONObject(str)) != 0;
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_TexturePathManager", "parseJsonData Exception");
            }
        }
        return true;
    }

    public static boolean d(String str) {
        return "66".equals(str);
    }

    public static boolean b(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_TexturePathManager", "createFileDir: untrusted -> " + str);
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            Object[] objArr = new Object[2];
            objArr[0] = "create dir:";
            objArr[1] = mkdirs ? "sucess" : ParamConstants.CallbackMethod.ON_FAIL;
            LogUtil.a("PLGACHIEVE_TexturePathManager", objArr);
            if (!mkdirs) {
                return false;
            }
        }
        return true;
    }

    public static String b(String str, boolean z) {
        if (TextUtils.isEmpty(str) || !str.contains("_")) {
            return "";
        }
        String[] split = str.split("_");
        if (split.length < 2) {
            return "";
        }
        if (z) {
            return split[0];
        }
        return split[1];
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            lastIndexOf = 0;
        }
        return str.substring(lastIndexOf, str.length());
    }

    public static int c(String str, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
                LogUtil.b("PLGACHIEVE_TexturePathManager", "judgeValidKey Exception");
            }
        }
        return 0;
    }
}

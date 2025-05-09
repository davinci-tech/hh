package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class owj {
    public static void c(int i) {
        Context context = BaseApplication.getContext();
        if (i == 0) {
            String e = e(context);
            if (e(e)) {
                SharedPreferenceManager.e(context, String.valueOf(10000), "ecg_blocklist", e, (StorageParams) null);
                dwo.d().a(new IBaseResponseCallback() { // from class: owj.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a("EcgBlockListUtil", "sendEcgBlockList errorCode : ", Integer.valueOf(i2));
                    }
                }, e);
                return;
            }
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(10000), "ecg_blocklist", "", (StorageParams) null);
    }

    private static String e(Context context) {
        try {
            return d(context.getFilesDir().getCanonicalPath() + File.separator + "lightcloud" + File.separator + "ecgblock" + File.separator + "ecg_block_list.json");
        } catch (IOException unused) {
            LogUtil.b("EcgBlockListUtil", "parseFile getCanonicalPath error");
            return "";
        }
    }

    private static String d(String str) {
        String str2;
        str2 = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
                fileInputStream.close();
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("EcgBlockListUtil", "getStringFile IOException ");
        }
        return str2;
    }

    private static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("EcgBlockListUtil", "handleJson json is is empty");
            return false;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "ecg_blocklist");
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        try {
        } catch (JSONException unused) {
            LogUtil.b("EcgBlockListUtil", "handleJson JSONException");
        }
        return new JSONObject(str).getInt("versionCode") > new JSONObject(b).getInt("versionCode");
    }
}

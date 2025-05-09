package defpackage;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import health.compact.a.CommonUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdk {
    public static int c(String str, JSONObject jSONObject, HashMap<String, String> hashMap, HttpResCallBack httpResCallBack) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (e(str)) {
            return mdh.e(str, jSONObject, hashMap, httpResCallBack);
        }
        return mdl.e(str, jSONObject, hashMap, httpResCallBack);
    }

    public static int d(String str, String str2, HttpResCallBack httpResCallBack) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return -1;
        }
        if (e(str)) {
            return mdh.e(str, str2, httpResCallBack);
        }
        return mdl.c(str, str2, httpResCallBack);
    }

    private static boolean e(String str) {
        int indexOf = str.indexOf(":");
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            LogUtil.a("PLGACHIEVE_HttpUtils", "isHttpProtocol protocol:", substring);
            if (substring.equalsIgnoreCase("http")) {
                return true;
            }
        }
        return false;
    }

    public static void b(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        if (httpURLConnection == null || hashMap == null || hashMap.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                httpURLConnection.setRequestProperty(String.valueOf(key), String.valueOf(value));
            }
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return new byte[1024];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        int i = 0;
        do {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
            i += read;
        } while (i <= 10485760);
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    public static boolean e(InputStream inputStream, String str) {
        boolean z;
        boolean z2 = false;
        if (inputStream == null) {
            return false;
        }
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_HttpUtils", "saveFile: untrusted -> " + str);
            return false;
        }
        String c = CommonUtil.c(str);
        if (c == null || !c.contains("/")) {
            LogUtil.h("PLGACHIEVE_HttpUtils", "saveFile error: path does not contain /");
            return false;
        }
        int lastIndexOf = c.lastIndexOf(47);
        if (lastIndexOf < 0) {
            return false;
        }
        String substring = c.substring(0, lastIndexOf);
        File file = new File(substring);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            LogUtil.a("PLGACHIEVE_HttpUtils", "saveFile mkDirRet:", Boolean.valueOf(mkdirs));
            if (!mkdirs) {
                return false;
            }
        }
        File file2 = new File(c);
        File file3 = new File(substring + "/_" + c.substring(lastIndexOf + 1, c.length()));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                boolean renameTo = file3.renameTo(file2);
                if (renameTo) {
                    z = true;
                } else {
                    if (file3.exists()) {
                        LogUtil.a("PLGACHIEVE_HttpUtils", "saveFile tmp deleteRet:", Boolean.valueOf(file3.delete()));
                    }
                    z = false;
                }
                LogUtil.a("PLGACHIEVE_HttpUtils", "saveFile isRenameOK:", Boolean.valueOf(renameTo));
                fileOutputStream.close();
                z2 = z;
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_HttpUtils", "saveFile:IOException");
        }
        d(inputStream);
        return z2;
    }

    private static void d(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_HttpUtils", "saveFile:IOException");
            }
        }
    }
}

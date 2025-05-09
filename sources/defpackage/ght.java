package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ght {
    /* JADX WARN: Multi-variable type inference failed */
    public static Map<Integer, fqz> a(File file) {
        BufferedReader bufferedReader;
        String str = "parseSrt() finally IOException";
        if (file == null || !file.exists()) {
            LogUtil.h("Suggestion_SrtParserUtils", "parseSrt() srtFile is null or no exists");
            return new HashMap();
        }
        HashMap hashMap = new HashMap(16);
        BufferedReader bufferedReader2 = null;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
            }
        } catch (IOException unused) {
        }
        try {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] a2 = a(readLine, sb);
                    if (a2.length != 0) {
                        hashMap.put(Integer.valueOf(i), c(a2));
                        i++;
                        sb.delete(0, sb.length());
                    }
                } else {
                    try {
                        break;
                    } catch (IOException unused2) {
                        LogUtil.b("Suggestion_SrtParserUtils", "parseSrt() finally IOException");
                        bufferedReader2 = i;
                    }
                }
            }
            bufferedReader.close();
            bufferedReader2 = i;
        } catch (IOException unused3) {
            bufferedReader3 = bufferedReader;
            LogUtil.b("Suggestion_SrtParserUtils", "parseSrt() IOException");
            bufferedReader2 = bufferedReader3;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                    bufferedReader2 = bufferedReader3;
                } catch (IOException unused4) {
                    LogUtil.b("Suggestion_SrtParserUtils", "parseSrt() finally IOException");
                    bufferedReader2 = bufferedReader3;
                }
            }
            str = "parseSrt() srtMap size:";
            LogUtil.a("Suggestion_SrtParserUtils", "parseSrt() srtMap size:", Integer.valueOf(hashMap.size()));
            return hashMap;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException unused5) {
                    LogUtil.b("Suggestion_SrtParserUtils", str);
                }
            }
            throw th;
        }
        str = "parseSrt() srtMap size:";
        LogUtil.a("Suggestion_SrtParserUtils", "parseSrt() srtMap size:", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    private static String[] a(String str, StringBuilder sb) {
        if (!TextUtils.isEmpty(str) && str.length() <= 1000) {
            sb.append(str);
            sb.append("@");
            return new String[0];
        }
        String[] split = sb.toString().split("@");
        if (split.length >= 3) {
            return split;
        }
        sb.delete(0, sb.length());
        return new String[0];
    }

    private static fqz c(String[] strArr) {
        fqz fqzVar = new fqz();
        String str = strArr[1];
        int e = e(str);
        int b = b(str);
        String a2 = a(strArr);
        fqzVar.a(e);
        fqzVar.c(b);
        fqzVar.d(a2);
        return fqzVar;
    }

    private static int e(String str) {
        return (((b(str, 0, 2) * 3600) + (b(str, 3, 5) * 60) + b(str, 6, 8)) * 1000) + b(str, 9, 12);
    }

    private static int b(String str) {
        return (((b(str, 17, 19) * 3600) + (b(str, 20, 22) * 60) + b(str, 23, 25)) * 1000) + b(str, 26, 29);
    }

    private static int b(String str, int i, int i2) {
        if (str == null) {
            ReleaseLogUtil.d("Suggestion_SrtParserUtils", "getFormatTime null startIndex:", Integer.valueOf(i), " endIndex:", Integer.valueOf(i2));
            return 0;
        }
        if (i < 0 || i2 > str.length()) {
            ReleaseLogUtil.d("Suggestion_SrtParserUtils", "getFormatTime totalTimeStr:", str, " startIndex:", Integer.valueOf(i), " endIndex:", Integer.valueOf(i2));
            return 0;
        }
        return CommonUtil.h(str.substring(i, i2));
    }

    private static String a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        int i = 2;
        while (true) {
            if (i >= strArr.length) {
                break;
            }
            sb.append(strArr[i]);
            sb.append(System.lineSeparator());
            if (i > 5) {
                LogUtil.h("Suggestion_SrtParserUtils", "parseSrtBody() same time max line beyond limit");
                break;
            }
            i++;
        }
        return sb.toString().substring(0, r3.length() - 1);
    }
}

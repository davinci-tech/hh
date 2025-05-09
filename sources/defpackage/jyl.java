package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jyl {
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.util.List<java.lang.String> r8, java.lang.String r9, java.lang.String r10, int r11) {
        /*
            java.lang.Integer r0 = java.lang.Integer.valueOf(r11)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = ", calendarName = "
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "writeCalendarFiles syncFlag: "
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0, r1}
            java.lang.String r1 = "CalendarFileUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = 1
            r3 = 0
            if (r8 == 0) goto L2b
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L29
            goto L2b
        L29:
            r4 = r3
            goto L2c
        L2b:
            r4 = r0
        L2c:
            boolean r5 = android.text.TextUtils.isEmpty(r9)
            if (r5 != 0) goto L3b
            boolean r5 = android.text.TextUtils.isEmpty(r10)
            if (r5 == 0) goto L39
            goto L3b
        L39:
            r5 = r3
            goto L3c
        L3b:
            r5 = r0
        L3c:
            if (r4 != 0) goto Ld0
            if (r5 == 0) goto L42
            goto Ld0
        L42:
            int r2 = r8.size()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r10)
            java.lang.String r9 = r4.toString()
            byte[] r10 = defpackage.cvx.g(r11)     // Catch: java.io.IOException -> Lc0
            java.io.FileOutputStream r9 = a(r9, r3)     // Catch: java.io.IOException -> Lc0
            r9.write(r10)     // Catch: java.io.IOException -> Lc0
            r10 = r3
            r11 = r10
        L62:
            int r4 = r8.size()     // Catch: java.io.IOException -> Lc1
            if (r11 >= r4) goto Lcb
            java.lang.Object r4 = r8.get(r11)     // Catch: java.io.IOException -> Lc1
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.io.IOException -> Lc1
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.io.IOException -> Lc1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lc1
            r6.<init>()     // Catch: java.io.IOException -> Lc1
            java.lang.String r7 = "write file json: "
            r6.append(r7)     // Catch: java.io.IOException -> Lc1
            r6.append(r4)     // Catch: java.io.IOException -> Lc1
            java.lang.String r6 = r6.toString()     // Catch: java.io.IOException -> Lc1
            r5[r3] = r6     // Catch: java.io.IOException -> Lc1
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)     // Catch: java.io.IOException -> Lc1
            byte[] r4 = defpackage.cvx.d(r4)     // Catch: java.io.IOException -> Lc1
            int r5 = r4.length     // Catch: java.io.IOException -> Lc1
            byte[] r5 = defpackage.cvx.g(r5)     // Catch: java.io.IOException -> Lc1
            boolean r4 = a(r9, r5, r4)     // Catch: java.io.IOException -> Lc1
            if (r4 == 0) goto L98
            int r10 = r10 + 1
        L98:
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.io.IOException -> Lc1
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lc1
            r6.<init>()     // Catch: java.io.IOException -> Lc1
            java.lang.String r7 = "write file result: "
            r6.append(r7)     // Catch: java.io.IOException -> Lc1
            r6.append(r4)     // Catch: java.io.IOException -> Lc1
            java.lang.String r4 = r6.toString()     // Catch: java.io.IOException -> Lc1
            r5[r3] = r4     // Catch: java.io.IOException -> Lc1
            java.lang.String r4 = " ,writeFileSuccessCount = "
            r5[r0] = r4     // Catch: java.io.IOException -> Lc1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)     // Catch: java.io.IOException -> Lc1
            r6 = 2
            r5[r6] = r4     // Catch: java.io.IOException -> Lc1
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)     // Catch: java.io.IOException -> Lc1
            int r11 = r11 + 1
            goto L62
        Lc0:
            r10 = r3
        Lc1:
            java.lang.String r8 = "writeCalendarFiles IOException"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
        Lcb:
            if (r10 != r2) goto Lce
            goto Lcf
        Lce:
            r0 = r3
        Lcf:
            return r0
        Ld0:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "invalid parameters: jsons = "
            r11.<init>(r0)
            r11.append(r8)
            java.lang.String r8 = ", calendarPath dir = "
            r11.append(r8)
            r11.append(r9)
            r11.append(r2)
            r11.append(r10)
            java.lang.String r8 = r11.toString()
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jyl.c(java.util.List, java.lang.String, java.lang.String, int):boolean");
    }

    public static boolean d(String str, String str2, String str3, int i) {
        LogUtil.a("CalendarFileUtils", "writeCalendarFiles syncFlag: ", Integer.valueOf(i), ", calendarName = " + str3);
        boolean z = str == null || str.isEmpty();
        boolean z2 = TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3);
        if (z || z2) {
            LogUtil.a("CalendarFileUtils", "invalid parameters: json = " + str + ", calendarPath dir = " + str2 + ", calendarName = " + str3);
            return false;
        }
        String str4 = str2 + str3;
        try {
            byte[] g = cvx.g(i);
            FileOutputStream a2 = a(str4, false);
            a2.write(g);
            LogUtil.a("CalendarFileUtils", "write file json to smartWatch: " + str);
            a2.write(cvx.d(str));
            a2.flush();
            return true;
        } catch (IOException unused) {
            LogUtil.a("CalendarFileUtils", "writeCalendarFiles IOException");
            return false;
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("CalendarFileUtils", "IllegalArgumentException: failed to write the file .");
            return false;
        }
    }

    public static boolean e(List<String> list, String str, String str2, int i) {
        LogUtil.a("CalendarFileUtils", "writeCalendarNullFiles syncFlag: ", Integer.valueOf(i), ", calendarName = " + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("CalendarFileUtils", "writeCalendarNullFiles: jsons = " + list + ", calendarPath dir = " + str + ", calendarName = " + str2);
            return false;
        }
        String str3 = str + str2;
        try {
            byte[] g = cvx.g(i);
            FileOutputStream a2 = a(str3, false);
            a2.write(g);
            LogUtil.a("CalendarFileUtils", "writeCalendarNullFiles file json: " + list);
            byte[] d = cvx.d(list.get(0));
            a2.write(cvx.g(d.length));
            a2.write(d);
            a2.flush();
            return true;
        } catch (IOException unused) {
            LogUtil.a("CalendarFileUtils", "writeCalendarNullFiles IOException");
            return false;
        }
    }

    private static boolean a(FileOutputStream fileOutputStream, byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.write(bArr2);
                fileOutputStream.flush();
                return true;
            } catch (IOException unused) {
                LogUtil.b("CalendarFileUtils", "writeToFile: failed to write the file.");
            } catch (IllegalArgumentException unused2) {
                LogUtil.b("CalendarFileUtils", "IllegalArgumentException: failed to write the file .");
                return false;
            }
        }
        return false;
    }

    private static FileOutputStream a(String str, boolean z) throws IOException, IllegalArgumentException {
        File file = new File(str);
        if (file.exists() && !z && !file.delete()) {
            LogUtil.a("CalendarFileUtils", "writeToFile: file delete failed.");
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            LogUtil.a("CalendarFileUtils", "writeToFile: failed to create file's parent.");
        }
        return FileUtils.openOutputStream(file, z);
    }

    public static String e(File file) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                            sb.append("\n");
                        } else {
                            try {
                                break;
                            } catch (IOException unused) {
                                LogUtil.b("CalendarFileUtils", "parseSrt() finally IOException");
                            }
                        }
                    } catch (IOException unused2) {
                        bufferedReader2 = bufferedReader;
                        LogUtil.b("CalendarFileUtils", "parseSrt() IOException");
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused3) {
                                LogUtil.b("CalendarFileUtils", "parseSrt() finally IOException");
                            }
                        }
                        LogUtil.a("CalendarFileUtils", sb.toString());
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused4) {
                                LogUtil.b("CalendarFileUtils", "parseSrt() finally IOException");
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
            }
        } catch (IOException unused5) {
        }
        LogUtil.a("CalendarFileUtils", sb.toString());
        return sb.toString();
    }
}

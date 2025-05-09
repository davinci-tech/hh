package defpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public final class ix {

    /* renamed from: a, reason: collision with root package name */
    public static String f13640a = "";
    public static String b = "";
    public static String c = "";

    public static void b(List<String> list) {
        synchronized (ix.class) {
            if (!mq.e(c) && !mq.e(b)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(b);
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(", " + it.next());
                }
                stringBuffer.append("\n");
                try {
                    File file = new File(f13640a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(f13640a, c);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileWriter fileWriter = ((long) stringBuffer.length()) + file2.length() <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                    fileWriter.write(stringBuffer.toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception unused) {
                }
            }
        }
    }

    public static void d(Throwable th) {
        String str;
        synchronized (ix.class) {
            ArrayList arrayList = new ArrayList();
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                str = stringWriter.toString();
            } else {
                str = "";
            }
            arrayList.add(str);
            b(arrayList);
        }
    }

    public static void c(String str, String str2, String str3) {
        synchronized (ix.class) {
            f13640a = str;
            c = str2;
            b = str3;
        }
    }

    public static void a(String str) {
        synchronized (ix.class) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            b(arrayList);
        }
    }
}

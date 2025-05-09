package com.huawei.secure.android.common.detect;

import android.content.Context;
import android.os.Process;
import com.huawei.secure.android.common.detect.b.b;
import com.huawei.secure.android.common.detect.b.c;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes9.dex */
public class XposedDetect {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8577a = "XpsDetect";
    private static final String b = "64652e726f62762e616e64726f69642e78706f7365642e696e7374616c6c6572";
    private static final String c = "64652e726f62762e616e64726f69642e78706f7365642e58706f736564427269646765";
    private static final String d = "58706f7365644272696467652e6a6172";

    private static boolean a() {
        try {
            throw new SecurityException("Xps detect exception");
        } catch (SecurityException e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                try {
                    if (stackTraceElement.getClassName().equals(new String(com.huawei.secure.android.common.detect.b.a.b(c), "UTF-8")) && stackTraceElement.getMethodName().equals("main")) {
                        c.b(f8577a, "find Xps Bridge by exception class name");
                        return true;
                    }
                } catch (UnsupportedEncodingException unused) {
                    c.b(f8577a, "UnsupportedEncodingException");
                }
            }
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean b() {
        Throwable th;
        InputStreamReader inputStreamReader;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader2;
        String readLine;
        InputStreamReader inputStreamReader3 = null;
        try {
            String str = "/proc/" + Process.myPid() + "/maps";
            String str2 = new String(com.huawei.secure.android.common.detect.b.a.b(d), "UTF-8");
            fileInputStream = new FileInputStream(str);
            try {
                inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
                try {
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    do {
                        try {
                            readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                b.a((Reader) bufferedReader);
                                b.a((Reader) inputStreamReader);
                                b.a((InputStream) fileInputStream);
                                return false;
                            }
                        } catch (IOException e) {
                            inputStreamReader3 = bufferedReader;
                            e = e;
                            inputStreamReader2 = inputStreamReader3;
                            inputStreamReader3 = inputStreamReader;
                            try {
                                c.b(f8577a, "Xps Jar Check " + e.getMessage());
                                b.a((Reader) inputStreamReader2);
                                b.a((Reader) inputStreamReader3);
                                b.a((InputStream) fileInputStream);
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                InputStreamReader inputStreamReader4 = inputStreamReader2;
                                inputStreamReader = inputStreamReader3;
                                inputStreamReader3 = inputStreamReader4;
                                b.a((Reader) inputStreamReader3);
                                b.a((Reader) inputStreamReader);
                                b.a((InputStream) fileInputStream);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            inputStreamReader3 = bufferedReader;
                            b.a((Reader) inputStreamReader3);
                            b.a((Reader) inputStreamReader);
                            b.a((InputStream) fileInputStream);
                            throw th;
                        }
                    } while (!readLine.contains(str2));
                    c.b(f8577a, "Xps JAR found: ");
                    b.a((Reader) bufferedReader);
                    b.a((Reader) inputStreamReader);
                    b.a((InputStream) fileInputStream);
                    return true;
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (IOException e3) {
                e = e3;
                inputStreamReader2 = null;
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader = null;
            }
        } catch (IOException e4) {
            e = e4;
            inputStreamReader2 = null;
            fileInputStream = null;
        } catch (Throwable th6) {
            th = th6;
            inputStreamReader = null;
            fileInputStream = null;
        }
    }

    public static boolean isXposedHook(Context context) {
        return a() && b();
    }
}

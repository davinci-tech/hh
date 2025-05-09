package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/* loaded from: classes8.dex */
public class nc {

    /* renamed from: a, reason: collision with root package name */
    public static Context f15242a = null;
    private static boolean b = true;

    public static class b {
        public static void c(String str, String str2) {
            Log.w(str, str2);
        }

        public static void a(String str, String str2) {
            Log.i(str, str2);
        }

        public static void b(String str, String str2) {
            Log.e(str, str2);
        }

        public static void e(String str, String str2) {
            if (nc.b) {
                Log.d(str, str2);
            }
        }
    }

    public static void d(String str, String str2) {
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream;
        if (b) {
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    Context context = f15242a;
                    if (context != null && context.getCacheDir() != null) {
                        File file = new File(f15242a.getCacheDir().getAbsolutePath() + "/" + str);
                        StringBuilder sb = new StringBuilder("writeFile file path:");
                        sb.append(file.getAbsolutePath());
                        b.e("Util", sb.toString());
                        if (file.exists()) {
                            file.delete();
                        }
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            fileOutputStream.write(str2.getBytes());
                            fileOutputStream2 = fileOutputStream;
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream2 = fileOutputStream;
                            b.b("Util", e.toString());
                            fileOutputStream2.close();
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                fileOutputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            } catch (Exception e4) {
                e = e4;
            }
            try {
                fileOutputStream2.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
    }

    public static String e(File file) {
        BufferedReader bufferedReader;
        if (file == null || !file.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (Exception e) {
                            e = e;
                            bufferedReader2 = bufferedReader;
                            e.printStackTrace();
                            bufferedReader2.close();
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return sb.toString();
    }
}

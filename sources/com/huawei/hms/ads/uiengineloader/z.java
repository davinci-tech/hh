package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public class z {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4401a = "z";
    private static final String b = "lib";
    private static final String c = "!";
    private static final String d = "armeabi-v7a";
    private static final String e = "armeabi";
    private static final Pattern f = Pattern.compile("lib/([^/]+)/(.*\\.so)$");

    private static ArrayList<String> b(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, a(context) ? Build.SUPPORTED_64_BIT_ABIS : Build.SUPPORTED_32_BIT_ABIS);
        return arrayList;
    }

    public static String b(Context context, String str) {
        Iterator<String> it = b(context).iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (a(new File(str), next)) {
                af.b(f4401a, "use the preferred abi:".concat(String.valueOf(next)));
                return str + c + File.separator + b + File.separator + next;
            }
        }
        af.c(f4401a, "cannot get a valid native path, return null.");
        return null;
    }

    private static boolean a(File file, String str) {
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            zipFile = zipFile2;
        }
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE)) {
                    af.c(f4401a, "Unsafe zip name!");
                    aj.a(zipFile);
                    return false;
                }
                Matcher matcher = f.matcher(name);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    if (TextUtils.equals(str, group)) {
                        af.b(f4401a, "abiName:" + group + " matched.");
                        aj.a(zipFile);
                        return true;
                    }
                }
            }
            aj.a(zipFile);
        } catch (Exception e3) {
            e = e3;
            zipFile2 = zipFile;
            af.c(f4401a, "isApkContainPrefAbi exception:" + e.getClass().getSimpleName());
            aj.a(zipFile2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            aj.a(zipFile);
            throw th;
        }
        return false;
    }

    private static boolean a(Context context) {
        if (context == null) {
            af.d(f4401a, "Null context, please check it.");
            return false;
        }
        if (context.getApplicationContext() != null) {
            context.getApplicationContext();
        }
        return Process.is64Bit();
    }

    public static String a(Context context, String str) {
        Iterator<String> it = b(context).iterator();
        while (it.hasNext()) {
            String next = it.next();
            String str2 = str.substring(0, str.lastIndexOf(File.separator)) + File.separator + next;
            if (new File(str2).exists()) {
                af.b(f4401a, "The so has been unzipped, abi:".concat(String.valueOf(next)));
                return str2;
            }
        }
        return b(context, str);
    }
}

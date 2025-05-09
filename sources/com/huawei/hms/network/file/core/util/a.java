package com.huawei.hms.network.file.core.util;

import android.app.ActivityManager;
import android.content.Context;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes4.dex */
public class a {
    public static long a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            try {
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo.availMem;
            } catch (Exception e) {
                FLogger.w("CpuTool", "getAvailableRam exception:", e);
            }
        }
        return 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    public static int a() {
        Closeable closeable;
        IOException e;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader2 = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"), "UTF-8");
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } catch (FileNotFoundException unused) {
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = 0;
            }
            try {
                String readLine = bufferedReader.readLine();
                r1 = Utils.isBlank(readLine) ? 0 : Integer.parseInt(readLine.trim());
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader);
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) bufferedReader);
            } catch (FileNotFoundException unused2) {
                inputStreamReader2 = bufferedReader;
                FLogger.w("CpuTool", "getCurCpuFreq exception fileNotFoundException", new Object[0]);
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader);
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader2);
                return r1;
            } catch (IOException e3) {
                e = e3;
                inputStreamReader2 = bufferedReader;
                FLogger.w("CpuTool", "getCurCpuFreq exception", e);
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader);
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader2);
                return r1;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader2 = inputStreamReader;
                closeable = bufferedReader;
                com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader2);
                com.huawei.hms.network.file.a.k.b.f.a(closeable);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            inputStreamReader = null;
        } catch (IOException e4) {
            e = e4;
            inputStreamReader = null;
        } catch (Throwable th3) {
            th = th3;
            closeable = null;
            com.huawei.hms.network.file.a.k.b.f.a((Closeable) inputStreamReader2);
            com.huawei.hms.network.file.a.k.b.f.a(closeable);
            throw th;
        }
        return r1;
    }
}

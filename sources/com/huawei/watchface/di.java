package com.huawei.watchface;

import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import com.huawei.watchface.cx;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.utils.HwLog;
import defpackage.ndd;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class di {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10985a = "com.huawei.watchface.di";
    private static volatile di c;
    private final ConcurrentHashMap<String, MemoryFile> b = new ConcurrentHashMap<>(32);

    private di() {
    }

    public static di a() {
        if (c == null) {
            synchronized (cx.class) {
                if (c == null) {
                    c = new di();
                }
            }
        }
        return c;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final ParcelFileDescriptor f10986a;
        private final int b;

        public a(ParcelFileDescriptor parcelFileDescriptor, int i) {
            this.f10986a = parcelFileDescriptor;
            this.b = i;
        }

        public int a() {
            return this.b;
        }

        public ParcelFileDescriptor b() {
            return this.f10986a;
        }
    }

    public ParcelFileDescriptor a(byte[] bArr, String str) {
        String str2 = f10985a;
        FlavorConfig.safeHwLog(str2, "createMemoryFile name: " + str);
        ParcelFileDescriptor parcelFileDescriptor = null;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        try {
            MemoryFile memoryFile = new MemoryFile(str, length);
            HwLog.i(str2, "createMemoryFile create object length:" + length);
            this.b.put(str, memoryFile);
            try {
                OutputStream outputStream = memoryFile.getOutputStream();
                try {
                    outputStream.write(bArr);
                    HwLog.i(str2, "createMemoryFile write object");
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    try {
                        parcelFileDescriptor = a(memoryFile);
                        StringBuilder sb = new StringBuilder("createMemoryFile parcelFileDescriptor is null: ");
                        sb.append(parcelFileDescriptor == null);
                        HwLog.i(str2, sb.toString());
                    } catch (Exception e) {
                        HwLog.e(f10985a, HwLog.printException(e));
                        b(str);
                    }
                    return parcelFileDescriptor;
                } finally {
                }
            } catch (IOException e2) {
                HwLog.e(f10985a, HwLog.printException((Exception) e2));
                b(str);
                return null;
            }
        } catch (IOException e3) {
            HwLog.e(f10985a, HwLog.printException((Exception) e3));
            return null;
        }
    }

    private static ParcelFileDescriptor a(MemoryFile memoryFile) throws Exception {
        if (memoryFile == null) {
            throw new IllegalArgumentException("memoryFile 不能为空");
        }
        return ParcelFileDescriptor.dup((FileDescriptor) ndd.c(MemoryFile.class, memoryFile, "getFileDescriptor", new Object[0]));
    }

    public a a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("name 不能为空");
        }
        FlavorConfig.safeHwLog(f10985a, "get name: " + str);
        MemoryFile memoryFile = this.b.get(str);
        if (memoryFile == null) {
            return null;
        }
        try {
            return new a(a(memoryFile), memoryFile.length());
        } catch (Exception e) {
            HwLog.e(f10985a, HwLog.printException(e));
            return null;
        }
    }

    public void b(String str) {
        FlavorConfig.safeHwLog(f10985a, "closeMemoryFile name: " + str);
        MemoryFile remove = this.b.remove(str);
        if (remove != null) {
            remove.close();
        }
    }

    public boolean c(String str) {
        FlavorConfig.safeHwLog(f10985a, "exists name: " + str);
        cx.a a2 = cx.a.a(str);
        List<String> d = d(a2.a());
        for (int i = 0; i < d.size(); i++) {
            String str2 = d.get(i) + "_" + a2.b();
            String str3 = f10985a;
            HwLog.i(str3, "exists tmpUUID: " + str2);
            if (this.b.containsKey(str2)) {
                HwLog.i(str3, "exists matched id: " + str2);
                return true;
            }
        }
        return false;
    }

    private static List<String> d(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(WatchResourcesInfo.markUpHiTopId(str));
        arrayList.add(d.a().g(str).trim());
        return arrayList;
    }
}

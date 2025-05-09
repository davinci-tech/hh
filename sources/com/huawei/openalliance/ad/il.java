package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class il implements ia {

    /* renamed from: a, reason: collision with root package name */
    private final hz f6946a;
    private final dn b;

    @Override // com.huawei.openalliance.ad.ia
    public void b(String str) {
        File file = new File(this.f6946a.a(), c(str));
        if (file.exists()) {
            if (file.delete()) {
                this.b.a(file.getName(), false, null);
            } else {
                ho.d("DiskManager", "delete damaged cache file failed.");
            }
            ho.b("DiskManager", "successful delete damaged cache file");
        }
    }

    public long b() {
        File a2 = this.f6946a.a();
        long j = 0;
        if (a2 == null) {
            return 0L;
        }
        File[] listFiles = a2.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    j += file.length();
                }
            }
        }
        return j;
    }

    public void a(File file) {
        Cif.a(file);
        this.b.a(file.getName(), System.currentTimeMillis());
    }

    @Override // com.huawei.openalliance.ad.ia
    public void a(ir irVar) {
        File file = (this.f6946a == null || irVar == null) ? null : new File(this.f6946a.a(), irVar.a());
        if (file == null || !file.exists()) {
            return;
        }
        if (file.delete()) {
            ho.b("DiskManager", "Successfully deleted file that failed to cache");
        } else {
            ho.d("DiskManager", "Failed to delete temporary file");
        }
    }

    @Override // com.huawei.openalliance.ad.ia
    public void a(long j, ir irVar) {
        long b = b();
        long c = this.f6946a.c();
        if (b >= c) {
            ho.b("DiskManager", "clear native cache, used: %s, max: %s", Long.valueOf(b), Long.valueOf(c));
            File file = new File(this.f6946a.a(), irVar.a());
            ArrayList arrayList = new ArrayList();
            arrayList.add(file);
            com.huawei.openalliance.ad.utils.ae.a(this.f6946a.a(), j * 3, arrayList);
        }
    }

    public void a() {
        if (this.f6946a.d() + 86400000 < com.huawei.openalliance.ad.utils.ao.c()) {
            ho.b("DiskManager", "clear old native cache.");
            this.f6946a.a(com.huawei.openalliance.ad.utils.ao.c());
            com.huawei.openalliance.ad.utils.ae.a(this.f6946a.b(), 604800000L);
        }
    }

    @Override // com.huawei.openalliance.ad.ia
    public String a(String str) {
        ho.a("DiskManager", "try to get cache file for " + com.huawei.openalliance.ad.utils.dl.a(str));
        File file = new File(this.f6946a.a(), c(str));
        if (!file.exists()) {
            ho.a("DiskManager", "The requested cache file for url %s does not exist", com.huawei.openalliance.ad.utils.dl.a(str));
            return "";
        }
        a(file);
        return Constants.PREFIX_FILE + com.huawei.openalliance.ad.utils.ae.h(file);
    }

    public File a(String str, ir irVar, String str2, String str3) {
        ho.b("DiskManager", "try to save cache file");
        try {
            irVar.b().close();
        } catch (IOException unused) {
            ho.d("DiskManager", "Failed to close the tempResource");
        }
        File file = new File(this.f6946a.a(), irVar.a());
        String c = c(str);
        File file2 = new File(this.f6946a.a(), c);
        if (file2.exists()) {
            ho.b("DiskManager", "Old cache file %s exists.", file2.getName());
            ho.a("DiskManager", "del temp file result: %s", Boolean.valueOf(file.delete()));
            return file2;
        }
        if (file.renameTo(file2)) {
            ho.a("DiskManager", "Cache file %s completed", file2.getName());
            this.b.a(c, a(c, str2, str3));
        } else {
            ho.c("DiskManager", "Failed to save the cached file %s", file2.getName());
        }
        return file2;
    }

    @Override // com.huawei.openalliance.ad.ia
    public File a(String str, ir irVar) {
        return a(str, irVar, null, null);
    }

    @Override // com.huawei.openalliance.ad.ia
    public ir a(String str, String str2) {
        File file = new File(this.f6946a.a(), b(str, str2));
        try {
            if (file.exists()) {
                ho.b("DiskManager", "temporary resource file is exists");
                return new ir(file.getName(), new FileOutputStream(file, true), file.length());
            }
            if (file.createNewFile()) {
                ho.b("DiskManager", "Successfully created temporary resource file");
                return new ir(file.getName(), new FileOutputStream(file), 0L);
            }
            ho.d("DiskManager", "Failed created new temporary resource file");
            return null;
        } catch (IOException unused) {
            ho.d("DiskManager", "Failed created temporary resource file");
            return null;
        }
    }

    private String c(String str) {
        return "cache_" + com.huawei.openalliance.ad.utils.cu.a(str);
    }

    private String b(String str, String str2) {
        return "temp_" + str2 + com.huawei.openalliance.ad.utils.cu.a(str);
    }

    private ContentResource a(String str, String str2, String str3) {
        ContentResource contentResource = new ContentResource();
        contentResource.a(3);
        contentResource.c(str2);
        contentResource.b(str3);
        contentResource.a(str);
        contentResource.c(1);
        contentResource.d(2);
        return contentResource;
    }

    public il(hz hzVar, dn dnVar) {
        this.f6946a = hzVar;
        this.b = dnVar;
    }
}

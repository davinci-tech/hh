package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class z {
    private long b;

    /* renamed from: a, reason: collision with root package name */
    private int f7753a = 0;
    private Comparator<File> c = new Comparator<File>() { // from class: com.huawei.openalliance.ad.utils.z.1
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            if (file.lastModified() < file2.lastModified()) {
                return -1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : 1;
        }
    };

    public void a(File file, long j, List<File> list) {
        if (file == null || !file.exists() || !file.isDirectory() || j <= 0) {
            return;
        }
        this.b = j;
        ho.b("DirCleaner", "cleanDir total: sizeToClean: %s", Long.valueOf(j));
        a(file, list);
    }

    private void a(File file, List<File> list) {
        int i = this.f7753a + 1;
        this.f7753a = i;
        if (i > 10) {
            ho.b("DirCleaner", "exceeds max depth");
            return;
        }
        if (file.isDirectory()) {
            if (ho.a()) {
                ho.a("DirCleaner", "clean dir: %s", dl.a(ae.h(file)));
            }
            File[] listFiles = file.listFiles();
            if (bg.a(listFiles)) {
                return;
            }
            List<File> asList = Arrays.asList(listFiles);
            Collections.sort(asList, this.c);
            for (File file2 : asList) {
                if (file2.isDirectory()) {
                    a(file2);
                } else {
                    if (ho.a()) {
                        ho.a("DirCleaner", "clean file: %s", dl.a(ae.h(file)));
                    }
                    if (bg.a(list) || !list.contains(file2)) {
                        this.b -= file2.length();
                        ae.a(file2);
                        if (this.b <= 0) {
                            return;
                        }
                    } else {
                        ho.b("DirCleaner", "white list file, skip clean");
                    }
                }
            }
        }
    }

    private void a(File file) {
        a(file, null);
    }
}

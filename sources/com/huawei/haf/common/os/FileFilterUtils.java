package com.huawei.haf.common.os;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
public final class FileFilterUtils {
    private FileFilterUtils() {
    }

    public static class FileNotEqualsCollectFilter implements FilenameFilter {
        private final String c;

        public FileNotEqualsCollectFilter(String str) {
            this.c = str;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return !str.equals(this.c);
        }
    }

    public static class FileContainCollectFilter implements FilenameFilter {

        /* renamed from: a, reason: collision with root package name */
        private final long f2095a;
        private final String d;
        private final long e;

        public FileContainCollectFilter(String str, long j) {
            this.d = str;
            this.f2095a = j > 0 ? System.currentTimeMillis() : 0L;
            this.e = j;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (!TextUtils.isEmpty(this.d) && !e(str, this.d)) {
                return false;
            }
            if (this.e <= 0) {
                return true;
            }
            File file2 = new File(file, str);
            return file2.isFile() && this.f2095a - file2.lastModified() >= this.e;
        }

        protected boolean e(String str, String str2) {
            return str.indexOf(str2) != -1;
        }
    }

    public static class FileExtensionCollectFilter extends FileContainCollectFilter {
        public FileExtensionCollectFilter(String str) {
            super(str, 0L);
        }

        public FileExtensionCollectFilter(String str, long j) {
            super(str, j);
        }

        @Override // com.huawei.haf.common.os.FileFilterUtils.FileContainCollectFilter
        protected boolean e(String str, String str2) {
            return str.endsWith(str2);
        }
    }

    public static class FilePrefixCollectFilter extends FileContainCollectFilter {
        public FilePrefixCollectFilter(String str) {
            super(str, 0L);
        }

        public FilePrefixCollectFilter(String str, long j) {
            super(str, j);
        }

        @Override // com.huawei.haf.common.os.FileFilterUtils.FileContainCollectFilter
        protected boolean e(String str, String str2) {
            return str.startsWith(str2);
        }
    }
}

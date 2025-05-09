package com.huawei.hms.network.embedded;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes9.dex */
public interface ea {

    /* renamed from: a, reason: collision with root package name */
    public static final ea f5234a = new a();

    void a(File file, File file2) throws IOException;

    boolean a(File file);

    void b(File file) throws IOException;

    zb c(File file) throws FileNotFoundException;

    long d(File file);

    yb e(File file) throws FileNotFoundException;

    void f(File file) throws IOException;

    yb g(File file) throws FileNotFoundException;

    public class a implements ea {
        @Override // com.huawei.hms.network.embedded.ea
        public yb g(File file) throws FileNotFoundException {
            try {
                return ob.a(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return ob.a(file);
            }
        }

        @Override // com.huawei.hms.network.embedded.ea
        public void f(File file) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    f(file2);
                }
                if (!file2.delete()) {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }

        @Override // com.huawei.hms.network.embedded.ea
        public yb e(File file) throws FileNotFoundException {
            try {
                return ob.b(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return ob.b(file);
            }
        }

        @Override // com.huawei.hms.network.embedded.ea
        public long d(File file) {
            return file.length();
        }

        @Override // com.huawei.hms.network.embedded.ea
        public zb c(File file) throws FileNotFoundException {
            return ob.c(file);
        }

        @Override // com.huawei.hms.network.embedded.ea
        public void b(File file) throws IOException {
            if (file.delete() || !file.exists()) {
                return;
            }
            throw new IOException("failed to delete " + file);
        }

        @Override // com.huawei.hms.network.embedded.ea
        public boolean a(File file) {
            return file.exists();
        }

        @Override // com.huawei.hms.network.embedded.ea
        public void a(File file, File file2) throws IOException {
            b(file2);
            if (file.renameTo(file2)) {
                return;
            }
            throw new IOException("failed to rename " + file + " to " + file2);
        }
    }
}

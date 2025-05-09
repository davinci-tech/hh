package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

/* loaded from: classes8.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final n f11367a = new n(101010256);
    private static final o b = new o(38651);

    static class a {

        /* renamed from: a, reason: collision with root package name */
        Properties f11368a;
        byte[] b;

        private a() {
            this.f11368a = new Properties();
        }

        void a(byte[] bArr) throws IOException {
            if (bArr == null) {
                return;
            }
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int length = b.b.a().length;
            byte[] bArr2 = new byte[length];
            wrap.get(bArr2);
            if (!b.b.equals(new o(bArr2))) {
                throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
            }
            if (bArr.length - length <= 2) {
                return;
            }
            byte[] bArr3 = new byte[2];
            wrap.get(bArr3);
            int b = new o(bArr3).b();
            if ((bArr.length - length) - 2 < b) {
                return;
            }
            byte[] bArr4 = new byte[b];
            wrap.get(bArr4);
            this.f11368a.load(new ByteArrayInputStream(bArr4));
            int length2 = ((bArr.length - length) - b) - 2;
            if (length2 > 0) {
                byte[] bArr5 = new byte[length2];
                this.b = bArr5;
                wrap.get(bArr5);
            }
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.f11368a + ", otherData=" + Arrays.toString(this.b) + "]";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a(File file, String str) throws IOException {
        RandomAccessFile randomAccessFile = null;
        Object[] objArr = 0;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                byte[] a2 = a(randomAccessFile2);
                if (a2 != null) {
                    a aVar = new a();
                    aVar.a(a2);
                    String property = aVar.f11368a.getProperty(str);
                    randomAccessFile2.close();
                    return property;
                }
                randomAccessFile2.close();
                return null;
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String a(File file) throws IOException {
        return a(file, "channelNo");
    }

    private static byte[] a(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] a2 = f11367a.a();
        int read = randomAccessFile.read();
        while (read != -1) {
            if (read != a2[0] || randomAccessFile.read() != a2[1] || randomAccessFile.read() != a2[2] || randomAccessFile.read() != a2[3]) {
                length--;
                randomAccessFile.seek(length);
                read = randomAccessFile.read();
            } else {
                randomAccessFile.seek(length + 20);
                byte[] bArr = new byte[2];
                randomAccessFile.readFully(bArr);
                int b2 = new o(bArr).b();
                if (b2 == 0) {
                    return null;
                }
                byte[] bArr2 = new byte[b2];
                randomAccessFile.read(bArr2);
                return bArr2;
            }
        }
        throw new ZipException("archive is not a ZIP archive");
    }
}

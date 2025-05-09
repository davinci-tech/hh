package com.huawei.ucd.helper.gles;

import android.content.Context;
import defpackage.njw;
import defpackage.njz;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes9.dex */
public class Obj3DBufferLoadAider {

    /* renamed from: a, reason: collision with root package name */
    private float[] f8742a;
    private float[] d;
    private float[] e;
    private ExecutorService b = Executors.newFixedThreadPool(3);
    private int c = 0;
    private int f = 0;
    private String j = "";

    public interface OnLoadListener {
        void onLoadFailed(String str);

        void onLoadOK(d dVar);
    }

    public void c(Context context, InputStream inputStream, InputStream inputStream2, InputStream inputStream3, OnLoadListener onLoadListener) {
        long currentTimeMillis;
        StringBuilder sb;
        long currentTimeMillis2 = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(inputStream3, 0, onLoadListener));
        arrayList.add(new c(inputStream2, 1, onLoadListener));
        arrayList.add(new c(inputStream, 2, onLoadListener));
        try {
            try {
                this.b.invokeAll(arrayList);
                njz.b(inputStream, inputStream2, inputStream3);
                currentTimeMillis = System.currentTimeMillis();
                sb = new StringBuilder();
            } catch (InterruptedException e) {
                e.printStackTrace();
                njw.c("Obj3DBufferLoadAider", njw.b() + " e=" + e.getMessage());
                e(-1, e.getMessage(), onLoadListener);
                onLoadListener.onLoadFailed(e.getMessage());
                njz.b(inputStream, inputStream2, inputStream3);
                currentTimeMillis = System.currentTimeMillis();
                sb = new StringBuilder();
            }
            sb.append(njw.b());
            sb.append(" Load Time=");
            sb.append(currentTimeMillis - currentTimeMillis2);
            njw.c("Obj3DBufferLoadAider", sb.toString());
        } catch (Throwable th) {
            njz.b(inputStream, inputStream2, inputStream3);
            njw.c("Obj3DBufferLoadAider", njw.b() + " Load Time=" + (System.currentTimeMillis() - currentTimeMillis2));
            throw th;
        }
    }

    public class c implements Callable<float[]> {
        private InputStream b;
        private int c;
        private OnLoadListener d;

        public c(InputStream inputStream, int i, OnLoadListener onLoadListener) {
            if (inputStream == null) {
                njw.c("Obj3DBufferLoadAider", " InputStream can not be null dataType=" + i);
                throw new RuntimeException(" InputStream can not be null dataType=" + i);
            }
            this.b = inputStream;
            this.c = i;
            this.d = onLoadListener;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public float[] call() throws Exception {
            byte[] d = d(this.b);
            int length = d.length;
            njw.c("Obj3DBufferLoadAider", njw.b() + " onLoadThread mDataType=" + this.c + " size=" + length);
            this.b.read(d, 0, length);
            ByteBuffer wrap = ByteBuffer.wrap(d);
            wrap.clear();
            float[] fArr = new float[wrap.capacity() / 4];
            wrap.asFloatBuffer().get(fArr);
            int i = this.c;
            if (i == 0) {
                Obj3DBufferLoadAider.this.d = fArr;
            } else if (i == 1) {
                Obj3DBufferLoadAider.this.e = fArr;
            } else if (i == 2) {
                Obj3DBufferLoadAider.this.f8742a = fArr;
            }
            Obj3DBufferLoadAider.this.e(1, "", this.d);
            return fArr;
        }

        private byte[] d(InputStream inputStream) throws IOException {
            byte[] bArr = new byte[4096];
            if (inputStream == null) {
                return bArr;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 != read) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str, OnLoadListener onLoadListener) {
        synchronized (this) {
            if (onLoadListener == null) {
                return;
            }
            this.f += i;
            this.c++;
            this.j += str;
            njw.c("Obj3DBufferLoadAider", njw.b() + " onLoadThreadFinishedCount=" + this.c + " onLoadThreadSucceedCount=" + this.f);
            int i2 = this.c;
            if (i2 == 3 && this.f == 3) {
                onLoadListener.onLoadOK(new d());
                this.f = 0;
                this.c = 0;
            } else if (i2 == 3 && this.f != 3) {
                onLoadListener.onLoadFailed(this.j);
                this.f = 0;
                this.c = 0;
                this.j = "";
            }
        }
    }

    public class d {
        public d() {
        }

        public float[] b() {
            return Obj3DBufferLoadAider.this.d;
        }

        public float[] c() {
            return Obj3DBufferLoadAider.this.e;
        }

        public float[] d() {
            return Obj3DBufferLoadAider.this.f8742a;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("LoadResult{VertexXYZ=null? ");
            sb.append(Obj3DBufferLoadAider.this.d == null);
            sb.append(", NormalVectorXYZ=null? ");
            sb.append(Obj3DBufferLoadAider.this.e == null);
            sb.append(", TextureVertexST=null? ");
            sb.append(Obj3DBufferLoadAider.this.f8742a == null);
            sb.append('}');
            return sb.toString();
        }
    }
}

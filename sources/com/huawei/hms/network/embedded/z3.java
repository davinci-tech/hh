package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.embedded.h1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpRetryException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* loaded from: classes9.dex */
public class z3 extends UploadDataProvider {
    public static final String f = "CronetUploadDataProvide";
    public static final int i = 22;

    /* renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<Object> f5590a = new LinkedBlockingQueue<>(22);
    public h1.d b;
    public h1.e c;
    public ByteBuffer d;
    public l2 e;
    public static final byte[] h = new byte[0];
    public static final String g = "cronet_upload_task";
    public static ExecutorService j = ExecutorsUtils.newCachedThreadPool(g);

    public void rewind(UploadDataSink uploadDataSink) throws IOException {
        Logger.w(f, "not support rewind");
        uploadDataSink.onRewindError(new HttpRetryException("Cannot rewind the RequestBody", -1));
    }

    public void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) throws IOException {
        int min = Math.min(this.f5590a.size(), (int) Math.floor(byteBuffer.capacity() / 16375.0d));
        Logger.d(f, "cronet try to read, times : " + min + " capacity : " + byteBuffer.capacity());
        int i2 = 1;
        do {
            try {
                Logger.d(f, "linkedBlockingQueue poll");
                Object poll = this.f5590a.poll(t(), TimeUnit.MILLISECONDS);
                if (h.equals(poll)) {
                    throw new IOException("An exception occurred when writing the request body.");
                }
                ByteBuffer wrap = ByteBuffer.wrap((byte[]) poll);
                this.d = wrap;
                byteBuffer.put(wrap);
                i2++;
            } catch (InterruptedException e) {
                Logger.w(f, "An Interrupted exception occurs when read interrupted in updaloadDataProvider.");
                throw new IOException("An exception occurs when read interrupted in updaloadDataProvider", e);
            } catch (RuntimeException e2) {
                Logger.w(f, "An Runtime exception occurs when read in updaloadDataProvider.", e2);
                throw new IOException("An exception occurs when read in updaloadDataProvider", e2);
            } catch (Exception e3) {
                Logger.w(f, "Exception occurs when read in updaloadDataProvider.", e3);
                throw new IOException("Exception occurs when read in updaloadDataProvider", e3);
            }
        } while (i2 <= min);
        this.e.updateLoopTimer(t());
        Logger.d(f, "uploadDataSink read success");
        uploadDataSink.onReadSucceeded(false);
    }

    public long getLength() throws IOException {
        if (this.c.contentLength() == 0) {
            Logger.w(f, "maybe the requestBody's contentLength be not override");
        }
        return this.c.contentLength();
    }

    private int t() {
        return this.b.getNetConfig().getWriteTimeout();
    }

    public class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            try {
                z3.this.c.writeTo(new y3(z3.this.f5590a));
            } catch (FileNotFoundException unused) {
                Logger.w(z3.f, "An IOException occurs during stream writing.");
                try {
                    z3.this.f5590a.put(z3.h);
                } catch (InterruptedException e) {
                    Logger.w(z3.f, "An IOException occurs during stream writing.", e);
                }
            } catch (IOException e2) {
                e = e2;
                Logger.w(z3.f, "An IOException occurs during stream writing.", e);
                z3.this.f5590a.put(z3.h);
            } catch (Exception e3) {
                e = e3;
                Logger.w(z3.f, "An IOException occurs during stream writing.", e);
                z3.this.f5590a.put(z3.h);
            }
        }

        public a() {
        }
    }

    public z3(h1.d dVar, l2 l2Var) {
        this.b = dVar;
        this.e = l2Var;
        this.c = dVar.getBody();
        j.execute(new a());
    }
}

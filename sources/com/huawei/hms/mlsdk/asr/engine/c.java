package com.huawei.hms.mlsdk.asr.engine;

import android.media.AudioRecord;
import android.os.Bundle;
import android.os.SystemClock;
import com.huawei.hms.mlsdk.asr.engine.AsrEngine;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private Lock f5074a;
    private Condition b;
    private List<InterfaceC0137c> c;
    private byte[] d;
    private final AtomicBoolean e;
    private final AtomicBoolean f;
    private final AtomicBoolean g;
    private AudioRecord h;
    private Thread i;

    /* loaded from: classes9.dex */
    class b implements Runnable {
        /* synthetic */ b(a aVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar;
            d dVar2;
            AtomicBoolean atomicBoolean;
            while (c.this.e.get()) {
                c.this.f5074a.lock();
                try {
                    try {
                    } catch (Exception e) {
                        SmartLogger.e("AsrRecorder", "InnerRecordRunnable Exception: " + e.getMessage());
                    }
                    if (c.this.h != null) {
                        int read = c.this.h.read(c.this.d, 0, c.this.d.length);
                        SmartLogger.d("AsrRecorder", "audioRecord read buffer");
                        if (read != -3 && read != -1 && read != -2) {
                            if (!c.this.f.get()) {
                                try {
                                    try {
                                        c.this.g.set(true);
                                        SmartLogger.d("AsrRecorder", "InnerRecordRunnable waiting");
                                        c.this.b.await();
                                        SmartLogger.d("AsrRecorder", "InnerRecordRunnable awake");
                                        atomicBoolean = c.this.g;
                                    } catch (Throwable th) {
                                        c.this.g.set(false);
                                        throw th;
                                    }
                                } catch (InterruptedException e2) {
                                    SmartLogger.e("AsrRecorder", "await failed" + e2.getMessage());
                                    atomicBoolean = c.this.g;
                                }
                                atomicBoolean.set(false);
                            }
                            for (InterfaceC0137c interfaceC0137c : c.this.c) {
                                byte[] bArr = c.this.d;
                                AsrEngine.c cVar = (AsrEngine.c) interfaceC0137c;
                                dVar = AsrEngine.this.mAsrVadDetector;
                                if (dVar != null) {
                                    dVar2 = AsrEngine.this.mAsrVadDetector;
                                    dVar2.a(bArr);
                                }
                            }
                        }
                    } else {
                        SmartLogger.e("AsrRecorder", "AudioRecord is null.");
                        c.this.f5074a.unlock();
                    }
                } finally {
                    c.this.f5074a.unlock();
                }
            }
            SmartLogger.i("AsrRecorder", "InnerRecordRunnable is over");
        }
    }

    /* renamed from: com.huawei.hms.mlsdk.asr.engine.c$c, reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0137c {
    }

    public c() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f5074a = reentrantLock;
        this.b = reentrantLock.newCondition();
        this.c = new ArrayList();
        this.e = new AtomicBoolean(false);
        this.f = new AtomicBoolean(false);
        this.g = new AtomicBoolean(false);
        this.f5074a.lock();
        try {
            try {
                int minBufferSize = AudioRecord.getMinBufferSize(16000, 16, 2);
                AsrConstants.bufferSize = minBufferSize;
                this.d = new byte[minBufferSize];
                AudioRecord audioRecord = new AudioRecord(1, 16000, 16, 2, minBufferSize);
                this.h = audioRecord;
                if (audioRecord.getState() != 1) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("subErrorCode", 1001);
                    Iterator<InterfaceC0137c> it = this.c.iterator();
                    while (it.hasNext()) {
                        ((AsrEngine.c) it.next()).a(new AsrError(40, "Failed to init audio recorder", bundle));
                    }
                } else {
                    this.h.startRecording();
                }
            } catch (Exception e) {
                SmartLogger.e("AsrRecorder", "initAudioRecorder Exception: " + e.getMessage());
            }
        } finally {
            this.f5074a.unlock();
        }
    }

    private void c() {
        this.f5074a.lock();
        try {
            this.b.signal();
        } finally {
            this.f5074a.unlock();
        }
    }

    public void a(InterfaceC0137c interfaceC0137c) {
        this.c.add(interfaceC0137c);
    }

    public void b() {
        if (this.i == null) {
            a(true);
            this.e.set(true);
            Thread thread = new Thread(new b(null), "InnerRecordRunnable");
            this.i = thread;
            thread.start();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Iterator<InterfaceC0137c> it = this.c.iterator();
        while (it.hasNext()) {
            AsrEngine.c cVar = (AsrEngine.c) it.next();
            AsrEngine.this.recordStartTime = Long.valueOf(elapsedRealtime);
            AsrEngine.this.posterExecutor.execute(new AsrEngine.j(AsrEngine.this.callback, 1));
            SmartLogger.d("AsrEngine", "onStart(long time)");
        }
    }

    public void a(boolean z) {
        this.f5074a.lock();
        try {
            this.f.set(z);
            if (this.g.get()) {
                c();
            }
        } finally {
            this.f5074a.unlock();
        }
    }

    public void a() {
        this.e.set(false);
        a(false);
        c();
        try {
            AudioRecord audioRecord = this.h;
            if (audioRecord != null) {
                audioRecord.stop();
            }
            AudioRecord audioRecord2 = this.h;
            if (audioRecord2 != null) {
                audioRecord2.release();
            }
            this.h = null;
        } catch (IllegalStateException e) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("IllegalStateException:");
            a2.append(e.getMessage());
            SmartLogger.e("AsrRecorder", a2.toString());
        }
    }
}

package com.huawei.hms.mlsdk.asr.engine;

import android.os.Bundle;
import android.os.SystemClock;
import com.huawei.hms.ml.grs.GrsListener;
import com.huawei.hms.ml.grs.GrsRegionUtils;
import com.huawei.hms.ml.grs.GrsUtils;
import com.huawei.hms.mlsdk.asr.engine.AsrConstants;
import com.huawei.hms.mlsdk.asr.engine.cloud.vo.f;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.mlsdk.common.MLApplication;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.jsoperation.JsUtil;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.SSLException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private Lock f5068a;
    private Condition b;
    private AsrEngine c;
    private List<e> d;
    private c e;
    private com.huawei.hms.mlsdk.asr.engine.utils.a f;
    private Thread g;
    private final AtomicBoolean h;
    private final AtomicBoolean i;
    private final AtomicBoolean j;
    private long k;
    private volatile boolean l;
    private com.huawei.hms.mlsdk.asr.engine.utils.e m;
    private volatile boolean n;
    private volatile boolean o;
    private long p;
    private long q;
    private volatile boolean r;
    private boolean s;
    private volatile boolean t;
    private String u;
    private long v;
    private AtomicInteger w;
    private AtomicBoolean x;

    /* renamed from: com.huawei.hms.mlsdk.asr.engine.b$b, reason: collision with other inner class name */
    class RunnableC0136b implements Runnable {
        RunnableC0136b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Iterator it;
            String str = "after send finish later";
            boolean z = false;
            try {
                try {
                    Thread.sleep(b.this.q);
                    b.this.a(false);
                    b.this.h.set(false);
                    SmartLogger.i("AsrProcessor", "after send finish later");
                    it = b.this.d.iterator();
                } catch (InterruptedException e) {
                    SmartLogger.e("AsrProcessor", e.getMessage());
                    b.this.a(false);
                    b.this.h.set(false);
                    SmartLogger.i("AsrProcessor", "after send finish later");
                    Iterator it2 = b.this.d.iterator();
                    while (true) {
                        z = it2.hasNext();
                        str = it2;
                        if (z) {
                            e eVar = (e) it2.next();
                            if (!b.this.o) {
                                b.this.o = true;
                                StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("after send finish ");
                                a2.append(b.this.q);
                                a2.append(" ms later but don't receive response, call onResult");
                                SmartLogger.i("AsrProcessor", a2.toString());
                                eVar.onResult(new AsrResult(8));
                            }
                        }
                    }
                }
                while (true) {
                    z = it.hasNext();
                    str = it;
                    if (z) {
                        e eVar2 = (e) it.next();
                        if (!b.this.o) {
                            b.this.o = true;
                            StringBuilder a3 = com.huawei.hms.mlsdk.asr.o.a.a("after send finish ");
                            a3.append(b.this.q);
                            a3.append(" ms later but don't receive response, call onResult");
                            SmartLogger.i("AsrProcessor", a3.toString());
                            eVar2.onResult(new AsrResult(8));
                        }
                    }
                    return;
                }
            } catch (Throwable th) {
                b.this.a(z);
                b.this.h.set(z);
                SmartLogger.i("AsrProcessor", str);
                for (e eVar3 : b.this.d) {
                    if (!b.this.o) {
                        b.this.o = true;
                        StringBuilder a4 = com.huawei.hms.mlsdk.asr.o.a.a("after send finish ");
                        a4.append(b.this.q);
                        a4.append(" ms later but don't receive response, call onResult");
                        SmartLogger.i("AsrProcessor", a4.toString());
                        eVar3.onResult(new AsrResult(8));
                    }
                }
                throw th;
            }
        }
    }

    /* loaded from: classes9.dex */
    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f5073a = true;

        /* synthetic */ d(a aVar) {
        }

        @Override // java.lang.Runnable
        public void run() {
            AtomicBoolean atomicBoolean;
            AtomicBoolean atomicBoolean2;
            while (true) {
                if (!b.this.h.get()) {
                    break;
                }
                b.this.f5068a.lock();
                try {
                    if (!b.this.i.get()) {
                        try {
                            try {
                                b.this.j.set(true);
                                SmartLogger.d("AsrProcessor", "InnerProcessRunnable begin waiting because of isActive is false");
                                b.this.b.await();
                                SmartLogger.d("AsrProcessor", "InnerProcessRunnable awake from isActive is false");
                                atomicBoolean = b.this.j;
                            } catch (InterruptedException e) {
                                SmartLogger.e("AsrProcessor", "await failed" + e.getMessage());
                                atomicBoolean = b.this.j;
                            }
                            atomicBoolean.set(false);
                        } finally {
                        }
                    }
                    b.this.e.b();
                    if (b.this.e.a()) {
                        SmartLogger.i("AsrProcessor", "one minutes later after receiving start, and send finish");
                        b.this.b();
                        break;
                    }
                    byte[] b = b.this.f.b();
                    while (b.this.h.get() && b != null && b.length == 0) {
                        try {
                            try {
                                b.this.j.set(true);
                                SmartLogger.d("AsrProcessor", "InnerProcessRunnable waiting: isRunning.get(): " + b.this.h.get() + "block.length: " + b.length);
                                b.this.b.await();
                                SmartLogger.d("AsrProcessor", "InnerProcessRunnable awake from isRunning.get()");
                                b = b.this.f.b();
                                atomicBoolean2 = b.this.j;
                            } catch (InterruptedException e2) {
                                SmartLogger.e("AsrProcessor", "await failed" + e2.getMessage());
                                atomicBoolean2 = b.this.j;
                            }
                            atomicBoolean2.set(false);
                        } finally {
                        }
                    }
                    if (b.this.h.get() && b.this.i.get()) {
                        byte[] a2 = new com.huawei.hms.mlsdk.asr.engine.cloud.vo.b(JsUtil.ServiceType.DATA, b.s(b.this), b).a();
                        SmartLogger.i("AsrProcessor", "send data in thread, messageSeqNo: " + b.this.k);
                        if (b.this.m != null) {
                            b.this.m.a(a2);
                        }
                        if (this.f5073a) {
                            b.this.p = System.currentTimeMillis();
                            SmartLogger.i("AsrProcessor", "mTimeOfSendFirstData is " + b.this.p);
                            this.f5073a = false;
                            if (b.this.h()) {
                                Iterator it = b.this.d.iterator();
                                while (it.hasNext()) {
                                    ((e) it.next()).onState(9);
                                }
                            }
                        }
                        int i = (int) b.this.k;
                        int i2 = (int) b.this.k;
                        Bundle bundle = new Bundle();
                        bundle.putInt("voiceStreamTime", i * 200);
                        bundle.putInt("uploadVoiceSize", i2 * 6400);
                        bundle.putLong("sendFinalDataTime", System.currentTimeMillis());
                        com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 5, bundle);
                        com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 6, bundle);
                        com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 11, bundle);
                        b.this.v = System.currentTimeMillis();
                    }
                    b.this.f5068a.unlock();
                } finally {
                    b.this.f5068a.unlock();
                }
            }
            SmartLogger.i("AsrProcessor", "InnerProcessRunnable is over");
        }
    }

    public interface e {
        void a(long j);

        void a(AsrError asrError);

        void b(long j);

        void onResult(AsrResult asrResult);

        void onState(int i);
    }

    public b(AsrEngine asrEngine) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f5068a = reentrantLock;
        this.b = reentrantLock.newCondition();
        this.d = new ArrayList();
        this.h = new AtomicBoolean(false);
        this.i = new AtomicBoolean(false);
        this.j = new AtomicBoolean(false);
        this.k = 0L;
        this.l = false;
        this.m = null;
        this.n = true;
        this.o = false;
        this.p = 0L;
        this.q = 0L;
        this.r = true;
        this.s = false;
        this.t = false;
        this.u = null;
        this.v = 0L;
        this.w = new AtomicInteger(-1);
        this.x = new AtomicBoolean(false);
        this.c = asrEngine;
        this.f = new com.huawei.hms.mlsdk.asr.engine.utils.a(asrEngine.getEngineConfig().getRecognizeDuration() * 32);
        if (g()) {
            Iterator<e> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().a(new AsrError(7, "No network", null));
            }
            this.n = false;
        }
    }

    static /* synthetic */ long s(b bVar) {
        long j = bVar.k + 1;
        bVar.k = j;
        return j;
    }

    /* loaded from: classes9.dex */
    class c {

        /* renamed from: a, reason: collision with root package name */
        private Long f5072a;
        private int b = 0;

        /* synthetic */ c(a aVar) {
        }

        void a(Long l) {
            this.f5072a = l;
            this.b = 0;
        }

        void b() {
            this.b = (int) (Long.valueOf(SystemClock.elapsedRealtime()).longValue() - this.f5072a.longValue());
        }

        boolean a() {
            return this.b > b.this.c.getEngineConfig().getMaxAudioDuration();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        int a2 = com.huawei.hms.mlsdk.asr.engine.utils.d.a();
        return a2 == 1 || a2 == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        return com.huawei.hms.mlsdk.asr.engine.utils.d.a() == 3;
    }

    private void i() {
        this.f5068a.lock();
        try {
            this.b.signal();
        } finally {
            this.f5068a.unlock();
        }
    }

    void e() {
        synchronized (this) {
            if (!this.l) {
                this.l = true;
                a(false);
                this.h.set(false);
                com.huawei.hms.mlsdk.asr.engine.cloud.vo.a aVar = new com.huawei.hms.mlsdk.asr.engine.cloud.vo.a();
                aVar.a("FINISH");
                StringBuilder sb = new StringBuilder(23);
                sb.append("{ \"command\":\"");
                sb.append(aVar.a());
                sb.append("\"}");
                String sb2 = sb.toString();
                com.huawei.hms.mlsdk.asr.engine.utils.e eVar = this.m;
                if (eVar != null) {
                    eVar.a(sb2);
                }
            }
        }
    }

    public void f() {
        synchronized (this) {
            if (!this.n) {
                Iterator<e> it = this.d.iterator();
                while (it.hasNext()) {
                    it.next().a(new AsrError(7, "No network", null));
                }
                return;
            }
            GrsUtils.setGrsListener(new a());
            String grsCountryCode = GrsRegionUtils.getGrsCountryCode();
            SmartLogger.i("AsrProcessor", "Grs-CountryCode:" + grsCountryCode);
            if (grsCountryCode != null) {
                GrsUtils.getAsrVisionSearchUrls(GrsUtils.initGrsVisionSearchClientWithCountry(MLApplication.getInstance().getAppContext(), grsCountryCode));
            } else {
                GrsUtils.getAsrUrls(MLApplication.getInstance().getAppContext(), false);
            }
        }
    }

    public boolean d() {
        return this.n;
    }

    public void b() {
        synchronized (this) {
            if (!this.l) {
                this.l = true;
                a(false);
                this.h.set(false);
                byte[] b = this.f.b();
                while (b != null && b.length > 0) {
                    long j = this.k + 1;
                    this.k = j;
                    byte[] a2 = new com.huawei.hms.mlsdk.asr.engine.cloud.vo.b(JsUtil.ServiceType.DATA, j, b).a();
                    SmartLogger.i("AsrProcessor", "send data in finish");
                    com.huawei.hms.mlsdk.asr.engine.utils.e eVar = this.m;
                    if (eVar != null) {
                        eVar.a(a2);
                    }
                    b = this.f.b();
                }
                com.huawei.hms.mlsdk.asr.engine.cloud.vo.a aVar = new com.huawei.hms.mlsdk.asr.engine.cloud.vo.a();
                aVar.a("FINISH");
                StringBuilder sb = new StringBuilder(23);
                sb.append("{ \"command\":\"");
                sb.append(aVar.a());
                sb.append("\"}");
                String sb2 = sb.toString();
                if (this.m != null) {
                    SmartLogger.i("AsrProcessor", "send finish");
                    this.m.a(sb2);
                }
                if (this.q < 2000) {
                    this.q = 2000L;
                }
                new Thread(new RunnableC0136b(), "sendFinishThread").start();
            }
        }
    }

    public long c() {
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, Integer num) {
        synchronized (this) {
            SmartLogger.i("AsrProcessor", "onAbnormal");
            this.l = true;
            a(false);
            this.h.set(false);
            if (num != null && num.intValue() == 3015) {
                for (e eVar : this.d) {
                    SmartLogger.i("AsrProcessor", "one minute timeout");
                    eVar.onResult(new AsrResult(8));
                }
                return;
            }
            Bundle bundle = new Bundle();
            if (num != null) {
                bundle.putInt("subErrorCode", num.intValue());
            }
            Iterator<e> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().a(new AsrError(i, str, bundle));
            }
        }
    }

    public void a(e eVar) {
        this.d.add(eVar);
    }

    public void a(byte[] bArr) {
        this.f.a(bArr);
        if (!this.j.get() || this.f.c() <= 0) {
            return;
        }
        SmartLogger.d("AsrProcessor", "setInput(byte[] input):notify InnerProcessRunnable");
        i();
    }

    public void a(byte[] bArr, boolean z) {
        this.f.a(bArr);
        if (this.j.get() && this.f.c() > 0) {
            SmartLogger.d("AsrProcessor", "setInputForWriteAudio(byte[] input):notify InnerProcessRunnable");
            i();
        }
        if (z) {
            com.huawei.hms.mlsdk.asr.engine.cloud.vo.a aVar = new com.huawei.hms.mlsdk.asr.engine.cloud.vo.a();
            aVar.a("FINISH");
            StringBuilder sb = new StringBuilder(23);
            sb.append("{ \"command\":\"");
            sb.append(aVar.a());
            sb.append("\"}");
            String sb2 = sb.toString();
            com.huawei.hms.mlsdk.asr.engine.utils.e eVar = this.m;
            if (eVar != null) {
                eVar.a(sb2);
            }
        }
    }

    public void a(boolean z) {
        this.f5068a.lock();
        try {
            this.i.set(z);
            if (this.j.get()) {
                i();
            }
        } finally {
            this.f5068a.unlock();
        }
    }

    public void a() {
        synchronized (this) {
            this.t = true;
            com.huawei.hms.mlsdk.asr.engine.utils.a aVar = this.f;
            if (aVar != null) {
                aVar.a();
            }
            a(false);
            this.h.set(false);
            i();
            com.huawei.hms.mlsdk.asr.engine.utils.e eVar = this.m;
            if (eVar != null) {
                eVar.e();
            }
        }
    }

    public void a(String str) {
        this.u = str;
    }

    static /* synthetic */ boolean a(b bVar, int i) {
        com.huawei.hms.mlsdk.asr.engine.utils.e eVar = bVar.m;
        return (eVar == null || eVar.b() || (3018 != i && 3017 != i && 3016 != i)) ? false : true;
    }

    class a implements GrsListener {
        a() {
        }

        @Override // com.huawei.hms.ml.grs.GrsListener
        public void onAsynGrsUrls(List<String> list) {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String next = it.next();
                SmartLogger.i("AsrProcessor", "url:" + next, true);
                if (!next.startsWith("ws://") && !next.startsWith("wss://")) {
                    next = com.huawei.hms.mlsdk.asr.o.a.a("wss://", next);
                }
                arrayList.add(b.this.c.isRecognizerLong() ? com.huawei.hms.mlsdk.asr.o.a.a(next, AsrConstants.AsrGrs.ASR_LONG_URL) : com.huawei.hms.mlsdk.asr.o.a.a(next, AsrConstants.AsrGrs.ASR_SHORT_URL));
            }
            if (b.this.m == null) {
                SmartLogger.i("AsrProcessor", "create WebSocketClient");
                b bVar = b.this;
                bVar.m = new com.huawei.hms.mlsdk.asr.engine.utils.e(bVar.c.isRecognizerLong(), arrayList);
            }
            b.this.m.a(new C0135a(System.currentTimeMillis()));
            if (b.this.m.a()) {
                return;
            }
            SmartLogger.i("AsrProcessor", "begin to start connect ");
            b.this.m.d();
        }

        /* renamed from: com.huawei.hms.mlsdk.asr.engine.b$a$a, reason: collision with other inner class name */
        class C0135a implements com.huawei.hms.mlsdk.asr.o.e {

            /* renamed from: a, reason: collision with root package name */
            long f5070a;
            final /* synthetic */ long b;

            C0135a(long j) {
                this.b = j;
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void a(Response response) {
                SmartLogger.i("AsrProcessor", "onOpen");
                b.this.w.set(-1);
                if (b.this.c.isRecognizerLong() && b.this.x.get()) {
                    Iterator it = b.this.d.iterator();
                    while (it.hasNext()) {
                        ((e) it.next()).onState(43);
                    }
                }
                boolean z = true;
                if (!b.this.x.get()) {
                    b.this.x.set(true);
                }
                f fVar = new f();
                fVar.a("pcm");
                fVar.b(b.this.c.getEngineConfig().getLanguage());
                fVar.b((Integer) 16000);
                fVar.a((Integer) 16);
                fVar.a(b.this.c.getEngineConfig().getEnablePunctuation());
                fVar.c(b.this.c.getEngineConfig().getEnableSilenceDetection());
                fVar.c(b.this.c.getEngineConfig().getSilenceDurationThreshold());
                fVar.e(Integer.valueOf(b.this.c.getEngineConfig().getVadStartMuteDuration()));
                fVar.d(Integer.valueOf(b.this.c.getEngineConfig().getVadEndMuteDuration()));
                if (b.this.c.isRecognizerLong()) {
                    fVar.d(Boolean.valueOf(b.this.c.getEngineConfig().isWordTimeOffset()));
                    fVar.b(Boolean.valueOf(b.this.c.getEngineConfig().isSentenceTimeOffset()));
                }
                fVar.c(b.this.c.getEngineConfig().getScenes());
                StringBuilder sb = new StringBuilder(701);
                sb.append("{\"command\":\"START\", \"audioConfig\":{\"encoding\":\"");
                sb.append(fVar.f());
                sb.append("\",\"languageCode\":\"");
                sb.append(fVar.g());
                sb.append("\",\"sampleRate\":");
                sb.append(fVar.h());
                sb.append(",\"bitDepth\":");
                sb.append(fVar.a());
                if (fVar.b() != null) {
                    sb.append(",\"enablePunctuation\":");
                    sb.append(fVar.b());
                }
                if (fVar.e() != null) {
                    sb.append(",\"enableWordTimeOffset\":");
                    sb.append(fVar.e());
                }
                if (fVar.c() != null) {
                    sb.append(",\"enableSentenceTimeOffset\":");
                    sb.append(fVar.c());
                }
                if (fVar.l() != null) {
                    sb.append(",\"vadStartMuteDuration\":");
                    sb.append(fVar.l());
                }
                if (fVar.k() != null) {
                    sb.append(",\"vadEndMuteDuration\":");
                    sb.append(fVar.k());
                }
                if (fVar.i() != null) {
                    sb.append(",\"scene\":\"");
                    sb.append(fVar.i());
                    sb.append("\"");
                }
                if (fVar.d() != null || fVar.j() != null) {
                    sb.append(",\"metadata\":{");
                    if (fVar.d() != null) {
                        sb.append("\"enableSilenceDetection\":");
                        sb.append(fVar.d());
                    } else {
                        z = false;
                    }
                    if (fVar.j() != null) {
                        if (z) {
                            sb.append(",");
                        }
                        sb.append("\"silenceDurationThreshold\":");
                        sb.append(fVar.j());
                    }
                    sb.append("}");
                }
                sb.append("}}");
                String sb2 = sb.toString();
                SmartLogger.i("AsrProcessor", "send start ");
                if (b.this.m != null) {
                    b.this.m.a(sb2);
                }
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void a(byte[] bArr) {
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void b(int i, String str) {
                SmartLogger.i("AsrProcessor", "onClosed: code: " + i + "reason: " + str);
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void a(String str) {
                SmartLogger.i("AsrProcessor", "onMessage:");
                if (b.this.t) {
                    SmartLogger.i("AsrProcessor", "although receive message, but Destroy() is called");
                    return;
                }
                try {
                    SmartLogger.d("AsrProcessor", "onMessage text: " + str);
                    com.huawei.hms.mlsdk.asr.engine.cloud.vo.e a2 = com.huawei.hms.mlsdk.asr.engine.cloud.vo.e.a(str);
                    String c = a2.c();
                    if (!"0".equalsIgnoreCase(c)) {
                        b.this.a(40, "Service unavailable", Integer.valueOf(Integer.parseInt(c)));
                        return;
                    }
                    a aVar = null;
                    if ("START".equalsIgnoreCase(a2.a())) {
                        long currentTimeMillis = System.currentTimeMillis() - this.b;
                        Bundle bundle = new Bundle();
                        bundle.putInt("chainBuildingDelay", (int) currentTimeMillis);
                        SmartLogger.i("AsrProcessor", "chainBuildingDelay: " + currentTimeMillis);
                        com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 8, bundle);
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        Iterator it = b.this.d.iterator();
                        while (it.hasNext()) {
                            ((e) it.next()).b(elapsedRealtime);
                        }
                        b.this.e = b.this.new c(aVar);
                        b.this.e.a(Long.valueOf(elapsedRealtime));
                        b.this.a(true);
                        if (b.this.g == null) {
                            b.this.h.set(true);
                            b.this.g = new Thread(b.this.new d(aVar), "InnerProcessRunnable");
                            b.this.g.start();
                            return;
                        }
                        return;
                    }
                    if (CommonConstant.RETKEY.STATUS.equalsIgnoreCase(a2.a())) {
                        JSONObject b = a2.b();
                        com.huawei.hms.mlsdk.asr.engine.cloud.vo.d dVar = new com.huawei.hms.mlsdk.asr.engine.cloud.vo.d();
                        if (b != null && b.has("silenceDetected")) {
                            dVar.a(b.getBoolean("silenceDetected"));
                        }
                        if (dVar.a()) {
                            b.this.s = true;
                            SmartLogger.i("AsrProcessor", "receive silence=true");
                            Bundle bundle2 = new Bundle();
                            bundle2.putLong("speechEndTime", System.currentTimeMillis());
                            SmartLogger.i("AsrProcessor", "isSilenceDetected speechEndTime: " + System.currentTimeMillis());
                            com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 2, bundle2);
                            long elapsedRealtime2 = SystemClock.elapsedRealtime();
                            if (b.this.c.isRecognizerLong()) {
                                return;
                            }
                            Iterator it2 = b.this.d.iterator();
                            while (it2.hasNext()) {
                                ((e) it2.next()).a(elapsedRealtime2);
                            }
                            return;
                        }
                        return;
                    }
                    if (JsUtil.ServiceType.DATA.equalsIgnoreCase(a2.a())) {
                        if (b.this.r) {
                            b.this.r = false;
                            Long e = com.huawei.hms.mlsdk.asr.o.c.c().e(b.this.u);
                            if (e != null) {
                                SmartLogger.d("AsrProcessor", "getSpeechStartTime: " + e);
                                long currentTimeMillis2 = System.currentTimeMillis() - e.longValue();
                                Bundle bundle3 = new Bundle();
                                bundle3.putInt("firstWordCost", (int) currentTimeMillis2);
                                com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 3, bundle3);
                                SmartLogger.i("AsrProcessor", "firstWordCost: " + currentTimeMillis2);
                            }
                            b.this.q = System.currentTimeMillis() - b.this.p;
                            SmartLogger.i("AsrProcessor", "mSendFinishDelayTime is " + b.this.q + "ms");
                            this.f5070a = System.currentTimeMillis();
                        } else {
                            SmartLogger.d("AsrProcessor", "time of between receive data is: " + (System.currentTimeMillis() - this.f5070a) + "ms");
                            this.f5070a = System.currentTimeMillis();
                        }
                        com.huawei.hms.mlsdk.asr.engine.cloud.vo.c a3 = com.huawei.hms.mlsdk.asr.engine.cloud.vo.e.a(a2.b());
                        if (b.this.c.isRecognizerLong()) {
                            AsrResult asrResult = new AsrResult(5, null, a3.c(), a3.e());
                            asrResult.setSentenceOffsetList(a3.b());
                            asrResult.setWordOffsetList(a3.d());
                            Iterator it3 = b.this.d.iterator();
                            while (it3.hasNext()) {
                                ((e) it3.next()).onResult(asrResult);
                            }
                            return;
                        }
                        if ((a3.c() == null || "".equals(a3.c())) && !a3.a()) {
                            if (b.this.c.hasResult()) {
                                b.this.a(6, "Cannot understand", 1003);
                                return;
                            }
                            return;
                        }
                        if (!a3.a()) {
                            Iterator it4 = b.this.d.iterator();
                            while (it4.hasNext()) {
                                ((e) it4.next()).onResult(new AsrResult(5, null, a3.c(), false));
                            }
                            return;
                        }
                        Bundle bundle4 = new Bundle();
                        if (!b.this.s && com.huawei.hms.mlsdk.asr.o.c.c().d(b.this.u) != null) {
                            long currentTimeMillis3 = System.currentTimeMillis() - com.huawei.hms.mlsdk.asr.o.c.c().d(b.this.u).longValue();
                            bundle4.putInt("lastWordCost", (int) currentTimeMillis3);
                            SmartLogger.i("AsrProcessor", "lastWordCost: " + currentTimeMillis3);
                            com.huawei.hms.mlsdk.asr.o.c.c().a(b.this.u, 4, bundle4);
                        }
                        SmartLogger.i("AsrProcessor", "result.getIsFinal(): " + a3.a() + " isFinished: " + b.this.l);
                        b.this.a(false);
                        b.this.h.set(false);
                        for (e eVar : b.this.d) {
                            if (!b.this.o) {
                                b.this.o = true;
                                SmartLogger.d("AsrProcessor", "onMessage: result.mHasCallOnResult()");
                                eVar.onResult(new AsrResult(8, null, a3.c(), true));
                            }
                        }
                    }
                } catch (JSONException unused) {
                    b.this.a(40, "Service unavailable", 1003);
                }
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void a() {
                SmartLogger.i("AsrProcessor", "reconnect");
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void a(int i, String str) {
                b.this.w.set(i);
                SmartLogger.i("AsrProcessor", "closing:" + i + "," + str);
                if (!b.this.c.isRecognizerLong()) {
                    if (i != 0) {
                        if (3001 == i) {
                            b.this.a(44, "Authentication failed.", Integer.valueOf(i));
                            return;
                        } else {
                            b.this.a(40, "Service unavailable", Integer.valueOf(i));
                            return;
                        }
                    }
                    return;
                }
                b.this.l = true;
                b.this.a(false);
                if (b.a(b.this, i)) {
                    for (e eVar : b.this.d) {
                        eVar.onResult(new AsrResult(5, null, "", true));
                        eVar.onState(42);
                    }
                    b.this.m.c();
                    return;
                }
                Iterator it = b.this.d.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).onResult(new AsrResult(8, null, "", true));
                }
                Bundle bundle = new Bundle();
                if (3001 == i) {
                    bundle.putInt("subErrorCode", 3001);
                    Iterator it2 = b.this.d.iterator();
                    while (it2.hasNext()) {
                        ((e) it2.next()).a(new AsrError(44, str, bundle));
                    }
                    return;
                }
                bundle.putInt("subErrorCode", i);
                Iterator it3 = b.this.d.iterator();
                while (it3.hasNext()) {
                    ((e) it3.next()).a(new AsrError(40, str, bundle));
                }
            }

            @Override // com.huawei.hms.mlsdk.asr.o.e
            public void a(Throwable th, Response response) {
                StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("onFailure socket error: ");
                a2.append(th.getMessage());
                a2.append(", ");
                a2.append(th.getClass().getSimpleName());
                SmartLogger.e("AsrProcessor", a2.toString());
                if (!b.this.c.isRecognizerLong() || b.this.m == null || b.this.m.b() || (!(th instanceof UnknownHostException) && !(th instanceof SSLException) && !(th instanceof ConnectException))) {
                    if (b.this.c.isRecognizerLong() && b.this.i.get()) {
                        b bVar = b.this;
                        if (b.a(bVar, bVar.w.get())) {
                            SmartLogger.i("AsrProcessor", "onFailure socket already reconnect in onClosing");
                            return;
                        }
                    }
                    if (!(th instanceof IOException) || !b.this.g()) {
                        if (b.this.l || b.this.s) {
                            return;
                        }
                        SmartLogger.i("AsrProcessor", th.getMessage() + " isFinished=" + b.this.l);
                        b.this.a(40, "Service unavailable", 1003);
                        return;
                    }
                    b.this.a(7, "No network", null);
                    return;
                }
                for (e eVar : b.this.d) {
                    eVar.onResult(new AsrResult(5, null, "", true));
                    eVar.onState(42);
                }
                b.this.m.c();
            }
        }
    }
}

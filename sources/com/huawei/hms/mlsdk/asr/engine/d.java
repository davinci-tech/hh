package com.huawei.hms.mlsdk.asr.engine;

import android.os.Bundle;
import android.os.SystemClock;
import com.huawei.hms.mlsdk.asr.energy.NativeVadDetector;
import com.huawei.hms.mlsdk.asr.energy.vo.DetectResult;
import com.huawei.hms.mlsdk.asr.energy.vo.SampleBuffer;
import com.huawei.hms.mlsdk.asr.energy.vo.VoiceDetectParams;
import com.huawei.hms.mlsdk.asr.engine.AsrEngine;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private AsrEngine f5082a;
    private com.huawei.hms.mlsdk.asr.engine.utils.a c;
    private NativeVadDetector h;
    private c i;
    private List<a> b = new ArrayList();
    private int d = 1;
    private boolean e = false;
    private Long f = null;
    private Long g = null;

    /* loaded from: classes9.dex */
    public interface a {
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private boolean f5083a;
        private double b;
        private byte[] c;

        public double b() {
            return this.b;
        }

        public boolean c() {
            return this.f5083a;
        }

        public void a(boolean z) {
            this.f5083a = z;
        }

        public void a(double d) {
            this.b = d;
        }

        public byte[] a() {
            byte[] bArr = this.c;
            if (bArr == null) {
                return null;
            }
            return (byte[]) bArr.clone();
        }

        public void a(byte[] bArr) {
            this.c = bArr == null ? null : (byte[]) bArr.clone();
        }
    }

    public d(AsrEngine asrEngine) {
        this.c = null;
        this.f5082a = asrEngine;
        asrEngine.getEngineConfig().setVadDetectDuration(AsrConstants.bufferSize / 64);
        this.c = new com.huawei.hms.mlsdk.asr.engine.utils.a(asrEngine.getEngineConfig().getVadDetectDuration() * 64);
        VoiceDetectParams a2 = new VoiceDetectParams.b().a(13500).a();
        NativeVadDetector nativeVadDetector = new NativeVadDetector();
        this.h = nativeVadDetector;
        nativeVadDetector.init(a2);
        this.i = new c(asrEngine.getEngineConfig().getVadEndMuteDuration());
    }

    private b b(byte[] bArr) throws com.huawei.hms.mlsdk.asr.engine.a {
        DetectResult detect = this.h.detect(new SampleBuffer.b().a(bArr).a(bArr.length).a());
        if (detect == null) {
            detect = new DetectResult(1, 0.0f);
        }
        b bVar = new b();
        bVar.a(bArr);
        bVar.a(detect.getEnergy());
        bVar.a(detect.getVoiceType() == 1);
        return bVar;
    }

    public void a(a aVar) {
        this.b.add(aVar);
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        byte[] b2 = this.c.b();
        if (b2 == null || b2.length == 0) {
            return;
        }
        try {
            b b3 = b(b2);
            Iterator<a> it = this.b.iterator();
            while (it.hasNext()) {
                ((AsrEngine.d) it.next()).a(b3);
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!b3.c() && !this.e) {
                this.e = true;
                this.g = Long.valueOf(elapsedRealtime);
                SmartLogger.i("AsrVadDetector", "start send to cloud, energy===>" + b3.b());
                Iterator<a> it2 = this.b.iterator();
                while (it2.hasNext()) {
                    ((AsrEngine.d) it2.next()).e(this.g.longValue());
                }
                return;
            }
            this.i.a(b3.b);
            if (this.f5082a.hasResult()) {
                this.i.a();
                if (this.i.b()) {
                    Iterator<a> it3 = this.b.iterator();
                    while (it3.hasNext()) {
                        ((AsrEngine.d) it3.next()).d(elapsedRealtime);
                    }
                    return;
                } else {
                    if (elapsedRealtime - this.g.longValue() > this.f5082a.getEngineConfig().getMaxAudioDuration()) {
                        Iterator<a> it4 = this.b.iterator();
                        while (it4.hasNext()) {
                            ((AsrEngine.d) it4.next()).a(elapsedRealtime);
                        }
                        return;
                    }
                    return;
                }
            }
            if (this.f == null) {
                this.f = this.f5082a.getRecordStartTime();
            }
            if (this.f == null) {
                this.f = Long.valueOf(elapsedRealtime);
            }
            long longValue = elapsedRealtime - this.f.longValue();
            int vadStartMuteDuration = this.f5082a.getEngineConfig().getVadStartMuteDuration();
            int i = this.d;
            if (longValue >= vadStartMuteDuration * i) {
                if (i != this.f5082a.getEngineConfig().getVadStartMuteDetectTimes()) {
                    SmartLogger.i("AsrProcessor", "onNoSound duration===>" + longValue);
                    Iterator<a> it5 = this.b.iterator();
                    while (it5.hasNext()) {
                        ((AsrEngine.d) it5.next()).b(elapsedRealtime);
                    }
                } else {
                    SmartLogger.i("AsrProcessor", "onNoSoundTimesExceed duration===>" + longValue);
                    Iterator<a> it6 = this.b.iterator();
                    while (it6.hasNext()) {
                        ((AsrEngine.d) it6.next()).c(elapsedRealtime);
                    }
                }
                this.d++;
            }
        } catch (RuntimeException e) {
            StringBuilder a2 = com.huawei.hms.mlsdk.asr.o.a.a("RuntimeException e = ");
            a2.append(e.getMessage());
            SmartLogger.e("AsrVadDetector", a2.toString());
            Bundle bundle = new Bundle();
            bundle.putInt("subErrorCode", 1002);
            Iterator<a> it7 = this.b.iterator();
            while (it7.hasNext()) {
                ((AsrEngine.d) it7.next()).a(new AsrError(40, "Service unavailable", bundle));
            }
        }
    }

    /* loaded from: classes9.dex */
    static class c {
        private static final double f = Math.log10(1000.0d) * 10.0d;

        /* renamed from: a, reason: collision with root package name */
        private double f5084a = -1.0d;
        private double b = -1.0d;
        private long c = -1;
        private long d;
        private List<Double[]> e;

        public c(long j) {
            ArrayList arrayList = new ArrayList();
            this.e = arrayList;
            this.d = j;
            Double valueOf = Double.valueOf(0.0d);
            Double valueOf2 = Double.valueOf(40.0d);
            arrayList.add(new Double[]{valueOf, valueOf2, Double.valueOf(0.15d)});
            List<Double[]> list = this.e;
            Double valueOf3 = Double.valueOf(60.0d);
            list.add(new Double[]{valueOf2, valueOf3, Double.valueOf(0.1d)});
            List<Double[]> list2 = this.e;
            Double valueOf4 = Double.valueOf(70.0d);
            list2.add(new Double[]{valueOf3, valueOf4, Double.valueOf(0.075d)});
            this.e.add(new Double[]{valueOf4, Double.valueOf(Double.MAX_VALUE), Double.valueOf(0.05d)});
        }

        public void a(double d) {
            double log10 = Double.compare(d, 0.0d) > 0 ? Math.log10(d) * 10.0d : 0.0d;
            if (Double.compare(log10, f) <= 0) {
                return;
            }
            if (Double.compare(this.f5084a, 0.0d) <= 0) {
                SmartLogger.i("AsrVadDetector", String.format(Locale.ENGLISH, "first energy = %.2f", Double.valueOf(log10)));
                this.f5084a = log10;
                return;
            }
            if (Double.compare(this.b, 0.0d) <= 0) {
                this.c = -1L;
                if (Double.compare(this.f5084a, log10) > 0) {
                    this.f5084a = log10;
                    return;
                }
                return;
            }
            double abs = Math.abs(log10 - this.b) / this.b;
            Iterator<Double[]> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Double[] next = it.next();
                if (Double.compare(this.b, next[0].doubleValue()) > 0 && Double.compare(this.b, next[1].doubleValue()) <= 0) {
                    if (Double.compare(abs, next[2].doubleValue()) > 0) {
                        this.c = -1L;
                    } else if (this.c <= 0) {
                        this.c = SystemClock.elapsedRealtime();
                    }
                }
            }
            SmartLogger.i("AsrVadDetector", String.format(Locale.ENGLISH, "lowerEnergy = %.2f, energy = %.2f, diff = %.2f", Double.valueOf(this.b), Double.valueOf(log10), Double.valueOf(abs)));
        }

        public boolean b() {
            if (this.c <= 0) {
                return false;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.c;
            SmartLogger.i("AsrVadDetector", String.format(Locale.ENGLISH, "duration = %d, timingDuration = %d, lowerEnergy = %.2f", Long.valueOf(elapsedRealtime), Long.valueOf(this.d), Double.valueOf(this.b)));
            return elapsedRealtime >= this.d;
        }

        public void a() {
            if (Double.compare(this.b, 0.0d) > 0) {
                return;
            }
            this.b = this.f5084a;
            SmartLogger.i("AsrVadDetector", String.format(Locale.ENGLISH, "stop calc lower energy, lowerEnergy = %.2f", Double.valueOf(this.b)));
        }
    }

    public void a() {
        synchronized (this) {
            com.huawei.hms.mlsdk.asr.engine.utils.a aVar = this.c;
            if (aVar != null) {
                aVar.a();
            }
            NativeVadDetector nativeVadDetector = this.h;
            if (nativeVadDetector != null) {
                nativeVadDetector.release();
                this.h = null;
            }
        }
    }
}

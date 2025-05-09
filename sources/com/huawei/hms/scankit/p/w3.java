package com.huawei.hms.scankit.p;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hms.feature.DynamicModuleInitializer;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.utils.FileUtil;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.wearengine.sensor.DataResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public final class w3 extends u3 {
    private volatile String h;
    private volatile String i;
    private boolean j;
    private volatile long k;
    public d l;

    class a extends SimpleDateFormat {
        a(String str) {
            super(str);
            setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        }
    }

    class b extends SimpleDateFormat {
        b(String str) {
            super(str);
            setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private int f5906a;
        private String b;
        private String c;
        private long d;
        private long e;
        private String f;
        private String g;
        private boolean h;
        private int i;
        private boolean j;

        /* synthetic */ c(long j, String str, String str2, boolean z, int i, int i2, a aVar) {
            this(j, str, str2, z, i, i2);
        }

        private c(long j, String str, String str2, boolean z, int i, int i2) {
            this.d = j;
            this.b = str;
            this.c = str2;
            this.h = z;
            this.i = i;
            this.f5906a = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public c b(String str) {
            this.g = str;
            return this;
        }

        public c a(int i) {
            this.f5906a = i;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public c a(long j) {
            this.e = j;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public c a(boolean z) {
            this.j = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public c a(String str) {
            this.f = str;
            return this;
        }
    }

    public class d {

        /* renamed from: a, reason: collision with root package name */
        private String f5907a = d.class.getSimpleName();
        public Timer b = new Timer();
        private volatile boolean c = true;
        private List<c> d = new ArrayList(10);
        private List<c> e = new ArrayList(10);

        class a extends TimerTask {
            a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    d.this.c = true;
                    d.this.a();
                } catch (Exception unused) {
                    o4.b(d.this.f5907a, "onLog Exception");
                }
            }
        }

        class b {

            /* renamed from: a, reason: collision with root package name */
            private StringBuilder f5909a;
            private AtomicInteger[] b;
            private String[] c;
            private long[] d;

            private b() {
                this.f5909a = new StringBuilder(100);
                this.b = new AtomicInteger[]{new AtomicInteger(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger(), new AtomicInteger()};
                this.c = new String[]{"lt10K:", "lt100K:", "lt1M:", "lt3M:", "lt10M:", "lt40M:", "gt40M:"};
                this.d = new long[]{FileUtil.LOCAL_REPORT_FILE_MAX_SIZE, 102400, 1048576, 3145728, 10485760, 41943040, LocationRequestCompat.PASSIVE_INTERVAL};
            }

            /* JADX INFO: Access modifiers changed from: private */
            public String a() {
                StringBuilder sb = this.f5909a;
                sb.delete(0, sb.length());
                this.f5909a.append("{");
                for (int i = 0; i < this.b.length; i++) {
                    this.f5909a.append(this.c[i]);
                    this.f5909a.append(this.b[i]);
                    this.f5909a.append(",");
                }
                this.f5909a.replace(r0.length() - 1, this.f5909a.length(), "}");
                return this.f5909a.toString();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(int i) {
                int i2 = 0;
                while (true) {
                    AtomicInteger[] atomicIntegerArr = this.b;
                    if (i2 >= atomicIntegerArr.length) {
                        return;
                    }
                    if (i <= this.d[i2]) {
                        atomicIntegerArr[i2].addAndGet(1);
                        return;
                    }
                    i2++;
                }
            }

            /* synthetic */ b(d dVar, a aVar) {
                this();
            }
        }

        class c {

            /* renamed from: a, reason: collision with root package name */
            private StringBuilder f5910a;
            private SparseArray<AtomicInteger> b;

            class a extends SparseArray<AtomicInteger> {
                a() {
                    put(0, new AtomicInteger());
                }
            }

            class b extends AtomicInteger {
                b() {
                    addAndGet(1);
                }
            }

            private c() {
                this.f5910a = new StringBuilder(60);
                this.b = new a();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(int i) {
                if (this.b.get(i) == null) {
                    this.b.put(i, new b());
                } else {
                    this.b.get(i).addAndGet(1);
                }
            }

            /* synthetic */ c(d dVar, a aVar) {
                this();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public String a() {
                StringBuilder sb = this.f5910a;
                sb.delete(0, sb.length());
                this.f5910a.append("{");
                for (int i = 0; i < this.b.size(); i++) {
                    this.f5910a.append(this.b.keyAt(i));
                    this.f5910a.append(":");
                    this.f5910a.append(this.b.valueAt(i));
                    this.f5910a.append(",");
                }
                this.f5910a.replace(r0.length() - 1, this.f5910a.length(), "}");
                return this.f5910a.toString();
            }
        }

        public d() {
        }

        public void b() {
            Timer timer = this.b;
            if (timer != null) {
                timer.cancel();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(c cVar) {
            if (this.d.size() > 100) {
                return;
            }
            synchronized (this) {
                this.d.add(cVar);
                if (this.c) {
                    this.c = false;
                    this.b.schedule(new a(), 1000L);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            if (this.d.size() > 0) {
                synchronized (this) {
                    List<c> list = this.d;
                    List<c> list2 = this.e;
                    this.d = list2;
                    this.e = list;
                    list2.clear();
                }
                a(this.e);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void a(List<c> list) {
            HashSet<String> hashSet = new HashSet();
            Iterator<c> it = list.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().c);
            }
            for (String str : hashSet) {
                Boolean bool = null;
                c cVar = new c(this, 0 == true ? 1 : 0);
                b bVar = new b(this, 0 == true ? 1 : 0);
                String str2 = "";
                long j = Long.MAX_VALUE;
                long j2 = 0;
                long j3 = 0;
                long j4 = 0;
                long j5 = 0;
                long j6 = Long.MIN_VALUE;
                String str3 = "";
                String str4 = str3;
                for (c cVar2 : list) {
                    str2 = cVar2.b;
                    str3 = cVar2.f;
                    str4 = cVar2.g;
                    boolean z = cVar2.h;
                    j2 += cVar2.e - cVar2.d;
                    cVar.a(cVar2.f5906a);
                    bVar.a(cVar2.i);
                    j3++;
                    if (cVar2.j) {
                        j4++;
                    }
                    if (cVar2.f5906a != 0) {
                        j5++;
                    }
                    if (cVar2.e - cVar2.d < j) {
                        j = cVar2.e - cVar2.d;
                    }
                    if (cVar2.e - cVar2.d > j6) {
                        j6 = cVar2.e - cVar2.d;
                    }
                    bool = Boolean.valueOf(z);
                }
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                w3.this.g();
                linkedHashMap.putAll(w3.this.b);
                linkedHashMap.put("result", cVar.a());
                linkedHashMap.put("imgSizeHistogram", bVar.a());
                linkedHashMap.put("callTime", str2);
                linkedHashMap.put("transId", str);
                if (j3 != 0) {
                    j2 /= j3;
                }
                linkedHashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(j2));
                linkedHashMap.put("allCnt", String.valueOf(j3));
                linkedHashMap.put("failCnt", String.valueOf(j5));
                linkedHashMap.put("codeCnt", String.valueOf(j4));
                linkedHashMap.put("scanType", str3);
                linkedHashMap.put("sceneType", str4);
                linkedHashMap.put("min", String.valueOf(j));
                linkedHashMap.put("max", String.valueOf(j6));
                linkedHashMap.put("algPhotoMode", String.valueOf(bool));
                a4.b().b("60001", linkedHashMap);
            }
        }
    }

    public w3(Bundle bundle, String str) {
        super(bundle, DynamicModuleInitializer.getContext().getApplicationContext());
        this.j = false;
        this.l = new d();
        this.b.put("apiName", str);
        if (DetailRect.PHOTO_MODE.equals(str)) {
            this.j = true;
        }
    }

    public void a(String str) {
        this.b.put("algapi", str);
    }

    public c a(boolean z, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.j) {
                return new c(currentTimeMillis, new a("yyyyMMddHHmmss.SSS").format(Long.valueOf(currentTimeMillis)), UUID.randomUUID().toString(), z, i, 0, null);
            }
            if (currentTimeMillis - this.k > ProfileExtendConstants.TIME_OUT) {
                String format = new b("yyyyMMddHHmmss.SSS").format(Long.valueOf(currentTimeMillis));
                String uuid = UUID.randomUUID().toString();
                if (currentTimeMillis - this.k > ProfileExtendConstants.TIME_OUT) {
                    this.h = format;
                    this.i = uuid;
                    this.k = currentTimeMillis;
                }
            }
            return new c(currentTimeMillis, this.h, this.i, z, i, 0, null);
        } catch (Exception unused) {
            o4.b("HaLog6001", "exception happens");
            return new c(currentTimeMillis, this.h, this.i, z, i, 0, null);
        }
    }

    public void a(HmsScan[] hmsScanArr, c cVar) {
        try {
            String str = u3.d;
            String str2 = u3.e;
            if (a()) {
                boolean z = false;
                int i = 0;
                z = false;
                if (hmsScanArr != null && hmsScanArr.length > 0) {
                    int length = hmsScanArr.length;
                    while (i < length) {
                        HmsScan hmsScan = hmsScanArr[i];
                        String a2 = u3.a(hmsScan.scanType);
                        i++;
                        str2 = u3.b(hmsScan.scanTypeForm);
                        str = a2;
                    }
                    z = true;
                }
                this.l.a(cVar.a(System.currentTimeMillis()).a(z).a(str).b(str2));
                this.k = cVar.e;
            }
        } catch (NullPointerException unused) {
            o4.b("HaLog60001", "nullPoint");
        } catch (Exception unused2) {
            o4.b("HaLog60001", "logEnd Exception");
        }
    }
}

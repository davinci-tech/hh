package com.huawei.hwcommonmodel;

import com.huawei.hwlogsmodel.LogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class TrackSimilarityIdentifier {

    public enum TrackRouteDateInfo {
        VALID_DATE,
        INVALID_DATE
    }

    private static void a(String str, List<TrackRecord> list) {
        LogUtil.c("TrackSimilarityIdentifier", str, " duplicatesEliminate records, records size:", Integer.valueOf(list.size()), " records print start");
        for (int i = 0; i < list.size(); i++) {
            TrackRecord trackRecord = list.get(i);
            LogUtil.c("TrackSimilarityIdentifier", str, " duplicatesEliminate[", Integer.valueOf(i), "]:", c(trackRecord.getStartTime()), " - ", c(trackRecord.getEndTime()), " duration:", Long.valueOf(trackRecord.getDuration()), " distance:", Float.valueOf(trackRecord.getDistance()), " isWatchData:", Boolean.valueOf(trackRecord.isWatchData()));
        }
        LogUtil.c("TrackSimilarityIdentifier", str, " duplicatesEliminate records, records size:", Integer.valueOf(list.size()), " records print end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, TrackRecord trackRecord) {
        if (trackRecord != null) {
            LogUtil.c("TrackSimilarityIdentifier", str, " TrackRecord :", c(trackRecord.getStartTime()), " - ", c(trackRecord.getEndTime()), " duration:", Long.valueOf(trackRecord.getDuration()), " distance:", Float.valueOf(trackRecord.getDistance()), " isWatchData:", Boolean.valueOf(trackRecord.isWatchData()));
        } else {
            LogUtil.c("TrackSimilarityIdentifier", str, " TrackRecord :TrackRecord = null ");
        }
    }

    private static String c(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    private List<TrackRecord> e(List<TrackRecord> list) {
        boolean z;
        a("Arg:", list);
        c cVar = new c();
        cVar.c(list);
        c.b b = cVar.b();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (b.b()) {
            TrackRecord c2 = b.c();
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (cVar.d(c2, (TrackRecord) it.next())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                arrayList.add(c2);
            }
            d("ranking(isConflictWithResult:" + z + "):" + i, c2);
            i++;
        }
        Collections.sort(arrayList, new Comparator<TrackRecord>() { // from class: com.huawei.hwcommonmodel.TrackSimilarityIdentifier.2
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(TrackRecord trackRecord, TrackRecord trackRecord2) {
                return trackRecord.getStartTime() >= trackRecord2.getStartTime() ? 1 : -1;
            }
        });
        a("result:", arrayList);
        return arrayList;
    }

    public List<TrackRecord> c(List<TrackRecord> list, TrackRouteDateInfo trackRouteDateInfo) {
        if (list == null) {
            return null;
        }
        if (trackRouteDateInfo == TrackRouteDateInfo.VALID_DATE) {
            return e(list);
        }
        if (trackRouteDateInfo != TrackRouteDateInfo.INVALID_DATE) {
            return null;
        }
        List<TrackRecord> e = e(list);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        arrayList.removeAll(e);
        return arrayList;
    }

    static class c {
        private List<TrackRecord> e;

        private c() {
            this.e = new ArrayList();
        }

        public void c(List<TrackRecord> list) {
            this.e = list;
        }

        public b b() {
            return new b();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean d(TrackRecord trackRecord, TrackRecord trackRecord2) {
            return b(trackRecord, trackRecord2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b(TrackRecord trackRecord, TrackRecord trackRecord2) {
            return (trackRecord.getDuration() + trackRecord2.getDuration()) - (Math.max(trackRecord.getEndTime(), trackRecord2.getEndTime()) - Math.min(trackRecord.getStartTime(), trackRecord2.getStartTime())) > 300000;
        }

        public class b {

            /* renamed from: a, reason: collision with root package name */
            private List<TrackRecord> f6262a = new ArrayList();
            private List<TrackRecord> b = new ArrayList();
            private List<TrackRecord> c = new ArrayList();
            private Iterator<TrackRecord> d;

            b() {
                C0160b c0160b;
                final HashMap hashMap = new HashMap();
                for (a aVar : c(c.this.e)) {
                    aVar.c();
                    for (Map.Entry entry : aVar.b()) {
                        if (!hashMap.containsKey(entry.getKey())) {
                            c0160b = new C0160b(0.0f);
                        } else {
                            c0160b = (C0160b) hashMap.get(entry.getKey());
                        }
                        c0160b.b(((Float) entry.getValue()).floatValue());
                        hashMap.put((TrackRecord) entry.getKey(), c0160b);
                    }
                }
                Iterator it = hashMap.entrySet().iterator();
                while (it.hasNext()) {
                    this.f6262a.add((TrackRecord) ((Map.Entry) it.next()).getKey());
                }
                Collections.sort(this.f6262a, new Comparator<TrackRecord>() { // from class: com.huawei.hwcommonmodel.TrackSimilarityIdentifier.c.b.5
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(TrackRecord trackRecord, TrackRecord trackRecord2) {
                        if (!hashMap.containsKey(trackRecord) || !hashMap.containsKey(trackRecord2)) {
                            throw new UnsupportedOperationException("sort records not in marker List,logical error!!!");
                        }
                        return Float.compare(((C0160b) hashMap.get(trackRecord)).b(), ((C0160b) hashMap.get(trackRecord2)).b());
                    }
                });
                for (TrackRecord trackRecord : c.this.e) {
                    if (!this.f6262a.contains(trackRecord)) {
                        this.b.add(trackRecord);
                    }
                }
                this.c.addAll(this.f6262a);
                this.c.addAll(this.b);
                Collections.reverse(this.c);
                this.d = this.c.iterator();
            }

            public boolean b() {
                return this.d.hasNext();
            }

            public TrackRecord c() {
                return this.d.next();
            }

            private List<a> c(List<TrackRecord> list) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    a d = d(i, list);
                    if (d != null) {
                        arrayList.add(d);
                    }
                }
                return arrayList;
            }

            private a d(int i, List<TrackRecord> list) {
                TrackRecord trackRecord = list.get(i);
                a aVar = null;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (i != i2 && c.this.b(trackRecord, list.get(i2))) {
                        if (aVar == null) {
                            aVar = new a();
                            aVar.e(trackRecord);
                        }
                        aVar.e(list.get(i2));
                    }
                }
                return aVar;
            }

            class a {
                private float b;
                private float c;
                private Map<TrackRecord, Float> d;
                private List<TrackRecord> e;

                private a() {
                    this.e = new ArrayList();
                    this.d = new HashMap();
                    this.b = 0.0f;
                    this.c = 0.0f;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public Set<Map.Entry<TrackRecord, Float>> b() {
                    return this.d.entrySet();
                }

                public void c() {
                    TrackRecord trackRecord = this.e.size() > 0 ? this.e.get(0) : null;
                    Collections.sort(this.e, new Comparator<TrackRecord>() { // from class: com.huawei.hwcommonmodel.TrackSimilarityIdentifier.c.b.a.2
                        @Override // java.util.Comparator
                        /* renamed from: c, reason: merged with bridge method [inline-methods] */
                        public int compare(TrackRecord trackRecord2, TrackRecord trackRecord3) {
                            if (Float.compare(trackRecord2.getDistance(), trackRecord3.getDistance()) != 0) {
                                return Float.compare(trackRecord2.getDistance(), trackRecord3.getDistance());
                            }
                            if (trackRecord2.getStartTime() < trackRecord3.getStartTime()) {
                                return 1;
                            }
                            return trackRecord2.getStartTime() > trackRecord3.getStartTime() ? -1 : 0;
                        }
                    });
                    if (this.e.size() == 0 || this.e.size() == 1) {
                        return;
                    }
                    this.d.clear();
                    float size = 2.0f / (this.e.size() - 1.0f);
                    LogUtil.c("TrackSimilarityIdentifier", "make score begin,size:", Integer.valueOf(this.e.size()));
                    TrackSimilarityIdentifier.d("make score begin,base:", trackRecord);
                    this.d.put(this.e.get(0), Float.valueOf(1.0f));
                    TrackSimilarityIdentifier.d("make score[" + this.d.get(this.e.get(0)) + "]:", this.e.get(0));
                    for (int i = 1; i < this.e.size() - 1; i++) {
                        this.d.put(this.e.get(i), Float.valueOf((i * size) + 1.0f));
                        TrackSimilarityIdentifier.d("make score[" + this.d.get(this.e.get(i)) + "]:", this.e.get(i));
                    }
                    Map<TrackRecord, Float> map = this.d;
                    List<TrackRecord> list = this.e;
                    map.put(list.get(list.size() - 1), Float.valueOf(3.0f));
                    StringBuilder sb = new StringBuilder("make score[");
                    Map<TrackRecord, Float> map2 = this.d;
                    List<TrackRecord> list2 = this.e;
                    sb.append(map2.get(list2.get(list2.size() - 1)));
                    sb.append("]:");
                    String sb2 = sb.toString();
                    List<TrackRecord> list3 = this.e;
                    TrackSimilarityIdentifier.d(sb2, list3.get(list3.size() - 1));
                    LogUtil.c("TrackSimilarityIdentifier", "make score end,size:", Integer.valueOf(this.e.size()));
                }

                public void e(TrackRecord trackRecord) {
                    this.e.add(trackRecord);
                    float distance = trackRecord.getDistance();
                    if (distance > this.b) {
                        this.b = distance;
                    }
                    if (!trackRecord.isWatchData() || distance <= this.c) {
                        return;
                    }
                    this.c = distance;
                }
            }

            /* renamed from: com.huawei.hwcommonmodel.TrackSimilarityIdentifier$c$b$b, reason: collision with other inner class name */
            class C0160b {
                private int b = 0;
                private float e;

                C0160b(float f) {
                    this.e = f;
                }

                public void b(float f) {
                    this.b++;
                    this.e += f;
                }

                public float b() {
                    int i = this.b;
                    if (i == 0) {
                        return 0.0f;
                    }
                    return this.e / i;
                }
            }
        }
    }

    public static abstract class TrackRecord {
        public abstract float getDistance();

        public abstract long getEndTime();

        public abstract long getStartTime();

        public abstract boolean isWatchData();

        public long getDuration() {
            return getEndTime() - getStartTime();
        }
    }
}

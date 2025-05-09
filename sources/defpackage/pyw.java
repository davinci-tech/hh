package defpackage;

import android.os.Looper;
import android.os.SystemClock;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiDateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class pyw {
    private static final Object c = new Object();
    private static volatile pyw e;

    /* renamed from: a, reason: collision with root package name */
    private volatile int f16347a = 0;
    private final Object d = new Object();
    private final Map<Long, List<HiHealthData>> b = new HashMap(0);

    private pyw() {
    }

    public static pyw e() {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new pyw();
                }
            }
        }
        return e;
    }

    public void c(long j, List<HiHealthData> list) {
        LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", addData ", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(Long.valueOf(j)), ", size=", Integer.valueOf(list.size()));
        synchronized (this.d) {
            this.b.put(Long.valueOf(j), list);
        }
    }

    public void b(final long j, final long j2, final int[] iArr, final IBaseResponseCallback iBaseResponseCallback) {
        if (j2 < j || j2 - j > 86400000) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pyv
                @Override // java.lang.Runnable
                public final void run() {
                    pyw.this.e(j, j2, iArr, iBaseResponseCallback);
                }
            });
        } else {
            e(j, j2, iArr, iBaseResponseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void e(long j, long j2, int[] iArr, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (this.d) {
            if (this.f16347a == 2) {
                return;
            }
            long j3 = j2 - j == 86400000 ? j2 - 1 : j2;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", thread_id=", Long.valueOf(Thread.currentThread().getId()), ", getDayData start, ", "startTime=", simpleDateFormat.format(Long.valueOf(j)), ", endTime=", simpleDateFormat.format(Long.valueOf(j3)));
            long t = HiDateUtil.t(j);
            List<HiHealthData> list = this.b.get(Long.valueOf(t));
            if (list == null) {
                LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", dayDataList==null");
                if (this.f16347a == 0) {
                    this.f16347a = 1;
                    LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", requestDataByTimeInterval start");
                    a(t, HiDateUtil.f(j) - 1);
                }
                try {
                    LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", wait");
                    this.d.wait();
                    e(j, j3, iArr, iBaseResponseCallback);
                } catch (InterruptedException unused) {
                    LogUtil.b("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", Failed to getDayData");
                }
                return;
            }
            List<HiHealthData> a2 = a(j, j3, list);
            a(iArr, a2);
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", getDayData finish, size=", Integer.valueOf(a2.size()));
            iBaseResponseCallback.d(0, a2);
        }
    }

    private List<HiHealthData> a(long j, long j2, List<HiHealthData> list) {
        if (!list.isEmpty() && j2 - j < 86399999) {
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", time filter");
            int e2 = e(list, j);
            int d = d(list, j2);
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", startIndex=", Integer.valueOf(e2), ", endIndex=", Integer.valueOf(d));
            if (e2 < 0 || d < 0) {
                return new ArrayList(0);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", data.start=", simpleDateFormat.format(Long.valueOf(list.get(e2).getStartTime())), ", data.end=", simpleDateFormat.format(Long.valueOf(list.get(d - 1).getStartTime())));
            return new ArrayList(list.subList(e2, d));
        }
        return new ArrayList(list);
    }

    private void a(int[] iArr, List<HiHealthData> list) {
        if (iArr.length != 10) {
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", type filter");
            Iterator<HiHealthData> it = list.iterator();
            while (it.hasNext()) {
                HiHealthData next = it.next();
                int length = iArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (next.getType() == iArr[i]) {
                            break;
                        } else {
                            i++;
                        }
                    } else {
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    private int e(List<HiHealthData> list, long j) {
        if (list.get(list.size() - 1).getStartTime() < j) {
            return -1;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStartTime() >= j) {
                return i;
            }
        }
        return -1;
    }

    private int d(List<HiHealthData> list, long j) {
        int i = 0;
        if (list.get(0).getStartTime() > j) {
            return -1;
        }
        while (i < list.size()) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getStartTime() == j) {
                return i + 1;
            }
            if (hiHealthData.getStartTime() > j) {
                return i;
            }
            i++;
        }
        return i;
    }

    private void a(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(qjv.d());
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(0);
        final long t = HiDateUtil.t(j);
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        kor.a().e(BaseApplication.e(), hiDataReadOption, new IBaseResponseCallback() { // from class: pyx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                pyw.this.e(elapsedRealtime, t, i, obj);
            }
        });
    }

    /* synthetic */ void e(long j, long j2, int i, Object obj) {
        List<HiHealthData> arrayList;
        LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", requestDataByTimeInterval ", "costTime = ", Long.valueOf(SystemClock.elapsedRealtime() - j));
        if (obj instanceof List) {
            arrayList = (List) obj;
        } else {
            arrayList = new ArrayList<>(0);
        }
        synchronized (this.d) {
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", requestDataByTimeInterval finish, size=", Integer.valueOf(arrayList.size()));
            if (this.f16347a == 1) {
                if (arrayList.size() > 1) {
                    Collections.sort(arrayList, new Comparator() { // from class: pyz
                        @Override // java.util.Comparator
                        public final int compare(Object obj2, Object obj3) {
                            int compare;
                            compare = Long.compare(((HiHealthData) obj2).getStartTime(), ((HiHealthData) obj3).getStartTime());
                            return compare;
                        }
                    });
                }
                if (this.b.size() > 180) {
                    Set<Map.Entry<Long, List<HiHealthData>>> entrySet = this.b.entrySet();
                    Iterator<Map.Entry<Long, List<HiHealthData>>> it = entrySet.iterator();
                    int size = entrySet.size() / 2;
                    for (int i2 = 0; i2 < size; i2++) {
                        it.next();
                    }
                    it.remove();
                }
                this.b.put(Long.valueOf(j2), arrayList);
                this.f16347a = 0;
            }
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", id=", Long.valueOf(Thread.currentThread().getId()), ", requestDataByTimeInterval notifyAll");
            this.d.notifyAll();
        }
    }

    public void d() {
        LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", destory");
        synchronized (c) {
            if (e != null) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: pyy
                    @Override // java.lang.Runnable
                    public final void run() {
                        pyw.this.c();
                    }
                });
                e = null;
            }
        }
    }

    /* synthetic */ void c() {
        synchronized (this.d) {
            LogUtil.a("BloodSugarCacheHelper", Integer.valueOf(hashCode()), ", destory execute");
            this.f16347a = 2;
        }
    }
}

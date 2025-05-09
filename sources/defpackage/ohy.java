package defpackage;

import android.os.SystemClock;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes6.dex */
public class ohy {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ohy f15687a;
    private int b;
    private int c;
    private Object g = null;
    private final ReentrantReadWriteLock f = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock d = new ReentrantReadWriteLock();
    private final Set<HiDataReadResultListener> e = new HashSet();

    public static int[] a() {
        return new int[]{44102, 44103, 44101, 44105, 44001, 44002, 44108, 44104, 44109, 44110};
    }

    public static ohy c() {
        if (f15687a == null) {
            synchronized (ohy.class) {
                if (f15687a == null) {
                    f15687a = new ohy();
                }
            }
        }
        return f15687a;
    }

    private ohy() {
        e();
    }

    public void d() {
        this.f.writeLock().lock();
        try {
            this.g = null;
            this.b = 0;
            this.c = 0;
            this.f.writeLock().unlock();
            this.d.writeLock().lock();
            try {
                this.e.clear();
            } finally {
                this.d.writeLock().unlock();
            }
        } catch (Throwable th) {
            this.f.writeLock().unlock();
            throw th;
        }
    }

    public void e() {
        LogUtil.a("FunctionSetDataUnitizedReader", "readUnitizedData start");
        d();
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthDataEx(h(), new HiDataReadResultListener() { // from class: ohy.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("FunctionSetDataUnitizedReader", "readUnitizedData read cost: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                ohy.this.f.writeLock().lock();
                try {
                    ohy.this.g = obj;
                    ohy.this.b = i;
                    ohy.this.c = i2;
                    ohy.this.f.writeLock().unlock();
                    ohy.this.d.readLock().lock();
                    try {
                        for (HiDataReadResultListener hiDataReadResultListener : ohy.this.e) {
                            if (hiDataReadResultListener != null) {
                                hiDataReadResultListener.onResult(obj, i, i2);
                            }
                        }
                    } finally {
                        ohy.this.d.readLock().unlock();
                    }
                } catch (Throwable th) {
                    ohy.this.f.writeLock().unlock();
                    throw th;
                }
            }
        });
    }

    public void a(HiDataReadResultListener hiDataReadResultListener) {
        LogUtil.a("FunctionSetDataUnitizedReader", "readCardData");
        this.f.readLock().lock();
        try {
            Object obj = this.g;
            if (obj == null && this.b == 0) {
                this.d.writeLock().lock();
                try {
                    this.e.add(hiDataReadResultListener);
                    this.d.writeLock().unlock();
                } catch (Throwable th) {
                    this.d.writeLock().unlock();
                    throw th;
                }
            }
            hiDataReadResultListener.onResult(obj, this.b, this.c);
        } finally {
            this.f.readLock().unlock();
        }
    }

    private static List<HiDataReadProOption> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(f());
        arrayList.add(k());
        arrayList.add(i());
        arrayList.add(j());
        arrayList.add(g());
        return arrayList;
    }

    public static void b() {
        if (f15687a != null) {
            synchronized (ohy.class) {
                if (f15687a != null) {
                    f15687a.d();
                    f15687a = null;
                }
            }
        }
    }

    private static HiDataReadProOption f() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2002, 2018, 2105, 2101, 2102});
        hiDataReadOption.setSortOrder(1);
        return HiDataReadProOption.builder().e(hiDataReadOption).d(true).e();
    }

    private static HiDataReadProOption k() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2034});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(16);
        return HiDataReadProOption.builder().e(hiDataReadOption).d(true).e();
    }

    private static HiDataReadProOption i() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        int[] a2 = a();
        hiDataReadOption.setType(a2);
        hiDataReadOption.setCount(a2.length);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        return HiDataReadProOption.builder().e(hiDataReadOption).d(true).e();
    }

    private static HiDataReadProOption j() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(qjv.b());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setReadType(0);
        return HiDataReadProOption.builder().e(hiDataReadOption).d(true).e();
    }

    private static HiDataReadProOption g() {
        long currentTimeMillis = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setType(new int[]{700017});
        return HiDataReadProOption.builder().e(hiDataReadOption).b(1).e();
    }
}

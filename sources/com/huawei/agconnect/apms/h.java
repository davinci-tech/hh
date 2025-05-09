package com.huawei.agconnect.apms;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import com.huawei.agconnect.apms.collect.model.event.interaction.ActivityLoadEvent;
import com.huawei.agconnect.apms.collect.model.event.interaction.ActivityRenderEvent;
import com.huawei.agconnect.apms.collect.model.event.interaction.AppStartEvent;
import com.huawei.agconnect.apms.l0;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes2.dex */
public class h extends o implements Application.ActivityLifecycleCallbacks {
    public static Runnable bcd = new abc();
    public static Runnable cde = new bcd();

    public class abc implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            n abc = n.abc();
            abc.bcd.execute(new l(abc));
        }
    }

    public class bcd implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            n abc = n.abc();
            abc.bcd.execute(new m(abc));
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        z abc2 = z.abc();
        abc2.getClass();
        abc2.ghi = System.currentTimeMillis();
        abc2.bcd = n.abc().bcd();
        l0 abc3 = l0.abc();
        abc3.getClass();
        abc3.lmn = System.currentTimeMillis();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostCreated(Activity activity, Bundle bundle) {
        z abc2 = z.abc();
        abc2.getClass();
        abc2.hij = System.currentTimeMillis();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(Activity activity) {
        WeakReference weakReference = new WeakReference(activity);
        z abc2 = z.abc();
        abc2.getClass();
        abc2.efg = System.currentTimeMillis();
        if (weakReference.get() != null && Build.VERSION.SDK_INT >= 29) {
            Activity activity2 = (Activity) weakReference.get();
            if (!Agent.isDisabled()) {
                String simpleName = activity2.getClass().getSimpleName().length() != 0 ? activity2.getClass().getSimpleName() : "";
                long j = abc2.cde;
                if (j != 0) {
                    long j2 = abc2.efg;
                    long j3 = abc2.hij;
                    abc2.abc(j, simpleName, AppStartEvent.StartType.COLD_START, j2 - j, j3 - j, j2 - j3);
                } else {
                    long j4 = abc2.fgh;
                    if (j4 != 0 && abc2.bcd) {
                        long j5 = abc2.efg;
                        long j6 = abc2.hij;
                        abc2.abc(j4, simpleName, AppStartEvent.StartType.HOT_START, j5 - j4, j6 - j4, j5 - j6);
                    }
                }
                abc2.bcd();
            }
        }
        l0 abc3 = l0.abc();
        abc3.getClass();
        abc3.jkl = System.currentTimeMillis();
        if (weakReference.get() != null && Build.VERSION.SDK_INT >= 29) {
            Activity activity3 = (Activity) weakReference.get();
            if (Agent.isDisabled() || abc3.klm == 0) {
                return;
            }
            String abc4 = abc3.abc(activity3);
            long j7 = abc3.jkl;
            long j8 = abc3.klm;
            long j9 = abc3.hij;
            abc3.mno.execute(new l0.abc(j8, abc4, ActivityLoadEvent.LoadType.COLD_LOAD, j7 - j8, j9 - j8, j7 - j9));
            abc3.klm = 0L;
            abc3.lmn = 0L;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        z abc2 = z.abc();
        abc2.getClass();
        abc2.fgh = System.currentTimeMillis();
        l0 abc3 = l0.abc();
        abc3.getClass();
        abc3.klm = System.currentTimeMillis();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreResumed(Activity activity) {
        l0 abc2 = l0.abc();
        abc2.getClass();
        abc2.hij = System.currentTimeMillis();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        WeakReference weakReference = new WeakReference(activity);
        z abc2 = z.abc();
        abc2.getClass();
        abc2.def = System.currentTimeMillis();
        if (weakReference.get() != null && Build.VERSION.SDK_INT <= 28) {
            Activity activity2 = (Activity) weakReference.get();
            if (!Agent.isDisabled()) {
                String simpleName = activity2.getClass().getSimpleName().length() != 0 ? activity2.getClass().getSimpleName() : "";
                long j = abc2.cde;
                if (j != 0) {
                    abc2.abc(j, simpleName, AppStartEvent.StartType.COLD_START, abc2.def - j, -1L, -1L);
                } else {
                    long j2 = abc2.ghi;
                    if (j2 != 0 && abc2.bcd) {
                        abc2.abc(j2, simpleName, AppStartEvent.StartType.HOT_START, abc2.def - j2, -1L, -1L);
                    }
                }
                abc2.bcd();
            }
        }
        l0 abc3 = l0.abc();
        abc3.getClass();
        abc3.ijk = System.currentTimeMillis();
        if (weakReference.get() != null && Build.VERSION.SDK_INT <= 28) {
            Activity activity3 = (Activity) weakReference.get();
            if (Agent.isDisabled() || abc3.lmn == 0) {
                return;
            }
            String abc4 = abc3.abc(activity3);
            long j3 = abc3.ijk;
            long j4 = abc3.lmn;
            abc3.mno.execute(new l0.abc(j4, abc4, ActivityLoadEvent.LoadType.COLD_LOAD, j3 - j4, -1L, -1L));
            abc3.klm = 0L;
            abc3.lmn = 0L;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        WeakReference weakReference = new WeakReference(activity);
        l0 abc2 = l0.abc();
        if (abc2.cde == null) {
            l0.abc.warn("can not get frame metrics with null frame metrics aggregator.");
        } else if (!Agent.isDisabled() && weakReference.get() != null) {
            Activity activity2 = (Activity) weakReference.get();
            if (abc2.bcd(activity2)) {
                abc2.def.put(activity2, Long.valueOf(System.currentTimeMillis()));
                abc2.cde.abc.abc(activity2);
            }
        }
        this.abc.submit(bcd);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        SparseIntArray[] sparseIntArrayArr;
        int i;
        int i2;
        int i3;
        int i4;
        WeakReference weakReference = new WeakReference(activity);
        l0 abc2 = l0.abc();
        if (abc2.cde == null) {
            l0.abc.warn("can not get frame metrics, because the android support library is not included.");
        } else if (weakReference.get() != null) {
            Activity activity2 = (Activity) weakReference.get();
            String abc3 = abc2.abc(activity2);
            if (abc2.bcd(activity2) && abc2.def.containsKey(activity2)) {
                long currentTimeMillis = System.currentTimeMillis();
                Long l = abc2.def.get(activity2);
                if (l != null) {
                    abc2.def.remove(activity2);
                    try {
                        sparseIntArrayArr = abc2.cde.abc.bcd(activity2);
                    } catch (Exception unused) {
                        l0.abc.warn(String.format(Locale.ENGLISH, "view not hardware accelerated, can not remove %s from frameMetricsAggregator.", abc3));
                        sparseIntArrayArr = null;
                    }
                    if (!Agent.isDisabled()) {
                        int i5 = 0;
                        if (sparseIntArrayArr == null || sparseIntArrayArr.length == 0) {
                            i = 0;
                            i2 = 0;
                        } else {
                            SparseIntArray sparseIntArray = sparseIntArrayArr[0];
                            if (sparseIntArray != null) {
                                i3 = 0;
                                i4 = 0;
                                for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                                    int keyAt = sparseIntArray.keyAt(i6);
                                    int valueAt = sparseIntArray.valueAt(i6);
                                    i5 += valueAt;
                                    if (keyAt > 700) {
                                        i4 += valueAt;
                                    }
                                    if (keyAt > 16) {
                                        i3 += valueAt;
                                    }
                                }
                            } else {
                                i3 = 0;
                                i4 = 0;
                            }
                            int i7 = i5 - abc2.efg;
                            i = i3 - abc2.fgh;
                            i2 = i4 - abc2.ghi;
                            abc2.efg = i5;
                            abc2.fgh = i3;
                            abc2.ghi = i4;
                            i5 = i7;
                        }
                        if (i5 == 0) {
                            l0.abc.warn(String.format(Locale.ENGLISH, "frame aggregator currentTotalFrames is 0， %s does not refresh any frames.", abc3));
                        } else {
                            rst.cde.add(new ActivityRenderEvent(l.longValue(), abc3, currentTimeMillis - l.longValue(), i, i2, i5));
                        }
                    }
                }
            }
        }
        this.abc.submit(cde);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }
}

package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.store.stat.HiHeartRateAndRestHeartRateStat;
import com.huawei.hihealthservice.store.stat.HiTrackStat;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class iso {
    private static Context c;

    /* renamed from: a, reason: collision with root package name */
    private iiz f13583a;

    private iso() {
        this.f13583a = iiz.a(c);
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private static final iso f13584a = new iso();
    }

    public static iso e(Context context) {
        if (context != null) {
            c = context.getApplicationContext();
        }
        return c.f13584a;
    }

    public void e() {
        int d = igm.e().d();
        if (d <= 0) {
            ReleaseLogUtil.d("HiH_StatDBOldData", "statDBOldData() userID <= 0");
            return;
        }
        List<Integer> a2 = iks.e().a(d);
        if (a2 == null || a2.isEmpty()) {
            ReleaseLogUtil.d("HiH_StatDBOldData", "statDBOldData() clients is empty");
            return;
        }
        e(d, a2);
        if (iwe.c(c, d)) {
            try {
                List<HiHealthData> b = this.f13583a.b(a2, 0L, System.currentTimeMillis(), 30001);
                int i = 0;
                if (b != null && !b.isEmpty()) {
                    ReleaseLogUtil.d("HiH_StatDBOldData", "statDBOldData sequenceMetaDatas size is = ", Integer.valueOf(b.size()));
                    for (HiHealthData hiHealthData : b) {
                        hiHealthData.setUserId(d);
                        int c2 = HiDateUtil.c(hiHealthData.getStartTime());
                        if (i != c2) {
                            if (!new HiTrackStat(c).b(hiHealthData)) {
                                iwe.r(c, d);
                                return;
                            }
                            i = c2;
                        }
                    }
                    iwe.r(c, d);
                    return;
                }
                ReleaseLogUtil.d("HiH_StatDBOldData", "statDBOldData sequenceMetaDatas is null");
                iwe.r(c, d);
            } catch (Exception unused) {
                ReleaseLogUtil.d("HiH_StatDBOldData", "statDBOldData Exception");
                iwe.r(c, d);
            }
        }
    }

    private void e(int i, List<Integer> list) {
        for (int a2 = iwe.a(c, i); a2 < 4; a2++) {
            if (a2 == 1) {
                a(i, list, 2);
            } else if (a2 == 2) {
                b(i, list, 3);
            } else if (a2 == 3) {
                d(i, 4);
            }
        }
    }

    private void a(int i, List<Integer> list, int i2) {
        ReleaseLogUtil.e("HiH_StatDBOldData", "statDBOldData upgradeOne start...");
        new HiHeartRateAndRestHeartRateStat(c).b(i, list, 0L, System.currentTimeMillis());
        iwe.e(c, i, i2);
        ReleaseLogUtil.e("HiH_StatDBOldData", "statDBOldData upgradeOne end!");
    }

    private void b(int i, List<Integer> list, int i2) {
        ReleaseLogUtil.e("HiH_StatDBOldData", "statDBOldData upgradeTwo start...");
        ivd.c().e(c);
        List<HiHealthData> b = this.f13583a.b(list, 0L, System.currentTimeMillis(), 30001);
        if (!HiCommonUtil.d(b)) {
            Iterator<HiHealthData> it = b.iterator();
            while (it.hasNext()) {
                it.next().setUserId(i);
            }
            isf.a(c).prepareRealTimeHealthDataStat(b);
            isf.a(c).doRealTimeHealthDataStat();
        }
        iwe.e(c, i, i2);
        ReleaseLogUtil.e("HiH_StatDBOldData", "statDBOldData upgradeTwo end!");
    }

    private void d(int i, int i2) {
        ReleaseLogUtil.e("HiH_StatDBOldData", "statDBOldData upgradeThree start...");
        ivd.c().e(c);
        iwe.e(c, i, i2);
        ReleaseLogUtil.e("HiH_StatDBOldData", "statDBOldData upgradeThree end!");
    }
}

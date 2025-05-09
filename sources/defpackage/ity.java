package defpackage;

import android.content.Context;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import health.compact.a.HiCommonUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class ity {
    private static Context e;

    /* renamed from: a, reason: collision with root package name */
    private List<SyncKey> f13609a;
    private List<Integer> d;

    private ity() {
        this.d = new ArrayList(20);
        b();
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final ity f13610a = new ity();
    }

    private void b() {
        this.d.clear();
        this.d.add(1);
        this.d.add(2);
        this.d.add(4);
        this.d.add(7);
        this.d.add(9);
        this.d.add(11);
        this.d.add(13);
        this.d.add(16);
        this.d.add(19);
        this.d.add(21);
        this.d.add(900000000);
        this.d.addAll(HiHealthDictManager.d(e).d());
        this.d.add(12);
        if (iuz.i()) {
            return;
        }
        this.d.add(14);
        this.d.add(15);
        this.d.add(18);
    }

    public static ity a(Context context) {
        e = context.getApplicationContext();
        return e.f13610a;
    }

    public void c() throws iut {
        b();
        this.f13609a = iuz.b(e, 2, this.d);
    }

    public void d() {
        if (HiCommonUtil.d(this.f13609a)) {
            return;
        }
        this.f13609a = null;
    }

    public List<SyncKey> d(int i) {
        if (HiCommonUtil.d(this.f13609a)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<SyncKey> it = this.f13609a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SyncKey next = it.next();
            if (i == next.getType().intValue()) {
                if (next.getVersion().longValue() == 0) {
                    ReleaseLogUtil.a("HiH_HiSyncVerMgr", "cloudNoDt,tp=", Integer.valueOf(i));
                } else {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public List<SyncKey> a(List<Integer> list) {
        if (HiCommonUtil.d(this.f13609a)) {
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        for (Integer num : list) {
            Iterator<SyncKey> it = this.f13609a.iterator();
            while (true) {
                if (it.hasNext()) {
                    SyncKey next = it.next();
                    if (num.equals(next.getType())) {
                        if (next.getVersion().longValue() == 0) {
                            ReleaseLogUtil.a("HiH_HiSyncVerMgr", "cloudNoDt,tp=", num);
                        } else {
                            arrayList.add(next);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public Map<Integer, Long> c(List<Integer> list) throws iut {
        return iuz.c(e, list);
    }
}

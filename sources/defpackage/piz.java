package defpackage;

import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.activity.active.writehelper.SportSimplifyItemCallback;
import com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment;
import defpackage.kwy;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class piz {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16152a;
    private final SportHistoryListFragment.m c = new SportHistoryListFragment.m();
    private long d;
    private SportSimplifyItemCallback e;

    public void a() {
        this.f16152a = true;
    }

    public void e(int i, long j, SportSimplifyItemCallback sportSimplifyItemCallback) {
        if (j != this.d || this.f16152a) {
            this.e = sportSimplifyItemCallback;
            this.d = j;
            this.f16152a = false;
            b(i, HiDateUtil.t(j), HiDateUtil.f(j));
        }
    }

    private void b(int i, long j, long j2) {
        this.c.a(new kwy.a().a(j).e(j2).d(0).a(0).b(i).d(), new e(i, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<rdr> list) {
        this.e.onResponse(0, list);
    }

    static class e implements SportHistoryListFragment.IMultiResponseCallback {
        private final int c;
        private final WeakReference<piz> d;

        e(int i, piz pizVar) {
            this.d = new WeakReference<>(pizVar);
            this.c = i;
        }

        @Override // com.huawei.ui.main.stories.history.fragment.SportHistoryListFragment.IMultiResponseCallback
        public void onNotify(int[] iArr, Object[] objArr) {
            List list;
            LogUtil.a("Track_SportListSearchHelper", "Simply cb errorCode : ", Integer.valueOf(iArr.length), "Simply cb datas : ", Integer.valueOf(objArr.length));
            piz pizVar = this.d.get();
            if (pizVar == null) {
                ReleaseLogUtil.d("Track_SportListSearchHelper", "ReadTrackSimplifyListCallback sportHistory mWeakReference is null");
                return;
            }
            List list2 = null;
            try {
                list = koq.e(objArr[0], HiHealthData.class) ? (List) objArr[0] : null;
                try {
                    if (koq.e(objArr[1], WorkoutRecord.class)) {
                        list2 = (List) objArr[1];
                    }
                } catch (ClassCastException e) {
                    e = e;
                    ReleaseLogUtil.d("Track_SportListSearchHelper", "parseTrackSimplifyData ", e.getMessage());
                    ArrayList arrayList = new ArrayList(10);
                    rdt.e(list, arrayList);
                    pizVar.b(rdu.c(arrayList, list2, this.c));
                }
            } catch (ClassCastException e2) {
                e = e2;
                list = null;
            }
            ArrayList arrayList2 = new ArrayList(10);
            rdt.e(list, arrayList2);
            pizVar.b(rdu.c(arrayList2, list2, this.c));
        }
    }
}

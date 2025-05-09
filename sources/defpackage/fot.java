package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class fot {
    private static final Object b = new Object();
    private static final String c = "Suggestion_HistoryOrderHelper";

    /* renamed from: a, reason: collision with root package name */
    private String f12588a;
    private String d;
    private List<FitWorkout> e;
    private List<FitWorkout> h;
    private List<FitWorkout> j;

    static class e {
        public static final fot d = new fot();
    }

    private fot() {
        this.f12588a = "";
        this.j = new ArrayList(10);
        this.e = new ArrayList(10);
        this.h = new ArrayList(10);
        if (TextUtils.isEmpty(this.f12588a)) {
            String b2 = ash.b("trained_order");
            this.d = b2;
            this.f12588a = b2;
            LogUtil.b(c, "origin order in sp : ", b2);
        }
    }

    public static fot a() {
        return e.d;
    }

    private String i(List<FitWorkout> list) {
        if (!koq.c(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                sb.append(fitWorkout.acquireId());
                sb.append(",");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public fot a(FitWorkout fitWorkout) {
        if (fitWorkout != null) {
            String str = c;
            LogUtil.a(str, "updateOrder: move name:", fitWorkout.acquireName(), " id: ", fitWorkout.acquireId());
            int indexOf = this.e.indexOf(fitWorkout);
            if (indexOf > 0) {
                LogUtil.a(str, "updateOrder: move:", fitWorkout.acquireName(), " to first");
                this.e.remove(fitWorkout);
                this.e.add(0, fitWorkout);
                this.f12588a = i(this.e);
            } else if (indexOf == -1) {
                LogUtil.a(str, "updateOrder: add new workout:", fitWorkout.acquireName(), " to first");
                this.e.add(0, fitWorkout);
                this.f12588a = i(this.e);
            }
            LogUtil.a(str, "updateOrder joined workout all size:", Integer.valueOf(this.e.size()));
        }
        return this;
    }

    public fot c() {
        asc.e().b(new Runnable() { // from class: fot.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(fot.c, "on destroy saveNewOrder-----", fot.this.f12588a);
                if (fot.this.e.isEmpty()) {
                    LogUtil.b(fot.c, "mOrderWorkouts.isEmpty()");
                } else {
                    if (fot.this.f12588a.equals(fot.this.d)) {
                        return;
                    }
                    LogUtil.a(fot.c, "origin_order: ", fot.this.d, "new_order: ", fot.this.f12588a);
                    fot fotVar = fot.this;
                    fotVar.d = fotVar.f12588a;
                    ash.a("trained_order", fot.this.d);
                }
            }
        });
        return this;
    }

    public List<FitWorkout> c(List<FitWorkout> list) {
        if (koq.b(list)) {
            LogUtil.b(c, "reorderList workouts is null");
            return list;
        }
        this.j = list;
        this.e = list;
        if (TextUtils.isEmpty(this.d)) {
            LogUtil.b(c, "have no origin order, use the default order of the joined list");
            String i = i(list);
            this.d = i;
            this.f12588a = i;
            ash.a("trained_order", i);
            return list;
        }
        return d(list, this.f12588a);
    }

    private List<FitWorkout> d(List<FitWorkout> list, String str) {
        final List<String> asList = Arrays.asList(str.split(","));
        boolean c2 = c(asList, list);
        LogUtil.a(c, "isSort:", Boolean.valueOf(c2), "reorderList: ", str, "-originOrder.size()", Integer.valueOf(asList.size()), "-workouts.size():", Integer.valueOf(list.size()));
        if (c2) {
            Collections.sort(list, new Comparator<FitWorkout>() { // from class: fot.5
                @Override // java.util.Comparator
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public int compare(FitWorkout fitWorkout, FitWorkout fitWorkout2) {
                    return fot.this.d((List<String>) asList, fitWorkout) - fot.this.d((List<String>) asList, fitWorkout2);
                }
            });
            this.f12588a = str;
        } else {
            ArrayList arrayList = new ArrayList(10);
            for (FitWorkout fitWorkout : list) {
                if (fitWorkout != null && asList.contains(fitWorkout.acquireId())) {
                    arrayList.add(fitWorkout);
                }
            }
            Collections.sort(arrayList, new Comparator<FitWorkout>() { // from class: fot.3
                @Override // java.util.Comparator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public int compare(FitWorkout fitWorkout2, FitWorkout fitWorkout3) {
                    return fot.this.d((List<String>) asList, fitWorkout2) - fot.this.d((List<String>) asList, fitWorkout3);
                }
            });
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                FitWorkout fitWorkout2 = (FitWorkout) it.next();
                if (fitWorkout2 != null) {
                    list.remove(fitWorkout2);
                }
            }
            list.addAll(0, arrayList);
            this.f12588a = i(list);
        }
        this.e = list;
        LogUtil.a(c, "joined workout all size:", Integer.valueOf(list.size()));
        return this.e;
    }

    private boolean c(List<String> list, List<FitWorkout> list2) {
        return (list == null || list2 == null || list.size() != list2.size()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(List<String> list, FitWorkout fitWorkout) {
        if (list.indexOf(fitWorkout.acquireId()) == -1) {
            LogUtil.h(c, "reorderList data from net diff between db have add new workout:", fitWorkout.acquireName(), " id: ", fitWorkout.acquireId());
        }
        return list.indexOf(fitWorkout.acquireId()) + 1;
    }

    public void e(List<FitWorkout> list) {
        this.e.removeAll(list);
        this.j.removeAll(list);
        String i = i(this.e);
        this.f12588a = i;
        String str = c;
        LogUtil.a(str, "removeWorkout-->>mNewOrder:", i);
        LogUtil.a(str, "joined workout all size:", Integer.valueOf(this.e.size()));
    }

    public fot d(List<FitWorkout> list) {
        synchronized (b) {
            LogUtil.a(c, "add fitworkout list from data to mTempListOne");
            if (koq.b(list)) {
                return this;
            }
            for (FitWorkout fitWorkout : list) {
                if (fitWorkout != null) {
                    if (!this.h.contains(fitWorkout)) {
                        this.h.add(fitWorkout);
                    } else {
                        LogUtil.a(c, "mTempListOne have already contains fitworkout:", fitWorkout.acquireName());
                    }
                }
            }
            return this;
        }
    }

    public ArrayList<FitWorkout> b(List<FitWorkout> list) {
        synchronized (b) {
            ArrayList<FitWorkout> arrayList = new ArrayList<>(10);
            if (koq.b(list)) {
                LogUtil.b(c, "getFitworkList list is null");
                return arrayList;
            }
            LogUtil.a(c, "origin size: ", Integer.valueOf(list.size()));
            for (FitWorkout fitWorkout : list) {
                if (fitWorkout != null) {
                    int indexOf = this.h.indexOf(fitWorkout);
                    if (indexOf != -1) {
                        arrayList.add(this.h.get(indexOf));
                    } else {
                        LogUtil.b(c, "----mTempListOne have no: ", fitWorkout.acquireName());
                    }
                }
            }
            LogUtil.a(c, "----return size: ", Integer.valueOf(arrayList.size()));
            return arrayList;
        }
    }

    public static String b(long j) {
        return ffy.d(arx.b(), R.string._2130842426_res_0x7f02133a, UnitUtil.a("yyyy/MM/dd", j), arx.b().getString(R.string._2130847826_res_0x7f022852));
    }

    public void a(List<FitWorkout> list) {
        if (koq.b(list)) {
            LogUtil.b(c, "sortCustomCourseByPublishDate list is null");
        } else {
            Collections.sort(list, new Comparator<FitWorkout>() { // from class: fot.2
                @Override // java.util.Comparator
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public int compare(FitWorkout fitWorkout, FitWorkout fitWorkout2) {
                    if (fitWorkout == null || fitWorkout2 == null) {
                        return fitWorkout == null ? -1 : 1;
                    }
                    long publishDate = fitWorkout.getPublishDate();
                    if (publishDate < 0) {
                        publishDate += 1000;
                    }
                    long publishDate2 = fitWorkout2.getPublishDate();
                    if (publishDate2 < 0) {
                        publishDate2 += 1000;
                    }
                    return publishDate - publishDate2 > 0 ? -1 : 1;
                }
            });
        }
    }
}

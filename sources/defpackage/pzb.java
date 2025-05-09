package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.util.Pair;
import com.huawei.health.R;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarDietMonitorCardView;
import defpackage.pzb;
import health.compact.a.HiDateUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class pzb {

    /* renamed from: a, reason: collision with root package name */
    private long f16349a;
    private LinearLayout b;
    private List<b> c;
    private Context d;
    private volatile long e;
    private int f;
    private ViewGroup h;
    private Handler i;

    public pzb(Context context, ViewGroup viewGroup) {
        if (context == null || viewGroup == null) {
            return;
        }
        this.d = context;
        this.h = viewGroup;
        ((HealthSubHeader) viewGroup.findViewById(R.id.diet_monitor_subheader)).setSubHeaderBackgroundColor(0);
        this.b = (LinearLayout) viewGroup.findViewById(R.id.blood_sugar_diet_monitor_container);
    }

    public void a() {
        this.h.setVisibility(8);
    }

    public void e(long j, boolean z) {
        long j2 = j * 60000;
        long t = HiDateUtil.t(j2);
        if (!z && this.f16349a == t) {
            this.h.setVisibility(this.f);
            return;
        }
        synchronized (this) {
            this.e = System.currentTimeMillis();
            LogUtil.a("BloodSugarDietMonitorViewHolder", "tryShowMonitorView, eventTag=", Long.valueOf(this.e));
            List<b> list = this.c;
            if (list != null) {
                list.clear();
            }
        }
        b();
        this.b.removeAllViews();
        this.f16349a = t;
        LogUtil.a("BloodSugarDietMonitorViewHolder", "tryShowMonitorView, ", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(t)));
        c(this.e, t, HiDateUtil.f(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f = 0;
        this.h.setVisibility(0);
    }

    private void b() {
        this.f = 8;
        this.h.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, List<HiHealthData> list, qul qulVar) {
        synchronized (this) {
            if (j != this.e) {
                LogUtil.h("BloodSugarDietMonitorViewHolder", "eventTag is different");
                return;
            }
            if (!list.isEmpty()) {
                if (this.c == null) {
                    this.c = new ArrayList(0);
                    this.i = new Handler(Looper.getMainLooper());
                }
                qaa b2 = b(qulVar);
                b2.e(a(list));
                this.c.add(new b(b2, d(list), deb.b()));
                LogUtil.a("BloodSugarDietMonitorViewHolder", "taskList=", Integer.valueOf(this.c.size()));
            } else {
                LogUtil.a("BloodSugarDietMonitorViewHolder", "hiHealthDataList is empty");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, AtomicInteger atomicInteger) {
        int decrementAndGet = atomicInteger.decrementAndGet();
        LogUtil.a("BloodSugarDietMonitorViewHolder", "onQueryDataFinish count=", Integer.valueOf(decrementAndGet));
        if (decrementAndGet == 0) {
            synchronized (this) {
                List<b> list = this.c;
                if (list == null) {
                    LogUtil.a("BloodSugarDietMonitorViewHolder", "onQueryDataFinish mTaskList == null");
                } else {
                    Collections.sort(list, new Comparator() { // from class: pzc
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return pzb.a((pzb.b) obj, (pzb.b) obj2);
                        }
                    });
                    this.i.post(new a(this, j));
                }
            }
        }
    }

    static /* synthetic */ int a(b bVar, b bVar2) {
        int i;
        int i2;
        if (bVar.b.k() != bVar2.b.k()) {
            i = bVar.b.k();
            i2 = bVar2.b.k();
        } else {
            i = bVar.b.i();
            i2 = bVar2.b.i();
        }
        return i - i2;
    }

    private void c(long j, long j2, long j3) {
        int b2 = DateFormatUtil.b(j2);
        int b3 = DateFormatUtil.b(j3);
        ReleaseLogUtil.b("BloodSugarDietMonitorViewHolder", "getDiet startDate ", Integer.valueOf(b2), " endDate ", Integer.valueOf(b3));
        qvz.d(b2, b3, new d(this, j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, List<quh> list) {
        quh quhVar;
        if (koq.b(list) || (quhVar = list.get(0)) == null) {
            return;
        }
        if (quhVar.d() == null) {
            LogUtil.a("BloodSugarDietMonitorViewHolder", "overview is null");
            return;
        }
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            LogUtil.a("BloodSugarDietMonitorViewHolder", "meals is empty");
            return;
        }
        LogUtil.a("BloodSugarDietMonitorViewHolder", "meals size=", Integer.valueOf(a2.size()));
        AtomicInteger atomicInteger = new AtomicInteger(a2.size());
        for (qul qulVar : a2) {
            if (scj.a(qulVar) <= 0.0f || qulVar.g() == 0) {
                LogUtil.a("BloodSugarDietMonitorViewHolder", "countCarbohydrate=", Float.valueOf(scj.a(qulVar)), ", setTime=", Long.valueOf(qulVar.g()));
                e(j, atomicInteger);
            } else {
                long g = qulVar.g() * 1000;
                e(j, atomicInteger, g, g + AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL, qulVar);
            }
        }
    }

    private void e(long j, AtomicInteger atomicInteger, long j2, long j3, qul qulVar) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{2108});
        hiDataReadOption.setStartTime(j2);
        hiDataReadOption.setEndTime(j3);
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setSortOrder(0);
        pyw.e().b(j2, j3, hiDataReadOption.getType(), new e(this, j, atomicInteger, qulVar));
    }

    private float a(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (e(it.next()) == 1003) {
                i++;
            }
        }
        return new BigDecimal((i * 100) / list.size()).setScale(2, 4).floatValue();
    }

    private int e(HiHealthData hiHealthData) {
        try {
            return Integer.parseInt(qjv.a(BaseApplication.getContext(), hiHealthData.getType(), hiHealthData.getFloatValue()).get("HEALTH_BLOOD_SUGAR_LEVEL_KEY"));
        } catch (NumberFormatException unused) {
            LogUtil.h("BloodSugarDietMonitorViewHolder", "levelValue is String, parse to Integer error");
            return 1000;
        }
    }

    private List<Pair<Long, Float>> d(List<HiHealthData> list) {
        if (list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            arrayList.add(new Pair(Long.valueOf(hiHealthData.getEndTime()), Float.valueOf(hiHealthData.getFloatValue())));
        }
        return arrayList;
    }

    private qaa b(qul qulVar) {
        qaa qaaVar = new qaa();
        String b2 = scj.b(this.d, qulVar.h());
        qaaVar.d(qulVar.g());
        qaaVar.c(qulVar.h());
        qaaVar.a(qulVar.a());
        qaaVar.d(b2);
        qaaVar.e(scj.b(qulVar));
        qaaVar.b(qulVar.b());
        qaaVar.c(scj.a(qulVar));
        qaaVar.c(qulVar.d());
        return qaaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        int b2 = scj.b(i);
        if (b2 == -1) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_ANALYSIS_FEED_CONTINUE_2040138.value();
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(b2));
        gge.e(value, hashMap);
    }

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<pzb> f16350a;
        private final long c;

        a(pzb pzbVar, long j) {
            this.f16350a = new WeakReference<>(pzbVar);
            this.c = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            pzb pzbVar = this.f16350a.get();
            if (pzbVar == null) {
                return;
            }
            if (this.c == pzbVar.e) {
                if (pzbVar.c.isEmpty()) {
                    LogUtil.h("BloodSugarDietMonitorViewHolder", "AddViewRunnable taskList is empty");
                    return;
                }
                LogUtil.a("BloodSugarDietMonitorViewHolder", "showMonitorView, eventTag=", Long.valueOf(this.c));
                pzbVar.c();
                for (int i = 0; i < pzbVar.c.size(); i++) {
                    b bVar = (b) pzbVar.c.get(i);
                    BloodSugarDietMonitorCardView bloodSugarDietMonitorCardView = new BloodSugarDietMonitorCardView(pzbVar.d);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                    if (i != 0) {
                        layoutParams.topMargin = pzbVar.b.getContext().getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
                    }
                    pzbVar.b.addView(bloodSugarDietMonitorCardView, layoutParams);
                    long m = 1000 * bVar.b.m();
                    bloodSugarDietMonitorCardView.d(bVar.b, bVar.f16351a, m, AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL + m, bVar.c);
                    pzbVar.e(bVar.b.k());
                }
                return;
            }
            LogUtil.h("BloodSugarDietMonitorViewHolder", "AddViewRunnable eventTag is different");
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private final List<Pair<Long, Float>> f16351a;
        public final qaa b;
        private final float[] c;

        b(qaa qaaVar, List<Pair<Long, Float>> list, float[] fArr) {
            this.b = qaaVar;
            this.f16351a = list;
            this.c = fArr;
        }
    }

    static class e implements IBaseResponseCallback {
        private final qul b;
        private final AtomicInteger c;
        private final long d;
        private final WeakReference<pzb> e;

        e(pzb pzbVar, long j, AtomicInteger atomicInteger, qul qulVar) {
            this.e = new WeakReference<>(pzbVar);
            this.d = j;
            this.c = atomicInteger;
            this.b = qulVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            pzb pzbVar = this.e.get();
            if (pzbVar == null) {
                return;
            }
            if (obj instanceof List) {
                pzbVar.c(this.d, (List<HiHealthData>) obj, this.b);
            }
            pzbVar.e(this.d, this.c);
        }
    }

    static class d implements ResponseCallback<List<quh>> {

        /* renamed from: a, reason: collision with root package name */
        private final long f16352a;
        private final WeakReference<pzb> e;

        d(pzb pzbVar, long j) {
            this.e = new WeakReference<>(pzbVar);
            this.f16352a = j;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            LogUtil.a("BloodSugarDietMonitorViewHolder", "DietCallBack errorCode ", Integer.valueOf(i), " list ", list);
            pzb pzbVar = this.e.get();
            if (pzbVar != null) {
                pzbVar.b(this.f16352a, list);
            } else {
                ReleaseLogUtil.a("BloodSugarDietMonitorViewHolder", "DietCallBack view is null");
            }
        }
    }
}

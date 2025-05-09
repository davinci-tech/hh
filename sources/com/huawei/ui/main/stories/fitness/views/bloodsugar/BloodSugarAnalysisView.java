package com.huawei.ui.main.stories.fitness.views.bloodsugar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.BloodSugarAnalysisView;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.util.BloodSugarSuperRuleEngineUtil;
import defpackage.koq;
import defpackage.pyw;
import defpackage.pzy;
import defpackage.pzz;
import defpackage.qaa;
import defpackage.qab;
import defpackage.qjv;
import defpackage.qkg;
import defpackage.qkh;
import defpackage.quh;
import defpackage.qul;
import defpackage.qvz;
import defpackage.scj;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class BloodSugarAnalysisView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private List<HiHealthData> f9955a;
    private List<pzy> b;
    private final Context c;
    private BloodSugarAnalysisDietView d;
    private BloodSugarAnalysisSportAdapter e;
    private HealthSubHeader f;
    private LinearLayout g;
    private boolean h;
    private final Handler i;
    private BloodSugarAnalysisDietView j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private LinearLayout p;
    private BloodSugarAnalysisDietView q;
    private long r;
    private String s;
    private final BloodSugarSuperRuleEngineUtil t;
    private HealthRecycleView u;
    private List<qab> v;

    public BloodSugarAnalysisView(Context context) {
        this(context, null);
    }

    public BloodSugarAnalysisView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarAnalysisView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h = false;
        this.l = false;
        this.m = false;
        this.o = false;
        this.k = false;
        this.n = false;
        this.f9955a = new ArrayList();
        this.r = 0L;
        this.s = "BLOOD_SUGAR_CONTINUE";
        this.c = context;
        this.i = new d(this);
        this.t = new BloodSugarSuperRuleEngineUtil(context);
        i();
    }

    public void setShowType(String str) {
        this.s = str;
    }

    private void i() {
        Context context = this.c;
        if (context == null) {
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.health_data_blood_sugar_analysis, this);
        this.g = (LinearLayout) inflate.findViewById(R.id.blood_sugar_diet);
        this.p = (LinearLayout) inflate.findViewById(R.id.blood_sugar_sport);
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.blood_sugar_analysis_diet_title);
        this.f = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
        ((HealthSubHeader) inflate.findViewById(R.id.blood_sugar_analysis_sport_title)).setSubHeaderBackgroundColor(0);
        this.u = (HealthRecycleView) inflate.findViewById(R.id.blood_sugar_analysis_sport_recycle);
        this.d = (BloodSugarAnalysisDietView) inflate.findViewById(R.id.blood_sugar_analysis_breakfast);
        this.q = (BloodSugarAnalysisDietView) inflate.findViewById(R.id.blood_sugar_analysis_lunch);
        this.j = (BloodSugarAnalysisDietView) inflate.findViewById(R.id.blood_sugar_analysis_dinner);
        this.d.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.p.setVisibility(8);
        this.e = new BloodSugarAnalysisSportAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.c);
        linearLayoutManager.setOrientation(1);
        this.u.setLayoutManager(linearLayoutManager);
        this.u.setAdapter(this.e);
    }

    private void d(long j, int i2, final List<pzy> list) {
        if (j < 0) {
            return;
        }
        long j2 = j * 60000;
        final long d2 = TimeUtil.d(j2);
        final long b2 = TimeUtil.b(j2);
        if (i2 == 0) {
            if (koq.b(list)) {
                this.b = null;
                return;
            }
            boolean z = true;
            if (this.b != null && list.size() == this.b.size()) {
                boolean z2 = false;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    pzy pzyVar = this.b.get(i3);
                    pzy pzyVar2 = list.get(i3);
                    if (pzyVar.k() != pzyVar2.k() || pzyVar.a() != pzyVar2.a()) {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (this.k || z) {
                this.k = false;
                this.b = list;
                this.h = false;
                this.l = false;
                this.m = false;
                ThreadPoolManager.d().execute(new Runnable() { // from class: pyq
                    @Override // java.lang.Runnable
                    public final void run() {
                        BloodSugarAnalysisView.this.c(d2, b2, list);
                    }
                });
                return;
            }
            return;
        }
        if (this.n || this.r != d2) {
            this.n = false;
            this.r = d2;
            qkh.c().b(d2, b2, new i(this));
        }
    }

    public /* synthetic */ void c(long j, long j2, List list) {
        if (CardConstants.b()) {
            LogUtil.a("BloodSugarAnalysisView", "the user has join diet diary");
            b(j, j2, (List<pzy>) list);
        }
    }

    public void setRefreshDiet(boolean z) {
        this.k = z;
    }

    public void setRefreshSport(boolean z) {
        this.n = z;
    }

    public void c() {
        this.r = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: pyn
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarAnalysisView.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: pyr
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarAnalysisView.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        this.p.setVisibility(8);
    }

    private void b(long j, long j2, List<pzy> list) {
        int b2 = DateFormatUtil.b(j);
        int b3 = DateFormatUtil.b(j2);
        ReleaseLogUtil.b("BloodSugarAnalysisView", "getDietData startDate ", Integer.valueOf(b2), " endDate ", Integer.valueOf(b3));
        qvz.d(b2, b3, new b(this, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<pzy> list, List<qul> list2) {
        int i2;
        HashMap hashMap = new HashMap(0);
        for (pzy pzyVar : list) {
            if (pzyVar.o() == 2008) {
                i2 = 10;
            } else if (pzyVar.o() == 2010) {
                i2 = 20;
            } else if (pzyVar.o() == 2012) {
                i2 = 30;
            } else {
                LogUtil.a("BloodSugarAnalysisView", "Type wrong!");
                i2 = -1;
            }
            Iterator<qul> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    qul next = it.next();
                    if (i2 > 0 && i2 == next.h()) {
                        hashMap.put(pzyVar, next);
                        break;
                    }
                }
            }
        }
        if (hashMap.isEmpty()) {
            LogUtil.c("BloodSugarAnalysisView", "data = null");
            a();
        } else {
            this.t.d(new a(this, hashMap));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<pzy, qul> map) {
        for (Map.Entry<pzy, qul> entry : map.entrySet()) {
            pzy key = entry.getKey();
            qul value = entry.getValue();
            if (value != null) {
                float a2 = key.e().a();
                float a3 = key.d().a();
                this.t.a(scj.b(value.h()), a2, a3, new e(this, value));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(qaa qaaVar) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = qaaVar;
        this.i.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDietData(qaa qaaVar) {
        if (qaaVar == null) {
            d();
            return;
        }
        int d2 = qaaVar.d();
        if (d2 == 0) {
            this.d.setDietData(qaaVar);
            this.h = true;
        } else if (d2 == 1) {
            this.q.setDietData(qaaVar);
            this.l = true;
        } else if (d2 == 2) {
            this.j.setDietData(qaaVar);
            this.m = true;
        } else {
            LogUtil.h("BloodSugarAnalysisView", "TYPE is wrong");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public qaa c(String str, qul qulVar) {
        String b2 = scj.b(this.c, qulVar.h());
        int round = Math.round(scj.a(qulVar));
        qaa qaaVar = new qaa();
        qaaVar.d(scj.b(qulVar.h()));
        qaaVar.b(qulVar.b());
        qaaVar.c(round);
        qaaVar.d(b2);
        qaaVar.e(scj.b(qulVar));
        qaaVar.c(qulVar.h());
        qaaVar.c(qulVar.d());
        if (!"".equals(str)) {
            int identifier = this.c.getResources().getIdentifier(str, "string", BaseApplication.e().getPackageName());
            if (round > 0) {
                str = this.c.getResources().getString(identifier, a(String.valueOf(round)));
            } else {
                str = this.c.getResources().getString(identifier);
            }
        }
        qaaVar.c(str);
        return qaaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSportBloodSugarData() {
        long startTime;
        long endTime;
        if (this.v.size() == 1) {
            qab qabVar = this.v.get(0);
            startTime = qabVar.getStartTime() - 1800000;
            endTime = qabVar.getEndTime();
        } else {
            int size = this.v.size();
            qab qabVar2 = this.v.get(0);
            startTime = this.v.get(size - 1).getStartTime() - 1800000;
            endTime = qabVar2.getEndTime();
        }
        pyw.e().b(startTime, endTime + 1800000, new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2106}, new c(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ArrayList arrayList = new ArrayList(16);
        HiHealthData hiHealthData = new HiHealthData();
        HiHealthData hiHealthData2 = new HiHealthData();
        for (int i2 = 0; i2 < this.v.size(); i2++) {
            pzz pzzVar = new pzz();
            qab qabVar = this.v.get(i2);
            if (qabVar != null) {
                long startTime = qabVar.getStartTime();
                long endTime = qabVar.getEndTime();
                Iterator<HiHealthData> it = this.f9955a.iterator();
                long j = startTime;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HiHealthData next = it.next();
                    if (next != null) {
                        long startTime2 = next.getStartTime();
                        if (startTime > startTime2) {
                            long j2 = startTime - startTime2;
                            if (j2 < j) {
                                hiHealthData = next;
                                j = j2;
                            }
                        }
                        if (startTime2 > endTime) {
                            hiHealthData2 = next;
                            break;
                        }
                    }
                }
                if (j <= 1800000) {
                    pzzVar.d(hiHealthData);
                    pzzVar.e(hiHealthData2);
                    pzzVar.b(qabVar);
                    arrayList.add(pzzVar);
                }
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("BloodSugarAnalysisView", "bloodSugarSportBeanList is empty");
            if (this.p.getVisibility() == 0) {
                g();
                return;
            }
            return;
        }
        this.t.d(new a(this, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSportBloodSugarSuggest(List<pzz> list) {
        AtomicInteger atomicInteger = new AtomicInteger(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            pzz pzzVar = list.get(i2);
            this.t.e(pzzVar.b().getFloatValue(), pzzVar.d().getFloatValue(), new g(this, list, atomicInteger, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<pzz> list) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = list;
        this.i.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String[] strArr) {
        if (strArr == null) {
            return "";
        }
        Resources resources = this.c.getResources();
        int identifier = resources.getIdentifier(str, "string", BaseApplication.e().getPackageName());
        if (strArr.length == 0) {
            return resources.getString(identifier);
        }
        if (strArr.length == 1) {
            return resources.getString(identifier, a(strArr[0]));
        }
        return strArr.length == 2 ? resources.getString(identifier, a(strArr[0]), a(strArr[1])) : "";
    }

    private String a(String str) {
        try {
            return this.c.getResources().getQuantityString(R.plurals._2130903246_res_0x7f0300ce, Integer.parseInt(str), Integer.valueOf(Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodSugarAnalysisView", "Integer.parseInt(num) is error");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSportBloodSugarView(List<pzz> list) {
        if (koq.b(list) || !j()) {
            LogUtil.h("BloodSugarAnalysisView", "mBloodSugarSportBean is null or is not FingerTip!");
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
            this.e.b(list);
        }
    }

    private void o() {
        if (this.o || this.h || this.l || this.m) {
            this.d.setVisibility(0);
            this.q.setVisibility(0);
            this.j.setVisibility(0);
        } else {
            this.d.setVisibility(8);
            this.q.setVisibility(8);
            this.j.setVisibility(8);
        }
    }

    public void e() {
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.p.setVisibility(8);
    }

    private boolean j() {
        return "BLOOD_SUGAR_FINGER_TIP".equals(this.s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public void d() {
        boolean z = this.h;
        if (!z && !this.l && !this.m) {
            LogUtil.a("BloodSugarAnalysisView", "DietView is GONE");
            this.d.setDietRootVisibility(8);
            this.q.setDietRootVisibility(8);
            this.j.setDietRootVisibility(8);
            return;
        }
        if (z) {
            this.d.setDietRootVisibility(0);
        } else {
            this.d.setDietRootVisibility(8);
        }
        if (this.l) {
            this.q.setDietRootVisibility(0);
        } else {
            this.q.setDietRootVisibility(8);
        }
        if (this.m) {
            this.j.setDietRootVisibility(0);
        } else {
            this.j.setDietRootVisibility(8);
        }
    }

    public void setSportDiffView(long j) {
        d(j, 1, (List<pzy>) null);
    }

    public void setDiffData(Map<Long, IStorageModel> map, long j) {
        d(j, 0, e(map));
    }

    private List<pzy> e(Map<Long, IStorageModel> map) {
        if (map == null || map.size() == 0) {
            this.o = false;
            this.f.setVisibility(8);
            o();
            return null;
        }
        Map<Long, IStorageModel> a2 = a(map);
        ArrayList arrayList = new ArrayList(a2.size());
        Iterator<Map.Entry<Long, IStorageModel>> it = a2.entrySet().iterator();
        while (it.hasNext()) {
            IStorageModel value = it.next().getValue();
            if (value instanceof pzy) {
                arrayList.add((pzy) value);
            }
        }
        Collections.sort(arrayList, new Comparator() { // from class: pyu
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(((pzy) obj).o(), ((pzy) obj2).o());
                return compare;
            }
        });
        if (koq.b(arrayList) || !j()) {
            this.g.setVisibility(8);
            this.o = false;
            this.f.setVisibility(8);
            o();
            return null;
        }
        this.g.setVisibility(0);
        this.o = true;
        o();
        int size = arrayList.size();
        if (size == 1) {
            c(arrayList);
        } else if (size == 2) {
            b(arrayList);
        } else if (size == 3) {
            d(arrayList);
        } else {
            LogUtil.h("BloodSugarAnalysisView", " mBloodSugarModels size is wrong ");
        }
        return arrayList;
    }

    private void d(BloodSugarAnalysisDietView bloodSugarAnalysisDietView, int i2, pzy pzyVar) {
        bloodSugarAnalysisDietView.setVisibility(0);
        this.f.setVisibility(0);
        qaa qaaVar = new qaa();
        qaaVar.d(i2);
        qaaVar.d(pzyVar.a());
        qaaVar.e(pzyVar.j());
        bloodSugarAnalysisDietView.setDiffData(qaaVar, pzyVar);
    }

    private void c(List<pzy> list) {
        pzy pzyVar = list.get(0);
        if (pzyVar.o() == 2008) {
            this.q.setVisibility(8);
            this.j.setVisibility(8);
            d(this.d, 0, pzyVar);
        } else if (pzyVar.o() == 2010) {
            this.d.setVisibility(8);
            this.j.setVisibility(8);
            d(this.q, 1, pzyVar);
        } else {
            if (pzyVar.o() == 2012) {
                this.d.setVisibility(8);
                this.q.setVisibility(8);
                d(this.j, 2, pzyVar);
                return;
            }
            LogUtil.h("BloodSugarAnalysisView", " updateOneDifference Type wrong!");
        }
    }

    private void b(List<pzy> list) {
        pzy pzyVar = list.get(0);
        pzy pzyVar2 = list.get(1);
        int o = pzyVar.o();
        int o2 = pzyVar2.o();
        if (o == 2008 && o2 == 2010) {
            this.j.setVisibility(8);
            d(this.d, 0, pzyVar);
            d(this.q, 1, pzyVar2);
        } else if (o == 2010 && o2 == 2012) {
            this.d.setVisibility(8);
            d(this.q, 1, pzyVar);
            d(this.j, 2, pzyVar2);
        } else {
            this.q.setVisibility(8);
            d(this.d, 0, pzyVar);
            d(this.j, 2, pzyVar2);
        }
    }

    private void d(List<pzy> list) {
        pzy pzyVar = list.get(0);
        pzy pzyVar2 = list.get(1);
        pzy pzyVar3 = list.get(2);
        d(this.d, 0, pzyVar);
        d(this.q, 1, pzyVar2);
        d(this.j, 2, pzyVar3);
    }

    private Map<Long, IStorageModel> a(Map<Long, IStorageModel> map) {
        HashMap hashMap = new HashMap(16);
        if (map == null || map.size() == 0) {
            LogUtil.h("BloodSugarAnalysisView", "fitnessBloodSugarDayData bloodModelMaps can not null");
            return hashMap;
        }
        List<qkg> c2 = c(map);
        if (koq.c(c2)) {
            ArrayList arrayList = new ArrayList(16);
            ArrayList arrayList2 = new ArrayList(16);
            ArrayList arrayList3 = new ArrayList(16);
            ArrayList arrayList4 = new ArrayList(16);
            ArrayList arrayList5 = new ArrayList(16);
            ArrayList arrayList6 = new ArrayList(16);
            for (qkg qkgVar : c2) {
                switch ((int) qkgVar.o()) {
                    case 2008:
                        arrayList.add(qkgVar);
                        break;
                    case 2009:
                        arrayList2.add(qkgVar);
                        break;
                    case 2010:
                        arrayList3.add(qkgVar);
                        break;
                    case 2011:
                        arrayList4.add(qkgVar);
                        break;
                    case 2012:
                        arrayList5.add(qkgVar);
                        break;
                    case 2013:
                        arrayList6.add(qkgVar);
                        break;
                }
            }
            b(hashMap, arrayList, arrayList2);
            b(hashMap, arrayList3, arrayList4);
            b(hashMap, arrayList5, arrayList6);
        }
        return hashMap;
    }

    private List<qkg> c(Map<Long, IStorageModel> map) {
        ArrayList arrayList = new ArrayList(map.size());
        Iterator<Map.Entry<Long, IStorageModel>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            IStorageModel value = it.next().getValue();
            if (value instanceof pzy) {
                qkg qkgVar = new qkg();
                qkgVar.b(r1.a());
                qkgVar.c(r1.o());
                qkgVar.a(((pzy) value).k());
                arrayList.add(qkgVar);
            }
        }
        return arrayList;
    }

    private void b(Map<Long, IStorageModel> map, List<qkg> list, List<qkg> list2) {
        qkg valueAt;
        if (list == null || list2 == null || list.isEmpty() || list2.isEmpty()) {
            LogUtil.h("BloodSugarAnalysisView", "The beforeMealList or afterMealList is Null or Empty");
            return;
        }
        SparseArray<qkg> dvP_ = dvP_(list);
        SparseArray<qkg> dvP_2 = dvP_(list2);
        if (dvP_ == null || dvP_2 == null) {
            LogUtil.h("BloodSugarAnalysisView", "The beforeMealUniqueData or afterMealUniqueData is Empty");
            return;
        }
        int size = dvP_.size();
        for (int i2 = 0; i2 < size; i2++) {
            qkg qkgVar = dvP_2.get(dvP_.keyAt(i2));
            if (qkgVar != null && !Double.isNaN(qkgVar.m()) && (valueAt = dvP_.valueAt(i2)) != null && !Double.isNaN(valueAt.m())) {
                float m = ((float) valueAt.m()) - ((float) qkgVar.m());
                pzy pzyVar = new pzy(Math.abs(m), (int) valueAt.o(), Math.abs(m) > 3.3f ? 2 : 1);
                pzyVar.b(valueAt.h());
                float m2 = (float) valueAt.m();
                int o = (int) valueAt.o();
                pzy pzyVar2 = new pzy(m2, o, a(this.c, m2, o));
                pzyVar2.b(valueAt.h());
                pzyVar.e(pzyVar2);
                float m3 = (float) qkgVar.m();
                int o2 = (int) qkgVar.o();
                pzy pzyVar3 = new pzy(m3, o2, a(this.c, m3, o2));
                pzyVar3.b(qkgVar.h());
                pzyVar.c(pzyVar3);
                map.put(Long.valueOf(valueAt.h()), pzyVar);
            }
        }
    }

    private SparseArray<qkg> dvP_(List<qkg> list) {
        SparseArray<qkg> sparseArray = new SparseArray<>();
        if (list == null) {
            LogUtil.h("BloodSugarAnalysisView", "The healthDataList is null, return null");
            return sparseArray;
        }
        Collections.sort(list, new Comparator() { // from class: pyt
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((qkg) obj).h(), ((qkg) obj2).h());
                return compare;
            }
        });
        for (qkg qkgVar : list) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(qkgVar.h());
            sparseArray.put((calendar.get(1) * 10000) + (calendar.get(2) * 100) + calendar.get(5), qkgVar);
        }
        return sparseArray;
    }

    private int a(Context context, float f, int i2) {
        Map<String, String> a2 = qjv.a(context, i2, f);
        if (!a2.containsKey("HEALTH_BLOOD_SUGAR_LEVEL_KEY")) {
            return 1000;
        }
        try {
            return Integer.parseInt(a2.get("HEALTH_BLOOD_SUGAR_LEVEL_KEY"));
        } catch (NumberFormatException unused) {
            LogUtil.h("BloodSugarAnalysisView", "levelValue is String, parse to Integer error");
            return 1000;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewClickInstrumentation.clickOnView(view);
    }

    static class a implements BloodSugarSuperRuleEngineUtil.LoadJsCallBack {
        private final Object b;
        private final WeakReference<BloodSugarAnalysisView> d;

        a(BloodSugarAnalysisView bloodSugarAnalysisView, Object obj) {
            this.d = new WeakReference<>(bloodSugarAnalysisView);
            this.b = obj;
        }

        @Override // com.huawei.ui.main.stories.health.util.BloodSugarSuperRuleEngineUtil.LoadJsCallBack
        public void onLoad(boolean z) {
            BloodSugarAnalysisView bloodSugarAnalysisView = this.d.get();
            if (bloodSugarAnalysisView == null) {
                return;
            }
            if (!z) {
                LogUtil.h("BloodSugarAnalysisView", "Load js code failed");
                return;
            }
            Object obj = this.b;
            if (obj instanceof List) {
                bloodSugarAnalysisView.setSportBloodSugarSuggest((List) obj);
            } else if (obj instanceof Map) {
                bloodSugarAnalysisView.d((Map<pzy, qul>) obj);
            } else {
                LogUtil.a("BloodSugarAnalysisView", "Load js code success, but do not any things");
            }
        }
    }

    static class e implements BloodSugarSuperRuleEngineUtil.BloodSugarFactCallBack {

        /* renamed from: a, reason: collision with root package name */
        private final qul f9956a;
        private final WeakReference<BloodSugarAnalysisView> e;

        e(BloodSugarAnalysisView bloodSugarAnalysisView, qul qulVar) {
            this.e = new WeakReference<>(bloodSugarAnalysisView);
            this.f9956a = qulVar;
        }

        @Override // com.huawei.ui.main.stories.health.util.BloodSugarSuperRuleEngineUtil.BloodSugarFactCallBack
        public void onFactCallback(boolean z, String str, String[] strArr) {
            BloodSugarAnalysisView bloodSugarAnalysisView = this.e.get();
            if (bloodSugarAnalysisView == null) {
                return;
            }
            LogUtil.c("BloodSugarAnalysisView", "isMatched=", Boolean.valueOf(z), ", suggestion=", str);
            bloodSugarAnalysisView.c((z && StringUtils.i(str)) ? bloodSugarAnalysisView.c(str, this.f9956a) : bloodSugarAnalysisView.c("", this.f9956a));
        }
    }

    static class g implements BloodSugarSuperRuleEngineUtil.BloodSugarFactCallBack {

        /* renamed from: a, reason: collision with root package name */
        private final List<pzz> f9957a;
        private final int b;
        private final AtomicInteger d;
        private final WeakReference<BloodSugarAnalysisView> e;

        g(BloodSugarAnalysisView bloodSugarAnalysisView, List<pzz> list, AtomicInteger atomicInteger, int i) {
            this.e = new WeakReference<>(bloodSugarAnalysisView);
            this.d = atomicInteger;
            this.f9957a = list;
            this.b = i;
        }

        @Override // com.huawei.ui.main.stories.health.util.BloodSugarSuperRuleEngineUtil.BloodSugarFactCallBack
        public void onFactCallback(boolean z, String str, String[] strArr) {
            BloodSugarAnalysisView bloodSugarAnalysisView = this.e.get();
            if (bloodSugarAnalysisView == null) {
                return;
            }
            LogUtil.c("BloodSugarAnalysisView", "isMatched=", Boolean.valueOf(z), ", suggestion=", str);
            if (z && StringUtils.i(str)) {
                this.f9957a.get(this.b).c(bloodSugarAnalysisView.a(str, strArr));
            }
            if (this.d.decrementAndGet() == 0) {
                bloodSugarAnalysisView.e(this.f9957a);
            }
        }
    }

    /* loaded from: classes9.dex */
    static class d extends BaseHandler<BloodSugarAnalysisView> {
        d(BloodSugarAnalysisView bloodSugarAnalysisView) {
            super(bloodSugarAnalysisView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dvQ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodSugarAnalysisView bloodSugarAnalysisView, Message message) {
            int i = message.what;
            if (i == 0) {
                bloodSugarAnalysisView.d();
                return;
            }
            if (i == 1) {
                bloodSugarAnalysisView.setDietData(message.obj instanceof qaa ? (qaa) message.obj : null);
            } else {
                if (i != 2) {
                    return;
                }
                bloodSugarAnalysisView.setSportBloodSugarView(message.obj instanceof List ? (List) message.obj : null);
            }
        }
    }

    public static class c implements IBaseResponseCallback {
        private final WeakReference<BloodSugarAnalysisView> c;

        c(BloodSugarAnalysisView bloodSugarAnalysisView) {
            this.c = new WeakReference<>(bloodSugarAnalysisView);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodSugarAnalysisView bloodSugarAnalysisView = this.c.get();
            if (bloodSugarAnalysisView == null) {
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                if (!list.isEmpty()) {
                    LogUtil.a("BloodSugarAnalysisView", "getFingertipsBloodSugarData hasData count = ", Integer.valueOf(list.size()));
                    Collections.sort(list, new Comparator() { // from class: pys
                        @Override // java.util.Comparator
                        public final int compare(Object obj2, Object obj3) {
                            return BloodSugarAnalysisView.c.b((HiHealthData) obj2, (HiHealthData) obj3);
                        }
                    });
                    bloodSugarAnalysisView.f9955a = list;
                    bloodSugarAnalysisView.f();
                    return;
                }
            }
            LogUtil.h("BloodSugarAnalysisView", "getSportBloodSugarData is Empty");
            bloodSugarAnalysisView.g();
        }

        public static /* synthetic */ int b(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return (int) (hiHealthData.getStartTime() - hiHealthData2.getStartTime());
        }
    }

    static class i implements IBaseResponseCallback {
        private final WeakReference<BloodSugarAnalysisView> c;

        i(BloodSugarAnalysisView bloodSugarAnalysisView) {
            this.c = new WeakReference<>(bloodSugarAnalysisView);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodSugarAnalysisView bloodSugarAnalysisView = this.c.get();
            if (bloodSugarAnalysisView == null) {
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                if (!list.isEmpty()) {
                    bloodSugarAnalysisView.v = list;
                    bloodSugarAnalysisView.getSportBloodSugarData();
                    return;
                }
            }
            bloodSugarAnalysisView.g();
        }
    }

    static class b implements ResponseCallback<List<quh>> {
        private final WeakReference<BloodSugarAnalysisView> c;
        private final List<pzy> d;

        public b(BloodSugarAnalysisView bloodSugarAnalysisView, List<pzy> list) {
            this.c = new WeakReference<>(bloodSugarAnalysisView);
            this.d = list;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<quh> list) {
            LogUtil.a("BloodSugarAnalysisView", "DietDiaryCallback errorCode ", Integer.valueOf(i), " list ", list);
            BloodSugarAnalysisView bloodSugarAnalysisView = this.c.get();
            if (bloodSugarAnalysisView == null) {
                ReleaseLogUtil.a("BloodSugarAnalysisView", "DietDiaryCallback view is null");
                return;
            }
            if (koq.b(list)) {
                bloodSugarAnalysisView.a();
                return;
            }
            quh quhVar = list.get(0);
            if (quhVar == null) {
                bloodSugarAnalysisView.a();
                return;
            }
            if (quhVar.d() == null) {
                bloodSugarAnalysisView.a();
                return;
            }
            List<qul> a2 = quhVar.a();
            if (koq.b(a2)) {
                bloodSugarAnalysisView.a();
            } else {
                bloodSugarAnalysisView.c(this.d, a2);
            }
        }
    }
}

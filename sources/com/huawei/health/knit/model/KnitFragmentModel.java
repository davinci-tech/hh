package com.huawei.health.knit.model;

import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.model.KnitFragmentModel;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class KnitFragmentModel extends ViewModel {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2520a = false;
    private final MutableLiveData<List<SectionBean>> b;
    private final MutableLiveData<SectionBean> d;
    private final List<SectionBean> e;

    public KnitFragmentModel() {
        LogUtil.a("KnitFragmentModel", "KnitFragmentModel");
        this.e = new ArrayList();
        this.b = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public void d(List<SectionBean> list) {
        LogUtil.a("KnitFragmentModel", "setSectionBeansListWithoutPost");
        if (koq.b(list)) {
            LogUtil.b("KnitFragmentModel", "do not setSectionBeans with empty or null beansList!");
            return;
        }
        a(list);
        f();
        Iterator<SectionBean> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().e(this);
        }
        Collections.sort(this.e, new Comparator() { // from class: eam
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return KnitFragmentModel.a((SectionBean) obj, (SectionBean) obj2);
            }
        });
        h();
        for (SectionBean sectionBean : this.e) {
            Object[] objArr = new Object[4];
            objArr[0] = "dp: ";
            objArr[1] = sectionBean.c();
            objArr[2] = ", online data: ";
            objArr[3] = sectionBean.i() == null ? Constants.NULL : "not null";
            LogUtil.a("KnitFragmentModel", objArr);
        }
    }

    public static /* synthetic */ int a(SectionBean sectionBean, SectionBean sectionBean2) {
        return sectionBean2.h() - sectionBean.h();
    }

    private void h() {
        ArrayList<SectionBean> arrayList = new ArrayList(this.e);
        this.e.clear();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (SectionBean sectionBean : arrayList) {
            if (sectionBean != null) {
                int o = sectionBean.o();
                if (o == 51 || o == 52) {
                    arrayList2.add(sectionBean);
                } else if (o == 1033) {
                    arrayList3.add(sectionBean);
                } else {
                    this.e.add(sectionBean);
                }
            }
        }
        SectionBean b = b(arrayList2);
        if (b != null) {
            this.e.add(0, b);
        }
        SectionBean b2 = b(arrayList3);
        if (b2 != null) {
            this.e.add(0, b2);
        }
        this.e.addAll(0, e(arrayList3));
    }

    private List<SectionBean> e(List<SectionBean> list) {
        if (koq.b(list)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            SectionBean sectionBean = list.get(i);
            if (sectionBean != null && sectionBean.m() == null) {
                arrayList.add(sectionBean);
            }
        }
        return arrayList;
    }

    private SectionBean b(List<SectionBean> list) {
        ResourceContentBase content;
        if (koq.b(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            SectionBean sectionBean = list.get(i);
            if (sectionBean != null) {
                ResourceBriefInfo m = sectionBean.m();
                long currentTimeMillis = System.currentTimeMillis();
                if (m != null && currentTimeMillis >= m.getEffectiveTime() && currentTimeMillis <= m.getExpirationTime() && (content = m.getContent()) != null && !TextUtils.isEmpty(content.getContent())) {
                    arrayList.add(sectionBean);
                }
            }
        }
        if (koq.b(arrayList)) {
            return null;
        }
        Collections.sort(arrayList, new Comparator<SectionBean>() { // from class: com.huawei.health.knit.model.KnitFragmentModel.2
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(SectionBean sectionBean2, SectionBean sectionBean3) {
                ResourceBriefInfo m2 = sectionBean2.m();
                ResourceBriefInfo m3 = sectionBean3.m();
                if (m2.getPriority() == m3.getPriority()) {
                    return Long.compare(m3.getModifyTime(), m2.getModifyTime());
                }
                return m3.getPriority() - m2.getPriority();
            }
        });
        LogUtil.a("KnitFragmentModel", "validList: ", arrayList);
        return (SectionBean) arrayList.get(0);
    }

    private void a(List<SectionBean> list) {
        SectionBean[] sectionBeanArr = (SectionBean[]) this.e.toArray(new SectionBean[0]);
        ArrayList arrayList = new ArrayList(this.e);
        for (SectionBean sectionBean : sectionBeanArr) {
            if (sectionBean != null && sectionBean.m() != null) {
                Iterator<SectionBean> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        SectionBean next = it.next();
                        if (next == null || next.m() == null || !TextUtils.equals(next.m().getResourceId(), sectionBean.m().getResourceId())) {
                        }
                    } else {
                        sectionBean.e(true);
                        break;
                    }
                }
            }
        }
        LogUtil.a("KnitFragmentModel", "onChanged init card list size: ", Integer.valueOf(list.size()), ", old card list size: ", Integer.valueOf(arrayList.size()));
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            SectionBean sectionBean2 = list.get(i);
            LogUtil.a("KnitFragmentModel", "new card i = ", Integer.valueOf(i), ", newCard = ", sectionBean2);
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    break;
                }
                SectionBean sectionBean3 = (SectionBean) arrayList.get(i2);
                if (sectionBean2.o() == sectionBean3.o() && sectionBean3.m() == null) {
                    LogUtil.a("KnitFragmentModel", "find type = ", Integer.valueOf(sectionBean2.o()));
                    sectionBean3.a(sectionBean2);
                    break;
                } else {
                    if (sectionBean3.m() != null && sectionBean2.m() != null && TextUtils.equals(sectionBean3.m().getResourceId(), sectionBean2.m().getResourceId())) {
                        sectionBean3.a(sectionBean2);
                        break;
                    }
                    i2++;
                }
            }
            if (i2 >= arrayList.size()) {
                LogUtil.a("KnitFragmentModel", "onChanged not found at i = " + i + ", type = " + sectionBean2.o());
                arrayList2.add(Integer.valueOf(i));
            }
        }
        this.e.clear();
        this.e.addAll(arrayList);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            SectionBean sectionBean4 = list.get(((Integer) it2.next()).intValue());
            if (sectionBean4 != null && !sectionBean4.p()) {
                this.e.add(sectionBean4);
            }
        }
    }

    private void f() {
        SectionBean sectionBean = null;
        boolean z = false;
        boolean z2 = false;
        for (SectionBean sectionBean2 : this.e) {
            if (!z) {
                z = sectionBean2.o() == 31;
                sectionBean = sectionBean2;
            }
            if (!z2) {
                z2 = sectionBean2.o() == 61;
            }
        }
        if (z && z2) {
            sectionBean.c(SectionBean.Operation.REMOVE);
            this.e.remove(sectionBean);
            this.d.setValue(sectionBean);
        }
    }

    public List<SectionBean> a() {
        return this.e;
    }

    public void e(Context context, SectionBean.LoadReason loadReason) {
        LogUtil.a("KnitFragmentModel", "loadSectionBeans, loadReason: ", loadReason);
        for (int i = 0; i < this.e.size(); i++) {
            SectionBean sectionBean = this.e.get(i);
            if (sectionBean != null) {
                LogUtil.a("KnitFragmentModel", "dp: ", sectionBean.c());
                sectionBean.a(context, loadReason);
            }
        }
    }

    public void a(Context context) {
        for (int i = 0; i < this.e.size(); i++) {
            SectionBean sectionBean = this.e.get(i);
            if (sectionBean != null) {
                LogUtil.a("KnitFragmentModel", "dp: ", sectionBean.c());
                sectionBean.d(context);
            }
        }
    }

    public void e() {
        if (koq.b(this.e)) {
            return;
        }
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.u();
            }
        }
    }

    public void j() {
        LogUtil.a("KnitFragmentModel", "onResume");
        if (koq.b(this.e)) {
            return;
        }
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.z();
            }
        }
    }

    public void c() {
        LogUtil.a("KnitFragmentModel", "onConfigurationChanged");
        if (koq.b(this.e)) {
            return;
        }
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.x();
            }
        }
    }

    public void d() {
        if (koq.b(this.e)) {
            return;
        }
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.ad();
            }
        }
    }

    public void i() {
        if (koq.b(this.e)) {
            return;
        }
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.ac();
                KnitDataProvider c = sectionBean.c();
                if (c != null) {
                    c.onStop();
                }
            }
        }
    }

    public void c(boolean z) {
        if (koq.b(this.e)) {
            return;
        }
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.d(z);
            }
        }
    }

    public void b(SectionBean sectionBean) {
        LogUtil.a("KnitFragmentModel", "updateSection");
        this.d.setValue(sectionBean);
    }

    public void b(LifecycleOwner lifecycleOwner, Observer<List<SectionBean>> observer) {
        LogUtil.a("KnitFragmentModel", "observeOverAll");
        this.b.observe(lifecycleOwner, observer);
    }

    public void d(LifecycleOwner lifecycleOwner, Observer<SectionBean> observer) {
        LogUtil.a("KnitFragmentModel", "observeIndividual");
        this.d.observe(lifecycleOwner, observer);
    }

    public void a(LifecycleOwner lifecycleOwner, Observer<SectionBean> observer) {
        LogUtil.a("KnitFragmentModel", "unregisterAllObservers");
        this.b.removeObservers(lifecycleOwner);
        this.d.removeObservers(lifecycleOwner);
        if (observer != null) {
            this.d.removeObserver(observer);
        }
    }

    public void c(LifecycleOwner lifecycleOwner, Observer<SectionBean> observer) {
        LogUtil.a("KnitFragmentModel", "onDestroy with lifecycleOwner");
        a(lifecycleOwner, observer);
        for (SectionBean sectionBean : this.e) {
            if (sectionBean != null) {
                sectionBean.v();
            }
        }
    }

    public void e(int i) {
        LogUtil.a("KnitFragmentModel", "updateGpsSignal");
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            SectionBean sectionBean = this.e.get(i2);
            if (sectionBean != null && sectionBean.o() == 62) {
                sectionBean.a(i);
            }
        }
    }

    public List<SectionBean> b() {
        return this.e;
    }

    public void b(Observer<SectionBean> observer) {
        this.d.observeForever(observer);
    }
}

package com.huawei.ui.commonui.subtab;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.viewpager.HealthEmptyFragment;
import com.huawei.ui.commonui.viewpager.HealthNativeViewPager;
import com.huawei.uikit.hwsubtab.widget.HwSubTabListener;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;
import defpackage.koq;
import defpackage.nqu;
import defpackage.sms;
import defpackage.smy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class HealthSubTabPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener, HwSubTabWidget.OnSubTabChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private FragmentTransaction f8953a;
    private final FragmentManager b;
    protected HealthSubTabWidget c;
    private Fragment d;
    protected ViewPager e;
    private boolean f;
    private boolean g;
    private final Handler h;
    private ArrayList<d> i;
    private boolean j;
    private int m;
    private final List<nqu> o;

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
    public void onSubTabReselected(smy smyVar) {
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
    public void onSubTabUnselected(smy smyVar) {
    }

    public HealthSubTabPagerAdapter(FragmentManager fragmentManager, ViewPager viewPager, HealthSubTabWidget healthSubTabWidget) {
        super(fragmentManager);
        this.f = true;
        this.f8953a = null;
        this.i = new ArrayList<>();
        this.d = null;
        this.j = false;
        this.o = new ArrayList(2);
        this.g = true;
        this.m = 0;
        this.h = new c(this);
        this.b = fragmentManager;
        this.c = healthSubTabWidget;
        this.e = viewPager;
        viewPager.setAdapter(this);
        this.e.addOnPageChangeListener(this);
        this.c.setOnSubTabChangeListener(this);
    }

    public void a() {
        LogUtil.a("HealthSubTabPagerAdapter", "unbindViews");
        ViewPager viewPager = this.e;
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
        }
        HealthSubTabWidget healthSubTabWidget = this.c;
        if (healthSubTabWidget != null) {
            healthSubTabWidget.setOnSubTabChangeListener(null);
        }
    }

    public void b(smy smyVar, nqu nquVar, boolean z) {
        if (smyVar == null || nquVar == null) {
            LogUtil.h("HealthSubTabPagerAdapter", "Parameter subTab and fragment of method add SubTab should not be null.");
            return;
        }
        for (nqu nquVar2 : this.o) {
            if (nquVar2.c() == nquVar.c() || nquVar2.a() == nquVar.a()) {
                LogUtil.b("HealthSubTabPagerAdapter", "try to add duplicated bean");
                return;
            }
        }
        smyVar.e(nquVar);
        this.o.add(nquVar);
        this.c.a(smyVar, z);
        notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        nqu e = e(i);
        if (e != null) {
            return e.a();
        }
        return new HealthEmptyFragment();
    }

    private nqu e(int i) {
        int size = this.o.size();
        if (i < 0 || i >= size) {
            return null;
        }
        return this.o.get(i);
    }

    public void a(List<nqu> list, int i) {
        this.o.clear();
        if (list != null) {
            this.o.addAll(list);
        }
        ViewPager viewPager = this.e;
        if (viewPager instanceof HealthNativeViewPager) {
            ((HealthNativeViewPager) viewPager).setInitItem(i);
        }
        notifyDataSetChanged();
    }

    private boolean a(nqu nquVar, nqu nquVar2) {
        return nquVar != null && nquVar.equals(nquVar2);
    }

    private int b(nqu nquVar) {
        return this.o.indexOf(nquVar);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.o.size();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (!this.f) {
            LogUtil.b("HealthSubTabPagerAdapter", "onPageScrolled not accessed");
            return;
        }
        if (this.g) {
            this.c.setIsViewPagerScroll(true);
            this.c.setSubTabScrollingOffsets(i, f);
            b();
            i();
        }
        if (f == 0.0f && this.m == this.e.getCurrentItem()) {
            this.g = true;
            b();
            this.c.setIsViewPagerScroll(false);
            this.c.setSubTabClicked(false);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.f) {
            this.c.setSubTabSelected(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (!this.f) {
            LogUtil.b("HealthSubTabPagerAdapter", "onPageScrollStateChanged not accessed");
        } else if (i == 0) {
            b();
            this.c.setIsViewPagerScroll(false);
        }
    }

    private void e(smy smyVar) {
        Object j = smyVar.j();
        if (!(j instanceof nqu)) {
            LogUtil.b("HealthSubTabPagerAdapter", "tabTag is not an instance of FragmentSubTabPagerBean");
            return;
        }
        nqu nquVar = (nqu) j;
        int size = this.o.size();
        for (int i = 0; i < size; i++) {
            if (this.o.get(i) == nquVar) {
                notifyDataSetChanged();
                this.e.setCurrentItem(i);
                LogUtil.a("HealthSubTabPagerAdapter", "found tab, index: ", Integer.valueOf(i));
                return;
            }
        }
        LogUtil.b("HealthSubTabPagerAdapter", "found no tabs, ", j, " mSubTabBeans: ", this.o);
    }

    public void e(List<nqu> list) {
        if (list == null || list.size() != this.o.size()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Collections.swap(this.o, i, this.o.indexOf(list.get(i)));
        }
        notifyDataSetChanged();
    }

    public List<nqu> c() {
        ArrayList arrayList = new ArrayList();
        for (nqu nquVar : this.o) {
            if (nquVar.c() != -2 && nquVar.c() != -3) {
                arrayList.add(nquVar);
            }
        }
        return arrayList;
    }

    public boolean c(nqu nquVar) {
        nqu nquVar2;
        nqu nquVar3;
        smy a2;
        this.e.removeOnPageChangeListener(this);
        this.e.addOnPageChangeListener(this);
        this.e.setOffscreenPageLimit(1);
        int indexOf = this.o.indexOf(nquVar);
        if (koq.b(this.o, indexOf)) {
            LogUtil.b("HealthSubTabPagerAdapter", "detachedAllFragment failed, cause retainFragBean = ", nquVar, " not exists!");
            return false;
        }
        int i = indexOf - 1;
        if (koq.d(this.o, i)) {
            nquVar2 = new nqu(new HealthEmptyFragment(), "", -2);
            this.o.set(i, nquVar2);
        } else {
            nquVar2 = null;
        }
        int i2 = indexOf + 1;
        if (koq.d(this.o, i2)) {
            nquVar3 = new nqu(new HealthEmptyFragment(), "", -3);
            this.o.set(i2, nquVar3);
        } else {
            nquVar3 = null;
        }
        int subTabCount = this.c.getSubTabCount();
        for (int i3 = 0; i3 < subTabCount; i3++) {
            if (i3 != indexOf && i3 != i && i3 != i2 && (a2 = this.c.a(i3)) != null) {
                a2.e(null);
                a2.d((HwSubTabListener) null);
            }
        }
        if (c(nquVar2, nquVar) || c(nquVar2, nquVar3) || c(nquVar, nquVar3)) {
            LogUtil.b("HealthSubTabPagerAdapter", "found same beans between the retained bean and its neighbours");
            return false;
        }
        this.o.clear();
        if (nquVar2 != null) {
            this.o.add(nquVar2);
        }
        this.o.add(nquVar);
        if (nquVar3 != null) {
            this.o.add(nquVar3);
        }
        notifyDataSetChanged();
        return true;
    }

    private boolean c(nqu nquVar, nqu nquVar2) {
        return (nquVar == null || nquVar2 == null || (nquVar.a() != nquVar2.a() && nquVar.c() != nquVar2.c())) ? false : true;
    }

    public boolean c(List<nqu> list, nqu nquVar) {
        if (list.size() != this.c.getSubTabCount()) {
            LogUtil.b("HealthSubTabPagerAdapter", "attachedAllFragment, size error.");
            return false;
        }
        this.e.removeOnPageChangeListener(this);
        this.e.addOnPageChangeListener(this);
        for (nqu nquVar2 : this.o) {
            for (int i = 0; i < list.size(); i++) {
                nqu nquVar3 = list.get(i);
                if (nquVar2 != null && nquVar3 != null && nquVar2.c() == nquVar3.c()) {
                    list.set(i, nquVar2);
                }
            }
        }
        List<nqu> a2 = nqu.a(list);
        if (a2.size() < list.size()) {
            LogUtil.b("HealthSubTabPagerAdapter", "attachedAllFragment found duplicated beans: ", Integer.valueOf(list.size()), " ", Integer.valueOf(a2.size()));
            return false;
        }
        this.o.clear();
        this.o.addAll(list);
        int i2 = 0;
        while (true) {
            if (i2 < this.o.size()) {
                nqu nquVar4 = this.o.get(i2);
                smy a3 = this.c.a(i2);
                if (nquVar4 == null || nquVar4 == nquVar || a3 == null) {
                    Object[] objArr = new Object[5];
                    objArr[0] = Boolean.valueOf(nquVar4 != null);
                    objArr[1] = " bean is not retainFragBean: ";
                    objArr[2] = Boolean.valueOf(nquVar4 != nquVar);
                    objArr[3] = " subTab is not null: ";
                    objArr[4] = Boolean.valueOf(a3 != null);
                    LogUtil.b("attachedAllFragment setTag failed, bean is not null: ", objArr);
                } else {
                    a3.e(nquVar4);
                }
                i2++;
            } else {
                notifyDataSetChanged();
                return true;
            }
        }
    }

    public void b(int i) {
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            nqu nquVar = this.o.get(i2);
            if (nquVar != null && nquVar.c() == i) {
                c(i2);
            }
        }
    }

    public void c(int i) {
        if (koq.d(this.o, i)) {
            smy a2 = this.c.a(i);
            if (a2 != null) {
                a2.e(null);
                a2.d((HwSubTabListener) null);
            }
            this.c.e(i);
            this.o.remove(i);
            notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        d dVar;
        if (this.i.size() > i && (dVar = this.i.get(i)) != null) {
            if (dVar.f8954a == i && dVar.d != null && dVar.d.a() != null && dVar.d.a().isAdded()) {
                return dVar;
            }
            e();
        }
        if (this.f8953a == null) {
            this.f8953a = this.b.beginTransaction();
        }
        while (this.i.size() <= i) {
            this.i.add(null);
        }
        d dVar2 = new d(e(i), i);
        this.i.set(i, dVar2);
        Fragment item = getItem(i);
        if (item != null) {
            item.setMenuVisibility(false);
            item.setUserVisibleHint(false);
            this.f8953a.remove(item);
            this.f8953a.add(viewGroup.getId(), item);
        }
        return dVar2;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        d dVar = (d) obj;
        if (this.f8953a == null) {
            this.f8953a = this.b.beginTransaction();
        }
        if (koq.d(this.i, i)) {
            this.i.set(i, null);
        }
        Fragment c2 = dVar.c();
        if (c2 != null) {
            this.f8953a.remove(c2);
        } else {
            LogUtil.b("HealthSubTabPagerAdapter", "destroyItem failed, cause fragment is null!");
        }
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment c2 = ((d) obj).c();
        Fragment fragment = this.d;
        if (c2 != fragment) {
            if (fragment != null) {
                fragment.setMenuVisibility(false);
                this.d.setUserVisibleHint(false);
            }
            if (c2 != null) {
                c2.setMenuVisibility(true);
                c2.setUserVisibleHint(true);
            }
            this.d = c2;
        }
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.f8953a;
        if (fragmentTransaction != null) {
            try {
                fragmentTransaction.commitNowAllowingStateLoss();
            } catch (IllegalStateException unused) {
                LogUtil.b("HealthSubTabPagerAdapter", "finishUpdate IllegalStateException");
                this.f8953a.commitAllowingStateLoss();
            }
            this.f8953a = null;
        }
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        Fragment c2;
        return (obj instanceof d) && (c2 = ((d) obj).c()) != null && c2.getView() == view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        this.j = true;
        d dVar = (d) obj;
        int indexOf = this.i.indexOf(dVar);
        if (indexOf < 0) {
            return -1;
        }
        nqu nquVar = dVar.d;
        if (a(nquVar, e(indexOf))) {
            return -1;
        }
        d dVar2 = this.i.get(indexOf);
        int b = b(nquVar);
        if (b < 0) {
            b = -2;
        }
        if (dVar2 != null) {
            dVar2.f8954a = b;
        }
        return b;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        e();
    }

    private void e() {
        if (this.j) {
            this.j = false;
            ArrayList<d> arrayList = new ArrayList<>(this.i.size());
            for (int i = 0; i < this.i.size(); i++) {
                arrayList.add(null);
            }
            Iterator<d> it = this.i.iterator();
            while (it.hasNext()) {
                d next = it.next();
                if (next != null && next.f8954a >= 0) {
                    while (arrayList.size() <= next.f8954a) {
                        arrayList.add(null);
                    }
                    arrayList.set(next.f8954a, next);
                }
            }
            this.i = arrayList;
        }
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabWidget.OnSubTabChangeListener
    public void onSubTabSelected(smy smyVar) {
        if (this.c.getSubTabAppearance() == 1 || (this.c.c() && sms.d(this.c.getContext()) != 2)) {
            this.g = false;
        }
        this.m = smyVar.e();
        this.f = false;
        e(smyVar);
        this.f = true;
    }

    public void d() {
        d(this.o);
    }

    public void d(List<nqu> list) {
        if (koq.b(list)) {
            return;
        }
        int i = 0;
        while (i < list.size()) {
            nqu nquVar = list.get(i);
            if (nquVar != null && (nquVar.c() == -2 || nquVar.c() == -3)) {
                list.remove(nquVar);
                i--;
            }
            i++;
        }
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private int f8954a;
        private final nqu d;

        public d(nqu nquVar, int i) {
            this.d = nquVar;
            this.f8954a = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Fragment c() {
            nqu nquVar = this.d;
            if (nquVar != null) {
                return nquVar.a();
            }
            return null;
        }
    }

    private void i() {
        this.h.sendEmptyMessageDelayed(100, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.h.removeMessages(100);
    }

    static class c extends BaseHandler<HealthSubTabPagerAdapter> {
        public c(HealthSubTabPagerAdapter healthSubTabPagerAdapter) {
            super(healthSubTabPagerAdapter);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cGs_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HealthSubTabPagerAdapter healthSubTabPagerAdapter, Message message) {
            HealthSubTabWidget healthSubTabWidget;
            if (message.what == 100 && (healthSubTabWidget = healthSubTabPagerAdapter.c) != null) {
                LogUtil.a("HealthSubTabPagerAdapter", "restore isViewPagerScroll");
                healthSubTabPagerAdapter.b();
                healthSubTabWidget.setIsViewPagerScroll(false);
            }
        }
    }
}

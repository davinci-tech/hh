package com.huawei.health.knit.ui;

import android.graphics.Bitmap;
import android.os.Looper;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.knit.ui.KnitSectionRecyclerAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.eet;
import defpackage.egv;
import defpackage.koq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class KnitSectionRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private Consumer<Boolean> f2758a;
    private final KnitFragment d;
    private final List<SectionBean> f = new ArrayList();
    private final List<SectionBean> c = new ArrayList();
    private final SparseBooleanArray b = new SparseBooleanArray();
    private boolean e = false;

    public KnitSectionRecyclerAdapter(KnitFragment knitFragment) {
        this.d = knitFragment;
    }

    public boolean d() {
        return koq.c(this.f);
    }

    public List<SectionBean> b() {
        return this.c;
    }

    public void e(final List<SectionBean> list) {
        if (!Looper.getMainLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: eht
                @Override // java.lang.Runnable
                public final void run() {
                    KnitSectionRecyclerAdapter.this.b(list);
                }
            });
            return;
        }
        this.f.clear();
        this.f.addAll(list);
        this.c.clear();
        for (SectionBean sectionBean : this.f) {
            if (sectionBean.t() && !sectionBean.s()) {
                this.c.add(sectionBean);
            }
        }
        notifyDataSetChanged();
    }

    public /* synthetic */ void b(List list) {
        e((List<SectionBean>) list);
    }

    public void a(final List<SectionBean> list, final Consumer<Boolean> consumer) {
        if (!Looper.getMainLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: ehk
                @Override // java.lang.Runnable
                public final void run() {
                    KnitSectionRecyclerAdapter.this.b(list, consumer);
                }
            });
            return;
        }
        d(list, consumer);
        this.f.clear();
        this.f.addAll(list);
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (SectionBean sectionBean : this.f) {
            if (this.c.contains(sectionBean)) {
                arrayList.add(sectionBean);
            }
        }
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            hashMap.put((SectionBean) arrayList.get(i2), Integer.valueOf(i2));
        }
        int i3 = 0;
        while (i3 < this.c.size()) {
            Integer num = (Integer) hashMap.get(this.c.get(i3));
            if (num == null) {
                LogUtil.b("KnitSectionRecyclerAdapter", "failed to find pos for activeBean, continue!");
            } else {
                int intValue = num.intValue();
                if (i3 != intValue) {
                    Collections.swap(this.c, i3, intValue);
                }
            }
            i3++;
        }
        for (SectionBean sectionBean2 : this.f) {
            if (!this.c.contains(sectionBean2)) {
                if (sectionBean2.t() && !sectionBean2.s()) {
                    this.c.add(i, sectionBean2);
                }
            }
            i++;
        }
        ArrayList arrayList2 = new ArrayList();
        for (SectionBean sectionBean3 : this.c) {
            if (sectionBean3 != null && sectionBean3.t()) {
                arrayList2.add(sectionBean3);
            }
        }
        this.c.clear();
        this.c.addAll(arrayList2);
        notifyDataSetChanged();
    }

    public /* synthetic */ void b(List list, Consumer consumer) {
        a((List<SectionBean>) list, (Consumer<Boolean>) consumer);
    }

    private void d(List<SectionBean> list, Consumer<Boolean> consumer) {
        this.f2758a = consumer;
        if (consumer != null) {
            this.b.clear();
            for (int i = 0; i < list.size(); i++) {
                this.b.put(i, !list.get(i).r());
            }
        }
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void c(final SectionBean sectionBean) {
        if (!Looper.getMainLooper().isCurrentThread()) {
            HandlerExecutor.a(new Runnable() { // from class: ehn
                @Override // java.lang.Runnable
                public final void run() {
                    KnitSectionRecyclerAdapter.this.c(sectionBean);
                }
            });
            return;
        }
        SectionBean.Operation g = sectionBean.g();
        int i = AnonymousClass5.b[g.ordinal()];
        if (i == 1 || i == 2) {
            d(sectionBean, g);
        } else if (i == 3) {
            d(sectionBean);
        } else {
            LogUtil.b("KnitSectionRecyclerAdapter", "updateItem failed cause operation type is unknown");
        }
    }

    /* renamed from: com.huawei.health.knit.ui.KnitSectionRecyclerAdapter$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[SectionBean.Operation.values().length];
            b = iArr;
            try {
                iArr[SectionBean.Operation.CHANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[SectionBean.Operation.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[SectionBean.Operation.INSERT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[SectionBean.Operation.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void d(SectionBean sectionBean) {
        LogUtil.a("KnitSectionRecyclerAdapter", "processItemInsert");
        if (this.c.contains(sectionBean)) {
            LogUtil.b("KnitSectionRecyclerAdapter", "no need to processItemInsert for ", sectionBean);
            return;
        }
        if (!sectionBean.t()) {
            LogUtil.b("KnitSectionRecyclerAdapter", "no active not processItemInsert for ", sectionBean);
            return;
        }
        int i = 0;
        for (SectionBean sectionBean2 : this.f) {
            if (this.c.contains(sectionBean2)) {
                i++;
            }
            if (sectionBean2 == sectionBean) {
                this.c.add(i, sectionBean);
                notifyItemInserted(i);
                return;
            }
        }
    }

    private void d(SectionBean sectionBean, SectionBean.Operation operation) {
        LogUtil.a("KnitSectionRecyclerAdapter", "processItemChangeOrRemove");
        for (int i = 0; i < this.c.size(); i++) {
            if (this.c.get(i) == sectionBean) {
                if (operation == SectionBean.Operation.CHANGE) {
                    notifyItemChanged(i);
                    return;
                } else if (operation == SectionBean.Operation.REMOVE) {
                    this.c.remove(i);
                    notifyItemRemoved(i);
                    return;
                } else {
                    LogUtil.b("KnitSectionRecyclerAdapter", "processItemChangeOrRemove failed, cause operation is ", operation);
                    return;
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (a(i) != null) {
            return r3.hashCode();
        }
        return 0L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: ajS_, reason: merged with bridge method [inline-methods] */
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.a("KnitSectionRecyclerAdapter", "onCreateViewHolder, viewType: ", Integer.valueOf(i));
        BaseSection d = eet.d(this.d.getActivity(), egv.a(i));
        if (d != null) {
            d.initView(this.d, i);
        }
        return new BaseViewHolder(d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
        LogUtil.a("KnitSectionRecyclerAdapter", "onBindViewHolder, position: ", Integer.valueOf(i));
        SectionBean a2 = a(i);
        if (a2.f() == null) {
            a2.e((Consumer<Boolean>) new c(this, i));
            baseViewHolder.d(a2, true);
        } else {
            baseViewHolder.d(a2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (this.e || this.b.indexOfKey(i) < 0 || this.b.get(i)) {
            return;
        }
        this.b.put(i, true);
        LogUtil.a("KnitSectionRecyclerAdapter", "checkAllSectionsDone at : ", Integer.valueOf(i));
        int i2 = 0;
        while (i2 < this.b.size()) {
            SparseBooleanArray sparseBooleanArray = this.b;
            if (!sparseBooleanArray.get(sparseBooleanArray.keyAt(i2), false)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 < this.b.size() || this.f2758a == null) {
            return;
        }
        HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitSectionRecyclerAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                KnitSectionRecyclerAdapter.this.f2758a.accept(true);
            }
        });
        this.e = true;
    }

    public List<BaseSection> e() {
        WeakReference<BaseSection> n;
        BaseSection baseSection;
        ArrayList arrayList = new ArrayList();
        for (SectionBean sectionBean : this.c) {
            if ("true".equals(sectionBean.y()) && (n = sectionBean.n()) != null && (baseSection = n.get()) != null) {
                arrayList.add(baseSection);
            }
        }
        for (SectionBean sectionBean2 : this.f) {
            if (!sectionBean2.t() && "true".equals(sectionBean2.y())) {
                int o = sectionBean2.o();
                BaseSection d = eet.d(this.d.getActivity(), egv.a(o));
                if (d != null) {
                    d.initView(this.d, o);
                    d.onBind(sectionBean2);
                    arrayList.add(d);
                }
            }
        }
        return arrayList;
    }

    public List<Bitmap> a() {
        ArrayList arrayList = new ArrayList();
        Iterator<SectionBean> it = this.c.iterator();
        while (it.hasNext()) {
            List<Bitmap> l = it.next().l();
            if (l != null) {
                arrayList.addAll(l);
            }
        }
        return arrayList;
    }

    public SectionBean a(int i) {
        if (koq.d(this.c, i)) {
            return this.c.get(i);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        SectionBean a2 = a(i);
        if (a2 != null) {
            return a2.o();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public void h() {
        BaseSection baseSection;
        LogUtil.a("KnitSectionRecyclerAdapter", "onDestroy");
        if (koq.b(this.f)) {
            LogUtil.a("KnitSectionRecyclerAdapter", "mTotalBeanList is empty");
            return;
        }
        for (SectionBean sectionBean : this.f) {
            if (sectionBean == null) {
                LogUtil.a("KnitSectionRecyclerAdapter", "section is null");
            } else {
                WeakReference<BaseSection> n = sectionBean.n();
                if (n != null && (baseSection = n.get()) != null) {
                    LogUtil.a("KnitSectionRecyclerAdapter", "onDestroy section: ", baseSection);
                    baseSection.clear();
                }
            }
        }
    }

    public void c() {
        this.f.clear();
        this.c.clear();
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private final BaseSection c;

        public BaseViewHolder(BaseSection baseSection) {
            super(baseSection);
            this.c = baseSection;
        }

        public void d(SectionBean sectionBean, boolean z) {
            if (z) {
                this.c.getViewTreeObserver().addOnDrawListener(new a(sectionBean.f(), this.c));
            }
            this.c.onBind(sectionBean);
        }

        static final class a implements ViewTreeObserver.OnDrawListener {
            private Consumer<Boolean> c;
            private WeakReference<View> e;

            public a(Consumer<Boolean> consumer, View view) {
                this.c = consumer;
                this.e = new WeakReference<>(view);
            }

            @Override // android.view.ViewTreeObserver.OnDrawListener
            public void onDraw() {
                Consumer<Boolean> consumer = this.c;
                if (consumer != null) {
                    consumer.accept(true);
                }
                HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitSectionRecyclerAdapter.BaseViewHolder.a.4
                    @Override // java.lang.Runnable
                    public void run() {
                        View view = (View) a.this.e.get();
                        if (view == null) {
                            LogUtil.h("KnitSectionRecyclerAdapter", "view is null in HolderOnDrawListener onDraw.");
                        } else {
                            view.getViewTreeObserver().removeOnDrawListener(a.this);
                        }
                    }
                });
            }
        }
    }

    static final class c implements Consumer<Boolean> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<KnitSectionRecyclerAdapter> f2760a;
        private int e;

        public c(KnitSectionRecyclerAdapter knitSectionRecyclerAdapter, int i) {
            this.f2760a = new WeakReference<>(knitSectionRecyclerAdapter);
            this.e = i;
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void accept(Boolean bool) {
            KnitSectionRecyclerAdapter knitSectionRecyclerAdapter = this.f2760a.get();
            if (knitSectionRecyclerAdapter == null) {
                return;
            }
            knitSectionRecyclerAdapter.b(this.e);
        }
    }
}

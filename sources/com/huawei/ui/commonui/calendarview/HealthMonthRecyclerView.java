package com.huawei.ui.commonui.calendarview;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.uikit.phone.hwrecyclerview.widget.HwRecyclerView;
import defpackage.nkw;
import defpackage.nky;
import defpackage.nsn;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class HealthMonthRecyclerView extends HwRecyclerView {

    /* renamed from: a, reason: collision with root package name */
    private b f8779a;
    private LinearLayoutManager b;
    private int c;
    private Set<String> e;
    private MarkDateTrigger g;
    private nky j;

    public HealthMonthRecyclerView(Context context) {
        super(context);
        this.e = new HashSet();
    }

    public HealthMonthRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new HashSet();
    }

    public HealthMonthRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new HashSet();
    }

    void c(nky nkyVar) {
        this.j = nkyVar;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.b = linearLayoutManager;
        setLayoutManager(linearLayoutManager);
        b bVar = new b(getContext());
        this.f8779a = bVar;
        setAdapter(bVar);
        a(false);
        d(false);
    }

    private void b(int i) {
        if (i > -1 && i < this.c - 1) {
            scrollToPosition(i);
            this.b.scrollToPositionWithOffset(i, 0);
        } else if (i == this.c - 1) {
            this.b.setStackFromEnd(true);
        } else {
            LogUtil.b("HealthMonthRecyclerView", "moveToPosition failed, position = ", Integer.valueOf(i), ", count = ", Integer.valueOf(this.c));
        }
    }

    void e() {
        this.c = nkw.c(this.j) + 1;
        this.f8779a.notifyDataSetChanged();
        b(this.c - 1);
    }

    void d(HealthCalendar healthCalendar, boolean z, boolean z2) {
        int e = nkw.e(healthCalendar, this.j);
        int i = this.c - 1;
        if (e > i) {
            e = i;
        }
        b(e);
        HealthCalendarBaseMonthView healthCalendarBaseMonthView = (HealthCalendarBaseMonthView) findViewWithTag(Integer.valueOf(e));
        if (healthCalendarBaseMonthView != null) {
            healthCalendarBaseMonthView.setSelectedCalendar(this.j.l);
            healthCalendarBaseMonthView.invalidate();
        }
    }

    final void d() {
        e();
        HealthCalendar healthCalendar = this.j.l;
        if (this.j.c != null) {
            this.j.c.onMonthDateSelected(healthCalendar, false);
        }
        if (this.j.d != null) {
            this.j.d.onCalendarSelect(healthCalendar, false);
        }
    }

    public void setMarkDateTrigger(MarkDateTrigger markDateTrigger) {
        this.g = markDateTrigger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(long j, long j2) {
        return this.e.contains(String.valueOf(j) + String.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, long j2) {
        this.e.add(String.valueOf(j) + String.valueOf(j2));
    }

    /* loaded from: classes9.dex */
    class b extends RecyclerView.Adapter<e> {

        /* renamed from: a, reason: collision with root package name */
        final LayoutInflater f8780a;

        b(Context context) {
            this.f8780a = LayoutInflater.from(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: cxB_, reason: merged with bridge method [inline-methods] */
        public e onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new e(this.f8780a.inflate(R.layout.health_calendar_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return HealthMonthRecyclerView.this.c;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(e eVar, int i) {
            int c = nkw.c(i, HealthMonthRecyclerView.this.j);
            int d = nkw.d(i, HealthMonthRecyclerView.this.j);
            eVar.e.initMonthWithDate(c, d);
            eVar.e.setSelectedCalendar(HealthMonthRecyclerView.this.j.l);
            String str = String.valueOf(c) + String.valueOf(d);
            if ((eVar.e.getTag() instanceof String) && !str.equals(eVar.e.getTag())) {
                eVar.e.requestLayout();
            }
            eVar.e.setTag(str);
            eVar.b.setText(DateUtils.formatDateTime(HealthMonthRecyclerView.this.getContext(), new HealthCalendar(c, d, 1).transformCalendar().getTimeInMillis(), 36));
            long c2 = nkw.c(c, d);
            long a2 = nkw.a(c, d);
            if (HealthMonthRecyclerView.this.g == null || HealthMonthRecyclerView.this.a(c2, a2)) {
                return;
            }
            c cVar = new c(HealthMonthRecyclerView.this, eVar);
            cVar.c(c2, a2);
            HealthMonthRecyclerView.this.g.retrieveMarkDate(c2, a2, cVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0055  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView e() {
            /*
                r6 = this;
                java.lang.String r0 = "HealthMonthRecyclerView"
                com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView r1 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                nky r1 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.c(r1)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Class r1 = r1.t()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r2 = 1
                java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Class<android.content.Context> r4 = android.content.Context.class
                r5 = 0
                r3[r5] = r4     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.reflect.Constructor r1 = r1.getConstructor(r3)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView r4 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                android.content.Context r4 = r4.getContext()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r3[r5] = r4     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object r3 = r1.newInstance(r3)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                boolean r3 = r3 instanceof com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                if (r3 == 0) goto L52
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView r3 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.this     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                android.content.Context r3 = r3.getContext()     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                r2[r5] = r3     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                java.lang.Object r1 = r1.newInstance(r2)     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView r1 = (com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView) r1     // Catch: java.lang.IllegalAccessException -> L3b java.lang.NoSuchMethodException -> L41 java.lang.reflect.InvocationTargetException -> L47 java.lang.InstantiationException -> L4d
                goto L53
            L3b:
                java.lang.String r1 = "IllegalAccessException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
                goto L52
            L41:
                java.lang.String r1 = "NoSuchMethodException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
                goto L52
            L47:
                java.lang.String r1 = "InvocationTargetException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
                goto L52
            L4d:
                java.lang.String r1 = "InstantiationException in reflect call HealthCalendarBaseMonthView constructor"
                android.util.Log.e(r0, r1)
            L52:
                r1 = 0
            L53:
                if (r1 != 0) goto L60
                com.huawei.ui.commonui.calendarview.HealthDefaultMonthView r1 = new com.huawei.ui.commonui.calendarview.HealthDefaultMonthView
                com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView r0 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.this
                android.content.Context r0 = r0.getContext()
                r1.<init>(r0)
            L60:
                com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView r0 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.this
                nky r0 = com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.c(r0)
                r1.init(r0)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.b.e():com.huawei.ui.commonui.calendarview.HealthCalendarBaseMonthView");
        }

        class e extends RecyclerView.ViewHolder {
            HealthTextView b;
            HealthCalendarBaseMonthView e;

            e(View view) {
                super(view);
                this.b = (HealthTextView) view.findViewById(R.id.text1);
                if (nsn.r()) {
                    this.b.setTextSize(0, HealthMonthRecyclerView.this.getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d));
                }
                HealthCalendarBaseMonthView e = b.this.e();
                this.e = e;
                if (view instanceof ViewGroup) {
                    ((ViewGroup) view).addView(e);
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    static class c extends UiCallback<Map<String, HealthCalendar>> {
        private long b;
        private long c;
        private WeakReference<b.e> d;
        private WeakReference<HealthMonthRecyclerView> e;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        public c(HealthMonthRecyclerView healthMonthRecyclerView, b.e eVar) {
            this.e = new WeakReference<>(healthMonthRecyclerView);
            this.d = new WeakReference<>(eVar);
        }

        public void c(long j, long j2) {
            this.b = j;
            this.c = j2;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Map<String, HealthCalendar> map) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.commonui.calendarview.HealthMonthRecyclerView.c.4
                @Override // java.lang.Runnable
                public void run() {
                    HealthMonthRecyclerView healthMonthRecyclerView = (HealthMonthRecyclerView) c.this.e.get();
                    b.e eVar = (b.e) c.this.d.get();
                    if (healthMonthRecyclerView != null && eVar != null) {
                        healthMonthRecyclerView.b(c.this.b, c.this.c);
                        healthMonthRecyclerView.j.e(map);
                        eVar.e.update();
                        return;
                    }
                    LogUtil.h("HealthMonthRecyclerView", "recyclerView or holder is null");
                }
            });
        }
    }
}

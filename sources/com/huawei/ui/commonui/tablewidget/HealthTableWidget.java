package com.huawei.ui.commonui.tablewidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.tablewidget.ScrollHelper;
import defpackage.nqz;
import defpackage.nra;
import defpackage.nrb;
import defpackage.nrc;
import defpackage.nrg;
import health.compact.a.LanguageUtil;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class HealthTableWidget extends ViewGroup implements ScrollHelper.ScrollHelperListener, HealthTableDataSetObserver {

    /* renamed from: a, reason: collision with root package name */
    private nqz f8957a;
    private nrb b;
    private float c;
    private ScrollHelper d;
    private HealthTableAdapter<ViewHolder> e;
    private nrg f;
    private ScrollHelper.a g;
    private nra<ViewHolder> h;
    private nrc i;

    public interface OnItemClickListener {
        void onItemClick(int i, int i2);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int i, int i2);
    }

    private int e(int i) {
        return i;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ScrollHelper.ScrollHelperListener
    public boolean onActionUp(MotionEvent motionEvent) {
        return true;
    }

    public HealthTableWidget(Context context) {
        super(context);
        this.c = 1.0f;
        cGE_(context, null);
        a(context);
    }

    public HealthTableWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 1.0f;
        cGE_(context, attributeSet);
        a(context);
    }

    public HealthTableWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 1.0f;
        a(context);
        cGE_(context, attributeSet);
    }

    public boolean a() {
        return getLayoutDirection() == 1 || LanguageUtil.bc(getContext());
    }

    @Override // android.view.View
    public void setLayoutDirection(int i) {
        super.setLayoutDirection(i);
        HealthTableAdapter<ViewHolder> healthTableAdapter = this.e;
        if (healthTableAdapter != null) {
            healthTableAdapter.setRtl(a());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            this.b.e(i3 - i);
            this.b.b(i4 - i2);
            notifyLayoutChanged();
        }
    }

    private void cGE_(Context context, AttributeSet attributeSet) {
        this.b = new nrb(context, attributeSet);
    }

    private void a(Context context) {
        this.h = new nra<>();
        this.f8957a = new nqz(this.b);
        this.g = new ScrollHelper.a(this);
        this.f = new nrg();
        ScrollHelper scrollHelper = new ScrollHelper(context);
        this.d = scrollHelper;
        scrollHelper.b(this);
        this.i = new nrc();
    }

    private void f() {
        nqz nqzVar = this.f8957a;
        if (nqzVar == null || this.e == null) {
            LogUtil.h("CommonUI_HealthTableWidget", "initItems with null manager, pls check");
            return;
        }
        nqzVar.b();
        this.f8957a.d(this.e.getRowCount(), this.e.getColumnCount(), this.e.getColumnHeaderNum(), this.e.getStatisticNum(), this.e.getRowHeaderNum());
        g();
        this.f8957a.o();
        cGD_(this.f8957a.cGB_(a()));
        if (a() && this.f8957a.h() != null && this.f8957a.h().b()) {
            scrollTo(((int) this.f8957a.i()) - this.b.c(), 0);
        }
    }

    private void g() {
        h();
        c();
    }

    private void h() {
        int i;
        int a2 = this.f8957a.a();
        int[] iArr = new int[a2];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < a2; i4++) {
            int max = Math.max(0, this.e.getColumnWidth(i4));
            iArr[i4] = max;
            i3 += max;
        }
        if (this.e.getColumnWidthValueType() != 1) {
            while (i2 < a2) {
                this.f8957a.c(i2, iArr[i2]);
                i2++;
            }
            return;
        }
        int c = this.b.c() - ((this.b.k() ? this.f8957a.a() + 1 : this.f8957a.a() - 1) * this.b.b());
        int i5 = 0;
        while (i2 < a2) {
            if (i2 < a2 - 1) {
                i = Math.round(((iArr[i2] * c) * 1.0f) / i3);
                i5 += i;
            } else {
                i = c - i5;
            }
            this.f8957a.c(i2, i);
            i2++;
        }
    }

    private void c() {
        int i;
        int j = this.f8957a.j();
        int[] iArr = new int[this.f8957a.j()];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < j; i4++) {
            int max = Math.max(0, this.e.getRowHeight(i4)) * Math.round(this.c);
            iArr[i4] = max;
            i3 += max;
        }
        if (this.e.getRowHeightValueType() != 1) {
            while (i2 < j) {
                this.f8957a.a(i2, iArr[i2]);
                i2++;
            }
            return;
        }
        int e = this.b.e() - ((this.b.l() ? this.f8957a.j() + 1 : this.f8957a.j() - 1) * this.b.a());
        int i5 = 0;
        while (i2 < j) {
            if (i2 < j - 1) {
                i = Math.round(((iArr[i2] * e) * 1.0f) / i3);
                i5 += i;
            } else {
                i = e - i5;
            }
            this.f8957a.a(i2, i);
            i2++;
        }
    }

    public void setAdapter(HealthTableAdapter healthTableAdapter) {
        HealthTableAdapter<ViewHolder> healthTableAdapter2 = this.e;
        if (healthTableAdapter2 != null) {
            healthTableAdapter2.unregisterDataSetObserver(this);
        }
        this.e = healthTableAdapter;
        if (healthTableAdapter != null) {
            healthTableAdapter.registerDataSetObserver(this);
            this.e.setRtl(a());
        }
        if (this.b.e() == 0 || this.b.c() == 0) {
            return;
        }
        f();
    }

    public void setPostureAdapter(HealthTableAdapter healthTableAdapter, float f) {
        setAdapter(healthTableAdapter);
        this.c = f;
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        scrollBy(i, i2);
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        long i3 = this.f8957a.i();
        long c = this.f8957a.c();
        if (this.f8957a.h().a() + i <= 0) {
            i = this.f8957a.h().a();
            this.f8957a.h().c(0);
        } else if (this.b.c() > i3) {
            this.f8957a.h().c(0);
            i = 0;
        } else if (this.f8957a.h().a() + this.b.c() + i > i3) {
            i = (int) ((i3 - this.f8957a.h().a()) - this.b.c());
            this.f8957a.h().c(this.f8957a.h().a() + i);
        } else {
            this.f8957a.h().c(this.f8957a.h().a() + i);
        }
        if (this.f8957a.h().e() + i2 <= 0) {
            i2 = this.f8957a.h().e();
            this.f8957a.h().e(0);
        } else if (this.b.e() > c) {
            this.f8957a.h().e(0);
            i2 = 0;
        } else if (this.f8957a.h().e() + this.b.e() + i2 > c) {
            i2 = (int) ((c - this.f8957a.h().e()) - this.b.e());
            this.f8957a.h().e(this.f8957a.h().e() + i2);
        } else {
            this.f8957a.h().e(this.f8957a.h().e() + i2);
        }
        if ((i == 0 && i2 == 0) || this.e == null) {
            return;
        }
        i();
        cGD_(this.f8957a.cGB_(a()));
        j();
    }

    private void j() {
        if (this.e != null) {
            Iterator<ViewHolder> it = this.h.b().iterator();
            while (it.hasNext()) {
                h(it.next());
            }
        }
        d();
    }

    private void h(ViewHolder viewHolder) {
        int itemType = viewHolder.getItemType();
        if (itemType == 0) {
            e(viewHolder);
            return;
        }
        if (itemType == 1) {
            f(viewHolder);
            return;
        }
        if (itemType == 2) {
            c(viewHolder);
            return;
        }
        if (itemType == 3) {
            a(viewHolder);
        } else if (itemType == 4) {
            g(viewHolder);
        } else {
            if (itemType != 5) {
                return;
            }
            i(viewHolder);
        }
    }

    private int a(int i) {
        if (a()) {
            return ((int) Math.max(this.f8957a.i(), this.b.c())) - (this.f8957a.d(0, Math.max(0, i)) + (this.b.k() ? this.b.b() : 0));
        }
        return this.f8957a.d(0, Math.max(0, i)) + (this.b.k() ? this.b.b() : 0);
    }

    private int c(int i) {
        return this.f8957a.e(0, Math.max(0, i)) + (this.b.l() ? this.b.a() : 0);
    }

    private void e(ViewHolder viewHolder) {
        View itemView = viewHolder.getItemView();
        int c = c(viewHolder.getRowIndex()) - this.f8957a.h().e();
        int a2 = a(viewHolder.getColumnIndex()) - this.f8957a.h().a();
        if (a()) {
            itemView.layout(a2 - this.f8957a.a(viewHolder.getColumnIndex()), c, a2, this.f8957a.d(viewHolder.getRowIndex()) + c);
        } else {
            itemView.layout(a2, c, this.f8957a.a(viewHolder.getColumnIndex()) + a2, this.f8957a.d(viewHolder.getRowIndex()) + c);
        }
    }

    private void c(ViewHolder viewHolder) {
        int a2 = a(viewHolder.getColumnIndex());
        int a3 = this.f8957a.h().a();
        int c = c(viewHolder.getRowIndex());
        if (!this.b.j()) {
            c -= this.f8957a.h().e();
        }
        c(viewHolder, a2 - a3, c);
    }

    private void f(ViewHolder viewHolder) {
        int b;
        int c = c(viewHolder.getRowIndex());
        int e = this.f8957a.h().e();
        int a2 = a(viewHolder.getColumnIndex());
        if (!this.b.o()) {
            b = this.f8957a.h().a();
        } else {
            if (a()) {
                a2 = this.b.c();
                b = this.b.k() ? this.b.b() : 0;
            }
            c(viewHolder, a2, c - e);
        }
        a2 -= b;
        c(viewHolder, a2, c - e);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.huawei.ui.commonui.tablewidget.ViewHolder r6) {
        /*
            r5 = this;
            int r0 = r6.getColumnIndex()
            int r0 = r5.a(r0)
            nrb r1 = r5.b
            boolean r1 = r1.o()
            if (r1 != 0) goto L1b
            nqz r1 = r5.f8957a
            nqy r1 = r1.h()
            int r1 = r1.a()
            goto L37
        L1b:
            boolean r1 = r5.a()
            if (r1 == 0) goto L38
            nrb r0 = r5.b
            int r0 = r0.c()
            nrb r1 = r5.b
            boolean r1 = r1.k()
            if (r1 == 0) goto L36
            nrb r1 = r5.b
            int r1 = r1.b()
            goto L37
        L36:
            r1 = 0
        L37:
            int r0 = r0 - r1
        L38:
            int r1 = r6.getRowIndex()
            int r1 = r5.c(r1)
            nrb r2 = r5.b
            boolean r2 = r2.j()
            if (r2 != 0) goto L53
            nqz r2 = r5.f8957a
            nqy r2 = r2.h()
            int r2 = r2.e()
            int r1 = r1 - r2
        L53:
            android.view.View r2 = r6.getItemView()
            boolean r3 = r5.a()
            if (r3 == 0) goto L78
            nqz r3 = r5.f8957a
            int r4 = r6.getColumnIndex()
            int r3 = r3.a(r4)
            nqz r4 = r5.f8957a
            int r6 = r6.getRowIndex()
            int r6 = r4.d(r6)
            int r3 = r0 - r3
            int r6 = r6 + r1
            r2.layout(r3, r1, r0, r6)
            goto L91
        L78:
            nqz r3 = r5.f8957a
            int r4 = r6.getColumnIndex()
            int r3 = r3.a(r4)
            nqz r4 = r5.f8957a
            int r6 = r6.getRowIndex()
            int r6 = r4.d(r6)
            int r3 = r3 + r0
            int r6 = r6 + r1
            r2.layout(r0, r1, r3, r6)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.tablewidget.HealthTableWidget.a(com.huawei.ui.commonui.tablewidget.ViewHolder):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void i(com.huawei.ui.commonui.tablewidget.ViewHolder r8) {
        /*
            r7 = this;
            int r0 = r8.getColumnIndex()
            int r0 = r7.a(r0)
            nrb r1 = r7.b
            boolean r1 = r1.o()
            r2 = 0
            if (r1 != 0) goto L1c
            nqz r1 = r7.f8957a
            nqy r1 = r1.h()
            int r1 = r1.a()
            goto L38
        L1c:
            boolean r1 = r7.a()
            if (r1 == 0) goto L39
            nrb r0 = r7.b
            int r0 = r0.c()
            nrb r1 = r7.b
            boolean r1 = r1.k()
            if (r1 == 0) goto L37
            nrb r1 = r7.b
            int r1 = r1.b()
            goto L38
        L37:
            r1 = r2
        L38:
            int r0 = r0 - r1
        L39:
            int r1 = r8.getRowIndex()
            int r1 = r7.c(r1)
            nrb r3 = r7.b
            boolean r3 = r3.n()
            if (r3 == 0) goto L74
            int r1 = r7.getBottom()
            int r3 = r7.getTop()
            nqz r4 = r7.f8957a
            int r5 = r8.getRowIndex()
            nqz r6 = r7.f8957a
            int r6 = r6.j()
            int r4 = r4.e(r5, r6)
            nrb r5 = r7.b
            boolean r5 = r5.l()
            if (r5 == 0) goto L6a
            goto L70
        L6a:
            nrb r2 = r7.b
            int r2 = r2.a()
        L70:
            int r1 = r1 - r3
            int r1 = r1 - r4
            int r1 = r1 + r2
            goto L7f
        L74:
            nqz r2 = r7.f8957a
            nqy r2 = r2.h()
            int r2 = r2.e()
            int r1 = r1 - r2
        L7f:
            r7.b(r8, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.tablewidget.HealthTableWidget.i(com.huawei.ui.commonui.tablewidget.ViewHolder):void");
    }

    private void g(ViewHolder viewHolder) {
        int e;
        int a2 = a(viewHolder.getColumnIndex());
        int a3 = this.f8957a.h().a();
        int c = c(viewHolder.getRowIndex());
        if (this.b.n()) {
            int bottom = getBottom();
            int top = getTop();
            e = ((bottom - top) - this.f8957a.e(viewHolder.getRowIndex(), this.f8957a.j())) + (this.b.l() ? 0 : this.b.a());
        } else {
            e = c - this.f8957a.h().e();
        }
        b(viewHolder, a2 - a3, e);
    }

    private void i() {
        a(false);
    }

    private void a(boolean z) {
        View itemView;
        if (this.e == null) {
            return;
        }
        for (ViewHolder viewHolder : this.h.b()) {
            if (viewHolder != null && (itemView = viewHolder.getItemView()) != null && (z || cGF_(itemView))) {
                this.h.e(viewHolder.getRowIndex(), viewHolder.getColumnIndex());
                d(viewHolder);
            }
        }
    }

    private void b(ViewHolder viewHolder, int i, int i2) {
        View itemView = viewHolder.getItemView();
        if (a()) {
            itemView.layout(i - this.f8957a.a(viewHolder.getColumnIndex()), i2, i, this.f8957a.d(viewHolder.getRowIndex()) + i2);
        } else {
            itemView.layout(i, i2, this.f8957a.a(viewHolder.getColumnIndex()) + i, this.f8957a.d(viewHolder.getRowIndex()) + i2);
        }
        itemView.bringToFront();
    }

    private boolean cGF_(View view) {
        return view.getRight() < 0 || view.getLeft() > this.b.c() || view.getBottom() < 0 || view.getTop() > this.b.e();
    }

    private void d(ViewHolder viewHolder) {
        this.f.a(viewHolder);
        removeView(viewHolder.getItemView());
        this.e.onViewHolderRecycled(viewHolder);
    }

    private void cGD_(Rect rect) {
        if (rect.left == rect.right || rect.top == rect.bottom) {
            return;
        }
        for (nqz.a[] aVarArr : this.f8957a.cGC_(rect)) {
            for (nqz.a aVar : aVarArr) {
                if (this.h.a(aVar.b(), aVar.c()) == null && this.e != null) {
                    b(aVar.b(), aVar.c(), aVar.d());
                }
            }
        }
        d();
    }

    private void d() {
        if (this.b.m()) {
            e();
            b();
        }
    }

    private void e() {
        if (this.b.c() >= this.f8957a.i()) {
            return;
        }
        if ((this.f8957a.h().a() == 0 && !a()) || (this.f8957a.h().a() != 0 && a())) {
            this.i.cGQ_(this);
            View cGN_ = this.i.cGN_();
            if (cGN_ == null) {
                cGN_ = this.i.cGI_(this, this.b.f());
            }
            int c = this.b.c();
            if (a() && this.b.j()) {
                c -= this.f8957a.g();
            }
            cGN_.layout(c - this.b.g(), this.b.j() ? 0 : -this.f8957a.h().e(), c, this.b.e());
            cGN_.bringToFront();
            return;
        }
        this.i.cGR_(this);
        View cGM_ = this.i.cGM_();
        if (cGM_ == null) {
            cGM_ = this.i.cGH_(this, this.b.i());
        }
        int g = (a() || !this.b.j()) ? 0 : this.f8957a.g();
        cGM_.layout(g, this.b.j() ? 0 : -this.f8957a.h().e(), this.b.g() + g, this.b.e());
        cGM_.bringToFront();
    }

    private void b() {
        if (this.b.e() >= this.f8957a.c()) {
            return;
        }
        if (this.f8957a.h().e() == 0) {
            this.i.cGS_(this);
            View cGL_ = this.i.cGL_();
            if (cGL_ == null) {
                cGL_ = this.i.cGJ_(this, this.b.d());
            }
            int e = this.b.e() - this.b.g();
            if (this.b.n()) {
                e -= this.f8957a.f();
            }
            cGL_.layout(this.b.o() ? 0 : -this.f8957a.h().a(), e, this.b.c(), this.b.g() + e);
            cGL_.bringToFront();
            return;
        }
        this.i.cGP_(this);
        View cGO_ = this.i.cGO_();
        if (cGO_ == null) {
            cGO_ = this.i.cGK_(this, this.b.h());
        }
        int e2 = this.b.o() ? this.f8957a.e() : 0;
        cGO_.layout(this.b.o() ? 0 : -this.f8957a.h().a(), e2, this.b.c(), this.b.g() + e2);
        cGO_.bringToFront();
    }

    private void b(int i, int i2, int i3) {
        ViewHolder e = this.f.e(i3);
        if (e == null) {
            e = d(i3);
        }
        if (e == null) {
            return;
        }
        e.setRowIndex(i);
        e.setColumnIndex(i2);
        e.setItemType(i3);
        View itemView = e.getItemView();
        if (a()) {
            itemView.setLayoutDirection(1);
        }
        itemView.setTag(R.id.tag_table_view_holder, e);
        addView(itemView, 0);
        this.h.a(i, i2, e);
        if (i3 == 0) {
            this.e.onBindContentViewHolder(e, i, e(i2));
        } else if (i3 == 1) {
            this.e.onBindRowHeaderViewHolder(e, i, e(i2));
        } else if (i3 == 2) {
            this.e.onBindColumnHeaderViewHolder(e, i, e(i2));
        } else if (i3 == 3) {
            this.e.onBindRowColumnHeaderViewHolder(e, i, e(i2));
        } else if (i3 == 4) {
            this.e.onBindStatisticViewHolder(e, i, e(i2));
        } else if (i3 == 5) {
            this.e.onBindStatisticHeaderViewHolder(e, i, e(i2));
        }
        itemView.measure(View.MeasureSpec.makeMeasureSpec(this.f8957a.a(i2), 1073741824), View.MeasureSpec.makeMeasureSpec(this.f8957a.d(i), 1073741824));
        h(e);
    }

    private ViewHolder d(int i) {
        if (i == 0) {
            return this.e.onCreateItemViewHolder(this);
        }
        if (i == 1) {
            return this.e.onCreateRowHeaderViewHolder(this);
        }
        if (i == 2) {
            return this.e.onCreateColumnHeaderViewHolder(this);
        }
        if (i == 3) {
            return this.e.onCreateRowColumnHeaderViewHolder(this);
        }
        if (i == 4) {
            return this.e.onCreateStatisticViewHolder(this);
        }
        if (i == 5) {
            return this.e.onCreateStatisticHeaderViewHolder(this);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.d.cGG_(motionEvent);
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.d.cGG_(motionEvent);
    }

    private int getRowHeaderStartX() {
        if (a()) {
            return this.b.c() - this.f8957a.g();
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        int a2;
        int i;
        ViewHolder viewHolder = (ViewHolder) view.getTag(R.id.tag_table_view_holder);
        canvas.save();
        if (this.b.o()) {
            a2 = 0;
        } else if (a()) {
            a2 = (((int) this.f8957a.i()) - this.b.c()) - this.f8957a.h().a();
        } else {
            a2 = this.f8957a.h().a();
        }
        int e = this.b.j() ? 0 : this.f8957a.h().e();
        int max = !a() ? Math.max(0, this.f8957a.g() - a2) : 0;
        int c = !a() ? this.b.c() : Math.min(getRowHeaderStartX() + a2, this.b.c());
        int max2 = Math.max(0, this.f8957a.e() - e);
        int e2 = this.b.e();
        if (this.b.n()) {
            e2 -= this.f8957a.f();
            i = e2;
        } else {
            i = max2;
        }
        int i2 = !a() ? 0 : c;
        int c2 = !a() ? max : this.b.c();
        if (viewHolder != null) {
            if (viewHolder.getItemType() == 0) {
                canvas.clipRect(max, max2, c, e2);
            } else if (viewHolder.getItemType() == 1) {
                canvas.clipRect(i2, max2, c2, e2);
            } else if (viewHolder.getItemType() == 2) {
                canvas.clipRect(max, 0, c, Math.max(0, this.f8957a.e() - e));
            } else if (viewHolder.getItemType() == 3) {
                canvas.clipRect(i2, 0, c2, Math.max(0, this.f8957a.e() - e));
            } else if (viewHolder.getItemType() == 5) {
                canvas.clipRect(i2, i, c2, this.b.e());
            } else if (viewHolder.getItemType() == 4) {
                canvas.clipRect(max, i, c, this.b.e());
            }
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restore();
        return drawChild;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ScrollHelper.ScrollHelperListener
    public boolean onDown(MotionEvent motionEvent) {
        if (this.g.a()) {
            return true;
        }
        this.g.d();
        return true;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ScrollHelper.ScrollHelperListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        OnItemClickListener onItemClickListener;
        ViewHolder a2 = a((int) motionEvent.getX(), (int) motionEvent.getY());
        if (a2 == null || (onItemClickListener = this.e.getOnItemClickListener()) == null) {
            return true;
        }
        onItemClickListener.onItemClick(a2.getRowIndex(), a2.getColumnIndex());
        return true;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ScrollHelper.ScrollHelperListener
    public void onLongPress(MotionEvent motionEvent) {
        ViewHolder a2 = a((int) motionEvent.getX(), (int) motionEvent.getY());
        if (a2 != null) {
            b(a2);
        }
    }

    private void b(ViewHolder viewHolder) {
        OnItemLongClickListener onItemLongClickListener = this.e.getOnItemLongClickListener();
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(viewHolder.getRowIndex(), viewHolder.getColumnIndex());
        }
    }

    private ViewHolder a(int i, int i2) {
        int a2;
        int e;
        int b;
        if (a()) {
            i = this.b.c() - i;
        }
        if (a()) {
            int i3 = (int) this.f8957a.i();
            a2 = ((i3 + i) - this.b.c()) - this.f8957a.h().a();
        } else {
            a2 = this.f8957a.h().a() + i;
        }
        int e2 = this.f8957a.h().e();
        if (this.b.o() && i <= this.f8957a.g()) {
            e = this.f8957a.e(i);
        } else {
            e = this.f8957a.e(a2);
        }
        if (this.b.j() && i2 <= this.f8957a.e()) {
            b = this.f8957a.b(i2);
        } else if (this.b.n() && this.b.e() - i2 <= this.f8957a.f()) {
            int n = this.f8957a.n();
            int i4 = 1;
            while (true) {
                if (i4 > n) {
                    i4 = 0;
                    break;
                }
                int e3 = this.b.e();
                nqz nqzVar = this.f8957a;
                if (e3 - i2 < nqzVar.e(nqzVar.j() - i4, this.f8957a.j())) {
                    break;
                }
                i4++;
            }
            b = this.f8957a.j() - i4;
        } else {
            b = this.f8957a.b(e2 + i2);
        }
        return this.h.a(b, e);
    }

    @Override // com.huawei.ui.commonui.tablewidget.ScrollHelper.ScrollHelperListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.g.a()) {
            this.g.d();
        }
        scrollBy((int) f, (int) f2);
        return true;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ScrollHelper.ScrollHelperListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.g.b(this.f8957a.h().a(), this.f8957a.h().e(), ((int) f) / 2, ((int) f2) / 2, (int) (this.f8957a.i() - this.b.c()), (int) (this.f8957a.c() - this.b.e()));
        return true;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyDataSetChanged() {
        a(true);
        f();
        cGD_(this.f8957a.cGB_(a()));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyLayoutChanged() {
        a(true);
        f();
        invalidate();
        cGD_(this.f8957a.cGB_(a()));
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyItemChanged(int i, int i2) {
        ViewHolder a2 = this.h.a(i, i2);
        if (a2 != null) {
            j(a2);
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyRowChanged(int i) {
        Iterator<ViewHolder> it = this.h.e(i).iterator();
        while (it.hasNext()) {
            j(it.next());
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyColumnChanged(int i) {
        Iterator<ViewHolder> it = this.h.d(i).iterator();
        while (it.hasNext()) {
            j(it.next());
        }
    }

    private void j(ViewHolder viewHolder) {
        this.h.e(viewHolder.getRowIndex(), viewHolder.getColumnIndex());
        d(viewHolder);
        b(viewHolder.getRowIndex(), viewHolder.getColumnIndex(), viewHolder.getItemType());
    }

    private void c(ViewHolder viewHolder, int i, int i2) {
        View itemView = viewHolder.getItemView();
        if (a()) {
            itemView.layout(i - this.f8957a.a(viewHolder.getColumnIndex()), i2, i, this.f8957a.d(viewHolder.getRowIndex()) + i2);
        } else {
            itemView.layout(i, i2, this.f8957a.a(viewHolder.getColumnIndex()) + i, this.f8957a.d(viewHolder.getRowIndex()) + i2);
        }
    }
}

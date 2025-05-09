package defpackage;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nmd {

    /* renamed from: a, reason: collision with root package name */
    private float f15383a;
    private nml b;
    private nmk c;
    private float d;
    private final HealthFlowLayout e;
    private boolean f;
    private float h;
    private float i;
    private View j;
    private final int o;
    private nmk g = new nmk();
    private final a n = new a();

    public boolean c() {
        return this.f;
    }

    public nmk b() {
        return this.g;
    }

    public nmd(HealthFlowLayout healthFlowLayout) {
        this.e = healthFlowLayout;
        this.o = ViewConfiguration.get(healthFlowLayout.getContext()).getScaledTouchSlop();
    }

    public boolean cAz_(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.h = motionEvent.getY();
            this.i = motionEvent.getX();
            return cAw_(motionEvent);
        }
        if (action == 1) {
            this.f = false;
            boolean e = e();
            this.h = -1.0f;
            return e;
        }
        if (action == 2) {
            if (!this.f) {
                return false;
            }
            this.h = motionEvent.getY();
            this.i = motionEvent.getX();
            return cAx_(motionEvent);
        }
        if (action != 3) {
            return false;
        }
        this.f = false;
        boolean d = d();
        this.h = -1.0f;
        return d;
    }

    private boolean cAw_(MotionEvent motionEvent) {
        this.f15383a = motionEvent.getRawX();
        this.d = motionEvent.getRawY();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean cAx_(android.view.MotionEvent r6) {
        /*
            r5 = this;
            float r0 = r6.getRawX()
            float r1 = r5.f15383a
            float r0 = r0 - r1
            float r1 = r6.getRawY()
            float r2 = r5.d
            float r1 = r1 - r2
            float r2 = r6.getX()
            int r2 = (int) r2
            float r3 = r6.getY()
            int r3 = (int) r3
            nmk r2 = r5.d(r2, r3)
            nml r3 = r5.b
            if (r3 != 0) goto L35
            int r4 = r5.o
            float r1 = r1 * r1
            float r0 = r0 * r0
            float r1 = r1 + r0
            int r4 = r4 * r4
            float r0 = (float) r4
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 <= 0) goto L35
            if (r2 == 0) goto L60
            int r6 = r2.c()
            r5.b(r6)
            goto L5e
        L35:
            if (r3 == 0) goto L60
            r3.cAF_(r6)
            if (r2 == 0) goto L59
            int r6 = r2.j()
            if (r6 != 0) goto L59
            nmk r6 = r5.g
            if (r2 != r6) goto L56
            android.graphics.Rect r6 = r2.cAD_()
            nmk r0 = r5.c
            android.graphics.Rect r0 = r0.cAD_()
            boolean r6 = r6.contains(r0)
            if (r6 != 0) goto L59
        L56:
            r5.c(r2)
        L59:
            com.huawei.ui.commonui.flowlayout.HealthFlowLayout r6 = r5.e
            r6.invalidate()
        L5e:
            r6 = 1
            goto L61
        L60:
            r6 = 0
        L61:
            if (r2 == 0) goto L65
            r5.g = r2
        L65:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nmd.cAx_(android.view.MotionEvent):boolean");
    }

    private void c(nmk nmkVar) {
        this.n.e(nmkVar);
    }

    public void b(int i) {
        this.f = true;
        View childAt = this.e.getChildAt(i);
        this.j = childAt;
        if (childAt != null) {
            this.b = new nml(this.j, this.h, this.i);
            this.j.setVisibility(4);
            Object tag = this.j.getTag();
            if (tag instanceof nmk) {
                this.c = (nmk) tag;
            }
        }
    }

    private boolean e() {
        View view = this.j;
        if (view == null) {
            return false;
        }
        view.setVisibility(0);
        this.b = null;
        this.j = null;
        return true;
    }

    private boolean d() {
        return e();
    }

    public void cAy_(Canvas canvas) {
        nml nmlVar = this.b;
        if (nmlVar != null) {
            nmlVar.draw(canvas);
        }
    }

    private nmk d(int i, int i2) {
        ArrayList<nmk> arrayList;
        nmk e;
        nmk nmkVar = null;
        if (this.j != null && !this.c.cAD_().contains(i, i2)) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 >= this.e.getRowSparseArray().size()) {
                    i3 = 0;
                    arrayList = null;
                    break;
                }
                if (i2 >= this.e.getRowSparseArray().get(i3).get(0).cAD_().top && i2 <= this.e.getRowSparseArray().get(i3).get(0).cAD_().bottom) {
                    arrayList = this.e.getRowSparseArray().get(i3);
                    break;
                }
                i4 += this.e.getRowSparseArray().get(i3).size();
                i3++;
            }
            int indexOf = this.e.getTagInfos().indexOf(this.c);
            if (arrayList != null) {
                boolean z = i > arrayList.get(arrayList.size() - 1).cAD_().right;
                if (!LanguageUtil.bc(this.e.getContext()) ? z : i < arrayList.get(arrayList.size() - 1).cAD_().left) {
                    e = e(arrayList, i3, i4);
                } else {
                    e = d(arrayList, i, i2, i4);
                }
                nmkVar = e;
            }
            b(nmkVar, indexOf);
        }
        return nmkVar;
    }

    private nmk e(List<nmk> list, int i, int i2) {
        if (i == this.e.getRowSparseArray().size() - 1) {
            nmk nmkVar = list.get(list.size() - 1);
            nmkVar.a((list.size() + i2) - 1);
            return nmkVar;
        }
        nmk nmkVar2 = this.e.getRowSparseArray().get(i + 1).get(0);
        nmkVar2.a(i2 + this.e.getRowSparseArray().get(i).size());
        return nmkVar2;
    }

    private nmk d(List<nmk> list, int i, int i2, int i3) {
        for (int i4 = 0; i4 < list.size(); i4++) {
            nmk nmkVar = list.get(i4);
            if (nmkVar.cAD_().contains(i, i2)) {
                boolean z = i <= (nmkVar.cAD_().left + nmkVar.cAD_().right) / 2;
                if (!LanguageUtil.bc(this.e.getContext()) ? z : i > (nmkVar.cAD_().left + nmkVar.cAD_().right) / 2) {
                    nmkVar.a(i3 + i4);
                    return nmkVar;
                }
                nmkVar.a(i4 + i3 + 1);
                return nmkVar;
            }
        }
        return null;
    }

    class a {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(nmk nmkVar) {
            nmd.this.e.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserverOnPreDrawListenerC0328a(nmkVar));
        }

        /* renamed from: nmd$a$a, reason: collision with other inner class name */
        class ViewTreeObserverOnPreDrawListenerC0328a implements ViewTreeObserver.OnPreDrawListener {
            private nmk b;

            public ViewTreeObserverOnPreDrawListenerC0328a(nmk nmkVar) {
                this.b = nmkVar;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                nmd.this.e.getViewTreeObserver().removeOnPreDrawListener(this);
                nmd.this.e.c(this.b);
                return true;
            }
        }
    }

    private void b(nmk nmkVar, int i) {
        if (nmkVar == null || nmkVar.j() != 0 || nmkVar.c() == i) {
            return;
        }
        if (nmkVar.c() == this.e.getTagInfos().size() - 1) {
            this.e.getTagInfos().remove(this.c);
            this.e.getTagInfos().add(this.c);
        } else if (nmkVar.c() < i) {
            this.e.getTagInfos().add(nmkVar.c(), this.c);
            this.e.getTagInfos().remove(i + 1);
        } else {
            this.e.getTagInfos().add(nmkVar.c(), this.c);
            this.e.getTagInfos().remove(i);
        }
    }
}

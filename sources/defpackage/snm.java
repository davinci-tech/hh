package defpackage;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import huawei.android.widget.HwSafeInsetsShareable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class snm implements HwSafeInsetsShareable {
    private Map<View, e> e = new HashMap();

    class e {
        View b;
        int c;
        Rect d = new Rect();

        e(View view, int i) {
            this.c = 1;
            this.b = view;
            if (i == 2) {
                this.c = i;
            }
            a();
        }

        private void a() {
            View view = this.b;
            if (this.c == 2) {
                this.d.set(snm.this.b(view));
            } else {
                this.d.set(snm.this.eiJ_(view));
            }
        }
    }

    private boolean d(e eVar, snj snjVar) {
        View view = eVar.b;
        Rect eiX_ = snjVar.eiX_(view, eVar.d);
        if (eiX_.equals(b(view))) {
            return false;
        }
        view.setPadding(eiX_.left, eiX_.top, eiX_.right, eiX_.bottom);
        return true;
    }

    @Override // huawei.android.widget.HwSafeInsetsShareable
    public void addSharedView(View view, int i) {
        if (view == null || this.e.containsKey(view)) {
            return;
        }
        this.e.put(view, new e(view, i));
    }

    public void d(snj snjVar) {
        Iterator<e> it = this.e.values().iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (c(it.next(), snjVar)) {
                z = true;
            }
        }
        if (z) {
            snjVar.c();
        }
    }

    public void eiL_(Configuration configuration) {
        e();
    }

    @Override // huawei.android.widget.HwSafeInsetsShareable
    public void removeSharedView(View view) {
        this.e.remove(view);
    }

    private boolean c(e eVar, snj snjVar) {
        int i = eVar.c;
        if (i == 1) {
            return e(eVar, snjVar);
        }
        if (i != 2) {
            return false;
        }
        return d(eVar, snjVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean e(snm.e r9, defpackage.snj r10) {
        /*
            r8 = this;
            android.view.View r0 = r9.b
            int r1 = r0.getWidth()
            r2 = 0
            if (r1 == 0) goto L70
            int r1 = r0.getHeight()
            if (r1 != 0) goto L10
            goto L70
        L10:
            android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
            boolean r3 = r1 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r3 != 0) goto L19
            return r2
        L19:
            android.view.ViewGroup$MarginLayoutParams r1 = (android.view.ViewGroup.MarginLayoutParams) r1
            android.graphics.Rect r3 = r10.eiY_()
            android.graphics.Rect r9 = r9.d
            android.graphics.Rect r10 = r10.eiX_(r0, r9)
            android.graphics.Rect r4 = r8.eiJ_(r0)
            int r5 = r3.left
            r6 = 1
            if (r5 != 0) goto L41
            int r7 = r3.right
            if (r7 != 0) goto L41
            boolean r10 = r9.equals(r4)
            if (r10 != 0) goto L6b
            int r10 = r9.left
            r1.leftMargin = r10
            int r9 = r9.right
            r1.rightMargin = r9
            goto L6a
        L41:
            if (r5 <= 0) goto L4c
            int r5 = r10.left
            int r7 = r4.left
            if (r5 <= r7) goto L55
            r1.leftMargin = r5
            goto L54
        L4c:
            int r5 = r4.left
            int r7 = r9.left
            if (r5 <= r7) goto L55
            r1.leftMargin = r7
        L54:
            r2 = r6
        L55:
            int r3 = r3.right
            if (r3 <= 0) goto L62
            int r9 = r10.right
            int r10 = r4.right
            if (r9 <= r10) goto L6b
            r1.rightMargin = r9
            goto L6a
        L62:
            int r10 = r4.right
            int r9 = r9.right
            if (r10 <= r9) goto L6b
            r1.rightMargin = r9
        L6a:
            r2 = r6
        L6b:
            if (r2 == 0) goto L70
            r0.setLayoutParams(r1)
        L70:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.snm.e(snm$e, snj):boolean");
    }

    private void e() {
        Iterator<e> it = this.e.values().iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    private void d(e eVar) {
        View view = eVar.b;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            Rect rect = eVar.d;
            marginLayoutParams.leftMargin = rect.left;
            marginLayoutParams.rightMargin = rect.right;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public void eiM_(View view, snj snjVar) {
        e eVar = this.e.get(view);
        if (eVar != null) {
            c(eVar, snjVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect eiJ_(View view) {
        Rect rect = new Rect();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            rect.set(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect b(View view) {
        Rect rect = new Rect();
        if (view != null) {
            rect.set(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
        return rect;
    }
}

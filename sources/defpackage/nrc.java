package defpackage;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes6.dex */
public class nrc {

    /* renamed from: a, reason: collision with root package name */
    private View f15453a;
    private View b;
    private View c;
    private View d;

    public View cGK_(ViewGroup viewGroup, int i) {
        if (this.c == null) {
            View view = new View(viewGroup.getContext());
            this.c = view;
            view.setBackgroundResource(i);
            viewGroup.addView(this.c, 0);
        }
        return this.c;
    }

    public View cGO_() {
        return this.c;
    }

    public void cGS_(ViewGroup viewGroup) {
        View view = this.c;
        if (view != null) {
            viewGroup.removeView(view);
            this.c = null;
        }
    }

    public View cGH_(ViewGroup viewGroup, int i) {
        if (this.f15453a == null) {
            View view = new View(viewGroup.getContext());
            this.f15453a = view;
            view.setBackgroundResource(i);
            viewGroup.addView(this.f15453a, 0);
        }
        return this.f15453a;
    }

    public View cGM_() {
        return this.f15453a;
    }

    public void cGQ_(ViewGroup viewGroup) {
        View view = this.f15453a;
        if (view != null) {
            viewGroup.removeView(view);
            this.f15453a = null;
        }
    }

    public View cGI_(ViewGroup viewGroup, int i) {
        if (this.d == null) {
            View view = new View(viewGroup.getContext());
            this.d = view;
            view.setBackgroundResource(i);
            viewGroup.addView(this.d, 0);
        }
        return this.d;
    }

    public View cGN_() {
        return this.d;
    }

    public void cGR_(ViewGroup viewGroup) {
        View view = this.d;
        if (view != null) {
            viewGroup.removeView(view);
            this.d = null;
        }
    }

    public View cGJ_(ViewGroup viewGroup, int i) {
        if (this.b == null) {
            View view = new View(viewGroup.getContext());
            this.b = view;
            view.setBackgroundResource(i);
            viewGroup.addView(this.b, 0);
        }
        return this.b;
    }

    public View cGL_() {
        return this.b;
    }

    public void cGP_(ViewGroup viewGroup) {
        View view = this.b;
        if (view != null) {
            viewGroup.removeView(view);
            this.b = null;
        }
    }
}

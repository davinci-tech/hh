package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class nqc {

    /* renamed from: a, reason: collision with root package name */
    private static Set<WeakReference<nqc>> f15437a = new HashSet();
    private Context b;
    private ListView c;
    private int d;
    private int e;
    private int f;
    private WeakReference<View> g;
    private PopupWindow h;

    public nqc(Context context, View view) {
        this.b = context;
        if (context == null) {
            this.b = BaseApplication.getContext();
        }
        this.h = new PopupWindow(view, -2, -2, true);
        if (SystemInfo.b()) {
            view.setBackgroundResource(R$drawable.popup_menu_bg);
            view.setClipToOutline(true);
            d();
        } else {
            view.setBackgroundResource(R$drawable.poplist_menu_bg);
        }
        this.h.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: nqc.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                nqc.this.b();
            }
        });
        view.measure(0, 0);
        this.e = view.getMeasuredWidth();
        this.d = view.getMeasuredHeight();
        this.c = (ListView) view.findViewById(R.id.list_pop_window_tab);
    }

    private void d() {
        slc.c(this.h, "setShadowEnabled", new Class[]{Boolean.TYPE}, new Object[]{true}, PopupWindow.class);
        slc.c(this.h, "setShadowStyle", new Class[]{Integer.TYPE}, new Object[]{0}, PopupWindow.class);
        slc.c(this.h, "setShadowSize", new Class[]{Integer.TYPE}, new Object[]{2}, PopupWindow.class);
        slc.c(this.h, "setShadowColor", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(this.b.getResources().getColor(R$color.hwspinner_list_shadow_color))}, PopupWindow.class);
        slc.c(this.h, "setShadowClip", new Class[]{Boolean.TYPE}, new Object[]{false}, PopupWindow.class);
    }

    private void a() {
        float f;
        View view;
        ListView listView = this.c;
        if (listView == null) {
            return;
        }
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null || adapter.getCount() <= 0 || (view = adapter.getView(0, null, this.c)) == null) {
            f = -1.0f;
        } else {
            view.measure(0, 0);
            f = view.getMeasuredHeight() / 2.0f;
        }
        slc.c(this.h, "setShadowViewZ", new Class[]{Float.TYPE}, new Object[]{Float.valueOf(f)}, PopupWindow.class);
    }

    public boolean c() {
        return this.h.isShowing();
    }

    public void cEh_(View view, int i) {
        if (view == null) {
            return;
        }
        f15437a.add(new WeakReference<>(this));
        if (SystemInfo.b()) {
            a();
        }
        this.g = new WeakReference<>(view);
        this.f = i;
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        cEg_(this.h.getContentView());
        cEe_(view, i, iArr);
        cEf_(view, i, iArr);
        cEd_(view, i, iArr);
    }

    public static void e() {
        for (WeakReference weakReference : new ArrayList(f15437a)) {
            nqc nqcVar = (nqc) weakReference.get();
            if (nqcVar == null || !nqcVar.c()) {
                f15437a.remove(weakReference);
            } else {
                nqcVar.b();
                WeakReference<View> weakReference2 = nqcVar.g;
                if (weakReference2 != null && weakReference2.get() != null) {
                    final View view = nqcVar.g.get();
                    view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: nqc.3
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            View view2;
                            if (nqc.this == null || (view2 = view) == null) {
                                return;
                            }
                            view2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            nqc nqcVar2 = nqc.this;
                            nqcVar2.cEh_(view, nqcVar2.f);
                        }
                    });
                }
            }
        }
    }

    private void cEe_(View view, int i, int[] iArr) {
        switch (i) {
            case 0:
                int width = (iArr[0] + view.getWidth()) - this.e;
                if (LanguageUtil.bc(this.b)) {
                    width = iArr[0];
                }
                this.h.showAtLocation(view, 0, width, iArr[1] + view.getHeight());
                break;
            case 1:
                if (LanguageUtil.bc(this.b)) {
                    this.h.showAtLocation(view, 0, iArr[0] - nsn.c(this.b, 48.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 3.0f));
                    break;
                } else {
                    PopupWindow popupWindow = this.h;
                    int i2 = iArr[0];
                    int width2 = view.getWidth();
                    int c = nsn.c(this.b, 48.0f);
                    popupWindow.showAtLocation(view, 0, ((i2 + width2) + c) - this.e, iArr[1] + view.getHeight() + nsn.c(this.b, 3.0f));
                    break;
                }
            case 2:
                PopupWindow popupWindow2 = this.h;
                int i3 = iArr[0];
                int c2 = nsn.c(this.b, 4.0f);
                popupWindow2.showAtLocation(view, 0, (i3 - c2) - this.e, (iArr[1] + nsn.c(this.b, 17.0f)) - (this.d / 2));
                break;
            case 3:
                PopupWindow popupWindow3 = this.h;
                int i4 = iArr[0];
                int i5 = this.e / 2;
                popupWindow3.showAtLocation(view, 0, (i4 - i5) + nsn.c(this.b, 42.0f), (iArr[1] - nsn.c(this.b, 4.0f)) - this.d);
                break;
            case 4:
                PopupWindow popupWindow4 = this.h;
                int i6 = iArr[0];
                int c3 = nsn.c(this.b, 180.0f);
                popupWindow4.showAtLocation(view, 0, (i6 + c3) - (this.e / 2), iArr[1] + nsn.c(this.b, 115.0f));
                break;
            case 5:
                PopupWindow popupWindow5 = this.h;
                int i7 = iArr[0];
                int c4 = nsn.c(this.b, 4.0f);
                int width3 = view.getWidth();
                popupWindow5.showAtLocation(view, 0, ((i7 + c4) + width3) - this.e, iArr[1] + nsn.c(this.b, 4.0f) + view.getHeight());
                break;
            case 6:
                PopupWindow popupWindow6 = this.h;
                int i8 = iArr[0];
                int width4 = view.getWidth() / 2;
                popupWindow6.showAtLocation(view, 0, (i8 + width4) - nsn.c(this.b, 30.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 3.0f));
                break;
        }
    }

    private void cEf_(View view, int i, int[] iArr) {
        if (i != 20) {
            switch (i) {
                case 13:
                    PopupWindow popupWindow = this.h;
                    int right = view.getRight();
                    int width = view.getWidth() / 2;
                    popupWindow.showAtLocation(view, 0, (right - width) - nsn.c(this.b, 16.0f), (iArr[1] - nsn.c(this.b, 2.0f)) - this.d);
                    break;
                case 14:
                    if (LanguageUtil.bc(this.b)) {
                        this.h.showAtLocation(view, 0, nsn.c(this.b, 12.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 4.0f));
                        break;
                    } else {
                        PopupWindow popupWindow2 = this.h;
                        int right2 = view.getRight();
                        int i2 = this.e;
                        popupWindow2.showAtLocation(view, 0, (right2 - i2) - nsn.c(this.b, 12.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 4.0f));
                        break;
                    }
                case 15:
                    if (LanguageUtil.bc(this.b)) {
                        int width2 = ((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth();
                        int i3 = this.e;
                        int c = nsn.c(this.b, 16.0f);
                        this.h.showAtLocation(view, 0, (width2 - i3) - c, iArr[1] + view.getHeight() + nsn.c(this.b, 17.0f));
                        break;
                    } else {
                        this.h.showAtLocation(view, 0, nsn.c(this.b, 16.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 17.0f));
                        break;
                    }
                case 16:
                    if (LanguageUtil.bc(this.b)) {
                        this.h.showAtLocation(view, 0, nsn.c(this.b, 12.0f), (iArr[1] - nsn.c(this.b, 3.0f)) - this.d);
                        break;
                    } else {
                        PopupWindow popupWindow3 = this.h;
                        int right3 = view.getRight();
                        int i4 = this.e;
                        popupWindow3.showAtLocation(view, 0, (right3 - i4) - nsn.c(this.b, 12.0f), (iArr[1] - nsn.c(this.b, 3.0f)) - this.d);
                        break;
                    }
            }
        }
        cEc_(view, iArr);
    }

    private void cEd_(View view, int i, int[] iArr) {
        if (view.getWindowToken() == null) {
            LogUtil.h("PopView", "view.getWindowToken() is null");
        }
        switch (i) {
            case 7:
                PopupWindow popupWindow = this.h;
                int i2 = iArr[0];
                int width = view.getWidth() / 8;
                popupWindow.showAtLocation(view, 0, (i2 + (width * 5)) - (this.e / 2), (iArr[1] + view.getHeight()) - nsn.c(this.b, 10.0f));
                break;
            case 8:
                PopupWindow popupWindow2 = this.h;
                int i3 = iArr[0];
                int c = nsn.c(this.b, 4.0f);
                popupWindow2.showAtLocation(view, 0, (i3 - c) + this.e, iArr[1] + nsn.c(this.b, 17.0f));
                break;
            case 9:
                PopupWindow popupWindow3 = this.h;
                int i4 = iArr[0];
                int c2 = nsn.c(this.b, 4.0f);
                popupWindow3.showAtLocation(view, 0, (i4 - c2) + this.e, iArr[1]);
                break;
            case 10:
                PopupWindow popupWindow4 = this.h;
                int i5 = iArr[0];
                int width2 = view.getWidth();
                int i6 = this.e;
                popupWindow4.showAtLocation(view, 0, ((i5 + width2) - i6) + nsn.c(this.b, 8.0f), (iArr[1] + view.getHeight()) - nsn.c(this.b, 3.0f));
                break;
            default:
                switch (i) {
                    case 17:
                        int height = iArr[1] + view.getHeight();
                        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131364634_res_0x7f0a0b1a);
                        if (!SystemInfo.b()) {
                            dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
                            height -= this.b.getResources().getDimensionPixelSize(R.dimen._2131362914_res_0x7f0a0462);
                        }
                        int h = nsn.h(this.b);
                        if (LanguageUtil.bc(this.b)) {
                            this.h.showAtLocation(view, 0, dimensionPixelSize, height);
                            break;
                        } else {
                            this.h.showAtLocation(view, 0, (h - this.e) - dimensionPixelSize, height);
                            break;
                        }
                    case 18:
                        this.h.showAtLocation(view, 0, iArr[0] - nsn.c(this.b, 8.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 8.0f));
                        break;
                    case 19:
                        this.h.showAtLocation(view, 0, nsn.c(this.b, 16.0f), iArr[1] + view.getHeight() + nsn.c(this.b, 8.0f));
                        break;
                }
        }
    }

    private void cEc_(View view, int[] iArr) {
        Context context = this.b;
        if (context instanceof Activity) {
            if (LanguageUtil.bc(context)) {
                PopupWindow popupWindow = this.h;
                int width = ((Activity) this.b).getWindowManager().getDefaultDisplay().getWidth();
                int i = this.e;
                popupWindow.showAtLocation(view, 0, (width - i) - nsn.c(this.b, 16.0f), iArr[1] + view.getHeight());
                return;
            }
            this.h.showAtLocation(view, 0, nsn.c(this.b, 16.0f), iArr[1] + view.getHeight());
        }
    }

    public void b() {
        this.h.dismiss();
        for (WeakReference weakReference : new ArrayList(f15437a)) {
            if (((nqc) weakReference.get()) == this) {
                f15437a.remove(weakReference);
                return;
            }
        }
    }

    public void cEg_(View view) {
        if (view != null) {
            view.measure(0, 0);
            this.e = view.getMeasuredWidth();
            this.d = view.getMeasuredHeight();
        }
    }
}

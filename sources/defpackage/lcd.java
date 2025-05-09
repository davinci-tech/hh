package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class lcd {

    /* renamed from: a, reason: collision with root package name */
    private final Context f14760a;
    private SportEquipItemDrawer b;
    private lbu c;
    private final boolean d;
    private lbs e;
    private LinearLayout f;
    private SportEquipItemDrawer g;
    private LinearLayout h;
    private LinearLayout j;
    private int k;
    private final View l;
    private IndoorEquipViewModel m;
    private int o;
    private String q;
    private LinearLayout.LayoutParams i = new LinearLayout.LayoutParams(-2, -2);
    private LinearLayout.LayoutParams n = new LinearLayout.LayoutParams(-2, -2);

    public lcd(View view, boolean z, IndoorEquipViewModel indoorEquipViewModel, Context context) {
        this.q = "";
        this.l = view;
        this.d = z;
        this.m = indoorEquipViewModel;
        if (indoorEquipViewModel != null) {
            this.k = indoorEquipViewModel.getSportType();
            this.o = indoorEquipViewModel.getSportTarget();
            this.q = indoorEquipViewModel.r();
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "mSportType=", Integer.valueOf(this.k), ", mTargetType=", Integer.valueOf(this.o), ", hasRunPostureDevice=", Boolean.valueOf(this.m.p()), ", mHasWear=", Boolean.valueOf(z));
        }
        this.f14760a = context;
        a();
    }

    private void a() {
        this.f = (LinearLayout) this.l.findViewById(R.id.land_left_data_layout);
        this.h = (LinearLayout) this.l.findViewById(R.id.land_right_data_layout);
        this.j = (LinearLayout) this.l.findViewById(R.id.land_layout_chart);
    }

    public void e(Map<Integer, Object> map) {
        if (this.l == null) {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "mRootView == null");
            return;
        }
        if (map == null) {
            return;
        }
        b(map, false);
        if (this.b.getParent() != null && (this.b.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        if (this.g.getParent() != null && (this.g.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        }
        this.h.addView(this.g);
        this.f.addView(this.b);
        LogUtil.c("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "add view");
    }

    public void a(Map<Integer, Object> map) {
        if (this.l == null) {
            LogUtil.b("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "mRootView == null");
            return;
        }
        if (map == null) {
            return;
        }
        b(map, true);
        if (this.b.getParent() != null && (this.b.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        if (this.g.getParent() != null && (this.g.getParent() instanceof ViewGroup)) {
            ((ViewGroup) this.g.getParent()).removeView(this.g);
        }
        this.h.addView(this.g);
        this.f.addView(this.b);
        LogUtil.c("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "add view");
    }

    public void c(SupportDataRange supportDataRange) {
        this.b = new SportEquipItemDrawer(this.f14760a);
        this.g = new SportEquipItemDrawer(this.f14760a);
        HashMap hashMap = new HashMap();
        this.c = new lbu(this.f14760a, this.k, this.d, supportDataRange);
        b(hashMap, false);
    }

    public void e(float f, float f2, SupportDataRange supportDataRange) {
        this.b = new SportEquipItemDrawer(this.f14760a);
        this.g = new SportEquipItemDrawer(this.f14760a);
        HashMap hashMap = new HashMap();
        this.e = new lbs(this.f14760a, this.k, this.d, f, f2, supportDataRange);
        b(hashMap, true);
    }

    private void b(Map<Integer, Object> map, boolean z) {
        int i = this.k;
        if (i == 264) {
            if (this.m.p()) {
                e(map, 2, z);
                return;
            } else {
                d(map, 1, 1, z);
                return;
            }
        }
        if (i == 265 || i == 273 || i == 274) {
            e(map, 2, z);
        } else if (i == 281) {
            d(map, 1, 1, z);
        } else {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "other sportType =", Integer.valueOf(i));
        }
    }

    private void e(Map<Integer, Object> map, int i, boolean z) {
        int[] d;
        if (nsn.ag(this.f14760a)) {
            this.i.setMarginStart(0);
            d();
        } else {
            this.i.setMarginStart((int) this.f14760a.getResources().getDimension(R.dimen._2131364635_res_0x7f0a0b1b));
        }
        if (this.k == 265) {
            this.i.setMarginStart(0);
        }
        if (nsn.ae(this.f14760a)) {
            this.i.setMarginStart(nsn.c(this.f14760a, 80.0f));
        }
        this.b.e(2);
        this.j.setVisibility(0);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.b.setLayoutParams(this.i);
        if (this.k == 264 && this.m.q()) {
            d = lbj.c(this.o);
        } else {
            d = lbj.d(this.k, 4, this.o, this.q);
        }
        LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayViewHolder", Arrays.toString(d));
        if (z) {
            LogUtil.a("Track_IDEQ_IndoorEquipLandDisplayViewHolder", "setIndoorDisplayInLeft");
            this.e.d(this.b, map, d, i);
        } else {
            this.c.e(this.b, map, d, i);
        }
    }

    private void d() {
        if (this.j.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            layoutParams.width = nsn.c(this.f14760a, 200.0f);
            if (nsn.ae(this.f14760a)) {
                layoutParams.setMarginEnd(nsn.c(this.f14760a, 122.0f));
            }
            this.j.setLayoutParams(layoutParams);
        }
    }

    private void d(Map<Integer, Object> map, int i, int i2, boolean z) {
        this.n.setMarginStart(nsn.c(this.f14760a, 160.0f));
        if (z) {
            this.i.setMarginStart(nsn.c(this.f14760a, 38.0f));
        } else if (nrs.a(this.f14760a)) {
            this.i.setMarginStart(nsn.c(this.f14760a, 88.0f));
        } else {
            this.i.setMarginStart(nsn.c(this.f14760a, 38.0f));
        }
        if (nsn.ag(this.f14760a)) {
            this.n.setMarginStart(nsn.c(this.f14760a, 160.0f));
            this.i.setMarginStart(nsn.c(this.f14760a, 38.0f));
        }
        if (nsn.ae(this.f14760a)) {
            this.n.setMargins(nsn.c(this.f14760a, 286.0f), nsn.c(this.f14760a, 42.0f), 0, 0);
            this.i.setMarginStart(nsn.c(this.f14760a, 122.0f));
        }
        this.g.setLayoutParams(this.n);
        this.b.setLayoutParams(this.i);
        this.j.setVisibility(8);
        this.g.setVisibility(0);
        this.h.setVisibility(0);
        this.b.e(1);
        this.g.e(1);
        int[] d = lbj.d(this.k, 2, this.o, this.q);
        int[] d2 = lbj.d(this.k, 3, this.o, this.q);
        if (z) {
            this.e.d(this.b, map, d, i);
            this.e.b(this.g, map, d2, i2);
        } else {
            this.c.e(this.b, map, d, i);
            this.c.c(this.g, map, d2, i2);
        }
    }
}

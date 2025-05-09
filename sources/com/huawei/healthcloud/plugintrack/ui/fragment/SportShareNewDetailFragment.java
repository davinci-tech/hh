package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.TrackShareNewDetailView;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.feb;
import defpackage.feh;
import defpackage.gvf;
import defpackage.gxp;
import defpackage.hjd;
import defpackage.hjw;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import huawei.android.hwcolorpicker.HwColorPicker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class SportShareNewDetailFragment extends BaseFragment {
    private TrackShareNewDetailView f;
    private MotionPath i;
    private Context j;
    private View k;
    private List<hjd> m;
    private MotionPathSimplify n;
    private feh o;
    private hjw p;
    private static final int[] e = {R.drawable.share_geometry_1, R.drawable.share_geometry_2, R.drawable.share_geometry_3};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f3733a = {R.drawable.share_run_default_background};
    private static final int[] b = {R.drawable.share_cycling_default_background};
    private static final int[] d = {R.drawable.basketball_background_default};
    private static final int[] c = {R.drawable.climb_hill_background_default};
    private static final int[] g = {R.drawable.swim_background_default};
    private ArrayList<Integer> h = new ArrayList<>(16);
    private int l = 0;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
        this.j = getActivity();
        this.k = layoutInflater.inflate(R.layout.track_fragment_sport_share_new, viewGroup, false);
        gxp a2 = gxp.a();
        hjw c2 = a2.c();
        this.p = c2;
        if (c2 == null) {
            return new View(this.j);
        }
        this.m = a2.f();
        h();
        e();
        return this.k;
    }

    private void j() {
        if (i()) {
            this.i = this.p.d();
            this.l = this.n.requestSportType();
            gvf gvfVar = new gvf(this.j, this.n, this.i, this.m);
            gvfVar.e();
            if (this.p.ax()) {
                gvfVar.e(getString(R.string._2130847254_res_0x7f022616));
            }
            gvfVar.b(this.p.h());
            this.o = gvfVar.a();
            g();
        }
    }

    private void e() {
        if (i()) {
            j();
        }
    }

    private boolean i() {
        MotionPathSimplify e2 = this.p.e();
        this.n = e2;
        if (e2 != null) {
            return true;
        }
        LogUtil.h("Track_SportShareNewDetailFragment", "mSimplify is null");
        return false;
    }

    public List<feb> a() {
        ArrayList arrayList;
        ArrayList arrayList2;
        List singletonList;
        if (LanguageUtil.m(this.j)) {
            arrayList = new ArrayList(Arrays.asList(Integer.valueOf(R.drawable.sport_share_watermark_1005), Integer.valueOf(R.drawable.sport_share_watermark_1006)));
            arrayList2 = new ArrayList(Arrays.asList(Integer.valueOf(R.drawable.sport_share_watermark_1005), Integer.valueOf(R.drawable.sport_share_watermark_1026)));
            singletonList = Collections.singletonList(Integer.valueOf(R.drawable.ic_sport_share_watermark_1166));
        } else {
            arrayList = new ArrayList(Arrays.asList(Integer.valueOf(R.drawable.sport_share_watermark_1005_english), Integer.valueOf(R.drawable.sport_share_watermark_1006_english)));
            arrayList2 = new ArrayList(Arrays.asList(Integer.valueOf(R.drawable.sport_share_watermark_1005_english), Integer.valueOf(R.drawable.sport_share_watermark_1026_english)));
            singletonList = Collections.singletonList(Integer.valueOf(R.drawable.ic_sport_share_watermark_1166_english));
        }
        ArrayList arrayList3 = new ArrayList(Arrays.asList(1005, 1006));
        ArrayList arrayList4 = new ArrayList(Arrays.asList(1005, 1026));
        ArrayList arrayList5 = new ArrayList(2);
        int i = this.l;
        int i2 = 0;
        if (i == 217) {
            while (i2 < 2) {
                feb febVar = new feb();
                febVar.e(((Integer) arrayList2.get(i2)).intValue());
                febVar.d(((Integer) arrayList4.get(i2)).intValue());
                arrayList5.add(febVar);
                i2++;
            }
        } else if (i == 222) {
            feb febVar2 = new feb();
            febVar2.e(((Integer) singletonList.get(0)).intValue());
            febVar2.d(1166);
            arrayList5.add(febVar2);
        } else {
            while (i2 < 2) {
                feb febVar3 = new feb();
                febVar3.e(((Integer) arrayList.get(i2)).intValue());
                febVar3.d(((Integer) arrayList3.get(i2)).intValue());
                arrayList5.add(febVar3);
                i2++;
            }
        }
        return arrayList5;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void g() {
        /*
            r3 = this;
            int r0 = r3.l
            r1 = 262(0x106, float:3.67E-43)
            r2 = 0
            if (r0 == r1) goto L4e
            r1 = 271(0x10f, float:3.8E-43)
            if (r0 == r1) goto L40
            r1 = 280(0x118, float:3.92E-43)
            if (r0 == r1) goto L32
            switch(r0) {
                case 258: goto L32;
                case 259: goto L24;
                case 260: goto L16;
                default: goto L12;
            }
        L12:
            switch(r0) {
                case 264: goto L32;
                case 265: goto L24;
                case 266: goto L4e;
                default: goto L15;
            }
        L15:
            goto L5b
        L16:
            int[] r0 = com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.c
            r0 = r0[r2]
            java.util.ArrayList<java.lang.Integer> r1 = r3.h
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L5b
        L24:
            int[] r0 = com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.b
            r0 = r0[r2]
            java.util.ArrayList<java.lang.Integer> r1 = r3.h
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L5b
        L32:
            int[] r0 = com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.f3733a
            r0 = r0[r2]
            java.util.ArrayList<java.lang.Integer> r1 = r3.h
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L5b
        L40:
            int[] r0 = com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.d
            r0 = r0[r2]
            java.util.ArrayList<java.lang.Integer> r1 = r3.h
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L5b
        L4e:
            int[] r0 = com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.g
            r0 = r0[r2]
            java.util.ArrayList<java.lang.Integer> r1 = r3.h
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
        L5b:
            java.util.ArrayList<java.lang.Integer> r0 = r3.h
            int[] r1 = com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.e
            r3.c(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.fragment.SportShareNewDetailFragment.g():void");
    }

    public View bfx_() {
        return this.f;
    }

    private void h() {
        int c2;
        Bundle arguments = getArguments();
        f();
        WindowManager windowManager = (WindowManager) this.j.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int c3 = nsn.c(this.j, 250.0f);
        if (arguments != null) {
            c3 = arguments.getInt("allDataWidth", c3);
            c2 = arguments.getInt("allDataHeight", nsn.c(this.j, 400.0f));
        } else {
            c2 = nsn.c(this.j, 400.0f);
        }
        c(i);
        boolean isEnable = HwColorPicker.isEnable();
        boolean bh = CommonUtil.bh();
        boolean z = isEnable && bh;
        LogUtil.a("Track_SportShareNewDetailFragment", "HwColorPicker isEnable ", Boolean.valueOf(isEnable), " , isHuaweiPhone ", Boolean.valueOf(bh));
        float f = (c3 * 1.0f) / i;
        int c4 = (int) ((i + nsn.c(this.j, 48.0f)) * f);
        View findViewById = this.k.findViewById(R.id.track_share_all_white);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        layoutParams.height = (int) (((c2 - c4) - (z ? nsn.c(this.j, 34.0f) : 0)) / 2.0f);
        findViewById.setLayoutParams(layoutParams);
        View findViewById2 = this.k.findViewById(R.id.track_share_new_detail_position);
        ViewGroup.LayoutParams layoutParams2 = findViewById2.getLayoutParams();
        layoutParams2.height = c4;
        findViewById2.setLayoutParams(layoutParams2);
        a(f);
    }

    private void f() {
        this.f = (TrackShareNewDetailView) this.k.findViewById(R.id.track_share_new_detail);
    }

    private void c(int i) {
        if (LanguageUtil.bc(this.j)) {
            this.f.setPivotX(i);
            this.f.setPivotY(0.0f);
            ((ImageView) this.k.findViewById(R.id.track_share_new_arrow)).setImageResource(R.drawable._2131427553_res_0x7f0b00e1);
        } else {
            this.f.setPivotX(0.0f);
            this.f.setPivotY(0.0f);
        }
    }

    private void a(float f) {
        this.f.setScaleX(f);
        this.f.setScaleY(f);
    }

    public ArrayList<Integer> d() {
        return this.h;
    }

    public feh b() {
        return this.o;
    }

    private void c(List<Integer> list, int[] iArr) {
        for (int i : iArr) {
            list.add(Integer.valueOf(i));
        }
    }
}

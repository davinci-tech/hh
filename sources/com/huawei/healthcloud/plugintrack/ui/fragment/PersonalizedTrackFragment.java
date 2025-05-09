package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.DynamicTrackActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.PersonalizedTrackFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.scrollview.HealthBottomView;
import com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView;
import defpackage.hcr;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class PersonalizedTrackFragment extends BaseFragment {
    private HealthBottomView b;
    private DynamicTrackActivity c;
    private Context d;
    private View f;
    private LinearLayout g;
    private View h;
    private View i;
    private View j;
    private LinearLayout k;
    private LinearLayout l;
    private int m;
    private View n;
    private Rect o;
    private LinearLayout p;
    private LinearLayout t;

    /* renamed from: a, reason: collision with root package name */
    private int f3721a = 0;
    private boolean e = false;

    public void c(int i) {
        this.m = i;
    }

    public void b(boolean z) {
        this.e = z;
        a();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = getActivity();
        if (getActivity() instanceof DynamicTrackActivity) {
            this.c = (DynamicTrackActivity) getActivity();
        }
        return layoutInflater.inflate(R.layout.fragment_track_personalized, viewGroup, false);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.n = view;
        this.b = (HealthBottomView) view.findViewById(R.id.dynamic_track_personalized_tabs);
        this.h = view.findViewById(R.id.dynamic_track_album);
        this.j = view.findViewById(R.id.map_dialog_layout);
        this.f = view.findViewById(R.id.dynamic_track_music);
        this.i = view.findViewById(R.id.dynamic_track_playback_view);
        this.c.initAlbumView(this.h);
        this.c.initMapTypeLayout(this.j);
        this.c.initViewSelectLayout(this.i);
        g();
        this.c.getWindow().setSoftInputMode(18);
        this.c.getWindow().setGravity(80);
        this.c.getWindow().setDimAmount(0.0f);
        i();
        this.p = (LinearLayout) view.findViewById(R.id.track_tab_mark_layer_layout);
        if (!this.e) {
            beO_(this.n);
        }
        this.o = new Rect();
        final View findViewById = this.h.findViewById(R.id.track_album_soft_input_view);
        final ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        view.findViewById(R.id.fragment_personalized_container).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: hht
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                PersonalizedTrackFragment.this.beT_(layoutParams, findViewById);
            }
        });
    }

    public /* synthetic */ void beT_(ViewGroup.LayoutParams layoutParams, View view) {
        int height = this.c.getWindow().getDecorView().getHeight();
        this.c.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.o);
        int i = height - this.o.bottom;
        if (i > height / 4) {
            this.c.b(true);
            this.p.setVisibility(0);
            layoutParams.height = i - this.b.getMeasuredHeight();
            view.setVisibility(0);
            view.setLayoutParams(layoutParams);
            return;
        }
        this.c.b(false);
        hcr.bai_(this.c);
        view.setVisibility(8);
        if (this.d.getSharedPreferences("retrack_file", 0).getBoolean("has_use_personalized_map", false)) {
            this.p.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beO_(View view) {
        Drawable drawable;
        if (this.d.getSharedPreferences("retrack_file", 0).getBoolean("has_use_personalized_map", false)) {
            LogUtil.a("Track_PersonalizedTrackFragment", "[handleGuideVisible] has click guide.");
            return;
        }
        View view2 = this.h;
        if (view2 == null || view == null) {
            LogUtil.b("Track_PersonalizedTrackFragment", "[handleGuideVisible] view is null.");
            return;
        }
        this.t = (LinearLayout) view2.findViewById(R.id.track_album_tip_layout);
        this.g = (LinearLayout) this.h.findViewById(R.id.track_album_mark_layer);
        this.l = (LinearLayout) view.findViewById(R.id.track_save_mark_layer);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.track_save_tip_layout);
        this.k = linearLayout;
        if (LanguageUtil.bc(this.d)) {
            drawable = this.d.getResources().getDrawable(R.drawable._2131429701_res_0x7f0b0945);
        } else {
            drawable = this.d.getResources().getDrawable(R.drawable._2131429702_res_0x7f0b0946);
        }
        linearLayout.setBackground(drawable);
        ViewGroup.LayoutParams layoutParams = this.k.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.topMargin += this.m;
            this.k.setLayoutParams(layoutParams2);
        }
        this.t.setVisibility(0);
        this.g.setVisibility(0);
        this.p.setVisibility(0);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: hhw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                PersonalizedTrackFragment.this.beR_(view3);
            }
        };
        this.g.setOnClickListener(onClickListener);
        this.h.findViewById(R.id.track_album_tip_got_it).setOnClickListener(onClickListener);
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: hhu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                PersonalizedTrackFragment.this.beS_(view3);
            }
        };
        this.l.setOnClickListener(onClickListener2);
        view.findViewById(R.id.save_tip_got_it).setOnClickListener(onClickListener2);
    }

    public /* synthetic */ void beR_(View view) {
        this.t.setVisibility(8);
        this.g.setVisibility(8);
        this.k.setVisibility(0);
        this.l.setVisibility(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void beS_(View view) {
        this.k.setVisibility(8);
        this.l.setVisibility(8);
        this.p.setVisibility(8);
        f();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        SharedPreferences.Editor edit = this.d.getSharedPreferences("retrack_file", 0).edit();
        edit.putBoolean("has_use_personalized_map", true);
        edit.commit();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        hcr.bai_(this.c);
        c();
        this.c.f();
    }

    private void i() {
        this.b.setBottomNavListener(new HwBottomNavigationView.BottomNavListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.PersonalizedTrackFragment.3
            @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavListener
            public void onBottomNavItemReselected(MenuItem menuItem, int i) {
            }

            @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavListener
            public void onBottomNavItemUnselected(MenuItem menuItem, int i) {
            }

            @Override // com.huawei.uikit.hwbottomnavigationview.widget.HwBottomNavigationView.BottomNavListener
            public void onBottomNavItemSelected(MenuItem menuItem, int i) {
                ReleaseLogUtil.e("Track_PersonalizedTrackFragment", "tab view choose with ", Integer.valueOf(i));
                PersonalizedTrackFragment.this.f3721a = i;
                PersonalizedTrackFragment.this.c();
                if (i == 0) {
                    PersonalizedTrackFragment.this.h.setVisibility(0);
                    if (PersonalizedTrackFragment.this.e) {
                        PersonalizedTrackFragment personalizedTrackFragment = PersonalizedTrackFragment.this;
                        personalizedTrackFragment.beO_(personalizedTrackFragment.n);
                    }
                    PersonalizedTrackFragment personalizedTrackFragment2 = PersonalizedTrackFragment.this;
                    personalizedTrackFragment2.beP_(personalizedTrackFragment2.j, PersonalizedTrackFragment.this.f, PersonalizedTrackFragment.this.i);
                    PersonalizedTrackFragment.this.c.c("common");
                    return;
                }
                if (i == 1) {
                    PersonalizedTrackFragment.this.j.setVisibility(0);
                    PersonalizedTrackFragment personalizedTrackFragment3 = PersonalizedTrackFragment.this;
                    personalizedTrackFragment3.beP_(personalizedTrackFragment3.h, PersonalizedTrackFragment.this.f, PersonalizedTrackFragment.this.i);
                    PersonalizedTrackFragment.this.c.c("mapPage");
                    return;
                }
                if (i == 2) {
                    PersonalizedTrackFragment.this.f.setVisibility(0);
                    PersonalizedTrackFragment.this.c.chooseMusic(PersonalizedTrackFragment.this.f);
                    PersonalizedTrackFragment personalizedTrackFragment4 = PersonalizedTrackFragment.this;
                    personalizedTrackFragment4.beP_(personalizedTrackFragment4.h, PersonalizedTrackFragment.this.j, PersonalizedTrackFragment.this.i);
                    PersonalizedTrackFragment.this.c.c("musicPage");
                    return;
                }
                if (i == 3) {
                    PersonalizedTrackFragment.this.i.setVisibility(0);
                    PersonalizedTrackFragment personalizedTrackFragment5 = PersonalizedTrackFragment.this;
                    personalizedTrackFragment5.beP_(personalizedTrackFragment5.h, PersonalizedTrackFragment.this.j, PersonalizedTrackFragment.this.f);
                    PersonalizedTrackFragment.this.c.c("playAngle");
                    return;
                }
                ReleaseLogUtil.c("Track_PersonalizedTrackFragment", "[onBottomNavItemSelected] item is not find.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beP_(View view, View view2, View view3) {
        beQ_(view);
        beQ_(view2);
        beQ_(view3);
    }

    private void beQ_(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private void g() {
        this.b.c();
        c(R.string._2130839958_res_0x7f020996, LanguageUtil.bc(this.d) ? R.drawable._2131431903_res_0x7f0b11df : R.drawable._2131431902_res_0x7f0b11de);
        c(R.string._2130839959_res_0x7f020997, R.drawable._2131431883_res_0x7f0b11cb);
        c(R.string._2130839960_res_0x7f020998, R.drawable._2131431906_res_0x7f0b11e2);
        c(R.string._2130839961_res_0x7f020999, LanguageUtil.bc(this.d) ? R.drawable._2131431800_res_0x7f0b1178 : R.drawable._2131431799_res_0x7f0b1177);
        a();
        c();
    }

    private void a() {
        HealthBottomView healthBottomView = this.b;
        if (healthBottomView == null || this.j == null) {
            LogUtil.h("Track_PersonalizedTrackFragment", "initPremiumEditionView: view is null");
            return;
        }
        if (!this.e) {
            healthBottomView.setItemChecked(0);
            this.f3721a = 0;
            return;
        }
        healthBottomView.setItemChecked(1);
        this.f3721a = 1;
        this.j.setVisibility(0);
        beQ_(this.h);
        beQ_(this.f);
        beQ_(this.i);
    }

    private void c(int i, int i2) {
        this.b.dZI_(i, this.d.getResources().getDrawable(i2), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.c.d()) {
            d();
        }
        e();
        b();
    }

    private void d() {
        DynamicTrackActivity dynamicTrackActivity = this.c;
        if (dynamicTrackActivity == null) {
            LogUtil.h("Track_PersonalizedTrackFragment", "changeLineStatus activity is null");
        } else if (this.f3721a == 0) {
            dynamicTrackActivity.i();
        } else {
            dynamicTrackActivity.h();
        }
    }

    private void e() {
        DynamicTrackActivity dynamicTrackActivity = this.c;
        if (dynamicTrackActivity == null) {
            LogUtil.h("Track_PersonalizedTrackFragment", "changeLineStatus activity is null");
        } else if (this.f3721a != 2) {
            dynamicTrackActivity.o();
        } else {
            dynamicTrackActivity.g();
        }
    }

    private void b() {
        DynamicTrackActivity dynamicTrackActivity = this.c;
        if (dynamicTrackActivity == null) {
            LogUtil.h("Track_PersonalizedTrackFragment", "changeLineStatus activity is null");
        } else if (this.f3721a == 1) {
            dynamicTrackActivity.c();
        }
    }
}

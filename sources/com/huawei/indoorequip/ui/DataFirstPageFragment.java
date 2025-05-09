package com.huawei.indoorequip.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.view.MusicControlLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipDisplayActivity;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.ui.view.SportEquipItemDrawer;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.diy;
import defpackage.gwg;
import defpackage.gwh;
import defpackage.jed;
import defpackage.koq;
import defpackage.lbc;
import defpackage.lbj;
import defpackage.lbt;
import defpackage.lbv;
import defpackage.nrs;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DataFirstPageFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private IndoorEquipDisplayActivity f6432a;
    private View aa;
    private int ab;
    private ImageView b;
    private lbt c;
    private ImageView d;
    private Context e;
    private IntermitentJumpData f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthColumnLinearLayout j;
    private ImageView k;
    private MusicControlLayout l;
    private HealthTextView n;
    private ImageView o;
    private RelativeLayout p;
    private HealthProgressBar q;
    private HealthProgressBar r;
    private HealthTextView s;
    private RelativeLayout t;
    private HealthTextView u;
    private SportEquipItemDrawer w;
    private HealthTextView x;
    private int y;
    private HealthTextView z;
    private long m = 0;
    private boolean i = false;
    private int v = 6;

    public static DataFirstPageFragment bVw_(Bundle bundle) {
        DataFirstPageFragment dataFirstPageFragment = new DataFirstPageFragment();
        dataFirstPageFragment.setArguments(bundle);
        return dataFirstPageFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (nsn.ag(this.e)) {
            this.aa = layoutInflater.inflate(R.layout.data_first_page_layout_tahiti, (ViewGroup) null);
        } else {
            this.aa = layoutInflater.inflate(R.layout.data_first_page_layout, (ViewGroup) null);
        }
        bVq_(this.aa);
        return this.aa;
    }

    private void bVq_(View view) {
        if (isAdded() && getActivity() != null) {
            getActivity().setRequestedOrientation(1);
            if (getActivity() instanceof IndoorEquipDisplayActivity) {
                this.f6432a = (IndoorEquipDisplayActivity) getActivity();
            }
        }
        this.y = getArguments().getInt("deviceType");
        this.v = getArguments().getInt("currentSkipperTargetType");
        this.ab = getArguments().getInt("currentSkipperTarget");
        if (this.y == 283) {
            this.f = (IntermitentJumpData) getArguments().getParcelable("type_intermittent_jump_mode_data");
        }
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "Current sport :", Integer.valueOf(this.y), "current target :", Integer.valueOf(this.v), "current  value:", Integer.valueOf(this.ab));
        bVp_(view);
        bVv_(view);
        h();
        this.l = (MusicControlLayout) view.findViewById(R.id.music_content_layout);
        HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) view.findViewById(R.id.music_column_layout);
        this.j = healthColumnLinearLayout;
        healthColumnLinearLayout.setPadding(0, nsn.c(this.e, 8.0f), 0, 0);
        this.l.setSportTypeDrawable(true, this.y);
        if (lbc.b(this.y)) {
            this.o.setVisibility(8);
            this.j.setVisibility(8);
        } else if (this.l.getTrackSharedPreferenceUtil().f(this.y) == 1 && gwg.a(this.e)) {
            this.j.setVisibility(0);
            this.o.setVisibility(8);
        } else {
            this.j.setVisibility(8);
            this.o.setVisibility(0);
        }
    }

    private void h() {
        if (Utils.d() == 1 && CommonUtil.bh() && CommonUtil.ba() && this.y != 283) {
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
    }

    private void bVv_(View view) {
        this.b = (ImageView) view.findViewById(R.id.ie_bt_icon);
        this.d = (ImageView) view.findViewById(R.id.ie_bolt_icon);
        this.q = (HealthProgressBar) view.findViewById(R.id.hw_recycler_loading_hpb);
        ImageView imageView = (ImageView) view.findViewById(R.id.ie_music_icon);
        this.o = imageView;
        imageView.setImageDrawable(lbv.bVR_(this.e, R.drawable._2131430349_res_0x7f0b0bcd));
        this.b.setImageDrawable(lbv.bVR_(this.e, lbj.c(this.y, true)));
        if (this.f6432a.ai()) {
            this.d.setVisibility(0);
            this.d.setImageResource(lbv.a(this.f6432a.ah()));
        }
        this.k = (ImageView) view.findViewById(R.id.ie_miracast_icon);
    }

    private void bVp_(View view) {
        view.findViewById(R.id.view_stub_distance).setVisibility(0);
        this.t = (RelativeLayout) view.findViewById(R.id.relativeLayout_distance_value);
        this.p = (RelativeLayout) view.findViewById(R.id.relativeLayout_distance_value_rope_skipping);
        if (this.y == 283 || "291".equals(g())) {
            this.t.setVisibility(8);
            this.p.setVisibility(0);
            this.g = (HealthTextView) view.findViewById(R.id.distance_value_rope_skipping);
            this.h = (HealthTextView) view.findViewById(R.id.distance_unit_rope_skipping);
            view.findViewById(R.id.ie_layout_map_tracking).setBackgroundResource(R.color._2131299296_res_0x7f090be0);
            if (!nrs.e(this.e)) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen._2131363122_res_0x7f0a0532);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout_view_stub);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setOrientation(1);
            }
        } else {
            this.t.setVisibility(0);
            this.p.setVisibility(8);
            this.g = (HealthTextView) view.findViewById(R.id.distance_value);
            this.h = (HealthTextView) view.findViewById(R.id.distance_unit);
        }
        if (this.y == 283) {
            this.g.setTypeface(Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
        }
        this.w = (SportEquipItemDrawer) view.findViewById(R.id.first_display_item_drawer);
        bVs_(view);
        bVt_(view);
        SupportDataRange m = this.f6432a.m() != null ? this.f6432a.m() : null;
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "initCourseView hasHeartRateDeviceConnect() :", Boolean.valueOf(this.f6432a.ag()));
        this.c = new lbt(this.e, this.y, this.f6432a.ag(), m);
        int[] d = lbj.d(this.y, 0, this.v, g());
        if (this.y == 264 && this.f6432a.ag()) {
            if (!koq.e(d)) {
                d = Arrays.copyOf(d, d.length - 1);
            }
            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "not show pace", Arrays.toString(d));
        }
        this.c.e(this.w, new HashMap(), d);
    }

    public void f() {
        lbt lbtVar = this.c;
        if (lbtVar != null) {
            lbtVar.setIsShowHeartRate(true);
        }
    }

    private void bVs_(View view) {
        if (this.y == 283) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
            this.w.setLayoutParams(layoutParams);
            this.s = (HealthTextView) view.findViewById(R.id.distance_value);
            this.x = (HealthTextView) view.findViewById(R.id.distance_unit);
            this.r = (HealthProgressBar) view.findViewById(R.id.progressbar_target);
            this.z = (HealthTextView) view.findViewById(R.id.target_type);
            this.u = (HealthTextView) view.findViewById(R.id.target_value);
            this.n = (HealthTextView) view.findViewById(R.id.number_target_unit);
            this.z.setVisibility(8);
            int i = this.v;
            if (i == 0) {
                view.findViewById(R.id.target_progress_layout).setVisibility(0);
                this.u.setText(getResources().getString(R.string._2130846245_res_0x7f022225, UnitUtil.a(this.ab)));
                this.h.setVisibility(8);
                this.n.setVisibility(8);
                this.g.setText(UnitUtil.a(0));
                return;
            }
            if (i != 5) {
                if (i != 6 && i != 7) {
                    if (i == 8) {
                        bVr_(view);
                        return;
                    } else if (i != 10 && i != 11) {
                        return;
                    }
                }
                view.findViewById(R.id.target_progress_layout).setVisibility(4);
                this.h.setVisibility(0);
                this.h.setText(this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, 0, "").trim());
                this.g.setText(UnitUtil.e(0.0d, 1, 0));
                return;
            }
            view.findViewById(R.id.target_progress_layout).setVisibility(0);
            Resources resources = this.e.getResources();
            int i2 = this.ab;
            this.u.setText(getResources().getString(R.string._2130846245_res_0x7f022225, resources.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, i2, Integer.valueOf(i2))));
            this.g.setText(UnitUtil.e(0.0d, 1, 0));
        }
    }

    private void bVt_(View view) {
        String string;
        double b;
        if (lbv.j(this.y)) {
            bVu_(view);
            int i = this.v;
            if (i == 0) {
                view.findViewById(R.id.target_progress_layout).setVisibility(0);
                this.s.setText(UnitUtil.a(0));
                this.x.setText(getString(R.string._2130839907_res_0x7f020963));
                a(UnitUtil.e(this.ab / 60.0d, 1, 0), getString(R.string._2130839756_res_0x7f0208cc));
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    view.findViewById(R.id.target_progress_layout).setVisibility(0);
                    this.s.setText(UnitUtil.e(0.0d, 1, 0));
                    this.x.setText(getString(R.string._2130847442_res_0x7f0226d2));
                    a(UnitUtil.e(this.ab, 1, 0), getString(R.string._2130841384_res_0x7f020f28));
                    return;
                }
                LogUtil.a("Track_IDEQ_DataFirstPageFragment", "initTargetLayout, mTargetType is ", Integer.valueOf(i));
                return;
            }
            view.findViewById(R.id.target_progress_layout).setVisibility(0);
            boolean h = UnitUtil.h();
            if (h) {
                string = getString(R.string._2130844081_res_0x7f0219b1);
            } else {
                string = getString(R.string._2130844082_res_0x7f0219b2);
            }
            this.s.setText(UnitUtil.e(0.0d, 1, 2));
            this.x.setText(string);
            if (h) {
                b = jed.c(this.ab);
            } else {
                b = jed.b(this.ab);
            }
            a(UnitUtil.e(b, 1, 2), string);
        }
    }

    private void bVu_(View view) {
        this.s = (HealthTextView) view.findViewById(R.id.distance_value);
        this.x = (HealthTextView) view.findViewById(R.id.distance_unit);
        this.r = (HealthProgressBar) view.findViewById(R.id.progressbar_target);
        view.findViewById(R.id.target_progress).setVisibility(8);
        view.findViewById(R.id.target_detail_layout).setVisibility(0);
        this.u = (HealthTextView) view.findViewById(R.id.target_value_text);
        this.n = (HealthTextView) view.findViewById(R.id.target_unit_text);
        this.z = (HealthTextView) view.findViewById(R.id.target_type_text);
    }

    private void a(String str, String str2) {
        this.u.setText(str);
        this.n.setText(str2);
        this.z.setText(this.y == 264 ? getString(R.string._2130842432_res_0x7f021340) : getString(R.string._2130842434_res_0x7f021342));
    }

    private void bVr_(View view) {
        view.findViewById(R.id.target_progress_layout).setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.t.getLayoutParams();
        layoutParams.height = -2;
        this.t.setLayoutParams(layoutParams);
        this.t.setVisibility(0);
        this.x.setVisibility(8);
        this.s.setTextSize(1, 12.0f);
        this.s.setTextColor(this.e.getColor(R.color._2131298663_res_0x7f090967));
        this.s.setTypeface(Typeface.create(getString(R.string._2130851582_res_0x7f0236fe), 0));
        this.s.setText(UnitUtil.e(1.0d, 1, 0) + "/" + UnitUtil.e(this.f.getIntermittentJumpGroups(), 1, 0));
        if (this.f.getIntermittentJumpMode() == 1) {
            this.h.setVisibility(8);
            this.g.setText(UnitUtil.a(this.f.getIntermittentJumpExerciseTime()));
            this.u.setText(getResources().getString(R.string._2130846245_res_0x7f022225, UnitUtil.a(this.f.getIntermittentJumpExerciseTime())));
        } else {
            this.h.setVisibility(8);
            this.u.setText(getResources().getString(R.string._2130846245_res_0x7f022225, this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, this.f.getIntermittentJumpExerciseNum(), "")));
        }
        this.z.setVisibility(8);
        this.n.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.i = true;
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.indoorequip.ui.DataFirstPageFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_IDEQ_DataFirstPageFragment", "mMusicIcon onClick()", Boolean.valueOf(DataFirstPageFragment.this.isAdded()));
                if (DataFirstPageFragment.this.isAdded()) {
                    if (DataFirstPageFragment.this.a()) {
                        LogUtil.h("Track_IDEQ_DataFirstPageFragment", "onClick() too fast");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    if (DataFirstPageFragment.this.getActivity() != null) {
                        lbv.c(DataFirstPageFragment.this.getActivity().getApplicationContext(), "MusicBtn");
                    }
                    if (lbv.b(DataFirstPageFragment.this.getArguments().getInt("deviceType"))) {
                        if (DataFirstPageFragment.this.l != null) {
                            diy.e(DataFirstPageFragment.this.e, DataFirstPageFragment.this.y);
                            DataFirstPageFragment.this.l.d();
                            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "gotoMusic");
                        }
                    } else if (CommonUtil.bd() && CommonUtil.v(gwh.s)) {
                        Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
                        intent.setFlags(268435456);
                        intent.setPackage(gwh.s);
                        DataFirstPageFragment.this.startActivity(intent);
                        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "startActivity");
                    } else {
                        LogUtil.h("Track_IDEQ_DataFirstPageFragment", "Don't play music.");
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.indoorequip.ui.DataFirstPageFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DataFirstPageFragment.this.getActivity() != null) {
                    lbv.c(DataFirstPageFragment.this.getActivity().getApplicationContext(), "ProjectionBtn");
                }
                DataFirstPageFragment.this.f6432a.af();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.m < 1000) {
            LogUtil.h("Track_IDEQ_DataFirstPageFragment", "onClick ", "click too fast");
            this.m = currentTimeMillis;
            return true;
        }
        this.m = currentTimeMillis;
        return false;
    }

    public void d(Map<Integer, Object> map) {
        if (map == null || !isAdded() || !this.i || this.c == null) {
            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "isAdded() = ", Boolean.valueOf(isAdded()), " mIsInitFinish = ", Boolean.valueOf(this.i));
            return;
        }
        int[] d = lbj.d(this.y, b(map), this.v, g());
        if (this.y == 264 && this.f6432a.ag() && !koq.e(d)) {
            d = Arrays.copyOf(d, d.length - 1);
        }
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", Arrays.toString(d));
        this.c.e(this.w, map, d);
        int intValue = map.get(1) == null ? 0 : ((Integer) map.get(1)).intValue();
        boolean h = UnitUtil.h();
        int i = this.y;
        if (i == 283) {
            a(map);
            return;
        }
        if (lbv.j(i)) {
            b(map, h);
        } else if (g().equals("291")) {
            e(map);
        } else {
            a(intValue, h);
        }
    }

    public void b(int i) {
        HealthProgressBar healthProgressBar = this.r;
        if (healthProgressBar != null) {
            healthProgressBar.setProgress(i);
        }
    }

    private int b(Map<Integer, Object> map) {
        boolean c = c(map);
        if (this.y != 283 || this.v != 8) {
            return 0;
        }
        if (c) {
            return this.f.getIntermittentJumpMode() == 0 ? 5 : 6;
        }
        return 7;
    }

    private String g() {
        IndoorEquipDisplayActivity indoorEquipDisplayActivity = this.f6432a;
        return (indoorEquipDisplayActivity == null || indoorEquipDisplayActivity.l() == null) ? "" : this.f6432a.l().r();
    }

    private void e(Map<Integer, Object> map) {
        this.g.setText(UnitUtil.e(c(map, 38), 1, 0));
        this.h.setText(this.e.getResources().getQuantityString(R.plurals._2130903241_res_0x7f0300c9, c(map, 38)));
    }

    private void a(Map<Integer, Object> map) {
        int i = this.v;
        if (i != 0) {
            if (i != 5 && i != 6 && i != 7) {
                if (i == 8) {
                    f(map);
                    return;
                } else if (i != 10 && i != 11) {
                    LogUtil.h("Track_IDEQ_DataFirstPageFragment", "Target Type is =", Integer.valueOf(i));
                    return;
                }
            }
            this.g.setText(UnitUtil.e(c(map, 40001), 1, 0));
            this.h.setText(this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, c(map, 40001), ""));
            return;
        }
        this.g.setText(UnitUtil.a(c(map, 2)));
    }

    private void b(Map<Integer, Object> map, boolean z) {
        int intValue = map.get(1) == null ? 0 : ((Integer) map.get(1)).intValue();
        int intValue2 = map.get(6) == null ? 0 : ((Integer) map.get(6)).intValue();
        int i = this.v;
        if (i == 0) {
            this.s.setText(UnitUtil.a(c(map, 2)));
            return;
        }
        if (i == 1) {
            a(intValue, z);
        } else if (i == 2) {
            this.s.setText(UnitUtil.e(intValue2, 1, 0));
        } else {
            a(intValue, z);
        }
    }

    private void f(Map<Integer, Object> map) {
        String string;
        String a2;
        String string2;
        boolean c = c(map);
        int c2 = c(map, 40016);
        int c3 = c(map, 40051);
        int c4 = c(map, 40052);
        int c5 = c(map, 40011);
        if (this.f.getIntermittentJumpMode() == 0) {
            if (c) {
                a2 = UnitUtil.e(c2, 1, 0);
            } else {
                a2 = UnitUtil.a(c4);
            }
            String quantityString = this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, this.f.getIntermittentJumpExerciseNum(), Integer.valueOf(this.f.getIntermittentJumpExerciseNum()));
            if (c) {
                string2 = getResources().getString(R.string._2130846245_res_0x7f022225, quantityString);
            } else {
                string2 = getResources().getString(R.string._2130845860_res_0x7f0220a4, UnitUtil.a(this.f.getIntermittentJumpBreakTime()));
            }
            this.g.setText(a2);
            this.u.setText(string2);
        } else if (this.f.getIntermittentJumpMode() == 1) {
            this.g.setText(c ? UnitUtil.a(c3) : UnitUtil.a(c4));
            HealthTextView healthTextView = this.u;
            if (c) {
                string = getResources().getString(R.string._2130846245_res_0x7f022225, UnitUtil.a(this.f.getIntermittentJumpExerciseTime()));
            } else {
                string = getResources().getString(R.string._2130845860_res_0x7f0220a4, UnitUtil.a(this.f.getIntermittentJumpBreakTime()));
            }
            healthTextView.setText(string);
        } else {
            LogUtil.h("Track_IDEQ_DataFirstPageFragment", "other mIntermittentJumpMode is =", Integer.valueOf(this.f.getIntermittentJumpMode()));
        }
        this.s.setText(UnitUtil.e(c5, 1, 0) + "/" + UnitUtil.e(this.f.getIntermittentJumpGroups(), 1, 0));
        this.r.setProgress(b(map, 10003));
    }

    private boolean c(Map<Integer, Object> map) {
        if (this.y != 283 || this.v != 8) {
            return true;
        }
        boolean e = e(map, 40015);
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "mIsIntermittentJumpMotion is =", Boolean.valueOf(e));
        return e;
    }

    private int b(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue();
    }

    private int c(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            return 0;
        }
        return ((Integer) map.get(Integer.valueOf(i))).intValue();
    }

    private boolean e(Map<Integer, Object> map, int i) {
        if (map.get(Integer.valueOf(i)) != null) {
            Object obj = map.get(Integer.valueOf(i));
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue();
            }
        }
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "sport type :", Integer.valueOf(this.y));
        if (this.l != null) {
            b();
        } else if (this.aa != null) {
            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "reacquire mMusicControlLayout id");
            this.l = (MusicControlLayout) this.aa.findViewById(R.id.music_content_layout);
            b();
        }
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "onResume");
    }

    public void b() {
        if (((SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class)).getStatus() == 7) {
            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "refreshMusicInfo while count down, return");
            return;
        }
        if (!SportMusicController.a().d() && IndoorEquipDisplayActivity.a(this.e, this.y)) {
            this.l.e(this.y);
        }
        this.l.c();
        if (lbc.b(this.y)) {
            this.o.setVisibility(8);
            this.j.setVisibility(8);
        } else if (this.l.getTrackSharedPreferenceUtil().f(this.y) == 1 && gwg.a(this.e) && lbv.b(this.y)) {
            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "ctrl music:", Integer.valueOf(this.y));
            this.o.setVisibility(8);
            this.j.setVisibility(0);
        } else {
            LogUtil.a("Track_IDEQ_DataFirstPageFragment", "back music:", Integer.valueOf(this.y));
            this.o.setVisibility(0);
            this.j.setVisibility(8);
        }
    }

    public void a(int i, boolean z) {
        String string;
        if ((isAdded() && this.g == null && this.h == null) || this.g == null) {
            return;
        }
        float f = i / 1000.0f;
        if (z) {
            f *= 0.621371f;
        }
        this.g.setText(new DecimalFormat("0.00").format(f));
        HealthTextView healthTextView = this.h;
        if (z) {
            string = getResources().getQuantityString(R.plurals._2130903253_res_0x7f0300d5, i, "");
        } else {
            string = getString(R.string._2130840225_res_0x7f020aa1);
        }
        healthTextView.setText(string);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "onPause");
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "onCreate");
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "onAttach");
        this.e = context;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        LogUtil.a("Track_IDEQ_DataFirstPageFragment", "onDetach, hashCode = ", Integer.valueOf(hashCode()));
    }

    public void c() {
        if (!isAdded() || this.b == null) {
            return;
        }
        this.q.setVisibility(8);
        this.b.setImageDrawable(lbv.bVR_(this.e, lbj.c(this.y, false)));
    }

    public void e() {
        if (!isAdded() || this.b == null) {
            return;
        }
        this.q.setVisibility(8);
        this.b.setImageDrawable(lbv.bVR_(this.e, lbj.c(this.y, true)));
    }

    public void d() {
        if (!isAdded() || this.b == null) {
            return;
        }
        this.q.setVisibility(0);
        this.b.setImageDrawable(lbv.bVR_(this.e, lbj.a(this.y)));
    }
}

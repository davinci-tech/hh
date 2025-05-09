package com.huawei.health.suggestion.ui.fitness.viewholder;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.amap.api.services.core.AMapException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionModeViewHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.services.codec.TypeToken;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Video;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.ffy;
import defpackage.fnz;
import defpackage.ggg;
import defpackage.koq;
import defpackage.mod;
import defpackage.moj;
import defpackage.nsi;
import defpackage.nsn;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class FitnessActionModeViewHolder implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f3205a;
    private AtomicAction b;
    private LinearLayout c;
    private List<c> d;
    private List<c> e;
    private List<c> f;
    private HealthTextView g;
    private int h;
    private List<c> i;
    private View j;
    private HealthTextView l;
    private int m;
    private HealthTextView n;

    public FitnessActionModeViewHolder(Context context, int i, AtomicAction atomicAction) {
        if (atomicAction == null) {
            LogUtil.h("Suggestion_FitnessActionModeViewHolder", "FitnessActionModeViewHolder atomicAction == null");
            return;
        }
        this.h = i;
        this.b = atomicAction;
        this.f3205a = context == null ? BaseApplication.e() : context;
        this.j = View.inflate(context, R.layout.sug_action_mode_select, null);
        this.d = d(true);
        this.i = d(false);
        this.e = e(true);
        this.f = e(false);
        g();
        j();
    }

    private void j() {
        if (this.b == null) {
            LogUtil.h("Suggestion_FitnessActionModeViewHolder", "initDataView mAtomicAction ==  null");
            return;
        }
        int i = this.h;
        if (i == -1) {
            m();
            return;
        }
        if (i == 1) {
            k();
            i();
        } else if (i == 10) {
            k();
            h();
        } else {
            LogUtil.a("Suggestion_FitnessActionModeViewHolder", "initDataView mTargetType:", Integer.valueOf(i));
        }
    }

    private void h() {
        ActionMode c2 = c(this.b.getId());
        if (c2 == null) {
            LogUtil.a("Suggestion_FitnessActionModeViewHolder", "initCountView actionMode == null");
            a(10);
        } else if (c2.getTargetType() == 10) {
            a(c2.getTargetValue());
        } else {
            a(10);
        }
    }

    private void a(int i) {
        this.m = i;
        String e = UnitUtil.e(i, 1, 0);
        SpannableString spannableString = new SpannableString(ffy.b(R.plurals._2130903362_res_0x7f030142, i, e));
        nsi.cKI_(spannableString, e, R.color._2131299236_res_0x7f090ba4);
        nsi.cKK_(spannableString, e, nsn.a(this.f3205a, r1.getResources().getDimensionPixelSize(R.dimen._2131365072_res_0x7f0a0cd0)));
        nsi.cKL_(spannableString, e, R.string._2130851581_res_0x7f0236fd);
        this.l.setText(spannableString);
        b(i);
    }

    private void b(int i) {
        long d = d(this.b);
        float extendPropertyFloat = this.b.getExtendPropertyFloat("calorie");
        ReleaseLogUtil.e("Suggestion_FitnessActionModeViewHolder", "calorieByCountView duration:", Long.valueOf(d), "calorie:", Float.valueOf(extendPropertyFloat));
        float c2 = mod.c(d, i, extendPropertyFloat, fnz.e());
        this.n.setText(aFt_(ffy.d(this.f3205a, R.string._2130848832_res_0x7f022c40, ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) c2, UnitUtil.e(c2, 1, 0))), R.style.health_text_chart_extreme_value_small, R.style.health_text_chart_cursor_unit));
    }

    public static long d(AtomicAction atomicAction) {
        if (atomicAction == null) {
            LogUtil.h("Suggestion_FitnessActionModeViewHolder", "getDuration atomicAction null");
            return 0L;
        }
        long extendPropertyLong = atomicAction.getExtendPropertyLong("duration");
        List<Video> extendPropertyList = atomicAction.getExtendPropertyList("actionVideo", Video[].class);
        if (koq.b(extendPropertyList)) {
            LogUtil.h("Suggestion_FitnessActionModeViewHolder", "getDuration videos isEmpty");
            return extendPropertyLong;
        }
        int a2 = ggg.a();
        ReleaseLogUtil.e("Suggestion_FitnessActionModeViewHolder", "getDuration duration:", Long.valueOf(extendPropertyLong), "coachGender:", Integer.valueOf(a2));
        for (Video video : extendPropertyList) {
            if (video != null && a2 == video.getGender()) {
                int during = video.getDuring();
                if (during != 0) {
                    extendPropertyLong = during;
                }
                int actionCount = video.getActionCount();
                ReleaseLogUtil.e("Suggestion_FitnessActionModeViewHolder", "getDuration isMatch true actionCount", Integer.valueOf(actionCount), "coachGender:", Integer.valueOf(video.getGender()), "videoDuring:", Integer.valueOf(during));
                if (actionCount != 0) {
                    return extendPropertyLong / actionCount;
                }
            }
        }
        return extendPropertyLong;
    }

    private void i() {
        ActionMode c2 = c(this.b.getId());
        if (c2 == null) {
            LogUtil.a("Suggestion_FitnessActionModeViewHolder", "initTimeView actionMode == null");
            e(60);
        } else if (c2.getTargetType() == 1) {
            e(c2.getTargetValue());
        } else {
            e(60);
        }
    }

    private void e(int i) {
        this.m = i;
        this.l.setText(aFt_(ffy.a(i), R.style.health_ai_action_time_value, R.style.health_ai_action_time_unit));
        d(i);
    }

    private void d(int i) {
        float extendPropertyFloat = this.b.getExtendPropertyFloat("calorie");
        ReleaseLogUtil.e("Suggestion_FitnessActionModeViewHolder", "calorieByCountView calorie:", Float.valueOf(extendPropertyFloat));
        float a2 = mod.a(extendPropertyFloat, i, fnz.e());
        this.n.setText(aFt_(ffy.d(this.f3205a, R.string._2130848832_res_0x7f022c40, ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) a2, UnitUtil.e(a2, 1, 0))), R.style.health_text_chart_extreme_value_small, R.style.health_text_chart_cursor_unit));
    }

    private SpannableString aFt_(String str, int i, int i2) {
        return UnitUtil.bCR_(this.f3205a, "\\d", str, i, i2);
    }

    private void m() {
        this.c.setVisibility(8);
        this.n.setVisibility(8);
        this.g.setVisibility(0);
    }

    private void k() {
        this.c.setVisibility(0);
        this.n.setVisibility(0);
        this.g.setVisibility(8);
    }

    private void g() {
        this.l = (HealthTextView) this.j.findViewById(R.id.action_target_value);
        ((HealthImageView) this.j.findViewById(R.id.value_reduce_img)).setOnClickListener(this);
        ((HealthImageView) this.j.findViewById(R.id.value_add_img)).setOnClickListener(this);
        this.n = (HealthTextView) this.j.findViewById(R.id.action_total_calorie);
        this.c = (LinearLayout) this.j.findViewById(R.id.action_mode_layout);
        this.g = (HealthTextView) this.j.findViewById(R.id.free_target);
    }

    public View aFu_() {
        return this.j;
    }

    public int a() {
        return this.m;
    }

    public int c() {
        return this.h;
    }

    public String d() {
        HealthTextView healthTextView;
        return (this.h == -1 || (healthTextView = this.l) == null || healthTextView.getText() == null) ? "" : this.l.getText().toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.value_reduce_img) {
            l();
        } else if (id == R.id.value_add_img) {
            e();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        if (this.h == 10) {
            a("count", 1000, this.d);
            a(this.m);
        } else {
            a("time", 3600, this.e);
            e(this.m);
        }
    }

    private void a(String str, int i, List<c> list) {
        if (this.m >= i) {
            this.m = i;
            LogUtil.a("Suggestion_FitnessActionModeViewHolder", "addTargetValue mTargetValue >= MAX_VALUE tag:", str);
            return;
        }
        for (c cVar : list) {
            if (this.m >= cVar.d()) {
                this.m += cVar.c();
                return;
            }
        }
    }

    private List<c> d(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(1000, 100));
        arrayList.add(new c(500, 100));
        arrayList.add(new c(100, 50));
        arrayList.add(new c(10, 10));
        arrayList.add(new c(5, 5));
        if (z) {
            return arrayList;
        }
        Collections.sort(arrayList, new Comparator() { // from class: frz
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return FitnessActionModeViewHolder.e((FitnessActionModeViewHolder.c) obj, (FitnessActionModeViewHolder.c) obj2);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ int e(c cVar, c cVar2) {
        return cVar.d() - cVar2.d();
    }

    private List<c> e(boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(3600, 600));
        arrayList.add(new c(AMapException.CODE_AMAP_CLIENT_ERRORCODE_MISSSING, 600));
        arrayList.add(new c(600, 300));
        arrayList.add(new c(300, 60));
        arrayList.add(new c(30, 30));
        if (z) {
            return arrayList;
        }
        Collections.sort(arrayList, new Comparator() { // from class: fsd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return FitnessActionModeViewHolder.a((FitnessActionModeViewHolder.c) obj, (FitnessActionModeViewHolder.c) obj2);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ int a(c cVar, c cVar2) {
        return cVar.d() - cVar2.d();
    }

    private void l() {
        if (this.h == 10) {
            d("count", 5, this.i);
            a(this.m);
        } else {
            d("time", 30, this.f);
            e(this.m);
        }
    }

    private void d(String str, int i, List<c> list) {
        if (this.m <= i) {
            this.m = i;
            LogUtil.a("Suggestion_FitnessActionModeViewHolder", "reduceCountValue mTargetValue <= minTargetValue tag:", str);
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            c cVar = list.get(i2);
            if (cVar != null && this.m <= cVar.d()) {
                int i3 = i2 - 1;
                if (koq.d(list, i3)) {
                    this.m -= list.get(i3).c();
                    return;
                }
            }
        }
    }

    public static void a(String str, int i, int i2) {
        Map f = f();
        if (f == null) {
            f = new HashMap(10);
        }
        ActionMode actionMode = new ActionMode();
        actionMode.setTargetType(i);
        actionMode.setTargetValue(i2);
        f.put(str, actionMode);
        SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "ACTION_ MODE_SP", moj.e(f));
    }

    private static Map<String, ActionMode> f() {
        String e = SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "ACTION_ MODE_SP", "");
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.d("Suggestion_FitnessActionModeViewHolder", "getSelectedActionModSp actionMode null");
            return null;
        }
        return (Map) moj.b(e, new TypeToken<Map<String, ActionMode>>() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionModeViewHolder.5
        }.getType());
    }

    public static ActionMode c(String str) {
        Map<String, ActionMode> f = f();
        if (f == null || str == null) {
            ReleaseLogUtil.d("Suggestion_FitnessActionModeViewHolder", "getLastActionMode modeMap == null || actionId == null");
            return null;
        }
        for (Map.Entry<String, ActionMode> entry : f.entrySet()) {
            if (str.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static class ActionMode implements Parcelable {
        public static final Parcelable.Creator<ActionMode> CREATOR = new Parcelable.Creator<ActionMode>() { // from class: com.huawei.health.suggestion.ui.fitness.viewholder.FitnessActionModeViewHolder.ActionMode.3
            @Override // android.os.Parcelable.Creator
            /* renamed from: aFv_, reason: merged with bridge method [inline-methods] */
            public ActionMode createFromParcel(Parcel parcel) {
                return new ActionMode(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public ActionMode[] newArray(int i) {
                return new ActionMode[i];
            }
        };
        private int mTargetType;
        private int mTargetValue;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ActionMode() {
        }

        public ActionMode(Parcel parcel) {
            this.mTargetType = parcel.readInt();
            this.mTargetValue = parcel.readInt();
        }

        public int getTargetType() {
            return this.mTargetType;
        }

        public void setTargetType(int i) {
            this.mTargetType = i;
        }

        public int getTargetValue() {
            return this.mTargetValue;
        }

        public void setTargetValue(int i) {
            this.mTargetValue = i;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mTargetType);
            parcel.writeInt(this.mTargetValue);
        }
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final int f3206a;
        private final int d;

        public c(int i, int i2) {
            this.f3206a = i;
            this.d = i2;
        }

        public int d() {
            return this.f3206a;
        }

        public int c() {
            return this.d;
        }
    }
}

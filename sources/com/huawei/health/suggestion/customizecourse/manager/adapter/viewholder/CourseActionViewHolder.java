package com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.ItemEventListener;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.OnStartDragListener;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fjd;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseActionViewHolder extends CustomCourseDragViewHolder<fjd> implements View.OnClickListener, View.OnLongClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final HealthTextView f3037a;
    private final HealthTextView aa;
    private final HealthTextView ab;
    private final HealthTextView ac;
    private final HealthTextView ad;
    private final HealthTextView af;
    private final HealthTextView ai;
    private final HealthTextView b;
    private fjd c;
    private final HealthCardView d;
    private final Context e;
    private final Space f;
    private final ImageView g;
    private final View h;
    private final OnStartDragListener i;
    private final ImageView j;
    private final View k;
    private final HealthTextView l;
    private final HealthTextView m;
    private final LinearLayout n;
    private final LinearLayout o;
    private final ItemEventListener p;
    private final HealthTextView q;
    private final HealthTextView r;
    private final HealthTextView s;
    private final LinearLayout t;
    private final ImageView u;
    private final Space v;
    private final HealthTextView w;
    private final LinearLayout x;
    private final LinearLayout y;
    private final HealthTextView z;

    public CourseActionViewHolder(View view, Context context, OnStartDragListener onStartDragListener, ItemEventListener itemEventListener) {
        super(view);
        this.e = context;
        this.i = onStartDragListener;
        this.p = itemEventListener;
        this.t = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_content);
        this.d = (HealthCardView) view.findViewById(R.id.sug_sug_custom_course_action_card);
        this.h = view.findViewById(R.id.sug_custom_course_item_space_left_multi);
        this.k = view.findViewById(R.id.sug_custom_course_item_space_right_multi);
        this.f = (Space) view.findViewById(R.id.sug_custom_course_item_icon_space);
        this.j = (ImageView) view.findViewById(R.id.sug_custom_course_item_icon_colum);
        this.b = (HealthTextView) view.findViewById(R.id.sug_custom_course_item_action_name);
        this.g = (ImageView) view.findViewById(R.id.sug_custom_course_item_icon_edit_action);
        ImageView imageView = (ImageView) view.findViewById(R.id.sug_custom_course_item_icon_open_close);
        this.u = imageView;
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_target_hint);
        this.x = linearLayout;
        this.w = (HealthTextView) view.findViewById(R.id.sug_course_item_target_name_hint);
        this.ac = (HealthTextView) view.findViewById(R.id.sug_course_item_target_value_hint_first);
        this.z = (HealthTextView) view.findViewById(R.id.sug_course_item_target_value_hint_second);
        this.aa = (HealthTextView) view.findViewById(R.id.sug_course_item_target_value_hint_third);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_intensity_hint);
        this.o = linearLayout2;
        this.m = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_name_hint);
        this.r = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_value_hint);
        this.y = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_target);
        this.ab = (HealthTextView) view.findViewById(R.id.sug_custom_course_item_target_name);
        this.ad = (HealthTextView) view.findViewById(R.id.sug_course_item_target_value_first);
        this.af = (HealthTextView) view.findViewById(R.id.sug_course_item_target_value_second);
        this.ai = (HealthTextView) view.findViewById(R.id.sug_course_item_target_value_third);
        this.n = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_intensity);
        this.l = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_name);
        this.s = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_value_first);
        this.q = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_value_last);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sug_custom_course_item_del_action);
        this.f3037a = healthTextView;
        this.v = (Space) view.findViewById(R.id.sug_custom_course_item_space_card_middle);
        view.findViewById(R.id.sug_course_item_open_close).setOnClickListener(this);
        view.findViewById(R.id.sug_course_item_target_value_parent).setOnClickListener(this);
        view.findViewById(R.id.sug_course_item_intensity_value_parent).setOnClickListener(this);
        view.findViewById(R.id.sug_custom_course_item_content).setOnLongClickListener(this);
        view.findViewById(R.id.sug_custom_course_item_icon_drag).setOnLongClickListener(this);
        view.findViewById(R.id.sug_course_item_open_close).setOnLongClickListener(this);
        linearLayout.setOnLongClickListener(this);
        linearLayout2.setOnLongClickListener(this);
        view.findViewById(R.id.sug_custom_course_item_action).setOnClickListener(this);
        view.findViewById(R.id.sug_custom_course_item_target_edit).setOnClickListener(this);
        view.findViewById(R.id.sug_custom_course_item_intensity_edit).setOnClickListener(this);
        imageView.setOnClickListener(this);
        healthTextView.setOnClickListener(this);
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void init(fjd fjdVar, int i) {
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "init, itemConfig == null");
            return;
        }
        setIsCanDrag(true);
        this.c = fjdVar;
        f();
        this.b.setText(fjdVar.j());
        this.j.setBackgroundResource(fjdVar.c());
        e(fjdVar);
        a(fjdVar);
    }

    public fjd b() {
        return this.c;
    }

    private void a(fjd fjdVar) {
        String string;
        if (!TextUtils.isEmpty(fjdVar.b())) {
            this.l.setText(fjdVar.b());
            this.m.setText(fjdVar.b());
            this.q.setVisibility(8);
            this.s.setVisibility(0);
            List<String> a2 = fjdVar.a();
            int size = a2.size();
            if (size >= 2) {
                string = this.e.getResources().getString(R.string._2130843702_res_0x7f021836, a2.get(0), a2.get(1));
            } else if (size >= 1) {
                string = a2.get(0);
            } else {
                string = this.e.getResources().getString(R.string._2130848598_res_0x7f022b56);
            }
            this.s.setText(string);
            this.r.setText(string);
            return;
        }
        this.n.setVisibility(8);
    }

    private void e(fjd fjdVar) {
        String str;
        if (TextUtils.isEmpty(fjdVar.o())) {
            this.y.setVisibility(8);
            return;
        }
        this.ab.setText(fjdVar.o());
        this.w.setText(fjdVar.o());
        this.ac.setVisibility(0);
        this.z.setVisibility(8);
        this.aa.setVisibility(8);
        this.ad.setVisibility(0);
        this.af.setVisibility(8);
        this.ai.setVisibility(8);
        List<String> k = fjdVar.k();
        int size = k.size();
        if (size >= 3) {
            this.aa.setVisibility(0);
            this.ai.setVisibility(0);
            this.z.setVisibility(0);
            this.af.setVisibility(0);
            this.aa.setText(k.get(2));
            this.ai.setText(k.get(2));
            this.z.setText(k.get(1));
            this.af.setText(k.get(1));
            this.ac.setText(k.get(0));
            this.ad.setText(k.get(0));
            return;
        }
        if (size >= 2) {
            str = this.e.getResources().getString(R.string._2130843702_res_0x7f021836, k.get(0), k.get(1));
        } else {
            str = k.get(0);
        }
        this.ac.setText(str);
        this.ad.setText(str);
    }

    private void f() {
        this.v.setVisibility(0);
        if (this.c.i() == 0) {
            this.h.setVisibility(8);
            this.k.setVisibility(8);
            this.j.setVisibility(0);
            this.f.setVisibility(0);
            this.t.setBackgroundColor(0);
            this.d.setCardBackgroundColor(this.e.getResources().getColor(R.color._2131299166_res_0x7f090b5e));
            this.d.setRadius(this.e.getResources().getDimension(R.dimen._2131362359_res_0x7f0a0237));
        } else {
            this.h.setVisibility(0);
            this.k.setVisibility(0);
            this.j.setVisibility(8);
            this.f.setVisibility(8);
            this.t.setBackgroundColor(this.e.getResources().getColor(R.color._2131299166_res_0x7f090b5e));
            this.d.setCardBackgroundColor(this.e.getResources().getColor(R.color._2131296691_res_0x7f0901b3));
            this.d.setRadius(this.e.getResources().getDimension(R.dimen._2131362361_res_0x7f0a0239));
            c();
        }
        if (this.c.l()) {
            e();
        } else {
            d();
        }
        if (LanguageUtil.h(BaseApplication.e())) {
            c(this.s);
            c(this.l);
            c(this.ai);
            c(this.af);
            c(this.ad);
            c(this.ab);
            c(this.r);
            c(this.m);
            c(this.aa);
            c(this.z);
            c(this.ac);
            c(this.w);
            c(this.b);
            return;
        }
        g();
    }

    private void g() {
        this.b.setSplittable(true);
        this.w.setSplittable(true);
        this.ac.setSplittable(true);
        this.ab.setSplittable(true);
        this.ad.setSplittable(true);
        this.l.setSplittable(true);
        this.m.setSplittable(true);
        this.s.setSplittable(true);
        this.r.setSplittable(true);
    }

    private void c(HealthTextView healthTextView) {
        healthTextView.setAutoTextInfo(9, 1, 2);
    }

    public void a() {
        fjd fjdVar = this.c;
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "updateSpaceState, mItemConfig == null");
            return;
        }
        if (fjdVar.i() == 0) {
            this.j.setVisibility(0);
            this.f.setVisibility(0);
            this.h.setVisibility(8);
            this.k.setVisibility(8);
            this.t.setBackgroundColor(0);
            this.d.setCardBackgroundColor(this.e.getResources().getColor(R.color._2131299166_res_0x7f090b5e));
            return;
        }
        this.j.setVisibility(8);
        this.f.setVisibility(8);
        this.h.setVisibility(0);
        this.k.setVisibility(0);
        this.t.setBackgroundColor(this.e.getResources().getColor(R.color._2131299166_res_0x7f090b5e));
        this.d.setCardBackgroundColor(this.e.getResources().getColor(R.color._2131296691_res_0x7f0901b3));
    }

    public void d() {
        this.g.setVisibility(8);
        this.x.setVisibility(0);
        this.o.setVisibility(0);
        this.y.setVisibility(8);
        this.n.setVisibility(8);
        this.f3037a.setVisibility(8);
        this.u.setImageResource(R.drawable.ic_health_list_drop_down_arrow_nor);
    }

    public void e() {
        this.g.setVisibility(0);
        this.x.setVisibility(8);
        this.o.setVisibility(8);
        this.y.setVisibility(0);
        fjd fjdVar = this.c;
        if (fjdVar == null || TextUtils.isEmpty(fjdVar.b())) {
            this.n.setVisibility(8);
        } else {
            this.n.setVisibility(0);
        }
        this.f3037a.setVisibility(0);
        this.u.setImageResource(R.drawable.ic_health_list_drop_down_arrow_sel);
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    public void setDragState(boolean z) {
        fjd fjdVar = this.c;
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "setDragState, mItemConfig == null");
            return;
        }
        if (z) {
            if (fjdVar.i() != 0) {
                this.t.setBackgroundColor(0);
                return;
            }
            return;
        }
        if (fjdVar.n()) {
            this.c.d(true);
            this.c.b(false);
            e();
        }
        if (this.c.i() != 0) {
            this.t.setBackgroundColor(this.e.getResources().getColor(R.color._2131299166_res_0x7f090b5e));
        }
    }

    private void c() {
        if (this.c.i() == 2) {
            this.v.setVisibility(8);
        } else {
            this.v.setVisibility(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ItemEventListener itemEventListener;
        if (view == null) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "onClick, view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        int adapterPosition = getAdapterPosition();
        if (adapterPosition < 0) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "onClick, position < 0");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (id == R.id.sug_custom_course_item_icon_open_close || id == R.id.sug_course_item_open_close) {
            ItemEventListener itemEventListener2 = this.p;
            if (itemEventListener2 != null) {
                itemEventListener2.onComItemClick(adapterPosition, 0);
            }
        } else if (id == R.id.sug_custom_course_item_del_action) {
            ItemEventListener itemEventListener3 = this.p;
            if (itemEventListener3 != null) {
                itemEventListener3.onComItemClick(adapterPosition, 1);
            }
        } else if (id == R.id.sug_custom_course_item_action) {
            fjd fjdVar = this.c;
            if (fjdVar != null && fjdVar.l() && (itemEventListener = this.p) != null) {
                itemEventListener.onComItemClick(adapterPosition, 4);
            }
        } else {
            c(id, adapterPosition);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(int i, int i2) {
        ItemEventListener itemEventListener = this.p;
        if (itemEventListener == null) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "handleClick mItemEventListener is null.");
            return;
        }
        if (i == R.id.sug_custom_course_item_target_edit) {
            itemEventListener.onComItemClick(i2, 5);
            return;
        }
        if (i == R.id.sug_course_item_target_value_parent) {
            itemEventListener.onComItemClick(i2, 6);
            return;
        }
        if (i == R.id.sug_custom_course_item_intensity_edit) {
            itemEventListener.onComItemClick(i2, 7);
        } else if (i == R.id.sug_course_item_intensity_value_parent) {
            itemEventListener.onComItemClick(i2, 8);
        } else {
            LogUtil.h("Suggestion_CourseActionViewHolder", "onClick, id = ", Integer.valueOf(i));
        }
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        fjd fjdVar;
        if (view == null) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "onLongClick, view == null");
            return false;
        }
        if (view.getId() != R.id.sug_custom_course_item_content && view.getId() != R.id.sug_custom_course_item_icon_drag && view.getId() != R.id.sug_custom_course_item_target_hint && view.getId() != R.id.sug_custom_course_item_intensity_hint && view.getId() != R.id.sug_course_item_open_close) {
            return false;
        }
        int adapterPosition = getAdapterPosition();
        if (adapterPosition < 0) {
            LogUtil.h("Suggestion_CourseActionViewHolder", "onLongClick, position < 0");
            return false;
        }
        ItemEventListener itemEventListener = this.p;
        if (itemEventListener != null) {
            itemEventListener.onComItemClick(adapterPosition, 10);
        }
        if (this.i != null && (fjdVar = this.c) != null) {
            if (fjdVar.l()) {
                this.c.b(true);
                this.c.d(false);
                d();
            }
            this.i.onStartDrag(this);
        }
        return true;
    }
}

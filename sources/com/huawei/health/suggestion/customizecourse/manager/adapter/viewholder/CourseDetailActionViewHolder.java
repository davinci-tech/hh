package com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fjd;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseDetailActionViewHolder extends CustomCourseDragViewHolder<fjd> {

    /* renamed from: a, reason: collision with root package name */
    private final HealthTextView f3040a;
    private final HealthTextView b;
    private final LinearLayout c;
    private final HealthTextView d;
    private final ImageView e;
    private View f;
    private final HealthTextView g;
    private final Space h;
    private final HealthTextView i;
    private final LinearLayout j;
    private final HealthTextView l;
    private final HealthTextView n;
    private final HealthTextView o;

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    public void setDragState(boolean z) {
    }

    public CourseDetailActionViewHolder(View view, int i) {
        super(view);
        this.e = (ImageView) view.findViewById(R.id.sug_custom_course_item_icon_column);
        this.b = (HealthTextView) view.findViewById(R.id.sug_custom_course_item_action_name);
        this.j = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_target);
        this.g = (HealthTextView) view.findViewById(R.id.sug_custom_course_item_target_name);
        this.n = (HealthTextView) view.findViewById(R.id.sug_custom_item_target_value_first);
        this.o = (HealthTextView) view.findViewById(R.id.sug_custom_item_target_value_middle);
        this.l = (HealthTextView) view.findViewById(R.id.sug_custom_item_target_value_last);
        this.c = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_intensity);
        this.f3040a = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_name);
        this.d = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_value_first);
        this.i = (HealthTextView) view.findViewById(R.id.sug_course_item_intensity_value_last);
        this.h = (Space) view.findViewById(R.id.sug_custom_course_card_padding_bottom);
        if (i != 0) {
            this.f = view.findViewById(R.id.sug_custom_course_detail_item_line);
        }
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void init(fjd fjdVar, int i) {
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CourseDetailActionViewHolder", "init, actionItemConfig == null");
            return;
        }
        this.b.setText(fjdVar.j());
        this.e.setBackgroundResource(fjdVar.c());
        e(fjdVar.i());
        c(fjdVar);
        a(fjdVar);
        if (LanguageUtil.h(BaseApplication.e())) {
            b(this.n);
            b(this.o);
            b(this.l);
            b(this.f3040a);
            b(this.d);
            b(this.i);
        }
    }

    private void b(HealthTextView healthTextView) {
        healthTextView.setAutoTextInfo(9, 1, 2);
    }

    private void a(fjd fjdVar) {
        String string;
        String b = fjdVar.b();
        String string2 = BaseApplication.e().getResources().getString(R.string._2130848599_res_0x7f022b57);
        if (!TextUtils.isEmpty(b) && !b.equals(string2)) {
            this.f3040a.setText(fjdVar.b());
            this.d.setVisibility(0);
            this.i.setVisibility(8);
            List<String> a2 = fjdVar.a();
            int size = a2.size();
            if (size >= 2) {
                string = BaseApplication.e().getResources().getString(R.string._2130843702_res_0x7f021836, a2.get(0), a2.get(1));
            } else if (size >= 1) {
                string = a2.get(0);
            } else {
                string = BaseApplication.e().getResources().getString(R.string._2130848598_res_0x7f022b56);
            }
            this.d.setText(string);
            return;
        }
        this.c.setVisibility(8);
    }

    private void c(fjd fjdVar) {
        String str;
        if (TextUtils.isEmpty(fjdVar.o())) {
            this.j.setVisibility(8);
            return;
        }
        this.g.setText(fjdVar.o());
        this.n.setVisibility(0);
        this.o.setVisibility(8);
        this.l.setVisibility(8);
        List<String> k = fjdVar.k();
        int size = k.size();
        if (size >= 3) {
            this.l.setVisibility(0);
            this.o.setVisibility(0);
            this.l.setText(k.get(2));
            this.o.setText(k.get(1));
            this.n.setText(k.get(0));
            return;
        }
        if (size >= 2) {
            str = BaseApplication.e().getResources().getString(R.string._2130843702_res_0x7f021836, k.get(0), k.get(1));
        } else {
            str = k.get(0);
        }
        this.n.setText(str);
    }

    private void e(int i) {
        if (i == 0) {
            this.e.setVisibility(0);
            this.h.setVisibility(0);
            return;
        }
        LogUtil.a("Suggestion_CourseDetailActionViewHolder", "init, in multi");
        this.e.setVisibility(4);
        if (i == 1) {
            this.f.setVisibility(0);
            this.h.setVisibility(0);
        } else {
            this.f.setVisibility(8);
            this.h.setVisibility(8);
        }
    }
}

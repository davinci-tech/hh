package com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.ItemEventListener;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.OnStartDragListener;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fjd;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseMultiViewHolder extends CustomCourseDragViewHolder<fjd> implements View.OnClickListener, View.OnLongClickListener {

    /* renamed from: a, reason: collision with root package name */
    private fjd f3041a;
    private OnMultiCloseListener b;
    private Context c;
    private ImageView d;
    private OnStartDragListener e;
    private List<fjd> f;
    private ItemEventListener g;
    private TextView h;
    private LinearLayout i;
    private ImageView j;
    private ImageView n;
    private int o;

    public interface OnMultiCloseListener {
        void onCloseClick(CourseMultiViewHolder courseMultiViewHolder);
    }

    public CourseMultiViewHolder(View view, Context context) {
        super(view);
        this.c = context;
        this.o = 1;
        ayd_(view);
    }

    public CourseMultiViewHolder(View view, Context context, OnStartDragListener onStartDragListener, ItemEventListener itemEventListener, OnMultiCloseListener onMultiCloseListener) {
        super(view);
        this.c = context;
        this.o = 0;
        this.e = onStartDragListener;
        this.g = itemEventListener;
        this.b = onMultiCloseListener;
        ayd_(view);
    }

    private void ayd_(View view) {
        this.j = (ImageView) view.findViewById(R.id.sug_custom_course_item_icon_drag);
        this.h = (TextView) view.findViewById(R.id.sug_custom_course_item_repeat_times);
        this.n = (ImageView) view.findViewById(R.id.sug_custom_course_item_icon_edit_action);
        this.d = (ImageView) view.findViewById(R.id.sug_custom_course_item_del_multi);
        this.i = (LinearLayout) view.findViewById(R.id.sug_custom_course_item_multi_content);
        this.j.setOnLongClickListener(this);
        this.i.setOnLongClickListener(this);
        view.findViewById(R.id.sug_custom_course_repeat_times_edit).setOnClickListener(this);
        this.d.setOnClickListener(this);
    }

    public List<fjd> b() {
        return this.f;
    }

    public void a(List<fjd> list) {
        this.f = list;
    }

    public fjd d() {
        return this.f3041a;
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void init(fjd fjdVar, int i) {
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CourseMultiViewHolder", "init, actionItemConfig == null");
            return;
        }
        this.f3041a = fjdVar;
        LogUtil.a("Suggestion_CourseMultiViewHolder", "init, mState = ", Integer.valueOf(this.o));
        if (this.o == 1) {
            setIsCanDrag(false);
            this.j.setVisibility(8);
            this.n.setVisibility(8);
            this.d.setVisibility(8);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.setMarginStart((int) this.c.getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e));
            layoutParams.setMarginEnd((int) this.c.getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d));
            this.i.setLayoutParams(layoutParams);
            this.i.setPadding((int) this.c.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8), (int) this.c.getResources().getDimension(R.dimen._2131361970_res_0x7f0a00b2), (int) this.c.getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8), 0);
        } else {
            setIsCanDrag(true);
        }
        this.h.setText(this.c.getResources().getQuantityString(R.plurals._2130903480_res_0x7f0301b8, this.f3041a.g(), Integer.valueOf(this.f3041a.g())));
    }

    public void a(boolean z) {
        if (z) {
            fjd fjdVar = this.f3041a;
            if (fjdVar == null || TextUtils.isEmpty(fjdVar.j())) {
                return;
            }
            this.h.setText(this.f3041a.j());
            return;
        }
        fjd fjdVar2 = this.f3041a;
        int g = (fjdVar2 == null || fjdVar2.g() <= 1) ? 2 : this.f3041a.g();
        this.h.setText(this.c.getResources().getQuantityString(R.plurals._2130903480_res_0x7f0301b8, g, Integer.valueOf(g)));
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    public void setDragState(boolean z) {
        if (z) {
            this.i.setBackgroundResource(R.drawable._2131431642_res_0x7f0b10da);
        } else {
            this.i.setBackgroundResource(R.drawable._2131431635_res_0x7f0b10d3);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.o == 1) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        int adapterPosition = getAdapterPosition();
        if (adapterPosition < 0) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (id == R.id.sug_custom_course_repeat_times_edit) {
            ItemEventListener itemEventListener = this.g;
            if (itemEventListener != null) {
                itemEventListener.onComItemClick(adapterPosition, 3);
            }
        } else if (id == R.id.sug_custom_course_item_del_multi) {
            ItemEventListener itemEventListener2 = this.g;
            if (itemEventListener2 != null) {
                itemEventListener2.onComItemClick(adapterPosition, 2);
            }
        } else {
            LogUtil.h("Suggestion_CourseMultiViewHolder", "onClick, id = ", Integer.valueOf(id));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (this.o == 1) {
            return false;
        }
        int id = view.getId();
        if (id != R.id.sug_custom_course_item_icon_drag && id != R.id.sug_custom_course_item_multi_content) {
            return false;
        }
        OnMultiCloseListener onMultiCloseListener = this.b;
        if (onMultiCloseListener != null && this.f3041a != null && this.e != null) {
            onMultiCloseListener.onCloseClick(this);
            a(true);
            this.e.onStartDrag(this);
        }
        return true;
    }
}

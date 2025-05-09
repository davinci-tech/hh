package com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.cbd;
import defpackage.fjd;
import defpackage.koq;
import java.util.List;

/* loaded from: classes4.dex */
public class CourseCourseHeadViewHolder extends CustomCourseDragViewHolder<fjd> {
    private final HealthEditText b;
    private final HealthEditText d;
    private fjd e;

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    public void setDragState(boolean z) {
    }

    public CourseCourseHeadViewHolder(View view) {
        super(view);
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.sug_text_action);
        healthSubHeader.setSubHeaderBackgroundColor(healthSubHeader.getContext().getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        HealthEditText healthEditText = (HealthEditText) view.findViewById(R.id.edit_custom_name);
        this.b = healthEditText;
        HealthEditText healthEditText2 = (HealthEditText) view.findViewById(R.id.sug_custom_course_description);
        this.d = healthEditText2;
        healthEditText.setFilters(new InputFilter[]{new a(79)});
        healthEditText2.setFilters(new InputFilter[]{new a(200)});
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void init(fjd fjdVar, int i) {
        if (fjdVar == null) {
            LogUtil.h("Suggestion_CourseCourseHeadViewHolder", "init, actionItemConfig == null");
            return;
        }
        this.e = fjdVar;
        d(this.b);
        d(this.d);
        this.b.setText(this.e.j());
        this.d.setText(this.e.o());
    }

    private void d(final HealthEditText healthEditText) {
        healthEditText.addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CourseCourseHeadViewHolder.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                HealthEditText healthEditText2 = healthEditText;
                if (healthEditText2 == null) {
                    return;
                }
                if (healthEditText2.equals(CourseCourseHeadViewHolder.this.b)) {
                    CourseCourseHeadViewHolder.this.e.e(editable.toString());
                } else {
                    CourseCourseHeadViewHolder.this.e.b(editable.toString());
                }
            }
        });
    }

    static class a implements InputFilter {

        /* renamed from: a, reason: collision with root package name */
        private final int f3039a;

        public a(int i) {
            this.f3039a = i;
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            int d;
            return (!TextUtils.isEmpty(charSequence) && (d = d(spanned.toString())) < this.f3039a && d + d(charSequence.toString()) <= this.f3039a) ? charSequence : "";
        }

        private int d(String str) {
            List<Integer> e = cbd.e(str);
            if (koq.b(e)) {
                return 0;
            }
            return e.size();
        }
    }
}

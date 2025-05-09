package com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder;

import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fjd;

/* loaded from: classes4.dex */
public class CourseMultiBottomViewHolder extends CustomCourseDragViewHolder<fjd> {
    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    public void setDragState(boolean z) {
    }

    public CourseMultiBottomViewHolder(View view) {
        super(view);
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.viewholder.CustomCourseDragViewHolder
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void init(fjd fjdVar, int i) {
        LogUtil.a("CourseMultiBottomViewHolder", "init, position=", Integer.valueOf(i));
    }
}

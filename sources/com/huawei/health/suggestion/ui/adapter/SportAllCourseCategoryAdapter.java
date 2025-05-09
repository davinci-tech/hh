package com.huawei.health.suggestion.ui.adapter;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;

/* loaded from: classes8.dex */
public class SportAllCourseCategoryAdapter extends BaseRecyclerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private int f3069a;
    private int b;
    private int c;

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void convert(com.huawei.ui.commonui.adapter.RecyclerHolder r3, int r4, java.lang.Object r5) {
        /*
            r2 = this;
            boolean r0 = r5 instanceof com.huawei.pluginfitnessadvice.ClassifyInfo
            if (r0 == 0) goto L16
            r0 = r5
            com.huawei.pluginfitnessadvice.ClassifyInfo r0 = (com.huawei.pluginfitnessadvice.ClassifyInfo) r0
            com.huawei.pluginfitnessadvice.Classify r1 = r0.getPrimaryClassify()
            if (r1 == 0) goto L16
            com.huawei.pluginfitnessadvice.Classify r0 = r0.getPrimaryClassify()
            java.lang.String r0 = r0.getClassifyName()
            goto L18
        L16:
            java.lang.String r0 = ""
        L18:
            boolean r1 = r5 instanceof com.huawei.pluginfitnessadvice.Classify
            if (r1 == 0) goto L22
            com.huawei.pluginfitnessadvice.Classify r5 = (com.huawei.pluginfitnessadvice.Classify) r5
            java.lang.String r0 = r5.getClassifyName()
        L22:
            r5 = 2131570646(0x7f0d2fd6, float:1.8766953E38)
            r3.b(r5, r0)
            int r0 = r2.f3069a
            if (r0 != r4) goto L2f
            int r4 = r2.b
            goto L31
        L2f:
            int r4 = r2.c
        L31:
            r3.c(r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.adapter.SportAllCourseCategoryAdapter.convert(com.huawei.ui.commonui.adapter.RecyclerHolder, int, java.lang.Object):void");
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerHolder recyclerHolder, int i) {
        if (recyclerHolder instanceof RecyclerHolder) {
            super.onBindViewHolder(recyclerHolder, i);
        } else {
            LogUtil.h("SportAllCourseCategoryAdapter", "onBindViewHolder, holder not RecyclerHolder.");
        }
    }
}

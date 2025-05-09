package com.huawei.health.suggestion.ui.polymericpage.adapter;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jcf;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class PolymericCategoryAdapter extends BaseRecyclerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private RecyclerHolder f3306a;
    private int c;
    private int d;
    private int e;

    public PolymericCategoryAdapter(List list) {
        super(list, R.layout.sug_item_sport_all_course_category);
        this.c = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        this.d = BaseApplication.getContext().getResources().getColor(R.color._2131299241_res_0x7f090ba9);
        this.e = 0;
    }

    public void a(int i) {
        if (this.e == i || i < 0 || i >= getItemCount()) {
            LogUtil.h("SportAllCourseCategoryAdapter", "setSelectIndex, selectIndex not change or selectIndex is out bound.");
        } else {
            this.e = i;
            notifyDataSetChanged();
        }
    }

    public int d() {
        return this.e;
    }

    public RecyclerHolder a() {
        return this.f3306a;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0037  */
    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void convert(com.huawei.ui.commonui.adapter.RecyclerHolder r3, int r4, java.lang.Object r5) {
        /*
            r2 = this;
            boolean r0 = r5 instanceof com.huawei.basefitnessadvice.model.Navigation
            if (r0 == 0) goto L8
            r2.e(r3, r4, r5)
            return
        L8:
            boolean r0 = r5 instanceof com.huawei.pluginfitnessadvice.ClassifyInfo
            if (r0 == 0) goto L1e
            r0 = r5
            com.huawei.pluginfitnessadvice.ClassifyInfo r0 = (com.huawei.pluginfitnessadvice.ClassifyInfo) r0
            com.huawei.pluginfitnessadvice.Classify r1 = r0.getPrimaryClassify()
            if (r1 == 0) goto L1e
            com.huawei.pluginfitnessadvice.Classify r0 = r0.getPrimaryClassify()
            java.lang.String r0 = r0.getClassifyName()
            goto L20
        L1e:
            java.lang.String r0 = ""
        L20:
            boolean r1 = r5 instanceof com.huawei.pluginfitnessadvice.Classify
            if (r1 == 0) goto L2a
            com.huawei.pluginfitnessadvice.Classify r5 = (com.huawei.pluginfitnessadvice.Classify) r5
            java.lang.String r0 = r5.getClassifyName()
        L2a:
            r5 = 2131570646(0x7f0d2fd6, float:1.8766953E38)
            r3.b(r5, r0)
            int r1 = r2.e
            if (r1 != r4) goto L37
            int r1 = r2.c
            goto L39
        L37:
            int r1 = r2.d
        L39:
            r3.c(r5, r1)
            int r5 = r2.e
            if (r5 != r4) goto L4d
            r4 = 2131570645(0x7f0d2fd5, float:1.876695E38)
            android.view.View r4 = r3.cwK_(r4)
            r5 = 1
            defpackage.jcf.bEJ_(r4, r0, r5)
            r2.f3306a = r3
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericCategoryAdapter.convert(com.huawei.ui.commonui.adapter.RecyclerHolder, int, java.lang.Object):void");
    }

    private void e(RecyclerHolder recyclerHolder, int i, Object obj) {
        Navigation navigation = (Navigation) obj;
        b(recyclerHolder);
        if (navigation.getDisplayNavigationIcon() == 1 && !TextUtils.isEmpty(navigation.getNavigationIconUrl())) {
            recyclerHolder.a(R.id.sug_item_course_category_icon, 0);
            recyclerHolder.a(R.id.sug_item_course_category_text, 8);
            if (this.e == i) {
                recyclerHolder.b(R.id.sug_item_course_category_icon, navigation.getNavigationSelectedIconUrl(), 0);
                return;
            } else {
                recyclerHolder.b(R.id.sug_item_course_category_icon, navigation.getNavigationIconUrl(), 0);
                return;
            }
        }
        recyclerHolder.a(R.id.sug_item_course_category_icon, 8);
        recyclerHolder.a(R.id.sug_item_course_category_text, 0);
        recyclerHolder.b(R.id.sug_item_course_category_text, navigation.getName());
        recyclerHolder.c(R.id.sug_item_course_category_text, this.e == i ? this.c : this.d);
        if (this.e == i) {
            jcf.bEJ_(recyclerHolder.cwK_(R.id.sug_item_course_category_layout), navigation.getName(), true);
            this.f3306a = recyclerHolder;
        }
    }

    private void b(RecyclerHolder recyclerHolder) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.sug_item_course_category_text);
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("SportAllCourseCategoryAdapter", "updateNavigationNameSize context == null");
        } else if (healthTextView != null) {
            if (LanguageUtil.h(context)) {
                healthTextView.setTextSize(2, 14.0f);
            } else {
                healthTextView.setTextSize(2, 12.0f);
            }
        }
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

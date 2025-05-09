package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9List_01Adapter;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ccq;
import defpackage.dpf;
import defpackage.nru;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class Section21_9List_01 extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f2647a;
    private HealthRecycleView b;
    private Context c;
    private View d;

    public Section21_9List_01(Context context) {
        this(context, null);
    }

    public Section21_9List_01(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Section21_9List_01(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9list_01_layout, (ViewGroup) this, false);
        this.d = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.section_sub_header);
        this.f2647a = healthSubHeader;
        ccq.c(healthSubHeader);
        this.f2647a.setSubHeaderBackgroundColor(ContextCompat.getColor(this.d.getContext(), R.color._2131296971_res_0x7f0902cb));
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.d.findViewById(R.id.section21_9list_01_recycler_view);
        this.b = healthRecycleView;
        ccq.a(healthRecycleView, context);
        if (this.b.getItemDecorationCount() > 0 && this.b.getItemDecorationAt(0) != null) {
            this.b.removeItemDecorationAt(0);
        }
        this.b.addItemDecoration(new b(this.c, 0, 12));
        return this.d;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        this.f2647a.setHeadTitleText(nru.b(hashMap, "TITLE", ""));
        OnClickSectionListener a2 = nru.a(hashMap, "CLICK_EVENT_LISTENER", null);
        dpf.b(hashMap, this.f2647a);
        List d = nru.d(hashMap, "SEARCH_CONTENT", GlobalSearchContent.class, null);
        if (d == null) {
            LogUtil.b("Section21_9List_01", "bindParamsToView not found content list");
            return;
        }
        Section21_9List_01Adapter section21_9List_01Adapter = new Section21_9List_01Adapter(this.c, d);
        if (a2 != null) {
            section21_9List_01Adapter.d(a2);
        }
        int d2 = nru.d((Map) hashMap, "ITEM_LIMIT", 0);
        if (d2 > 0) {
            section21_9List_01Adapter.c(d2);
        }
        this.b.setAdapter(section21_9List_01Adapter);
    }

    static class b extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private int f2648a;
        private int c;
        private Context e;

        b(Context context, int i, int i2) {
            this.e = context;
            this.f2648a = i;
            this.c = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDraw(canvas, recyclerView, state);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (rect == null || recyclerView == null) {
                LogUtil.h("Section21_9List_01", "getItemOffsets, outRect or parent is null");
                return;
            }
            super.getItemOffsets(rect, view, recyclerView, state);
            Context context = this.e;
            int i = this.f2648a;
            ccq.Db_(rect, view, recyclerView, 2, context, i, i, 2, true);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "Section21_9List_01";
    }
}

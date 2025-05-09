package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.Section21_9Target_03_OverseaAdapter;
import com.huawei.health.knit.section.view.Section21_9Target_03OverseaImpl;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ece;
import defpackage.koq;
import defpackage.nru;
import defpackage.nsn;
import defpackage.nsy;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class Section21_9Target_03OverseaImpl extends Section21_9Target_03Impl {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f2654a;
    private View b;
    private HealthRecycleView c;
    private Section21_9Target_03_OverseaAdapter d;

    public Section21_9Target_03OverseaImpl(Context context) {
        super(context);
    }

    @Override // com.huawei.health.knit.section.view.Section21_9Target_03Impl
    public View onCreateView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section21_9target_03_layout_oversea, viewGroup, false);
        this.b = inflate;
        HealthSubHeader healthSubHeader = (HealthSubHeader) inflate.findViewById(R.id.sub_header);
        this.f2654a = healthSubHeader;
        healthSubHeader.setMoreTextVisibility(8);
        this.f2654a.setSubHeaderBackgroundColor(context.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        this.c = (HealthRecycleView) this.b.findViewById(R.id.grid_view);
        int i = nsn.ag(context) ? 2 : 1;
        this.c.setLayoutManager(new GridLayoutManager(context, i, 1, false));
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        this.c.addItemDecoration(new a(i, dimensionPixelSize, dimensionPixelSize));
        Section21_9Target_03_OverseaAdapter section21_9Target_03_OverseaAdapter = new Section21_9Target_03_OverseaAdapter(context, dimensionPixelSize, i);
        this.d = section21_9Target_03_OverseaAdapter;
        this.c.setAdapter(section21_9Target_03_OverseaAdapter);
        this.b.post(new Runnable() { // from class: efu
            @Override // java.lang.Runnable
            public final void run() {
                Section21_9Target_03OverseaImpl.this.a();
            }
        });
        return this.b;
    }

    public /* synthetic */ void a() {
        nsn.cLD_(this.b);
    }

    @Override // com.huawei.health.knit.section.view.Section21_9Target_03Impl
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        Object obj = hashMap.get("SectionAiWorkoutPlanType_PLAN_LIST");
        if (koq.e(obj, ece.class)) {
            List<ece> list = (List) obj;
            nsy.d(this.f2654a, koq.b(list) ? 8 : 0, nru.b(hashMap, "TITLE", ""), 8, 8);
            Section21_9Target_03_OverseaAdapter section21_9Target_03_OverseaAdapter = this.d;
            if (section21_9Target_03_OverseaAdapter != null) {
                section21_9Target_03_OverseaAdapter.e(list);
            }
        }
    }

    static final class a extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private int f2655a;
        private int c;
        private int d;

        public a(int i, int i2, int i3) {
            this.f2655a = i;
            this.d = i2;
            this.c = i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = this.f2655a;
            if (childAdapterPosition % i > 0) {
                rect.left = this.d / i;
            }
            if (childAdapterPosition / this.f2655a >= 1) {
                rect.top = this.c;
            }
        }
    }

    @Override // com.huawei.health.knit.section.view.Section21_9Target_03Impl
    public String getLogTag() {
        return "Section21_9Target_03OverseaImpl";
    }
}

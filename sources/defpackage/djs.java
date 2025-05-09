package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.ecologydevice.ui.measure.adapter.SecondRopeItemAdapter;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class djs {

    /* renamed from: a, reason: collision with root package name */
    private Context f11685a;
    private SecondRopeItemAdapter b;
    private HealthRecycleView d;

    public djs(HealthRecycleView healthRecycleView) {
        this.d = healthRecycleView;
        this.f11685a = healthRecycleView.getContext();
        a();
    }

    private void a() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.f11685a, 2);
        gridLayoutManager.setOrientation(1);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: djs.3
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (djs.this.b.get(i).g()) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        this.d.setLayoutManager(gridLayoutManager);
        this.d.setIsScroll(false);
        SecondRopeItemAdapter secondRopeItemAdapter = new SecondRopeItemAdapter();
        this.b = secondRopeItemAdapter;
        this.d.setAdapter(secondRopeItemAdapter);
        this.d.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: djs.2

            /* renamed from: a, reason: collision with root package name */
            private int f11686a;
            private boolean e;

            {
                this.f11686a = hcn.a(djs.this.f11685a, 6.0f);
                this.e = LanguageUtil.bc(djs.this.f11685a);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                if (djs.this.b.get(childAdapterPosition).g()) {
                    return;
                }
                int spanIndex = gridLayoutManager.getSpanSizeLookup().getSpanIndex(childAdapterPosition, 2) % 2;
                if ((spanIndex == 0 && !this.e) || (spanIndex == 1 && this.e)) {
                    rect.right = this.f11686a;
                } else {
                    rect.left = this.f11686a;
                }
            }
        });
    }

    public void d(BaseRecyclerAdapter.OnItemClickListener<dhe> onItemClickListener) {
        this.b.setOnItemClickListener(onItemClickListener);
    }

    public void c(List<dhe> list) {
        this.b.c(list);
    }
}

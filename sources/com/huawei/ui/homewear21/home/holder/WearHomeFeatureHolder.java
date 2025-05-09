package com.huawei.ui.homewear21.home.holder;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class WearHomeFeatureHolder extends RecyclerView.ViewHolder {
    private static int d;

    /* renamed from: a, reason: collision with root package name */
    private HealthRecycleView f9672a;

    public WearHomeFeatureHolder(View view) {
        super(view);
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMd_(view, R.id.recyclerView);
        this.f9672a = healthRecycleView;
        healthRecycleView.addItemDecoration(new SpaceItemDecoration((int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362008_res_0x7f0a00d8)));
    }

    public RecyclerView e() {
        return this.f9672a;
    }

    public static void a(int i) {
        d = i;
    }

    public static class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private int f9673a;

        public SpaceItemDecoration(int i) {
            this.f9673a = i;
        }

        private int c(RecyclerView recyclerView) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                return ((GridLayoutManager) layoutManager).getSpanCount();
            }
            return -1;
        }

        private boolean a(RecyclerView recyclerView, int i, int i2, int i3) {
            return (recyclerView.getLayoutManager() instanceof GridLayoutManager) && i >= i3 - (i3 % i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
            int c = c(recyclerView);
            int itemCount = recyclerView.getAdapter().getItemCount();
            LogUtil.d("WearHomeFeatureHolder", "getItemOffsets itemPosition ", Integer.valueOf(childLayoutPosition), " spanCount ", Integer.valueOf(c), " childCount ", Integer.valueOf(itemCount));
            if (a(recyclerView, childLayoutPosition, c, itemCount) && childLayoutPosition == itemCount - 1 && c == 2) {
                LogUtil.d("WearHomeFeatureHolder", "getItemOffsets itemPosition == childCount -1 spanCount == 2");
                rect.set(0, 0, 0, 0);
            } else {
                dmv_(rect, view);
            }
        }

        private void dmv_(Rect rect, View view) {
            if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                if (layoutParams.getSpanIndex() != -1) {
                    if (LanguageUtil.bc(BaseApplication.getContext())) {
                        if ((layoutParams.getSpanIndex() + 1) % WearHomeFeatureHolder.d != 1) {
                            if ((layoutParams.getSpanIndex() + 1) % WearHomeFeatureHolder.d == 0) {
                                rect.set(0, 0, this.f9673a / 2, 0);
                                return;
                            } else {
                                int i = this.f9673a / 2;
                                rect.set(i, 0, i, 0);
                                return;
                            }
                        }
                        rect.set(this.f9673a / 2, 0, 0, 0);
                        return;
                    }
                    if ((layoutParams.getSpanIndex() + 1) % WearHomeFeatureHolder.d != 1) {
                        if ((layoutParams.getSpanIndex() + 1) % WearHomeFeatureHolder.d == 0) {
                            rect.set(this.f9673a / 2, 0, 0, 0);
                            return;
                        } else {
                            int i2 = this.f9673a / 2;
                            rect.set(i2, 0, i2, 0);
                            return;
                        }
                    }
                    rect.set(0, 0, this.f9673a / 2, 0);
                }
            }
        }
    }
}

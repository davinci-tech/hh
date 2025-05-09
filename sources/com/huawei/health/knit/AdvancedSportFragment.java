package com.huawei.health.knit;

import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.popupview.PopViewList;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class AdvancedSportFragment extends BaseFragment {
    protected e mPopViewController = new e();

    public static class e {
        private PopViewList.PopViewClickListener c;
        private boolean d = false;
        private ArrayList<String> e = new ArrayList<>();

        public final void a(boolean z) {
            this.d = z;
        }

        public final boolean c() {
            return this.d;
        }

        public final ArrayList<String> d() {
            return this.e;
        }

        public final void a(ArrayList<String> arrayList) {
            this.e.clear();
            this.e.addAll(arrayList);
        }

        public final PopViewList.PopViewClickListener b() {
            return this.c;
        }

        public final void c(PopViewList.PopViewClickListener popViewClickListener) {
            this.c = popViewClickListener;
        }
    }

    public e getPopViewController() {
        return this.mPopViewController;
    }
}

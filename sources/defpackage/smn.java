package defpackage;

import android.view.View;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.uikit.hwrecyclerview.widget.HwLinearLayoutManagerEx;

/* loaded from: classes9.dex */
public class smn {
    public static int d(RecyclerView.State state, OrientationHelper orientationHelper, HwLinearLayoutManagerEx.b bVar, RecyclerView.LayoutManager layoutManager, boolean z) {
        View view;
        if (state == null || state.getItemCount() == 0 || layoutManager == null || layoutManager.getChildCount() == 0 || orientationHelper == null || bVar == null || (view = bVar.c) == null || bVar.f10717a == null) {
            return 0;
        }
        int max = z ? Math.max(0, (state.getItemCount() - Math.max(layoutManager.getPosition(bVar.c), layoutManager.getPosition(bVar.f10717a))) - 1) : Math.max(0, Math.min(layoutManager.getPosition(view), layoutManager.getPosition(bVar.f10717a)));
        return !bVar.d ? max : Math.round((max * (Math.abs(orientationHelper.getDecoratedEnd(bVar.f10717a) - orientationHelper.getDecoratedStart(bVar.c)) / (Math.abs(layoutManager.getPosition(bVar.c) - layoutManager.getPosition(bVar.f10717a)) + 1))) + (orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(bVar.c)));
    }

    public static int e(RecyclerView.State state, OrientationHelper orientationHelper, HwLinearLayoutManagerEx.b bVar, RecyclerView.LayoutManager layoutManager) {
        View view;
        if (state == null || state.getItemCount() == 0 || layoutManager == null || layoutManager.getChildCount() == 0 || orientationHelper == null || bVar == null || bVar.c == null || (view = bVar.f10717a) == null) {
            return 0;
        }
        return !bVar.d ? state.getItemCount() : (int) (((orientationHelper.getDecoratedEnd(view) - orientationHelper.getDecoratedStart(bVar.c)) / (Math.abs(layoutManager.getPosition(bVar.c) - layoutManager.getPosition(bVar.f10717a)) + 1)) * state.getItemCount());
    }

    public static int b(RecyclerView.State state, OrientationHelper orientationHelper, HwLinearLayoutManagerEx.b bVar, RecyclerView.LayoutManager layoutManager) {
        if (state == null || state.getItemCount() == 0 || layoutManager == null || layoutManager.getChildCount() == 0 || orientationHelper == null || bVar == null) {
            return 0;
        }
        if (!bVar.d) {
            return Math.abs(layoutManager.getPosition(bVar.c) - layoutManager.getPosition(bVar.f10717a)) + 1;
        }
        return Math.min(orientationHelper.getTotalSpace(), orientationHelper.getDecoratedEnd(bVar.f10717a) - orientationHelper.getDecoratedStart(bVar.c));
    }
}

package defpackage;

import android.util.SparseArray;
import com.huawei.ui.commonui.tablewidget.ViewHolder;
import java.util.ArrayDeque;
import java.util.Deque;

/* loaded from: classes6.dex */
public class nrg {
    private SparseArray<Deque<ViewHolder>> e = new SparseArray<>(6);

    public void a(ViewHolder viewHolder) {
        Deque<ViewHolder> deque = this.e.get(viewHolder.getItemType());
        if (deque == null) {
            deque = new ArrayDeque<>();
            this.e.put(viewHolder.getItemType(), deque);
        }
        deque.push(viewHolder);
    }

    public ViewHolder e(int i) {
        Deque<ViewHolder> deque = this.e.get(i);
        if (deque == null || deque.isEmpty()) {
            return null;
        }
        return deque.pop();
    }
}

package defpackage;

import android.util.SparseArray;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.hwbasemgr.ResponseCallback;

/* loaded from: classes3.dex */
public final /* synthetic */ class avt implements ResponseCallback {
    public final /* synthetic */ TaskDataListener e;

    @Override // com.huawei.hwbasemgr.ResponseCallback
    public final void onResponse(int i, Object obj) {
        this.e.onDataChange(i, (SparseArray) obj);
    }
}

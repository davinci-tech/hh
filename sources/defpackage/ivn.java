package defpackage;

import android.util.SparseArray;
import com.huawei.hihealthservice.updatemanager.command.UpdateCommand;

/* loaded from: classes7.dex */
public class ivn {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<UpdateCommand> f13631a;

    static {
        SparseArray<UpdateCommand> sparseArray = new SparseArray<>(10);
        f13631a = sparseArray;
        sparseArray.put(100, new ivs());
        sparseArray.put(102, new ivp());
    }

    public static UpdateCommand a(int i) {
        return f13631a.get(i);
    }
}

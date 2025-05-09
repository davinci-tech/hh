package defpackage;

import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;

/* loaded from: classes6.dex */
public class nod {
    public static boolean c(IStorageModel iStorageModel) {
        if (iStorageModel instanceof StorageGenericModel) {
            return ((StorageGenericModel) iStorageModel).a() instanceof StorageGenericModel.d;
        }
        return false;
    }
}

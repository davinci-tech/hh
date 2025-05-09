package defpackage;

import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.model.MultiDataBarModel;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;

/* loaded from: classes6.dex */
public class noy {
    public static float c(IStorageModel iStorageModel) {
        if (nod.c(iStorageModel)) {
            return 0.0f;
        }
        return d(iStorageModel);
    }

    public static float h(IStorageModel iStorageModel) {
        if (nod.c(iStorageModel)) {
            return 0.0f;
        }
        return d(iStorageModel);
    }

    public static float a(IStorageModel iStorageModel) {
        if (nod.c(iStorageModel)) {
            return 0.0f;
        }
        if (iStorageModel instanceof nnc) {
            return ((nnc) iStorageModel).e();
        }
        if (iStorageModel instanceof StorageGenericModel) {
            StorageGenericModel.PresentStyle a2 = ((StorageGenericModel) iStorageModel).a();
            if (!(a2 instanceof StorageGenericModel.e)) {
                throw new RuntimeException("parseBarFloor IStorageModel found presentStyle not bar");
            }
            return ((StorageGenericModel.e) a2).e();
        }
        if (iStorageModel instanceof noa) {
            return ((noa) iStorageModel).c();
        }
        if (iStorageModel instanceof MultiDataBarModel) {
            return 0.0f;
        }
        throw new RuntimeException("parseBarFloor failed");
    }

    public static float i(IStorageModel iStorageModel) {
        if (nod.c(iStorageModel)) {
            return 0.0f;
        }
        if (iStorageModel instanceof nnw) {
            return ((nnw) iStorageModel).a();
        }
        if (iStorageModel instanceof StorageGenericModel) {
            StorageGenericModel.PresentStyle a2 = ((StorageGenericModel) iStorageModel).a();
            if (!(a2 instanceof StorageGenericModel.a)) {
                throw new RuntimeException("parseLineValue IStorageModel found presentStyle not bar");
            }
            return ((StorageGenericModel.a) a2).c();
        }
        throw new RuntimeException("parseLineValue failed");
    }

    public static int b(IStorageModel iStorageModel) {
        if (nod.c(iStorageModel)) {
            return 0;
        }
        if (iStorageModel instanceof noa) {
            return ((noa) iStorageModel).e();
        }
        if (iStorageModel instanceof nnc) {
            return ((nnc) iStorageModel).f();
        }
        return 0;
    }

    public static int e(IStorageModel iStorageModel) {
        if (nod.c(iStorageModel)) {
            return 0;
        }
        if (iStorageModel instanceof noa) {
            return ((noa) iStorageModel).d();
        }
        if (iStorageModel instanceof nnc) {
            return ((nnc) iStorageModel).g();
        }
        return 0;
    }

    public static float d(IStorageModel iStorageModel) {
        if (iStorageModel instanceof nnc) {
            return ((nnc) iStorageModel).b();
        }
        if (iStorageModel instanceof StorageGenericModel) {
            StorageGenericModel.PresentStyle a2 = ((StorageGenericModel) iStorageModel).a();
            if (!(a2 instanceof StorageGenericModel.e)) {
                throw new RuntimeException("parseBarCeil IStorageModel found presentStyle not bar");
            }
            return ((StorageGenericModel.e) a2).b();
        }
        if (iStorageModel instanceof noa) {
            return ((noa) iStorageModel).c();
        }
        if (iStorageModel instanceof MultiDataBarModel) {
            return ((MultiDataBarModel) iStorageModel).getCeil();
        }
        throw new RuntimeException("parseBarCeil failed");
    }
}

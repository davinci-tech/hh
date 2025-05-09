package defpackage;

import android.content.Context;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.hihealthkit.context.OutOfBandContext;

/* loaded from: classes4.dex */
public class idr extends OutOfBandContext {
    private H5ProAppInfo e;

    public idr(Context context, H5ProAppInfo h5ProAppInfo) {
        super(context);
        this.e = h5ProAppInfo;
    }

    @Override // com.huawei.hihealthkit.context.OutOfBandContext
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public H5ProAppInfo getOutOfBandData() {
        return this.e;
    }
}

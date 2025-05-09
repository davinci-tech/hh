package com.huawei.agconnect.config;

import android.content.Context;
import com.huawei.agconnect.config.impl.Utils;
import java.io.InputStream;

@Deprecated
/* loaded from: classes2.dex */
public abstract class LazyInputStream {
    private final Context mContext;
    private InputStream mInput;

    public abstract InputStream get(Context context);

    public InputStream loadInputStream() {
        if (this.mInput == null) {
            this.mInput = get(this.mContext);
        }
        return this.mInput;
    }

    public final void close() {
        Utils.closeQuietly(this.mInput);
    }

    public LazyInputStream(Context context) {
        this.mContext = context;
    }
}

package org.apache.commons.io;

import java.io.InputStream;
import org.apache.commons.io.function.IOTriFunction;

/* loaded from: classes10.dex */
public final /* synthetic */ class IOUtils$$ExternalSyntheticLambda3 implements IOTriFunction {
    public final /* synthetic */ InputStream f$0;

    @Override // org.apache.commons.io.function.IOTriFunction
    public final Object apply(Object obj, Object obj2, Object obj3) {
        int read;
        read = this.f$0.read((byte[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
        return Integer.valueOf(read);
    }

    public /* synthetic */ IOUtils$$ExternalSyntheticLambda3(InputStream inputStream) {
        this.f$0 = inputStream;
    }
}

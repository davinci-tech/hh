package org.apache.commons.io;

import java.io.Closeable;
import java.util.function.Consumer;

/* loaded from: classes10.dex */
public final /* synthetic */ class IOUtils$$ExternalSyntheticLambda2 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        IOUtils.closeQuietly((Closeable) obj);
    }
}

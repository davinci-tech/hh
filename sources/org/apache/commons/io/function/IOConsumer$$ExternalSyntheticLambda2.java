package org.apache.commons.io.function;

import java.io.IOException;
import java.util.function.BiFunction;
import org.apache.commons.io.IOIndexedException;

/* loaded from: classes7.dex */
public final /* synthetic */ class IOConsumer$$ExternalSyntheticLambda2 implements BiFunction {
    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return new IOIndexedException(((Integer) obj).intValue(), (IOException) obj2);
    }
}

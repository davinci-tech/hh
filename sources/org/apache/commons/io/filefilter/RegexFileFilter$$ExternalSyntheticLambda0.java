package org.apache.commons.io.filefilter;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.function.Function;
import org.apache.commons.io.file.PathUtils;

/* loaded from: classes10.dex */
public final /* synthetic */ class RegexFileFilter$$ExternalSyntheticLambda0 implements Function, Serializable {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return PathUtils.getFileNameString((Path) obj);
    }
}

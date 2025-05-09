package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import org.apache.commons.io.function.IOBiFunction;

/* loaded from: classes10.dex */
public class NoopPathVisitor extends SimplePathVisitor {
    public static final NoopPathVisitor INSTANCE = new NoopPathVisitor();

    public NoopPathVisitor() {
    }

    public NoopPathVisitor(IOBiFunction<Path, IOException, FileVisitResult> iOBiFunction) {
        super(iOBiFunction);
    }
}

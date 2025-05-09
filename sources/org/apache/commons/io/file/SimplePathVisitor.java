package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.Objects;
import org.apache.commons.io.function.IOBiFunction;

/* loaded from: classes7.dex */
public abstract class SimplePathVisitor extends SimpleFileVisitor<Path> implements PathVisitor {
    private final IOBiFunction<Path, IOException, FileVisitResult> visitFileFailedFunction;

    protected SimplePathVisitor() {
        this.visitFileFailedFunction = new IOBiFunction() { // from class: org.apache.commons.io.file.SimplePathVisitor$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                return SimplePathVisitor.this.m1170lambda$new$0$orgapachecommonsiofileSimplePathVisitor((Path) obj, (IOException) obj2);
            }
        };
    }

    /* renamed from: lambda$new$0$org-apache-commons-io-file-SimplePathVisitor, reason: not valid java name */
    /* synthetic */ FileVisitResult m1170lambda$new$0$orgapachecommonsiofileSimplePathVisitor(Object obj, IOException iOException) throws IOException {
        return super.visitFileFailed((SimplePathVisitor) obj, iOException);
    }

    protected SimplePathVisitor(IOBiFunction<Path, IOException, FileVisitResult> iOBiFunction) {
        this.visitFileFailedFunction = (IOBiFunction) Objects.requireNonNull(iOBiFunction, "visitFileFailed");
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFileFailed(Path path, IOException iOException) throws IOException {
        return this.visitFileFailedFunction.apply(path, iOException);
    }
}

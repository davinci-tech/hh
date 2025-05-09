package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.file.NoopPathVisitor;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.PathVisitor;
import org.apache.commons.io.function.IOSupplier;

/* loaded from: classes10.dex */
public class PathVisitorFileFilter extends AbstractFileFilter {
    private final PathVisitor pathVisitor;

    public PathVisitorFileFilter(PathVisitor pathVisitor) {
        this.pathVisitor = pathVisitor == null ? NoopPathVisitor.INSTANCE : pathVisitor;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        try {
            Path path = file.toPath();
            return visitFile(path, file.exists() ? PathUtils.readBasicFileAttributes(path) : null) == FileVisitResult.CONTINUE;
        } catch (IOException e) {
            return handle(e) == FileVisitResult.CONTINUE;
        }
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        try {
            Path resolve = file.toPath().resolve(str);
            return accept(resolve, PathUtils.readBasicFileAttributes(resolve)) == FileVisitResult.CONTINUE;
        } catch (IOException e) {
            return handle(e) == FileVisitResult.CONTINUE;
        }
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(final Path path, final BasicFileAttributes basicFileAttributes) {
        return get(new IOSupplier() { // from class: org.apache.commons.io.filefilter.PathVisitorFileFilter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return PathVisitorFileFilter.this.m1175xba762e63(path, basicFileAttributes);
            }
        });
    }

    /* renamed from: lambda$accept$0$org-apache-commons-io-filefilter-PathVisitorFileFilter, reason: not valid java name */
    /* synthetic */ FileVisitResult m1175xba762e63(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        return Files.isDirectory(path, new LinkOption[0]) ? this.pathVisitor.postVisitDirectory(path, null) : visitFile(path, basicFileAttributes);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        return this.pathVisitor.visitFile(path, basicFileAttributes);
    }
}

package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SymbolicLinkFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.function.IOBiFunction;

/* loaded from: classes7.dex */
public class CountingPathVisitor extends SimplePathVisitor {
    static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final PathFilter dirFilter;
    private final PathFilter fileFilter;
    private final Counters.PathCounters pathCounters;

    static IOFileFilter defaultDirFilter() {
        return TrueFileFilter.INSTANCE;
    }

    static IOFileFilter defaultFileFilter() {
        return new SymbolicLinkFileFilter(FileVisitResult.TERMINATE, FileVisitResult.CONTINUE);
    }

    public static CountingPathVisitor withBigIntegerCounters() {
        return new CountingPathVisitor(Counters.bigIntegerPathCounters());
    }

    public static CountingPathVisitor withLongCounters() {
        return new CountingPathVisitor(Counters.longPathCounters());
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters) {
        this(pathCounters, defaultFileFilter(), defaultDirFilter());
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters, PathFilter pathFilter, PathFilter pathFilter2) {
        this.pathCounters = (Counters.PathCounters) Objects.requireNonNull(pathCounters, "pathCounter");
        this.fileFilter = (PathFilter) Objects.requireNonNull(pathFilter, "fileFilter");
        this.dirFilter = (PathFilter) Objects.requireNonNull(pathFilter2, "dirFilter");
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters, PathFilter pathFilter, PathFilter pathFilter2, IOBiFunction<Path, IOException, FileVisitResult> iOBiFunction) {
        super(iOBiFunction);
        this.pathCounters = (Counters.PathCounters) Objects.requireNonNull(pathCounters, "pathCounter");
        this.fileFilter = (PathFilter) Objects.requireNonNull(pathFilter, "fileFilter");
        this.dirFilter = (PathFilter) Objects.requireNonNull(pathFilter2, "dirFilter");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CountingPathVisitor) {
            return Objects.equals(this.pathCounters, ((CountingPathVisitor) obj).pathCounters);
        }
        return false;
    }

    public Counters.PathCounters getPathCounters() {
        return this.pathCounters;
    }

    public int hashCode() {
        return Objects.hash(this.pathCounters);
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
        updateDirCounter(path, iOException);
        return FileVisitResult.CONTINUE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        return this.dirFilter.accept(path, basicFileAttributes) != FileVisitResult.CONTINUE ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
    }

    public String toString() {
        return this.pathCounters.toString();
    }

    protected void updateDirCounter(Path path, IOException iOException) {
        this.pathCounters.getDirectoryCounter().increment();
    }

    protected void updateFileCounters(Path path, BasicFileAttributes basicFileAttributes) {
        this.pathCounters.getFileCounter().increment();
        this.pathCounters.getByteCounter().add(basicFileAttributes.size());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (Files.exists(path, new LinkOption[0]) && this.fileFilter.accept(path, basicFileAttributes) == FileVisitResult.CONTINUE) {
            updateFileCounters(path, basicFileAttributes);
        }
        return FileVisitResult.CONTINUE;
    }
}

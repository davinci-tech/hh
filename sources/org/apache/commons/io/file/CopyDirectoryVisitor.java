package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.file.Counters;

/* loaded from: classes10.dex */
public class CopyDirectoryVisitor extends CountingPathVisitor {
    private final CopyOption[] copyOptions;
    private final Path sourceDirectory;
    private final Path targetDirectory;

    private static CopyOption[] toCopyOption(CopyOption... copyOptionArr) {
        return copyOptionArr == null ? PathUtils.EMPTY_COPY_OPTIONS : (CopyOption[]) copyOptionArr.clone();
    }

    public CopyDirectoryVisitor(Counters.PathCounters pathCounters, Path path, Path path2, CopyOption... copyOptionArr) {
        super(pathCounters);
        this.sourceDirectory = path;
        this.targetDirectory = path2;
        this.copyOptions = toCopyOption(copyOptionArr);
    }

    public CopyDirectoryVisitor(Counters.PathCounters pathCounters, PathFilter pathFilter, PathFilter pathFilter2, Path path, Path path2, CopyOption... copyOptionArr) {
        super(pathCounters, pathFilter, pathFilter2);
        this.sourceDirectory = path;
        this.targetDirectory = path2;
        this.copyOptions = toCopyOption(copyOptionArr);
    }

    protected void copy(Path path, Path path2) throws IOException {
        Files.copy(path, path2, this.copyOptions);
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        CopyDirectoryVisitor copyDirectoryVisitor = (CopyDirectoryVisitor) obj;
        return Arrays.equals(this.copyOptions, copyDirectoryVisitor.copyOptions) && Objects.equals(this.sourceDirectory, copyDirectoryVisitor.sourceDirectory) && Objects.equals(this.targetDirectory, copyDirectoryVisitor.targetDirectory);
    }

    public CopyOption[] getCopyOptions() {
        return (CopyOption[]) this.copyOptions.clone();
    }

    public Path getSourceDirectory() {
        return this.sourceDirectory;
    }

    public Path getTargetDirectory() {
        return this.targetDirectory;
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor
    public int hashCode() {
        int hashCode = super.hashCode();
        return (((hashCode * 31) + Arrays.hashCode(this.copyOptions)) * 31) + Objects.hash(this.sourceDirectory, this.targetDirectory);
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor, java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        Path resolveRelativeAsString = resolveRelativeAsString(path);
        if (Files.notExists(resolveRelativeAsString, new LinkOption[0])) {
            Files.createDirectory(resolveRelativeAsString, new FileAttribute[0]);
        }
        return super.preVisitDirectory(path, basicFileAttributes);
    }

    private Path resolveRelativeAsString(Path path) {
        return this.targetDirectory.resolve(this.sourceDirectory.relativize(path).toString());
    }

    @Override // org.apache.commons.io.file.CountingPathVisitor, java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        Path resolveRelativeAsString = resolveRelativeAsString(path);
        copy(path, resolveRelativeAsString);
        return super.visitFile(resolveRelativeAsString, basicFileAttributes);
    }
}

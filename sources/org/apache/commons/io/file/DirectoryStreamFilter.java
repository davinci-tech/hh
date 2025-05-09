package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.Objects;

/* loaded from: classes10.dex */
public class DirectoryStreamFilter implements DirectoryStream.Filter<Path> {
    private final PathFilter pathFilter;

    public DirectoryStreamFilter(PathFilter pathFilter) {
        this.pathFilter = (PathFilter) Objects.requireNonNull(pathFilter, "pathFilter");
    }

    @Override // java.nio.file.DirectoryStream.Filter
    public boolean accept(Path path) throws IOException {
        return this.pathFilter.accept(path, PathUtils.readBasicFileAttributes(path, PathUtils.EMPTY_LINK_OPTION_ARRAY)) == FileVisitResult.CONTINUE;
    }

    public PathFilter getPathFilter() {
        return this.pathFilter;
    }
}

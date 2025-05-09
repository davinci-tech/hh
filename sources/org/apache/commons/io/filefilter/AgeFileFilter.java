package org.apache.commons.io.filefilter;

import com.huawei.hihealth.HiDataFilter;
import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.function.IOSupplier;

/* loaded from: classes10.dex */
public class AgeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -2132740084016138541L;
    private final boolean acceptOlder;
    private final Instant cutoffInstant;

    public AgeFileFilter(Date date) {
        this(date, true);
    }

    public AgeFileFilter(Date date, boolean z) {
        this(date.toInstant(), z);
    }

    public AgeFileFilter(File file) {
        this(file, true);
    }

    public AgeFileFilter(File file, boolean z) {
        this(FileUtils.lastModifiedUnchecked(file), z);
    }

    public AgeFileFilter(Instant instant) {
        this(instant, true);
    }

    public AgeFileFilter(Instant instant, boolean z) {
        this.acceptOlder = z;
        this.cutoffInstant = instant;
    }

    public AgeFileFilter(long j) {
        this(Instant.ofEpochMilli(j), true);
    }

    public AgeFileFilter(long j, boolean z) {
        this(Instant.ofEpochMilli(j), z);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return this.acceptOlder != FileUtils.isFileNewer(file, this.cutoffInstant);
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(final Path path, BasicFileAttributes basicFileAttributes) {
        return get(new IOSupplier() { // from class: org.apache.commons.io.filefilter.AgeFileFilter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return AgeFileFilter.this.m1171lambda$accept$0$orgapachecommonsiofilefilterAgeFileFilter(path);
            }
        });
    }

    /* renamed from: lambda$accept$0$org-apache-commons-io-filefilter-AgeFileFilter, reason: not valid java name */
    /* synthetic */ FileVisitResult m1171lambda$accept$0$orgapachecommonsiofilefilterAgeFileFilter(Path path) throws IOException {
        return toFileVisitResult(this.acceptOlder != PathUtils.isNewer(path, this.cutoffInstant, new LinkOption[0]));
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + Constants.LEFT_BRACKET_ONLY + (this.acceptOlder ? HiDataFilter.DataFilterExpression.LESS_EQUAL : HiDataFilter.DataFilterExpression.BIGGER_THAN) + this.cutoffInstant + Constants.RIGHT_BRACKET_ONLY;
    }
}

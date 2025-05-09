package org.apache.commons.io.filefilter;

import com.huawei.operation.utils.Constants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes10.dex */
public class DelegateFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -8723373124984771318L;
    private final transient FileFilter fileFilter;
    private final transient FilenameFilter fileNameFilter;

    public DelegateFileFilter(FileFilter fileFilter) {
        Objects.requireNonNull(fileFilter, Constant.FILTER);
        this.fileFilter = fileFilter;
        this.fileNameFilter = null;
    }

    public DelegateFileFilter(FilenameFilter filenameFilter) {
        Objects.requireNonNull(filenameFilter, Constant.FILTER);
        this.fileNameFilter = filenameFilter;
        this.fileFilter = null;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        FileFilter fileFilter = this.fileFilter;
        if (fileFilter != null) {
            return fileFilter.accept(file);
        }
        return super.accept(file);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String str) {
        FilenameFilter filenameFilter = this.fileNameFilter;
        if (filenameFilter != null) {
            return filenameFilter.accept(file, str);
        }
        return super.accept(file, str);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        Object obj = this.fileFilter;
        if (obj == null) {
            obj = this.fileNameFilter;
        }
        return super.toString() + Constants.LEFT_BRACKET_ONLY + obj.toString() + Constants.RIGHT_BRACKET_ONLY;
    }
}

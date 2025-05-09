package org.apache.commons.io.filefilter;

import com.huawei.operation.utils.Constants;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;

/* loaded from: classes10.dex */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] bArr) {
        this(bArr, 0L);
    }

    public MagicNumberFileFilter(byte[] bArr, long j) {
        Objects.requireNonNull(bArr, "magicNumbers");
        if (bArr.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = (byte[]) bArr.clone();
        this.byteOffset = j;
    }

    public MagicNumberFileFilter(String str) {
        this(str, 0L);
    }

    public MagicNumberFileFilter(String str, long j) {
        Objects.requireNonNull(str, "magicNumber");
        if (str.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        }
        if (j < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
        this.magicNumbers = str.getBytes(Charset.defaultCharset());
        this.byteOffset = j;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        if (file == null || !file.isFile() || !file.canRead()) {
            return false;
        }
        try {
            RandomAccessFile create = RandomAccessFileMode.READ_ONLY.create(file);
            try {
                byte[] bArr = this.magicNumbers;
                boolean equals = Arrays.equals(bArr, RandomAccessFiles.read(create, this.byteOffset, bArr.length));
                if (create != null) {
                    create.close();
                }
                return equals;
            } finally {
            }
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(Path path, BasicFileAttributes basicFileAttributes) {
        if (path != null && Files.isRegularFile(path, new LinkOption[0]) && Files.isReadable(path)) {
            try {
                FileChannel open = FileChannel.open(path, new OpenOption[0]);
                try {
                    ByteBuffer allocate = ByteBuffer.allocate(this.magicNumbers.length);
                    open.position(this.byteOffset);
                    int read = open.read(allocate);
                    byte[] bArr = this.magicNumbers;
                    if (read != bArr.length) {
                        FileVisitResult fileVisitResult = FileVisitResult.TERMINATE;
                        if (open != null) {
                            open.close();
                        }
                        return fileVisitResult;
                    }
                    FileVisitResult fileVisitResult2 = toFileVisitResult(Arrays.equals(bArr, allocate.array()));
                    if (open != null) {
                        open.close();
                    }
                    return fileVisitResult2;
                } finally {
                }
            } catch (IOException unused) {
            }
        }
        return FileVisitResult.TERMINATE;
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        return super.toString() + Constants.LEFT_BRACKET_ONLY + new String(this.magicNumbers, Charset.defaultCharset()) + "," + this.byteOffset + Constants.RIGHT_BRACKET_ONLY;
    }
}

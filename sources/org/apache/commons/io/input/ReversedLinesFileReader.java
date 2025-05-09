package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileSystem;
import org.apache.commons.io.StandardLineSeparator;
import org.apache.commons.io.build.AbstractStreamBuilder;

/* loaded from: classes10.dex */
public class ReversedLinesFileReader implements Closeable {
    private static final int DEFAULT_BLOCK_SIZE = FileSystem.getCurrent().getBlockSize();
    private static final String EMPTY_STRING = "";
    private final int avoidNewlineSplitBufferSize;
    private final int blockSize;
    private final int byteDecrement;
    private final SeekableByteChannel channel;
    private final Charset charset;
    private FilePart currentFilePart;
    private final byte[][] newLineSequences;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    public static class Builder extends AbstractStreamBuilder<ReversedLinesFileReader, Builder> {
        public Builder() {
            setBufferSizeDefault(ReversedLinesFileReader.DEFAULT_BLOCK_SIZE);
            setBufferSize(ReversedLinesFileReader.DEFAULT_BLOCK_SIZE);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public ReversedLinesFileReader get() throws IOException {
            return new ReversedLinesFileReader(getPath(), getBufferSize(), getCharset());
        }
    }

    final class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;
        private final long no;

        private FilePart(long j, int i, byte[] bArr) throws IOException {
            this.no = j;
            int length = (bArr != null ? bArr.length : 0) + i;
            byte[] bArr2 = new byte[length];
            this.data = bArr2;
            long j2 = ReversedLinesFileReader.this.blockSize;
            if (j > 0) {
                ReversedLinesFileReader.this.channel.position((j - 1) * j2);
                if (ReversedLinesFileReader.this.channel.read(ByteBuffer.wrap(bArr2, 0, i)) != i) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (bArr != null) {
                System.arraycopy(bArr, 0, bArr2, i, bArr.length);
            }
            this.currentLastBytePos = length - 1;
            this.leftOver = null;
        }

        private void createLeftOver() {
            int i = this.currentLastBytePos + 1;
            if (i > 0) {
                this.leftOver = Arrays.copyOf(this.data, i);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] bArr, int i) {
            for (byte[] bArr2 : ReversedLinesFileReader.this.newLineSequences) {
                boolean z = true;
                for (int length = bArr2.length - 1; length >= 0; length--) {
                    int length2 = (i + length) - (bArr2.length - 1);
                    z &= length2 >= 0 && bArr[length2] == bArr2[length];
                }
                if (z) {
                    return bArr2.length;
                }
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String readLine() {
            String str;
            byte[] bArr;
            boolean z = this.no == 1;
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!z && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount <= 0) {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int i2 = i + 1;
                        int i3 = (this.currentLastBytePos - i2) + 1;
                        if (i3 < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + i3);
                        }
                        str = new String(Arrays.copyOfRange(this.data, i2, i3 + i2), ReversedLinesFileReader.this.charset);
                        this.currentLastBytePos = i - newLineMatchByteCount;
                    }
                } else {
                    break;
                }
            }
            str = null;
            if (!z || (bArr = this.leftOver) == null) {
                return str;
            }
            String str2 = new String(bArr, ReversedLinesFileReader.this.charset);
            this.leftOver = null;
            return str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            }
            long j = this.no;
            if (j > 1) {
                ReversedLinesFileReader reversedLinesFileReader = ReversedLinesFileReader.this;
                return reversedLinesFileReader.new FilePart(j - 1, reversedLinesFileReader.blockSize, this.leftOver);
            }
            byte[] bArr = this.leftOver;
            if (bArr == null) {
                return null;
            }
            throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=".concat(new String(bArr, ReversedLinesFileReader.this.charset)));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public ReversedLinesFileReader(File file) throws IOException {
        this(file, DEFAULT_BLOCK_SIZE, Charset.defaultCharset());
    }

    @Deprecated
    public ReversedLinesFileReader(File file, Charset charset) throws IOException {
        this(file.toPath(), charset);
    }

    @Deprecated
    public ReversedLinesFileReader(File file, int i, Charset charset) throws IOException {
        this(file.toPath(), i, charset);
    }

    @Deprecated
    public ReversedLinesFileReader(File file, int i, String str) throws IOException {
        this(file.toPath(), i, str);
    }

    @Deprecated
    public ReversedLinesFileReader(Path path, Charset charset) throws IOException {
        this(path, DEFAULT_BLOCK_SIZE, charset);
    }

    @Deprecated
    public ReversedLinesFileReader(Path path, int i, Charset charset) throws IOException {
        int i2;
        this.blockSize = i;
        Charset charset2 = Charsets.toCharset(charset);
        this.charset = charset2;
        if (charset2.newEncoder().maxBytesPerChar() == 1.0f || charset2 == StandardCharsets.UTF_8) {
            this.byteDecrement = 1;
        } else if (charset2 == Charset.forName("Shift_JIS") || charset2 == Charset.forName("windows-31j") || charset2 == Charset.forName("x-windows-949") || charset2 == Charset.forName("gbk") || charset2 == Charset.forName("x-windows-950")) {
            this.byteDecrement = 1;
        } else if (charset2 == StandardCharsets.UTF_16BE || charset2 == StandardCharsets.UTF_16LE) {
            this.byteDecrement = 2;
        } else {
            if (charset2 == StandardCharsets.UTF_16) {
                throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
            }
            throw new UnsupportedEncodingException("Encoding " + charset + " is not supported yet (feel free to submit a patch)");
        }
        byte[][] bArr = {StandardLineSeparator.CRLF.getBytes(charset2), StandardLineSeparator.LF.getBytes(charset2), StandardLineSeparator.CR.getBytes(charset2)};
        this.newLineSequences = bArr;
        this.avoidNewlineSplitBufferSize = bArr[0].length;
        SeekableByteChannel newByteChannel = Files.newByteChannel(path, StandardOpenOption.READ);
        this.channel = newByteChannel;
        long size = newByteChannel.size();
        this.totalByteLength = size;
        long j = i;
        int i3 = (int) (size % j);
        if (i3 > 0) {
            this.totalBlockCount = (size / j) + 1;
        } else {
            this.totalBlockCount = size / j;
            if (size > 0) {
                i2 = i;
                this.currentFilePart = new FilePart(this.totalBlockCount, i2, null);
            }
        }
        i2 = i3;
        this.currentFilePart = new FilePart(this.totalBlockCount, i2, null);
    }

    @Deprecated
    public ReversedLinesFileReader(Path path, int i, String str) throws IOException {
        this(path, i, Charsets.toCharset(str));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.channel.close();
    }

    public String readLine() throws IOException {
        String readLine = this.currentFilePart.readLine();
        while (readLine == null) {
            FilePart rollOver = this.currentFilePart.rollOver();
            this.currentFilePart = rollOver;
            if (rollOver == null) {
                break;
            }
            readLine = rollOver.readLine();
        }
        if (!"".equals(readLine) || this.trailingNewlineOfFileSkipped) {
            return readLine;
        }
        this.trailingNewlineOfFileSkipped = true;
        return readLine();
    }

    public List<String> readLines(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("lineCount < 0");
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            String readLine = readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine);
        }
        return arrayList;
    }

    public String toString(int i) throws IOException {
        List<String> readLines = readLines(i);
        Collections.reverse(readLines);
        if (readLines.isEmpty()) {
            return "";
        }
        return String.join(System.lineSeparator(), readLines) + System.lineSeparator();
    }
}

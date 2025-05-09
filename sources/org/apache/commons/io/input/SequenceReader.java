package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes10.dex */
public class SequenceReader extends Reader {
    private Reader reader;
    private final Iterator<? extends Reader> readers;

    public SequenceReader(Iterable<? extends Reader> iterable) {
        this.readers = ((Iterable) Objects.requireNonNull(iterable, "readers")).iterator();
        this.reader = (Reader) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.SequenceReader$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Reader nextReader;
                nextReader = SequenceReader.this.nextReader();
                return nextReader;
            }
        });
    }

    public SequenceReader(Reader... readerArr) {
        this(Arrays.asList(readerArr));
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        while (nextReader() != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Reader nextReader() throws IOException {
        Reader reader = this.reader;
        if (reader != null) {
            reader.close();
        }
        if (this.readers.hasNext()) {
            this.reader = this.readers.next();
        } else {
            this.reader = null;
        }
        return this.reader;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int i = -1;
        while (true) {
            Reader reader = this.reader;
            if (reader == null || (i = reader.read()) != -1) {
                break;
            }
            nextReader();
        }
        return i;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        Objects.requireNonNull(cArr, "cbuf");
        if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
        }
        int i3 = 0;
        while (true) {
            Reader reader = this.reader;
            if (reader == null) {
                break;
            }
            int read = reader.read(cArr, i, i2);
            if (read == -1) {
                nextReader();
            } else {
                i3 += read;
                i += read;
                i2 -= read;
                if (i2 <= 0) {
                    break;
                }
            }
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }
}

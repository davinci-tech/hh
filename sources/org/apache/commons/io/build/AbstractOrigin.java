package org.apache.commons.io.build;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.apache.commons.io.input.CharSequenceReader;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.commons.io.output.WriterOutputStream;

/* loaded from: classes10.dex */
public abstract class AbstractOrigin<T, B extends AbstractOrigin<T, B>> extends AbstractSupplier<T, B> {
    final T origin;

    public static class ByteArrayOrigin extends AbstractOrigin<byte[], ByteArrayOrigin> {
        public ByteArrayOrigin(byte[] bArr) {
            super(bArr);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public byte[] getByteArray() {
            return get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public InputStream getInputStream(OpenOption... openOptionArr) throws IOException {
            return new ByteArrayInputStream((byte[]) this.origin);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Reader getReader(Charset charset) throws IOException {
            return new InputStreamReader(getInputStream(new OpenOption[0]), charset);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public long size() throws IOException {
            return ((byte[]) this.origin).length;
        }
    }

    public static class CharSequenceOrigin extends AbstractOrigin<CharSequence, CharSequenceOrigin> {
        public CharSequenceOrigin(CharSequence charSequence) {
            super(charSequence);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public byte[] getByteArray() {
            return ((CharSequence) this.origin).toString().getBytes(Charset.defaultCharset());
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public CharSequence getCharSequence(Charset charset) {
            return get();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.io.build.AbstractOrigin
        public InputStream getInputStream(OpenOption... openOptionArr) throws IOException {
            return ((CharSequenceInputStream.Builder) CharSequenceInputStream.builder().setCharSequence(getCharSequence(Charset.defaultCharset()))).get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Reader getReader(Charset charset) throws IOException {
            return new CharSequenceReader(get());
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public long size() throws IOException {
            return ((CharSequence) this.origin).length();
        }
    }

    public static class FileOrigin extends AbstractOrigin<File, FileOrigin> {
        public FileOrigin(File file) {
            super(file);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public byte[] getByteArray(long j, int i) throws IOException {
            RandomAccessFile create = RandomAccessFileMode.READ_ONLY.create((File) this.origin);
            try {
                byte[] read = RandomAccessFiles.read(create, j, i);
                if (create != null) {
                    create.close();
                }
                return read;
            } catch (Throwable th) {
                if (create != null) {
                    try {
                        create.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public File getFile() {
            return get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Path getPath() {
            return get().toPath();
        }
    }

    public static class InputStreamOrigin extends AbstractOrigin<InputStream, InputStreamOrigin> {
        public InputStreamOrigin(InputStream inputStream) {
            super(inputStream);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public byte[] getByteArray() throws IOException {
            return IOUtils.toByteArray((InputStream) this.origin);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public InputStream getInputStream(OpenOption... openOptionArr) {
            return get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Reader getReader(Charset charset) throws IOException {
            return new InputStreamReader(getInputStream(new OpenOption[0]), charset);
        }
    }

    public static class OutputStreamOrigin extends AbstractOrigin<OutputStream, OutputStreamOrigin> {
        public OutputStreamOrigin(OutputStream outputStream) {
            super(outputStream);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public OutputStream getOutputStream(OpenOption... openOptionArr) {
            return get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Writer getWriter(Charset charset, OpenOption... openOptionArr) throws IOException {
            return new OutputStreamWriter((OutputStream) this.origin, charset);
        }
    }

    public static class PathOrigin extends AbstractOrigin<Path, PathOrigin> {
        public PathOrigin(Path path) {
            super(path);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public byte[] getByteArray(long j, int i) throws IOException {
            RandomAccessFile create = RandomAccessFileMode.READ_ONLY.create((Path) this.origin);
            try {
                byte[] read = RandomAccessFiles.read(create, j, i);
                if (create != null) {
                    create.close();
                }
                return read;
            } catch (Throwable th) {
                if (create != null) {
                    try {
                        create.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public File getFile() {
            return get().toFile();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Path getPath() {
            return get();
        }
    }

    public static class ReaderOrigin extends AbstractOrigin<Reader, ReaderOrigin> {
        public ReaderOrigin(Reader reader) {
            super(reader);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public byte[] getByteArray() throws IOException {
            return IOUtils.toByteArray((Reader) this.origin, Charset.defaultCharset());
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public CharSequence getCharSequence(Charset charset) throws IOException {
            return IOUtils.toString((Reader) this.origin);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.io.build.AbstractOrigin
        public InputStream getInputStream(OpenOption... openOptionArr) throws IOException {
            return ((ReaderInputStream.Builder) ReaderInputStream.builder().setReader((Reader) this.origin)).setCharset(Charset.defaultCharset()).get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Reader getReader(Charset charset) throws IOException {
            return get();
        }
    }

    public static class URIOrigin extends AbstractOrigin<URI, URIOrigin> {
        public URIOrigin(URI uri) {
            super(uri);
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public File getFile() {
            return getPath().toFile();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Path getPath() {
            return Paths.get(get());
        }
    }

    public static class WriterOrigin extends AbstractOrigin<Writer, WriterOrigin> {
        public WriterOrigin(Writer writer) {
            super(writer);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.io.build.AbstractOrigin
        public OutputStream getOutputStream(OpenOption... openOptionArr) throws IOException {
            return ((WriterOutputStream.Builder) WriterOutputStream.builder().setWriter((Writer) this.origin)).setCharset(Charset.defaultCharset()).get();
        }

        @Override // org.apache.commons.io.build.AbstractOrigin
        public Writer getWriter(Charset charset, OpenOption... openOptionArr) throws IOException {
            return get();
        }
    }

    protected AbstractOrigin(T t) {
        this.origin = (T) Objects.requireNonNull(t, "origin");
    }

    @Override // org.apache.commons.io.function.IOSupplier
    public T get() {
        return this.origin;
    }

    public byte[] getByteArray() throws IOException {
        return Files.readAllBytes(getPath());
    }

    public byte[] getByteArray(long j, int i) throws IOException {
        int i2;
        byte[] byteArray = getByteArray();
        int intExact = Math.toIntExact(j);
        if (intExact < 0 || i < 0 || (i2 = intExact + i) < 0 || i2 > byteArray.length) {
            throw new IllegalArgumentException("Couldn't read array (start: " + intExact + ", length: " + i + ", data length: " + byteArray.length + ").");
        }
        return Arrays.copyOfRange(byteArray, intExact, i2);
    }

    public CharSequence getCharSequence(Charset charset) throws IOException {
        return new String(getByteArray(), charset);
    }

    public File getFile() {
        throw new UnsupportedOperationException(String.format("%s#getFile() for %s origin %s", getSimpleName(), this.origin.getClass().getSimpleName(), this.origin));
    }

    public InputStream getInputStream(OpenOption... openOptionArr) throws IOException {
        return Files.newInputStream(getPath(), openOptionArr);
    }

    public OutputStream getOutputStream(OpenOption... openOptionArr) throws IOException {
        return Files.newOutputStream(getPath(), openOptionArr);
    }

    public Path getPath() {
        throw new UnsupportedOperationException(String.format("%s#getPath() for %s origin %s", getSimpleName(), this.origin.getClass().getSimpleName(), this.origin));
    }

    public Reader getReader(Charset charset) throws IOException {
        return Files.newBufferedReader(getPath(), charset);
    }

    private String getSimpleName() {
        return getClass().getSimpleName();
    }

    public Writer getWriter(Charset charset, OpenOption... openOptionArr) throws IOException {
        return Files.newBufferedWriter(getPath(), charset, openOptionArr);
    }

    public long size() throws IOException {
        return Files.size(getPath());
    }

    public String toString() {
        return getSimpleName() + "[" + this.origin.toString() + "]";
    }
}

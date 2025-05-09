package org.apache.commons.io.build;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractOriginSupplier;

/* loaded from: classes10.dex */
public abstract class AbstractOriginSupplier<T, B extends AbstractOriginSupplier<T, B>> extends AbstractSupplier<T, B> {
    private AbstractOrigin<?, ?> origin;

    protected static AbstractOrigin.ByteArrayOrigin newByteArrayOrigin(byte[] bArr) {
        return new AbstractOrigin.ByteArrayOrigin(bArr);
    }

    protected static AbstractOrigin.CharSequenceOrigin newCharSequenceOrigin(CharSequence charSequence) {
        return new AbstractOrigin.CharSequenceOrigin(charSequence);
    }

    public static AbstractOrigin.FileOrigin newFileOrigin(File file) {
        return new AbstractOrigin.FileOrigin(file);
    }

    public static AbstractOrigin.FileOrigin newFileOrigin(String str) {
        return new AbstractOrigin.FileOrigin(new File(str));
    }

    protected static AbstractOrigin.InputStreamOrigin newInputStreamOrigin(InputStream inputStream) {
        return new AbstractOrigin.InputStreamOrigin(inputStream);
    }

    protected static AbstractOrigin.OutputStreamOrigin newOutputStreamOrigin(OutputStream outputStream) {
        return new AbstractOrigin.OutputStreamOrigin(outputStream);
    }

    protected static AbstractOrigin.PathOrigin newPathOrigin(Path path) {
        return new AbstractOrigin.PathOrigin(path);
    }

    protected static AbstractOrigin.PathOrigin newPathOrigin(String str) {
        return new AbstractOrigin.PathOrigin(Paths.get(str, new String[0]));
    }

    protected static AbstractOrigin.ReaderOrigin newReaderOrigin(Reader reader) {
        return new AbstractOrigin.ReaderOrigin(reader);
    }

    protected static AbstractOrigin.URIOrigin newURIOrigin(URI uri) {
        return new AbstractOrigin.URIOrigin(uri);
    }

    protected static AbstractOrigin.WriterOrigin newWriterOrigin(Writer writer) {
        return new AbstractOrigin.WriterOrigin(writer);
    }

    protected AbstractOrigin<?, ?> checkOrigin() {
        AbstractOrigin<?, ?> abstractOrigin = this.origin;
        if (abstractOrigin != null) {
            return abstractOrigin;
        }
        throw new IllegalStateException("origin == null");
    }

    protected AbstractOrigin<?, ?> getOrigin() {
        return this.origin;
    }

    protected boolean hasOrigin() {
        return this.origin != null;
    }

    public B setByteArray(byte[] bArr) {
        return setOrigin(newByteArrayOrigin(bArr));
    }

    public B setCharSequence(CharSequence charSequence) {
        return setOrigin(newCharSequenceOrigin(charSequence));
    }

    public B setFile(File file) {
        return setOrigin(newFileOrigin(file));
    }

    public B setFile(String str) {
        return setOrigin(newFileOrigin(str));
    }

    public B setInputStream(InputStream inputStream) {
        return setOrigin(newInputStreamOrigin(inputStream));
    }

    public B setOrigin(AbstractOrigin<?, ?> abstractOrigin) {
        this.origin = abstractOrigin;
        return (B) asThis();
    }

    public B setOutputStream(OutputStream outputStream) {
        return setOrigin(newOutputStreamOrigin(outputStream));
    }

    public B setPath(Path path) {
        return setOrigin(newPathOrigin(path));
    }

    public B setPath(String str) {
        return setOrigin(newPathOrigin(str));
    }

    public B setReader(Reader reader) {
        return setOrigin(newReaderOrigin(reader));
    }

    public B setURI(URI uri) {
        return setOrigin(newURIOrigin(uri));
    }

    public B setWriter(Writer writer) {
        return setOrigin(newWriterOrigin(writer));
    }
}

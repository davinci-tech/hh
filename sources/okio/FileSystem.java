package okio;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.operation.ble.BleConstants;
import defpackage.ued;
import defpackage.uhx;
import defpackage.uhy;
import defpackage.uib;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import okio.Path;
import okio.internal.ResourceFileSystem;
import org.eclipse.californium.elements.util.JceNames;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0006J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0006J\u001a\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\bH&J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0006J\u001a\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH&J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0006J\u001a\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0006J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a2\u0006\u0010\u0011\u001a\u00020\u0006H&J\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001a2\u0006\u0010\u0011\u001a\u00020\u0006H&J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\u0006\u0010\u0011\u001a\u00020\u0006J \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\bH\u0016J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u0006J\u0012\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u000e\u0010$\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u0006J$\u0010$\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\bH&J:\u0010%\u001a\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u0002H&0(¢\u0006\u0002\b*H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020\u0006H&JD\u0010/\u001a\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\b2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u0002H&0(¢\u0006\u0002\b*H\u0087\bø\u0001\u0000¢\u0006\u0004\b2\u00103\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00065"}, d2 = {"Lokio/FileSystem;", "", "()V", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", BleConstants.KEY_PATH, "copy", "createDirectories", "dir", "mustCreate", "createDirectory", "createSymlink", "delete", "deleteRecursively", "fileOrDirectory", "exists", "list", "", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "metadata", "Lokio/FileMetadata;", "metadataOrNull", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "read", ExifInterface.GPS_DIRECTION_TRUE, "readerAction", "Lkotlin/Function1;", "Lokio/BufferedSource;", "Lkotlin/ExtensionFunctionType;", "-read", "(Lokio/Path;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sink", "Lokio/Source;", "write", "writerAction", "Lokio/BufferedSink;", "-write", "(Lokio/Path;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public abstract class FileSystem {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final FileSystem RESOURCES;
    public static final FileSystem SYSTEM;
    public static final Path SYSTEM_TEMPORARY_DIRECTORY;

    public abstract Sink appendingSink(Path file, boolean mustExist) throws IOException;

    public abstract void atomicMove(Path source, Path target) throws IOException;

    public abstract Path canonicalize(Path path) throws IOException;

    public abstract void createDirectory(Path dir, boolean mustCreate) throws IOException;

    public abstract void createSymlink(Path source, Path target) throws IOException;

    public abstract void delete(Path path, boolean mustExist) throws IOException;

    public abstract List<Path> list(Path dir) throws IOException;

    public abstract List<Path> listOrNull(Path dir);

    public abstract FileMetadata metadataOrNull(Path path) throws IOException;

    public abstract FileHandle openReadOnly(Path file) throws IOException;

    public abstract FileHandle openReadWrite(Path file, boolean mustCreate, boolean mustExist) throws IOException;

    public abstract Sink sink(Path file, boolean mustCreate) throws IOException;

    public abstract Source source(Path file) throws IOException;

    public final FileMetadata metadata(Path path) throws IOException {
        uhy.e((Object) path, "");
        return okio.internal.FileSystem.commonMetadata(this, path);
    }

    public final boolean exists(Path path) throws IOException {
        uhy.e((Object) path, "");
        return okio.internal.FileSystem.commonExists(this, path);
    }

    public static /* synthetic */ Sequence listRecursively$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listRecursively");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return fileSystem.listRecursively(path, z);
    }

    public Sequence<Path> listRecursively(Path dir, boolean followSymlinks) {
        uhy.e((Object) dir, "");
        return okio.internal.FileSystem.commonListRecursively(this, dir, followSymlinks);
    }

    public final Sequence<Path> listRecursively(Path dir) {
        uhy.e((Object) dir, "");
        return listRecursively(dir, false);
    }

    public static /* synthetic */ FileHandle openReadWrite$default(FileSystem fileSystem, Path path, boolean z, boolean z2, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openReadWrite");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return fileSystem.openReadWrite(path, z, z2);
    }

    public final FileHandle openReadWrite(Path file) throws IOException {
        uhy.e((Object) file, "");
        return openReadWrite(file, false, false);
    }

    public static /* synthetic */ Sink sink$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return fileSystem.sink(path, z);
    }

    public final Sink sink(Path file) throws IOException {
        uhy.e((Object) file, "");
        return sink(file, false);
    }

    /* renamed from: -write$default, reason: not valid java name */
    public static /* synthetic */ Object m1128write$default(FileSystem fileSystem, Path path, boolean z, Function1 function1, int i, Object obj) throws IOException {
        Object obj2;
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            uhy.e((Object) path, "");
            uhy.e((Object) function1, "");
            BufferedSink buffer = Okio.buffer(fileSystem.sink(path, z));
            Throwable th = null;
            try {
                obj2 = function1.invoke(buffer);
                uhx.a(1);
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                uhx.b(1);
            } catch (Throwable th3) {
                uhx.a(1);
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th4) {
                        ued.c(th3, th4);
                    }
                }
                uhx.b(1);
                obj2 = null;
                th = th3;
            }
            if (th != null) {
                throw th;
            }
            uhy.d(obj2);
            return obj2;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
    }

    public static /* synthetic */ Sink appendingSink$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendingSink");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return fileSystem.appendingSink(path, z);
    }

    public final Sink appendingSink(Path file) throws IOException {
        uhy.e((Object) file, "");
        return appendingSink(file, false);
    }

    public static /* synthetic */ void createDirectory$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectory");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.createDirectory(path, z);
    }

    public final void createDirectory(Path dir) throws IOException {
        uhy.e((Object) dir, "");
        createDirectory(dir, false);
    }

    public static /* synthetic */ void createDirectories$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectories");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.createDirectories(path, z);
    }

    public final void createDirectories(Path dir, boolean mustCreate) throws IOException {
        uhy.e((Object) dir, "");
        okio.internal.FileSystem.commonCreateDirectories(this, dir, mustCreate);
    }

    public final void createDirectories(Path dir) throws IOException {
        uhy.e((Object) dir, "");
        createDirectories(dir, false);
    }

    public void copy(Path source, Path target) throws IOException {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        okio.internal.FileSystem.commonCopy(this, source, target);
    }

    public static /* synthetic */ void delete$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.delete(path, z);
    }

    public final void delete(Path path) throws IOException {
        uhy.e((Object) path, "");
        delete(path, false);
    }

    public static /* synthetic */ void deleteRecursively$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteRecursively");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.deleteRecursively(path, z);
    }

    public void deleteRecursively(Path fileOrDirectory, boolean mustExist) throws IOException {
        uhy.e((Object) fileOrDirectory, "");
        okio.internal.FileSystem.commonDeleteRecursively(this, fileOrDirectory, mustExist);
    }

    public final void deleteRecursively(Path fileOrDirectory) throws IOException {
        uhy.e((Object) fileOrDirectory, "");
        deleteRecursively(fileOrDirectory, false);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\b\u001a\u00020\u0004*\u00020\tH\u0007¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lokio/FileSystem$Companion;", "", "()V", "RESOURCES", "Lokio/FileSystem;", JceNames.JCE_PROVIDER_SYSTEM, "SYSTEM_TEMPORARY_DIRECTORY", "Lokio/Path;", "asOkioFileSystem", "Ljava/nio/file/FileSystem;", "get", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final FileSystem get(java.nio.file.FileSystem fileSystem) {
            uhy.e((Object) fileSystem, "");
            return new NioFileSystemWrappingFileSystem(fileSystem);
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }

    static {
        NioSystemFileSystem jvmSystemFileSystem;
        try {
            Class.forName("java.nio.file.Files");
            jvmSystemFileSystem = new NioSystemFileSystem();
        } catch (ClassNotFoundException unused) {
            jvmSystemFileSystem = new JvmSystemFileSystem();
        }
        SYSTEM = jvmSystemFileSystem;
        Path.Companion companion = Path.INSTANCE;
        String property = System.getProperty("java.io.tmpdir");
        uhy.a(property, "");
        SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(companion, property, false, 1, (Object) null);
        ClassLoader classLoader = ResourceFileSystem.class.getClassLoader();
        uhy.a(classLoader, "");
        RESOURCES = new ResourceFileSystem(classLoader, false, null, 4, null);
    }

    /* renamed from: -read, reason: not valid java name */
    public final <T> T m1129read(Path file, Function1<? super BufferedSource, ? extends T> readerAction) throws IOException {
        T t;
        uhy.e((Object) file, "");
        uhy.e((Object) readerAction, "");
        BufferedSource buffer = Okio.buffer(source(file));
        Throwable th = null;
        try {
            t = readerAction.invoke(buffer);
            uhx.a(1);
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            uhx.b(1);
        } catch (Throwable th3) {
            uhx.a(1);
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Throwable th4) {
                    ued.c(th3, th4);
                }
            }
            uhx.b(1);
            th = th3;
            t = null;
        }
        if (th != null) {
            throw th;
        }
        uhy.d(t);
        return t;
    }

    /* renamed from: -write, reason: not valid java name */
    public final <T> T m1130write(Path file, boolean mustCreate, Function1<? super BufferedSink, ? extends T> writerAction) throws IOException {
        T t;
        uhy.e((Object) file, "");
        uhy.e((Object) writerAction, "");
        BufferedSink buffer = Okio.buffer(sink(file, mustCreate));
        Throwable th = null;
        try {
            t = writerAction.invoke(buffer);
            uhx.a(1);
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            uhx.b(1);
        } catch (Throwable th3) {
            uhx.a(1);
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (Throwable th4) {
                    ued.c(th3, th4);
                }
            }
            uhx.b(1);
            th = th3;
            t = null;
        }
        if (th != null) {
            throw th;
        }
        uhy.d(t);
        return t;
    }

    @JvmStatic
    public static final FileSystem get(java.nio.file.FileSystem fileSystem) {
        return INSTANCE.get(fileSystem);
    }
}

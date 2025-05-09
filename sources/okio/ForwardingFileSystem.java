package okio;

import com.huawei.hms.network.embedded.g4;
import com.huawei.operation.ble.BleConstants;
import defpackage.ufe;
import defpackage.uhy;
import defpackage.uie;
import defpackage.ujh;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00172\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u0018\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001a2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\nH\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0010\u001a\u00020\bH\u0016J \u0010\u001e\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0016J\u0018\u0010\"\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010%\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010&\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010(\u001a\u00020 H\u0016R\u0013\u0010\u0002\u001a\u00020\u00018\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0004¨\u0006)"}, d2 = {"Lokio/ForwardingFileSystem;", "Lokio/FileSystem;", "delegate", "(Lokio/FileSystem;)V", "()Lokio/FileSystem;", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", BleConstants.KEY_PATH, "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "metadataOrNull", "Lokio/FileMetadata;", "onPathParameter", "functionName", "", "parameterName", "onPathResult", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "toString", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public abstract class ForwardingFileSystem extends FileSystem {
    private final FileSystem delegate;

    /* renamed from: delegate, reason: from getter */
    public final FileSystem getDelegate() {
        return this.delegate;
    }

    public ForwardingFileSystem(FileSystem fileSystem) {
        uhy.e((Object) fileSystem, "");
        this.delegate = fileSystem;
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path path) throws IOException {
        uhy.e((Object) path, "");
        return onPathResult(this.delegate.canonicalize(onPathParameter(path, "canonicalize", BleConstants.KEY_PATH)), "canonicalize");
    }

    @Override // okio.FileSystem
    public FileMetadata metadataOrNull(Path path) throws IOException {
        FileMetadata copy;
        uhy.e((Object) path, "");
        FileMetadata metadataOrNull = this.delegate.metadataOrNull(onPathParameter(path, "metadataOrNull", BleConstants.KEY_PATH));
        if (metadataOrNull == null) {
            return null;
        }
        if (metadataOrNull.getSymlinkTarget() == null) {
            return metadataOrNull;
        }
        copy = metadataOrNull.copy((r18 & 1) != 0 ? metadataOrNull.isRegularFile : false, (r18 & 2) != 0 ? metadataOrNull.isDirectory : false, (r18 & 4) != 0 ? metadataOrNull.symlinkTarget : onPathResult(metadataOrNull.getSymlinkTarget(), "metadataOrNull"), (r18 & 8) != 0 ? metadataOrNull.size : null, (r18 & 16) != 0 ? metadataOrNull.createdAtMillis : null, (r18 & 32) != 0 ? metadataOrNull.lastModifiedAtMillis : null, (r18 & 64) != 0 ? metadataOrNull.lastAccessedAtMillis : null, (r18 & 128) != 0 ? metadataOrNull.extras : null);
        return copy;
    }

    @Override // okio.FileSystem
    public List<Path> list(Path dir) throws IOException {
        uhy.e((Object) dir, "");
        List<Path> list = this.delegate.list(onPathParameter(dir, "list", "dir"));
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult((Path) it.next(), "list"));
        }
        ArrayList arrayList2 = arrayList;
        ufe.c((List) arrayList2);
        return arrayList2;
    }

    @Override // okio.FileSystem
    public List<Path> listOrNull(Path dir) {
        uhy.e((Object) dir, "");
        List<Path> listOrNull = this.delegate.listOrNull(onPathParameter(dir, "listOrNull", "dir"));
        if (listOrNull == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listOrNull.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult((Path) it.next(), "listOrNull"));
        }
        ArrayList arrayList2 = arrayList;
        ufe.c((List) arrayList2);
        return arrayList2;
    }

    @Override // okio.FileSystem
    public Sequence<Path> listRecursively(Path dir, boolean followSymlinks) {
        uhy.e((Object) dir, "");
        return ujh.b((Sequence) this.delegate.listRecursively(onPathParameter(dir, "listRecursively", "dir"), followSymlinks), (Function1) new Function1<Path, Path>() { // from class: okio.ForwardingFileSystem$listRecursively$1
            @Override // kotlin.jvm.functions.Function1
            public final Path invoke(Path path) {
                uhy.e((Object) path, "");
                return ForwardingFileSystem.this.onPathResult(path, "listRecursively");
            }

            {
                super(1);
            }
        });
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path file) throws IOException {
        uhy.e((Object) file, "");
        return this.delegate.openReadOnly(onPathParameter(file, "openReadOnly", "file"));
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path file, boolean mustCreate, boolean mustExist) throws IOException {
        uhy.e((Object) file, "");
        return this.delegate.openReadWrite(onPathParameter(file, "openReadWrite", "file"), mustCreate, mustExist);
    }

    @Override // okio.FileSystem
    public Source source(Path file) throws IOException {
        uhy.e((Object) file, "");
        return this.delegate.source(onPathParameter(file, "source", "file"));
    }

    @Override // okio.FileSystem
    public Sink sink(Path file, boolean mustCreate) throws IOException {
        uhy.e((Object) file, "");
        return this.delegate.sink(onPathParameter(file, "sink", "file"), mustCreate);
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path file, boolean mustExist) throws IOException {
        uhy.e((Object) file, "");
        return this.delegate.appendingSink(onPathParameter(file, "appendingSink", "file"), mustExist);
    }

    @Override // okio.FileSystem
    public void createDirectory(Path dir, boolean mustCreate) throws IOException {
        uhy.e((Object) dir, "");
        this.delegate.createDirectory(onPathParameter(dir, "createDirectory", "dir"), mustCreate);
    }

    @Override // okio.FileSystem
    public void atomicMove(Path source, Path target) throws IOException {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        this.delegate.atomicMove(onPathParameter(source, "atomicMove", "source"), onPathParameter(target, "atomicMove", "target"));
    }

    @Override // okio.FileSystem
    public void delete(Path path, boolean mustExist) throws IOException {
        uhy.e((Object) path, "");
        this.delegate.delete(onPathParameter(path, "delete", BleConstants.KEY_PATH), mustExist);
    }

    @Override // okio.FileSystem
    public void createSymlink(Path source, Path target) throws IOException {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        this.delegate.createSymlink(onPathParameter(source, "createSymlink", "source"), onPathParameter(target, "createSymlink", "target"));
    }

    public String toString() {
        return uie.d(getClass()).getSimpleName() + g4.k + this.delegate + g4.l;
    }

    public Path onPathResult(Path path, String functionName) {
        uhy.e((Object) path, "");
        uhy.e((Object) functionName, "");
        return path;
    }

    public Path onPathParameter(Path path, String functionName, String parameterName) {
        uhy.e((Object) path, "");
        uhy.e((Object) functionName, "");
        uhy.e((Object) parameterName, "");
        return path;
    }
}

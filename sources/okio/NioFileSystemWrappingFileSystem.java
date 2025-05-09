package okio;

import com.huawei.operation.ble.BleConstants;
import defpackage.ufe;
import defpackage.uhp;
import defpackage.uhy;
import defpackage.uie;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import okio.Path;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00172\u0006\u0010\u0012\u001a\u00020\bH\u0016J \u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\nH\u0002J\u0018\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00172\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010!\u001a\u00020\"H\u0016J\f\u0010#\u001a\u00020$*\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lokio/NioFileSystemWrappingFileSystem;", "Lokio/NioSystemFileSystem;", "nioFileSystem", "Ljava/nio/file/FileSystem;", "(Ljava/nio/file/FileSystem;)V", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", BleConstants.KEY_PATH, "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "throwOnFailure", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "toString", "", "resolve", "Ljava/nio/file/Path;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class NioFileSystemWrappingFileSystem extends NioSystemFileSystem {
    private final java.nio.file.FileSystem nioFileSystem;

    public NioFileSystemWrappingFileSystem(java.nio.file.FileSystem fileSystem) {
        uhy.e((Object) fileSystem, "");
        this.nioFileSystem = fileSystem;
    }

    private final java.nio.file.Path resolve(Path path) {
        java.nio.file.Path path2 = this.nioFileSystem.getPath(path.toString(), new String[0]);
        uhy.a(path2, "");
        return path2;
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Path canonicalize(Path path) {
        uhy.e((Object) path, "");
        try {
            Path.Companion companion = Path.INSTANCE;
            java.nio.file.Path realPath = resolve(path).toRealPath(new LinkOption[0]);
            uhy.a(realPath, "");
            return Path.Companion.get$default(companion, realPath, false, 1, (Object) null);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException("no such file: " + path);
        }
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem, okio.FileSystem
    public FileMetadata metadataOrNull(Path path) {
        uhy.e((Object) path, "");
        return metadataOrNull(resolve(path));
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public List<Path> list(Path dir) {
        uhy.e((Object) dir, "");
        List<Path> list = list(dir, true);
        uhy.d(list);
        return list;
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public List<Path> listOrNull(Path dir) {
        uhy.e((Object) dir, "");
        return list(dir, false);
    }

    private final List<Path> list(Path dir, boolean throwOnFailure) {
        java.nio.file.Path resolve = resolve(dir);
        try {
            List c = uhp.c(resolve, null, 1, null);
            ArrayList arrayList = new ArrayList();
            Iterator it = c.iterator();
            while (it.hasNext()) {
                arrayList.add(Path.Companion.get$default(Path.INSTANCE, (java.nio.file.Path) it.next(), false, 1, (Object) null));
            }
            ArrayList arrayList2 = arrayList;
            ufe.c((List) arrayList2);
            return arrayList2;
        } catch (Exception unused) {
            if (!throwOnFailure) {
                return null;
            }
            if (Files.exists(resolve, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                throw new IOException("failed to list " + dir);
            }
            throw new FileNotFoundException("no such file: " + dir);
        }
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public FileHandle openReadOnly(Path file) {
        uhy.e((Object) file, "");
        try {
            FileChannel open = FileChannel.open(resolve(file), StandardOpenOption.READ);
            uhy.d(open);
            return new NioFileSystemFileHandle(false, open);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException("no such file: " + file);
        }
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public FileHandle openReadWrite(Path file, boolean mustCreate, boolean mustExist) {
        uhy.e((Object) file, "");
        if (mustCreate && mustExist) {
            throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.".toString());
        }
        List a2 = ufe.a();
        a2.add(StandardOpenOption.READ);
        a2.add(StandardOpenOption.WRITE);
        if (mustCreate) {
            a2.add(StandardOpenOption.CREATE_NEW);
        } else if (!mustExist) {
            a2.add(StandardOpenOption.CREATE);
        }
        List d = ufe.d(a2);
        try {
            java.nio.file.Path resolve = resolve(file);
            StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) d.toArray(new StandardOpenOption[0]);
            FileChannel open = FileChannel.open(resolve, (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length));
            uhy.d(open);
            return new NioFileSystemFileHandle(true, open);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException("no such file: " + file);
        }
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Source source(Path file) {
        uhy.e((Object) file, "");
        try {
            InputStream newInputStream = Files.newInputStream(resolve(file), (OpenOption[]) Arrays.copyOf(new OpenOption[0], 0));
            uhy.a(newInputStream, "");
            return Okio.source(newInputStream);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException("no such file: " + file);
        }
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Sink sink(Path file, boolean mustCreate) {
        uhy.e((Object) file, "");
        List a2 = ufe.a();
        if (mustCreate) {
            a2.add(StandardOpenOption.CREATE_NEW);
        }
        List d = ufe.d(a2);
        try {
            java.nio.file.Path resolve = resolve(file);
            StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) d.toArray(new StandardOpenOption[0]);
            OpenOption[] openOptionArr = (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length);
            OutputStream newOutputStream = Files.newOutputStream(resolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            uhy.a(newOutputStream, "");
            return Okio.sink(newOutputStream);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException("no such file: " + file);
        }
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public Sink appendingSink(Path file, boolean mustExist) {
        uhy.e((Object) file, "");
        List a2 = ufe.a();
        a2.add(StandardOpenOption.APPEND);
        if (!mustExist) {
            a2.add(StandardOpenOption.CREATE);
        }
        List d = ufe.d(a2);
        java.nio.file.Path resolve = resolve(file);
        StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) d.toArray(new StandardOpenOption[0]);
        OpenOption[] openOptionArr = (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length);
        OutputStream newOutputStream = Files.newOutputStream(resolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        uhy.a(newOutputStream, "");
        return Okio.sink(newOutputStream);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
    
        if (r1.getIsDirectory() == true) goto L8;
     */
    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void createDirectory(okio.Path r5, boolean r6) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            defpackage.uhy.e(r5, r0)
            okio.FileMetadata r1 = r4.metadataOrNull(r5)
            r2 = 0
            if (r1 == 0) goto L14
            boolean r1 = r1.getIsDirectory()
            r3 = 1
            if (r1 != r3) goto L14
            goto L15
        L14:
            r3 = r2
        L15:
            if (r3 == 0) goto L31
            if (r6 != 0) goto L1a
            goto L31
        L1a:
            java.io.IOException r6 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r5 = " already exists."
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        L31:
            java.nio.file.Path r6 = r4.resolve(r5)     // Catch: java.io.IOException -> L45
            java.nio.file.attribute.FileAttribute[] r1 = new java.nio.file.attribute.FileAttribute[r2]     // Catch: java.io.IOException -> L45
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)     // Catch: java.io.IOException -> L45
            java.nio.file.attribute.FileAttribute[] r1 = (java.nio.file.attribute.FileAttribute[]) r1     // Catch: java.io.IOException -> L45
            java.nio.file.Path r6 = java.nio.file.Files.createDirectory(r6, r1)     // Catch: java.io.IOException -> L45
            defpackage.uhy.a(r6, r0)     // Catch: java.io.IOException -> L45
            return
        L45:
            r6 = move-exception
            if (r3 == 0) goto L49
            return
        L49:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "failed to create directory: "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            r0.<init>(r5, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.NioFileSystemWrappingFileSystem.createDirectory(okio.Path, boolean):void");
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem, okio.FileSystem
    public void atomicMove(Path source, Path target) {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        try {
            uhy.a(Files.move(resolve(source), resolve(target), (CopyOption[]) Arrays.copyOf(new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING}, 2)), "");
        } catch (UnsupportedOperationException unused) {
            throw new IOException("atomic move not supported");
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    @Override // okio.JvmSystemFileSystem, okio.FileSystem
    public void delete(Path path, boolean mustExist) {
        uhy.e((Object) path, "");
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        java.nio.file.Path resolve = resolve(path);
        try {
            Files.delete(resolve);
        } catch (NoSuchFileException unused) {
            if (mustExist) {
                throw new FileNotFoundException("no such file: " + path);
            }
        } catch (IOException unused2) {
            if (Files.exists(resolve, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                throw new IOException("failed to delete " + path);
            }
        }
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem, okio.FileSystem
    public void createSymlink(Path source, Path target) {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        uhy.a(Files.createSymbolicLink(resolve(source), resolve(target), (FileAttribute[]) Arrays.copyOf(new FileAttribute[0], 0)), "");
    }

    @Override // okio.NioSystemFileSystem, okio.JvmSystemFileSystem
    public String toString() {
        String simpleName = uie.d(this.nioFileSystem.getClass()).getSimpleName();
        uhy.d((Object) simpleName);
        return simpleName;
    }
}

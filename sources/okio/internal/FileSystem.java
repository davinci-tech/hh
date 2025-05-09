package okio.internal;

import com.huawei.operation.ble.BleConstants;
import defpackage.ued;
import defpackage.uew;
import defpackage.uhy;
import defpackage.ujh;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.Okio;
import okio.Path;
import okio.Source;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aI\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001c\u0010\r\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nH\u0000\u001a\u0014\u0010\u0016\u001a\u00020\n*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u0019\u001a\u00020\u001a*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0003*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"collectRecursively", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "stack", "Lkotlin/collections/ArrayDeque;", BleConstants.KEY_PATH, "followSymlinks", "", "postorder", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "commonCopy", "source", "target", "commonCreateDirectories", "dir", "mustCreate", "commonDeleteRecursively", "fileOrDirectory", "mustExist", "commonExists", "commonListRecursively", "Lkotlin/sequences/Sequence;", "commonMetadata", "Lokio/FileMetadata;", "symlinkTarget", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-FileSystem, reason: invalid class name */
/* loaded from: classes10.dex */
public final class FileSystem {
    public static final FileMetadata commonMetadata(okio.FileSystem fileSystem, Path path) throws IOException {
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        FileMetadata metadataOrNull = fileSystem.metadataOrNull(path);
        if (metadataOrNull != null) {
            return metadataOrNull;
        }
        throw new FileNotFoundException("no such file: " + path);
    }

    public static final boolean commonExists(okio.FileSystem fileSystem, Path path) throws IOException {
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        return fileSystem.metadataOrNull(path) != null;
    }

    public static final void commonCreateDirectories(okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        uew uewVar = new uew();
        for (Path path2 = path; path2 != null && !fileSystem.exists(path2); path2 = path2.parent()) {
            uewVar.e((uew) path2);
        }
        if (z && uewVar.isEmpty()) {
            throw new IOException(path + " already exists.");
        }
        Iterator it = uewVar.iterator();
        while (it.hasNext()) {
            fileSystem.createDirectory((Path) it.next());
        }
    }

    public static final void commonDeleteRecursively(okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        Iterator it = ujh.c(new FileSystem$commonDeleteRecursively$sequence$1(fileSystem, path, null)).iterator();
        while (it.hasNext()) {
            fileSystem.delete((Path) it.next(), z && !it.hasNext());
        }
    }

    public static final Sequence<Path> commonListRecursively(okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        return ujh.c(new FileSystem$commonListRecursively$1(path, fileSystem, z, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x00de, code lost:
    
        if (r0 != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e0, code lost:
    
        if (r9 != 0) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e2, code lost:
    
        r6.c((defpackage.uew<okio.Path>) r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e9, code lost:
    
        r9 = r6;
        r6 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r3.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0126, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object collectRecursively(kotlin.sequences.SequenceScope<? super okio.Path> r15, okio.FileSystem r16, defpackage.uew<okio.Path> r17, okio.Path r18, boolean r19, boolean r20, kotlin.coroutines.Continuation<? super defpackage.ueu> r21) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.FileSystem.collectRecursively(kotlin.sequences.SequenceScope, okio.FileSystem, uew, okio.Path, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Path symlinkTarget(okio.FileSystem fileSystem, Path path) throws IOException {
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        Path symlinkTarget = fileSystem.metadata(path).getSymlinkTarget();
        if (symlinkTarget == null) {
            return null;
        }
        Path parent = path.parent();
        uhy.d(parent);
        return parent.resolve(symlinkTarget);
    }

    public static final void commonCopy(okio.FileSystem fileSystem, Path path, Path path2) throws IOException {
        Long l;
        Throwable th;
        Long l2;
        uhy.e((Object) fileSystem, "");
        uhy.e((Object) path, "");
        uhy.e((Object) path2, "");
        Source source = fileSystem.source(path);
        Throwable th2 = null;
        try {
            Source source2 = source;
            BufferedSink buffer = Okio.buffer(fileSystem.sink(path2));
            try {
                l2 = Long.valueOf(buffer.writeAll(source2));
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                th = null;
            } catch (Throwable th4) {
                if (buffer != null) {
                    try {
                        buffer.close();
                    } catch (Throwable th5) {
                        ued.c(th4, th5);
                    }
                }
                th = th4;
                l2 = null;
            }
        } catch (Throwable th6) {
            if (source != null) {
                try {
                    source.close();
                } catch (Throwable th7) {
                    ued.c(th6, th7);
                }
            }
            th2 = th6;
            l = null;
        }
        if (th != null) {
            throw th;
        }
        uhy.d(l2);
        l = Long.valueOf(l2.longValue());
        if (source != null) {
            try {
                source.close();
            } catch (Throwable th8) {
                th2 = th8;
            }
        }
        if (th2 != null) {
            throw th2;
        }
        uhy.d(l);
    }
}

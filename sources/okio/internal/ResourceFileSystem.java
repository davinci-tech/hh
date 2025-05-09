package okio.internal;

import com.huawei.operation.ble.BleConstants;
import defpackage.to;
import defpackage.ueh;
import defpackage.ueo;
import defpackage.ufe;
import defpackage.uhy;
import defpackage.uib;
import defpackage.ujy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;
import okio.Source;
import okio.internal.ResourceFileSystem;
import org.apache.commons.io.IOUtils;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 /2\u00020\u0001:\u0001/B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0016J\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u0018\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0018\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\u0018\u0010!\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\u0019\u001a\u00020\u000bH\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J \u0010&\u001a\u00020%2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J\u0010\u0010\u0016\u001a\u00020(2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016J\u001e\u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n0\t*\u00020\u0003H\u0002J\u001a\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n*\u00020+H\u0002J\u001a\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n*\u00020+H\u0002J\f\u0010-\u001a\u00020.*\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lokio/internal/ResourceFileSystem;", "Lokio/FileSystem;", "classLoader", "Ljava/lang/ClassLoader;", "indexEagerly", "", "systemFileSystem", "(Ljava/lang/ClassLoader;ZLokio/FileSystem;)V", "roots", "", "Lkotlin/Pair;", "Lokio/Path;", "getRoots", "()Ljava/util/List;", "roots$delegate", "Lkotlin/Lazy;", "appendingSink", "Lokio/Sink;", "file", "mustExist", "atomicMove", "", "source", "target", "canonicalize", BleConstants.KEY_PATH, "canonicalizeInternal", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "toClasspathRoots", "toFileRoot", "Ljava/net/URL;", "toJarRoot", "toRelativePath", "", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ResourceFileSystem extends FileSystem {
    private static final Companion Companion = new Companion(null);
    private static final Path ROOT = Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null);
    private final ClassLoader classLoader;

    /* renamed from: roots$delegate, reason: from kotlin metadata */
    private final Lazy roots;
    private final FileSystem systemFileSystem;

    public /* synthetic */ ResourceFileSystem(ClassLoader classLoader, boolean z, FileSystem fileSystem, int i, uib uibVar) {
        this(classLoader, z, (i & 4) != 0 ? FileSystem.SYSTEM : fileSystem);
    }

    public ResourceFileSystem(ClassLoader classLoader, boolean z, FileSystem fileSystem) {
        uhy.e((Object) classLoader, "");
        uhy.e((Object) fileSystem, "");
        this.classLoader = classLoader;
        this.systemFileSystem = fileSystem;
        this.roots = ueh.a(new Function0<List<? extends ueo<? extends FileSystem, ? extends Path>>>() { // from class: okio.internal.ResourceFileSystem$roots$2
            @Override // kotlin.jvm.functions.Function0
            public final List<? extends ueo<? extends FileSystem, ? extends Path>> invoke() {
                ClassLoader classLoader2;
                List<? extends ueo<? extends FileSystem, ? extends Path>> classpathRoots;
                ResourceFileSystem resourceFileSystem = ResourceFileSystem.this;
                classLoader2 = resourceFileSystem.classLoader;
                classpathRoots = resourceFileSystem.toClasspathRoots(classLoader2);
                return classpathRoots;
            }

            {
                super(0);
            }
        });
        if (z) {
            getRoots().size();
        }
    }

    private final List<ueo<FileSystem, Path>> getRoots() {
        return (List) this.roots.getValue();
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path path) {
        uhy.e((Object) path, "");
        return canonicalizeInternal(path);
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    @Override // okio.FileSystem
    public List<Path> list(Path dir) {
        uhy.e((Object) dir, "");
        String relativePath = toRelativePath(dir);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z = false;
        for (ueo<FileSystem, Path> ueoVar : getRoots()) {
            FileSystem b = ueoVar.b();
            Path d = ueoVar.d();
            try {
                LinkedHashSet linkedHashSet2 = linkedHashSet;
                List<Path> list = b.list(d.resolve(relativePath));
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (Companion.keepPath((Path) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(ufe.d(arrayList2, 10));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList3.add(Companion.removeBase((Path) it.next(), d));
                }
                ufe.d(linkedHashSet2, arrayList3);
                z = true;
            } catch (IOException unused) {
            }
        }
        if (!z) {
            throw new FileNotFoundException("file not found: " + dir);
        }
        return ufe.g(linkedHashSet);
    }

    @Override // okio.FileSystem
    public List<Path> listOrNull(Path dir) {
        uhy.e((Object) dir, "");
        String relativePath = toRelativePath(dir);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<ueo<FileSystem, Path>> it = getRoots().iterator();
        boolean z = false;
        while (true) {
            ArrayList arrayList = null;
            if (!it.hasNext()) {
                break;
            }
            ueo<FileSystem, Path> next = it.next();
            FileSystem b = next.b();
            Path d = next.d();
            List<Path> listOrNull = b.listOrNull(d.resolve(relativePath));
            if (listOrNull != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listOrNull) {
                    if (Companion.keepPath((Path) obj)) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                ArrayList arrayList4 = new ArrayList(ufe.d(arrayList3, 10));
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    arrayList4.add(Companion.removeBase((Path) it2.next(), d));
                }
                arrayList = arrayList4;
            }
            if (arrayList != null) {
                ufe.d(linkedHashSet, arrayList);
                z = true;
            }
        }
        if (z) {
            return ufe.g(linkedHashSet);
        }
        return null;
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path file) {
        uhy.e((Object) file, "");
        if (!Companion.keepPath(file)) {
            throw new FileNotFoundException("file not found: " + file);
        }
        String relativePath = toRelativePath(file);
        for (ueo<FileSystem, Path> ueoVar : getRoots()) {
            try {
                return ueoVar.b().openReadOnly(ueoVar.d().resolve(relativePath));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException("file not found: " + file);
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path file, boolean mustCreate, boolean mustExist) {
        uhy.e((Object) file, "");
        throw new IOException("resources are not writable");
    }

    @Override // okio.FileSystem
    public FileMetadata metadataOrNull(Path path) {
        uhy.e((Object) path, "");
        if (!Companion.keepPath(path)) {
            return null;
        }
        String relativePath = toRelativePath(path);
        for (ueo<FileSystem, Path> ueoVar : getRoots()) {
            FileMetadata metadataOrNull = ueoVar.b().metadataOrNull(ueoVar.d().resolve(relativePath));
            if (metadataOrNull != null) {
                return metadataOrNull;
            }
        }
        return null;
    }

    @Override // okio.FileSystem
    public Source source(Path file) {
        Source source;
        uhy.e((Object) file, "");
        if (!Companion.keepPath(file)) {
            throw new FileNotFoundException("file not found: " + file);
        }
        Path path = ROOT;
        InputStream resourceAsStream = this.classLoader.getResourceAsStream(Path.resolve$default(path, file, false, 2, (Object) null).relativeTo(path).toString());
        if (resourceAsStream != null && (source = Okio.source(resourceAsStream)) != null) {
            return source;
        }
        throw new FileNotFoundException("file not found: " + file);
    }

    @Override // okio.FileSystem
    public Sink sink(Path file, boolean mustCreate) {
        uhy.e((Object) file, "");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path file, boolean mustExist) {
        uhy.e((Object) file, "");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void createDirectory(Path dir, boolean mustCreate) {
        uhy.e((Object) dir, "");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(Path source, Path target) {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void delete(Path path, boolean mustExist) {
        uhy.e((Object) path, "");
        throw new IOException(this + " is read-only");
    }

    @Override // okio.FileSystem
    public void createSymlink(Path source, Path target) {
        uhy.e((Object) source, "");
        uhy.e((Object) target, "");
        throw new IOException(this + " is read-only");
    }

    private final String toRelativePath(Path path) {
        return canonicalizeInternal(path).relativeTo(ROOT).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ueo<FileSystem, Path>> toClasspathRoots(ClassLoader classLoader) {
        Enumeration<URL> resources = classLoader.getResources("");
        uhy.a(resources, "");
        ArrayList<URL> list = Collections.list(resources);
        uhy.a(list, "");
        ArrayList arrayList = new ArrayList();
        for (URL url : list) {
            uhy.d(url);
            ueo<FileSystem, Path> fileRoot = toFileRoot(url);
            if (fileRoot != null) {
                arrayList.add(fileRoot);
            }
        }
        ArrayList arrayList2 = arrayList;
        Enumeration<URL> resources2 = classLoader.getResources("META-INF/MANIFEST.MF");
        uhy.a(resources2, "");
        ArrayList<URL> list2 = Collections.list(resources2);
        uhy.a(list2, "");
        ArrayList arrayList3 = new ArrayList();
        for (URL url2 : list2) {
            uhy.d(url2);
            ueo<FileSystem, Path> jarRoot = toJarRoot(url2);
            if (jarRoot != null) {
                arrayList3.add(jarRoot);
            }
        }
        return ufe.c((Collection) arrayList2, (Iterable) arrayList3);
    }

    private final ueo<FileSystem, Path> toFileRoot(URL url) {
        if (uhy.e((Object) url.getProtocol(), (Object) "file")) {
            return to.d(this.systemFileSystem, Path.Companion.get$default(Path.INSTANCE, new File(url.toURI()), false, 1, (Object) null));
        }
        return null;
    }

    private final ueo<FileSystem, Path> toJarRoot(URL url) {
        int b;
        String url2 = url.toString();
        uhy.a(url2, "");
        if (!ujy.c(url2, "jar:file:", false, 2, (Object) null) || (b = ujy.b(url2, "!", 0, false, 6, null)) == -1) {
            return null;
        }
        Path.Companion companion = Path.INSTANCE;
        String substring = url2.substring(4, b);
        uhy.a(substring, "");
        return to.d(ZipFilesKt.openZip(Path.Companion.get$default(companion, new File(URI.create(substring)), false, 1, (Object) null), this.systemFileSystem, new Function1<ZipEntry, Boolean>() { // from class: okio.internal.ResourceFileSystem$toJarRoot$zip$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ZipEntry zipEntry) {
                ResourceFileSystem.Companion companion2;
                uhy.e((Object) zipEntry, "");
                companion2 = ResourceFileSystem.Companion;
                return Boolean.valueOf(companion2.keepPath(zipEntry.getCanonicalPath()));
            }
        }), ROOT);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0012\u0010\n\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lokio/internal/ResourceFileSystem$Companion;", "", "()V", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "keepPath", "", BleConstants.KEY_PATH, "removeBase", "base", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Companion {
        private Companion() {
        }

        public final Path getROOT() {
            return ResourceFileSystem.ROOT;
        }

        public final Path removeBase(Path path, Path path2) {
            uhy.e((Object) path, "");
            uhy.e((Object) path2, "");
            return getROOT().resolve(ujy.a(ujy.b(path.toString(), path2.toString()), IOUtils.DIR_SEPARATOR_WINDOWS, '/', false, 4, (Object) null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean keepPath(Path path) {
            return !ujy.a(path.name(), ".class", true);
        }

        public /* synthetic */ Companion(uib uibVar) {
            this();
        }
    }
}

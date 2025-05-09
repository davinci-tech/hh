package org.apache.commons.io.file;

import com.huawei.operation.ble.BleConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.AccessDeniedException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.time.Duration;
import java.time.Instant;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.io.RandomAccessFiles;
import org.apache.commons.io.ThreadUtils;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOSupplier;

/* loaded from: classes7.dex */
public final class PathUtils {
    private static final OpenOption[] OPEN_OPTIONS_TRUNCATE = {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
    private static final OpenOption[] OPEN_OPTIONS_APPEND = {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
    public static final CopyOption[] EMPTY_COPY_OPTIONS = new CopyOption[0];
    public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
    public static final FileAttribute<?>[] EMPTY_FILE_ATTRIBUTE_ARRAY = new FileAttribute[0];
    public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
    public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];

    @Deprecated
    public static final LinkOption[] NOFOLLOW_LINK_OPTION_ARRAY = {LinkOption.NOFOLLOW_LINKS};
    static final LinkOption NULL_LINK_OPTION = null;
    public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];
    public static final Path[] EMPTY_PATH_ARRAY = new Path[0];

    /* loaded from: classes10.dex */
    static final class RelativeSortedPaths {
        final boolean equals;
        final List<Path> relativeFileList1;
        final List<Path> relativeFileList2;

        private RelativeSortedPaths(Path path, Path path2, int i, LinkOption[] linkOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
            List<Path> list;
            List<Path> list2 = null;
            if (path == null && path2 == null) {
                this.equals = true;
            } else {
                if ((path == null) ^ (path2 == null)) {
                    this.equals = false;
                } else {
                    boolean notExists = Files.notExists(path, linkOptionArr);
                    boolean notExists2 = Files.notExists(path2, linkOptionArr);
                    if (!notExists && !notExists2) {
                        AccumulatorPathVisitor accumulate = PathUtils.accumulate(path, i, fileVisitOptionArr);
                        AccumulatorPathVisitor accumulate2 = PathUtils.accumulate(path2, i, fileVisitOptionArr);
                        if (accumulate.getDirList().size() != accumulate2.getDirList().size() || accumulate.getFileList().size() != accumulate2.getFileList().size()) {
                            this.equals = false;
                        } else if (!accumulate.relativizeDirectories(path, true, null).equals(accumulate2.relativizeDirectories(path2, true, null))) {
                            this.equals = false;
                        } else {
                            List<Path> relativizeFiles = accumulate.relativizeFiles(path, true, null);
                            List<Path> relativizeFiles2 = accumulate2.relativizeFiles(path2, true, null);
                            this.equals = relativizeFiles.equals(relativizeFiles2);
                            list2 = relativizeFiles;
                            list = relativizeFiles2;
                            this.relativeFileList1 = list2;
                            this.relativeFileList2 = list;
                        }
                    } else {
                        this.equals = notExists && notExists2;
                    }
                }
            }
            list = null;
            this.relativeFileList1 = list2;
            this.relativeFileList2 = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AccumulatorPathVisitor accumulate(Path path, int i, FileVisitOption[] fileVisitOptionArr) throws IOException {
        return (AccumulatorPathVisitor) visitFileTree(AccumulatorPathVisitor.withLongCounters(), path, toFileVisitOptionSet(fileVisitOptionArr), i);
    }

    public static Counters.PathCounters cleanDirectory(Path path) throws IOException {
        return cleanDirectory(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters cleanDirectory(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return ((CleaningPathVisitor) visitFileTree(new CleaningPathVisitor(Counters.longPathCounters(), deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    private static int compareLastModifiedTimeTo(Path path, FileTime fileTime, LinkOption... linkOptionArr) throws IOException {
        return getLastModifiedTime(path, linkOptionArr).compareTo(fileTime);
    }

    public static long copy(IOSupplier<InputStream> iOSupplier, Path path, CopyOption... copyOptionArr) throws IOException {
        InputStream inputStream = iOSupplier.get();
        try {
            long copy = Files.copy(inputStream, path, copyOptionArr);
            if (inputStream != null) {
                inputStream.close();
            }
            return copy;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Counters.PathCounters copyDirectory(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        Path absolutePath = path.toAbsolutePath();
        return ((CopyDirectoryVisitor) visitFileTree(new CopyDirectoryVisitor(Counters.longPathCounters(), absolutePath, path2, copyOptionArr), absolutePath)).getPathCounters();
    }

    public static Path copyFile(URL url, Path path, CopyOption... copyOptionArr) throws IOException {
        Objects.requireNonNull(url);
        copy(new PathUtils$$ExternalSyntheticLambda0(url), path, copyOptionArr);
        return path;
    }

    public static Path copyFileToDirectory(Path path, Path path2, CopyOption... copyOptionArr) throws IOException {
        return Files.copy(path, path2.resolve(path.getFileName()), copyOptionArr);
    }

    public static Path copyFileToDirectory(URL url, Path path, CopyOption... copyOptionArr) throws IOException {
        Path resolve = path.resolve(FilenameUtils.getName(url.getFile()));
        Objects.requireNonNull(url);
        copy(new PathUtils$$ExternalSyntheticLambda0(url), resolve, copyOptionArr);
        return resolve;
    }

    public static Counters.PathCounters countDirectory(Path path) throws IOException {
        return ((CountingPathVisitor) visitFileTree(CountingPathVisitor.withLongCounters(), path)).getPathCounters();
    }

    public static Counters.PathCounters countDirectoryAsBigInteger(Path path) throws IOException {
        return ((CountingPathVisitor) visitFileTree(CountingPathVisitor.withBigIntegerCounters(), path)).getPathCounters();
    }

    public static Path createParentDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        return createParentDirectories(path, LinkOption.NOFOLLOW_LINKS, fileAttributeArr);
    }

    public static Path createParentDirectories(Path path, LinkOption linkOption, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path parent = getParent(path);
        if (linkOption != LinkOption.NOFOLLOW_LINKS) {
            parent = readIfSymbolicLink(parent);
        }
        if (parent == null) {
            return null;
        }
        return linkOption == null ? Files.exists(parent, new LinkOption[0]) : Files.exists(parent, linkOption) ? parent : Files.createDirectories(parent, fileAttributeArr);
    }

    public static Path current() {
        return Paths.get(".", new String[0]);
    }

    public static Counters.PathCounters delete(Path path) throws IOException {
        return delete(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters delete(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS) ? deleteDirectory(path, deleteOptionArr) : deleteFile(path, deleteOptionArr);
    }

    public static Counters.PathCounters delete(Path path, LinkOption[] linkOptionArr, DeleteOption... deleteOptionArr) throws IOException {
        return Files.isDirectory(path, linkOptionArr) ? deleteDirectory(path, linkOptionArr, deleteOptionArr) : deleteFile(path, linkOptionArr, deleteOptionArr);
    }

    public static Counters.PathCounters deleteDirectory(Path path) throws IOException {
        return deleteDirectory(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters deleteDirectory(final Path path, final DeleteOption... deleteOptionArr) throws IOException {
        final LinkOption[] noFollowLinkOptionArray = noFollowLinkOptionArray();
        return (Counters.PathCounters) withPosixFileAttributes(getParent(path), noFollowLinkOptionArray, overrideReadOnly(deleteOptionArr), new IOFunction() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Counters.PathCounters pathCounters;
                pathCounters = ((DeletingPathVisitor) PathUtils.visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), noFollowLinkOptionArray, deleteOptionArr, new String[0]), path)).getPathCounters();
                return pathCounters;
            }
        });
    }

    public static Counters.PathCounters deleteDirectory(Path path, LinkOption[] linkOptionArr, DeleteOption... deleteOptionArr) throws IOException {
        return ((DeletingPathVisitor) visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), linkOptionArr, deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    public static Counters.PathCounters deleteFile(Path path) throws IOException {
        return deleteFile(path, EMPTY_DELETE_OPTION_ARRAY);
    }

    public static Counters.PathCounters deleteFile(Path path, DeleteOption... deleteOptionArr) throws IOException {
        return deleteFile(path, noFollowLinkOptionArray(), deleteOptionArr);
    }

    public static Counters.PathCounters deleteFile(Path path, LinkOption[] linkOptionArr, DeleteOption... deleteOptionArr) throws NoSuchFileException, IOException {
        if (Files.isDirectory(path, linkOptionArr)) {
            throw new NoSuchFileException(path.toString());
        }
        Counters.PathCounters longPathCounters = Counters.longPathCounters();
        long j = 0;
        long size = (!exists(path, linkOptionArr) || Files.isSymbolicLink(path)) ? 0L : Files.size(path);
        try {
            if (Files.deleteIfExists(path)) {
                longPathCounters.getFileCounter().increment();
                longPathCounters.getByteCounter().add(size);
                return longPathCounters;
            }
        } catch (AccessDeniedException unused) {
        }
        Path parent = getParent(path);
        PosixFileAttributes posixFileAttributes = null;
        try {
            if (overrideReadOnly(deleteOptionArr)) {
                posixFileAttributes = readPosixFileAttributes(parent, linkOptionArr);
                setReadOnly(path, false, linkOptionArr);
            }
            if (exists(path, linkOptionArr) && !Files.isSymbolicLink(path)) {
                j = Files.size(path);
            }
            if (Files.deleteIfExists(path)) {
                longPathCounters.getFileCounter().increment();
                longPathCounters.getByteCounter().add(j);
            }
            return longPathCounters;
        } finally {
            if (posixFileAttributes != null) {
                Files.setPosixFilePermissions(parent, posixFileAttributes.permissions());
            }
        }
    }

    public static void deleteOnExit(Path path) {
        ((File) Objects.requireNonNull(path.toFile())).deleteOnExit();
    }

    public static boolean directoryAndFileContentEquals(Path path, Path path2) throws IOException {
        return directoryAndFileContentEquals(path, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }

    public static boolean directoryAndFileContentEquals(Path path, Path path2, LinkOption[] linkOptionArr, OpenOption[] openOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
        if (path == null && path2 == null) {
            return true;
        }
        if (path == null || path2 == null) {
            return false;
        }
        if (notExists(path, new LinkOption[0]) && notExists(path2, new LinkOption[0])) {
            return true;
        }
        RelativeSortedPaths relativeSortedPaths = new RelativeSortedPaths(path, path2, Integer.MAX_VALUE, linkOptionArr, fileVisitOptionArr);
        if (!relativeSortedPaths.equals) {
            return false;
        }
        List<Path> list = relativeSortedPaths.relativeFileList1;
        List<Path> list2 = relativeSortedPaths.relativeFileList2;
        for (Path path3 : list) {
            if (Collections.binarySearch(list2, path3) <= -1) {
                throw new IllegalStateException("Unexpected mismatch.");
            }
            if (!fileContentEquals(path.resolve(path3), path2.resolve(path3), linkOptionArr, openOptionArr)) {
                return false;
            }
        }
        return true;
    }

    public static boolean directoryContentEquals(Path path, Path path2) throws IOException {
        return directoryContentEquals(path, path2, Integer.MAX_VALUE, EMPTY_LINK_OPTION_ARRAY, EMPTY_FILE_VISIT_OPTION_ARRAY);
    }

    public static boolean directoryContentEquals(Path path, Path path2, int i, LinkOption[] linkOptionArr, FileVisitOption[] fileVisitOptionArr) throws IOException {
        return new RelativeSortedPaths(path, path2, i, linkOptionArr, fileVisitOptionArr).equals;
    }

    private static boolean exists(Path path, LinkOption... linkOptionArr) {
        Objects.requireNonNull(path, BleConstants.KEY_PATH);
        return linkOptionArr != null ? Files.exists(path, linkOptionArr) : Files.exists(path, new LinkOption[0]);
    }

    public static boolean fileContentEquals(Path path, Path path2) throws IOException {
        return fileContentEquals(path, path2, EMPTY_LINK_OPTION_ARRAY, EMPTY_OPEN_OPTION_ARRAY);
    }

    public static boolean fileContentEquals(Path path, Path path2, LinkOption[] linkOptionArr, OpenOption[] openOptionArr) throws IOException {
        if (path == null && path2 == null) {
            return true;
        }
        if (path == null || path2 == null) {
            return false;
        }
        Path normalize = path.normalize();
        Path normalize2 = path2.normalize();
        boolean exists = exists(normalize, linkOptionArr);
        if (exists != exists(normalize2, linkOptionArr)) {
            return false;
        }
        if (!exists) {
            return true;
        }
        if (Files.isDirectory(normalize, linkOptionArr)) {
            throw new IOException("Can't compare directories, only files: " + normalize);
        }
        if (Files.isDirectory(normalize2, linkOptionArr)) {
            throw new IOException("Can't compare directories, only files: " + normalize2);
        }
        if (Files.size(normalize) != Files.size(normalize2)) {
            return false;
        }
        if (path.equals(path2)) {
            return true;
        }
        try {
            RandomAccessFile create = RandomAccessFileMode.READ_ONLY.create(path.toRealPath(linkOptionArr));
            try {
                RandomAccessFile create2 = RandomAccessFileMode.READ_ONLY.create(path2.toRealPath(linkOptionArr));
                try {
                    boolean contentEquals = RandomAccessFiles.contentEquals(create, create2);
                    if (create2 != null) {
                        create2.close();
                    }
                    if (create != null) {
                        create.close();
                    }
                    return contentEquals;
                } finally {
                }
            } finally {
            }
        } catch (UnsupportedOperationException unused) {
            InputStream newInputStream = Files.newInputStream(normalize, openOptionArr);
            try {
                InputStream newInputStream2 = Files.newInputStream(normalize2, openOptionArr);
                try {
                    boolean contentEquals2 = IOUtils.contentEquals(newInputStream, newInputStream2);
                    if (newInputStream2 != null) {
                        newInputStream2.close();
                    }
                    if (newInputStream != null) {
                        newInputStream.close();
                    }
                    return contentEquals2;
                } finally {
                }
            } catch (Throwable th) {
                if (newInputStream != null) {
                    try {
                        newInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    public static Path[] filter(PathFilter pathFilter, Path... pathArr) {
        Objects.requireNonNull(pathFilter, Constant.FILTER);
        if (pathArr == null) {
            return EMPTY_PATH_ARRAY;
        }
        return (Path[]) ((List) filterPaths(pathFilter, Stream.of((Object[]) pathArr), Collectors.toList())).toArray(EMPTY_PATH_ARRAY);
    }

    private static <R, A> R filterPaths(final PathFilter pathFilter, Stream<Path> stream, Collector<? super Path, A, R> collector) {
        Objects.requireNonNull(pathFilter, Constant.FILTER);
        Objects.requireNonNull(collector, "collector");
        if (stream == null) {
            return (R) Stream.empty().collect(collector);
        }
        return (R) stream.filter(new Predicate() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PathUtils.lambda$filterPaths$1(PathFilter.this, (Path) obj);
            }
        }).collect(collector);
    }

    static /* synthetic */ boolean lambda$filterPaths$1(PathFilter pathFilter, Path path) {
        if (path != null) {
            try {
                if (pathFilter.accept(path, readBasicFileAttributes(path)) == FileVisitResult.CONTINUE) {
                    return true;
                }
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public static List<AclEntry> getAclEntryList(Path path) throws IOException {
        AclFileAttributeView aclFileAttributeView = getAclFileAttributeView(path, new LinkOption[0]);
        if (aclFileAttributeView == null) {
            return null;
        }
        return aclFileAttributeView.getAcl();
    }

    public static AclFileAttributeView getAclFileAttributeView(Path path, LinkOption... linkOptionArr) {
        return (AclFileAttributeView) Files.getFileAttributeView(path, AclFileAttributeView.class, linkOptionArr);
    }

    public static String getBaseName(Path path) {
        Path fileName;
        if (path == null || (fileName = path.getFileName()) == null) {
            return null;
        }
        return FilenameUtils.removeExtension(fileName.toString());
    }

    public static DosFileAttributeView getDosFileAttributeView(Path path, LinkOption... linkOptionArr) {
        return (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, linkOptionArr);
    }

    public static String getExtension(Path path) {
        String fileNameString = getFileNameString(path);
        if (fileNameString != null) {
            return FilenameUtils.getExtension(fileNameString);
        }
        return null;
    }

    public static <R> R getFileName(Path path, Function<Path, R> function) {
        Path fileName = path != null ? path.getFileName() : null;
        if (fileName != null) {
            return function.apply(fileName);
        }
        return null;
    }

    public static String getFileNameString(Path path) {
        return (String) getFileName(path, new Function() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Path) obj).toString();
            }
        });
    }

    public static FileTime getLastModifiedFileTime(File file) throws IOException {
        return getLastModifiedFileTime(file.toPath(), null, EMPTY_LINK_OPTION_ARRAY);
    }

    public static FileTime getLastModifiedFileTime(Path path, FileTime fileTime, LinkOption... linkOptionArr) throws IOException {
        return Files.exists(path, new LinkOption[0]) ? getLastModifiedTime(path, linkOptionArr) : fileTime;
    }

    public static FileTime getLastModifiedFileTime(Path path, LinkOption... linkOptionArr) throws IOException {
        return getLastModifiedFileTime(path, null, linkOptionArr);
    }

    public static FileTime getLastModifiedFileTime(URI uri) throws IOException {
        return getLastModifiedFileTime(Paths.get(uri), null, EMPTY_LINK_OPTION_ARRAY);
    }

    public static FileTime getLastModifiedFileTime(URL url) throws IOException, URISyntaxException {
        return getLastModifiedFileTime(url.toURI());
    }

    private static FileTime getLastModifiedTime(Path path, LinkOption... linkOptionArr) throws IOException {
        return Files.getLastModifiedTime((Path) Objects.requireNonNull(path, BleConstants.KEY_PATH), linkOptionArr);
    }

    private static Path getParent(Path path) {
        if (path == null) {
            return null;
        }
        return path.getParent();
    }

    public static PosixFileAttributeView getPosixFileAttributeView(Path path, LinkOption... linkOptionArr) {
        return (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, linkOptionArr);
    }

    public static Path getTempDirectory() {
        return Paths.get(FileUtils.getTempDirectoryPath(), new String[0]);
    }

    public static boolean isDirectory(Path path, LinkOption... linkOptionArr) {
        return path != null && Files.isDirectory(path, linkOptionArr);
    }

    public static boolean isEmpty(Path path) throws IOException {
        return Files.isDirectory(path, new LinkOption[0]) ? isEmptyDirectory(path) : isEmptyFile(path);
    }

    public static boolean isEmptyDirectory(Path path) throws IOException {
        DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path);
        try {
            boolean hasNext = newDirectoryStream.iterator().hasNext();
            if (newDirectoryStream != null) {
                newDirectoryStream.close();
            }
            return !hasNext;
        } catch (Throwable th) {
            if (newDirectoryStream != null) {
                try {
                    newDirectoryStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static boolean isEmptyFile(Path path) throws IOException {
        return Files.size(path) <= 0;
    }

    public static boolean isNewer(Path path, ChronoZonedDateTime<?> chronoZonedDateTime, LinkOption... linkOptionArr) throws IOException {
        Objects.requireNonNull(chronoZonedDateTime, "czdt");
        return isNewer(path, chronoZonedDateTime.toInstant(), linkOptionArr);
    }

    public static boolean isNewer(Path path, FileTime fileTime, LinkOption... linkOptionArr) throws IOException {
        return !notExists(path, new LinkOption[0]) && compareLastModifiedTimeTo(path, fileTime, linkOptionArr) > 0;
    }

    public static boolean isNewer(Path path, Instant instant, LinkOption... linkOptionArr) throws IOException {
        return isNewer(path, FileTime.from(instant), linkOptionArr);
    }

    public static boolean isNewer(Path path, long j, LinkOption... linkOptionArr) throws IOException {
        return isNewer(path, FileTime.fromMillis(j), linkOptionArr);
    }

    public static boolean isNewer(Path path, Path path2) throws IOException {
        return isNewer(path, getLastModifiedTime(path2, new LinkOption[0]), new LinkOption[0]);
    }

    public static boolean isOlder(Path path, FileTime fileTime, LinkOption... linkOptionArr) throws IOException {
        return !notExists(path, new LinkOption[0]) && compareLastModifiedTimeTo(path, fileTime, linkOptionArr) < 0;
    }

    public static boolean isOlder(Path path, Instant instant, LinkOption... linkOptionArr) throws IOException {
        return isOlder(path, FileTime.from(instant), linkOptionArr);
    }

    public static boolean isOlder(Path path, long j, LinkOption... linkOptionArr) throws IOException {
        return isOlder(path, FileTime.fromMillis(j), linkOptionArr);
    }

    public static boolean isOlder(Path path, Path path2) throws IOException {
        return isOlder(path, getLastModifiedTime(path2, new LinkOption[0]), new LinkOption[0]);
    }

    public static boolean isPosix(Path path, LinkOption... linkOptionArr) {
        return exists(path, linkOptionArr) && readPosixFileAttributes(path, linkOptionArr) != null;
    }

    public static boolean isRegularFile(Path path, LinkOption... linkOptionArr) {
        return path != null && Files.isRegularFile(path, linkOptionArr);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path path, PathFilter pathFilter) throws IOException {
        return Files.newDirectoryStream(path, new DirectoryStreamFilter(pathFilter));
    }

    public static OutputStream newOutputStream(Path path, boolean z) throws IOException {
        return newOutputStream(path, EMPTY_LINK_OPTION_ARRAY, z ? OPEN_OPTIONS_APPEND : OPEN_OPTIONS_TRUNCATE);
    }

    static OutputStream newOutputStream(Path path, LinkOption[] linkOptionArr, OpenOption... openOptionArr) throws IOException {
        if (!exists(path, linkOptionArr)) {
            createParentDirectories(path, (linkOptionArr == null || linkOptionArr.length <= 0) ? NULL_LINK_OPTION : linkOptionArr[0], new FileAttribute[0]);
        }
        if (openOptionArr == null) {
            openOptionArr = EMPTY_OPEN_OPTION_ARRAY;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(openOptionArr));
        if (linkOptionArr == null) {
            linkOptionArr = EMPTY_LINK_OPTION_ARRAY;
        }
        arrayList.addAll(Arrays.asList(linkOptionArr));
        return Files.newOutputStream(path, (OpenOption[]) arrayList.toArray(EMPTY_OPEN_OPTION_ARRAY));
    }

    public static LinkOption[] noFollowLinkOptionArray() {
        return (LinkOption[]) NOFOLLOW_LINK_OPTION_ARRAY.clone();
    }

    private static boolean notExists(Path path, LinkOption... linkOptionArr) {
        return Files.notExists((Path) Objects.requireNonNull(path, BleConstants.KEY_PATH), linkOptionArr);
    }

    static /* synthetic */ boolean lambda$overrideReadOnly$2(DeleteOption deleteOption) {
        return deleteOption == StandardDeleteOption.OVERRIDE_READ_ONLY;
    }

    private static boolean overrideReadOnly(DeleteOption... deleteOptionArr) {
        if (deleteOptionArr == null) {
            return false;
        }
        return Stream.of((Object[]) deleteOptionArr).anyMatch(new Predicate() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PathUtils.lambda$overrideReadOnly$2((DeleteOption) obj);
            }
        });
    }

    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> cls, LinkOption... linkOptionArr) {
        if (path != null) {
            try {
                return (A) Files.readAttributes(path, cls, linkOptionArr);
            } catch (IOException | UnsupportedOperationException unused) {
            }
        }
        return null;
    }

    public static BasicFileAttributes readBasicFileAttributes(Path path) throws IOException {
        return Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[0]);
    }

    public static BasicFileAttributes readBasicFileAttributes(Path path, LinkOption... linkOptionArr) {
        return readAttributes(path, BasicFileAttributes.class, linkOptionArr);
    }

    @Deprecated
    public static BasicFileAttributes readBasicFileAttributesUnchecked(Path path) {
        return readBasicFileAttributes(path, EMPTY_LINK_OPTION_ARRAY);
    }

    public static DosFileAttributes readDosFileAttributes(Path path, LinkOption... linkOptionArr) {
        return (DosFileAttributes) readAttributes(path, DosFileAttributes.class, linkOptionArr);
    }

    private static Path readIfSymbolicLink(Path path) throws IOException {
        if (path != null) {
            return Files.isSymbolicLink(path) ? Files.readSymbolicLink(path) : path;
        }
        return null;
    }

    public static BasicFileAttributes readOsFileAttributes(Path path, LinkOption... linkOptionArr) {
        PosixFileAttributes readPosixFileAttributes = readPosixFileAttributes(path, linkOptionArr);
        return readPosixFileAttributes != null ? readPosixFileAttributes : readDosFileAttributes(path, linkOptionArr);
    }

    public static PosixFileAttributes readPosixFileAttributes(Path path, LinkOption... linkOptionArr) {
        return (PosixFileAttributes) readAttributes(path, PosixFileAttributes.class, linkOptionArr);
    }

    public static String readString(Path path, Charset charset) throws IOException {
        return new String(Files.readAllBytes(path), Charsets.toCharset(charset));
    }

    static List<Path> relativize(Collection<Path> collection, final Path path, boolean z, Comparator<? super Path> comparator) {
        Stream<Path> stream = collection.stream();
        Objects.requireNonNull(path);
        Stream map = stream.map(new Function() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return path.relativize((Path) obj);
            }
        });
        if (z) {
            map = comparator == null ? map.sorted() : map.sorted(comparator);
        }
        return (List) map.collect(Collectors.toList());
    }

    private static Path requireExists(Path path, String str, LinkOption... linkOptionArr) {
        Objects.requireNonNull(path, str);
        if (exists(path, linkOptionArr)) {
            return path;
        }
        throw new IllegalArgumentException("File system element for parameter '" + str + "' does not exist: '" + path + "'");
    }

    private static boolean setDosReadOnly(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        DosFileAttributeView dosFileAttributeView = getDosFileAttributeView(path, linkOptionArr);
        if (dosFileAttributeView == null) {
            return false;
        }
        dosFileAttributeView.setReadOnly(z);
        return true;
    }

    public static void setLastModifiedTime(Path path, Path path2) throws IOException {
        Objects.requireNonNull(path, "sourceFile");
        Files.setLastModifiedTime(path2, getLastModifiedTime(path, new LinkOption[0]));
    }

    private static boolean setPosixDeletePermissions(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        return setPosixPermissions(path, z, Arrays.asList(PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE), linkOptionArr);
    }

    private static boolean setPosixPermissions(Path path, boolean z, List<PosixFilePermission> list, LinkOption... linkOptionArr) throws IOException {
        if (path == null) {
            return false;
        }
        Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path, linkOptionArr);
        HashSet hashSet = new HashSet(posixFilePermissions);
        if (z) {
            hashSet.addAll(list);
        } else {
            hashSet.removeAll(list);
        }
        if (hashSet.equals(posixFilePermissions)) {
            return true;
        }
        Files.setPosixFilePermissions(path, hashSet);
        return true;
    }

    private static void setPosixReadOnlyFile(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(path, linkOptionArr);
        List asList = Arrays.asList(PosixFilePermission.OWNER_READ);
        List asList2 = Arrays.asList(PosixFilePermission.OWNER_WRITE);
        if (z) {
            posixFilePermissions.addAll(asList);
            posixFilePermissions.removeAll(asList2);
        } else {
            posixFilePermissions.addAll(asList);
            posixFilePermissions.addAll(asList2);
        }
        Files.setPosixFilePermissions(path, posixFilePermissions);
    }

    public static Path setReadOnly(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        try {
            if (setDosReadOnly(path, z, linkOptionArr)) {
                return path;
            }
        } catch (IOException unused) {
        }
        Path parent = getParent(path);
        if (!isPosix(parent, linkOptionArr)) {
            throw new IOException(String.format("DOS or POSIX file operations not available for '%s' %s", path, Arrays.toString(linkOptionArr)));
        }
        if (z) {
            setPosixReadOnlyFile(path, z, linkOptionArr);
            setPosixDeletePermissions(parent, false, linkOptionArr);
        } else {
            setPosixDeletePermissions(parent, true, linkOptionArr);
        }
        return path;
    }

    public static long sizeOf(Path path) throws IOException {
        requireExists(path, BleConstants.KEY_PATH, new LinkOption[0]);
        return Files.isDirectory(path, new LinkOption[0]) ? sizeOfDirectory(path) : Files.size(path);
    }

    public static BigInteger sizeOfAsBigInteger(Path path) throws IOException {
        requireExists(path, BleConstants.KEY_PATH, new LinkOption[0]);
        return Files.isDirectory(path, new LinkOption[0]) ? sizeOfDirectoryAsBigInteger(path) : BigInteger.valueOf(Files.size(path));
    }

    public static long sizeOfDirectory(Path path) throws IOException {
        return countDirectory(path).getByteCounter().getLong().longValue();
    }

    public static BigInteger sizeOfDirectoryAsBigInteger(Path path) throws IOException {
        return countDirectoryAsBigInteger(path).getByteCounter().getBigInteger();
    }

    static Set<FileVisitOption> toFileVisitOptionSet(FileVisitOption... fileVisitOptionArr) {
        return fileVisitOptionArr == null ? EnumSet.noneOf(FileVisitOption.class) : (Set) Stream.of((Object[]) fileVisitOptionArr).collect(Collectors.toSet());
    }

    public static Path touch(Path path) throws IOException {
        Objects.requireNonNull(path, "file");
        if (!Files.exists(path, new LinkOption[0])) {
            createParentDirectories(path, new FileAttribute[0]);
            Files.createFile(path, new FileAttribute[0]);
        } else {
            FileTimes.setLastModifiedTime(path);
        }
        return path;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path) throws IOException {
        Files.walkFileTree(path, t);
        return t;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path, Set<FileVisitOption> set, int i) throws IOException {
        Files.walkFileTree(path, set, i, t);
        return t;
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, String str, String... strArr) throws IOException {
        return (T) visitFileTree(t, Paths.get(str, strArr));
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, URI uri) throws IOException {
        return (T) visitFileTree(t, Paths.get(uri));
    }

    public static boolean waitFor(Path path, Duration duration, LinkOption... linkOptionArr) {
        Objects.requireNonNull(path, "file");
        Instant plus = Instant.now().plus((TemporalAmount) duration);
        boolean z = false;
        while (!exists(path, linkOptionArr)) {
            try {
                Instant now = Instant.now();
                if (now.isAfter(plus)) {
                    return false;
                }
                try {
                    ThreadUtils.sleep(Duration.ofMillis(Math.min(100L, plus.minusMillis(now.toEpochMilli()).toEpochMilli())));
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Exception unused2) {
                }
            } finally {
                if (z) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return exists(path, linkOptionArr);
    }

    public static Stream<Path> walk(Path path, final PathFilter pathFilter, int i, final boolean z, FileVisitOption... fileVisitOptionArr) throws IOException {
        return Files.walk(path, i, fileVisitOptionArr).filter(new Predicate() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PathUtils.lambda$walk$3(PathFilter.this, z, (Path) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$walk$3(PathFilter pathFilter, boolean z, Path path) {
        return pathFilter.accept(path, z ? readBasicFileAttributesUnchecked(path) : null) == FileVisitResult.CONTINUE;
    }

    private static <R> R withPosixFileAttributes(Path path, LinkOption[] linkOptionArr, boolean z, IOFunction<PosixFileAttributes, R> iOFunction) throws IOException {
        PosixFileAttributes readPosixFileAttributes = z ? readPosixFileAttributes(path, linkOptionArr) : null;
        try {
            return iOFunction.apply(readPosixFileAttributes);
        } finally {
            if (readPosixFileAttributes != null && path != null && Files.exists(path, linkOptionArr)) {
                Files.setPosixFilePermissions(path, readPosixFileAttributes.permissions());
            }
        }
    }

    public static Path writeString(Path path, CharSequence charSequence, Charset charset, OpenOption... openOptionArr) throws IOException {
        Objects.requireNonNull(path, BleConstants.KEY_PATH);
        Objects.requireNonNull(charSequence, "charSequence");
        Files.write(path, String.valueOf(charSequence).getBytes(Charsets.toCharset(charset)), openOptionArr);
        return path;
    }

    private PathUtils() {
    }
}

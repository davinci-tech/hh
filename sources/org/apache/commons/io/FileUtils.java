package org.apache.commons.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.io.file.AccumulatorPathVisitor;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.StandardDeleteOption;
import org.apache.commons.io.filefilter.FileEqualsFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.function.IOBiFunction;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes2.dex */
public class FileUtils {
    public static final File[] EMPTY_FILE_ARRAY;
    public static final long ONE_EB = 1152921504606846976L;
    public static final BigInteger ONE_EB_BI;
    public static final long ONE_GB = 1073741824;
    public static final BigInteger ONE_GB_BI;
    public static final long ONE_KB = 1024;
    public static final BigInteger ONE_KB_BI;
    public static final long ONE_MB = 1048576;
    public static final BigInteger ONE_MB_BI;
    public static final long ONE_PB = 1125899906842624L;
    public static final BigInteger ONE_PB_BI;
    public static final long ONE_TB = 1099511627776L;
    public static final BigInteger ONE_TB_BI;
    public static final BigInteger ONE_YB;
    public static final BigInteger ONE_ZB;

    private static int toMaxDepth(boolean z) {
        return z ? Integer.MAX_VALUE : 1;
    }

    static {
        BigInteger valueOf = BigInteger.valueOf(1024L);
        ONE_KB_BI = valueOf;
        BigInteger multiply = valueOf.multiply(valueOf);
        ONE_MB_BI = multiply;
        BigInteger multiply2 = valueOf.multiply(multiply);
        ONE_GB_BI = multiply2;
        BigInteger multiply3 = valueOf.multiply(multiply2);
        ONE_TB_BI = multiply3;
        BigInteger multiply4 = valueOf.multiply(multiply3);
        ONE_PB_BI = multiply4;
        ONE_EB_BI = valueOf.multiply(multiply4);
        BigInteger multiply5 = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(ONE_EB));
        ONE_ZB = multiply5;
        ONE_YB = valueOf.multiply(multiply5);
        EMPTY_FILE_ARRAY = new File[0];
    }

    public static String byteCountToDisplaySize(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "size");
        BigInteger bigInteger2 = ONE_EB_BI;
        if (bigInteger.divide(bigInteger2).compareTo(BigInteger.ZERO) > 0) {
            return bigInteger.divide(bigInteger2) + " EB";
        }
        BigInteger bigInteger3 = ONE_PB_BI;
        if (bigInteger.divide(bigInteger3).compareTo(BigInteger.ZERO) > 0) {
            return bigInteger.divide(bigInteger3) + " PB";
        }
        BigInteger bigInteger4 = ONE_TB_BI;
        if (bigInteger.divide(bigInteger4).compareTo(BigInteger.ZERO) > 0) {
            return bigInteger.divide(bigInteger4) + " TB";
        }
        BigInteger bigInteger5 = ONE_GB_BI;
        if (bigInteger.divide(bigInteger5).compareTo(BigInteger.ZERO) > 0) {
            return bigInteger.divide(bigInteger5) + " GB";
        }
        BigInteger bigInteger6 = ONE_MB_BI;
        if (bigInteger.divide(bigInteger6).compareTo(BigInteger.ZERO) > 0) {
            return bigInteger.divide(bigInteger6) + " MB";
        }
        BigInteger bigInteger7 = ONE_KB_BI;
        if (bigInteger.divide(bigInteger7).compareTo(BigInteger.ZERO) > 0) {
            return bigInteger.divide(bigInteger7) + " KB";
        }
        return bigInteger + " bytes";
    }

    public static String byteCountToDisplaySize(long j) {
        return byteCountToDisplaySize(BigInteger.valueOf(j));
    }

    public static String byteCountToDisplaySize(Number number) {
        return byteCountToDisplaySize(number.longValue());
    }

    private static void checkFileExists(File file, String str) throws FileNotFoundException {
        Objects.requireNonNull(file, str);
        if (file.isFile()) {
            return;
        }
        if (file.exists()) {
            throw new IllegalArgumentException("Parameter '" + str + "' is not a file: " + file);
        }
        if (Files.isSymbolicLink(file.toPath())) {
            return;
        }
        throw new FileNotFoundException("Source '" + file + "' does not exist");
    }

    private static File checkIsFile(File file, String str) {
        if (file.isFile()) {
            return file;
        }
        throw new IllegalArgumentException(String.format("Parameter '%s' is not a file: %s", str, file));
    }

    public static Checksum checksum(File file, Checksum checksum) throws IOException {
        checkFileExists(file, "file");
        Objects.requireNonNull(checksum, "checksum");
        CheckedInputStream checkedInputStream = new CheckedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]), checksum);
        try {
            IOUtils.consume(checkedInputStream);
            checkedInputStream.close();
            return checksum;
        } catch (Throwable th) {
            try {
                checkedInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long checksumCRC32(File file) throws IOException {
        return checksum(file, new CRC32()).getValue();
    }

    public static void cleanDirectory(File file) throws IOException {
        IOConsumer.forAll(new IOConsumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                FileUtils.forceDelete((File) obj);
            }
        }, listFiles(file, null));
    }

    private static void cleanDirectoryOnExit(File file) throws IOException {
        IOConsumer.forAll(new IOConsumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                FileUtils.forceDeleteOnExit((File) obj);
            }
        }, listFiles(file, null));
    }

    public static boolean contentEquals(File file, File file2) throws IOException {
        boolean exists;
        if (file == null && file2 == null) {
            return true;
        }
        if (file == null || file2 == null || (exists = file.exists()) != file2.exists()) {
            return false;
        }
        if (!exists) {
            return true;
        }
        checkIsFile(file, "file1");
        checkIsFile(file2, "file2");
        if (file.length() != file2.length()) {
            return false;
        }
        if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        return PathUtils.fileContentEquals(file.toPath(), file2.toPath());
    }

    public static boolean contentEqualsIgnoreEOL(File file, File file2, String str) throws IOException {
        boolean exists;
        if (file == null && file2 == null) {
            return true;
        }
        if (file == null || file2 == null || (exists = file.exists()) != file2.exists()) {
            return false;
        }
        if (!exists) {
            return true;
        }
        checkFileExists(file, "file1");
        checkFileExists(file2, "file2");
        if (file.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        Charset charset = Charsets.toCharset(str);
        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(file.toPath(), new OpenOption[0]), charset);
        try {
            InputStreamReader inputStreamReader2 = new InputStreamReader(Files.newInputStream(file2.toPath(), new OpenOption[0]), charset);
            try {
                boolean contentEqualsIgnoreEOL = IOUtils.contentEqualsIgnoreEOL(inputStreamReader, inputStreamReader2);
                inputStreamReader2.close();
                inputStreamReader.close();
                return contentEqualsIgnoreEOL;
            } finally {
            }
        } catch (Throwable th) {
            try {
                inputStreamReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static File[] convertFileCollectionToFileArray(Collection<File> collection) {
        return (File[]) collection.toArray(EMPTY_FILE_ARRAY);
    }

    public static void copyDirectory(File file, File file2) throws IOException {
        copyDirectory(file, file2, true);
    }

    public static void copyDirectory(File file, File file2, boolean z) throws IOException {
        copyDirectory(file, file2, null, z);
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter) throws IOException {
        copyDirectory(file, file2, fileFilter, true);
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter, boolean z) throws IOException {
        copyDirectory(file, file2, fileFilter, z, StandardCopyOption.REPLACE_EXISTING, LinkOption.NOFOLLOW_LINKS);
    }

    public static void copyDirectory(File file, File file2, FileFilter fileFilter, boolean z, CopyOption... copyOptionArr) throws IOException {
        ArrayList arrayList;
        Objects.requireNonNull(file2, "destination");
        requireDirectoryExists(file, "srcDir");
        requireCanonicalPathsNotEquals(file, file2);
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            File[] listFiles = listFiles(file, fileFilter);
            if (listFiles.length > 0) {
                arrayList = new ArrayList(listFiles.length);
                for (File file3 : listFiles) {
                    arrayList.add(new File(file2, file3.getName()).getCanonicalPath());
                }
                doCopyDirectory(file, file2, fileFilter, arrayList, z, copyOptionArr);
            }
        }
        arrayList = null;
        doCopyDirectory(file, file2, fileFilter, arrayList, z, copyOptionArr);
    }

    public static void copyDirectoryToDirectory(File file, File file2) throws IOException {
        Objects.requireNonNull(file, "sourceDir");
        requireDirectoryIfExists(file2, "destinationDir");
        copyDirectory(file, new File(file2, file.getName()), true);
    }

    public static void copyFile(File file, File file2) throws IOException {
        copyFile(file, file2, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(File file, File file2, boolean z) throws IOException {
        copyFile(file, file2, z, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(File file, File file2, boolean z, CopyOption... copyOptionArr) throws IOException {
        Objects.requireNonNull(file2, "destination");
        checkFileExists(file, "srcFile");
        requireCanonicalPathsNotEquals(file, file2);
        createParentDirectories(file2);
        if (file2.exists()) {
            checkFileExists(file2, "destFile");
        }
        Path path = file.toPath();
        Files.copy(path, file2.toPath(), copyOptionArr);
        if (z && !Files.isSymbolicLink(path) && !setTimes(file, file2)) {
            throw new IOException("Cannot set the file time.");
        }
    }

    public static void copyFile(File file, File file2, CopyOption... copyOptionArr) throws IOException {
        copyFile(file, file2, true, copyOptionArr);
    }

    public static long copyFile(File file, OutputStream outputStream) throws IOException {
        InputStream newInputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
        try {
            long copyLarge = IOUtils.copyLarge(newInputStream, outputStream);
            if (newInputStream != null) {
                newInputStream.close();
            }
            return copyLarge;
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

    public static void copyFileToDirectory(File file, File file2) throws IOException {
        copyFileToDirectory(file, file2, true);
    }

    public static void copyFileToDirectory(File file, File file2, boolean z) throws IOException {
        Objects.requireNonNull(file, "sourceFile");
        requireDirectoryIfExists(file2, "destinationDir");
        copyFile(file, new File(file2, file.getName()), z);
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        try {
            copyToFile(inputStream, file);
            if (inputStream != null) {
                inputStream.close();
            }
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

    public static void copyToDirectory(File file, File file2) throws IOException {
        Objects.requireNonNull(file, "sourceFile");
        if (file.isFile()) {
            copyFileToDirectory(file, file2);
        } else {
            if (file.isDirectory()) {
                copyDirectoryToDirectory(file, file2);
                return;
            }
            throw new FileNotFoundException("The source " + file + " does not exist");
        }
    }

    public static void copyToDirectory(Iterable<File> iterable, File file) throws IOException {
        Objects.requireNonNull(iterable, "sourceIterable");
        Iterator<File> it = iterable.iterator();
        while (it.hasNext()) {
            copyFileToDirectory(it.next(), file);
        }
    }

    public static void copyToFile(InputStream inputStream, File file) throws IOException {
        OutputStream newOutputStream = newOutputStream(file, false);
        try {
            IOUtils.copy(inputStream, newOutputStream);
            if (newOutputStream != null) {
                newOutputStream.close();
            }
        } catch (Throwable th) {
            if (newOutputStream != null) {
                try {
                    newOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void copyURLToFile(final URL url, File file) throws IOException {
        Path path = file.toPath();
        PathUtils.createParentDirectories(path, new FileAttribute[0]);
        Objects.requireNonNull(url);
        PathUtils.copy(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                InputStream openStream;
                openStream = url.openStream();
                return openStream;
            }
        }, path, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyURLToFile(URL url, File file, int i, int i2) throws IOException {
        CloseableURLConnection open = CloseableURLConnection.open(url);
        try {
            open.setConnectTimeout(i);
            open.setReadTimeout(i2);
            InputStream inputStream = open.getInputStream();
            try {
                copyInputStreamToFile(inputStream, file);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (open != null) {
                    open.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static File createParentDirectories(File file) throws IOException {
        return mkdirs(getParentFile(file));
    }

    public static File current() {
        return PathUtils.current().toFile();
    }

    static String decodeUrl(String str) {
        int i;
        if (str == null || str.indexOf(37) < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        ByteBuffer allocate = ByteBuffer.allocate(length);
        int i2 = 0;
        while (i2 < length) {
            if (str.charAt(i2) == '%') {
                while (true) {
                    i = i2 + 3;
                    try {
                        try {
                            allocate.put((byte) Integer.parseInt(str.substring(i2 + 1, i), 16));
                            if (i >= length) {
                                break;
                            }
                            try {
                                if (str.charAt(i) != '%') {
                                    break;
                                }
                                i2 = i;
                            } catch (IndexOutOfBoundsException | NumberFormatException unused) {
                                i2 = i;
                                if (allocate.position() > 0) {
                                    allocate.flip();
                                    sb.append(StandardCharsets.UTF_8.decode(allocate).toString());
                                    allocate.clear();
                                }
                                sb.append(str.charAt(i2));
                                i2++;
                            }
                        } catch (IndexOutOfBoundsException | NumberFormatException unused2) {
                        }
                    } finally {
                        if (allocate.position() > 0) {
                            allocate.flip();
                            sb.append(StandardCharsets.UTF_8.decode(allocate).toString());
                            allocate.clear();
                        }
                    }
                }
                i2 = i;
            }
            sb.append(str.charAt(i2));
            i2++;
        }
        return sb.toString();
    }

    public static File delete(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        Files.delete(file.toPath());
        return file;
    }

    public static void deleteDirectory(File file) throws IOException {
        Objects.requireNonNull(file, "directory");
        if (file.exists()) {
            if (!isSymlink(file)) {
                cleanDirectory(file);
            }
            delete(file);
        }
    }

    private static void deleteDirectoryOnExit(File file) throws IOException {
        if (file.exists()) {
            file.deleteOnExit();
            if (isSymlink(file)) {
                return;
            }
            cleanDirectoryOnExit(file);
        }
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception unused) {
        }
        try {
            return file.delete();
        } catch (Exception unused2) {
            return false;
        }
    }

    public static boolean directoryContains(File file, File file2) throws IOException {
        requireDirectoryExists(file, "directory");
        if (file2 == null || !file2.exists()) {
            return false;
        }
        return FilenameUtils.directoryContains(file.getCanonicalPath(), file2.getCanonicalPath());
    }

    private static void doCopyDirectory(File file, File file2, FileFilter fileFilter, List<String> list, boolean z, CopyOption... copyOptionArr) throws IOException {
        File[] listFiles = listFiles(file, fileFilter);
        requireDirectoryIfExists(file2, "destDir");
        mkdirs(file2);
        for (File file3 : listFiles) {
            File file4 = new File(file2, file3.getName());
            if (list == null || !list.contains(file3.getCanonicalPath())) {
                if (file3.isDirectory()) {
                    doCopyDirectory(file3, file4, fileFilter, list, z, copyOptionArr);
                } else {
                    copyFile(file3, file4, z, copyOptionArr);
                }
            }
        }
        if (z) {
            setTimes(file, file2);
        }
    }

    public static void forceDelete(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        try {
            Counters.PathCounters delete = PathUtils.delete(file.toPath(), PathUtils.EMPTY_LINK_OPTION_ARRAY, StandardDeleteOption.OVERRIDE_READ_ONLY);
            if (delete.getFileCounter().get() >= 1 || delete.getDirectoryCounter().get() >= 1) {
                return;
            }
            throw new FileNotFoundException("File does not exist: " + file);
        } catch (IOException e) {
            throw new IOException("Cannot delete file: " + file, e);
        }
    }

    public static void forceDeleteOnExit(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    public static void forceMkdir(File file) throws IOException {
        mkdirs(file);
    }

    public static void forceMkdirParent(File file) throws IOException {
        forceMkdir(getParentFile((File) Objects.requireNonNull(file, "file")));
    }

    public static File getFile(File file, String... strArr) {
        Objects.requireNonNull(file, "directory");
        Objects.requireNonNull(strArr, "names");
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            File file2 = new File(file, strArr[i]);
            i++;
            file = file2;
        }
        return file;
    }

    public static File getFile(String... strArr) {
        Objects.requireNonNull(strArr, "names");
        File file = null;
        for (String str : strArr) {
            if (file == null) {
                file = new File(str);
            } else {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static File getParentFile(File file) {
        if (file == null) {
            return null;
        }
        return file.getParentFile();
    }

    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());
    }

    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    public static boolean isDirectory(File file, LinkOption... linkOptionArr) {
        return file != null && Files.isDirectory(file.toPath(), linkOptionArr);
    }

    public static boolean isEmptyDirectory(File file) throws IOException {
        return PathUtils.isEmptyDirectory(file.toPath());
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate) {
        return isFileNewer(file, chronoLocalDate, LocalTime.MAX);
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(localTime, "localTime");
        return isFileNewer(file, chronoLocalDate.atTime(localTime));
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate, OffsetTime offsetTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(offsetTime, "offsetTime");
        return isFileNewer(file, chronoLocalDate.atTime(offsetTime.toLocalTime()));
    }

    public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
        return isFileNewer(file, chronoLocalDateTime, ZoneId.systemDefault());
    }

    public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
        Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
        Objects.requireNonNull(zoneId, "zoneId");
        return isFileNewer(file, chronoLocalDateTime.atZone(zoneId));
    }

    public static boolean isFileNewer(final File file, final ChronoZonedDateTime<?> chronoZonedDateTime) {
        Objects.requireNonNull(file, "file");
        Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda15
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isNewer(file.toPath(), (ChronoZonedDateTime<?>) chronoZonedDateTime, new LinkOption[0]));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileNewer(File file, Date date) {
        Objects.requireNonNull(date, "date");
        return isFileNewer(file, date.getTime());
    }

    public static boolean isFileNewer(final File file, final File file2) {
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda23
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isNewer(file.toPath(), file2.toPath()));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileNewer(File file, FileTime fileTime) throws IOException {
        Objects.requireNonNull(file, "file");
        return PathUtils.isNewer(file.toPath(), fileTime, new LinkOption[0]);
    }

    public static boolean isFileNewer(final File file, final Instant instant) {
        Objects.requireNonNull(instant, "instant");
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isNewer(file.toPath(), instant, new LinkOption[0]));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileNewer(final File file, final long j) {
        Objects.requireNonNull(file, "file");
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isNewer(file.toPath(), j, new LinkOption[0]));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileNewer(File file, OffsetDateTime offsetDateTime) {
        Objects.requireNonNull(offsetDateTime, "offsetDateTime");
        return isFileNewer(file, offsetDateTime.toInstant());
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate) {
        return isFileOlder(file, chronoLocalDate, LocalTime.MAX);
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(localTime, "localTime");
        return isFileOlder(file, chronoLocalDate.atTime(localTime));
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate, OffsetTime offsetTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(offsetTime, "offsetTime");
        return isFileOlder(file, chronoLocalDate.atTime(offsetTime.toLocalTime()));
    }

    public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
        return isFileOlder(file, chronoLocalDateTime, ZoneId.systemDefault());
    }

    public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
        Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
        Objects.requireNonNull(zoneId, "zoneId");
        return isFileOlder(file, chronoLocalDateTime.atZone(zoneId));
    }

    public static boolean isFileOlder(File file, ChronoZonedDateTime<?> chronoZonedDateTime) {
        Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
        return isFileOlder(file, chronoZonedDateTime.toInstant());
    }

    public static boolean isFileOlder(File file, Date date) {
        Objects.requireNonNull(date, "date");
        return isFileOlder(file, date.getTime());
    }

    public static boolean isFileOlder(final File file, final File file2) throws FileNotFoundException {
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda20
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isOlder(file.toPath(), file2.toPath()));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileOlder(File file, FileTime fileTime) throws IOException {
        Objects.requireNonNull(file, "file");
        return PathUtils.isOlder(file.toPath(), fileTime, new LinkOption[0]);
    }

    public static boolean isFileOlder(final File file, final Instant instant) {
        Objects.requireNonNull(instant, "instant");
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isOlder(file.toPath(), instant, new LinkOption[0]));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileOlder(final File file, final long j) {
        Objects.requireNonNull(file, "file");
        return ((Boolean) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Boolean valueOf;
                valueOf = Boolean.valueOf(PathUtils.isOlder(file.toPath(), j, new LinkOption[0]));
                return valueOf;
            }
        })).booleanValue();
    }

    public static boolean isFileOlder(File file, OffsetDateTime offsetDateTime) {
        Objects.requireNonNull(offsetDateTime, "offsetDateTime");
        return isFileOlder(file, offsetDateTime.toInstant());
    }

    public static boolean isRegularFile(File file, LinkOption... linkOptionArr) {
        return file != null && Files.isRegularFile(file.toPath(), linkOptionArr);
    }

    public static boolean isSymlink(File file) {
        return file != null && Files.isSymbolicLink(file.toPath());
    }

    public static Iterator<File> iterateFiles(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return listFiles(file, iOFileFilter, iOFileFilter2).iterator();
    }

    public static Iterator<File> iterateFiles(final File file, final String[] strArr, final boolean z) {
        return StreamIterator.iterator((Stream) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda19
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Stream streamFiles;
                streamFiles = FileUtils.streamFiles(file, z, strArr);
                return streamFiles;
            }
        }));
    }

    public static Iterator<File> iterateFilesAndDirs(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        return listFilesAndDirs(file, iOFileFilter, iOFileFilter2).iterator();
    }

    public static long lastModified(File file) throws IOException {
        return lastModifiedFileTime(file).toMillis();
    }

    public static FileTime lastModifiedFileTime(File file) throws IOException {
        return Files.getLastModifiedTime((Path) Objects.requireNonNull(file.toPath(), "file"), new LinkOption[0]);
    }

    public static long lastModifiedUnchecked(File file) {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda14
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return Long.valueOf(FileUtils.lastModified((File) obj));
            }
        }, file)).longValue();
    }

    public static LineIterator lineIterator(File file) throws IOException {
        return lineIterator(file, null);
    }

    public static LineIterator lineIterator(File file, String str) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
            return IOUtils.lineIterator(inputStream, str);
        } catch (IOException | RuntimeException e) {
            IOUtils.closeQuietly(inputStream, new Consumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda17
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    e.addSuppressed((IOException) obj);
                }
            });
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AccumulatorPathVisitor listAccumulate(File file, IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2, FileVisitOption... fileVisitOptionArr) throws IOException {
        boolean z = iOFileFilter2 != null;
        IOFileFilter fileEqualsFileFilter = new FileEqualsFileFilter(file);
        if (z) {
            fileEqualsFileFilter = fileEqualsFileFilter.or(iOFileFilter2);
        }
        AccumulatorPathVisitor accumulatorPathVisitor = new AccumulatorPathVisitor(Counters.noopPathCounters(), iOFileFilter, fileEqualsFileFilter, new IOBiFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda11
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                FileVisitResult fileVisitResult;
                fileVisitResult = FileVisitResult.CONTINUE;
                return fileVisitResult;
            }
        });
        HashSet hashSet = new HashSet();
        if (fileVisitOptionArr != null) {
            Collections.addAll(hashSet, fileVisitOptionArr);
        }
        Files.walkFileTree(file.toPath(), hashSet, toMaxDepth(z), accumulatorPathVisitor);
        return accumulatorPathVisitor;
    }

    private static File[] listFiles(File file, FileFilter fileFilter) throws IOException {
        requireDirectoryExists(file, "directory");
        File[] listFiles = fileFilter == null ? file.listFiles() : file.listFiles(fileFilter);
        if (listFiles != null) {
            return listFiles;
        }
        throw new IOException("Unknown I/O error listing contents of directory: " + file);
    }

    public static Collection<File> listFiles(File file, final IOFileFilter iOFileFilter, final IOFileFilter iOFileFilter2) {
        return toList(((AccumulatorPathVisitor) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                AccumulatorPathVisitor listAccumulate;
                File file2 = (File) obj;
                listAccumulate = FileUtils.listAccumulate(file2, FileFileFilter.INSTANCE.and(IOFileFilter.this), iOFileFilter2, FileVisitOption.FOLLOW_LINKS);
                return listAccumulate;
            }
        }, file)).getFileList().stream().map(new FileUtils$$ExternalSyntheticLambda9()));
    }

    public static Collection<File> listFiles(final File file, final String[] strArr, final boolean z) {
        Stream stream = (Stream) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda18
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Stream streamFiles;
                streamFiles = FileUtils.streamFiles(file, z, strArr);
                return streamFiles;
            }
        });
        try {
            List<File> list = toList(stream);
            if (stream != null) {
                stream.close();
            }
            return list;
        } catch (Throwable th) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Collection<File> listFilesAndDirs(File file, final IOFileFilter iOFileFilter, final IOFileFilter iOFileFilter2) {
        AccumulatorPathVisitor accumulatorPathVisitor = (AccumulatorPathVisitor) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda16
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                AccumulatorPathVisitor listAccumulate;
                listAccumulate = FileUtils.listAccumulate((File) obj, IOFileFilter.this, iOFileFilter2, FileVisitOption.FOLLOW_LINKS);
                return listAccumulate;
            }
        }, file);
        List<Path> fileList = accumulatorPathVisitor.getFileList();
        fileList.addAll(accumulatorPathVisitor.getDirList());
        return toList(fileList.stream().map(new FileUtils$$ExternalSyntheticLambda9()));
    }

    private static File mkdirs(File file) throws IOException {
        if (file == null || file.mkdirs() || file.isDirectory()) {
            return file;
        }
        throw new IOException("Cannot create directory '" + file + "'.");
    }

    public static void moveDirectory(File file, File file2) throws IOException {
        Objects.requireNonNull(file2, "destination");
        requireDirectoryExists(file, "srcDir");
        requireAbsent(file2, "destDir");
        if (file.renameTo(file2)) {
            return;
        }
        if (file2.getCanonicalPath().startsWith(file.getCanonicalPath() + File.separator)) {
            throw new IOException("Cannot move directory: " + file + " to a subdirectory of itself: " + file2);
        }
        copyDirectory(file, file2);
        deleteDirectory(file);
        if (file.exists()) {
            throw new IOException("Failed to delete original directory '" + file + "' after copy to '" + file2 + "'");
        }
    }

    public static void moveDirectoryToDirectory(File file, File file2, boolean z) throws IOException {
        validateMoveParameters(file, file2);
        if (!file2.isDirectory()) {
            if (file2.exists()) {
                throw new IOException("Destination '" + file2 + "' is not a directory");
            }
            if (!z) {
                throw new FileNotFoundException("Destination directory '" + file2 + "' does not exist [createDestDir=false]");
            }
            mkdirs(file2);
        }
        moveDirectory(file, new File(file2, file.getName()));
    }

    public static void moveFile(File file, File file2) throws IOException {
        moveFile(file, file2, StandardCopyOption.COPY_ATTRIBUTES);
    }

    public static void moveFile(File file, File file2, CopyOption... copyOptionArr) throws IOException {
        Objects.requireNonNull(file2, "destination");
        checkFileExists(file, "srcFile");
        requireAbsent(file2, "destFile");
        if (file.renameTo(file2)) {
            return;
        }
        copyFile(file, file2, false, copyOptionArr);
        if (file.delete()) {
            return;
        }
        deleteQuietly(file2);
        throw new IOException("Failed to delete original file '" + file + "' after copy to '" + file2 + "'");
    }

    public static void moveFileToDirectory(File file, File file2, boolean z) throws IOException {
        validateMoveParameters(file, file2);
        if (!file2.exists() && z) {
            mkdirs(file2);
        }
        requireDirectoryExists(file2, "destDir");
        moveFile(file, new File(file2, file.getName()));
    }

    public static void moveToDirectory(File file, File file2, boolean z) throws IOException {
        validateMoveParameters(file, file2);
        if (file.isDirectory()) {
            moveDirectoryToDirectory(file, file2, z);
        } else {
            moveFileToDirectory(file, file2, z);
        }
    }

    public static OutputStream newOutputStream(File file, boolean z) throws IOException {
        return PathUtils.newOutputStream(((File) Objects.requireNonNull(file, "file")).toPath(), z);
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        return new FileInputStream(file);
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        Objects.requireNonNull(file, "file");
        if (file.exists()) {
            checkIsFile(file, "file");
        } else {
            createParentDirectories(file);
        }
        return new FileOutputStream(file, z);
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        Objects.requireNonNull(file, "file");
        return Files.readAllBytes(file.toPath());
    }

    @Deprecated
    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, Charset.defaultCharset());
    }

    public static String readFileToString(final File file, Charset charset) throws IOException {
        return IOUtils.toString((IOSupplier<InputStream>) new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda13
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                InputStream newInputStream;
                newInputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
                return newInputStream;
            }
        }, Charsets.toCharset(charset));
    }

    public static String readFileToString(File file, String str) throws IOException {
        return readFileToString(file, Charsets.toCharset(str));
    }

    @Deprecated
    public static List<String> readLines(File file) throws IOException {
        return readLines(file, Charset.defaultCharset());
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        return Files.readAllLines(file.toPath(), charset);
    }

    public static List<String> readLines(File file, String str) throws IOException {
        return readLines(file, Charsets.toCharset(str));
    }

    private static void requireAbsent(File file, String str) throws FileExistsException {
        if (file.exists()) {
            throw new FileExistsException(String.format("File element in parameter '%s' already exists: '%s'", str, file));
        }
    }

    private static void requireCanonicalPathsNotEquals(File file, File file2) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        if (canonicalPath.equals(file2.getCanonicalPath())) {
            throw new IllegalArgumentException(String.format("File canonical paths are equal: '%s' (file1='%s', file2='%s')", canonicalPath, file, file2));
        }
    }

    private static void requireDirectoryExists(File file, String str) throws FileNotFoundException {
        Objects.requireNonNull(file, str);
        if (file.isDirectory()) {
            return;
        }
        if (file.exists()) {
            throw new IllegalArgumentException("Parameter '" + str + "' is not a directory: '" + file + "'");
        }
        throw new FileNotFoundException("Directory '" + file + "' does not exist.");
    }

    private static void requireDirectoryIfExists(File file, String str) throws FileNotFoundException {
        Objects.requireNonNull(file, str);
        if (!file.exists() || file.isDirectory()) {
            return;
        }
        throw new IllegalArgumentException("Parameter '" + str + "' is not a directory: '" + file + "'");
    }

    private static boolean setTimes(File file, File file2) {
        Objects.requireNonNull(file, "sourceFile");
        Objects.requireNonNull(file2, "targetFile");
        try {
            BasicFileAttributes readAttributes = Files.readAttributes(file.toPath(), (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
            ((BasicFileAttributeView) Files.getFileAttributeView(file2.toPath(), BasicFileAttributeView.class, new LinkOption[0])).setTimes(readAttributes.lastModifiedTime(), readAttributes.lastAccessTime(), readAttributes.creationTime());
            return true;
        } catch (IOException unused) {
            return file2.setLastModified(file.lastModified());
        }
    }

    public static long sizeOf(final File file) {
        return ((Long) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Long valueOf;
                valueOf = Long.valueOf(PathUtils.sizeOf(file.toPath()));
                return valueOf;
            }
        })).longValue();
    }

    public static BigInteger sizeOfAsBigInteger(final File file) {
        return (BigInteger) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda10
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                BigInteger sizeOfAsBigInteger;
                sizeOfAsBigInteger = PathUtils.sizeOfAsBigInteger(file.toPath());
                return sizeOfAsBigInteger;
            }
        });
    }

    public static long sizeOfDirectory(final File file) {
        try {
            requireDirectoryExists(file, "directory");
            return ((Long) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda24
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    Long valueOf;
                    valueOf = Long.valueOf(PathUtils.sizeOfDirectory(file.toPath()));
                    return valueOf;
                }
            })).longValue();
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static BigInteger sizeOfDirectoryAsBigInteger(final File file) {
        try {
            requireDirectoryExists(file, "directory");
            return (BigInteger) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda12
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    BigInteger sizeOfDirectoryAsBigInteger;
                    sizeOfDirectoryAsBigInteger = PathUtils.sizeOfDirectoryAsBigInteger(file.toPath());
                    return sizeOfDirectoryAsBigInteger;
                }
            });
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static Stream<File> streamFiles(File file, boolean z, String... strArr) throws IOException {
        IOFileFilter and;
        if (strArr == null) {
            and = FileFileFilter.INSTANCE;
        } else {
            and = FileFileFilter.INSTANCE.and(new SuffixFileFilter(toSuffixes(strArr)));
        }
        return PathUtils.walk(file.toPath(), and, toMaxDepth(z), false, FileVisitOption.FOLLOW_LINKS).map(new FileUtils$$ExternalSyntheticLambda9());
    }

    public static File toFile(URL url) {
        if (url == null || !"file".equalsIgnoreCase(url.getProtocol())) {
            return null;
        }
        return new File(decodeUrl(url.getFile().replace('/', File.separatorChar)));
    }

    public static File[] toFiles(URL... urlArr) {
        if (IOUtils.length(urlArr) == 0) {
            return EMPTY_FILE_ARRAY;
        }
        File[] fileArr = new File[urlArr.length];
        for (int i = 0; i < urlArr.length; i++) {
            URL url = urlArr[i];
            if (url != null) {
                if (!"file".equalsIgnoreCase(url.getProtocol())) {
                    throw new IllegalArgumentException("Can only convert file URL to a File: " + url);
                }
                fileArr[i] = toFile(url);
            }
        }
        return fileArr;
    }

    private static List<File> toList(Stream<File> stream) {
        return (List) stream.collect(Collectors.toList());
    }

    static /* synthetic */ String lambda$toSuffixes$17(String str) {
        return "." + str;
    }

    static /* synthetic */ String[] lambda$toSuffixes$18(int i) {
        return new String[i];
    }

    private static String[] toSuffixes(String... strArr) {
        return (String[]) Stream.of((Object[]) Objects.requireNonNull(strArr, "extensions")).map(new Function() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda21
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FileUtils.lambda$toSuffixes$17((String) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda22
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return FileUtils.lambda$toSuffixes$18(i);
            }
        });
    }

    public static void touch(File file) throws IOException {
        PathUtils.touch(((File) Objects.requireNonNull(file, "file")).toPath());
    }

    public static URL[] toURLs(File... fileArr) throws IOException {
        Objects.requireNonNull(fileArr, "files");
        int length = fileArr.length;
        URL[] urlArr = new URL[length];
        for (int i = 0; i < length; i++) {
            urlArr[i] = fileArr[i].toURI().toURL();
        }
        return urlArr;
    }

    private static void validateMoveParameters(File file, File file2) throws FileNotFoundException {
        Objects.requireNonNull(file, "source");
        Objects.requireNonNull(file2, "destination");
        if (file.exists()) {
            return;
        }
        throw new FileNotFoundException("Source '" + file + "' does not exist");
    }

    public static boolean waitFor(File file, int i) {
        Objects.requireNonNull(file, "file");
        return PathUtils.waitFor(file.toPath(), Duration.ofSeconds(i), PathUtils.EMPTY_LINK_OPTION_ARRAY);
    }

    @Deprecated
    public static void write(File file, CharSequence charSequence) throws IOException {
        write(file, charSequence, Charset.defaultCharset(), false);
    }

    @Deprecated
    public static void write(File file, CharSequence charSequence, boolean z) throws IOException {
        write(file, charSequence, Charset.defaultCharset(), z);
    }

    public static void write(File file, CharSequence charSequence, Charset charset) throws IOException {
        write(file, charSequence, charset, false);
    }

    public static void write(File file, CharSequence charSequence, Charset charset, boolean z) throws IOException {
        writeStringToFile(file, Objects.toString(charSequence, null), charset, z);
    }

    public static void write(File file, CharSequence charSequence, String str) throws IOException {
        write(file, charSequence, str, false);
    }

    public static void write(File file, CharSequence charSequence, String str, boolean z) throws IOException {
        write(file, charSequence, Charsets.toCharset(str), z);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr) throws IOException {
        writeByteArrayToFile(file, bArr, false);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr, boolean z) throws IOException {
        writeByteArrayToFile(file, bArr, 0, bArr.length, z);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr, int i, int i2) throws IOException {
        writeByteArrayToFile(file, bArr, i, i2, false);
    }

    public static void writeByteArrayToFile(File file, byte[] bArr, int i, int i2, boolean z) throws IOException {
        OutputStream newOutputStream = newOutputStream(file, z);
        try {
            newOutputStream.write(bArr, i, i2);
            if (newOutputStream != null) {
                newOutputStream.close();
            }
        } catch (Throwable th) {
            if (newOutputStream != null) {
                try {
                    newOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void writeLines(File file, Collection<?> collection) throws IOException {
        writeLines(file, null, collection, null, false);
    }

    public static void writeLines(File file, Collection<?> collection, boolean z) throws IOException {
        writeLines(file, null, collection, null, z);
    }

    public static void writeLines(File file, Collection<?> collection, String str) throws IOException {
        writeLines(file, null, collection, str, false);
    }

    public static void writeLines(File file, Collection<?> collection, String str, boolean z) throws IOException {
        writeLines(file, null, collection, str, z);
    }

    public static void writeLines(File file, String str, Collection<?> collection) throws IOException {
        writeLines(file, str, collection, null, false);
    }

    public static void writeLines(File file, String str, Collection<?> collection, boolean z) throws IOException {
        writeLines(file, str, collection, null, z);
    }

    public static void writeLines(File file, String str, Collection<?> collection, String str2) throws IOException {
        writeLines(file, str, collection, str2, false);
    }

    public static void writeLines(File file, String str, Collection<?> collection, String str2, boolean z) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(newOutputStream(file, z));
        try {
            IOUtils.writeLines(collection, str2, bufferedOutputStream, str);
            bufferedOutputStream.close();
        } catch (Throwable th) {
            try {
                bufferedOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Deprecated
    public static void writeStringToFile(File file, String str) throws IOException {
        writeStringToFile(file, str, Charset.defaultCharset(), false);
    }

    @Deprecated
    public static void writeStringToFile(File file, String str, boolean z) throws IOException {
        writeStringToFile(file, str, Charset.defaultCharset(), z);
    }

    public static void writeStringToFile(File file, String str, Charset charset) throws IOException {
        writeStringToFile(file, str, charset, false);
    }

    public static void writeStringToFile(File file, String str, Charset charset, boolean z) throws IOException {
        OutputStream newOutputStream = newOutputStream(file, z);
        try {
            IOUtils.write(str, newOutputStream, charset);
            if (newOutputStream != null) {
                newOutputStream.close();
            }
        } catch (Throwable th) {
            if (newOutputStream != null) {
                try {
                    newOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void writeStringToFile(File file, String str, String str2) throws IOException {
        writeStringToFile(file, str, str2, false);
    }

    public static void writeStringToFile(File file, String str, String str2, boolean z) throws IOException {
        writeStringToFile(file, str, Charsets.toCharset(str2), z);
    }

    @Deprecated
    public FileUtils() {
    }
}

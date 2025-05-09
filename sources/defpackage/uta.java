package defpackage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;

/* loaded from: classes7.dex */
public class uta {
    public static final byte[] c = {0, 0, -92, -127};
    public static final byte[] d = {0, 0, -19, 65};

    public static void b(Path path, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        if (b()) {
            a(path, bArr);
        } else if (c() || e()) {
            e(path, bArr);
        }
    }

    public static void a(Path path, long j) {
        if (j <= 0 || !Files.exists(path, new LinkOption[0])) {
            return;
        }
        try {
            Files.setLastModifiedTime(path, FileTime.fromMillis(utd.a(j)));
        } catch (Exception unused) {
        }
    }

    public static void d(File file, long j) {
        file.setLastModified(utd.a(j));
    }

    public static byte[] d(File file) {
        if (file != null) {
            try {
                if (Files.isSymbolicLink(file.toPath()) || file.exists()) {
                    Path path = file.toPath();
                    if (b()) {
                        return a(path);
                    }
                    if (!c() && !e()) {
                        return new byte[4];
                    }
                    return c(path);
                }
            } catch (NoSuchMethodError unused) {
                return new byte[4];
            }
        }
        return new byte[4];
    }

    public static List<File> c(File file, ZipParameters zipParameters) throws ZipException {
        if (file == null) {
            throw new ZipException("input path is null, cannot read files in the directory");
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (file.isDirectory() && file.canRead() && listFiles != null) {
            for (File file2 : listFiles) {
                if ((zipParameters.f() == null || !zipParameters.f().isExcluded(file2)) && (!file2.isHidden() || zipParameters.r())) {
                    arrayList.add(file2);
                    ZipParameters.SymbolicLinkAction m = zipParameters.m();
                    boolean b = b(file2);
                    if ((b && !ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(m)) || (!b && file2.isDirectory())) {
                        arrayList.addAll(c(file2, zipParameters));
                    }
                }
            }
        }
        return arrayList;
    }

    public static String c(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public static String e(String str) throws ZipException {
        if (!utd.b(str)) {
            throw new ZipException("zip file name is empty or null, cannot determine zip file name");
        }
        if (str.contains(System.getProperty("file.separator"))) {
            str = str.substring(str.lastIndexOf(System.getProperty("file.separator")) + 1);
        }
        return str.endsWith(".zip") ? str.substring(0, str.lastIndexOf(".")) : str;
    }

    public static String e(File file, ZipParameters zipParameters) throws ZipException {
        String a2;
        String substring;
        try {
            String canonicalPath = file.getCanonicalPath();
            if (utd.b(zipParameters.c())) {
                String canonicalPath2 = new File(zipParameters.c()).getCanonicalPath();
                if (!canonicalPath2.endsWith(usw.b)) {
                    canonicalPath2 = canonicalPath2 + usw.b;
                }
                if (b(file)) {
                    substring = new File(file.getParentFile().getCanonicalFile().getPath() + File.separator + file.getCanonicalFile().getName()).getPath().substring(canonicalPath2.length());
                } else if (!file.getCanonicalFile().getPath().startsWith(canonicalPath2)) {
                    substring = file.getCanonicalFile().getParentFile().getName() + usw.b + file.getCanonicalFile().getName();
                } else {
                    substring = canonicalPath.substring(canonicalPath2.length());
                }
                if (substring.startsWith(System.getProperty("file.separator"))) {
                    substring = substring.substring(1);
                }
                File file2 = new File(canonicalPath);
                if (file2.isDirectory()) {
                    a2 = substring.replaceAll("\\\\", "/") + "/";
                } else {
                    a2 = substring.substring(0, substring.lastIndexOf(file2.getName())).replaceAll("\\\\", "/") + a(file2, zipParameters.o());
                }
            } else {
                File file3 = new File(canonicalPath);
                a2 = a(file3, zipParameters.o());
                if (file3.isDirectory()) {
                    a2 = a2 + "/";
                }
            }
            String k = zipParameters.k();
            if (utd.b(k)) {
                if (!k.endsWith("\\") && !k.endsWith("/")) {
                    k = k + usw.b;
                }
                k = k.replaceAll("\\\\", "/");
                a2 = k + a2;
            }
            if (utd.b(a2)) {
                return a2;
            }
            String str = "fileName to add to zip is empty or null. fileName: '" + a2 + "' DefaultFolderPath: '" + zipParameters.c() + "' FileNameInZip: " + zipParameters.o();
            if (b(file)) {
                str = str + "isSymlink: true ";
            }
            if (utd.b(k)) {
                str = "rootFolderNameInZip: '" + k + "' ";
            }
            throw new ZipException(str);
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    private static String a(File file, String str) throws IOException {
        if (utd.b(str)) {
            return str;
        }
        if (b(file)) {
            return file.toPath().toRealPath(LinkOption.NOFOLLOW_LINKS).getFileName().toString();
        }
        return file.getName();
    }

    public static boolean d(String str) {
        return str.endsWith("/") || str.endsWith("\\");
    }

    public static void b(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor, int i) throws ZipException {
        byte[] bArr;
        long j3 = 0;
        if (j < 0 || j2 < 0 || j > j2) {
            throw new ZipException("invalid offsets");
        }
        if (j == j2) {
            return;
        }
        try {
            randomAccessFile.seek(j);
            long j4 = j2 - j;
            if (j4 < i) {
                bArr = new byte[(int) j4];
            } else {
                bArr = new byte[i];
            }
            while (true) {
                int read = randomAccessFile.read(bArr);
                if (read == -1) {
                    return;
                }
                outputStream.write(bArr, 0, read);
                long j5 = read;
                progressMonitor.e(j5);
                if (progressMonitor.d()) {
                    progressMonitor.b(ProgressMonitor.Result.CANCELLED);
                    return;
                }
                j3 += j5;
                if (j3 == j4) {
                    return;
                }
                if (bArr.length + j3 > j4) {
                    bArr = new byte[(int) (j4 - j3)];
                }
            }
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    public static void a(List<File> list, ZipParameters.SymbolicLinkAction symbolicLinkAction) throws ZipException {
        for (File file : list) {
            if (b(file)) {
                if (symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE) || symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY)) {
                    f(file);
                }
            } else {
                h(file);
            }
        }
    }

    public static boolean a(File file) {
        return file.getName().endsWith(".zip.001");
    }

    public static String c(File file) {
        String name = file.getName();
        return !name.contains(".") ? "" : name.substring(name.lastIndexOf(".") + 1);
    }

    public static File[] e(File file) {
        final String c2 = c(file.getName());
        File[] listFiles = file.getParentFile().listFiles(new FilenameFilter() { // from class: uta.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.startsWith(c2 + ".");
            }
        });
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles);
        return listFiles;
    }

    public static boolean b(File file) {
        try {
            return Files.isSymbolicLink(file.toPath());
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static String g(File file) {
        try {
            return Files.readSymbolicLink(file.toPath()).toString();
        } catch (Error | Exception unused) {
            return "";
        }
    }

    public static byte[] d(boolean z) {
        byte[] bArr = new byte[4];
        if (e() || c()) {
            if (z) {
                System.arraycopy(d, 0, bArr, 0, 4);
            } else {
                System.arraycopy(c, 0, bArr, 0, 4);
            }
        } else if (b() && z) {
            bArr[0] = usy.b(bArr[0], 4);
        }
        return bArr;
    }

    public static boolean b() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean c() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static boolean e() {
        return System.getProperty("os.name").toLowerCase().contains("nux");
    }

    private static void a(Path path, byte[] bArr) {
        DosFileAttributeView dosFileAttributeView;
        if (bArr[0] == 0 || (dosFileAttributeView = (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)) == null) {
            return;
        }
        try {
            dosFileAttributeView.setReadOnly(usy.c(bArr[0], 0));
            dosFileAttributeView.setHidden(usy.c(bArr[0], 1));
            dosFileAttributeView.setSystem(usy.c(bArr[0], 2));
            dosFileAttributeView.setArchive(usy.c(bArr[0], 5));
        } catch (IOException unused) {
        }
    }

    private static void e(Path path, byte[] bArr) {
        if (bArr[2] == 0 && bArr[3] == 0) {
            return;
        }
        try {
            HashSet hashSet = new HashSet();
            c(bArr[3], 0, hashSet, PosixFilePermission.OWNER_READ);
            c(bArr[2], 7, hashSet, PosixFilePermission.OWNER_WRITE);
            c(bArr[2], 6, hashSet, PosixFilePermission.OWNER_EXECUTE);
            c(bArr[2], 5, hashSet, PosixFilePermission.GROUP_READ);
            c(bArr[2], 4, hashSet, PosixFilePermission.GROUP_WRITE);
            c(bArr[2], 3, hashSet, PosixFilePermission.GROUP_EXECUTE);
            c(bArr[2], 2, hashSet, PosixFilePermission.OTHERS_READ);
            c(bArr[2], 1, hashSet, PosixFilePermission.OTHERS_WRITE);
            c(bArr[2], 0, hashSet, PosixFilePermission.OTHERS_EXECUTE);
            Files.setPosixFilePermissions(path, hashSet);
        } catch (IOException unused) {
        }
    }

    private static byte[] a(Path path) {
        DosFileAttributeView dosFileAttributeView;
        byte[] bArr = new byte[4];
        try {
            dosFileAttributeView = (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException unused) {
        }
        if (dosFileAttributeView == null) {
            return bArr;
        }
        DosFileAttributes readAttributes = dosFileAttributeView.readAttributes();
        bArr[0] = d(readAttributes.isArchive(), d(readAttributes.isDirectory(), d(readAttributes.isSystem(), d(readAttributes.isHidden(), d(readAttributes.isReadOnly(), (byte) 0, 0), 1), 2), 4), 5);
        return bArr;
    }

    private static void h(File file) throws ZipException {
        if (file.exists()) {
            return;
        }
        throw new ZipException("File does not exist: " + file);
    }

    private static void f(File file) throws ZipException {
        if (file.exists()) {
            return;
        }
        throw new ZipException("Symlink target '" + g(file) + "' does not exist for link '" + file + "'");
    }

    private static byte[] c(Path path) {
        byte[] bArr = new byte[4];
        try {
            Set<PosixFilePermission> permissions = ((PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)).readAttributes().permissions();
            boolean isSymbolicLink = Files.isSymbolicLink(path);
            if (isSymbolicLink) {
                byte b = usy.b(bArr[3], 7);
                bArr[3] = b;
                bArr[3] = usy.e(b, 6);
            } else {
                bArr[3] = d(Files.isRegularFile(path, new LinkOption[0]), bArr[3], 7);
                bArr[3] = d(Files.isDirectory(path, new LinkOption[0]), bArr[3], 6);
            }
            bArr[3] = d(isSymbolicLink, bArr[3], 5);
            bArr[3] = d(permissions.contains(PosixFilePermission.OWNER_READ), bArr[3], 0);
            bArr[2] = d(permissions.contains(PosixFilePermission.OWNER_WRITE), bArr[2], 7);
            bArr[2] = d(permissions.contains(PosixFilePermission.OWNER_EXECUTE), bArr[2], 6);
            bArr[2] = d(permissions.contains(PosixFilePermission.GROUP_READ), bArr[2], 5);
            bArr[2] = d(permissions.contains(PosixFilePermission.GROUP_WRITE), bArr[2], 4);
            bArr[2] = d(permissions.contains(PosixFilePermission.GROUP_EXECUTE), bArr[2], 3);
            bArr[2] = d(permissions.contains(PosixFilePermission.OTHERS_READ), bArr[2], 2);
            bArr[2] = d(permissions.contains(PosixFilePermission.OTHERS_WRITE), bArr[2], 1);
            bArr[2] = d(permissions.contains(PosixFilePermission.OTHERS_EXECUTE), bArr[2], 0);
        } catch (IOException unused) {
        }
        return bArr;
    }

    private static byte d(boolean z, byte b, int i) {
        return z ? usy.b(b, i) : b;
    }

    private static void c(byte b, int i, Set<PosixFilePermission> set, PosixFilePermission posixFilePermission) {
        if (usy.c(b, i)) {
            set.add(posixFilePermission);
        }
    }
}

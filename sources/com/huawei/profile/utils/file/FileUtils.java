package com.huawei.profile.utils.file;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import com.huawei.profile.utils.logger.DbLogUtil;
import com.huawei.profile.utils.logger.DsLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class FileUtils {
    private static final int BUFFER_MARK = -1;
    private static final int BUFFER_SIZE = 4096;
    private static final String EXCEPTION_MESSAGE = " input params contain null!";
    private static final long FILE_COPY_BUFFER_SIZE = 31457280;
    private static final String PATH_WHITE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?";
    private static final String TAG = "FileUtils";
    private static final Pattern PATTERN = Pattern.compile("(.*([/\\\\]{1}[\\.\\.]{1,2}|[\\.\\.]{1,2}[/\\\\]{1}|\\.\\.).*|\\.)");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private FileUtils() {
    }

    public static File getFile(String str) throws IOException {
        return getFile(str, null);
    }

    public static File getFile(String str, String str2) throws IOException {
        String canonicalize = canonicalize(str, str2);
        if (canonicalize == null) {
            throw new IOException("get file failed while canonicalizing!");
        }
        if (!isSafePath(canonicalize)) {
            throw new IOException("Invalid file filePath, not safe!");
        }
        String checkFile = checkFile(canonicalize);
        if (checkFile == null) {
            throw new IOException("checkedPath is null");
        }
        return new File(checkFile);
    }

    private static boolean isSafePath(String str) {
        boolean matches = PATTERN.matcher(str).matches();
        if (matches) {
            DsLog.et(TAG, "Invalid file path : " + DbLogUtil.getSafeNameForLog(str), new Object[0]);
        }
        return !matches;
    }

    private static String checkFile(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            while (true) {
                if (i2 >= 94) {
                    break;
                }
                if (str.charAt(i) == PATH_WHITE_LIST.charAt(i2)) {
                    sb.append(PATH_WHITE_LIST.charAt(i2));
                    break;
                }
                i2++;
            }
        }
        return sb.toString();
    }

    private static int getDirMode(boolean z, boolean z2) {
        int i = OsConstants.S_IRUSR | OsConstants.S_IWUSR | OsConstants.S_IXUSR;
        if (z) {
            i |= OsConstants.S_IRGRP;
        }
        if (z2) {
            i |= OsConstants.S_IWGRP;
        }
        return (z && z2) ? i | OsConstants.S_IXGRP : i;
    }

    private static int getFileMode(boolean z, boolean z2) {
        int i = OsConstants.S_IRUSR | OsConstants.S_IWUSR;
        if (z) {
            i |= OsConstants.S_IRGRP;
        }
        return z2 ? i | OsConstants.S_IWGRP : i;
    }

    public static boolean mkdir(File file, int i, int i2, boolean z, boolean z2) {
        if (file.mkdir() && (i >= 0 || i2 >= 0)) {
            try {
                String canonicalPath = file.getCanonicalPath();
                Os.chown(canonicalPath, i, i2);
                Os.chmod(canonicalPath, getDirMode(z, z2));
                return true;
            } catch (ErrnoException | IOException unused) {
                if (!file.delete()) {
                    DsLog.wt(TAG, "mkdir a dir with wrong uid/gid remains.", new Object[0]);
                }
            }
        }
        return false;
    }

    public static boolean mkdirs(File file, int i, int i2, boolean z, boolean z2) {
        if (file.exists()) {
            return false;
        }
        if (mkdir(file, i, i2, z, z2)) {
            return true;
        }
        try {
            File canonicalFile = file.getCanonicalFile();
            File parentFile = canonicalFile.getParentFile();
            if (parentFile != null) {
                return (parentFile.exists() || mkdirs(parentFile, i, i2, z, z2)) && mkdir(canonicalFile, i, i2, z, z2);
            }
            return false;
        } catch (IOException unused) {
            return false;
        }
    }

    public static FileOutputStream openOutputStream(File file, boolean z) throws IOException {
        if (file.exists()) {
            if (file.isDirectory() || !file.canWrite()) {
                throw new IOException("File is a directory or un-writable");
            }
        } else {
            forceMkdirParent(file);
        }
        return new FileOutputStream(file, z);
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        return new FileInputStream(getFile(file.getCanonicalPath()));
    }

    public static void setFileAccessPermission(String str, int i, int i2, boolean z, boolean z2) throws ErrnoException {
        if (i >= 0 || i2 >= 0) {
            Os.chown(str, i, i2);
            Os.chmod(str, getFileMode(z, z2));
        }
    }

    public static File getOutputFile(String str, int i, int i2, boolean z, boolean z2) throws IOException {
        File file = getFile(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + DbLogUtil.getSafeNameForLog(file.toString()) + "' exists but is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException("File '" + DbLogUtil.getSafeNameForLog(file.toString()) + "' cannot be written to");
            }
        } else {
            File parentFile = file.getParentFile();
            if (parentFile != null && !mkdirs(parentFile, i, i2, z, z2) && !parentFile.isDirectory()) {
                throw new IOException("Directory '" + DbLogUtil.getSafeNameForLog(parentFile.toString()) + "' could not be created");
            }
        }
        return file;
    }

    public static String canonicalize(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        boolean isEmpty = TextUtils.isEmpty(str2);
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        try {
            return (isEmpty ? new File(normalize) : new File(normalize, isEmpty ? "" : Normalizer.normalize(str2, Normalizer.Form.NFKC))).getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }

    public static void closeCloseable(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                DsLog.et(TAG, "closeClosable failed.", new Object[0]);
            }
        }
    }

    public static List<File> getAllFileInDir(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            getAllFile(str, arrayList);
        } catch (IOException unused) {
            DsLog.wt(TAG, "IOException happened while checkLogDataFiles err. ", new Object[0]);
        }
        return arrayList;
    }

    private static void getAllFile(String str, List<File> list) throws IOException {
        File file = new File(str);
        if (list == null || !file.exists()) {
            DsLog.et(TAG, "pathName is not exists or List null.", new Object[0]);
            return;
        }
        if (!file.isDirectory()) {
            if (file.isFile()) {
                list.add(file);
            }
            DsLog.et(TAG, "pathName is not a dir nor file.", new Object[0]);
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            list.add(file2);
            if (file2.isDirectory()) {
                getAllFile(file2.getCanonicalPath(), list);
            }
        }
    }

    public static boolean deleteDir(String str) {
        try {
            forceDelete(getFile(str));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean deleteDir(File file) {
        try {
            forceDelete(file);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File is null to delete");
        }
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean exists = file.exists();
        if (file.delete()) {
            return;
        }
        if (!exists) {
            throw new FileNotFoundException("File does not exist: " + DbLogUtil.getSafeNameForLog(file.toString()));
        }
        throw new IOException("Unable to delete file: " + DbLogUtil.getSafeNameForLog(file.toString()));
    }

    private static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            cleanDirectory(file);
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + DbLogUtil.getSafeNameForLog(file.toString()));
        }
    }

    private static void cleanDirectory(File file) throws IOException {
        IOException e = null;
        for (File file2 : listFiles(file)) {
            try {
                forceDelete(file2);
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e != null) {
            throw e;
        }
    }

    private static File[] listFiles(File file) {
        File[] listFiles = (file.exists() && file.isDirectory()) ? file.listFiles() : null;
        return listFiles == null ? new File[0] : listFiles;
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = openOutputStream(file, false);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        closeCloseable(fileOutputStream);
                        closeCloseable(inputStream);
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                closeCloseable(fileOutputStream);
                closeCloseable(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static String readFileToString(File file, String str, long j) throws IOException {
        FileInputStream fileInputStream;
        if (file == null || TextUtils.isEmpty(str)) {
            throw new IOException(EXCEPTION_MESSAGE);
        }
        try {
            fileInputStream = openInputStream(file);
            try {
                String readFileToString = readFileToString(fileInputStream, str, j);
                closeCloseable(fileInputStream);
                return readFileToString;
            } catch (Throwable th) {
                th = th;
                closeCloseable(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static String readFileToString(InputStream inputStream, String str, long j) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (inputStream == null || TextUtils.isEmpty(str)) {
            throw new IOException(EXCEPTION_MESSAGE);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byte[] bArr = new byte[4096];
                long j2 = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        closeCloseable(bufferedInputStream);
                        return byteArrayOutputStream.toString(str);
                    }
                    j2 += read;
                    if (j2 > j) {
                        throw new IOException(" file is too large!");
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (Throwable th) {
                th = th;
                closeCloseable(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void writeStringToFile(File file, String str, boolean z, String str2) throws IOException {
        FileOutputStream fileOutputStream;
        if (file == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IOException(EXCEPTION_MESSAGE);
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            FileOutputStream openOutputStream = openOutputStream(file, z);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openOutputStream);
                try {
                    bufferedOutputStream.write(str.getBytes(str2));
                    bufferedOutputStream.flush();
                    closeCloseable(openOutputStream);
                    closeCloseable(bufferedOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream2 = bufferedOutputStream;
                    FileOutputStream fileOutputStream3 = fileOutputStream2;
                    fileOutputStream2 = openOutputStream;
                    fileOutputStream = fileOutputStream3;
                    closeCloseable(fileOutputStream2);
                    closeCloseable(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static void writeLineToFile(File file, String str, String str2) throws IOException {
        if (str == null) {
            return;
        }
        writeStringToFile(file, str + LINE_SEPARATOR, true, str2);
    }

    public static void copyFile(File file, File file2) throws IOException {
        if (file == null || file2 == null) {
            throw new IllegalArgumentException("Source file or destination file is null");
        }
        if (!file.exists() || file.isDirectory()) {
            throw new IOException("Source file does not exists or is a directory");
        }
        if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            throw new IOException("Source and destination files are the same");
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
            throw new IOException("Destination parent directory cannot be created");
        }
        if (file2.exists() && !file2.canWrite()) {
            throw new IOException("Destination file exists but is read-only");
        }
        if (file2.exists() && file2.isDirectory()) {
            throw new IOException("Destination file exists but is a directory");
        }
        doCopyFile(file, file2);
    }

    private static void doCopyFile(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileChannel fileChannel;
        FileChannel fileChannel2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    fileChannel = fileInputStream.getChannel();
                    try {
                        fileChannel2 = fileOutputStream.getChannel();
                        long size = fileChannel.size();
                        long j = 0;
                        while (j < size) {
                            long transferFrom = fileChannel2.transferFrom(fileChannel, j, Math.min(size - j, FILE_COPY_BUFFER_SIZE));
                            if (transferFrom == 0) {
                                break;
                            } else {
                                j += transferFrom;
                            }
                        }
                        closeCloseable(fileChannel2);
                        closeCloseable(fileOutputStream);
                        closeCloseable(fileChannel);
                        closeCloseable(fileInputStream);
                        if (file.length() != file2.length()) {
                            throw new IOException("Failed to copy full contents from source to destination");
                        }
                        if (file2.setLastModified(file.lastModified())) {
                            return;
                        }
                        DsLog.et(TAG, " Set last modified failed while do copy file.", new Object[0]);
                    } catch (Throwable th) {
                        th = th;
                        closeCloseable(fileChannel2);
                        closeCloseable(fileOutputStream);
                        closeCloseable(fileChannel);
                        closeCloseable(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = null;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                fileChannel = null;
                closeCloseable(fileChannel2);
                closeCloseable(fileOutputStream);
                closeCloseable(fileChannel);
                closeCloseable(fileInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
        }
    }

    public static void moveFile(File file, File file2) throws IOException {
        if (file == null || file2 == null) {
            throw new IllegalArgumentException("Source file or destination file is null");
        }
        if (!file.exists() || file.isDirectory()) {
            throw new IOException("Source file does not exists or is a directory");
        }
        if (file2.exists() || file2.isDirectory()) {
            throw new IOException("Destination file exists or is a directory");
        }
        if (file.renameTo(file2)) {
            return;
        }
        copyFile(file, file2);
        if (file.delete()) {
            return;
        }
        deleteDir(file2);
        throw new IOException("Failed to delete original file after copy");
    }

    public static void forceMkdirParent(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            forceMkdir(parentFile);
        }
    }

    public static void forceMkdir(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IOException("Unable to create directory, as the file exists and is not a directory");
            }
        } else if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("Unable to create directory");
        }
    }

    public static boolean fileExists(String str) {
        try {
            return getFile(str).exists();
        } catch (IOException unused) {
            return false;
        }
    }
}

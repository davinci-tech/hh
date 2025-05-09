package com.huawei.profile.utils;

import android.text.TextUtils;
import com.huawei.profile.utils.file.FileUtils;
import com.huawei.profile.utils.logger.DsLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class ProfileFileUtils {
    private static final String PATH_WHITE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?";
    private static final Pattern PATTERN = Pattern.compile("(.*([/\\\\]{1}[\\.\\.]{1,2}|[\\.\\.]{1,2}[/\\\\]{1}|\\.\\.).*|\\.)");
    private static final String TAG = "ProfileFileUtils";

    private ProfileFileUtils() {
    }

    public static File getFile(String str) throws IOException {
        String canonicalize = canonicalize(str);
        if (canonicalize == null) {
            throw new IOException("get file failed while canonicalizing!");
        }
        if (!isSafePath(canonicalize)) {
            throw new IOException("Invalid file filePath, not safe!");
        }
        String checkFile = checkFile(canonicalize);
        if (checkFile == null) {
            throw new IOException("The file path is empty.");
        }
        return new File(checkFile);
    }

    private static boolean isSafePath(String str) {
        boolean matches = PATTERN.matcher(str).matches();
        if (matches) {
            DsLog.et(TAG, "Invalid file filePath, not safe!", new Object[0]);
        }
        return !matches;
    }

    private static String checkFile(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            while (true) {
                if (i2 >= 94) {
                    break;
                }
                if (str.charAt(i) == PATH_WHITE_LIST.charAt(i2)) {
                    stringBuffer.append(PATH_WHITE_LIST.charAt(i2));
                    break;
                }
                i2++;
            }
        }
        return stringBuffer.toString();
    }

    public static String canonicalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new File(Normalizer.normalize(str, Normalizer.Form.NFKC)).getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }

    public static void deleteFile(String str) throws IOException {
        File file = getFile(str);
        if (file.exists() && file.isFile()) {
            FileUtils.forceDelete(file);
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
            throw new FileNotFoundException("File does not exist: " + file);
        }
        throw new IOException("Unable to delete file: " + file);
    }

    private static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            cleanDirectory(file);
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file.toString());
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
}

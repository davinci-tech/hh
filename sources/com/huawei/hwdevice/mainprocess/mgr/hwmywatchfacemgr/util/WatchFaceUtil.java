package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.healthcloud.PackageInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceParamsResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.encrypt.hash.SHA;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import com.huawei.watchface.utils.ArrayUtils;
import defpackage.cun;
import defpackage.cwi;
import defpackage.dro;
import defpackage.jls;
import defpackage.jpp;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HEXUtils;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class WatchFaceUtil {
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final int CONVERT_RADIX_HEX = 16;
    private static final int INT_BIT_FIRST = 2;
    private static final String MOVE_BACKGROUND_DIR_KEY = "remove_background_dir";
    private static final String MY_WATCH_FACE_MODULE_ID = "my_watch_face";
    private static final String TAG = "WatchFaceUtil";
    private static final String TAG_RELEASE = "DEVMGR_WatchFaceUtil";

    private WatchFaceUtil() {
    }

    public static String joinUrl(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str2.startsWith("/")) {
            str2 = str2.substring(1, str.length());
        }
        return str + "/" + str2;
    }

    public static List<String> asciiNumToStringList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            sb.append((char) it.next().intValue());
        }
        return new ArrayList(Arrays.asList(sb.toString().split(",")));
    }

    public static String asciiNumToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            sb.append((char) it.next().intValue());
        }
        return sb.toString();
    }

    public static String hexListToName(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            sb.append(Integer.toHexString(it.next().intValue()));
        }
        return HEXUtils.d(sb.toString().toUpperCase(Locale.ENGLISH));
    }

    public static List<Integer> hexToIntList(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (!TextUtils.isEmpty(str)) {
            int i = 0;
            while (i < str.length()) {
                int i2 = i + 2;
                arrayList.add(Integer.valueOf(CommonUtil.a(str.substring(i, i2), 16)));
                i = i2;
            }
        }
        return arrayList;
    }

    public static String makeWatchFacePackage(File file) {
        String str = BaseApplication.e().getFilesDir() + WatchFaceConstant.WATCH_FACE_PATH;
        String str2 = BaseApplication.e().getFilesDir() + WatchFaceConstant.WATCH_FACE_PACK_PATH;
        boolean createFileDir = createFileDir(str);
        File file2 = new File(file.getPath().replace(WatchFaceConstant.HWT_SUFFIX, ".zip"));
        boolean renameTo = file.renameTo(file2);
        if (!createFileDir || !renameTo) {
            LogUtil.h(TAG, "makeWatchFacePackage err");
        }
        if (dro.e(file2.getPath(), str) == -1) {
            sqo.an("makeWatchFacePackage unzip watch face package fail");
        }
        return str2;
    }

    public static String makeWatchFaceBinFile(String str) {
        String str2 = BaseApplication.e().getFilesDir() + WatchFaceConstant.WATCH_FACE_PACK_DE_PATH;
        String str3 = BaseApplication.e().getFilesDir() + WatchFaceConstant.WATCH_FACE_BIN_PATH;
        if (!createFileDir(str2)) {
            LogUtil.h(TAG, "makeWatchFaceBinFile err");
        }
        int e = dro.e(str, str2);
        if (e == -1) {
            sqo.an("makeWatchFaceBinFile unzip watch face bin file fail");
        } else if (e == 0) {
            return str;
        }
        return str3;
    }

    public static boolean createFileDir(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static int getFileSize(String str) {
        return (int) new File(str).length();
    }

    public static boolean checkForUpdate(String str, String str2) {
        if (Objects.equals(str, str2)) {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i = 0;
        while (i < Math.max(split.length, split2.length)) {
            int parseInt = i < split.length ? Integer.parseInt(split[i]) : 0;
            int parseInt2 = i < split2.length ? Integer.parseInt(split2[i]) : 0;
            if (parseInt < parseInt2) {
                return true;
            }
            if (parseInt > parseInt2) {
                return false;
            }
            i++;
        }
        return false;
    }

    public static String bitmapToBase64Encode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public static boolean checkObjectIsNull(String str, Object... objArr) {
        if (objArr == null) {
            LogUtil.h(TAG, "checkObjectIsNull objects is null");
            return true;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

    public static File getFile(String str) {
        String c = CommonUtil.c(str);
        if (c == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "filePath is null");
            return null;
        }
        return new File(c);
    }

    public static String getFileDirPath(String str) {
        File file = getFile(str);
        if (file == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "getFileDirPath file is null");
            return "";
        }
        if (file.exists()) {
            return str;
        }
        boolean mkdirs = file.mkdirs();
        ReleaseLogUtil.e(TAG_RELEASE, "getFileDirPath isMkdirs :", Boolean.valueOf(mkdirs));
        return mkdirs ? str : "";
    }

    public static void copyResources(String str, File file) throws IOException {
        File file2 = getFile(str);
        if (file2 == null) {
            ReleaseLogUtil.e(TAG_RELEASE, "copyResources sourceFiles null");
            return;
        }
        if (!file2.exists()) {
            ReleaseLogUtil.e(TAG_RELEASE, "copyResources sourceFiles not exists");
            return;
        }
        if (!file2.isDirectory()) {
            ReleaseLogUtil.e(TAG_RELEASE, "copyResources sourceFiles not Directory");
            return;
        }
        String[] list = file2.list();
        if (CollectionUtils.b(list)) {
            ReleaseLogUtil.e(TAG_RELEASE, "copyResources list isEmpty");
            return;
        }
        for (String str2 : list) {
            FileUtils.d(new File(str, str2), new File(file, str2));
        }
        ReleaseLogUtil.e(TAG_RELEASE, "copyResources sourceFiles null");
    }

    public static void deleteOrRetainBgFile(File file, List<String> list, boolean z) {
        boolean z2;
        if (CollectionUtils.d(list)) {
            if (z) {
                ReleaseLogUtil.e(TAG_RELEASE, "deleteOrRetainBgFile delete all");
                FileUtils.e(file);
                return;
            }
            return;
        }
        File[] listFiles = file.listFiles();
        if (CollectionUtils.b(listFiles)) {
            ReleaseLogUtil.e(TAG_RELEASE, "deleteOrRetainBgFile files is empty");
            return;
        }
        for (File file2 : listFiles) {
            String name = file2.getName();
            if (!name.startsWith(WatchFaceConstant.PRESET_RES)) {
                Iterator<String> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = z;
                        break;
                    }
                    String next = it.next();
                    String substring = next.contains(".") ? next.substring(0, next.indexOf(".")) : next;
                    if (substring.contains("_")) {
                        substring = next.substring(0, next.indexOf("_"));
                    }
                    LogUtil.c(TAG, "deleteOrRetainBgFile filePrefix:", substring, ", name:", name);
                    if (name.startsWith(substring)) {
                        z2 = !z;
                        break;
                    }
                }
                if (z2) {
                    FileUtils.b(file2);
                }
            }
        }
    }

    public static void copyDirectory(File file, File file2) throws IOException {
        if (file.exists() && file.isDirectory()) {
            if (!file2.exists() && !file2.mkdirs()) {
                LogUtil.a(TAG, "copyAndDeleteDir make destDir fail:", file2.getName());
                return;
            }
            LogUtil.a(TAG, "copyDirectory:", file.getAbsolutePath(), " to ", file2.getAbsolutePath());
            if (Objects.equals(file.getAbsolutePath(), file2.getAbsolutePath())) {
                return;
            }
            File[] listFiles = file.listFiles();
            if (ArrayUtils.isEmpty(listFiles)) {
                return;
            }
            for (File file3 : listFiles) {
                File file4 = new File(file2, file3.getName());
                if (file3.isDirectory()) {
                    copyDirectory(file3, file4);
                } else {
                    FileUtils.d(file3, file4);
                }
            }
        }
    }

    public static void copyAndDeleteDir(String str, String str2) throws IOException {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            LogUtil.a(TAG, "copyAndDeleteDir srcDir not exist:", str);
            return;
        }
        File[] listFiles = file.listFiles();
        if (ArrayUtils.isEmpty(listFiles)) {
            LogUtil.a(TAG, "copyAndDeleteDir files is empty", str);
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                copyDirectory(file2, new File(str2, file2.getName()));
                FileUtils.e(file2);
            }
        }
    }

    public static void deleteWatchFaceAlbumBackgroundDir() {
        LogUtil.a(TAG, "deleteWatchFaceAlbumBackgroundDir enter");
        if (!SharedPreferenceManager.a(MY_WATCH_FACE_MODULE_ID, MOVE_BACKGROUND_DIR_KEY, false)) {
            ThreadPoolManager.a(1, 1).d(TAG, new Runnable() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String canonicalPath = BaseApplication.e().getFilesDir().getCanonicalPath();
                        String str = File.separator + "watchfacePhoto" + File.separator;
                        WatchFaceUtil.copyAndDeleteDir((canonicalPath + str) + WatchFaceProvider.BACKGROUND_LABEL + File.separator, (canonicalPath + File.separator + "myWatchFace" + File.separator + str) + WatchFaceProvider.BACKGROUND_LABEL + File.separator);
                        SharedPreferenceManager.e(WatchFaceUtil.MY_WATCH_FACE_MODULE_ID, WatchFaceUtil.MOVE_BACKGROUND_DIR_KEY, true);
                    } catch (Exception e) {
                        LogUtil.b(WatchFaceUtil.TAG, "deleteWatchFaceAlbumBackgroundDir Exception:", ExceptionUtils.d(e));
                    }
                }
            });
        } else {
            LogUtil.a(TAG, "deleteWatchFaceAlbumBackgroundDir dirs already moved");
        }
    }

    public static boolean isSupportMyWatch(DeviceInfo deviceInfo) {
        boolean z = false;
        if (deviceInfo == null) {
            return false;
        }
        if (cwi.c(deviceInfo, 224) && !Utils.l()) {
            z = true;
        }
        ReleaseLogUtil.e(TAG, "isSupportMyWatch is, ", Boolean.valueOf(z));
        return z;
    }

    public static boolean isSupportMyWatch() {
        return isSupportMyWatch(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG));
    }

    public static boolean isSupportWatchFaceMarket() {
        Map<String, String> d = jls.d(GRSManager.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).c());
        boolean z = (TextUtils.isEmpty(d.get("watchFaceH5")) || TextUtils.isEmpty(d.get("watchFace"))) ? false : true;
        ReleaseLogUtil.e(TAG_RELEASE, "isSupportWatchFaceMarket is, ", Boolean.valueOf(z));
        return z;
    }

    public static String generateSyncPackage(File file, PackageInfoResponse.PackageInfo packageInfo) {
        LogUtil.a(TAG, "generateSyncPackage enter");
        String makeWatchFacePackage = makeWatchFacePackage(file);
        String makeWatchFaceBinFile = makeWatchFaceBinFile(makeWatchFacePackage);
        try {
            String str = BaseApplication.e().getFilesDir() + WatchFaceConstant.WATCH_FACE_SYNC_PACKAGE_PATH;
            if (mergeToSyncFileDisk(packageInfo.getWatchFaceId(), packageInfo.getVersion(), str, makeWatchFacePackage, makeWatchFaceBinFile)) {
                return str;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "generateSyncPackage file data error");
            return "";
        } catch (IOException e) {
            ReleaseLogUtil.d(TAG_RELEASE, "generateSyncPackage error:", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String generateFileToSend(DeviceInfo deviceInfo, File file) {
        LogUtil.a(TAG, "generateFileToSend enter");
        String makeWatchFacePackage = makeWatchFacePackage(file);
        return (jpp.e(deviceInfo) && deviceInfo.getPowerSaveModel() == 0) ? makeWatchFacePackage : makeWatchFaceBinFile(makeWatchFacePackage);
    }

    public static boolean isNeedSyncWatchFace(WatchFaceParamsResult watchFaceParamsResult, PackageInfoResponse.PackageInfo packageInfo) {
        LogUtil.a(TAG, "isNeedSyncWatchFace enter");
        String version = packageInfo.getVersion();
        if (TextUtils.isEmpty(version)) {
            LogUtil.a(TAG, "isSupportSync version is empty");
            return false;
        }
        String[] split = version.split("\\.");
        if (split.length < 1 || Integer.parseInt(split[0]) < 2) {
            LogUtil.a(TAG, "isSupportSync isVersionTwo: false");
            return false;
        }
        int supportSync = watchFaceParamsResult.getSupportSync();
        int currentMode = watchFaceParamsResult.getCurrentMode();
        if (supportSync == 1 && currentMode == 0) {
            LogUtil.a(TAG, "isSupportSync true");
            return true;
        }
        LogUtil.a(TAG, "isSupportSync false");
        return false;
    }

    private static boolean mergeToSyncFileDisk(String str, String str2, String str3, String... strArr) throws IOException {
        LogUtil.a(TAG, "mergeToSyncFileDisk enter");
        int length = strArr.length * 30;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        String str4 = BaseApplication.e().getFilesDir() + WatchFaceConstant.WATCH_FACE_SYNC_PACKAGE_HEADER_PATH;
        allocate.put((byte) 1);
        allocate.put((byte) strArr.length);
        allocate.putShort((short) 0);
        int i = 0;
        for (String str5 : strArr) {
            int fileSize = getFileSize(str5);
            allocate.putInt(length + 5 + i);
            allocate.putInt(fileSize);
            allocate.put(!str5.endsWith(WatchFaceConstant.BIN_SUFFIX) ? (byte) 1 : (byte) 0);
            byte[] stringToBytes = stringToBytes(str, 11);
            byte[] stringToBytes2 = stringToBytes(str2, 10);
            allocate.put(stringToBytes);
            allocate.put(stringToBytes2);
            i += fileSize;
        }
        byte[] array = allocate.array();
        byte[] shaEncryptByte = SHA.shaEncryptByte(array, "SHA-256");
        if (shaEncryptByte == null || shaEncryptByte.length == 0) {
            ReleaseLogUtil.d(TAG_RELEASE, "mergeToSyncFileDisk calc checksum fail");
            return false;
        }
        byte b = shaEncryptByte[shaEncryptByte.length - 1];
        LogUtil.a(TAG, "mergeToSyncFileDisk check sum:", Byte.valueOf(b));
        FileOutputStream fileOutputStream = new FileOutputStream(str4);
        try {
            fileOutputStream.write(array);
            fileOutputStream.write(b);
            fileOutputStream.close();
            File file = new File(str3);
            File file2 = new File(str4);
            int length2 = strArr.length + 1;
            File[] fileArr = new File[length2];
            fileArr[0] = file2;
            int i2 = 0;
            while (i2 < strArr.length) {
                int i3 = i2 + 1;
                fileArr[i3] = new File(strArr[i2]);
                i2 = i3;
            }
            FileUtils.a(fileArr, file);
            for (int i4 = 0; i4 < length2; i4++) {
                FileUtils.i(fileArr[i4]);
            }
            return true;
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static byte[] stringToBytes(String str, int i) {
        byte[] bArr = new byte[i];
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, 0, Math.min(i, bytes.length));
        return bArr;
    }
}

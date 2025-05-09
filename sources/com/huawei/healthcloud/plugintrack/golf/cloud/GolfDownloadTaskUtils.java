package com.huawei.healthcloud.plugintrack.golf.cloud;

import com.huawei.haf.application.BaseApplication;
import com.huawei.healthcloud.plugintrack.golf.Utils;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseMapInfo;
import com.huawei.healthcloud.plugintrack.golf.cloud.beans.HandshakeInfo;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GetGolfCourseMapDataReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GetGolfCourseMapDataRsp;
import com.huawei.healthcloud.plugintrack.golf.h5.GolfH5DownloadInterface;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.lqg;
import defpackage.lqi;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class GolfDownloadTaskUtils {
    private static final int AES_HEADER = 9;
    private static final int AES_IV_LENGTH = 12;
    private static final float DOWNLOAD_WEIGHT = 0.5f;
    private static final int SUFFIX_LENGTH = 3;
    private static final String TAG = "Track_GolfDownloadTaskUtils";
    private static GolfEncryptionUtils encryptionUtils;
    private static volatile GolfDownloadTaskUtils instance;
    public static HashMap<Long, GolfDownloadTask> taskMap = new HashMap<>();
    private static final Object LOCK = new Object();
    private static final Map<Long, GolfH5DownloadInterface.InvokerBundle> sDownloadInvokers = new HashMap();
    private static final List<Long> sBgUpdateCourseIds = new ArrayList();

    public enum DeviceType {
        LITE_WEARABLE(0, "lite_wearable"),
        WEARABLE(1, "wearable");

        private final int index;
        private final String type;

        DeviceType(int i, String str) {
            this.index = i;
            this.type = str;
        }

        public static String getType(int i) {
            for (DeviceType deviceType : values()) {
                if (deviceType.getIndex() == i) {
                    return deviceType.type;
                }
            }
            return null;
        }

        public static DeviceType getDeviceType(int i) {
            for (DeviceType deviceType : values()) {
                if (deviceType.getIndex() == i) {
                    return deviceType;
                }
            }
            return null;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public void downloadHandler(long j, int i, int i2, GolfH5DownloadInterface.InvokerBundle invokerBundle, boolean z) {
        String str = BaseApplication.e().getFilesDir() + File.separator + LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011) + File.separator + j + ".zip";
        GetGolfCourseMapDataReq.Builder builder = new GetGolfCourseMapDataReq.Builder();
        DeviceType deviceType = DeviceType.getDeviceType(i);
        builder.addCourseId(Long.valueOf(j)).language(Utils.getLanguage()).type(DeviceType.getType(i)).isAnon(z).mapLevel(Utils.getGolfMapDeviceLevel(TAG));
        if (deviceType == null) {
            LogUtil.d(TAG, "Device Type is null");
            return;
        }
        LogUtil.d(TAG, "Device Type = " + deviceType.type);
        if (deviceType == DeviceType.WEARABLE) {
            GolfEncryptionUtils golfEncryptionUtils = GolfEncryptionUtils.getInstance();
            encryptionUtils = golfEncryptionUtils;
            builder.publicKey(golfEncryptionUtils.getPublicKey());
        }
        GetGolfCourseMapDataRsp golfCourseMapData = CloudManager.getInstance().getGolfCourseMapData(builder.build());
        GolfCourseMapInfo golfCourseMapInfo = new GolfCourseMapInfo();
        if (deviceType == DeviceType.WEARABLE) {
            String decryption = encryptionUtils.decryption(golfCourseMapData.getCourseMapDataList().get(0).getSecretKey());
            golfCourseMapInfo.setIvLength(12);
            golfCourseMapInfo.setAesHeaderLength(9);
            golfCourseMapInfo.setKey(decryption);
            golfCourseMapInfo.setAlgorithm(golfCourseMapData.getCourseMapDataList().get(0).getCryptoAlg());
        }
        golfCourseMapInfo.setCourseId((int) j);
        LogUtil.d(TAG, "Downloading...");
        taskMap.put(Long.valueOf(j), new GolfDownloadTask(golfCourseMapData.getCourseMapDataList().get(0).getUrl(), str));
        if (taskMap.get(Long.valueOf(j)) != null) {
            if (!taskMap.get(Long.valueOf(j)).isNeedCancel()) {
                putDownloadInvoker(invokerBundle, j);
                downloadHelper(taskMap.get(Long.valueOf(j)).getUrl(), golfCourseMapInfo, i2, str, deviceType, z);
                return;
            } else {
                LogUtil.d(TAG, "Task have been canceled, Download Stopped");
                return;
            }
        }
        LogUtil.d(TAG, "Task is null, Download Stopped");
    }

    public void bgUpdateDownloadHandler(long j, int i, int i2) {
        sBgUpdateCourseIds.add(Long.valueOf(j));
        downloadHandler(j, i, i2, null, false);
    }

    private void downloadHelper(String str, final GolfCourseMapInfo golfCourseMapInfo, final int i, String str2, final DeviceType deviceType, final boolean z) {
        ProgressListener<File> progressListener = new ProgressListener<File>() { // from class: com.huawei.healthcloud.plugintrack.golf.cloud.GolfDownloadTaskUtils.1
            @Override // com.huawei.networkclient.ProgressListener
            public void onFinish(File file) {
                int courseId = golfCourseMapInfo.getCourseId();
                long j = courseId;
                if (GolfDownloadTaskUtils.this.isCourseNeedCancel(j)) {
                    GolfDownloadTaskUtils.this.removeCourseId(j);
                    boolean delete = file.delete();
                    Object[] objArr = new Object[3];
                    objArr[0] = "Course is cancel with ";
                    objArr[1] = Integer.valueOf(courseId);
                    objArr[2] = delete ? "success" : ParamConstants.CallbackMethod.ON_FAIL;
                    LogUtil.d(GolfDownloadTaskUtils.TAG, objArr);
                    return;
                }
                if (deviceType == DeviceType.WEARABLE) {
                    GolfDownloadTaskUtils.this.handleWearableFile(file, golfCourseMapInfo, i, z);
                } else {
                    GolfDownloadTaskUtils.this.handleLiteWearableFile(file, golfCourseMapInfo, i, z);
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z2) {
                int i2 = (int) (((j * 1.0d) / j2) * 100.0d);
                int courseId = golfCourseMapInfo.getCourseId();
                GolfH5DownloadInterface.InvokerBundle invoker = GolfDownloadTaskUtils.this.getInvoker(courseId);
                if (invoker == null || i2 % 2 != 0) {
                    return;
                }
                invoker.onSuccess((i2 * 0.5f) + "", courseId, 1);
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                LogUtil.b(GolfDownloadTaskUtils.TAG, "Download Failed: " + th.getMessage());
                GolfH5DownloadInterface.InvokerBundle invoker = GolfDownloadTaskUtils.this.getInvoker((long) golfCourseMapInfo.getCourseId());
                if (invoker != null) {
                    invoker.onFailure(1, th.getMessage(), golfCourseMapInfo.getCourseId());
                }
            }
        };
        File file = new File(str2);
        if (file.exists()) {
            boolean delete = file.delete();
            Object[] objArr = new Object[2];
            objArr[0] = "DeleteFile : ";
            objArr[1] = delete ? "Success" : "Failed";
            LogUtil.d(TAG, objArr);
        }
        boolean mkdirs = file.mkdirs();
        Object[] objArr2 = new Object[2];
        objArr2[0] = "CreateFile : ";
        objArr2[1] = mkdirs ? "Success" : "Failed";
        LogUtil.d(TAG, objArr2);
        lqi.d().d(new lqg(str, null, file, progressListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleLiteWearableFile(File file, GolfCourseMapInfo golfCourseMapInfo, int i, boolean z) {
        String str = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 3) + "bin";
        LogUtil.d(TAG, "UnZipping...");
        GolfZipUtils.unZipBinFile(file, str);
        LogUtil.d(TAG, "unZipBinFile: Zip File Deleted: " + file.delete());
        golfCourseMapInfo.setMapFile(new File(str));
        notifyTransmission(golfCourseMapInfo, i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWearableFile(File file, GolfCourseMapInfo golfCourseMapInfo, int i, boolean z) {
        golfCourseMapInfo.setMapFile(file);
        notifyTransmission(golfCourseMapInfo, i, z);
    }

    private void notifyTransmission(GolfCourseMapInfo golfCourseMapInfo, int i, boolean z) {
        HandshakeInfo handshakeInfo = new HandshakeInfo();
        handshakeInfo.setCourseMapInfo(golfCourseMapInfo);
        handshakeInfo.setMessageId(i);
        GolfDownloadObservable golfDownloadObservable = new GolfDownloadObservable();
        golfDownloadObservable.addObserver(new GolfDownloadObserver(z));
        golfDownloadObservable.setData(handshakeInfo);
    }

    public static GolfDownloadTaskUtils getInstance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new GolfDownloadTaskUtils();
                }
            }
        }
        return instance;
    }

    public void cancelDownloadTask(long j) {
        GolfDownloadTask golfDownloadTask = taskMap.get(Long.valueOf(j));
        if (golfDownloadTask == null) {
            LogUtil.d(TAG, "Download Task for CourseId: " + j + " is not available");
            return;
        }
        golfDownloadTask.setNeedCancel(true);
        String url = golfDownloadTask.getUrl();
        if (url == null) {
            LogUtil.d(TAG, "Download Task haven't Start");
            return;
        }
        if (lqi.d().d(url)) {
            File file = new File(golfDownloadTask.filePath);
            if (file.exists()) {
                boolean delete = file.delete();
                Object[] objArr = new Object[2];
                objArr[0] = "Task Canceled, DeleteFile : ";
                objArr[1] = delete ? "Success" : "Failed";
                LogUtil.d(TAG, objArr);
            }
            removeCourseId(j);
            LogUtil.d(TAG, "Download Task cancel success");
            return;
        }
        LogUtil.d(TAG, "Download Task is finished");
    }

    public boolean isBgUpdate(long j) {
        return sBgUpdateCourseIds.contains(Long.valueOf(j));
    }

    public void removeBgUpdate(long j) {
        sBgUpdateCourseIds.remove(Long.valueOf(j));
        sDownloadInvokers.remove(Long.valueOf(j));
    }

    public void clearBgUpdate() {
        for (Map.Entry<Long, GolfH5DownloadInterface.InvokerBundle> entry : sDownloadInvokers.entrySet()) {
            if (entry.getValue() != null && getInstance().isBgUpdate(entry.getKey().longValue())) {
                sDownloadInvokers.remove(entry.getKey());
            }
        }
        sBgUpdateCourseIds.clear();
    }

    public long[] getBgUpdateCourseIds() {
        long[] jArr = new long[sBgUpdateCourseIds.size()];
        int i = 0;
        while (true) {
            List<Long> list = sBgUpdateCourseIds;
            if (i >= list.size()) {
                return jArr;
            }
            jArr[i] = list.get(i).longValue();
            i++;
        }
    }

    public void putDownloadInvoker(GolfH5DownloadInterface.InvokerBundle invokerBundle, long... jArr) {
        for (long j : jArr) {
            sDownloadInvokers.put(Long.valueOf(j), invokerBundle);
        }
    }

    public GolfH5DownloadInterface.InvokerBundle getInvoker(long j) {
        return sDownloadInvokers.get(Long.valueOf(j));
    }

    public GolfH5DownloadInterface.InvokerBundle getBgUpdateInvoker() {
        for (Map.Entry<Long, GolfH5DownloadInterface.InvokerBundle> entry : sDownloadInvokers.entrySet()) {
            GolfH5DownloadInterface.InvokerBundle value = entry.getValue();
            if (value != null && getInstance().isBgUpdate(entry.getKey().longValue())) {
                return value;
            }
        }
        return null;
    }

    public boolean isCourseDownloading(long j) {
        return (taskMap.isEmpty() || taskMap.get(Long.valueOf(j)) == null) ? false : true;
    }

    public boolean isCourseNeedCancel(long j) {
        return taskMap.get(Long.valueOf(j)) == null || taskMap.get(Long.valueOf(j)).isNeedCancel();
    }

    public void removeCourseId(long j) {
        taskMap.remove(Long.valueOf(j));
    }

    static class GolfDownloadTask {
        private String filePath;
        private boolean needCancel = false;
        private final String url;

        GolfDownloadTask(String str, String str2) {
            this.url = str;
            this.filePath = str2;
        }

        public void setNeedCancel(boolean z) {
            this.needCancel = z;
        }

        public String getFilePath() {
            return this.filePath;
        }

        public void setFilePath(String str) {
            this.filePath = str;
        }

        public String getUrl() {
            return this.url;
        }

        public boolean isNeedCancel() {
            return this.needCancel;
        }
    }
}

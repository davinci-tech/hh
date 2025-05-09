package com.huawei.watchface.mvp.model.filedownload;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.ssl.SSFCompatiableSystemCA;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.ak;
import com.huawei.watchface.an;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.cx;
import com.huawei.watchface.d;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.m;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.FileHelper;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.HwSfpPolicyManagerHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.OperationCallback;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes7.dex */
public class FilePuller {
    private static final int ARRAY_LENGTH = 2;
    private static final int BUFFER_SIZE = 1024;
    public static final int DOWNLOAD_FILE_SIZE_LIMIT = 209715200;
    private static final int INDEX_FIRST = 0;
    private static final int INDEX_SECOND = 1;
    private static final Object LOCK = new Object();
    private static final int MAX_DOWNLOAD_TASK = 5;
    private static final int PAUSE_TASK = 1;
    private static final int RESULT_SUCCESS = 0;
    private static final String SPLIT = "_";
    private static final int STATE_FIND_ERROR = 0;
    private static final int STATE_FIND_NEW = 2;
    private static final int STATE_FIND_OLD = 1;
    private static final String TAG = "HwWatchFace_FilePuller";
    private static FilePuller sInstance;
    private Context mContext;
    private ConcurrentHashMap<String, PullResult> mDownloadingMap = new ConcurrentHashMap<>(32);
    private ConcurrentHashMap<String, PullTask> mWaitQueue = new ConcurrentHashMap<>(32);
    private ConcurrentHashMap<String, PullTask> mPauseQueue = new ConcurrentHashMap<>(32);
    private ConcurrentHashMap<String, PullTask> mWatchFaceDownloadList = new ConcurrentHashMap<>(32);
    private ThreadPoolManager mThreadPoolManager = ThreadPoolManager.make(5, 5);

    private FilePuller(Context context) {
        this.mContext = context;
    }

    public static FilePuller getInstance(Context context) {
        FilePuller filePuller;
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new FilePuller(context.getApplicationContext());
            }
            filePuller = sInstance;
        }
        return filePuller;
    }

    public void addTask(final PullTask pullTask) {
        if (pullTask == null) {
            HwLog.w(TAG, "addTask, task is null");
            return;
        }
        if (this.mDownloadingMap.containsKey(pullTask.fetchUUID())) {
            HwLog.w(TAG, "addTask, the thread multiple: " + pullTask.fetchUUID());
            return;
        }
        pullTask.configStatus(-8);
        PullResult pullResult = new PullResult();
        pullResult.configPulledSize(0);
        pullResult.configUuid(pullTask.fetchUUID());
        pullResult.configStatus(-8);
        HwLog.i(TAG, "addTask, file total size:" + pullTask.fetchTotalSize() + " addTask mWatchFaceDownloadList add ：" + pullTask.fetchUUID());
        pullResult.configTotalSize(pullTask.fetchTotalSize());
        pullResult.setInstallationType(pullTask.getInstallationType());
        this.mWatchFaceDownloadList.put(pullTask.fetchUUID(), pullTask);
        if (this.mDownloadingMap.size() == 5) {
            notifyDownloadStatus(pullTask.fetchUUID(), -8);
            this.mWaitQueue.put(pullTask.fetchUUID(), pullTask);
            pullResult.configStatus(-8);
            pullTask.informResult(pullResult);
            HwLog.i(TAG, "addTask, add wait queue");
            return;
        }
        this.mDownloadingMap.put(pullTask.fetchUUID(), pullResult);
        notifyDownloadStatus(pullTask.fetchUUID(), 7);
        pullResult.configStatus(7);
        pullTask.informResult(pullResult);
        this.mThreadPoolManager.tagExecute(TAG, new Runnable() { // from class: com.huawei.watchface.mvp.model.filedownload.FilePuller.1
            @Override // java.lang.Runnable
            public void run() {
                PullResult pullResult2 = (PullResult) FilePuller.this.mDownloadingMap.get(pullTask.fetchUUID());
                if (pullResult2 != null) {
                    pullResult2.configStatus(0);
                    pullResult2.configPulledSize(0);
                    FilePuller.this.processTask(pullTask, pullResult2);
                }
            }
        });
    }

    public void cancelTask(String str, String str2) {
        cancelTask(str, str2, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void cancelTask(String str, String str2, boolean z) {
        String buildTaskId = buildTaskId(str, str2);
        String f = d.a().f(str);
        String buildTaskId2 = buildTaskId(f, str2);
        HwLog.i(TAG, "cancelTask, taskId:" + buildTaskId + ", installNext: " + z + " aodCancelTaskid:" + buildTaskId2 + " mWatchFaceDownloadList:" + GsonHelper.toJson(this.mWatchFaceDownloadList.keySet()));
        boolean containsKey = this.mWatchFaceDownloadList.containsKey(buildTaskId);
        boolean z2 = containsKey;
        if (this.mWatchFaceDownloadList.containsKey(buildTaskId2)) {
            z2 = containsKey;
            if (!containsKey) {
                z2 = 2;
            }
        }
        if (z2 == 0) {
            HwLog.e(TAG, "cancelTask,STATE_FIND_ERROR");
            return;
        }
        if (z2 == 2) {
            buildTaskId = buildTaskId2;
        }
        if (z2 == 2) {
            str = f;
        }
        HwLog.i(TAG, "cancelTask, hiTopId:" + str + ",version: " + str2 + ",taskId:" + buildTaskId);
        PullTask pullTask = this.mWatchFaceDownloadList.get(buildTaskId);
        if (pullTask == null) {
            HwLog.w(TAG, "cancelTask, task is null");
            return;
        }
        if (this.mDownloadingMap.containsKey(buildTaskId)) {
            HwLog.i(TAG, "cancelTask, cancel task in mDownloadingMap");
            PullResult pullResult = this.mDownloadingMap.get(buildTaskId);
            if (pullResult == null) {
                HwLog.w(TAG, "cancelTask, result is null");
                return;
            } else {
                pullResult.configStatus(-10);
                removeTask(str, str2, z);
            }
        }
        if (pullTask.fetchStatus() == 6) {
            HwLog.i(TAG, "cancelTask, cancel task in installing");
            removeTask(str, str2, z);
        }
        if (this.mWaitQueue.containsKey(buildTaskId)) {
            HwLog.i(TAG, "cancelTask, cancel task in mWaitQueue");
            removeTask(str, str2, z);
        }
        if (this.mPauseQueue.containsKey(buildTaskId)) {
            HwLog.i(TAG, "cancelTask, cancel task in mPauseQueue");
            removeTask(str, str2, z);
        }
        if (pullTask.fetchStatus() == 3) {
            HwLog.i(TAG, "cancelTask, cancel task in pause");
            removeTask(str, str2, z);
        }
    }

    public void removeTask(String str, String str2) {
        removeTask(str, str2, true);
    }

    public void removeTask(String str, String str2, boolean z) {
        HwLog.i(TAG, "removeTask hiTopId:" + str + ",version:" + str2);
        String buildTaskId = buildTaskId(str, str2);
        String buildTaskId2 = buildTaskId(d.a().c(str), str2);
        this.mWatchFaceDownloadList.remove(buildTaskId);
        this.mWatchFaceDownloadList.remove(buildTaskId2);
        if (this.mPauseQueue.containsKey(buildTaskId)) {
            HwLog.i(TAG, "removeTask() mPauseQueue taskId:" + buildTaskId);
            this.mPauseQueue.remove(buildTaskId);
        }
        if (this.mPauseQueue.containsKey(buildTaskId2)) {
            HwLog.i(TAG, "removeTask() mPauseQueue taskId:" + buildTaskId2);
            this.mPauseQueue.remove(buildTaskId2);
        }
        if (this.mWaitQueue.containsKey(buildTaskId)) {
            HwLog.i(TAG, "removeTask() mWaitQueue taskId:" + buildTaskId);
            this.mWaitQueue.remove(buildTaskId);
        }
        if (this.mWaitQueue.containsKey(buildTaskId2)) {
            HwLog.i(TAG, "removeTask() mWaitQueue taskId:" + buildTaskId2);
            this.mWaitQueue.remove(buildTaskId2);
        }
        if (z) {
            downloadSuccessAutoDownloadNextTask(str, str2);
        }
    }

    public void clearTask() {
        HwLog.i(TAG, "clearTask");
        this.mWatchFaceDownloadList.clear();
        this.mPauseQueue.clear();
        this.mWaitQueue.clear();
    }

    public void downloadSuccessAutoDownloadNextTask(String str, String str2) {
        String buildTaskId = buildTaskId(str, str2);
        if (this.mDownloadingMap.containsKey(buildTaskId)) {
            HwLog.i(TAG, "mDownloadingMap remove task, taskId:" + buildTaskId);
            this.mDownloadingMap.remove(buildTaskId);
        }
        if (this.mWaitQueue.isEmpty()) {
            HwLog.w(TAG, "removeTask, mWaitQueue is empty");
            return;
        }
        HwLog.i(TAG, "remove done");
        addTask(this.mWaitQueue.entrySet().iterator().next().getValue());
        this.mWaitQueue.remove(buildTaskId);
    }

    public void operationDownloadTask(String str, String str2, int i, OperationCallback operationCallback) {
        PullResult pullResult;
        HwLog.i(TAG, "operationDownloadTask enter");
        String buildTaskId = buildTaskId(str, str2);
        if (!this.mWatchFaceDownloadList.containsKey(buildTaskId)) {
            HwLog.w(TAG, "downloadOperation, not contain hiTopId");
            return;
        }
        PullTask pullTask = this.mWatchFaceDownloadList.get(buildTaskId);
        if (this.mDownloadingMap.containsKey(buildTaskId) && (pullResult = this.mDownloadingMap.get(buildTaskId)) != null) {
            pullResult.configStatus(i == 1 ? 3 : 4);
        }
        if (i == 1) {
            if (this.mDownloadingMap.containsKey(buildTaskId)) {
                HwLog.i(TAG, "taskId pause：" + buildTaskId);
                this.mPauseQueue.put(buildTaskId, pullTask);
                this.mDownloadingMap.remove(buildTaskId);
            }
            if (this.mWaitQueue.containsKey(buildTaskId)) {
                this.mPauseQueue.put(buildTaskId, pullTask);
                this.mWaitQueue.remove(buildTaskId);
            }
            notifyDownloadStatus(buildTaskId, 3);
            operationCallback.operationResult(0);
            if (this.mWaitQueue.isEmpty() || this.mDownloadingMap.size() >= 5) {
                return;
            }
            addTask(this.mWaitQueue.entrySet().iterator().next().getValue());
            this.mWaitQueue.remove(buildTaskId);
            return;
        }
        if (this.mPauseQueue.containsKey(buildTaskId)) {
            this.mPauseQueue.remove(buildTaskId);
        }
        notifyDownloadStatus(buildTaskId, -8);
        operationCallback.operationResult(0);
    }

    public void notifyDownloadStatus(String str, int i) {
        if (str == null) {
            HwLog.w(TAG, "notifyDownloadStatus, param illegal");
            return;
        }
        if (this.mWatchFaceDownloadList.isEmpty() || !this.mWatchFaceDownloadList.containsKey(str)) {
            HwLog.w(TAG, "notifyDownloadStatus, list is empty or not contain taskId :" + str);
        } else {
            PullTask pullTask = this.mWatchFaceDownloadList.get(str);
            if (pullTask == null) {
                HwLog.w(TAG, "notifyDownloadStatus, task is null");
            } else {
                pullTask.configStatus(i);
            }
        }
    }

    public void notifyAllTaskPause() {
        HwLog.i(TAG, "notifyAllTaskPause");
        if (this.mWatchFaceDownloadList.isEmpty()) {
            HwLog.w(TAG, "notifyAllTaskPause, mWatchFaceDownloadList is empty");
            return;
        }
        for (Map.Entry<String, PullTask> entry : this.mWatchFaceDownloadList.entrySet()) {
            HwLog.d(TAG, "notifyAllTaskPause, taskId:" + entry.getKey());
            notifyDownloadStatus(entry.getKey(), 3);
        }
    }

    public void notifyMapTaskPause(LinkedHashMap<String, String> linkedHashMap, int i) {
        HwLog.i(TAG, "notifyMapTaskPause");
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            HwLog.w(TAG, "notifyMapTaskPause, map is empty");
            return;
        }
        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            HwLog.d(TAG, "notifyMapTaskPause, taskId:" + entry.getKey());
            notifyDownloadStatus(buildTaskId(entry.getKey(), entry.getValue()), i);
        }
    }

    public ConcurrentHashMap<String, PullTask> getDownloadTaskList() {
        HwLog.i(TAG, "getDownloadTaskList() enter.");
        String j = WatchFacePhotoAlbumManager.getInstance(this.mContext).j();
        String videoPackageName = HwWatchFaceBtManager.getInstance(this.mContext).getVideoPackageName();
        String wearPackageName = HwWatchFaceBtManager.getInstance(this.mContext).getWearPackageName();
        String kaleidoscopePackageName = HwWatchFaceBtManager.getInstance(this.mContext).getKaleidoscopePackageName();
        Iterator<Map.Entry<String, PullTask>> it = this.mWatchFaceDownloadList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, PullTask> next = it.next();
            if ((!TextUtils.isEmpty(j) && next.getKey().contains(j)) || ((!TextUtils.isEmpty(videoPackageName) && next.getKey().contains(videoPackageName)) || ((!TextUtils.isEmpty(wearPackageName) && next.getKey().contains(wearPackageName)) || (!TextUtils.isEmpty(kaleidoscopePackageName) && next.getKey().contains(kaleidoscopePackageName))))) {
                it.remove();
            }
        }
        return this.mWatchFaceDownloadList;
    }

    public void setDownloadTaskList(List<PullTask> list) {
        HwLog.i(TAG, "setDownloadTaskList() enter.");
        if (list == null || list.isEmpty()) {
            HwLog.e(TAG, "setDownloadTaskList() param illegal.");
            return;
        }
        this.mWatchFaceDownloadList.clear();
        for (PullTask pullTask : list) {
            if (pullTask != null && pullTask.fetchUUID() != null) {
                pullTask.configStatus(3);
                HwLog.i(TAG, "setDownloadTaskList mWatchFaceDownloadList add where 1 fetchUUID：" + pullTask.fetchUUID());
                this.mWatchFaceDownloadList.put(pullTask.fetchUUID(), pullTask);
            } else {
                HwLog.e(TAG, "setDownloadTaskList() task is null.");
            }
        }
        HwLog.i(TAG, "setDownloadTaskList() complete.");
    }

    public String buildTaskId(String str, String str2) {
        if (str == null || str2 == null) {
            HwLog.w(TAG, "buildTaskId() param illegal");
            return "";
        }
        return str + "_" + str2;
    }

    public int getDownloadingMapTotalSize() {
        ConcurrentHashMap<String, PullTask> concurrentHashMap = this.mWatchFaceDownloadList;
        int i = 0;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            HwLog.w(TAG, "getDownloadingMapTotalSize, mWatchFaceDownloadList is empty");
            return 0;
        }
        String j = WatchFacePhotoAlbumManager.getInstance(this.mContext).j();
        String videoPackageName = HwWatchFaceBtManager.getInstance(this.mContext).getVideoPackageName();
        String wearPackageName = HwWatchFaceBtManager.getInstance(this.mContext).getWearPackageName();
        String kaleidoscopePackageName = HwWatchFaceBtManager.getInstance(this.mContext).getKaleidoscopePackageName();
        for (Map.Entry<String, PullTask> entry : this.mWatchFaceDownloadList.entrySet()) {
            if (!entry.getKey().contains(j) && !entry.getKey().contains(videoPackageName) && !entry.getKey().contains(wearPackageName) && !entry.getKey().contains(kaleidoscopePackageName)) {
                i += entry.getValue().fetchTotalSize();
            }
        }
        HwLog.i(TAG, "getDownloadingMapTotalSize, total:" + i);
        return i;
    }

    public int getDownloadTaskStatus(String str) {
        HwLog.i(TAG, "enter getInstallTaskStatus");
        if (str == null) {
            HwLog.w(TAG, "getDownloadTaskStatus, taskId is null");
            return -100;
        }
        if (this.mWatchFaceDownloadList.containsKey(str)) {
            return this.mWatchFaceDownloadList.get(str).fetchStatus();
        }
        return -100;
    }

    public int getCurrentTaskStatus(String str) {
        if (str == null) {
            HwLog.w(TAG, "getCurrentTaskStatus, taskId is null");
            return -100;
        }
        PullTask pullTask = this.mWatchFaceDownloadList.get(str);
        if (!this.mWatchFaceDownloadList.containsKey(str) || pullTask == null) {
            HwLog.w(TAG, "getCurrentTaskStatus, result is empty");
            return -100;
        }
        int fetchStatus = pullTask.fetchStatus();
        HwLog.i(TAG, "getCurrentTaskStatus, status" + fetchStatus);
        return fetchStatus;
    }

    private String getPathName(PullTask pullTask) {
        HwLog.i(TAG, "enter getPathName");
        return pullTask.fetchDestPath() + ".tmp";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processTask(PullTask pullTask, PullResult pullResult) {
        if (pullTask == null || pullResult == null) {
            HwLog.w(TAG, "processTask() illegal param");
            return;
        }
        if (!pullTask.fetchUUID().equals(pullResult.fetchUuid())) {
            HwLog.w(TAG, "processTask() task id : " + pullTask.fetchUUID() + " , result id :" + pullResult.fetchUuid());
            return;
        }
        pullResult.configPathName(getPathName(pullTask));
        String fileUrl = getFileUrl(pullTask);
        if (fileUrl == null) {
            HwLog.w(TAG, "processTask() fileUrlJson is null");
            pullResult.configStatus(-5);
        }
        pullResult.configFileUrlJson(fileUrl);
        pullTask.configHttpUrl(fileUrl);
        int downloadFile = downloadFile(pullTask, pullResult);
        HwLog.w(TAG, "processTask() downloadResult: " + downloadFile);
        if (downloadFile == 0) {
            pullResult.configStatus(1);
            if (!hashCodeCheck(pullTask, pullResult)) {
                HwLog.w(TAG, "processTask() hashcode checkout failure");
                return;
            }
            HwLog.i(TAG, "processTask() resource type : " + pullTask.fetchOption());
            doUnzip(pullTask, pullResult);
        } else {
            pullResult.configStatus(downloadFile);
        }
        HwLog.i(TAG, "processTask() result status: " + pullResult.fetchStatus());
        pullTask.informResult(pullResult);
    }

    private boolean hashCodeCheck(PullTask pullTask, PullResult pullResult) {
        String fetchDigest = pullTask.fetchDigest();
        String sha256File = FileHelper.sha256File(pullResult.fetchPathName());
        HwLog.i(TAG, "processTask() digest is :" + fetchDigest + " ,hashcode is :" + sha256File);
        if (TextUtils.isEmpty(fetchDigest) || fetchDigest.equalsIgnoreCase(sha256File)) {
            return true;
        }
        pullResult.configStatus(-11);
        deleteFile(pullResult);
        pullTask.informResult(pullResult);
        return false;
    }

    private void deleteFile(PullResult pullResult) {
        if (pullResult == null) {
            HwLog.w(TAG, "deleteFile result is null");
        } else {
            deleteFile(pullResult.fetchPathName());
        }
    }

    private void deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(TAG, "deleteFile pathName is empty");
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            HwLog.i(TAG, "deleteFile is : " + file.delete());
        }
    }

    private void doUnzip(PullTask pullTask, PullResult pullResult) {
        HwLog.i(TAG, "doUnzip() enter.");
        if ((pullTask.fetchOption() & 4) != 0) {
            HwLog.i(TAG, "doUnzip() -- to decrypt file");
            decrptFile(pullTask, pullResult);
            deleteFile(pullResult.fetchPathName());
            return;
        }
        removeFile(pullResult.fetchPathName(), pullTask.fetchDestPath());
    }

    private void decrptFile(PullTask pullTask, PullResult pullResult) {
        Map<String, byte[]> a2;
        String fetchPathName = pullResult.fetchPathName();
        pullResult.addDescInfo("sourceFile=" + fetchPathName);
        if (TextUtils.isEmpty(fetchPathName)) {
            HwLog.e(TAG, "sourceFile is null");
            return;
        }
        String filterFilePath = CommonUtils.filterFilePath(fetchPathName);
        if (FileHelper.b(filterFilePath)) {
            HwLog.e(TAG, "sourceFile is IllegalPath");
            return;
        }
        byte[] a3 = ak.a(filterFilePath);
        if (a3 == null || a3.length <= 0) {
            HwLog.e(TAG, "decryptFile failure");
            pullResult.configStatus(-12);
            return;
        }
        byte[] a4 = m.a().a(a3);
        StringBuilder sb = new StringBuilder("comHuaweiWatchface is null=");
        sb.append(a4 == null);
        pullResult.addDescInfo(sb.toString());
        if (a4 == null || a4.length <= 0) {
            HwLog.i(TAG, "decryptFile failure <= 0");
            pullResult.configStatus(-6);
            return;
        }
        HwLog.i(TAG, "decrptFile com.huawei.watchface.length: " + a4.length);
        String fetchUUID = pullTask.fetchUUID();
        HwLog.i(TAG, "uuid: " + fetchUUID);
        pullResult.addDescInfo("uuid=" + fetchUUID);
        String[] split = fetchUUID.split("_");
        String str = split[0];
        String str2 = split[1];
        boolean isSupportSync = HwWatchFaceBtManager.getInstance(this.mContext).isSupportSync();
        boolean i = t.a().i(str, str2);
        boolean d = an.d(str2);
        HwLog.i(TAG, "decrypt, sync watchface: " + isSupportSync);
        HwLog.i(TAG, "decrypt, tryout task: " + i);
        HwLog.i(TAG, "decrypt,isVersionTwo:" + d);
        pullResult.addDescInfo("isSyncWatchFace=" + isSupportSync);
        pullResult.addDescInfo("isTryOutTask=" + i);
        if (isSupportSync && !i && d) {
            a2 = m.a().a(a4, str, str2);
        } else {
            a2 = m.a().a(this.mContext, a4, pullTask.fetchZip());
        }
        cx.a().a(fetchUUID, a2);
    }

    private int downloadFile(PullTask pullTask, PullResult pullResult) {
        if (!TextUtils.isEmpty(pullTask.fetchHttpUrl())) {
            if (isHttpProtocol(pullTask.fetchHttpUrl())) {
                HwLog.i(TAG, "download http");
                pullTask.configHttpUrl(WatchFaceHttpUtil.e(pullTask.fetchHttpUrl()));
            } else {
                HwLog.i(TAG, "download https");
            }
            return httpsDownloadFile(pullTask, pullResult);
        }
        HwLog.w(TAG, "http url isEmpty");
        return -13;
    }

    private String getFileUrl(PullTask pullTask) {
        String fetchFileUrlJson = pullTask.fetchFileUrlJson();
        return !TextUtils.isEmpty(fetchFileUrlJson) ? fetchFileUrlJson : pullTask.fetchHttpUrl();
    }

    private int httpDownloadFile(PullTask pullTask, PullResult pullResult) {
        HttpURLConnection configHttpUrlConnection = configHttpUrlConnection(pullTask);
        if (configHttpUrlConnection == null) {
            HwLog.w(TAG, "httpDownloadFile httpUrlConnection is null");
            return PullResult.REQUEST_URL_EXCEPTION;
        }
        return doHttpDownloadFile(configHttpUrlConnection, pullTask, pullResult);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f6, code lost:
    
        r5.flush();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00fd, code lost:
    
        if (r13.fetchStatus() != (-10)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ff, code lost:
    
        com.huawei.watchface.utils.HwLog.i(com.huawei.watchface.mvp.model.filedownload.FilePuller.TAG, "cancel download task");
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0104, code lost:
    
        closeStream(r4);
        closeStream(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x010a, code lost:
    
        if (r11 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010c, code lost:
    
        r11.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x010f, code lost:
    
        return -10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0110, code lost:
    
        closeStream(r4);
        closeStream(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0116, code lost:
    
        if (r11 == null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0118, code lost:
    
        r11.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011b, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0163  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int doHttpDownloadFile(java.net.HttpURLConnection r11, com.huawei.watchface.mvp.model.filedownload.PullTask r12, com.huawei.watchface.mvp.model.filedownload.PullResult r13) {
        /*
            Method dump skipped, instructions count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.mvp.model.filedownload.FilePuller.doHttpDownloadFile(java.net.HttpURLConnection, com.huawei.watchface.mvp.model.filedownload.PullTask, com.huawei.watchface.mvp.model.filedownload.PullResult):int");
    }

    private HttpURLConnection configHttpUrlConnection(PullTask pullTask) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(pullTask.fetchHttpUrl()).openConnection());
            httpURLConnection.setConnectTimeout(7000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            return httpURLConnection;
        } catch (IOException unused) {
            HwLog.e(TAG, "configHttpUrlConnection IOEexception");
            return null;
        }
    }

    private boolean isValidFile(File file) {
        if (file.exists() && file.isFile()) {
            HwLog.i(TAG, "deleteFo is : " + file.delete());
        }
        if (!file.getParentFile().exists()) {
            HwLog.i(TAG, "makeParentFile is :" + file.getParentFile().mkdirs());
        }
        if (file.isFile()) {
            return true;
        }
        try {
            HwLog.i(TAG, "createNewFile is :" + file.createNewFile());
            return true;
        } catch (IOException unused) {
            HwLog.e(TAG, "createNewFile error");
            return false;
        }
    }

    private int httpsDownloadFile(PullTask pullTask, PullResult pullResult) {
        HttpsURLConnection configHttpsUrlConnection = configHttpsUrlConnection(pullTask);
        if (configHttpsUrlConnection == null) {
            HwLog.w(TAG, "httpsDownloadFile error httpsURLConnection is null");
            return PullResult.REQUEST_URL_EXCEPTION;
        }
        return doHttpsDownloadFile(configHttpsUrlConnection, pullTask, pullResult);
    }

    private int doHttpsDownloadFile(HttpsURLConnection httpsURLConnection, PullTask pullTask, PullResult pullResult) {
        Closeable closeable;
        Closeable closeable2;
        Closeable closeable3;
        FileOutputStream fileOutputStream;
        Closeable closeable4 = null;
        try {
            try {
                int responseCode = httpsURLConnection.getResponseCode();
                if (responseCode != 200) {
                    HwLog.w(TAG, "httpsDownloadFile ConRspCode:" + responseCode);
                    closeStream(null);
                    closeStream(null);
                    if (httpsURLConnection == null) {
                        return -3;
                    }
                    httpsURLConnection.disconnect();
                    return -3;
                }
                if (FileHelper.b(pullResult.fetchPathName())) {
                    closeStream(null);
                    closeStream(null);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return -14;
                }
                File file = new File(pullResult.fetchPathName());
                if (!isValidFile(file)) {
                    closeStream(null);
                    closeStream(null);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return -14;
                }
                InputStream inputStream = httpsURLConnection.getInputStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e) {
                    e = e;
                } catch (Exception e2) {
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                        pullResult.configUuid(pullResult.fetchUuid());
                        pullResult.configPulledSize(pullResult.fetchPulledSize() + read);
                        if (pullResult.fetchPulledSize() > 209715200) {
                            HwLog.d(TAG, "fetchPulledSize is Exceeded the limit");
                            closeStream(inputStream);
                            closeStream(fileOutputStream);
                            if (httpsURLConnection != null) {
                                httpsURLConnection.disconnect();
                            }
                            return -15;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        if (cancelDownloadTask(pullTask, pullResult)) {
                            break;
                        }
                    }
                    HwLog.i(TAG, "doHttpsDownloadFile, PulledSize: " + pullResult.fetchPulledSize() + ", result fetchUuid(): " + pullResult.fetchUuid());
                    fileOutputStream.flush();
                    HwSfpPolicyManagerHelper.setDefaultCeLabel(file);
                    if (pullResult.fetchStatus() == -10) {
                        HwLog.i(TAG, "cancle download task");
                        closeStream(inputStream);
                        closeStream(fileOutputStream);
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        return -10;
                    }
                    if (pullResult.fetchStatus() != 3) {
                        closeStream(inputStream);
                        closeStream(fileOutputStream);
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        return 0;
                    }
                    HwLog.i(TAG, "pause download task");
                    closeStream(inputStream);
                    closeStream(fileOutputStream);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return 3;
                } catch (IOException e3) {
                    e = e3;
                    closeable4 = fileOutputStream;
                    closeable3 = closeable4;
                    closeable4 = inputStream;
                    HwLog.e(TAG, "doHttpsDownloadFile download IOEexception" + HwLog.printException((Exception) e));
                    pullResult.addDescInfo(e.toString());
                    closeStream(closeable4);
                    closeStream(closeable3);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return -15;
                } catch (Exception e4) {
                    e = e4;
                    closeable4 = fileOutputStream;
                    closeable2 = closeable4;
                    closeable4 = inputStream;
                    HwLog.e(TAG, "doHttpsDownloadFile download exception" + HwLog.printException(e));
                    pullResult.addDescInfo(e.toString());
                    closeStream(closeable4);
                    closeStream(closeable2);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return -15;
                } catch (Throwable th2) {
                    th = th2;
                    closeable4 = fileOutputStream;
                    closeable = closeable4;
                    closeable4 = inputStream;
                    closeStream(closeable4);
                    closeStream(closeable);
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e5) {
            e = e5;
            closeable3 = null;
        } catch (Exception e6) {
            e = e6;
            closeable2 = null;
        } catch (Throwable th4) {
            th = th4;
            closeable = null;
        }
    }

    private boolean cancelDownloadTask(PullTask pullTask, PullResult pullResult) {
        if (pullResult.fetchStatus() == -10) {
            String[] split = pullTask.fetchUUID().split("_");
            if (split.length >= 2) {
                removeTask(split[0], split[1]);
            }
            HwLog.i(TAG, "cancelDownloadTask, PulledSize :" + pullResult.fetchPulledSize() + " , result id :" + pullResult.fetchUuid() + " , task id :" + pullTask.fetchUUID() + ", fetchStatus: " + pullResult.fetchStatus());
            return true;
        }
        if (pullResult.fetchStatus() == 3) {
            HwLog.i(TAG, "cancelDownloadTask, download break  , task id :" + pullTask.fetchUUID() + ", fetchStatus: " + pullResult.fetchStatus());
            return true;
        }
        pullTask.informResult(pullResult);
        return false;
    }

    private HttpsURLConnection configHttpsUrlConnection(PullTask pullTask) {
        try {
            URL url = new URL(pullTask.fetchHttpUrl());
            SSFCompatiableSystemCA sSFCompatiableSystemCA = SSFCompatiableSystemCA.getInstance(Environment.getApplicationContext(), EncryptUtil.genSecureRandom());
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
            httpsURLConnection.setSSLSocketFactory(sSFCompatiableSystemCA);
            httpsURLConnection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
            httpsURLConnection.setConnectTimeout(7000);
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setRequestMethod("GET");
            return httpsURLConnection;
        } catch (IOException unused) {
            HwLog.e(TAG, "configHttpUrlConnection IOEexception.");
            return null;
        } catch (KeyManagementException unused2) {
            HwLog.e(TAG, "configHttpUrlConnection KeyManagementException.");
            return null;
        } catch (KeyStoreException unused3) {
            HwLog.e(TAG, "configHttpUrlConnection KeyStoreException.");
            return null;
        } catch (NoSuchAlgorithmException unused4) {
            HwLog.e(TAG, "configHttpUrlConnection NoSuchAlgorithmException.");
            return null;
        } catch (CertificateException unused5) {
            HwLog.e(TAG, "configHttpUrlConnection CertificateException.");
            return null;
        } catch (Exception e) {
            HwLog.e(TAG, "configHttpUrlConnection exception:" + HwLog.printException(e));
            return null;
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                HwLog.e(TAG, "close stream IoException");
            }
        }
    }

    private void removeFile(String str, String str2) {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File file2 = new File(str2);
        Closeable closeable = null;
        try {
            if (file2.exists()) {
                HwLog.i(TAG, "removeFile() deleteFile is : " + file2.delete());
            }
            if (!file2.getParentFile().exists()) {
                HwLog.i(TAG, "removeFile() mkdirFile is : " + file2.getParentFile().mkdirs());
            }
            if (!file2.isFile()) {
                HwLog.i(TAG, "removeFile() createNewFile is : " + file2.createNewFile());
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (IOException unused) {
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                for (int read = fileInputStream.read(bArr); read != -1; read = fileInputStream.read(bArr)) {
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                HwSfpPolicyManagerHelper.setDefaultCeLabel(str2);
                if (file.exists() && file.isFile()) {
                    HwLog.i(TAG, "removeFile() isDeleteSuc: " + file.delete());
                }
                closeStream(fileInputStream);
            } catch (IOException unused2) {
                closeable = fileInputStream;
                try {
                    HwLog.e(TAG, "removeFile() remove file IoException");
                    if (file.exists() && file.isFile()) {
                        HwLog.i(TAG, "removeFile() isDeleteSuc: " + file.delete());
                    }
                    closeStream(closeable);
                    closeStream(fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    if (file.exists() && file.isFile()) {
                        HwLog.i(TAG, "removeFile() isDeleteSuc: " + file.delete());
                    }
                    closeStream(closeable);
                    closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                closeable = fileInputStream;
                if (file.exists()) {
                    HwLog.i(TAG, "removeFile() isDeleteSuc: " + file.delete());
                }
                closeStream(closeable);
                closeStream(fileOutputStream);
                throw th;
            }
        } catch (IOException unused3) {
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
        closeStream(fileOutputStream);
    }

    public void downloadSuccessRemoveTask(String str, String str2) {
        HwLog.i(TAG, "enter downloadSuccessRemoveTask");
        String buildTaskId = buildTaskId(str, str2);
        if (this.mPauseQueue.containsKey(buildTaskId)) {
            HwLog.i(TAG, "mPauseQueue remove task, taskId:" + buildTaskId);
            this.mPauseQueue.remove(buildTaskId);
        }
        if (this.mWaitQueue.containsKey(buildTaskId)) {
            HwLog.i(TAG, "mWaitQueue remove task, taskId:" + buildTaskId);
            this.mWaitQueue.remove(buildTaskId);
        }
        downloadSuccessAutoDownloadNextTask(str, str2);
    }

    public void installSuccessRemoveTask(String str, String str2) {
        HwLog.i(TAG, "enter installSuccessRemoveTask");
        String buildTaskId = buildTaskId(str, str2);
        if (!this.mWatchFaceDownloadList.containsKey(buildTaskId)) {
            HwLog.w(TAG, "removeTask, no contain hiTopId");
            d.a().a(str, str2, this.mWatchFaceDownloadList);
        } else {
            HwLog.i(TAG, "mWatchFaceDownloadList remove where3 hiTopId:" + str);
            this.mWatchFaceDownloadList.remove(buildTaskId);
        }
    }

    private static boolean isHttpProtocol(String str) {
        int indexOf = str.indexOf(":");
        if (indexOf == -1) {
            return false;
        }
        return SafeString.substring(str, 0, indexOf).equalsIgnoreCase("http");
    }
}

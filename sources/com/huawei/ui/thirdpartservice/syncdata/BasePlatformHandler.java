package com.huawei.ui.thirdpartservice.syncdata;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.route.TrackInfo;
import com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler;
import com.huawei.ui.thirdpartservice.syncdata.TaskPool;
import com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import com.huawei.ui.thirdpartservice.syncdata.oauth.OauthManager;
import defpackage.sjs;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes7.dex */
public abstract class BasePlatformHandler implements TaskPool.TaskPoolListener {
    private static final String TAG = "BasePlatformHandler";
    private final int MINI_TRACK_SIZE = 2;
    protected Context mContext;
    private OauthManager mOauthManager;
    private SyncResultListener mResultListener;
    private TaskPool mTaskPool;

    public interface GenerateSyncDataTaskFactory {
        SyncTask generateSyncDataTask(HiHealthData hiHealthData, File file);
    }

    public interface SyncResultListener {
        void syncResultCallback(String str, boolean z, String str2);
    }

    protected abstract GenerateSyncDataTaskFactory getGenerateSyncDataTaskFactory();

    public abstract String getGpxType(int i);

    protected abstract OauthManager getOauthManager();

    protected abstract String getPlatform();

    public abstract Set<Integer> getSyncDataType();

    protected abstract String getSynchronizedTimeStampsKey();

    public BasePlatformHandler(Context context, SyncResultListener syncResultListener) {
        this.mResultListener = syncResultListener;
        this.mContext = context.getApplicationContext();
    }

    protected void init() {
        this.mTaskPool = new TaskPool(getSynchronizedTimeStampsKey(), this);
        this.mOauthManager = getOauthManager();
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.TaskPool.TaskPoolListener
    public void onTaskFinish() {
        ReleaseLogUtil.e(TAG, getPlatform(), " Sync finish!");
        syncComplete(getPlatform(), true, "Sync finish!");
    }

    public void checkConnectStatus(final RefreshTokenCallback refreshTokenCallback) {
        this.mOauthManager.checkConnectStatus(new RefreshTokenCallback() { // from class: com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler.2
            @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
            public void refreshResult(boolean z, boolean z2) {
                LogUtil.a(BasePlatformHandler.TAG, BasePlatformHandler.this.getPlatform(), "refreshResult:", Boolean.valueOf(z), " - ", Boolean.valueOf(z2));
                if (z && z2) {
                    if (TextUtils.isEmpty(BasePlatformHandler.this.mOauthManager.getAccessToken())) {
                        BasePlatformHandler.this.mOauthManager.refreshAccessToken(this);
                        return;
                    } else {
                        refreshTokenCallback.refreshResult(z, z2);
                        return;
                    }
                }
                refreshTokenCallback.refreshResult(z, z2);
                BasePlatformHandler basePlatformHandler = BasePlatformHandler.this;
                basePlatformHandler.syncComplete(basePlatformHandler.getPlatform(), true, "Not Connected");
            }
        });
    }

    void runSyncTask(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData, TrackInfo trackInfo) {
        SyncTask generateSyncDataTask = getGenerateSyncDataTaskFactory().generateSyncDataTask(hiHealthData, createGpxFile(hiHealthData, hiTrackMetaData, trackInfo));
        if (trackInfo.getLbsInfo().getRealSize() < 2) {
            LogUtil.h(TAG, getPlatform(), " sportDataException ");
            generateSyncDataTask.sportDataException();
        }
        LogUtil.a(TAG, getPlatform(), " runSyncTAsk ");
        this.mTaskPool.d(generateSyncDataTask);
    }

    private File createGpxFile(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData, TrackInfo trackInfo) {
        if (trackInfo == null || trackInfo.getLbsInfo() == null || !trackInfo.getLbsInfo().isValid()) {
            ReleaseLogUtil.d(TAG, "Lbs invalid data. ");
            return null;
        }
        try {
            File gpxFolder = getGpxFolder();
            if (!gpxFolder.exists() && !gpxFolder.mkdirs()) {
                ReleaseLogUtil.c(TAG, "create folder error.");
                return null;
            }
            File c = sjs.c(hiHealthData, hiTrackMetaData, trackInfo, getGpxType(hiHealthData.getSubType()), gpxFolder.getCanonicalPath() + File.separator + hiHealthData.getStartTime() + "_" + getPlatform() + "_SyncData.gpx");
            if (c == null) {
                LogUtil.h(TAG, "Generate gpx file null.");
                return null;
            }
            ReleaseLogUtil.e(TAG, "Generate gpx  file size：", Long.valueOf(c.length()));
            return c;
        } catch (IOException | NumberFormatException | XmlPullParserException e) {
            LogUtil.h(TAG, "Generate gpx file error: ", e.getMessage());
            return null;
        }
    }

    private File getGpxFolder() throws IOException {
        return new File(CommonUtil.c(BaseApplication.e().getFilesDir().getCanonicalPath()), SyncDataConstant.SYNC_DATA_TRACKS_FOLDER + File.separator + SyncDataConstant.SYNC_DATA_TRACKS_EXPORT_FOLDER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncComplete(String str, boolean z, String str2) {
        this.mResultListener.syncResultCallback(str, z, str2);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.TaskPool.TaskPoolListener
    public void tokenInvalidation() {
        this.mOauthManager.refreshAccessToken(new RefreshTokenCallback() { // from class: sih
            @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
            public final void refreshResult(boolean z, boolean z2) {
                BasePlatformHandler.this.m868xa1550e33(z, z2);
            }
        });
    }

    /* renamed from: lambda$tokenInvalidation$0$com-huawei-ui-thirdpartservice-syncdata-BasePlatformHandler, reason: not valid java name */
    public /* synthetic */ void m868xa1550e33(boolean z, boolean z2) {
        if (z && z2) {
            this.mTaskPool.e();
            return;
        }
        this.mTaskPool.a();
        sjs.d(OperationKey.HEALTH_APP_SYNC_DATA_80070005.value(), "", getPlatform(), "", "refresh token failed.");
        syncComplete(getPlatform(), false, "refresh token error!");
    }

    private boolean isAlreadySync(HiHealthData hiHealthData) {
        return this.mTaskPool.b(hiHealthData);
    }

    public void release() {
        this.mTaskPool.a();
    }

    void forceStopSync() {
        this.mTaskPool.b();
    }

    long getOauthTime() {
        return getOauthManager().getOauthTime();
    }

    public boolean needUpload(HiHealthData hiHealthData) {
        boolean contains = getSyncDataType().contains(Integer.valueOf(hiHealthData.getSubType()));
        boolean isAlreadySync = isAlreadySync(hiHealthData);
        boolean z = hiHealthData.getStartTime() <= getOauthTime();
        LogUtil.a(TAG, getClass().getSimpleName(), " hiHealthData return startTime:", Long.valueOf(hiHealthData.getStartTime()), " type:", Integer.valueOf(hiHealthData.getType()));
        LogUtil.a(TAG, getClass().getSimpleName(), " typeNeedUpload:", Boolean.valueOf(contains), " isAlreadySync:", Boolean.valueOf(isAlreadySync), " isBeforeOauth：", Boolean.valueOf(z));
        return (!contains || isAlreadySync || z) ? false : true;
    }

    boolean isSyncFinish() {
        return this.mTaskPool.d();
    }
}

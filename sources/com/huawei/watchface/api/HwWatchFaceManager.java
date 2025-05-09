package com.huawei.watchface.api;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.secure.android.common.intent.SafeBroadcastReceiver;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.R$string;
import com.huawei.watchface.aa;
import com.huawei.watchface.ac;
import com.huawei.watchface.ao;
import com.huawei.watchface.ap;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.ba;
import com.huawei.watchface.cl;
import com.huawei.watchface.cm;
import com.huawei.watchface.cn;
import com.huawei.watchface.cv;
import com.huawei.watchface.cx;
import com.huawei.watchface.d;
import com.huawei.watchface.db;
import com.huawei.watchface.dg;
import com.huawei.watchface.di;
import com.huawei.watchface.dl;
import com.huawei.watchface.dp;
import com.huawei.watchface.ds;
import com.huawei.watchface.ee;
import com.huawei.watchface.eg;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.eo;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.k;
import com.huawei.watchface.manager.HwDeviceConfigManager;
import com.huawei.watchface.manager.WatchFacePhotoAlbumManager;
import com.huawei.watchface.mvp.model.datatype.AbilityListForH5;
import com.huawei.watchface.mvp.model.datatype.CustomWatchfaceInfo;
import com.huawei.watchface.mvp.model.datatype.DeviceInfo;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.ScreenInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceInfoForH5;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSupportInfo;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.filedownload.FilePuller;
import com.huawei.watchface.mvp.model.filedownload.PullListenerInterface;
import com.huawei.watchface.mvp.model.filedownload.PullResult;
import com.huawei.watchface.mvp.model.filedownload.PullTask;
import com.huawei.watchface.mvp.model.filedownload.threadpool.ThreadPoolManager;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.n;
import com.huawei.watchface.p;
import com.huawei.watchface.q;
import com.huawei.watchface.t;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.DelectUserDesignationWatchFaceCallback;
import com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback;
import com.huawei.watchface.utils.callback.IBaseResponseCallback;
import com.huawei.watchface.utils.callback.IFileTransferStateCallback;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.callback.OperationCallback;
import com.huawei.watchface.videoedit.sysc.Consumer;
import com.huawei.watchface.videoedit.sysc.Optional;
import com.huawei.watchface.videoedit.utils.FileUtils;
import com.huawei.watchface.y;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HwWatchFaceManager {
    private static final String TAG = "HwWatchFaceManager";
    private static HwWatchFaceManager sInstance;
    private String cancelHiTopId;
    private Context mContext;
    private String mCurrentDeleteHiTopId;
    private String mCurrentDeleteName;
    private String mCurrentDeleteVersion;
    private String mCurrentDeviceName;
    private String mCurrentInstallWatchFaceHiTopId;
    private String mCurrentInstallWatchFaceVersion;
    private String mCurrentTransferWatchFaceHiTopId;
    private String mCurrentTransferWatchFaceVersion;
    private DelectUserDesignationWatchFaceCallback mDeleteUserDesignationWatchFaceCallback;
    private cm mDialogNoPermission;
    private String mDownLoadPath;
    private Handler mHandler;
    private HwDeviceConfigManager mHwDeviceConfigManager;
    private HwWatchFaceBtManager mHwWatchFaceBtManager;
    private boolean mIsCanceled;
    private boolean mIsDesignerInstall;
    private boolean mIsDoLogin;
    private long mLastCurrentInstallTime;
    private long mLastCurrentTime;
    private long mLastTransmitWatchInfoChange;
    private cn mNoTitleDialog;
    private OperateWatchFaceCallback mOperateCallback;
    private cl.a mProgressBuilder;
    private cl mProgressDialog;
    private OperateWatchFaceCallback pOperateCallback;
    private int mCurrentInstallState = 0;
    private boolean mIsAppSetWatchFace = false;
    private boolean mIsInstallState = false;
    public HashMap<String, WatchResourcesInfo> mVipFreeWatchFaceInfos = new HashMap<>();
    private boolean mIsBtConnect = true;
    private boolean mIsFirstGprsInstallWatchFace = true;
    private int mLastPercentage = -1;
    private LinkedHashMap<String, String> mWatchScreenMap = new LinkedHashMap<>();
    private ConcurrentHashMap<String, eg> mInstallEventHashMap = new ConcurrentHashMap<>(32);
    private ConcurrentHashMap<String, CustomWatchfaceInfo> mInstallEventInfo = new ConcurrentHashMap<>();
    private LinkedHashMap<String, Boolean> mUpdateMap = new LinkedHashMap<>();
    private IBaseResponseCallback mBtResponseCallback = new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.1
        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onResponse(int i, Object obj) {
            boolean equals;
            String watchInfoId;
            String watchInfoVersion;
            int i2;
            HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse:" + i);
            if (i == 101) {
                HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_TYPE_SUCCESS");
                return;
            }
            if (i != 115) {
                switch (i) {
                    case 106:
                        HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_CURRENT_CHANGE");
                        if (HwWatchFaceManager.this.mCurrentInstallState == 2 || HwWatchFaceManager.this.mIsAppSetWatchFace) {
                            HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() skip this pace");
                        } else {
                            HwWatchFaceManager.this.getDeviceAndWatchFaceInfoByBt();
                        }
                        t.a().a(i, obj);
                        break;
                    case 107:
                        HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_TRANSFER_SUCCESS");
                        WatchResourcesInfo watchResourcesInfo = (obj == null || !(obj instanceof WatchResourcesInfo)) ? null : (WatchResourcesInfo) obj;
                        if (watchResourcesInfo == null) {
                            String currentInstallWatchFaceHiTopId = HwWatchFaceManager.this.getCurrentInstallWatchFaceHiTopId();
                            watchInfoVersion = HwWatchFaceManager.this.getCurrentInstallWatchFaceVersion();
                            watchInfoId = currentInstallWatchFaceHiTopId;
                            equals = true;
                        } else {
                            String currentInstallWatchFaceHiTopId2 = HwWatchFaceManager.this.getCurrentInstallWatchFaceHiTopId();
                            equals = !TextUtils.isEmpty(currentInstallWatchFaceHiTopId2) ? TextUtils.equals(watchResourcesInfo.getWatchInfoId(), currentInstallWatchFaceHiTopId2) : false;
                            watchInfoId = watchResourcesInfo.getWatchInfoId();
                            watchInfoVersion = watchResourcesInfo.getWatchInfoVersion();
                        }
                        HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() isInstallId: " + equals + " ,hitopid:" + watchInfoId + " ,version:" + watchInfoVersion);
                        HwWatchFaceManager.this.closeDesignerTranslateDialog(watchInfoId);
                        if (HwWatchFaceManager.this.mOperateCallback != null) {
                            if (t.a().i(watchInfoId, watchInfoVersion)) {
                                HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback -- call back h5 try out install success");
                                i2 = 2;
                            } else {
                                i2 = 1;
                            }
                            HwWatchFaceManager.this.mOperateCallback.transmitInstallWatchFaceResult(watchInfoId, watchInfoVersion, i2);
                            if (HwWatchFaceManager.this.pOperateCallback != null) {
                                HwWatchFaceManager.this.pOperateCallback.transmitInstallWatchFaceResult(watchInfoId, watchInfoVersion, i2);
                            }
                        }
                        if (watchResourcesInfo == null) {
                            HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_TRANSFER_SUCCESS watchResourcesInfo is null.");
                            if (equals) {
                                HwWatchFaceManager.this.applyWatchFace(watchInfoId, watchInfoVersion, 2, "");
                                break;
                            }
                        } else if (!HwWatchFaceManager.this.mUpdateMap.containsKey(watchResourcesInfo.getWatchInfoId())) {
                            if (equals) {
                                HwWatchFaceManager.this.applyWatchFace(watchInfoId, watchInfoVersion, 2, "");
                                break;
                            }
                        } else {
                            HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_TRANSFER_SUCCESS remove.");
                            HwWatchFaceManager.this.mUpdateMap.remove(watchResourcesInfo.getWatchInfoId());
                            HwWatchFaceManager.this.continueInstallNext(103, watchResourcesInfo.getWatchInfoId() + "_" + watchResourcesInfo.getWatchInfoVersion(), true);
                            break;
                        }
                        break;
                    case 108:
                        HwLog.w(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_WATCH_FACE_DELETE.");
                        HwWatchFaceManager.this.handleWatchFaceDelete(obj);
                        break;
                    case 109:
                    case 110:
                        HwWatchFaceManager.this.getDeviceAndWatchFaceInfoByBt();
                        t.a().a(i, obj);
                        break;
                    default:
                        HwLog.w(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse:" + i);
                        break;
                }
                return;
            }
            HwLog.i(HwWatchFaceManager.TAG, "mBtResponseCallback() onResponse CALLBACK_PHOTO_TRANSFER_SUCCESS");
            WatchFacePhotoAlbumManager.getInstance(HwWatchFaceManager.this.mContext).refreshData();
        }
    };
    private IFileTransferStateCallback mFileTransferStateCallback = new IFileTransferStateCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.2
        @Override // com.huawei.watchface.utils.callback.IFileTransferStateCallback
        public void onFileTransferState(int i) {
            HwWatchFaceManager.this.handleOnFileTransferState(i);
        }

        @Override // com.huawei.watchface.utils.callback.IFileTransferStateCallback
        public void onUpgradeFailed(int i, String str) {
            HwWatchFaceManager.this.handleOnUpgradeFailed(i, str);
        }

        @Override // com.huawei.watchface.utils.callback.IFileTransferStateCallback
        public void onFileRespond(int i) {
            HwWatchFaceManager.this.handleOnFileRespond(i);
        }
    };
    private IAppTransferFileResultAIDLCallback mAppTransferFileResultAIDLCallback = new IAppTransferFileResultAIDLCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.3
        @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
        public void onFileTransferState(int i, String str) {
            HwWatchFaceManager.this.handleOnFileTransferState(i);
        }

        @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
        public void onUpgradeFailed(int i, String str) {
            HwWatchFaceManager.this.handleOnUpgradeFailed(i, str);
        }

        @Override // com.huawei.watchface.utils.callback.IAppTransferFileResultAIDLCallback
        public void onFileRespond(int i, String str) {
            HwWatchFaceManager.this.handleOnFileRespond(i);
        }
    };
    private IBaseResponseCallback mInstallWatchFaceCallback = new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.4
        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onResponse(int i, Object obj) {
            HwLog.i(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() errorCode: " + i);
            String currentInstallWatchFaceHiTopId = HwWatchFaceManager.this.getCurrentInstallWatchFaceHiTopId();
            String currentInstallWatchFaceVersion = HwWatchFaceManager.this.getCurrentInstallWatchFaceVersion();
            if (obj instanceof String) {
                String[] split = ((String) obj).split("_");
                if (split.length >= 2) {
                    currentInstallWatchFaceHiTopId = split[0];
                    currentInstallWatchFaceVersion = split[1];
                    HwLog.i(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() setCurrentInstallWatchFaceHiTopId : " + currentInstallWatchFaceHiTopId + ", version: " + currentInstallWatchFaceVersion);
                    HwWatchFaceManager.this.setCurrentInstallWatchFaceHiTopId(currentInstallWatchFaceHiTopId);
                    HwWatchFaceManager.this.setCurrentInstallWatchFaceVersion(currentInstallWatchFaceVersion);
                }
            }
            HwLog.i(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() hiTopId: " + currentInstallWatchFaceHiTopId + ", version: " + currentInstallWatchFaceVersion);
            switch (i) {
                case 103:
                    HwLog.i(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() Apply watchFace success.");
                    HwWatchFaceManager.this.getDeviceAndWatchFaceInfoByBt();
                    HwWatchFaceManager.this.changeInstallState(0);
                    HwWatchFaceManager.this.setCurrentTransferWatchFaceHiTopId(null);
                    HwWatchFaceManager.this.setCurrentTransferWatchFaceVersion(null);
                    if (HwWatchFaceManager.this.mOperateCallback != null) {
                        HwWatchFaceManager.this.mOperateCallback.transmitInstallWatchFaceResult(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, 1);
                    }
                    if (HwWatchFaceManager.this.pOperateCallback != null) {
                        HwWatchFaceManager.this.pOperateCallback.transmitInstallWatchFaceResult(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, 1);
                    }
                    k.a().b(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion);
                    HwWatchFaceManager.this.continueInstallNext(i, obj, false);
                    break;
                case 104:
                    HwWatchFaceManager.this.setCurrentTransferWatchFaceHiTopId(null);
                    HwWatchFaceManager.this.setCurrentTransferWatchFaceVersion(null);
                    t.a().a(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
                    HwWatchFaceManager.this.processInstallFailed(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, -4);
                    HwWatchFaceManager.this.installWaitInstallWatchFace(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
                    break;
                case 105:
                    HwLog.i(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() Apply watchFace need receive.");
                    String buildTaskId = (HwWatchFaceManager.this.mVipFreeWatchFaceInfos.get(currentInstallWatchFaceHiTopId) == null || !HwWatchFaceManager.this.mVipFreeWatchFaceInfos.get(currentInstallWatchFaceHiTopId).isVipFreeWatchFace()) ? FilePuller.getInstance(HwWatchFaceManager.this.mContext).buildTaskId(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion) : FilePuller.getInstance(HwWatchFaceManager.this.mContext).buildTaskId(WatchResourcesInfo.markUpHiTopId(currentInstallWatchFaceHiTopId), currentInstallWatchFaceVersion);
                    if (!TextUtils.isEmpty(buildTaskId)) {
                        HwLog.d(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() taskId =" + buildTaskId);
                        if (currentInstallWatchFaceHiTopId.equalsIgnoreCase(HwWatchFaceManager.this.getCancelHiTopId())) {
                            HwLog.i(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() cancelHiTopId：" + currentInstallWatchFaceHiTopId);
                            HwWatchFaceManager.this.setCancelHiTopId(null);
                            break;
                        } else {
                            HwWatchFaceManager.this.setCurrentTransferWatchFaceHiTopId(currentInstallWatchFaceHiTopId);
                            HwWatchFaceManager.this.setCurrentTransferWatchFaceVersion(currentInstallWatchFaceVersion);
                            String watchFacePath = HwWatchFaceManager.this.getWatchFacePath(buildTaskId);
                            FlavorConfig.safeHwLog(HwWatchFaceManager.TAG, "mInstallWatchFaceCallback() WatchFacePath = " + watchFacePath + "，taskId =" + buildTaskId);
                            HwWatchFaceManager.this.transferFile(watchFacePath, buildTaskId, 1);
                            HwWatchFaceManager.this.initAnalyticsInstallEvent(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion);
                            break;
                        }
                    }
                    break;
                default:
                    t.a().a(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
                    HwWatchFaceManager.this.installWaitInstallWatchFace(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
                    break;
            }
        }
    };
    private Runnable mResetInstallStateRunnable = new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda5
        @Override // java.lang.Runnable
        public final void run() {
            HwWatchFaceManager.this.m884lambda$new$0$comhuaweiwatchfaceapiHwWatchFaceManager();
        }
    };
    private Runnable mResetInstallState = new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda6
        @Override // java.lang.Runnable
        public final void run() {
            HwWatchFaceManager.this.m885lambda$new$1$comhuaweiwatchfaceapiHwWatchFaceManager();
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new AnonymousClass5();
    private IBaseResponseCallback mDeleteMyWatchFaceCallback = new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.6
        @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
        public void onResponse(int i, Object obj) {
            String str = HwWatchFaceManager.this.mCurrentDeleteHiTopId;
            if (obj instanceof String) {
                String[] split = ((String) obj).split("_");
                if (split.length >= 2) {
                    str = split[0];
                }
            }
            if (i != 103) {
                if (i == 104) {
                    if (HwWatchFaceManager.this.mDeleteUserDesignationWatchFaceCallback != null) {
                        HwWatchFaceManager.this.mDeleteUserDesignationWatchFaceCallback.onDelectWatchFaceState("0", str);
                    }
                    HwWatchFaceManager hwWatchFaceManager = HwWatchFaceManager.this;
                    hwWatchFaceManager.processDeleteFailed(hwWatchFaceManager.mCurrentDeleteHiTopId, HwWatchFaceManager.this.mCurrentDeleteVersion);
                    return;
                }
                return;
            }
            HwLog.i(HwWatchFaceManager.TAG, "mDeleteMyWatchFaceCallback success objectData: " + obj);
            HwLog.i(HwWatchFaceManager.TAG, "mUpdateMap info :" + GsonHelper.toJson(HwWatchFaceManager.this.mUpdateMap));
            if (dg.a((Map<?, ?>) HwWatchFaceManager.this.mUpdateMap, str)) {
                HwLog.i(HwWatchFaceManager.TAG, "mUpdateMap remove :" + str);
                HwWatchFaceManager.this.mUpdateMap.remove(str);
            } else {
                String c = d.a().c(str);
                if (dg.a((Map<?, ?>) HwWatchFaceManager.this.mUpdateMap, c)) {
                    HwWatchFaceManager.this.mUpdateMap.remove(c);
                    HwLog.i(HwWatchFaceManager.TAG, "mUpdateMap remove hiTopId2 :" + c);
                }
            }
            if (HwWatchFaceManager.this.mDeleteUserDesignationWatchFaceCallback != null) {
                HwWatchFaceManager.this.mDeleteUserDesignationWatchFaceCallback.onDelectWatchFaceState("1", str);
            }
            HwWatchFaceManager.this.mCurrentDeleteHiTopId = null;
            HwWatchFaceManager.this.mCurrentDeleteVersion = null;
        }
    };
    private BroadcastReceiver mConnectStateChangedReceiver = new WatchFaceBroadCastReceiver();

    private eg getInstallEvent(String str) {
        if (TextUtils.isEmpty(str)) {
            return new eg().a(System.currentTimeMillis());
        }
        return this.mInstallEventHashMap.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWatchFaceDelete(Object obj) {
        if (this.mOperateCallback == null) {
            HwLog.w(TAG, "handleWatchFaceDelete() mOperateCallback is null.");
            return;
        }
        if (obj == null) {
            HwLog.w(TAG, "handleWatchFaceDelete() objectData is null.");
            return;
        }
        if (!(obj instanceof WatchResourcesInfo)) {
            HwLog.w(TAG, "handleWatchFaceDelete() objectData not instanceof WatchResourcesInfo.");
            return;
        }
        WatchResourcesInfo watchResourcesInfo = (WatchResourcesInfo) obj;
        if (watchResourcesInfo != null) {
            ac.a().e(watchResourcesInfo.getWatchInfoId(), watchResourcesInfo.getWatchInfoVersion());
        }
        this.mOperateCallback.transmitDeleteWatchFaceResult(watchResourcesInfo.getWatchInfoId(), watchResourcesInfo.getWatchInfoVersion(), 1);
        OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitDeleteWatchFaceResult(watchResourcesInfo.getWatchInfoId(), watchResourcesInfo.getWatchInfoVersion(), 1);
        }
        getDeviceAndWatchFaceInfoByBt();
    }

    private void pauseAllTask() {
        p.a(this.mContext).d();
        FilePuller.getInstance(this.mContext).notifyAllTaskPause();
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitRefreshPullData();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitRefreshPullData();
        }
    }

    public void setLastPercentage(int i) {
        this.mLastPercentage = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealTaskTransferredOrCanceled(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "enter dealTaskTransferredOrCanceled hitopid =" + str + ",version =" + str2);
        ConcurrentHashMap<String, String> c = p.a(this.mContext).c();
        String currentInstallWatchFaceHiTopId = getCurrentInstallWatchFaceHiTopId();
        if (currentInstallWatchFaceHiTopId != null && c.containsKey(currentInstallWatchFaceHiTopId)) {
            c.clear();
            HwLog.i(str3, "dealTaskTransferredOrCanceled, remove installed hang task");
        }
        if (this.mVipFreeWatchFaceInfos.get(str) != null && this.mVipFreeWatchFaceInfos.get(str).isVipFreeWatchFace()) {
            str = WatchResourcesInfo.markUpHiTopId(str);
        }
        if (d.a().h(str)) {
            str = d.a().i(str);
        }
        String str4 = str + "_" + str2;
        String str5 = this.mDownLoadPath + str4;
        di.a().b(str4);
        cx.a().b(str4);
        HwLog.i(str3, "dealTaskTransferredOrCanceled isDeleteSuccess: " + CommonUtils.d(new File(str5)) + ", hitopId: " + str);
        setCurrentTransferWatchFaceHiTopId(null);
        setCurrentTransferWatchFaceVersion(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void continueInstallNext(int i, Object obj, boolean z) {
        HwLog.i(TAG, "continueInstallNext enter errorCode:" + i);
        FilePuller.getInstance(this.mContext).installSuccessRemoveTask(d.a().c(getCurrentInstallWatchFaceHiTopId()), getCurrentInstallWatchFaceVersion());
        t.a().a(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), i);
        if (obtainJsonAbility().optBoolean("isSupportedDownloadManager")) {
            dealSetDefaultWatchFaceResponse(i, true, obj, z);
        }
    }

    /* renamed from: lambda$new$0$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m884lambda$new$0$comhuaweiwatchfaceapiHwWatchFaceManager() {
        this.mCurrentInstallState = 0;
        HwLog.i(TAG, "mResetInstallStateRunnable() mCurrentInstallState: " + this.mCurrentInstallState);
        transmitWatchInfoChange(60000);
    }

    /* renamed from: lambda$new$1$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m885lambda$new$1$comhuaweiwatchfaceapiHwWatchFaceManager() {
        HwLog.i(TAG, "mResetInstallState() reset");
        this.mLastPercentage = -1;
        String currentInstallWatchFaceHiTopId = getCurrentInstallWatchFaceHiTopId();
        String currentInstallWatchFaceVersion = getCurrentInstallWatchFaceVersion();
        p.a(this.mContext).c(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion);
        processInstallFailed(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, -2);
        stopTransfer(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, false);
        eo.a(getInstallEvent(currentInstallWatchFaceHiTopId), String.valueOf(141000));
        this.mIsInstallState = false;
    }

    public void notifyGetShortCutIsCreate() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyGetShortCutIsCreate();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyGetShortCutIsCreate();
        }
    }

    static class WatchFaceBroadCastReceiver extends SafeBroadcastReceiver {
        private WatchFaceBroadCastReceiver() {
        }

        @Override // com.huawei.secure.android.common.intent.SafeBroadcastReceiver
        public void onReceiveMsg(Context context, Intent intent) {
            if (context == null) {
                return;
            }
            if (intent == null || intent.getExtras() == null || TextUtils.isEmpty(intent.getAction())) {
                HwLog.w(HwWatchFaceManager.TAG, "WatchFaceBroadCastReceiver() intent is null.");
            } else if ("com.huawei.watchface.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                HwLog.i(HwWatchFaceManager.TAG, "WatchFaceBroadCastReceiver() ACTION_CONNECTION_STATE_CHANGED.");
                if (HwWatchFaceManager.sInstance == null) {
                    return;
                }
                HwWatchFaceManager.sInstance.onBtConnectionStateChange(intent);
            }
        }
    }

    private void transmitDeviceConnectState(boolean z, String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitDeviceConnectState(z, str);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitDeviceConnectState(z, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBtConnectionStateChange(Intent intent) {
        Handler handler;
        DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
        if (deviceInfo == null) {
            HwLog.w(TAG, "mConnectStateChangedReceiver() deviceInfo is null");
            return;
        }
        int deviceConnectState = deviceInfo.getDeviceConnectState();
        String str = TAG;
        HwLog.i(str, "mConnectStateChangedReceiver() deviceConnectState: " + deviceConnectState);
        HwWatchFaceApi.getInstance(Environment.getApplicationContext()).setDisableWearInfoList(null);
        if (deviceConnectState != 2) {
            if (deviceConnectState == 3) {
                this.mIsBtConnect = false;
                transmitDeviceConnectState(false, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
                this.mIsCanceled = false;
                dismissStartSaveDialog();
                p.a(this.mContext).c(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion());
                this.mIsInstallState = false;
                return;
            }
            HwLog.w(str, "mConnectStateChangedReceiver() default");
            return;
        }
        this.mIsBtConnect = true;
        String a2 = db.a(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdentify());
        HwLog.i(str, "mConnectStateChangedReceiver() deviceId is empty:" + TextUtils.isEmpty(a2));
        boolean a3 = dp.a(a2);
        HwLog.i(str, "mConnectStateChangedReceiver() isDeviceChange: " + a3);
        t.a().a(a3);
        transmitDeviceConnectState(true, String.valueOf(a3));
        if (a3 && (handler = this.mHandler) != null) {
            handler.removeCallbacks(this.mResetInstallState);
            setCurrentInstallWatchFaceHiTopId(null);
            setCurrentInstallWatchFaceVersion(null);
            setCurrentTransferWatchFaceHiTopId(null);
            setCurrentTransferWatchFaceVersion(null);
        }
        this.mIsCanceled = false;
        if (getCancelHiTopId() != null) {
            setCancelHiTopId(null);
        }
        getDeviceInfoByBt(a3);
        d.a().a(a3);
        doRefreshUi();
    }

    /* renamed from: com.huawei.watchface.api.HwWatchFaceManager$5, reason: invalid class name */
    class AnonymousClass5 extends BroadcastReceiver {
        AnonymousClass5() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HwLog.i(HwWatchFaceManager.TAG, "mBroadcastReceiver() onReceive enter ");
            if (intent == null) {
                HwLog.w(HwWatchFaceManager.TAG, "mBroadcastReceiver() onReceive intent is null.");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                HwLog.w(HwWatchFaceManager.TAG, "mBroadcastReceiver() onReceive broadcast action is null.");
            } else if (Objects.equals(action, "com.huawei.plugin.account.login")) {
                HwLog.i(HwWatchFaceManager.TAG, "mBroadcastReceiver() ACTION_LOGIN_SUCCESSFUL.");
                doLoginBroadcast();
            }
        }

        private void doLoginBroadcast() {
            if (HwWatchFaceManager.this.mIsDoLogin) {
                HwLog.i(HwWatchFaceManager.TAG, "doLoginBroadcast() mIsDoLogin true.");
                return;
            }
            HwWatchFaceManager.this.mIsDoLogin = true;
            HwLog.i(HwWatchFaceManager.TAG, "doLoginBroadcast() enter.");
            BackgroundTaskUtils.a(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager$5$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HwWatchFaceManager.AnonymousClass5.this.m887x83b0f937();
                }
            }, 5000);
            p.a(HwWatchFaceManager.this.mContext).j();
            y.d();
            n.a(HwWatchFaceManager.this.mContext).e();
            dp.c(HwWatchFaceManager.this.mContext, "");
            dp.h(HwWatchFaceManager.this.mContext, "");
            WatchFaceHttpUtil.a((WatchFaceSignBean) null);
            if (HwWatchFaceBtManager.getInstance(HwWatchFaceManager.this.mContext).getConnectWatchDeviceInfo() == null) {
                HwLog.w(HwWatchFaceManager.TAG, "doLoginBroadcast() deviceInfo null.");
            } else {
                HwWatchFaceManager.this.mHwWatchFaceBtManager.getWatchInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.5.1
                    @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                    public void onResponse(int i, Object obj) {
                        HwLog.i(HwWatchFaceManager.TAG, "doLoginBroadcast() errorCode: " + i);
                    }
                });
            }
        }

        /* renamed from: lambda$doLoginBroadcast$0$com-huawei-watchface-api-HwWatchFaceManager$5, reason: not valid java name */
        /* synthetic */ void m887x83b0f937() {
            HwLog.i(HwWatchFaceManager.TAG, "doLoginBroadcast() reset mIsDoLogin.");
            HwWatchFaceManager.this.mIsDoLogin = false;
        }
    }

    private HwWatchFaceManager(Context context) {
        this.mContext = context;
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mConnectStateChangedReceiver, new IntentFilter("com.huawei.watchface.action.CONNECTION_STATE_CHANGED"));
        this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.mContext);
        this.mDownLoadPath = this.mContext.getFilesDir().getAbsolutePath() + File.separator + "watchface" + File.separator;
        this.mHwDeviceConfigManager = HwDeviceConfigManager.getInstance(this.mContext);
        IntentFilter intentFilter = new IntentFilter("com.huawei.plugin.account.login");
        if (this.mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mBroadcastReceiver, intentFilter);
        }
        HwWatchFaceBtManager.setCallBackForH5(this.mBtResponseCallback);
    }

    public boolean isCanceled() {
        return this.mIsCanceled;
    }

    public void setIsCanceled(boolean z) {
        this.mIsCanceled = z;
    }

    public void doRefreshUi() {
        if (this.mHwWatchFaceBtManager == null) {
            this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.mContext);
        }
        this.mHwWatchFaceBtManager.getWatchInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.7
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                if (i == 101) {
                    HwLog.i(HwWatchFaceManager.TAG, "doRefreshUi() CALLBACK_TYPE_SUCCESS");
                    HwWatchFaceManager.this.transmitWatchInfoChange(1);
                } else {
                    HwLog.w(HwWatchFaceManager.TAG, "doRefreshUi() not CALLBACK_TYPE_SUCCESS");
                }
            }
        });
    }

    public static HwWatchFaceManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (HwWatchFaceManager.class) {
                if (sInstance == null) {
                    sInstance = new HwWatchFaceManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public void init() {
        HwLog.i(TAG, "init enter.");
        p.a(this.mContext).a(false);
        q.a().b();
    }

    public void installWatchFace(InstallWatchFaceBean installWatchFaceBean) {
        if (installWatchFaceBean == null) {
            HwLog.i(TAG, "installWatchFace installWatchFaceBean is null.");
            return;
        }
        String str = TAG;
        HwLog.i(str, "installWatchFace enter.");
        if (getCancelHiTopId() != null) {
            setCancelHiTopId(null);
        }
        p.a(this.mContext).p();
        if (!isBtConnect()) {
            transmitDeviceConnectState(false, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitDownloadWatchFaceResult(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion(), 2);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitDownloadWatchFaceResult(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion(), 2);
            }
            HwLog.w(str, "installWatchFace, failed: Bluetooth not connected");
            return;
        }
        if (this.mOperateCallback == null) {
            HwLog.e(str, "installWatchFace mOperateCallback is null");
            return;
        }
        if (!CommonUtils.f()) {
            showToast(DensityUtil.getStringById(R$string.IDS_confirm_network_whether_connected));
            OperateWatchFaceCallback operateWatchFaceCallback3 = this.mOperateCallback;
            if (operateWatchFaceCallback3 != null) {
                operateWatchFaceCallback3.transmitDownloadWatchFaceResult(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion(), 2);
            }
            OperateWatchFaceCallback operateWatchFaceCallback4 = this.pOperateCallback;
            if (operateWatchFaceCallback4 != null) {
                operateWatchFaceCallback4.transmitDownloadWatchFaceResult(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion(), 2);
            }
            HwLog.w(str, "installWatchFace, failed: Network not connected");
            return;
        }
        if (!HwWatchFaceApi.getInstance(this.mContext).isLogin() || !HwWatchFaceApi.getInstance(this.mContext).isLoginParamSuitable()) {
            HwLog.i(str, "installWatchFace, not login");
            OperateWatchFaceCallback operateWatchFaceCallback5 = this.mOperateCallback;
            if (operateWatchFaceCallback5 != null) {
                ap.a(operateWatchFaceCallback5.getCustomWebViewContext()).a(1, installWatchFaceBean);
            }
            OperateWatchFaceCallback operateWatchFaceCallback6 = this.pOperateCallback;
            if (operateWatchFaceCallback6 != null) {
                ap.a(operateWatchFaceCallback6.getCustomWebViewContext()).a(1, installWatchFaceBean);
                return;
            }
            return;
        }
        if (!this.mUpdateMap.containsKey(installWatchFaceBean.getWatchFaceHiTopId()) && installWatchFaceBean.isUpdate()) {
            this.mUpdateMap.put(installWatchFaceBean.getWatchFaceHiTopId(), true);
        }
        if (this.mIsFirstGprsInstallWatchFace && CommonUtils.d(this.mContext) != 1 && !installWatchFaceBean.isOneClick()) {
            firstInstallRemind(installWatchFaceBean);
        } else {
            downloadWatchFace(installWatchFaceBean);
        }
    }

    private void firstInstallRemind(final InstallWatchFaceBean installWatchFaceBean) {
        String format;
        String str;
        String stringById = DensityUtil.getStringById(R$string.IDS_watchface_install_prompt);
        String fileSize = installWatchFaceBean.getFileSize();
        if (fileSize != null && fileSize.endsWith("KB")) {
            int indexOf = fileSize.indexOf("KB");
            try {
                int parseInt = Integer.parseInt(SafeString.substring(fileSize, 0, indexOf));
                if (parseInt >= 1024) {
                    str = CommonUtils.a(parseInt / 1024.0f, 1, 2) + "MB";
                } else {
                    str = CommonUtils.a(parseInt, 1, 0) + SafeString.substring(fileSize, indexOf, fileSize.length());
                }
            } catch (NumberFormatException unused) {
                HwLog.e(TAG, "firstInstallRemind, NumberFormatException");
                str = "";
            }
            format = String.format(Locale.ENGLISH, stringById, str);
        } else {
            format = String.format(Locale.ENGLISH, stringById, fileSize);
        }
        showFirstInstallRemindDialogNoTitle(format, new View.OnClickListener() { // from class: com.huawei.watchface.api.HwWatchFaceManager.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwWatchFaceManager.this.downloadWatchFace(installWatchFaceBean);
                HwWatchFaceManager.this.dismissNoTitleDialog();
                HwWatchFaceManager.this.mIsFirstGprsInstallWatchFace = false;
                ViewClickInstrumentation.clickOnView(view);
            }
        }, installWatchFaceBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadWatchFace(final InstallWatchFaceBean installWatchFaceBean) {
        if (installWatchFaceBean == null) {
            HwLog.e(TAG, "downloadWatchFace() installWatchFaceBean is null.");
            return;
        }
        HwLog.i(TAG, "downloadWatchFace() enter.");
        changeInstallState(1);
        WatchFaceInfo watchFaceInfo = new WatchFaceInfo();
        String fileSize = installWatchFaceBean.getFileSize();
        if (!TextUtils.isEmpty(fileSize)) {
            try {
                watchFaceInfo.configFileSize(Integer.parseInt(SafeString.substring(fileSize, 0, fileSize.length() - 2)));
            } catch (NumberFormatException unused) {
                HwLog.e(TAG, "downloadWatchFace() NumberFormatException.");
            }
        }
        StringBuffer stringBuffer = new StringBuffer(installWatchFaceBean.getWatchFaceHiTopId());
        stringBuffer.append("_");
        stringBuffer.append(installWatchFaceBean.getVersion());
        String stringBuffer2 = stringBuffer.toString();
        watchFaceInfo.configFileType(String.valueOf(4));
        watchFaceInfo.configDigest(installWatchFaceBean.getHashCode());
        watchFaceInfo.setInstallationType(installWatchFaceBean.getInstallationType());
        final ee analyticsDownloadEvent = getAnalyticsDownloadEvent(installWatchFaceBean);
        if (installWatchFaceBean.isVipFreeWatchFace()) {
            aa.a().b(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion());
        }
        pullWatchFaceFile(stringBuffer2, installWatchFaceBean.getFileUrl(), p.a(this.mContext).b(stringBuffer2), watchFaceInfo, installWatchFaceBean.isZip(), installWatchFaceBean.isOneClick(), new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.9
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                String str;
                String str2;
                if (!(obj instanceof PullResult)) {
                    HwLog.w(HwWatchFaceManager.TAG, "downloadWatchFace() objectData isn't PullResult");
                    return;
                }
                PullResult pullResult = (PullResult) obj;
                String fetchUuid = pullResult.fetchUuid();
                if (!TextUtils.isEmpty(fetchUuid)) {
                    String[] split = fetchUuid.split("_");
                    if (split.length > 1) {
                        String str3 = split[0];
                        str2 = split[1];
                        str = str3;
                        HwWatchFaceManager.this.dealDownloadResult(i, pullResult, str, str2, installWatchFaceBean.getWatchScreen(), analyticsDownloadEvent, installWatchFaceBean.isVipFreeWatchFace());
                    }
                }
                str = "";
                str2 = str;
                HwWatchFaceManager.this.dealDownloadResult(i, pullResult, str, str2, installWatchFaceBean.getWatchScreen(), analyticsDownloadEvent, installWatchFaceBean.isVipFreeWatchFace());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealDownloadResult(int i, PullResult pullResult, String str, String str2, String str3, ee eeVar, boolean z) {
        if (i != 0) {
            HwLog.i(TAG, "dealDownloadResult, responseCode:" + i);
        }
        if (i == -10) {
            HwLog.i(TAG, "download cancel");
            changeInstallState(0);
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitDownloadWatchFaceResult(str, str2, 2);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitDownloadWatchFaceResult(str, str2, 2);
            }
            p.a(this.mContext).d(str);
            eo.a(eeVar, String.valueOf(i));
            return;
        }
        if (i == -8) {
            OperateWatchFaceCallback operateWatchFaceCallback3 = this.mOperateCallback;
            if (operateWatchFaceCallback3 != null) {
                operateWatchFaceCallback3.transmitDownloadWatchFaceResult(str, str2, -8);
            }
            OperateWatchFaceCallback operateWatchFaceCallback4 = this.pOperateCallback;
            if (operateWatchFaceCallback4 != null) {
                operateWatchFaceCallback4.transmitDownloadWatchFaceResult(str, str2, -8);
                return;
            }
            return;
        }
        if (i != 1) {
            if (i != 7) {
                if (i == 3 || i == 4) {
                    HwLog.i(TAG, "dealDownloadResult, download pause or restart");
                    return;
                } else {
                    eeVar.d(pullResult.getDescInfo());
                    dealDownloadProgressResult(str, str2, i, pullResult, eeVar);
                    return;
                }
            }
            OperateWatchFaceCallback operateWatchFaceCallback5 = this.mOperateCallback;
            if (operateWatchFaceCallback5 != null) {
                operateWatchFaceCallback5.transmitDownloadWatchFaceResult(str, str2, 7);
            }
            OperateWatchFaceCallback operateWatchFaceCallback6 = this.pOperateCallback;
            if (operateWatchFaceCallback6 != null) {
                operateWatchFaceCallback6.transmitDownloadWatchFaceResult(str, str2, 7);
                return;
            }
            return;
        }
        String str4 = TAG;
        HwLog.i(str4, "dealDownloadResult() download success");
        dg.a(this.mInstallEventInfo, str, new CustomWatchfaceInfo(eeVar.j(), eeVar.k(), eeVar.e(), eeVar.d()));
        if (!p.a(this.mContext).o()) {
            HwLog.w(str4, "dealDownloadResult() device diff");
            OperateWatchFaceCallback operateWatchFaceCallback7 = this.mOperateCallback;
            if (operateWatchFaceCallback7 != null) {
                operateWatchFaceCallback7.transmitDownloadWatchFaceResult(str, str2, 2);
            }
            OperateWatchFaceCallback operateWatchFaceCallback8 = this.pOperateCallback;
            if (operateWatchFaceCallback8 != null) {
                operateWatchFaceCallback8.transmitDownloadWatchFaceResult(str, str2, 2);
                return;
            }
            return;
        }
        OperateWatchFaceCallback operateWatchFaceCallback9 = this.mOperateCallback;
        if (operateWatchFaceCallback9 != null) {
            operateWatchFaceCallback9.transmitDownloadWatchFaceResult(str, str2, 1);
        }
        OperateWatchFaceCallback operateWatchFaceCallback10 = this.pOperateCallback;
        if (operateWatchFaceCallback10 != null) {
            operateWatchFaceCallback10.transmitDownloadWatchFaceResult(str, str2, 1);
        }
        FilePuller.getInstance(this.mContext).downloadSuccessRemoveTask(str, str2);
        if (HwWatchFaceBtManager.getInstance(this.mContext).getConnectWatchDeviceInfo() != null) {
            HwLog.i(str4, "dealDownloadResult() has connected devices");
            if (z) {
                HwLog.i(str4, "dealDownloadResult() rename result" + FileUtils.renameTo(this.mDownLoadPath + pullResult.fetchUuid(), this.mDownLoadPath + WatchResourcesInfo.markUpHiTopId(pullResult.fetchUuid())) + ",new hiTopId:" + str + ",version:" + str2);
            } else if (d.a().c()) {
                String trim = d.a().b(str).trim();
                if (!TextUtils.equals(str, trim)) {
                    HwLog.i(str4, "dealDownloadResult() AodManager :" + FileUtils.renameTo(this.mDownLoadPath + pullResult.fetchUuid(), this.mDownLoadPath + trim + "_" + str2) + ",hiTopId:" + trim + ", version:" + str2);
                }
            }
            applyWatchFace(str, str2, 1, str3, z, pullResult.getInstallationType());
        } else {
            HwLog.i(str4, "dealDownloadResult() hiTopId:" + str + ", version:" + str2);
            p.a(this.mContext).c(str, str2);
            processInstallFailed(str, str2, -5);
        }
        eeVar.d(pullResult.getDescInfo());
        eo.a(eeVar, String.valueOf(i));
    }

    private void dealDownloadProgressResult(final String str, final String str2, int i, PullResult pullResult, ee eeVar) {
        if (i != -6) {
            if (i == 0) {
                changeInstallState(1);
                float round = Math.round(pullResult.fetchPulledSize() / 1024.0f);
                int fetchTotalSize = pullResult.fetchTotalSize();
                if (fetchTotalSize == 0) {
                    return;
                }
                int i2 = (int) ((round / fetchTotalSize) * 100.0f);
                if (i2 > 100) {
                    i2 = 100;
                }
                dealTransmitProgress(i2, str);
                return;
            }
            switch (i) {
                case -13:
                case -12:
                case -11:
                    break;
                default:
                    processInstallFailed(str, str2, -1);
                    FilePuller.getInstance(this.mContext).operationDownloadTask(str, str2, 1, new OperationCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.10
                        @Override // com.huawei.watchface.utils.callback.OperationCallback
                        public void operationResult(int i3) {
                            if (i3 != 0 || HwWatchFaceManager.this.mOperateCallback == null) {
                                return;
                            }
                            HwLog.i(HwWatchFaceManager.TAG, "dealDownloadProgressResult, operationResult");
                            HwWatchFaceManager.this.mOperateCallback.transmitDownloadWatchFaceResult(str, str2, 3);
                            if (HwWatchFaceManager.this.pOperateCallback != null) {
                                HwWatchFaceManager.this.pOperateCallback.transmitDownloadWatchFaceResult(str, str2, 3);
                            }
                        }
                    });
                    p.a(this.mContext).d(str);
                    eo.a(eeVar, String.valueOf(i));
                    break;
            }
        }
        HwLog.i(TAG, "dealDownloadProgressResult, PULL_DECOMPRESS_FAIL");
        cancelInstallWatchFace(str, str2);
        p.a(this.mContext).d(str);
        decryptResult(i, str);
        eo.a(eeVar, String.valueOf(i));
    }

    private void dealTransmitProgress(int i, String str) {
        String str2 = this.mCurrentDeleteHiTopId;
        if (str2 != null && str2.equals(str)) {
            HwLog.w(TAG, "dealTransmitProgress, hiTopId is delete task id.");
            return;
        }
        if (this.mOperateCallback != null) {
            if (((this.mLastCurrentTime > System.currentTimeMillis() || System.currentTimeMillis() - this.mLastCurrentTime < 200) && i != 100) || i < 0 || i % 20 != 0 || i <= p.a(this.mContext).c(str)) {
                return;
            }
            HwLog.i(TAG, "dealTransmitProgress, download progress to h5:" + i);
            p.a(this.mContext).a(str, i);
            String a2 = CommonUtils.a((double) i, 2, 0);
            this.mLastCurrentTime = System.currentTimeMillis();
            this.mOperateCallback.transmitDownloadProgressWatchFace(str, i, a2);
            OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitDownloadProgressWatchFace(str, i, a2);
            }
        }
    }

    public void transferFile(final String str, final String str2, final int i) {
        changeInstallState(3);
        if (this.mHwDeviceConfigManager != null) {
            ThreadPoolManager.getInstance().tagExecute(TAG, new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager.11
                @Override // java.lang.Runnable
                public void run() {
                    HwDeviceConfigManager.getInstance(HwWatchFaceManager.this.mContext).a(str, str2, i, HwWatchFaceManager.this.mFileTransferStateCallback, HwWatchFaceManager.this.mAppTransferFileResultAIDLCallback);
                }
            });
        } else {
            HwLog.w(TAG, "transferFile() mHwDeviceConfigManager is null.");
        }
    }

    public String getRandomVersion() {
        StringBuffer stringBuffer = new StringBuffer(1);
        for (int i = 0; i < 3; i++) {
            int b = dl.b(10) + 1;
            if (i == 2) {
                stringBuffer.append(b);
                return stringBuffer.toString();
            }
            stringBuffer.append(b).append(".");
        }
        return stringBuffer.toString();
    }

    public void showInstallProgress(int i, Activity activity) {
        if (activity == null) {
            HwLog.w(TAG, "showInstallProgress() activity is null");
            return;
        }
        String str = TAG;
        HwLog.i(str, "showInstallProgress() is canceled: " + this.mIsCanceled);
        if (this.mIsCanceled) {
            return;
        }
        if (i == 0) {
            this.mIsDesignerInstall = true;
        }
        cl clVar = this.mProgressDialog;
        if (clVar != null && clVar.isShowing() && this.mProgressBuilder != null) {
            HwLog.i(str, "showInstallProgress() setProgress: " + i);
            this.mProgressBuilder.a(i);
            this.mProgressBuilder.b(CommonUtils.a((double) i, 2, 0));
            return;
        }
        HwLog.i(str, "showInstallProgress() startShowDialog Time: " + System.currentTimeMillis());
        this.mProgressDialog = new cl(activity);
        cl.a aVar = new cl.a(activity);
        this.mProgressBuilder = aVar;
        aVar.a(DensityUtil.getStringById(R$string.watch_face_installing)).a(new View.OnClickListener() { // from class: com.huawei.watchface.api.HwWatchFaceManager.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HwWatchFaceManager.this.mIsCanceled = true;
                HwWatchFaceManager.this.mIsDesignerInstall = false;
                if (HwWatchFaceManager.this.mProgressDialog != null) {
                    HwWatchFaceManager.this.mProgressDialog.dismiss();
                    HwWatchFaceManager.this.mProgressDialog = null;
                }
                HwWatchFaceManager hwWatchFaceManager = HwWatchFaceManager.this;
                hwWatchFaceManager.cancelInstallWatchFace(hwWatchFaceManager.getCurrentInstallWatchFaceHiTopId(), HwWatchFaceManager.this.getCurrentInstallWatchFaceVersion());
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mProgressDialog = this.mProgressBuilder.a();
        HwLog.i(str, "showInstallProgress() create dialog");
        this.mProgressDialog.setCancelable(false);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressBuilder.a(0);
        this.mProgressBuilder.b(CommonUtils.a(0.0d, 2, 0));
        this.mProgressDialog.show();
        HwLog.i(str, "showInstallProgress() mProgressDialog is show");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeDesignerTranslateDialog(String str) {
        if (TextUtils.equals("000000001", str) && ba.a(this.mContext).a()) {
            hideDesignerInstallDialog(0);
        }
    }

    private void hideDesignerInstallDialog(int i) {
        cl clVar;
        String str = TAG;
        HwLog.i(str, "hideDesignerInstallDialog() mIsDesignerInstall: " + this.mIsDesignerInstall);
        if (!this.mIsDesignerInstall || (clVar = this.mProgressDialog) == null || !clVar.isShowing() || this.mProgressBuilder == null) {
            return;
        }
        HwLog.i(str, "hideDesignerInstallDialog() enter.");
        this.mProgressDialog.dismiss();
        this.mProgressDialog = null;
        if (1 == i) {
            ds.a(R$string.photo_album_dialog_loading_install_failed);
        } else if (i == 0) {
            this.mIsDesignerInstall = false;
        }
    }

    public String getCurrentInstallWatchFaceHiTopId() {
        HwLog.i(TAG, "getCurrentInstallWatchFaceHiTopId  =" + this.mCurrentInstallWatchFaceHiTopId);
        return this.mCurrentInstallWatchFaceHiTopId;
    }

    public void setCurrentInstallWatchFaceHiTopId(String str) {
        String str2 = TAG;
        HwLog.i(str2, "setCurrentInstallWatchFaceHiTopId currentInstallWatchFaceHiTopId =" + str);
        this.mCurrentInstallWatchFaceHiTopId = WatchResourcesInfo.markDownHiTopId(str);
        HwLog.i(str2, "setCurrentInstallWatchFaceHiTopId hitopid =" + this.mCurrentInstallWatchFaceHiTopId);
    }

    public String getCurrentInstallWatchFaceVersion() {
        return this.mCurrentInstallWatchFaceVersion;
    }

    public void setCurrentInstallWatchFaceVersion(String str) {
        this.mCurrentInstallWatchFaceVersion = str;
    }

    public String getCurrentTransferWatchFaceHiTopId() {
        return this.mCurrentTransferWatchFaceHiTopId;
    }

    public void setCurrentTransferWatchFaceHiTopId(String str) {
        this.mCurrentTransferWatchFaceHiTopId = WatchResourcesInfo.markDownHiTopId(str);
        HwLog.i(TAG, "setCurrentTransferWatchFaceHiTopId hitopid =" + this.mCurrentInstallWatchFaceHiTopId);
    }

    public String getCurrentTransferWatchFaceVersion() {
        return this.mCurrentTransferWatchFaceVersion;
    }

    public void setCurrentTransferWatchFaceVersion(String str) {
        this.mCurrentTransferWatchFaceVersion = str;
    }

    public void applyWatchFace(String str, String str2, int i, String str3) {
        applyWatchFace(str, str2, i, str3, 0);
    }

    public void applyWatchFace(String str, String str2, int i, String str3, int i2) {
        applyWatchFace(str, str2, i, str3, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVipFreeFromCache(str), i2);
    }

    public void applyWatchFace(String str, String str2, int i, String str3, boolean z, int i2) {
        String str4 = TAG;
        HwLog.i(str4, "applyWatchFace() hiTopId: " + str + ", version: " + str2 + ", installState: " + i + ", mIsInstallState: " + this.mIsInstallState + ", watchScreen: " + str3 + ", isVipFreeWatchFace: " + z + ", installationType: " + i2);
        if (getCancelHiTopId() != null) {
            setCancelHiTopId(null);
        }
        String b = d.a().b(str);
        if (!TextUtils.isEmpty(str3)) {
            this.mWatchScreenMap.put(b, str3);
        }
        String buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(str, str2);
        int i3 = 5;
        if (hasInstallingTask(buildTaskId, i) || !(p.a(this.mContext).c().isEmpty() || p.a(this.mContext).c().containsKey(str))) {
            p.a(this.mContext).a(b, str2);
            FilePuller.getInstance(this.mContext).notifyDownloadStatus(buildTaskId, 5);
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitInstallWatchFaceResult(b, str2, 5);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitInstallWatchFaceResult(b, str2, 5);
            }
            HwLog.i(str4, "applyWatchFace, add wait queue");
            return;
        }
        if (b == null || str2 == null) {
            HwLog.w(str4, "applyWatchFace() hiTopId or version is null");
            return;
        }
        changeInstallState(2);
        FilePuller.getInstance(this.mContext).notifyDownloadStatus(buildTaskId, 6);
        OperateWatchFaceCallback operateWatchFaceCallback3 = this.mOperateCallback;
        if (operateWatchFaceCallback3 != null && i == 1) {
            operateWatchFaceCallback3.transmitInstallWatchFaceResult(b, str2, 6);
        }
        OperateWatchFaceCallback operateWatchFaceCallback4 = this.pOperateCallback;
        if (operateWatchFaceCallback4 != null && i == 1) {
            operateWatchFaceCallback4.transmitInstallWatchFaceResult(b, str2, 6);
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mHandler.removeCallbacks(this.mResetInstallState);
        this.mIsInstallState = true;
        HwLog.w(str4, "applyWatchFace() apply immediately.");
        this.mHandler.postDelayed(this.mResetInstallState, 60000L);
        setCurrentInstallWatchFaceHiTopId(b);
        setCurrentInstallWatchFaceVersion(str2);
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        watchResourcesInfo.setWatchInfoId(b);
        watchResourcesInfo.setWatchInfoVersion(str2);
        watchResourcesInfo.setWatchScreen(this.mWatchScreenMap.get(b));
        watchResourcesInfo.setVipFreeWatchFace(z);
        boolean i4 = t.a().i(b, str2);
        if (i2 == 0) {
            if (i4) {
                HwLog.i(str4, "applyWatchFace INSTALLATION_TYPE_NORMAL-- is try out watch face.Send OPERATE_TYPE_TRY_OUT_START");
            }
            i3 = 1;
        } else {
            if (i2 == 1) {
                if (i4) {
                    HwLog.i(str4, "applyWatchFace INSTALLATION_TYPE_TRYOUT-- is try out watch face.Send OPERATE_TYPE_TRY_OUT_START");
                }
            } else if (i2 == 2 && !i4) {
                HwLog.i(str4, "applyWatchFace INSTALLATION_TYPE_APPLY-- not is try out watch face.Send OPERATE_TYPE_APPLY");
                i3 = 1;
            }
            i3 = 0;
        }
        if (i3 == 0) {
            HwLog.e(str4, "applyWatchFace operateType error ");
        } else {
            this.mHwWatchFaceBtManager.operateDevice(watchResourcesInfo, i3, this.mInstallWatchFaceCallback, true);
        }
    }

    private boolean hasInstallingTask(String str, int i) {
        String str2 = TAG;
        HwLog.i(str2, "hasInstallingTask enter");
        if (!obtainJsonAbility().optBoolean("isSupportedDownloadManager")) {
            HwLog.i(str2, "hasInstallingTask Not supported download");
            return false;
        }
        if (i != 1) {
            HwLog.i(str2, "hasInstallingTask, installState isn't WATCH_FACE_INSTALLING");
            return false;
        }
        if (!this.mIsInstallState) {
            HwLog.i(str2, "hasInstallingTask, No task is being installed.");
            return false;
        }
        if (FilePuller.getInstance(this.mContext).getDownloadTaskStatus(str) != 3) {
            return true;
        }
        HwLog.i(str2, "hasInstallingTask, PULL_IN_PAUSE");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTask(String str, String str2) {
        FilePuller.getInstance(this.mContext).cancelTask(str, str2);
        if (str.equals(getCurrentInstallWatchFaceHiTopId()) && str2.equals(getCurrentInstallWatchFaceVersion())) {
            setCurrentInstallWatchFaceHiTopId(null);
            setCurrentInstallWatchFaceVersion(null);
            setCurrentTransferWatchFaceVersion(null);
            setCurrentTransferWatchFaceVersion(null);
            HwLog.i(TAG, "cancelTask() reset current hiTopId and version");
        }
        LinkedHashMap<String, String> a2 = p.a(this.mContext).a();
        if (a2.containsKey(str)) {
            HwLog.i(TAG, "cancelTask() cancel task in map" + str);
            a2.remove(str);
            FilePuller.getInstance(this.mContext).removeTask(str, str2);
        }
        ConcurrentHashMap<String, String> b = p.a(this.mContext).b();
        if (b.containsKey(str)) {
            HwLog.i(TAG, "cancelTask() cancel task in pauseMap" + str);
            b.remove(str);
            FilePuller.getInstance(this.mContext).removeTask(str, str2);
        }
        installWaitInstallWatchFace(str, str2, 0);
    }

    public String getWatchFacePath(String str) {
        StringBuffer stringBuffer = new StringBuffer(this.mDownLoadPath);
        stringBuffer.append(str);
        stringBuffer.append(File.separator);
        stringBuffer.append(FlavorConfig.SERVICE_WATCH_FACE);
        return stringBuffer.toString();
    }

    public void setWatchFaceCallback(OperateWatchFaceCallback operateWatchFaceCallback, boolean z) {
        if (z) {
            this.mOperateCallback = operateWatchFaceCallback;
            this.pOperateCallback = null;
        } else {
            this.pOperateCallback = operateWatchFaceCallback;
        }
        p.a(this.mContext).a(operateWatchFaceCallback, z);
        if (operateWatchFaceCallback != null) {
            ap.a(operateWatchFaceCallback.getCustomWebViewContext()).a(operateWatchFaceCallback);
        }
    }

    public void getDeviceInfoByBt(final boolean z) {
        String str = TAG;
        HwLog.i(str, "getDeviceInfoByBt() enter. isDeviceChange：" + z);
        if (this.mHwWatchFaceBtManager == null) {
            this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.mContext);
        }
        if (z) {
            this.mHwWatchFaceBtManager.resetWatchFaceSupportInfoAndWatchFaces();
        }
        if (this.mHwWatchFaceBtManager.getConnectWatchDeviceInfo() != null) {
            this.mHwWatchFaceBtManager.getDeviceInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda10
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    HwWatchFaceManager.this.m881xb19fe74e(z, i, obj);
                }
            });
        } else {
            HwLog.w(str, "getDeviceInfoByBt() current Device info is null");
        }
    }

    /* renamed from: lambda$getDeviceInfoByBt$2$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m881xb19fe74e(boolean z, int i, Object obj) {
        HwLog.i(TAG, "getDeviceInfoByBt() Response: " + i);
        checkDeviceMode(z);
    }

    private void checkDeviceMode(boolean z) {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
        int watchfaceMode = watchFaceSupportInfo != null ? watchFaceSupportInfo.getWatchfaceMode() : -1;
        int b = dp.b("device_connect_mode", -1);
        String str = TAG;
        HwLog.i(str, "checkDeviceMode() watchfaceMode=" + watchfaceMode + ", cacheMode=" + b);
        boolean z2 = b != watchfaceMode;
        dp.a("device_connect_mode", watchfaceMode);
        dp.b("mode_change", z2);
        HwLog.i(str, "checkDeviceMode modeChange ==" + z2);
        if (!z && z2) {
            dp.b("mode_change_not_refresh", true);
            HwLog.i(str, "checkDeviceMode CHANGE_NOT_REFRESH true");
        }
        if (obtainJsonAbility().optBoolean("isSupportedDownloadManager")) {
            p.a(this.mContext).a(z2);
            p.a(this.mContext).m();
        }
    }

    public void getDeviceAndWatchFaceInfoByBt() {
        String str = TAG;
        HwLog.i(str, "getDeviceAndWatchFaceInfoByBt() enter");
        this.mIsAppSetWatchFace = false;
        if (this.mHwWatchFaceBtManager == null) {
            this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.mContext);
        }
        if (this.mHwWatchFaceBtManager.getConnectWatchDeviceInfo() == null) {
            HwLog.w(str, "getDeviceAndWatchFaceInfoByBt() current Device info is null");
        } else {
            this.mHwWatchFaceBtManager.getWatchInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.13
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public void onResponse(int i, Object obj) {
                    if (i != 101) {
                        HwLog.w(HwWatchFaceManager.TAG, "getDeviceAndWatchFaceInfoByBt() getWatchInfoForUI error:" + i);
                        return;
                    }
                    HwWatchFaceManager.this.transmitWatchInfoChange(1);
                }
            });
        }
    }

    public String getWatchFaceInfo() {
        HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(this.mContext);
        if (hwWatchFaceApi == null || hwWatchFaceApi.getDeviceConnectState() != 1) {
            return "1";
        }
        String watchFaceScreen = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceScreen(true, false);
        String watchFaceMaxVersion = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceMaxVersion(true, false);
        if (TextUtils.isEmpty(watchFaceMaxVersion) || TextUtils.isEmpty(watchFaceScreen)) {
            HwLog.w(TAG, "getWatchFaceInfo() themeVersion or screen is empty.");
            HwWatchFaceBtManager.getInstance(this.mContext).getDeviceInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda7
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    HwWatchFaceManager.this.m882x682920a(i, obj);
                }
            });
            return "0";
        }
        if (HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo() == null) {
            HwLog.w(TAG, "getWatchFaceInfo() getWatchFaceSupportInfo is null");
            HwWatchFaceBtManager.getInstance(this.mContext).getDeviceInfoForUi(new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda8
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    HwWatchFaceManager.this.m883xa123548b(i, obj);
                }
            });
        }
        int watchfaceMode = HwWatchFaceBtManager.getInstance(this.mContext).getWatchfaceMode();
        String a2 = LanguageUtils.a(true);
        String deviceModel = hwWatchFaceApi.getDeviceModel();
        String commonCountryCode = hwWatchFaceApi.getCommonCountryCode();
        String softVersion = hwWatchFaceApi.getSoftVersion();
        String currentWatchFaceId = getCurrentWatchFaceId();
        String preWatchFaceIdStore = getPreWatchFaceIdStore();
        String watchFaceIdStore = getWatchFaceIdStore();
        String g = d.a().g();
        String deviceName = hwWatchFaceApi.getDeviceName();
        String a3 = cv.a();
        boolean i = CommonUtils.i();
        List<ScreenInfo> compatibleInfo = getCompatibleInfo();
        List<ScreenInfo> purchasedInfo = getPurchasedInfo();
        WatchFaceInfoForH5 watchFaceInfoForH5 = new WatchFaceInfoForH5();
        watchFaceInfoForH5.setFirmware("6.0.1");
        watchFaceInfoForH5.setLocale(a2);
        watchFaceInfoForH5.setScreen(watchFaceScreen);
        watchFaceInfoForH5.setPhoneType(deviceModel);
        watchFaceInfoForH5.setIsoCode(commonCountryCode);
        watchFaceInfoForH5.setBuildNumber(softVersion);
        watchFaceInfoForH5.setFiletype("hwt");
        watchFaceInfoForH5.setThemeVersion(watchFaceMaxVersion);
        watchFaceInfoForH5.setCurrentWatchFaceId(currentWatchFaceId);
        watchFaceInfoForH5.setPreWatchFaceIdStore(preWatchFaceIdStore);
        watchFaceInfoForH5.setWatchFaceIdStore(watchFaceIdStore);
        watchFaceInfoForH5.setHideWatchFaceIdStore(g);
        watchFaceInfoForH5.setDeviceName(deviceName);
        watchFaceInfoForH5.setAppVersion(a3);
        watchFaceInfoForH5.setHuaweiPhone(i);
        watchFaceInfoForH5.setCompatibleInfo(compatibleInfo);
        watchFaceInfoForH5.setPurchasedInfo(purchasedInfo);
        watchFaceInfoForH5.setAbilityList(getWatchAbility());
        watchFaceInfoForH5.setPowerLevel(getPowerLevel());
        watchFaceInfoForH5.setDeviceMode(watchfaceMode);
        watchFaceInfoForH5.setAarVersion("10005151");
        final String json = GsonHelper.toJson(watchFaceInfoForH5);
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                dp.c("watchface_info_key", ao.a(json, "storagePw"));
            }
        });
        return json;
    }

    /* renamed from: lambda$getWatchFaceInfo$3$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m882x682920a(int i, Object obj) {
        if (i == 101) {
            this.mOperateCallback.transmitGetResult();
            OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitGetResult();
            }
            HwLog.i(TAG, "getWatchFaceInfo() transmitGetResult.");
        }
    }

    /* renamed from: lambda$getWatchFaceInfo$4$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m883xa123548b(int i, Object obj) {
        if (i == 101) {
            this.mOperateCallback.transmitGetResult();
            OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitGetResult();
            }
            HwLog.i(TAG, "getWatchFaceInfo() transmitGetResult.");
        }
    }

    public static AbilityListForH5 getWatchAbility() {
        AbilityListForH5 abilityListForH5 = new AbilityListForH5();
        abilityListForH5.setSupportedAlbumWatchFace(true);
        abilityListForH5.setSupportedVideoWatchFace(true);
        abilityListForH5.setSupportedDownloadManager(true);
        abilityListForH5.setSupportedDownloadTotalSizeIsKB(true);
        abilityListForH5.setSupportedGetPhoneInfo(true);
        abilityListForH5.setSupportedTryOut(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).isSupportTryOut());
        abilityListForH5.setSupportedCoupon(true);
        abilityListForH5.setSupportArrEncrypt(true);
        abilityListForH5.setSupportedThreePay(true);
        abilityListForH5.setSupportedDialUnite(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).isSupportDialUnite());
        abilityListForH5.setSupportedKaleidoscopeWatchFace(true);
        abilityListForH5.setSupportExchange(true);
        abilityListForH5.setSupportSharing(true);
        abilityListForH5.setSupportedMemberShip(CommonUtils.B());
        abilityListForH5.setSupportPrivacyUrl(true);
        abilityListForH5.setSupportedSportKaleidoscopeWatchFace(true);
        abilityListForH5.setSupportedWearWatchFace(getInstance(Environment.getApplicationContext()).getIsWearSupport() != 0);
        abilityListForH5.setSupportedHumanPortrait(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).isSupportedHumanPortrait());
        abilityListForH5.setSupportedGalleryPlugin(true);
        abilityListForH5.setSupportedVideoPlugin(true);
        abilityListForH5.setSupportedWearPlugin(true);
        abilityListForH5.setSupportedMemberExclusive(true);
        abilityListForH5.setSupportedKaleidoscopePlugin(true);
        abilityListForH5.setSupportShortcutseCreate(true);
        abilityListForH5.setSupportNewAlbum(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).isSupportNewAlbum());
        abilityListForH5.setSupportPictureSearch(true);
        abilityListForH5.setPreInstalledVersion(false);
        abilityListForH5.setPreInstalledVersionType("1");
        abilityListForH5.setVipPayType("1");
        abilityListForH5.setSupportMyWatch(TextUtils.equals(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getIsSupportMyWatchface(), "1"));
        abilityListForH5.setAarVersion("10005151");
        abilityListForH5.setGalleryType(abilityListForH5.isSupportedHumanPortrait() ? "3" : "1");
        abilityListForH5.setWearType(abilityListForH5.isSupportedWearWatchFace() ? "2" : "0");
        abilityListForH5.setLocalFileType("1");
        return abilityListForH5;
    }

    private JSONObject obtainJsonAbility() {
        HwLog.i(TAG, "obtainJsonAbility");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isSupportedAlbumWatchFace", true);
            jSONObject.put("isSupportedVideoWatchFace", true);
            jSONObject.put("isSupportedDownloadManager", true);
            jSONObject.put("isSupportedDownloadTotalSizeIsKB", true);
            jSONObject.put("isSupportedGetPhoneInfo", true);
            jSONObject.put("isSupportedTryOut", HwWatchFaceBtManager.getInstance(this.mContext).isSupportTryOut());
            jSONObject.put("isSupportedCoupon", true);
            jSONObject.put("isSupportedThreePay", true);
        } catch (JSONException e) {
            HwLog.e(TAG, "obtainJsonAbility, JSONException." + HwLog.printException((Exception) e));
        }
        return jSONObject;
    }

    public String getWatchOtherThemeVersion() {
        String watchFaceOtherVersion = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceOtherVersion();
        if (TextUtils.isEmpty(watchFaceOtherVersion)) {
            HwLog.e(TAG, "getWatchOtherThemeVersion otherThemeVersion is empty.");
            return "";
        }
        HwLog.i(TAG, "getWatchOtherThemeVersion otherThemeVersion:" + watchFaceOtherVersion);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("otherThemeVersion", watchFaceOtherVersion);
        } catch (JSONException unused) {
            HwLog.e(TAG, "Create WatchFaceInfo Json error");
        }
        return jSONObject.toString();
    }

    public String getWatchThemeVersion() {
        if (this.mHwWatchFaceBtManager == null) {
            this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.mContext);
        }
        String str = "";
        if (this.mHwWatchFaceBtManager.getConnectWatchDeviceInfo() == null) {
            HwLog.w(TAG, "getWatchThemeVersion, current Device info is null");
            return "";
        }
        WatchFaceSupportInfo watchFaceSupportInfo = this.mHwWatchFaceBtManager.getWatchFaceSupportInfo();
        if (watchFaceSupportInfo != null && watchFaceSupportInfo.getWatchFaceMaxVersion() != null) {
            str = watchFaceSupportInfo.getWatchFaceMaxVersion();
        }
        HwLog.i(TAG, "getWatchThemeVersion, return:" + str);
        return str;
    }

    public String getCurrentWatchFaceId() {
        String str;
        WatchResourcesInfo currentWatchFace = HwWatchFaceBtManager.getInstance(this.mContext).getCurrentWatchFace();
        String watchInfoId = currentWatchFace.getWatchInfoId();
        String watchInfoVersion = currentWatchFace.getWatchInfoVersion();
        if (watchInfoId == null || watchInfoVersion == null) {
            str = "";
        } else {
            StringBuffer stringBuffer = new StringBuffer(watchInfoId);
            stringBuffer.append("_");
            stringBuffer.append(watchInfoVersion);
            str = stringBuffer.toString();
        }
        HwLog.i(TAG, "getCurrentWatchFaceId() result: " + str);
        return str;
    }

    public String getPreWatchFaceIdStore() {
        if (this.mHwWatchFaceBtManager == null) {
            this.mHwWatchFaceBtManager = HwWatchFaceBtManager.getInstance(this.mContext);
        }
        ArrayList<WatchResourcesInfo> presetWatchInfo = this.mHwWatchFaceBtManager.getPresetWatchInfo();
        StringBuilder sb = new StringBuilder("");
        Iterator<WatchResourcesInfo> it = presetWatchInfo.iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            String watchInfoId = next.getWatchInfoId();
            String watchInfoVersion = next.getWatchInfoVersion();
            if (watchInfoId != null && watchInfoVersion != null) {
                StringBuffer stringBuffer = new StringBuffer(",");
                stringBuffer.append(watchInfoId);
                stringBuffer.append("_");
                stringBuffer.append(watchInfoVersion);
                sb.append(stringBuffer.toString());
            }
        }
        String sb2 = sb.toString();
        HwLog.i(TAG, "getPreWatchFaceIdStore return:" + sb2);
        return sb2;
    }

    public String getWatchFaceIdStore() {
        ArrayList<WatchResourcesInfo> noPresetWatchInfo = HwWatchFaceBtManager.getInstance(this.mContext).getNoPresetWatchInfo();
        StringBuilder sb = new StringBuilder("");
        Iterator<WatchResourcesInfo> it = noPresetWatchInfo.iterator();
        while (it.hasNext()) {
            WatchResourcesInfo next = it.next();
            String watchInfoId = next.getWatchInfoId();
            String watchInfoVersion = next.getWatchInfoVersion();
            if (watchInfoId != null && watchInfoVersion != null) {
                StringBuffer stringBuffer = new StringBuffer(",");
                stringBuffer.append(watchInfoId);
                stringBuffer.append("_");
                stringBuffer.append(watchInfoVersion);
                sb.append(stringBuffer.toString());
            }
        }
        String sb2 = sb.toString();
        HwLog.i(TAG, "getWatchFaceIdStore result: " + sb2);
        return sb2;
    }

    public List<WatchResourcesInfo> getWatchFaceResourcesInfo() {
        return HwWatchFaceBtManager.getInstance(this.mContext).getNoPresetWatchInfo();
    }

    public List<ScreenInfo> getCompatibleInfo() {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.e(TAG, "getCompatibleInfo() watchFaceSupportInfo is null.");
            return null;
        }
        List<ScreenInfo> compatibleList = watchFaceSupportInfo.getCompatibleList();
        if (ArrayUtils.isEmpty(compatibleList)) {
            HwLog.e(TAG, "getCompatibleInfo() compatibleList is null.");
        }
        return compatibleList;
    }

    private List<ScreenInfo> getPurchasedInfo() {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.e(TAG, "getPurchasedInfo() watchFaceSupportInfo is null.");
            return null;
        }
        List<ScreenInfo> purchasedList = watchFaceSupportInfo.getPurchasedList();
        if (ArrayUtils.isEmpty(purchasedList)) {
            HwLog.e(TAG, "getPurchasedInfo() purchasedList is null.");
        }
        return purchasedList;
    }

    public void setDefaultWatchFace(String str, String str2, String str3) {
        if (TextUtils.equals(str, t.a().e())) {
            HwLog.i(TAG, "setDefaultWatchFace -- current id is try out id.Not apply");
            return;
        }
        t.a().g(str, str2);
        if (d.a().a(str, str2, str3)) {
            return;
        }
        setDefaultWatchFace(str, str2, str3, false);
    }

    private void setDefaultWatchFace(String str, String str2, String str3, final boolean z) {
        String str4 = TAG;
        HwLog.i(str4, "setDefaultWatchFace() enter.");
        if (!this.mIsBtConnect) {
            transmitDeviceConnectState(false, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
            HwLog.i(str4, "setDefaultWatchFace() failed: Bluetooth not connected");
            return;
        }
        if (str == null || str2 == null) {
            HwLog.w(str4, "setDefaultWatchFace() Error:hiTopId or version is null");
            return;
        }
        WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
        watchResourcesInfo.setWatchInfoId(str);
        watchResourcesInfo.setVipFreeWatchFace(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVipFreeFromCache(str));
        watchResourcesInfo.setWatchInfoVersion(str2);
        watchResourcesInfo.setWatchScreen(str3);
        int i = 1;
        this.mIsAppSetWatchFace = true;
        if (t.a().i(str, str2)) {
            HwLog.i(str4, "setDefaultWatchFace -- is try out watch face.Send OPERATE_TYPE_TRY_OUT_START");
            i = 5;
        }
        this.mHwWatchFaceBtManager.operateDevice(watchResourcesInfo, i, new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.14
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i2, Object obj) {
                HwLog.i(HwWatchFaceManager.TAG, "setDefaultWatchFace() operateDevice response: " + i2);
                HwWatchFaceManager.this.dealSetDefaultWatchFaceResponse(i2, z, obj, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dealSetDefaultWatchFaceResponse(int r7, boolean r8, java.lang.Object r9, boolean r10) {
        /*
            r6 = this;
            java.lang.String r0 = com.huawei.watchface.api.HwWatchFaceManager.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "dealSetDefaultWatchFaceResponse() errorCode: "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r2 = ", isOperationType: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.huawei.watchface.utils.HwLog.i(r0, r1)
            android.os.Handler r1 = r6.mHandler
            if (r1 == 0) goto L24
            java.lang.Runnable r2 = r6.mResetInstallState
            r1.removeCallbacks(r2)
        L24:
            boolean r1 = r9 instanceof java.lang.String
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L3c
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r1 = "_"
            java.lang.String[] r9 = r9.split(r1)
            int r1 = r9.length
            r5 = 2
            if (r1 < r5) goto L3c
            r1 = r9[r3]
            r9 = r9[r2]
            goto L3e
        L3c:
            r9 = r4
            r1 = r9
        L3e:
            java.lang.String r5 = r6.mCurrentInstallWatchFaceHiTopId
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L50
            java.lang.String r5 = r6.mCurrentInstallWatchFaceHiTopId
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L50
            r6.mIsInstallState = r3
        L50:
            r5 = 103(0x67, float:1.44E-43)
            if (r7 == r5) goto L81
            r10 = 104(0x68, float:1.46E-43)
            if (r7 == r10) goto L6b
            com.huawei.watchface.t r10 = com.huawei.watchface.t.a()
            r10.a(r1, r9, r7)
            if (r8 == 0) goto Lba
            r6.setCurrentInstallWatchFaceHiTopId(r4)
            r6.setCurrentInstallWatchFaceVersion(r4)
            r6.installWaitInstallWatchFace(r1, r9, r3)
            goto Lba
        L6b:
            com.huawei.watchface.t r10 = com.huawei.watchface.t.a()
            r10.a(r1, r9, r7)
            r6.processApplyFailed(r1, r9)
            if (r8 == 0) goto Lba
            r6.setCurrentInstallWatchFaceHiTopId(r4)
            r6.setCurrentInstallWatchFaceVersion(r4)
            r6.installWaitInstallWatchFace(r1, r9, r3)
            goto Lba
        L81:
            java.lang.String r5 = "dealSetDefaultWatchFaceResponse success"
            com.huawei.watchface.utils.HwLog.i(r0, r5)
            r6.getDeviceAndWatchFaceInfoByBt()
            com.huawei.watchface.utils.callback.OperateWatchFaceCallback r0 = r6.mOperateCallback
            if (r0 == 0) goto L9f
            if (r10 != 0) goto L92
            r0.transmitSetDefaultWatchFaceResult(r1, r9, r2)
        L92:
            com.huawei.watchface.t r0 = com.huawei.watchface.t.a()
            boolean r0 = r0.k(r1, r9)
            if (r0 != 0) goto L9f
            com.huawei.watchface.dt.a(r2)
        L9f:
            com.huawei.watchface.utils.callback.OperateWatchFaceCallback r0 = r6.pOperateCallback
            if (r0 == 0) goto La8
            if (r10 != 0) goto La8
            r0.transmitSetDefaultWatchFaceResult(r1, r9, r2)
        La8:
            com.huawei.watchface.t r10 = com.huawei.watchface.t.a()
            r10.a(r1, r9, r7)
            if (r8 == 0) goto Lba
            r6.setCurrentInstallWatchFaceHiTopId(r4)
            r6.setCurrentInstallWatchFaceVersion(r4)
            r6.installWaitInstallWatchFace(r1, r9, r3)
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.api.HwWatchFaceManager.dealSetDefaultWatchFaceResponse(int, boolean, java.lang.Object, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installWaitInstallWatchFace(String str, String str2, int i) {
        String str3 = TAG;
        HwLog.i(str3, "installWaitInstallWatchFace, install waitInstallWatchFace hiTopId:" + str + ",version:" + str2 + ",errorCode:" + i);
        if (i != 0) {
            p.a(this.mContext).a(str, str2);
            String buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(str, str2);
            HwLog.i(str3, "installWaitInstallWatchFace, taskId:" + buildTaskId);
            FilePuller.getInstance(this.mContext).notifyDownloadStatus(buildTaskId, 3);
        }
        if (i == 141001) {
            HwLog.w(str3, "installWaitInstallWatchFace, ERROR_CODE_CONNECT_LOST");
            return;
        }
        LinkedHashMap<String, String> a2 = p.a(this.mContext).a();
        HwLog.i(str3, "installWaitInstallWatchFace map：" + GsonHelper.toJson(a2));
        if (a2.containsKey(str)) {
            a2.remove(str);
            HwLog.i(str3, "installWaitInstallWatchFace, remove installed task");
        }
        try {
            if (!a2.isEmpty()) {
                Iterator<Map.Entry<String, String>> it = a2.entrySet().iterator();
                String key = (it == null || !it.hasNext()) ? null : it.next().getKey();
                if (a2.containsKey(key)) {
                    applyWatchFace(key, a2.get(key), 1, "");
                    return;
                } else {
                    HwLog.w(str3, "installWaitInstallWatchFace, mWaitInstallMap not contain hiTopId");
                    return;
                }
            }
            HwLog.w(str3, "installWaitInstallWatchFace, Wait map is empty");
        } catch (Exception e) {
            HwLog.w(TAG, "installWaitInstallWatchFace Exception" + HwLog.printException(e));
        }
    }

    public void cancelInstallWatchFaceByH5(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "cancelInstallWatchFaceByH5() enter. hiTopId" + str);
        if (this.mHwDeviceConfigManager == null || HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() != 1) {
            HwLog.w(str3, "cancelInstallWatchFace() error: hwDeviceConfigManager is null or device is disconnect!");
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitCancelInstallWatchFaceResult(str, str2, 0);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitCancelInstallWatchFaceResult(str, str2, 0);
                return;
            }
            return;
        }
        cancelInstallWatchFace(str, str2);
    }

    public void cancelInstallWatchFace(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "cancelInstallWatchFace() enter. hiTopId" + str);
        String e = d.a().e(str);
        setCancelHiTopId(e);
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (this.mHwDeviceConfigManager == null) {
            HwLog.w(str3, "cancelInstallWatchFace() error: hwDeviceConfigManager is null");
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitCancelInstallWatchFaceResult(e, str2, 0);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitCancelInstallWatchFaceResult(e, str2, 0);
                return;
            }
            return;
        }
        cancelInstallWatchFace2(e, str2);
        k.a().b(e, str2);
    }

    public void decryptResult(int i, String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        operateWatchFaceCallback.transmitDecryptResult(i, str);
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitDecryptResult(i, str);
        }
    }

    private void cancelInstallWatchFace2(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "cancelInstallWatchFace2 enter: " + str);
        stopTransfer(str, str2, true);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mResetInstallState);
        }
        if (obtainJsonAbility().optBoolean("isSupportedDownloadManager")) {
            if (FilePuller.getInstance(this.mContext).getCurrentTaskStatus(FilePuller.getInstance(this.mContext).buildTaskId(str, str2)) != 6) {
                HwLog.i(str3, "cancelInstallWatchFace2 entry != PullResult.PULL_INSTALLING");
                OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
                if (operateWatchFaceCallback != null) {
                    operateWatchFaceCallback.transmitCancelInstallWatchFaceResult(str, str2, 1);
                }
                OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
                if (operateWatchFaceCallback2 != null) {
                    operateWatchFaceCallback2.transmitCancelInstallWatchFaceResult(str, str2, 1);
                }
                if (str.equals(getCurrentInstallWatchFaceHiTopId())) {
                    this.mIsInstallState = false;
                    HwLog.i(str3, "mOperateCallback() cancelTask, init mInstallState");
                }
                cancelTask(str, str2);
                return;
            }
            if (str.equals(getCurrentInstallWatchFaceHiTopId())) {
                return;
            }
            HwLog.i(str3, "cancelInstallWatchFace2 entry !hiTopId.equals(getCurrentInstallWatchFaceHiTopId()");
            OperateWatchFaceCallback operateWatchFaceCallback3 = this.mOperateCallback;
            if (operateWatchFaceCallback3 != null) {
                operateWatchFaceCallback3.transmitCancelInstallWatchFaceResult(str, str2, 1);
            }
            OperateWatchFaceCallback operateWatchFaceCallback4 = this.pOperateCallback;
            if (operateWatchFaceCallback4 != null) {
                operateWatchFaceCallback4.transmitCancelInstallWatchFaceResult(str, str2, 1);
                return;
            }
            return;
        }
        if (this.mCurrentInstallState != 3) {
            HwLog.i(str3, "cancelInstallWatchFace2 entry mCurrentInstallState != WatchFaceConstant.INSTALL_STATE_TRANSFER");
            OperateWatchFaceCallback operateWatchFaceCallback5 = this.mOperateCallback;
            if (operateWatchFaceCallback5 != null) {
                operateWatchFaceCallback5.transmitCancelInstallWatchFaceResult(str, str2, 1);
            }
            OperateWatchFaceCallback operateWatchFaceCallback6 = this.pOperateCallback;
            if (operateWatchFaceCallback6 != null) {
                operateWatchFaceCallback6.transmitCancelInstallWatchFaceResult(str, str2, 1);
            }
            if (str.equals(getCurrentInstallWatchFaceHiTopId())) {
                this.mIsInstallState = false;
                HwLog.i(str3, "mOperateCallback() cancelTask, init mInstallState");
            }
            cancelTask(str, str2);
        }
    }

    private void stopTransfer(final String str, final String str2, final boolean z) {
        String buildTaskId;
        String str3 = TAG;
        HwLog.i(str3, "stopTransfer() hiTopId: " + str + ", version: " + str2 + ",tCurrentInstallWatchFaceHiTopId:" + getCurrentInstallWatchFaceHiTopId());
        if (TextUtils.isEmpty(str)) {
            HwLog.w(str3, "stopTransfer, hiTopId is empty.");
            return;
        }
        if (!str.equals(getCurrentInstallWatchFaceHiTopId()) && !d.a().h(str)) {
            dealTaskTransferredOrCanceled(str, str2);
            HwLog.w(str3, "stopTransfer, not current install task");
            return;
        }
        if (getInstance(this.mContext).mVipFreeWatchFaceInfos.containsKey(str)) {
            buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(WatchResourcesInfo.markUpHiTopId(str), str2);
        } else if (d.a().h(str)) {
            buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(d.a().i(str), str2);
        } else {
            buildTaskId = FilePuller.getInstance(this.mContext).buildTaskId(str, str2);
        }
        HwLog.i(str3, "stopTransfer() taskId: " + buildTaskId + ", version: " + str2 + "tCurrentInstallWatchFaceHiTopId:" + getCurrentInstallWatchFaceHiTopId());
        HwDeviceConfigManager.getInstance(this.mContext).a(buildTaskId, 1, new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.15
            @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
            public void onResponse(int i, Object obj) {
                HwLog.i(HwWatchFaceManager.TAG, "stopTransfer() response code: " + i + ", cancelTime: " + System.currentTimeMillis());
                if (!(obj instanceof String)) {
                    HwLog.i(HwWatchFaceManager.TAG, "stopTransfer() response objData is not string");
                    return;
                }
                String str4 = (String) obj;
                HwLog.i(HwWatchFaceManager.TAG, "stopTransfer() result: " + str4);
                if (!z) {
                    HwLog.i(HwWatchFaceManager.TAG, "stopTransfer() is not reset");
                    return;
                }
                String[] split = str4.split("_");
                HwLog.i(HwWatchFaceManager.TAG, "stopTransfer() result hiTopIdMarkDown: " + split[0]);
                String j = d.a().j(WatchResourcesInfo.markDownHiTopId(split[0]));
                HwWatchFaceManager.this.mIsCanceled = false;
                if (i == 20003) {
                    if (HwWatchFaceManager.this.mHandler != null) {
                        HwWatchFaceManager.this.mHandler.removeCallbacks(HwWatchFaceManager.this.mResetInstallState);
                    }
                    HwWatchFaceManager.this.mLastPercentage = -1;
                    if (HwWatchFaceManager.this.mOperateCallback != null && split.length >= 2) {
                        HwWatchFaceManager.this.mOperateCallback.transmitCancelInstallWatchFaceResult(j, split[1], 1);
                    }
                    if (HwWatchFaceManager.this.pOperateCallback != null && split.length >= 2) {
                        HwWatchFaceManager.this.pOperateCallback.transmitCancelInstallWatchFaceResult(j, split[1], 1);
                    }
                    HwWatchFaceManager.this.changeInstallState(0);
                    HwWatchFaceManager.this.dealTaskTransferredOrCanceled(str, str2);
                    if (str.equals(HwWatchFaceManager.this.getCurrentInstallWatchFaceHiTopId())) {
                        HwWatchFaceManager.this.mIsInstallState = false;
                        HwLog.i(HwWatchFaceManager.TAG, "cancelTask, init mInstallState");
                    }
                    HwWatchFaceManager.this.cancelTask(str, str2);
                    return;
                }
                if (HwWatchFaceManager.this.mOperateCallback != null && split.length >= 2) {
                    HwWatchFaceManager.this.mOperateCallback.transmitCancelInstallWatchFaceResult(j, split[1], 0);
                }
                if (HwWatchFaceManager.this.pOperateCallback == null || split.length < 2) {
                    return;
                }
                HwWatchFaceManager.this.pOperateCallback.transmitCancelInstallWatchFaceResult(j, split[1], 0);
            }
        });
    }

    public void deleteWatchFace(String str, String str2) {
        HwLog.i(TAG, "deleteWatchFace has parms");
        this.mCurrentDeleteHiTopId = str;
        this.mCurrentDeleteVersion = str2;
        showDialogNoTitle(DensityUtil.getStringById(R$string.IDS_watchface_delete_prompt), new View.OnClickListener() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HwWatchFaceManager.lambda$deleteWatchFace$6(view);
            }
        });
    }

    static /* synthetic */ void lambda$deleteWatchFace$6(View view) {
        HwLog.i(TAG, "deleteWatchFace");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void deleteWatchFace(boolean z) {
        HwLog.i(TAG, "deleteWatchFace() start to show dialog");
        if (z) {
            showToast(DensityUtil.getStringById(R$string.watch_face_preset_not_delete));
        } else {
            showDialogNoTitle(DensityUtil.getStringById(R$string.IDS_watchface_delete_prompt), new View.OnClickListener() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HwWatchFaceManager.this.m880x7055b031(view);
                }
            });
        }
    }

    /* renamed from: lambda$deleteWatchFace$7$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m880x7055b031(View view) {
        HwLog.i(TAG, "deleteWatchFace() onClick.");
        doDeleteWatchFace();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void doDeleteWatchFace() {
        doDeleteWatchFace(this.mCurrentDeleteHiTopId, this.mCurrentDeleteVersion, this.mDeleteMyWatchFaceCallback);
    }

    public void doDeleteWatchFace(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        String str3 = TAG;
        HwLog.i(str3, "doDeleteWatchFace() enter hitopid=" + str + ",version=" + str2);
        if (!this.mIsBtConnect) {
            transmitDeviceConnectState(false, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
            HwLog.i(str3, "doDeleteWatchFace() bluetooth not connected");
        } else {
            if (str != null && str2 != null) {
                WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
                watchResourcesInfo.setWatchInfoId(str);
                watchResourcesInfo.setVipFreeWatchFace(HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getVipFreeFromCache(str));
                watchResourcesInfo.setWatchInfoVersion(str2);
                this.mHwWatchFaceBtManager.operateDevice(watchResourcesInfo, 2, iBaseResponseCallback);
                return;
            }
            HwLog.w(str3, "doDeleteWatchFace() failed: current id or version is null");
        }
    }

    public void deleteDesignationWatchFace(String str, String str2, DelectUserDesignationWatchFaceCallback delectUserDesignationWatchFaceCallback) {
        this.mCurrentDeleteHiTopId = str;
        this.mCurrentDeleteVersion = str2;
        this.mDeleteUserDesignationWatchFaceCallback = delectUserDesignationWatchFaceCallback;
        boolean isPreWatchFace = isPreWatchFace(str);
        HwLog.i(TAG, "Design isPreWatchFace:" + isPreWatchFace);
        deleteWatchFace(isPreWatchFace);
    }

    public void h5deleteWatchFace(String str, String str2) {
        this.mCurrentDeleteHiTopId = str;
        this.mCurrentDeleteVersion = str2;
        boolean isPreWatchFace = isPreWatchFace(str);
        String str3 = TAG;
        HwLog.i(str3, "deleteWatchFace isPreWatchFace:" + isPreWatchFace);
        if (!this.mIsBtConnect) {
            HwLog.d(str3, "bluetooth not connected");
            return;
        }
        if (isPreWatchFace) {
            HwLog.d(str3, "preWatchFace is not delete");
            return;
        }
        if (str != null && str2 != null) {
            WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
            watchResourcesInfo.setWatchInfoId(str);
            watchResourcesInfo.setVipFreeWatchFace(false);
            watchResourcesInfo.setWatchInfoVersion(str2);
            this.mHwWatchFaceBtManager.operateDevice(watchResourcesInfo, 2, this.mDeleteMyWatchFaceCallback);
            return;
        }
        HwLog.w(str3, "doDeleteWatchFace() failed: current id or version is null");
    }

    public boolean isPreWatchFace(String str) {
        String preWatchFaceIdStore = getPreWatchFaceIdStore();
        if (!TextUtils.isEmpty(preWatchFaceIdStore) && !TextUtils.isEmpty(str)) {
            return preWatchFaceIdStore.contains(str);
        }
        HwLog.i(TAG, "isPreWatchFace() preWatchFace or hiTopId is null");
        return false;
    }

    public void setWatchFaceDeleteId(String str, String str2) {
        this.mCurrentDeleteVersion = str2;
        String str3 = TAG;
        HwLog.i(str3, "setWatchFaceDeleteId, delete hiTopId:" + str);
        if (!TextUtils.isEmpty(str) && str.contains("_")) {
            String[] split = str.split("_");
            if (split.length >= 2) {
                this.mCurrentDeleteName = split[1];
            } else {
                this.mCurrentDeleteName = "";
            }
            this.mCurrentDeleteHiTopId = split[0];
            HwLog.i(str3, "setWatchFaceDeleteId, delete hiTopId and name:" + this.mCurrentDeleteHiTopId + this.mCurrentDeleteName);
        } else {
            this.mCurrentDeleteHiTopId = str;
            this.mCurrentDeleteName = "";
        }
        HwLog.i(str3, "setWatchFaceDeleteId, hiTopId:" + str + "currentInstallHiTopId:" + this.mCurrentInstallWatchFaceHiTopId);
    }

    public void getWatchFaceNames(String str) {
        String str2 = TAG;
        HwLog.i(str2, "getWatchFaceNames" + str);
        if (str == null) {
            HwLog.w(str2, "watchFaceIds is null");
        } else {
            final ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(SafeString.replace(str, " ", "").split(",")));
            HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceName(arrayList, this.mContext.getResources().getConfiguration().locale.getLanguage().equals(Locale.CHINA.getLanguage()) ? 2 : 1, new IBaseResponseCallback() { // from class: com.huawei.watchface.api.HwWatchFaceManager.16
                @Override // com.huawei.watchface.utils.callback.IBaseResponseCallback
                public void onResponse(int i, Object obj) {
                    HwLog.i(HwWatchFaceManager.TAG, "getWatchFaceName, onResponse:" + i);
                    if (i != 101) {
                        HwLog.w(HwWatchFaceManager.TAG, "getWatchFaceName, onResponse failed");
                        return;
                    }
                    try {
                        HwWatchFaceManager.this.transmitWatchFaceNames(arrayList, CommonUtils.a(obj));
                    } catch (IllegalArgumentException unused) {
                        HwLog.e(HwWatchFaceManager.TAG, "getWatchFaceNames, IllegalArgumentException");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transmitWatchFaceNames(ArrayList<String> arrayList, HashMap<String, WatchResourcesInfo> hashMap) {
        if (this.mOperateCallback == null) {
            HwLog.w(TAG, "transmitWatchFaceNames, error call is null");
            return;
        }
        if (arrayList == null || hashMap == null) {
            HwLog.w(TAG, "transmitWatchFaceNames, error params is null");
            return;
        }
        HwLog.i(TAG, "transmitWatchFaceNames, watchFaceIdList size:" + arrayList.size() + "watchFaceMap size:" + hashMap.size());
        dealWatchFaceInfoTransmit(hashMap);
    }

    private void dealWatchFaceInfoTransmit(HashMap<String, WatchResourcesInfo> hashMap) {
        String str = this.mContext.getFilesDir().getAbsolutePath() + File.separator + "watchfaceAsset";
        StringBuffer stringBuffer = new StringBuffer(1);
        for (String str2 : hashMap.keySet()) {
            String watchInfoName = hashMap.get(str2).getWatchInfoName();
            String watchInfoBrief = hashMap.get(str2).getWatchInfoBrief();
            int watchInfoSize = hashMap.get(str2).getWatchInfoSize();
            HwLog.d(TAG, "dealWatchFaceInfoTransmit, name:" + watchInfoName + " brief: " + watchInfoBrief + " size: " + watchInfoSize);
            if ("000000001".equals(str2)) {
                stringBuffer = createDesignerWatchFaceUrl(stringBuffer, watchInfoName);
            } else {
                stringBuffer.append(str2).append("_").append(watchInfoName).append("_").append(watchInfoBrief).append("_").append(watchInfoSize).append("_").append(str + File.separator + str2 + ".png").append(",");
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        if (!TextUtils.isEmpty(stringBuffer2) && stringBuffer2.endsWith("_")) {
            stringBuffer2 = SafeString.substring(stringBuffer2, 0, stringBuffer2.length() - 1);
        }
        String str3 = TAG;
        HwLog.i(str3, "transmitWatchFaceNames:" + stringBuffer2);
        if (TextUtils.isEmpty(stringBuffer2)) {
            return;
        }
        OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitWatchFaceNames(stringBuffer2);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.mOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitWatchFaceNames(stringBuffer2);
        } else {
            HwLog.w(str3, "transmitWatchFaceNames error");
        }
    }

    private StringBuffer createDesignerWatchFaceUrl(StringBuffer stringBuffer, String str) {
        if (getCurrentInstallWatchFaceVersion() == null) {
            setCurrentInstallWatchFaceVersion(getRandomVersion());
        }
        StringBuilder sb = new StringBuilder(this.mDownLoadPath);
        sb.append("000000001_" + getCurrentInstallWatchFaceVersion());
        stringBuffer.append("000000001").append("_").append(str).append("_").append(sb.toString() + File.separator + WatchFaceConstant.PREVIEW_RES + File.separator + "cover.jpg").append(",");
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder("createDesignerWatchFaceUrl, transmitWatchFaceNames:");
        sb2.append(stringBuffer.toString());
        HwLog.i(str2, sb2.toString());
        return stringBuffer;
    }

    public void showToast(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(TAG, "content is null");
            return;
        }
        if (str.equals(DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice)) || str.equals(DensityUtil.getStringById(R$string.IDS_watchface_device_no_connect))) {
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitDeviceConnectState(false, str);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitDeviceConnectState(false, str);
                return;
            }
            return;
        }
        OperateWatchFaceCallback operateWatchFaceCallback3 = this.mOperateCallback;
        if (operateWatchFaceCallback3 == null) {
            HwLog.w(TAG, "showToast, operateWatchFaceCallback is null");
            return;
        }
        operateWatchFaceCallback3.showToast(str);
        OperateWatchFaceCallback operateWatchFaceCallback4 = this.pOperateCallback;
        if (operateWatchFaceCallback4 != null) {
            operateWatchFaceCallback4.showToast(str);
        }
    }

    public void showToast() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.showToast(DensityUtil.getStringById(R$string.IDS_hwh_show_save_failed));
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.showToast(DensityUtil.getStringById(R$string.IDS_hwh_show_save_failed));
        }
    }

    private void showDialogNoPermission(final String str, final View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str) || this.mOperateCallback == null) {
            HwLog.w(TAG, "showDialogNoPermission() contentInfo is null or mOperateWatchFaceCallback is null.");
            return;
        }
        if (str.equals(DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice)) || str.equals(DensityUtil.getStringById(R$string.IDS_watchface_device_no_connect))) {
            this.mOperateCallback.transmitDeviceConnectState(false, str);
            OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitDeviceConnectState(false, str);
                return;
            }
            return;
        }
        final Context customWebViewContext = this.mOperateCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w(TAG, "showDialogNoPermission() customWebViewContext is not Activity.");
        } else {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager.17
                @Override // java.lang.Runnable
                public void run() {
                    HwWatchFaceManager.this.mDialogNoPermission = new cm.a(customWebViewContext).a("").b(str).a(R$string.IDS_user_permission_know, onClickListener).a();
                    if (!HwWatchFaceManager.this.mDialogNoPermission.isShowing()) {
                        HwLog.i(HwWatchFaceManager.TAG, "showDialogNoPermission() dialogNoPermission is Showing.");
                        HwWatchFaceManager.this.mDialogNoPermission.setCancelable(false);
                        HwWatchFaceManager.this.mDialogNoPermission.show();
                    }
                    HwLog.i(HwWatchFaceManager.TAG, "showDialogNoPermission() dialogNoPermission has showed.");
                }
            });
        }
    }

    private void showDialogNoTitle(final String str, final View.OnClickListener onClickListener) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        final Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w(TAG, "showDialogNoTitle() customWebViewContext is not Activity");
        } else {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager.18
                @Override // java.lang.Runnable
                public void run() {
                    HwWatchFaceManager.this.mNoTitleDialog = new cn.a(customWebViewContext).a(str).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.api.HwWatchFaceManager.18.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            HwWatchFaceManager.this.dismissNoTitleDialog();
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }).a(R$string.confirm, onClickListener).a();
                    if (!HwWatchFaceManager.this.mNoTitleDialog.isShowing() && !((Activity) customWebViewContext).isFinishing()) {
                        HwLog.i(HwWatchFaceManager.TAG, "showDialogNoTitle() mNoTitleDialog is showing");
                        HwWatchFaceManager.this.mNoTitleDialog.setCancelable(false);
                        HwWatchFaceManager.this.mNoTitleDialog.show();
                    }
                    HwLog.i(HwWatchFaceManager.TAG, "showDialogNoTitle() dialog has showed");
                }
            });
        }
    }

    private void showFirstInstallRemindDialogNoTitle(final String str, final View.OnClickListener onClickListener, final InstallWatchFaceBean installWatchFaceBean) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback == null) {
            return;
        }
        final Context customWebViewContext = operateWatchFaceCallback.getCustomWebViewContext();
        if (!(customWebViewContext instanceof Activity)) {
            HwLog.w(TAG, "showFirstInstallRemindDialogNoTitle() customWebViewContext is not Activity");
        } else {
            ((Activity) customWebViewContext).runOnUiThread(new Runnable() { // from class: com.huawei.watchface.api.HwWatchFaceManager.19
                @Override // java.lang.Runnable
                public void run() {
                    HwWatchFaceManager.this.mNoTitleDialog = new cn.a(customWebViewContext).a(str).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.api.HwWatchFaceManager.19.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            HwWatchFaceManager.this.dismissNoTitleDialog();
                            if (HwWatchFaceManager.this.mOperateCallback != null) {
                                HwWatchFaceManager.this.mOperateCallback.transmitDownloadWatchFaceResult(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion(), 2);
                            }
                            if (HwWatchFaceManager.this.pOperateCallback != null) {
                                HwWatchFaceManager.this.pOperateCallback.transmitDownloadWatchFaceResult(installWatchFaceBean.getWatchFaceHiTopId(), installWatchFaceBean.getVersion(), 2);
                            }
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    }).a(R$string.confirm, onClickListener).a();
                    if (!HwWatchFaceManager.this.mNoTitleDialog.isShowing() && !((Activity) customWebViewContext).isFinishing()) {
                        HwLog.i(HwWatchFaceManager.TAG, "showFirstInstallRemindDialogNoTitle() mNoTitleDialog is showing");
                        HwWatchFaceManager.this.mNoTitleDialog.setCancelable(false);
                        HwWatchFaceManager.this.mNoTitleDialog.show();
                    }
                    HwLog.i(HwWatchFaceManager.TAG, "showFirstInstallRemindDialogNoTitle() dialog has showed");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0061, code lost:
    
        if (com.huawei.watchface.api.HwWatchFaceApi.getInstance(r6.mContext).getIsTaskExecuting() != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void processInstallFailed(java.lang.String r7, java.lang.String r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = com.huawei.watchface.api.HwWatchFaceManager.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "processInstallFailed() errorCode: "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r2 = ", hiTopId: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = ", version: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.huawei.watchface.utils.HwLog.i(r0, r1)
            android.os.Handler r1 = r6.mHandler
            if (r1 == 0) goto L2c
            java.lang.Runnable r2 = r6.mResetInstallState
            r1.removeCallbacks(r2)
        L2c:
            r1 = 0
            r6.changeInstallState(r1)
            r2 = 20000(0x4e20, float:2.8026E-41)
            r3 = -6
            if (r9 == r3) goto L74
            r4 = -5
            if (r9 == r4) goto L6d
            r4 = -3
            if (r9 == r4) goto L66
            r4 = -2
            if (r9 == r4) goto L51
            r4 = -1
            if (r9 == r4) goto L4a
            if (r9 == r2) goto L63
            int r4 = com.huawei.watchface.R$string.photo_album_dialog_loading_install_failed
            java.lang.String r4 = com.huawei.watchface.utils.DensityUtil.getStringById(r4)
            goto L7a
        L4a:
            int r4 = com.huawei.watchface.R$string.network_unstable_notice2
            java.lang.String r4 = com.huawei.watchface.utils.DensityUtil.getStringById(r4)
            goto L7a
        L51:
            int r4 = com.huawei.watchface.R$string.IDS_watchface_install_retry
            java.lang.String r4 = com.huawei.watchface.utils.DensityUtil.getStringById(r4)
            android.content.Context r5 = r6.mContext
            com.huawei.watchface.api.HwWatchFaceApi r5 = com.huawei.watchface.api.HwWatchFaceApi.getInstance(r5)
            boolean r5 = r5.getIsTaskExecuting()
            if (r5 == 0) goto L7a
        L63:
            java.lang.String r4 = ""
            goto L7a
        L66:
            int r4 = com.huawei.watchface.R$string.number_of_dials_has_reached_maximum_notice
            java.lang.String r4 = com.huawei.watchface.utils.DensityUtil.getStringById(r4)
            goto L7a
        L6d:
            int r4 = com.huawei.watchface.R$string.wearable_device_is_not_connected_notice
            java.lang.String r4 = com.huawei.watchface.utils.DensityUtil.getStringById(r4)
            goto L7a
        L74:
            int r4 = com.huawei.watchface.R$string.IDS_watchface_device_insufficient_space_new
            java.lang.String r4 = com.huawei.watchface.utils.DensityUtil.getStringById(r4)
        L7a:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L95
            boolean r5 = com.huawei.watchface.utils.CommonUtils.y()
            if (r5 == 0) goto L8d
            if (r9 != r3) goto L89
            goto L8d
        L89:
            r6.showToast(r4)
            goto L95
        L8d:
            com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda2 r3 = new com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda2
            r3.<init>()
            r6.showDialogNoPermission(r4, r3)
        L95:
            if (r7 == 0) goto Lba
            if (r8 == 0) goto Lba
            if (r9 == r2) goto Lab
            com.huawei.watchface.utils.callback.OperateWatchFaceCallback r9 = r6.mOperateCallback
            r0 = 3
            if (r9 == 0) goto La3
            r9.transmitInstallWatchFaceResult(r7, r8, r0)
        La3:
            com.huawei.watchface.utils.callback.OperateWatchFaceCallback r9 = r6.pOperateCallback
            if (r9 == 0) goto Lbf
            r9.transmitInstallWatchFaceResult(r7, r8, r0)
            goto Lbf
        Lab:
            com.huawei.watchface.utils.callback.OperateWatchFaceCallback r9 = r6.mOperateCallback
            if (r9 == 0) goto Lb2
            r9.transmitInstallWatchFaceResult(r7, r8, r1)
        Lb2:
            com.huawei.watchface.utils.callback.OperateWatchFaceCallback r9 = r6.pOperateCallback
            if (r9 == 0) goto Lbf
            r9.transmitInstallWatchFaceResult(r7, r8, r1)
            goto Lbf
        Lba:
            java.lang.String r7 = "processInstallFailed, currentInstallHiTopId is null"
            com.huawei.watchface.utils.HwLog.w(r0, r7)
        Lbf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.api.HwWatchFaceManager.processInstallFailed(java.lang.String, java.lang.String, int):void");
    }

    /* renamed from: lambda$processInstallFailed$8$com-huawei-watchface-api-HwWatchFaceManager, reason: not valid java name */
    /* synthetic */ void m886x482fa3f4(View view) {
        cm cmVar = this.mDialogNoPermission;
        if (cmVar != null) {
            cmVar.dismiss();
            this.mDialogNoPermission = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public int getWatchFaceInstallState() {
        HwLog.i(TAG, "curInstallState:" + this.mCurrentInstallState);
        return this.mCurrentInstallState;
    }

    public void startSaveDialog() {
        this.mOperateCallback.showLoadingDialog(DensityUtil.getStringById(R$string.saving));
        OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.showLoadingDialog(DensityUtil.getStringById(R$string.saving));
        }
    }

    public void notifyH5DeviceIsBreak() {
        transmitDeviceConnectState(false, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
    }

    public void dismissStartSaveDialog() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.hideLoadingDialog();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.hideLoadingDialog();
        }
    }

    public void saveSuccess() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.saveSuccessComeback();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.saveSuccessComeback();
        }
    }

    public void processDeleteFailed(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "processDeleteFailed() enter. id: " + str + ", version: " + str2);
        showToast(DensityUtil.getStringById(R$string.delete_fail));
        if (str != null && str2 != null) {
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitDeleteWatchFaceResult(str, str2, 0);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitDeleteWatchFaceResult(str, str2, 0);
                return;
            }
            return;
        }
        HwLog.w(str3, "processDeleteFailed() error: id or version is null");
    }

    private void processApplyFailed(String str, String str2) {
        String str3 = TAG;
        HwLog.i(str3, "processApplyFailed() enter.");
        showToast(DensityUtil.getStringById(R$string.watch_face_apply_failed));
        if (str != null && str2 != null) {
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitSetDefaultWatchFaceResult(str, str2, 0);
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitSetDefaultWatchFaceResult(str, str2, 0);
                return;
            }
            return;
        }
        HwLog.w(str3, "processApplyFailed() error: id or version is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissNoTitleDialog() {
        cn cnVar = this.mNoTitleDialog;
        if (cnVar != null) {
            cnVar.dismiss();
            this.mNoTitleDialog = null;
        }
    }

    public void destroy() {
        cancelInstallWatchFace(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion());
        if (this.mConnectStateChangedReceiver != null) {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mConnectStateChangedReceiver);
        }
        if (this.mBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mBroadcastReceiver);
        }
        if (this.mFileTransferStateCallback != null) {
            this.mFileTransferStateCallback = null;
        }
        if (this.mAppTransferFileResultAIDLCallback != null) {
            this.mAppTransferFileResultAIDLCallback = null;
        }
        if (this.mDeleteUserDesignationWatchFaceCallback != null) {
            this.mDeleteUserDesignationWatchFaceCallback = null;
        }
        k.a().b();
        destroyInstance();
        HwWatchFaceBtManager.getInstance(this.mContext).destroy();
        p.a(this.mContext).n();
    }

    private static void destroyInstance() {
        synchronized (HwWatchFaceManager.class) {
            if (sInstance != null) {
                HwLog.i(TAG, "destroyInstance() enter.");
                sInstance = null;
            }
        }
    }

    public void transmitWatchInfoChange(int i) {
        String str = TAG;
        HwLog.i(str, "transmitWatchInfoChange() installState: " + i);
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() != 1) {
            HwLog.e(str, "transmitWatchInfoChange() current connect is error ");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - this.mLastTransmitWatchInfoChange) >= 500) {
            OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
            if (operateWatchFaceCallback != null) {
                operateWatchFaceCallback.transmitWatchInfoChange(i);
                this.mLastTransmitWatchInfoChange = currentTimeMillis;
            }
            OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
            if (operateWatchFaceCallback2 != null) {
                operateWatchFaceCallback2.transmitWatchInfoChange(i);
                return;
            }
            return;
        }
        HwLog.w(str, "transmitWatchInfoChange() error:Short time interval");
    }

    public void transmitMembershipDialogStatus() {
        HwLog.i(TAG, "transmitMembershipDialogStatus()");
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitMembershipDialogStatus();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitMembershipDialogStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeInstallState(int i) {
        this.mCurrentInstallState = i;
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mHandler.removeCallbacks(this.mResetInstallStateRunnable);
        if (this.mCurrentInstallState == 0) {
            return;
        }
        this.mHandler.postDelayed(this.mResetInstallStateRunnable, 60000L);
    }

    public boolean isBtConnect() {
        return this.mIsBtConnect;
    }

    public void setIsBtConnect(boolean z) {
        this.mIsBtConnect = z;
    }

    public void setCurrentDeviceName(String str) {
        this.mCurrentDeviceName = str;
    }

    private void pullWatchFaceFile(String str, String str2, String str3, WatchFaceInfo watchFaceInfo, boolean z, boolean z2, final IBaseResponseCallback iBaseResponseCallback) {
        p.a(this.mContext).a(str, str2, str3, watchFaceInfo, z, z2, new PullListenerInterface() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda1
            @Override // com.huawei.watchface.mvp.model.filedownload.PullListenerInterface
            public final void onPullingChange(PullTask pullTask, PullResult pullResult) {
                IBaseResponseCallback.this.onResponse(pullResult.fetchStatus(), pullResult);
            }
        });
    }

    public void notifyTryoutWatchFaceFinish(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        HwLog.i(TAG, "notifyTryoutWatchFaceFinish() enter.");
        notifyDeviceTryOutOperate(str, str2, 6, iBaseResponseCallback);
    }

    public void notifyTryOutWatchFacePaid(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        HwLog.i(TAG, "notifyTryOutWatchFacePaid() enter.");
        notifyDeviceTryOutOperate(str, str2, 7, iBaseResponseCallback);
    }

    private void notifyDeviceTryOutOperate(String str, String str2, int i, IBaseResponseCallback iBaseResponseCallback) {
        String str3 = TAG;
        HwLog.i(str3, "notifyDeviceTryOutOperate() enter.");
        if (!this.mIsBtConnect) {
            transmitDeviceConnectState(false, DensityUtil.getStringById(R$string.wearable_device_is_not_connected_notice));
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.onError(1);
            }
            HwLog.i(str3, "notifyDeviceTryOutOperate() bluetooth not connected");
            return;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
            watchResourcesInfo.setWatchInfoId(str);
            watchResourcesInfo.setWatchInfoVersion(str2);
            this.mHwWatchFaceBtManager.operateDevice(watchResourcesInfo, i, iBaseResponseCallback);
            return;
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.onError(2);
        }
        HwLog.w(str3, "notifyDeviceTryOutOperate() failed: current id or version is null");
    }

    private int getPowerLevel() {
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.e(TAG, "getPowerLevel() watchFaceSupportInfo is null.");
            return 0;
        }
        return watchFaceSupportInfo.getPowerLevel();
    }

    private int getIsWearSupport() {
        if (!CommonUtils.D()) {
            HwLog.e(TAG, "getIsWearSupport() isCloudConfigSupportedWear result: is not supported.");
            return 0;
        }
        WatchFaceSupportInfo watchFaceSupportInfo = HwWatchFaceBtManager.getInstance(this.mContext).getWatchFaceSupportInfo();
        if (watchFaceSupportInfo == null) {
            HwLog.e(TAG, "getIsWearSupport() watchFaceSupportInfo is null.");
            return 0;
        }
        return watchFaceSupportInfo.getIsWearSupport();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAnalyticsInstallEvent(String str, String str2) {
        final eg egVar = new eg();
        egVar.a(System.currentTimeMillis());
        egVar.l(str);
        egVar.m(str2);
        Optional.ofNullable((CustomWatchfaceInfo) dg.getMapValue(this.mInstallEventInfo, str)).ifPresent(new Consumer() { // from class: com.huawei.watchface.api.HwWatchFaceManager$$ExternalSyntheticLambda3
            @Override // com.huawei.watchface.videoedit.sysc.Consumer
            public final void accept(Object obj) {
                HwWatchFaceManager.lambda$initAnalyticsInstallEvent$10(eg.this, (CustomWatchfaceInfo) obj);
            }
        });
        this.mInstallEventHashMap.put(str, egVar);
    }

    static /* synthetic */ void lambda$initAnalyticsInstallEvent$10(eg egVar, CustomWatchfaceInfo customWatchfaceInfo) {
        egVar.b(customWatchfaceInfo.getTitle());
        egVar.c(customWatchfaceInfo.getCnTitle());
        egVar.a(customWatchfaceInfo.getUseType());
        egVar.d(customWatchfaceInfo.getFileSize());
    }

    private ee getAnalyticsDownloadEvent(InstallWatchFaceBean installWatchFaceBean) {
        ee eeVar = new ee();
        eeVar.a(System.currentTimeMillis());
        eeVar.f(installWatchFaceBean.getHashCode());
        eeVar.g(installWatchFaceBean.getFileUrl());
        eeVar.l(installWatchFaceBean.getWatchFaceHiTopId());
        eeVar.m(installWatchFaceBean.getVersion());
        eeVar.e("2");
        eeVar.h(installWatchFaceBean.getTitle());
        eeVar.i(installWatchFaceBean.getCnTitle());
        eeVar.c(installWatchFaceBean.getFileSize());
        eeVar.b(String.valueOf(installWatchFaceBean.getInstallationType()));
        eeVar.j(String.valueOf(installWatchFaceBean.getResourceType()));
        eeVar.a(String.valueOf(installWatchFaceBean.getProductId()));
        return eeVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnFileRespond(int i) {
        String currentInstallWatchFaceVersion;
        HwLog.i(TAG, "handleOnFileRespond() checkResult: " + i);
        try {
            String currentInstallWatchFaceHiTopId = getCurrentInstallWatchFaceHiTopId();
            String currentTransferWatchFaceHiTopId = getCurrentTransferWatchFaceHiTopId();
            if (!TextUtils.isEmpty(currentTransferWatchFaceHiTopId) && !TextUtils.equals(currentTransferWatchFaceHiTopId, currentInstallWatchFaceHiTopId)) {
                currentInstallWatchFaceVersion = getCurrentTransferWatchFaceVersion();
                currentInstallWatchFaceHiTopId = currentTransferWatchFaceHiTopId;
            } else {
                currentInstallWatchFaceVersion = getCurrentInstallWatchFaceVersion();
            }
            eo.a(getInstallEvent(currentInstallWatchFaceHiTopId), i == 0 ? String.valueOf(142001) : String.valueOf(i));
            this.mIsCanceled = false;
            this.mLastPercentage = -1;
            dealTaskTransferredOrCanceled(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion);
            if (i == 0) {
                String currentInstallWatchFaceHiTopId2 = getCurrentInstallWatchFaceHiTopId();
                String currentInstallWatchFaceVersion2 = getCurrentInstallWatchFaceVersion();
                processInstallFailed(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), -2);
                p.a(this.mContext).a(currentInstallWatchFaceHiTopId2, currentInstallWatchFaceVersion2, 1, new InstallWatchFaceBean(currentInstallWatchFaceHiTopId2, currentInstallWatchFaceVersion2, "", "", "", "", false));
                installWaitInstallWatchFace(currentInstallWatchFaceHiTopId2, currentInstallWatchFaceVersion2, i);
            }
        } catch (Exception e) {
            HwLog.e(TAG, "handleOnFileRespond error =" + HwLog.printException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnUpgradeFailed(int i, String str) {
        HwLog.i(TAG, "handleOnUpgradeFailed() onUpgradeFailed: " + i + ", errorMessage: " + str);
        try {
            eo.a(getInstallEvent(getCurrentInstallWatchFaceHiTopId()), String.valueOf(i));
            this.mIsCanceled = false;
            this.mIsInstallState = false;
            String currentInstallWatchFaceHiTopId = getCurrentInstallWatchFaceHiTopId();
            String currentInstallWatchFaceVersion = getCurrentInstallWatchFaceVersion();
            hideDesignerInstallDialog(1);
            if (i == 140004) {
                processInstallFailed(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), -3);
                pauseAllTask();
            } else if (i == 140009) {
                processInstallFailed(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), -6);
                pauseAllTask();
            } else if (i == 141001) {
                processInstallFailed(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), -5);
                p.a(this.mContext).a(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, 1, new InstallWatchFaceBean(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, "", "", "", "", false));
                installWaitInstallWatchFace(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
            } else if (i == 141000) {
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.removeCallbacks(this.mResetInstallState);
                    this.mHandler.post(this.mResetInstallState);
                }
            } else if (i == 20000) {
                processInstallFailed(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), 20000);
                p.a(this.mContext).a(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, 1, new InstallWatchFaceBean(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, "", "", "", "", false));
                installWaitInstallWatchFace(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
            } else {
                processInstallFailed(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion(), -2);
                p.a(this.mContext).a(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, 1, new InstallWatchFaceBean(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, "", "", "", "", false));
                installWaitInstallWatchFace(currentInstallWatchFaceHiTopId, currentInstallWatchFaceVersion, i);
            }
        } catch (Exception e) {
            HwLog.e(TAG, "handleOnUpgradeFailed error =" + HwLog.printException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnFileTransferState(int i) {
        try {
            if (i == 142000) {
                HwLog.i(TAG, "handleOnFileTransferState() ERROR_CODE_WATCH_FACE_TASK_HANG");
                p.a(this.mContext).b(getCurrentInstallWatchFaceHiTopId(), getCurrentInstallWatchFaceVersion());
                return;
            }
            String str = TAG;
            HwLog.i(str, "handleOnFileTransferState percentage:" + i);
            if (i < this.mLastPercentage) {
                Handler handler = this.mHandler;
                if (handler != null) {
                    handler.removeCallbacks(this.mResetInstallState);
                    this.mHandler.postDelayed(this.mResetInstallState, 60000L);
                    return;
                }
                return;
            }
            changeInstallState(3);
            this.mHandler.removeCallbacks(this.mResetInstallState);
            this.mHandler.postDelayed(this.mResetInstallState, 60000L);
            if (Math.abs(System.currentTimeMillis() - this.mLastCurrentInstallTime) >= 1000 && i != this.mLastPercentage) {
                this.mLastCurrentInstallTime = System.currentTimeMillis();
                this.mLastPercentage = i;
                if (getCurrentInstallWatchFaceHiTopId() == null) {
                    HwLog.w(str, "handleOnFileTransferState() getCurrentInstallWatchFaceHiTopId is null.");
                    return;
                }
                String a2 = CommonUtils.a(i, 2, 0);
                OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
                if (operateWatchFaceCallback != null) {
                    operateWatchFaceCallback.callTransmitProgressJsFunction(getCurrentInstallWatchFaceHiTopId(), i, a2);
                }
                OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
                if (operateWatchFaceCallback2 != null) {
                    operateWatchFaceCallback2.callTransmitProgressJsFunction(getCurrentInstallWatchFaceHiTopId(), i, a2);
                }
            }
        } catch (Exception e) {
            HwLog.e(TAG, "handleOnFileTransferState error = " + HwLog.printException(e));
        }
    }

    public void transmitRefreshPullData() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.pOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.transmitRefreshPullData();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.mOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.transmitRefreshPullData();
        } else {
            HwLog.i(TAG, "transmitRefreshPullData -- mOperateCallback is null");
        }
    }

    public WatchResourcesInfo getVipFreeWatchFace(String str) {
        if (this.mVipFreeWatchFaceInfos == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return this.mVipFreeWatchFaceInfos.get(str);
    }

    public void addVipFreeWatchFace(String str, WatchResourcesInfo watchResourcesInfo) {
        HashMap<String, WatchResourcesInfo> hashMap = this.mVipFreeWatchFaceInfos;
        if (hashMap == null || watchResourcesInfo == null) {
            return;
        }
        hashMap.put(str, watchResourcesInfo);
    }

    public boolean isVipFreeWatchFaceContain(String str) {
        if (this.mVipFreeWatchFaceInfos == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mVipFreeWatchFaceInfos.containsKey(str);
    }

    public void removeVipFreeWatchFace(String str) {
        HashMap<String, WatchResourcesInfo> hashMap;
        HwLog.i(TAG, "removeVipFreeWatchFace -- key is " + str);
        if (TextUtils.isEmpty(str) || (hashMap = this.mVipFreeWatchFaceInfos) == null) {
            return;
        }
        hashMap.remove(str);
    }

    public String getCancelHiTopId() {
        return this.cancelHiTopId;
    }

    public void setCancelHiTopId(String str) {
        this.cancelHiTopId = str;
    }

    public void toNotifyWatchfaceModeChange() {
        int b = dp.b("device_connect_mode", -1);
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyWatchfaceModeChange(b);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyWatchfaceModeChange(b);
        }
    }

    public void notifyPhotoPackageName(String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyPhotoPackageName(str);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyPhotoPackageName(str);
        }
    }

    public void notifyWearAlbumPackageName(String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyWearAlbumPackageName(str);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyWearAlbumPackageName(str);
        }
    }

    public void notifyKaleidoscopePackageName(String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyKaleidoscopePackageName(str);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyKaleidoscopePackageName(str);
        }
    }

    public void notifyVideoAlbumPackageName(String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyVideoAlbumPackageName(str);
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyVideoAlbumPackageName(str);
        }
    }

    public void notifyGetWatchfaceStore() {
        OperateWatchFaceCallback operateWatchFaceCallback = this.mOperateCallback;
        if (operateWatchFaceCallback != null) {
            operateWatchFaceCallback.notifyGetWatchfaceStore();
        }
        OperateWatchFaceCallback operateWatchFaceCallback2 = this.pOperateCallback;
        if (operateWatchFaceCallback2 != null) {
            operateWatchFaceCallback2.notifyGetWatchfaceStore();
        }
    }
}

package com.huawei.hwdevice.phoneprocess.mgr.exercise;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.callback.TransferFileCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.trackprocess.api.TrackPostProcessApi;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import defpackage.iyv;
import defpackage.jqj;
import defpackage.jrw;
import defpackage.jsz;
import defpackage.jvl;
import defpackage.jvy;
import defpackage.jyp;
import defpackage.kbf;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class HwDeviceGpsFileWorkoutManager implements ParserInterface {
    private static final int ARRAY_INDEX_FIRST = 0;
    private static final int ARRAY_INDEX_SECOND = 1;
    private static final int ARRAY_INDEX_THIRD = 2;
    private static final int ARRAY_LENGTH = 3;
    private static final int GPS_FILE_TYPE = 17;
    private static final String GPS_SUFFIX = "_gps.bin";
    private static final int GRS_TRANS_FAILURE = 30004;
    private static final Object LOCK = new Object();
    private static final int MAX_GPS_PDR_LENGTH = 20971520;
    private static final int NO_FILE_ERROR = 144001;
    private static final int PDR_FILE_TYPE = 18;
    private static final String PDR_SUFFIX = "_pdr.bin";
    private static final int RECORDID_INDEX = 0;
    private static final String TAG = "HwDeviceGpsFileWorkoutManager";
    private static final String TAG_RELEASE = "BTSYNC_HwDeviceGpsFileWorkoutManager";
    private static final int TIMES_ARRAY_SIZE = 2;
    private static int sCurrentFileType;
    private static HwDeviceGpsFileWorkoutManager sInstance;
    private Context mContext;
    private String mGpsFileName;
    private String mGpsOrPdrFileName;
    private String mPdrFileName;
    private int mRecordId;
    private List<Integer> mRecordIdList;
    private int[] mTimes = new int[2];
    private jvy mGpsFileUtil = new jvy();
    private TransferFileCallback mCallback = null;
    private boolean isSupportAltitude = false;
    private jrw mDetailGpsWorkoutUtil = jrw.a();
    private Map<Integer, Map<Long, double[]>> mAllMap = new HashMap(16);
    private Map<Integer, Integer> mAllMapType = new HashMap(16);
    private Map<Integer, List<Long>> mAllTrackData = new HashMap(16);
    private ITransferSleepAndDFXFileCallback.Stub mGpsFileCallback = new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwDeviceGpsFileWorkoutManager.1
        @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
        public void onProgress(int i, String str) throws RemoteException {
        }

        @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
        public void onSuccess(int i, String str, String str2) throws RemoteException {
            ReleaseLogUtil.e(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "errorCode : ", Integer.valueOf(i));
            if (i == 30000) {
                byte[] fileByte = HwDeviceGpsFileWorkoutManager.this.getFileByte(str);
                HwDeviceGpsFileWorkoutManager.this.mGpsFileUtil.d(fileByte, HwDeviceGpsFileWorkoutManager.this.mGpsOrPdrFileName);
                kbf a2 = HwDeviceGpsFileWorkoutManager.this.mDetailGpsWorkoutUtil.a(fileByte);
                int d = HwDeviceGpsFileWorkoutManager.this.mDetailGpsWorkoutUtil.d(a2);
                LogUtil.a(HwDeviceGpsFileWorkoutManager.TAG, "mapType = ", Integer.valueOf(d), "mRecordId = ", Integer.valueOf(HwDeviceGpsFileWorkoutManager.this.mRecordId));
                HwDeviceGpsFileWorkoutManager.this.mAllMapType.put(Integer.valueOf(HwDeviceGpsFileWorkoutManager.this.mRecordId), Integer.valueOf(d));
                if (!HwDeviceGpsFileWorkoutManager.this.mGpsFileUtil.e()) {
                    HwDeviceGpsFileWorkoutManager.this.dealWithGps(fileByte, d);
                } else {
                    HwDeviceGpsFileWorkoutManager.this.dealWithGpsAndRequestPdr(fileByte, a2, d);
                }
            }
        }

        @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
        public void onFailure(int i, String str) throws RemoteException {
            ReleaseLogUtil.d(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "GPS failure errorCode : ", Integer.valueOf(i));
            if (CommonUtil.as()) {
                sqo.q("GPS failure errorCode : " + i);
            }
            iyv.e(i);
            HwDeviceGpsFileWorkoutManager hwDeviceGpsFileWorkoutManager = HwDeviceGpsFileWorkoutManager.this;
            hwDeviceGpsFileWorkoutManager.gpsLogStartPartHandle(i, hwDeviceGpsFileWorkoutManager.mAllMap, HwDeviceGpsFileWorkoutManager.this.mAllMapType, HwDeviceGpsFileWorkoutManager.this.mAllTrackData);
        }
    };
    private ITransferSleepAndDFXFileCallback.Stub mPdrFileCallback = new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwDeviceGpsFileWorkoutManager.2
        @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
        public void onProgress(int i, String str) throws RemoteException {
        }

        @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
        public void onSuccess(int i, String str, String str2) throws RemoteException {
            ReleaseLogUtil.e(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "errorCode : ", Integer.valueOf(i));
            if (i == 30000) {
                HwDeviceGpsFileWorkoutManager.this.mGpsFileUtil.d(HwDeviceGpsFileWorkoutManager.this.getFileByte(str), HwDeviceGpsFileWorkoutManager.this.mGpsOrPdrFileName);
                HwDeviceGpsFileWorkoutManager hwDeviceGpsFileWorkoutManager = HwDeviceGpsFileWorkoutManager.this;
                hwDeviceGpsFileWorkoutManager.mPdrFileName = hwDeviceGpsFileWorkoutManager.mGpsOrPdrFileName;
                int postProcessingTrackResult = HwDeviceGpsFileWorkoutManager.this.getPostProcessingTrackResult();
                ReleaseLogUtil.e(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "trackProcess result :", Integer.valueOf(postProcessingTrackResult));
                if (postProcessingTrackResult != 0) {
                    ReleaseLogUtil.d(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "result is not 0 return");
                    HwDeviceGpsFileWorkoutManager hwDeviceGpsFileWorkoutManager2 = HwDeviceGpsFileWorkoutManager.this;
                    hwDeviceGpsFileWorkoutManager2.gpsLogStartPartHandle(10001, hwDeviceGpsFileWorkoutManager2.mAllMap, HwDeviceGpsFileWorkoutManager.this.mAllMapType, HwDeviceGpsFileWorkoutManager.this.mAllTrackData);
                } else {
                    ReleaseLogUtil.e(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "GpsHandle success");
                    HwDeviceGpsFileWorkoutManager hwDeviceGpsFileWorkoutManager3 = HwDeviceGpsFileWorkoutManager.this;
                    hwDeviceGpsFileWorkoutManager3.gpsLogStartPartHandle(10000, hwDeviceGpsFileWorkoutManager3.mAllMap, HwDeviceGpsFileWorkoutManager.this.mAllMapType, HwDeviceGpsFileWorkoutManager.this.mAllTrackData);
                }
            }
        }

        @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
        public void onFailure(int i, String str) throws RemoteException {
            iyv.e(i);
            HwDeviceGpsFileWorkoutManager hwDeviceGpsFileWorkoutManager = HwDeviceGpsFileWorkoutManager.this;
            hwDeviceGpsFileWorkoutManager.gpsLogStartPartHandle(AwarenessStatusCodes.AWARENESS_LOCATION_NOCACHE_CODE, hwDeviceGpsFileWorkoutManager.mAllMap, HwDeviceGpsFileWorkoutManager.this.mAllMapType, HwDeviceGpsFileWorkoutManager.this.mAllTrackData);
            ReleaseLogUtil.e(HwDeviceGpsFileWorkoutManager.TAG_RELEASE, "PDR onFailure errorCode:", Integer.valueOf(i));
            sqo.q("PDR onFailure errorCode : " + i);
        }
    };

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
    }

    private HwDeviceGpsFileWorkoutManager(Context context) {
        this.mContext = null;
        this.mContext = context;
        LogUtil.a(TAG, "HwDeviceGpsFileWorkoutManager() context :", context);
    }

    public static HwDeviceGpsFileWorkoutManager getInstance() {
        HwDeviceGpsFileWorkoutManager hwDeviceGpsFileWorkoutManager;
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new HwDeviceGpsFileWorkoutManager(BaseApplication.getContext());
            }
            hwDeviceGpsFileWorkoutManager = sInstance;
        }
        return hwDeviceGpsFileWorkoutManager;
    }

    public void getWorkoutDetailFromDevice(int i, List<Integer> list, TransferFileCallback transferFileCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "getWorkoutDetailFromDevice");
        if (transferFileCallback == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "transferFileCallback is null");
            sqo.q("transferFileCallback is null");
            return;
        }
        this.mCallback = transferFileCallback;
        if (list == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "getWorkoutDetailFromDevice workoutRecordIds is null");
            sqo.q("getWorkoutDetailFromDevice workoutRecordIds is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        TransferFileInfo transferFileInfo = new TransferFileInfo();
        transferFileInfo.setType(1);
        transferFileInfo.setGpsType(i);
        transferFileInfo.setPriority(3);
        transferFileInfo.setSuspend(0);
        transferFileInfo.setTaskType(0);
        transferFileInfo.setRecordId(arrayList);
        DeviceCapability capability = getCapability();
        if (capability != null) {
            ReleaseLogUtil.e(TAG_RELEASE, "deviceCapability.isSupportUploadGpsAndPdrFile():", Boolean.valueOf(capability.isSupportUploadGpsAndPdrFile()));
            if (!capability.isSupportUploadGpsAndPdrFile()) {
                ReleaseLogUtil.e(TAG_RELEASE, "goto old sync file aisle");
                jvl.b().b(transferFileInfo, this.mCallback);
                return;
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "goto new sync file aisle");
                goToCommonFileTransfer(transferFileInfo);
                return;
            }
        }
        ReleaseLogUtil.d(TAG_RELEASE, "deviceCapability is null");
        sqo.q("deviceCapability is null");
        this.mCallback.onResponse(10002, "deviceCapability is null");
    }

    private DeviceCapability getCapability() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", TAG);
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        return deviceCapability;
    }

    private void goToCommonFileTransfer(TransferFileInfo transferFileInfo) {
        ReleaseLogUtil.e(TAG_RELEASE, "transferFileInfo:", transferFileInfo.getRecordId());
        this.mRecordIdList = transferFileInfo.getRecordId();
        this.mTimes[0] = transferFileInfo.getStartTime();
        this.mTimes[1] = transferFileInfo.getEndTime();
        sCurrentFileType = 17;
        startRequest(17);
    }

    private void startRequest(int i) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter goToCommonFileTransfer fileType:", Integer.valueOf(i));
        List<Integer> list = this.mRecordIdList;
        if (list != null && !list.isEmpty()) {
            this.mRecordId = this.mRecordIdList.get(0).intValue();
            jqj jqjVar = new jqj();
            jqjVar.d(i);
            jqjVar.a(false);
            jqjVar.e(this.mTimes);
            jqjVar.c((String) null);
            if (i == 17) {
                this.mGpsOrPdrFileName = this.mRecordId + GPS_SUFFIX;
                this.mAllMap.clear();
                this.mAllMapType.clear();
                this.mAllTrackData.clear();
                jqjVar.a(this.mGpsOrPdrFileName);
                jyp.c().b(jqjVar, this.mGpsFileCallback);
                return;
            }
            String str = this.mRecordId + PDR_SUFFIX;
            this.mGpsOrPdrFileName = str;
            jqjVar.a(str);
            jyp.c().b(jqjVar, this.mPdrFileCallback);
            return;
        }
        ReleaseLogUtil.d(TAG_RELEASE, "recordId is empty");
        sqo.q("recordId is empty");
        this.mCallback.onResponse(10002, "recordId is empty");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPostProcessingTrackResult() {
        return ((TrackPostProcessApi) Services.c("Module_Track_Post_Process_Service", TrackPostProcessApi.class)).getPostProcessingTrack(this.mGpsFileName, this.mPdrFileName, this.isSupportAltitude, new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.HwDeviceGpsFileWorkoutManager$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                HwDeviceGpsFileWorkoutManager.this.m663xfc916a93(i, obj);
            }
        });
    }

    /* renamed from: lambda$getPostProcessingTrackResult$0$com-huawei-hwdevice-phoneprocess-mgr-exercise-HwDeviceGpsFileWorkoutManager, reason: not valid java name */
    /* synthetic */ void m663xfc916a93(int i, Object obj) {
        if (obj instanceof Map) {
            this.mAllMap.put(Integer.valueOf(this.mRecordId), (Map) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gpsLogStartPartHandle(int i, Map<Integer, Map<Long, double[]>> map, Map<Integer, Integer> map2, Map<Integer, List<Long>> map3) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        HashMap hashMap3 = new HashMap(16);
        if (this.mCallback != null && map != null && map2 != null) {
            for (Map.Entry<Integer, Map<Long, double[]>> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
                hashMap2.put(entry2.getKey(), entry2.getValue());
            }
            if (map3 != null) {
                for (Map.Entry<Integer, List<Long>> entry3 : map3.entrySet()) {
                    hashMap3.put(entry3.getKey(), entry3.getValue());
                }
            } else {
                hashMap3.put(Integer.valueOf(this.mRecordId), null);
            }
            if (i != 10000) {
                ReleaseLogUtil.d(TAG_RELEASE, "no gps data");
                hashMap.put(Integer.valueOf(this.mRecordId), null);
                hashMap2.put(Integer.valueOf(this.mRecordId), null);
                hashMap3.put(Integer.valueOf(this.mRecordId), null);
            }
            Object[] objArr = {hashMap, hashMap2, hashMap3};
            LogUtil.a(TAG, "go to callback", objArr);
            if (i == 30004) {
                this.mCallback.onResponse(10001, objArr);
                return;
            } else {
                this.mCallback.onResponse(10000, objArr);
                return;
            }
        }
        ReleaseLogUtil.d(TAG_RELEASE, "GpsHandle callback is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.Closeable, java.io.FileInputStream] */
    public byte[] getFileByte(String str) {
        byte[] bArr;
        ?? r5;
        File file;
        long length;
        byte[] bArr2 = null;
        Closeable closeable = null;
        try {
            try {
                file = FileUtils.getFile(str);
                r5 = FileUtils.openInputStream(file);
            } catch (Throwable th) {
                th = th;
                r5 = bArr2;
            }
        } catch (IOException e) {
            e = e;
            bArr = null;
        }
        try {
            length = file.length();
        } catch (IOException e2) {
            e = e2;
            bArr = null;
            closeable = r5;
            ReleaseLogUtil.c(TAG_RELEASE, "getFileByte : IOException,", ExceptionUtils.d(e));
            sqo.q("getFileByte : IOException," + ExceptionUtils.d(e));
            IoUtils.e(closeable);
            bArr2 = bArr;
            return bArr2;
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e((Closeable) r5);
            throw th;
        }
        if (length > 20971520) {
            ReleaseLogUtil.d(TAG_RELEASE, "wrong file. file.length is too large.");
            IoUtils.e((Closeable) r5);
            return null;
        }
        bArr2 = new byte[(int) length];
        if (r5.read(bArr2) == length) {
            ReleaseLogUtil.e(TAG_RELEASE, "read inputStream success.");
        }
        IoUtils.e((Closeable) r5);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealWithGps(byte[] bArr, int i) {
        ReleaseLogUtil.e(TAG_RELEASE, "unSupportTrackPostProcessing");
        this.mAllMap.put(Integer.valueOf(this.mRecordId), this.mDetailGpsWorkoutUtil.c(bArr, i));
        gpsLogStartPartHandle(10000, this.mAllMap, this.mAllMapType, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealWithGpsAndRequestPdr(byte[] bArr, kbf kbfVar, int i) {
        this.isSupportAltitude = this.mDetailGpsWorkoutUtil.a(kbfVar);
        ReleaseLogUtil.e(TAG_RELEASE, "mapType :", Integer.valueOf(i), " ,isSupportAltitude :", Boolean.valueOf(this.isSupportAltitude));
        sCurrentFileType = 18;
        this.mGpsFileName = this.mGpsOrPdrFileName;
        this.mAllTrackData.put(Integer.valueOf(this.mRecordId), this.mGpsFileUtil.b(bArr));
        startRequest(sCurrentFileType);
    }
}

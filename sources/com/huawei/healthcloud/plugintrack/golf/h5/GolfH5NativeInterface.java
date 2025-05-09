package com.huawei.healthcloud.plugintrack.golf.h5;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.healthcloud.plugintrack.golf.Utils;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseData;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfHistoryDetail;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfHistoryGraph;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfLocationData;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.hwsportmodel.TrackGolfCourseSegment;
import defpackage.cwa;
import defpackage.gwk;
import defpackage.hjd;
import defpackage.jdi;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@H5ProService(name = "GolfH5NativeInterface", users = {"", "", "9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class GolfH5NativeInterface {
    private static final int BACKGROUND_LOCATION_PERMISSION = 2;
    private static final int DEFAULT_FAILURE_CODE = 1;
    public static final int DEFAULT_SUCCESS_CODE = 0;
    public static final int DEVICE_NOT_SUPPORT_GOLF = 2;
    public static final int DEVICE_SUPPORT_V2_SUCCESS_CODE = 3;
    public static final int DEVICE_SUPPORT_V3_SUCCESS_CODE = 4;
    private static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12;
    private static final int NO_LOCATION_PERMISSION = 3;
    private static final int RUNNING_LOCATION_PERMISSION = 1;
    private static final String TAG = "Track_GolfH5LogicManager";

    @H5ProMethod(name = "getGolfHistoryDetail")
    public static void getGolfHistoryDetail(long j, long j2, final GolfH5Callback<GolfHistoryDetail> golfH5Callback) {
        LogUtil.a(TAG, "call getGolfHistoryDetail success");
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h(TAG, "getGolfHistoryDetail healthDataMgrApi is null.");
            golfH5Callback.onFailure(1, "getGolfHistoryDetail healthDataMgrApi is null");
        } else {
            healthDataMgrApi.requestTrackDetailData(j, j2, new IBaseResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5NativeInterface.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "HistoryDetail requestTrackDetailData not success");
                        GolfH5Callback.this.onFailure(1, "HistoryDetail requestTrackDetailData not success");
                    }
                    if (obj == null) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "HistoryDetail requestTrackDetailData objData null");
                        GolfH5Callback.this.onFailure(1, "HistoryDetail requestTrackDetailData objData is null");
                    }
                    if (!koq.e(obj, HiHealthData.class)) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "HistoryDetail objData not instanceof List");
                        GolfH5Callback.this.onFailure(1, "HistoryDetail requestTrackDetailData objData not instanceof List");
                    }
                    GolfH5NativeInterface.parsingDetailData(i, obj, GolfH5Callback.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parsingDetailData(int i, Object obj, GolfH5Callback<GolfHistoryDetail> golfH5Callback) {
        if (obj == null || golfH5Callback == null) {
            LogUtil.h(TAG, "parsingDetailData: objData or callback is null.");
            golfH5Callback.onFailure(1, "objData or callback is null");
            return;
        }
        GolfHistoryDetail golfHistoryDetail = new GolfHistoryDetail();
        List list = (List) obj;
        if (list.isEmpty() || list.get(0) == null) {
            LogUtil.h(TAG, "parsingDetailData list size 0.");
            golfH5Callback.onFailure(1, "parsingDetailData list size 0");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        try {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            int i2 = hiHealthData.getInt("trackdata_deviceType");
            String a2 = cwa.a(i2, BaseApplication.getContext(), BaseApplication.getContext().getPackageName(), hiHealthData.getString("device_prodid"));
            golfHistoryDetail.setDeviceName(a2);
            LogUtil.a(TAG, "parsingDetailData: deviceType=" + i2, "deviceName=" + a2);
            golfHistoryDetail.setWorkoutStartTime(hiHealthData.getStartTime());
            golfHistoryDetail.setWorkoutEndTime(hiHealthData.getEndTime());
            setTrackDetailData(golfHistoryDetail, hiTrackMetaData);
            String sequenceFileUrl = hiHealthData.getSequenceFileUrl();
            if (TextUtils.isEmpty(sequenceFileUrl)) {
                LogUtil.h(TAG, "parsingDetailData fileUrl is null");
                golfH5Callback.onFailure(1, "parsingDetailData fileUrl is null");
                return;
            }
            MotionPath c = gwk.c(BaseApplication.getActivity(), sequenceFileUrl, 286);
            if (c == null) {
                LogUtil.h(TAG, "parsingDetailData motionPath is null");
                golfH5Callback.onFailure(1, "parsingDetailData motionPath is null");
                return;
            }
            List<CommonSegment> requestSegmentList = c.requestSegmentList();
            ArrayList arrayList = new ArrayList();
            Iterator<CommonSegment> it = requestSegmentList.iterator();
            while (it.hasNext()) {
                arrayList.add((TrackGolfCourseSegment) it.next());
            }
            golfHistoryDetail.setRecordCard(arrayList);
            golfH5Callback.onSuccess(golfHistoryDetail);
            LogUtil.a(TAG, "parsingDetailData: " + golfHistoryDetail.toString());
        } catch (JsonSyntaxException unused) {
            LogUtil.h(TAG, "analyzeTrackDetailData trackMetaData is error");
            golfH5Callback.onFailure(1, "analyze trackMetaData error");
        }
    }

    private static void setTrackDetailData(GolfHistoryDetail golfHistoryDetail, HiTrackMetaData hiTrackMetaData) {
        golfHistoryDetail.setWorkoutType(hiTrackMetaData.getSportType());
        golfHistoryDetail.setWorkoutRecordId(hiTrackMetaData.getSportId());
        golfHistoryDetail.setWorkoutCalorie(hiTrackMetaData.getTotalCalories());
        golfHistoryDetail.setWorkoutStep(hiTrackMetaData.getTotalSteps());
        golfHistoryDetail.setWorkoutTotalTime(hiTrackMetaData.getTotalTime());
        golfHistoryDetail.setDuplicated(hiTrackMetaData.getDuplicated());
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        golfHistoryDetail.setGolfSwingCount(extendTrackMap.get("golfSwingCount"));
        golfHistoryDetail.setCourseId(extendTrackMap.get("courseId"));
        golfHistoryDetail.setBranchId1(extendTrackMap.get("branchId1"));
        golfHistoryDetail.setBranchId2(extendTrackMap.get("branchId2"));
        golfHistoryDetail.setEagle(extendTrackMap.get("eagle"));
        golfHistoryDetail.setBirdie(extendTrackMap.get("birdie"));
        golfHistoryDetail.setPar(extendTrackMap.get("par"));
        golfHistoryDetail.setBogey(extendTrackMap.get("bogey"));
        golfHistoryDetail.setBogey2(extendTrackMap.get("bogey2"));
        golfHistoryDetail.setPutts(extendTrackMap.get("putts"));
        golfHistoryDetail.setAvgPutts(extendTrackMap.get("avgPutts"));
        golfHistoryDetail.setGir(extendTrackMap.get("gir"));
        golfHistoryDetail.setAvgPar3(extendTrackMap.get("avgPar3"));
        golfHistoryDetail.setAvgPar4(extendTrackMap.get("avgPar4"));
        golfHistoryDetail.setAvgPar5(extendTrackMap.get("avgPar5"));
        golfHistoryDetail.setFairwayLeft(extendTrackMap.get("fairwayLeft"));
        golfHistoryDetail.setFairwayHits(extendTrackMap.get("fairwayHits"));
        golfHistoryDetail.setFairwayRight(extendTrackMap.get("fairwayRight"));
        golfHistoryDetail.setAvgHandicap(extendTrackMap.get("avgHandicap"));
        golfHistoryDetail.setBestHandicap(extendTrackMap.get("bestHandicap"));
        golfHistoryDetail.setTotalHandicap(extendTrackMap.get("totalHandicap"));
        golfHistoryDetail.setTotalHoles(extendTrackMap.get("totalHoles"));
        golfHistoryDetail.setDoubleEagle(extendTrackMap.get("doubleEagle"));
        golfHistoryDetail.setActiveCalorie(extendTrackMap.get(HwExerciseConstants.JSON_NAME_ACTIVECALORIE));
    }

    @H5ProMethod(name = "getGolfHistoryGraph")
    public static void getGolfHistoryGraph(long j, long j2, final GolfH5Callback<GolfHistoryGraph> golfH5Callback) {
        LogUtil.a(TAG, "call getGolfHistoryGraph success");
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h(TAG, "getGolfHistoryGraph healthDataMgrApi is null.");
            golfH5Callback.onFailure(1, "getGolfHistoryGraph healthDataMgrApi is null");
        } else {
            healthDataMgrApi.requestTrackDetailData(j, j2, new IBaseResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5NativeInterface.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "HistoryGraph requestTrackDetailData not success");
                        GolfH5Callback.this.onFailure(1, "HistoryGraph requestTrackDetailData not success");
                    }
                    if (obj == null) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "HistoryGraph requestTrackDetailData objData null");
                        GolfH5Callback.this.onFailure(1, "HistoryGraph objData is null");
                    }
                    if (!koq.e(obj, HiHealthData.class)) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "HistoryGraph objData not instanceof List");
                        GolfH5Callback.this.onFailure(1, "objData not instanceof List");
                    }
                    GolfH5NativeInterface.parsingGraphData(i, obj, GolfH5Callback.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parsingGraphData(int i, Object obj, GolfH5Callback<GolfHistoryGraph> golfH5Callback) {
        if (obj == null || golfH5Callback == null) {
            LogUtil.h(TAG, "parsingGraphData: objData or callback is null.");
            golfH5Callback.onFailure(1, "objData or callback is null");
            return;
        }
        List list = (List) obj;
        if (list.isEmpty() || list.get(0) == null) {
            LogUtil.h(TAG, "parsingGraphData list size 0.");
            golfH5Callback.onFailure(1, "parsingGraphData list size 0");
            return;
        }
        GolfHistoryGraph golfHistoryGraph = new GolfHistoryGraph();
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        String sequenceFileUrl = hiHealthData.getSequenceFileUrl();
        if (TextUtils.isEmpty(sequenceFileUrl)) {
            LogUtil.h(TAG, "parsingGraphData fileUrl is null");
            golfH5Callback.onFailure(1, "parsingGraphData fileUrl is null");
            return;
        }
        setGraphExtendData(golfHistoryGraph, hiHealthData, golfH5Callback);
        MotionPath c = gwk.c(BaseApplication.getActivity(), sequenceFileUrl, 286);
        if (c == null) {
            LogUtil.h(TAG, "parsingGraphData motionPath is null");
            golfH5Callback.onFailure(i, "parsingGraphData motionPath is null");
            return;
        }
        List<CommonSegment> requestSegmentList = c.requestSegmentList();
        ArrayList arrayList = new ArrayList();
        Iterator<CommonSegment> it = requestSegmentList.iterator();
        while (it.hasNext()) {
            arrayList.add((TrackGolfCourseSegment) it.next());
        }
        golfHistoryGraph.setRecordCard(arrayList);
        ArrayList<HeartRateData> requestHeartRateList = c.requestHeartRateList();
        ArrayList<HeartRateData> requestInvalidHeartRateList = c.requestInvalidHeartRateList();
        golfHistoryGraph.setHeartRateList(requestHeartRateList);
        golfHistoryGraph.setInvalidHeartRateList(requestInvalidHeartRateList);
        golfH5Callback.onSuccess(golfHistoryGraph);
        LogUtil.a(TAG, "parsingGraphData: " + golfHistoryGraph);
    }

    private static void setGraphExtendData(GolfHistoryGraph golfHistoryGraph, HiHealthData hiHealthData, GolfH5Callback<GolfHistoryGraph> golfH5Callback) {
        if (hiHealthData == null) {
            LogUtil.h(TAG, "setGraphExtendData: hiHealthData is null");
            return;
        }
        try {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData == null) {
                LogUtil.h(TAG, "setGraphExtendData: trackMetaData is null");
                return;
            }
            String str = hiTrackMetaData.getExtendTrackMap().get("avgHandicap");
            String str2 = hiTrackMetaData.getExtendTrackMap().get("bestHandicap");
            golfHistoryGraph.setAvgHandicap(str);
            golfHistoryGraph.setBestHandicap(str2);
        } catch (JsonSyntaxException unused) {
            LogUtil.h(TAG, "parsingGraphData analyze trackMetaData is error");
            golfH5Callback.onFailure(1, "parsingGraphData analyze trackMetaData error");
        }
    }

    @H5ProMethod(name = "getGolfHistoryScoreCard")
    public static void getGolfHistoryScoreCard(long j, long j2, final GolfH5Callback<List<TrackGolfCourseSegment>> golfH5Callback) {
        LogUtil.a(TAG, "call getGolfHistoryScoreCard success");
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h(TAG, "getGolfHistoryDetail : healthDataMgrApi is null.");
            golfH5Callback.onFailure(1, "getGolfHistoryGraph healthDataMgrApi is null");
        } else {
            healthDataMgrApi.requestTrackDetailData(j, j2, new IBaseResponseCallback() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5NativeInterface.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "scoreCard requestTrackDetailData not success, errorCode=", Integer.valueOf(i));
                        GolfH5Callback.this.onFailure(1, "scoreCard requestTrackDetailData not success");
                    }
                    if (obj == null) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "scoreCard requestTrackDetailData objData null");
                        GolfH5Callback.this.onFailure(1, "scoreCard requestTrackDetailData objData null");
                    }
                    if (!koq.e(obj, HiHealthData.class)) {
                        LogUtil.h(GolfH5NativeInterface.TAG, "scoreCard requestTrackDetailData objData not instanceof List");
                        GolfH5Callback.this.onFailure(1, "scoreCard requestTrackDetailData objData not instanceof List");
                    }
                    GolfH5NativeInterface.parsingRecordCardData(i, obj, GolfH5Callback.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parsingRecordCardData(int i, Object obj, GolfH5Callback<List<TrackGolfCourseSegment>> golfH5Callback) {
        if (obj == null || golfH5Callback == null) {
            LogUtil.h(TAG, "parsingRecordCardData: objData or callback is null.");
            golfH5Callback.onFailure(1, "objData or callback is null");
            return;
        }
        List list = (List) obj;
        if (list.isEmpty() || list.get(0) == null) {
            LogUtil.h(TAG, "parsingRecordCardData list size 0.");
            golfH5Callback.onFailure(1, "parsingRecordCardData list size 0");
            return;
        }
        String sequenceFileUrl = ((HiHealthData) list.get(0)).getSequenceFileUrl();
        if (TextUtils.isEmpty(sequenceFileUrl)) {
            LogUtil.h(TAG, "parsingRecordCardData fileUrl is null");
            golfH5Callback.onFailure(1, "parsingRecordCardData fileUrl is null");
            return;
        }
        MotionPath c = gwk.c(BaseApplication.getActivity(), sequenceFileUrl, 286);
        if (c == null) {
            LogUtil.h(TAG, "parsingRecordCardData motionPath is null");
            golfH5Callback.onFailure(1, "parsingRecordCardData motionPath is null");
            return;
        }
        List<CommonSegment> requestSegmentList = c.requestSegmentList();
        ArrayList arrayList = new ArrayList();
        Iterator<CommonSegment> it = requestSegmentList.iterator();
        while (it.hasNext()) {
            arrayList.add((TrackGolfCourseSegment) it.next());
        }
        golfH5Callback.onSuccess(arrayList);
        LogUtil.a(TAG, "parsingRecordCardData: " + arrayList);
    }

    @H5ProMethod(name = "checkDeviceConnectedInfo")
    public static void checkDeviceConnectedInfo(GolfH5Callback<String> golfH5Callback) {
        LogUtil.c(TAG, "checkDeviceConnectedInfo");
        if (GolfDeviceProxy.getInstance().isDeviceConnected()) {
            golfH5Callback.onSuccess(Integer.toString(Utils.getGolfResultCode()));
        } else {
            golfH5Callback.onSuccess(Integer.toString(1));
        }
        LogUtil.c(TAG, "call checkDeviceConnectedInfo success");
    }

    @H5ProMethod(name = "setLocationAuth")
    public static void setLocationAuth(int i, final GolfH5Callback<String> golfH5Callback) {
        ReleaseLogUtil.e(TAG, "setLocationAuth mode is ", Integer.valueOf(i));
        if (i > 2) {
            return;
        }
        PermissionUtil.b(BaseApplication.getActivity(), PermissionUtil.PermissionType.LOCATION, new PermissionsResultAction() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5NativeInterface.4
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a(GolfH5NativeInterface.TAG, "Permission is onGranted");
                GolfH5Callback.this.onSuccess(String.valueOf(1));
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a(GolfH5NativeInterface.TAG, "Permission is onDenied");
                GolfH5Callback.this.onSuccess(String.valueOf(3));
            }
        });
    }

    @H5ProMethod(name = "getLocationAuth")
    public static void getLocationAuth(GolfH5Callback golfH5Callback) {
        if (jdi.c(BaseApplication.getContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})) {
            if (29 <= Build.VERSION.SDK_INT && jdi.c(BaseApplication.getActivity(), new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"})) {
                golfH5Callback.onSuccess(2);
                return;
            } else {
                golfH5Callback.onSuccess(1);
                return;
            }
        }
        golfH5Callback.onSuccess(3);
    }

    @H5ProMethod(name = "getLocation")
    public static void getLocation(final GolfH5Callback<GolfLocationData> golfH5Callback) {
        LogUtil.c(TAG, "call getLocation success");
        if (golfH5Callback == null) {
            ReleaseLogUtil.c(TAG, "callback is null in getLocation");
            return;
        }
        if (!jdi.c(BaseApplication.getContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})) {
            golfH5Callback.onFailure(12, "no location permissions");
            ReleaseLogUtil.d(TAG, "no location permissions in getLocation");
        } else {
            GolfGetLocation.getInstance().getLocation(new GolfGetLocation.GetLocationCallback() { // from class: com.huawei.healthcloud.plugintrack.golf.h5.GolfH5NativeInterface.5
                @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
                public void onSuccess(hjd hjdVar) {
                    GolfLocationData golfLocationData = new GolfLocationData();
                    if (hjdVar != null) {
                        golfLocationData.setLatitude(hjdVar.b);
                        golfLocationData.setLongitude(hjdVar.d);
                    }
                    GolfH5Callback.this.onSuccess(golfLocationData);
                    LogUtil.c(GolfH5NativeInterface.TAG, golfLocationData);
                }

                @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
                public void onFailure(int i, String str) {
                    GolfH5Callback.this.onFailure(i, str);
                }
            });
        }
    }

    @H5ProMethod(name = "getCourseData")
    public static void getCourseData(int i, GolfH5Callback<GolfCourseData> golfH5Callback) {
        golfH5Callback.onSuccess(new GolfCourseData());
        LogUtil.c(TAG, "call getCourseData success");
    }

    @H5ProMethod(name = "getBaseUrl")
    public static void getBaseUrl(GolfH5Callback<String> golfH5Callback) {
        if (golfH5Callback == null) {
            LogUtil.b(TAG, "callback is null");
        } else {
            golfH5Callback.onSuccess("url");
            LogUtil.c(TAG, "call getBaseUrl success");
        }
    }

    @H5ProMethod(name = "getWatchType")
    public static void getWatchType(GolfH5Callback<String> golfH5Callback) {
        if (golfH5Callback == null) {
            LogUtil.b(TAG, "call back is null");
            return;
        }
        LogUtil.c(TAG, "call getWatchType success");
        if (!GolfDeviceProxy.getInstance().isDeviceConnected()) {
            golfH5Callback.onFailure(1, "no device connected");
        } else if (!GolfDeviceProxy.getInstance().isSupportGolf()) {
            golfH5Callback.onFailure(1, "device not support golf");
        } else {
            golfH5Callback.onSuccess(Integer.toString(GolfDeviceProxy.getInstance().getWatchType()));
        }
    }
}

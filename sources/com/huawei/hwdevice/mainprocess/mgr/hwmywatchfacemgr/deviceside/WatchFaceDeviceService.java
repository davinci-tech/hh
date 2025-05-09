package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside;

import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.CompatibleDialStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceInfoResponse;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceList;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceOperateInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceParamsResult;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util.WatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bmk;
import defpackage.bmt;
import defpackage.cvx;
import defpackage.cwd;
import defpackage.cwe;
import defpackage.cwg;
import defpackage.cwl;
import defpackage.jfq;
import defpackage.jfs;
import defpackage.koq;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class WatchFaceDeviceService implements BluetoothDataReceiveCallback {
    private static final int APPLY_OPERATE_TYPE = 3;
    private static final int APPLY_SYNC_TYPE = 7;
    private static final int APPLY_WATCH_FACE_ID = 1;
    private static final int APPLY_WATCH_FACE_VERSION = 2;
    private static final int COMMAND_ID_DEVICE_WATCH_FACE_INFO = 1;
    private static final int COMMAND_ID_WATCH_FACE_APPLY = 3;
    private static final int COMMAND_ID_WATCH_FACE_INFO = 2;
    private static final int COMMAND_ID_WATCH_FACE_INFO_REPORT = 5;
    private static final int COMMAND_ID_WATCH_FACE_REQUEST_NAME = 6;
    private static final int COMMAND_ID_WATCH_FACE_SORT = 4;
    private static final int COMMAND_TL_NUM = 128;
    private static final int CONVERT_RADIX_HEX = 16;
    private static final int CURRENT_MODE_0x18 = 24;
    private static final int DEFAULT_TAG_LENGTH = 1;
    private static final int DIAL_HEIGHT = 10;
    private static final int DIAL_WIDTH = 9;
    private static final int ERROR_CODE = 127;
    private static final int ERROR_CODE_LENGTH = 4;
    private static final int ERROR_CODE_SUCCESS = 100000;
    private static final int GET_NAME_LANGUAGE_MODE = 1;
    private static final int GET_NAME_WATCH_FACE_BRIEF = 6;
    private static final int GET_NAME_WATCH_FACE_ID = 4;
    private static final int GET_NAME_WATCH_FACE_LIST = 2;
    private static final int GET_NAME_WATCH_FACE_NAME = 5;
    private static final int GET_NAME_WATCH_FACE_SIZE = 7;
    private static final int GET_NAME_WATCH_FACE_STRUCT = 3;
    private static final int INT_BIT_FIRST = 2;
    private static final int IS_WATCHFACE_FULL = 8;
    private static final int LIST_MAX_LENGTH = 20;
    private static final int MAX_FIRMWARE = 1;
    private static final int NEED_RECEIVE = 4;
    private static final int OTHER_FIRMWARE = 6;
    private static final int REMAINING_STORAGE_SPACE = 9;
    private static final int REPORT_WATCH_FACE_ID = 1;
    private static final int REPORT_WATCH_FACE_STATUS = 3;
    private static final int REPORT_WATCH_FACE_VERSION = 2;
    private static final int SCREEN_HEIGHT = 3;
    private static final int SCREEN_WIDTH = 2;
    private static final int SERVER_MODE = 6;
    private static final int SERVER_MODE_CHINA_RELEASE = 1;
    private static final int SERVER_MODE_CHINA_TEST = 2;
    private static final int SERVER_MODE_OVERSEA_RELEASE = 3;
    private static final int SUPPORT_FILE_TYPE = 4;
    private static final int SUPPORT_GAUSSIAN_COLOR_0x1A = 26;
    private static final int SUPPORT_ONE_TOUCH = 25;
    private static final int SUPPORT_SORT = 5;
    private static final int SUPPORT_SYNC_0x17 = 23;
    private static final int SUPPORT_VERSION = 11;
    private static final String TAG = "WatchFaceDeviceService";
    private static final String TAG_RELEASE = "R_WatchFaceDeviceService";
    private static final int TLV_HEAD = 4;
    private static final int WATCH_FACE_EXPAND_TYPE = 7;
    private static final int WATCH_FACE_ID = 3;
    private static final int WATCH_FACE_LIST = 1;
    private static final int WATCH_FACE_TYPE = 5;
    private static final int WATCH_FACE_VERSION = 4;
    private static Map<Integer, List<IBaseResponseCallback>> sCommandCallbacks = new HashMap(20);
    private jfq mDeviceConfigManager;
    private int mDeviceHeight;
    private int mDeviceWidth;
    private final cwl mTlvUtils;
    private Map<String, ResponseCallback<WatchFaceIdReportInfo>> sWatchFaceCallbackList;

    private WatchFaceDeviceService() {
        this.sWatchFaceCallbackList = new HashMap(16);
        this.mTlvUtils = new cwl();
        this.mDeviceConfigManager = jfq.c();
        jfs.d().b(TAG, this);
    }

    static class SingletonHolder {
        private static final WatchFaceDeviceService INSTANCE = new WatchFaceDeviceService();

        private SingletonHolder() {
        }
    }

    public static WatchFaceDeviceService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null) {
            ReleaseLogUtil.e(TAG_RELEASE, "data is null");
            return;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        if (b != 39) {
            return;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "onDataReceived errorCode is, ", Integer.valueOf(i), Integer.valueOf(b), " commandId is,", Integer.valueOf(b2));
        getResult(bArr);
    }

    public void queryWatchFaceParams(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter queryWatchFaceParams");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(1);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter queryWatchFaceParams have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(1, arrayList);
                } else {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter queryWatchFaceParams have callback,add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        getDeviceInfo();
    }

    public void queryWatchFaceInfoList(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter queryWatchFaceInfoList");
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(2);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter queryWatchFaceInfoList have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(2, arrayList);
                } else {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter queryWatchFaceInfoList have callback,add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        getDeviceWatchInfo();
    }

    public void obtainWatchFaceInfo(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter obtainWatchFaceInfo");
        int i = LanguageUtil.h(BaseApplication.getContext()) ? 2 : 1;
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list2 = sCommandCallbacks.get(6);
                if (list2 == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter obtainWatchFaceInfo have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(6, arrayList);
                } else {
                    LogUtil.a(TAG, "enter obtainWatchFaceInfo have callback,add");
                    list2.add(iBaseResponseCallback);
                }
            }
        }
        sendGetNameCommand(list, i);
    }

    private void getResult(byte[] bArr) {
        LogUtil.a(TAG, "getResult():", cvx.d(bArr));
        if (bArr == null || bArr.length <= 1) {
            ReleaseLogUtil.d(TAG_RELEASE, "data illegal");
            return;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "getResult() id commandData 1:", Byte.valueOf(bArr[1]));
        switch (bArr[1]) {
            case 1:
                handleDeviceInfo(bArr);
                break;
            case 2:
                handleFaceInfo(bArr);
                break;
            case 3:
                handleApply(bArr);
                break;
            case 4:
                break;
            case 5:
                handleReportStatus(bArr);
                break;
            case 6:
                handleNameInfo(bArr);
                break;
            default:
                LogUtil.h(TAG, "getResult nothing to do");
                break;
        }
    }

    private void handleNameInfo(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a(TAG, "5.39.6 handleNameInfo:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleNameInfo data is error");
            return;
        }
        try {
            cwe cweVar = this.mTlvUtils.a(d.substring(4)).a().get(0);
            LogUtil.a(TAG, "handleNameInfo size:", Integer.valueOf(cweVar.a().size()));
            WatchFaceInfoResponse watchFaceInfoResponse = new WatchFaceInfoResponse();
            watchFaceInfoResponse.setWatchFaceList(new ArrayList(16));
            Iterator<cwe> it = cweVar.a().iterator();
            while (it.hasNext()) {
                handleOneTlv(watchFaceInfoResponse, it.next());
            }
            LogUtil.a(TAG, "handleNameInfo End, watchFaceList size:", Integer.valueOf(watchFaceInfoResponse.getWatchFaceList().size()));
            reportWatchFaceNameInfo(watchFaceInfoResponse);
        } catch (cwg e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleNameInfo TlvException:", ExceptionUtils.d(e));
            sqo.an("handleNameInfo request watch info TlvException: " + ExceptionUtils.d(e));
        } catch (IndexOutOfBoundsException e2) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleNameInfo IndexOutOfBoundsException:", ExceptionUtils.d(e2));
            sqo.an("handleNameInfo request watch info IndexOutOfBoundsException: " + ExceptionUtils.d(e2));
        } catch (NumberFormatException e3) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleNameInfo NumberFormatException:", ExceptionUtils.d(e3));
            sqo.an("handleNameInfo request watch info NumberFormatException: " + ExceptionUtils.d(e3));
        }
    }

    private void handleOneTlv(WatchFaceInfoResponse watchFaceInfoResponse, cwe cweVar) {
        WatchFaceInfoResponse.WatchFaceInfoStruct watchFaceInfoStruct = new WatchFaceInfoResponse.WatchFaceInfoStruct();
        for (cwd cwdVar : cweVar.e()) {
            int a2 = CommonUtil.a(cwdVar.e(), 16);
            if (a2 == 4) {
                watchFaceInfoStruct.setWatchFaceId(WatchFaceUtil.hexToIntList(cwdVar.c()));
            } else if (a2 == 5) {
                watchFaceInfoStruct.setWatchFaceName(WatchFaceUtil.hexToIntList(cwdVar.c()));
            } else if (a2 == 6) {
                watchFaceInfoStruct.setWatchFaceBrief(WatchFaceUtil.hexToIntList(cwdVar.c()));
            } else if (a2 == 7) {
                watchFaceInfoStruct.setWatchFaceSize(CommonUtil.a(cwdVar.c(), 16));
            } else {
                LogUtil.h(TAG, "handleOneTlv default:", Integer.valueOf(CommonUtil.a(cwdVar.e(), 16)));
            }
        }
        watchFaceInfoResponse.getWatchFaceList().add(watchFaceInfoStruct);
    }

    private void handleReportStatus(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a(TAG, "5.39.5 handleReportStatus, info:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleReportStatus data is error");
            return;
        }
        WatchFaceIdReportInfo watchFaceIdReportInfo = new WatchFaceIdReportInfo();
        int i = 100000;
        try {
            List<cwd> e = this.mTlvUtils.a(d.substring(4)).e();
            if (e != null && !e.isEmpty()) {
                for (cwd cwdVar : e) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 1) {
                        watchFaceIdReportInfo.setWatchFaceId(cvx.e(c));
                    } else if (a2 == 2) {
                        watchFaceIdReportInfo.setWatchFaceVersion(cvx.e(c));
                    } else if (a2 == 3) {
                        watchFaceIdReportInfo.setReportStatus(CommonUtil.a(c, 16));
                    } else if (a2 == 127) {
                        i = CommonUtil.a(c, 16);
                    } else {
                        LogUtil.h(TAG, "handleReportStatus, nothing to do");
                    }
                }
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleReportStatus tlvList error");
            }
        } catch (cwg e2) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleReportStatus TlvException:", ExceptionUtils.d(e2));
        }
        reportAck(watchFaceIdReportInfo, i);
        dispatchIdReportCb(watchFaceIdReportInfo);
    }

    private void dispatchIdReportCb(WatchFaceIdReportInfo watchFaceIdReportInfo) {
        Iterator<String> it = this.sWatchFaceCallbackList.keySet().iterator();
        while (it.hasNext()) {
            this.sWatchFaceCallbackList.get(it.next()).onResponse(0, watchFaceIdReportInfo);
        }
    }

    private void handleApply(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a(TAG, "5.39.3 handleApply:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleApply data is error");
            return;
        }
        WatchFaceOperateInfo watchFaceOperateInfo = new WatchFaceOperateInfo();
        try {
            bmt.b b = new bmt().b(bArr, 2);
            watchFaceOperateInfo.setWatchfaceId(b.b((byte) 1, ""));
            watchFaceOperateInfo.setWatchfaceVersion(b.b((byte) 2, ""));
            watchFaceOperateInfo.setOperate(b.a((byte) 3, 0));
            watchFaceOperateInfo.setNeedReceive(b.a((byte) 4, 1));
            watchFaceOperateInfo.setErrorCode(b.a(Byte.MAX_VALUE, 0));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleApply TlvException:", ExceptionUtils.d(e));
            sqo.an("handleApply tlv data is exception " + ExceptionUtils.d(e));
        }
        reportForUi(watchFaceOperateInfo);
    }

    private void handleFaceInfo(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a(TAG, "5.39.2 handleFaceInfo:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleFaceInfo data is error");
            return;
        }
        WatchFaceList watchFaceList = new WatchFaceList();
        watchFaceList.setWatchFaceList(new ArrayList(16));
        String substring = d.substring(4);
        try {
            bmt.b b = new bmt().b(bArr, 2);
            watchFaceList.setIsWatchfaceFull(b.a((byte) 8, 0) == 1);
            watchFaceList.setLeftSpace(b.a((byte) 9, -1));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleFaceInfo TlvException:", ExceptionUtils.d(e));
        }
        try {
            cwe cweVar = this.mTlvUtils.a(substring).a().get(0);
            LogUtil.a(TAG, "getTlvfathers size:", Integer.valueOf(cweVar.a().size()));
            for (cwe cweVar2 : cweVar.a()) {
                WatchFaceList.WatchFace watchFace = new WatchFaceList.WatchFace();
                for (cwd cwdVar : cweVar2.e()) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    if (a2 == 3) {
                        watchFace.setWatchFaceId(WatchFaceUtil.hexToIntList(cwdVar.c()));
                    } else if (a2 == 4) {
                        watchFace.setWatchFaceVersion(WatchFaceUtil.hexToIntList(cwdVar.c()));
                    } else if (a2 == 5) {
                        watchFace.setWatchFaceType(CommonUtil.a(cwdVar.c(), 16));
                    } else if (a2 == 7) {
                        watchFace.setWatchFaceExpandType(CommonUtil.a(cwdVar.c(), 16));
                    } else {
                        LogUtil.h(TAG, "handleFaceInfo, nothing to do");
                    }
                }
                watchFaceList.getWatchFaceList().add(watchFace);
            }
            reportSuccessFaceInfo(watchFaceList);
        } catch (cwg e2) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleFaceInfo TlvException:", ExceptionUtils.d(e2));
        } catch (IndexOutOfBoundsException e3) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleFaceInfo IndexOutOfBoundsException:", ExceptionUtils.d(e3));
        }
    }

    private void reportSuccessFaceInfo(WatchFaceList watchFaceList) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(2);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(0, watchFaceList);
                }
                sCommandCallbacks.remove(2);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleFaceInfo callback error");
            }
        }
    }

    private void reportWatchFaceNameInfo(WatchFaceInfoResponse watchFaceInfoResponse) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(6);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(0, watchFaceInfoResponse);
                }
                sCommandCallbacks.remove(6);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleNameInfo callback error");
            }
        }
    }

    private void reportForUi(WatchFaceOperateInfo watchFaceOperateInfo) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(3);
            if (list != null && !list.isEmpty()) {
                LogUtil.h(TAG, "reportForUi, callbackList isn't null");
                sCommandCallbacks.remove(3);
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(0, watchFaceOperateInfo);
                }
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleApply callback error");
            }
        }
    }

    private void handleDeviceInfo(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a(TAG, "5.39.1 handleDeviceInfo ", d);
        ReleaseLogUtil.e(TAG_RELEASE, "5.39.1 handleDeviceInfo is ok.");
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceInfo data is error");
            return;
        }
        String substring = d.substring(4);
        WatchFaceParamsResult watchFaceParamsResult = new WatchFaceParamsResult();
        try {
            cwe a2 = this.mTlvUtils.a(substring);
            List<cwd> e = a2.e();
            if (e != null && !e.isEmpty()) {
                for (cwd cwdVar : e) {
                    handleDeviceInfoTlv(CommonUtil.a(cwdVar.e(), 16), cwdVar.c(), watchFaceParamsResult);
                }
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceInfo tlv error");
            }
            addCompatibleDialList(a2, watchFaceParamsResult);
            reportDeviceInfo(watchFaceParamsResult);
        } catch (cwg e2) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleDeviceInfo error:", ExceptionUtils.d(e2));
        }
    }

    private void reportDeviceInfo(WatchFaceParamsResult watchFaceParamsResult) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(1);
            if (list != null && !list.isEmpty()) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().d(0, watchFaceParamsResult);
                }
                sCommandCallbacks.remove(1);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleDeviceInfo callback error");
            }
        }
    }

    private void addCompatibleDialList(cwe cweVar, WatchFaceParamsResult watchFaceParamsResult) {
        if (koq.b(cweVar.a()) || watchFaceParamsResult == null) {
            return;
        }
        for (cwe cweVar2 : cweVar.a()) {
            ArrayList arrayList = new ArrayList(10);
            Iterator<cwe> it = cweVar2.a().iterator();
            while (it.hasNext()) {
                List<cwd> e = it.next().e();
                CompatibleDialStruct compatibleDialStruct = new CompatibleDialStruct();
                for (cwd cwdVar : e) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 9) {
                        compatibleDialStruct.setDialWidth(CommonUtil.a(c, 16));
                    } else if (a2 == 10) {
                        compatibleDialStruct.setDialHeight(CommonUtil.a(c, 16));
                    } else if (a2 == 11) {
                        compatibleDialStruct.setSupportVersion(cvx.e(c));
                    } else {
                        LogUtil.h(TAG, "addCompatibleDialList, invalid propType: ", Integer.valueOf(a2));
                    }
                }
                arrayList.add(compatibleDialStruct);
            }
            watchFaceParamsResult.setCompatibleDialList(arrayList);
        }
    }

    private void handleDeviceInfoTlv(int i, String str, WatchFaceParamsResult watchFaceParamsResult) {
        switch (i) {
            case 1:
                watchFaceParamsResult.setMaxFirmware(WatchFaceUtil.hexToIntList(str));
                break;
            case 2:
                this.mDeviceWidth = CommonUtil.a(str, 16);
                watchFaceParamsResult.setScreenWidth(CommonUtil.a(str, 16));
                break;
            case 3:
                this.mDeviceHeight = CommonUtil.a(str, 16);
                watchFaceParamsResult.setScreenHeight(CommonUtil.a(str, 16));
                break;
            case 4:
                watchFaceParamsResult.setSupportFileType(CommonUtil.a(str, 16));
                break;
            case 5:
                watchFaceParamsResult.setSupportSort(CommonUtil.a(str, 16));
                break;
            case 6:
                watchFaceParamsResult.setSupportEarlyFirmwareVersion(WatchFaceUtil.hexToIntList(str));
                break;
            default:
                switch (i) {
                    case 23:
                        watchFaceParamsResult.setSupportSync(CommonUtil.a(str, 16));
                        break;
                    case 24:
                        watchFaceParamsResult.setCurrentMode(CommonUtil.a(str, 16));
                        break;
                    case 25:
                        watchFaceParamsResult.setSupportOneTouch(String.valueOf(CommonUtil.a(str, 16)));
                        break;
                    case 26:
                        watchFaceParamsResult.setSupportGaussianColor(String.valueOf(CommonUtil.a(str, 16)));
                        break;
                    default:
                        LogUtil.h(TAG, "handleDeviceInfoTlv default type:", Integer.valueOf(i));
                        break;
                }
        }
    }

    public int getDeviceWidth() {
        return this.mDeviceWidth;
    }

    public int getDeviceHeight() {
        return this.mDeviceHeight;
    }

    private static Object getCommandCallbackCallbackList() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (WatchFaceDeviceService.class) {
            map = sCommandCallbacks;
        }
        return map;
    }

    public void getDeviceInfo() {
        String str = (cvx.e(1) + cvx.e(0)) + (cvx.e(2) + cvx.e(0)) + (cvx.e(3) + cvx.e(0)) + (cvx.e(4) + cvx.e(0)) + (cvx.e(5) + cvx.e(0));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(1);
        deviceCommand.setDataContent(cvx.a(str));
        deviceCommand.setDataLen(cvx.a(str).length);
        LogUtil.a(TAG, "getDeviceInfo, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    private void getDeviceWatchInfo() {
        String str = cvx.e(1) + cvx.e(0);
        int i = CommonUtil.cc() ? 2 : 1;
        if (Utils.o() && !TextUtils.equals("CN", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode())) {
            i = 3;
        }
        String str2 = str + (cvx.e(6) + cvx.e(1) + cvx.e(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(2);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        LogUtil.a(TAG, "getDeviceWatchInfo, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    private void reportAck(WatchFaceIdReportInfo watchFaceIdReportInfo, int i) {
        if (i != 100000) {
            LogUtil.a(TAG, "reportAck fail errorCode:", Integer.valueOf(i));
            return;
        }
        String c = cvx.c(watchFaceIdReportInfo.getWatchFaceId());
        String str = cvx.e(1) + cvx.e(c.length() / 2) + c;
        String c2 = cvx.c(watchFaceIdReportInfo.getWatchFaceVersion());
        String str2 = str + (cvx.e(2) + cvx.e(c2.length() / 2) + c2) + (cvx.e(127) + cvx.e(4) + cvx.b(i));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(5);
        deviceCommand.setDataContent(cvx.a(str2));
        deviceCommand.setDataLen(cvx.a(str2).length);
        LogUtil.a(TAG, "reportACK, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    private void sendGetNameCommand(List<String> list, int i) {
        if (list == null || list.isEmpty()) {
            return;
        }
        String str = cvx.e(1) + cvx.e(1) + cvx.e(i);
        StringBuilder sb = new StringBuilder(20);
        for (String str2 : list) {
            String str3 = cvx.e(4) + cvx.e(cvx.c(str2).length() / 2) + cvx.c(str2);
            sb.append(cvx.e(131) + cvx.e(str3.length() / 2) + str3);
        }
        String str4 = str + (cvx.e(OldToNewMotionPath.SPORT_TYPE_TENNIS) + cvx.d(sb.length() / 2) + ((Object) sb));
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(6);
        deviceCommand.setDataContent(cvx.a(str4));
        deviceCommand.setDataLen(cvx.a(str4).length);
        LogUtil.a(TAG, "sendGetNameCommand, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    public void queryWatchStatus(IBaseResponseCallback iBaseResponseCallback) {
        registerWatchFaceCallback(iBaseResponseCallback, 2);
        getDeviceWatchInfo();
    }

    public void applyWatchFace(String str, String str2, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        registerWatchFaceCallback(iBaseResponseCallback, 3);
        if (z) {
            OperateWatchFaceService.getInstance().sendApplySyncWatchFaceCommand(str, str2);
        } else {
            OperateWatchFaceService.getInstance().sendApplyWatchFaceCommand(str, str2);
        }
    }

    public void deleteWatchFace(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        registerWatchFaceCallback(iBaseResponseCallback, 3);
        OperateWatchFaceService.getInstance().sendDeleteWatchFaceCommand(str, str2);
    }

    private void registerWatchFaceCallback(IBaseResponseCallback iBaseResponseCallback, int i) {
        ReleaseLogUtil.e(TAG_RELEASE, "registerWatchFaceCallback enter, command is 5.39.", Integer.valueOf(i));
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(Integer.valueOf(i));
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "command 5.39.", Integer.valueOf(i), " have no callback");
                    ArrayList arrayList = new ArrayList(20);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(Integer.valueOf(i), arrayList);
                } else {
                    LogUtil.a(TAG, "command 5.39.", Integer.valueOf(i), " have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
    }

    public void registerIdReportCb(String str, ResponseCallback<WatchFaceIdReportInfo> responseCallback) {
        this.sWatchFaceCallbackList.put(str, responseCallback);
    }

    public void unregisterIdReportCb(String str) {
        this.sWatchFaceCallbackList.remove(str);
    }
}

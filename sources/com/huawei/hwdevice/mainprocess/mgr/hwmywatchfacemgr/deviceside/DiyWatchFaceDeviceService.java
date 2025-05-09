package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.AlbumWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.KaleidoscopeWatchFaceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WearWatchFaceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bmk;
import defpackage.bms;
import defpackage.bmt;
import defpackage.jfq;
import defpackage.jfs;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class DiyWatchFaceDeviceService implements BluetoothDataReceiveCallback {
    private static final int BACKGROUND_COLOR_TYPE = 16;
    private static final int BACKGROUND_IMAGE_INDEX = 4;
    private static final int BACKGROUND_IMAGE_LIST = 2;
    private static final int BACKGROUND_IMAGE_NAME = 5;
    private static final int BACKGROUND_IMAGE_NUM = 1;
    private static final int BACKGROUND_IMAGE_OPTION = 11;
    private static final int BACKGROUND_IMAGE_STRUCT = 3;
    private static final int BACKGROUND_IMAGE_TYPE = 3;
    private static final int BACKGROUND_INDEX = 6;
    private static final int BACKGROUND_LIST = 4;
    private static final int BACKGROUND_NAME = 7;
    private static final int CALLBACK_TYPE_FAILED = 102;
    private static final int CALLBACK_TYPE_SUCCESS = 101;
    private static final int CALLBACK_WATCH_FACE_PHOTO_TRANSFER_NUM = 111;
    private static final int CAN_INTELLECT_COLOR = 2;
    private static final int CAROUSEL_MODE_TYPE = 23;
    private static final int COMMAND_ID_GET_KALEIDOSCOPE_WATCH_FACE = 17;
    private static final int COMMAND_ID_GET_WEAR_WATCH_FACE = 19;
    private static final int COMMAND_ID_SET_KALEIDOSCOPE_WATCH_FACE = 18;
    private static final int COMMAND_ID_SET_WEAR_WATCH_FACE = 20;
    private static final int COMMAND_ID_WATCH_FACE_GET_PHOTO_INFO = 8;
    private static final int COMMAND_ID_WATCH_FACE_NOTIFY_DEVICE_RECEIVE = 9;
    private static final int CONTAINER_INDEX_TYPE = 21;
    private static final int DATA_LIST = 19;
    private static final int DEFAULT_ERROR_INT = -1;
    private static final int ERROR_CODE = 127;
    private static final int ERROR_CODE_SUCCESS = 100000;
    private static final int FONT_OPA_0x1E = 30;
    private static final int GAUSS_BLUR_RADIUS_0x1D = 29;
    private static final int GAUSS_COLOR_BRIGHTNESS_0x1C = 28;
    private static final int GAUSS_COLOR_SATURATION_0x1B = 27;
    private static final int GET_CLOCK_TYPE_CAPABILITY = 5;
    private static final int GET_CUR_STYLE_INDEX = 10;
    private static final int GET_KALEIDOSCOPE_CURRENT_MATERIAL_IMAGE_INDEX = 7;
    private static final int GET_KALEIDOSCOPE_HANDS_STYLE_INDEX = 8;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_INDEX = 5;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_LIST = 3;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_NAME = 6;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_STRUCT = 4;
    private static final int GET_KALEIDOSCOPE_MATERIAL_IMAGE_TYPE = 2;
    private static final int GET_KALEIDOSCOPE_MAX_MATERIAL_IMAGES = 1;
    private static final int GET_KALEIDOSCOPE_TYPE = 9;
    private static final int GET_MAX_STYLE_NUM = 1;
    private static final int GET_PREVIEW_NAME = 9;
    private static final int GET_TINT_CLOCK_OPTION = 12;
    private static final int GET_WEAR_IMAGE_OPTION = 11;
    private static final int GET_WEAR_IMAGE_RADIUS = 13;
    private static final int GET_WEAR_STYLE_ID = 8;
    private static final int GET_WEAR_STYLE_LIST = 6;
    private static final int GET_WEAR_STYLE_NUM = 2;
    private static final int GET_WEAR_STYLE_TYPE = 3;
    private static final int GET_WEAR_TYPE_CAPABILITY = 4;
    private static final int MAX_BACKGROUND_IMAGES = 1;
    private static final int NEED_TRANSFER_NUM = 8;
    private static final int OPTION_IDX_0x21 = 33;
    private static final int OPTION_INDEX_TYPE = 22;
    private static final int OPTION_SELECTED_LIST_0x1F = 31;
    private static final int OPTION_SELECTED_STRUCT_0x20 = 32;
    private static final int PORTRAIT_MODE_TYPE = 13;
    private static final int PORT_POSITION_INDEX_TYPE = 12;
    private static final int POSITION_INDEX = 8;
    private static final int POSITION_INDEX_VALUE = 6;
    private static final int SET_BACKGROUND_COLOR_TYPE = 14;
    private static final int SET_BG_IMAGE_NAME = 7;
    private static final int SET_CLOCK_STYLE_COLOR = 9;
    private static final int SET_CLOCK_STYLE_INDEX = 8;
    private static final int SET_CONTAINER_INDEX_TYPE = 19;
    private static final int SET_CUR_WEAR_IMAGE_INDEX = 10;
    private static final int SET_DATA_LIST_TYPE = 17;
    private static final int SET_DATA_STRUCT_TYPE = 18;
    private static final int SET_FONT_OPA_0x1B = 27;
    private static final int SET_GAUSS_BLUR_RADIUS_0x1A = 26;
    private static final int SET_GAUSS_COLOR_BRIGHTNESS_0x19 = 25;
    private static final int SET_GAUSS_COLOR_SATURATION_0x18 = 24;
    private static final int SET_IS_USE_GAUSS_COLOR_0x1C = 28;
    private static final int SET_KALEIDOSCOPE_CURRENT_MATERIAL_IMAGE_INDEX = 6;
    private static final int SET_KALEIDOSCOPE_HANDS_STYLE_INDEX = 7;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_INDEX = 4;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_LIST = 2;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_NAME = 5;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_NUM = 1;
    private static final int SET_KALEIDOSCOPE_MATERIAL_IMAGE_STRUCT = 3;
    private static final int SET_KALEIDOSCOPE_TRANSFER_NUM = 9;
    private static final int SET_KALEIDOSCOPE_TYPE = 8;
    private static final int SET_OPTION_IDX_0x1F = 31;
    private static final int SET_OPTION_INDEX_TYPE = 20;
    private static final int SET_OPTION_SELECTED_LIST_0x1D = 29;
    private static final int SET_OPTION_SELECTED_STRUCT_0X1E = 30;
    private static final int SET_PORTRAIT_MODE_TYPE = 11;
    private static final int SET_PORT_POSITION_INDEX_TYPE = 10;
    private static final int SET_PREVIEW_NAME_VALUE = 5;
    private static final int SET_SMART_FONT_INDEX_0x16 = 22;
    private static final int SET_SMART_POS_INDEX_0x17 = 23;
    private static final int SET_SMART_TIME_INDEX_0x15 = 21;
    private static final int SET_STYLE_APPLY_ALL_TYPE = 16;
    private static final int SET_TIME_COLOR_TYPE = 13;
    private static final int SET_TIME_STYLE_INDEX_TYPE = 12;
    private static final int SET_VALUE_TYPE_INDEX_TYPE = 9;
    private static final int SET_WEAR_STYLE_ID_VALUE = 4;
    private static final int SET_WEAR_STYLE_LIST_VALUE = 2;
    private static final int SET_WEAR_STYLE_NUM_VALUE = 1;
    private static final int SET_WEAR_STYLE_STRUCT_VALUE = 3;
    private static final int SET_WEAR_TRANSFER_NUM = 11;
    private static final int SET_WEAR_TYPE_VALUE = 6;
    private static final int SET_WRIST_SWITCH_TYPE = 15;
    private static final int SMART_FONT_INDEX_0x19 = 25;
    private static final int SMART_POS_INDEX_0x1A = 26;
    private static final int SMART_TIME_INDEX_0x18 = 24;
    private static final int STYLE_APPLY_ALL_TYPE = 18;
    private static final int STYLE_INDEX = 9;
    private static final int STYLE_INDEX_VALUE = 7;
    private static final String TAG = "DiyWatchFaceDeviceService";
    private static final String TAG_RELEASE = "R_DiyWatchFaceDeviceService";
    private static final int TIME_COLOR_TYPE = 15;
    private static final int TIME_STYLE_INDEX_TYPE = 14;
    private static final int VALUE_TYPE_INDEX_TYPE = 10;
    private static final int WRIST_SWITCH_TYPE = 17;
    private static Map<Integer, List<IBaseResponseCallback>> sCommandCallbacks = new HashMap(16);
    private jfq mDeviceConfigManager;

    private DiyWatchFaceDeviceService() {
        this.mDeviceConfigManager = jfq.c();
        jfs.d().b(TAG, this);
    }

    public static DiyWatchFaceDeviceService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static class SingletonHolder {
        private static final DiyWatchFaceDeviceService INSTANCE = new DiyWatchFaceDeviceService();

        private SingletonHolder() {
        }
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 1) {
            ReleaseLogUtil.d(TAG_RELEASE, "onDataReceived data illegal");
            return;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "onDataReceived commands id :", Byte.valueOf(bArr[1]));
        byte b = bArr[1];
        if (b == 8) {
            handlePhotoInfo(bArr);
        }
        if (b == 9) {
            handlePhotoStatus(bArr);
            return;
        }
        switch (b) {
            case 17:
                handleKaleidoscopeInfo(bArr);
                break;
            case 18:
                handleKaleidoscopeStatus(bArr);
                break;
            case 19:
                handleWearInfo(bArr);
                break;
            case 20:
                handleWearStatus(bArr);
                break;
            default:
                LogUtil.h(TAG, "onDataReceived nothing to do");
                break;
        }
    }

    private void handlePhotoInfo(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "handlePhotoInfo data is error");
            return;
        }
        AlbumWatchFaceInfo albumWatchFaceInfo = new AlbumWatchFaceInfo();
        try {
            bmt.b b = new bmt().b(bArr, 2);
            albumWatchFaceInfo.setMaxBackgroundImages(b.a((byte) 1, -1));
            albumWatchFaceInfo.setCanIntellectColor(b.a((byte) 2, -1));
            albumWatchFaceInfo.setBackgroundImageType(b.a((byte) 3, -1));
            albumWatchFaceInfo.setPositionIndex(b.a((byte) 8, -1));
            albumWatchFaceInfo.setStyleIndex(b.a((byte) 9, -1));
            albumWatchFaceInfo.setBackgroundImageOption(b.a(BaseType.Float, -1));
            albumWatchFaceInfo.setValueTypeIndex(b.a((byte) 10, -1));
            albumWatchFaceInfo.setWristSwitch(b.a(BaseType.Array, -1));
            albumWatchFaceInfo.setStyleApplyAll(b.a(BaseType.Vector64, -1));
            albumWatchFaceInfo.setCarouselMode(b.a((byte) 23, -1));
            handleNewBackground(b.a((byte) 4), albumWatchFaceInfo);
            handleCustomData(b.a(BaseType.MaxBaseType), albumWatchFaceInfo);
            LogUtil.a(TAG, "handlePhotoInfo get optionSelectedList");
            handleOptionSelectedList(b.a((byte) 31), albumWatchFaceInfo);
            LogUtil.a(TAG, "handlePhotoInfo albumWatchFaceInfo is:", albumWatchFaceInfo);
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handlePhotoInfo TlvException:", ExceptionUtils.d(e));
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(8);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).d(101, albumWatchFaceInfo);
                sCommandCallbacks.remove(8);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handlePhotoInfo callback error");
            }
        }
    }

    private void handleOptionSelectedList(List<bmt.b> list, AlbumWatchFaceInfo albumWatchFaceInfo) {
        LogUtil.a("handleOptionSelectedList enter", new Object[0]);
        if (CollectionUtils.d(list)) {
            LogUtil.a("handleOptionSelectedList optionSelectedList is empty", new Object[0]);
            return;
        }
        if (list.size() != 2) {
            LogUtil.a("handleOptionSelectedList length expected 2 but ", Integer.valueOf(list.size()), " found");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<bmt.b> it = list.iterator();
        while (it.hasNext()) {
            int a2 = it.next().a(PublicSuffixDatabase.i, -1);
            if (a2 == -1) {
                LogUtil.b(TAG, "handleOptionSelectedList error");
                return;
            }
            arrayList.add(Integer.valueOf(a2));
        }
        albumWatchFaceInfo.setOptionSelectedList(arrayList);
        LogUtil.b(TAG, "handleOptionSelectedList out");
    }

    private void handleNewBackground(List<bmt.b> list, AlbumWatchFaceInfo albumWatchFaceInfo) {
        LogUtil.a(TAG, "enter handleNewBackground");
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleNewBackground backgroundTlvList is empty");
            return;
        }
        ArrayList<AlbumWatchFaceInfo.BackgroundBean> arrayList = new ArrayList<>(16);
        for (bmt.b bVar : list) {
            AlbumWatchFaceInfo.BackgroundBean backgroundBean = new AlbumWatchFaceInfo.BackgroundBean();
            backgroundBean.setBackgroundIndex(bVar.a((byte) 6, -1));
            backgroundBean.setBackgroundName(bVar.b((byte) 7, ""));
            backgroundBean.setPortPositionIndex(bVar.a(BaseType.Double, -1));
            backgroundBean.setPortraitMode(bVar.a((byte) 13, -1));
            backgroundBean.setTimeStyleIndex(bVar.a(BaseType.Vector, -1));
            backgroundBean.setTimeColor(bVar.b(BaseType.Obj, ""));
            backgroundBean.setBackgroundColor(bVar.b(BaseType.Union, ""));
            LogUtil.a(TAG, "handleNewBackground start set smart param");
            backgroundBean.setSmartTimeIndex(bVar.a((byte) 24, -1));
            backgroundBean.setSmartFontIndex(bVar.a((byte) 25, -1));
            backgroundBean.setSmartPosIndex(bVar.a((byte) 26, -1));
            backgroundBean.setGaussColorSaturation(bVar.a((byte) 27, -1));
            backgroundBean.setGaussColorBrightness(bVar.a((byte) 28, -1));
            backgroundBean.setGaussBlurRadius(bVar.a((byte) 29, -1));
            backgroundBean.setFontOpa(bVar.a((byte) 30, -1));
            LogUtil.a(TAG, "handleNewBackground end set smart param");
            arrayList.add(backgroundBean);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "handleNewBackground backgroundList size:", Integer.valueOf(arrayList.size()));
        albumWatchFaceInfo.setBackgroundList(arrayList);
    }

    private void handleCustomData(List<bmt.b> list, AlbumWatchFaceInfo albumWatchFaceInfo) {
        LogUtil.a(TAG, "enter handleCustomData");
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleNewBackground dataTlvList is empty");
            return;
        }
        ArrayList<AlbumWatchFaceInfo.WatchFacePhotoCustom> arrayList = new ArrayList<>(16);
        for (bmt.b bVar : list) {
            AlbumWatchFaceInfo.WatchFacePhotoCustom watchFacePhotoCustom = new AlbumWatchFaceInfo.WatchFacePhotoCustom();
            watchFacePhotoCustom.setContainerIndex(bVar.a((byte) 21, -1));
            watchFacePhotoCustom.setOptionIndex(bVar.a((byte) 22, -1));
            arrayList.add(watchFacePhotoCustom);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "handleNewBackground customList size:", Integer.valueOf(arrayList.size()));
        albumWatchFaceInfo.setDataList(arrayList);
    }

    private void handlePhotoStatus(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "handlePhotoInfo data is error");
            return;
        }
        try {
            bmt.b b = new bmt().b(bArr, 2);
            reportNewPhotoStatus(b.a(Byte.MAX_VALUE, -1), b.a((byte) 8, -1));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handlePhotoStatus error:", ExceptionUtils.d(e));
        }
    }

    private void reportNewPhotoStatus(int i, int i2) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(9);
            if (list == null || list.isEmpty()) {
                ReleaseLogUtil.d(TAG_RELEASE, "reportNewPhotoStatus callback error");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).d(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                ReleaseLogUtil.e(TAG_RELEASE, "reportNewPhotoStatus need transfer num:", Integer.valueOf(i2));
                list.get(list.size() - 1).d(111, Integer.valueOf(i2));
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "reportNewPhotoStatus errorCode is failed");
                list.get(list.size() - 1).d(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    private void handleKaleidoscopeInfo(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleKaleidoscopeInfo data is error");
            return;
        }
        KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo = new KaleidoscopeWatchFaceInfo();
        try {
            bmt.b b = new bmt().b(bArr, 2);
            kaleidoscopeWatchFaceInfo.setMaxMaterialImages(b.a((byte) 1, -1));
            kaleidoscopeWatchFaceInfo.setMaterialImageType(b.a((byte) 2, -1));
            handleMaterialImageList(b.a((byte) 3), kaleidoscopeWatchFaceInfo);
            kaleidoscopeWatchFaceInfo.setCurrentMaterialImageIndex(b.a((byte) 7, -1));
            kaleidoscopeWatchFaceInfo.setHandsStyleIndex(b.a((byte) 8, -1));
            kaleidoscopeWatchFaceInfo.setKaleidoscopeType(b.a((byte) 9, -1));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleKaleidoscopeInfo TlvException:", ExceptionUtils.d(e));
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(17);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).d(101, kaleidoscopeWatchFaceInfo);
                sCommandCallbacks.remove(17);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleKaleidoscopeInfo callback error");
            }
        }
    }

    private void handleMaterialImageList(List<bmt.b> list, KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo) {
        LogUtil.a(TAG, "enter handleMaterialImageList");
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleMaterialImageList materialImageList is empty");
            return;
        }
        ArrayList<KaleidoscopeWatchFaceInfo.MaterialImage> arrayList = new ArrayList<>(16);
        for (bmt.b bVar : list) {
            KaleidoscopeWatchFaceInfo.MaterialImage materialImage = new KaleidoscopeWatchFaceInfo.MaterialImage();
            materialImage.setMaterialImageIndex(bVar.a((byte) 5, -1));
            materialImage.setMaterialImageName(bVar.b((byte) 6, ""));
            arrayList.add(materialImage);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "handleMaterialImageList materialImages size:", Integer.valueOf(arrayList.size()));
        kaleidoscopeWatchFaceInfo.setMaterialImageList(arrayList);
    }

    private void handleKaleidoscopeStatus(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleKaleidoscopeStatus data is error");
            return;
        }
        try {
            bmt.b b = new bmt().b(bArr, 2);
            reportKaleidoscopeStatus(b.a(Byte.MAX_VALUE, -1), b.a((byte) 9, -1));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleKaleidoscopeStatus error:", ExceptionUtils.d(e));
        }
    }

    private void reportKaleidoscopeStatus(int i, int i2) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(18);
            if (list == null || list.isEmpty()) {
                ReleaseLogUtil.d(TAG_RELEASE, "reportKaleidoscopeStatus callback error");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).d(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                ReleaseLogUtil.e(TAG_RELEASE, "reportKaleidoscopeStatus need transfer num:", Integer.valueOf(i2));
                list.get(list.size() - 1).d(111, Integer.valueOf(i2));
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "reportKaleidoscopeStatus errorCode is failed");
                list.get(list.size() - 1).d(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    private void handleWearInfo(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWearInfo data is error");
            return;
        }
        WearWatchFaceInfo wearWatchFaceInfo = new WearWatchFaceInfo();
        try {
            bmt.b b = new bmt().b(bArr, 2);
            wearWatchFaceInfo.setMaxStyleNum(b.a((byte) 1, -1));
            wearWatchFaceInfo.setWearStyleNum(b.a((byte) 2, -1));
            wearWatchFaceInfo.setWearStyleType(b.a((byte) 3, -1));
            wearWatchFaceInfo.setWearTypeCapability(b.b((byte) 4, -1L));
            wearWatchFaceInfo.setClockTypeCapability(b.b((byte) 5, -1L));
            handleWearStyleList(b.a((byte) 6), wearWatchFaceInfo);
            wearWatchFaceInfo.setCurStyleIndex(b.b((byte) 10, -1L));
            wearWatchFaceInfo.setWearImageOption(b.a(BaseType.Float, -1));
            wearWatchFaceInfo.setTintClockOption(b.a(BaseType.Double, -1));
            wearWatchFaceInfo.setRectRadius(b.a((byte) 13, -1));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleWearInfo TlvException:", ExceptionUtils.d(e));
        }
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(19);
            if (list != null && !list.isEmpty()) {
                list.get(list.size() - 1).d(101, wearWatchFaceInfo);
                sCommandCallbacks.remove(19);
            } else {
                ReleaseLogUtil.d(TAG_RELEASE, "handleWearInfo callback error");
            }
        }
    }

    private void handleWearStyleList(List<bmt.b> list, WearWatchFaceInfo wearWatchFaceInfo) {
        LogUtil.a(TAG, "enter handleWearStyleList");
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWearStyleList wearStyleTlvList is empty");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (bmt.b bVar : list) {
            WearWatchFaceInfo.WearStyleStruct wearStyleStruct = new WearWatchFaceInfo.WearStyleStruct();
            wearStyleStruct.setWearStyleId(bVar.b((byte) 8, -1L));
            wearStyleStruct.setPreviewName(bVar.b((byte) 9, ""));
            arrayList.add(wearStyleStruct);
        }
        ReleaseLogUtil.e(TAG_RELEASE, "handleWearStyleList wearStyleList size:", Integer.valueOf(arrayList.size()));
        wearWatchFaceInfo.setWearStyleList(arrayList);
    }

    private void handleWearStatus(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            ReleaseLogUtil.d(TAG_RELEASE, "handleWearStatus data is error");
            return;
        }
        try {
            bmt.b b = new bmt().b(bArr, 2);
            reportWearStatus(b.a(Byte.MAX_VALUE, -1), b.a(BaseType.Float, -1));
        } catch (bmk e) {
            ReleaseLogUtil.c(TAG_RELEASE, "handleWearStatus error:", ExceptionUtils.d(e));
        }
    }

    private void reportWearStatus(int i, int i2) {
        synchronized (getCommandCallbackCallbackList()) {
            List<IBaseResponseCallback> list = sCommandCallbacks.get(20);
            if (list == null || list.isEmpty()) {
                ReleaseLogUtil.d(TAG_RELEASE, "reportWearStatus callback error");
            } else if (i == 100000 && i2 == 0) {
                list.get(list.size() - 1).d(101, 0);
                list.remove(list.size() - 1);
            } else if (i == 100000 && i2 > 0) {
                ReleaseLogUtil.e(TAG_RELEASE, "reportWearStatus need transfer num:", Integer.valueOf(i2));
                list.get(list.size() - 1).d(111, Integer.valueOf(i2));
            } else {
                ReleaseLogUtil.e(TAG_RELEASE, "reportWearStatus errorCode is failed");
                list.get(list.size() - 1).d(102, null);
                list.remove(list.size() - 1);
            }
        }
    }

    private static Object getCommandCallbackCallbackList() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (DiyWatchFaceDeviceService.class) {
            map = sCommandCallbacks;
        }
        return map;
    }

    public void getWatchFacePhotoInfo(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(8);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter getWatchFacePhotoInfo have no callback");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(8, arrayList);
                } else {
                    LogUtil.a(TAG, "enter getWatchFacePhotoInfo have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        getPhotoWatchInfo();
    }

    private void getPhotoWatchInfo() {
        bms bmsVar = new bms();
        bmsVar.e(1);
        bmsVar.e(2);
        bmsVar.e(3);
        bmsVar.e(4);
        bmsVar.e(8);
        bmsVar.e(9);
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(8);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "getPhotoWatchInfo, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    public void saveNewPhotoInfoToDevice(AlbumWatchFaceInfo albumWatchFaceInfo, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter saveNewPhotoInfoToDevice");
        if (albumWatchFaceInfo == null) {
            iBaseResponseCallback.d(102, "");
            ReleaseLogUtil.d(TAG_RELEASE, "saveNewPhotoInfoToDevice error");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(9);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter saveNewPhotoInfoToDevice have no callback");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(9, arrayList);
                } else {
                    LogUtil.a(TAG, "enter saveNewPhotoInfoToDevice have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        LogUtil.a(TAG, "saveNewPhotoInfoToDevice:", albumWatchFaceInfo);
        makeNewPhotoInfoCommand(albumWatchFaceInfo);
    }

    private void makeNewPhotoInfoCommand(AlbumWatchFaceInfo albumWatchFaceInfo) {
        bms bmsVar = new bms();
        ArrayList<AlbumWatchFaceInfo.BackgroundBean> backgroundList = albumWatchFaceInfo.getBackgroundList();
        if (!CollectionUtils.d(backgroundList)) {
            bmsVar.j(1, backgroundList.size());
            combineWatchFaceBgListCommand(bmsVar, backgroundList, albumWatchFaceInfo.getIsUseGaussColor());
        }
        bmsVar.j(6, albumWatchFaceInfo.getPositionIndex());
        bmsVar.j(7, albumWatchFaceInfo.getStyleIndex());
        bmsVar.j(9, albumWatchFaceInfo.getValueTypeIndex());
        bmsVar.j(15, albumWatchFaceInfo.getWristSwitch());
        bmsVar.j(16, albumWatchFaceInfo.getStyleApplyAll());
        ArrayList<AlbumWatchFaceInfo.WatchFacePhotoCustom> dataList = albumWatchFaceInfo.getDataList();
        if (!CollectionUtils.d(dataList)) {
            bmsVar.b(17);
            for (AlbumWatchFaceInfo.WatchFacePhotoCustom watchFacePhotoCustom : dataList) {
                bmsVar.c(18);
                bmsVar.d(19, watchFacePhotoCustom.getContainerIndex());
                bmsVar.d(20, watchFacePhotoCustom.getOptionIndex());
                bmsVar.c(bmsVar.c());
            }
            bmsVar.b(bmsVar.b());
        }
        bmsVar.j(28, albumWatchFaceInfo.getIsUseGaussColor());
        List<Integer> optionSelectedList = albumWatchFaceInfo.getOptionSelectedList();
        if (!CollectionUtils.d(optionSelectedList)) {
            if (optionSelectedList.size() != 2) {
                LogUtil.h(TAG, "makeNewPhotoInfoCommand option size expected 2 but ", Integer.valueOf(optionSelectedList.size()));
            } else {
                LogUtil.h(TAG, "makeNewPhotoInfoCommand start create option_selected_list");
                bmsVar.b(29);
                for (Integer num : optionSelectedList) {
                    bmsVar.c(30);
                    bmsVar.d(31, num.intValue());
                    bmsVar.c(bmsVar.c());
                }
                bmsVar.b(bmsVar.b());
                LogUtil.h(TAG, "makeNewPhotoInfoCommand end create option_selected_list");
            }
        } else {
            LogUtil.h(TAG, "makeNewPhotoInfoCommand optionSelectedList is empty");
        }
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(9);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "makeNewPhotoInfoCommand, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    private void combineWatchFaceBgListCommand(bms bmsVar, List<AlbumWatchFaceInfo.BackgroundBean> list, int i) {
        bmsVar.b(2);
        for (AlbumWatchFaceInfo.BackgroundBean backgroundBean : list) {
            bmsVar.c(3);
            bmsVar.d(4, backgroundBean.getBackgroundIndex());
            bmsVar.e(5, backgroundBean.getBackgroundName());
            bmsVar.d(10, backgroundBean.getPortPositionIndex());
            bmsVar.d(11, backgroundBean.getPortraitMode());
            bmsVar.d(12, backgroundBean.getTimeStyleIndex());
            bmsVar.e(13, backgroundBean.getTimeColor());
            bmsVar.e(14, backgroundBean.getBackgroundColor());
            LogUtil.a(TAG, "combineWatchFaceBgListCommand start create smart param");
            if (i != -1) {
                bmsVar.d(21, backgroundBean.getSmartTimeIndex());
                bmsVar.d(22, backgroundBean.getSmartFontIndex());
                bmsVar.d(23, backgroundBean.getSmartPosIndex());
                bmsVar.d(28, i);
            }
            if (i == 1) {
                LogUtil.a(TAG, "combineWatchFaceBgListCommand isUseGaussColor is 1");
                bmsVar.d(24, backgroundBean.getGaussColorSaturation());
                bmsVar.d(25, backgroundBean.getGaussColorBrightness());
                bmsVar.d(26, backgroundBean.getGaussBlurRadius());
                bmsVar.d(27, backgroundBean.getFontOpa());
            }
            LogUtil.a(TAG, "combineWatchFaceBgListCommand end create smart param");
            bmsVar.c(bmsVar.c());
        }
        bmsVar.b(bmsVar.b());
    }

    public void getKaleidoscopeWatchFaceInfo(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(17);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter getKaleidoscopeWatchFaceInfo have no callback");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(17, arrayList);
                } else {
                    LogUtil.a(TAG, "enter getKaleidoscopeWatchFaceInfo have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        sendKaleidoscopeWatchFaceCommand();
    }

    private void sendKaleidoscopeWatchFaceCommand() {
        bms bmsVar = new bms();
        bmsVar.e(1);
        bmsVar.e(2);
        bmsVar.e(3);
        bmsVar.e(5);
        bmsVar.e(6);
        bmsVar.e(7);
        bmsVar.e(8);
        bmsVar.e(9);
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(17);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "sendKaleidoscopeWatchFaceCommand, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    public void saveKaleidoscopeWatchFaceToDevice(KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter saveKaleidoscopeWatchFaceToDevice");
        if (kaleidoscopeWatchFaceInfo == null) {
            iBaseResponseCallback.d(102, "");
            ReleaseLogUtil.d(TAG_RELEASE, "saveKaleidoscopeWatchFaceToDevice error");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(18);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter saveKaleidoscopeWatchFaceToDevice have no callback");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(18, arrayList);
                } else {
                    LogUtil.a(TAG, "enter saveKaleidoscopeWatchFaceToDevice have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        setKaleidoscopeWatchFaceCommand(kaleidoscopeWatchFaceInfo);
    }

    private void setKaleidoscopeWatchFaceCommand(KaleidoscopeWatchFaceInfo kaleidoscopeWatchFaceInfo) {
        bms bmsVar = new bms();
        ArrayList<KaleidoscopeWatchFaceInfo.MaterialImage> materialImageList = kaleidoscopeWatchFaceInfo.getMaterialImageList();
        if (!CollectionUtils.d(materialImageList)) {
            bmsVar.j(1, materialImageList.size());
            bmsVar.b(2);
            for (KaleidoscopeWatchFaceInfo.MaterialImage materialImage : materialImageList) {
                bmsVar.c(3);
                bmsVar.d(4, materialImage.getMaterialImageIndex());
                bmsVar.e(5, materialImage.getMaterialImageName());
                bmsVar.c(bmsVar.c());
            }
            bmsVar.b(bmsVar.b());
        }
        bmsVar.j(6, kaleidoscopeWatchFaceInfo.getCurrentMaterialImageIndex());
        bmsVar.j(7, kaleidoscopeWatchFaceInfo.getHandsStyleIndex());
        bmsVar.j(8, kaleidoscopeWatchFaceInfo.getKaleidoscopeType());
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(18);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "setKaleidoscopeWatchFaceCommand, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    public void getWearWatchFaceInfo(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(19);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter getWearWatchFaceInfo have no callback");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(19, arrayList);
                } else {
                    LogUtil.a(TAG, "enter getWearWatchFaceInfo have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        sendWearWatchFaceCommand();
    }

    private void sendWearWatchFaceCommand() {
        bms bmsVar = new bms();
        bmsVar.e(1);
        bmsVar.e(2);
        bmsVar.e(3);
        bmsVar.e(4);
        bmsVar.e(5);
        bmsVar.e(6);
        bmsVar.e(8);
        bmsVar.e(9);
        bmsVar.e(10);
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(19);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "sendWearWatchFaceCommand, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }

    public void saveWearWatchFaceToDevice(WearWatchFaceInfo wearWatchFaceInfo, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG_RELEASE, "enter saveWearWatchFaceToDevice");
        if (wearWatchFaceInfo == null) {
            iBaseResponseCallback.d(102, "");
            ReleaseLogUtil.d(TAG_RELEASE, "saveWearWatchFaceToDevice error");
            return;
        }
        synchronized (getCommandCallbackCallbackList()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = sCommandCallbacks.get(20);
                if (list == null) {
                    ReleaseLogUtil.e(TAG_RELEASE, "enter saveWearWatchFaceToDevice have no callback");
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(iBaseResponseCallback);
                    sCommandCallbacks.put(20, arrayList);
                } else {
                    LogUtil.a(TAG, "enter saveWearWatchFaceToDevice have callback, add");
                    list.add(iBaseResponseCallback);
                }
            }
        }
        setWearWatchFaceCommand(wearWatchFaceInfo);
    }

    private void setWearWatchFaceCommand(WearWatchFaceInfo wearWatchFaceInfo) {
        bms bmsVar = new bms();
        List<WearWatchFaceInfo.WearStyleStruct> wearStyleList = wearWatchFaceInfo.getWearStyleList();
        if (!CollectionUtils.d(wearStyleList)) {
            bmsVar.j(1, wearStyleList.size());
            bmsVar.b(2);
            for (WearWatchFaceInfo.WearStyleStruct wearStyleStruct : wearStyleList) {
                bmsVar.c(3);
                bmsVar.b(4, wearStyleStruct.getWearStyleId());
                bmsVar.e(5, wearStyleStruct.getPreviewName());
                bmsVar.d(6, wearStyleStruct.getWearType());
                bmsVar.e(7, wearStyleStruct.getBgImageName());
                bmsVar.d(8, wearStyleStruct.getClockStyleIndex());
                bmsVar.e(9, wearStyleStruct.getClockStyleColor());
                bmsVar.c(bmsVar.c());
            }
            bmsVar.b(bmsVar.b());
        }
        bmsVar.d(10, wearWatchFaceInfo.getCurWearImageIndex());
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(20);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "setWearWatchFaceCommand, deviceCommand:", deviceCommand.toString());
        this.mDeviceConfigManager.b(deviceCommand);
    }
}

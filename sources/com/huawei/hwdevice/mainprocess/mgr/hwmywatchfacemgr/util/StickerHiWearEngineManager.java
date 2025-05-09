package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.util;

import android.content.Context;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import java.io.File;

/* loaded from: classes9.dex */
public class StickerHiWearEngineManager extends EngineLogicBaseManager {
    private static final String DEVICE_PACKAGE_NAME = "hw.watch.watchface.sticker";
    private static final Object LOCK = new Object();
    private static final String RELEASE_TAG = "R_StickerHiWearEngineManager";
    private static final String TAG = "StickerHiWearEngineManager";
    private static final String WEAR_FINGER_PRINT = "SystemApp";
    private static volatile StickerHiWearEngineManager sInstance;

    private StickerHiWearEngineManager(Context context) {
        super(context);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a(TAG, "StickerHiWearEngineManager onReceiveDeviceCommand");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.STICKER_MODULE;
    }

    public static StickerHiWearEngineManager getInstance() {
        StickerHiWearEngineManager stickerHiWearEngineManager;
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (LOCK) {
            LogUtil.a(TAG, "getInstance()");
            if (sInstance == null) {
                sInstance = new StickerHiWearEngineManager(BaseApplication.getContext());
            }
            stickerHiWearEngineManager = sInstance;
        }
        return stickerHiWearEngineManager;
    }

    public void sendStickerImageToDevice(String str, SendCallback sendCallback) {
        LogUtil.a(TAG, "sendStickerImageToDevice enter");
        spn.b bVar = new spn.b();
        bVar.a(new File(str));
        sendComand(bVar.e(), sendCallback);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.h(TAG, "onDeviceConnectionChange");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.watchface.sticker";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "SystemApp";
    }
}

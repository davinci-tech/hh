package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside;

import com.huawei.datatype.DeviceCommand;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bms;
import defpackage.jfq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class OperateWatchFaceService {
    private static final int APPLY_OPERATE_TYPE = 3;
    private static final int APPLY_SYNC_TYPE_0x7 = 7;
    private static final int APPLY_WATCH_FACE_ID = 1;
    private static final int APPLY_WATCH_FACE_VERSION = 2;
    private static final int NEED_RECEIVE = 4;
    private static final String TAG = "OperateWatchFaceService";
    private static final String TAG_RELEASE = "R_OperateWatchFaceService";
    private static final int VALUE_OPERATE_APPLY = 1;
    private static final int VALUE_OPERATE_DELETE = 2;

    public void sendDeleteWatchFaceCommand(String str, String str2) {
        ReleaseLogUtil.e(TAG_RELEASE, "sendDeleteWatchFaceCommand enter");
        sendOperateWatchFaceCommand(buildOperateCommandUtil(str, str2, 2));
    }

    public void sendApplyWatchFaceCommand(String str, String str2) {
        ReleaseLogUtil.e(TAG_RELEASE, "sendApplyWatchFaceCommand enter");
        sendOperateWatchFaceCommand(buildOperateCommandUtil(str, str2, 1));
    }

    public void sendApplySyncWatchFaceCommand(String str, String str2) {
        ReleaseLogUtil.e(TAG_RELEASE, "sendApplySyncWatchFaceCommand enter");
        bms buildOperateCommandUtil = buildOperateCommandUtil(str, str2, 1);
        buildOperateCommandUtil.j(7, 1);
        sendOperateWatchFaceCommand(buildOperateCommandUtil);
    }

    private bms buildOperateCommandUtil(String str, String str2, int i) {
        bms bmsVar = new bms();
        bmsVar.d(1, str);
        bmsVar.d(2, str2);
        bmsVar.a(3, i);
        return bmsVar;
    }

    private void sendOperateWatchFaceCommand(bms bmsVar) {
        ReleaseLogUtil.e(TAG_RELEASE, "sendOperateWatchFaceCommand enter");
        byte[] d = bmsVar.d();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(39);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(d);
        deviceCommand.setDataLen(d.length);
        LogUtil.a(TAG, "sendOperateWatchFaceCommand, deviceCommand:", deviceCommand.toString());
        jfq.c().b(deviceCommand);
    }

    static class SingletonHolder {
        private static final OperateWatchFaceService INSTANCE = new OperateWatchFaceService();

        private SingletonHolder() {
        }
    }

    public static OperateWatchFaceService getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

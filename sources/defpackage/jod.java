package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraAuthStatusCallback;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jod implements CameraContorlInterface {
    private static final Uri c = Uri.parse("content://com.huawei.camera.remotecallconfirmProvider/device");

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public boolean checkSupportCamera() {
        if (!a("com.huawei.camera.intent.RemoteCall") && !a("android.intent.action.RemoteCall")) {
            LogUtil.h("RemoteProviderImpl", "resolveInfo or resolveInfo.activityInfo is null, isSupportAuthAction is false");
            return false;
        }
        LogUtil.h("RemoteProviderImpl", "isSupportAuthAction is true");
        DeviceInfo a2 = jpt.a("RemoteProviderImpl");
        return (a2 == null || !jpp.e(a2) || a2.getPowerSaveModel() == 1) ? false : true;
    }

    private boolean a(String str) {
        Intent intent = new Intent(str);
        PackageManager packageManager = BaseApplication.getContext().getPackageManager();
        if (packageManager == null) {
            return false;
        }
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
        if (resolveActivity != null && resolveActivity.activityInfo != null) {
            return true;
        }
        LogUtil.h("RemoteProviderImpl", "resolveInfo or resolveInfo.activityInfo is null, isSupportAuthAction is false");
        return false;
    }

    private Cursor bIC_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("RemoteProviderImpl", "getCursor deviceUdid isEmpty");
            return null;
        }
        try {
            return BaseApplication.getContext().getContentResolver().query(c, null, str, null, null);
        } catch (SQLException | IllegalArgumentException | SecurityException e) {
            LogUtil.b("RemoteProviderImpl", "getCursor Exception :", ExceptionUtils.d(e));
            return null;
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public boolean supportAlgoArch() {
        int i;
        Cursor bIC_ = bIC_("default");
        if (bIC_ == null) {
            LogUtil.h("RemoteProviderImpl", "supportAlgoArch cursor is null");
            return false;
        }
        if (!bIC_.moveToFirst() || bIC_.getColumnIndex("support") < 0) {
            i = 0;
        } else {
            i = bIC_.getInt(bIC_.getColumnIndex("support"));
            LogUtil.a("RemoteProviderImpl", "supportAlgoArch status:", Integer.valueOf(i));
        }
        bIC_.close();
        return i == 1;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void getCameraStatus(CameraAuthStatusCallback cameraAuthStatusCallback) {
        int i;
        DeviceInfo a2 = jpt.a("RemoteProviderImpl");
        if (a2 == null) {
            LogUtil.h("RemoteProviderImpl", "getCameraAuthorizationStatus getConnectDeviceInfo is null");
            cameraAuthStatusCallback.authStatus(false);
            return;
        }
        Cursor bIC_ = bIC_(a2.getDeviceUdid());
        if (bIC_ == null) {
            LogUtil.h("RemoteProviderImpl", "getCameraAuthorizationStatus cursor is null");
            cameraAuthStatusCallback.authStatus(false);
            return;
        }
        if (bIC_.moveToFirst()) {
            i = bIC_.getInt(bIC_.getColumnIndex("status"));
            LogUtil.a("RemoteProviderImpl", "getCameraAuthorizationStatus status:", Integer.valueOf(i));
        } else {
            i = 0;
        }
        bIC_.close();
        cameraAuthStatusCallback.authStatus(Boolean.valueOf(i == 1));
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void updateCameraAuthStatus(boolean z) {
        DeviceInfo a2 = jpt.a("RemoteProviderImpl");
        if (a2 == null) {
            LogUtil.h("RemoteProviderImpl", "updateCameraAuthorization getConnectDeviceInfo is null");
            return;
        }
        String deviceUdid = a2.getDeviceUdid();
        Cursor bIC_ = bIC_(deviceUdid);
        if (bIC_ == null) {
            LogUtil.h("RemoteProviderImpl", "updateCameraAuthorization cursor is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        contentValues.put("status", Integer.valueOf(z ? 1 : 0));
        if (bIC_.getCount() == 0) {
            contentValues.put("udid", deviceUdid);
            contentResolver.insert(c, contentValues);
        } else if (bIC_.moveToFirst()) {
            if (bIC_.getInt(bIC_.getColumnIndex("status")) == -1) {
                contentValues.put("udid", deviceUdid);
                contentResolver.insert(c, contentValues);
            } else {
                contentResolver.update(c, contentValues, deviceUdid, null);
            }
        }
        bIC_.close();
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void deleteCameraAuthorization(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("RemoteProviderImpl", "deleteCameraAuth deviceInfo is null");
            return;
        }
        String deviceUdid = deviceInfo.getDeviceUdid();
        Cursor bIC_ = bIC_(deviceUdid);
        if (bIC_ == null) {
            LogUtil.h("RemoteProviderImpl", "deleteCameraAuth cursor is null");
            return;
        }
        if (BaseApplication.getContext().getContentResolver().delete(c, deviceUdid, null) > 0) {
            LogUtil.a("RemoteProviderImpl", "deleteCameraAuth success:", deviceInfo.getDeviceName());
        }
        bIC_.close();
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void unbindCameraService() {
        LogUtil.a("RemoteProviderImpl", "unbindCameraService success:", true);
    }
}

package defpackage;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.embedded.q0;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraAuthStatusCallback;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.SettingCameraCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class joc implements CameraContorlInterface {
    private static final Uri b = Uri.parse("content://com.huawei.dmsdp.provider/camera_remote_ctrl");

    /* renamed from: a, reason: collision with root package name */
    private ServiceConnection f13980a;
    private DeviceInfo c;
    private CameraAuthStatusCallback e;
    private boolean g;
    private String i;
    private Messenger f = null;
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: joc.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LogUtil.a("DmsdpServiceImpl", "get a message from server");
            switch (message.what) {
                case 65538:
                    joc.this.b(message.getData().getString("dmsdp_result"));
                    break;
                case 65539:
                default:
                    LogUtil.h("DmsdpServiceImpl", "other handleMessage");
                    break;
                case InputDeviceCompat.SOURCE_TRACKBALL /* 65540 */:
                case 65541:
                    LogUtil.h("DmsdpServiceImpl", "delete success");
                    break;
            }
            joc.this.unbindCameraService();
        }
    };
    private Messenger j = new Messenger(this.d);
    private boolean h = false;
    private SettingCameraCallback m = new SettingCameraCallback() { // from class: joc.4
        @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.SettingCameraCallback
        public void connectSuccess() {
            LogUtil.a("DmsdpServiceImpl", "mPermissionOperationType is:", joc.this.i);
            if ("query".equals(joc.this.i)) {
                joc.this.e();
            } else if ("update".equals(joc.this.i)) {
                joc.this.b();
            } else if ("delete".equals(joc.this.i)) {
                joc.this.c();
            } else {
                LogUtil.h("DmsdpServiceImpl", "mPermissionOperationType is illegal");
            }
            joc.this.i = "";
        }

        @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.SettingCameraCallback
        public void connectFail() {
            LogUtil.c("DmsdpServiceImpl", "connect fail");
        }
    };

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public boolean supportAlgoArch() {
        return true;
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public boolean checkSupportCamera() {
        LogUtil.a("DmsdpServiceImpl", "enter checkCameraCapability");
        if (!cwi.c(jpt.a("DmsdpServiceImpl"), 28)) {
            LogUtil.a("DmsdpServiceImpl", "checkSupportCamera watch not support");
            return false;
        }
        if (a()) {
            unbindCameraService();
            return true;
        }
        try {
            Cursor query = BaseApplication.getContext().getContentResolver().query(b, null, null, null, null);
            boolean z = query != null;
            if (query != null) {
                query.close();
            }
            return z;
        } catch (SQLException unused) {
            LogUtil.b("DmsdpServiceImpl", "checkCameraCapability SQLException");
            return false;
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("DmsdpServiceImpl", "checkCameraCapability IllegalArgumentException");
            return false;
        } catch (SecurityException unused3) {
            LogUtil.b("DmsdpServiceImpl", "checkCameraCapability SecurityException");
            return false;
        }
    }

    private boolean d() {
        int i;
        DeviceInfo a2 = jpt.a("DmsdpServiceImpl");
        if (a2 == null) {
            LogUtil.h("DmsdpServiceImpl", "getCameraAuthorizationStatus getConnectDeviceInfo is null");
            return false;
        }
        Cursor bIB_ = bIB_(a2.getUdidFromDevice());
        if (bIB_ == null) {
            LogUtil.h("DmsdpServiceImpl", "getCameraAuthorizationStatus cursor is null");
            return false;
        }
        if (bIB_.moveToFirst()) {
            i = bIB_.getInt(bIB_.getColumnIndex("status"));
            LogUtil.a("DmsdpServiceImpl", "getCameraAuthorizationStatus status:", Integer.valueOf(i));
        } else {
            i = 0;
        }
        bIB_.close();
        return i == 1;
    }

    private void d(boolean z) {
        DeviceInfo a2 = jpt.a("DmsdpServiceImpl");
        if (a2 == null) {
            LogUtil.h("DmsdpServiceImpl", "updateCameraAuthorization getConnectDeviceInfo is null");
            return;
        }
        String udidFromDevice = a2.getUdidFromDevice();
        Cursor bIB_ = bIB_(udidFromDevice);
        if (bIB_ == null) {
            LogUtil.h("DmsdpServiceImpl", "updateCameraAuthorization cursor is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        contentValues.put("status", Integer.valueOf(z ? 1 : 0));
        if (bIB_.getCount() == 0) {
            contentValues.put("udid", udidFromDevice);
            contentResolver.insert(b, contentValues);
        } else if (bIB_.moveToFirst()) {
            contentResolver.update(b, contentValues, "udid =? ", new String[]{udidFromDevice});
        }
        bIB_.close();
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void deleteCameraAuthorization(DeviceInfo deviceInfo) {
        LogUtil.a("DmsdpServiceImpl", "deleteCameraAuthorization");
        this.i = "delete";
        this.c = deviceInfo;
        if (a()) {
            return;
        }
        this.c = null;
        this.i = "";
        d(deviceInfo);
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("DmsdpServiceImpl", "deleteCameraAuthorization deviceInfo is null");
            return;
        }
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        Cursor bIB_ = bIB_(udidFromDevice);
        if (bIB_ == null) {
            LogUtil.h("DmsdpServiceImpl", "deleteCameraAuthorization cursor is null");
            return;
        }
        if (BaseApplication.getContext().getContentResolver().delete(b, "udid =? ", new String[]{udidFromDevice}) > 0) {
            LogUtil.a("DmsdpServiceImpl", "deleteCameraAuthorization success:", deviceInfo.getDeviceName());
        }
        bIB_.close();
    }

    private Cursor bIB_(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DmsdpServiceImpl", "getCursor deviceUdid isEmpty");
            return null;
        }
        try {
            return BaseApplication.getContext().getContentResolver().query(b, null, "udid =? ", new String[]{str}, null);
        } catch (SQLException unused) {
            LogUtil.b("DmsdpServiceImpl", "getCursor SQLException");
            return null;
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("DmsdpServiceImpl", "getCursor IllegalArgumentException");
            return null;
        } catch (SecurityException unused3) {
            LogUtil.b("DmsdpServiceImpl", "getCursor SecurityException");
            return null;
        }
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void getCameraStatus(CameraAuthStatusCallback cameraAuthStatusCallback) {
        LogUtil.a("DmsdpServiceImpl", "enter getCameraStatus");
        this.i = "query";
        this.e = cameraAuthStatusCallback;
        if (a()) {
            return;
        }
        this.i = "";
        this.e.authStatus(Boolean.valueOf(d()));
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void updateCameraAuthStatus(boolean z) {
        LogUtil.a("DmsdpServiceImpl", "enter updateCameraAuthStatus,isAuthorizatio ", Boolean.valueOf(z));
        this.i = "update";
        this.g = z;
        if (a()) {
            return;
        }
        this.i = "";
        this.g = false;
        d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        DeviceInfo a2 = jpt.a("DmsdpServiceImpl");
        if (a2 == null) {
            LogUtil.h("DmsdpServiceImpl", "queryDmsdp device info is null");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 65538;
        obtain.replyTo = this.j;
        Bundle bundle = new Bundle();
        bundle.putStringArray("projection", new String[]{"status"});
        bundle.putString("selection", "udid=?");
        bundle.putStringArray("selectionArgs", new String[]{a2.getUdidFromDevice()});
        obtain.setData(bundle);
        LogUtil.a("DmsdpServiceImpl", "send DMSDP_CAMERA_REMOTE_CTRL_QUERY");
        try {
            this.f.send(obtain);
        } catch (RemoteException unused) {
            LogUtil.b("DmsdpServiceImpl", "queryDmsdp exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        DeviceInfo a2 = jpt.a("DmsdpServiceImpl");
        if (a2 == null) {
            LogUtil.h("DmsdpServiceImpl", "updateOrInsertDmsdp device info is null");
            return;
        }
        String udidFromDevice = a2.getUdidFromDevice();
        Message obtain = Message.obtain();
        obtain.what = 65541;
        obtain.replyTo = this.j;
        Bundle bundle = new Bundle();
        ContentValues contentValues = new ContentValues();
        contentValues.put("udid", udidFromDevice);
        contentValues.put("status", Integer.valueOf(this.g ? 1 : 0));
        bundle.putParcelable(q0.j, contentValues);
        obtain.setData(bundle);
        LogUtil.a("DmsdpServiceImpl", "send DMSDP_CAMERA_REMOTE_CTRL_INSERT_OR_UPDATE");
        try {
            this.f.send(obtain);
        } catch (RemoteException unused) {
            LogUtil.b("DmsdpServiceImpl", "updateOrInsertDmsdp exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        DeviceInfo deviceInfo = this.c;
        if (deviceInfo == null) {
            LogUtil.h("DmsdpServiceImpl", "deleteDmsdp deviceInfo is null");
            return;
        }
        String udidFromDevice = deviceInfo.getUdidFromDevice();
        Message obtain = Message.obtain();
        obtain.what = InputDeviceCompat.SOURCE_TRACKBALL;
        obtain.replyTo = this.j;
        Bundle bundle = new Bundle();
        bundle.putString("selection", "udid=?");
        bundle.putStringArray("selectionArgs", new String[]{udidFromDevice});
        obtain.setData(bundle);
        LogUtil.a("DmsdpServiceImpl", "send DMSDP_CAMERA_REMOTE_CTRL_DELETE");
        try {
            this.f.send(obtain);
        } catch (RemoteException unused) {
            LogUtil.b("DmsdpServiceImpl", "deleteDmsdp exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        LogUtil.a("DmsdpServiceImpl", "enter parseQueryResult result is", str);
        boolean z = false;
        if (StringUtils.g(str)) {
            this.e.authStatus(false);
            return;
        }
        try {
            JSONObject jSONObject = new JSONArray(str).getJSONObject(0);
            if (jSONObject.has("status")) {
                if (1 == jSONObject.getInt("status")) {
                    z = true;
                }
            }
        } catch (JSONException unused) {
            LogUtil.b("DmsdpServiceImpl", "parseQueryResult JSONException");
        }
        this.e.authStatus(Boolean.valueOf(z));
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraContorlInterface
    public void unbindCameraService() {
        synchronized (this) {
            if (this.f13980a != null && this.h) {
                LogUtil.a("DmsdpServiceImpl", "unbind service");
                BaseApplication.getContext().unbindService(this.f13980a);
                this.h = false;
            }
        }
    }

    private boolean a() {
        boolean z;
        synchronized (this) {
            this.f13980a = new a(this.m);
            Intent intent = new Intent();
            intent.setAction("com.huawei.dmsdp.DMSDP_CAMERA_REMOTE_CTRL");
            intent.setPackage("com.huawei.dmsdp");
            try {
                z = BaseApplication.getContext().bindService(intent, this.f13980a, 1);
            } catch (SecurityException e) {
                LogUtil.b("DmsdpServiceImpl", "bindService exception", LogAnonymous.b((Throwable) e));
                z = false;
            }
            this.h = z;
            LogUtil.a("DmsdpServiceImpl", "bindRemoteService status is:", Boolean.valueOf(z));
        }
        return z;
    }

    class a implements ServiceConnection {
        private SettingCameraCallback b;

        public a(SettingCameraCallback settingCameraCallback) {
            this.b = settingCameraCallback;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            joc.this.f = new Messenger(iBinder);
            this.b.connectSuccess();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            joc.this.unbindCameraService();
            this.b.connectFail();
        }
    }
}

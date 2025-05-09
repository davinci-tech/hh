package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class kba extends EngineLogicBaseManager {
    private static final Set<Integer> b = new HashSet();

    /* renamed from: a, reason: collision with root package name */
    private final SendCallback f14243a;
    private final Map<String, kas> c;

    private kba() {
        super(BaseApplication.getContext());
        this.c = new ConcurrentHashMap();
        this.f14243a = new SendCallback() { // from class: kba.5
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("ScreenshotSynergyManager", "send msg code: ", Integer.valueOf(i), " in ScreenshotSynergyManager");
            }
        };
        b.add(1);
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("ScreenshotSynergyManager", "onReceiveDeviceCommand errorCode: ", Integer.valueOf(i));
        if (deviceInfo == null || spnVar == null) {
            LogUtil.b("ScreenshotSynergyManager", "deviceInfo or message is null in onReceiveDeviceCommand.");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.b("ScreenshotSynergyManager", "deviceIdentify is null in onReceiveDeviceCommand.");
        } else if (spnVar.d() == 2) {
            d(deviceInfo, spnVar.a());
        } else {
            b(deviceInfo, spnVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(com.huawei.health.devicemgr.business.entity.DeviceInfo r4, defpackage.spn r5) {
        /*
            r3 = this;
            byte[] r5 = r5.b()
            java.lang.String r0 = "ScreenshotSynergyManager"
            if (r5 != 0) goto L12
            java.lang.String r4 = "data is null in handleReceiveData."
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
            return
        L12:
            java.lang.String r1 = new java.lang.String
            java.nio.charset.Charset r2 = java.nio.charset.StandardCharsets.UTF_8
            r1.<init>(r5, r2)
            boolean r5 = android.text.TextUtils.isEmpty(r1)
            if (r5 == 0) goto L29
            java.lang.String r4 = "msgStr is null in handleReceiveData."
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r4)
            return
        L29:
            kas r5 = r3.b(r1)
            r1 = 1
            if (r5 != 0) goto L3a
            java.lang.String r2 = "tmpImageProcessParameters is null"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
            goto L57
        L3a:
            java.lang.String r2 = r5.toString()
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r2)
            int r0 = r5.a()
            java.util.Set<java.lang.Integer> r2 = defpackage.kba.b
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r0 = r2.contains(r0)
            if (r0 == 0) goto L57
            r0 = r1
            goto L58
        L57:
            r0 = 2
        L58:
            if (r0 != r1) goto L63
            java.util.Map<java.lang.String, kas> r1 = r3.c
            java.lang.String r4 = r4.getDeviceIdentify()
            r1.put(r4, r5)
        L63:
            java.lang.String r4 = r3.e(r0)
            java.nio.charset.Charset r5 = java.nio.charset.StandardCharsets.UTF_8
            byte[] r4 = r4.getBytes(r5)
            spn$b r5 = new spn$b
            r5.<init>()
            spn$b r4 = r5.c(r4)
            spn r4 = r4.e()
            com.huawei.health.deviceconnect.callback.SendCallback r5 = r3.f14243a
            r3.c(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kba.b(com.huawei.health.devicemgr.business.entity.DeviceInfo, spn):void");
    }

    private kas b(String str) {
        try {
            return (kas) new Gson().fromJson(CommonUtil.z(str), kas.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("ScreenshotSynergyManager", "ParseImageProcessParameters has an JsonSyntaxException");
            return null;
        }
    }

    private boolean d(String str) {
        if (CommonUtil.bv()) {
            return false;
        }
        return SharedPreferenceManager.a(Integer.toString(1000), str, false);
    }

    private String e(int i) {
        if (d("screenshot_negotiation_fail_switch")) {
            LogUtil.a("ScreenshotSynergyManager", "stub negotiation fail.");
            i = 2;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("operateType", 3);
            jSONObject.put("statusCode", i);
        } catch (JSONException unused) {
            LogUtil.b("ScreenshotSynergyManager", "createResponseForParameterNegotiation has an JSONException");
        }
        return jSONObject.toString();
    }

    private void d(final DeviceInfo deviceInfo, final File file) {
        Object[] objArr = new Object[2];
        objArr[0] = "file name: ";
        objArr[1] = file != null ? file.getName() : null;
        LogUtil.a("ScreenshotSynergyManager", objArr);
        if (file == null || !file.exists()) {
            ReleaseLogUtil.c("ScreenshotSynergyManager", "file is not exists in saveScreenshotToAlbum.");
            return;
        }
        if (!file.canRead()) {
            ReleaseLogUtil.c("ScreenshotSynergyManager", "file can't read in saveScreenshotToAlbum.");
            e(file.getName(), false);
        } else if (!CommonUtil.c(file)) {
            ReleaseLogUtil.c("ScreenshotSynergyManager", "filePath is invalid in saveScreenshotToAlbum.");
            e(file.getName(), false);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kax
                @Override // java.lang.Runnable
                public final void run() {
                    kba.this.b(deviceInfo, file);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [kba] */
    /* JADX WARN: Type inference failed for: r8v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v3, types: [kas] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.String] */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void b(DeviceInfo deviceInfo, File file) {
        Throwable th;
        boolean z;
        kas kasVar = this.c.get(deviceInfo.getDeviceIdentify());
        boolean z2 = false;
        if (kasVar == 0) {
            LogUtil.b("ScreenshotSynergyManager", "imageProcessParameters is null in processPhotoAndSaveToAlbum.");
            e(file.getName(), false);
            return;
        }
        Bitmap bMR_ = bMR_(kasVar, file);
        if (kasVar.b() == 1) {
            bMR_ = bMQ_(kasVar, bMR_);
        }
        try {
            if (bMR_ == null) {
                LogUtil.b("ScreenshotSynergyManager", "bitmap is null in processPhotoAndSaveToAlbum.");
                e(file.getName(), false);
                return;
            }
            try {
                if (Build.VERSION.SDK_INT >= 29) {
                    z = bMT_(file.getName(), bMR_);
                } else {
                    z = bMS_(file.getName(), bMR_);
                }
                try {
                    LogUtil.a("ScreenshotSynergyManager", "delete file:", file.getName(), ", res:", Boolean.valueOf(file.delete()), " in processPhotoAndSaveToAlbum.");
                    kasVar = z;
                } catch (IOException unused) {
                    LogUtil.b("ScreenshotSynergyManager", "decodeFile has an exception in processPhotoAndSaveToAlbum.");
                    kasVar = z;
                    file = file.getName();
                    e(file, kasVar);
                }
            } catch (IOException unused2) {
                z = false;
            } catch (Throwable th2) {
                th = th2;
                e(file.getName(), z2);
                throw th;
            }
            file = file.getName();
            e(file, kasVar);
        } catch (Throwable th3) {
            z2 = kasVar;
            th = th3;
        }
    }

    private Bitmap bMQ_(kas kasVar, Bitmap bitmap) {
        LogUtil.a("ScreenshotSynergyManager", "enter cropPicture");
        if (bitmap == null) {
            LogUtil.b("ScreenshotSynergyManager", "bitmap is null in cropPicture");
            return null;
        }
        int k = kasVar.k();
        if (k == 1) {
            return kav.bMI_(kasVar, bitmap);
        }
        if (k == 2) {
            return kav.bMJ_(kasVar, bitmap);
        }
        LogUtil.b("ScreenshotSynergyManager", "watchType is not supported");
        return null;
    }

    private Bitmap bMR_(kas kasVar, File file) {
        LogUtil.a("ScreenshotSynergyManager", "enter parsePicture");
        Bitmap bitmap = null;
        try {
            if (kasVar.a() == 1) {
                String canonicalPath = file.getCanonicalPath();
                LogUtil.a("ScreenshotSynergyManager", "parsePicture imagePath is ", canonicalPath);
                bitmap = BitmapFactory.decodeFile(canonicalPath);
                if (bitmap == null) {
                    LogUtil.b("ScreenshotSynergyManager", "parsePicture decodeFile bitmap is null");
                }
            } else {
                LogUtil.b("ScreenshotSynergyManager", "compressionAlgorithm is not supported");
            }
        } catch (IOException unused) {
            LogUtil.b("ScreenshotSynergyManager", "has an IOException in parsePicture");
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("ScreenshotSynergyManager", "has an IllegalArgumentException in parsePicture");
        } catch (Exception e) {
            LogUtil.b("ScreenshotSynergyManager", "has Exception in parsePicture ", ExceptionUtils.d(e));
        }
        return bitmap;
    }

    private void e(String str, boolean z) {
        int i = 2;
        int i2 = z ? 1 : 2;
        if (d("screenshot_save_photo_fail_switch")) {
            LogUtil.a("ScreenshotSynergyManager", "stub save photo fail.");
        } else {
            i = i2;
        }
        spn e = new spn.b().c(d(str, i).getBytes(StandardCharsets.UTF_8)).e();
        if (d("screenshot_command_lost_switch")) {
            LogUtil.a("ScreenshotSynergyManager", "stub command lost.");
        } else {
            c(e, this.f14243a);
        }
    }

    private String d(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("operateType", 2);
            jSONObject.put(ContentResource.FILE_NAME, str);
            jSONObject.put("statusCode", i);
        } catch (JSONException unused) {
            LogUtil.b("ScreenshotSynergyManager", "createResponseForSaveResult has an JSONException");
        }
        return jSONObject.toString();
    }

    private boolean bMS_(String str, Bitmap bitmap) throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Screenshots");
        if (!file.exists() && !file.mkdir()) {
            LogUtil.b("ScreenshotSynergyManager", "create savePhotoDir failed in saveImageInLegacy.");
            return false;
        }
        File file2 = new File(file, str);
        if (!CommonUtil.c(file2)) {
            LogUtil.b("ScreenshotSynergyManager", "filePath is invalid in saveImageInLegacy.");
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                    LogUtil.b("ScreenshotSynergyManager", "save failed in saveImageInLegacy.");
                    fileOutputStream.close();
                    return false;
                }
                fileOutputStream.close();
                BaseApplication.getContext().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file2)));
                LogUtil.a("ScreenshotSynergyManager", "save picture success, fileName: ", str, " in saveImageInLegacy.");
                return true;
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("ScreenshotSynergyManager", "save image has an exception in saveImageInLegacy.");
            return false;
        }
    }

    private boolean bMT_(String str, Bitmap bitmap) {
        OutputStream openOutputStream;
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + File.separator + "Screenshots");
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", "image/png");
        contentValues.put("is_pending", (Integer) 1);
        ContentResolver contentResolver = BaseApplication.getContext().getContentResolver();
        Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert == null) {
            LogUtil.b("ScreenshotSynergyManager", "imageUri is null in saveImageInQ.");
            contentValues.clear();
            contentValues.put("is_pending", (Integer) 0);
            return false;
        }
        try {
            try {
                openOutputStream = contentResolver.openOutputStream(insert);
                try {
                } catch (Throwable th) {
                    if (openOutputStream != null) {
                        try {
                            openOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused) {
                LogUtil.b("ScreenshotSynergyManager", "save image has an exception in saveImageInQ.");
                if (Build.VERSION.SDK_INT >= 30) {
                    contentResolver.delete(insert, null);
                }
            }
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, openOutputStream)) {
                LogUtil.b("ScreenshotSynergyManager", "save failed in saveImageInQ.");
                contentValues.clear();
                contentValues.put("is_pending", (Integer) 0);
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
                return false;
            }
            if (openOutputStream != null) {
                openOutputStream.close();
            }
            contentValues.clear();
            contentValues.put("is_pending", (Integer) 0);
            contentResolver.update(insert, contentValues, null, null);
            LogUtil.a("ScreenshotSynergyManager", "save picture success, fileName: ", str, " in saveImageInQ.");
            return true;
        } finally {
            contentValues.clear();
            contentValues.put("is_pending", (Integer) 0);
            contentResolver.update(insert, contentValues, null, null);
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.SCREENSHOT_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        if (connectState == null || deviceInfo == null) {
            LogUtil.b("ScreenshotSynergyManager", "connectState or deviceInfo is null in onDeviceConnectionChange.");
            return;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.b("ScreenshotSynergyManager", "deviceIdentify is null in onDeviceConnectionChange.");
        } else if (connectState == ConnectState.DISCONNECTED) {
            this.c.remove(deviceInfo.getDeviceIdentify());
            LogUtil.b("ScreenshotSynergyManager", "remove imageProcessParameters success.");
        }
    }

    public void c(spn spnVar, SendCallback sendCallback) {
        sendComand(spnVar, sendCallback);
    }

    public static kba a() {
        return b.d;
    }

    static class b {
        private static final kba d = new kba();
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.watch.screenshot";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}

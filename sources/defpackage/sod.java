package defpackage;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import defpackage.bmt;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sod {
    private static final Object c = new Object();
    private static volatile sod e;
    private final ReentrantLock d = new ReentrantLock();
    private Timer b = null;

    /* renamed from: a, reason: collision with root package name */
    private CountDownLatch f17176a = null;

    private sod() {
    }

    public sol a(CommonFileInfoParcel commonFileInfoParcel) {
        sol solVar = new sol();
        if (commonFileInfoParcel == null) {
            return solVar;
        }
        solVar.a(commonFileInfoParcel.getFileName());
        solVar.m(commonFileInfoParcel.getFileType());
        solVar.h(commonFileInfoParcel.getSourcePackageName());
        solVar.d(commonFileInfoParcel.getDestinationPackageName());
        return solVar;
    }

    public JSONObject b(FileInfo fileInfo) {
        JSONObject jSONObject = new JSONObject();
        if (fileInfo == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "fileInfo is null");
            return jSONObject;
        }
        try {
            jSONObject.put(ContentResource.FILE_NAME, fileInfo.getFileName());
            jSONObject.put("fileType", fileInfo.getFileType());
        } catch (JSONException unused) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "transfer to JSONObject error");
        }
        return jSONObject;
    }

    public static sod b() {
        sod sodVar;
        synchronized (c) {
            if (e == null) {
                e = new sod();
            }
            sodVar = e;
        }
        return sodVar;
    }

    static bir b(som somVar, byte[] bArr) {
        byte[] g = blq.g(somVar.d());
        byte[] g2 = blq.g(somVar.a());
        byte[] i = blq.i(somVar.e());
        ByteBuffer allocate = ByteBuffer.allocate(g.length + 2 + g2.length + i.length + bArr.length);
        allocate.put((byte) 40);
        allocate.put((byte) 6);
        allocate.put(g);
        allocate.put(g2);
        allocate.put(i);
        allocate.put(bArr);
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate.array());
        return birVar;
    }

    public static UniteDevice b(DeviceInfo deviceInfo) {
        UniteDevice uniteDevice = new UniteDevice();
        if (deviceInfo != null) {
            uniteDevice.setDeviceInfo(deviceInfo);
            uniteDevice.setIdentify(deviceInfo.getDeviceMac());
        }
        return uniteDevice;
    }

    public static boolean a(soo sooVar, byte[] bArr) throws bmk {
        if (sooVar == null) {
            return false;
        }
        bmt.b b = new bmt().b(bArr, 2);
        sooVar.c(b.b((byte) 2, ""));
        sooVar.b(b.a((byte) 3, 30));
        sooVar.d(b.a((byte) 4, 0) == 1);
        int a2 = b.a((byte) 5, 0);
        if (a2 == 0) {
            return false;
        }
        sooVar.h(a2);
        sooVar.a(b.a((byte) 6, 0));
        sooVar.e(b.a((byte) 7, 0));
        sooVar.j(b.a((byte) 8, 0));
        sooVar.d(b.a((byte) 1, 0));
        sooVar.a(b.a((byte) 9, 0) == 1);
        sooVar.e(b.a((byte) 10, 0) == 1);
        return true;
    }

    public static FileInputStream e(sol solVar, long j) {
        if (solVar == null) {
            return null;
        }
        Object[] objArr = new Object[6];
        objArr[0] = "fileInfo.getFilePath() :";
        objArr[1] = solVar.s();
        objArr[2] = "uriId :";
        objArr[3] = Long.valueOf(j);
        objArr[4] = "getFileInputStream, fileInfo.getParcelFileDescriptor() isNull :";
        objArr[5] = Boolean.valueOf(solVar.ejT_() == null);
        LogUtil.c("unite_HwCommonFileMgrProcess", objArr);
        if (solVar.ejT_() != null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "FileDescriptor is :", solVar.ejT_().getFileDescriptor());
            return new FileInputStream(solVar.ejT_().getFileDescriptor());
        }
        return a(solVar.s(), j);
    }

    public static String ejR_(sol solVar, ParcelFileDescriptor parcelFileDescriptor) {
        if (solVar == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "CommonFileInfo is null.");
            return null;
        }
        FileInputStream e2 = e(solVar, solVar.ap());
        if (e2 == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "sha256File error.");
            blv.d(parcelFileDescriptor);
            return null;
        }
        try {
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    byte[] bArr = new byte[1024];
                    int read = e2.read(bArr);
                    if (read != -1) {
                        messageDigest.update(bArr, 0, read);
                        while (true) {
                            int read2 = e2.read(bArr);
                            if (read2 == -1) {
                                break;
                            }
                            messageDigest.update(bArr, 0, read2);
                        }
                    } else {
                        messageDigest.update(bArr, 0, 0);
                    }
                    return blq.d(messageDigest.digest());
                } catch (IOException unused) {
                    LogUtil.e("unite_HwCommonFileMgrProcess", "sha256File IOException");
                    try {
                        e2.close();
                    } catch (IOException unused2) {
                        LogUtil.e("unite_HwCommonFileMgrProcess", "close stream IoException");
                    }
                    blv.d(parcelFileDescriptor);
                    return "";
                }
            } catch (NoSuchAlgorithmException unused3) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "MessageDigest not support");
                try {
                    e2.close();
                } catch (IOException unused4) {
                    LogUtil.e("unite_HwCommonFileMgrProcess", "close stream IoException");
                }
                blv.d(parcelFileDescriptor);
                return "";
            }
        } finally {
            try {
                e2.close();
            } catch (IOException unused5) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "close stream IoException");
            }
            blv.d(parcelFileDescriptor);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v9 */
    public static int ejQ_(File file, long j, ParcelFileDescriptor parcelFileDescriptor) {
        int i;
        Object[] objArr;
        Object[] objArr2;
        int i2 = 0;
        if (file == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "getFileSizeByFilePath file is null");
            return 0;
        }
        if (file.exists()) {
            FileInputStream a2 = a(file.getPath(), j);
            if (a2 == null) {
                LogUtil.a("unite_HwCommonFileMgrProcess", "getFileSizeByFilePath, getFileSize error.");
                blv.d(parcelFileDescriptor);
                return 0;
            }
            try {
                try {
                    i = a2.available();
                    try {
                        long size = a2.getChannel().size();
                        LogUtil.c("unite_HwCommonFileMgrProcess", "fileInputStream getChannel( size is: ", Long.valueOf(size));
                        int i3 = size > 2147483647L ? -1 : (int) size;
                        try {
                            a2.close();
                            objArr2 = a2;
                        } catch (IOException unused) {
                            Object[] objArr3 = {"closeQuietly occurIOException."};
                            LogUtil.e("unite_HwCommonFileMgrProcess", objArr3);
                            objArr2 = objArr3;
                        }
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException unused2) {
                                objArr2 = new Object[]{"closeQuietly occurIOException."};
                                LogUtil.e("unite_HwCommonFileMgrProcess", objArr2);
                            }
                        }
                        i2 = i3;
                        a2 = objArr2;
                    } catch (IOException unused3) {
                        LogUtil.e("unite_HwCommonFileMgrProcess", "getFileSizeByFilePath, IOException");
                        try {
                            a2.close();
                            objArr = a2;
                        } catch (IOException unused4) {
                            Object[] objArr4 = {"closeQuietly occurIOException."};
                            LogUtil.e("unite_HwCommonFileMgrProcess", objArr4);
                            objArr = objArr4;
                        }
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException unused5) {
                                objArr = new Object[]{"closeQuietly occurIOException."};
                                LogUtil.e("unite_HwCommonFileMgrProcess", objArr);
                            }
                        }
                        i2 = i;
                        a2 = objArr;
                        return i2;
                    }
                } finally {
                }
            } catch (IOException unused6) {
                i = 0;
            }
        }
        return i2;
    }

    private static FileInputStream a(String str, long j) {
        FileInputStream fileInputStream;
        if (!sor.b(str)) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "getFileInputStreamByUriId checkFilepath error");
            return null;
        }
        try {
            if (j == -1) {
                String d = bli.d(str);
                if (d != null) {
                    return new FileInputStream(d);
                }
                return null;
            }
            Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, j);
            InputStream openInputStream = BaseApplication.e().getContentResolver().openInputStream(withAppendedId);
            if (openInputStream instanceof FileInputStream) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "transform type success ");
                fileInputStream = (FileInputStream) openInputStream;
            } else {
                LogUtil.e("unite_HwCommonFileMgrProcess", "transform type fail ");
                ParcelFileDescriptor openFileDescriptor = BaseApplication.e().getContentResolver().openFileDescriptor(withAppendedId, "r");
                snz.a().ejO_(openFileDescriptor);
                if (openFileDescriptor == null) {
                    return null;
                }
                fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
            }
            return fileInputStream;
        } catch (FileNotFoundException e2) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "getFileInputStreamByUriId FileNotFoundException", bll.a(e2));
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object[]] */
    public static int ejP_(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) {
        int i;
        int i2;
        if (parcelFileDescriptor == null) {
            return 0;
        }
        FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
        try {
            try {
                i = fileInputStream.available();
                try {
                    long size = fileInputStream.getChannel().size();
                    LogUtil.c("unite_HwCommonFileMgrProcess", "fileInputStream getChannel( size is: ", Long.valueOf(size));
                    i2 = size > 2147483647L ? -1 : (int) size;
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                        LogUtil.e("unite_HwCommonFileMgrProcess", "closeQuietly occurIOException.");
                    }
                    if (parcelFileDescriptor2 != 0) {
                        try {
                            parcelFileDescriptor2.close();
                        } catch (IOException unused2) {
                            parcelFileDescriptor2 = new Object[]{"closeQuietly occurIOException."};
                            LogUtil.e("unite_HwCommonFileMgrProcess", parcelFileDescriptor2);
                        }
                    }
                } catch (IOException unused3) {
                    LogUtil.e("unite_HwCommonFileMgrProcess", "getFileSizeByFileDescriptor, IOException");
                    try {
                        fileInputStream.close();
                    } catch (IOException unused4) {
                        LogUtil.e("unite_HwCommonFileMgrProcess", "closeQuietly occurIOException.");
                    }
                    if (parcelFileDescriptor2 != 0) {
                        try {
                            parcelFileDescriptor2.close();
                        } catch (IOException unused5) {
                            LogUtil.e("unite_HwCommonFileMgrProcess", "closeQuietly occurIOException.");
                        }
                    }
                    i2 = i;
                    return i2;
                }
            } finally {
            }
        } catch (IOException unused6) {
            i = 0;
        }
        return i2;
    }

    public static String e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "getJsonString. please check.");
            return null;
        }
    }

    public void a(sol solVar, snz snzVar, int i) {
        if (solVar == null || snzVar == null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "param is null.");
            return;
        }
        if (solVar.e() == 0) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "left file is too small, no change to wifi.");
            return;
        }
        if (solVar.ag() == 1) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "this task wait sport watch ota, alwasy send file by bt.");
            return;
        }
        if (solVar.u() == -1) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "normal watch no change wifi.");
            return;
        }
        if (!HwWifiP2pTransferManager.d().l()) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "wifi is not open, please check!");
            return;
        }
        try {
            try {
                this.d.tryLock(1L, TimeUnit.SECONDS);
                if (this.b != null) {
                    LogUtil.c("unite_HwCommonFileMgrProcess", "wifi already count time, no switch this time. wait time switch wifi");
                    return;
                }
            } catch (InterruptedException e2) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "trySwitchBtToWifi ", e2.getMessage());
            }
            this.d.unlock();
            int i2 = 3;
            if (solVar.u() == 7) {
                int v = solVar.v() / Constants.GIF_SIZE_UPPER_LIMIT;
                LogUtil.c("unite_HwCommonFileMgrProcess", "fileSize / MIN_FILE_SIZE_FOR_TIMERS ", Integer.valueOf(v));
                if (1 <= v) {
                    i2 = 3 + v;
                }
            }
            this.b = new Timer("switchBtToWifi");
            int i3 = i != 1 ? 0 : -1;
            if (i > 1) {
                i3 = i / 2;
            }
            d(i3, i2, snzVar);
        } finally {
            this.d.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i, final int i2, final snz snzVar) {
        LogUtil.c("unite_HwCommonFileMgrProcess", "TrySwitchWifiTask time : ", Integer.valueOf(i), " maxTime : ", Integer.valueOf(i2));
        try {
            try {
                this.d.tryLock(1L, TimeUnit.SECONDS);
                Timer timer = this.b;
                if (timer == null) {
                    LogUtil.a("unite_HwCommonFileMgrProcess", "delayTask timer is null. please check.");
                } else {
                    long j = ((i * 2) + 1) * 60000;
                    if (j <= 0) {
                        j = 0;
                    }
                    timer.schedule(new TimerTask() { // from class: sod.5
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (i < i2) {
                                if (HwWifiP2pTransferManager.d().n() != 1) {
                                    snzVar.d();
                                }
                                sod.this.d(i + 1, i2, snzVar);
                                return;
                            }
                            sod.this.c();
                        }
                    }, j);
                }
            } catch (InterruptedException e2) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "delayTask ", e2.getMessage());
            }
        } finally {
            this.d.unlock();
        }
    }

    public boolean c() {
        boolean z = true;
        try {
            try {
                boolean tryLock = this.d.tryLock(1L, TimeUnit.SECONDS);
                Timer timer = this.b;
                if (timer != null) {
                    LogUtil.c("unite_HwCommonFileMgrProcess", "closeTrySwitchWifiTask : ", Boolean.valueOf(tryLock));
                    timer.cancel();
                    this.b = null;
                } else {
                    z = false;
                }
                this.d.unlock();
                return z;
            } catch (InterruptedException e2) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "closeTrySwitchWifiTask exception : ", e2.getMessage());
                this.d.unlock();
                return false;
            }
        } catch (Throwable th) {
            this.d.unlock();
            throw th;
        }
    }

    public boolean d(sol solVar) {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        if (solVar == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "mCommonWifiFileInfo is null");
            return false;
        }
        long v = solVar.v();
        long t = v - solVar.t();
        LogUtil.c("unite_HwCommonFileMgrProcess", "totalSize:", Long.valueOf(v), "translate file remainingSize:", Long.valueOf(t));
        if (v <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE || t < PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) {
            return false;
        }
        boolean b = HwWifiP2pTransferManager.d().b(solVar.u(), solVar.i());
        LogUtil.c("unite_HwCommonFileMgrProcess", "no support wifi, do not switch to wifi. : ", Boolean.valueOf(b));
        return HwWifiP2pTransferManager.d().l() && !HwWifiP2pTransferManager.d().o() && b;
    }

    public void b(int i, int i2, int i3, int i4) {
        if (bky.i()) {
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("transferData psnID ");
        sb.append(i);
        sb.append("length = ");
        sb.append(i2);
        sb.append("ota_offset = ");
        sb.append(i3);
        sb.append("sended_length = ");
        sb.append(i4);
        LogUtil.c("unite_HwCommonFileMgrProcess", sb.toString());
    }

    public boolean e(IResultAIDLCallback iResultAIDLCallback, FileInfo fileInfo) {
        String str;
        if (iResultAIDLCallback == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "start To Device TransFile callback is null");
            return false;
        }
        try {
            if (fileInfo == null) {
                LogUtil.a("unite_HwCommonFileMgrProcess", "start To Device TransFile fileInfo is null");
                iResultAIDLCallback.onTransferFailed(20000, "");
                return false;
            }
            if (TextUtils.isEmpty(fileInfo.getFilePath()) && fileInfo.getFileDescriptor() == null) {
                LogUtil.a("unite_HwCommonFileMgrProcess", "start to Device Transfile filePath is null");
                iResultAIDLCallback.onTransferFailed(20006, "");
                return false;
            }
            if (!TextUtils.isEmpty(fileInfo.getFilePath())) {
                try {
                    str = new File(fileInfo.getFilePath()).getCanonicalPath();
                } catch (IOException unused) {
                    LogUtil.e("unite_HwCommonFileMgrProcess", "startTransfer IOException");
                    str = null;
                }
                if (TextUtils.isEmpty(str)) {
                    LogUtil.a("unite_HwCommonFileMgrProcess", "safePath is not exist");
                    iResultAIDLCallback.onTransferFailed(20007, "");
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "checkAvailability Exception", bll.a(e2));
            return false;
        }
    }

    public static void e(UniteDevice uniteDevice) {
        LogUtil.c("unite_HwCommonFileMgrProcess", "tryCloseWifi");
        if (uniteDevice == null) {
            return;
        }
        soz i = HwWifiP2pTransferManager.d().i();
        String identify = uniteDevice.getIdentify();
        if (TextUtils.isEmpty(identify)) {
            return;
        }
        if (i != null) {
            UniteDevice e2 = i.e();
            if (e2 == null || identify.equalsIgnoreCase(e2.getIdentify())) {
                LogUtil.c("unite_HwCommonFileMgrProcess", "disconnect target device, close wifi.");
                spc.d().b();
                return;
            }
            return;
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "no wifi task, do nothing.");
    }

    public static void a(sol solVar, ConcurrentHashMap<String, sol> concurrentHashMap) {
        if (solVar == null || concurrentHashMap == null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "adapterFileId param is wrong.");
            return;
        }
        String m = solVar.m();
        int u = solVar.u();
        for (Map.Entry<String, sol> entry : concurrentHashMap.entrySet()) {
            if (TextUtils.equals(entry.getValue().m(), m) && entry.getValue().u() == u) {
                int l = entry.getValue().l();
                LogUtil.c("unite_HwCommonFileMgrProcess", "stopTransferFile fileName :", entry.getValue().m(), "fileId : ", Integer.valueOf(l));
                solVar.c(l);
                return;
            }
        }
    }

    public son e(int i, int i2, int i3, List<Integer> list, DeviceInfo deviceInfo) {
        son sonVar = new son();
        sonVar.d(i);
        sonVar.a(i2);
        sonVar.c(i3);
        sonVar.a(list);
        sonVar.a(deviceInfo);
        return sonVar;
    }

    public void d(sol solVar, ConcurrentHashMap<String, sol> concurrentHashMap) {
        if (solVar == null || concurrentHashMap == null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "dealWatchFaceTaskHang param is wrong.");
            return;
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "dealWatchFaceTaskHang, getFileId :", Integer.valueOf(solVar.l()));
        try {
            if (solVar.u() == 1) {
                LogUtil.c("unite_HwCommonFileMgrProcess", "dealWatchFaceTaskHang, watch face type");
                return;
            }
            for (sol solVar2 : concurrentHashMap.values()) {
                if (solVar2.u() == 1 && solVar2.n() != null) {
                    LogUtil.c("unite_HwCommonFileMgrProcess", "dealWatchFaceTaskHang, watch face task HANG");
                    solVar2.n().onFileTransferState(142000, "");
                    return;
                }
                LogUtil.a("unite_HwCommonFileMgrProcess", "dealWatchFaceTaskHang, fileCallback is null or type error");
            }
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "dealWatchFaceTaskHang, Exception", bll.a(e2));
        }
    }

    public void c(sol solVar, sol solVar2) {
        if (solVar == null || solVar2 == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "5.40.2 param is null.");
            return;
        }
        solVar.ejU_(solVar2.ejT_());
        solVar.g(solVar2.ai());
        solVar.e(solVar2.s());
        solVar.i(solVar2.v());
        solVar.b(solVar2.n());
        solVar.e(solVar2.ap());
        solVar.c(solVar2.j());
        solVar.f(solVar2.ah());
        solVar.b(solVar2.h());
        solVar.j(solVar2.ac());
        LogUtil.c("unite_HwCommonFileMgrProcess", "commonFileInfo.name: ", solVar.m());
    }

    public boolean a(sol solVar, int i) {
        if (i != 1021) {
            return false;
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "wifiSendBtClosed : ", Integer.valueOf(i));
        if (solVar == null) {
            return true;
        }
        iyv.c("WifiP2PTransfer", "Close bluetooth during wifip2p file transfer.");
        a(solVar.n(), 141001, "");
        return true;
    }

    public void a(IResultAIDLCallback iResultAIDLCallback, int i, String str) {
        LogUtil.c("unite_HwCommonFileMgrProcess", "reportUiFail code : ", Integer.valueOf(i), " errorMessage : ", str);
        if (iResultAIDLCallback != null) {
            try {
                iResultAIDLCallback.onTransferFailed(i, "");
                return;
            } catch (Exception e2) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "reportUiFail Exception", bll.a(e2));
                return;
            }
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "no callback");
    }

    public boolean e(boolean z, sol solVar, snz snzVar) {
        if (z) {
            return true;
        }
        sol c2 = TransferFileQueueManager.d().c("wifi_change_bt");
        if (c2 == null || c2.al() == 1) {
            e(c2);
            TransferFileQueueManager.d().b("wifi_change_bt");
            return false;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.f17176a = countDownLatch;
        try {
            a();
        } catch (InterruptedException unused) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "setCancelDownloadFlagDelay InterruptedException");
        }
        if (countDownLatch.await(7L, TimeUnit.SECONDS) && solVar != null && snzVar != null) {
            this.f17176a = null;
            if (solVar.i() != null && solVar.i().getDeviceInfo() != null) {
                LogUtil.c("unite_HwCommonFileMgrProcess", "wifi change bt, but user stop transfer.");
                TransferFileQueueManager.d().h("isWaitWatchSuccess");
                snzVar.a(100000, solVar.i().getDeviceInfo(), solVar);
            } else {
                LogUtil.c("unite_HwCommonFileMgrProcess", "param has null, please check.");
            }
            return false;
        }
        countDownLatch.countDown();
        this.f17176a = null;
        return true;
    }

    private void a() {
        ThreadPoolManager a2 = ThreadPoolManager.a(1, 1);
        a2.execute(new Runnable() { // from class: sof
            @Override // java.lang.Runnable
            public final void run() {
                sod.this.e();
            }
        });
        a2.shutdown();
    }

    /* synthetic */ void e() {
        while (this.f17176a != null) {
            snz.a().d("bluetooth");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException unused) {
                LogUtil.e("unite_HwCommonFileMgrProcess", "post wait task exception.");
            }
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "report wifi change bt 0 process finish.");
    }

    private void e(sol solVar) {
        if (solVar == null || solVar.d() == null) {
            return;
        }
        try {
            if (solVar.u() == 1) {
                solVar.d().onResponse(20003, String.valueOf(solVar.m()));
                LogUtil.c("unite_HwCommonFileMgrProcess", "entry  watchType callback , fileName: ", solVar.m());
            } else {
                solVar.d().onResponse(20003, "");
            }
        } catch (Exception e2) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "reportCancel Exception", bll.a(e2));
        }
    }

    public void d() {
        CountDownLatch countDownLatch = this.f17176a;
        if (countDownLatch != null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "wifi change to bt, stop change.");
            countDownLatch.countDown();
        } else {
            LogUtil.c("unite_HwCommonFileMgrProcess", "normal stop.");
        }
    }

    protected boolean b(sol solVar) {
        Collection<UniteDevice> values = bgl.c().getDeviceList().values();
        if (values.isEmpty()) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "isTargetDeviceConnected try get device wrong, please check.");
            return false;
        }
        UniteDevice i = solVar.i();
        if (i == null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "isTargetDeviceConnected target device is wrong.");
            return false;
        }
        String identify = i.getIdentify();
        if (TextUtils.isEmpty(identify)) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "isTargetDeviceConnected target device identify is wrong.");
            return false;
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "isTargetDeviceConnected values size : ", Integer.valueOf(values.size()));
        for (UniteDevice uniteDevice : values) {
            if (identify.equalsIgnoreCase(uniteDevice.getIdentify())) {
                return uniteDevice.getDeviceInfo().getDeviceConnectState() == 2;
            }
        }
        return false;
    }

    public void ejS_(Message message, ConcurrentHashMap<String, sol> concurrentHashMap) {
        if (message == null || concurrentHashMap == null) {
            return;
        }
        LogUtil.c("unite_HwCommonFileMgrProcess", "5.54.7 timeout, close wifi.");
        iyv.c("WifiP2PTransfer", "Device replies 5.54.7 reach timeout");
        int i = message.arg1;
        sol solVar = concurrentHashMap.get(String.valueOf(i));
        if (solVar != null) {
            LogUtil.c("unite_HwCommonFileMgrProcess", "7 timeout, switch bt, send file.");
            snz.a().d(GLMapStaticValue.MAP_PARAMETERNAME_SCENIC, "7 timeout", solVar, i);
        } else {
            snz.a().d(0, i);
            HwWifiP2pTransferManager.d().e();
            soy.b(1);
        }
    }

    public void d(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null) {
            LogUtil.a("unite_HwCommonFileMgrProcess", "dealHash device is null.");
            return;
        }
        if (bArr == null || bArr.length < 2) {
            return;
        }
        blt.d("unite_HwCommonFileMgrProcess", bArr, "5.40.3 handleRequestHash :");
        try {
            bmt.b b = new bmt().b(bArr, 2);
            snz.a().c(b.a((byte) 1, 0), b.a((byte) 2, 0), deviceInfo, b.a((byte) 4, 0));
        } catch (bmk unused) {
            LogUtil.e("unite_HwCommonFileMgrProcess", "handleRequestHash TlvException");
        }
    }
}

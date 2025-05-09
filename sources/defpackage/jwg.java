package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwdevice.phoneprocess.mgr.httpproxy.ResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jwg {
    private static String c() {
        try {
            File filesDir = BaseApplication.getContext().getFilesDir();
            if (filesDir != null) {
                return filesDir.getCanonicalPath();
            }
        } catch (IOException unused) {
            LogUtil.b("HttpProxyFileTransferHelper", "getSourcePath catch IOException");
        }
        return "";
    }

    private static SecureRandom e() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            health.compact.a.LogUtil.e("HttpProxyFileTransferHelper", "getRandom exception : ", bll.a(e));
            return null;
        }
    }

    private static String d() {
        SecureRandom e = e();
        if (e == null) {
            return "";
        }
        int[] iArr = new int[6];
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int nextInt = e.nextInt(10);
            iArr[i] = nextInt;
            sb.append(nextInt);
        }
        return sb.toString();
    }

    private static FileOutputStream a(String str) throws IOException, IllegalArgumentException {
        File file = new File(str);
        if (file.exists() && !file.delete()) {
            LogUtil.a("HttpProxyFileTransferHelper", "writeToFile: file delete failed.");
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            LogUtil.a("HttpProxyFileTransferHelper", "writeToFile: failed to create file's parent.");
        }
        return FileUtils.openOutputStream(file);
    }

    private static boolean e(String str, byte[] bArr) {
        if (bArr != null) {
            try {
                FileOutputStream a2 = a(str);
                a2.write(new String(bArr, "utf-8").getBytes("utf-8"));
                a2.flush();
                return true;
            } catch (IOException unused) {
                LogUtil.a("HttpProxyFileTransferHelper", "writeHttpProxyFiles IOException");
            } catch (IllegalArgumentException unused2) {
                LogUtil.b("HttpProxyFileTransferHelper", "IllegalArgumentException: failed to write the file.");
                return false;
            }
        }
        return false;
    }

    public static void c(long j, byte[] bArr, ResultCallback resultCallback) {
        String d = d();
        if (StringUtils.g(d)) {
            LogUtil.h("HttpProxyFileTransferHelper", "JSONException occurs when generating random number");
            resultCallback.onResult("create cloud response file failed");
            return;
        }
        String str = "httpProxy_" + j + "_" + d + WatchFaceConstant.BIN_SUFFIX;
        if (!e(c() + File.separator + 7 + File.separator + str, bArr)) {
            LogUtil.h("HttpProxyFileTransferHelper", "Failed to write cloud response to file");
            resultCallback.onResult("create cloud response file failed");
        } else {
            resultCallback.onResult(str);
        }
    }

    public static void b(String str, DeviceInfo deviceInfo, final ResultCallback resultCallback) {
        jqj jqjVar = new jqj();
        jqjVar.a(str);
        jqjVar.d(10);
        jqjVar.j("hw.wearable.httpProxy");
        jqjVar.a(true);
        jqjVar.h("hw.unitedevice.httpProxy");
        jqjVar.g("UniteDeviceManagement");
        jqjVar.d("hw.wearable.httpProxy");
        jqjVar.e(OfflineMapWearEngineManager.WEAR_FINGERPRINT);
        jqjVar.b(str);
        jqjVar.c(deviceInfo.getDeviceIdentify());
        jyp.c().b(jqjVar, new ITransferSleepAndDFXFileCallback.Stub() { // from class: jwg.3
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, String str2, String str3) {
                ReleaseLogUtil.b("R_HttpProxyFileTransferHelper", "Get body file from device succeed");
                try {
                    String str4 = new String(Files.readAllBytes(Paths.get(str2, new String[0])));
                    LogUtil.a("HttpProxyFileTransferHelper", "Receive body content from device: ", str4);
                    ResultCallback.this.onResult(str4);
                } catch (IOException unused) {
                    LogUtil.b("HttpProxyFileTransferHelper", "failed to read body content from body file");
                    ResultCallback.this.onResult("file transfer failed");
                }
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("R_HttpProxyFileTransferHelper", "Obtain body file through 5.44 failed, errorCode: ", Integer.valueOf(i), ", errorMessage: ", str2);
                ResultCallback.this.onResult("file transfer failed");
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str2) {
                LogUtil.a("HttpProxyFileTransferHelper", "mStateCallback, onProgress progress is ", Integer.valueOf(i));
            }
        });
    }
}

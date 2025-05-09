package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import com.huawei.openalliance.ad.db.bean.ContentResource;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kbl {

    /* renamed from: a, reason: collision with root package name */
    private static kbl f14254a;
    private static final Object e = new Object();

    private kbl() {
    }

    public static kbl e() {
        kbl kblVar;
        synchronized (e) {
            if (f14254a == null) {
                f14254a = new kbl();
            }
            kblVar = f14254a;
        }
        return kblVar;
    }

    public void e(String str, final IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("HwGetFileManager", "enter captureLog");
        if (iBaseCommonCallback == null) {
            LogUtil.h("HwGetFileManager", "callback is null");
            return;
        }
        final DeviceInfo a2 = kbi.a(str);
        if (a2 == null) {
            kbi.d(iBaseCommonCallback, 150, "no device connected");
        } else if (kbi.b(a2.getDeviceIdentify())) {
            d(a2, 0, iBaseCommonCallback, true);
        } else {
            jvl.b().b(d(a2), new ITransferSleepAndDFXFileCallback.Stub() { // from class: kbl.3
                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onSuccess(int i, String str2, String str3) {
                    LogUtil.a("HwGetFileManager", "captureLog successCode:", Integer.valueOf(i));
                    kbl.this.d(a2, 0, iBaseCommonCallback);
                }

                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Dfx_HwGetFileManager", "captureLog errorCode:", Integer.valueOf(i), ", errorMessage = ", str2);
                    kbl.this.d(a2, 0, iBaseCommonCallback);
                }

                @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
                public void onProgress(int i, String str2) {
                    LogUtil.a("HwGetFileManager", "captureLog progress:", Integer.valueOf(i), ", progressDesc", str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo, int i, IBaseCommonCallback iBaseCommonCallback) {
        d(deviceInfo, i, iBaseCommonCallback, false);
    }

    private void d(DeviceInfo deviceInfo, int i, IBaseCommonCallback iBaseCommonCallback, boolean z) {
        String e2;
        try {
            if (z) {
                e2 = kbi.b(deviceInfo);
            } else {
                e2 = kbi.e(deviceInfo);
            }
            LogUtil.a("HwGetFileManager", "callBackResult filePath:", e2);
            if (TextUtils.isEmpty(e2)) {
                kbi.d(iBaseCommonCallback, 1, "there is no log file");
                return;
            }
            File file = new File(e2);
            if (!file.exists()) {
                kbi.d(iBaseCommonCallback, 1, "there is no log file");
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ContentResource.FILE_NAME, file.getName());
            jSONObject.put("fileSize", file.length());
            kbi.d(iBaseCommonCallback, i, jSONObject.toString());
        } catch (JSONException unused) {
            ReleaseLogUtil.c("Dfx_HwGetFileManager", "callBackResult JSONException");
        }
    }

    public void a(String str, final String str2, final IBaseCommonCallback iBaseCommonCallback) {
        final DeviceInfo a2;
        LogUtil.a("HwGetFileManager", "enter uploadLog, fileName:", str2, ", uuid:", str);
        a(kbi.d);
        if (iBaseCommonCallback == null) {
            LogUtil.h("HwGetFileManager", "callback is null");
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            LogUtil.a("HwGetFileManager", "fileName is empty");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            a2 = kbi.e(str2);
        } else {
            a2 = kbi.a(str);
        }
        Object[] objArr = new Object[2];
        objArr[0] = "uploadLog deviceInfo:";
        objArr[1] = a2 == null ? "is null" : a2.toString();
        LogUtil.a("HwGetFileManager", objArr);
        ThreadPoolManager.d().execute(new Runnable() { // from class: kbl.2
            @Override // java.lang.Runnable
            public void run() {
                if (CommonUtil.bv()) {
                    kbh.a().a(a2, str2, iBaseCommonCallback);
                } else {
                    kbg.e().c(a2, str2, iBaseCommonCallback);
                }
            }
        });
    }

    public void c(String str, IBaseCommonCallback iBaseCommonCallback) {
        File[] listFiles;
        LogUtil.a("HwGetFileManager", "removeLog");
        if (TextUtils.isEmpty(str) || iBaseCommonCallback == null) {
            LogUtil.a("HwGetFileManager", "can not remove log");
            return;
        }
        File file = new File(kbi.d);
        if (!file.exists() || (listFiles = file.listFiles()) == null || file.length() == 0) {
            return;
        }
        int length = listFiles.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            File file2 = listFiles[i];
            if (!file2.getName().equals(str)) {
                i++;
            } else if (file2.delete()) {
                kbi.d(iBaseCommonCallback, 0, "delete success");
                return;
            }
        }
        kbi.d(iBaseCommonCallback, 1, "delete failed");
    }

    private TransferFileInfo d(DeviceInfo deviceInfo) {
        TransferFileInfo transferFileInfo = new TransferFileInfo();
        transferFileInfo.setIsFromAbout(1);
        transferFileInfo.setIsUploadAppLog(1);
        transferFileInfo.setType(0);
        transferFileInfo.setRecordId(new ArrayList(16));
        transferFileInfo.setLevel(0);
        transferFileInfo.setPriority(1);
        transferFileInfo.setSuspend(0);
        transferFileInfo.setTaskType(2);
        int productType = deviceInfo.getProductType();
        transferFileInfo.setDeviceMac(deviceInfo.getSecurityDeviceId());
        transferFileInfo.setDeviceSn(deviceInfo.getDeviceIdentify());
        transferFileInfo.setDeviceModel(deviceInfo.getDeviceModel());
        transferFileInfo.setDeviceType(productType);
        transferFileInfo.setDeviceVersion(deviceInfo.getSoftVersion());
        transferFileInfo.setUdidFromDevice(deviceInfo.getUdidFromDevice());
        if (CommonUtil.bv()) {
            transferFileInfo.setSelectedFlag(1);
        } else {
            transferFileInfo.setSelectedFlag(0);
        }
        return transferFileInfo;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HwGetFileManager", "deleteTenDayFile(), filePath is null");
            return;
        }
        ArrayList<File> d = jsf.d(str);
        if (d == null) {
            LogUtil.a("HwGetFileManager", "deleteTenDayFile(), not have ten days log");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HwDrmConstant.TIME_FORMAT, Locale.ENGLISH);
        String format = simpleDateFormat.format(new Date());
        LogUtil.c("HwGetFileManager", "deleteTenDayFile, newDate = ", format);
        String str2 = "";
        for (int i = 0; i < d.size(); i++) {
            File file = d.get(i);
            String[] split = file.getName().split("_");
            if (split.length > 4) {
                str2 = split[3];
            } else {
                LogUtil.c("HwGetFileManager", "deleteTenDayFile(), file name contains no date.");
            }
            try {
                long time = (simpleDateFormat.parse(format).getTime() - simpleDateFormat.parse(str2).getTime()) / 86400000;
                if (time > 10) {
                    LogUtil.a("HwGetFileManager", "deleteTenDayFile, delete days = ", Long.valueOf(time), " file = ", file.getName());
                    LogUtil.c("HwGetFileManager", "deleteTenDayFile isDelete: ", Boolean.valueOf(file.delete()));
                }
            } catch (ParseException e2) {
                LogUtil.b("HwGetFileManager", "PackageManager.ParseException is:" + e2.getMessage());
            }
        }
    }
}

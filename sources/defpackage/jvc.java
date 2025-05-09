package defpackage;

import android.text.TextUtils;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.network.embedded.b6;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.CommonApi;
import com.huawei.networkclient.ProgressListener;
import com.huawei.operation.OpAnalyticsConstants;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.IoUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class jvc {
    public static void b(List<String> list, IBaseResponseCallback iBaseResponseCallback, jvb jvbVar) {
        if (koq.b(list)) {
            LogUtil.h("HwEphemerisNet", "requestUrl return");
            e(iBaseResponseCallback, 1);
            return;
        }
        ArrayList arrayList = new ArrayList(0);
        for (String str : list) {
            try {
                CommonApi commonApi = (CommonApi) lqi.d().b(CommonApi.class);
                HashMap hashMap = new HashMap(16);
                hashMap.put(b6.a.b, "HealthApp");
                hashMap.put("traceId", UUID.randomUUID().toString());
                Response<String> execute = commonApi.commonGet(str, hashMap).execute();
                if (execute != null && c(execute, arrayList, null, iBaseResponseCallback)) {
                    return;
                }
            } catch (IOException unused) {
                LogUtil.b("R_Stress_HwEphemerisNet", "requestUrl net or Server is error.");
                e(iBaseResponseCallback, 2);
            } catch (IllegalArgumentException unused2) {
                LogUtil.b("HwEphemerisNet", "requestUtl find IllegalArgumentException");
                e(iBaseResponseCallback, 2);
            }
        }
        c(arrayList, iBaseResponseCallback, jvbVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwEphemerisNet", "responseCallback callback is null");
        } else {
            iBaseResponseCallback.d(i, null);
        }
    }

    private static void c(List<juz> list, IBaseResponseCallback iBaseResponseCallback, jvb jvbVar) {
        if (list.isEmpty()) {
            LogUtil.h("HwEphemerisNet", "download REQUEST_TAG_URL_ILLEGAL");
            e(iBaseResponseCallback, 1);
            return;
        }
        File filesDir = BaseApplication.getContext().getFilesDir();
        for (juz juzVar : list) {
            String d = d(juzVar.e());
            String str = knl.a(jvbVar.c().getDeviceIdentify()) + d;
            LogUtil.a("HwEphemerisNet", "file name : ", str);
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("HwEphemerisNet", "net data exception :", juzVar.e());
                e(iBaseResponseCallback, 4);
            }
            jvbVar.b(d);
            File file = new File(filesDir, CommonUtil.c(str));
            if (file.exists()) {
                if (b(file, juzVar.b(), juzVar.d())) {
                    if (list.get(list.size() - 1).equals(juzVar)) {
                        e(iBaseResponseCallback, 0);
                    }
                } else {
                    LogUtil.a("HwEphemerisNet", "download delete file:", Boolean.valueOf(file.delete()));
                }
            }
            a(juzVar, list, file, iBaseResponseCallback);
        }
    }

    private static void a(final juz juzVar, final List<juz> list, File file, final IBaseResponseCallback iBaseResponseCallback) {
        lqi.d().d(new lqg(juzVar.e(), null, file, new ProgressListener<File>() { // from class: jvc.2
            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file2) {
                sqc.c(file2.getPath(), "S2", 0);
                if (((juz) list.get(r3.size() - 1)).equals(juzVar)) {
                    jvc.e(iBaseResponseCallback, 0);
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                LogUtil.c("HwEphemerisNet", "onProgress, handleBytes: ", Long.valueOf(j), ", contentLength: ", Long.valueOf(j2), ", isDone: ", Boolean.valueOf(z));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                jvc.e(iBaseResponseCallback, 8);
            }
        }));
    }

    public static void d(gki gkiVar, IBaseResponseCallback iBaseResponseCallback, jvb jvbVar) {
        ReleaseLogUtil.e("R_Stress_HwEphemerisNet", "requestUrlExt start");
        if (gkiVar == null || iBaseResponseCallback == null || jvbVar == null) {
            LogUtil.h("HwEphemerisNet", "requestUrlExt Invalid argument.");
            return;
        }
        Map<String, String> i = gkiVar.i();
        if (i == null || i.isEmpty()) {
            LogUtil.h("HwEphemerisNet", "requestUrlExt return");
            iBaseResponseCallback.d(1, null);
            return;
        }
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl(gkiVar.g(), GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
        LogUtil.a("HwEphemerisNet", "grsUrl:", noCheckUrl);
        int c = gkiVar.c();
        if (gkiVar.d() == 2) {
            b();
        }
        List arrayList = new ArrayList(0);
        if (c == 2) {
            for (Map.Entry<String, String> entry : i.entrySet()) {
                juz juzVar = new juz();
                juzVar.d(noCheckUrl + entry.getKey());
                juzVar.e(entry.getValue());
                arrayList.add(juzVar);
            }
        } else if (c == 1) {
            arrayList = a(noCheckUrl, i, gkiVar, iBaseResponseCallback);
        } else {
            LogUtil.h("HwEphemerisNet", "requestUrlExt not supported requestType");
        }
        if (arrayList.isEmpty()) {
            LogUtil.h("HwEphemerisNet", "requestUrlExt entityList is empty");
            iBaseResponseCallback.d(1, null);
        } else {
            d(arrayList, gkiVar, iBaseResponseCallback, jvbVar);
        }
    }

    private static List<juz> a(String str, Map<String, String> map, gki gkiVar, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(0);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                Response<String> execute = ((CommonApi) lqi.d().b(CommonApi.class)).commonGet(str + entry.getKey(), gkiVar.a()).execute();
                if (execute != null && c(execute, arrayList, entry.getValue(), iBaseResponseCallback)) {
                    return arrayList;
                }
            } catch (IOException unused) {
                ReleaseLogUtil.c("R_Stress_HwEphemerisNet", "requestUrlExt net or Server is error.");
                iBaseResponseCallback.d(2, null);
            } catch (IllegalArgumentException unused2) {
                ReleaseLogUtil.c("HwEphemerisNet", "requestUrlExt find IllegalArgumentException");
                iBaseResponseCallback.d(2, null);
            }
        }
        return arrayList;
    }

    private static boolean c(Response<String> response, List<juz> list, String str, IBaseResponseCallback iBaseResponseCallback) {
        if (response.isOK()) {
            String body = response.getBody();
            LogUtil.a("HwEphemerisNet", "isAddValidData jsonString : ", body);
            juz d = juy.d(body);
            if (d == null) {
                LogUtil.h("HwEphemerisNet", "isAddValidData ephemerisUrlBean is null.");
                iBaseResponseCallback.d(2, null);
                return true;
            }
            if (str != null) {
                d.e(str);
            }
            if (d.c()) {
                LogUtil.h("HwEphemerisNet", "isAddValidData invalid.");
                return false;
            }
            list.add(d);
        }
        return false;
    }

    private static void d(List<juz> list, gki gkiVar, IBaseResponseCallback iBaseResponseCallback, jvb jvbVar) {
        if (list.isEmpty()) {
            LogUtil.h("HwEphemerisNet", "downloadExt REQUEST_TAG_URL_ILLEGAL");
            iBaseResponseCallback.d(1, null);
            return;
        }
        File filesDir = BaseApplication.getContext().getFilesDir();
        for (juz juzVar : list) {
            String a2 = juzVar.a();
            String str = knl.a(jvbVar.c().getDeviceIdentify()) + a2;
            LogUtil.a("HwEphemerisNet", "downloadExt file name : ", str);
            if (TextUtils.isEmpty(str)) {
                iBaseResponseCallback.d(4, null);
            }
            if (!gkiVar.j()) {
                jvbVar.b(a2);
            }
            File file = new File(filesDir, CommonUtil.c(str));
            if (file.exists()) {
                LogUtil.a("HwEphemerisNet", "downloadExt delete file: ", Boolean.valueOf(file.delete()));
            }
            String e = gkiVar.e();
            ReleaseLogUtil.e("R_Stress_HwEphemerisNet", "downloadExt httpMethod: ", e);
            if ("POST".equals(e)) {
                lqi.d().d(new lqg(juzVar.e(), gkiVar.a(), file, b(list, gkiVar, iBaseResponseCallback, jvbVar, juzVar), gkiVar.b(), 2));
            } else {
                lqi.d().d(new lqg(juzVar.e(), gkiVar.a(), file, b(list, gkiVar, iBaseResponseCallback, jvbVar, juzVar)));
            }
        }
    }

    private static ProgressListener b(final List<juz> list, final gki gkiVar, final IBaseResponseCallback iBaseResponseCallback, final jvb jvbVar, final juz juzVar) {
        return new ProgressListener<File>() { // from class: jvc.5
            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                if (!jvc.c(file, gki.this, jvbVar)) {
                    iBaseResponseCallback.d(8, null);
                    return;
                }
                if (((juz) list.get(r3.size() - 1)).equals(juzVar)) {
                    iBaseResponseCallback.d(0, null);
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                LogUtil.a("HwEphemerisNet", "downloadExt onProgress, handleBytes: ", Long.valueOf(j), ", contentLength: ", Long.valueOf(j2), ", isDone: ", Boolean.valueOf(z));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                iBaseResponseCallback.d(8, null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(File file, gki gkiVar, jvb jvbVar) {
        ReleaseLogUtil.e("R_Stress_HwEphemerisNet", "processDataExt start");
        sqc.c(file.getPath(), "S2", 0);
        if (gkiVar.j()) {
            int d = gkiVar.d();
            String path = BaseApplication.getContext().getFilesDir().getPath();
            String a2 = knl.a(jvbVar.c().getDeviceIdentify());
            gkm gkmVar = new gkm();
            gkmVar.c(d);
            gkmVar.a(path);
            gkmVar.e(a2);
            gkmVar.d(file.getPath());
            Map<String, String> ephResponse = gkj.b().getEphResponse(gkmVar);
            if (ephResponse == null || ephResponse.isEmpty()) {
                LogUtil.h("HwEphemerisNet", "downloadExt reprocessed maps is null");
                return false;
            }
            for (Map.Entry<String, String> entry : ephResponse.entrySet()) {
                sqc.c(entry.getKey(), "S2", 0);
                jvbVar.b(entry.getValue());
            }
        }
        ReleaseLogUtil.e("R_Stress_HwEphemerisNet", "processDataExt end");
        return true;
    }

    private static boolean b(File file, String str, String str2) {
        long length = file.length();
        if (length == 0) {
            LogUtil.h("HwEphemerisNet", "checkFile length is null");
            return false;
        }
        byte[] bArr = new byte[(int) length];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = FileUtils.openInputStream(file);
            LogUtil.c("HwEphemerisNet", "read : ", Integer.valueOf(fileInputStream.read(bArr)));
            boolean e = e(d(bArr, str2.getBytes(Charset.forName("UTF-8")), "SHA-256"), str);
            LogUtil.a("HwEphemerisNet", "checked : ", str);
            return e;
        } catch (IOException | IllegalArgumentException | NoSuchAlgorithmException unused) {
            LogUtil.b("HwEphemerisNet", "checkFile find IllegalArgumentException|IOException|NoSuchAlgorithmException");
            return false;
        } finally {
            IoUtils.e(fileInputStream);
        }
    }

    private static byte[] d(byte[] bArr, byte[] bArr2, String str) throws IOException, NoSuchAlgorithmException {
        ByteArrayInputStream byteArrayInputStream;
        MessageDigest messageDigest;
        if (bArr == null) {
            throw new IllegalArgumentException("File not found.");
        }
        try {
            messageDigest = MessageDigest.getInstance(str);
            byteArrayInputStream = new ByteArrayInputStream(bArr);
        } catch (Throwable th) {
            th = th;
            byteArrayInputStream = null;
        }
        try {
            byte[] bArr3 = new byte[8192];
            int i = 0;
            while (true) {
                int read = byteArrayInputStream.read(bArr3);
                if (read == -1) {
                    break;
                }
                messageDigest.update(bArr3, 0, read);
                i += read;
            }
            if (i > 0) {
                byte[] d = d(bArr2, messageDigest);
                IoUtils.e(byteArrayInputStream);
                return d;
            }
            throw new IllegalArgumentException("Input file is empty.");
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(byteArrayInputStream);
            throw th;
        }
    }

    private static byte[] d(byte[] bArr, MessageDigest messageDigest) {
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (i < bArr.length) {
                int i2 = i + 8192;
                if (bArr.length >= i2) {
                    messageDigest.update(bArr, i, 8192);
                } else {
                    messageDigest.update(bArr, i, bArr.length - i);
                }
                i = i2;
            }
        }
        return messageDigest.digest();
    }

    private static boolean e(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            LogUtil.h("HwEphemerisNet", "checkHash input fileBytes or signature string is null");
            return false;
        }
        if (!str.equals(c(bArr))) {
            return false;
        }
        LogUtil.c("HwEphemerisNet", "checkHash is OK");
        return true;
    }

    private static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(0);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private static String d(String str) {
        return (TextUtils.isEmpty(str) || !str.contains("/")) ? "" : str.substring(str.lastIndexOf("/") + 1);
    }

    private static void b() {
        LogUtil.a("HwEphemerisNet", "PowerKitManager applyPowerKit Ephemeris");
        PowerKitManager.e().d("HwEphemerisNet", 65535, OpAnalyticsConstants.H5_LOADING_DELAY, "user-eph-download");
        if (EnvironmentInfo.r()) {
            return;
        }
        PowerKitManager.e().d("HwEphemerisNet", 512, OpAnalyticsConstants.H5_LOADING_DELAY, "user-eph-download");
    }

    public static void d() {
        LogUtil.a("HwEphemerisNet", "PowerKitManager unApplyPowerKit Ephemeris");
        PowerKitManager.e().e("HwEphemerisNet", 65535, "user-eph-download");
        if (EnvironmentInfo.r()) {
            return;
        }
        PowerKitManager.e().e("HwEphemerisNet", 512, "user-eph-download");
    }
}

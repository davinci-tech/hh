package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class onu extends QrCodeDataBase {
    private static final List<String> b = Arrays.asList(FitRunPlayAudio.PLAY_TYPE_T);
    private static final List<String> c = Arrays.asList("r");
    private static final List<String> d = Arrays.asList("bt_name", "n");

    /* renamed from: a, reason: collision with root package name */
    private String f15800a;
    private String e;
    private int f;

    onu(String str) {
        super(str);
        this.f15800a = null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase
    public int parser(Object obj) {
        if (obj == null || !(obj instanceof String)) {
            LogUtil.b("R_QrCode_DeviceQrCodeData", "mQrCodeDataBase is null.");
            return -3;
        }
        String str = (String) obj;
        String[] split = str.split("&");
        if (split == null || split.length < 1) {
            LogUtil.h("DeviceQrCodeData", "split is error.");
            return -3;
        }
        String b2 = b(split[0], b);
        if (b2 == null) {
            LogUtil.b("R_QrCode_DeviceQrCodeData", "type is null.");
            return -4;
        }
        if (split.length > 1) {
            this.e = b(split[1], d);
        }
        LogUtil.a("DeviceQrCodeData", " mDeviceName: ", this.e);
        if (a(str)) {
            String b3 = b(split[2], c);
            this.f15800a = b3;
            LogUtil.a("DeviceQrCodeData", " pinCode: ", b3);
        }
        try {
            this.f = Integer.parseInt(b2);
            int c2 = jfu.c(this.e);
            if (this.f == 79 && !TextUtils.isEmpty(this.e) && (this.e.toLowerCase().startsWith("huawei band 8-") || this.e.toLowerCase().startsWith("huawei band ask-"))) {
                this.f = 71;
            }
            if (this.e == null) {
                c2 = this.f;
            } else if (Utils.o() && e()) {
                oau.c(100103, "");
            }
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isHtyVersion()) {
                return 0;
            }
            if (d(c2)) {
                if (!jfu.g(this.f) && !c(this.f)) {
                    return -19;
                }
                LogUtil.h("DeviceQrCodeData", "DeviceInfoManager isHasUuid");
                if (!CommonUtil.aa(BaseApplication.getContext())) {
                    return -21;
                }
                d(b2);
                return -20;
            }
            LogUtil.a("DeviceQrCodeData", "parse result ", Integer.valueOf(this.f), " ,device name is ", this.e);
            if (!CommonUtil.aa(BaseApplication.getContext())) {
                return 0;
            }
            d(b2);
            return -20;
        } catch (NumberFormatException unused) {
            return -4;
        }
    }

    private boolean e() {
        List<cve> deviceList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList();
        if (koq.b(deviceList)) {
            return true;
        }
        Iterator<cve> it = deviceList.iterator();
        while (it.hasNext()) {
            List<String> e = it.next().e();
            if (!koq.b(e)) {
                Iterator<String> it2 = e.iterator();
                while (it2.hasNext()) {
                    if (this.e.contains(it2.next())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean d(int i) {
        boolean d2 = jfu.d(this.f, this.e);
        boolean c2 = c(this.f);
        boolean e = e(i);
        LogUtil.a("DeviceQrCodeData", "currentPairDeviceProductType :", Integer.valueOf(i), "isSupportScan:", Boolean.valueOf(d2), "isNeedOobeRes:", Boolean.valueOf(c2), "isDownloadImageFile:", Boolean.valueOf(e));
        return i == -1 || !d2 || c2 || e;
    }

    private void d(String str) {
        msq e = msn.c().e(jfu.d(this.f));
        if (e != null) {
            EzPluginManager.a().a(e);
        }
        int a2 = CommonUtil.a(str, 10);
        this.f = a2;
        if (a2 == 79 && !TextUtils.isEmpty(this.e) && (this.e.toLowerCase().startsWith("huawei band 8-") || this.e.toLowerCase().startsWith("huawei band ask-"))) {
            this.f = 71;
        }
        pem.a();
        ArrayList arrayList = new ArrayList();
        arrayList.add(jfu.d(this.f));
        ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).downloadIndexByUuidList(arrayList, null);
    }

    public boolean e(int i) {
        String d2 = jfu.d(i);
        if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(d2)) {
            boolean exists = new File(msj.a().d(d2) + File.separator + d2 + File.separator + "img").exists();
            boolean exists2 = new File(msj.a().d(d2) + File.separator + d2 + File.separator + "lang").exists();
            if (exists || !exists2) {
                return false;
            }
        }
        return true;
    }

    private boolean c(int i) {
        String pluginIndexOobe = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginIndexOobe(jfu.d(i));
        if (TextUtils.isEmpty(pluginIndexOobe)) {
            return false;
        }
        boolean exists = new File(cuv.f11488a + pluginIndexOobe + File.separator + pluginIndexOobe + File.separator).exists();
        LogUtil.a("DeviceQrCodeData", "isExistOobeRes:", Boolean.valueOf(exists));
        return !exists;
    }

    private String b(String str, List<String> list) {
        String[] split = str.split("=");
        if (split.length != 2) {
            return null;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(split[0])) {
                try {
                    return URLDecoder.decode(split[1], "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    LogUtil.b("DeviceQrCodeData", "parseKeyValue UnsupportedEncodingException:");
                }
            }
        }
        return null;
    }

    boolean b() {
        return this.f == 0;
    }

    public int d() {
        return this.f;
    }

    public String c() {
        return this.e;
    }

    public String a() {
        return this.f15800a;
    }

    private boolean a(String str) {
        return Pattern.compile("^.*&r=\\d{6}.*$").matcher(str).matches();
    }
}

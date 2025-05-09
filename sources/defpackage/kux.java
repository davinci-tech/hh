package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.List;

/* loaded from: classes9.dex */
public class kux {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dataSourceId")
    private String f14616a;

    @SerializedName("appInfo")
    private a d;

    @SerializedName("deviceInfo")
    private d e;

    public kux(HiHealthClient hiHealthClient) {
        if (hiHealthClient == null || hiHealthClient.getHiDeviceInfo() == null) {
            return;
        }
        e(hiHealthClient.getHiDeviceInfo().getDeviceUniqueCode());
        b(new a(hiHealthClient.getHiAppInfo()));
        b(new d(hiHealthClient.getHiDeviceInfo()));
    }

    public class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("mac")
        private String f14618a;

        @SerializedName("hardwareVersion")
        private String b;

        @SerializedName("category")
        private String c;

        @SerializedName("firmwareVersion")
        private String e;

        @SerializedName("manufacturer")
        private String f;

        @SerializedName("sn")
        private String g;

        @SerializedName("productId")
        private String h;

        @SerializedName("model")
        private String i;

        @SerializedName("name")
        private String j;

        @SerializedName("uniqueId")
        private String l;

        @SerializedName("softwareVersion")
        private String n;

        @SerializedName("udid")
        private String o;

        public d(HiDeviceInfo hiDeviceInfo) {
            if (hiDeviceInfo == null) {
                return;
            }
            k(hiDeviceInfo.getDeviceUniqueCode());
            i(hiDeviceInfo.getDeviceUdid());
            j(hiDeviceInfo.getDeviceName());
            b(e(hiDeviceInfo.getDeviceType()));
            f(hiDeviceInfo.getModel());
            e(hiDeviceInfo.getManufacturer());
            a(hiDeviceInfo.getDeviceMac());
            g(hiDeviceInfo.getDeviceSN());
            h(hiDeviceInfo.getSoftwareVersion());
            c(hiDeviceInfo.getHardwareVersion());
            d(hiDeviceInfo.getFirmwareVersion());
        }

        private String e(int i) {
            ProductMapParseUtil.b(BaseApplication.getContext());
            List<ProductMapInfo> b = ProductMap.b("deviceId", String.valueOf(i));
            return koq.b(b) ? "" : b.get(0).e();
        }

        public void k(String str) {
            this.l = str;
        }

        public void i(String str) {
            this.o = str;
        }

        public void j(String str) {
            this.j = str;
        }

        public void b(String str) {
            this.c = str;
        }

        public void f(String str) {
            this.i = str;
        }

        public void e(String str) {
            this.f = str;
        }

        public void a(String str) {
            this.f14618a = str;
        }

        public void g(String str) {
            this.g = str;
        }

        public void c(String str) {
            this.b = str;
        }

        public void h(String str) {
            this.n = str;
        }

        public void d(String str) {
            this.e = str;
        }
    }

    public class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("appId")
        private String f14617a;

        @SerializedName("version")
        private String c;

        @SerializedName("appName")
        private String d;

        @SerializedName("bundleName")
        private String e;

        public a(HiAppInfo hiAppInfo) {
            if (hiAppInfo == null) {
                return;
            }
            e(hiAppInfo.getPackageName());
            d(hiAppInfo.getAppName());
            c(hiAppInfo.getAppName());
            b(hiAppInfo.getVersion());
        }

        public void e(String str) {
            this.e = str;
        }

        public void d(String str) {
            this.f14617a = str;
        }

        public void c(String str) {
            this.d = str;
        }

        public void b(String str) {
            this.c = str;
        }
    }

    public void e(String str) {
        this.f14616a = str;
    }

    public void b(d dVar) {
        this.e = dVar;
    }

    public void b(a aVar) {
        this.d = aVar;
    }
}

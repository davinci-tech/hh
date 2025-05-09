package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.device.util.EventBus;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceDetailInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetAllDeviceRsp;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class cse {
    private static cse b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private List<DeviceDetailInfo> f11432a;
    private ArrayList<String> d;
    private List<DeviceDetailInfo> g;
    private ArrayList<String> j;
    private int h = 0;
    private boolean c = false;

    static /* synthetic */ int c(cse cseVar) {
        int i = cseVar.h;
        cseVar.h = i + 1;
        return i;
    }

    private cse() {
    }

    public static cse e() {
        cse cseVar;
        synchronized (e) {
            if (b == null) {
                b = new cse();
            }
            cseVar = b;
        }
        return cseVar;
    }

    private boolean i() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        this.c = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f11432a = null;
        this.g = null;
        this.d = null;
        this.j = null;
        this.h = 0;
        a(false);
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (g()) {
            a(true);
            a();
        } else {
            cpw.d(false, "WiFiDeviceUnBondPushHandler", "getUserDevice local has not device");
        }
    }

    private boolean g() {
        this.d = ctq.j();
        this.j = ctq.d();
        return c(this.d) || c(this.j);
    }

    private boolean c(ArrayList<String> arrayList) {
        return arrayList != null && arrayList.size() > 0;
    }

    private boolean d(List<DeviceDetailInfo> list) {
        return list != null && list.size() > 0;
    }

    private void a() {
        jbs.a(cpp.a()).d(new ICloudOperationResult<WifiDeviceGetAllDeviceRsp>() { // from class: cse.4
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceGetAllDeviceRsp wifiDeviceGetAllDeviceRsp, String str, boolean z) {
                int i;
                String str2;
                cse.c(cse.this);
                if (z) {
                    if (wifiDeviceGetAllDeviceRsp != null) {
                        cpw.a(false, "WiFiDeviceUnBondPushHandler", "getUserDevice reg device success :", wifiDeviceGetAllDeviceRsp.toString());
                        cse.this.f11432a = wifiDeviceGetAllDeviceRsp.getAuthorizedDeviceDetailInfoList();
                        cse.this.g = wifiDeviceGetAllDeviceRsp.getDeviceDetailInfoList();
                    }
                    cse.this.b();
                    cse.this.d();
                    cse.this.a(false);
                    return;
                }
                if (wifiDeviceGetAllDeviceRsp != null) {
                    i = wifiDeviceGetAllDeviceRsp.getResultCode().intValue();
                    str2 = wifiDeviceGetAllDeviceRsp.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str2 = "unknown error";
                }
                cpw.d(false, "WiFiDeviceUnBondPushHandler", "getUserAllDevice() errCode = ", Integer.valueOf(i), ",resultDesc:", str2);
                if (cse.this.h <= 2) {
                    cse.this.j();
                } else {
                    cse.this.a(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Object[] objArr = new Object[2];
        objArr[0] = "compareAuthDevice mAuthDevice :";
        List<DeviceDetailInfo> list = this.f11432a;
        objArr[1] = list == null ? Constants.NULL : Integer.valueOf(list.size());
        cpw.a(false, "WiFiDeviceUnBondPushHandler", objArr);
        if (c(this.d)) {
            if (!d(this.f11432a)) {
                e(this.d);
                h();
            } else {
                ArrayList<String> c = c(this.d, this.f11432a);
                if (c.size() > 0) {
                    cpw.a(false, "WiFiDeviceUnBondPushHandler", "compareAuthDevice deviceDevices ", Integer.valueOf(c.size()));
                    e(c);
                    h();
                } else {
                    cpw.a(false, "WiFiDeviceUnBondPushHandler", "compareAuthDevice No Contact binding device");
                }
            }
        } else {
            cpw.a(false, "WiFiDeviceUnBondPushHandler", "compareAuthDevice Local No devices");
        }
        csb.a().g();
    }

    private ArrayList<String> c(ArrayList<String> arrayList, List<DeviceDetailInfo> list) {
        ArrayList<String> arrayList2 = new ArrayList<>(16);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                break;
            }
            Iterator<DeviceDetailInfo> it2 = list.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                if (next.equals(it2.next().getDevId())) {
                    z = true;
                }
            }
            if (!z) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    private void h() {
        Bundle bundle = new Bundle();
        bundle.putString("pushType", "release_auth");
        EventBus.d(new EventBus.b("multi_user_auto_cancle_dialog", bundle));
    }

    private void e(ArrayList<String> arrayList) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            cpw.a(false, "WiFiDeviceUnBondPushHandler", "unBondLocalDevice Whether the desorption was successful ", Boolean.valueOf(cjx.e().f(next)), " deviceID: ", cpw.d(next));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Object[] objArr = new Object[2];
        objArr[0] = "getUserDevice reg device MainUserDevice :";
        List<DeviceDetailInfo> list = this.g;
        objArr[1] = list == null ? Constants.NULL : Integer.valueOf(list.size());
        cpw.a(false, "WiFiDeviceUnBondPushHandler", objArr);
        ArrayList<String> c = ctq.c(true);
        this.j = c;
        if (c(c)) {
            if (!d(this.g)) {
                e(this.j);
                return;
            }
            ArrayList<String> c2 = c(this.j, this.g);
            if (c2.size() > 0) {
                cpw.a(false, "WiFiDeviceUnBondPushHandler", "compareDevice deviceDevices ", Integer.valueOf(c2.size()));
                e(c2);
                return;
            } else {
                cpw.a(false, "WiFiDeviceUnBondPushHandler", "compareDevice No Contact binding device");
                return;
            }
        }
        cpw.a(false, "WiFiDeviceUnBondPushHandler", "compareDevice Local No devices");
    }

    public void c() {
        if (!i()) {
            cpw.a(false, "WiFiDeviceUnBondPushHandler", "onStart working ...");
            jdx.b(new Runnable() { // from class: cse.2
                @Override // java.lang.Runnable
                public void run() {
                    cse.this.f();
                }
            });
        } else {
            cpw.a(false, "WiFiDeviceUnBondPushHandler", "onStart is working");
        }
    }
}

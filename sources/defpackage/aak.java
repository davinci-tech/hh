package defpackage;

import android.content.Context;
import com.huawei.android.hicloud.sync.logic.SyncBase;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.logic.c;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class aak extends SyncBase {
    private static aac b = new aac();
    private static int c = -1;
    private static String e;
    private final aau d;
    private c f;
    private final SyncProcessInterface g;
    private int h;
    private final Context i;
    private String j;
    private boolean m;
    private int o;

    @Deprecated
    public aak(Context context, String str, SyncProcessInterface syncProcessInterface) {
        super(context);
        this.h = -1;
        this.j = "";
        this.o = 0;
        this.m = false;
        this.i = context;
        this.g = syncProcessInterface;
        this.d = new aau(context, str, syncProcessInterface);
        this.f = null;
        this.h = -1;
        abd.c("CloudSync", "App call: new CloudSync, version code: 13.3.0.303");
    }

    private JSONObject b(String str) {
        JSONObject d = d(str);
        try {
            d.put("isSdkSupportDownPartical", true);
        } catch (Exception e2) {
            abd.b("CloudSync", "extraInfo err " + e2.getMessage());
        }
        return d;
    }

    private JSONObject d(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("prepareTraceId", e);
            jSONObject.put("prepareTimes", abe.e(this.i).h(str));
            jSONObject.put("firstStartTime", abe.e(this.i).f(str));
            jSONObject.put("thirdAppSyncScene", c);
        } catch (Exception e2) {
            abd.b("CloudSync", "extraInfo err " + e2.getMessage());
        }
        return jSONObject;
    }

    private void e(String str) {
        if (aav.a(this.i)) {
            this.f.a(str, this.j, this.h, b(str));
            return;
        }
        if (aav.b(this.i)) {
            this.f.a(str, this.j, this.h, d(str));
            return;
        }
        if (aaw.d(this.i)) {
            this.f.a(str, this.j, this.h, new JSONObject());
            return;
        }
        int i = this.h;
        if (i == -1) {
            this.f.a(str, i, "");
        } else {
            this.f.a(str, i, abf.e(str, i));
        }
    }

    public void a(String str, String str2, List<UnstructData> list, int i) {
        abd.c("CloudSync", "App call: downUnstructFile, dataType = " + str2);
        a(str, i);
        this.f.a(str, str2, list);
    }

    public void b(String str, List<String> list, List<String> list2) {
        abd.c("CloudSync", "App call: endSync, syncType = " + str);
        c cVar = this.f;
        if (cVar == null) {
            abd.b("CloudSync", "endSync error: cloudSync is null");
        } else {
            cVar.a(abf.a(str, list.toString(), list2.toString()));
            this.f.a(str, list, list2);
        }
    }

    private void a(String str, int i) {
        abd.c("CloudSync", "initCloudSync: syncType = " + str + ", versionCode = " + i);
        aal.c(i);
        switch (i) {
            case 100:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV100");
                    this.f = new aam(this.i, str, this.g, this.d);
                    break;
                }
                break;
            case 101:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV101");
                    this.f = new aai(this.i, str, this.g, this.d);
                    break;
                }
                break;
            case 102:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV102");
                    this.f = new aaj(this.i, str, this.g, this.d);
                    break;
                }
                break;
            case 103:
            case 106:
            default:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV1");
                    this.f = new aaq(this.i, str, this.g, this.d);
                    break;
                }
                break;
            case 104:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV104");
                    this.f = new aan(this.i, str, this.g, this.d);
                    break;
                }
                break;
            case 105:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV105");
                    this.f = new aao(this.i, str, this.g, this.d);
                    break;
                }
                break;
            case 107:
                if (this.f == null) {
                    abd.c("CloudSync", "cloudSync is null, new CloudSyncV107");
                    this.f = new aar(this.i, str, this.g, this.d);
                    break;
                }
                break;
        }
    }

    public int e(String str, String str2, int i, int i2, int i3) {
        abd.c("CloudSync", "App call: syncData, syncType = " + str + ", dataType = " + str2 + ", order = " + i + ", batchNumber = " + i2 + ", versionCode = " + i3);
        if (!abl.c(this.i)) {
            abd.b("CloudSync", "App call: syncData: checkHiCloudValidate is false");
            return -11;
        }
        if (!c(str, str2, i, i3)) {
            return -9;
        }
        if (i3 < 2) {
            return -1;
        }
        a(str, i3);
        if (i == 1) {
            this.m = true;
            e(str);
        }
        zr.a(this.i).b(str, false);
        b = new aac();
        this.f.a(str, str2, i, i2, this.o);
        return 0;
    }

    private boolean c(String str, String str2, int i, int i2) {
        c cVar = this.f;
        if (cVar == null && i != 1) {
            abd.d("CloudSync", "order is not 1, syncType = " + str + ", dataType = " + str2 + ", order = " + i);
            a(str, i2);
            this.f.a(str2, -9);
            return false;
        }
        if (cVar == null || i == 1 || this.m) {
            return true;
        }
        abd.d("CloudSync", "not call order 1, syncType = " + str + ", dataType = " + str2 + ", order = " + i);
        this.f.a(str2, -9);
        return false;
    }
}

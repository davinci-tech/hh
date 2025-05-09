package defpackage;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkRequest;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkResponse;
import com.huawei.wisesecurity.ucs_credential.o;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class txl {

    /* renamed from: a, reason: collision with root package name */
    public NetworkCapability f17404a;
    public o b;
    public Context e;

    public final void b(NetworkResponse networkResponse, twp twpVar) throws twc {
        if (!networkResponse.isSuccessful()) {
            if (networkResponse.getCode() != 304) {
                twb.a("KeyComponentManger", "file data update failed And statusCode = {0}", Integer.valueOf(networkResponse.getCode()));
                return;
            }
            twb.a("KeyComponentManger", "file data has not modified!", new Object[0]);
            twi.a(twpVar.c(), System.currentTimeMillis(), this.e);
            txm.b(this.e, twpVar);
            return;
        }
        txm.b(this.e, networkResponse.getBody());
        Context context = this.e;
        Map<String, List<String>> headers = networkResponse.getHeaders();
        twb.d("LocalCDNFile", "Update local meta data : ucscomponent", new Object[0]);
        if (headers.containsKey("etag")) {
            twb.d("LocalCDNFile", "Update local meta data -etag: ucscomponent", new Object[0]);
            twi.c("ETag_ucscomponent", headers.get("etag").get(0), context);
        }
        if (headers.containsKey("last-modified")) {
            twb.d("LocalCDNFile", "Update local meta data -last-modified: ucscomponent", new Object[0]);
            twi.c("Last-Modified_ucscomponent", headers.get("last-modified").get(0), context);
        }
        twi.a(twpVar.c(), System.currentTimeMillis(), this.e);
        String str = this.e.createDeviceProtectedStorageContext().getFilesDir() + "/ucscomponent.jws";
        twi.c("ucscomponent.jws", str, this.e);
        String body = networkResponse.getBody();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            try {
                fileOutputStream.write(body.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e) {
            StringBuilder e2 = twf.e("Write file data failed : ");
            e2.append(e.getMessage());
            twb.e("KeyComponentLocalHandler", e2.toString(), new Object[0]);
            StringBuilder e3 = twf.e("Write file data failed : ");
            e3.append(e.getMessage());
            throw new twc(1011L, e3.toString());
        }
    }

    public void c(boolean z, twp twpVar) throws twc {
        synchronized (this) {
            twb.a("KeyComponentManger", "start download C1 file from Service", new Object[0]);
            try {
                Map hashMap = new HashMap();
                if (!z) {
                    hashMap = twpVar.a(this.e);
                }
                String a2 = this.b.a("ucscomponent", "ucscomponent.jws");
                twb.a("KeyComponentManger", "updateFileFromCDN domain is {0}", a2);
                b(this.f17404a.get(new NetworkRequest(a2, hashMap)), twpVar);
                twb.a("KeyComponentManger", "updateFileFromCDN OK", new Object[0]);
            } catch (IOException e) {
                StringBuilder e2 = twf.e("Update file data get IOException，exception: ");
                e2.append(e.getMessage());
                String sb = e2.toString();
                throw two.e("KeyComponentManger", sb, new Object[0], 1010L, sb);
            }
        }
    }

    public txl(Context context, o oVar, NetworkCapability networkCapability) {
        this.e = context;
        this.f17404a = networkCapability;
        this.b = oVar;
    }
}

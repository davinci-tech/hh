package defpackage;

import android.content.Context;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.entity.ErrorBody;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkResponse;
import com.huawei.wisesecurity.ucs_credential.c;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class twm extends c {
    public boolean h;
    public txl j;

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public void a() throws twc {
        txl txlVar = this.j;
        twp twpVar = new twp();
        if (twpVar.d(txlVar.e)) {
            txlVar.c(false, twpVar);
            return;
        }
        try {
            txm.b(txlVar.e, twpVar);
        } catch (twc e) {
            twb.b("KeyComponentManger", "Init using local file failed, code = {0}, msg = {1}", Long.valueOf(e.b()), e.getMessage());
            twb.a("KeyComponentManger", "Try update data = componnet from server", new Object[0]);
            txlVar.c(true, twpVar);
        }
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public String b() throws twc {
        int c = twi.c("Local-C1-Version", -1, this.b);
        twb.d("KidHandler", "c1 version is " + c + ", so version is " + ((int) UcsLib.ucsGetSoVersion()), new Object[0]);
        return new String(UcsLib.genReqJws(this.b, this.d, this.e, 0, c), StandardCharsets.UTF_8);
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public Credential a(String str) throws twc {
        try {
            int parseInt = Integer.parseInt(new JSONObject(str).getString("expire"));
            if (parseInt == 0) {
                return this.g.genCredentialFromString(str);
            }
            if (parseInt != 1) {
                if (parseInt != 2) {
                    throw new twc(1017L, "unenable expire.");
                }
                throw new twc(1016L, "so version is unenable.");
            }
            if (!this.h) {
                throw new twc(1021L, "c1 vision is unenable.");
            }
            twb.d("KidHandler", "c1 version expired, start to force update c1!", new Object[0]);
            this.j.c(true, new twp());
            this.h = false;
            return a(this.c, this.d, this.e, this.f);
        } catch (NumberFormatException e) {
            StringBuilder e2 = twf.e("parse TSMS resp expire error : ");
            e2.append(e.getMessage());
            throw new twc(2001L, e2.toString());
        } catch (JSONException e3) {
            StringBuilder e4 = twf.e("parse TSMS resp get json error : ");
            e4.append(e3.getMessage());
            throw new twc(Const.RawDataType.HEALTH_SLEEP_RECORD, e4.toString());
        }
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public String a(NetworkResponse networkResponse) throws twc {
        boolean isSuccessful = networkResponse.isSuccessful();
        String body = networkResponse.getBody();
        if (isSuccessful) {
            return body;
        }
        ErrorBody fromString = ErrorBody.fromString(body);
        StringBuilder e = twf.e("tsms service error, ");
        e.append(fromString.getErrorMessage());
        String sb = e.toString();
        throw two.e("KidHandler", sb, new Object[0], 1024L, sb);
    }

    @Override // com.huawei.wisesecurity.ucs_credential.c
    public Credential a(String str, String str2, String str3, String str4, twr twrVar) throws twc {
        twb.a("KidHandler", "applyCredential use KidHandler.", new Object[0]);
        return a(str, str2, str3, str4);
    }

    public twm(CredentialClient credentialClient, Context context, NetworkCapability networkCapability, txl txlVar) throws twc {
        super(credentialClient, context, networkCapability);
        this.h = true;
        this.j = txlVar;
    }
}

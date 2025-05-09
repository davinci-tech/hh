package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hwidauth.h.f;
import com.huawei.secure.android.common.intent.SafeBundle;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kry extends f<kqw> {
    public kry(kqw kqwVar, Context context, String str, f.a aVar) {
        this.f6367a = kqwVar;
        this.b = context;
        this.c = str;
        this.d = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hwidauth.h.f
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(kqw kqwVar, Response<ResponseBody> response) {
        int code = response.getCode();
        ksy.b("GetResourceCase", "code:" + code, true);
        if (code == 200) {
            try {
                String str = new String(response.getBody().bytes(), "UTF-8");
                ksy.b("GetResourceCase", "handleRequestNet data:".concat(str), false);
                kqwVar.c(str);
                if (kqwVar.j() == 0) {
                    String i = kqwVar.i();
                    SafeBundle safeBundle = new SafeBundle();
                    ArrayList<String> arrayList = new ArrayList<>();
                    if (!TextUtils.isEmpty(i)) {
                        JSONObject jSONObject = new JSONObject(i);
                        safeBundle.putString("publicKey", jSONObject.getString("public-key"));
                        JSONArray jSONArray = jSONObject.getJSONArray("domain-whitelist");
                        int length = jSONArray.length();
                        for (int i2 = 0; i2 < length; i2++) {
                            arrayList.add((String) jSONArray.get(i2));
                        }
                        safeBundle.putStringArrayList("domainAllowList", arrayList);
                        ksy.b("GetResourceCase", "allow list" + arrayList.toString(), false);
                        ksy.b("GetResourceCase", "allow-length---" + length, true);
                        a(safeBundle);
                        return;
                    }
                    ksy.b("GetResourceCase", "getErrorCode ==" + kqwVar.g(), true);
                    ksy.b("GetResourceCase", "getErrorDesc ==" + kqwVar.h(), false);
                    a(kqwVar.g(), kqwVar.h());
                    return;
                }
                a(6, "Request Error:casLoginJson is empty.");
                return;
            } catch (Exception e) {
                ksy.b("GetResourceCase", "Exception:" + e.getClass().getSimpleName(), true);
                a(2015, "Request Error:" + e.getClass().getSimpleName());
                return;
            }
        }
        a(2005, "Request Error:code is " + code);
    }
}

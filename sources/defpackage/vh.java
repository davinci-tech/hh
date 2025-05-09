package defpackage;

import android.text.TextUtils;
import com.huawei.ads.adsrec.db.table.AdContentRspRecord;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class vh implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private JSONArray f17707a;
    private int b;
    private String c;
    private String d;
    private List<vg> e;
    private AdContentRspRecord f;
    private JSONObject i;
    private int j;

    public String toString() {
        return "AdContentRsp{retCode='" + this.b + "', pkgName=" + this.d + ", adContentRspRecord=" + this.f + ", slots=" + this.e + '}';
    }

    public boolean g() {
        List<vg> list = this.e;
        if (list == null) {
            return true;
        }
        Iterator<vg> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().g()) {
                return false;
            }
        }
        return true;
    }

    public List<vg> j() {
        return this.e;
    }

    public String d() {
        return this.d;
    }

    public JSONArray c() {
        return this.f17707a;
    }

    public JSONObject a() {
        if (this.i == null) {
            try {
                JSONObject d = d(this.f);
                this.i = d;
                d.put("retcode", this.b);
                this.i.put("clientAdRequestId", this.c);
                this.i.remove("cost");
                this.i.remove("ppsStore");
            } catch (Throwable th) {
                HiAdLog.w("AdContentRsp", "gen json fail " + th.getClass().getSimpleName());
            }
        }
        if (!ListUtil.isEmpty(this.e)) {
            JSONArray jSONArray = new JSONArray();
            Iterator<vg> it = this.e.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().c());
            }
            vj.b(this.i, "multiad", jSONArray);
        }
        return this.i;
    }

    public void c(int i) {
        this.j = i;
    }

    public AdContentRspRecord e() {
        return this.f;
    }

    public void a(List<vg> list) {
        this.e = list;
    }

    public void a(String str) {
        this.c = str;
    }

    public void b(int i) {
        this.b = i;
    }

    public Set<String> d(Integer num, Integer num2) {
        HashSet hashSet = new HashSet();
        List<vg> list = this.e;
        if (list != null) {
            Iterator<vg> it = list.iterator();
            while (it.hasNext()) {
                hashSet.addAll(it.next().a(num, num2));
            }
        }
        return hashSet;
    }

    public vh b() {
        try {
            vh vhVar = (vh) super.clone();
            if (this.e != null) {
                ArrayList arrayList = new ArrayList(this.e.size());
                Iterator<vg> it = this.e.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().d());
                }
                vhVar.e = arrayList;
            }
            vhVar.f = (AdContentRspRecord) this.f.copy();
            return vhVar;
        } catch (CloneNotSupportedException unused) {
            HiAdLog.e("AdContentRsp", "copy failed");
            return null;
        }
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.b = jSONObject.optInt("retcode", -1);
            this.f17707a = jSONObject.optJSONArray("invalidcontentid");
            JSONArray optJSONArray = jSONObject.optJSONArray("multiad");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.e = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        this.e.add(new vg(this.d, optJSONObject));
                    }
                }
            }
            jSONObject.remove("multiad");
            this.f = new AdContentRspRecord(this.d, jSONObject);
        }
    }

    private static JSONObject d(AdContentRspRecord adContentRspRecord) {
        if (adContentRspRecord == null || TextUtils.isEmpty(adContentRspRecord.e())) {
            return new JSONObject();
        }
        try {
            return new JSONObject(adContentRspRecord.e());
        } catch (JSONException unused) {
            HiAdLog.w("AdContentRsp", "create valued json obj err");
            return new JSONObject();
        }
    }

    public vh(String str, JSONObject jSONObject) {
        this.d = str;
        this.i = jSONObject;
        a(jSONObject);
    }

    public vh(String str, AdContentRspRecord adContentRspRecord) {
        this.d = str;
        this.b = 200;
        this.f = adContentRspRecord;
    }
}

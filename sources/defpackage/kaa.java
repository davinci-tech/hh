package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kaa implements Bean {
    private String ab;
    private String ac;
    private String ad;
    private String af;
    private String b;
    private String c;
    private String d;
    private String k;
    private String p;
    private String r;
    private String s;
    private byte[] v;
    private String w;
    private String z;
    private HashMap<String, String> aa = new HashMap<>();
    private HashMap<Integer, List<String>> u = new HashMap<>(16);
    private HashMap<String, List<String>> j = new HashMap<>(16);
    private HashMap<Integer, List<String>> o = new HashMap<>(16);
    private HashMap<String, List<String>> f = new HashMap<>(16);
    private HashMap<String, String> x = new HashMap<>(16);
    private HashMap<Integer, List<String[]>> e = new HashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, List<String[]>> f14235a = new HashMap<>(16);
    private HashMap<Integer, List<String>> ag = new HashMap<>(16);
    private HashMap<Integer, List<String>> q = new HashMap<>(16);
    private HashMap<String, List<String>> g = new HashMap<>(16);
    private HashMap<Integer, List<String>> n = new HashMap<>(16);
    private HashMap<String, List<String>> i = new HashMap<>(16);
    private Map<String, HashMap<Integer, List<String>>> t = new HashMap(6);
    private Map<String, HashMap<String, List<String>>> h = new HashMap(6);
    private HashMap<Integer, List<String>> y = new HashMap<>(16);
    private HashMap<String, List<String>> l = new HashMap<>(16);
    private ArrayList<kaj> m = new ArrayList<>(10);

    public kaa() {
        m();
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean
    public String getId() {
        return this.k;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean
    public void setId(String str) {
        if (kat.b(str)) {
            return;
        }
        this.k = str;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean
    public String getUid() {
        return this.ac;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean
    public void setUid(String str) {
        if (kat.b(str)) {
            return;
        }
        this.ac = str;
    }

    public void a(String str, int i, String str2) {
        HashMap<Integer, List<String>> o = o(str);
        if (o == null) {
            LogUtil.h("ContactSync", 1, "RawContactBean", "setKindByType: kind map is null or empty.");
        } else {
            d(o, i, str2);
        }
    }

    public List<String> b(String str, int i) {
        HashMap<Integer, List<String>> o = o(str);
        if (o == null || o.isEmpty()) {
            return new ArrayList(0);
        }
        return b(o, i);
    }

    public void c(String str, String str2, String str3) {
        HashMap<String, List<String>> l = l(str);
        if (l == null) {
            LogUtil.h("ContactSync", 1, "RawContactBean", "setCustomEntry: unknown type");
        } else {
            c(l, str2, str3);
        }
    }

    public HashMap<String, List<String>> b(String str) {
        HashMap<String, List<String>> l = l(str);
        return (l == null || l.isEmpty()) ? new HashMap<>(0) : l;
    }

    public String i() {
        return this.p;
    }

    public void i(String str) {
        if (kat.b(str)) {
            return;
        }
        this.p = str;
    }

    public String j() {
        return this.s;
    }

    public void j(String str) {
        if (kat.b(str)) {
            return;
        }
        this.s = str;
    }

    public byte[] g() {
        byte[] bArr = this.v;
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ContactSync", 1, "RawContactBean", "getPhoto: photo bytes is null or empty.");
            return new byte[0];
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    public void a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("ContactSync", 1, "RawContactBean", "setPhoto: photo bytes is null or empty.");
        } else {
            this.v = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public String a() {
        return this.c;
    }

    public String f() {
        return this.w;
    }

    public void g(String str) {
        if (kat.b(str)) {
            return;
        }
        this.w = str;
    }

    public String n() {
        return this.z;
    }

    public void m(String str) {
        if (kat.b(str)) {
            return;
        }
        this.z = str;
    }

    public String k() {
        return this.ab;
    }

    public void k(String str) {
        if (kat.b(str)) {
            return;
        }
        this.ab = str;
    }

    public String l() {
        return this.ad;
    }

    public void f(String str) {
        if (kat.b(str)) {
            return;
        }
        this.ad = str;
    }

    public String h() {
        return this.r;
    }

    public void h(String str) {
        if (kat.b(str)) {
            return;
        }
        this.r = str;
    }

    public void a(kaj kajVar) {
        if (kajVar != null) {
            this.m.add(kajVar);
        }
    }

    public String e() {
        try {
            JSONObject jSONObject = new JSONObject();
            ArrayList<kaj> arrayList = this.m;
            if (arrayList != null) {
                kaw.e(jSONObject, arrayList);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            LogUtil.b("ContactSync", 1, "RawContactBean", "[getHiCallDeviceField] JSONException:", ExceptionUtils.d(e));
            return "";
        }
    }

    public List<String[]> a(int i) {
        return a(this.e, i);
    }

    public HashMap<String, List<String[]>> b() {
        return this.f14235a;
    }

    public String a(String str) {
        return a(this.x, str);
    }

    public String e(String str) {
        return a(this.aa, str);
    }

    public void d(String str, String str2) {
        a(this.x, str, str2);
    }

    public void b(String str, String str2) {
        a(this.aa, str, str2);
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean
    public String getVersion() {
        return this.af;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean
    public void setVersion(String str) {
        if (kat.b(str)) {
            return;
        }
        this.af = str;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        if (kat.b(str)) {
            return;
        }
        this.d = str;
    }

    public void d(String str) {
        if (kat.b(str)) {
            return;
        }
        this.b = str;
    }

    public String d() {
        return this.b;
    }

    public void b(int i, String[] strArr) {
        e(this.e, i, strArr);
    }

    public void e(String str, String[] strArr) {
        a(this.f14235a, str, strArr);
    }

    private void c(HashMap<String, List<String>> hashMap, String str, String str2) {
        d((HashMap<HashMap<String, List<String>>, List<String>>) hashMap, (HashMap<String, List<String>>) str, str2);
    }

    private String a(HashMap<String, String> hashMap, String str) {
        return b((HashMap<HashMap<String, String>, String>) hashMap, (HashMap<String, String>) str);
    }

    private void a(HashMap<String, String> hashMap, String str, String str2) {
        if (hashMap == null || kat.b(str2)) {
            return;
        }
        hashMap.put(str, str2);
    }

    private List<String[]> a(HashMap<Integer, List<String[]>> hashMap, int i) {
        return c(hashMap, Integer.valueOf(i));
    }

    private void e(HashMap<Integer, List<String[]>> hashMap, int i, String[] strArr) {
        d((HashMap<HashMap<Integer, List<String[]>>, List<Integer>>) hashMap, (HashMap<Integer, List<String[]>>) Integer.valueOf(i), (Integer) strArr);
    }

    private void d(HashMap<Integer, List<String>> hashMap, int i, String str) {
        d((HashMap<HashMap<Integer, List<String>>, List<Integer>>) hashMap, (HashMap<Integer, List<String>>) Integer.valueOf(i), (Integer) str);
    }

    private List<String> b(HashMap<Integer, List<String>> hashMap, int i) {
        return c(hashMap, Integer.valueOf(i));
    }

    private void a(HashMap<String, List<String[]>> hashMap, String str, String[] strArr) {
        d((HashMap<HashMap<String, List<String[]>>, List<String>>) hashMap, (HashMap<String, List<String[]>>) str, (String) strArr);
    }

    private <K, V> void d(HashMap<K, List<V>> hashMap, K k, V v) {
        if (hashMap == null) {
            LogUtil.h("ContactSync", 1, "RawContactBean", "setKindFields hashMap is null.");
            return;
        }
        List<V> list = hashMap.get(k);
        if (list == null) {
            list = new ArrayList<>(0);
        }
        if (v != null) {
            if ((v instanceof String) && TextUtils.isEmpty((CharSequence) v)) {
                return;
            }
            list.add(v);
            hashMap.put(k, list);
        }
    }

    private <K, V> List<V> c(HashMap<K, List<V>> hashMap, K k) {
        if (hashMap == null || !hashMap.containsKey(k)) {
            return new ArrayList(0);
        }
        return hashMap.get(k);
    }

    private <K> String b(HashMap<K, String> hashMap, K k) {
        return (hashMap == null || !hashMap.containsKey(k)) ? "" : hashMap.get(k);
    }

    private void m() {
        this.t.put("vnd.android.cursor.item/phone_v2", this.u);
        this.t.put("vnd.android.cursor.item/email_v2", this.o);
        this.t.put("vnd.android.cursor.item/website", this.ag);
        this.t.put("vnd.android.cursor.item/im", this.q);
        this.t.put("vnd.android.cursor.item/contact_event", this.n);
        this.t.put("vnd.android.cursor.item/relation", this.y);
        this.h.put("vnd.android.cursor.item/phone_v2", this.j);
        this.h.put("vnd.android.cursor.item/email_v2", this.f);
        this.h.put("vnd.android.cursor.item/im", this.g);
        this.h.put("vnd.android.cursor.item/contact_event", this.i);
        this.h.put("vnd.android.cursor.item/relation", this.l);
    }

    private HashMap<Integer, List<String>> o(String str) {
        return this.t.get(str);
    }

    private HashMap<String, List<String>> l(String str) {
        return this.h.get(str);
    }

    public String toString() {
        return "RawContactBean{mId='" + this.k + "', mUid='" + this.ac + "', mVersion='" + this.af + "', mStructuredName=" + this.aa + ", mNickName='" + this.p + "', mPhone=" + this.u + ", mCustomPhone=" + this.j + ", mEmail=" + this.o + ", mCustomEmail=" + this.f + ", mOrganization=" + this.x + ", mAddress=" + this.e + ", mCustomAddress=" + this.f14235a + ", mNotes='" + this.s + "', mWebSite=" + this.ag + ", mIm=" + this.q + ", mCustomIm=" + this.g + ", mEvent=" + this.n + ", mCustomEvent=" + this.i + ", mPhoto=" + Arrays.toString(this.v) + ", mCardHash='" + this.c + "', mPhotoFileName='" + this.w + "', mRelation=" + this.y + ", mCustomRelation=" + this.l + ", mTimesContacted='" + this.z + "', mStarredOrder='" + this.ab + "', mStarred='" + this.ad + "', mIsPrivate='" + this.r + "', mAccountType='" + this.d + "', mAccountName='" + this.b + "', mHiCallDeviceModelList=" + this.m + '}';
    }
}

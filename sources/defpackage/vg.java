package defpackage;

import android.text.TextUtils;
import com.huawei.ads.adsrec.db.table.SlotRecord;
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
public class vg implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private String f17706a;
    private SlotRecord b;
    private List<vb> c;
    private int d;
    private String e;
    private JSONObject i;
    private String j;

    public String toString() {
        return "AdSlot{slotId='" + this.f17706a + "', pkgName=" + this.e + ", retcode30=" + this.d + ", contents=" + this.c + '}';
    }

    public boolean g() {
        List<vb> list = this.c;
        return list == null || list.isEmpty();
    }

    public SlotRecord j() {
        return this.b;
    }

    public String h() {
        return this.f17706a;
    }

    public String e() {
        return this.e;
    }

    public List<vb> a() {
        return this.c;
    }

    public String b() {
        JSONObject a2;
        if (this.j == null && (a2 = vj.a(this.b.c())) != null) {
            this.j = a2.optString("adtype");
        }
        return this.j;
    }

    public JSONObject c() {
        if (this.i == null) {
            JSONObject c = c(this.b);
            this.i = c;
            vj.b(c, "retcode30", this.d);
        }
        if (!ListUtil.isEmpty(this.c)) {
            JSONArray jSONArray = new JSONArray();
            Iterator<vb> it = this.c.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().l());
            }
            vj.b(this.i, "content", jSONArray);
        }
        return this.i;
    }

    public void b(List<vb> list) {
        this.c = list;
    }

    public void a(vg vgVar) {
        this.f17706a = vgVar.f17706a;
        this.b = vgVar.b;
        this.c = vgVar.c;
        this.i = vgVar.i;
        this.d = vgVar.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public Set<String> a(Integer num, Integer num2) {
        return this.c == null ? new HashSet() : (num == null && num2 == null) ? f() : num != null ? d(num.intValue()) : b(num2.intValue());
    }

    public vg d() {
        try {
            vg vgVar = (vg) super.clone();
            if (this.c != null) {
                ArrayList arrayList = new ArrayList(this.c.size());
                Iterator<vb> it = this.c.iterator();
                while (it.hasNext()) {
                    vb e = it.next().e();
                    if (e != null) {
                        arrayList.add(e);
                    }
                }
                vgVar.c = arrayList;
            }
            SlotRecord slotRecord = this.b;
            if (slotRecord != null) {
                vgVar.b = (SlotRecord) slotRecord.copy();
            }
            return vgVar;
        } catch (CloneNotSupportedException unused) {
            HiAdLog.w("AdSlot", "copy failed");
            return null;
        }
    }

    private Set<String> f() {
        return new HashSet();
    }

    private Set<String> d(int i) {
        ArrayList arrayList = new ArrayList(i);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        for (vb vbVar : this.c) {
            if (vbVar != null) {
                vbVar.f();
                vbVar.s();
                if (i2 < i) {
                    arrayList.add(vbVar);
                    i2++;
                } else {
                    hashSet.add(vbVar.f());
                }
            }
        }
        this.c = arrayList;
        return hashSet;
    }

    private Set<String> b(int i) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        int i2 = 0;
        for (vb vbVar : this.c) {
            if (vbVar != null) {
                vbVar.f();
                vbVar.s();
                if (vbVar.s() == 0) {
                    arrayList.add(vbVar);
                } else if (i2 < i) {
                    arrayList.add(vbVar);
                    i2++;
                } else {
                    hashSet.add(vbVar.f());
                }
            }
        }
        this.c = arrayList;
        return hashSet;
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.j = jSONObject.optString("adtype");
        this.f17706a = jSONObject.optString("slotid");
        this.d = jSONObject.optInt("retcode30");
        JSONArray optJSONArray = jSONObject.optJSONArray("content");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            this.c = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    this.c.add(new vb(this.e, this.j, this.f17706a, optJSONObject));
                }
            }
        }
        jSONObject.remove("content");
        this.b = new SlotRecord(this.e, jSONObject);
    }

    private JSONObject c(SlotRecord slotRecord) {
        if (slotRecord == null || TextUtils.isEmpty(slotRecord.c())) {
            return new JSONObject();
        }
        try {
            return new JSONObject(slotRecord.c());
        } catch (JSONException unused) {
            HiAdLog.w("AdSlot", "create valued json obj err");
            return new JSONObject();
        }
    }

    public vg(String str, JSONObject jSONObject) {
        this.e = str;
        this.i = jSONObject;
        b(jSONObject);
    }

    public vg(String str, String str2) {
        this.e = str;
        this.f17706a = str2;
    }

    public vg(SlotRecord slotRecord) {
        if (slotRecord != null) {
            this.f17706a = slotRecord.e();
            this.e = slotRecord.d();
        }
        this.b = slotRecord;
    }
}

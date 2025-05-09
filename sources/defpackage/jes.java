package defpackage;

import android.database.Cursor;
import android.database.SQLException;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.KeyValDbManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class jes {
    public static final String c;
    private static volatile jes e;

    /* renamed from: a, reason: collision with root package name */
    private jeu f13775a;

    static {
        StringBuilder sb = new StringBuilder(16);
        sb.append("id integer primary key autoincrement,uid varchar(300),description varchar(300),url varchar(300),title varchar(300),time varchar(300)");
        c = sb.toString();
    }

    private jes() {
        LogUtil.a("SleepServiceDB", "create table");
        this.f13775a = jeu.d();
        e();
    }

    public static jes d() {
        if (e == null) {
            synchronized (jes.class) {
                if (e == null) {
                    LogUtil.a("SleepServiceDB", "mSleepServiceDb");
                    e = new jes();
                }
            }
        }
        return e;
    }

    private void e() {
        this.f13775a.b("coresleepservice", 1, c);
    }

    public List<jew> b(String str) {
        int i;
        Cursor rawQueryStorageData = this.f13775a.rawQueryStorageData(1, "select *  from " + ("module_" + this.f13775a.getModuleId() + "_coresleepservice") + " where   uid= '" + str + "'", null);
        ArrayList arrayList = new ArrayList(16);
        if (rawQueryStorageData == null) {
            LogUtil.b("SleepServiceDB", "get() query failed");
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(16);
        while (true) {
            if (!rawQueryStorageData.moveToNext()) {
                break;
            }
            jew jewVar = new jew();
            try {
                jewVar.e(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("title")));
                jewVar.c(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("time")));
                jewVar.b(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("uid")));
                jewVar.d(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("description")));
                jewVar.a(rawQueryStorageData.getString(rawQueryStorageData.getColumnIndex("url")));
            } catch (SQLException e2) {
                LogUtil.b("SleepServiceDB", "Exception = ", e2.getMessage());
            }
            arrayList2.add(0, jewVar);
        }
        rawQueryStorageData.close();
        arrayList.clear();
        for (i = 0; i < arrayList2.size() - 1; i++) {
            for (int size = arrayList2.size() - 1; size > i; size--) {
                if (((jew) arrayList2.get(size)).e().trim().equals(((jew) arrayList2.get(i)).e().trim())) {
                    arrayList2.remove(size);
                }
            }
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    public String c() {
        return KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
    }

    public String a() {
        List<jew> b = e.b(c());
        Iterator<jew> it = b.iterator();
        while (it.hasNext()) {
            LogUtil.a("SleepServiceDB", "data:", it.next().toString());
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        for (jew jewVar : b) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("time", jewVar.a());
                jSONObject2.put("description", jewVar.d());
                jSONObject2.put("title", jewVar.e());
                jSONObject2.put(JsbMapKeyNames.H5_USER_ID, jewVar.c());
                jSONObject2.put("url", jewVar.b());
                jSONArray.put(jSONObject2);
            } catch (JSONException e2) {
                LogUtil.a("SleepServiceDB", "put joson object:", e2.getMessage());
            }
        }
        try {
            jSONObject.put("sleepServiceType", jSONArray.toString());
        } catch (JSONException e3) {
            LogUtil.b("SleepServiceDB", "Exception = ", e3.getMessage());
        }
        return jSONObject.toString();
    }
}

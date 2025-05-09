package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.hz;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hms.scankit.b;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.operation.utils.CloudParamKeys;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class mz {

    /* renamed from: a, reason: collision with root package name */
    private hz f1345a;

    public mz(String str) {
        this.f1345a = null;
        try {
            this.f1345a = new hz.a(str, "1.0", "1.0.0").a(new String[]{CloudParamKeys.INFO}).a();
        } catch (hm unused) {
        }
    }

    public final void a(Context context, hz hzVar) {
        if (hzVar == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(hzVar);
        String jSONArray = a(arrayList).toString();
        if (TextUtils.isEmpty(jSONArray)) {
            return;
        }
        jh.a(context, this.f1345a, "rbck", jSONArray);
    }

    public final List<hz> a(Context context) {
        try {
            return a(new JSONArray(jh.a(context, this.f1345a, "rbck")));
        } catch (JSONException unused) {
            return new ArrayList();
        }
    }

    private static JSONArray a(List<hz> list) {
        if (list.size() == 0) {
            return new JSONArray();
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<hz> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(a(it.next()));
        }
        return jSONArray;
    }

    private static JSONObject a(hz hzVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(VideoPlayFlag.PLAY_IN_ALL, hzVar.a());
            jSONObject.put(b.H, hzVar.b());
            jSONObject.put("c", hzVar.c());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; hzVar.f() != null && i < hzVar.f().length; i++) {
                jSONArray.put(hzVar.f()[i]);
            }
            jSONObject.put(FitRunPlayAudio.PLAY_TYPE_D, jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static hz a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            String optString = jSONObject.optString(VideoPlayFlag.PLAY_IN_ALL);
            String optString2 = jSONObject.optString(b.H);
            String optString3 = jSONObject.optString("c");
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray(FitRunPlayAudio.PLAY_TYPE_D);
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.getString(i));
            }
            return new hz.a(optString, optString2, optString).a(optString3).a((String[]) arrayList.toArray(new String[0])).a();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static List<hz> a(JSONArray jSONArray) {
        hz hzVar;
        if (jSONArray.length() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                hzVar = a(jSONArray.getJSONObject(i));
            } catch (JSONException unused) {
                hzVar = null;
            }
            if (hzVar != null) {
                arrayList.add(hzVar);
            }
        }
        return arrayList;
    }
}

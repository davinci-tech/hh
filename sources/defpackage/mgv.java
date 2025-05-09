package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mgv {
    public static mka a(String str, int i) {
        mka mkaVar = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() != 0) {
                long j = 0;
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    long d = mdn.d("value", jSONObject);
                    long d2 = mdn.d("startTime", jSONObject);
                    if (mht.e(i, d2)) {
                        mka mkaVar2 = new mka();
                        try {
                            int e = mdn.e("source", jSONObject);
                            if (d > j) {
                                mkaVar2.b(d);
                                mkaVar2.d(d2);
                                mkaVar2.d(e);
                                j = d;
                            }
                            mkaVar = mkaVar2;
                        } catch (JSONException e2) {
                            e = e2;
                            mkaVar = mkaVar2;
                            LogUtil.b("PLGACHIEVE_AchieveAnnualPBDataHlr", "parseBestMotionByYear Exception:", e.getMessage());
                            return mkaVar;
                        }
                    }
                }
            }
        } catch (JSONException e3) {
            e = e3;
        }
        return mkaVar;
    }

    public static mke e(String str, int i) {
        mke mkeVar = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_AchieveAnnualPBDataHlr", "parseBestStep reportData is null");
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 0) {
                return null;
            }
            mke mkeVar2 = null;
            int i2 = 0;
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i3);
                    int e = mdn.e("value", jSONObject);
                    int e2 = mdn.e(ParsedFieldTag.RECORD_DAY, jSONObject);
                    if (mht.a(i, e2)) {
                        mke mkeVar3 = new mke();
                        if (e > i2) {
                            try {
                                mkeVar3.e(e);
                                mkeVar3.d(e2);
                                i2 = e;
                            } catch (JSONException e3) {
                                e = e3;
                                mkeVar = mkeVar3;
                                LogUtil.b("PLGACHIEVE_AchieveAnnualPBDataHlr", "parseBestStepByYear Exception:", e.getMessage());
                                return mkeVar;
                            }
                        }
                        mkeVar2 = mkeVar3;
                    }
                } catch (JSONException e4) {
                    e = e4;
                    mkeVar = mkeVar2;
                }
            }
            return mkeVar2;
        } catch (JSONException e5) {
            e = e5;
        }
    }

    public static ArrayList<String> b(int i, List<mcz> list) {
        long j;
        ArrayList<String> arrayList = new ArrayList<>(0);
        if (list == null) {
            return arrayList;
        }
        long b = mht.b(i, true);
        long b2 = mht.b(i, false);
        HashMap hashMap = new HashMap(list.size());
        for (mcz mczVar : list) {
            if (mczVar instanceof MedalLocation) {
                MedalLocation medalLocation = (MedalLocation) mczVar;
                if (medalLocation.acquireGainedCount() >= 1) {
                    try {
                        j = Long.parseLong(medalLocation.acquireMedalGainedTime());
                    } catch (NumberFormatException unused) {
                        LogUtil.b("PLGACHIEVE_AchieveAnnualPBDataHlr", "getData() NumberFormatException");
                        j = 0;
                    }
                    if (j >= b && j <= b2) {
                        hashMap.put(medalLocation.acquireMedalID(), Long.valueOf(j));
                    }
                }
            }
        }
        return b(hashMap);
    }

    private static ArrayList<String> b(Map<String, Long> map) {
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Long>>() { // from class: mgv.1
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                if (entry.getValue().longValue() - entry2.getValue().longValue() > 0) {
                    return -1;
                }
                if (entry.getValue().longValue() - entry2.getValue().longValue() != 0) {
                    return 1;
                }
                String key = entry.getKey();
                String key2 = entry2.getKey();
                int length = key.length();
                int length2 = key2.length();
                return length2 == length ? key2.compareTo(key) : length2 - length;
            }
        });
        ArrayList<String> arrayList2 = new ArrayList<>(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) ((Map.Entry) it.next()).getKey());
        }
        return arrayList2;
    }
}

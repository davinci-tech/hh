package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.main.stories.healthzone.model.GetSpecifiedAuthUserReq;
import com.huawei.ui.main.stories.healthzone.model.HasFollowListCallBack;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class rbt {
    public static boolean a(Context context, List<rbk> list, String str) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "isMemberListSameToLocal context is null");
            return true;
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
        LogUtil.a("HealthZone_HealthZoneUtil", "localList is ", b);
        if (e(context) == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "getLocalBelong is null");
            return true;
        }
        if (!e(context).equals(LoginInit.getInstance(context).getAccountInfo(1011))) {
            b = "";
        }
        if (TextUtils.isEmpty(b) && koq.b(list)) {
            LogUtil.a("HealthZone_HealthZoneUtil", "localMemberInfoCloudList and locallist is empty1");
            return true;
        }
        if (list == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList(10);
        for (rbk rbkVar : list) {
            rbd rbdVar = new rbd();
            rbdVar.a(rbkVar.a());
            rbdVar.e(rbkVar.b());
            rbdVar.d(rbkVar.e());
            arrayList.add(rbdVar);
            LogUtil.c("HealthZone_HealthZoneUtil", "localMemberInfo = ", Integer.valueOf(rbdVar.d()));
        }
        if (koq.b(arrayList) && TextUtils.isEmpty(b)) {
            LogUtil.a("HealthZone_HealthZoneUtil", "localMemberInfoCloudList and locallist is empty2");
            return true;
        }
        return b(new Gson().toJson(arrayList), b);
    }

    public static void c(Context context, List<rbk> list, String str) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "saveFollowListToLocal context is null");
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        if (list != null) {
            for (rbk rbkVar : list) {
                rbd rbdVar = new rbd();
                rbdVar.a(rbkVar.a());
                rbdVar.e(rbkVar.b());
                rbdVar.d(rbkVar.e());
                arrayList.add(rbdVar);
            }
        }
        if (koq.b(arrayList)) {
            SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str, "", (StorageParams) null);
        } else {
            SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str, new Gson().toJson(arrayList), (StorageParams) null);
        }
    }

    public static void a(Context context, String str, String str2) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "saveFollowListToLocal context is null");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str2, str, (StorageParams) null);
        }
    }

    public static String d(Context context, String str) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "saveFollowListToLocal context is null");
            return "";
        }
        return SharedPreferenceManager.b(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
    }

    public static boolean b(String str, String str2) {
        List<rbd> e = e(str);
        List<rbd> e2 = e(str2);
        Comparator<rbd> comparator = new Comparator<rbd>() { // from class: rbt.1
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(rbd rbdVar, rbd rbdVar2) {
                if (rbdVar.a() > rbdVar2.a()) {
                    return 1;
                }
                return rbdVar.a() < rbdVar2.a() ? -1 : 0;
            }
        };
        Collections.sort(e, comparator);
        Collections.sort(e2, comparator);
        return e.equals(e2);
    }

    public static List<rbd> e(String str) {
        JsonSyntaxException e;
        List<rbd> list;
        ArrayList arrayList = new ArrayList();
        try {
            list = (List) new Gson().fromJson(str, new TypeToken<List<rbd>>() { // from class: rbt.2
            }.getType());
            if (list != null) {
                return list;
            }
            try {
                return new ArrayList(10);
            } catch (JsonSyntaxException e2) {
                e = e2;
                LogUtil.b("HealthZone_HealthZoneUtil", "parseStringToFollowList:", LogAnonymous.b((Throwable) e));
                return list;
            }
        } catch (JsonSyntaxException e3) {
            e = e3;
            list = arrayList;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List<rbd> c(Context context, String str) {
        JsonSyntaxException e;
        List arrayList = new ArrayList(10);
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "getMemberListFromLocal context is null");
            return arrayList;
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
        if (e(context) == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "getLocalBelong is null");
            return arrayList;
        }
        if (!e(context).equals(LoginInit.getInstance(context).getAccountInfo(1011))) {
            return arrayList;
        }
        if (b == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "localList is null");
            return arrayList;
        }
        try {
            List list = (List) new Gson().fromJson(b, new TypeToken<List<rbd>>() { // from class: rbt.4
            }.getType());
            if (list != null) {
                return list;
            }
            try {
                return new ArrayList(10);
            } catch (JsonSyntaxException e2) {
                e = e2;
                arrayList = list;
                LogUtil.b("HealthZone_HealthZoneUtil", "getMemberListFromLocal:", LogAnonymous.b((Throwable) e));
                return arrayList;
            }
        } catch (JsonSyntaxException e3) {
            e = e3;
        }
    }

    public static void a(Context context, rbe rbeVar) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "saveLocalImage context is null");
            return;
        }
        List<rbe> c = c(context);
        if (rbeVar == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "saveLocalImage localHealthZoneImage is null");
            return;
        }
        boolean z = false;
        for (rbe rbeVar2 : c) {
            if (rbeVar2 != null && rbeVar.d() == rbeVar2.d()) {
                rbeVar2.c(rbeVar.a());
                z = true;
            }
        }
        if (!z) {
            c.add(rbeVar);
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (rbe rbeVar3 : c) {
                if (rbeVar3 != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mHuid", rbeVar3.d());
                    jSONObject.put("mImageUrl", rbeVar3.a());
                    jSONArray.put(jSONObject);
                }
            }
            SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "key_health_zone_image", jSONArray.toString(), (StorageParams) null);
        } catch (JSONException e) {
            LogUtil.b("HealthZone_HealthZoneUtil", "saveLocalImage", LogAnonymous.b((Throwable) e));
        }
    }

    public static String e(Context context, long j) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "getLocalImageUrl context is null");
            return null;
        }
        for (rbe rbeVar : c(context)) {
            if (rbeVar != null && j == rbeVar.d()) {
                return rbeVar.a();
            }
        }
        return null;
    }

    private static List<rbe> c(Context context) {
        String b = SharedPreferenceManager.b(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "key_health_zone_image");
        ArrayList arrayList = new ArrayList(10);
        try {
            JSONArray jSONArray = new JSONArray(b);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add((rbe) new Gson().fromJson(jSONArray.getString(i), rbe.class));
            }
        } catch (JSONException e) {
            LogUtil.b("HealthZone_HealthZoneUtil", "getLocalImageList:", LogAnonymous.b((Throwable) e));
        }
        return arrayList;
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "saveLocalBelong context is null");
        } else {
            SharedPreferenceManager.e(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "key_health_zone_belong", String.valueOf(LoginInit.getInstance(context).getAccountInfo(1011)), (StorageParams) null);
        }
    }

    public static String e(Context context) {
        if (context == null) {
            LogUtil.h("HealthZone_HealthZoneUtil", "getLocalBelong context is null");
            return "";
        }
        return SharedPreferenceManager.b(context, String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "key_health_zone_belong");
    }

    public static String b(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), str);
    }

    public static void e(final HasFollowListCallBack hasFollowListCallBack) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rbt.5
            @Override // java.lang.Runnable
            public void run() {
                rbt.a(HasFollowListCallBack.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final HasFollowListCallBack hasFollowListCallBack) {
        rbm.a(new ICloudOperationResult<raz>() { // from class: rbt.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void operationResult(raz razVar, String str, boolean z) {
                if (z) {
                    if (razVar == null) {
                        LogUtil.h("HealthZone_HealthZoneUtil", "getUserListByAuthUser getFollowRelationsRsp is null");
                        rbt.b(HasFollowListCallBack.this);
                        return;
                    }
                    List<rax> d = razVar.d();
                    if (koq.b(d)) {
                        LogUtil.a("HealthZone_HealthZoneUtil", "getUserListByAuthUser getFollowRelations is empty");
                        rbt.b(HasFollowListCallBack.this);
                        return;
                    }
                    for (rax raxVar : d) {
                        if (raxVar != null && raxVar.c() == 2) {
                            HasFollowListCallBack.this.hasFollowList(true);
                            return;
                        }
                    }
                }
                rbt.b(HasFollowListCallBack.this);
            }
        });
    }

    public static void b(final HasFollowListCallBack hasFollowListCallBack) {
        rbm.a(new GetSpecifiedAuthUserReq(), new ICloudOperationResult<ray>() { // from class: rbt.9
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void operationResult(ray rayVar, String str, boolean z) {
                if (!z) {
                    HasFollowListCallBack.this.hasFollowList(false);
                    return;
                }
                if (rayVar == null) {
                    HasFollowListCallBack.this.hasFollowList(false);
                    LogUtil.b("HealthZone_HealthZoneUtil", "getUserListByFollowUser getAuthRelationsRsp is null");
                } else if (rayVar.a() == null) {
                    HasFollowListCallBack.this.hasFollowList(false);
                    LogUtil.a("HealthZone_HealthZoneUtil", "getUserListByFollowUser getAuthRelations is empty");
                } else {
                    HasFollowListCallBack.this.hasFollowList(true);
                }
            }
        });
    }

    public static boolean b() {
        String b = b("sIsFirstHealth");
        return TextUtils.isEmpty(b) || !b.equals("1");
    }

    public static Map<String, Integer> b(List<String> list) {
        if (list == null || list.size() == 0) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap();
        for (String str : list) {
            Integer num = (Integer) hashMap.get(str);
            int i = 1;
            if (num != null) {
                i = 1 + num.intValue();
            }
            hashMap.put(str, Integer.valueOf(i));
        }
        return hashMap;
    }

    public static boolean d(List<rbd> list, Map<String, Integer> map) {
        if (list == null || list.size() == 0 || map == null || map.size() == 0) {
            return false;
        }
        List asList = Arrays.asList(HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, HealthZonePushReceiver.FAMILY_CARE_NOTIFY, "4000");
        int i = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (asList.contains(entry.getKey())) {
                i += entry.getValue().intValue();
            }
        }
        return i > 0 && list.size() == i;
    }
}

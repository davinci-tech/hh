package defpackage;

import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.main.R$string;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class pqi {
    private static void e(final int i, final HttpResCallback httpResCallback) {
        final HashMap<String, String> e = pfe.e();
        e.put(CommonUtil.PAGE_TYPE, String.valueOf(i));
        e.put("maxSize", "6");
        e.put(CloudParamKeys.CLIENT_TYPE, String.valueOf(nsn.b()));
        e.put("infoType", String.valueOf(2));
        if (i == 3) {
            e.put("decompressType", String.valueOf(-1));
        }
        final HashMap<String, String> a2 = pfe.a();
        GRSManager.a(BaseApplication.getContext()).e("messageCenterUrl", new GrsQueryCallback() { // from class: pqi.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                String str2;
                LogUtil.a("FavoritesRequestUtils", "requestFavoritesCloudData onCallBackSuccess");
                int i2 = i;
                if (i2 == 1) {
                    str2 = str + "/messageCenter/getFavoriteAudios";
                } else if (i2 == 3) {
                    str2 = str + "/messageCenter/v2/getFavoriteAudios";
                } else {
                    LogUtil.a("page type error", new Object[0]);
                    str2 = "";
                }
                String str3 = str2 + CommonUtil.getUrlSuffix();
                LogUtil.a("FavoritesRequestUtils", "url: ", str3);
                LogUtil.a("FavoritesRequestUtils", "params: ", e.toString());
                LogUtil.a("FavoritesRequestUtils", "headers: ", a2.toString());
                jei.d(str3, e, a2, httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.h("FavoritesRequestUtils", "requestFavoritesCloudData grs get url errorCode ", Integer.valueOf(i2));
            }
        });
    }

    public static void c(final int i, final CommonUiBaseResponse commonUiBaseResponse) {
        if (!lop.c(BaseApplication.getContext())) {
            LogUtil.h("FavoritesRequestUtils", "initFavoritesData network not connect");
            b(i, a(i), commonUiBaseResponse, true);
        } else {
            e(i, new HttpResCallback() { // from class: pqn
                @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                public final void onFinished(int i2, String str) {
                    pqi.c(CommonUiBaseResponse.this, i, i2, str);
                }
            });
        }
    }

    static /* synthetic */ void c(CommonUiBaseResponse commonUiBaseResponse, int i, int i2, String str) {
        if (commonUiBaseResponse == null) {
            return;
        }
        if (i2 != 200 || TextUtils.isEmpty(str)) {
            LogUtil.h("FavoritesRequestUtils", "getFavoriteAudios resCode = ", Integer.valueOf(i2), " or result is empty");
            b(i, a(i), commonUiBaseResponse, true);
            commonUiBaseResponse.onResponse(-1, null);
            return;
        }
        b(i, str, commonUiBaseResponse, false);
    }

    private static void b(int i, String str, CommonUiBaseResponse commonUiBaseResponse, boolean z) {
        LogUtil.a("FavoritesRequestUtils", "parseFavoritesData getFavoriteAudios result = ", str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("FavoritesRequestUtils", "parseFavoritesData result is empty");
            commonUiBaseResponse.onResponse(-1, null);
        } else if (i == 1) {
            b(str, commonUiBaseResponse, z);
        } else if (i != 3) {
            LogUtil.h("FavoritesRequestUtils", "parseFavoritesData not match type ", Integer.valueOf(i));
        } else {
            e(str, commonUiBaseResponse, z);
        }
    }

    private static void b(String str, CommonUiBaseResponse commonUiBaseResponse, boolean z) {
        try {
            commonUiBaseResponse.onResponse(0, (ppl) HiJsonUtil.e(str, ppl.class));
            a(str);
        } catch (JsonSyntaxException unused) {
            commonUiBaseResponse.onResponse(-1, "sleep parse json fail");
            if (z) {
                return;
            }
            b(1, a(1), commonUiBaseResponse, true);
        }
    }

    private static void e(String str, CommonUiBaseResponse commonUiBaseResponse, boolean z) {
        try {
            commonUiBaseResponse.onResponse(0, (ppl) HiJsonUtil.e(str, ppl.class));
            c(str);
        } catch (JsonSyntaxException unused) {
            commonUiBaseResponse.onResponse(-1, "pressure parse json fail");
            if (z) {
                return;
            }
            b(3, a(3), commonUiBaseResponse, true);
        }
    }

    private static void a(String str) {
        if (str == null || !str.equals(a(1))) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_COIN_DROP), "sleep_audios_sp_key", str, new StorageParams());
        }
    }

    private static void c(String str) {
        if (str == null || !str.equals(a(3))) {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_COIN_DROP), "pressure_audios_sp_key", str, new StorageParams());
        }
    }

    private static String a(int i) {
        if (i == 1) {
            return d();
        }
        return i == 3 ? c() : "";
    }

    private static String d() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_COIN_DROP), "sleep_audios_sp_key");
    }

    private static String c() {
        return SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_COIN_DROP), "pressure_audios_sp_key");
    }

    private static String b(List<ppn> list, String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (ppn ppnVar : list) {
            if (ppnVar != null) {
                if (TextUtils.isEmpty(ppnVar.c()) || TextUtils.isEmpty(ppnVar.d())) {
                    LogUtil.c("FavoritesRequestUtils", "getSleepConfiguredData icon or url is empty");
                } else {
                    SingleGridContent.Builder builder = new SingleGridContent.Builder();
                    builder.setPicture(ppnVar.c());
                    builder.setLinkValue(b(z, ppnVar));
                    builder.setDescriptionVisibility(true);
                    builder.setTheme(ppnVar.f());
                    builder.setThemeVisibility(true);
                    SingleGridContent singleGridContent = new SingleGridContent(builder);
                    singleGridContent.setHeatCount(ppnVar.b());
                    singleGridContent.setLabelType(ppnVar.i());
                    singleGridContent.setVip(ppnVar.g());
                    d(singleGridContent, ppnVar);
                    arrayList.add(singleGridContent);
                }
            }
        }
        if (arrayList.isEmpty()) {
            LogUtil.h("FavoritesRequestUtils", "getCardContent list is empty");
            return "";
        }
        GridTemplate gridTemplate = new GridTemplate();
        gridTemplate.setLinkValue(c(z, str));
        gridTemplate.setGridContents(arrayList);
        gridTemplate.setSleepAudioType(3);
        gridTemplate.setNameVisibility(true);
        gridTemplate.setName(BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_my_favorites));
        String json = new GsonBuilder().disableHtmlEscaping().create().toJson(gridTemplate);
        LogUtil.c("FavoritesRequestUtils", "getSleepConfiguredData item ", json);
        return json;
    }

    private static String b(boolean z, ppn ppnVar) {
        String str;
        if ("1".equals(ppnVar.a())) {
            LogUtil.a("FavoritesRequestUtils", "series detail utl not need tranfer");
            return ppnVar.d();
        }
        int e = ppnVar.e();
        if (e == 0) {
            LogUtil.a("FavoritesRequestUtils", "id error: ", Integer.valueOf(e));
            return "";
        }
        if (z) {
            str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&id=" + e + "&type=sleepAudio&statusBarTextBlack&isImmerse";
        } else {
            str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&id=" + e + "&type=decompressAudio&statusBarTextBlack&isImmerse";
        }
        LogUtil.a("FavoritesRequestUtils", "tansferDetailUrl url: ", str);
        return str;
    }

    private static void d(SingleGridContent singleGridContent, ppn ppnVar) {
        singleGridContent.setInfoType(ppnVar.a());
        if (ppnVar.g() == 1) {
            singleGridContent.setCorner(nsf.h(R$string.IDS_vip));
            singleGridContent.setCornerColor("#F2BA49");
            singleGridContent.setCornerVisibility(true);
        } else if (ppnVar.g() == 2) {
            singleGridContent.setCorner(nsf.h(R$string.IDS_sug_series_course_pay));
            singleGridContent.setCornerColor("#FF6522");
            singleGridContent.setCornerVisibility(true);
        }
    }

    public static Map<Integer, ResourceResultInfo> b(ppl pplVar) {
        HashMap hashMap = new HashMap();
        List<ppn> d = d(pplVar);
        if (koq.b(d)) {
            LogUtil.h("FavoritesRequestUtils", "getFavoritesResult sleepAudios is null");
            return hashMap;
        }
        if (pplVar == null) {
            LogUtil.h("FavoritesRequestUtils", "favoritesModel is null");
            return hashMap;
        }
        String b = b(d, pplVar.b(), true);
        if (b.isEmpty()) {
            LogUtil.h("FavoritesRequestUtils", "getFavoritesResult json is empty");
            return hashMap;
        }
        ResourceBriefInfo resourceBriefInfo = new ResourceBriefInfo(new ResourceBriefInfo.Builder().setContentType(79).setContent(new ResourceContentBase(new ResourceContentBase.Builder().setContent(b))).setPriority(pplVar.c()));
        ArrayList arrayList = new ArrayList();
        arrayList.add(resourceBriefInfo);
        ResourceResultInfo.Builder totalNum = new ResourceResultInfo.Builder().setResources(arrayList).setTotalNum(1);
        totalNum.setResources(arrayList);
        hashMap.put(Integer.valueOf(WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED), new ResourceResultInfo(totalNum));
        return hashMap;
    }

    public static Map<Integer, ResourceResultInfo> c(ppl pplVar) {
        HashMap hashMap = new HashMap();
        List<ppn> d = d(pplVar);
        if (koq.b(d)) {
            LogUtil.h("FavoritesRequestUtils", "getFavoritesResult sleepAudios is null");
            return hashMap;
        }
        if (pplVar == null) {
            LogUtil.h("FavoritesRequestUtils", "favoritesModel is null");
            return hashMap;
        }
        String b = b(d, pplVar.b(), false);
        if (b.isEmpty()) {
            LogUtil.h("FavoritesRequestUtils", "getFavoritesResult json is empty");
            return hashMap;
        }
        ResourceBriefInfo resourceBriefInfo = new ResourceBriefInfo(new ResourceBriefInfo.Builder().setContentType(92).setContent(new ResourceContentBase(new ResourceContentBase.Builder().setContent(b))).setPriority(pplVar.c()));
        ArrayList arrayList = new ArrayList();
        arrayList.add(resourceBriefInfo);
        ResourceResultInfo.Builder totalNum = new ResourceResultInfo.Builder().setResources(arrayList).setTotalNum(1);
        totalNum.setResources(arrayList);
        hashMap.put(4007, new ResourceResultInfo(totalNum));
        return hashMap;
    }

    public static CopyOnWriteArrayList<cdy> a(ppl pplVar) {
        List<ppn> d = d(pplVar);
        CopyOnWriteArrayList<cdy> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        if (koq.b(d)) {
            LogUtil.h("FavoritesRequestUtils", "getSleepConfiguredData sleepAudios is null");
            return copyOnWriteArrayList;
        }
        LogUtil.a("FavoritesRequestUtils", "getSleepConfiguredData sleepAudios size ", Integer.valueOf(d.size()));
        cdy cdyVar = new cdy();
        cdyVar.d(1);
        cdyVar.e(1);
        cdyVar.c(1);
        cdyVar.a(1);
        cdyVar.b(14);
        cdyVar.h(2);
        cdyVar.d(BaseApplication.getContext().getResources().getString(R$string.IDS_core_sleep_my_favorites));
        if (pplVar != null) {
            cdyVar.c(pplVar.b());
            cdyVar.f(pplVar.c());
        }
        for (ppn ppnVar : d) {
            if (ppnVar != null) {
                cdu cduVar = new cdu();
                if (TextUtils.isEmpty(ppnVar.c()) || TextUtils.isEmpty(ppnVar.d())) {
                    LogUtil.c("FavoritesRequestUtils", "getSleepConfiguredData icon or url is empty");
                } else {
                    cduVar.a(ppnVar.d());
                    cduVar.h(ppnVar.c());
                    cduVar.g(3);
                    cduVar.f(ppnVar.f());
                    cduVar.j(ppnVar.b());
                    cduVar.m(ppnVar.i());
                    cdyVar.e(cduVar);
                    LogUtil.c("FavoritesRequestUtils", "getSleepConfiguredData item ", cduVar.toString());
                }
            }
        }
        copyOnWriteArrayList.add(cdyVar);
        LogUtil.a("FavoritesRequestUtils", "getSleepConfiguredData list size ", Integer.valueOf(copyOnWriteArrayList.size()));
        return copyOnWriteArrayList;
    }

    private static List<ppn> d(ppl pplVar) {
        if (pplVar == null) {
            LogUtil.h("FavoritesRequestUtils", "getSleepAudioList favoritesModel is null");
            return null;
        }
        if (pplVar.d() != 0) {
            LogUtil.h("FavoritesRequestUtils", "getSleepAudioList resultCode ", Integer.valueOf(pplVar.d()));
            return null;
        }
        List<ppn> e = pplVar.e();
        if (koq.b(e)) {
            LogUtil.h("FavoritesRequestUtils", "getSleepAudioList sleepAudios is empty");
            return null;
        }
        LogUtil.a("FavoritesRequestUtils", "getSleepAudioList sleepAudios ", Integer.valueOf(e.size()));
        e(e);
        return e;
    }

    private static void e(List<ppn> list) {
        ArrayList<ppn> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (ppn ppnVar : arrayList) {
            if ("1".equals(ppnVar.a())) {
                arrayList2.add(ppnVar);
            } else if ("0".equals(ppnVar.a())) {
                arrayList3.add(ppnVar);
            } else {
                LogUtil.a("FavoritesRequestUtils", "audio InfoType is invalid");
            }
        }
        list.clear();
        list.addAll(arrayList2);
        list.addAll(arrayList3);
    }

    private static String c(boolean z, String str) {
        return z ? "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioFavoriteList&type=sleepAudio&statusBarTextBlack&isImmerse" : "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioFavoriteList&type=decompressAudio&statusBarTextBlack&isImmerse";
    }
}

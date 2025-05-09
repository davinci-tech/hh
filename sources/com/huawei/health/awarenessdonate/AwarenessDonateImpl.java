package com.huawei.health.awarenessdonate;

import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.agconnect.credential.obs.c;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.kit.awareness.Awareness;
import com.huawei.hms.kit.awareness.DonateClient;
import com.huawei.hms.kit.awareness.donate.DonateResponse;
import com.huawei.hms.kit.awareness.donate.message.Content;
import com.huawei.hms.kit.awareness.donate.message.ContentData;
import com.huawei.hms.kit.awareness.donate.message.Message;
import com.huawei.hms.kit.awareness.donate.message.Session;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bzn;
import defpackage.nrv;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = AwarenessDonateApi.class)
@Singleton
/* loaded from: classes3.dex */
public class AwarenessDonateImpl implements AwarenessDonateApi {
    public static final String AWARENESS_FEATURE_MODULE_ID = "awareness_feature_module";
    private static final String CONTENT_VERSION = "2.1";
    private static final int DELETE_OPERATION = 1;
    private static final int DONATE_OPERATION = 0;
    private static final int MIN_TIME_PERIOD = 600000;
    private static final String NAMESPACE = "event";
    private static final int ONE_MINUTE = 60000;
    private static final String RELEASE_TAG = "R_AwarenessDonateImpl";
    private static final String REMOVE_DONATE_DATA = "controlProtocol";
    private static final String REMOVE_DONATE_DATA_NAME = "removeData";
    private static final String SECURE_ALGORITHM = "SHA1PRNG";
    private static final String TAG = "AwarenessDonateImpl";

    @Override // com.huawei.health.awarenessdonate.AwarenessDonateApi
    public boolean isSupportDonate() {
        if (!CommonUtil.az()) {
            LogUtil.h(TAG, "is not harmonyOS, not support");
            return false;
        }
        boolean a2 = AuthorizationUtils.a(BaseApplication.e());
        LogUtil.a(TAG, "isOpenDonate: ", Boolean.valueOf(a2));
        return a2;
    }

    @Override // com.huawei.health.awarenessdonate.AwarenessDonateApi
    public void donateFeature(bzn bznVar) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter donateFeature");
        dealData(bznVar, 0);
    }

    @Override // com.huawei.health.awarenessdonate.AwarenessDonateApi
    public void deleteDonateFeature(bzn bznVar) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter deleteDonateFeature");
        dealData(bznVar, 1);
    }

    private void dealData(bzn bznVar, int i) {
        LogUtil.c(TAG, "DonateBean: ", bznVar);
        DonateClient donateClient = Awareness.getDonateClient(BaseApplication.e());
        Session session = new Session();
        session.setMessageName(bznVar.l());
        session.setMessageId(bznVar.o());
        session.setDeviceModel(bznVar.b());
        session.setDeviceCategory(bznVar.a());
        session.setDeviceName(bznVar.f());
        List<ContentData> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList<bzn> arrayList3 = new ArrayList();
        if (i == 0) {
            for (String str : SharedPreferenceManager.d(BaseApplication.e(), AWARENESS_FEATURE_MODULE_ID)) {
                bzn donateBeanFromSp = getDonateBeanFromSp(str);
                if (donateBeanFromSp == null) {
                    ReleaseLogUtil.d(RELEASE_TAG, "sp value is null, key is", str);
                } else if (str.equals(bznVar.h())) {
                    LogUtil.a(TAG, "already donate feature, not add old one into list, continue");
                    ArrayList arrayList4 = new ArrayList(1);
                    arrayList4.add(donateBeanFromSp);
                    Iterator<bzn.e> it = dealConflict(arrayList4, bznVar).get(0).r().iterator();
                    while (it.hasNext()) {
                        bznVar.r().add(it.next());
                    }
                    LogUtil.c(TAG, "after add oldBean: ", bznVar);
                } else {
                    arrayList2.add(donateBeanFromSp);
                }
            }
            LogUtil.a(TAG, "before dealConflict: ", arrayList2);
            arrayList3.addAll(dealConflict(arrayList2, bznVar));
            LogUtil.a(TAG, "after dealConflict: ", arrayList3);
            for (bzn bznVar2 : arrayList3) {
                if (bznVar2.r().isEmpty()) {
                    ReleaseLogUtil.e(RELEASE_TAG, "timePeriods is empty: ", bznVar2.h());
                    long currentTimeMillis = System.currentTimeMillis() + 60000;
                    bznVar2.r().add(new bzn.e(currentTimeMillis, currentTimeMillis));
                }
                arrayList.add(getContentData(bznVar2));
            }
        } else if (i == 1) {
            SharedPreferenceManager.d(AWARENESS_FEATURE_MODULE_ID, bznVar.h());
            long currentTimeMillis2 = System.currentTimeMillis() + 60000;
            bznVar.r().add(new bzn.e(currentTimeMillis2, currentTimeMillis2));
            arrayList = Arrays.asList(getContentData(bznVar));
        }
        Content content = new Content();
        content.setContentVersion(CONTENT_VERSION);
        content.setContentData(arrayList);
        Message message = new Message();
        message.setSession(session);
        message.setContent(content);
        donateClient.sendMessage(message).addOnSuccessListener(new OnSuccessListener<DonateResponse>() { // from class: com.huawei.health.awarenessdonate.AwarenessDonateImpl.2
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(DonateResponse donateResponse) {
                ReleaseLogUtil.e(AwarenessDonateImpl.RELEASE_TAG, "sendMessage success: ", donateResponse.getDonateStatus());
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    AwarenessDonateImpl.this.saveDonateBeanToSp((bzn) it2.next());
                }
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.awarenessdonate.AwarenessDonateImpl.4
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ReleaseLogUtil.e(AwarenessDonateImpl.RELEASE_TAG, "sendMessage failed: ", exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDonateBeanToSp(bzn bznVar) {
        SharedPreferenceManager.c(AWARENESS_FEATURE_MODULE_ID, bznVar.h(), nrv.a(bznVar));
    }

    private bzn getDonateBeanFromSp(String str) {
        LogUtil.a(TAG, "enter getContentDataFromSp");
        String e = SharedPreferenceManager.e(AWARENESS_FEATURE_MODULE_ID, str, (String) null);
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.e(RELEASE_TAG, "key: ", str, ", value is null");
            return null;
        }
        bzn bznVar = (bzn) nrv.b(e, bzn.class);
        LogUtil.a(TAG, "key: ", str, ", getDonateBeanFromSp: ", bznVar);
        return bznVar;
    }

    private ContentData getContentData(bzn bznVar) {
        JSONArray jSONArray;
        List<bzn.e> r;
        ContentData contentData = new ContentData();
        contentData.setHeader(e.j, "event");
        contentData.setHeader("name", "featurePrediction");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put("timeZone", bznVar.s());
            jSONObject.put(c.b, bznVar.e());
            jSONObject.put("effectiveTime", bznVar.j());
            jSONObject.put("expirationTime", bznVar.g());
            jSONObject2.put("bundleName", bznVar.c());
            jSONObject2.put("moduleName", bznVar.k());
            jSONObject2.put("abilityName", bznVar.d());
            jSONObject2.put("formName", bznVar.n());
            jSONObject2.put("formDimension", bznVar.m());
            jSONObject3.put("featureName", bznVar.h());
            jSONArray = new JSONArray();
            r = bznVar.r();
        } catch (JSONException e) {
            LogUtil.h(TAG, "dataReport JSONException: ", e);
        }
        if (r == null) {
            ReleaseLogUtil.e(RELEASE_TAG, "in donateFeature, but timePeriods is null, leave dealData");
            return contentData;
        }
        for (bzn.e eVar : r) {
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("startTime", eVar.c());
            jSONObject4.put("endTime", eVar.e());
            jSONArray.put(jSONObject4);
        }
        jSONObject3.put("timelineInfo", jSONArray);
        contentData.setPayload("eventCommonInfo", jSONObject);
        contentData.setPayload("predictInfo", jSONObject3);
        contentData.setPayload("faFormInfo", jSONObject2);
        LogUtil.c(TAG, "contentData: ", contentData.toJson());
        return contentData;
    }

    private List<bzn> dealConflict(List<bzn> list, bzn bznVar) {
        if (list.isEmpty()) {
            LogUtil.a(TAG, "no oldDonateBeans, leave dealConflict");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(bznVar);
            return arrayList;
        }
        CopyOnWriteArrayList<bzn> copyOnWriteArrayList = new CopyOnWriteArrayList(list);
        int i = bznVar.i();
        Iterator<bzn.e> it = bznVar.r().iterator();
        while (it.hasNext()) {
            bzn.e next = it.next();
            for (bzn bznVar2 : copyOnWriteArrayList) {
                if (compareOldDonateBeans(bznVar2, next, it, i, bznVar2.i())) {
                    break;
                }
            }
        }
        copyOnWriteArrayList.add(bznVar);
        return copyOnWriteArrayList;
    }

    private boolean compareOldDonateBeans(bzn bznVar, bzn.e eVar, Iterator it, int i, int i2) {
        bzn.e eVar2 = eVar;
        Iterator<bzn.e> it2 = bznVar.r().iterator();
        while (it2.hasNext()) {
            bzn.e next = it2.next();
            if (next.e() <= System.currentTimeMillis()) {
                LogUtil.a(TAG, "timePeriod expiration: ", next, ", featureName: ", bznVar.h());
                it2.remove();
            } else if (isConflict(next, eVar2)) {
                long c = next.c();
                long e = next.e();
                long c2 = eVar.c();
                long e2 = eVar.e();
                long max = Math.max(c, c2);
                long min = Math.min(e, e2);
                if (i >= i2) {
                    ReleaseLogUtil.e(RELEASE_TAG, "newFeatureType is bigger than old");
                    if (max == c) {
                        c = min;
                    }
                    if (min == e) {
                        e = max;
                    }
                    if (e - c < 600000) {
                        ReleaseLogUtil.e(RELEASE_TAG, "timePeriod is less than 10 min, remove old timePeriod");
                        it2.remove();
                    } else {
                        next.d(c, e);
                    }
                    eVar2 = eVar;
                } else {
                    ReleaseLogUtil.e(RELEASE_TAG, "oldFeatureType is bigger than new");
                    if (max == c2) {
                        c = min;
                    }
                    if (min == e2) {
                        e = max;
                    }
                    if (e - c < 600000) {
                        ReleaseLogUtil.e(RELEASE_TAG, "timePeriod is less than 10 min, remove new timePeriod");
                        it.remove();
                        return true;
                    }
                    eVar2 = eVar;
                    eVar2.d(c, e);
                }
            }
        }
        return false;
    }

    private boolean isConflict(bzn.e eVar, bzn.e eVar2) {
        return eVar.c() <= eVar2.c() ? eVar.e() > eVar2.c() : eVar2.e() > eVar.c();
    }

    @Override // com.huawei.health.awarenessdonate.AwarenessDonateApi
    public void removeData() {
        SecureRandom secureRandom;
        DonateClient donateClient = Awareness.getDonateClient(BaseApplication.e());
        Session session = new Session();
        session.setMessageName(REMOVE_DONATE_DATA);
        try {
            secureRandom = SecureRandom.getInstance(SECURE_ALGORITHM);
        } catch (NoSuchAlgorithmException unused) {
            secureRandom = new SecureRandom();
            ReleaseLogUtil.c(RELEASE_TAG, "NoSuchAlgorithmException");
        }
        session.setMessageId(String.valueOf(secureRandom.nextInt(90000000) + ExceptionCode.CRASH_EXCEPTION));
        ContentData contentData = new ContentData();
        contentData.setHeader(e.j, "event");
        contentData.setHeader("name", REMOVE_DONATE_DATA_NAME);
        contentData.setPayload("eventCommonInfo", contentData.getDefaultEventCommonInfo());
        Content content = new Content();
        content.setContentVersion(CONTENT_VERSION);
        content.setContentData(Collections.singletonList(contentData));
        Message message = new Message();
        message.setSession(session);
        message.setContent(content);
        donateClient.sendMessage(message).addOnSuccessListener(new OnSuccessListener<DonateResponse>() { // from class: com.huawei.health.awarenessdonate.AwarenessDonateImpl.5
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(DonateResponse donateResponse) {
                ReleaseLogUtil.e(AwarenessDonateImpl.RELEASE_TAG, "removeData success");
                SharedPreferenceManager.e(AwarenessDonateImpl.AWARENESS_FEATURE_MODULE_ID, (String[]) SharedPreferenceManager.d(BaseApplication.e(), AwarenessDonateImpl.AWARENESS_FEATURE_MODULE_ID).toArray(new String[0]));
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.awarenessdonate.AwarenessDonateImpl.3
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                ReleaseLogUtil.d(AwarenessDonateImpl.RELEASE_TAG, "removeData failed: ", exc);
            }
        });
    }
}

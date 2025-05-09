package com.huawei.featureuserprofile.healthrecord.js;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.featureuserprofile.healthrecord.HealthRecordAlarmReceiver;
import com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk;
import com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.userprofilemgr.model.HealthRecordCbk;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.DeleteHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByReversedOrderReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByReversedOrderRsp;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.arx;
import defpackage.brg;
import defpackage.brh;
import defpackage.brm;
import defpackage.bro;
import defpackage.gmt;
import defpackage.ius;
import defpackage.iut;
import defpackage.jah;
import defpackage.jbs;
import health.compact.a.KeyValDbManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@H5ProService(name = HealthRecordsJsApi.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class HealthRecordsJsApi {
    private static final String KEY_DEVICE_CODE = "key_device_code";
    private static final String QUERY_TIME = "query_time";
    private static final int QUERY_TIME_DEFAULT = 10;
    private static final String RELEASE_TAG = "R_HealthRecordsJsApi";
    private static final String TAG = "HealthRecordsJsApi";
    private static final int TYPE_ID = 800001;
    private static bro hiCloudUtil = bro.e(BaseApplication.e());

    @H5ProMethod(name = "setReminder")
    public static void setReminder(brh brhVar, HealthRecordCbk<String> healthRecordCbk) {
        if (brhVar == null || brhVar.d() == 0) {
            healthRecordCbk.onFailure(-1, "healthReport is null");
            ReleaseLogUtil.d(RELEASE_TAG, "setHealthRecordRemind recordContent is null, return");
            return;
        }
        Intent intent = new Intent(arx.b(), (Class<?>) HealthRecordAlarmReceiver.class);
        if (brhVar.i() != 0) {
            brg.tK_(intent, brhVar, healthRecordCbk);
        } else {
            brg.tI_(brhVar.d(), intent, healthRecordCbk);
        }
    }

    @H5ProMethod(name = "uploadFile")
    public static void uploadFile(final String str, String str2, final HealthRecordCbk<String> healthRecordCbk) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: brl
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecordsJsApi.hiCloudUtil.e(str, new HiCloudCbk<String>() { // from class: com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi.3
                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str3) {
                        HealthRecordCbk.this.onSuccess(str3);
                    }

                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    public void onFailure(int i, String str3) {
                        ReleaseLogUtil.c(HealthRecordsJsApi.RELEASE_TAG, "uploadFile fail");
                        HealthRecordsJsApi.dealError(i, str3, HealthRecordCbk.this);
                    }
                });
            }
        });
    }

    @H5ProMethod(name = "getFileThumnail")
    public static void getFileThumnail(final String str, final HealthRecordCbk<String> healthRecordCbk) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bru
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecordsJsApi.hiCloudUtil.c(str, new HiCloudCbk<String>() { // from class: com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi.4
                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str2) {
                        HealthRecordCbk.this.onSuccess(str2);
                    }

                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    public void onFailure(int i, String str2) {
                        ReleaseLogUtil.c(HealthRecordsJsApi.RELEASE_TAG, "getFileThumnail fail:", Integer.valueOf(i));
                        HealthRecordsJsApi.dealError(i, str2, HealthRecordCbk.this);
                    }
                });
            }
        });
    }

    @H5ProMethod(name = "getFile")
    public static void getFile(final String str, final HealthRecordCbk<String> healthRecordCbk) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: brs
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecordsJsApi.hiCloudUtil.b(str, new HiCloudCbk<String>() { // from class: com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi.2
                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str2) {
                        HealthRecordCbk.this.onSuccess(str2);
                    }

                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    public void onFailure(int i, String str2) {
                        ReleaseLogUtil.c(HealthRecordsJsApi.RELEASE_TAG, "getFile fail:", Integer.valueOf(i));
                        HealthRecordsJsApi.dealError(i, str2, HealthRecordCbk.this);
                    }
                });
            }
        });
    }

    @H5ProMethod(name = "deleteFile")
    public static void deleteFile(final String str, final HealthRecordCbk<Boolean> healthRecordCbk) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: brt
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecordsJsApi.hiCloudUtil.d(str, new HiCloudCbk<Boolean>() { // from class: com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi.5
                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Boolean bool) {
                        HealthRecordCbk.this.onSuccess(bool);
                    }

                    @Override // com.huawei.featureuserprofile.healthrecord.hicloud.HiCloudCbk
                    public void onFailure(int i, String str2) {
                        ReleaseLogUtil.c(HealthRecordsJsApi.RELEASE_TAG, "deleteFile fail:", Integer.valueOf(i));
                        HealthRecordsJsApi.dealError(i, str2, HealthRecordCbk.this);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dealError(int i, String str, HealthRecordCbk healthRecordCbk) {
        if (i == 22044011 || i == 21084036) {
            HealthAccessTokenUtil.getAtInstance().refreshAccessToken();
        }
        healthRecordCbk.onFailure(i, str);
    }

    @H5ProMethod(name = "deleteRecords")
    public static void deleteRecords(final List<String> list, final List<String> list2, final List<String> list3, final HealthRecordCbk<Boolean> healthRecordCbk) {
        if (list.size() != list2.size() || list.size() != list3.size()) {
            healthRecordCbk.onFailure(0, "size is not right");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: brr
                @Override // java.lang.Runnable
                public final void run() {
                    HealthRecordsJsApi.lambda$deleteRecords$4(list, list2, list3, healthRecordCbk);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$deleteRecords$4(List list, List list2, List list3, HealthRecordCbk healthRecordCbk) {
        LogUtil.a(TAG, "enter deleteRecords");
        ArrayList arrayList = new ArrayList(1);
        for (int i = 0; i < list.size(); i++) {
            try {
                DataDeleteCondition dataDeleteCondition = new DataDeleteCondition();
                dataDeleteCondition.setStartTime(Long.valueOf(Long.parseLong((String) list.get(i))));
                dataDeleteCondition.setEndTime(Long.valueOf(Long.parseLong((String) list2.get(i))));
                dataDeleteCondition.setType(Integer.valueOf(TYPE_ID));
                dataDeleteCondition.setDeviceCode(Long.valueOf(getDeviceCode((String) list3.get(i))));
                arrayList.add(dataDeleteCondition);
            } catch (NumberFormatException unused) {
                healthRecordCbk.onFailure(0, "NumberFormatException fail");
                LogUtil.b(TAG, "deleteRecords number parse error");
                return;
            }
        }
        DeleteHealthDataReq deleteHealthDataReq = new DeleteHealthDataReq();
        deleteHealthDataReq.setDelHealthDataConditons(arrayList);
        if (dealWithRsp(jbs.a(BaseApplication.e()).a(deleteHealthDataReq))) {
            healthRecordCbk.onSuccess(true);
        } else {
            healthRecordCbk.onFailure(0, ParamConstants.CallbackMethod.ON_FAIL);
        }
    }

    @H5ProMethod(name = "queryRecords")
    public static void queryRecords(final int i, final int i2, final HealthRecordCbk<List<gmt>> healthRecordCbk) {
        LogUtil.a(TAG, "enter queryRecords");
        ThreadPoolManager.d().execute(new Runnable() { // from class: brq
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecordsJsApi.queryRecords(new ArrayList(), i, i2, new HealthRecordCbk<List<gmt>>() { // from class: com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi.1
                    @Override // com.huawei.health.userprofilemgr.model.HealthRecordCbk
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<gmt> list) {
                        LogUtil.a(HealthRecordsJsApi.TAG, "queryRecords onSuccess");
                        HealthRecordCbk.this.onSuccess(list);
                    }

                    @Override // com.huawei.health.userprofilemgr.model.HealthRecordCbk
                    public void onFailure(int i3, String str) {
                        LogUtil.h(HealthRecordsJsApi.TAG, "queryRecords onFailure");
                        HealthRecordCbk.this.onFailure(i3, str);
                    }
                }, 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void queryRecords(List<gmt> list, int i, int i2, HealthRecordCbk<List<gmt>> healthRecordCbk, int i3) {
        ReleaseLogUtil.e(RELEASE_TAG, "queryRecords, startTime:", Integer.valueOf(i), "endTime:", Integer.valueOf(i2), "time:", Integer.valueOf(i3));
        GetHealthDataByReversedOrderReq getHealthDataByReversedOrderReq = new GetHealthDataByReversedOrderReq();
        getHealthDataByReversedOrderReq.setRecordDay(i2);
        getHealthDataByReversedOrderReq.setType(TYPE_ID);
        getHealthDataByReversedOrderReq.setDataType(2);
        GetHealthDataByReversedOrderRsp e = jbs.a(BaseApplication.e()).e(getHealthDataByReversedOrderReq);
        if (dealWithRsp(e)) {
            Map<String, List<HealthDetail>> detailInfos = e.getDetailInfos();
            if (detailInfos != null) {
                addRecords(list, detailInfos);
                if (e.getCurrentRecordDay() > i && i3 < getQueryTime()) {
                    queryRecords(list, i, e.getCurrentRecordDay(), healthRecordCbk, i3 + 1);
                    return;
                } else {
                    healthRecordCbk.onSuccess(list);
                    return;
                }
            }
            healthRecordCbk.onSuccess(list);
            return;
        }
        LogUtil.a(TAG, "queryRecords fail");
        healthRecordCbk.onFailure(0, ParamConstants.CallbackMethod.ON_FAIL);
    }

    private static List<gmt> addRecords(List<gmt> list, Map<String, List<HealthDetail>> map) {
        List<HealthDetail> next;
        Iterator<List<HealthDetail>> it = map.values().iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            for (HealthDetail healthDetail : next) {
                if (healthDetail.getSamplePoints() == null) {
                    return list;
                }
                for (SamplePoint samplePoint : healthDetail.getSamplePoints()) {
                    gmt gmtVar = (gmt) HiJsonUtil.e(samplePoint.getValue(), gmt.class);
                    brm brmVar = (brm) HiJsonUtil.e(samplePoint.getFieldsMetadata(), brm.class);
                    if (brmVar == null && healthDetail.getMetadata() != null) {
                        brmVar = (brm) HiJsonUtil.e(healthDetail.getMetadata(), brm.class);
                    }
                    if (brmVar == null || gmtVar == null) {
                        LogUtil.h(TAG, "addRecords metaData == null || value == null");
                    } else {
                        gmt gmtVar2 = new gmt();
                        gmtVar2.b(gmtVar.m());
                        gmtVar2.d(gmtVar.i());
                        gmtVar2.a(gmtVar.g());
                        gmtVar2.b(gmtVar.j());
                        gmtVar2.e(gmtVar.b());
                        gmtVar2.j(brmVar.h());
                        gmtVar2.b(brmVar.e());
                        gmtVar2.c(brmVar.c());
                        gmtVar2.h(brmVar.b());
                        gmtVar2.e(brmVar.d());
                        gmtVar2.a(samplePoint.getStartTime().longValue());
                        gmtVar2.d(brmVar.a());
                        gmtVar2.a(String.valueOf(healthDetail.getDeviceCode()));
                        LogUtil.a(TAG, "addRecords extendData：", brmVar.a(), " deviceCode: ", healthDetail.getDeviceCode());
                        list.add(gmtVar2);
                    }
                }
            }
        }
        return list;
    }

    @H5ProMethod(name = "uploadRecords")
    public static void uploadRecords(final gmt gmtVar, final HealthRecordCbk<Boolean> healthRecordCbk) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: brp
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecordsJsApi.lambda$uploadRecords$6(gmt.this, healthRecordCbk);
            }
        });
    }

    public static /* synthetic */ void lambda$uploadRecords$6(gmt gmtVar, HealthRecordCbk healthRecordCbk) {
        long o;
        LogUtil.a(TAG, "enter uploadRecords");
        if (gmtVar.o() == 0) {
            o = gmtVar.i() == 0 ? System.currentTimeMillis() : gmtVar.i();
        } else {
            o = gmtVar.o();
        }
        SamplePoint samplePoint = new SamplePoint();
        samplePoint.setStartTime(Long.valueOf(o));
        samplePoint.setEndTime(Long.valueOf(o));
        samplePoint.setKey("HEALTH_EXAMINATION_RECORD");
        gmt gmtVar2 = new gmt();
        gmtVar2.b(gmtVar.j());
        gmtVar2.a(gmtVar.g());
        gmtVar2.e(gmtVar.b());
        gmtVar2.d(gmtVar.i());
        gmtVar2.b(gmtVar.m());
        samplePoint.setValue(HiJsonUtil.d(gmtVar2, gmt.class));
        brm brmVar = new brm();
        brmVar.c(gmtVar.a());
        brmVar.a(gmtVar.h());
        brmVar.e(gmtVar.c());
        brmVar.b(gmtVar.f());
        brmVar.g(gmtVar.k());
        brmVar.d(gmtVar.d());
        LogUtil.a(TAG, "uploadRecords setExtendData：", gmtVar.d());
        samplePoint.setFieldsMetadata(HiJsonUtil.d(brmVar, brm.class));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(samplePoint);
        HealthDetail healthDetail = new HealthDetail();
        healthDetail.setStartTime(Long.valueOf(o));
        healthDetail.setEndTime(Long.valueOf(o));
        healthDetail.setType(Integer.valueOf(TYPE_ID));
        healthDetail.setTimeZone(getTimeZone());
        healthDetail.setDeviceCode(Long.valueOf(getDeviceCode(gmtVar.e())));
        healthDetail.setSamplePoints(arrayList);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList2.add(healthDetail);
        AddHealthDataReq addHealthDataReq = new AddHealthDataReq();
        addHealthDataReq.setDetailInfo(arrayList2);
        if (dealWithRsp(jbs.a(BaseApplication.e()).d(addHealthDataReq))) {
            healthRecordCbk.onSuccess(true);
        } else {
            healthRecordCbk.onFailure(0, ParamConstants.CallbackMethod.ON_FAIL);
        }
    }

    private static int getQueryTime() {
        try {
            return Integer.parseInt(jah.c().e(QUERY_TIME));
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "getQueryTime NumberFormatException");
            return 10;
        }
    }

    private static long getDeviceCode(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.a(TAG, "getDeviceCode recordDeviceCode: ", str);
        } else {
            str = KeyValDbManager.b(BaseApplication.e()).e(KEY_DEVICE_CODE);
        }
        if (!TextUtils.isEmpty(str)) {
            return Long.parseLong(str);
        }
        BindDeviceReq bindDeviceReq = new BindDeviceReq();
        bindDeviceReq.setProductId(1);
        bindDeviceReq.setUniqueId("-1");
        bindDeviceReq.setAppName(BaseApplication.d());
        BindDeviceRsp b = jbs.a(BaseApplication.e()).b(bindDeviceReq);
        if (!dealWithRsp(b)) {
            return 0L;
        }
        KeyValDbManager.b(BaseApplication.e()).e(KEY_DEVICE_CODE, b.getDeviceCode().toString());
        return b.getDeviceCode().longValue();
    }

    private static String getTimeZone() {
        return new SimpleDateFormat("Z").format(Calendar.getInstance().getTime());
    }

    private static boolean dealWithRsp(CloudCommonReponse cloudCommonReponse) {
        try {
            return ius.a(cloudCommonReponse, false);
        } catch (iut e) {
            LogUtil.b(TAG, " dealWithRsp err:", ExceptionUtils.d(e));
            return false;
        }
    }
}

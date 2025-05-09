package com.huawei.health.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.receiver.GprsAbstractPushProcess;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.ceo;
import defpackage.cpp;
import defpackage.dcq;
import defpackage.dcz;
import defpackage.ezq;
import defpackage.jdh;
import defpackage.koq;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class GprsBloodSugarPushReceiver extends GprsAbstractPushProcess {
    private static final int NOTIFICATION_NOTIFY_ID = 20210113;
    private static final String SERIAL_NUMBER_REGEX = "^[0-9A-Z]{11,}$";
    private static final String TAG = "GprsBloodSugarPushReceiver";

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.health.receiver.GprsAbstractPushProcess
    protected void process(Context context, ezq.e eVar, GprsAbstractPushProcess.ProcessResult processResult) {
        Collections.sort(eVar.e);
        getBloodSugarData(context, eVar, processResult);
    }

    private void getBloodSugarData(final Context context, final ezq.e eVar, final GprsAbstractPushProcess.ProcessResult processResult) {
        if (koq.b(eVar.e)) {
            processResult.onResult();
            return;
        }
        int[] d = HiHealthDataType.d(10001);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long longValue = eVar.e.get(0).longValue();
        long longValue2 = eVar.e.get(eVar.e.size() - 1).longValue();
        LogUtil.a(TAG, "getBloodSugarData startTime:", Long.valueOf(longValue), "; endTime:", Long.valueOf(longValue2));
        hiDataReadOption.setTimeInterval(longValue, longValue2);
        hiDataReadOption.setType(d);
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.receiver.GprsBloodSugarPushReceiver.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a(GprsBloodSugarPushReceiver.TAG, "getBloodSugarData errorCode:", Integer.valueOf(i));
                if (obj == null) {
                    LogUtil.h(GprsBloodSugarPushReceiver.TAG, "getBloodSugarData data is null");
                    processResult.onResult();
                    return;
                }
                if (obj instanceof SparseArray) {
                    GprsBloodSugarPushReceiver.this.parseBloodSugarData(context, (SparseArray) obj, eVar);
                } else {
                    LogUtil.h(GprsBloodSugarPushReceiver.TAG, "getBloodSugarData data not instanceof SparseArray");
                }
                processResult.onResult();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseBloodSugarData(Context context, SparseArray<?> sparseArray, ezq.e eVar) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h(TAG, "parseBloodSugarData map is null");
            return;
        }
        LogUtil.a(TAG, "parseBloodSugarData map size:", Integer.valueOf(sparseArray.size()));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            Object valueAt = sparseArray.valueAt(i);
            LogUtil.a(TAG, "parseBloodSugarData map key:", Integer.valueOf(sparseArray.keyAt(i)));
            if (valueAt instanceof List) {
                List list = (List) valueAt;
                if (koq.b(list)) {
                    LogUtil.h(TAG, "parseBloodSugarData healthDataList is null");
                } else {
                    arrayList.addAll(list);
                }
            }
        }
        arrayList.removeAll(Collections.singleton(null));
        if (koq.b(arrayList)) {
            return;
        }
        Collections.sort(arrayList, new Comparator<HiHealthData>() { // from class: com.huawei.health.receiver.GprsBloodSugarPushReceiver.2
            @Override // java.util.Comparator
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                return Long.compare(hiHealthData2.getEndTime(), hiHealthData.getEndTime());
            }
        });
        findTargetBloodSugarData(context, arrayList, eVar);
    }

    private void findTargetBloodSugarData(Context context, List<HiHealthData> list, ezq.e eVar) {
        Map<String, String> bondedDeviceUniqueIdMap = getBondedDeviceUniqueIdMap();
        if (bondedDeviceUniqueIdMap.size() == 0) {
            LogUtil.a(TAG, "findTargetBloodSugarData current not bond device");
            return;
        }
        boolean z = true;
        long longValue = eVar.e.get(eVar.e.size() - 1).longValue();
        Iterator<HiHealthData> it = list.iterator();
        String str = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HiHealthData next = it.next();
            if (longValue == next.getEndTime()) {
                LogUtil.a(TAG, "lastEndTime: ", Long.valueOf(longValue));
                str = next.getString("device_uniquecode");
                LogUtil.a(TAG, "currentUniqueId: ", CommonUtil.l(str));
            }
            String string = next.getString("device_uniquecode");
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(string) && string.equals(str) && !isConfirmed(next)) {
                z = false;
                break;
            }
        }
        if (z) {
            LogUtil.a(TAG, "AllBloodSugarData confirmed");
            Intent intent = new Intent();
            intent.setData(Uri.parse("huaweischeme://healthapp/basicHealth?healthType=8"));
            sendGprsBloodSugarNotification(intent);
            return;
        }
        if (bondedDeviceUniqueIdMap.containsKey(str)) {
            String str2 = bondedDeviceUniqueIdMap.get(str);
            if (TextUtils.isEmpty(str2)) {
                LogUtil.a(TAG, "findTargetBloodSugarData productId is null");
                return;
            } else {
                processingDataIntent(context, str2, str);
                return;
            }
        }
        LogUtil.a(TAG, "The uniqueId does not match.");
    }

    private boolean isConfirmed(HiHealthData hiHealthData) {
        HiBloodSugarMetaData hiBloodSugarMetaData;
        if (hiHealthData == null || TextUtils.isEmpty(hiHealthData.getMetaData()) || (hiBloodSugarMetaData = (HiBloodSugarMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiBloodSugarMetaData.class)) == null) {
            return false;
        }
        return hiBloodSugarMetaData.getConfirmed();
    }

    private void processingDataIntent(Context context, String str, String str2) {
        Intent intent;
        String str3;
        LogUtil.a(TAG, "sendGprsBloodSugarNotification productId:", str);
        dcz d = ResourceManager.e().d(str);
        if (d == null) {
            return;
        }
        LogUtil.a(TAG, "sendGprsBloodSugarNotification H5_TYPE:", d.j());
        if ("1".equals(d.j())) {
            if (BleConstants.BLE_THIRD_DEVICE_H5.equals(d.m().d())) {
                LogUtil.a(TAG, "Go to H5 Pending Confirmation Page");
                str3 = "huaweihealth://huaweihealth.app/openwith?from=thirdDevice&productId=" + str + "&sn=" + str2;
            } else {
                LogUtil.a(TAG, "The page for confirming the app is displayed.");
                str3 = "huaweihealth://huaweihealth.app/openwith?from=thirdDeviceToApp&productId=" + str + "&sn=" + str2;
            }
            intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str3));
        } else {
            Intent intent2 = new Intent();
            intent2.setClassName(context.getPackageName(), "com.huawei.operation.activity.WebViewActivity");
            intent2.setFlags(268435456);
            intent2.putExtra("url", dcq.b().c(str) + "#/type=3/sn=" + str2);
            intent2.putExtra("productId", str);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", str2);
            contentValues.put("productId", str);
            intent2.putExtra("commonDeviceInfo", contentValues);
            intent = intent2;
        }
        sendGprsBloodSugarNotification(intent);
    }

    private void sendGprsBloodSugarNotification(Intent intent) {
        if (!CommonUtil.bh() && !NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            LogUtil.h(TAG, "sendGprsBloodSugarNotification permission is not enabled.");
            return;
        }
        PendingIntent activity = PendingIntent.getActivity(cpp.a(), 0, intent, AppRouterExtras.COLDSTART);
        Resources resources = cpp.a().getResources();
        String string = resources.getString(R.string.IDS_device_recive_blood_sugar_content_two);
        Notification build = jdh.c().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setContentTitle(resources.getString(R.string.IDS_device_recive_blood_sugar_title)).setContentText(string).setStyle(new Notification.BigTextStyle().bigText(string)).setContentIntent(activity).setAutoCancel(true).build();
        build.flags |= 16;
        jdh.c().xh_(NOTIFICATION_NOTIFY_ID, build);
    }

    private Map<String, String> getBondedDeviceUniqueIdMap() {
        HashMap hashMap = new HashMap(16);
        ArrayList<ContentValues> f = ceo.d().f();
        if (koq.b(f)) {
            return hashMap;
        }
        Iterator<ContentValues> it = f.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            if (next != null) {
                String asString = next.getAsString("productId");
                String asString2 = next.getAsString("uniqueId");
                if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                    LogUtil.h(TAG, "getBondedDeviceUniqueIdMap productId or deviceIdentify is empty");
                } else {
                    hashMap.put(asString2, asString);
                }
            }
        }
        return hashMap;
    }
}

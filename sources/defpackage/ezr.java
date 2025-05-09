package defpackage;

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
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.receiver.GprsAbstractPushProcess;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ezq;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ezr extends GprsAbstractPushProcess {
    @Override // com.huawei.health.receiver.GprsAbstractPushProcess
    public void process(Context context, ezq.e eVar, GprsAbstractPushProcess.ProcessResult processResult) {
        Collections.sort(eVar.e);
        a(context, eVar, processResult);
    }

    private void a(final Context context, final ezq.e eVar, final GprsAbstractPushProcess.ProcessResult processResult) {
        if (koq.b(eVar.e)) {
            processResult.onResult();
            return;
        }
        int[] iArr = {DicDataTypeUtil.DataType.VENTILATOR_RECORD_TYPE.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(eVar.e.get(0).longValue());
        hiDataReadOption.setEndTime(eVar.e.get(eVar.e.size() - 1).longValue());
        hiDataReadOption.setType(iArr);
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: ezr.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("GprsVentilatorPushReceiver", "getVentilatorData errorCode:", Integer.valueOf(i));
                if (i != 0) {
                    LogUtil.h("GprsVentilatorPushReceiver", "getVentilatorData data is error");
                    processResult.onResult();
                } else {
                    if (obj == null) {
                        LogUtil.h("GprsVentilatorPushReceiver", "getVentilatorData data is null");
                        processResult.onResult();
                        return;
                    }
                    if (obj instanceof SparseArray) {
                        ezr.this.auk_(context, (SparseArray) obj, eVar);
                    } else {
                        LogUtil.h("GprsVentilatorPushReceiver", "getVentilatorData data not instanceof SparseArray");
                    }
                    processResult.onResult();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void auk_(Context context, SparseArray<?> sparseArray, ezq.e eVar) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("GprsVentilatorPushReceiver", "parseVentilatorData map is null");
            return;
        }
        LogUtil.a("GprsVentilatorPushReceiver", "parseVentilatorData map size:", Integer.valueOf(sparseArray.size()));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            Object valueAt = sparseArray.valueAt(i);
            LogUtil.a("GprsVentilatorPushReceiver", "parseVentilatorData map key:", Integer.valueOf(sparseArray.keyAt(i)));
            if (valueAt instanceof List) {
                List list = (List) valueAt;
                if (koq.b(list)) {
                    LogUtil.h("GprsVentilatorPushReceiver", "parseVentilatorData healthDataList is null");
                } else {
                    arrayList.addAll(list);
                }
            }
        }
        arrayList.removeAll(Collections.singleton(null));
        if (koq.b(arrayList)) {
            return;
        }
        Collections.sort(arrayList, new Comparator<HiHealthData>() { // from class: ezr.5
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
                return Long.compare(hiHealthData2.getEndTime(), hiHealthData.getEndTime());
            }
        });
        e(context, arrayList, eVar);
    }

    private void e(Context context, List<HiHealthData> list, ezq.e eVar) {
        Map<String, String> c = c();
        if (c.size() == 0) {
            LogUtil.a("GprsVentilatorPushReceiver", "findTargetVentilatorData current not bond device");
            return;
        }
        long longValue = eVar.e.get(eVar.e.size() - 1).longValue();
        String str = null;
        for (HiHealthData hiHealthData : list) {
            if (longValue == hiHealthData.getEndTime()) {
                LogUtil.a("GprsVentilatorPushReceiver", "lastEndTime: ", Long.valueOf(longValue));
                str = hiHealthData.getString("device_uniquecode");
                LogUtil.a("GprsVentilatorPushReceiver", "currentUniqueId: ", CommonUtil.l(str));
            }
            String string = hiHealthData.getString("device_uniquecode");
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(string) && string.equals(str)) {
                break;
            }
        }
        if (c.containsKey(str)) {
            String str2 = c.get(str);
            if (TextUtils.isEmpty(str2)) {
                LogUtil.a("GprsVentilatorPushReceiver", "findTargetBloodSugarData productId is null");
                return;
            } else {
                e(context, str2, str);
                return;
            }
        }
        LogUtil.a("GprsVentilatorPushReceiver", "The uniqueId does not match.");
    }

    private void e(Context context, String str, String str2) {
        LogUtil.a("GprsVentilatorPushReceiver", "sendGprsVentilatorNotification productId:", str);
        if (ResourceManager.e().d(str) == null) {
            return;
        }
        LogUtil.a("GprsVentilatorPushReceiver", "Go to H5  Page");
        aul_(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?from=thirdDevice&productId=" + str + "&sn=" + str2)));
    }

    private void aul_(Intent intent) {
        if (!CommonUtil.bh() && !NotificationManagerCompat.from(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).areNotificationsEnabled()) {
            LogUtil.h("GprsVentilatorPushReceiver", "sendGprsVentilatorNotification permission is not enabled.");
            return;
        }
        PendingIntent activity = PendingIntent.getActivity(cpp.a(), 0, intent, AppRouterExtras.COLDSTART);
        Resources resources = cpp.a().getResources();
        String string = resources.getString(R.string.IDS_device_recive_Ventilator_content_two);
        Notification build = jdh.c().xf_().setSmallIcon(R.drawable.healthlogo_ic_notification).setContentTitle(resources.getString(R.string.IDS_device_recive_Ventilator_title)).setContentText(string).setStyle(new Notification.BigTextStyle().bigText(string)).setContentIntent(activity).setAutoCancel(true).build();
        build.flags |= 16;
        jdh.c().xh_(20220830, build);
    }

    private Map<String, String> c() {
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
                    LogUtil.h("GprsVentilatorPushReceiver", "getBondedDeviceUniqueIdMap productId or deviceIdentify is empty");
                } else {
                    hashMap.put(asString2, asString);
                }
            }
        }
        return hashMap;
    }
}

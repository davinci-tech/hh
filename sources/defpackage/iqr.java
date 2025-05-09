package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.HiHealthKitData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthkit.context.H5ProAppInfo;
import com.huawei.hihealthkit.context.OutOfBandData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HeartDeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearkit.IRealTimeCallback;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class iqr {
    private static List<Integer> b;

    static {
        ArrayList arrayList = new ArrayList(10);
        b = arrayList;
        arrayList.add(10006);
        b.add(10002);
        b.add(10001);
        b.add(22101);
        b.add(22102);
        b.add(22103);
        b.add(22104);
        b.add(22105);
        b.add(2002);
    }

    public static int e(String str, int i) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            ReleaseLogUtil.d("HiH_CommonUtil", "invalid method name format");
            return 4;
        }
        String substring = str.substring(0, lastIndexOf);
        try {
            Object invoke = Class.forName(substring).getMethod(str.substring(lastIndexOf + 1), Integer.TYPE).invoke(null, Integer.valueOf(i));
            if (invoke instanceof Integer) {
                return ((Integer) invoke).intValue();
            }
            ReleaseLogUtil.d("HiH_CommonUtil", "invalid return type");
            return 4;
        } catch (ClassNotFoundException e) {
            e = e;
            ReleaseLogUtil.c("HiH_CommonUtil", "class or method not found", LogAnonymous.b(e));
            return 4;
        } catch (IllegalAccessException e2) {
            e = e2;
            ReleaseLogUtil.c(CommonUtil.TAG, "invoke Exception", LogAnonymous.b(e));
            return 4;
        } catch (IllegalArgumentException e3) {
            e = e3;
            ReleaseLogUtil.c(CommonUtil.TAG, "invoke Exception", LogAnonymous.b(e));
            return 4;
        } catch (NoSuchMethodException e4) {
            e = e4;
            ReleaseLogUtil.c("HiH_CommonUtil", "class or method not found", LogAnonymous.b(e));
            return 4;
        } catch (InvocationTargetException e5) {
            e = e5;
            ReleaseLogUtil.c(CommonUtil.TAG, "invoke Exception", LogAnonymous.b(e));
            return 4;
        }
    }

    public static boolean c(List<HiHealthKitData> list, String str) {
        Iterator<HiHealthKitData> it = list.iterator();
        while (it.hasNext()) {
            if (b.contains(Integer.valueOf(it.next().getType())) && !str.startsWith("com.huawei.health.device") && !str.equals("com.huawei.ohos.health.device")) {
                return c(str);
            }
        }
        return false;
    }

    private static boolean c(String str) {
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("privacy_data_source_third_party_app_flag_file", 0);
        if (sharedPreferences == null) {
            return false;
        }
        String string = sharedPreferences.getString("privacy_data_source_third_party_app_flag_context", "");
        LogUtil.a(CommonUtil.TAG, "packageNameMapJsonOld", string);
        Map<String, String> a2 = moj.a(string);
        a2.put(str, str);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String e = moj.e(a2);
        edit.putString("privacy_data_source_third_party_app_flag_context", e);
        LogUtil.a(CommonUtil.TAG, "packageNameMapJsonNew", e);
        return edit.commit();
    }

    public static void c(Context context, OutOfBandData outOfBandData, String str, String str2) {
        int appId;
        if (outOfBandData instanceof H5ProAppInfo) {
            H5ProAppInfo h5ProAppInfo = (H5ProAppInfo) outOfBandData;
            if ("com.huawei.health".equals(str2)) {
                return;
            }
            if (!"execQuery".equals(str) || "com.huawei.health.ecg.collection".equals(str2)) {
                HiAppInfo b2 = iip.b().b(str2);
                if (b2 != null) {
                    if (b2.getAppName() == null || !b2.getAppName().equals(h5ProAppInfo.getAppName())) {
                        LogUtil.a(CommonUtil.TAG, "update app info");
                        b2.setAppName(h5ProAppInfo.getAppName());
                        b2.setSignature(h5ProAppInfo.getCertPrint());
                        iip.b().c(b2);
                    }
                    appId = b2.getAppId();
                } else {
                    LogUtil.a(CommonUtil.TAG, "construct new app info");
                    HiAppInfo hiAppInfo = new HiAppInfo();
                    hiAppInfo.setPackageName(str2);
                    hiAppInfo.setSignature(h5ProAppInfo.getCertPrint());
                    hiAppInfo.setAppName(h5ProAppInfo.getAppName());
                    iip.b().e(hiAppInfo, 0);
                    appId = iip.b().b(hiAppInfo.getPackageName()).getAppId();
                }
                c(context, appId);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(android.content.Context r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "CommonUtil"
            java.lang.String r1 = "com.huawei.ohos.health.device"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto Lb
            return
        Lb:
            r1 = 1
            r2 = 0
            android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            boolean r3 = defpackage.ivu.i(r3, r2)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            if (r3 != 0) goto L20
            android.content.Context r3 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            boolean r3 = defpackage.ivu.e(r3, r2)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
            goto L21
        L20:
            r3 = r2
        L21:
            iip r4 = defpackage.iip.b()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            com.huawei.hihealth.HiAppInfo r4 = r4.b(r7)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            if (r4 == 0) goto L30
            int r7 = r4.getAppId()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            goto L54
        L30:
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            java.lang.String r5 = "construct new app info"
            r4[r2] = r5     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            com.huawei.hwlogsmodel.LogUtil.a(r0, r4)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            com.huawei.hihealth.HiAppInfo r7 = defpackage.ivw.c(r6, r7)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            iip r4 = defpackage.iip.b()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            r4.e(r7, r2)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            iip r4 = defpackage.iip.b()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            java.lang.String r7 = r7.getPackageName()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            com.huawei.hihealth.HiAppInfo r7 = r4.b(r7)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            int r7 = r7.getAppId()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
        L54:
            if (r3 == 0) goto L5d
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
            defpackage.ivu.j(r4, r2)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> L8c
        L5d:
            if (r3 == 0) goto L66
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r0, r2)
        L66:
            c(r6, r7)
            return
        L6a:
            r6 = move-exception
            goto L71
        L6c:
            r6 = move-exception
            r3 = r2
            goto L8d
        L6f:
            r6 = move-exception
            r3 = r2
        L71:
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L8c
            java.lang.String r4 = "updateAppAndSyncInfo exception: "
            r7[r2] = r4     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> L8c
            r7[r1] = r6     // Catch: java.lang.Throwable -> L8c
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r0, r7)     // Catch: java.lang.Throwable -> L8c
            if (r3 == 0) goto L8b
            android.content.Context r6 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r6, r2)
        L8b:
            return
        L8c:
            r6 = move-exception
        L8d:
            if (r3 == 0) goto L96
            android.content.Context r7 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            defpackage.ivu.c(r7, r2)
        L96:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iqr.d(android.content.Context, java.lang.String):void");
    }

    private static void c(Context context, int i) {
        HiHealthNativeApi.a(context).updateHealthKitPermission(i, 101000, 1, true, new HiDataOperateListener() { // from class: iqp
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                iqr.c(i2, obj);
            }
        });
    }

    static /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            ReleaseLogUtil.e("HiH_CommonUtil", "save sync flag success");
        } else {
            ReleaseLogUtil.e("HiH_CommonUtil", "save sync flag fail, errCode: ", Integer.valueOf(i));
        }
    }

    public static boolean d(long j, long j2) {
        Date date = new Date(j);
        Date date2 = new Date(j2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(date2));
    }

    public static void a(Context context, String str, IRealTimeCallback iRealTimeCallback) throws RemoteException {
        ArrayList arrayList = new ArrayList(10);
        List list = (List) HiJsonUtil.b(str, new TypeToken<List<HeartDeviceInfo>>() { // from class: iqr.3
        }.getType());
        if (list != null && list.size() > 0) {
            arrayList.addAll(list);
            ReleaseLogUtil.e("HiH_CommonUtil", " get wear device list size: ", Integer.valueOf(list.size()));
        }
        List<HeartDeviceInfo> d = ivr.d(context);
        if (d.size() > 0) {
            arrayList.addAll(d);
            ReleaseLogUtil.e("HiH_CommonUtil", " get health device list size: ", Integer.valueOf(d.size()));
        }
        iRealTimeCallback.onChange(0, HiJsonUtil.e(arrayList));
    }
}

package com.huawei.watchface;

import android.content.SharedPreferences;
import com.google.gson.reflect.TypeToken;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.api.MembershipRepository;
import com.huawei.watchface.api.ResponseListener;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryBean;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryRequestModel;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryResponse;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ac {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10885a = "ac";
    private static volatile ac f;
    private List<OrderHistoryBean> c = new ArrayList();
    private List<String> d = new ArrayList();
    private List<String> e = new ArrayList();
    private boolean g = false;
    private final Runnable b = new Runnable() { // from class: com.huawei.watchface.ac$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            ac.this.m();
        }
    };

    public static ac a() {
        ac acVar;
        synchronized (ac.class) {
            if (f == null) {
                synchronized (ac.class) {
                    if (f == null) {
                        f = new ac();
                    }
                }
            }
            acVar = f;
        }
        return acVar;
    }

    private ac() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        try {
            HwLog.i(f10885a, "update data on runnable");
            b(CommonUtils.B());
        } catch (Exception e) {
            HwLog.e(f10885a, "update data exception: " + HwLog.printException(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<OrderHistoryBean> list) {
        synchronized (this) {
            if (list == null) {
                return;
            }
            this.c = list;
        }
    }

    public void a(List<String> list) {
        if (list == null) {
            return;
        }
        this.e = list;
    }

    public boolean a(String str, String str2) {
        return this.e.contains(d(str, str2));
    }

    public List<OrderHistoryBean> b() {
        return this.c;
    }

    public void b(List<String> list) {
        synchronized (this) {
            if (list == null) {
                return;
            }
            this.d = list;
            c(list);
        }
    }

    private boolean i() {
        List<String> list = this.d;
        return list == null || list.size() == 0;
    }

    public void b(String str, String str2) {
        synchronized (this) {
            if (str == null || str2 == null) {
                return;
            }
            String d = d(str, str2);
            if (!this.e.contains(d)) {
                this.e.add(d);
            }
        }
    }

    public void c(String str, String str2) {
        synchronized (this) {
            if (str == null || str2 == null) {
                return;
            }
            String d = d(str, str2);
            if (!this.d.contains(d)) {
                this.d.add(d);
            }
        }
    }

    private void j() {
        synchronized (this) {
            x.a().a(this.d);
        }
    }

    public String d(String str, String str2) {
        synchronized (this) {
            if (str == null || str2 == null) {
                return "";
            }
            return str + "_" + str2;
        }
    }

    public void a(boolean z) {
        synchronized (this) {
            if (z) {
                HwLog.i(f10885a, "startVipFlow");
                if (i()) {
                    b(z);
                } else {
                    j();
                }
            }
        }
    }

    public void e(String str, String str2) {
        synchronized (this) {
            if (str == null || str2 == null) {
                return;
            }
            this.d.remove(d(str, str2));
        }
    }

    private List<OrderHistoryRequestModel> k() {
        List<WatchResourcesInfo> watchFaceResourcesInfo = HwWatchFaceManager.getInstance(Environment.getApplicationContext()).getWatchFaceResourcesInfo();
        ArrayList arrayList = new ArrayList();
        for (WatchResourcesInfo watchResourcesInfo : watchFaceResourcesInfo) {
            if ((watchResourcesInfo.getWatchInfoType() & 64) != 64 && watchResourcesInfo.isVipFreeWatchFace()) {
                arrayList.add(new OrderHistoryRequestModel(watchResourcesInfo.getWatchInfoId(), String.valueOf(1)));
            }
        }
        return arrayList;
    }

    public void b(final boolean z) {
        synchronized (this) {
            String str = f10885a;
            HwLog.i(str, "update process");
            if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() != 1) {
                HwLog.e(str, "update current connect is error ");
                return;
            }
            List<OrderHistoryRequestModel> k = k();
            if (k.isEmpty()) {
                HwLog.i(str, "update orderHistoryBody isEmpty");
            } else {
                MembershipRepository.getOrderHistoryResponse(k, new ResponseListener<OrderHistoryResponse>() { // from class: com.huawei.watchface.ac.1
                    @Override // com.huawei.watchface.api.ResponseListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onResponse(OrderHistoryResponse orderHistoryResponse) {
                        ac.this.l();
                        ac.this.d(orderHistoryResponse.getOrderHistoryBeanList());
                        if (z) {
                            x.a().b();
                        }
                    }

                    @Override // com.huawei.watchface.api.ResponseListener
                    public void onError() {
                        HwLog.i(ac.f10885a, "update -- getOrderHistoryResponse -- onError");
                    }
                });
            }
        }
    }

    public void c() {
        synchronized (this) {
            if (this.b != null) {
                HwLog.i(f10885a, "stopPolling: removeTaskFromWorker");
                BackgroundTaskUtils.b(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        synchronized (this) {
            c();
            HwLog.i(f10885a, "startPolling");
            BackgroundTaskUtils.a(this.b, 3600000);
        }
    }

    public void d() {
        synchronized (this) {
            HwLog.i(f10885a, "clear:");
            c();
            f = null;
        }
    }

    public void c(List<String> list) {
        a(GsonHelper.toJson(list));
    }

    public void a(String str) {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "WATCH_FACE_DOWNLOAD_QUEUE");
        if (a2 == null) {
            HwLog.i(f10885a, "preferences is null");
            return;
        }
        a2.edit().putString("QUEUE_DATA", str).apply();
        HwLog.i(f10885a, "saveQueueTask jsonString: " + str);
    }

    public String e() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "WATCH_FACE_DOWNLOAD_QUEUE");
        if (a2 == null) {
            HwLog.i(f10885a, "sharedPreferences is null");
            return null;
        }
        String string = a2.getString("QUEUE_DATA", null);
        HwLog.d(f10885a, "getSharedPreferencesQueueTask() completed.");
        return string;
    }

    public List<String> f() {
        return (List) GsonHelper.fromJson(e(), new TypeToken<List<String>>() { // from class: com.huawei.watchface.ac.2
        }.getType());
    }

    public void g() {
        SharedPreferences a2 = dp.a(Environment.getApplicationContext(), "WATCH_FACE_DOWNLOAD_QUEUE");
        if (a2 == null) {
            HwLog.i(f10885a, "sharedPreferences is null");
            return;
        }
        SharedPreferences.Editor edit = a2.edit();
        edit.clear();
        edit.apply();
        HwLog.i(f10885a, "clearSharedPreferencesData completed");
    }
}

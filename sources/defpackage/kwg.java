package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartResponseWrapper;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartObserver;
import com.huawei.hwsmartinteractmgr.userlabel.LabelObserver;
import com.huawei.login.ui.login.LoginInit;
import com.tencent.open.apireq.BaseResp;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kwg {
    private String d;
    private Context e;
    private kvs i;
    private static final kwg b = new kwg();
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14661a = new Object();
    private kwb g = kwb.d();
    private List<LabelObserver> j = new ArrayList(3);

    private kwg() {
        Context context = BaseApplication.getContext();
        this.e = context;
        this.i = kvs.b(context);
    }

    public static kwg c() {
        return b;
    }

    public boolean b(List<String> list) {
        if (list == null) {
            return false;
        }
        List<String> b2 = b();
        LogUtil.c("SMART_UserLabelPuller", "all Label is ", b2, "&& ", list);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (b2.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private List<String> b() {
        return e(e(CommonUtil.USER_LABEL_STORAGE_KEY, this.i));
    }

    public void d() {
        LogUtil.a("SMART_UserLabelPuller", "enter doRefresh");
        String e = e("_cacheTime_key", this.i);
        try {
            if (!TextUtils.isEmpty(e) && !e(Long.parseLong(e), 86400000L)) {
                LogUtil.a("SMART_UserLabelPuller", "cache not expired");
            }
            LogUtil.a("SMART_UserLabelPuller", "not cached yet! or cache expired! ", Boolean.valueOf(TextUtils.isEmpty(e)));
            b((SmartHttpCallback<List<String>>) null);
        } catch (NumberFormatException e2) {
            LogUtil.b("SMART_UserLabelPuller", "doRefresh numberFormatException = ", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> e(String str) {
        if (str == null) {
            str = "";
        }
        return Arrays.asList(str.split(","));
    }

    private boolean e(long j, long j2) {
        return j == 0 || System.currentTimeMillis() - j >= j2;
    }

    private void b(SmartHttpCallback<List<String>> smartHttpCallback) {
        LogUtil.a("SMART_UserLabelPuller", "to request server");
        d dVar = new d();
        this.g.d(1, new kwk(1));
        this.g.a(1, dVar);
        this.g.c(1, new HashMap(3), smartHttpCallback);
    }

    private int b(String str, String str2, HwBaseManager hwBaseManager) {
        String e = e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("SMART_UserLabelPuller", "get huid failed!");
            return BaseResp.CODE_UNSUPPORTED_BRANCH;
        }
        return hwBaseManager.setSharedPreference(e + str, str2, new StorageParams(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(String str, HwBaseManager hwBaseManager) {
        synchronized (c) {
            String e = e();
            if (TextUtils.isEmpty(e)) {
                LogUtil.h("SMART_UserLabelPuller", "get huid failed!");
                return null;
            }
            return hwBaseManager.getSharedPreference(e + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Map<String, String> map, HwBaseManager hwBaseManager) {
        synchronized (c) {
            if (map != null) {
                if (!map.isEmpty()) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key != null && value != null) {
                            int b2 = b(key, value, hwBaseManager);
                            LogUtil.a("SMART_UserLabelPuller", "save result = ", Integer.valueOf(b2));
                            if (b2 != 0) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
            LogUtil.h("SMART_UserLabelPuller", "no values to cache!");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.a("SMART_UserLabelPuller", "userLabels none!");
            return "";
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        StringBuffer stringBuffer = new StringBuffer(3);
        stringBuffer.append(list.get(0));
        for (String str : list) {
            stringBuffer.append(",");
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    private String e() {
        return LoginInit.getInstance(this.e).getAccountInfo(1011);
    }

    public void d(final LabelObserver labelObserver) {
        if (labelObserver == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: kwg.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (kwg.f14661a) {
                    if (!kwg.this.j.contains(labelObserver)) {
                        LogUtil.a("SMART_UserLabelPuller", "add observer!");
                        kwg.this.j.add(labelObserver);
                    }
                }
            }
        });
    }

    public void b(final LabelObserver labelObserver) {
        if (labelObserver == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: kwg.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (kwg.f14661a) {
                    kwg.this.j.remove(labelObserver);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<Integer, List<String>> map) {
        LogUtil.c("SMART_UserLabelPuller", "enter notifyUserLabelSmarter ", map);
        synchronized (f14661a) {
            Iterator<LabelObserver> it = this.j.iterator();
            while (it.hasNext()) {
                it.next().onChange(map);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<Integer, List<String>> b(List<String> list, List<String> list2) {
        boolean z = true;
        LogUtil.c("SMART_UserLabelPuller", "enter diff : ", list, " olds.size : ", Integer.valueOf(list.size()), " && ", list2);
        HashMap hashMap = new HashMap(3);
        hashMap.put(1, new ArrayList(3));
        hashMap.put(2, new ArrayList(3));
        for (String str : list) {
            if (!list2.contains(str)) {
                ((List) hashMap.get(2)).add(str);
            }
        }
        for (String str2 : list2) {
            if (!list.contains(str2)) {
                ((List) hashMap.get(1)).add(str2);
            }
        }
        if (((List) hashMap.get(1)).isEmpty()) {
            hashMap.put(1, null);
            z = false;
        }
        if (((List) hashMap.get(2)).isEmpty()) {
            hashMap.put(2, null);
            if (!z) {
                LogUtil.a("SMART_UserLabelPuller", "no change");
            }
        }
        LogUtil.c("SMART_UserLabelPuller", hashMap);
        return hashMap;
    }

    class d implements SmartObserver {
        private d() {
        }

        @Override // com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartObserver
        public void onDataChanged(SmartResponseWrapper smartResponseWrapper) {
            LogUtil.a("SMART_UserLabelPuller", "enter onDataChanged");
            kwg.this.g.d(1, this);
            if (smartResponseWrapper == null) {
                LogUtil.h("SMART_UserLabelPuller", "onDataChanged wrapper is null!");
                return;
            }
            int responseCode = smartResponseWrapper.getResponseCode();
            if (responseCode != 0 && responseCode != 101) {
                LogUtil.h("SMART_UserLabelPuller", "http connection success, response abnormal code is ", Integer.valueOf(responseCode));
                return;
            }
            if (!(smartResponseWrapper.getResponse() instanceof List)) {
                LogUtil.h("SMART_UserLabelPuller", "onDataChanged wrapper.getResponse() is null!");
                return;
            }
            List list = (List) smartResponseWrapper.getResponse();
            LogUtil.c("SMART_UserLabelPuller", "labels from notify ", list);
            kvs b = kvs.b(kwg.this.e);
            String e = kwg.this.d == null ? kwg.this.e(CommonUtil.USER_LABEL_STORAGE_KEY, b) : kwg.this.d;
            String d = kwg.this.d((List<String>) list);
            LogUtil.c("SMART_UserLabelPuller", "labelStr = ", d);
            HashMap hashMap = new HashMap(3);
            hashMap.put(CommonUtil.USER_LABEL_STORAGE_KEY, d);
            if (TextUtils.isEmpty(d)) {
                hashMap.put("_cacheTime_key", String.valueOf(0));
            } else {
                hashMap.put("_cacheTime_key", String.valueOf(System.currentTimeMillis()));
            }
            List e2 = kwg.this.e(e);
            if (kwg.this.e(hashMap, b)) {
                kwg.this.d = d;
            } else {
                kwg.this.d = null;
            }
            Map b2 = kwg.this.b(e2, list);
            if (b2 != null) {
                kwg.this.d((Map<Integer, List<String>>) b2);
            }
        }
    }
}

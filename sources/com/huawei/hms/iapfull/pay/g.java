package com.huawei.hms.iapfull.pay;

import android.text.TextUtils;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.hms.iapfull.network.model.DeveloperSignResponse;
import com.huawei.hms.iapfull.network.model.MyPayType;
import com.huawei.hms.iapfull.y0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private final List<MyPayType> f4749a = new CopyOnWriteArrayList();
    private boolean b = true;

    static class a {

        /* renamed from: a, reason: collision with root package name */
        public static final g f4750a = new g();
    }

    public List<MyPayType> a() {
        ArrayList arrayList = new ArrayList();
        MyPayType myPayType = null;
        for (MyPayType myPayType2 : this.f4749a) {
            if (17 == myPayType2.getPayType() && !this.b) {
                y0.b("PayManager", "getSupportPayTypeList: wx not support");
                myPayType = myPayType2;
            } else if (myPayType2.getMode() == 0) {
                arrayList.add(myPayType2);
            }
        }
        if (myPayType != null) {
            this.f4749a.remove(myPayType);
        }
        if (arrayList.isEmpty()) {
            y0.b("PayManager", "removePayTypeToLast： not stop service");
        } else {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                MyPayType myPayType3 = (MyPayType) it.next();
                if (this.f4749a.contains(myPayType3)) {
                    this.f4749a.remove(myPayType3);
                }
            }
            this.f4749a.addAll(arrayList);
        }
        return this.f4749a;
    }

    public void a(List<DeveloperSignResponse.MyDics> list, String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            y0.b("PayManager", "setPayChannelListMap: appId is null");
            return;
        }
        for (DeveloperSignResponse.MyDics myDics : list) {
            if ("FP_PayTypePolices".equals(myDics.getName())) {
                String value = myDics.getValue();
                if (TextUtils.isEmpty(value)) {
                    str2 = "setPayTypePolicesList: FP_PAY_TYPE_POLICES value is null";
                } else {
                    try {
                        JSONArray optJSONArray = new JSONObject(value).optJSONArray("payList");
                        if (optJSONArray != null && optJSONArray.length() > 0) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                int optInt = optJSONObject.optInt("payType");
                                for (int i2 = 0; i2 < this.f4749a.size(); i2++) {
                                    if (optInt == this.f4749a.get(i2).getPayType()) {
                                        this.f4749a.get(i2).setMode(optJSONObject.optInt(Wpt.MODE));
                                    }
                                }
                            }
                        }
                    } catch (JSONException unused) {
                        str2 = "setPayTypePolicesList: FP_PAY_TYPE_POLICES value is JSONException";
                    }
                }
                y0.a("PayManager", str2);
            } else if ("liteSdkWXNotSupportMerchants".equals(myDics.getName())) {
                String value2 = myDics.getValue();
                if (TextUtils.isEmpty(value2)) {
                    str3 = "setWxPayTypeMode: setWxPayTypeMode value is null";
                } else if (TextUtils.isEmpty(str)) {
                    str3 = "setWxPayTypeMode: setWxPayTypeMode packageName is null";
                } else {
                    try {
                        JSONArray jSONArray = new JSONArray(value2);
                        if (jSONArray.length() > 0) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= jSONArray.length()) {
                                    break;
                                }
                                if (str.equals(jSONArray.optJSONObject(i3).optString("merchant"))) {
                                    this.b = false;
                                    break;
                                }
                                i3++;
                            }
                        }
                    } catch (JSONException unused2) {
                        str3 = "setPayTypePolicesList: WX_SUPPORT_MERCHANTS value is JSONException";
                    }
                }
                y0.a("PayManager", str3);
            }
        }
    }

    public boolean b() {
        Iterator<MyPayType> it = this.f4749a.iterator();
        while (it.hasNext()) {
            if (1 == it.next().getMode()) {
                return true;
            }
        }
        return false;
    }

    public void a(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            y0.b("PayManager", "setPayChannelListMap: listPayType is null");
            return;
        }
        this.f4749a.clear();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (f.a(intValue)) {
                MyPayType myPayType = new MyPayType();
                myPayType.setPayType(intValue);
                myPayType.setMode(1);
                this.f4749a.add(myPayType);
            }
        }
    }
}

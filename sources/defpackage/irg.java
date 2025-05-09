package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.ICommonListener;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class irg {
    public static void d(final List<String> list, final ICommonCallback iCommonCallback, final irc ircVar, final iqy iqyVar, final Context context) throws RemoteException {
        if (koq.b(list)) {
            ReleaseLogUtil.d("HiH_HiHealthUserInfoUtil", "getUserInfo param is invalid");
            if (iCommonCallback != null) {
                iCommonCallback.onResult(2, ipd.b(2));
                return;
            } else {
                ReleaseLogUtil.d("HiH_HiHealthUserInfoUtil", "callback is null, unable to notify");
                return;
            }
        }
        ify.e().c((ICommonListener) new ICommonListener.Stub() { // from class: irg.5
            @Override // com.huawei.hihealth.ICommonListener
            public void onSuccess(int i, List list2) throws RemoteException {
                ReleaseLogUtil.e("HiH_HiHealthUserInfoUtil", "getUserInfo onSuccess Code = ", Integer.valueOf(i));
                if (koq.b(list2) || !(list2.get(0) instanceof HiUserInfo)) {
                    ReleaseLogUtil.d("HiH_HiHealthUserInfoUtil", "userInfo is null");
                    ICommonCallback.this.onResult(4, ipd.b(4));
                    ircVar.c(context, iqyVar.b(4));
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) list2.get(0);
                JSONObject jSONObject = new JSONObject();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    irg.d(jSONObject, (String) it.next(), hiUserInfo);
                }
                ICommonCallback.this.onResult(0, jSONObject.toString());
                ircVar.c(context, iqyVar.b(0));
            }

            @Override // com.huawei.hihealth.ICommonListener
            public void onFailure(int i, List list2) throws RemoteException {
                ReleaseLogUtil.d("HiH_HiHealthUserInfoUtil", "getUserInfo onFailure errorCode = ", Integer.valueOf(i));
                int b = iox.b(i);
                ICommonCallback.this.onResult(b, ipd.b(b));
                ircVar.c(context, iqyVar.b(b));
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(JSONObject jSONObject, String str, HiUserInfo hiUserInfo) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            int i = 0;
            int i2 = -1;
            switch (str.hashCode()) {
                case -2137162425:
                    if (str.equals("Height")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1707725160:
                    if (str.equals("Weight")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1134020253:
                    if (str.equals("Birthday")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 2129321697:
                    if (str.equals("Gender")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                if (hiUserInfo.getGender() != -100) {
                    i2 = hiUserInfo.getGender();
                }
                jSONObject.put(str, i2);
            } else if (c == 1) {
                if (hiUserInfo.getBirthday() != -100) {
                    i = hiUserInfo.getBirthday();
                }
                jSONObject.put(str, i);
            } else if (c == 2) {
                jSONObject.put(str, hiUserInfo.getHeight());
            } else {
                if (c != 3) {
                    return;
                }
                jSONObject.put(str, Float.toString(hiUserInfo.getWeight()));
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_HiHealthUserInfoUtil", "putUserInfoJson JSONException: ", e.getMessage());
        }
    }
}

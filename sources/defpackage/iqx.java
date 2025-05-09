package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IReadCallback;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.IWriteCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearkit.IRealTimeCallback;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class iqx {
    public static IRealTimeDataCallback e(final IRealTimeDataCallback iRealTimeDataCallback, final irc ircVar, final Context context, final iqy iqyVar) {
        return new IRealTimeDataCallback.Stub() { // from class: iqx.4
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) {
                try {
                    IRealTimeDataCallback.this.onResult(i);
                } catch (Exception e) {
                    ReleaseLogUtil.c("HiH_DotProxy", "third party app exception ", LogAnonymous.b((Throwable) e));
                }
                ircVar.c(context, iqyVar.b(i));
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str) throws RemoteException {
                iqx.a(IRealTimeDataCallback.this, i, str, context, iqyVar);
                if (iox.e(i)) {
                    return;
                }
                ircVar.c(context, iqyVar.b(i));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final IRealTimeDataCallback iRealTimeDataCallback, int i, String str, Context context, iqy iqyVar) throws RemoteException {
        if (iqz.c(iqyVar.a()) >= 8 && i == 0 && "getDeviceList".equals(iqyVar.b())) {
            iqr.a(context, str, new IRealTimeCallback.Stub() { // from class: iqx.2
                @Override // com.huawei.wearkit.IRealTimeCallback
                public void onResult(int i2) {
                    LogUtil.a("DotProxy", "nothing to do");
                }

                @Override // com.huawei.wearkit.IRealTimeCallback
                public void onChange(int i2, String str2) {
                    try {
                        IRealTimeDataCallback.this.onChange(i2, str2);
                    } catch (Exception e) {
                        ReleaseLogUtil.c("HiH_DotProxy", "third party app exception ", LogAnonymous.b((Throwable) e));
                    }
                }
            });
            return;
        }
        try {
            iRealTimeDataCallback.onChange(i, str);
        } catch (Exception e) {
            ReleaseLogUtil.d("HiH_DotProxy", "third party app exception ", LogAnonymous.b((Throwable) e));
        }
    }

    public static ICommonCallback a(final ICommonCallback iCommonCallback, final irc ircVar, final Context context, final iqy iqyVar, final String str) {
        return new ICommonCallback.Stub() { // from class: iqx.1
            @Override // com.huawei.hihealth.ICommonCallback
            public void onResult(int i, String str2) throws RemoteException {
                ICommonCallback.this.onResult(i, str2);
                ircVar.c(context, iqyVar.b(iqr.e(str, i)));
            }
        };
    }

    public static IWriteCallback a(final IWriteCallback iWriteCallback, final irc ircVar, final Context context, final iqy iqyVar, final JSONObject jSONObject) {
        if (iWriteCallback == null) {
            LogUtil.b("DotProxy", "getWriteCallback writeCallback is null");
            return null;
        }
        return new IWriteCallback.Stub() { // from class: iqx.3
            @Override // com.huawei.hihealth.IWriteCallback
            public void onResult(int i, String str) throws RemoteException {
                IWriteCallback.this.onResult(i, str);
                iqyVar.b(iox.d(i));
                try {
                    JSONObject jSONObject2 = jSONObject;
                    if (jSONObject2 == null) {
                        LogUtil.b("DotProxy", "getWriteCallback infoObject is null");
                        return;
                    }
                    if (jSONObject2.optBoolean("isWriteFile")) {
                        if (new JSONObject(jSONObject.optString("sizeAndFinish")).optBoolean("is_finished")) {
                            if (i == 200 && TextUtils.equals("100", str)) {
                                ircVar.c(context, iqyVar);
                                return;
                            } else {
                                if (i != 200) {
                                    ircVar.c(context, iqyVar);
                                    return;
                                }
                                return;
                            }
                        }
                        return;
                    }
                    ircVar.c(context, iqyVar);
                } catch (JSONException e) {
                    ReleaseLogUtil.c("HiH_DotProxy", "getWriteCallback JSONException", LogAnonymous.b((Throwable) e));
                    ircVar.c(context, iqyVar);
                }
            }
        };
    }

    public static IReadCallback b(final IReadCallback iReadCallback, final irc ircVar, final Context context, final iqy iqyVar) {
        return new IReadCallback.Stub() { // from class: iqx.5
            @Override // com.huawei.hihealth.IReadCallback
            public void onResult(int i, String str, byte[] bArr) throws RemoteException {
                IReadCallback.this.onResult(i, str, bArr);
                ircVar.c(context, iqyVar.b(iox.d(i)));
            }
        };
    }
}

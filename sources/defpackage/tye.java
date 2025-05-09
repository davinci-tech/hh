package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.zhangyue.iReader.sdk.scheme.ISchemeListener;
import com.zhangyue.iReader.sdk.scheme.a;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class tye {

    /* renamed from: a, reason: collision with root package name */
    private static Context f17406a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    private static int f = -1;
    private static String h = null;
    private static boolean i = false;

    private static String d(String str) {
        return str.replace("{host}", tyf.d(f17406a).b("SCHEME_SUPPORT_PACKAGE", "")).replace("{from}", b).replace("{traceid}", h);
    }

    private static String a(String str) {
        return (!"com.huawei.hwireader".contains(str.toLowerCase()) || b(str, "schemeVersion") >= 220) ? str : "";
    }

    public static void d(final String str, final ISchemeListener iSchemeListener) {
        if (!i) {
            throw new RuntimeException("Call SchemeManager.init() first!");
        }
        try {
            if (str.contains("{from}")) {
                str = d(str);
            }
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            intent.addFlags(268468224);
            intent.setClassName("com.huawei.hwireader", "com.zhangyue.iReader.online.ui.ActivityOutsideWeb");
            f17406a.startActivity(intent);
            if (iSchemeListener != null) {
                iSchemeListener.onSuccess(200);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            int i2 = e;
            if (i2 > 0) {
                iSchemeListener.onError(402, "url error.");
            } else {
                e = i2 + 1;
                e(f17406a, c, "", h, new ISchemeListener() { // from class: tye.2
                    @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                    public void onSuccess(Object obj) {
                        tye.d(str, iSchemeListener);
                    }

                    @Override // com.zhangyue.iReader.sdk.scheme.ISchemeListener
                    public void onError(int i3, String str2) {
                        ISchemeListener iSchemeListener2 = iSchemeListener;
                        if (iSchemeListener2 != null) {
                            iSchemeListener2.onError(i3, str2);
                        }
                    }
                });
            }
        }
    }

    public static void d(ISchemeListener iSchemeListener) {
        b("channel_ch_earclub", iSchemeListener);
    }

    public static void b(String str, final ISchemeListener iSchemeListener) {
        if (iSchemeListener == null) {
            return;
        }
        b(str, new a() { // from class: tye.1
            @Override // com.zhangyue.iReader.sdk.scheme.a
            public void a(String str2) {
                tye.d(str2, ISchemeListener.this);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(android.content.Context r1, java.lang.String r2, java.lang.String r3, java.lang.String r4, com.zhangyue.iReader.sdk.scheme.ISchemeListener r5) {
        /*
            r0 = 1
            defpackage.tye.i = r0
            defpackage.tye.f17406a = r1
            java.lang.String r0 = r1.getPackageName()
            defpackage.tye.b = r0
            defpackage.tye.c = r2
            defpackage.tye.d = r3
            defpackage.tye.h = r4
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L1a
            java.lang.String r2 = "appId not valid"
            goto L22
        L1a:
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 == 0) goto L27
            java.lang.String r2 = "appSecret not valid"
        L22:
            r3 = 401(0x191, float:5.62E-43)
            r5.onError(r3, r2)
        L27:
            java.lang.String r2 = b()
            java.lang.String r2 = a(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "supportPkgName:"
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            defpackage.tyc.a(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            java.lang.String r4 = "SCHEME_SUPPORT_PACKAGE"
            if (r3 != 0) goto L63
            java.lang.String r3 = "schemeVersion"
            int r3 = b(r2, r3)
            defpackage.tye.f = r3
            tyf r1 = defpackage.tyf.d(r1)
            r1.c(r4, r2)
            if (r5 == 0) goto L74
            r1 = 200(0xc8, float:2.8E-43)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r5.onSuccess(r1)
            goto L74
        L63:
            tyf r1 = defpackage.tyf.d(r1)
            r2 = 0
            r1.c(r4, r2)
            if (r5 == 0) goto L74
            r1 = 400(0x190, float:5.6E-43)
            java.lang.String r2 = "There is no application support uri scheme."
            r5.onError(r1, r2)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.tye.e(android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.zhangyue.iReader.sdk.scheme.ISchemeListener):void");
    }

    private static String b() {
        String[] split = "com.huawei.hwireader,com.chaozh.iReaderFree.scheme, com.chaozh.iReaderFree15, com.chaozh.iReaderFree".split(",");
        if (split == null || split.length == 0) {
            return null;
        }
        List<ResolveInfo> queryIntentActivities = f17406a.getPackageManager().queryIntentActivities(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("zhangyueireader://")), 0);
        int length = split.length;
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        boolean z = false;
        while (it.hasNext()) {
            String str = it.next().activityInfo.packageName;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (str.equals(split[i2])) {
                    z = true;
                    length = i2;
                    break;
                }
                i2++;
            }
            if (length == 0) {
                break;
            }
        }
        return z ? split[length] : "";
    }

    static class e extends AsyncTask<Void, Integer, String> {

        /* renamed from: a, reason: collision with root package name */
        private String f17408a;
        private a c;
        private String e;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            tyc.a("result:" + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("code") == 0) {
                    String string = jSONObject.getJSONObject("body").getString("url");
                    tyc.a("url:" + string);
                    if (TextUtils.isEmpty(string)) {
                        return;
                    }
                    tyf.d(tye.f17406a).c(this.e, string);
                    a aVar = this.c;
                    if (aVar != null) {
                        aVar.a(string);
                    }
                }
            } catch (JSONException e) {
                tyc.b("parse result error:" + e.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String doInBackground(Void... voidArr) {
            return tyd.d(this.f17408a);
        }

        public e(String str, String str2, a aVar) {
            this.e = str;
            this.c = aVar;
            this.f17408a = str2;
        }
    }

    private static int b(String str, String str2) {
        try {
            Bundle bundle = f17406a.getPackageManager().getApplicationInfo(str, 128).metaData;
            if (bundle == null || !bundle.containsKey(str2)) {
                return -1;
            }
            return bundle.getInt(str2);
        } catch (PackageManager.NameNotFoundException e2) {
            tyc.b("getAppMetaIntData error:" + e2.getMessage());
            return -1;
        }
    }

    private static void b(String str, a aVar) {
        if (!i) {
            throw new RuntimeException("Call SchemeManager.init() first!");
        }
        String str2 = "";
        String b2 = tyf.d(f17406a).b(str, "");
        String b3 = tyf.d(f17406a).b("SCHEME_SUPPORT_PACKAGE", "");
        boolean z = true;
        if (TextUtils.isEmpty(b2)) {
            str2 = b2;
        } else {
            String host = Uri.parse(b2).getHost();
            if (TextUtils.isEmpty(b3) || b3.equalsIgnoreCase(host)) {
                aVar.a(b2);
                str2 = b2;
                z = false;
            } else {
                tyf.d(f17406a).c(str, "");
            }
        }
        if (TextUtils.isEmpty(str2) && tyg.f17409a.containsKey(str)) {
            aVar.a(tyg.f17409a.get(str));
            z = false;
        }
        String replace = "https://api.zhangyue.com/third/pageid?package={package}&appId={appid}&from={from}&traceid={traceid}&schemeVersion={schemeVersion}&closeback={closeback}&backfrom={backfrom}".replace("{package}", b3).replace("{appid}", c).replace("{traceid}", h).replace("{from}", b).replace("{schemeVersion}", String.valueOf(f)).replace("{closeback}", "false").replace("{backfrom}", "false");
        String str3 = replace + "&sign=" + tyd.a(replace, d);
        if (!z) {
            aVar = null;
        }
        new e(str, str3, aVar).execute(new Void[0]);
    }
}

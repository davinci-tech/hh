package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.col.p0003sl.u;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.tools.GLFileUtil;
import com.autonavi.extra.b;
import com.huawei.hms.network.embedded.w9;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class t extends Thread {

    /* renamed from: a, reason: collision with root package name */
    WeakReference<IAMapDelegate> f1360a;
    private Context b;

    public t(Context context, IAMapDelegate iAMapDelegate) {
        this.f1360a = null;
        this.b = context;
        this.f1360a = new WeakReference<>(iAMapDelegate);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        JSONObject optJSONObject;
        WeakReference<IAMapDelegate> weakReference;
        WeakReference<IAMapDelegate> weakReference2;
        JSONObject optJSONObject2;
        JSONObject optJSONObject3;
        hz a2;
        JSONObject optJSONObject4;
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                ht.a().a(this.b);
                StringBuilder sb = new StringBuilder();
                sb.append("14S");
                sb.append(";");
                sb.append("11K");
                sb.append(";");
                sb.append("001");
                sb.append(";");
                sb.append("14M");
                sb.append(";");
                sb.append("14L");
                sb.append(";");
                sb.append("16V");
                sb.append(";");
                sb.append("14Z");
                sb.append(";");
                sb.append("154");
                sb.append(";");
                sb.append("156");
                sb.append(";");
                sb.append("15C");
                sb.append(";");
                sb.append("16G");
                sb.append(";");
                sb.append("17W");
                sb.append(";");
                sb.append("17E");
                try {
                    WeakReference<IAMapDelegate> weakReference3 = this.f1360a;
                    if (weakReference3 != null && weakReference3.get() != null) {
                        IAMapDelegate iAMapDelegate = this.f1360a.get();
                        if (iAMapDelegate.getAMapExtraInterfaceManager() != null) {
                            String g = iAMapDelegate.getAMapExtraInterfaceManager().g();
                            if (!TextUtils.isEmpty(g)) {
                                if (g.indexOf(";") == 0) {
                                    sb.append(g);
                                } else {
                                    sb.append(";");
                                    sb.append(g);
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
                Pair<JSONObject, ho.b.a> a3 = a(sb);
                if (a3 != null && a3.first != null && (optJSONObject4 = ((JSONObject) a3.first).optJSONObject("154")) != null && ho.a(optJSONObject4.getString("able"), true)) {
                    String optString = optJSONObject4.optString("mc");
                    String optString2 = optJSONObject4.optString("si");
                    String optString3 = optJSONObject4.optString(w9.m);
                    if (!TextUtils.isEmpty(optString)) {
                        dl.a(this.b, "approval_number", "mc", (Object) optString);
                    }
                    if (!TextUtils.isEmpty(optString2)) {
                        dl.a(this.b, "approval_number", "si", (Object) optString2);
                    }
                    if (!TextUtils.isEmpty(optString3)) {
                        dl.a(this.b, "approval_number", w9.m, (Object) optString3);
                    }
                }
                if (a3 != null && a3.second != null && (a2 = dv.a()) != null) {
                    a2.a(((ho.b.a) a3.second).f1136a);
                }
                if (a3 != null) {
                    a((ho.b.a) a3.second);
                }
                if (a3 != null) {
                    try {
                        if (a3.first != null && (optJSONObject = ((JSONObject) a3.first).optJSONObject("14M")) != null && optJSONObject.has("able") && ho.a(optJSONObject.getString("able"), true)) {
                            if (System.currentTimeMillis() - dl.a(this.b, "Map3DCache", "time", (Long) 0L).longValue() > (optJSONObject.has("time") ? Math.max(60, optJSONObject.getInt("time")) : 2592000) * 1000 && (weakReference = this.f1360a) != null && weakReference.get() != null) {
                                this.f1360a.get().clearTileCache();
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (a3 != null && a3.first != null) {
                    try {
                        JSONObject optJSONObject5 = ((JSONObject) a3.first).optJSONObject("14L");
                        if (optJSONObject5 != null && optJSONObject5.has("able")) {
                            boolean a4 = ho.a(optJSONObject5.getString("able"), false);
                            WeakReference<IAMapDelegate> weakReference4 = this.f1360a;
                            if (weakReference4 != null && weakReference4.get() != null) {
                                this.f1360a.get().setHideLogoEnble(!a4);
                            }
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (a3 != null && a3.first != null && (optJSONObject3 = ((JSONObject) a3.first).optJSONObject("156")) != null) {
                    dj.a(ho.a(optJSONObject3.optString("able"), false));
                }
                if (a3 != null && a3.first != null) {
                    a(this.b, dv.a(), (JSONObject) a3.first);
                }
                if (a3 != null && a3.first != null && (optJSONObject2 = ((JSONObject) a3.first).optJSONObject("15C")) != null) {
                    final boolean a5 = ho.a(optJSONObject2.optString("able"), false);
                    final String optString4 = optJSONObject2.optString("logo_day_url");
                    final String optString5 = optJSONObject2.optString("logo_day_md5");
                    final String optString6 = optJSONObject2.optString("logo_night_url");
                    final String optString7 = optJSONObject2.optString("logo_night_md5");
                    final String optString8 = optJSONObject2.optString("logo_day_ipv6_url");
                    final String optString9 = optJSONObject2.optString("logo_night_ipv6_url");
                    dt.a().a(new lb() { // from class: com.amap.api.col.3sl.t.1
                        @Override // com.amap.api.col.p0003sl.lb
                        public final void runTask() {
                            if (!TextUtils.isEmpty(optString5) && !TextUtils.isEmpty(optString4)) {
                                boolean z = a5;
                                String str = AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME;
                                String str2 = optString4;
                                String str3 = optString5;
                                String str4 = optString8;
                                if (z) {
                                    u.d dVar = new u.d(str2, str3, str4, str);
                                    dVar.a("amap_web_logo", "md5_day");
                                    Context context = t.this.b;
                                    dv.a();
                                    new u(context, dVar).a();
                                }
                                if (t.this.f1360a != null && t.this.f1360a.get() != null) {
                                    t.this.f1360a.get().changeLogoIconStyle(str, z, 0);
                                }
                            }
                            if (TextUtils.isEmpty(optString7) || TextUtils.isEmpty(optString6)) {
                                return;
                            }
                            boolean z2 = a5;
                            String str5 = AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME;
                            String str6 = optString6;
                            String str7 = optString7;
                            String str8 = optString9;
                            if (z2) {
                                u.d dVar2 = new u.d(str6, str7, str8, str5);
                                dVar2.a("amap_web_logo", "md5_night");
                                Context context2 = t.this.b;
                                dv.a();
                                new u(context2, dVar2).a();
                            }
                            if (t.this.f1360a == null || t.this.f1360a.get() == null) {
                                return;
                            }
                            t.this.f1360a.get().changeLogoIconStyle(str5, z2, 1);
                        }
                    });
                }
                if (a3 != null) {
                    try {
                        if (a3.first != null && (weakReference2 = this.f1360a) != null && weakReference2.get() != null) {
                            IAMapDelegate iAMapDelegate2 = this.f1360a.get();
                            if (iAMapDelegate2.getAMapExtraInterfaceManager() != null) {
                                b aMapExtraInterfaceManager = iAMapDelegate2.getAMapExtraInterfaceManager();
                                Object obj = a3.first;
                                aMapExtraInterfaceManager.h();
                            }
                        }
                    } catch (Throwable unused2) {
                    }
                }
                if (a3 != null && a3.first != null) {
                    b((JSONObject) a3.first);
                }
                if (a3 != null && a3.first != null) {
                    c((JSONObject) a3.first);
                }
                if (a3 != null && a3.first != null) {
                    a((JSONObject) a3.first);
                }
                iv.a(this.b, dv.a());
                interrupt();
                WeakReference<IAMapDelegate> weakReference5 = this.f1360a;
                if (weakReference5 == null || weakReference5.get() == null) {
                    return;
                }
                this.f1360a.get().setRunLowFrame(false);
            }
        } catch (Throwable th3) {
            interrupt();
            iv.c(th3, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th3.printStackTrace();
            dx.b(dw.e, "auth exception " + th3.getMessage());
        }
    }

    private static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            dx.a(jSONObject.optJSONObject("17E"));
        } catch (Throwable unused) {
        }
    }

    private static void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("16G");
            boolean a2 = ho.a(optJSONObject.optString("able", ""), false);
            boolean a3 = ho.a(optJSONObject.optString("removeCache", ""), false);
            boolean a4 = ho.a(optJSONObject.optString("uploadInfo", ""), false);
            dm.a(a2);
            dm.b(a3);
            dm.c(a4);
        } catch (Throwable unused) {
        }
    }

    private static void a(Context context, hz hzVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("16V");
            boolean a2 = ho.a(optJSONObject.optString("di", ""), false);
            String optString = optJSONObject.optString("dis", "");
            boolean a3 = ho.a(optJSONObject.optString("able", ""), false);
            boolean a4 = ho.a(optJSONObject.optString("isFilter", ""), true);
            if (!a2 || ia.e(optString)) {
                jd.a(hzVar).a(context, a3, a4);
            }
        } catch (Throwable unused) {
        }
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            dl.a(this.b, "amap_param", "overlay_use_old_type", Boolean.valueOf(!ho.a(jSONObject.optJSONObject("17W").optString("able", ""), false)));
        } catch (Throwable unused) {
        }
    }

    private void a(ho.b.a aVar) {
        if (aVar != null) {
            try {
                dr.a(this.b, "maploc", "ue", Boolean.valueOf(aVar.f1136a));
                JSONObject jSONObject = aVar.c;
                int optInt = jSONObject.optInt("fn", 1000);
                int optInt2 = jSONObject.optInt("mpn", 0);
                if (optInt2 > 500) {
                    optInt2 = 500;
                }
                if (optInt2 < 30) {
                    optInt2 = 30;
                }
                kh.a(optInt, ho.a(jSONObject.optString("igu"), false));
                dr.a(this.b, "maploc", "opn", Integer.valueOf(optInt2));
            } catch (Throwable th) {
                iv.c(th, "AuthUtil", "loadConfigDataUploadException");
            }
        }
    }

    @Override // java.lang.Thread
    public final void interrupt() {
        super.interrupt();
    }

    private Pair<JSONObject, ho.b.a> a(StringBuilder sb) {
        String str;
        JSONObject jSONObject;
        ho.b.a aVar;
        WeakReference<IAMapDelegate> weakReference;
        try {
            long longValue = dl.a(this.b, "cloud_config_pull", "cloud_config_pull_timestamp", (Long) 0L).longValue();
            long currentTimeMillis = System.currentTimeMillis();
            if (Math.abs(currentTimeMillis - longValue) >= 86400000) {
                str = sb.toString();
                str.replaceAll(";;", ";");
                dl.a(this.b, "cloud_config_pull", "cloud_config_pull_timestamp", (Object) new Long(currentTimeMillis));
            } else {
                str = "";
            }
            ho.b a2 = ho.a(this.b, dv.a(), str, (Map<String, String>) null);
            if (ho.f1133a != 1 && str != "" && a2 != null && (weakReference = this.f1360a) != null && weakReference.get() != null) {
                Message obtainMessage = this.f1360a.get().getMainHandler().obtainMessage();
                obtainMessage.what = 2;
                if (a2.c != null) {
                    obtainMessage.obj = a2.c;
                }
                this.f1360a.get().getMainHandler().sendMessage(obtainMessage);
            }
            String str2 = GLFileUtil.getCacheDir(this.b).getAbsolutePath() + "/authCustomConfigName";
            if (!TextUtils.isEmpty(str) && a2 != null && a2.f != null) {
                jSONObject = a2.f;
                GLFileUtil.writeDatasToFile(str2, a2.f.toString().getBytes());
            } else {
                jSONObject = new JSONObject(new String(GLFileUtil.readFileContents(str2)));
            }
            String str3 = GLFileUtil.getCacheDir(this.b).getAbsolutePath() + "/authLogConfigName";
            if (!TextUtils.isEmpty(str) && a2 != null && a2.g != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("IsExceptionUpdate", a2.g.f1136a);
                jSONObject2.put("mOfflineLoc", a2.g.c);
                GLFileUtil.writeDatasToFile(str3, jSONObject2.toString().getBytes());
                aVar = a2.g;
            } else {
                byte[] readFileContents = GLFileUtil.readFileContents(str3);
                ho.b.a aVar2 = new ho.b.a();
                JSONObject jSONObject3 = new JSONObject(new String(readFileContents));
                aVar2.f1136a = jSONObject3.getBoolean("IsExceptionUpdate");
                if (jSONObject3.has("mOfflineLoc")) {
                    aVar2.c = jSONObject3.getJSONObject("mOfflineLoc");
                }
                aVar = aVar2;
            }
            return new Pair<>(jSONObject, aVar);
        } catch (Throwable unused) {
            return null;
        }
    }
}

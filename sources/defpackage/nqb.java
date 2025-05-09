package defpackage;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.npw;
import defpackage.npx;
import defpackage.npy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nqb {
    private static volatile nqb d;

    public static nqb c() {
        nqb nqbVar;
        synchronized (nqb.class) {
            synchronized (nqb.class) {
                if (d == null) {
                    d = new nqb();
                }
                nqbVar = d;
            }
            return nqbVar;
        }
        return nqbVar;
    }

    private String d(String str, Context context) {
        BufferedReader bufferedReader;
        if (context == null) {
            LogUtil.h("MuscleJsonUtil", "getJson context == null");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str), StandardCharsets.UTF_8));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            try {
                                break;
                            } catch (IOException e) {
                                LogUtil.b("MuscleJsonUtil", "getJson error:", e.getMessage());
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        LogUtil.b("MuscleJsonUtil", "getJson error:", e.getMessage());
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e3) {
                                LogUtil.b("MuscleJsonUtil", "getJson error:", e3.getMessage());
                            }
                        }
                        return JsonSanitizer.sanitize(sb.toString());
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                LogUtil.b("MuscleJsonUtil", "getJson error:", e4.getMessage());
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
            }
        } catch (IOException e5) {
            e = e5;
        }
        return JsonSanitizer.sanitize(sb.toString());
    }

    public List<npy> d(Context context, int i) {
        String d2;
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            d2 = d("body/front.data", context);
        } else {
            d2 = i != 2 ? "" : d("body/back.data", context);
        }
        try {
            return (List) new Gson().fromJson(JsonSanitizer.sanitize(d2), new npy.a().getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b("MuscleJsonUtil", "getBodyPath error:", e.getMessage());
            return arrayList;
        }
    }

    public List<npw> b(Context context, int i) {
        String d2;
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            d2 = d("body/front_body.data", context);
        } else {
            d2 = i != 2 ? "" : d("body/back_body.data", context);
        }
        try {
            return (List) new Gson().fromJson(JsonSanitizer.sanitize(d2), new npw.e().getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b("MuscleJsonUtil", "getMusclePath error:", e.getMessage());
            return arrayList;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.util.List] */
    public npx e(Context context, int i, int i2) {
        String d2;
        ArrayList arrayList = new ArrayList();
        if (i == 1) {
            d2 = d("body/musclePositiveInfo.json", context);
        } else {
            d2 = i != 2 ? "" : d("body/muscleNegativeInfo.json", context);
        }
        try {
            arrayList = (List) new Gson().fromJson(JsonSanitizer.sanitize(d2), new npx.b().getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b("MuscleJsonUtil", "getMuscleInfo error:", e.getMessage());
        }
        if (i2 < arrayList.size() && i2 >= 0) {
            return (npx) arrayList.get(i2);
        }
        LogUtil.h("MuscleJsonUtil", "getMuscleInfo index error");
        return null;
    }
}

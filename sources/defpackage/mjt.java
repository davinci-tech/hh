package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.profile.profile.ProfileExtendConstants;
import com.huawei.ucd.helper.gles.Obj3DBufferLoadAider;
import com.huawei.ucd.medal.MedalBackType;
import com.huawei.ucd.medal.MedalView;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mjt {
    private static long c;
    private static String e;

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(java.util.Map<java.lang.String, java.util.ArrayList<com.huawei.pluginachievement.manager.model.MedalInfoDesc>> r3, int r4) {
        /*
            r0 = 0
            java.lang.String r1 = "PLGACHIEVE_AchieveSportMedalInitData"
            if (r3 != 0) goto Lf
            java.lang.String r3 = "isHaveSportMedals() secondTabRelationshipForSport == null"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            return r0
        Lf:
            r2 = 264(0x108, float:3.7E-43)
            if (r4 == r2) goto L2a
            r2 = 10001(0x2711, float:1.4014E-41)
            if (r4 == r2) goto L2a
            r2 = 282(0x11a, float:3.95E-43)
            if (r4 == r2) goto L2a
            r2 = 283(0x11b, float:3.97E-43)
            if (r4 == r2) goto L2a
            switch(r4) {
                case 257: goto L2a;
                case 258: goto L2a;
                case 259: goto L2a;
                default: goto L22;
            }
        L22:
            java.util.ArrayList r3 = new java.util.ArrayList
            r2 = 10
            r3.<init>(r2)
            goto L34
        L2a:
            java.lang.String r2 = java.lang.String.valueOf(r4)
            java.lang.Object r3 = r3.get(r2)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
        L34:
            java.util.List r3 = c(r3)
            boolean r3 = defpackage.koq.b(r3)
            if (r3 == 0) goto L4c
            java.lang.String r3 = "isHaveSportMedals() medalTabInfoDesc isEmpty, sporttype = "
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r3)
            return r0
        L4c:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mjt.a(java.util.Map, int):boolean");
    }

    public static boolean c() {
        String b = mct.b(BaseApplication.getContext(), "sportMedalLastShowTime");
        long h = CommonUtil.h(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "hw_health_start_count_key"));
        if (h == 1) {
            LogUtil.h("PLGACHIEVE_AchieveSportMedalInitData", "setMedalFloatButton startCount = ", Long.valueOf(h));
            return false;
        }
        String b2 = ash.b("is_reminder_dialog_click");
        if (!"true".equals(b2)) {
            LogUtil.h("PLGACHIEVE_AchieveSportMedalInitData", "isFirstTimeOfDay isPushDataRemind = ", b2);
            return false;
        }
        if (!TextUtils.isEmpty(b) && !mle.c(nsn.h(b), System.currentTimeMillis())) {
            return false;
        }
        mct.b(BaseApplication.getContext(), "sportMedalLastShowTime", String.valueOf(System.currentTimeMillis()));
        return true;
    }

    public static FrameLayout.LayoutParams ciO_(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = GravityCompat.END;
        layoutParams.setMargins(layoutParams.leftMargin, (int) context.getResources().getDimension(R.dimen._2131363478_res_0x7f0a0696), 0, layoutParams.bottomMargin);
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams ciN_(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(context.getResources().getDimensionPixelOffset(R.dimen._2131362954_res_0x7f0a048a), context.getResources().getDimensionPixelOffset(R.dimen._2131362954_res_0x7f0a048a));
        layoutParams.topMargin = context.getResources().getDimensionPixelOffset(R.dimen._2131363080_res_0x7f0a0508);
        layoutParams.addRule(3, R.id.icon);
        if (LanguageUtil.bc(context)) {
            layoutParams.setMarginStart((int) context.getResources().getDimension(R.dimen._2131363480_res_0x7f0a0698));
        } else {
            layoutParams.addRule(14);
        }
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams ciP_(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins((int) context.getResources().getDimension(R.dimen._2131362632_res_0x7f0a0348), (int) context.getResources().getDimension(R.dimen._2131362626_res_0x7f0a0342), (int) context.getResources().getDimension(R.dimen._2131362632_res_0x7f0a0348), 0);
        return layoutParams;
    }

    public static void d(Context context, Map<String, ArrayList<MedalInfoDesc>> map, int i, int i2) {
        ArrayList<MedalInfoDesc> arrayList;
        if (d() || context == null || map == null) {
            return;
        }
        if (i != 264 && i != 10001 && i != 282 && i != 283) {
            switch (i) {
                case 257:
                case 258:
                case 259:
                    break;
                default:
                    arrayList = new ArrayList<>(10);
                    break;
            }
            e(context, c(arrayList), i2);
            d(String.valueOf(i));
        }
        arrayList = map.get(String.valueOf(i));
        e(context, c(arrayList), i2);
        d(String.valueOf(i));
    }

    private static List<MedalInfoDesc> c(ArrayList<MedalInfoDesc> arrayList) {
        return koq.b(arrayList) ? new ArrayList(10) : e(arrayList, b());
    }

    private static List<MedalInfoDesc> e(ArrayList<MedalInfoDesc> arrayList, List<String> list) {
        ArrayList arrayList2 = new ArrayList(10);
        if (list != null && !koq.b(arrayList)) {
            Iterator<MedalInfoDesc> it = arrayList.iterator();
            while (it.hasNext()) {
                MedalInfoDesc next = it.next();
                if (next.acquireMedalType() != null && next.acquireMedalType().length() < 3 && next.acquireGainCount() <= 0 && !list.contains(next.acquireMedalId()) && (mlb.l(next.acquireMedalId()) <= 19 || !c(next.acquireMedalId()))) {
                    arrayList2.add(next);
                    if (arrayList2.size() >= 5) {
                        break;
                    }
                }
            }
        }
        return arrayList2;
    }

    private static boolean c(String str) {
        return !new File(mlb.d(str, ParsedFieldTag.LIGHT_LIST_STYLE)).exists();
    }

    private static List<String> b() {
        ArrayList arrayList = new ArrayList(10);
        String b = mct.b(BaseApplication.getContext(), "_sportGainMedalIdArray");
        if (TextUtils.isEmpty(b)) {
            return arrayList;
        }
        LogUtil.a("PLGACHIEVE_AchieveSportMedalInitData", "dealNotUploadMedal=", b);
        String[] split = b.split(",");
        return split.length != 0 ? Arrays.asList(split) : arrayList;
    }

    public static void e(Context context, List<MedalInfoDesc> list, int i) {
        if (context == null || koq.b(list)) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(context, PersonalData.CLASS_NAME_SPORT_MEDAL_VIEW);
        if (list instanceof Serializable) {
            intent.putExtra("sport_medal_record", (Serializable) list);
        }
        intent.putExtra("medal_sport_type_bi_click", String.valueOf(i));
        intent.setFlags(276824064);
        context.startActivity(intent);
        e(String.valueOf(i));
    }

    public static void d(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("from", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MEDAL_SPORT_2041090.value(), hashMap, 0);
    }

    public static void e(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("from", str);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MEDAL_SPORT_2041091.value(), hashMap, 0);
    }

    public static boolean d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - c < ProfileExtendConstants.TIME_OUT) {
            return true;
        }
        c = elapsedRealtime;
        return false;
    }

    public static int e(String str, boolean z) {
        MedalInfo medalInfo = mla.e().c(true).get(str);
        if (medalInfo == null) {
            return 0;
        }
        if (z) {
            LogUtil.a("PLGACHIEVE_AchieveSportMedalInitData", "medalInfo getEnableResId is ", Integer.valueOf(medalInfo.getEnableResId()));
            return medalInfo.getEnableResId();
        }
        return medalInfo.getDisableResId();
    }

    public static String b(UserInfomation userInfomation) {
        if (userInfomation == null) {
            return "";
        }
        String name = userInfomation.getName();
        return TextUtils.isEmpty(name) ? new UpApi(BaseApplication.getContext()).getAccountName() : name;
    }

    public static void ciR_(MedalInfoDesc medalInfoDesc, LinearLayout linearLayout, ImageView imageView) {
        if (medalInfoDesc == null) {
            return;
        }
        String acquireMedalId = medalInfoDesc.acquireMedalId();
        Bitmap cko_ = mlb.cko_(acquireMedalId, true, false);
        int e2 = e(medalInfoDesc.acquireMedalTypeLevel(), true);
        String a2 = meb.a(acquireMedalId);
        if (!mfl.e(BaseApplication.getContext(), acquireMedalId)) {
            if (cko_ != null) {
                imageView.setImageBitmap(cko_);
                return;
            } else {
                if (e2 != 0) {
                    imageView.setImageResource(e2);
                    return;
                }
                return;
            }
        }
        LogUtil.c("PLGACHIEVE_AchieveSportMedalInitData", "3D medal");
        if (ciQ_(linearLayout, a2)) {
            return;
        }
        if (cko_ == null) {
            imageView.setImageResource(e2);
        } else {
            imageView.setImageBitmap(cko_);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v6 */
    private static boolean ciQ_(LinearLayout linearLayout, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        FileInputStream fileInputStream4;
        FileInputStream fileInputStream5;
        FileInputStream fileInputStream6;
        FileInputStream fileInputStream7;
        FileInputStream fileInputStream8;
        FileInputStream fileInputStream9;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_AchieveSportMedalInitData", "initMedal: untrusted -> " + str);
            return false;
        }
        String str2 = CommonUtil.c(str) + File.separator;
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("PLGACHIEVE_AchieveSportMedalInitData", "initMedal: path is empty");
            return false;
        }
        String str3 = str2 + "medal.tST";
        FileInputStream fileInputStream10 = str2 + "medal.nXYZ";
        String str4 = str2 + "medal.vXYZ";
        Bitmap ciM_ = ciM_(str2 + "texture.jpg");
        FileInputStream fileInputStream11 = null;
        try {
            try {
                fileInputStream4 = new FileInputStream(str3);
                try {
                    fileInputStream8 = new FileInputStream((String) fileInputStream10);
                    try {
                        fileInputStream9 = new FileInputStream(str4);
                    } catch (FileNotFoundException unused) {
                        fileInputStream9 = null;
                    } catch (OutOfMemoryError unused2) {
                        fileInputStream9 = null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream9 = null;
                    }
                } catch (FileNotFoundException unused3) {
                    fileInputStream5 = null;
                } catch (OutOfMemoryError unused4) {
                    fileInputStream3 = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream7 = null;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream11;
                fileInputStream6 = fileInputStream10;
            }
            try {
                ciS_(fileInputStream4, fileInputStream8, fileInputStream9, ciM_, linearLayout);
                d(fileInputStream4, fileInputStream8, fileInputStream9);
                return true;
            } catch (FileNotFoundException unused5) {
                fileInputStream11 = fileInputStream8;
                fileInputStream5 = fileInputStream9;
                LogUtil.c("PLGACHIEVE_AchieveSportMedalInitData", " close InputStream is exception");
                fileInputStream10 = fileInputStream5;
                d(fileInputStream4, fileInputStream11, fileInputStream10);
                return false;
            } catch (OutOfMemoryError unused6) {
                fileInputStream11 = fileInputStream8;
                fileInputStream3 = fileInputStream9;
                LogUtil.b("PLGACHIEVE_AchieveSportMedalInitData", "Obj3DBufferLoadAider.loadFromInputStream OutOfMemoryError");
                fileInputStream10 = fileInputStream3;
                d(fileInputStream4, fileInputStream11, fileInputStream10);
                return false;
            } catch (Throwable th4) {
                th = th4;
                fileInputStream11 = fileInputStream8;
                fileInputStream7 = fileInputStream9;
                fileInputStream = fileInputStream11;
                fileInputStream6 = fileInputStream7;
                fileInputStream11 = fileInputStream4;
                fileInputStream2 = fileInputStream6;
                d(fileInputStream11, fileInputStream, fileInputStream2);
                throw th;
            }
        } catch (FileNotFoundException unused7) {
            fileInputStream5 = null;
            fileInputStream4 = null;
        } catch (OutOfMemoryError unused8) {
            fileInputStream3 = null;
            fileInputStream4 = null;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            fileInputStream2 = null;
            d(fileInputStream11, fileInputStream, fileInputStream2);
            throw th;
        }
    }

    private static Bitmap ciM_(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_AchieveSportMedalInitData", "getBitmapFromPath: untrusted -> " + str);
            return null;
        }
        String c2 = CommonUtil.c(str);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        if (!new File(c2).exists()) {
            LogUtil.c("PLGACHIEVE_AchieveSportMedalInitData", "getBitmapFromPath: file not exists");
            return null;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(c2);
        if (decodeFile == null) {
            LogUtil.c("PLGACHIEVE_AchieveSportMedalInitData", " bitmap is null");
        }
        return decodeFile;
    }

    private static void ciS_(final InputStream inputStream, final InputStream inputStream2, final InputStream inputStream3, final Bitmap bitmap, final LinearLayout linearLayout) {
        new Obj3DBufferLoadAider().c(BaseApplication.getContext(), inputStream, inputStream2, inputStream3, new Obj3DBufferLoadAider.OnLoadListener() { // from class: mjt.4
            @Override // com.huawei.ucd.helper.gles.Obj3DBufferLoadAider.OnLoadListener
            public void onLoadOK(final Obj3DBufferLoadAider.d dVar) {
                if (dVar == null) {
                    return;
                }
                HandlerExecutor.e(new Runnable() { // from class: mjt.4.4
                    @Override // java.lang.Runnable
                    public void run() {
                        linearLayout.removeAllViews();
                        MedalView medalView = new MedalView(BaseApplication.getContext());
                        if (!TextUtils.isEmpty(mjt.e())) {
                            medalView.setBackContent(new String[]{mjt.e(), BaseApplication.getContext().getResources().getString(R.string._2130840759_res_0x7f020cb7)}, MedalBackType.ModelType.SOLID_CIRCLE, MedalBackType.ColorType.GOLD);
                        }
                        medalView.setTexture(bitmap);
                        medalView.setTouchRect(new Rect(0, 0, mlu.f(BaseApplication.getContext()), mlu.j(BaseApplication.getContext())));
                        medalView.setObjData(dVar);
                        medalView.setAnimatorScaleX(0.02f, 0.12f);
                        medalView.setAnimatorScaleY(0.02f, 0.12f);
                        medalView.setAnimatorRotationY(0.0f, 360.0f);
                        medalView.setAnimatorPositionX(nsn.c(BaseApplication.getContext(), 150.0f), nsn.c(BaseApplication.getContext(), 150.0f));
                        medalView.setAnimatorPositionY(nsn.c(BaseApplication.getContext(), 150.0f), nsn.c(BaseApplication.getContext(), 150.0f));
                        medalView.setAnimatorDuration(1000L);
                        linearLayout.addView(medalView);
                        medalView.e();
                    }
                });
                mjt.d(inputStream, inputStream2, inputStream3);
            }

            @Override // com.huawei.ucd.helper.gles.Obj3DBufferLoadAider.OnLoadListener
            public void onLoadFailed(String str) {
                LogUtil.b("PLGACHIEVE_AchieveSportMedalInitData", "Obj3DBufferLoadAider.OnLoadListener load failed，onLoadFailed msg=", str);
                mjt.d(inputStream, inputStream2, inputStream3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e() {
        if (TextUtils.isEmpty(e)) {
            e = mlb.a(b(((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo()));
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(InputStream inputStream, InputStream inputStream2, InputStream inputStream3) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_AchieveSportMedalInitData", " InpuStream tst close exception!");
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException unused2) {
                LogUtil.b("PLGACHIEVE_AchieveSportMedalInitData", " InputStream nxy close exception!");
            }
        }
        if (inputStream3 != null) {
            try {
                inputStream3.close();
            } catch (IOException unused3) {
                LogUtil.b("PLGACHIEVE_AchieveSportMedalInitData", " InputStream xyz close exception!");
            }
        }
    }
}

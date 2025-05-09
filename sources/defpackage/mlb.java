package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.pluginachievement.manager.model.MedalInfoDesc;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.profile.profile.ProfileExtendConstants;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class mlb {
    private static long d;
    private static final String[] b = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
    private static final List<String> e = Arrays.asList("D1", "D2", "C6", "C7", "B8", "B9", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "B19", "B20", "B21", "B22");

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f15049a = Arrays.asList("B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "B19", "B20", "B21", "B22");
    private static final String[] c = {"A5", "A10", "A20", "A40", WatchFaceConstant.PRESET_RES, "B7", "B21", "B100", "A2_10", "A2_50", "A2_100", "C10000", "C100000", "C300000", "C1000000", "C3000000", "C5000000", "C10000000", "C15000000"};

    public static boolean a(int i) {
        return i == 262 || i == 266;
    }

    public static boolean b(int i) {
        return i == 258 || i == 264 || i == 280;
    }

    public static boolean c(int i) {
        return i == 259;
    }

    public static boolean e(int i) {
        return i == 283;
    }

    public static List<String> c() {
        return Arrays.asList(b);
    }

    public static List<String> d() {
        return e;
    }

    public static boolean n(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return e.contains(str);
    }

    public static boolean i(String str) {
        return (TextUtils.isEmpty(str) || n(str) || str.length() < 3) ? false : true;
    }

    public static List<String> a() {
        return Utils.o() ? Arrays.asList(b) : new ArrayList(0);
    }

    public static boolean e() {
        return Utils.o() && !((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isOperation(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
    }

    public static void b(Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        String[] strArr = c;
        map.put(strArr[0], Integer.valueOf(R.mipmap._2131821087_res_0x7f11021f));
        map.put(strArr[1], Integer.valueOf(R.mipmap._2131821082_res_0x7f11021a));
        map.put(strArr[2], Integer.valueOf(R.mipmap._2131821092_res_0x7f110224));
        map.put(strArr[3], Integer.valueOf(R.mipmap._2131821096_res_0x7f110228));
        map.put(strArr[4], Integer.valueOf(R.mipmap._2131821094_res_0x7f110226));
        map.put(strArr[5], Integer.valueOf(R.mipmap._2131821149_res_0x7f11025d));
        map.put(strArr[6], Integer.valueOf(R.mipmap._2131821147_res_0x7f11025b));
        map.put(strArr[7], Integer.valueOf(R.mipmap._2131821145_res_0x7f110259));
        map.put(strArr[8], Integer.valueOf(R.mipmap._2131821071_res_0x7f11020f));
        map.put(strArr[9], Integer.valueOf(R.mipmap._2131821074_res_0x7f110212));
        map.put(strArr[10], Integer.valueOf(R.mipmap._2131821068_res_0x7f11020c));
        if (Utils.o()) {
            map.put(strArr[11], Integer.valueOf(R.mipmap._2131821151_res_0x7f11025f));
            map.put(strArr[12], Integer.valueOf(R.mipmap._2131821157_res_0x7f110265));
            map.put(strArr[13], Integer.valueOf(R.mipmap._2131821184_res_0x7f110280));
            map.put(strArr[14], Integer.valueOf(R.mipmap._2131821174_res_0x7f110276));
            map.put(strArr[15], Integer.valueOf(R.mipmap._2131821190_res_0x7f110286));
            map.put(strArr[16], Integer.valueOf(R.mipmap._2131821197_res_0x7f11028d));
            map.put(strArr[17], Integer.valueOf(R.mipmap._2131821162_res_0x7f11026a));
            map.put(strArr[18], Integer.valueOf(R.mipmap._2131821170_res_0x7f110272));
            return;
        }
        map.put(strArr[11], Integer.valueOf(R.mipmap._2131821151_res_0x7f11025f));
        map.put(strArr[12], Integer.valueOf(R.mipmap._2131821143_res_0x7f110257));
        map.put(strArr[13], Integer.valueOf(R.mipmap._2131821180_res_0x7f11027c));
        map.put(strArr[14], Integer.valueOf(R.mipmap._2131821141_res_0x7f110255));
        map.put(strArr[15], Integer.valueOf(R.mipmap._2131821178_res_0x7f11027a));
        map.put(strArr[16], Integer.valueOf(R.mipmap._2131821192_res_0x7f110288));
        map.put(strArr[17], Integer.valueOf(R.mipmap._2131821139_res_0x7f110253));
        map.put(strArr[18], Integer.valueOf(R.mipmap._2131821165_res_0x7f11026d));
    }

    public static void c(Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        map.put("1", Integer.valueOf(R.mipmap._2131821087_res_0x7f11021f));
        map.put("2", Integer.valueOf(R.mipmap._2131821082_res_0x7f11021a));
        map.put("3", Integer.valueOf(R.mipmap._2131821092_res_0x7f110224));
        map.put("4", Integer.valueOf(R.mipmap._2131821096_res_0x7f110228));
        map.put("5", Integer.valueOf(R.mipmap._2131821094_res_0x7f110226));
        map.put("6", Integer.valueOf(R.mipmap._2131821149_res_0x7f11025d));
        map.put("7", Integer.valueOf(R.mipmap._2131821147_res_0x7f11025b));
        map.put("8", Integer.valueOf(R.mipmap._2131821145_res_0x7f110259));
        if (Utils.o()) {
            map.put("9", Integer.valueOf(R.mipmap._2131821151_res_0x7f11025f));
            map.put("10", Integer.valueOf(R.mipmap._2131821157_res_0x7f110265));
            map.put("11", Integer.valueOf(R.mipmap._2131821184_res_0x7f110280));
            map.put("12", Integer.valueOf(R.mipmap._2131821174_res_0x7f110276));
            map.put("13", Integer.valueOf(R.mipmap._2131821190_res_0x7f110286));
            map.put("14", Integer.valueOf(R.mipmap._2131821197_res_0x7f11028d));
            map.put("15", Integer.valueOf(R.mipmap._2131821162_res_0x7f11026a));
            map.put("16", Integer.valueOf(R.mipmap._2131821170_res_0x7f110272));
        } else {
            map.put("9", Integer.valueOf(R.mipmap._2131821151_res_0x7f11025f));
            map.put("10", Integer.valueOf(R.mipmap._2131821143_res_0x7f110257));
            map.put("11", Integer.valueOf(R.mipmap._2131821180_res_0x7f11027c));
            map.put("12", Integer.valueOf(R.mipmap._2131821141_res_0x7f110255));
            map.put("13", Integer.valueOf(R.mipmap._2131821178_res_0x7f11027a));
            map.put("14", Integer.valueOf(R.mipmap._2131821192_res_0x7f110288));
            map.put("15", Integer.valueOf(R.mipmap._2131821139_res_0x7f110253));
            map.put("16", Integer.valueOf(R.mipmap._2131821165_res_0x7f11026d));
        }
        map.put("17", Integer.valueOf(R.mipmap._2131821071_res_0x7f11020f));
        map.put("18", Integer.valueOf(R.mipmap._2131821074_res_0x7f110212));
        map.put("19", Integer.valueOf(R.mipmap._2131821068_res_0x7f11020c));
    }

    public static void a(Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        map.put("1", Integer.valueOf(R.mipmap._2131821088_res_0x7f110220));
        map.put("2", Integer.valueOf(R.mipmap._2131821083_res_0x7f11021b));
        map.put("3", Integer.valueOf(R.mipmap._2131821093_res_0x7f110225));
        map.put("4", Integer.valueOf(R.mipmap._2131821097_res_0x7f110229));
        map.put("5", Integer.valueOf(R.mipmap._2131821095_res_0x7f110227));
        map.put("6", Integer.valueOf(R.mipmap._2131821150_res_0x7f11025e));
        map.put("7", Integer.valueOf(R.mipmap._2131821148_res_0x7f11025c));
        map.put("8", Integer.valueOf(R.mipmap._2131821146_res_0x7f11025a));
        if (Utils.o()) {
            map.put("9", Integer.valueOf(R.mipmap._2131821152_res_0x7f110260));
            map.put("10", Integer.valueOf(R.mipmap._2131821158_res_0x7f110266));
            map.put("11", Integer.valueOf(R.mipmap._2131821185_res_0x7f110281));
            map.put("12", Integer.valueOf(R.mipmap._2131821175_res_0x7f110277));
            map.put("13", Integer.valueOf(R.mipmap._2131821191_res_0x7f110287));
            map.put("14", Integer.valueOf(R.mipmap._2131821198_res_0x7f11028e));
            map.put("15", Integer.valueOf(R.mipmap._2131821163_res_0x7f11026b));
            map.put("16", Integer.valueOf(R.mipmap._2131821171_res_0x7f110273));
        } else {
            map.put("9", Integer.valueOf(R.mipmap._2131821152_res_0x7f110260));
            map.put("10", Integer.valueOf(R.mipmap._2131821144_res_0x7f110258));
            map.put("11", Integer.valueOf(R.mipmap._2131821181_res_0x7f11027d));
            map.put("12", Integer.valueOf(R.mipmap._2131821142_res_0x7f110256));
            map.put("13", Integer.valueOf(R.mipmap._2131821179_res_0x7f11027b));
            map.put("14", Integer.valueOf(R.mipmap._2131821193_res_0x7f110289));
            map.put("15", Integer.valueOf(R.mipmap._2131821140_res_0x7f110254));
            map.put("16", Integer.valueOf(R.mipmap._2131821166_res_0x7f11026e));
        }
        map.put("17", Integer.valueOf(R.mipmap._2131821072_res_0x7f110210));
        map.put("18", Integer.valueOf(R.mipmap._2131821075_res_0x7f110213));
        map.put("19", Integer.valueOf(R.mipmap._2131821069_res_0x7f11020d));
    }

    public static void e(Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        String[] strArr = c;
        map.put(strArr[0], Integer.valueOf(R.mipmap._2131821086_res_0x7f11021e));
        map.put(strArr[1], Integer.valueOf(R.mipmap._2131821081_res_0x7f110219));
        map.put(strArr[2], Integer.valueOf(R.mipmap._2131821090_res_0x7f110222));
        map.put(strArr[3], Integer.valueOf(R.mipmap._2131821098_res_0x7f11022a));
        map.put(strArr[4], Integer.valueOf(R.mipmap._2131821099_res_0x7f11022b));
        map.put(strArr[5], Integer.valueOf(R.mipmap._2131821199_res_0x7f11028f));
        map.put(strArr[6], Integer.valueOf(R.mipmap._2131821177_res_0x7f110279));
        map.put(strArr[7], Integer.valueOf(R.mipmap._2131821154_res_0x7f110262));
        map.put(strArr[8], Integer.valueOf(R.mipmap._2131821070_res_0x7f11020e));
        map.put(strArr[9], Integer.valueOf(R.mipmap._2131821073_res_0x7f110211));
        map.put(strArr[10], Integer.valueOf(R.mipmap._2131821067_res_0x7f11020b));
        if (Utils.o()) {
            map.put(strArr[11], Integer.valueOf(R.mipmap._2131821176_res_0x7f110278));
            map.put(strArr[12], Integer.valueOf(R.mipmap._2131821156_res_0x7f110264));
            map.put(strArr[13], Integer.valueOf(R.mipmap._2131821183_res_0x7f11027f));
            map.put(strArr[14], Integer.valueOf(R.mipmap._2131821173_res_0x7f110275));
            map.put(strArr[15], Integer.valueOf(R.mipmap._2131821189_res_0x7f110285));
            map.put(strArr[16], Integer.valueOf(R.mipmap._2131821196_res_0x7f11028c));
            map.put(strArr[17], Integer.valueOf(R.mipmap._2131821161_res_0x7f110269));
            map.put(strArr[18], Integer.valueOf(R.mipmap._2131821169_res_0x7f110271));
            return;
        }
        map.put(strArr[11], Integer.valueOf(R.mipmap._2131821176_res_0x7f110278));
        map.put(strArr[12], Integer.valueOf(R.mipmap._2131821164_res_0x7f11026c));
        map.put(strArr[13], Integer.valueOf(R.mipmap._2131821187_res_0x7f110283));
        map.put(strArr[14], Integer.valueOf(R.mipmap._2131821159_res_0x7f110267));
        map.put(strArr[15], Integer.valueOf(R.mipmap._2131821186_res_0x7f110282));
        map.put(strArr[16], Integer.valueOf(R.mipmap._2131821194_res_0x7f11028a));
        map.put(strArr[17], Integer.valueOf(R.mipmap._2131821153_res_0x7f110261));
        map.put(strArr[18], Integer.valueOf(R.mipmap._2131821167_res_0x7f11026f));
    }

    public static void d(Map<String, Integer> map) {
        if (map == null) {
            return;
        }
        String[] strArr = c;
        map.put(strArr[0], Integer.valueOf(R.mipmap._2131820686_res_0x7f11008e));
        map.put(strArr[1], Integer.valueOf(R.mipmap._2131820685_res_0x7f11008d));
        map.put(strArr[2], Integer.valueOf(R.mipmap._2131820687_res_0x7f11008f));
        map.put(strArr[3], Integer.valueOf(R.mipmap._2131820688_res_0x7f110090));
        map.put(strArr[4], Integer.valueOf(R.mipmap._2131820689_res_0x7f110091));
        map.put(strArr[5], Integer.valueOf(R.mipmap._2131820700_res_0x7f11009c));
        map.put(strArr[6], Integer.valueOf(R.mipmap._2131820696_res_0x7f110098));
        map.put(strArr[7], Integer.valueOf(R.mipmap._2131820691_res_0x7f110093));
        map.put(strArr[8], Integer.valueOf(R.mipmap._2131820683_res_0x7f11008b));
        map.put(strArr[9], Integer.valueOf(R.mipmap._2131820684_res_0x7f11008c));
        map.put(strArr[10], Integer.valueOf(R.mipmap._2131820682_res_0x7f11008a));
        if (Utils.o()) {
            map.put(strArr[11], Integer.valueOf(R.mipmap._2131820695_res_0x7f110097));
            map.put(strArr[12], Integer.valueOf(R.mipmap._2131821155_res_0x7f110263));
            map.put(strArr[13], Integer.valueOf(R.mipmap._2131821182_res_0x7f11027e));
            map.put(strArr[14], Integer.valueOf(R.mipmap._2131821172_res_0x7f110274));
            map.put(strArr[15], Integer.valueOf(R.mipmap._2131821188_res_0x7f110284));
            map.put(strArr[16], Integer.valueOf(R.mipmap._2131821195_res_0x7f11028b));
            map.put(strArr[17], Integer.valueOf(R.mipmap._2131821160_res_0x7f110268));
            map.put(strArr[18], Integer.valueOf(R.mipmap._2131821168_res_0x7f110270));
            return;
        }
        map.put(strArr[11], Integer.valueOf(R.mipmap._2131820695_res_0x7f110097));
        map.put(strArr[12], Integer.valueOf(R.mipmap._2131820693_res_0x7f110095));
        map.put(strArr[13], Integer.valueOf(R.mipmap._2131820698_res_0x7f11009a));
        map.put(strArr[14], Integer.valueOf(R.mipmap._2131820692_res_0x7f110094));
        map.put(strArr[15], Integer.valueOf(R.mipmap._2131820697_res_0x7f110099));
        map.put(strArr[16], Integer.valueOf(R.mipmap._2131820699_res_0x7f11009b));
        map.put(strArr[17], Integer.valueOf(R.mipmap._2131820690_res_0x7f110092));
        map.put(strArr[18], Integer.valueOf(R.mipmap._2131820694_res_0x7f110096));
    }

    public static Bitmap cko_(String str, boolean z, boolean z2) {
        String i;
        if (str == null) {
            return null;
        }
        if (z) {
            i = i(str, ParsedFieldTag.LIGHT_DETAIL_STYLE);
        } else {
            i = i(str, ParsedFieldTag.GRAY_DETAIL_STYLE);
        }
        if (!z2) {
            return ckn_(str, i);
        }
        return ckq_(str, i(str, ParsedFieldTag.LIGHT_LIST_STYLE));
    }

    private static String i(String str, String str2) {
        if (str2 == null || str == null) {
            return "";
        }
        String b2 = b(str);
        String c2 = c(str, str2);
        return c(b2) + File.separator + c2 + ".png";
    }

    public static Bitmap ckq_(String str, String str2) {
        Bitmap bitmap = null;
        if (!GeneralUtil.isSafePath(str2)) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "getSmallBitmap: untrusted -> " + str2);
            return null;
        }
        String c2 = CommonUtil.c(str2);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(c2, options);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        try {
            bitmap = BitmapFactory.decodeFile(c2, options);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "showMedalBitmap getSmallBitmap:OutOfMemoryError");
        }
        if (bitmap == null) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "showMedalBitmap getSmallBitmap is null! getBitmapFromPath ", str);
        }
        return bitmap;
    }

    public static Bitmap ckp_(String str) {
        if (str == null) {
            return null;
        }
        String b2 = b(str);
        String c2 = c(str, ParsedFieldTag.SHARE_IMAGE_URL);
        return ckn_(str, c(b2) + File.separator + c2 + ".png");
    }

    public static String d(String str, String str2) {
        String b2 = b(str);
        String c2 = c(str, str2);
        return c(b2) + File.separator + c2 + ".png";
    }

    public static String b(String str) {
        return "medal_" + str;
    }

    public static String c(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return str + "_" + str2;
    }

    public static String c(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "getOneMedalPngPath: untrusted -> " + str);
            return "";
        }
        try {
            return (BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "achievemedalpng") + File.separator + str;
        } catch (IOException unused) {
            LogUtil.c("PLGACHIEVE_MedalUtil", "getOneMedalPngPath IOException:");
            return null;
        }
    }

    public static Bitmap ckn_(String str, String str2) {
        Bitmap bitmap = null;
        if (!GeneralUtil.isSafePath(str2)) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "getBitmapFromPath: untrusted -> " + str2);
            return null;
        }
        String c2 = CommonUtil.c(str2);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        if (!new File(c2).exists()) {
            LogUtil.c("PLGACHIEVE_MedalUtil", "getBitmapFromPath: file not exists");
            return null;
        }
        try {
            bitmap = BitmapFactory.decodeFile(c2);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "showMedalBitmap getBitmapFromPath:OutOfMemoryError");
        }
        if (bitmap == null) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "showMedalBitmap is null! getBitmapFromPath ", str);
        }
        return bitmap;
    }

    public static boolean d(String str) {
        LogUtil.c("MedalUtil", "fileIsExists path=", str);
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "fileIsExists: untrusted -> " + str);
            return false;
        }
        String c2 = CommonUtil.c(str);
        if (TextUtils.isEmpty(c2)) {
            return false;
        }
        if (!new File(c2).exists()) {
            LogUtil.c("MedalUtil", "file Is not Exists ");
            return false;
        }
        LogUtil.c("MedalUtil", "file Is Exists ");
        return true;
    }

    public static boolean d(String str, int i) {
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        if (i > 0 || TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "isNeedShow NumberFormatException");
            j = 0;
        }
        if (currentTimeMillis - 259200000 <= j) {
            return true;
        }
        LogUtil.c("PLGACHIEVE_MedalUtil", "isNeedShow =false");
        return false;
    }

    public static boolean j(String str) {
        return !TextUtils.isEmpty(str) && f15049a.contains(str);
    }

    public static boolean b(String str, String str2, String str3, int i) {
        if (i > 0) {
            return true;
        }
        return r(str2) && f(str3, PhoneInfoUtils.getPhoneType());
    }

    private static boolean f(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.contains(str2);
    }

    private static boolean r(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.contains("1");
    }

    public static String a(String str) {
        return str == null ? "" : str.length() > 10 ? str.substring(0, 10).concat("...") : str;
    }

    public static boolean o(String str) {
        return MedalConstants.ACTION_MEDAL_TYPE.equals(str) || MedalConstants.MATCH_MEDAL_TYPE.equals(str);
    }

    public static boolean k(String str) {
        return o(str) || MedalConstants.HEALTH_MEDAL_TYPE.equals(str);
    }

    public static long e(String str, String str2) {
        try {
            return new SimpleDateFormat(str2, Locale.ENGLISH).parse(str).getTime();
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "date2Timestamp IllegalArgumentException");
            return 0L;
        } catch (ParseException unused2) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "date2Timestamp ParseException");
            return 0L;
        }
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return UnitUtil.a(new Date(Long.parseLong(str)), 20);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "setGainTime NumberFormatException");
            return str;
        }
    }

    public static String e(double d2, int i) {
        String e2;
        LogUtil.a("PLGACHIEVE_MedalUtil", "value = ", Double.valueOf(d2), " num = ", Integer.valueOf(i));
        double e3 = UnitUtil.e(d2, 3);
        if (i == 0) {
            e2 = UnitUtil.e(e3, 1, 0);
        } else if (4 == i) {
            e2 = UnitUtil.e(e3, 1, 4);
        } else {
            e2 = 3 == i ? UnitUtil.e(e3, 1, 3) : "";
        }
        LogUtil.a("PLGACHIEVE_MedalUtil", "transferEnglishUnit():valueMile =", Double.valueOf(e3), ",valueMileStr = ", e2);
        return e2;
    }

    public static int l(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "string2Int e=", e2.getMessage());
            return 0;
        }
    }

    public static String d(int i) {
        return b(i) ? "A" : c(i) ? "A2" : a(i) ? "A3" : e(i) ? "A5" : "";
    }

    public static String m(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String a2 = UnitUtil.a(new Date(Long.parseLong(str)), 20);
            return String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(R.string._2130840762_res_0x7f020cba), a2);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_MedalUtil", "setGainTime NumberFormatException");
            return "";
        }
    }

    public static void e(List<MedalInfoDesc> list, Map<String, Bitmap> map) {
        if (koq.b(list) || map == null) {
            return;
        }
        for (MedalInfoDesc medalInfoDesc : list) {
            map.put(medalInfoDesc.acquireMedalId(), cko_(medalInfoDesc.acquireMedalId(), true, false));
        }
    }

    public static boolean g(String str) {
        if (MedalConstants.ACTION_MEDAL_TYPE.equals(str) && e()) {
            return true;
        }
        return MedalConstants.ACTION_MEDAL_TYPE.equals(str) && LoginInit.getInstance(BaseApplication.getContext()).isKidAccount();
    }

    public static boolean b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - d < ProfileExtendConstants.TIME_OUT) {
            return true;
        }
        d = elapsedRealtime;
        return false;
    }

    public static boolean f(String str) {
        Context context = BaseApplication.getContext();
        String b2 = mct.b(context, "_sportGainMedalIdArray");
        if (!TextUtils.isEmpty(b2) && Arrays.asList(b2.split(",")).contains(str)) {
            LogUtil.h("PLGACHIEVE_MedalUtil", "interceptMedalRemind: reminded -> " + str);
            return true;
        }
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(b2)) {
            sb.append(str);
        } else {
            sb.append(b2);
            sb.append(",");
            sb.append(str);
        }
        mct.b(context, "_sportGainMedalIdArray", sb.toString());
        return false;
    }
}

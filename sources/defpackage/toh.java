package defpackage;

import android.content.Context;
import android.os.Binder;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.repository.api.AuthInfoRepository;
import com.huawei.wearengine.scope.ScopeManager;
import com.huawei.wearengine.scope.ScopeServerRequest;
import com.huawei.wearengine.sensor.Sensor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class toh {

    /* renamed from: a, reason: collision with root package name */
    private AuthInfoRepository f17277a;
    private String f;
    private ScopeManager i = new ScopeManager(tot.a());
    private static c[] d = {new c("com.huawei.hiwearkit.sdk", "67DFB7FD19CB4C508A8F25E313FE3B13A7300375758EBA8AE559B3B29884A849", -1), new c("com.huawei.superwearengine.demo", "5F584EC7444D65099150B4D607D0D53AE9FCB77683844CAB9F5D91BAB793AD43", -1), new c("com.huawei.trustedthingsauth", "0F37AF8E1D7E009EFE579FBFE2A87BFEE7EDEAC689767A54B4F90B89FE790396", -1), new c(Constants.HW_INTELLIEGNT_PACKAGE, "1E3EEE2A88A6DF75FB4AF56ADC8373BB818F3CB90A4935C7821582B8CEBB694C", -1), new c(Constants.HW_INTELLIEGNT_PACKAGE, "603AC6A57E2023E00C9C93BB539CA653DF3003EBA4E92EA1904BA4AAA5D938F0", -1), new c("com.huawei.hiai", "1E3EEE2A88A6DF75FB4AF56ADC8373BB818F3CB90A4935C7821582B8CEBB694C", -1), new c("com.huawei.health", "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", -1), new c("com.huawei.health", "B2881B2EE3D4EFA03342AE07DAFC0466B63EDE959EC2E2F58C05A54266F45748", -1), new c("com.android.calendar", "2771BCFE40C0F6194CE52701DAAD4EFA0F8C380C844E83081E4592F0B13163E5", -1), new c("com.huawei.calendar", "5847832319B14EE04C8D55A5687819BD7F8C4558F697A236B47467A0B0658725", -1), new c("com.huawei.android.totemweather", "50787DFF857CCC7423352C5273275AD14B21F2B977CA3C124CF4684C1A9BC05C", -1), new c("com.huawei.android.totemweather", "25B72B2A1AD324D8417F48ABF566B9F81505E0243B3282FF04A6CCA5F57F618B", -1), new c("com.huawei.hidisk", "35EE6832A80D8552EEF38408A504F169D27564B87D3BD7A3B0779BEE68A57DF4", -1), new c("com.huawei.notepad", "E2FE75589A653A27F68245FD402A9E58779F6D32658FB48BDA7BA23CFBE16442", -1), new c("com.example.android.notepad", "2771BCFE40C0F6194CE52701DAAD4EFA0F8C380C844E83081E4592F0B13163E5", -1), new c("com.hihonor.hiai", "22DCB04CFAA28F382B613794EBA4441A8BCB1DBC8576776F1B1E6A457B00D449", -1), new c("com.huawei.mediacontroller", "303F68F797BA525184F09135D8FDF7E31F2151B64C493F6DEC84B3A1C6D5CAD0", -1), new c("com.vmall.client", "3508B0754E914E78C0000EA99EDEE5B8654DB5FE32527337F4487597D259C4CE", -1), new c("com.vmall.client", "0A612D7AAC96AC5D85F1C4A3768DD3CA7032681BAE68E0DD7009BD25AD1FA3A0", -1), new c("com.huawei.controlcenter", "603AC6A57E2023E00C9C93BB539CA653DF3003EBA4E92EA1904BA4AAA5D938F0", -1), new c("com.huawei.hms.dupdateengine", "6AA7E4A7995987A357D744C3A9E90FD0C990DA3295304CA985B8D7D2F7AD158D", -1), new c("com.huawei.phoneservice", "E2291158F313D5EC41ABD52212FB880FEE6FB9895CA2EBA32DAC8EDB8306CD88", -1), new c("com.huawei.mycenter", "BC8B6BE3AD05B9752F3171FA232D4B5EA93EDB88AF6E56100E8F56EA8BD94EF6", -1), new c("com.huawei.hwdiagnosis", "ED09886C1F363021F9954C22A00B82C8F2A7DB2F3C9675F3070560D505C22B3D", -1), new c("com.huawei.hilink.framework", "3517262215D8D3008CBF888750B6418EDC4D562AC33ED6874E0D73ABA667BC3C", -1), new c("com.huawei.smarthome", "E55977EA94EFF538744F93301A1F8B5015C8C575FE36B76B9CB4902791ADCCA4", -1), new c("com.huawei.maps.app", "DDAB0C1DB857B116AB1F7A41583F8B452A609CDBD9A2EF088F5592D6FADB5CFF", -1), new c("com.huawei.hmos.petalride", "250D102DED4DEF51C1EBDF8F38D69B5650D79FCEC7586BF383769AA036B5FFA9", -1), new c("com.huawei.study.hiresearch", "E1902FDBF8A84469F5D347FC7A329C21F5CE5D44C7A75F23A572034220176D0F", -1), new c("com.huawei.hicar", "A235C4485624AD5F72D5C5DC137DA3821203BA2B1B7DE63BC3F6EF8FA0489EF2", -1), new c("com.huawei.hicar", "E7B18F7C52D27FEFD8936D8078A3AD3A3B87BB02EC8F83578E9817500473E2D3", -1)};
    private static c[] b = {new c("com.huawei.health", "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", -1), new c("com.huawei.health", "B2881B2EE3D4EFA03342AE07DAFC0466B63EDE959EC2E2F58C05A54266F45748", -1)};
    private static c[] e = {new c("com.huawei.hms.dupdateengine", "6AA7E4A7995987A357D744C3A9E90FD0C990DA3295304CA985B8D7D2F7AD158D", -1), new c("com.huawei.hiwear.test", "67DFB7FD19CB4C508A8F25E313FE3B13A7300375758EBA8AE559B3B29884A849", -1)};
    private static c[] c = {new c("com.huawei.phoneservice", "E2291158F313D5EC41ABD52212FB880FEE6FB9895CA2EBA32DAC8EDB8306CD88", -1), new c("com.huawei.mycenter", "BC8B6BE3AD05B9752F3171FA232D4B5EA93EDB88AF6E56100E8F56EA8BD94EF6", -1), new c("com.huawei.hiwear.test", "67DFB7FD19CB4C508A8F25E313FE3B13A7300375758EBA8AE559B3B29884A849", -1)};

    public toh(AuthInfoRepository authInfoRepository, String str) {
        this.f17277a = authInfoRepository;
        this.f = str;
    }

    private boolean a(tol tolVar, WearEngineBiOperate wearEngineBiOperate, String str, tqo tqoVar, Permission permission) {
        if (tolVar == null) {
            tos.e("AuthorityCheck", "checkAllAuthorization callAppInfo is null");
            return false;
        }
        if (wearEngineBiOperate == null || tqoVar == null || permission == null) {
            tos.e("AuthorityCheck", "checkAllAuthorization biOperate or scope or permission is null");
            return false;
        }
        if (!TextUtils.isEmpty(tolVar.e()) && !TextUtils.isEmpty(str)) {
            return true;
        }
        tos.e("AuthorityCheck", "checkAllAuthorization callingPackageName or apiName is null");
        return false;
    }

    public int c(tol tolVar, WearEngineBiOperate wearEngineBiOperate, String str, tqo tqoVar, Permission permission) {
        if (!a(tolVar, wearEngineBiOperate, str, tqoVar, permission)) {
            return 5;
        }
        Context a2 = tot.a();
        String a3 = tri.a(a2);
        if (!trd.e(a3)) {
            tos.e("AuthorityCheck", str + " check health is not login in HuaWei Health!");
            wearEngineBiOperate.biApiCalling(a2, tolVar.e(), str, String.valueOf(3));
            return 3;
        }
        if (!tri.d(a2)) {
            tos.e("AuthorityCheck", str + " check user not authorized in HuaWei Health!");
            wearEngineBiOperate.biApiCalling(a2, tolVar.e(), str, String.valueOf(7));
            return 7;
        }
        if (c(tolVar.a(), tolVar.e())) {
            wearEngineBiOperate.biApiCalling(a2, tolVar.e(), str, String.valueOf(0));
            return 0;
        }
        int j = tri.j(a2, tolVar.e());
        e(tolVar.a(), tolVar.b(), String.valueOf(j));
        if (!this.i.checkScopeAvailability(tqoVar.d(), tolVar.b(), tolVar.a(), "wearEngine")) {
            tos.e("AuthorityCheck", str + " check scope not availability in cloud");
            wearEngineBiOperate.biApiCalling(a2, tolVar.e(), str, String.valueOf(8));
            return 8;
        }
        if (!trf.c(this.f17277a.getAuth(tolVar.a(), a3, j), permission)) {
            wearEngineBiOperate.biApiCalling(a2, tolVar.e(), str, String.valueOf(9));
            tos.e("AuthorityCheck", str + " check user not authorized in wear engine");
            return 9;
        }
        tos.b("AuthorityCheck", str + " check all authorized");
        return 0;
    }

    public void a(String str, String str2, tqo tqoVar, Permission permission) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            tos.e("AuthorityCheck", "checkAllAuthorization callingPackageName or apiName is null");
            return;
        }
        if (tqoVar == null) {
            tos.e("AuthorityCheck", "checkAllAuthorization scope is null");
            return;
        }
        if (permission == null) {
            tos.e("AuthorityCheck", "checkAllAuthorization permission is null");
            return;
        }
        Context a2 = tot.a();
        String a3 = tri.a(a2);
        a(str2, a3);
        a(str2, a2);
        if (c(tot.d(), str)) {
            return;
        }
        int j = tri.j(a2, str);
        e(Binder.getCallingUid(), Binder.getCallingPid(), String.valueOf(j));
        tos.a("AuthorityCheck", "check scope entry");
        if (!this.i.checkScopeAvailability(tqoVar.d(), Binder.getCallingPid(), Binder.getCallingUid(), "wearEngine")) {
            tos.e("AuthorityCheck", str2 + " check scope not availability in cloud");
            throw new IllegalStateException(String.valueOf(8));
        }
        tos.a("AuthorityCheck", "getAuth entry");
        if (!trf.c(this.f17277a.getAuth(Binder.getCallingUid(), a3, j), permission)) {
            tos.e("AuthorityCheck", str2 + " check user not authorized in wear engine");
            throw new IllegalStateException(String.valueOf(9));
        }
        tos.b("AuthorityCheck", str2 + " check all authorized");
    }

    private void a(String str, Context context) {
        if (tri.d(context)) {
            return;
        }
        tos.e("AuthorityCheck", str + " check user not authorized in HuaWei Health!");
        throw new IllegalStateException(String.valueOf(7));
    }

    private void a(String str, String str2) {
        if (trd.e(str2)) {
            return;
        }
        tos.e("AuthorityCheck", str + " check health is not login in HuaWei Health!");
        throw new IllegalStateException(String.valueOf(3));
    }

    public void d(int i, String str, String str2) {
        Context a2 = tot.a();
        a(str2, tri.a(a2));
        a(str2, a2);
        if (e(i, str)) {
            return;
        }
        tos.e("AuthorityCheck", str2 + " check user not authorized in wear engine");
        throw new IllegalStateException(String.valueOf(9));
    }

    public void c(String str, String str2, tqo tqoVar, Permission permission) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            tos.e("AuthorityCheck", str2 + " checkAllAuthorizationIncludeWhiteList callingPackageName or apiName is null");
            return;
        }
        if (tqoVar == null) {
            tos.e("AuthorityCheck", str2 + " checkAllAuthorizationIncludeWhiteList scope is null");
            return;
        }
        if (permission == null) {
            tos.e("AuthorityCheck", str2 + " checkAllAuthorizationIncludeWhiteList permission is null");
            return;
        }
        Context a2 = tot.a();
        String a3 = tri.a(a2);
        a(str2, a3);
        a(str2, a2);
        int j = tri.j(a2, str);
        e(Binder.getCallingUid(), Binder.getCallingPid(), String.valueOf(j));
        tos.a("AuthorityCheck", "check scope entry");
        if (!this.i.checkScopeAvailability(tqoVar.d(), Binder.getCallingPid(), Binder.getCallingUid(), "wearEngine")) {
            tos.e("AuthorityCheck", str2 + " check scope not availability in cloud");
            throw new IllegalStateException(String.valueOf(8));
        }
        if (!trf.c(this.f17277a.getAuth(Binder.getCallingUid(), a3, j), permission)) {
            tos.e("AuthorityCheck", str2 + " check user not authorized in wear engine");
            throw new IllegalStateException(String.valueOf(9));
        }
        tos.b("AuthorityCheck", str2 + " check all authorized");
    }

    public void a(String str) {
        a(str, tri.a(tot.a()));
    }

    public void c(String str) {
        a(str, tot.a());
    }

    public List<Sensor> d(String str, String str2, List<Sensor> list, tqp tqpVar) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            tov.d("AuthorityCheck", "getAuthorizedSensorList callingPackageName or apiName is null");
            return arrayList;
        }
        if (list == null) {
            tov.d("AuthorityCheck", "getAuthorizedSensorList sensorList is null");
            return arrayList;
        }
        if (tqpVar == null) {
            tov.d("AuthorityCheck", "getAuthorizedSensorList sensorCoreManager is null");
            return arrayList;
        }
        Context a2 = tot.a();
        if (c(tot.d(), str)) {
            return list;
        }
        int j = tri.j(a2, str);
        e(Binder.getCallingUid(), Binder.getCallingPid(), String.valueOf(j));
        tov.a("AuthorityCheck", "check scope entry");
        boolean checkScopeAvailability = this.i.checkScopeAvailability(tqo.e.d(), Binder.getCallingPid(), Binder.getCallingUid(), "wearEngine");
        boolean checkScopeAvailability2 = this.i.checkScopeAvailability(tqo.c.d(), Binder.getCallingPid(), Binder.getCallingUid(), "wearEngine");
        String a3 = tri.a(a2);
        if (!checkScopeAvailability && !checkScopeAvailability2) {
            tos.e("AuthorityCheck", str2 + " check scope not availability in cloud");
            throw new IllegalStateException(String.valueOf(8));
        }
        tov.a("AuthorityCheck", "getAuth entry");
        List<AuthInfo> auth = this.f17277a.getAuth(Binder.getCallingUid(), a3, j);
        boolean c2 = trf.c(auth, Permission.SENSOR);
        boolean c3 = trf.c(auth, Permission.MOTION_SENSOR);
        if (!c2 && !c3) {
            tos.e("AuthorityCheck", str2 + " check user not authorized in wear engine");
            throw new IllegalStateException(String.valueOf(9));
        }
        tos.b("AuthorityCheck", str2 + " check all authorized");
        if (c2 && checkScopeAvailability) {
            arrayList.addAll(tqpVar.c(list));
        }
        if (c3 && checkScopeAvailability2) {
            arrayList.addAll(tqpVar.e(list));
        }
        return arrayList;
    }

    private void e(int i, int i2, String str) {
        tos.b("AuthorityCheck", "setScopeManager uid:" + i + "; pid:" + i2 + "; appId:" + str);
        if (TextUtils.isEmpty(this.f)) {
            this.f = tri.e(tot.a());
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.f)) {
            tos.d("AuthorityCheck", "mGrsUrl =" + this.f);
        } else {
            ScopeServerRequest scopeServerRequest = new ScopeServerRequest(str);
            tos.b("AuthorityCheck", "setScopeManager url:" + scopeServerRequest.getUrl(this.f));
            this.i.setScopeServerUrl(str, scopeServerRequest.getUrl(this.f));
            this.i.setAppId(i2, i, str);
        }
    }

    public static boolean c(int i, String str) {
        return b(i, str, d);
    }

    public boolean e(int i, String str) {
        return b(i, str, e);
    }

    public static boolean d(String str) {
        return b(tot.d(), str, c);
    }

    private static boolean b(int i, String str, c[] cVarArr) {
        if (str == null) {
            tos.e("AuthorityCheck", "checkCaller, error in callingPackageName");
            return false;
        }
        tos.b("AuthorityCheck", "callerUid is " + i);
        int length = cVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            c cVar = cVarArr[i2];
            boolean equals = cVar.b().equals(str);
            if (equals && cVar.c() == i) {
                return true;
            }
            if (equals && cVar.d().equals(tri.c(str))) {
                cVar.a(i);
                return true;
            }
        }
        return false;
    }

    public boolean b(int i, String str) {
        if (!b(i, str, b)) {
            return false;
        }
        tos.a("AuthorityCheck", "checkInternalInvoke true");
        return true;
    }

    public static boolean b(String str, String str2) {
        return a(str, str2, b);
    }

    private static boolean a(String str, String str2, c[] cVarArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            tos.e("AuthorityCheck", "checkCaller, srcPkgName or srcFingerPrint is null");
            return false;
        }
        for (c cVar : cVarArr) {
            boolean equals = cVar.b().equals(str);
            boolean equals2 = cVar.d().equals(str2);
            if (equals && equals2) {
                tos.a("AuthorityCheck", "isSuperPermissionCaller true");
                return true;
            }
        }
        return false;
    }

    public static List<String> c() {
        ArrayList arrayList = new ArrayList();
        for (c cVar : d) {
            arrayList.add(cVar.b());
        }
        return arrayList;
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private String f17278a;
        private String b;
        private int d;

        c(String str, String str2, int i) {
            this.f17278a = str;
            this.b = str2;
            this.d = i;
        }

        public void a(int i) {
            this.d = i;
        }

        public String b() {
            return this.f17278a;
        }

        public String d() {
            return this.b;
        }

        public int c() {
            return this.d;
        }
    }
}

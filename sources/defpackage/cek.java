package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class cek implements GrsQueryCallback {
    private final WeakReference<HagridDeviceManagerFragment> c;

    public cek(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        this.c = new WeakReference<>(hagridDeviceManagerFragment);
    }

    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
    public void onCallBackSuccess(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("R_HelpCustomerGrsQueryCallback", "obtainTipsUrlDomain url is empty");
        } else {
            c(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.ref.WeakReference<com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment> r0 = r5.c
            java.lang.Object r0 = r0.get()
            com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment r0 = (com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment) r0
            java.lang.String r1 = "R_HelpCustomerGrsQueryCallback"
            if (r0 != 0) goto L16
            java.lang.String r6 = "fragment is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r6)
            return
        L16:
            android.app.Activity r2 = r0.getMainActivity()
            if (r2 != 0) goto L26
            java.lang.String r6 = "activity is null"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r6)
            return
        L26:
            java.lang.String r3 = "#/help?cid=11069"
            java.lang.String r6 = com.huawei.operation.utils.HelpCustomerOperate.getHelpCustomerUrl(r6, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r6)
            java.lang.String r6 = "&isDevice=4"
            r3.append(r6)
            boolean r6 = e()
            if (r6 == 0) goto L57
            ixj r6 = defpackage.ixj.b()
            boolean r6 = r6.a()
            if (r6 == 0) goto L57
            java.lang.String r6 = "&unitId="
            r3.append(r6)
            java.lang.String r6 = r0.getUniqueId()
            r3.append(r6)
            java.lang.String r6 = "&isEnhancementMode=false"
            r3.append(r6)
        L57:
            android.content.Intent r6 = new android.content.Intent     // Catch: android.content.ActivityNotFoundException -> L8f
            java.lang.Class<com.huawei.operation.activity.WebViewActivity> r0 = com.huawei.operation.activity.WebViewActivity.class
            r6.<init>(r2, r0)     // Catch: android.content.ActivityNotFoundException -> L8f
            boolean r0 = defpackage.njn.e(r2)     // Catch: android.content.ActivityNotFoundException -> L8f
            if (r0 != 0) goto L6d
            boolean r0 = com.huawei.operation.utils.Utils.isShowJapanCustomer(r2)     // Catch: android.content.ActivityNotFoundException -> L8f
            if (r0 == 0) goto L6b
            goto L6d
        L6b:
            r0 = 0
            goto L6e
        L6d:
            r0 = 1
        L6e:
            java.lang.String r4 = "url"
            java.lang.String r3 = r3.toString()     // Catch: android.content.ActivityNotFoundException -> L8f
            r6.putExtra(r4, r3)     // Catch: android.content.ActivityNotFoundException -> L8f
            if (r0 == 0) goto L7e
            r0 = 2130843618(0x7f0217e2, float:1.7292365E38)
            goto L81
        L7e:
            r0 = 2130842101(0x7f0211f5, float:1.7289288E38)
        L81:
            java.lang.String r3 = "title"
            java.lang.String r0 = r2.getString(r0)     // Catch: android.content.ActivityNotFoundException -> L8f
            r6.putExtra(r3, r0)     // Catch: android.content.ActivityNotFoundException -> L8f
            r2.startActivity(r6)     // Catch: android.content.ActivityNotFoundException -> L8f
            goto L99
        L8f:
            java.lang.String r6 = "startWebViewActivity ActivityNotFoundException"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r6)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cek.c(java.lang.String):void");
    }

    @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
    public void onCallBackFail(int i) {
        ReleaseLogUtil.d("R_HelpCustomerGrsQueryCallback", "obtainTipsUrlDomain onCallBackFail ", Integer.valueOf(i));
    }

    public static boolean e() {
        boolean bv = CommonUtil.bv();
        boolean i = Utils.i();
        boolean e = ixj.e();
        boolean o = Utils.o();
        boolean z = bv && !cpl.b();
        boolean z2 = (o || !z || ixj.c()) ? false : true;
        boolean z3 = o && z && e && i;
        ReleaseLogUtil.e("R_HelpCustomerGrsQueryCallback", "isOverSea: ", Boolean.valueOf(o), ", isSupport: ", Boolean.valueOf(z), ", isOpen: ", Boolean.valueOf(e), ", isAllowLogin: ", Boolean.valueOf(i));
        return z2 || z3;
    }
}

package defpackage;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class crw {
    private String b;
    private ArrayList<crx> d;

    public String c() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public ArrayList<crx> b() {
        return this.d;
    }

    public void d(ArrayList<crx> arrayList) {
        this.d = arrayList;
    }

    public void e(crx crxVar) {
        if (this.d == null) {
            this.d = new ArrayList<>(16);
        }
        this.d.add(crxVar);
    }

    public void e(String str, String str2, String str3) {
        if (this.d == null) {
            this.d = new ArrayList<>(16);
        }
        crx crxVar = new crx();
        crxVar.e(str);
        crxVar.d(str2);
        crxVar.b(str3);
        this.d.add(crxVar);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeviceUserInfo{mDeviceId ='");
        String str = this.b;
        String str2 = Constants.NULL;
        stringBuffer.append(str == null ? Constants.NULL : cpw.d(str)).append("'mUsers ='");
        ArrayList<crx> arrayList = this.d;
        if (arrayList != null) {
            str2 = arrayList.toString();
        }
        stringBuffer.append(str2).append("'}");
        return stringBuffer.toString();
    }

    public boolean d() {
        ArrayList<crx> arrayList;
        return TextUtils.isEmpty(this.b) || (arrayList = this.d) == null || arrayList.size() <= 0;
    }
}

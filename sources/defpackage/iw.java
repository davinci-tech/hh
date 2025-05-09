package defpackage;

import com.alipay.sdk.m.g0.a;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class iw {
    public File b;
    public a c;

    public final void b() {
        new Thread(new iy(this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        synchronized (this) {
            File file = this.b;
            if (file == null) {
                return;
            }
            if (file.exists() && this.b.isDirectory() && this.b.list().length != 0) {
                ArrayList arrayList = new ArrayList();
                for (String str : this.b.list()) {
                    arrayList.add(str);
                }
                Collections.sort(arrayList);
                String str2 = (String) arrayList.get(arrayList.size() - 1);
                int size = arrayList.size();
                if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                    if (arrayList.size() < 2) {
                        return;
                    }
                    str2 = (String) arrayList.get(arrayList.size() - 2);
                    size--;
                }
                if (!this.c.logCollect(c(mn.d(this.b.getAbsolutePath(), str2)))) {
                    size--;
                }
                for (int i = 0; i < size; i++) {
                    new File(this.b, (String) arrayList.get(i)).delete();
                }
            }
        }
    }

    public static String c(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put(VastAttribute.ERROR, str);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public iw(String str, a aVar) {
        this.b = null;
        this.c = null;
        this.b = new File(str);
        this.c = aVar;
    }
}

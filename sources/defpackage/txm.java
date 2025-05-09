package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes7.dex */
public class txm {
    public static void b(Context context, String str) throws twc {
        try {
            txj txjVar = new txj(str);
            twq.d(context, txjVar);
            UcsLib.ucsUpdateRootKey(twe.c(txjVar.d.d, 0), 32);
            twi.a("Local-C1-Version", txjVar.d.c, context);
        } catch (Throwable th) {
            String b = twi.b("ucscomponent.jws", (String) null, context);
            if (TextUtils.isEmpty(b) || !new File(b).exists()) {
                twb.a("KeyComponentLocalHandler", "tryToDeleteFile failed, file not exists.", new Object[0]);
            } else {
                try {
                    boolean delete = new File(b).delete();
                    twb.a("KeyComponentLocalHandler", delete ? "deleteFile success." : "deleteFile failed.", new Object[0]);
                    if (delete) {
                        twi.a("Last-Query-Time_ucscomponent_ucscomponent.jws", 0L, context);
                        twi.c("ucscomponent.jws", "", context);
                        twi.c("ETag_ucscomponent", "", context);
                        twi.c("Last-Modified_ucscomponent", "", context);
                    }
                } catch (Throwable th2) {
                    twb.e("KeyComponentLocalHandler", "deleteFile failed, {0}", th2.getMessage());
                }
            }
            StringBuilder e = twf.e("verify jws error, ");
            e.append(th.getMessage());
            String sb = e.toString();
            throw two.e("KeyComponentLocalHandler", sb, new Object[0], 1012L, sb);
        }
    }

    public static void b(Context context, twp twpVar) throws twc {
        String b = twi.b("ucscomponent.jws", (String) null, context);
        if (b == null || !new File(b).exists()) {
            throw new twc(1009L, "Init component from local failed, file error");
        }
        twb.a("KeyComponentLocalHandler", "Start init data =  component through local file", new Object[0]);
        try {
            FileInputStream fileInputStream = new FileInputStream(b);
            try {
                b(context, twh.c(fileInputStream, "UTF-8"));
                fileInputStream.close();
            } finally {
            }
        } catch (IOException e) {
            StringBuilder e2 = twf.e("Init data failed, msg = ");
            e2.append(e.getMessage());
            String sb = e2.toString();
            throw two.e("KeyComponentLocalHandler", sb, new Object[0], 1009L, sb);
        }
    }
}

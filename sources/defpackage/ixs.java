package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: classes5.dex */
public class ixs {
    private String c;

    public ixs(String str) {
        this.c = str;
    }

    public String d() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(BaseApplication.getContext().getResources().getAssets().open(this.c), "utf-8");
            try {
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    String readLine = bufferedReader.readLine();
                    int i = 0;
                    while (readLine != null) {
                        if (readLine.length() < 1024 && i < 1024) {
                            stringBuffer.append(readLine);
                        }
                        readLine = bufferedReader.readLine();
                        i++;
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    return stringBuffer.toString();
                } finally {
                }
            } finally {
            }
        } catch (FileNotFoundException unused) {
            LogUtil.b("ReadKey", "publicKey file does not exist");
            return null;
        } catch (IOException unused2) {
            LogUtil.b("ReadKey", "read the publicKey file catch IOException");
            return null;
        }
    }
}

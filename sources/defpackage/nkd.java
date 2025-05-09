package defpackage;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class nkd {
    private String d = "";
    private String e = "";
    private ConcurrentHashMap<String, String> b = new ConcurrentHashMap<>();

    public void b(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return;
                }
                String[] split = readLine.split("[ ]+");
                String trim = split[0].trim();
                if (trim.equals("vertex_source_file")) {
                    this.d = split[1];
                } else if (trim.equals("fragment_source_file")) {
                    this.e = split[1];
                } else if (trim.equals("attribute") || trim.equals("uniform")) {
                    this.b.put(split[2], trim);
                }
            }
        } catch (Exception e) {
            njw.c("MaterialParser", njw.b() + " load error e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<String, String> b() {
        return this.b;
    }

    public String e() {
        return this.d;
    }

    public String a() {
        return this.e;
    }

    public void d() {
        ConcurrentHashMap<String, String> concurrentHashMap = this.b;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            this.b = null;
        }
    }
}

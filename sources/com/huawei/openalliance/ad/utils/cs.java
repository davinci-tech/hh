package com.huawei.openalliance.ad.utils;

import com.huawei.openalliance.ad.ho;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class cs {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7667a = "cs";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v7 */
    public static Serializable b(String str) {
        Throwable th;
        cq cqVar;
        String str2;
        String str3;
        Closeable closeable;
        Closeable closeable2;
        ?? b = cz.b(str);
        ?? r1 = 0;
        r1 = null;
        r1 = 0;
        Serializable serializable = null;
        try {
            if (b != 0) {
                return null;
            }
            try {
                b = new ByteArrayInputStream(an.a(str));
                try {
                    cqVar = new cq(b);
                    try {
                        Object readObject = cqVar.readObject();
                        closeable2 = b;
                        if (readObject instanceof Serializable) {
                            serializable = (Serializable) readObject;
                            closeable2 = b;
                        }
                    } catch (UnsupportedEncodingException unused) {
                        str2 = f7667a;
                        str3 = "fail to get Serializable UnsupportedEncodingException";
                        closeable = b;
                        ho.d(str2, str3);
                        closeable2 = closeable;
                        cy.a((Closeable) cqVar);
                        cy.a(closeable2);
                        return serializable;
                    } catch (IOException unused2) {
                        str2 = f7667a;
                        str3 = "fail to get Serializable IOException";
                        closeable = b;
                        ho.d(str2, str3);
                        closeable2 = closeable;
                        cy.a((Closeable) cqVar);
                        cy.a(closeable2);
                        return serializable;
                    } catch (ClassNotFoundException unused3) {
                        str2 = f7667a;
                        str3 = "fail to get Serializable ClassNotFoundException";
                        closeable = b;
                        ho.d(str2, str3);
                        closeable2 = closeable;
                        cy.a((Closeable) cqVar);
                        cy.a(closeable2);
                        return serializable;
                    }
                } catch (UnsupportedEncodingException unused4) {
                    cqVar = null;
                } catch (IOException unused5) {
                    cqVar = null;
                } catch (ClassNotFoundException unused6) {
                    cqVar = null;
                } catch (Throwable th2) {
                    th = th2;
                    cy.a((Closeable) r1);
                    cy.a((Closeable) b);
                    throw th;
                }
            } catch (UnsupportedEncodingException unused7) {
                cqVar = null;
                b = 0;
            } catch (IOException unused8) {
                cqVar = null;
                b = 0;
            } catch (ClassNotFoundException unused9) {
                cqVar = null;
                b = 0;
            } catch (Throwable th3) {
                th = th3;
                b = 0;
            }
            cy.a((Closeable) cqVar);
            cy.a(closeable2);
            return serializable;
        } catch (Throwable th4) {
            r1 = str;
            th = th4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.Serializable, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.io.Closeable, java.io.ObjectOutputStream] */
    public static boolean a(Serializable serializable, String str) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        FileOutputStream fileOutputStream3;
        String str2;
        String str3;
        FileOutputStream fileOutputStream4 = null;
        try {
            try {
                File file = new File(str);
                if (!file.getParentFile().exists() && !ae.g(file.getParentFile())) {
                    ho.c(f7667a, "writeObject, mkdir failed");
                }
                FileOutputStream fileOutputStream5 = new FileOutputStream(str);
                try {
                    ?? objectOutputStream = new ObjectOutputStream(fileOutputStream5);
                    try {
                        objectOutputStream.writeObject(serializable);
                        cy.a(fileOutputStream5);
                        cy.a((Closeable) objectOutputStream);
                        return true;
                    } catch (FileNotFoundException unused) {
                        fileOutputStream4 = objectOutputStream;
                        fileOutputStream3 = fileOutputStream4;
                        fileOutputStream4 = fileOutputStream5;
                        str2 = f7667a;
                        str3 = "write file FileNotFoundException";
                        serializable = fileOutputStream3;
                        ho.c(str2, str3);
                        cy.a(fileOutputStream4);
                        cy.a((Closeable) serializable);
                        return false;
                    } catch (IOException unused2) {
                        fileOutputStream4 = objectOutputStream;
                        fileOutputStream2 = fileOutputStream4;
                        fileOutputStream4 = fileOutputStream5;
                        str2 = f7667a;
                        str3 = "write file IOException";
                        serializable = fileOutputStream2;
                        ho.c(str2, str3);
                        cy.a(fileOutputStream4);
                        cy.a((Closeable) serializable);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream4 = objectOutputStream;
                        fileOutputStream = fileOutputStream4;
                        fileOutputStream4 = fileOutputStream5;
                        cy.a(fileOutputStream4);
                        cy.a(fileOutputStream);
                        throw th;
                    }
                } catch (FileNotFoundException unused3) {
                } catch (IOException unused4) {
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                fileOutputStream = serializable;
                th = th3;
            }
        } catch (FileNotFoundException unused5) {
            fileOutputStream3 = null;
        } catch (IOException unused6) {
            fileOutputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    public static String a(Serializable serializable) {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        if (serializable == null) {
            return "";
        }
        ObjectOutputStream objectOutputStream2 = null;
        byte[] bArr = null;
        objectOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream;
                objectOutputStream2 = objectOutputStream;
            }
        } catch (IOException unused) {
            byteArrayOutputStream = null;
            objectOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(serializable);
                objectOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
            } catch (IOException unused2) {
                ho.d(f7667a, "fail to get sequence");
                cy.a(objectOutputStream);
                cy.a(byteArrayOutputStream);
                return an.a(bArr);
            }
        } catch (IOException unused3) {
            objectOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            cy.a(objectOutputStream2);
            cy.a(byteArrayOutputStream);
            throw th;
        }
        cy.a(objectOutputStream);
        cy.a(byteArrayOutputStream);
        return an.a(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0047: MOVE (r4 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:35:0x0047 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.Closeable] */
    public static Serializable a(String str) {
        ?? r1;
        cq cqVar;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        String str2;
        String str3;
        String str4;
        FileInputStream fileInputStream4 = null;
        r0 = null;
        r0 = null;
        Serializable serializable = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                fileInputStream3 = fileInputStream2;
                str2 = str;
                th = th;
            }
            try {
                cqVar = new cq(fileInputStream);
                try {
                    Object readObject = cqVar.readObject();
                    if (readObject instanceof Serializable) {
                        serializable = (Serializable) readObject;
                    }
                } catch (FileNotFoundException unused) {
                    ho.d(f7667a, "read file FileNotFoundException");
                    cy.a((Closeable) fileInputStream);
                    cy.a((Closeable) cqVar);
                    return serializable;
                } catch (IOException unused2) {
                    str3 = f7667a;
                    str4 = "read file IOException";
                    ho.c(str3, str4);
                    cy.a((Closeable) fileInputStream);
                    cy.a((Closeable) cqVar);
                    return serializable;
                } catch (ClassNotFoundException unused3) {
                    str3 = f7667a;
                    str4 = "read file ClassNotFoundException";
                    ho.c(str3, str4);
                    cy.a((Closeable) fileInputStream);
                    cy.a((Closeable) cqVar);
                    return serializable;
                }
            } catch (FileNotFoundException unused4) {
                cqVar = null;
            } catch (IOException unused5) {
                cqVar = null;
            } catch (ClassNotFoundException unused6) {
                cqVar = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream3 = fileInputStream;
                str2 = null;
                fileInputStream4 = fileInputStream3;
                r1 = str2;
                cy.a((Closeable) fileInputStream4);
                cy.a((Closeable) r1);
                throw th;
            }
        } catch (FileNotFoundException unused7) {
            cqVar = null;
            fileInputStream = null;
        } catch (IOException unused8) {
            cqVar = null;
            fileInputStream = null;
        } catch (ClassNotFoundException unused9) {
            cqVar = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            r1 = 0;
            cy.a((Closeable) fileInputStream4);
            cy.a((Closeable) r1);
            throw th;
        }
        cy.a((Closeable) fileInputStream);
        cy.a((Closeable) cqVar);
        return serializable;
    }
}

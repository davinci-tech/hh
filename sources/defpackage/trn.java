package defpackage;

import android.os.ParcelFileDescriptor;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import java.io.File;
import java.io.FileNotFoundException;

/* loaded from: classes9.dex */
public class trn {
    public static MessageParcel b(tpy tpyVar) {
        File b;
        if (tpyVar == null) {
            return null;
        }
        MessageParcel messageParcel = new MessageParcel();
        int d = tpyVar.d();
        messageParcel.setType(d);
        if (d == 1) {
            messageParcel.setData(tpyVar.a());
        } else if (d == 2 && (b = tpyVar.b()) != null) {
            try {
                messageParcel.setParcelFileDescriptor(ParcelFileDescriptor.open(b, 268435456));
                messageParcel.setFileName(b.getName());
                messageParcel.setDescription(tpyVar.c());
                messageParcel.setFileSha256(trl.a(b));
            } catch (FileNotFoundException unused) {
                tov.d("ConvertParcelUtil", "convertToMessageParcel FileNotFoundException");
                throw new tnx(10);
            }
        }
        return messageParcel;
    }

    public static MessageParcelExtra c(tpy tpyVar, MessageParcel messageParcel) {
        if (tpyVar == null) {
            tov.d("ConvertParcelUtil", "convertToMessageParcelExtra message is null");
            return null;
        }
        if (messageParcel == null) {
            tov.d("ConvertParcelUtil", "convertToMessageParcelExtra messageParcel is null");
            return null;
        }
        MessageParcelExtra messageParcelExtra = new MessageParcelExtra();
        messageParcelExtra.setType(messageParcel.getType());
        messageParcelExtra.setData(messageParcel.getData());
        messageParcelExtra.setParcelFileDescriptor(messageParcel.getParcelFileDescriptor());
        messageParcelExtra.setFileName(messageParcel.getFileName());
        messageParcelExtra.setDescription(messageParcel.getDescription());
        messageParcelExtra.setFileSha256(messageParcel.getFileSha256());
        messageParcelExtra.setExtendJson(tsb.c(tpyVar));
        return messageParcelExtra;
    }

    public static FileIdentificationParcel c(tpv tpvVar) {
        if (tpvVar == null) {
            tov.d("ConvertParcelUtil", "convertToFileIdentificationParcel fileIdentification is null");
            return null;
        }
        FileIdentificationParcel fileIdentificationParcel = new FileIdentificationParcel();
        File b = tpvVar.b();
        if (b != null) {
            fileIdentificationParcel.setFileName(b.getName());
        }
        fileIdentificationParcel.setDescription(tpvVar.a());
        return fileIdentificationParcel;
    }
}

package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.text.TextUtils;
import androidx.print.PrintHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.muw;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class muw {
    private static fdu d;

    muw(fdu fduVar) {
        d = fduVar;
    }

    public void cpQ_(Activity activity) {
        fdu fduVar = d;
        if (fduVar == null || activity == null) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "createPdfDocument shareContent/activity is null");
            return;
        }
        if (koq.b(fduVar.n())) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "createPdfDocument SaveFileImages is null");
            return;
        }
        Intent intent = new Intent("android.intent.action.CREATE_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("application/pdf");
        if (!TextUtils.isEmpty(d.t())) {
            LogUtil.a("Share_PrintOrSavePdfInteractor", "createPdfDocument shareTitleContent = ", d.t());
            intent.putExtra("android.intent.extra.TITLE", d.t());
        }
        intent.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("content://com.android.externalstorage.documents/document"));
        activity.startActivityForResult(intent, 5);
    }

    public static void cpP_(Context context, Intent intent) {
        final OutputStream outputStream;
        if (intent == null) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "writeBitmapToPdf intent is null.");
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "writeBitmapToPdf uri is null");
            return;
        }
        LogUtil.a("Share_PrintOrSavePdfInteractor", "writeBitmapToPdf File uri is: ", data.toString());
        try {
            outputStream = context.getContentResolver().openOutputStream(data);
        } catch (IOException unused) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "writeBitmapToPdf Failed to generate the PDF outputStream");
            outputStream = null;
        }
        final PdfDocument cpN_ = cpN_();
        ThreadPoolManager.d().execute(new Runnable() { // from class: muy
            @Override // java.lang.Runnable
            public final void run() {
                muw.cpO_(cpN_, outputStream);
            }
        });
    }

    static /* synthetic */ void cpO_(PdfDocument pdfDocument, OutputStream outputStream) {
        try {
            try {
                pdfDocument.writeTo(outputStream);
                pdfDocument.close();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException unused) {
                        LogUtil.b("Share_PrintOrSavePdfInteractor", "writePdf Pdf create error");
                    }
                }
            } catch (IOException unused2) {
                LogUtil.b("Share_PrintOrSavePdfInteractor", "writePdf PDF CREATE FAIL");
                pdfDocument.close();
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b("Share_PrintOrSavePdfInteractor", "writePdf Pdf create error");
                    }
                }
            }
        } catch (Throwable th) {
            pdfDocument.close();
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("Share_PrintOrSavePdfInteractor", "writePdf Pdf create error");
                }
            }
            throw th;
        }
    }

    private static PdfDocument cpN_() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = d.n().iterator();
        while (it.hasNext()) {
            arrayList.add(nrf.cHn_(it.next()));
        }
        int widthMils = (PrintAttributes.MediaSize.ISO_A4.getWidthMils() * 72) / 1000;
        float width = widthMils / ((Bitmap) arrayList.get(0)).getWidth();
        int height = (int) (((Bitmap) arrayList.get(0)).getHeight() * width);
        Matrix matrix = new Matrix();
        matrix.postScale(width, width);
        Paint paint = new Paint(1);
        PdfDocument pdfDocument = new PdfDocument();
        for (int i = 0; i < arrayList.size(); i++) {
            PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(widthMils, height, i).create());
            startPage.getCanvas().drawBitmap((Bitmap) arrayList.get(i), matrix, paint);
            pdfDocument.finishPage(startPage);
        }
        return pdfDocument;
    }

    public void e(Context context) {
        fdu fduVar = d;
        if (fduVar == null || context == null) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "printBitmap fail:mShareContent/mContext is null");
            return;
        }
        if (koq.b(fduVar.n())) {
            LogUtil.b("Share_PrintOrSavePdfInteractor", "printBitmap SaveFileImages is null");
            return;
        }
        String str = context.getString(R.string.IDS_app_name_health) + " Physical examination.pdf";
        if (!TextUtils.isEmpty(d.t())) {
            str = d.t() + ".pdf";
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = d.n().iterator();
        while (it.hasNext()) {
            arrayList.add(nrf.cHn_(it.next()));
        }
        if (arrayList.size() == 1) {
            PrintHelper printHelper = new PrintHelper(context);
            printHelper.setScaleMode(1);
            printHelper.printBitmap(str, (Bitmap) arrayList.get(0));
            return;
        }
        ((PrintManager) context.getSystemService("print")).print(str, new e(context, arrayList, str), null);
    }

    static class e extends PrintDocumentAdapter {

        /* renamed from: a, reason: collision with root package name */
        private int f15188a;
        private PdfDocument b;
        private ArrayList<Bitmap> c;
        private Context d;
        private String e;
        private int f;
        private int i;

        e(Context context, ArrayList<Bitmap> arrayList, String str) {
            this.d = context;
            this.c = arrayList;
            this.e = str;
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, final PrintAttributes printAttributes2, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mvd
                @Override // java.lang.Runnable
                public final void run() {
                    muw.e.this.cpT_(printAttributes2, cancellationSignal, layoutResultCallback);
                }
            });
        }

        /* synthetic */ void cpT_(PrintAttributes printAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback) {
            this.b = new PrintedPdfDocument(this.d, printAttributes);
            this.f15188a = (PrintAttributes.MediaSize.ISO_A4.getHeightMils() * 72) / 1000;
            this.f = (PrintAttributes.MediaSize.ISO_A4.getWidthMils() * 72) / 1000;
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
                LogUtil.h("Share_PrintOrSavePdfInteractor", "SharePrintDocumentAdapter onLayout cancellationSignal.isCanceled()");
                return;
            }
            int size = this.c.size();
            this.i = size;
            if (size <= 1) {
                layoutResultCallback.onLayoutFailed("onLayout Page count is zero.");
            }
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.e).setContentType(0).setPageCount(this.i).build(), true);
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(final PageRange[] pageRangeArr, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mve
                @Override // java.lang.Runnable
                public final void run() {
                    muw.e.this.cpU_(pageRangeArr, cancellationSignal, writeResultCallback, parcelFileDescriptor);
                }
            });
        }

        /* synthetic */ void cpU_(PageRange[] pageRangeArr, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback, ParcelFileDescriptor parcelFileDescriptor) {
            for (int i = 0; i < this.i; i++) {
                try {
                    if (cpS_(pageRangeArr, i)) {
                        PdfDocument.Page startPage = this.b.startPage(new PdfDocument.PageInfo.Builder(this.f, this.f15188a, i).create());
                        if (cancellationSignal.isCanceled()) {
                            writeResultCallback.onWriteCancelled();
                            this.b.close();
                            this.b = null;
                            LogUtil.h("Share_PrintOrSavePdfInteractor", "SharePrintDocumentAdapter onWrite cancellationSignal.isCanceled()");
                            return;
                        }
                        cpR_(startPage, i);
                        this.b.finishPage(startPage);
                    }
                } catch (Throwable th) {
                    this.b.close();
                    this.b = null;
                    throw th;
                }
            }
            try {
                this.b.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                this.b.close();
                this.b = null;
                writeResultCallback.onWriteFinished(pageRangeArr);
            } catch (IOException e) {
                writeResultCallback.onWriteFailed(e.toString());
                LogUtil.b("Share_PrintOrSavePdfInteractor", "SharePrintDocumentAdapter onWrite e: ", e.getMessage());
                this.b.close();
                this.b = null;
            }
        }

        private boolean cpS_(PageRange[] pageRangeArr, int i) {
            for (PageRange pageRange : pageRangeArr) {
                if (i >= pageRange.getStart() && i <= pageRange.getEnd()) {
                    return true;
                }
            }
            return false;
        }

        private void cpR_(PdfDocument.Page page, int i) {
            Canvas canvas = page.getCanvas();
            if (koq.b(this.c)) {
                LogUtil.b("Share_PrintOrSavePdfInteractor", "SharePrintDocumentAdapter drawPage bitmaps is null");
                return;
            }
            Paint paint = new Paint();
            Bitmap bitmap = this.c.get(i);
            if (bitmap == null) {
                LogUtil.b("Share_PrintOrSavePdfInteractor", "SharePrintDocumentAdapter drawPage bitmap is null, pageNumber: ", Integer.valueOf(i));
                return;
            }
            float width = this.f / bitmap.getWidth();
            Matrix matrix = new Matrix();
            matrix.postScale(width, width);
            canvas.drawBitmap(bitmap, matrix, paint);
        }
    }
}

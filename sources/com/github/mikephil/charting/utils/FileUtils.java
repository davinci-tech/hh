package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FileUtils {
    private static final String LOG = "MPChart-FileUtils";

    public static List<Entry> loadEntriesFromFile(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("#");
                if (split.length <= 2) {
                    arrayList.add(new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));
                } else {
                    int length = split.length - 1;
                    float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry(Integer.parseInt(split[split.length - 1]), fArr));
                }
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }
        return arrayList;
    }

    public static List<Entry> loadEntriesFromAssets(AssetManager assetManager, String str) {
        BufferedReader bufferedReader;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
            }
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
        try {
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                String[] split = readLine.split("#");
                if (split.length <= 2) {
                    arrayList.add(new Entry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
                } else {
                    int length = split.length - 1;
                    float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry(Integer.parseInt(split[split.length - 1]), fArr));
                }
            }
            bufferedReader.close();
        } catch (IOException e3) {
            e = e3;
            bufferedReader2 = bufferedReader;
            Log.e(LOG, e.toString());
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    Log.e(LOG, e4.toString());
                }
            }
            throw th;
        }
        return arrayList;
    }

    public static void saveToSdCard(List<Entry> list, String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(LOG, e.toString());
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (Entry entry : list) {
                bufferedWriter.append((CharSequence) (entry.getY() + "#" + entry.getX()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
    }

    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager assetManager, String str) {
        BufferedReader bufferedReader;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
            }
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
        try {
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                String[] split = readLine.split("#");
                arrayList.add(new BarEntry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
            }
            bufferedReader.close();
        } catch (IOException e3) {
            e = e3;
            bufferedReader2 = bufferedReader;
            Log.e(LOG, e.toString());
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    Log.e(LOG, e4.toString());
                }
            }
            throw th;
        }
        return arrayList;
    }
}

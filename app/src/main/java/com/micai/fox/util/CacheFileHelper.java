package com.micai.fox.util;


import android.content.Context;
import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class CacheFileHelper {

	public static void saveCacheFile(Context context,String filename, String cache) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter writer = null;
        try {
            fileOutputStream =context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(fileOutputStream,"UTF-8");
			writer.write(cache);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
					writer = null;
                    fileOutputStream.close();
                    fileOutputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getCacheFile(Context context,String filename) {

        String result = "";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream =context.openFileInput(filename);
            int lenght = fileInputStream.available();
            byte[] buffer = new byte[lenght];
            fileInputStream.read(buffer);
            result = EncodingUtils.getString(buffer, "UTF-8");
		} catch(Exception e) {
           e.printStackTrace();
		} finally{
			if(fileInputStream != null)
				try {
                    fileInputStream.close();
                    fileInputStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
        return result;
	}

    public static boolean clearCacheFile(Context context,String filename) {
        try {
            return context.deleteFile(filename);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
	 * 将文件按照修改时间正序排列
	 * @param files
	 * @return
	 */
	public static TreeMap<Long, File> formatFileList(File[] files) {
	    TreeMap<Long,File> tm = new TreeMap<Long,File>();
	    for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.exists() && file.isFile()) {
                long time = file.lastModified();
                tm.put(time, file);
            }
        }
	    return tm;
	}
}

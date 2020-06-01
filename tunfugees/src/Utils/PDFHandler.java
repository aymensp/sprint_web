/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Display;

/**
 *
 * @author arafet
 */
public class PDFHandler {

    private final static String URL = "http://api.html2pdfrocket.com/pdf";
    private final static String APIKEY = "efa3724e-5dd9-476e-8890-4fecd91a12f3";

    /**
     * Stores given HTML String or URL to Storage with given filename
     *
     * @param value URL or HTML add quote if you have spaces. use single quotes
     * instead of double
     * @param filename
     */
    public void getFile(String value, String filename) {
        // Validate parameters
        if (value == null || value.length() < 1) {
            return;
        }
        if (filename == null || filename.length() < 1) {
            return;
        }
        //Encode
        value = Util.encodeUrl(value);

        //String fullPathToFile = FileSystemStorage.getInstance().getAppHomePath() + filename;
       // FileSystemStorage fs = FileSystemStorage.getInstance();
        //String fn = fs.getAppHomePath() + "pdf-sample.pdf";
        //Util.downloadUrlToFileSystemInBackground(URL + "?apikey=" + APIKEY + "&value=" + value, fullPathToFile);
        //Util.downloadUrlToFile(URL + "?apikey=" + APIKEY + "&value=" + value, fn, true);
                //Display.getInstance().execute(fn);
        //String fullPathToFile = FileSystemStorage.getInstance().getAppHomePath() + "skan.pdf";
        FileSystemStorage fs = FileSystemStorage.getInstance();
        String fn = fs.getAppHomePath() + filename; //C:\Users\raiiz\.cn1 path to saved file
        System.out.println(fn);
        //Util.downloadUrlToFileSystemInBackground(URL + "?apikey=" + APIKEY + "&value=" + value, fullPathToFile);
        Util.downloadUrlToFile("http://api.html2pdfrocket.com/pdf?apikey=a1e41309-bf0b-4684-b53f-853a93036917&value=" + value,fn, true);
       Display.getInstance().execute(fn);
       

    }

}

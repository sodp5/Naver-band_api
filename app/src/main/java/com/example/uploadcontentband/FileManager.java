package com.example.uploadcontentband;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileManager {
    private String fileName;
    private String folderName;
	private File fileDir;

	public FileManager(String fileName) {
		this.fileName = fileName;
		folderName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/openBand";
		fileDir = new File(folderName);

		if (!fileDir.exists())
		    fileDir.mkdir();
	}
	
	public String readFile() {
		StringBuilder result = new StringBuilder();
        BufferedReader bReader = null;
        FileReader reader = null;
        
        try {
            String s;
            reader = new FileReader(fileDir + "/"+ fileName + ".html");
            bReader = new BufferedReader(reader);
            
            while((s = bReader.readLine()) != null) {
                result.append(s).append('\n');
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bReader != null) bReader.close();
                if(reader != null) reader.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
	
	public void writeFile(String fileContent) {
        BufferedWriter bWriter = null;
        String[] content = fileContent.split("\n");

        try {
            String fileName1 = fileDir + "/"+ fileName + ".html";
            bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName1, true), "EUC-KR"));
            
            for(String c : content) {
            	bWriter.write(c);
            	bWriter.newLine();
            }
            bWriter.flush();

            Log.d("myLog", "DONE");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bWriter != null) bWriter.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}

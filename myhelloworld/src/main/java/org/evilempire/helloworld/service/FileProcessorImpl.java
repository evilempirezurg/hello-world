package org.evilempire.helloworld.service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/* Generic CSV file processor */
/* Just practicing */

public class FileProcessorImpl implements FileProcessor {

    private String DELIM;
    
    private static final Logger logger = Logger.getLogger(FileProcessor.class.getName());

    public FileProcessorImpl() {
        this.DELIM = ",";
    }

    public FileProcessorImpl(String delim) {
        this.DELIM = delim;
    }

    // Not so good implementation
    private List<Map<String, Object>> processor(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        List<String> stringRows = new LinkedList<String>();

        String strLine;
        while ((strLine = bufferedReader.readLine()) != null) {
            stringRows.add(strLine);
        }

        //get Header Row
        String headerRow = stringRows.get(0);

        //get Data Rows
        List<String> dataRows = stringRows.subList(1, stringRows.size());
        String[] keyset = headerRow.split(DELIM);

        //Create the Map and prepare the Result
        List<Map<String, Object>> resultMapList = null;
        if (dataRows.size() > 0) {
            resultMapList = new ArrayList<Map<String, Object>>();
        }

        for (String data : dataRows) {

            Map<String, Object> resultMap = new HashMap<String, Object>();
            String[] values = data.split(DELIM);

            int i = 0;
            for (String key : keyset) {
                if (i < values.length) {
                    resultMap.put(key, values[i]);
                } else {
                    resultMap.put(key, "");
                }
                i++;
            }

            resultMapList.add(resultMap);
        }

        bufferedReader.close();

        return resultMapList;
    }

    //Single Pass everything
    public List<Map<String, Object>> process(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        List<Map<String, Object>> resultMapList = null;
        String strLine;
        boolean isHeaderSet = false;
        String[] keySet = null;

        int lines = 0;
        while ((strLine = bufferedReader.readLine()) != null) {

            lines++;

            if (!isHeaderSet) {
                keySet = strLine.split(DELIM);

                if (keySet.length < 1) {
                    logger.severe("INVALID HEADERS");
                    break;
                }

                isHeaderSet = true;

                logger.info("HEADER IS NOW SET: " + Arrays.toString(keySet));

                continue;
            }

            if (strLine.length() < 1) {
                logger.warning("SKIPPING EMPTY LINE");
                continue;
            }

            if (resultMapList == null) {
                resultMapList = new ArrayList<Map<String, Object>>();
            }

            String[] valueSet = strLine.split(DELIM);

            int i = 0;
            Map<String, Object> resultMap = new HashMap<String, Object>();
            for (String key : keySet) {
                if (i < valueSet.length) {
                    resultMap.put(key, valueSet[i]);
                } else
                    resultMap.put(key, "");
                i++;
            }

            resultMapList.add(resultMap);
        }

        bufferedReader.close();

        logger.info(String.format("There are %d number of row(s) processed.", lines));

        return resultMapList;
    }
}

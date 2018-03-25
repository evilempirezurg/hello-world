package org.evilempire.helloworld.application;

import org.evilempire.helloworld.domain.Entity;
import org.evilempire.helloworld.service.FileProcessor;
import org.evilempire.helloworld.service.FileProcessorImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Hello world!
 */
class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws IOException {

        //Centralise the file retrieval mechanism, this will be a micro application

        final FileProcessor myFileProcessor = new FileProcessorImpl();
        List<Map<String, Object>> resultMapList = myFileProcessor.process("etc/sample.csv");

        for (Map<String, Object> resultMap : resultMapList) {
            logger.info("Displaying Map Result: " + resultMap.toString());

            try {

                //Object Mapping
                Entity object = new Entity();

                object.setEntityId((Integer.parseInt(resultMap.get("Id").toString())));
                object.setEntityName(resultMap.get("Name").toString());
                object.setEntityTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultMap.get("Date").toString()));
                object.setEntityDouble(Double.parseDouble(resultMap.get("Double").toString()));

                logger.info("Object Result -> " + object.toString());

            } catch (Exception e) {

                String id = resultMap.get("Id") != null ? resultMap.get("Id").toString() : "#";
                logger.severe("ROW " + id + " failed with " + e);
            }

        }

        //A generic Map -> Object implementation (?) with rules
        //Map KEY_FROM_MAP_1 -> Object.Field1
        //Map KEY_FROM_MAP_2 -> Object.Field2 | MethodCall(resultMap)

    }
}

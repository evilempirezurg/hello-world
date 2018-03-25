package org.evilempire.helloworld.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileProcessor {
    List<Map<String, Object>> process(String fileName) throws IOException;
}

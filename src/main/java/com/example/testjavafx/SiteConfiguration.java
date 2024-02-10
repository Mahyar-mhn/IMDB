package com.example.testjavafx;

import java.util.HashMap;
import java.util.Map;

public class SiteConfiguration {
    Map<String, String> options;

    public SiteConfiguration() {
        this.options = new HashMap<>();
    }

    public void setOption(String optionName, String optionValue) {
        options.put(optionName, optionValue);
    }
}


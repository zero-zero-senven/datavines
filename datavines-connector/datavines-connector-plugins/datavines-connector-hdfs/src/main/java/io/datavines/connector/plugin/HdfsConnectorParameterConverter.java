/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.datavines.connector.plugin;

import io.datavines.connector.api.ConnectorParameterConverter;

import java.util.HashMap;
import java.util.Map;

import static io.datavines.common.ConfigConstants.*;
import static io.datavines.common.ConfigConstants.URL;

/**
 * @author chenyuanbo
 * @DATE: 2024/2/5
 */
public class HdfsConnectorParameterConverter implements ConnectorParameterConverter {
    @Override
    public Map<String, Object> converter(Map<String, Object> parameter) {
        Map<String,Object> config = new HashMap<>();
        config.put(SRC_CONNECTOR_TYPE, parameter.get(SRC_CONNECTOR_TYPE));
        config.put(TABLE,parameter.get(TABLE));
        config.put(USER,parameter.get(USER));
        config.put(PASSWORD, parameter.get(PASSWORD));
        config.put(DATABASE, parameter.get(DATABASE));
        config.put(CATALOG, parameter.get(CATALOG));
        config.put(SCHEMA, parameter.get(SCHEMA));
        config.put(PROPERTIES, parameter.get(PROPERTIES));
        config.put(HDFS_FILE, parameter.get(HDFS_FILE));
        config.put(URL, parameter.get(URL) == null ? getUrl(parameter) : parameter.get(URL));
        return config;
    }

    private String getUrl(Map<String, Object> parameter){
        return String.format("hdfs://%s:%s", parameter.get(HOST), parameter.get(PORT));
    }
}

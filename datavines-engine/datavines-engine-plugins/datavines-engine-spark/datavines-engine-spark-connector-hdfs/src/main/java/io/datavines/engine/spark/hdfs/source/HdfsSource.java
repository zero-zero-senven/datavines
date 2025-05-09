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
package io.datavines.engine.spark.hdfs.source;

import io.datavines.common.config.CheckResult;
import io.datavines.common.config.Config;
import io.datavines.common.exception.DataVinesException;
import io.datavines.common.utils.StringUtils;
import io.datavines.engine.api.env.RuntimeEnvironment;
import io.datavines.engine.spark.api.SparkRuntimeEnvironment;
import io.datavines.engine.spark.api.batch.SparkBatchSource;
import org.apache.spark.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.datavines.common.ConfigConstants.*;

/**
 * @author chenyuanbo
 */
public class HdfsSource implements SparkBatchSource {

    private final Logger logger = LoggerFactory.getLogger(HdfsSource.class);

    private Config config = new Config();

    @Override
    public void prepare(RuntimeEnvironment env) throws Exception {

    }

    @Override
    public void setConfig(Config config) {
        if(config != null) {
            this.config = config;
        }
    }

    @Override
    public Config getConfig() {
        return this.config;
    }

    @Override
    public CheckResult checkConfig() {
        List<String> requiredOptions = Collections.singletonList(URL);

        List<String> nonExistsOptions = new ArrayList<>();
        requiredOptions.forEach(x->{
            if(!config.has(x)){
                nonExistsOptions.add(x);
            }
        });

        if (!nonExistsOptions.isEmpty()) {
            return new CheckResult(
                    false,
                    "please specify " + nonExistsOptions.stream().map(option ->
                            "[" + option + "]").collect(Collectors.joining(",")) + " as non-empty string");
        } else {
            return new CheckResult(true, "");
        }
    }

    @Override
    public Dataset<Row> getData(SparkRuntimeEnvironment env) {
        SparkSession sparkSession = env.sparkSession();
        String hdfsFile = config.getString(HDFS_FILE);
        if(StringUtils.isEmpty(hdfsFile)){
            throw new DataVinesException("hdfs file not found, please set hdfs_file.");
        }
        logger.info("hdfs file : " + hdfsFile);
        logger.info("source config : " + config.entrySet().stream().map(i -> i.getKey() + ":" + i.getValue()).collect(Collectors.joining(";")));
        return sparkSession
                .read()
                .format(config.getString(DRIVER))
                .load(hdfsFile)
                ;
    }

}

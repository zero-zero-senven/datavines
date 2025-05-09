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
package io.datavines.runner;

import org.junit.Test;

import java.util.Base64;

public class JobExecuteBootstrapTest {

    @Test
    public void testMain() {
        // Setup
        // Run the test
//        String parameterJson = "{\n" +
//                "    \"name\":\"shell_123_1708503798625\",\n" +
//                "    \"id\":\"1231231231\",\n" +
//                "    \"tenantCode\":\"chenyuanbo\",\n" +
//                "    \"executePlatformType\":\"client\",\n" +
//                "    \"executePlatformParameter\":{\n" +
//                "\n" +
//                "    },\n" +
//                "    \"engineType\":\"spark\",\n" +
//                "    \"engineParameter\":{\n" +
//                "        \"programType\":\"JAVA\",\n" +
//                "        \"deployMode\":\"cluster\",\n" +
//                "        \"driverCores\":1,\n" +
//                "        \"driverMemory\":\"512M\",\n" +
//                "        \"numExecutors\":\"1\",\n" +
//                "        \"executorMemory\":\"2G\",\n" +
//                "        \"executorCores\":\"1\",\n" +
//                "        \"others\":\"--conf spark.yarn.maxAppAttempts=1\"\n" +
//                "    },\n" +
//                "    \"parameter\":{\n" +
//                "        \"connectorParameter\":{\n" +
//                "            \"type\":\"hdfs\",\n" +
//                "            \"parameters\":{\n" +
//                "                \"port\":\"8020\",\n" +
//                "                \"password\":\"123\",\n" +
//                "                \"host\":\"192.168.1.236\",\n" +
//                "                \"user\":\"hdfs\"\n" +
//                "            }\n" +
//                "        },\n" +
//                "        \"metricParameterList\":[\n" +
//                "            {\n" +
//                "                \"metricType\":\"column_length\",\n" +
//                "                \"metricParameter\":{\n" +
//                "                    \"database\":\"default\",\n" +
//                "                    \"table\":\"default\",\n" +
//                "                    \"column\":\"name\",\n" +
//                "                    \"comparator\":\"&gt;\",\n" +
//                "                    \"length\":\"1\"\n" +
//                "                },\n" +
//                "                \"expectedType\":\"none\",\n" +
//                "                \"resultFormula\":\"count\",\n" +
//                "                \"operator\":\"gte\",\n" +
//                "                \"threshold\":0\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"errorDataStorageType\":\"\",\n" +
//                "    \"errorDataStorageParameter\":{\n" +
//                "\n" +
//                "    },\n" +
//                "    \"validateResultDataStorageType\":\"mysql\",\n" +
//                "    \"validateResultDataStorageParameter\":{\n" +
//                "        \"src_connector_type\":\"mysql\",\n" +
//                "        \"password\":\"Szlx123$\",\n" +
//                "        \"driver\":\"com.mysql.cj.jdbc.Driver\",\n" +
//                "        \"user\":\"root\",\n" +
//                "        \"url\":\"jdbc:mysql://192.168.1.201:3307/datavines?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai\"\n" +
//                "    },\n" +
//                "    \"retryTimes\":0,\n" +
//                "    \"retryInterval\":1000,\n" +
//                "    \"timeout\":3600,\n" +
//                "    \"timeoutStrategy\":\"WARN\",\n" +
//                "    \"env\":\"SPARK_HOME2=/Users/chenyuanbo/Desktop/datalink/soft/spark/spark-3.4.2-bin-hadoop3\",\n" +
//                "    \"languageEn\":false\n" +
//                "}";
//        JobExecuteBootstrap.main(new String[]{parameterJson});

        String p1 = "eyJuYW1lIjoiZGF0YS1xdWFsaXR5LWNoZW4tMDAxXzE3MDkxOTk4NDAzMzMiLCJqb2JEZWZpbml0aW9uSWQiOjE3NjMxMzUxMDQ3NTE3OTIxMjksImV4ZWN1dGVQbGF0Zm9ybVR5cGUiOiJjbGllbnQiLCJleGVjdXRlUGxhdGZvcm1QYXJhbWV0ZXIiOnt9LCJlbmdpbmVUeXBlIjoic3BhcmsiLCJlbmdpbmVQYXJhbWV0ZXIiOnsicHJvZ3JhbVR5cGUiOiJKQVZBIiwiZGVwbG95TW9kZSI6ImNsaWVudCIsImRyaXZlckNvcmVzIjoxLCJkcml2ZXJNZW1vcnkiOiI1MTJNIiwibnVtRXhlY3V0b3JzIjoiMSIsImV4ZWN1dG9yTWVtb3J5IjoiMkciLCJleGVjdXRvckNvcmVzIjoiMSIsIm90aGVycyI6Ii0tY29uZiBzcGFyay55YXJuLm1heEFwcEF0dGVtcHRzPTEifSwicGFyYW1ldGVyIjp7ImNvbm5lY3RvclBhcmFtZXRlciI6eyJ0eXBlIjoiaGRmcyIsInBhcmFtZXRlcnMiOnsiZGF0YWJhc2UiOiJkZWZhdWx0IiwicGFzc3dvcmQiOiIxMjMiLCJwb3J0IjoiODAyMCIsImhvc3QiOiIxOTIuMTY4LjEuMjM2IiwidXNlciI6ImhkZnMiLCJwcm9wZXJ0aWVzIjoiL2hhZG9vcC9oZGZzL2RhdGEvc3BhcmtfdGFibGUifX0sIm1ldHJpY1BhcmFtZXRlckxpc3QiOlt7Im1ldHJpY1R5cGUiOiJjb2x1bW5fbGVuZ3RoIiwibWV0cmljUGFyYW1ldGVyIjp7ImRhdGFiYXNlIjoiZGVmYXVsdCIsInRhYmxlIjoiZGVmYXVsdCIsImNvbHVtbiI6Im5hbWUiLCJjb21wYXJhdG9yIjoiPiIsImxlbmd0aCI6IjEifSwiZXhwZWN0ZWRUeXBlIjoibm9uZSIsInJlc3VsdEZvcm11bGEiOiJjb3VudCIsIm9wZXJhdG9yIjoiZ3RlIiwidGhyZXNob2xkIjowfV19LCJlcnJvckRhdGFTdG9yYWdlVHlwZSI6IiIsImVycm9yRGF0YVN0b3JhZ2VQYXJhbWV0ZXIiOnt9LCJ2YWxpZGF0ZVJlc3VsdERhdGFTdG9yYWdlUGFyYW1ldGVyIjp7InNyY19jb25uZWN0b3JfdHlwZSI6Im15c3FsIiwicGFzc3dvcmQiOiIiLCJkcml2ZXIiOiIiLCJ1c2VyIjoiIiwidXJsIjoiIn0sInJldHJ5VGltZXMiOjAsInJldHJ5SW50ZXJ2YWwiOjEwMDAsInRpbWVvdXQiOjM2MDAsInRpbWVvdXRTdHJhdGVneSI6IldBUk4iLCJ0ZW5hbnRDb2RlIjoibGZ5bCIsImVudiI6ImVudiIsImxhbmd1YWdlRW4iOmZhbHNlfQ==";
        String p2 = "123";
        JobExecuteBootstrap.main(new String[]{p1, p2});

        // Verify the results
    }

    public static void main(String[] args) {
        String tre = "eyJuYW1lIjoic3BhcmtfdGFibGUtMDAzXzE3MDk4NzA0OTQxMzAiLCJlbnYiOnsiZW5naW5lIjoic3BhcmsiLCJ0eXBlIjoiYmF0Y2gifSwic291cmNlcyI6W3sicGx1Z2luIjoiaGRmcyIsInR5cGUiOiJzb3VyY2UiLCJjb25maWciOnsicGFzc3dvcmQiOiJkZWZhdWx0IiwiZGF0YWJhc2UiOiJkZWZhdWx0IiwiZHJpdmVyIjoib3JnLmFwYWNoZS5odWRpIiwidXNlciI6ImhkZnMiLCJvdXRwdXRfdGFibGUiOiJgZGVmYXVsdF9zcGFya190YWJsZWAiLCJ0YWJsZSI6ImRlZmF1bHQuc3BhcmtfdGFibGUiLCJwcm9wZXJ0aWVzIjoiL2hhZG9vcC9oZGZzL2RhdGEvc3BhcmtfdGFibGUiLCJ1cmwiOiJoZGZzOi8vMTkyLjE2OC4xLjIzNjo4MDIwIn19XSwidHJhbnNmb3JtcyI6W3sicGx1Z2luIjoic3FsIiwidHlwZSI6ImludmFsaWRhdGVfaXRlbXMiLCJjb25maWciOnsiaW52YWxpZGF0ZV9pdGVtc190YWJsZSI6ImludmFsaWRhdGVfaXRlbXNfNDQwMzI2YWYiLCJpbmRleCI6MCwib3V0cHV0X3RhYmxlIjoiaW52YWxpZGF0ZV9pdGVtc180NDAzMjZhZiIsInNxbCI6InNlbGVjdCAqIGZyb20gYGRlZmF1bHRfc3BhcmtfdGFibGVgIHdoZXJlICBsZW5ndGgoYGRyaXZlcmApIDwgMTAifX0seyJwbHVnaW4iOiJzcWwiLCJ0eXBlIjoiYWN0dWFsX3ZhbHVlIiwiY29uZmlnIjp7ImludmFsaWRhdGVfaXRlbXNfdGFibGUiOiJpbnZhbGlkYXRlX2l0ZW1zXzQ0MDMyNmFmIiwiaW5kZXgiOjAsIm91dHB1dF90YWJsZSI6ImludmFsaWRhdGVfY291bnRfNDQwMzI2YWYiLCJzcWwiOiJzZWxlY3QgY291bnQoMSkgYXMgYWN0dWFsX3ZhbHVlXzQ0MDMyNmFmIGZyb20gaW52YWxpZGF0ZV9pdGVtc180NDAzMjZhZiJ9fV0sInNpbmtzIjpbeyJwbHVnaW4iOiJteXNxbCIsImNvbmZpZyI6eyJqb2JfZXhlY3V0aW9uX2lkIjo5LCJ1cmwiOiJqZGJjOm15c3FsOi8vMTkyLjE2OC4xLjIxNjozMzA5L2RhdGFsaW5rX3F1YWxpdHk/dXNlVW5pY29kZT10cnVlJmNoYXJhY3RlckVuY29kaW5nPVVURi04JnVzZVNTTD1mYWxzZSZzZXJ2ZXJUaW1lem9uZT1Bc2lhL1NoYW5naGFpIiwic3FsIjoic2VsZWN0IDE3NjU2NDc1MDYzMzY2NTc0MTAgYXMgam9iX2RlZmluaXRpb25faWQsIDkgYXMgam9iX2V4ZWN1dGlvbl9pZCwgJ2NvbHVtbl9sZW5ndGgnIGFzIG1ldHJpY19uYW1lLCAnT0VNWU1YL1pEUERXTkE2RFFSTVVGWllMNlRLUjhBQVE2NENFV09XWVBDMD0nIGFzIHVuaXF1ZV9jb2RlLCBhY3R1YWxfdmFsdWVfNDQwMzI2YWYgYXMgYWN0dWFsX3ZhbHVlLCAnMjAyNC0wMy0wOCAwNDowODoyNScgYXMgZGF0YV90aW1lLCAnMjAyNC0wMy0wOCAwNDowODoyNScgYXMgY3JlYXRlX3RpbWUsICcyMDI0LTAzLTA4IDA0OjA4OjI1JyBhcyB1cGRhdGVfdGltZSBmcm9tIGludmFsaWRhdGVfY291bnRfNDQwMzI2YWYiLCJzcmNfY29ubmVjdG9yX3R5cGUiOiJteXNxbCIsImludmFsaWRhdGVfaXRlbXNfdGFibGUiOiJpbnZhbGlkYXRlX2l0ZW1zXzQ0MDMyNmFmIiwicGFzc3dvcmQiOiIzaHVvU09KVEdWT0dlRmxIIiwiam9iX2RlZmluaXRpb25faWQiOjE3NjU2NDc1MDYzMzY2NTc0MTAsImRyaXZlciI6ImNvbS5teXNxbC5jai5qZGJjLkRyaXZlciIsIm1ldHJpY191bmlxdWVfa2V5IjoiNDQwMzI2YWYiLCJleHBlY3RlZF92YWx1ZSI6IjAiLCJ1c2VyIjoicm9vdCIsIm91dHB1dF90YWJsZSI6ImR2X2FjdHVhbF92YWx1ZXMiLCJ0YWJsZSI6ImR2X2FjdHVhbF92YWx1ZXMifX0seyJwbHVnaW4iOiJteXNxbCIsImNvbmZpZyI6eyJqb2JfZXhlY3V0aW9uX2lkIjo5LCJ1cmwiOiJqZGJjOm15c3FsOi8vMTkyLjE2OC4xLjIxNjozMzA5L2RhdGFsaW5rX3F1YWxpdHk/dXNlVW5pY29kZT10cnVlJmNoYXJhY3RlckVuY29kaW5nPVVURi04JnVzZVNTTD1mYWxzZSZzZXJ2ZXJUaW1lem9uZT1Bc2lhL1NoYW5naGFpIiwic3FsIjoic2VsZWN0IDE3NjU2NDc1MDYzMzY2NTc0MTAgYXMgam9iX2RlZmluaXRpb25faWQsIDkgYXMgam9iX2V4ZWN1dGlvbl9pZCwgJzQ0MDMyNmFmJyBhcyBtZXRyaWNfdW5pcXVlX2tleSwgJ3NpbmdsZV90YWJsZScgYXMgbWV0cmljX3R5cGUsICdjb2x1bW5fbGVuZ3RoJyBhcyBtZXRyaWNfbmFtZSwgJ2NvbXBsZXRlbmVzcycgYXMgbWV0cmljX2RpbWVuc2lvbiwgJ2RlZmF1bHQnIGFzIGRhdGFiYXNlX25hbWUsICdzcGFya190YWJsZScgYXMgdGFibGVfbmFtZSwgJ2RyaXZlcicgYXMgY29sdW1uX25hbWUsIGFjdHVhbF92YWx1ZV80NDAzMjZhZiBhcyBhY3R1YWxfdmFsdWUsIDAgYXMgZXhwZWN0ZWRfdmFsdWUsICdub25lJyBhcyBleHBlY3RlZF90eXBlLCAnY291bnQnIGFzIHJlc3VsdF9mb3JtdWxhLCAnZ3RlJyBhcyBvcGVyYXRvciwgMC4wIGFzIHRocmVzaG9sZCwgJzIwMjQtMDMtMDggMDQ6MDg6MjUnIGFzIGNyZWF0ZV90aW1lLCAnMjAyNC0wMy0wOCAwNDowODoyNScgYXMgdXBkYXRlX3RpbWUgZnJvbSBpbnZhbGlkYXRlX2NvdW50XzQ0MDMyNmFmICIsInNyY19jb25uZWN0b3JfdHlwZSI6Im15c3FsIiwiaW52YWxpZGF0ZV9pdGVtc190YWJsZSI6ImludmFsaWRhdGVfaXRlbXNfNDQwMzI2YWYiLCJwYXNzd29yZCI6IjNodW9TT0pUR1ZPR2VGbEgiLCJqb2JfZGVmaW5pdGlvbl9pZCI6MTc2NTY0NzUwNjMzNjY1NzQxMCwiZHJpdmVyIjoiY29tLm15c3FsLmNqLmpkYmMuRHJpdmVyIiwibWV0cmljX3VuaXF1ZV9rZXkiOiI0NDAzMjZhZiIsImV4cGVjdGVkX3ZhbHVlIjoiMCIsInVzZXIiOiJyb290Iiwib3V0cHV0X3RhYmxlIjoiZHZfam9iX2V4ZWN1dGlvbl9yZXN1bHQiLCJ0YWJsZSI6ImR2X2pvYl9leGVjdXRpb25fcmVzdWx0In19XX0=";
        System.out.println(new String(Base64.getDecoder().decode(tre.getBytes())));
    }
}

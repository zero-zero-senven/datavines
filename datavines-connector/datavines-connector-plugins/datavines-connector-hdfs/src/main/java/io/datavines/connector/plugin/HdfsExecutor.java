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

import io.datavines.common.datasource.jdbc.entity.ColumnInfo;
import io.datavines.common.datasource.jdbc.utils.SqlUtils;
import io.datavines.common.entity.ListWithQueryColumn;
import io.datavines.common.entity.QueryColumn;
import io.datavines.common.param.ConnectorResponse;
import io.datavines.common.param.ExecuteRequestParam;
import io.datavines.connector.api.Executor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyuanbo
 * @DATE: 2024/2/5
 */
public class HdfsExecutor implements Executor {
    @Override
    public ConnectorResponse queryForPage(ExecuteRequestParam param) throws Exception {
        ListWithQueryColumn result = new ListWithQueryColumn();

        List<QueryColumn> columnInfos = new ArrayList<>();
        QueryColumn columnInfo = new QueryColumn();
        columnInfo.setName("id");
        columnInfo.setType("BIGINT");
        columnInfo.setComment("主键");
        columnInfos.add(columnInfo);
        QueryColumn columnInfo2 = new QueryColumn();
        columnInfo2.setName("name");
        columnInfo2.setType("String");
        columnInfo2.setComment("名字");
        columnInfos.add(columnInfo2);
        result.setColumns(columnInfos);


        result.setResultList(new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("name", "1");
                put("id", "1");
            }});
            add(new HashMap<String, Object>() {{
                put("name", "2");
                put("id", "2");
            }});
        }});
        result.setPageSize(10);
        result.setPageNumber(1);
        result.setTotalCount(2);
        return ConnectorResponse.builder().result(result).build();
    }

    @Override
    public ConnectorResponse queryForList(ExecuteRequestParam param) throws Exception {
        return queryForPage(param);
    }

    @Override
    public ConnectorResponse queryForOne(ExecuteRequestParam param) throws Exception {
        return queryForPage(param);
    }

}

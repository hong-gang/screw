
# ${title!'數據字典'}

<#if (database)??>
    **數據庫名：** ${database!''}

</#if>
<#if (version)??>
    **文檔版本：** ${version!''}

</#if>
<#if (description)??>
    **文檔描述：** ${description!''}
</#if>

|   模式名  | 表名                  | 說明       |
| :---: | :---: |
<#list tables>
    <#items as t>
        | ${t.tableSchem!''} | [${t.tableName!''}](#${t.tableName!''}) | ${t.remarks!''} |
    </#items>
</#list>
<#list tables><#items as t>

**表名：** <a id="${t.tableName!''}">${t.tableName!''}</a>

**說明：** ${t.remarks!''}

<#list t.columns>
| 序號 | 字段名 | 數據類型 | 允許空值 | 主鍵 | 默認值 | 說明 |
| :---: | :---: | :---:| :---: | :---: | :---: | :---: | :---: |
<#items as c>
|  ${c?index+1}   | ${c.columnName!''} |   <#if (c.columnType?index_of("CHAR") > -1) > ${c.columnType}(${c.columnSize}) <#elseif (c.columnType?index_of("DECIMAL") > -1) >  ${c.columnType}(${c.columnSize},${c.decimalDigits}) <#else>  ${c.columnType} </#if>  |    ${c.nullable!''}     |  ${c.primaryKey!''}   |   ${c.columnDef!''}    | ${c.remarks!''}  |
</#items>
</#list></#items>
</#list>
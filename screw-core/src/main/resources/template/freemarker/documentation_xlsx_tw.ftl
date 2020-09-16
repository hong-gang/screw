<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<worksheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main" xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships" xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006" mc:Ignorable="x14ac xr xr2 xr3" xmlns:x14ac="http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac" xmlns:xr="http://schemas.microsoft.com/office/spreadsheetml/2014/revision" xmlns:xr2="http://schemas.microsoft.com/office/spreadsheetml/2015/revision2" xmlns:xr3="http://schemas.microsoft.com/office/spreadsheetml/2016/revision3" xr:uid="{00000000-0001-0000-0100-000000000000}">
    <dimension ref="A1:J17"/>
    <sheetViews>
        <sheetView tabSelected="1" zoomScale="70" zoomScaleNormal="70" workbookViewId="0">
            <selection activeCell="J1" sqref="A1:J1"/>
        </sheetView>
    </sheetViews>
    <sheetFormatPr defaultRowHeight="15" customHeight="1" x14ac:dyDescent="0.3"/>
    <cols>
        <col min="1" max="1" width="8.33203125" bestFit="1" customWidth="1"/>
        <col min="2" max="2" width="36.4140625" customWidth="1"/>
        <col min="3" max="3" width="46" customWidth="1"/>
        <col min="4" max="4" width="34.6640625" customWidth="1"/>
        <col min="5" max="5" width="27" customWidth="1"/>
        <col min="6" max="6" width="51.4140625" customWidth="1"/>
        <col min="7" max="7" width="16.6640625" customWidth="1"/>
        <col min="8" max="8" width="11" customWidth="1"/>
    </cols>
    <sheetData>
        <row r="1" spans="1:10" ht="15" customHeight="1" x14ac:dyDescent="0.3">
            <c r="A1" s="4">
                <v>TABLE_SCHEMA</v>
            </c>
            <c r="B1" s="4">
                <v>TABLE_NAME</v>
            </c>
            <c r="C1" s="4">
                <v>TABLE_CNAME</v>
            </c>
            <c r="D1" s="4">
                <v>COLUMN_NAME</v>
            </c>
            <c r="E1" s="4">
                <v>COLUMN_CNAME</v>
            </c>
            <c r="F1" s="4">
                <v>REMARKS</v>
            </c>
            <c r="G1" s="4">
                <v>COLUMN_TYPE</v>
            </c>
            <c r="H1" s="4">
                <v>IS_PRIMARY</v>
            </c>
            <c r="I1" s="4">
                <v>IS_NUALL_ABLE</v>
            </c>
            <c r="J1" s="4">
                <v>COLNO</v>
            </c>
        </row>
        <#assign rowIndex=1>
        <#list tables>
            <#items as t>
                <#list t.columns>
                    <#items as c>
                        <#assign rowIndex=rowIndex + 1>
                        <row r="${rowIndex}" spans="1:10" ht="15" customHeight="1" x14ac:dyDescent="0.3">

                            <c r="A${rowIndex}" s="4">
                                <v>${t.tableSchem!''}</v>
                            </c>
                            <c r="B${rowIndex}" s="4">
                                <v>${t.tableName!''}</v>
                            </c>
                            <c r="C${rowIndex}" s="4">
                                <v>${t.remarks!''}</v>
                            </c>
                            <c r="D${rowIndex}" s="4">
                                <v>${c.columnName!''}</v>
                            </c>
                            <c r="E${rowIndex}" s="4">
                                <v>${c.remarks!''}</v>
                            </c>
                            <c r="F${rowIndex}" s="4">
                                <v>${c.desc!''}</v>
                            </c>
                            <c r="G${rowIndex}" s="4">
                                <v><#if (c.columnType?index_of("CHAR") > -1) >${c.columnType}(${c.columnSize})<#elseif (c.columnType?index_of("DECIMAL") > -1) >${c.columnType}(${c.columnSize},${c.decimalDigits})<#else>${c.columnType}</#if></v>
                            </c>
                            <c r="H${rowIndex}" s="4">
                                <v>${c.primaryKey!''}</v>
                            </c>
                            <c r="I${rowIndex}" s="4">
                                <v>${c.nullable!''}</v>
                            </c>
                            <c r="J${rowIndex} " s="4">
                                <v>${c?index+1}</v>
                            </c>
                        </row>
                    </#items>
                </#list>
            </#items>
        </#list>
    </sheetData>
   <#--
    <sortState ref="A2:K17" xmlns:xlrd2="http://schemas.microsoft.com/office/spreadsheetml/2017/richdata2">
        <sortCondition ref="A2:A17"/>
        <sortCondition ref="B2:B17"/>
        <sortCondition ref="J2:J17"/>
    </sortState>
    <mergeCells count="1">
        <mergeCell ref="F2:F5"/>
    </mergeCells>
    -->
    <phoneticPr fontId="1" type="noConversion"/>
    <pageMargins left="0.7" right="0.7" top="0.75" bottom="0.75" header="0.3" footer="0.3"/>
    <pageSetup paperSize="9" orientation="portrait" r:id="rId1"/>
</worksheet>

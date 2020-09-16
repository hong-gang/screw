<#--

    screw-core - 簡潔好用的數據庫表結構文檔生成工具
    Copyright © 2020 SanLi (qinggang.zuo@gmail.com)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

-->
﻿<html lang="zh"><head><title>${title!'數據字典'}</title><style type='text/css'>body {            padding-bottom: 50px        }        body, td {            font-family: verdana, fantasy;            font-size: 12px;            line-height: 150%        }        table {            width: 100%;            background-color: #ccc;            margin: 5px 0        }        td {            background-color: #fff;            padding: 3px 3px 3px 10px        }        thead td {            text-align: center;            font-weight: bold;            background-color: #eee        }        a:link, a:visited, a:active {            color: #015fb6;            text-decoration: none        }        a:hover {            color: #e33e06        }</style></head><body style='text-align:center;'><div style='width:800px; margin:20px auto; text-align:left;'><a name='index'></a><h2 style='text-align:center; line-height:50px;'>${title!'數據庫設計文檔'}</h2><div><b>數據庫名：${database!''}</b><br><#if (version)??><b>文檔版本：${version!''}</b><br></#if><#if (description)??><b>文檔描述：${description!''}</b><br></#if></div><table cellspacing='1'><thead><tr><td style='width:40px; '>序號</td><td>模式名</td><td>表名</td><td>說明</td></tr></thead><#list tables><#items as t><tr><td style='text-align:center;'>${t?index+1}</td><td>${t.tableSchem!''}</td><td><a href='#${t.tableName}'>${t.tableName}</a></td><td>${t.remarks!''}</td></tr></#items></#list></table><#list tables><#items as t><a name='${t.tableName}'></a><div style='margin-top:30px;'><a href='#index'                                         style='float:right; margin-top:6px;'>返回目錄</a><b>表名：${t.tableName}</b></div><div>說明：${t.remarks!''}</div><table cellspacing='1'><thead><tr><td style='width:40px; '>序號</td><td>字段名</td><td>數據類型</td><td>允許空值</td><td>主鍵</td><td>默認值</td><td>說明</td></tr></thead><#list t.columns><#items as c><tr><td style='text-align:center;'>${c?index+1}</td><td>${c.columnName!''}</td><td align='center'><#if (c.columnType?index_of("CHAR") > -1) > ${c.columnType}(${c.columnSize}) <#elseif (c.columnType?index_of("DECIMAL") > -1) >  ${c.columnType}(${c.columnSize},${c.decimalDigits}) <#else>  ${c.columnType} </#if></td><td align='center'>${c.nullable!''}</td><td align='center'>${c.primaryKey!''}</td><td align='center'>${c.columnDef!''}</td><td align='center'>${c.remarks!''}</td></tr></#items></#list></table></#items></#list></div><footer><#--機構信息--><#if (organization)??><div><a href="${organizationUrl!'#'}">${organization!''}</a></div></#if></footer></body></html>
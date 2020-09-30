/*
 * screw-core - 简洁好用的数据库表结构文档生成工具
 * Copyright © 2020 SanLi (qinggang.zuo@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cn.smallbun.screw.core.query.db2.model;

import cn.smallbun.screw.core.mapping.MappingField;
import cn.smallbun.screw.core.metadata.ForeignKey;
import lombok.Data;

/**
 * @author gavin
 * @desc
 * @date 2020-09-05
 */
@Data
public class DB2ForeignKeyModel implements ForeignKey {

    /**
     * 表名
     */
    @MappingField(value = "TABLE_NAME")
    private String tableName;
    /**
     * fk name
     */
    @MappingField(value = "FK_NAME")
    private String fkName;
    /**
     * 表模式
     */
    @MappingField(value = "REF_TABLE_NAME")
    private String refTableName;
    /**
     * 列名
     */
    @MappingField(value = "REF_COLUMN_NAME")
    private String refColumnName;

}

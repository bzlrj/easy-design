package com.y1ph.easy.design.data.mybatis.handler;

import com.y1ph.easy.design.common.BaseEnum;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举处理器
 *
 * @author WFT
 * @since 2022/1/4
 */
@RequiredArgsConstructor
public class EnumTypeHandler<E extends Enum<E> & BaseEnum<Integer>> extends BaseTypeHandler<E> {

    private final Class<E> clazz;

    @Override
    public void setNonNullParameter(PreparedStatement statement, int i, E parameter, JdbcType type) throws SQLException {
        statement.setInt(i, parameter.getValue());
    }

    @Override
    public E getNullableResult(ResultSet result, String columnName) throws SQLException {
        return valueOf(this.clazz, result.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet result, int index) throws SQLException {
        return valueOf(this.clazz, result.getInt(index));
    }

    @Override
    public E getNullableResult(CallableStatement statement, int index) throws SQLException {
        return valueOf(this.clazz, statement.getInt(index));
    }


    private static <E extends Enum<?> & BaseEnum<Integer>> E valueOf(Class<E> clazz, int value) {
        E[] constants = clazz.getEnumConstants();
        for (E e : constants) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }

}

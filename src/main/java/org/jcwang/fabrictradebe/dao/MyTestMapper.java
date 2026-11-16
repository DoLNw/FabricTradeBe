package org.jcwang.fabrictradebe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.jcwang.fabrictradebe.model.TestCustomer;

@Mapper
public interface MyTestMapper {
    @Select("""
            SELECT id, id_card AS idCard, customer_name AS customerName, phone, hobby,
                   update_time AS updateTime, create_time AS createTime
            FROM test_table
            ORDER BY id
            """)
    List<TestCustomer> findAll();

    @Select("""
            SELECT id, id_card AS idCard, customer_name AS customerName, phone, hobby,
                   update_time AS updateTime, create_time AS createTime
            FROM test_table
            WHERE id = #{id}
            """)
    TestCustomer findById(@Param("id") long id);

    @Insert("""
            INSERT INTO test_table (id_card, customer_name, phone, hobby)
            VALUES (#{idCard}, #{customerName}, #{phone}, #{hobby})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TestCustomer customer);

    @Update("""
            UPDATE test_table
            SET id_card = #{customer.idCard},
                customer_name = #{customer.customerName},
                phone = #{customer.phone},
                hobby = #{customer.hobby}
            WHERE id = #{id}
            """)
    int update(@Param("id") long id, @Param("customer") TestCustomer customer);

    @Delete("DELETE FROM test_table WHERE id = #{id}")
    int deleteById(@Param("id") long id);
}

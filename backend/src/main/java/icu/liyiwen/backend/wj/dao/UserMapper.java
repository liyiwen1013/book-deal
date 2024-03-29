package icu.liyiwen.backend.wj.dao;

import icu.liyiwen.backend.wj.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nanfang
 * @create 2020/02/05/14:45
 */

@Mapper
@Repository(value = "userMapper")
public interface UserMapper {
    //登录功能根据账号密码查询
    @Select("Select * from user where username=#{param1} and password=#{param2}")
    User findByUsernameAndPassword(String username, String password);

    //申请账号功能插入一个账号密码
    @Insert("Insert into user(username,password) values(#{param1},#{param2})")
    int insertIntoUser(String username,String password);

    //修改密码
    @Update("update user set password=#{param1} where uid=#{param2} and password=#{param3}")
    Boolean changePassword(String newPass,String uid,String password);

    @Select("Select * from user where username=#{param1}")
    User findByUserName(String username);

    @Select("Select * from user where uid=#{param1}")
    User findById(Integer id);

    @Select("Select count(*) from user where username=#{param1}")
    int countByUsername(String username);
}

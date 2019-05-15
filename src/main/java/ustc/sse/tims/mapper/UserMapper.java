package ustc.sse.tims.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ustc.sse.tims.bean.User;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.mapper
 * @date 2019/3/12-15:16
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Mapper
public interface UserMapper {

    @Select("select * from users where userName=#{username}")
    public User getUserByName(String username);

    @Insert("insert into users(username,password) values (#{user}.username,#{user}.password)")
    public void insertUser(User user);
}



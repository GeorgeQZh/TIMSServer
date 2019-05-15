package ustc.sse.tims.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ustc.sse.tims.bean.Device;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.mapper
 * @date 2019/5/14-12:40
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Mapper
public interface DHCPMapper {

    @Select("SELECT * FROM dhcp_fingerprint WHERE option55 =#{option55}")
    public Device getDevByOpt55(String option55);

    @Insert("INSERT INTO dhcp_fingerprint(id,option55,os,dev_name,version) " +
            "values(#{dev.id},#{dev.option55},#{dev.os_name},#{dev.dev_name},#{dev.version})")
    public void setDev(Device dev);

    @Delete("DELETE FROM dhcp_fringerprint WHERE option55= #{option55}")
    public void deletDevByOpt55(String option55);

}

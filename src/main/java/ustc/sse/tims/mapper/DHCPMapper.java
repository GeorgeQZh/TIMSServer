package ustc.sse.tims.mapper;

import org.apache.ibatis.annotations.*;
import ustc.sse.tims.bean.Device;
import ustc.sse.tims.bean.FingerPrint;

import java.util.ArrayList;

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

    //devices表操作
    @Select("SELECT * FROM devices WHERE id =#{id}")
    public Device getDevById(@Param("id") Integer id);

    @Select("SELECT * FROM devices")
    public ArrayList<Device> getDevs();

    @Insert("INSERT INTO devices(id,name,created_at,updated_at,parent_id," +
            "virtual_parent_id,child_devices_count,child_virtual_devices_count,can_be_more_precise)" +
            "values(#{dev.id},#{dev.name},#{dev.created_at},#{dev.updated_at},#{dev.parent_id}," +
            "#{dev.virtual_parent_id},#{dev.child_devices_count},#{dev.child_virtual_devices_count},#{dev.can_be_more_precise})")
    public void setDev(@Param("dev") Device dev);

    @Delete("DELETE FROM devices WHERE id = #{id}")
    public void deletDevById(@Param("id") Integer id);

    @Update("UPDATE devices SET name = #{dev.name}, created_at=#{dev.created_at},updated_at=#{dev.updated_at}," +
            "parent_id=#{dev.parent_id}, virtual_parent_id=#{dev.virtual_parent_id}, child_devices_count=#{dev.child_devices_count}," +
            " child_virtual_devices_count = #{dev.child_virtual_devices_count}, can_be_more_precise = #{dev.can_be_more_precise} WHERE id=#{dev.id}")
    public void updateDev(@Param("dev") Device dev);



    //fingerprints表操作
    @Select("SELECT * FROM fingerprints WHERE opt55 = #{opt55}")
    public FingerPrint getFingerPrintByOpt55(@Param("opt55") String opt55);

    @Select("SELECT * FROM fingerprints")
    public ArrayList<FingerPrint> getFingerPrints();

    @Insert("INSERT INTO fingerprints(opt55,dev_id,score,version,dev_name) " +
            "VALUES(#{fp.opt55},#{fp.device.id},#{fp.score},#{fp.version},#{fp.device.name})")
    public void setFingerPrintByOpt55(@Param("fp") FingerPrint fp);

    @Update("UPDATE fingerprints SET dev_id =#{fp.device.id},score=#{fp.score}," +
            "version = #{fp.version} , dev_name = #{fp.device.name} WHERE opt55 = #{fp.opt55}")
    public void updateFingerPrint(@Param("fp") FingerPrint fp);

    @Delete("DELETE FROM devices WHERE opt55 = #{opt55}")
    public void deleteFingerPrintByOpt55(@Param("opt55") String opt55);

}

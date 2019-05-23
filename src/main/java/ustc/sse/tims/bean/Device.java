package ustc.sse.tims.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.bean
 * @date 2019/5/14-12:44
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Device {

    public Integer id;
    public String name;
    public Date created_at;
    public Date updated_at;
    public Integer parent_id;
    public Integer virtual_parent_id;
    public Integer child_devices_count;
    public Integer child_virtual_devices_count;
    public boolean can_be_more_precise;
    @JsonIgnore
    public List<Device> parents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getVirtual_parent_id() {
        return virtual_parent_id;
    }

    public void setVirtual_parent_id(Integer virtual_parent_id) {
        this.virtual_parent_id = virtual_parent_id;
    }

    public Integer getChild_devices_count() {
        return child_devices_count;
    }

    public void setChild_devices_count(Integer child_devices_count) {
        this.child_devices_count = child_devices_count;
    }

    public Integer getChild_virtual_devices_count() {
        return child_virtual_devices_count;
    }

    public void setChild_virtual_devices_count(Integer child_virtual_devices_count) {
        this.child_virtual_devices_count = child_virtual_devices_count;
    }

    public boolean isCan_be_more_precise() {
        return can_be_more_precise;
    }

    public void setCan_be_more_precise(boolean can_be_more_precise) {
        this.can_be_more_precise = can_be_more_precise;
    }

    public List<Device> getParents() {
        return parents;
    }

    public void setParents(List<Device> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", parent_id=" + parent_id +
                ", virtual_parent_id=" + virtual_parent_id +
                ", child_devices_count=" + child_devices_count +
                ", child_virtual_devices_count=" + child_virtual_devices_count +
                ", can_be_more_precise=" + can_be_more_precise +
                ", parents=" + parents +
                '}';
    }
}

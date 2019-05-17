package ustc.sse.tims.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.bean
 * @date 2019/5/14-12:44
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
public class Device {
    private int id;
    private String name;
    private Date created_at;
    private Date updated_at;
    private int parent_id;
    private String virtual_parent_id;
    @JsonIgnore
    private int child_devices_count;
    private int child_virtual_devices_count;
    private boolean can_be_more_precise;
    private Device parents;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getVirtual_parent_id() {
        return virtual_parent_id;
    }

    public void setVirtual_parent_id(String virtual_parent_id) {
        this.virtual_parent_id = virtual_parent_id;
    }

    public int getChile_decices_count() {
        return child_devices_count;
    }

    public void setChile_decices_count(int chile_decices_count) {
        this.child_devices_count = chile_decices_count;
    }

    public int getChild_virtual_devices_count() {
        return child_virtual_devices_count;
    }

    public void setChild_virtual_devices_count(int child_virtual_devices_count) {
        this.child_virtual_devices_count = child_virtual_devices_count;
    }

    public boolean isCan_be_more_precise() {
        return can_be_more_precise;
    }

    public void setCan_be_more_precise(boolean can_be_more_precise) {
        this.can_be_more_precise = can_be_more_precise;
    }

    public Device getParents() {
        return parents;
    }

    public void setParents(Device parents) {
        this.parents = parents;
    }



    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", parent_id='" + parent_id + '\'' +
                ", virtual_parent_id='" + virtual_parent_id + '\'' +
                ", chile_decices_count=" + child_devices_count +
                ", child_virtual_devices_count=" + child_virtual_devices_count +
                ", can_be_more_precise=" + can_be_more_precise +
                ", parents=" + parents +
                '}';
    }
}

package ustc.sse.tims.model.menu;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.model.menu
 * @date 2019/3/8-9:50
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */
@XmlRootElement(name="menu")
public class Menu {

    private List<Submenu> submenus = new ArrayList<>();

    @XmlElement(name="submenu")
    public List<Submenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(List<Submenu> submenus) {
        this.submenus = submenus;
    }

}

package infrastructure.persistence.ciqual.foodgroups;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "TABLE")
public class FoodGroupsXmlFile {

    @XmlElement(name = "ALIM_GRP")
    public List<FoodGroupXml> foodGroups;

}

package infrastructure.persistence.ciqual.foodgroups;

import jakarta.xml.bind.annotation.XmlElement;

public class FoodGroupXml {
    @XmlElement(name = "alim_grp_code")
    public String code;
    @XmlElement(name = "alim_grp_nom_fr")
    public String name;
}

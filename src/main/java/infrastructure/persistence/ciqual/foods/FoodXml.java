package infrastructure.persistence.ciqual.foods;


import jakarta.xml.bind.annotation.XmlElement;


public class FoodXml {
    @XmlElement(name = "alim_code")
    public Integer code;
    @XmlElement(name = "ALIM_NOM_INDEX_FR")
    public String name;
    @XmlElement(name = "alim_grp_code")
    public String groupCode;

}

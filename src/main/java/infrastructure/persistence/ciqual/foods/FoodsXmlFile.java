package infrastructure.persistence.ciqual.foods;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "TABLE")
public class FoodsXmlFile {
    @XmlElement(name = "ALIM")
    public List<FoodXml> foods;
}

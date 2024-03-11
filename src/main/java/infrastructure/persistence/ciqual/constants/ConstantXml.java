package infrastructure.persistence.ciqual.constants;

import jakarta.xml.bind.annotation.XmlElement;

public class ConstantXml {
    @XmlElement(name = "const_code")
    public Integer code;
    @XmlElement(name = "const_nom_fr")
    public String nameFr;
    @XmlElement(name = "const_nom_eng")
    public String nameEn;

}
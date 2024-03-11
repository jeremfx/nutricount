package infrastructure.persistence.ciqual.compositions;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class CompositionXml {
    @XmlElement(name = "alim_code")
    public Integer foodCode;
    @XmlElement(name = "const_code")
    public Integer constantCode;
    @XmlElement(name = "teneur")
    @XmlJavaTypeAdapter(CommaSeparatedDoubleAdapter.class)
    public Double content;

}

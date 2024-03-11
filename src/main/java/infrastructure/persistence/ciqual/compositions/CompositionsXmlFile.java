package infrastructure.persistence.ciqual.compositions;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "TABLE")
public class CompositionsXmlFile {
    @XmlElement(name = "COMPO")
    public List<CompositionXml> compositions;
}

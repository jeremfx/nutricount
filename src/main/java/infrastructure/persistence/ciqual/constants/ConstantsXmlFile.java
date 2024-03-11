package infrastructure.persistence.ciqual.constants;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "TABLE")
public class ConstantsXmlFile {
    @XmlElement(name = "CONST")
    public List<ConstantXml> constants;
}
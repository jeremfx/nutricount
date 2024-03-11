package infrastructure.persistence.ciqual.compositions;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CommaSeparatedDoubleAdapter extends XmlAdapter<String, Double> {

    @Override
    public Double unmarshal(String value) throws Exception {
        return Double.parseDouble(value.trim().replace(',', '.'));
    }

    @Override
    public String marshal(Double aDouble) throws Exception {
        return aDouble.toString();
    }
}

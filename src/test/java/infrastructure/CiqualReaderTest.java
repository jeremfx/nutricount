package infrastructure;

import infrastructure.persistence.ciqual.CiqualReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CiqualReaderTest {

    CiqualReader reader = new CiqualReader();

    @Test
    void should_return_foods() {
        assertFalse(reader.foodsXml.isEmpty());
        assertNotNull(reader.foodsXml.get(0).code);
        assertNotNull(reader.foodsXml.get(0).name);
        assertNotNull(reader.foodsXml.get(0).groupCode);
    }

    @Test
    void should_return_foodGroups() {
        assertFalse(reader.foodGroupsXml.isEmpty());
        assertNotNull(reader.foodGroupsXml.get(0).code);
        assertNotNull(reader.foodGroupsXml.get(0).name);
    }

    @Test
    void should_return_compositions() {
        assertFalse(reader.compositionsXml.isEmpty());
        assertNotNull(reader.compositionsXml.get(0).foodCode);
        assertNotNull(reader.compositionsXml.get(0).constantCode);
        assertNotNull(reader.compositionsXml.get(0).content);
    }

    @Test
    void should_return_constants() {
        assertFalse(reader.constantsXml.isEmpty());
        assertNotNull(reader.constantsXml.get(0).code);
        assertNotNull(reader.constantsXml.get(0).nameFr);
        assertNotNull(reader.constantsXml.get(0).nameEn);
    }
}

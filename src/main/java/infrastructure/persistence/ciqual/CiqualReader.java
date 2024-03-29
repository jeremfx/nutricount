package infrastructure.persistence.ciqual;

import infrastructure.persistence.ciqual.compositions.CompositionXml;
import infrastructure.persistence.ciqual.compositions.CompositionsXmlFile;
import infrastructure.persistence.ciqual.constants.ConstantXml;
import infrastructure.persistence.ciqual.constants.ConstantsXmlFile;
import infrastructure.persistence.ciqual.foodgroups.FoodGroupXml;
import infrastructure.persistence.ciqual.foodgroups.FoodGroupsXmlFile;
import infrastructure.persistence.ciqual.foods.FoodXml;
import infrastructure.persistence.ciqual.foods.FoodsXmlFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CiqualReader {
    public final List<FoodXml> foodsXml;
    public final List<FoodGroupXml> foodGroupsXml;
    public final List<CompositionXml> compositionsXml;
    public final List<ConstantXml> constantsXml;

    public CiqualReader() {
        CompletableFuture<List<FoodXml>> foodsXmlfuture = CompletableFuture.supplyAsync(() -> readFoods().foods);
        CompletableFuture<List<FoodGroupXml>> foodGroupsXmlFuture = CompletableFuture.supplyAsync(() -> readFoodGroups().foodGroups);
        CompletableFuture<List<CompositionXml>> compositionsXmlFuture = CompletableFuture.supplyAsync(() -> readCompositionsXml().compositions);
        CompletableFuture<List<ConstantXml>> constantsXmlFuture = CompletableFuture.supplyAsync(() -> readConstantsXml().constants);
        try {
            foodsXml = foodsXmlfuture.get();
            foodGroupsXml = foodGroupsXmlFuture.get();
            compositionsXml = compositionsXmlFuture.get();
            constantsXml = constantsXmlFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private FoodsXmlFile readFoods() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ciqual/alim_2020_07_07.xml");
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(FoodsXmlFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (FoodsXmlFile) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private FoodGroupsXmlFile readFoodGroups() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ciqual/alim_grp_2020_07_07.xml");
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(FoodGroupsXmlFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (FoodGroupsXmlFile) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private CompositionsXmlFile readCompositionsXml() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ciqual/compo_2020_07_07.xml");
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(CompositionsXmlFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (CompositionsXmlFile) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private ConstantsXmlFile readConstantsXml() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ciqual/const_2020_07_07.xml");
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(ConstantsXmlFile.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (ConstantsXmlFile) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}

package entil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XMLTest {
    @Test
    public void saxParseXML() throws ParserConfigurationException, SAXException, IOException {
        // 工厂模式， 先生存创建解析器的工厂对象，然后这个工厂来生产解析器，
        // 由解析器通过读进来的输入流来解析xml
        // 创建解析器对象
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 由解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();

        PersonHandler personHandler = new PersonHandler();

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/person.xml");

        saxParser.parse(is, personHandler);

        List<Person> persons = personHandler.getPersonList();

        for (Person e :
                persons) {
            System.out.println(e);
        }
    }

    //第二种解析方法  使用dom4j第三方工具来进行解析，在项目src下面建立一个lib包，
    //将dom4j.jar包拷贝进去，然后构建路径
    @Test
    public void dom4jParseXML() throws DocumentException {
        //1 创建一个解析器对象
        SAXReader saxReader = new SAXReader();
        //2读文件进来，作为输入流
        InputStream is = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("data/person.xml");
        Document doc = saxReader.read(is);
        //3确定根节点
        Element rootElement = doc.getRootElement();
        Iterator<Element> iterator = rootElement.elementIterator();
        ArrayList<Person> persons = new ArrayList<Person>();
        Person p = null;
        //4.开始追个对元素进行解析，并创建person对象，将其加入列表
        while (iterator.hasNext()) {
            p = new Person();
            Element e = (Element) iterator.next();
            p.setPersonId(e.attributeValue("personId"));

            Iterator<Element> it1 = e.elementIterator();
            while (it1.hasNext()) {
                Element next = (Element) it1.next();
                String tag = next.getName();
                if ("name".equals(tag)) {
                    p.setName(next.getText());
                } else if ("age".equals(tag)) {
                    p.setAge(next.getText());

                } else if ("sex".equals(tag)) {
                    p.setSex(next.getText());

                } else if ("fax".equals(tag)) {
                    p.setFax(next.getText());
                }
            }
            persons.add(p);

        }
        //5 输出
        System.out.println("dom4j的输出结果是：");
        System.out.println(Arrays.toString(persons.toArray()));
    }

}

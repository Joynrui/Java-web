package entil;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

// 针对person.xml 的数据处理器
public class PersonHandler extends DefaultHandler {
    // 存放解析出的对象
    private List<Person> personList = null;
    private Person person;
    // 每次都保存标签
    private String tag;

    public List<Person> getPersonList() {
        return personList;
    }
    // Parse xml document.
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        personList = new ArrayList<Person>();
        System.out.println("Document begin to parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Parse end.");
    }

    /**
     * Parse xml elements.
     * @param uri  命名空间
     * @param localName 后缀
     * @param qName 标签名
     * @param attributes 标签属性
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if ("person".equals(qName)) {
            person = new Person();
            String personId = attributes.getValue("personId");
            person.setPersonId(personId);
        }
        tag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ("person".equals(qName)) {
            personList.add(person);
        }
        tag = null;
    }

    // 元素内部的标签的文本内容
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(tag != null) {
            if ("name".equals(tag)) {
                person.setName(new String(ch, start, length));
            } else if ("age".equals(tag)) {
                person.setAge(new String(ch, start, length));
            } else if ("sex".equals(tag)) {
                person.setSex(new String(ch, start, length));
            } else if ("fax".equals(tag)) {
                person.setFax(new String(ch, start, length));
            }
        }
    }


}

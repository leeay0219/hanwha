package lab2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TVUser {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lab2.xml");
		TV tv = (TV) context.getBean("stv"); // TV tv = context.getBean("ltv", TV.class) 해도됨
		
		//모바일 기기 쓸때는 이방법 좋음
//		Resource r = new ClassPathResource("lab2.xml");
//		BeanFactory f = new XmlBeanFactory(r); 
		
		tv.powerOn();
		tv.powerOff();
	}

}


package com.jeya.java;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.jeya.java.LvbSystem.Line;

public class Main {

	public static void main(String[] args) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			LvbSystem lvbSystem = (LvbSystem) unmarshaller.unmarshal(new File("lvb_system.xml")); // this will return an object annotated with @XmlRootElement
			
			List<LvbSystem.Line> lines = lvbSystem.getLine();
			for(int i = 0; i < lines.size(); i++)
			{
				Line line = lines.get(i);
				System.out.println("Code: " + line.getCode());
				System.out.println("CountVehicle: " + line.getCountVehicles());
				System.out.println("Type: " + line.getType());
				System.out.println("****************************");
			}
			System.out.println("--------------------------------");
			LvbSystem.Line newLine = new LvbSystem.Line();
			newLine.setCode("miyaav");
			lines.add(newLine);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(lines, new File("created.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
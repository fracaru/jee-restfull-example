package org.example.jee.rest.common.service.util;

import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Serializer {

	/**
	 * Serialize an object
	 * 
	 * @param o
	 * @throws JAXBException
	 */
	public static String serialize(Object o) throws JAXBException {

		StringWriter writer = new StringWriter();
		serialize(o, writer);
		return writer.toString();
	}

	/**
	 * Serialize an object
	 * 
	 * @param o
	 * @param writer
	 * @throws JAXBException
	 */
	public static void serialize(Object o, Writer writer) throws JAXBException {

		// write it out as XML
		final JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());

		// for cool output
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(o, writer);
	}

	/**
	 * Generic deserialization
	 * 
	 * @param <O>
	 * @param clazz
	 * @param reader
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <O extends Object> O deserialize(Class<O> clazz, Reader reader)
			throws JAXBException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		final O object = (O) jaxbContext.createUnmarshaller().unmarshal(reader);
		return object;
	}

}

package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.eclipse.persistence.exceptions.JAXBException;
import org.eclipse.persistence.internal.oxm.Marshaller;
import org.eclipse.persistence.internal.oxm.Unmarshaller;
import org.eclipse.persistence.jaxb.JAXBContext;

import db.interfaces.DBManager;
import db.jdbc.JDBCManager;
import pojos.Articulo;
import pojos.Marca;
import pojos.Tienda;

public class testTienda {

	 final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	 private static DBManager dbman = new JDBCManager(); 
	 
		public static void main(String[] args) throws JAXBException, IOException {
			
			//MyLogger.setupFromFile();
			Tienda tienda = randomTienda();
			//Empresa empresa = generarEmpresa();
			marshalling(tienda);
			unMarshalling();
			
		}

		private static Tienda randomTienda() {
			
			Tienda tienda = new Tienda ();
			ArrayList <Marca> marcas = dbman.getMarcas();
			ArrayList <Articulo> articulos = dbman.getArticulos();
		
			for (int i = 0 ; i < marcas.size() ; i ++ ) {
				
				ArrayList <Articulo> art = 
						dbman.getArticulosPorMarca(marcas.get(i).getIdM());
				
				for ( int j = 0 ; i < art.size(); j++) {
					
					marcas.get(i).addArticulo(art.get(j));
				}
				tienda.addMarca(marcas.get(i));
			}
			
			System.out.println("Se ha generado una tienda con " + marcas.size() +
					" marcas y " + articulos.size() + "articulos");
			return tienda;
		}

		private static void marshalling(Tienda t) throws JAXBException {
			// Creamos el JAXBContext
			JAXBContext jaxbC = JAXBContext.newInstance(Tienda.class);
			// Creamos el JAXBMarshaller
			Marshaller jaxbM = jaxbC.createMarshaller();
			// Formateo bonito
			jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			//jaxbM.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE Tienda SYSTEM \"Tienda.dtd\">");
			//jaxbM.setProperty("com.sun.xml.bind.xmlDeclaration", false);
			// Escribiendo en un fichero
			File XMLfile = new File("./xml/Tienda.xml");
			jaxbM.marshal(t, XMLfile);
			// Escribiendo por pantalla
			jaxbM.marshal(t, System.out);
		}
		
		private static void unMarshalling() throws JAXBException {
			// Creamos el JAXBContext
			JAXBContext jaxbC = JAXBContext.newInstance(Tienda.class);
			// Creamos el JAXBMarshaller
			Unmarshaller jaxbU = jaxbC.createUnmarshaller();
			// Leyendo un fichero
			File XMLfile = new File("./xml/Tienda.xml");
			// Creando el objeto
			Tienda t = (Tienda) jaxbU.unmarshal(XMLfile);
			// Escribiendo por pantalla el objeto
			System.out.println(t);
		}
	 
}

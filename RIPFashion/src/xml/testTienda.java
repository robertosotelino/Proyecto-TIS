package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import db.interfaces.DBManager;
import db.jdbc.JDBCManager;
import pojos.Articulo;
import pojos.Marca;
import pojos.Tienda;

public class testTienda {

	 final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	 private static DBManager dbman = new JDBCManager(); 

		public static Tienda randomTienda() {
			
			ArrayList <Tienda> tiendas = dbman.getInfoTiendasConCapital();
			Tienda tienda = tiendas.get(0);
			
			ArrayList <Marca> marcas = dbman.getMarcasPorTienda(tienda.getNombreTienda());
		
			for (int i = 0 ; i < marcas.size() ; i ++ ) {
				
				ArrayList <Articulo> art = 
						dbman.getArticulosPorMarca(marcas.get(i).getIdM()); 

				
				marcas.get(i).setArticulos(art);
			
				tienda.addMarca(marcas.get(i));
				
			}
			
			System.out.println("Se ha generado una tienda con " + marcas.size() +
					" marcas" );
			return tienda;
		}

		public void marshalling() throws JAXBException {
			Tienda t = randomTienda();
			// Creamos el JAXBContext
			JAXBContext jaxbC = JAXBContext.newInstance(Tienda.class);
			// Creamos el JAXBMarshaller
			Marshaller jaxbM = jaxbC.createMarshaller();
			// Formateo bonito
			jaxbM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			//jaxbM.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE Tienda SYSTEM \"Tienda.dtd\">");
			//jaxbM.setProperty("com.sun.xml.bind.xmlDeclaration", false);
			// Escribiendo en un fichero
			File XMLfile = new File("./src/xml/Tienda.xml");
			jaxbM.marshal(t, XMLfile);
			// Escribiendo por pantalla
			jaxbM.marshal(t, System.out);
		}
		
		public void unMarshalling() throws JAXBException {
			// Creamos el JAXBContext
			JAXBContext jaxbC = JAXBContext.newInstance(Tienda.class);
			// Creamos el JAXBMarshaller
			Unmarshaller jaxbU = jaxbC.createUnmarshaller();
			// Leyendo un fichero
			File XMLfile = new File("./src/xml/Tienda.xml");
			// Creando el objeto
			Tienda t = (Tienda) jaxbU.unmarshal(XMLfile);
			// Escribiendo por pantalla el objeto
			System.out.println(t);
		}

}

package katastar.UI;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import katastar.utils.ScannerWrapper;

public class AppUI {

	public static Connection conn;

	static {
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");

			// konekcija
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/katastar", "root", "eEflakjv39fE!fjd");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int odluka = -1;
		while (odluka != 0) {
			AppUI.ispisiMenu();

			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();

			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				VlasnikUI.prikaziSveVlasnike();
				break;
			case 2:
				NekrentinaUI.prikaziSveNekretninePoVlasniku();
				break;
			case 3:
				NekrentinaUI.promeniVlasnikaNekretnine();
				break;
			case 4:
				napraviJDOMxml();
			case 5:
				NekrentinaUI.izracunajPoreskuStopu();	
				break;
			case 6:
				NekrentinaUI.napraviFoldereZaMesta();	
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void napraviJDOMxml() {
		try {

			Element mesto = new Element("mesto");
			Document doc = new Document(mesto);
			

			Element nekretnina = new Element("NEKRETNINA");
			nekretnina.setAttribute(new Attribute("IDENTIFIKATOR", "1"));
			nekretnina.addContent(new Element("ADRESA").setText("Bulevar Oslobođenja 11/25, Novi Sad"));
			nekretnina.addContent(new Element("VREDNOST").setText("47000.00"));
			nekretnina.addContent(new Element("VLASNIK").setText("Petar Petrović (111111111111)"));
			

			doc.getRootElement().addContent(nekretnina);
			
			Element nekretnina2= new Element("NEKRETNINA");
			nekretnina2.setAttribute(new Attribute("IDENTIFIKATOR", "2"));
			nekretnina2.addContent(new Element("ADRESA").setText("Bulevar Oslobođenja 12/25, Novi Sad"));
			nekretnina2.addContent(new Element("VREDNOST").setText("46000.00"));
			nekretnina2.addContent(new Element("VLASNIK").setText("Petar Milosevic (22222222)"));
			

			doc.getRootElement().addContent(nekretnina2);
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data\\nekretnine.xml"));

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}

	}

	// ispis teksta osnovnih opcija
	public static void ispisiMenu() {
		System.out.println("Katastar - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Ispisi Sve Vlasnike");
		System.out.println("\tOpcija broj 2 - Nekretnine Vlasnika");
		System.out.println("\tOpcija broj 3 - Promena Vlasnika");
		System.out.println("\tOpcija broj 4 - Napravi nekretnine.xml");
		System.out.println("\tOpcija broj 5 - Izracunaj poresku stopu");
		System.out.println("\tOpcija broj 6 - Napravi foldere za mesta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

}

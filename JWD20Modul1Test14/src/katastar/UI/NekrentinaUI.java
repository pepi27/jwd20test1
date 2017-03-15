package katastar.UI;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import katastar.DAO.NekretninaDAO;
import katastar.DAO.VlasnikDAO;
import katastar.model.Nekretnina;
import katastar.model.Vlasnik;
import katastar.utils.ScannerWrapper;

public class NekrentinaUI {
	
	public static ArrayList<Vlasnik> sviVlasnici = new ArrayList<Vlasnik>();
	public static ArrayList<Nekretnina> sveNekretnine = new ArrayList<Nekretnina>(); 
    public static HashSet<String> svaMesta = new HashSet<>();
    private static double KOEF_KVAL = 0.8, STOPA_AMOR = 0.04, POR_STOPA = 0.4; 
	public static void prikaziSveNekretninePoVlasniku() {
		
		System.out.println("Prikazi nekretnine odredjenog vlasnika");
		
		VlasnikUI.prikaziSveVlasnike();
		sviVlasnici = VlasnikDAO.getAll(AppUI.conn); 
		
		System.out.println("unesite jmbg vlasnika za kojeg zelite da prikazete nekretninu");
		
		int jmbg = ScannerWrapper.ocitajCeoBroj();
		
		Vlasnik v = VlasnikUI.nadjiVlasnikaPoJMBG(jmbg, sviVlasnici);
		
		if(v != null) {
			// ovde pokazati dao
			System.out.println(NekretninaDAO.prikaziNekretninuVlasnika(AppUI.conn, v).toString());
		}	
		else 
			System.out.println("Ne postoji trazeni vlasnik");

	}

	public static void promeniVlasnikaNekretnine() {
		
		System.out.println("Sve nekretnine");
		System.out.println();
		
		sveNekretnine = NekretninaDAO.prikaziSveNekretnine(AppUI.conn); 
		
		for(Nekretnina n : sveNekretnine) {
			System.out.println(n.toString());
		}
		
		System.out.println();
		System.out.println("Unesite id nekretnine kojoj zelite da promenite vlasnika");
		int nekretninaId = ScannerWrapper.ocitajCeoBroj(); 
		System.out.println("unesite jmbg vlasnika");
		int jmbg = ScannerWrapper.ocitajCeoBroj(); 
		
		NekretninaDAO.promeniVlasnika(AppUI.conn, nekretninaId, jmbg); 
		
		sveNekretnine = NekretninaDAO.prikaziSveNekretnine(AppUI.conn); 
		
		for(Nekretnina n : sveNekretnine) {
			System.out.println(n.toString());
		}
		
	}

	public static void izracunajPoreskuStopu() {
		sveNekretnine = NekretninaDAO.prikaziSveNekretnine(AppUI.conn); 
		String[] tokeni = new String[sveNekretnine.size()]; 
		
		for (int i = 0; i < tokeni.length; i++) {
			tokeni[i] = sveNekretnine.get(i).getAdresa().replaceAll(".*, ", "");
			svaMesta.add(tokeni[i]);
		}
		
		for(String s : svaMesta) {
			System.out.println(s);
			for(Nekretnina n : sveNekretnine) {
				if(n.getAdresa().contains(s)) {
					double UMANJ_PORES_SUMA = (n.getAdresa().equals(n.getVlasnik().getAdresa())) ? 0.5 : 1; 
					System.out.println("\t" + n.getVlasnik() + " " + 
					((n.getVrednost() * KOEF_KVAL) - (n.getVrednost() * STOPA_AMOR)) * POR_STOPA * UMANJ_PORES_SUMA);
				}
			}
		}
	}

	public static void napraviFoldereZaMesta() {
		sveNekretnine = NekretninaDAO.prikaziSveNekretnine(AppUI.conn); 
		String[] tokeni = new String[sveNekretnine.size()]; 
		
		for (int i = 0; i < tokeni.length; i++) {
			tokeni[i] = sveNekretnine.get(i).getAdresa().replaceAll(".*, ", "");
		}
		
		for(int i = 0; i < tokeni.length; i++) {
			System.out.println(tokeni[i]);
			File dir = new File("data\\" + tokeni[i]);
			dir.mkdir();
		}
	}
	
	
}

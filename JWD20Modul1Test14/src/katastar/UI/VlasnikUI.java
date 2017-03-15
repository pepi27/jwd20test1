package katastar.UI;

import java.util.ArrayList;

import katastar.DAO.VlasnikDAO;
import katastar.model.Vlasnik;

public class VlasnikUI {

	public static ArrayList<Vlasnik> sviVlasnici = new ArrayList<Vlasnik>();
	
	public static void prikaziSveVlasnike() {
		
		System.out.println();
		sviVlasnici = VlasnikDAO.getAll(AppUI.conn);
		
		for(Vlasnik v : sviVlasnici) {
			System.out.println(v.toString());
		}
		System.out.println();
	}

	public static void promeniVlasnikaNekretnine() {
		// TODO Auto-generated method stub
		
	}
	
	public static Vlasnik nadjiVlasnikaPoJMBG(int jmbg, ArrayList<Vlasnik> sviVlasnici) {
		
		Vlasnik retVal = null;
		
		for(Vlasnik v : sviVlasnici) {
			if(v.getJmbg() == jmbg) {
				retVal = v; 
				break; 
			}
		}
		return retVal; 
	}
}

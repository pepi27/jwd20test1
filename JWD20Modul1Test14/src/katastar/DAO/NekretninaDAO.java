package katastar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import katastar.UI.AppUI;
import katastar.UI.VlasnikUI;
import katastar.model.Nekretnina;
import katastar.model.Vlasnik;

public class NekretninaDAO {

	public static ArrayList<Nekretnina> prikaziNekretninuVlasnika(Connection conn, Vlasnik v) {

		ArrayList<Vlasnik> sviVlasnici = new ArrayList<Vlasnik>();
		ArrayList<Nekretnina> retVal = new ArrayList<Nekretnina>();
		Set<String> mesta = new HashSet<>(); 

		try {

			String query = "SELECT * FROM nekretnina ";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				sviVlasnici = VlasnikDAO.getAll(AppUI.conn);
				Vlasnik vlasnik = VlasnikUI.nadjiVlasnikaPoJMBG(rs.getInt(4), sviVlasnici);
				Nekretnina n = new Nekretnina(rs.getInt(1), rs.getString(2), rs.getDouble(3), vlasnik);

				if (n.getVlasnik().getJmbg() == v.getJmbg()) {
					retVal.add(n);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static ArrayList<Nekretnina> prikaziSveNekretnine(Connection conn) {
		ArrayList<Nekretnina> retVal = new ArrayList<Nekretnina>();
		ArrayList<Vlasnik> sviVlasnici = new ArrayList<Vlasnik>();

		try {
			String query = "SELECT * FROM nekretnina ";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				sviVlasnici = VlasnikDAO.getAll(AppUI.conn);
				Vlasnik vlasnik = VlasnikUI.nadjiVlasnikaPoJMBG(rs.getInt(4), sviVlasnici);
				Nekretnina n = new Nekretnina(rs.getInt(1), rs.getString(2), rs.getDouble(3), vlasnik);
				retVal.add(n);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public static void promeniVlasnika(Connection conn, int nekretninaId, int jmbg) {
		try {
			String query = "UPDATE nekretnina SET vlasnik_id = '" + jmbg + "' WHERE id =" + nekretninaId;
			PreparedStatement st = conn.prepareStatement(query);
			ArrayList<Vlasnik> sviVlasnici = VlasnikDAO.getAll(AppUI.conn);

			if (VlasnikUI.nadjiVlasnikaPoJMBG(jmbg, sviVlasnici) == null)
				System.out.println("Ne postoji vlasnik sa jmbg " + jmbg);
			else
				st.executeUpdate();

			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void izracunajPS(Connection conn) {
	
		
	}
}
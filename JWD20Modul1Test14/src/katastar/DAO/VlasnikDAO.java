package katastar.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import katastar.model.Vlasnik;

public class VlasnikDAO {

	public static ArrayList<Vlasnik> getAll(Connection conn) {
		ArrayList<Vlasnik> retVal = new ArrayList<Vlasnik>(); 
		
		try {
			String query = "SELECT * FROM vlasnik"; 
			Statement st = conn.createStatement(); 
			ResultSet rs = st.executeQuery(query); 
			
			while(rs.next()) {
				Vlasnik vlasnik = new Vlasnik(rs.getInt(1), rs.getString(2), rs.getString(3));
				retVal.add(vlasnik); 
			}
			
			st.close(); 
			rs.close(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

}

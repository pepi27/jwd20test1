package katastar.model;

public class Vlasnik {

	private int jmbg; 
	private String ime_prezime; 
	private String adresa; 
	
	public Vlasnik () {}
	
	public Vlasnik(int jmbg, String ime_prezime, String adresa) {
		this.jmbg = jmbg; 
		this.ime_prezime = ime_prezime; 
		this.adresa = adresa; 
	}

	public String getIme_prezime() {
		return ime_prezime;
	}

	public void setIme_prezime(String ime_prezime) {
		this.ime_prezime = ime_prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getJmbg() {
		return jmbg;
	}

	@Override
	public String toString() {
		return "Vlasnik [jmbg=" + jmbg + ", ime_prezime=" + ime_prezime + ", adresa=" + adresa + "]";
	}
}

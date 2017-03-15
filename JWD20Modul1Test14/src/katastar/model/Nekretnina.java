package katastar.model;

public class Nekretnina {

	private int id; 
	private String adresa; 
	private double vrednost; 
	private Vlasnik vlasnik; 
	
	public Nekretnina() {}

	public Nekretnina(int id, String adresa, double vrednost, Vlasnik vlasnik) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.vrednost = vrednost;
		this.vlasnik = vlasnik;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public double getVrednost() {
		return vrednost;
	}

	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}

	public Vlasnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Vlasnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Nekretnina [id=" + id + ", adresa=" + adresa + ", vrednost=" + vrednost + ", vlasnik=" + vlasnik + "]";
	}
	
	
}

package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.IspitnaPrijava;
import model.Predmet;
import model.Student;
import utils.PomocnaKlasa;

public class PredmetUI {

	/** ATRIBUTI KLASE ****/
	public static ArrayList<Predmet> sviPredmeti = new ArrayList<Predmet>();
	
	public static void meniPredmetUI(){
		int odluka = -1;
		while (odluka != 0) {
			ispisiTekstStudentOpcije();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				unosNovogPredmeta();
				break;
			case 2:
				izmenaPodatakaOPredmetu();
				break;
			case 3:
				brisanjePodatakaOPredmetu();
				break;
			case 4:
				ispisiSvePredmete();
				break;
			case 5:
				Predmet pr = pronadjiPredmet();
				if(pr!=null){
					System.out.println(pr.toStringAllStudent());
				}	
				break;
			case 6:
				Predmet pr2 = pronadjiPredmet();
				if(pr2!=null){
					System.out.println(pr2.toStringAllIspitnaPrijava());
				}	
				break;
			case 7:
				sortirajPredmetePoNazivu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	public static void ispisiTekstStudentOpcije() {
		System.out.println("Rad sa predmetima - opcije:");
		System.out.println("\tOpcija broj 1 - unos podataka o novom Predmetu");
		System.out.println("\tOpcija broj 2 - izmena podataka o Predmetu");
		System.out.println("\tOpcija broj 3 - brisanje podataka o Predmetu");
		System.out.println("\tOpcija broj 4 - ispis podataka svih predmeta");
		System.out.println("\tOpcija broj 5 - ispis podataka o odre\u0111enom Predmetu i svih studenta koji poha\u0111aju predmet");
		System.out.println("\tOpcija broj 6 - ispis podataka o odre\u0111enom Predmetu i svih ispitnih prijava za predmet");
		System.out.println("\tOpcija broj 7 - sortiranje predmeta po nazivu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	
	/** METODE ZA ISPIS PREDMETA ****/
	// ispisi sve predmete
	public static void ispisiSvePredmete() {
		for (int i = 0; i < sviPredmeti.size(); i++) {
			System.out.println(sviPredmeti.get(i));
		}
	}
	
	/** METODE ZA PRETRAGU PREDMETA ****/
	// pronadji predmet
	public static Predmet pronadjiPredmet() {
		Predmet retVal = null;
		System.out.print("Unesi id predmeta:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiPredmetId(id);
		if (retVal == null)
			System.out.println("Predmet sa id-om " + id
					+ " ne postoji u evidenciji");
		return retVal;
	}

	// pronadji predmet
	public static Predmet pronadjiPredmetId(int id) {
		Predmet retVal = null;
		for (int i = 0; i < sviPredmeti.size(); i++) {
			Predmet pr = sviPredmeti.get(i);
			if (pr.getId() ==id) {
				retVal = pr;
				break;
			}
		}
		return retVal;
	}
	
	public static int pronadjiPozicijuPredmeta(){
		int retVal = -1;
		System.out.println("Unesi id predmeta:");
		int id = PomocnaKlasa.ocitajCeoBroj();
		for (int i = 0; i < sviPredmeti.size(); i++) {
			Predmet pr = sviPredmeti.get(i);
			if (pr.getId() ==id) {
				retVal = i;
				break;
			}
		}
		if(retVal == -1)
			System.out.println("Predmet sa id-om " + id
					+ " ne postoji u evidenciji");
		return retVal;
	}
	/** METODA ZA SORTIRANJE PREDMETA****/
	public static void sortirajPredmetePoNazivu(){
		System.out.println("Premete je mogu\u0107e sortirati\n\t1 - Naziv Rastu\u0107e\n\t2- Naziv Opadaju\u0107e\nIzaberi opciju:");
		int sortOpcija=PomocnaKlasa.ocitajCeoBroj();
		switch (sortOpcija) {
		case 1:
			//TO DO
			break;
		case 2:
			//TO DO
			break;
		default:
			break;
		}
		
	}
	
	/** METODE ZA UNOS, IZMENU I BRISANJE PREDMETA ****/
	// unos novog predmeta
	public static void unosNovogPredmeta() {
		System.out.print("Naziv:");
		String prNaziv= PomocnaKlasa.ocitajTekst();
		
		Predmet pred = new Predmet(0, prNaziv);
		sviPredmeti.add(pred);
		
		while (PomocnaKlasa.ocitajOdlukuOPotvrdi("upisati studente da poha\u0111aju predmet") == 'Y') {
			PohadjaUI.dodajStudentaNaPredmet(pred);
		}
	}
	
	// izmena predmeta
	public static void izmenaPodatakaOPredmetu() {
		Predmet pred = pronadjiPredmet();
		if(pred != null){
			System.out.print("Unesi novi naziv :");
			String prNaziv = PomocnaKlasa.ocitajTekst();
			pred.setNaziv(prNaziv);
			
			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("ukloniti studente da ne poha\u0111aju predmet") == 'Y') {
				PohadjaUI.dodajStudentaNaPredmet(pred);
			}
			
			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("upisati studente da poha\u0111aju predmet") == 'Y') {
				PohadjaUI.dodajStudentaNaPredmet(pred);
			}
		}
	}

	//brisanje predmeta
	public static void brisanjePodatakaOPredmetu(){
		int pozicija = pronadjiPozicijuPredmeta();
		if(pozicija!=-1){
			Predmet pr = sviPredmeti.remove(pozicija);
			
			ArrayList<Student> listStudOdPredmetaZaBrisanje = new ArrayList<Student>(pr.getStudenti());
			ArrayList<IspitnaPrijava> listIspOdPredmetaZaBrisanje = new ArrayList<IspitnaPrijava>(pr.getIspitnePrijave());
			
			//sada moramo da uklonim sve za pohadja
			for (Student st : listStudOdPredmetaZaBrisanje) {
				PohadjaUI.ukloniStudentaSaPredmeta(st, pr);
			}
			
			//sada moramo da uklonim sve za ispitnu prijavu
			for (IspitnaPrijava isp : listIspOdPredmetaZaBrisanje) {
				IspitnaPrijavaUI.ukloniIspitnuPrijavu(isp);
			}
			
			
			System.out.println("Podaci obrisani iz evidencije");
		}
	}	
	
	/** METODA ZA UCITAVANJE PODATAKA****/
	static void citajIzFajlaPredmete(File dokument) throws IOException {
		if(dokument.exists()){

			BufferedReader in = new BufferedReader(new FileReader(dokument));
			
			//workaround for UTF-8 files and BOM marker
			//BOM (byte order mark) marker may appear on the beginning of the file
			//BOM can signal which of several Unicode encodings (8-bit, 16-bit, 32-bit) that text is encoded as
			
			in.mark(1); //zapamti trenutnu poziciju u fajlu da mozes kasnije da se vratis na nju
			if(in.read()!='\ufeff'){
				in.reset();
			}
			
			String s2;
			while((s2 = in.readLine()) != null) {
				sviPredmeti.add(new Predmet(s2));
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}
	
	static void pisiUFajlPredmete(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (Predmet predmet : sviPredmeti) {
			out2.println(predmet.toFileRepresentation());
		}
		
		out2.flush();
		out2.close();
	}
}

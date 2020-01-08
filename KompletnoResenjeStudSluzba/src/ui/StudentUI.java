package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import model.IspitnaPrijava;
import model.Predmet;
import model.Student;
import utils.PomocnaKlasa;
import utils.StudentNameComparator;
import utils.StudentPolozeniIspitiComparator;

public class StudentUI {

	/** ATRIBUTI KLASE ****/
	public static ArrayList<Student> sviStudenti = new ArrayList<Student>();
	
	/** MENI OPCJA ****/
	public static void meniStudentUI(){	
		int odluka = -1;
		while(odluka!= 0){
			ispisiTekstStudentOpcije();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
				case 0:	
					System.out.println("Izlaz");	
					break;
				case 1:	
					unosNovogStudenta();	
					break;
				case 2:	
					izmenaPodatakaOStudentu();	
					break;
				case 3:	
					brisanjePodatakaOStudentu();
					break;
				case 4:	
					ispisiSveStudente();
					break;
				case 5:	
					Student st = pronadjiStudentaIndeks();
					if(st!=null){
						System.out.println(st.toStringAllPredmet());
					}	
					break;
				case 6:	
					Student st2 = pronadjiStudentaIndeks();
					if(st2!=null){
						System.out.println(st2.toStringAllIspitnaPrijava());
					}	
					break;
				case 7:	
					sortirajStudentePoImenu();
					break;
				case 8:	
					sortirajStudentePoBrojuPolozenihPredmeta();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;	
			}
		}
	}
	
	/** METODE ZA ISPIS OPCIJA ****/
	//ispis teksta osnovnih opcija
	
	public static void ispisiTekstStudentOpcije(){	
		System.out.println("Rad sa studentima - opcije:");
		System.out.println("\tOpcija broj 1 - unos podataka o novom studentu");
		System.out.println("\tOpcija broj 2 - izmena podataka o studentu");
		System.out.println("\tOpcija broj 3 - brisanje podataka o studentu");
		System.out.println("\tOpcija broj 4 - ispis podataka svih studenata");
		System.out.println("\tOpcija broj 5 - ispis podataka o odre\u0111enom studentu sa njegovim predmetima koje poha\u0111a");
		System.out.println("\tOpcija broj 6 - ispis podataka o odre\u0111enom studentu sa njegovim ispitnim prijavama");
		System.out.println("\tOpcija broj 7 - sortiranje studenata po imenu");
		System.out.println("\tOpcija broj 8 - sortiranje studenata po broju položenih predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	
	/** METODE ZA ISPIS STUDENATA****/
	//ispisi sve studente
	public static void ispisiSveStudente(){
		for (int i = 0; i < sviStudenti.size(); i++) {
			System.out.println(sviStudenti.get(i));
		}
	}
	
	/** METODE ZA PRETRAGU STUDENATA****/
	//pronadji studenta
	public static Student pronadjiStudentaIndeks(){
		Student retVal = null;
		System.out.println("Unesi indeks studenta:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		retVal = pronadjiStudentaIndeks(stIndex);
		if(retVal == null)
			System.out.println("Student sa indeksom " +stIndex + " ne postoji u evidenciji");
		return retVal;
	}
	
	//pronadji studenta
	public static Student pronadjiStudentaIndeks(String stIndex){
		Student retVal = null;
		for (int i = 0; i < sviStudenti.size(); i++) {
			Student st = sviStudenti.get(i);
			if (st.getIndeks().equals(stIndex)) {
				retVal = st;
				break;
			}
		}
		return retVal;
	}
	
	//pronadji studenta
	public static Student pronadjiStudentaId(int id){
		Student retVal = null;
		for (int i = 0; i < sviStudenti.size(); i++) {
			Student st = sviStudenti.get(i);
			if (st.getId()==id) {
				retVal = st;
				break;
			}
		}
		return retVal;
	}
	
	public static int pronadjiPozicijuStudentaIndeks(){
		int retVal = -1;
		System.out.println("Unesi indeks studenta:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		for (int i = 0; i < sviStudenti.size(); i++) {
			Student st = sviStudenti.get(i);
			if (st.getIndeks().equals(stIndex)) {
				retVal = i;
				break;
			}
		}
		if(retVal == -1)
			System.out.println("Student sa indeksom " +stIndex + " ne postoji u evidenciji");
		return retVal;
	}
	
	/** METODA ZA SORTIRANJE STUDENATA****/
	public static void sortirajStudentePoImenu(){
		System.out.println("Studente je mogu\u0107e sortirati\n\t1 - Ime Rastu\u0107e\n\t2- Ime Opadaju\u0107e\nIzaberi opciju:");
		int sortOpcija=PomocnaKlasa.ocitajCeoBroj();
		switch (sortOpcija) {
		case 1:
			Collections.sort(sviStudenti, new StudentNameComparator(1));
			break;
		case 2:
			Collections.sort(sviStudenti, new StudentNameComparator(-1));
			break;
		default:
			break;
		}
		
	}
	
	/** METODA ZA SORTIRANJE STUDENATA****/
	public static void sortirajStudentePoBrojuPolozenihPredmeta(){
		System.out.println("Studente je mogu\u0107e sortirati\n\t1 - Br Položenih predmeta Rastu\u0107e\n\t2 - Br Položenih predmeta Opadaju\u0107e\nIzaberi opciju:");
		int sortOpcija=PomocnaKlasa.ocitajCeoBroj();
		switch (sortOpcija) {
		case 1:
			Collections.sort(sviStudenti, new StudentPolozeniIspitiComparator(1));
			break;
		case 2:
			Collections.sort(sviStudenti, new StudentPolozeniIspitiComparator(-1));
			break;
		default:
			break;
		}
		
	}
	
	/** METODE ZA UNOS, IZMENU i BRISANJE STUDENATA****/
	
	//unos novog studenta
	public static void unosNovogStudenta(){
		System.out.print("Unesi index:");
		String stIndex = PomocnaKlasa.ocitajTekst();
		stIndex = stIndex.toUpperCase();
		while(pronadjiStudentaIndeks(stIndex) != null){
			System.out.println("Student sa indeksom "+stIndex + " vec postoji");
			stIndex = PomocnaKlasa.ocitajTekst();
		}
		System.out.print("Unesi ime:");
		String stIme = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi prezime:");
		String stPrezime = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi grad:");
		String stGrad  = PomocnaKlasa.ocitajTekst();
	
		//ID atribut ce se dodeliti automatski
		Student st = new Student(0,stIme, stPrezime, stGrad, stIndex);
		sviStudenti.add(st);
		
		while (PomocnaKlasa.ocitajOdlukuOPotvrdi("upisati studenta da poha\u0111a odre\u0111ene predmet") == 'Y') {
			PohadjaUI.dodajStudentaNaPredmet(st);
		}
	}
	
	//izmena studenta
	public static void izmenaPodatakaOStudentu(){
		Student st  = pronadjiStudentaIndeks();
		if(st != null){
			System.out.print("Unesi ime:");
			String stIme = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi prezime:");
			String stPrezime = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi grad:");
			String stGrad  = PomocnaKlasa.ocitajTekst();
			
			st.setIme(stIme);
			st.setPrezime(stPrezime);
			st.setGrad(stGrad);
			
			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("ukloniti studenta da ne poha\u0111a odre\u0111ene predmet") == 'Y') {
				PohadjaUI.ukloniStudentaSaPredmeta(st);
			}
			
			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("upisati studenta da poha\u0111a odre\u0111ene predmet") == 'Y') {
				PohadjaUI.dodajStudentaNaPredmet(st);
			}
		}
	}
	
	//brisanje studenta
	public static void brisanjePodatakaOStudentu(){
		int pozicija = pronadjiPozicijuStudentaIndeks();
		if(pozicija!=-1){
			Student st = sviStudenti.remove(pozicija);
			
			ArrayList<Predmet> listStudOdPredmetaZaBrisanje = new ArrayList<Predmet>(st.getPredmeti());
			ArrayList<IspitnaPrijava> listIspOdPredmetaZaBrisanje = new ArrayList<IspitnaPrijava>(st.getIspitnePrijave());
			
			//sada moramo da uklonim sve za pohadja
			for (Predmet pr : listStudOdPredmetaZaBrisanje) {
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
	static void citajIzFajlaStudente(File dokument) throws IOException {
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
				sviStudenti.add(new Student(s2));
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}
	
	static void pisiUFajlStudente(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (Student student : sviStudenti) {
			out2.println(student.toFileRepresentation());
		}
		
		out2.flush();
		out2.close();
	}
}

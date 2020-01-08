package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.Predmet;
import model.Student;
import utils.PomocnaKlasa;

public class PohadjaUI {
	private static void ispisiMenu() {
		System.out.println("Rad sa predmetima studenta - opcije:");
		System.out.println("\tOpcija broj 1 - predmeti koje student poha\u0111a");
		System.out.println("\tOpcija broj 2 - studenti koji pohadjaju predmet");
		System.out.println("\tOpcija broj 3 - dodavanje studenta na predmet");
		System.out.println("\tOpcija broj 4 - uklanjanje studenta sa predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}

	public static void menuPohadjaUI() {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				ispisiPredmeteZaStudenta();
				break;
			case 2:
				ispisiStudenteZaPredmet();
				break;
			case 3:
				dodajStudentaNaPredmet();
				break;
			case 4:
				ukloniStudentaSaPredmeta();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiPredmeteZaStudenta() {
		// najpre pronadjemo studenta za kojeg zelimo ispis predmeta
		Student student = StudentUI.pronadjiStudentaIndeks();
		if (student != null) {
			List<Predmet> predmeti = student.getPredmeti();
	
			for (Predmet p : predmeti) {
				System.out.println(p);
			}
		}
	}
	
	public static void ispisiStudenteZaPredmet() {
		// najpre pronadjemo predmet za koji zelimo ispis studenata
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if (predmet != null) {
			List<Student> studenti = predmet.getStudenti();
	
			for (Student s : studenti) {
				System.out.println(s);
			}
		}
	}

	public static void dodajStudentaNaPredmet() {
		// najpre pronadjemo studenta kojeg zelimo da dodamo na predmet
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo predmet na koji zelimo da dodamo studenta
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		dodajStudentaNaPredmet(student, predmet);
	}
	
	public static void dodajStudentaNaPredmet(Student student) {
		//pronadjemo predmet na koji zelimo da dodamo studenta
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		dodajStudentaNaPredmet(student, predmet);
	}
	
	public static void dodajStudentaNaPredmet(Predmet predmet) {
		// najpre pronadjemo studenta kojeg zelimo da dodamo na predmet
		Student student = StudentUI.pronadjiStudentaIndeks();
		
		dodajStudentaNaPredmet(student, predmet);
	}
	
	public static void dodajStudentaNaPredmet(Student student, Predmet predmet) {	
		if (student != null && predmet != null) {
			//uspostavimo vezu izmedju studenta i predmeta
			List<Predmet> predmeti = student.getPredmeti();
			boolean found = false;
			for (int i = 0; i < predmeti.size(); i++) {
				if(predmet.equals(predmeti.get(i))){
					found = true;
					break;
				}
			}
			if(!found){
				student.getPredmeti().add(predmet);
				predmet.getStudenti().add(student);
			}
		}
		else {
			System.out.println("ne postoje podaci o studentu/premetu");
		}
	}
	
	public static void ukloniStudentaSaPredmeta() {
		// najpre pronadjemo studenta kojeg zelimo da uklonimo sa predmeta
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo predmet sa kojeg zelimo da ukloniko studenta
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		ukloniStudentaSaPredmeta(student, predmet);
	}
	
	public static void ukloniStudentaSaPredmeta(Student student) {
		//pronadjemo predmet sa kojeg zelimo da ukloniko studenta
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		ukloniStudentaSaPredmeta(student, predmet);
	}
	
	public static void ukloniStudentaSaPredmeta(Predmet predmet) {
		// najpre pronadjemo studenta kojeg zelimo da uklonimo sa predmeta
		Student student = StudentUI.pronadjiStudentaIndeks();
		
		ukloniStudentaSaPredmeta(student, predmet);
	}
	
	public static void ukloniStudentaSaPredmeta(Student student,Predmet predmet) {
		if (student != null && predmet != null) {
			//brisemo vezu izmedju studenta i predmeta u studentu
			List<Predmet> predmeti = student.getPredmeti();
			boolean found = false;
			for (int i = 0; i < predmeti.size(); i++) {
				if(predmet.equals(predmeti.get(i))){
					found=true;
					predmeti.remove(i);
					break;
				}
			}
			if(found){
				//brisemo vezu izmedju studenta i predmeta u predmetu
				List<Student> studenti = predmet.getStudenti();
				for (int i = 0; i < studenti.size(); i++) {
					if(student.equals(studenti.get(i))){
						studenti.remove(i);
						break;
					}
				}
			}
		}
	}
	
	/** METODA ZA UCITAVANJE PODATAKA****/
	static void citajIzFajlaPohadja(File dokument) throws IOException {
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
				String [] pohadjaPodaciTekst = s2.split(",");
				int idStudenta = Integer.parseInt(pohadjaPodaciTekst[0]);
				int idPredmeta = Integer.parseInt(pohadjaPodaciTekst[1]);
				Student st = StudentUI.pronadjiStudentaId(idStudenta);
				Predmet pr = PredmetUI.pronadjiPredmetId(idPredmeta);
				if(st!=null && pr!=null){
					st.getPredmeti().add(pr);
					pr.getStudenti().add(st);
				}
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}
	
	static void pisiUFajlPohadja(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (Student st : StudentUI.sviStudenti) {
			for (Predmet pr : st.getPredmeti()) {
				out2.println(st.getId()+","+pr.getId());
			}
		}
		
		out2.flush();
		out2.close();
	}
}

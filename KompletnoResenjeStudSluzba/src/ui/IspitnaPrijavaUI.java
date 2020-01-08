package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.IspitnaPrijava;
import model.IspitniRok;
import model.Predmet;
import model.Student;
import utils.PomocnaKlasa;

public class IspitnaPrijavaUI {
	private static void ispisiMenu() {
		System.out.println("Rad sa ispitnim prijavama - opcije:");
		System.out.println("\tOpcija broj 1 - ispitne prijave za ispitni rok");
		System.out.println("\tOpcija broj 2 - ispitne prijave za studenta");
		System.out.println("\tOpcija broj 3 - ispitne prijave za predmet");
		System.out.println("\tOpcija broj 4 - dodavanje ispitne prijave");
		System.out.println("\tOpcija broj 5 - uklanjanje ispitne prijave");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
	}

	public static void menuIspitnaPrijavaUI() {
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
				ispisiIspitnePrijaveZaIspitnirok();
				break;
			case 2:
				ispisiIspitnePrijaveZaStudenta();
				break;
			case 3:
				ispisiispitnePrijaveZaPredmet();
				break;
			case 4:
				dodajIspitnuPrijavu();
				break;
			case 5:
				ukloniIspitnuPrijavu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	public static void ispisiIspitnePrijaveZaIspitnirok() {
		// najpre pronadjemo ispitni rok za kojeg zelimo ispis
		IspitniRok ir = IspitniRokUI.pronadjiIspitniRok();
		if (ir != null) {
			List<IspitnaPrijava> ispitnePrijave = ir.getIspitnePrijave();
	
			for (IspitnaPrijava isp : ispitnePrijave) {
				System.out.println(isp);
			}
		}
	}
	
	public static void ispisiIspitnePrijaveZaStudenta() {
		// najpre pronadjemo studenta za kojeg zelimo ispis
		Student st = StudentUI.pronadjiStudentaIndeks();
		if (st != null) {
			List<IspitnaPrijava> ispitnePrijave = st.getIspitnePrijave();
	
			for (IspitnaPrijava isp : ispitnePrijave) {
				System.out.println(isp);
			}
		}
	}
	
	public static void ispisiispitnePrijaveZaPredmet() {
		// najpre pronadjemo predmet za koji zelimo ispis
		Predmet predmet = PredmetUI.pronadjiPredmet();
		if (predmet != null) {
			List<IspitnaPrijava> ispitnePrijave = predmet.getIspitnePrijave();
	
			for (IspitnaPrijava ips : ispitnePrijave) {
				System.out.println(ips);
			}
		}
	}

	public static void dodajIspitnuPrijavu() {
		// najpre pronadjemo studenta za kojeg zelimo da dodamo na ispitnu prijavu
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo predmet za koji zelimo da dodamo na ispitnu prijavu
		Predmet predmet = PredmetUI.pronadjiPredmet();
		//pronadjemo ispitni rok za koji zelimo da dodamo na ispitnu prijavu
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		dodajIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void dodajIspitnuPrijavu(IspitniRok ispitniRok) {
		
		// najpre pronadjemo studenta za kojeg zelimo da dodamo na ispitnu prijavu
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo predmet za koji zelimo da dodamo na ispitnu prijavu
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		dodajIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void dodajIspitnuPrijavu(Student student) {
		
		//pronadjemo predmet za koji zelimo da dodamo na ispitnu prijavu
		Predmet predmet = PredmetUI.pronadjiPredmet();
		//pronadjemo ispitni rok za koji zelimo da dodamo na ispitnu prijavu
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		dodajIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void dodajIspitnuPrijavu(Predmet predmet) {
		// najpre pronadjemo studenta za kojeg zelimo da dodamo na ispitnu prijavu
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo ispitni rok za koji zelimo da dodamo na ispitnu prijavu
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		dodajIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void dodajIspitnuPrijavu(IspitniRok ispitniRok, Student student, Predmet predmet) {	
		if (ispitniRok != null && student != null && predmet != null) {
			//uspostavimo vezu izmedju entiteta
			List<IspitnaPrijava> ispitnePrijave = ispitniRok.getIspitnePrijave();
			boolean found = false;
			for (int i = 0; i < ispitnePrijave.size(); i++) {
				if(ispitniRok.equals(ispitnePrijave.get(i).getIr()) && student.equals(ispitnePrijave.get(i).getSt())
						&& predmet.equals(ispitnePrijave.get(i).getPr())){
					found = true;
					break;
				}
			}
			if(!found){
				System.out.println("unesite ceo broj bodova za teoriju ");
				int teorija = PomocnaKlasa.ocitajCeoBroj();
				System.out.println("unesite ceo broj bodova za zadatke ");
				int zadaci = PomocnaKlasa.ocitajCeoBroj();
				IspitnaPrijava isp = new IspitnaPrijava(student, predmet, ispitniRok, teorija, zadaci);
				
				ispitniRok.getIspitnePrijave().add(isp);
				student.getIspitnePrijave().add(isp);
				predmet.getIspitnePrijave().add(isp);
			}
		}
		else {
			System.out.println("ne postoje podaci o ispitnom roku/studentu/premetu");
		}
	}
	
	public static void ukloniIspitnuPrijavu(IspitnaPrijava isp) {
		// najpre pronadjemo studenta za kojeg zelimo da uklonimo ispitnu prijavu
		Student student = isp.getSt();
		//pronadjemo predmet za koji zelimo da uklonimo ispitnu prijavu
		Predmet predmet = isp.getPr();
		//pronadjemo ispitni rok za koji zelimo da uklonimo ispitnu prijavu
		IspitniRok ispitniRok = isp.getIr();
		
		ukloniIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void ukloniIspitnuPrijavu() {
		// najpre pronadjemo studenta za kojeg zelimo da uklonimo ispitnu prijavu
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo predmet za koji zelimo da uklonimo ispitnu prijavu
		Predmet predmet = PredmetUI.pronadjiPredmet();
		//pronadjemo ispitni rok za koji zelimo da uklonimo ispitnu prijavu
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		ukloniIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void ukloniIspitnuPrijavu(IspitniRok ispitniRok) {
		// najpre pronadjemo studenta za kojeg zelimo da uklonimo ispitnu prijavu
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo predmet za koji zelimo da uklonimo ispitnu prijavu
		Predmet predmet = PredmetUI.pronadjiPredmet();
		
		ukloniIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void ukloniIspitnuPrijavu(Student student) {
		//pronadjemo predmet za koji zelimo da uklonimo ispitnu prijavu
		Predmet predmet = PredmetUI.pronadjiPredmet();
		//pronadjemo ispitni rok za koji zelimo da uklonimo ispitnu prijavu
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		ukloniIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void ukloniIspitnuPrijavu(Predmet predmet) {
		// najpre pronadjemo studenta za kojeg zelimo da uklonimo ispitnu prijavu
		Student student = StudentUI.pronadjiStudentaIndeks();
		//pronadjemo ispitni rok za koji zelimo da uklonimo ispitnu prijavu
		IspitniRok ispitniRok = IspitniRokUI.pronadjiIspitniRok();
		
		ukloniIspitnuPrijavu(ispitniRok, student, predmet);
	}
	
	public static void ukloniIspitnuPrijavu(IspitniRok ispitniRok, Student student,Predmet predmet) {
		if (ispitniRok != null && student != null && predmet != null) {
			//brisemo vezu izmedju ispitne prijave i ostalih entiteta
			List<IspitnaPrijava> ispitnePrijaveOdIspitnogRoka = ispitniRok.getIspitnePrijave();
			for (int i = 0; i < ispitnePrijaveOdIspitnogRoka.size(); i++) {
				if(ispitniRok.equals(ispitnePrijaveOdIspitnogRoka.get(i).getIr()) && student.equals(ispitnePrijaveOdIspitnogRoka.get(i).getSt())
						&& predmet.equals(ispitnePrijaveOdIspitnogRoka.get(i).getPr())){
					ispitnePrijaveOdIspitnogRoka.remove(i);
					break;
				}
			}
			
			List<IspitnaPrijava> ispitnePrijaveOdStudenta = student.getIspitnePrijave();
			for (int i = 0; i < ispitnePrijaveOdStudenta.size(); i++) {
				if(ispitniRok.equals(ispitnePrijaveOdStudenta.get(i).getIr()) && student.equals(ispitnePrijaveOdStudenta.get(i).getSt())
						&& predmet.equals(ispitnePrijaveOdStudenta.get(i).getPr())){
					ispitnePrijaveOdStudenta.remove(i);
					break;
				}
			}
			
			List<IspitnaPrijava> ispitnePrijaveOdPredmeta = predmet.getIspitnePrijave();
			for (int i = 0; i < ispitnePrijaveOdPredmeta.size(); i++) {
				if(ispitniRok.equals(ispitnePrijaveOdPredmeta.get(i).getIr()) && student.equals(ispitnePrijaveOdPredmeta.get(i).getSt())
						&& predmet.equals(ispitnePrijaveOdPredmeta.get(i).getPr())){
					ispitnePrijaveOdPredmeta.remove(i);
					break;
				}
			}
		}
	}
	/** METODA ZA UCITAVANJE PODATAKA****/
	static void citajIzFajlaIspitnePrijave(File dokument) throws IOException {
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
				new IspitnaPrijava(s2);
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}
	
	static void pisiUFajlIspitnePrijave(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (IspitniRok	ir : IspitniRokUI.sviIspitniRokovi) {
			for (IspitnaPrijava isp : ir.getIspitnePrijave()) {
				out2.println(isp.toFileRepresentation());
			}
		}
		
		out2.flush();
		out2.close();
	}
}

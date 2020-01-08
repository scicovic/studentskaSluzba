package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.IspitnaPrijava;
import model.IspitniRok;
import utils.PomocnaKlasa;

public class IspitniRokUI {

	/** ATRIBUTI KLASE ****/
	public static ArrayList<IspitniRok> sviIspitniRokovi = new ArrayList<IspitniRok>();
	
	/** MENI OPCJA ****/
	public static void meniIspitniRokUI(){	
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
					unosNovogIspitnogRoka();	
					break;
				case 2:	
					izmenaPodatakaOIspitnomRoku();	
					break;
				case 3:	
					brisanjePodatakaOIspitnomRoku();
					break;
				case 4:	
					ispisiSveIspitneRokove();
					break;
				case 5:	
					IspitniRok ir = pronadjiIspitniRok();
					if(ir!=null){
						System.out.println(ir.toStringAllIspitnaPrijava());
					}	
					break;
				case 6:	
					sortirajIspitneRokovePoDatumnu();
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
		System.out.println("Rad sa Ispitnim Rokovima - opcije:");
		System.out.println("\tOpcija broj 1 - unos podataka o novom ispitnom roku");
		System.out.println("\tOpcija broj 2 - izmena podataka o ispitnom roku");
		System.out.println("\tOpcija broj 3 - brisanje podataka o ispitnom roku");
		System.out.println("\tOpcija broj 4 - ispis podataka svih ispitnih rokova");
		System.out.println("\tOpcija broj 5 - ispis podataka o odre\u0111enom Ispitnom roku sa njegovim Ispitnim prijavama");
		System.out.println("\tOpcija broj 6 - sortiranje ispitnih rokova po datumu");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");	
	}
	
	/** METODE ZA ISPIS ISPITNIH ROKOVA ****/
	//ispisi sve studente
	public static void ispisiSveIspitneRokove(){
		for (int i = 0; i < sviIspitniRokovi.size(); i++) {
			System.out.println(sviIspitniRokovi.get(i));
		}
	}
	
	/** METODE ZA PRETRAGU ISPITNIH ROKOVA****/
	//pronadji studenta
	public static IspitniRok pronadjiIspitniRok(){
		IspitniRok retVal = null;
		System.out.println("Unesi id ispitnog rok:");
		Integer id = PomocnaKlasa.ocitajCeoBroj();
		retVal = pronadjiIspitniRokId(id);
		if(retVal == null)
			System.out.println("Ispitni Rok sa id " +id + " ne postoji u evidenciji");
		return retVal;
	}
	
	//pronadji ispitni rok
	public static IspitniRok pronadjiIspitniRokId(int id){
		IspitniRok retVal = null;
		for (int i = 0; i < sviIspitniRokovi.size(); i++) {
			IspitniRok ir = sviIspitniRokovi.get(i);
			if (ir.getId()==id) {
				retVal = ir;
				break;
			}
		}
		return retVal;
	}
	
	public static int pronadjiPozicijuIspitnogRoka(){
		int retVal = -1;
		System.out.println("Unesi id ispitnog roka:");
		Integer id = PomocnaKlasa.ocitajCeoBroj();
		for (int i = 0; i < sviIspitniRokovi.size(); i++) {
			IspitniRok ir = sviIspitniRokovi.get(i);
			if (ir.getId() == id) {
				retVal = i;
				break;
			}
		}
		if(retVal == -1)
			System.out.println("Ispitni rok sa id " +id + " ne postoji u evidenciji");
		return retVal;
	}
	
	/** METODA ZA SORTIRANJE ISPITNIH ROKOVA****/
	public static void sortirajIspitneRokovePoDatumnu(){
		System.out.println("Ispitne rokove je mogu\u0107e sortirati\n\t1 - Datum Rastu\u0107e\n\t2- Datum Opadaju\u0107e\nIzaberi opciju:");
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
	
	/** METODE ZA UNOS, IZMENU i BRISANJE ISPITNOG ROKA****/
	
	//unos novog studenta
	public static void unosNovogIspitnogRoka(){
		System.out.print("Unesi naziv:");
		String irNaziv = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi pocetak:");
		String irPocetak = PomocnaKlasa.ocitajTekst();
		System.out.print("Unesi kraj:");
		String irKraj  = PomocnaKlasa.ocitajTekst();
	
		//ID atribut ce se dodeliti automatski
		IspitniRok ir = new IspitniRok(0,irNaziv, irPocetak, irKraj);
		sviIspitniRokovi.add(ir);
		
		while (PomocnaKlasa.ocitajOdlukuOPotvrdi("dodati ispitne prijave za ispitni rok") == 'Y') {
			//IspitnaPrijavaUI.dodajIspitnuPrijavuZaIspitniRok(ir);
		}
	}
	
	//izmena ispitnog roka
	public static void izmenaPodatakaOIspitnomRoku(){
		IspitniRok ir  = pronadjiIspitniRok();
		if(ir != null){
			System.out.print("Unesi naziv:");
			String irNaziv = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi pocetak:");
			String irPocetak = PomocnaKlasa.ocitajTekst();
			System.out.print("Unesi kraj:");
			String irKraj  = PomocnaKlasa.ocitajTekst();
			
			ir.setNaziv(irNaziv);
			ir.setPocetak(irPocetak);
			ir.setKraj(irKraj);
			
			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("ukloniti ispitne prijave za ispitni rok") == 'Y') {
				//IspitnaPrijavaUI.ukloniIspitnuPrijavuZaIspitniRok(ir);
			}
			
			while (PomocnaKlasa.ocitajOdlukuOPotvrdi("dodati ispitne prijave za ispitni rok") == 'Y') {
				//IspitnaPrijavaUI.dodajIspitnuPrijavuZaIspitniRok(ir);
			}
		}
	}
	
	//brisanje ispitnog roka
	public static void brisanjePodatakaOIspitnomRoku(){
		int pozicija = pronadjiPozicijuIspitnogRoka();
		if(pozicija!=-1){
			IspitniRok ir = sviIspitniRokovi.remove(pozicija);
			
			ArrayList<IspitnaPrijava> listIspOdPredmetaZaBrisanje = new ArrayList<IspitnaPrijava>(ir.getIspitnePrijave());
			
			//sada moramo da uklonim sve za ispitnu prijavu
			for (IspitnaPrijava isp : listIspOdPredmetaZaBrisanje) {
				IspitnaPrijavaUI.ukloniIspitnuPrijavu(isp);
			}
			
			
			System.out.println("Podaci obrisani iz evidencije");
		}
	}	
	
	/** METODA ZA UCITAVANJE PODATAKA****/
	static void citajIzFajlaIspitneRokove(File dokument) throws IOException {
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
				sviIspitniRokovi.add(new IspitniRok(s2));
			}
			in.close();
		} else {
			System.out.println("Ne postoji fajl!");
		}
	}
	
	static void pisiUFajlIspitneRokove(File dokument) throws IOException {
		PrintWriter out2 = new PrintWriter(new FileWriter(dokument));
		for (IspitniRok ir : sviIspitniRokovi) {
			out2.println(ir.toFileRepresentation());
		}
		
		out2.flush();
		out2.close();
	}
}

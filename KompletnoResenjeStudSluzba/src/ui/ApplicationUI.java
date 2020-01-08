package ui;

import java.io.File;
import java.io.IOException;

import utils.PomocnaKlasa;


public class ApplicationUI {
	
	public static void ispisiTekstOsnovneOpcije(){	
		System.out.println("Studentska Sluzba - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Rad sa studentima");
		System.out.println("\tOpcija broj 2 - Rad sa predmetima");
		System.out.println("\tOpcija broj 3 - Rad sa nastavnicima");
		System.out.println("\tOpcija broj 4 - Rad sa ispitnim rokovima");
		System.out.println("\tOpcija broj 5 - Rad sa poha\u0111a");
		System.out.println("\tOpcija broj 6 - Rad sa predaje");
		System.out.println("\tOpcija broj 7 - Rad sa ispitnim prijavama");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");	
	}
	
	public static void main(String[] args) throws IOException {
		String sP = System.getProperty("file.separator");
		
		File studentiFajl = new File("."+sP+"data"+sP+"studenti.csv");
		StudentUI.citajIzFajlaStudente(studentiFajl);
		
		File predmetiFajl = new File("."+sP+"data"+sP+"predmeti.csv");
		PredmetUI.citajIzFajlaPredmete(predmetiFajl);
		
		File pohadjaFajl = new File("."+sP+"data"+sP+"pohadja.csv");
		PohadjaUI.citajIzFajlaPohadja(pohadjaFajl);
		
		File ispitniRokFajl = new File("."+sP+"data"+sP+"ispitni_rokovi.csv");
		IspitniRokUI.citajIzFajlaIspitneRokove(ispitniRokFajl);
		
		File ispitnePrijaveFajl = new File("."+sP+"data"+sP+"ispitne_prijave.csv");
		IspitnaPrijavaUI.citajIzFajlaIspitnePrijave(ispitnePrijaveFajl);
		
		int odluka = -1;
		while(odluka!= 0){
			ApplicationUI.ispisiTekstOsnovneOpcije();
			System.out.print("opcija:");
			odluka = PomocnaKlasa.ocitajCeoBroj();
			switch (odluka) {
				case 0:	
					System.out.println("Izlaz iz programa");	
					break;
				case 1:
					StudentUI.meniStudentUI();
					break;
				case 2:
					PredmetUI.meniPredmetUI();
					break;
				case 4:
					IspitniRokUI.meniIspitniRokUI();
					break;
				case 5:
					PohadjaUI.menuPohadjaUI();
					break;
				case 7:
					IspitnaPrijavaUI.menuIspitnaPrijavaUI();
					break;
				default:
					System.out.println("Nepostojeca komanda");
					break;
			}
		}
		
		StudentUI.pisiUFajlStudente(studentiFajl);
		PredmetUI.pisiUFajlPredmete(predmetiFajl);
		PohadjaUI.pisiUFajlPohadja(pohadjaFajl);
		IspitniRokUI.pisiUFajlIspitneRokove(ispitniRokFajl);
		IspitnaPrijavaUI.pisiUFajlIspitnePrijave(ispitnePrijaveFajl);
		System.out.print("Program izvrsen");
	}
}

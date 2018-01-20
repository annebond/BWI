package bwi.WS1718.PROG1.TUT;

import bwi.prog.utils.TextIO;

public class ShoeShop {

		public static void main(String[] args) {

			Schuhmodell[] produktkatalog = new Schuhmodell[100];
			Kunde[] kundenkartei = new Kunde[100];
			Schuhmodell[] verkaufteSchuhe = new Schuhmodell[100];

			TextIO.putln("Willkommen im Schuhshop!");
			while (true) {
				TextIO.putln("Operationscodes: \n" + "1 Neues Modell hinzufügen \n" + "2 Neuen Kunden hinzufügen \n"
						+ "3 Produktkatalog anzeigen\n" + "4 Kundenkartei anzeigen \n" + "5 Schuh verkaufen \n"
						+ "6 Bisherige Tageseinnahmen \n" + "7 Besten Kunden anzeigen \n" + "0 Programm beenden");
				int operation = TextIO.getlnInt();
				switch (operation) {
				case 1:
					TextIO.putln("Modell hinzufügen");
					TextIO.putln("Modellname:");
					String modellname = TextIO.getln();
					TextIO.putln("Hersteller:");
					String hersteller = TextIO.getln();
					TextIO.putln("Preis:");
					float preis = TextIO.getlnFloat();
					modellHinzufuegen(produktkatalog, modellname, hersteller, preis);
					break;
				case 3:
					printSchuhKatalog(produktkatalog);
					break;
				case 0:
					return;
				}

			}
		}

		/**
		 * Gibt alle Schuhe bis zum ersten null wert in der Konsole aus
		 * @param produktkatalog
		 */
		private static void printSchuhKatalog(Schuhmodell[] produktkatalog) {
			for (Schuhmodell tempSchuh : produktkatalog) {
				if(tempSchuh!=null) {
					TextIO.putln(tempSchuh.toString());
				} else {
					return;
				}
			}

		}

		/**
		 * Fügt modeele hinzu...
		 * 
		 * @param produktkatalog
		 *            - macht so und so
		 * @param modellname
		 *            - sdfasdas
		 * @param hersteller
		 * @param preis
		 */
		private static void modellHinzufuegen(Schuhmodell[] produktkatalog, String modellname, String hersteller,
				float preis) {
			Schuhmodell neuerSchuh = new Schuhmodell();
			neuerSchuh.Modell = modellname;
			neuerSchuh.Herstellermarke = hersteller;
			neuerSchuh.Preis = preis;
			for (int i = 0; i < produktkatalog.length; i++) {
				if (produktkatalog[i] == null) {
					produktkatalog[i] = neuerSchuh;
					break;
				}
			}

		}
	}

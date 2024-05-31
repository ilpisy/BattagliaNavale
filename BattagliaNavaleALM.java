import java.util.*;

public class BattagliaNavaleALM {

	public static void main(String[] args) {
		ArrayList<ArrayList<String>> marePL = new ArrayList<>();
		ArrayList<ArrayList<String>> marePC = new ArrayList<>();
		ArrayList<ArrayList<String>> marePCG = new ArrayList<>();
		ArrayList<Integer> rigacolonna = new ArrayList<Integer>();
		ArrayList<Integer> l = new ArrayList<Integer>();
		ArrayList<Integer> w = new ArrayList<Integer>();
		String s = "?";
		Scanner t = new Scanner(System.in);
		int v = 0;

		System.out.println(
				"Benvenuto in battaglia navale, hai a disposizione uno spazio 7x7 per posizionare 3 navi lunghe 3 caselle e altre 3 lunghe 2 caselle");
		System.out.println(
				"Ogni turno ti alternerai al computer e avrai un colpo per turno in cui potrai cercare di colpire le navi del PC e cercare di vincere");
		System.out.println(
				"Se becchi una nave la casella selezionata si trasormerà in 'O' mentre se si manca in 'X' stessa cosa vale per il tuo avversario con il tuo mare");

		for (int i = 0; i < 7; i++) {
			ArrayList<String> riga = new ArrayList<>();
			for (int j = 0; j < 7; j++) {
				riga.add("~");
			}
			marePL.add(riga);						
		}
		for (int i = 0; i < 7; i++) {
			ArrayList<String> rigabot = new ArrayList<>();
			for (int j = 0; j < 7; j++) {
				rigabot.add("~");
			}
			marePC.add(rigabot);
		}
		for (int i = 0; i < 7; i++) {
			ArrayList<String> rigabotG = new ArrayList<>();
			for (int j = 0; j < 7; j++) {
				rigabotG.add("~");
			}
			marePCG.add(rigabotG);
		}
		System.out.println("Posiziona 3 navi lunghe 3");
		switchScelta3(s, t, rigacolonna, marePL);
		System.out.println("Ora posiziona 3 navi lunghe 2");
		switchScelta2(s, t, rigacolonna, marePL);
		System.out.println("Caricamento del mare avversario...");
		switchSceltaBot3(s, t, rigacolonna, marePC);
		switchSceltaBot2(s, t, rigacolonna, marePC);
		do {
			gioco(v, rigacolonna, marePC, marePL, t, marePCG, l, w);
		} while (v == 0);
		t.close();
	}

	public static int gioco(int v, ArrayList<Integer> rigacolonna, ArrayList<ArrayList<String>> marePC,
			ArrayList<ArrayList<String>> marePL, Scanner t, ArrayList<ArrayList<String>> marePCG, ArrayList<Integer> l,ArrayList<Integer> q) {
		for (int u = 0; u < 1; u++) {
			System.out.println("Scegli una coordinata da colpire all'avversario");
			rigaColonna(rigacolonna, t);
			if (marePCG.get(rigacolonna.get(0)).get(rigacolonna.get(1)) != "O"
					&& marePCG.get(rigacolonna.get(0)).get(rigacolonna.get(1)) != "X") {
				if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "N") {
					System.out.println("-----COLPITO-----");
					marePCG.get(rigacolonna.get(0)).set(rigacolonna.get(1), "O");
					l.add(0);
					System.out.println("                      Navi colpite: "+l.size()+"/15");
				} else {
					System.out.println("-----mancato-----");
					marePCG.get(rigacolonna.get(0)).set(rigacolonna.get(1), "X");
					System.out.println("                      Navi colpite: "+l.size()+"/15");
				}
			} else {
				System.out.println("!!!Hai già colpito questo punto!!!");
				u -= 1;
			}

		}
		stampaMareBot(marePCG);
		System.out.println();
		System.out.println("----------TURNO DEL BOT----------");
		System.out.println();
		for (int u = 0; u < 1; u++) {
			rigacolonna.add(0, (int) (Math.random() * 7));
			rigacolonna.add(1, (int) (Math.random() * 7));
			if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "X"
					|| marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "O") {
				u = u - 1;
			} else {
				if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "N") {
					System.out.println("ti ha colpito");
					marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "O");
					q.add(0);
					System.out.println("                      Navi colpite dal bot: "+q.size()+"/15");
				} else {
					System.out.println("______ti ha mancato______");
					marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "X");
					System.out.println("                      Navi colpite dal bot: "+q.size()+"/15");
				}
				stampaMare(marePL);
			}
		}
		if (q.size()>= 15) {
			System.out.println("_____HAI PERSO, il bot ha distrutto tutte le tue navi_____");
			System.exit(0);
		}
		if (l.size() >= 15) {
			System.out.println();
			System.out.println(" YYY    YYY   OOOOOOOO   UU    UU       WW      WW      WW  OOOOOOOO   NNNN   NNNN");
	        System.out.println("   YY  YY    OO    OO   UU    UU        WW     WW     WW   OO    OO    NNNN   NN ");
	        System.out.println("     YY      OO    OO   UU    UU         WW   WWWW   WW    OO    OO    NN NN  NN ");
	        System.out.println("     YY      OO    OO   UU    UU          WW WW  WW WW     OO    OO    NN  NN NN ");
	        System.out.println("     YY      OOOOOOOO   UUUUUUUU           WW      WW      OOOOOOOO   NNNN   NNNN");
			System.exit(0);
		}

		return v;
	}

	public static ArrayList<ArrayList<String>> stampaMare(ArrayList<ArrayList<String>> marePL) {
		int c = 0;
		System.out.println("  0  1  2  3  4  5  6   <----- Colonne");
		for (ArrayList<String> riga : marePL) {
			System.out.print(c);
			for (String elemento : riga) {
				System.out.print(" " + elemento + " ");
			}
			System.out.println();
			c++;
		}
		System.out.println("|______ Righe");
		return marePL;
	}

	public static ArrayList<ArrayList<String>> stampaMareBot(ArrayList<ArrayList<String>> marePCG) {
		int c = 0;
		System.out.println("  0  1  2  3  4  5  6 ");
		for (ArrayList<String> rigabotG : marePCG) {
			System.out.print(c);
			for (String elemento : rigabotG) {
				System.out.print(" " + elemento + " ");
			}
			System.out.println();
			c++;
		}
		return marePCG;
	}

	public static ArrayList<ArrayList<String>> switchScelta3(String s, Scanner t, ArrayList<Integer> rigacolonna,
			ArrayList<ArrayList<String>> marePL) {
		for (int i = 0; i < 3; i++) {
			rigaColonna(rigacolonna, t);
			s = direzione(s, t);
			switch (s) {
			case "a":
				if (rigacolonna.get(0) >= 2) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0) - 1).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0) - 2).get(rigacolonna.get(1)) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0) - 1).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0) - 2).set(rigacolonna.get(1), "N");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			case "b":
				if (rigacolonna.get(0) <= 4) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0) + 1).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0) + 2).get(rigacolonna.get(1)) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0) + 1).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0) + 2).set(rigacolonna.get(1), "N");
						System.out.println("   0  1  2  3  4  5  6 ");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			case "d":
				if (rigacolonna.get(1) <= 4) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1) + 1) == "~"
							&& marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1) + 2) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1) + 1, "N");
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1) + 2, "N");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			case "s":
				if (rigacolonna.get(1) >= 2) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1) - 1) == "~"
							&& marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1) - 2) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1) - 1, "N");
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1) - 2, "N");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			default:
				System.out.println("devi inserire una di queste lettere: a, b, d, s");
				i -= 1;
			}
		}
		return marePL;
	}

	public static ArrayList<ArrayList<String>> switchScelta2(String s, Scanner t, ArrayList<Integer> rigacolonna,

			ArrayList<ArrayList<String>> marePL) {
		for (int i = 0; i < 3; i++) {
			rigaColonna(rigacolonna, t);
			s = direzione(s, t);
			switch (s) {
			case "a":
				if (rigacolonna.get(0) >= 1) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0) - 1).get(rigacolonna.get(1)) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0) - 1).set(rigacolonna.get(1), "N");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			case "b":
				if (rigacolonna.get(0) <= 5) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0) + 1).get(rigacolonna.get(1)) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0) + 1).set(rigacolonna.get(1), "N");
						System.out.println("   0  1  2  3  4  5  6 ");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			case "d":
				if (rigacolonna.get(1) <= 5) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1) + 1) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1) + 1, "N");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			case "s":
				if (rigacolonna.get(1) >= 1) {
					if (marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePL.get(rigacolonna.get(0)).get(rigacolonna.get(1) - 1) == "~") {
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePL.get(rigacolonna.get(0)).set(rigacolonna.get(1) - 1, "N");
						stampaMare(marePL);
					} else {
						System.out.println("Non puoi sovrapporre le navi, rifai");
						i -= 1;
					}
				} else {
					System.out.println("hai sforato, rifai");
					i -= 1;
				}
				break;
			default:
				System.out.println("devi inserire una di queste lettere: a, b, d, s");
				i -= 1;
			}
		}
		return marePL;
	}

	public static ArrayList<ArrayList<String>> switchSceltaBot3(String s, Scanner t, ArrayList<Integer> rigacolonna,
			ArrayList<ArrayList<String>> marePC) {
		s = "0";

		for (int i = 0; i < 3; i++) {
			rigacolonna.add(0, (int) (Math.random() * 7));
			rigacolonna.add(1, (int) (Math.random() * 7));
			int sr = (int) (Math.random() * 4);
			if (sr == 0) {
				s = "a";
			}
			if (sr == 1) {
				s = "b";
			}
			if (sr == 2) {
				s = "c";
			}
			if (sr == 3) {
				s = "d";
			}
			switch (s) {
			case "a":
				if (rigacolonna.get(0) >= 2) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0) - 1).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0) - 2).get(rigacolonna.get(1)) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0) - 1).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0) - 2).set(rigacolonna.get(1), "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			case "b":
				if (rigacolonna.get(0) <= 4) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0) + 1).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0) + 2).get(rigacolonna.get(1)) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0) + 1).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0) + 2).set(rigacolonna.get(1), "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			case "d":
				if (rigacolonna.get(1) <= 4) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1) + 1) == "~"
							&& marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1) + 2) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1) + 1, "N");
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1) + 2, "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			case "s":
				if (rigacolonna.get(1) >= 2) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1) - 1) == "~"
							&& marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1) - 2) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1) - 1, "N");
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1) - 2, "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			default:
				i -= 1;
			}
		}
		return marePC;
	}

	public static ArrayList<ArrayList<String>> switchSceltaBot2(String s, Scanner t, ArrayList<Integer> rigacolonna,
			ArrayList<ArrayList<String>> marePC) {
		s = "0";
		for (int i = 0; i < 3; i++) {
			rigacolonna.add(0, (int) (Math.random() * 7));
			rigacolonna.add(1, (int) (Math.random() * 7));
			int sr = (int) (Math.random() * 4);
			if (sr == 0) {
				s = "a";
			}
			if (sr == 1) {
				s = "b";
			}
			if (sr == 2) {
				s = "c";
			}
			if (sr == 3) {
				s = "d";
			}
			switch (s) {
			case "a":
				if (rigacolonna.get(0) >= 1) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0) - 1).get(rigacolonna.get(1)) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0) - 1).set(rigacolonna.get(1), "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			case "b":
				if (rigacolonna.get(0) <= 5) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0) + 1).get(rigacolonna.get(1)) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0) + 1).set(rigacolonna.get(1), "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			case "d":
				if (rigacolonna.get(1) <= 5) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1) + 1) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1) + 1, "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			case "s":
				if (rigacolonna.get(1) >= 1) {
					if (marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1)) == "~"
							&& marePC.get(rigacolonna.get(0)).get(rigacolonna.get(1) - 1) == "~") {
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1), "N");
						marePC.get(rigacolonna.get(0)).set(rigacolonna.get(1) - 1, "N");
					} else {
						i -= 1;
					}
				} else {
					i -= 1;
				}
				break;
			default:
				i -= 1;
			}
		}
		return marePC;
	}

	public static ArrayList<Integer> rigaColonna(ArrayList<Integer> rigacolonna, Scanner t) {
		rigacolonna.removeAll(rigacolonna);
		for (int i = 0; i < 1; i++) {
			System.out.println("Inserisci le coordinate");
			System.out.println("Riga");
			rigacolonna.add(0, t.nextInt());
			System.out.println("Colonna");
			rigacolonna.add(1, t.nextInt());
			if (rigacolonna.get(0) > 7 || rigacolonna.get(0) < 0 || rigacolonna.get(1) > 7 || rigacolonna.get(0) < 0) {
				System.out.println(" i numeri inseriti non sono validi, bisogna inserire due numeri interi < 7 e >=0");
				i -= 1;
			}
		}

		return rigacolonna;
	}

	public static String direzione(String s, Scanner t) {

		System.out.println("a b d s?");
		s = t.next();

		return s;
	}

}
